package biblioteca.exemplar;

import biblioteca.dao.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExemplarDAO extends DAO {

//    public void alterar(Exemplar exemplar) {
//		try {
//			Connection conexao = getConexao();
// 
//			PreparedStatement pstmt = conexao
//					.prepareStatement("Update tbexemplar SET nome = ?, telefone = ?, email = ?, datacadastro = ?"
//							+ " WHERE matricula = ? ");
//			pstmt.setString(1, exemplar.getNome());
//			pstmt.setString(2, exemplar.getTelefone());
//			pstmt.setString(3, exemplar.getEmail());
//			pstmt.setDate(4, new java.sql.Date(exemplar.getDataCadastro().getTime()));
//			pstmt.setLong(5, exemplar.getMatricula());
//			pstmt.execute();
//			pstmt.close();
//			conexao.close();
// 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
// 
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
    public boolean inserir(Exemplar exemplar) {
        try {
            Connection conexao = getConexao();
            PreparedStatement pstm = conexao.prepareStatement("Insert into exemplar (numero, publicacao_isbn, preco, status) values (?,?,?,?)");
            pstm.setInt(1, exemplar.getNumero());
            pstm.setInt(2,Integer.parseInt(exemplar.getPublicacao_isbn()));
            pstm.setDouble(3, exemplar.getPreco());
            pstm.setString(4, exemplar.getStatus());
            pstm.execute();
            pstm.close();
            conexao.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Exemplar> listar() {
        List<Exemplar> lista = new ArrayList<>();
        try {
            Connection conexao = getConexao();
            Statement stm = conexao.createStatement();
            ResultSet rs = stm.executeQuery("Select * from exemplar");
            Exemplar exemplar;
            while (rs.next()) {
                exemplar = new Exemplar();
                exemplar.setNumero(rs.getInt("numero"));
                exemplar.setPreco(rs.getDouble("preco"));
                exemplar.setPublicacao_isbn(rs.getString("publicacao_isbn"));
                exemplar.setId(rs.getInt("id"));
                exemplar.setStatus(rs.getString("status"));
                lista.add(exemplar);
            }
            stm.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Exemplar> filtrar(int numero, String isbn) {
        try {
            List<Exemplar> exemplars = new ArrayList<Exemplar>();
            Connection conexao = getConexao();
            PreparedStatement pstm = null;
            if (numero != 0 && (isbn == null || isbn.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from exemplar where numero = ?");
                pstm.setInt(1, numero);
            } else if (numero == 0 && (isbn != null && !isbn.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from exemplar where publicacao_isbn ilike ?");
                pstm.setString(1, isbn);
            } else if (numero != 0 && (isbn != null && !isbn.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from exemplar where numero = ? and publicacao_isbn ilike ?");
                pstm.setInt(1, numero);
                pstm.setString(2, isbn);
            }else if (numero == 0 && (isbn == null || isbn.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from exemplar");
            }

            ResultSet rs = pstm.executeQuery();
            Exemplar exemplar;
            if (rs.next()) {
                exemplar = new Exemplar();
                exemplar = new Exemplar();
                exemplar.setNumero(rs.getInt("numero"));
                exemplar.setPreco(rs.getDouble("preco"));
                exemplar.setPublicacao_isbn(rs.getString("publicacao_isbn"));
                exemplar.setId(rs.getInt("id"));
                exemplar.setStatus(rs.getString("status"));
                exemplars.add(exemplar);
            }
            pstm.close();
            conexao.close();
            return exemplars;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
