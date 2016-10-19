package biblioteca.emprestimo;

import biblioteca.dao.DAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO extends DAO {

    public boolean alterar(Emprestimo emprestimo) {
        try {
            Connection conexao = getConexao();
            PreparedStatement pstmt = conexao.prepareStatement("Update emprestimo SET status = ? , data_devolucao = ? WHERE id = ?");
            pstmt.setString(1, emprestimo.getStatus());
            pstmt.setDate(2, emprestimo.getData_devolucao());
            pstmt.setInt(3, emprestimo.getId());
            pstmt.execute();
            pstmt.close();
            conexao.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean inserir(Emprestimo emprestimo) {
        try {
            Connection conexao = getConexao();
            PreparedStatement pstm = conexao.prepareStatement("Insert into emprestimo "
                    + "(exemplar_numero,"
                    + "publicacao_isbn,"
                    + "data_emprestimo,"
                    + "status,"
                    + "associado_codigo,"
                    + "data_maxima) values (?,?,?,?,?,?)");
            pstm.setInt(1, emprestimo.getExemplar_numero());
            pstm.setInt(2, emprestimo.getPublicacao_ISBN());
            pstm.setDate(3, emprestimo.getData_emprestimo());
            pstm.setString(4, emprestimo.getStatus());
            pstm.setInt(5, emprestimo.getAssociado_codigo());
            pstm.setDate(6, emprestimo.getData_maxima());
            pstm.execute();
            pstm.close();
            conexao.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Emprestimo> listar() {
        List<Emprestimo> lista = new ArrayList<>();
        try {
            Connection conexao = getConexao();
            Statement stm = conexao.createStatement();
            ResultSet rs = stm.executeQuery("Select * from emprestimo");
            Emprestimo emprestimo;
            while (rs.next()) {
                emprestimo = new Emprestimo();
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setExemplar_numero(rs.getInt("exemplar_numero"));
                emprestimo.setPublicacao_ISBN(rs.getInt("publicacao_ISBN"));
                emprestimo.setStatus(rs.getString("status"));
                emprestimo.setData_emprestimo(rs.getDate("data_emprestimo"));
                emprestimo.setData_devolucao(rs.getDate("data_devolucao"));
                emprestimo.setAssociado_codigo(rs.getInt("associado_codigo"));
                lista.add(emprestimo);
            }
            stm.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Emprestimo> filtrar(int id, String isbn) {
        try {
            List<Emprestimo> lista = new ArrayList<Emprestimo>();
            Connection conexao = getConexao();
            PreparedStatement pstm = null;
            if (id != 0 && (isbn == null || isbn.isEmpty())) {
                pstm = conexao.
                        prepareStatement("Select * from emprestimo where id = ?");
                pstm.setInt(1, id);
            } else if (id == 0 && (isbn != null && !isbn.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from emprestimo where publicacao_ISBN = ?");
                pstm.setString(1, isbn);
            } else if (id != 0 && (isbn != null && !isbn.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from emprestimo where id = ? and publicacao_ISBN = ?");
                pstm.setInt(1, id);
                pstm.setString(2, isbn);
            } else if (id == 0 && (isbn == null || isbn.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from emprestimo");
            }

            ResultSet rs = pstm.executeQuery();
            Emprestimo emprestimo;
            if (rs.next()) {
                emprestimo = new Emprestimo();
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setExemplar_numero(rs.getInt("exemplar_numero"));
                emprestimo.setPublicacao_ISBN(rs.getInt("publicacao_isbn"));
                emprestimo.setStatus(rs.getString("status"));
                emprestimo.setData_emprestimo(rs.getDate("data_emprestimo"));
                emprestimo.setData_devolucao(rs.getDate("data_devolucao"));
                emprestimo.setAssociado_codigo(rs.getInt("associado_codigo"));
                lista.add(emprestimo);
            }
            pstm.close();
            conexao.close();
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//	public void excluir(Exemplar exemplar) {
//		try {
//			Connection conexao = getConexao();
//			PreparedStatement pstm = conexao
//					.prepareStatement("Delete from	tbexemplar where matricula = ? ");
//			pstm.setLong(1, exemplar.getMatricula());
//			pstm.execute();
//			pstm.close();
//			conexao.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
// 
//	public boolean existe(String email, String senha) {
//		boolean achou = false;
//		try {
//			Connection conexao = getConexao();
//			PreparedStatement pstm = conexao
//					.prepareStatement("Select * from exemplar where email = ? and senha = ?");
//			pstm.setString(1, email);
//                        pstm.setString(2, senha);
//			ResultSet rs = pstm.executeQuery();
//			if (rs.next()) {
//				achou = true;
//			}
//			pstm.close();
//			conexao.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return achou;
//	}
//  
    /**/
    List<Emprestimo> listarPorAssociado(int associadoCodigo) {
        List<Emprestimo> lista = new ArrayList<>();
        try {
            Connection conexao = getConexao();
            Statement stm = conexao.createStatement();
            ResultSet rs = stm.executeQuery("Select * from emprestimo where associado_codigo = " + associadoCodigo);
            Emprestimo emprestimo;
            while (rs.next()) {
                emprestimo = new Emprestimo();
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setExemplar_numero(rs.getInt("exemplar_numero"));
                emprestimo.setPublicacao_ISBN(rs.getInt("publicacao_ISBN"));
                emprestimo.setStatus(rs.getString("status"));
                emprestimo.setData_emprestimo(rs.getDate("data_emprestimo"));
                emprestimo.setData_devolucao(rs.getDate("data_devolucao"));
                emprestimo.setAssociado_codigo(rs.getInt("associado_codigo"));
                emprestimo.setData_maxima(rs.getDate("data_maxima"));
                lista.add(emprestimo);
            }
            stm.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
