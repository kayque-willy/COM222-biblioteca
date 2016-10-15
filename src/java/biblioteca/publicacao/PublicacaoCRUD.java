package biblioteca.publicacao;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PublicacaoCRUD extends HttpServlet {

    /*--------- Atributos ---------*/
    private Publicacao publicacao;
    private PublicacaoDAO dao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        publicacao = new Publicacao();
        publicacao.setAno(request.getParameter("ano") != null ? Integer.valueOf(request.getParameter("ano")) : 0);
        publicacao.setAutor(request.getParameter("autor") != null ? request.getParameter("autor") : "");
        publicacao.setEditora(request.getParameter("editora") != null ? request.getParameter("editora") : "");
        publicacao.setFuncionario_codigo(request.getParameter("funcionario") != null ? Integer.valueOf(request.getParameter("funcionario")) : 0);
        publicacao.setIsbn(request.getParameter("isbn") != null ? Integer.valueOf(request.getParameter("isbn")) : 0);
        publicacao.setTitulo(request.getParameter("titulo") != null ? request.getParameter("titulo") : "");

        dao = new PublicacaoDAO();
        if (request.getParameter("tipo") != null && request.getParameter("tipo").equals("cadastro")) {
            if (dao.inserir(publicacao)) {
                //RequestDispatcher dispatcher = request.getRequestDispatcher("pages/publicacao.jsp");
                //dispatcher.forward(request, response);
                response.sendRedirect("/biblioteca/pages/publicacao.jsp");
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
