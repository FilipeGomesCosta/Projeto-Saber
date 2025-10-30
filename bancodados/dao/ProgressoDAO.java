package bancodados.dao;

import bancodados.*;
import bancodados.modelo.*;
import bancodados.dao.*;
import java.sql.*;
import java.lang.Math;

public class ProgressoDAO {

    public static void cadastrarProgresso(String email) {
        String sql = "INSERT INTO Progresso (email, missao, xp, nivel, moedas) VALUES (?, 1, 0, 1, 0)";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, email);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar progresso do usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static int verificarMissao(String email) {
        int numeroMissao = 0;
        String sql = "SELECT missao from Progresso where email like ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                numeroMissao = rs.getInt(1);
            }
            rs.close();

        } catch (SQLException e) {
            System.err.println("Erro ao verificar missao do usuário: " + e.getMessage());
            e.printStackTrace();
        }
        return numeroMissao;
    }

    public static int verificarXP(String email) {
        int numeroXP = 0;
        String sql = "SELECT xp from Progresso where email like ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                numeroXP = rs.getInt(1);
            }
            rs.close();

        } catch (SQLException e) {
            System.err.println("Erro ao verificar XP do usuario: " + e.getMessage());
            e.printStackTrace();
        }
        return numeroXP;
    }

    public static int verificarMoedas(String email) {
        int numeroMoedas = 0;
        String sql = "SELECT moedas from Progresso where email like ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                numeroMoedas = rs.getInt(1);
            }
            rs.close();

        } catch (SQLException e) {
            System.err.println("Erro ao verificar moedas do usuario: " + e.getMessage());
            e.printStackTrace();
        }
        return numeroMoedas;
    }

    public static int verificarNivel(String email) {
        int nivel = 1;
        String sql = "SELECT nivel from Progresso where email like ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                nivel = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar nivel do usuario: " + e.getMessage());
            e.printStackTrace();
        }
        return nivel;
    }

    public static void atualizarProgresso(Progresso progresso) {

        int xpTotalAntigo = verificarXP(progresso.getEmail());
        int xpTotalNovo = xpTotalAntigo + progresso.getXp();

        int moedasTotalAntigo = verificarMoedas(progresso.getEmail());
        int moedasTotalNovo = moedasTotalAntigo + progresso.getMoedas();

        int nivelNovo = (int) Math.ceil((double) xpTotalNovo / 100.0);
        if (nivelNovo > 3) {
            nivelNovo = 3;
        }

        String sql = "UPDATE Progresso SET missao = ?, xp = ?, moedas = ?, nivel = ? WHERE email = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, progresso.getMissao());
            pst.setInt(2, xpTotalNovo);
            pst.setInt(3, moedasTotalNovo);
            pst.setInt(4, nivelNovo);
            pst.setString(5, progresso.getEmail());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar progresso do usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void zerarProgresso(Progresso progresso) {
        String sql = "UPDATE Progresso SET missao = ?, xp = ?, moedas = ?, nivel = 1 WHERE email = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, progresso.getMissao());
            pst.setInt(2, progresso.getXp());
            pst.setInt(3, progresso.getMoedas());
            pst.setString(4, progresso.getEmail());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao zerar progresso do usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
