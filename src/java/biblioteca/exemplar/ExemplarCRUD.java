package biblioteca.exemplar;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExemplarCRUD extends HttpServlet {

    /*--------- Atributos ---------*/
    private Exemplar exemplar;
    private ExemplarDAO dao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        exemplar = new Exemplar();
        exemplar.setNumero(request.getParameter("numero") != null ? Integer.valueOf(request.getParameter("numero")) : 0);
        exemplar.setPreco(request.getParameter("preco") != null ? Double.valueOf(request.getParameter("preco")) : 0.0D);
        exemplar.setPublicacao_isbn(request.getParameter("publicacao") != null ? Integer.valueOf(request.getParameter("publicacao")) : 0);
        exemplar.setStatus(request.getParameter("status") != null ? request.getParameter("status") : "");
        exemplar.setStatus("Livre");
        dao = new ExemplarDAO();
        if (request.getParameter("tipo") != null && request.getParameter("tipo").equals("cadastro")) {
            if (dao.inserir(exemplar)) {
                //RequestDispatcher dispatcher = request.getRequestDispatcher("pages/exemplar.jsp");
                //dispatcher.forward(request, response);
                response.sendRedirect("/biblioteca/pages/exemplar.jsp");
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
