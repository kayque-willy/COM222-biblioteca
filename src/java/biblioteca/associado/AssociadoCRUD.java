package biblioteca.associado;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AssociadoCRUD extends HttpServlet {

    /*--------- Atributos ---------*/
    private Associado associado;
    private AssociadoDAO dao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        associado = new Associado();
        associado.setCodigo(request.getParameter("codigo") != null ? Integer.valueOf(request.getParameter("codigo")) : 0);
        associado.setEmail(request.getParameter("email") != null ? request.getParameter("email") : "");
        associado.setEndereco(request.getParameter("endereco") != null ? request.getParameter("endereco") : "");
        associado.setNome(request.getParameter("nome") != null ? request.getParameter("nome") : "");
        associado.setSenha(request.getParameter("senha") != null ? request.getParameter("senha") : "");
        associado.setStatus(request.getParameter("status") != null ? request.getParameter("status") : "");
        associado.setTipo(request.getParameter("tipo") != null ? request.getParameter("tipo") : "");

        dao = new AssociadoDAO();
        if (request.getParameter("tipo-operacao") != null && request.getParameter("tipo-operacao").equals("cadastro")) {
            if (dao.inserir(associado)) {
                //RequestDispatcher dispatcher = request.getRequestDispatcher("pages/associado.jsp");
                //dispatcher.forward(request, response);
                response.sendRedirect("/biblioteca/pages/associado.jsp");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
