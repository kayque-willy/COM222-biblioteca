package biblioteca.emprestimo;

import biblioteca.associado.Associado;
import biblioteca.associado.AssociadoDAO;
import biblioteca.exemplar.Exemplar;
import biblioteca.exemplar.ExemplarDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EmprestimoCRUD", urlPatterns = {"/EmprestimoCRUD"})
public class EmprestimoCRUD extends HttpServlet {

    /*--------- Atributos ---------*/
    private Emprestimo emprestimo;
    private EmprestimoDAO emprestimo_dao;
    private ExemplarDAO exemplar_dao;
    private AssociadoDAO associado_dao;
    private Exemplar exemplar;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //CADASTRA NOVO EMPRÉSTIMO
        if (request.getParameter("tipo-operacao") != null && request.getParameter("tipo-operacao").equals("cadastrar")) {
            //Cria o objeto exemplar para atualizar o status
            exemplar = new Exemplar();
            exemplar.setNumero(request.getParameter("numero") != null ? Integer.valueOf(request.getParameter("numero")) : 0);
            exemplar.setPublicacao_isbn(request.getParameter("isbn") != null ? Integer.valueOf(request.getParameter("isbn")) : 0);
            exemplar.setStatus("Emprestado");

            //Consulta a disponibilidade do exemplar
            exemplar_dao = new ExemplarDAO();
            associado_dao = new AssociadoDAO();
            List<Exemplar> verifica;

            //Verifica se o exemplar esta disponível
            verifica = exemplar_dao.filtrar(exemplar.getNumero(), null);
            for (Exemplar c : verifica) {
                //Verifica se o exemplar esta disponível para empréstimo
                if (c.getStatus().equals("Livre")) {
                    //Atualizar o status do exemplar para emprestado
                    String tipoAssociado = "";
                    tipoAssociado = associado_dao.consultaTipo(Integer.valueOf(request.getParameter("codigo_associado")));
                    //Cadastra o empréstimo
                    emprestimo = new Emprestimo();
                    if (tipoAssociado.equals("Grad")) {
                        emprestimo.setData_maxima(new Date(Date.valueOf(request.getParameter("data_emprestimo")).getTime() + 604800000L));
                    } else if (tipoAssociado.equals("Pos-Grad")) {
                        emprestimo.setData_maxima(new Date(Date.valueOf(request.getParameter("data_emprestimo")).getTime() + 864000000L));
                    } else if (tipoAssociado.equals("Prof")) {
                        emprestimo.setData_maxima(new Date(Date.valueOf(request.getParameter("data_emprestimo")).getTime() + 1209600000L));
                    }
                    emprestimo.setExemplar_numero(request.getParameter("numero") != null ? Integer.valueOf(request.getParameter("numero")) : 0);
                    emprestimo.setPublicacao_ISBN(request.getParameter("isbn") != null ? Integer.valueOf(request.getParameter("isbn")) : 0);
                    emprestimo.setData_emprestimo(request.getParameter("data_emprestimo") != null ? Date.valueOf(request.getParameter("data_emprestimo")) : null);
                    emprestimo.setAssociado_codigo(request.getParameter("codigo_associado") != null ? Integer.valueOf(request.getParameter("codigo_associado")) : 0);
                    emprestimo.setStatus("Emprestado");
                    emprestimo_dao = new EmprestimoDAO();
                    if (emprestimo_dao.inserir(emprestimo)) {
                        if (exemplar_dao.alterar(exemplar)) {
                            response.sendRedirect("/biblioteca/pages/emprestimo.jsp");
                        }
                    }
                } else {
                    response.sendRedirect("/biblioteca/pages/emprestimo.jsp");
                }
            }
            //ATUALIZA O EMPRÉSTIMO PARA DEVOLUÇÃO    
        } else if (request.getParameter("tipo-operacao") != null && request.getParameter("tipo-operacao").equals("atualizar")) {

            //Recebe os parâmetros do empréstimo
            emprestimo = new Emprestimo();
            emprestimo.setId(request.getParameter("numero") != null ? Integer.valueOf(request.getParameter("numero")) : 0);
            emprestimo.setData_devolucao(request.getParameter("data_devolucao") != null ? Date.valueOf(request.getParameter("data_devolucao")) : null);
            emprestimo.setStatus("Devolvido");

            //Recupera os dados do emprestimo
            emprestimo_dao = new EmprestimoDAO();
            List<Emprestimo> consulta;
            consulta = emprestimo_dao.filtrar(emprestimo.getId(), null);

            //Consulta o empréstimo 
            for (Emprestimo f : consulta) {
                //Datas para calcular os dias de atraso
                Date data_devolucao = emprestimo.getData_devolucao();
                Date data_emprestimo = f.getData_emprestimo();

                //Só realiza a devolução se data de empréstimo for maior ou igual a data de devolução
                if (data_devolucao.compareTo(data_emprestimo) >= 0) {

                    //Altera o status do empréstimo para Devolvido
                    if (emprestimo_dao.alterar(emprestimo)) {

                        if (f.getStatus().equals("Emprestado")) {
                            //Atualiza o status do exemplar
                            exemplar = new Exemplar();
                            exemplar.setNumero(f.getExemplar_numero());
                            exemplar.setPublicacao_isbn(f.getPublicacao_ISBN());
                            exemplar.setStatus("Livre");
                            exemplar_dao = new ExemplarDAO();

                            //Altera o status do exemplar para Livre
                            if (exemplar_dao.alterar(exemplar)) {

                                //Verifica o tipo de associado
                                AssociadoDAO associadoDAO = new AssociadoDAO();
                                List<Associado> associado;
                                associado = associadoDAO.filtrar(f.getAssociado_codigo(), null, null);

                                for (Associado ac : associado) {
                                    //Calcula o atraso
                                    Calendar a = Calendar.getInstance();
                                    Calendar b = Calendar.getInstance();
                                    //data maior
                                    a.setTime(data_devolucao);
                                    //data menor
                                    b.setTime(data_emprestimo);
                                    //Calculo de atraso
                                    a.add(Calendar.DATE, -b.get(Calendar.DAY_OF_MONTH));
                                    //Dias de atraso
                                    double dias = a.get(Calendar.DAY_OF_MONTH);

                                    //Se a data de entrega for menor ou igual que a data de empréstimo, desconsidera o calculo
                                    if (data_devolucao.compareTo(data_emprestimo) == 0) {
                                        dias = 0;
                                    }

                                    //Mensagem da multa
                                    response.setContentType("text/html");
                                    PrintWriter out = response.getWriter();

                                    //Se o associado tiver multa, exibe a mensagem
                                    if (ac.getTipo().equals("Grad") && dias > 7) {
                                        out.println("<h2 class='info'>O aluno de graduação tem uma multa de R$:" + NumberFormat.getCurrencyInstance().format((dias - 7)) + " pelo atraso na devolução.</h2>");
                                        out.println("<br><a href='/biblioteca/pages/emprestimo.jsp'>Ok</a>");
                                    } else if (ac.getTipo().equals("Posgrad") && dias > 10) {
                                        out.println("<h2 class='info'>O aluno de pós graduação tem uma multa de R$:" + NumberFormat.getCurrencyInstance().format((dias - 10)) + " pelo atraso na devolução.</h2>");
                                        out.println("<br><a href='/biblioteca/pages/emprestimo.jsp'>Ok</a>");
                                    } else if (ac.getTipo().equals("Prof") && dias > 14) {
                                        out.println("<h2 class='info'>O professor tem uma multa de R$:" + NumberFormat.getCurrencyInstance().format((dias - 14)) + " pelo atraso na devolução.</h2>");
                                        out.println("<br><a href='/biblioteca/pages/emprestimo.jsp'>Ok</a>");
                                    } else {
                                        response.sendRedirect("/biblioteca/pages/emprestimo.jsp");
                                    }
                                }
                            }
                        }
                    }
                } else {
                    response.sendRedirect("/biblioteca/pages/emprestimo.jsp");
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }

}
