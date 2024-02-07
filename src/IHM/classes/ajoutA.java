/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ajoutA {
    private int id,id_sev,id_admin;
    private String nom,postnom,prenom,emprente,date_embau,photo,role_serv,statut;
    private float salaire;

    public ajoutA(int id, int id_sev, int id_admin, String nom, String postnom, String prenom, String emprente, String date_embau, String photo, String role_serv, String statut, float salaire) {
        this.id = id;
        this.id_sev = id_sev;
        this.id_admin = id_admin;
        this.nom = nom;
        this.postnom = postnom;
        this.prenom = prenom;
        this.emprente = emprente;
        this.date_embau = date_embau;
        this.photo = photo;
        this.role_serv = role_serv;
        this.statut = statut;
        this.salaire = salaire;
    }

    public ajoutA() {
    }

    public int getId() {
        return id;
    }

    public int getId_sev() {
        return id_sev;
    }

    public int getId_admin() {
        return id_admin;
    }

    public String getNom() {
        return nom;
    }

    public String getPostnom() {
        return postnom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmprente() {
        return emprente;
    }

    public String getDate_embau() {
        return date_embau;
    }

    public String getPhoto() {
        return photo;
    }

    public String getRole_serv() {
        return role_serv;
    }

    public String getStatut() {
        return statut;
    }

    public float getSalaire() {
        return salaire;
    }

    public Connection getCon() {
        return con;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_sev(int id_sev) {
        this.id_sev = id_sev;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPostnom(String postnom) {
        this.postnom = postnom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmprente(String emprente) {
        this.emprente = emprente;
    }

    public void setDate_embau(String date_embau) {
        this.date_embau = date_embau;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setRole_serv(String role_serv) {
        this.role_serv = role_serv;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    

    
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
 Connection con =null; //DECLATION TJR DEHORS
 public void Enregistre( ) {
        
        Connetion();
       
             
        
  
        try {
            Statement st = con.createStatement();
        String requette = "INSERT INTO employes (id_employe,empreinte,nom,post_nom,prenom,date_embauche,photo,salire,role_servise,id_service,id_admin) values ('"+this.id+"','"+this.emprente+"','"+this.nom+"','"+this.postnom+"','"+this.prenom+"','"+this.date_embau+"','"+this.photo+"','"+this.salaire+"','"+this.role_serv+"','"+this.id_sev+"','"+this.id_admin+"');";
        int n = st.executeUpdate(requette);// car il y aura une modification à la BD
        if(n==1){//
           // JOptionPane.showMessageDialog(null, "ENREGISTREMENT REUSSI AVEC SUCCES !");
        
////        DefaultTableModel model = (DefaultTableModel) tables.getModel();
////        Object[] ligne ={this.id,this.emprente,this.nom,this.postnom,this.prenom,this.date_embau,this.photo,this.salaire,this.role_serv,this.statut,this.id_sev,this.id_admin};// this car c'est la personne même qui va l'appeler(enregistrer) en l'instant
////        model.addRow(ligne);
         }   
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur d'enregistrement  due à "+e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "L'enregistrement existe déjà !");
//            Afficher(tables);
            
        }}
           // affichage de jtable  
         public void Afficher(JTable table ){
         try {
           Connetion();
           Statement st=null;
           ResultSet result=null;
           String requete="select *  from employes ;";
            st=con.createStatement();
            result=st.executeQuery(requete);
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            model.setRowCount(0);
           
          
            while(result.next())
            {     Object[] ligne ={ result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7),result.getFloat(8),result.getString(9),result.getString(10),result.getInt(11),result.getInt(12)};
                  model.addRow(ligne);
            }
           
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"requete  non effectuer"+ex);
       }
          
      } 
         ArrayList<String> Rplace(ArrayList<String> ar) {
        ArrayList<String> rpl = new ArrayList<>();
        int K = 0;
        boolean test;
        while (K < ar.size()) {
            test = true;
            for (int i = 0; i < rpl.size(); i++) {
                if (ar.get(K).equals(rpl.get(i))) {
                    test = false;
                }
            }
            if (test) {
                rpl.add(ar.get(K));
            }
            K++;
        }

        return rpl;
    }public void extraireKey(JComboBox combo) {
        Connetion();
        try {
             Connetion();
           Statement st=null;
           ResultSet result=null;
           String requete="select id_service  from service ;";
            st=con.createStatement();
            result=st.executeQuery(requete);
            ArrayList<String> ar = new ArrayList<>();
            ArrayList<String> rpl = new ArrayList<>();

            while (result.next()) {

                ar.add(result.getString("id_service").toString());
            }
            rpl = Rplace(ar);
            for (int i = 0; i < rpl.size(); i++) {
                combo.addItem(rpl.get(i));
            }
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }public void extraireCle(JComboBox combo) {
        Connetion();
        try {
             Connetion();
           Statement st=null;
           ResultSet result=null;
           String requete="select id_admin  from admin ;";
            st=con.createStatement();
            result=st.executeQuery(requete);
            ArrayList<String> ar = new ArrayList<>();
            ArrayList<String> rpl = new ArrayList<>();

            while (result.next()) {

                ar.add(result.getString("id_admin").toString());
            }
            rpl = Rplace(ar);
            for (int i = 0; i < rpl.size(); i++) {
                combo.addItem(rpl.get(i));
            }
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
     
    
}public int recuperationUser(String user, String pass) {
    int re = 0;
    try {
        Connetion();
        // Assurez-vous d'avoir une connexion valide à votre base de données
        PreparedStatement ps = con.prepareStatement("SELECT id_admin FROM admin WHERE user = ? AND password = ?");
        ps.setString(1, user);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            re = Integer.parseInt(rs.getString("id_admin")); // Utilisateur et mot de passe valides
        } else {
            re = 0; // Aucune correspondance trouvée
        }
        
        rs.close();
        ps.close();
        con.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
    }
    return re;
}public void AfficherLISTE(JTable table ){
         try {
           Connetion();
           Statement st=null;
           ResultSet result=null;
           String requete="select *  from employes ;";
            st=con.createStatement();
            result=st.executeQuery(requete);
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            model.setRowCount(0);
           
          
            while(result.next())
            {     Object[] ligne ={ result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7),result.getFloat(8),result.getString(9),result.getString(10),result.getInt(11),result.getInt(12)};
                  model.addRow(ligne);
            }
           
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"requete  non effectuer"+ex);
       }
          
      }
}
