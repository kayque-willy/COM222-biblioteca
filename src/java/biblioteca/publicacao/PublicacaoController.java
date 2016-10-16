package biblioteca.publicacao;

import biblioteca.funcionario.Funcionario;
import biblioteca.funcionario.FuncionarioDAO;
import java.util.List;

public class PublicacaoController {

    /*--------- Atributos ---------*/
    private Publicacao publicacao;
    private PublicacaoDAO dao;
    private FuncionarioDAO funcionariodao;
    private List<Publicacao> publicacaos;
    private List<Funcionario> funcionarios;

    /*--------- Métodos ---------*/
    public PublicacaoController() {
        this.publicacao = new Publicacao();
        this.dao = new PublicacaoDAO();
        this.publicacaos = dao.listar();
        this.funcionarios = funcionariodao.listar();
    }

    public List<Publicacao> filtrar(String isbn, String titulo) {
        return dao.filtrar(isbn, titulo);
    }

    /*--------- Get e Set ---------*/
    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public List<Publicacao> getPublicacaos() {
        return publicacaos;
    }

    public void setPublicacaos(List<Publicacao> publicacaos) {
        this.publicacaos = publicacaos;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

}
