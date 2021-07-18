
package Vistas;


import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

public class Render3 extends DefaultTableCellRenderer{
    private Component componente;
    private int  columnaCliente;

    public Render3()
    {


    }


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    
    componente= super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  

    String codigoCliente=table.getValueAt(row,columnaCliente).toString();   
    
     if(value instanceof JButton){
            JButton btn = (JButton)value;
            if(isSelected){
                btn.setForeground(table.getSelectionForeground());
      btn.setBackground(table.getSelectionBackground());
            }else{
                btn.setForeground(table.getForeground());
      btn.setBackground(UIManager.getColor("Button.background"));
            }
            return btn;
        }
    
    if(value instanceof JCheckBox){
            JCheckBox ch = (JCheckBox)value;
            return ch;
        }
    return componente;
    }
}