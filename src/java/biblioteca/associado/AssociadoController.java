package biblioteca.associado;

import biblioteca.associado.*;
import java.util.List;

public class AssociadoController {

    /*--------- Atributos ---------*/
    private Associado associado;
    private AssociadoDAO dao;
    private List<Associado> associados;

    /*--------- Métodos ---------*/
    public AssociadoController() {
        this.associado = new Associado();
        this.dao = new AssociadoDAO();
        this.associados = dao.listar();
    }

    public List<Associado> filtrar(int codigo, String nome, String email) {
        return dao.filtrar(codigo, nome, email);
    }
    
    /*--------- Get e Set ---------*/
    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    public AssociadoDAO getDao() {
        return dao;
    }

    public void setDao(AssociadoDAO dao) {
        this.dao = dao;
    }

    public List<Associado> getAssociados() {
        return associados;
    }

    public void setAssociados(List<Associado> associados) {
        this.associados = associados;
    }

}
