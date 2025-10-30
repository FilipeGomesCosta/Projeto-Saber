
package bancodados.dao;

import bancodados.*;
import bancodados.modelo.*;
import bancodados.dao.*;
import java.sql.*;

public class MochilaDAO {

    public static void cadastrarInventario(String email) {
        String sql = "INSERT INTO Mochila (email, quantidadePapiro, quantidadeLampada) VALUES (?, 0,0)";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, email);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar inventário do usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static int verificarPapiro(String email) {
        int quantidadePapiro = 0;
        String sql = "SELECT quantidadePapiro from Mochila where email like ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                quantidadePapiro = rs.getInt(1);
            }
            rs.close();

        } catch (SQLException e) {
            System.err.println("Erro ao verificar quantidade de papiros do usuário: " + e.getMessage());
            e.printStackTrace();
        }
        return quantidadePapiro;
    }

    public static void comprarPapiro(Mochila mochila) {
        String email = mochila.getEmail();
        int quantidadePapiro = verificarPapiro(email);
        int quantidadeMoedas = ProgressoDAO.verificarMoedas(email);
        String sql = "UPDATE Mochila SET quantidadePapiro = ? WHERE email = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, quantidadePapiro + mochila.getQuantidadePapiro());
            pst.setString(2, email);

            pst.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao comprar papiro: " + e.getMessage());
            e.printStackTrace();
        }
        sql = "UPDATE Progresso SET moedas = ? WHERE email = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, quantidadeMoedas - 10);
            pst.setString(2, email);

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao comprar papiro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static int verificarLampada(String email) {
        int quantidadeLampada = 0;
        String sql = "SELECT quantidadeLampada from Mochila where email like ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                quantidadeLampada = rs.getInt(1);
            }
            rs.close();

        } catch (SQLException e) {
            System.err.println("Erro ao verificar quantidade de lampadas do usuário: " + e.getMessage());
            e.printStackTrace();
        }
        return quantidadeLampada;
    }

    public static void comprarLampada(Mochila mochila) {
        String email = mochila.getEmail();
        int quantidadeLampada = verificarLampada(email);
        int quantidadeMoedas = ProgressoDAO.verificarMoedas(email);
        String sql = "UPDATE Mochila SET quantidadeLampada = ? WHERE email = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, quantidadeLampada + mochila.getQuantidadeLampada());
            pst.setString(2, email);

            pst.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao comprar Lampada: " + e.getMessage());
            e.printStackTrace();
        }
        sql = "UPDATE Progresso SET moedas = ? WHERE email = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, quantidadeMoedas - 15);
            pst.setString(2, email);

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao comprar lampada: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void usarPapiro(Mochila mochila) {
        String email = mochila.getEmail();
        int quantidadePapiro = verificarPapiro(email);
        String sql = "UPDATE Mochila SET quantidadePapiro = ? WHERE email = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            quantidadePapiro--;
            pst.setInt(1, quantidadePapiro);
            pst.setString(2, email);

            pst.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao usar papiro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void usarLampada(Mochila mochila) {
        String email = mochila.getEmail();
        int quantidadeLampada = verificarLampada(email);
        String sql = "UPDATE Mochila SET quantidadeLampada = ? WHERE email = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            quantidadeLampada--;
            pst.setInt(1, quantidadeLampada);
            pst.setString(2, email);

            pst.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao usar lâmpada: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void zerarMochila(Mochila mochila) {
        String sql = "UPDATE Mochila SET quantidadePapiro = ?, quantidadeLampada = ? WHERE email = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, mochila.getQuantidadePapiro());
            pst.setInt(2, mochila.getQuantidadeLampada());
            pst.setString(3, mochila.getEmail());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao zerar mochila do usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
