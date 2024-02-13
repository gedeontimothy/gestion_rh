/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package IHM.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */

public class listeAdmi {

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    int idAdmin;
    String nom, password;

    public listeAdmi() {
    }
    
    
    
   Connection con ;
    public void Connetion(){
        
        String url,user,password;
        user="root";
        password="";
        url="jdbc:mysql://localhost:3306/grh";
        String driver="com.mysql.jdbc.Driver";
        
        try{
        Class.forName(driver);
         con=DriverManager.getConnection(url,user,password);
        }
        catch(Exception e){
           
                JOptionPane.showMessageDialog(null,"erreur de Driver"+e.getMessage() );
                }
            
        }
    public void AfficherAdmin(JTable table ){
         try {
           Connetion();
           Statement st=null;
           ResultSet result=null;
           String requete="select *  from admin ;";
            st=con.createStatement();
            result=st.executeQuery(requete);
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            model.setRowCount(0);
           
          
            while(result.next())
            {     Object[] ligne ={ result.getInt(1),result.getString(2),result.getString(3)};
                  model.addRow(ligne);
            }
           
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"requete  non effectuer"+ex);
       }
          
      }
    
    
    
}
