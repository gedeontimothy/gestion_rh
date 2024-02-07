/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM.classes;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author New User
 */
public class recherche {
    public  static  void Recherche(JTable jt ,JTextField jtf ,JLabel jl){
            DefaultTableModel dtm=(DefaultTableModel)jt.getModel();
            String mot =jtf.getText().trim();
            TableRowSorter <DefaultTableModel>trs=new TableRowSorter<>(dtm);
            jt.setRowSorter(trs);
            trs.setRowFilter(RowFilter.regexFilter(mot));
            int nbr=jt.getRowCount();
            if(nbr==0){
                jl.setForeground(Color.red);
                jl.setText("rien trouvée");
            }if(nbr==1){
                jl.setForeground(new Color(0,123,0));
                jl.setText("trouvée");
            }if(nbr>=1){
                jl.setForeground(new Color(0,123,0) );
                jl.setText(" retrouvée :"+nbr);
            }
        }
}
