package core;

import core.servers.Serve;
import core.servers.Connexion;
import IHM.Empreinte;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Laptop
 */
public class Device {

    private static Serve server;

    private static String host = "172.20.10.4";
    
    private static int port = 3001;

    private static Device instance;
    
    public Device(){
        
        server = new Serve(host, port);
    
    }
    
    public static Device i(){
        
        if(instance == null)
        
            instance = new Device();
        
        return instance;
    
    }
    
    
    public static Serve getServe(){
    
        return server;
    
    }

    public Map<String, String> identification() throws IOException, InterruptedException{

        return this.identification(30, 30, null);

    }

    public Map<String, String> identification(int connectionTentative) throws IOException, InterruptedException{

        return this.identification(connectionTentative, 30, null);

    }
    
    public Map<String, String> identification(int connectionTentative, int scanTentative) throws IOException, InterruptedException{

        return this.identification(connectionTentative, scanTentative, null);

    }

    public Map<String, String> identification(int connectionTentative, int scanTentative, Map<String, Runnable> callbacks) throws IOException, InterruptedException{

        Boolean verification = false;
        
        Map<String, String> response = new HashMap<>();

        for (int i = 0; i < connectionTentative; i++) {

            if(
                this.server.post("?agent=desktop&route=identification&operation=identification-employe")
                &&
                this.server.getConnexion().getResultToString().equals("ok")
            ){
                
                if(callbacks.containsKey("connexion")) callbacks.get("connexion").run();

                verification = true;

                break;

            }

            Thread.sleep(1000);

        }
        
        if(verification){

            verification = false;
            
            for (int i = 0; i < connectionTentative; i++) {

                if(this.server.post("?agent=desktop&route=identification-attempt-scan")){

                    //if(callbacks.containsKey("scan")) callbacks.get("scan").run();
                    String[] res = this.server.getConnexion().getResultToString().split("\n");
                    
                    //System.out.println(this.server.getConnexion().getResultToString());
                    
                    response.put("device_id", res[1]);
                    
                    if(res[0].equals("not-authentified")){
                    
                        response.put("type", "not-authentified");
                        
                        response.put("message", null);
                        
                        break;
                    
                    }
                    
                    else if(res[0].equals("authentified")){
                    
                        response.put("type", "authentified");
                        
                        response.put("message", null);

                        Object[] empreinte = {res[1]};

                        ArrayList<Map> resp = DB.i().statement("SELECT * FROM employes WHERE empreinte = ?", empreinte);

                        if(resp.isEmpty()){
                            response.put("in-database", "no");
                        }
                        else{
                            response.put("in-database", "yes");
                            response.put("id_employe", resp.get(0).get("id_employe").toString());
                        }
                        
                        break;
                    
                    }
                    
                    else response.put("type", res[0]);
                    
                    //else
                    //verification = true;

                }
            

                Thread.sleep(1000);
            
            }

        }
        else{
            response.put("type", "connexion-erreur");
            response.put("message", null);
            response.put("device_id", null);
        }
        
        return response;

    }
    
    public static String generateDeviceId(){
        
        Boolean turn = true;
        
        String device_id = null;
        
        while(turn){

            String[] alpha_numeric = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0987654321".split("");

            Collections.shuffle(Arrays.asList(alpha_numeric));

            device_id = String.join("", alpha_numeric).substring(0, 12);

            // C'est ici qu'on peut vérfier la disponibilité du device_id dans la base de donnée


            Object[] empreinte = {device_id};

            ArrayList<Map> resp = DB.i().statement("SELECT * FROM employes WHERE empreinte = ?", empreinte);

            if(resp.isEmpty()) turn = false;
            
            
        }
        
        return device_id;
    }

    
    public String registerConnexion(){

        return this.registerConnexion(30, 30);

    }
    public String registerConnexion(int connectionTentative, int scanTentative){
        
        //servers.Serve s = gestion_rh.Gestion_RH.testS();
        
        try{

            Boolean verification = false;
            
            String g = Device.generateDeviceId();
            // g = "uhs8huhs2HJH";
            for (int i = 0; i < connectionTentative; i++) {

                
                if(this.server.addDatas("device_id", g).post("?agent=desktop&route=register&operation=ajout-employe")){

                    System.out.println(this.server.getConnexion().getResultToString());

                    verification = true;

                    break;

                }

                Thread.sleep(1000);

            }

            if(verification){

                verification = false;

                if(this.server.getConnexion().errorExists()){

                    JOptionPane.showMessageDialog(null, "Echec de connexion");
                    
                    return "connection-error";

                }

                else{

                    Thread.sleep(5000);

                    JOptionPane.showMessageDialog(null, "Connexion ok");

                    for (int i = 0; i < scanTentative; i++) {

                        if(this.server.post("?agent=desktop&route=register-attempt-scan")){

                            Connexion con = this.server.getConnexion();

                            // System.out.println(con.getResultToString());
                            
                            String[] resp = con.getResultToString().split("\n");
                            
                            
                            
                            return resp[1];

                        }

                        // else System.out.println("hhhh");

                        Thread.sleep(1000);

                    }

                }

            }

            else return null;

        }

        catch (IOException e) {
            e.printStackTrace();
            return "IOException";
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            return "InterruptedException";
        }
        return null;
    }
    
    public Boolean operationTerminate(){
        return this.operationTerminate(5, null);
    }
    public Boolean operationTerminate(int tentative, String device){
        try {
            for (int i = 1; i <= tentative; i++) {
                if(device != null) this.server.addDatas("device_id", device);
                if(this.server.post("?agent=desktop&route=identification-terminate")){
                    System.out.println(this.server.getConnexion().getResultToString());
                    return true;
                }
                Thread.sleep(1000);
            }
        } catch (IOException ex) {
            Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    public static String identityButton(JLabel jLabel3){
        try {

            Map<String, String> response;
            
            Map<String, Runnable> callback = new HashMap<>();
            
            callback.put("connexion", new Runnable() {public void run() {
                //jLabel3.setText("Connexion établie");
                jLabel3.setText("Attente du scan de l'appareil...");
            }});
            
            response = Device.i().identification(10,30, callback);
            
            //System.out.println(response);

            if(response.get("type").equals("connexion-erreur")){

                jLabel3.setText("Erreur de scan");

            }

            else if(response.get("type").equals("not-authentified")){

                jLabel3.setText("Votre appareil ne vous a pas reconnu.");

            }

            else if(response.get("type").equals("authentified")){
                if(response.containsKey("in-database") && response.get("in-database") == "yes"){
                    //JOptionPane.showMessageDialog(null, "Employe id : " + response.get("id_employe") + "\n" + " code : " + response.get("device_id"));
                    if(Device.i().operationTerminate(5, response.get("device_id"))){
                        //JOptionPane.showMessageDialog(null,"Test");
                        return response.get("id_employe").toString();
                    }
                }
                else{
                    jLabel3.setText(
                        "L'appareil avec le code " + response.get("device_id").substring(0, 3) 
                        + " - - - - - - " + response.get("device_id").substring(9, 12) 
                        + " a été authentifier"
                    );
                    JOptionPane.showMessageDialog(null, "Cet appareil n'a pas été trouver dans le système.");
                }
            }

            else if(response.get("type").equals("nothing-device")){

                System.out.println("Aucun appareil connecté.");

            }

            // jLabel3.setText("Inserez votre empreinte digitale ");
        } catch (IOException ex) {
            Logger.getLogger(Empreinte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Empreinte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "echec";
    }
}
