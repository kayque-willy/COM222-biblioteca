package biblioteca.associado;

import biblioteca.dao.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AssociadoDAO extends DAO {

//    public void alterar(Associado associado) {
//		try {
//			Connection conexao = getConexao();
// 
//			PreparedStatement pstmt = conexao
//					.prepareStatement("Update tbassociado SET nome = ?, telefone = ?, email = ?, datacadastro = ?"
//							+ " WHERE matricula = ? ");
//			pstmt.setString(1, associado.getNome());
//			pstmt.setString(2, associado.getTelefone());
//			pstmt.setString(3, associado.getEmail());
//			pstmt.setDate(4, new java.sql.Date(associado.getDataCadastro().getTime()));
//			pstmt.setLong(5, associado.getMatricula());
//			pstmt.execute();
//			pstmt.close();
//			conexao.close();
// 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
// 
//	public void excluir(Associado associado) {
//		try {
//			Connection conexao = getConexao();
//			PreparedStatement pstm = conexao
//					.prepareStatement("Delete from	tbassociado where matricula = ? ");
//			pstm.setLong(1, associado.getMatricula());
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
//					.prepareStatement("Select * from associado where email = ? and senha = ?");
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
    public boolean inserir(Associado associado) {
        try {
            Connection conexao = getConexao();
            PreparedStatement pstm = conexao
                    .prepareStatement("Insert into	associado (codigo, email, senha, nome, endereco, status, tipo) values (?,?,?,?,?,?,?)");
            pstm.setInt(1, associado.getCodigo());
            pstm.setString(2, associado.getEmail());
            pstm.setString(3, associado.getSenha());
            pstm.setString(4, associado.getNome());
            pstm.setString(5, associado.getEndereco());
            pstm.setString(6, associado.getStatus());
            pstm.setString(7, associado.getTipo());
            pstm.execute();
            pstm.close();
            conexao.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Associado> listar() {
        List<Associado> lista = new ArrayList<>();
        try {
            Connection conexao = getConexao();
            Statement stm = conexao.createStatement();
            ResultSet rs = stm.executeQuery("Select * from associado");
            Associado associado;
            while (rs.next()) {
                associado = new Associado();
                associado.setCodigo(rs.getInt("codigo"));
                associado.setNome(rs.getString("nome"));
                associado.setEmail(rs.getString("email"));
                associado.setEndereco(rs.getString("endereco"));
                associado.setId(rs.getInt("id"));
                associado.setSenha(rs.getString("senha"));
                associado.setStatus(rs.getString("status"));
                associado.setTipo(rs.getString("tipo"));
                lista.add(associado);
            }
            stm.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Associado consultar(int login, String senha) {
        Associado associado;
        try {
            associado = new Associado();
            Connection conexao = getConexao();
            PreparedStatement pstm = conexao
                    .prepareStatement("Select * from associado where codigo = ? and senha = ?");
            pstm.setInt(1, login);
            pstm.setString(2, senha);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                associado.setCodigo(rs.getInt("codigo"));
                associado.setNome(rs.getString("nome"));
                associado.setEmail(rs.getString("email"));
                associado.setEndereco(rs.getString("endereco"));
                associado.setId(rs.getInt("id"));
                associado.setSenha(rs.getString("senha"));
                associado.setStatus(rs.getString("status"));
                associado.setTipo(rs.getString("tipo"));
            }
            pstm.close();
            conexao.close();
            return associado;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Associado> filtrar(int codigo, String nome, String tipo) {
        try {
            List<Associado> associados = new ArrayList<Associado>();
            Connection conexao = getConexao();
            PreparedStatement pstm = null;
            if (codigo != 0 && (nome == null || nome.isEmpty()) && (tipo == null || tipo.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from associado where codigo = ?");
                pstm.setInt(1, codigo);
            } else if (codigo == 0 && (nome != null && !nome.isEmpty()) && (tipo == null || tipo.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from associado where nome ilike ?");
                pstm.setString(1, nome);
            } else if (codigo != 0 && (tipo == null || tipo.isEmpty()) && (nome == null || nome.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from associado where tipo ilike ?");
                pstm.setString(1, tipo);
            } else if (codigo != 0 && (nome != null && !nome.isEmpty()) && (tipo == null || tipo.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from associado where codigo = ? and nome ilike ?");
                pstm.setInt(1, codigo);
                pstm.setString(2, nome);
            } else if (codigo != 0 && (tipo != null && !tipo.isEmpty()) && (nome == null || nome.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from associado where codigo = ? and tipo ilike ?");
                pstm.setInt(1, codigo);
                pstm.setString(2, tipo);
            } else if (codigo == 0 && (tipo != null && !tipo.isEmpty()) && (nome != null && !nome.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from associado where nome ilike ? and tipo ilike ?");
                pstm.setString(1, nome);
                pstm.setString(2, tipo);
            } else if (codigo != 0 && (tipo != null && !tipo.isEmpty()) && (nome != null && !nome.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from associado where nome ilike ? and tipo ilike ? and codigo = ?");
                pstm.setString(1, nome);
                pstm.setString(2, tipo);
                pstm.setInt(3, codigo);
            } else if (codigo == 0 && (nome == null || nome.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from associado");
            }
            ResultSet rs = pstm.executeQuery();
            Associado associado;
            if (rs.next()) {
                associado = new Associado();
                associado.setCodigo(rs.getInt("codigo"));
                associado.setNome(rs.getString("nome"));
                associado.setEmail(rs.getString("email"));
                associado.setEndereco(rs.getString("endereco"));
                associado.setId(rs.getInt("id"));
                associado.setSenha(rs.getString("senha"));
                associado.setStatus(rs.getString("status"));
                associado.setTipo(rs.getString("tipo"));
                associados.add(associado);
            }
            pstm.close();
            conexao.close();
            return associados;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
