package biblioteca.exemplar;

import biblioteca.publicacao.Publicacao;
import biblioteca.publicacao.PublicacaoDAO;
import java.util.List;

public class ExemplarController {

    /*--------- Atributos ---------*/
    private Exemplar exemplar;
    private ExemplarDAO dao;
    private PublicacaoDAO publicacaodao;
    private List<Exemplar> exemplars;
    private List<Publicacao> publicacoes;

    /*--------- Métodos ---------*/
    public ExemplarController() {
        this.exemplar = new Exemplar();
        this.dao = new ExemplarDAO();
        this.publicacaodao = new PublicacaoDAO();
        this.exemplars = dao.listar();
        this.publicacoes = publicacaodao.listar();
    }

    public List<Exemplar> filtrar(int numero, String isbn) {
        return dao.filtrar(numero, isbn);
    }

    /*--------- Get e Set ---------*/
    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public List<Exemplar> getExemplars() {
        return exemplars;
    }

    public void setExemplars(List<Exemplar> exemplars) {
        this.exemplars = exemplars;
    }

    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(List<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

}
