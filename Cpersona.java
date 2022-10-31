/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra2.java_crud_mysql;


import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author TF039
 */
public class Cpersona { 
    
    int codigo;
    String nombrePersona;
    String apellidoPersona;
    String emailPersona;
    String telefonoPersona;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    public String getEmailPersona() {
        return emailPersona;
    }

    public void setEmailPersona(String emailPersona) {
        this.emailPersona = emailPersona;
    }

    public String getTelefonoPersona() {
        return telefonoPersona;
    }

    public void setTelefonoPersona(String telefonoPersona) {
        this.telefonoPersona = telefonoPersona;
    }
  
    public void insertarPersona(JTextField paramNombre, JTextField paramApellido, JTextField paramEmail, JTextField paramTelefono){
    
        setNombrePersona(paramNombre.getText());
        setApellidoPersona(paramApellido.getText());
        setEmailPersona(paramEmail.getText());
        setTelefonoPersona(paramTelefono.getText());
        
        Cconexion objetoConexion = new Cconexion();
        
        String consulta = ("insert into persona(nombre, apellido, email, telefono) values (?, ?, ?, ?);");
        
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getNombrePersona());
            cs.setString(2, getApellidoPersona());
            cs.setString(3, getEmailPersona());
            cs.setString(4, getTelefonoPersona());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente la persona");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " No se inserto correctamente la persona, error:"+e.toString());
        }
        
    }
    
    public void MostrarPersona(JTable paramTablaTotalPersonas){
        
        Cconexion objetoConexion = new Cconexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel> (modelo);
        paramTablaTotalPersonas.setRowSorter(OrdenarTabla);
       
        String sql="";
        
        modelo.addColumn("Id_persona");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Email");
        modelo.addColumn("Telefono");
        
        paramTablaTotalPersonas.setModel(modelo);
        
        sql = "SELECT * FROM db.persona;";
        
        String[] datos  = new String [5];
        Statement st;
        
        try {
            st = objetoConexion.estableceConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                datos[0]= rs.getString(1);
                datos[1]= rs.getString(2);
                datos[2]= rs.getString(3);
                datos[3]= rs.getString(4);
                datos[4]= rs.getString(5);

                modelo.addRow(datos);
            }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"No se pudo mostrar el registro, error:"+e.toString());
        }
        
    }
    
    public void SeleccionarPersona(JTable paramTablaPersona, JTextField paramId_persona, JTextField paramNombre, JTextField paramApellido, JTextField paramEmail, JTextField paramTelefono){
        
        try {
            int fila = paramTablaPersona.getSelectedRow();
            
            if (fila >=0){
                
                paramId_persona.setText((paramTablaPersona.getValueAt(fila, 0).toString()));
                paramNombre.setText((paramTablaPersona.getValueAt(fila, 1).toString()));
                paramApellido.setText((paramTablaPersona.getValueAt(fila, 2).toString()));
                paramEmail.setText((paramTablaPersona.getValueAt(fila, 3).toString()));
                paramTelefono.setText((paramTablaPersona.getValueAt(fila, 4).toString()));
            }
            else {
                JOptionPane.showMessageDialog(null,"Fila no selexionada");
            }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"Error de seleccion, Error:"+e.toString());
            
        }
    }
    
    public void ModificarPersona (JTextField paramCodigo, JTextField paramNombre, JTextField paramApellido, JTextField paramEmail, JTextField paramTelefono){
        
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombrePersona(paramNombre.getText());
        setApellidoPersona(paramApellido.getText());
        setEmailPersona(paramEmail.getText());
        setTelefonoPersona(paramTelefono.getText());
        
        Cconexion objetoConexion = new Cconexion();
        
        String consulta = "update persona set persona.nombre = ?, persona.apellido = ?,persona.email = ?,persona.telefono = ? where persona.id_persona = ?;";
        
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
       
            cs.setString(1, getNombrePersona());
            cs.setString(2, getApellidoPersona());
            cs.setString(3, getEmailPersona());
            cs.setString(4, getTelefonoPersona());
            cs.setInt(5, getCodigo());

            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificacion exitosa");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo modificar, error:"+e.toString());
        }
        
    }
    
    public void Eliminarpersona(JTextField paramCodigo){
        
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
        Cconexion objetoConexion = new Cconexion();
        
        String consulta = "DELETE FROM persona where persona.id_persona = ?;";
        
       try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
       
            cs.setInt(1, getCodigo());

            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimino correctamente");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error:"+e.toString());
        }
        
    }
    
}
