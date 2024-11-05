package Util;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    
    public Statement stm;
    public ResultSet rs;
    
    private final String driver = "com.mysql.cj.jdbc.Driver"; 
    private final String root = "jdbc:mysql://127.0.0.1:3306/aps";
    private final String user = "root";
    private final String pass = "";
    
    public Connection conn;
    
    public void conectar() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(root, user, pass);
            System.out.println("Conexao estabelecida com sucesso!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver nao encontrado: " + e);
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e);
        }
    }

    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexao fechada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexao: " + e);
        }
    }
    
    public void executaSQL(String SQL) {
        try {
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(SQL);
        } catch (SQLException e) {
            System.out.println("Erro ao executar SQL: " + e);
        }
    }
}