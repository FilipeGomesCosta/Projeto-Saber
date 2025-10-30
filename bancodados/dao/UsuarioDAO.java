package bancodados.dao;

import bancodados.*;
import bancodados.modelo.*;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioDAO {

    public boolean login(Usuario usuario) {
        String emailDigitado = usuario.getEmail();
        String senhaDigitada = usuario.getSenha();
        String sql = "SELECT senha FROM Usuario WHERE email = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, emailDigitado);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String hashSalvo = rs.getString("senha");
                    return BCrypt.checkpw(senhaDigitada, hashSalvo);
                }
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Erro durante o login: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean cadastrar(Usuario usuario) {
        String hashSenha = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
        String sql = "INSERT INTO Usuario (email, nome, senha) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, usuario.getEmail());
            pst.setString(2, usuario.getNome());
            pst.setString(3, hashSenha);

            pst.executeUpdate();

            ProgressoDAO.cadastrarProgresso(usuario.getEmail());
            MochilaDAO.cadastrarInventario(usuario.getEmail());
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarSenha(Usuario usuario) {
        String email = usuario.getEmail();
        String novaSenha = usuario.getSenha();
        String sql = "UPDATE Usuario SET senha = ? WHERE email = ?";
        String hashNovaSenha = BCrypt.hashpw(novaSenha, BCrypt.gensalt());

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, hashNovaSenha);
            pst.setString(2, email);

            int linhasAfetadas = pst.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar senha: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean verificarEmailExistente(String email) {
        String sql = "SELECT email FROM Usuario WHERE email = ? LIMIT 1";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, email);

            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.err.println("Erro ao verificar email existente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static String verificarNome(String email) {
        String nome = null;
        String sql = "SELECT nome from Usuario where email like ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                nome = rs.getString(1);
            }
            rs.close();

        } catch (SQLException e) {
            System.err.println("Erro ao verificar nome do usuário: " + e.getMessage());
            e.printStackTrace();
        }
        return nome;
    }

}
