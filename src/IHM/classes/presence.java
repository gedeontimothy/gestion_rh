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
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author New User
 */
public class presence {
    private  int id,id_eploi;
    private String heureA,heurD,date, nom_Empl;

    public presence(int id, int id_eploi, String heureA, String heurD, String date) {
        this.id = id;
        this.id_eploi = id_eploi;
        this.heureA = heureA;
        this.heurD = heurD;
        this.date = date;
    }

    public presence() {
    }

    public int getId() {
        return id;
    }

    public int getId_eploi() {
        return id_eploi;
    }

    public String getHeureA() {
        return heureA;
    }

    public String getHeurD() {
        return heurD;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_eploi(int id_eploi) {
        this.id_eploi = id_eploi;
    }

    public void setHeureA(String heureA) {
        this.heureA = heureA;
    }

    public void setHeurD(String heurD) {
        this.heurD = heurD;
    }

    public void setDate(String date) {
        this.date = date;
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
            JOptionPane.showConfirmDialog(null, "la connexion est dans le bon ");
                JOptionPane.showMessageDialog(null,"erreur de Driver"+e.getMessage() );
                }
            
        }
 Connection con =null; //DECLATION TJR DEHORS
 public void Enregistre( ) {
        
        Connetion();
       
             
        
  
        try {
            Statement st = con.createStatement();
        String requette = "INSERT INTO presence (id_presence,heur_arrive,date,id_employe) values ('"+this.id+"','"+this.heureA+"','"+this.date+"','"+this.id_eploi+"');";
        int n = st.executeUpdate(requette);// car il y aura une modification à la BD
        if(n==1){//
            JOptionPane.showMessageDialog(null, "ENREGISTREMENT REUSSI AVEC SUCCES !");
        
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
           String requete="select *  from presence ;";
            st=con.createStatement();
            result=st.executeQuery(requete);
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            model.setRowCount(0);
           
          
            while(result.next())
            {     Object[] ligne ={ result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getInt(5)};
                  model.addRow(ligne);
            }
           
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"requete  non effectuer"+ex);
       }
          
      }  ArrayList<String> Rplace(ArrayList<String> ar) {
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
           String requete="select id_employe, nom  from employes ;";
            st=con.createStatement();
            result=st.executeQuery(requete);
            ArrayList<String> ar = new ArrayList<>();
            ArrayList<String> rpl = new ArrayList<>();

            while (result.next()) {

                ar.add(result.getInt("id_employe")+" "+result.getString("nom").toString());
            }
            rpl = Rplace(ar);
            for (int i = 0; i < rpl.size(); i++) {
                combo.addItem(rpl.get(i));
            }
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }
    
    public void extraireNom(JComboBox comboNom){
         Connetion();
        try {
             Connetion();
           Statement st=null;
           ResultSet result=null;
           String requete="select Employes.nom from Employes innerjoin presence on Employes.id_employe=Presence.id_presence;";
            st=con.createStatement();
            result=st.executeQuery(requete);
            ArrayList<String> ar = new ArrayList<>();
            ArrayList<String> rpl = new ArrayList<>();

            while (result.next()) {

                ar.add(result.getString("nom").toString());
            }
            rpl = Rplace(ar);
            for (int i = 0; i < rpl.size(); i++) {
                comboNom.addItem(rpl.get(i));
            }
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
        
    }
}
