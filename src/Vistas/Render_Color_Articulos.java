
package Vistas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Render_Color_Articulos extends DefaultTableCellRenderer{
    private Component componente;
    private int  columnaStock;


    public Render_Color_Articulos( int stock){
        this.columnaStock = stock;
    }


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    
        componente= super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  

        String variableStock=table.getValueAt(row,columnaStock).toString();
        double varStock=Double.parseDouble(variableStock);

        if(varStock>0 && varStock<=2 ){
            Font fuente = new Font("Tahoma", Font.BOLD, 19); 
            this.setFont(fuente);
            setBackground(new Color(255, 214, 30));
        }else{
            if(varStock==0){ 
                Font fuente = new Font("Tahoma", Font.BOLD, 19); 
                this.setFont(fuente);
                setBackground(new Color(252, 112, 112));
            }
            else{  
                Font fuente = new Font("Tahoma", Font.PLAIN, 19); 
                this.setFont(fuente);
                setBackground(new Color(255,255,255));
                setForeground(new Color(0,0,0));
            }
        }
        return componente;
    }
}