package biblioteca.publicacao;

import biblioteca.dao.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PublicacaoDAO extends DAO {

//    public void alterar(Publicacao publicacao) {
//		try {
//			Connection conexao = getConexao();
// 
//			PreparedStatement pstmt = conexao
//					.prepareStatement("Update tbpublicacao SET nome = ?, telefone = ?, email = ?, datacadastro = ?"
//							+ " WHERE matricula = ? ");
//			pstmt.setString(1, publicacao.getNome());
//			pstmt.setString(2, publicacao.getTelefone());
//			pstmt.setString(3, publicacao.getEmail());
//			pstmt.setDate(4, new java.sql.Date(publicacao.getDataCadastro().getTime()));
//			pstmt.setLong(5, publicacao.getMatricula());
//			pstmt.execute();
//			pstmt.close();
//			conexao.close();
// 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
// 
//	public void excluir(Publicacao publicacao) {
//		try {
//			Connection conexao = getConexao();
//			PreparedStatement pstm = conexao
//					.prepareStatement("Delete from	tbpublicacao where matricula = ? ");
//			pstm.setLong(1, publicacao.getMatricula());
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
//					.prepareStatement("Select * from publicacao where email = ? and senha = ?");
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
    public boolean inserir(Publicacao publicacao) {
        try {
            Connection conexao = getConexao();
            PreparedStatement pstm = conexao
                    .prepareStatement("Insert into publicacao (isbn, titulo, autor, editora, ano, funcionario_codigo) values (?,?,?,?,?,?)");
            pstm.setInt(1, publicacao.getIsbn());
            pstm.setString(2, publicacao.getTitulo());
            pstm.setString(3, publicacao.getAutor());
            pstm.setString(4, publicacao.getEditora());
            pstm.setInt(5, publicacao.getAno());
            pstm.setInt(6, publicacao.getFuncionario_codigo());
            pstm.execute();
            pstm.close();
           System.out.println(pstm);
            conexao.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Publicacao> listar() {
        List<Publicacao> lista = new ArrayList<>();
        try {
            Connection conexao = getConexao();
            Statement stm = conexao.createStatement();
            ResultSet rs = stm.executeQuery("Select * from publicacao");
            Publicacao publicacao;
            while (rs.next()) {
                publicacao = new Publicacao();
                publicacao.setAno(rs.getInt("ano"));
                publicacao.setAutor(rs.getString("autor"));
                publicacao.setEditora(rs.getString("editora"));
                publicacao.setTitulo(rs.getString("titulo"));
                publicacao.setIsbn(rs.getInt("isbn"));
                publicacao.setFuncionario_codigo(rs.getInt("funcionario_codigo"));
                publicacao.setId(rs.getInt("id"));
                lista.add(publicacao);
            }
            stm.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Publicacao consultar(int login, String senha) {
        Publicacao publicacao;
        try {
            publicacao = new Publicacao();
            Connection conexao = getConexao();
            PreparedStatement pstm = conexao
                    .prepareStatement("Select * from publicacao where codigo = ? and senha = ?");
            pstm.setInt(1, login);
            pstm.setString(2, senha);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                publicacao.setAno(rs.getInt("ano"));
                publicacao.setAutor(rs.getString("autor"));
                publicacao.setEditora(rs.getString("editor"));
                publicacao.setTitulo(rs.getString("titulo"));
                publicacao.setIsbn(rs.getInt("isbn"));
                publicacao.setFuncionario_codigo(rs.getInt("funcionario_codigo"));
                publicacao.setId(rs.getInt("id"));
            }
            pstm.close();
            conexao.close();
            return publicacao;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Publicacao> filtrar(String isbn, String titulo) {
        try {
            List<Publicacao> publicacaos = new ArrayList<>();
            Connection conexao = getConexao();
            PreparedStatement pstm = null;
            if ((isbn != null && !isbn.isEmpty()) && (titulo == null || titulo.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from publicacao where isbn ilike ?");
                pstm.setString(1, isbn);
            } else if ((isbn == null || isbn.isEmpty()) && (titulo != null && !titulo.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from publicacao where titulo ilike ?");
                pstm.setString(1, titulo);
            } else if ((isbn != null && !isbn.isEmpty()) && (titulo != null && !titulo.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from publicacao where isbn ilike ? and titulo ilike ?");
                pstm.setString(1, isbn);
                pstm.setString(2, titulo);
            }else if ((isbn == null || isbn.isEmpty()) && (titulo == null || titulo.isEmpty())) {
                pstm = conexao
                        .prepareStatement("Select * from publicacao");
            }
            ResultSet rs = pstm.executeQuery();
            Publicacao publicacao;
            if (rs.next()) {
                publicacao = new Publicacao();
                publicacao.setAno(rs.getInt("ano"));
                publicacao.setAutor(rs.getString("autor"));
                publicacao.setEditora(rs.getString("editor"));
                publicacao.setTitulo(rs.getString("titulo"));
                publicacao.setIsbn(rs.getInt("isbn"));
                publicacao.setFuncionario_codigo(rs.getInt("funcionario_codigo"));
                publicacao.setId(rs.getInt("id"));
                publicacaos.add(publicacao);
            }
            pstm.close();
            conexao.close();
            return publicacaos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
