package biblioteca.funcionario;

import biblioteca.dao.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO extends DAO {

//    public void alterar(Funcionario funcionario) {
//		try {
//			Connection conexao = getConexao();
// 
//			PreparedStatement pstmt = conexao
//					.prepareStatement("Update tbfuncionario SET nome = ?, telefone = ?, email = ?, datacadastro = ?"
//							+ " WHERE matricula = ? ");
//			pstmt.setString(1, funcionario.getNome());
//			pstmt.setString(2, funcionario.getTelefone());
//			pstmt.setString(3, funcionario.getEmail());
//			pstmt.setDate(4, new java.sql.Date(funcionario.getDataCadastro().getTime()));
//			pstmt.setLong(5, funcionario.getMatricula());
//			pstmt.execute();
//			pstmt.close();
//			conexao.close();
// 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
// 
//	public void excluir(Funcionario funcionario) {
//		try {
//			Connection conexao = getConexao();
//			PreparedStatement pstm = conexao
//					.prepareStatement("Delete from	tbfuncionario where matricula = ? ");
//			pstm.setLong(1, funcionario.getMatricula());
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
//					.prepareStatement("Select * from funcionario where email = ? and senha = ?");
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
    public boolean inserir(Funcionario funcionario) {
        try {
            Connection conexao = getConexao();
            PreparedStatement pstm = conexao
                    .prepareStatement("Insert into	funcionario (codigo, email, senha, nome, endereco) values (?,?,?,?,?)");
            pstm.setInt(1, funcionario.getCodigo());
            pstm.setString(2, funcionario.getEmail());
            pstm.setString(3, funcionario.getSenha());
            pstm.setString(4, funcionario.getNome());
            pstm.setString(5, funcionario.getEndereco());
            pstm.execute();
            pstm.close();
            conexao.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Funcionario> listar() {
        List<Funcionario> lista = new ArrayList<>();
        try {
            Connection conexao = getConexao();
            Statement stm = conexao.createStatement();
            ResultSet rs = stm.executeQuery("Select * from funcionario");
            Funcionario funcionario;
            while (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setCodigo(rs.getInt("codigo"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setId(rs.getInt("id"));
                funcionario.setSenha(rs.getString("senha"));
                lista.add(funcionario);
            }
            stm.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Funcionario consultar(int login, String senha) {
        Funcionario funcionario;
        try {
            funcionario = new Funcionario();
            Connection conexao = getConexao();
            PreparedStatement pstm = conexao
                    .prepareStatement("Select * from funcionario where codigo = ? and senha = ?");
            pstm.setInt(1, login);
            pstm.setString(2, senha);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                funcionario.setCodigo(rs.getInt("codigo"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setId(rs.getInt("id"));
                funcionario.setSenha(rs.getString("senha"));
            }
            pstm.close();
            conexao.close();
            return funcionario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Funcionario> filtrar(int codigo, String nome, String email) {
        try {
            List<Funcionario> funcionarios = new ArrayList<Funcionario>();
            Connection conexao = getConexao();
            PreparedStatement pstm = conexao
                    .prepareStatement("Select * from funcionario where codigo = ? and nome ilike ? and email ilike ?" );
            pstm.setInt(1, codigo);
            pstm.setString(2, nome);
            pstm.setString(3, email);
            ResultSet rs = pstm.executeQuery();
            Funcionario funcionario;
            if (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setCodigo(rs.getInt("codigo"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setId(rs.getInt("id"));
                funcionario.setSenha(rs.getString("senha"));
                funcionarios.add(funcionario);
            }
            pstm.close();
            conexao.close();
            return funcionarios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
