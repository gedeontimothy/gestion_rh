/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IHM.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jovial
 */
public class administrateur {
    Connection con=null;
    int id_admin;
    String user_name,password;
   

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public administrateur(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }
    public void ajout_employe(employe monemploye)
    {
        
    }
    public void ajout_admin()
    {
         Connetion();        
         
        try {
            Statement st = con.createStatement();
        String requette = "INSERT INTO admin (id_admin,user,password) values ('"+this.id_admin+"','"+this.user_name+"','"+this.password+"');";
        int n = st.executeUpdate(requette);// car il y aura une modification à la BD
        if(n==1){//
            //JOptionPane.showMessageDialog(null, "ENREGISTREMENT REUSSI AVEC SUCCES !");
        
         }   
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur d'enregistrement  due à "+e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "L'enregistrement existe déjà !"+e);
            
            
        }
    }
    public void supprimer_employe()
    {
        
    }
    
    
    public void Connetion(){
        String url,user,pass;
        user="root";
        pass="";
        url="jdbc:mysql://localhost:3306/grh";
        String driver="com.mysql.jdbc.Driver";
        
        try
        {
        Class.forName(driver);
        con=DriverManager.getConnection(url,user,pass);
        }
        catch(Exception e)
        {
          JOptionPane.showMessageDialog(null,"erreur de Driver"+e.getMessage() );
        }
        
} 
}
