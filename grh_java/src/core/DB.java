package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Laptop
 */
public class DB{
    
    
    public static final String DB_NAME = "grh";
    
    public static final String DB_CONNECTION = "mysql";

    public static final String DB_HOST = "localhost:3306";

    public static final String DB_USERNAME = "root";

    public static final String DB_PASSWORD = "";
    
    public static final String DB_CHARSET = "utf8mb4";
    
    public static final String DB_COLLATE = "utf8mb4_general_ci";
    
    private Connection connection;
    
    private Integer update_count = null;
    
    private Boolean is_update = null;
    
    public static void main(String[] args){
        
        Object[] dd = {12345, "1"};
        System.out.println(DB.i().statement("UPDATE admin SET `password` = ? WHERE id_admin = ?;", dd));
        
    }
    
    public DB(){}

    public static DB i() { return new DB(); }

    public Connection getConnection() {
        
        try{
            
            //Class.forName("com.mysql.cj.jdbc.Driver");

            if(this.connection == null){

                String url = "jdbc:mysql://" + DB_HOST + "/" + DB_NAME;

                this.connection = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);

            }

            return this.connection;
        
        } catch (SQLException ex) {
            
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        
        } /*catch (ClassNotFoundException ex) {
            
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        
        }*/
        
        return null;

    }
    
    public ArrayList statement(String query){

        return statement(query, null);

    }

    public ArrayList statement(String query, Object[] values){
        
        try {
            
            this.getConnection().setAutoCommit(false);
            
            PreparedStatement statement;
            
            statement = this.getConnection().prepareStatement(query);
            
            if(values != null && values.length > 0){

                for (int i = 0; i < values.length; i++)

                    statement.setObject(i + 1, values[i]);

            }
            
            Boolean exec = statement.execute();
            
            this.getConnection().commit();
            
            
            ResultSet result = statement.getResultSet();

            ArrayList<HashMap<String, Object>> datas = new ArrayList<>();
            
            if(exec){
                
                while (result.next()) {

                    HashMap<String, Object> map = new HashMap<>();

                    for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {

                        map.put(result.getMetaData().getColumnLabel(i), result.getString(result.getMetaData().getColumnLabel(i)));

                    }

                    datas.add(map);

                }
                
                result.close();
                
            }
            else{
                
                /*C'est un INSERT, UPDATE, DELETE, CREATE TABLE, DROP TABLE,...*/
                
                this.update_count = statement.getUpdateCount();
                
                if(this.update_count > 0) this.is_update = true;

            }
            
            statement.close();
            
            this.getConnection().close();
            
            this.connection = null;

            return datas;
            

        } catch (SQLException ex) {

            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            
            try {
                
                if(this.connection != null)
                    
                    this.connection.rollback();

            } catch (SQLException ex1) {

                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex1);

            }

        }

        return null;
        
    }

    public Boolean isUpdate(){
        return is_update;
    }

    public int getUpdateCount(){
        return update_count;
    }

    
    public Boolean exists(){

        return !this.statement("SHOW DATABASES LIKE \"" + DB_NAME + "\"").isEmpty();

    }
}

