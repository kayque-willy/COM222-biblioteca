package biblioteca.funcionario;

import java.util.List;

public class FuncionarioController {

    /*--------- Atributos ---------*/
    private Funcionario funcionario;
    private FuncionarioDAO dao;
    private List<Funcionario> funcionarios;

    /*--------- Métodos ---------*/
    public FuncionarioController() {
        this.funcionario = new Funcionario();
        this.dao = new FuncionarioDAO();
        this.funcionarios = dao.listar();
    }

    public List<Funcionario> filtrar(int codigo, String nome) {
        return dao.filtrar(codigo, nome);
    }
    
    /*--------- Get e Set ---------*/
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public FuncionarioDAO getDao() {
        return dao;
    }

    public void setDao(FuncionarioDAO dao) {
        this.dao = dao;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

}
