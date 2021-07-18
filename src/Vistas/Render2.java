
package Vistas;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Render2 extends DefaultTableCellRenderer{
    
    private Component componente;
    private int  columnaCliente;

    public Render2( int codCliente){
        this.columnaCliente = codCliente;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    
        componente= super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  

        String codigoCliente=table.getValueAt(row,columnaCliente).toString();
        int bandera=0;
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
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
        }else{     
              setBackground(new Color(255,255,255)); 
        }
        return componente;

    }
}