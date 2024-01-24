/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionrh.app.models;
import gestionrh.core.databases.*;
/**
 *
 * @author HP
 */
public class Administrateur extends ORM {
    protected String table = "administrateurs";
    protected String primary_key = "id_administrateur";
    
}
