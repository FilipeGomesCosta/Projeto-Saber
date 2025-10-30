package bancodados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

    private static final String URL_BASE = "jdbc:mysql://localhost:3306/";
    private static final String NOME_BD = "bancoSaber";
    private static final String URL_COMPLETA = URL_BASE + NOME_BD;
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static boolean inicializado = false;

   
    private static synchronized void inicializarBanco() {
        if (inicializado) {
            return;
        }

        try (Connection connBase = DriverManager.getConnection(URL_BASE, USER, PASSWORD);
             Statement stmtBase = connBase.createStatement()) {

            String sqlCreateDB = "CREATE DATABASE IF NOT EXISTS " + NOME_BD + ";";
            stmtBase.executeUpdate(sqlCreateDB);
            
            try (Connection conn = DriverManager.getConnection(URL_COMPLETA, USER, PASSWORD);
                 Statement stmt = conn.createStatement()) {

                String sqlCreateTabela = "CREATE TABLE IF NOT EXISTS Usuario ("
                        + "email VARCHAR(100) NOT NULL PRIMARY KEY,"
                        + "nome VARCHAR(100) NOT NULL,"
                        + "senha VARCHAR(60) NOT NULL);";

                stmt.executeUpdate(sqlCreateTabela);
      
                sqlCreateTabela = "CREATE TABLE IF NOT EXISTS Progresso ("
                        + "email VARCHAR(100) NOT NULL PRIMARY KEY,"
                        + "foreign key(email) references Usuario(email) ON DELETE CASCADE ON UPDATE CASCADE,"
                        + "missao int NOT NULL default 1,"
                        + "xp int NOT NULL default 0,"
                        + "nivel int not null default 1,"
                        + "moedas int not null default 0);";

                stmt.executeUpdate(sqlCreateTabela);
      
                sqlCreateTabela = "CREATE TABLE IF NOT EXISTS Mochila ("
                        + "email VARCHAR(100) NOT NULL PRIMARY KEY,"
                        + "foreign key(email) references Usuario(email) ON DELETE CASCADE ON UPDATE CASCADE," 
                        + "quantidadePapiro int not null default 0,"
                        + "quantidadeLampada int not null default 0);";

                stmt.executeUpdate(sqlCreateTabela);
                
                inicializado = true;

            } catch (SQLException e) {
                System.err.println("Erro ao conectar ou criar tabelas: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inicializar o banco de dados (Conexão Base): " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        inicializarBanco(); 
        
        return DriverManager.getConnection(URL_COMPLETA, USER, PASSWORD);
    }
}