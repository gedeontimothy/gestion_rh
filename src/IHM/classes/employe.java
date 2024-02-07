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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jovial
 */
public class employe {
    int id_employe;
    byte empreinte;
    String nom ,prenom,date_embauche,photo,role,status;
    Double salaire;
    Connection con=null;

    public employe() {
    }

    public employe(byte empreinte, String nom, String prenom, String date_embauche, String photo, String role, String status, Double salaire) {
        this.empreinte = empreinte;
        this.nom = nom;
        this.prenom = prenom;
        this.date_embauche = date_embauche;
        this.photo = photo;
        this.role = role;
        this.status = status;
        this.salaire = salaire;
    }

    public int getId_employe() {
        return id_employe;
    }

    public void setId_employe(int id_employe) {
        this.id_employe = id_employe;
    }

    public byte getEmpreinte() {
        return empreinte;
    }

    public void setEmpreinte(byte empreinte) {
        this.empreinte = empreinte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDate_embauche() {
        return date_embauche;
    }

    public void setDate_embauche(String date_embauche) {
        this.date_embauche = date_embauche;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
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
        con = DriverManager.getConnection(url,user,pass);
        }
        catch(Exception e)
        {
          JOptionPane.showMessageDialog(null,"erreur de Driver"+e.getMessage() );
        }
        
} 
    
    public ArrayList<String> select_all()
    { 
        ArrayList<String> monarray=new ArrayList<>();
        Connetion();
        
        
        try {
             Statement st =con.createStatement();
             String requete="select id_employe ,nom FROM employes ;";
             ResultSet res =st.executeQuery(requete);
             int i=0;
             while(res.next())
             {
                // ={res.getInt("id_boulangrie"),  res.getString("nom"),res.getString("postnom"), res.getString("prenom"),res.getString("adresse"),res.getString("sexe"),res.getFloat("caisse")};
                 monarray.add(i,res.getInt("id_employe")+" "+res.getString("nom") );
                  i++;
             }
           }   
        catch (Exception e) 
        {
            
             JOptionPane.showConfirmDialog(null, "Erreur d/enrestrment,de à"+e.getMessage());
             
        }
        return monarray;
    }
    }




