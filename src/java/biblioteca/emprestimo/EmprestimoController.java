package biblioteca.emprestimo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public List<Emprestimo> emprestimosAssociado(int associadoCodigo) {
        List<Emprestimo> es = new ArrayList<Emprestimo>();
        es = dao.listarPorAssociado(associadoCodigo);
        Date hoje = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        for (Emprestimo e : es) {
            if (!e.getStatus().equals("Devolvido")) {
                Long dif = (hoje.getTime() - e.getData_maxima().getTime());
                if (dif > 0){
                    e.setDias_atraso(Integer.valueOf(String.valueOf(dif/86400000L)));
                }else{
                    e.setDias_atraso(0);
                }
                if (e.getData_devolucao() == null){
                    e.setData_devolucao_view("-");
                }else{
                    e.setData_devolucao_view(dt.format(e.getData_devolucao()));
                }
            }
        }
        return es;
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
