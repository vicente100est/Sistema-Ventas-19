/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Vistas.Frm_Articulo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

public class Render extends DefaultTableCellRenderer{
private Component componente;
private int  columnaStock;


/*public Render( )
{
    this.columnaMes = "";
    this.columnaAño = colA;
}*/
public Render( int stock)
{
    this.columnaStock = stock;

}


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    
    componente= super.getTableCellRendererComponent(table, value, isSelected, 
            hasFocus, row, column);  
    
    String variableStock=table.getValueAt(row,columnaStock).toString();
    int varStock=Integer.parseInt(variableStock);
    
    if(varStock>0 && varStock<=5 ){
        
              setBackground(new Color(255, 127, 59));
              //setBackground(new Color(88,214,141));
    }else{
        if(varStock==0){    
        setBackground(new Color(255, 91, 91));
        }
        else{     
             setBackground(new Color(255,255,255));
             //setBackground(new Color(46,204,113));    
        }
    }
    
    return componente;

    }
}