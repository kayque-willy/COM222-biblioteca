package biblioteca.publicacao;

import java.util.List;

public class PublicacaoController {

    /*--------- Atributos ---------*/
    private Publicacao publicacao;
    private PublicacaoDAO dao;
    private List<Publicacao> publicacaos;

    /*--------- Métodos ---------*/
    public PublicacaoController() {
        this.publicacao = new Publicacao();
        this.dao = new PublicacaoDAO();
        this.publicacaos = dao.listar();
    }

    public List<Publicacao> filtrar(int codigo, String nome, String email) {
        return dao.filtrar(codigo, nome, email);
    }
    
    /*--------- Get e Set ---------*/
    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public PublicacaoDAO getDao() {
        return dao;
    }

    public void setDao(PublicacaoDAO dao) {
        this.dao = dao;
    }

    public List<Publicacao> getPublicacaos() {
        return publicacaos;
    }

    public void setPublicacaos(List<Publicacao> publicacaos) {
        this.publicacaos = publicacaos;
    }

}
