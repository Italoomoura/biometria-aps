package Util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControlPerson {
    
    ConnectDB db = new ConnectDB();
    
    public void inserir(ModelPerson mod){
        try{
            db.conectar();
            PreparedStatement pst = db.conn.prepareStatement("INSERT INTO person (id, first_name, last_name, office, nasc_date) values (?, ?, ?, ?, ?)");
            pst.setInt(1, mod.getId());
            pst.setString(2, mod.getNome());
            pst.setString(3, mod.getSobrenome());
            pst.setString(4, mod.getCargo());
            pst.setString(5, mod.getData_nasc());
            pst.executeUpdate();
            System.out.println("Dados do(a): "+ mod.getNome() +" cadastrados");
            db.desconectar();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    
}