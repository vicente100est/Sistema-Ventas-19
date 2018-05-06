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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

public class Render2 extends DefaultTableCellRenderer{
private Component componente;
private int  columnaCliente;



/*public Render( )
{
    this.columnaMes = "";
    this.columnaAño = colA;
}*/
public Render2( int codCliente)
{
    this.columnaCliente = codCliente;

}


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    
    componente= super.getTableCellRendererComponent(table, value, isSelected, 
            hasFocus, row, column);  
    
    //String variableStock=table.getValueAt(row,columnaCliente).toString();
    //int varStock=Integer.parseInt(variableStock);
    String codigoCliente=table.getValueAt(row,columnaCliente).toString();
    int bandera=0;
    try{
            String bd = "ventas";
            String login = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/"+bd;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=(Connection) DriverManager.getConnection(url,login,password);
            Statement consulta=conn.createStatement();
            ResultSet r= consulta.executeQuery("select * from  factura order by cod_factura");
            
            while(r.next()){
                if(codigoCliente.equals(r.getString(3))){
                    bandera=1;
                }
            }
            
    }catch(Exception e){
        
    }
    
    
    if(bandera==1){
        
           setBackground(new Color(187, 255, 150));
           //setBackground(new Color(88,214,141));
    }else{     
          setBackground(new Color(255,255,255));
          //setBackground(new Color(46,204,113));    
    }
    
    
    return componente;

    }
}