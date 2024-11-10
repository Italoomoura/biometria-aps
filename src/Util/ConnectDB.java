package Util;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

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
    
    public ResultSet readNoticias() {
        try {
            String SQL = "SELECT * FROM noticias";
            PreparedStatement ps = conn.prepareStatement(SQL);
            return ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erro ao ler noticias: " + e);
            return null;
        }
    }

    public void createNoticia(String titulo, String conteudo) {
        try {
            String SQL = "INSERT INTO noticias (titulo, conteudo) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, titulo);
            ps.setString(2, conteudo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao criar noticia: " + e);
        }
    }

    public void updateNoticia(int id, String titulo, String conteudo) {
        try {
            String SQL = "UPDATE noticias SET titulo = ?, conteudo = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, titulo);
            ps.setString(2, conteudo);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar noticia: " + e);
        }
    }

    public void deleteNoticia(int id) {
        try {
            String SQL = "DELETE FROM noticias WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar noticia: " + e);
        }
    }
}