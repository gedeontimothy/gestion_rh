/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_rh;

import IHM.login;
import java.io.IOException;
import  servers.*;

/**
 *
 * @author New User
 */
public class Gestion_RH {

       public static Serve server;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        server = new Serve("192.168.202.151", 3001);
         new login().setVisible(true);
    }
    public static Serve getServe(){
        return server;
    }
    public static Serve testS(){
        return new Serve("192.168.202.151", 3001);
    }
    
}
