/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionrh.core.databases;

/**
 *
 * @author HP
 */
public class database {
    public String name = "gestion_rh";
    public String host = "localhost:3306";
    public String charset = "utf8mb4";
    public String collate = "utf8mb4_general_ci";
    public String username = "root";
    public String password = "";
    
    public void getConnection(){
        
    }
    
    public void statement(String query, Object[]...values){
        
    }
}
