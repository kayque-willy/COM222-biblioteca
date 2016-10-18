package biblioteca.emprestimo;

import biblioteca.exemplar.Exemplar;
import biblioteca.publicacao.Publicacao;
import biblioteca.publicacao.PublicacaoDAO;
import java.util.List;

public class EmprestimoController {

    /*--------- Atributos ---------*/
    private Emprestimo emprestimo;
    private EmprestimoDAO dao;
    private List<Emprestimo> emprestimos;
   
    /*--------- Métodos ---------*/
    public EmprestimoController() {
        this.emprestimo = new Emprestimo();
        this.dao = new EmprestimoDAO();
        this.emprestimos = dao.listar();
    }
    
    public List<Emprestimo> filtrar(int id, String isbn) {
        return dao.filtrar(id, isbn);
    }

    /*--------- Get e Set ---------*/
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public EmprestimoDAO getDao() {
        return dao;
    }

    public void setDao(EmprestimoDAO dao) {
        this.dao = dao;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }
    
}