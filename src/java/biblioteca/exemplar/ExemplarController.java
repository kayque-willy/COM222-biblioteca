package biblioteca.exemplar;

import biblioteca.exemplar.*;
import java.util.List;

public class ExemplarController {

    /*--------- Atributos ---------*/
    private Exemplar exemplar;
    private ExemplarDAO dao;
    private List<Exemplar> exemplars;

    /*--------- Métodos ---------*/
    public ExemplarController() {
        this.exemplar = new Exemplar();
        this.dao = new ExemplarDAO();
        this.exemplars = dao.listar();
    }

    public List<Exemplar> filtrar(int codigo, String nome, String email) {
        return dao.filtrar(codigo, nome, email);
    }
    
    /*--------- Get e Set ---------*/
    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public ExemplarDAO getDao() {
        return dao;
    }

    public void setDao(ExemplarDAO dao) {
        this.dao = dao;
    }

    public List<Exemplar> getExemplars() {
        return exemplars;
    }

    public void setExemplars(List<Exemplar> exemplars) {
        this.exemplars = exemplars;
    }

}
