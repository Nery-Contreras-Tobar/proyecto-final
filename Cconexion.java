
package com.progra2.java_crud_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class Cconexion {
    
    Connection conectar = null;
    
    String usuario = "root";
    String contraseña = "Root";
    String bd = "db";
    String ip = "localhost";
    String puerto = "3306";

    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion (){
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contraseña);
           // JOptionPane.showMessageDialog(null,"La conexion ha sido exitosa");
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos, error: "+ e.toString());
        }
        return conectar;
    }
}
