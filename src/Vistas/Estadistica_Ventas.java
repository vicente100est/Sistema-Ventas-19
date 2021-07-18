/*TABLA2/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vistas;
import java.awt.Color;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class Estadistica_Ventas extends javax.swing.JDialog {
    int Select,bandera;
    String fecha1,fecha2,diaG,mesG,añoG,fechaG,añoDesde,añoHasta,signo_moneda;
    Double total,totalBrutoFact,totalBrutoRem,gananciaTotal,margenGanancia;

    public Estadistica_Ventas(java.awt.Frame parent, boolean modal) {
        //super(parent, modal);
        initComponents();
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
         }catch(Exception e){
            
        } 
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            ResultSet r2= consulta.executeQuery("SELECT signo_moneda FROM  monedas WHERE seleccion_moneda LIKE 'SELECCIONADA'");
            signo_moneda="$";
            while(r2.next()){
                signo_moneda=r2.getString(1);
            }
        }catch(Exception e){
            
        }
        
        setLocationRelativeTo(null);
        panelDetalles.setVisible(false);
        panelEsta.setVisible(false);
        panelDia.setVisible(false);
        panelMes.setVisible(false);
        botonAtras.setVisible(true);
       
        txtGananciaNeta.setText("");
        txtMargenGanancia.setText("");
        txtMargenGanancia.setVisible(false);
        txtGananciaNeta.setVisible(false);
        txtTotalBruto.setVisible(false);
        labelMargenGanancia.setVisible(false);
        labelGananciaNeta.setVisible(false);
        labelTotalBruto.setVisible(false);
        panelEsta.setVisible(true);
       
        setearFecha();
       
        JTableHeader th;
        th = tabla1.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        JTableHeader th2;
        th2 = tabla2.getTableHeader(); 
        Font fuente2 = new Font("Calibri", Font.BOLD, 20); 
        th2.setFont(fuente2); 
        th2.setBackground(new Color(93,116,163));
        th2.setForeground(new Color(255,255,255));
        
        tabla1.getColumnModel().getColumn(0).setMaxWidth(60);
        tabla1.getColumnModel().getColumn(0).setMinWidth(60);
        tabla1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(60);
        tabla1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(60);
        
        tabla1.getColumnModel().getColumn(1).setMaxWidth(110);
        tabla1.getColumnModel().getColumn(1).setMinWidth(110);
        tabla1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(11);
        tabla1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(110);
        
        tabla1.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla1.getColumnModel().getColumn(2).setMinWidth(0);
        tabla1.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        tabla1.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        
        tabla1.getColumnModel().getColumn(4).setMaxWidth(100);
        tabla1.getColumnModel().getColumn(4).setMinWidth(100);
        tabla1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(100);
        tabla1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(100);
        
        tabla1.getColumnModel().getColumn(5).setMaxWidth(100);
        tabla1.getColumnModel().getColumn(5).setMinWidth(100);
        tabla1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(100);
        tabla1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(100);
        
     
    }
    
    public void setearFecha() {     
        Calendar c2 = new GregorianCalendar();
        calendario1.setCalendar(c2);    
        calendario2.setCalendar(c2); 
    }
    
    public void cargarTablaRemitosYFacturas(){
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();

            ResultSet r= consulta.executeQuery("SELECT f.cod_factura, f.fecha, f.tipo_pago,f.total_con_iva, rr.totalBruto, f.tipo_factura FROM referencia_factura rr, factura f WHERE f.cod_factura=rr.cod_factura and f.fecha >= '"+fecha1+"' AND f.fecha <= '"+fecha2+"' group by f.cod_factura");
            total=0.0;
            totalBrutoFact=0.0;
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
            tabla1.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null,null});
                tabla1.setValueAt(r.getString(1),i,0);
                tabla1.setValueAt(r.getString(2),i,1);
                tabla1.setValueAt(r.getString(3),i,2);
                tabla1.setValueAt("FACTURA "+r.getString(6),i,3);
                tabla1.setValueAt(r.getString(5),i,4);
                tabla1.setValueAt(r.getString(4),i,5); 

                total=total+ Double.parseDouble(r.getString(4)); 
                totalBrutoFact=totalBrutoFact+ Double.parseDouble(r.getString(5));         
                i++;
            }
            r.close();
            
            Statement consulta1=conn.createStatement();
            ResultSet r5= consulta1.executeQuery("select SUM(total_con_iva) from factura where fecha BETWEEN '"+fecha1+"' AND '"+fecha2+"'");
            while(r5.next()){
               total=Double.parseDouble(r5.getString(1));
            }
            
            ResultSet r2= consulta.executeQuery("SELECT r.cod_remito, r.fecha, r.tipo_pago, rr.total, rr.totalBruto FROM referencia_remito rr, remito r WHERE r.cod_remito=rr.cod_remito and r.condicion='NO FACTURADO' and r.fecha >= '"+fecha1+"' AND r.fecha <= '"+fecha2+"' group by r.cod_remito");
            while(r2.next()){
                modelo.addRow( new Object [] {null,null,null,null,null});
                tabla1.setValueAt(r2.getString(1),i,0);
                tabla1.setValueAt(r2.getString(2),i,1);
                tabla1.setValueAt(r2.getString(3),i,2);
                tabla1.setValueAt("REMITO",i,3);
                tabla1.setValueAt(r2.getString(5),i,4);
                tabla1.setValueAt(r2.getString(4),i,5);
               
                total=total+ Double.parseDouble(r2.getString(4));
                totalBrutoFact=totalBrutoFact+ Double.parseDouble(r2.getString(5));
                i++;
            }
            tabla1.getRowSorter().toggleSortOrder(1);//----ORDENA LOS ELEMENTOS DE LA TABLA POR UNA COLUMNA EN ESPECIAL

            txtTotal.setText(signo_moneda+" "+String.format("%.2f",total));
            gananciaTotal=total-totalBrutoFact;
            margenGanancia=(gananciaTotal *100)/total;           
            txtGananciaNeta.setText(signo_moneda+" "+String.format("%.2f",gananciaTotal));
            txtMargenGanancia.setText(String.format("%.2f",margenGanancia)+" %");
            txtTotalBruto.setText(signo_moneda+" "+String.format("%.2f",totalBrutoFact));        
            r2.close();
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
    }
    public void cargarTablaRemitosYFacturas22(){
         try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();

            ResultSet r= consulta.executeQuery("SELECT f.fecha, sum(f.total_con_iva) FROM factura f WHERE f.fecha >= '"+fecha1+"' AND f.fecha <= '"+fecha2+"' group by f.fecha order by f.fecha ASC");
            total=0.0;
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla2.getModel();
            tabla2.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null});

                tabla2.setValueAt(r.getString(1),i,0);
                tabla2.setValueAt(r.getString(2),i,1);
                tabla2.setValueAt("FACTURAS Y REMITOS",i,2);         
                String cad= r.getString(2);
                String numeroNuevo = cad.replace(",", "."); //----REMPLAZO LA COMA QUE VIENE DE LA BD POR UN PUNTO XQ SINO ME TIRA ERROR DE FORMATO
                total=total+ Double.parseDouble(numeroNuevo);
                i++;
                j++;
            }
            r.close();
            
            ResultSet r2= consulta.executeQuery("SELECT r.fecha, sum(rr.valor_total) FROM referencia_remito rr, remito r WHERE r.cod_remito=rr.cod_remito and r.condicion='NO FACTURADO' and r.fecha >= '"+fecha1+"' AND r.fecha <= '"+fecha2+"' group by r.fecha order by r.fecha ASC");
            while(r2.next()){               
                bandera=0;
                for (int x=0;x<j;x++){             
                    if(r2.getString(1).equals(tabla2.getValueAt(x,0).toString())){
                        Double contador= Double.parseDouble(tabla2.getValueAt(x,1).toString());
                        contador=contador + Double.parseDouble(r2.getString(2));
                        tabla2.setValueAt(contador.toString(),x,1);
                        bandera=1;
                    }
                }
                if(bandera==0){
                    modelo.addRow( new Object [] {null,null,null,null});        
                    tabla2.setValueAt(r2.getString(1),i,0);
                    tabla2.setValueAt(r2.getString(2),i,1);
                    tabla2.setValueAt("FACTURAS Y REMITOS",i,2);
                    
                    String cad= r2.getString(2);
                    String numeroNuevo = cad.replace(",", "."); //----REMPLAZO LA COMA QUE VIENE DE LA BD POR UN PUNTO XQ SINO ME TIRA ERROR DE FORMATO
                    total=total+ Double.parseDouble(numeroNuevo);
                    i++;
                }
            }    
            r2.close();  
                       
            tabla2.getRowSorter().toggleSortOrder(0);            
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
    }
    
    public void cargarTablaRemitosYFacturasMes(){
         try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();

            ResultSet r= consulta.executeQuery("SELECT f.cod_factura, f.fecha, f.tipo_pago,f.total_con_iva, rr.totalBruto, f.tipo_factura FROM referencia_factura rr, factura f WHERE f.cod_factura=rr.cod_factura and MONTH(f.fecha) >= '"+fecha1+"'AND YEAR(f.fecha) >= '"+añoDesde+"' AND MONTH(f.fecha) <= '"+fecha2+"' AND YEAR(f.fecha) <= '"+añoHasta+"' group by f.cod_factura");
            total=0.0;
            totalBrutoFact=0.0;
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
            tabla1.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null,null});
                tabla1.setValueAt(r.getString(1),i,0);
                tabla1.setValueAt(r.getString(2),i,1);
                tabla1.setValueAt(r.getString(3),i,2);
                tabla1.setValueAt("FACTURA "+r.getString(6),i,3);
                tabla1.setValueAt(r.getString(5),i,4);
                tabla1.setValueAt(r.getString(4),i,5);
                
                total=total+ Double.parseDouble(r.getString(4));
                totalBrutoFact=totalBrutoFact+ Double.parseDouble(r.getString(5));
                i++;
            }
            r.close();
            
            Statement consulta1=conn.createStatement();
            ResultSet r5= consulta1.executeQuery("select SUM(total_con_iva) from factura where MONTH(fecha) >= '"+fecha1+"'AND YEAR(fecha) >= '"+añoDesde+"' AND MONTH(fecha) <= '"+fecha2+"' AND YEAR(fecha) <= '"+añoHasta+"'");
            while(r5.next()){
               total=Double.parseDouble(r5.getString(1));
            }
            
            ResultSet r2= consulta.executeQuery("SELECT r.cod_remito, r.fecha, r.tipo_pago, rr.total, rr.totalBruto FROM referencia_remito rr, remito r WHERE r.cod_remito=rr.cod_remito and r.condicion='NO FACTURADO' and MONTH(r.fecha) >= '"+fecha1+"' AND MONTH(r.fecha) <= '"+fecha2+"' group by r.cod_remito");
            while(r2.next()){
                modelo.addRow( new Object [] {null,null,null,null,null});
                tabla1.setValueAt(r2.getString(1),i,0);
                tabla1.setValueAt(r2.getString(2),i,1);
                tabla1.setValueAt(r2.getString(3),i,2);
                tabla1.setValueAt("REMITO",i,3);
                tabla1.setValueAt(r2.getString(5),i,4);
                tabla1.setValueAt(r2.getString(4),i,5);
               
                total=total+ Double.parseDouble(r2.getString(4));
                totalBrutoFact=totalBrutoFact+ Double.parseDouble(r2.getString(5));
                i++;
            }
            tabla1.getRowSorter().toggleSortOrder(1);

            txtTotal.setText(signo_moneda+" "+String.format("%.2f",total));
            gananciaTotal=total-totalBrutoFact;
            margenGanancia=(gananciaTotal *100)/total;
            
            txtGananciaNeta.setText(signo_moneda+" "+String.format("%.2f",gananciaTotal));
            txtMargenGanancia.setText(String.format("%.2f",margenGanancia)+" %");
            txtTotalBruto.setText(signo_moneda+" "+String.format("%.2f",totalBrutoFact));   
            r2.close();
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
    }
    
    public void cargarTablaRemitosYFacturasMes22(){
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();

            ResultSet r= consulta.executeQuery("SELECT MONTH(f.fecha),YEAR(f.fecha), sum(f.total_con_iva) FROM factura f WHERE MONTH(f.fecha) >= '"+fecha1+"' and YEAR(f.fecha) >= '"+añoDesde+"' AND MONTH(f.fecha) <= '"+fecha2+"' and YEAR(f.fecha) <= '"+añoHasta+"' group by MONTH(f.fecha)");
            total=0.0;
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla2.getModel();
            tabla2.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null});
                int mesFecha=Integer.parseInt(r.getString(1));
                if(mesFecha<10){
                    tabla2.setValueAt("0"+r.getString(1)+"/"+r.getString(2),i,0);
                }else{
                   tabla2.setValueAt(r.getString(1)+"/"+r.getString(2),i,0); 
                }
                tabla2.setValueAt(String.format("%.2f", Double.parseDouble(r.getString(3))),i,1);
                tabla2.setValueAt("FACTURAS Y REMITOS",i,2);
                
                String cad= r.getString(2);
                String numeroNuevo = cad.replace(",", "."); //----REMPLAZO LA COMA QUE VIENE DE LA BD POR UN PUNTO XQ SINO ME TIRA ERROR DE FORMATO
                total=2+total+ Double.parseDouble(numeroNuevo);
                i++;
                j++;
            }
            r.close();
            
            Statement consulta1=conn.createStatement();
            ResultSet r5= consulta1.executeQuery("select SUM(total_con_iva) from factura where MONTH(fecha) >= '"+fecha1+"'AND YEAR(fecha) >= '"+añoDesde+"' AND MONTH(fecha) <= '"+fecha2+"' AND YEAR(fecha) <= '"+añoHasta+"'");
            while(r5.next()){
               total=Double.parseDouble(r5.getString(1));
            }
            
            ResultSet r2= consulta.executeQuery("SELECT MONTH(r.fecha),YEAR(r.fecha),sum(rr.valor_total) FROM referencia_remito rr, remito r WHERE r.cod_remito=rr.cod_remito and r.condicion='NO FACTURADO' and MONTH(r.fecha)>= '"+fecha1+"' and YEAR(r.fecha)>= '"+añoDesde+"'  AND MONTH(r.fecha) <= '"+fecha2+"' and YEAR(r.fecha)>= '"+añoHasta+"' group by MONTH(r.fecha)");

            while(r2.next()){           
                bandera=0;
                for (int x=0;x<j;x++){                  
                    int mesFecha=Integer.parseInt(r2.getString(1));
                    String mesFecha1;
                    if(mesFecha<10){
                        mesFecha1="0"+r2.getString(1)+"/"+r2.getString(2);
                    }else{
                       mesFecha1=r2.getString(1)+"/"+r2.getString(2); 
                    }                    
                    if(mesFecha1.equals(tabla2.getValueAt(x,0).toString())){
                        Double contador= Double.parseDouble(tabla2.getValueAt(x,1).toString().replace(',','.'));
                        contador=contador + Double.parseDouble(r2.getString(3));
                        tabla2.setValueAt(contador.toString(),x,1);
                        bandera=1;
                    }
                }
                if(bandera==0){
                    modelo.addRow( new Object [] {null,null,null,null});
                    int mesFecha=Integer.parseInt(r2.getString(1));
                    if(mesFecha<10){
                        tabla2.setValueAt("0"+r2.getString(1)+"/"+r2.getString(2),i,0);
                    }else{
                       tabla2.setValueAt(r2.getString(1)+"/"+r2.getString(2),i,0); 
                    }
                    tabla2.setValueAt(String.format("%.2f",Double.parseDouble(r2.getString(3))),i,1);
                    tabla2.setValueAt("FACTURAS Y REMITOS",i,2);

                    String cad2= r2.getString(2);
                    String numeroNuevo2 = cad2.replace(",", ".");//----REMPLAZO LA COMA QUE VIENE DE LA BD POR UN PUNTO XQ SINO ME TIRA ERROR DE FORMATO
                    total=2+total+ Double.parseDouble(numeroNuevo2);
                    i++;
                    j++;
                }
            }
            r2.close();
            
            tabla2.getRowSorter().toggleSortOrder(0);           
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
    }
    
    public void cargarTablaRemitos(){
         try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();

            total=0.0;
            totalBrutoRem=0.0;
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
            tabla1.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            
            ResultSet r2= consulta.executeQuery("SELECT r.cod_remito, r.fecha, r.tipo_pago, rr.total, rr.totalBruto FROM referencia_remito rr, remito r WHERE r.cod_remito=rr.cod_remito and r.condicion='NO FACTURADO' and r.fecha >= '"+fecha1+"' AND r.fecha <= '"+fecha2+"' group by r.cod_remito order by r.fecha ASC");
            while(r2.next()){
                modelo.addRow( new Object [] {null,null,null,null,null});
                tabla1.setValueAt(r2.getString(1),i,0);
                tabla1.setValueAt(r2.getString(2),i,1);
                tabla1.setValueAt(r2.getString(3),i,2);
                tabla1.setValueAt("REMITO",i,3);
                tabla1.setValueAt(r2.getString(5),i,4);//total bruto de la factura
                tabla1.setValueAt(r2.getString(4),i,5);
               
                total=total+ Double.parseDouble(r2.getString(4));
                totalBrutoRem=totalBrutoRem+ Double.parseDouble(r2.getString(5));
                i++;
            }
            tabla1.getRowSorter().toggleSortOrder(1);

            txtTotal.setText(signo_moneda+" "+String.format("%.2f",total));
            gananciaTotal=total-totalBrutoRem;
            margenGanancia=(gananciaTotal *100)/total;
            
            txtGananciaNeta.setText(signo_moneda+" "+String.format("%.2f",gananciaTotal));
            txtMargenGanancia.setText(String.format("%.2f",margenGanancia)+" %");
            txtTotalBruto.setText(signo_moneda+" "+String.format("%.2f",totalBrutoRem));   
            r2.close();
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
    }
    public void cargarTablaRemitos22(){
         try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();

            total=0.0;
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla2.getModel();
            tabla2.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            ResultSet r2= consulta.executeQuery("SELECT r.fecha, sum(rr.valor_total) FROM referencia_remito rr, remito r WHERE r.cod_remito=rr.cod_remito and r.condicion='NO FACTURADO' and r.fecha >= '"+fecha1+"' AND r.fecha <= '"+fecha2+"' group by r.fecha order by r.fecha ASC");
            while(r2.next()){
                modelo.addRow( new Object [] {null,null,null,null});
                
                tabla2.setValueAt(r2.getString(1),i,0);
                tabla2.setValueAt(r2.getString(2),i,1);
                tabla2.setValueAt("REMITO",i,2);
            
                String cad2= r2.getString(2);
                String numeroNuevo2 = cad2.replace(",", ".");//----REMPLAZO LA COMA QUE VIENE DE LA BD POR UN PUNTO XQ SINO ME TIRA ERROR DE FORMATO
                total=2+total+ Double.parseDouble(numeroNuevo2);
                i++;
            }
            tabla2.getRowSorter().toggleSortOrder(0);
            r2.close();
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
    }
   
    public void cargarTablaRemitosMes(){
         try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();

            total=0.0;
            totalBrutoRem=0.0;
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
            tabla1.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            
            ResultSet r2= consulta.executeQuery("SELECT r.cod_remito, r.fecha, r.tipo_pago, rr.total, rr.totalBruto FROM referencia_remito rr, remito r WHERE r.cod_remito=rr.cod_remito and r.condicion='NO FACTURADO' and MONTH(r.fecha) >= '"+fecha1+"'AND YEAR(r.fecha) >= '"+añoDesde+"' AND MONTH(r.fecha) <= '"+fecha2+"' AND YEAR(r.fecha) <= '"+añoHasta+"' group by r.cod_remito");
            while(r2.next()){
                modelo.addRow( new Object [] {null,null,null,null,null});
                tabla1.setValueAt(r2.getString(1),i,0);
                tabla1.setValueAt(r2.getString(2),i,1);
                tabla1.setValueAt(r2.getString(3),i,2);
                tabla1.setValueAt("REMITO",i,3);
                tabla1.setValueAt(r2.getString(5),i,4);
                tabla1.setValueAt(r2.getString(4),i,5);
               
                total=total+ Double.parseDouble(r2.getString(4));

                totalBrutoRem=totalBrutoRem+ Double.parseDouble(r2.getString(5));
                i++;
            }
            tabla1.getRowSorter().toggleSortOrder(1);

            txtTotal.setText(signo_moneda+" "+String.format("%.2f",total));
            gananciaTotal=total-totalBrutoRem;
            margenGanancia=(gananciaTotal *100)/total;       
            txtGananciaNeta.setText(signo_moneda+" "+String.format("%.2f",gananciaTotal));
            txtMargenGanancia.setText(String.format("%.2f",margenGanancia)+" %");
            txtTotalBruto.setText(signo_moneda+" "+String.format("%.2f",totalBrutoRem));
            r2.close();
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
    }
    
    public void cargarTablaRemitosMes22(){
         try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            total=0.0;
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla2.getModel();
            tabla2.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            ResultSet r2= consulta.executeQuery("SELECT MONTH(r.fecha),YEAR(r.fecha), sum(rr.valor_total) FROM referencia_remito rr, remito r WHERE r.cod_remito=rr.cod_remito and r.condicion='NO FACTURADO' and MONTH(r.fecha) >= '"+fecha1+"' and YEAR(r.fecha) >= '"+añoDesde+"' AND MONTH(r.fecha) <= '"+fecha2+"' and YEAR(r.fecha) <= '"+añoHasta+"' group by MONTH(r.fecha)");
            while(r2.next()){
                modelo.addRow( new Object [] {null,null,null,null});
                int mesFecha=Integer.parseInt(r2.getString(1));
                if(mesFecha<10){
                    tabla2.setValueAt("0"+r2.getString(1)+"/"+r2.getString(2),i,0);
                }else{
                   tabla2.setValueAt(r2.getString(1)+"/"+r2.getString(2),i,0); 
                }
                tabla2.setValueAt(String.format("%.2f", Double.parseDouble(r2.getString(3))),i,1);
                tabla2.setValueAt("REMITO",i,2);
              
                String cad2= r2.getString(2);
                String numeroNuevo2 = cad2.replace(",", ".");//----REMPLAZO LA COMA QUE VIENE DE LA BD POR UN PUNTO XQ SINO ME TIRA ERROR DE FORMATO
                total=2+total+ Double.parseDouble(numeroNuevo2);
                i++;
            }
            tabla2.getRowSorter().toggleSortOrder(0);
            r2.close();
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
    }
    
    public void cargarTablaFacturas(){
         try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();

            ResultSet r= consulta.executeQuery("SELECT f.cod_factura, f.fecha, f.tipo_pago,f.total_con_iva,rr.totalBruto,f.tipo_factura FROM referencia_factura rr, factura f WHERE f.cod_factura=rr.cod_factura and f.fecha >= '"+fecha1+"' AND f.fecha <= '"+fecha2+"' group by f.cod_factura order by f.fecha ASC");
            total=0.0;
            totalBrutoFact=0.0;
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
            tabla1.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null,null,null});
                tabla1.setValueAt(r.getString(1),i,0);
                tabla1.setValueAt(r.getString(2),i,1);
                tabla1.setValueAt(r.getString(3),i,2);
                tabla1.setValueAt("FACTURA "+r.getString(6),i,3);
                tabla1.setValueAt(r.getString(5),i,4);//total bruto de la factura
                tabla1.setValueAt(r.getString(4),i,5);
                
                total=total+ Double.parseDouble(r.getString(4));
                
                totalBrutoFact=totalBrutoFact+ Double.parseDouble(r.getString(5));        
                i++;
            }
            r.close();
            
            Statement consulta1=conn.createStatement();
            ResultSet r5= consulta1.executeQuery("select SUM(total_con_iva) from factura where fecha BETWEEN '"+fecha1+"' AND '"+fecha2+"'");
            while(r5.next()){
               total=Double.parseDouble(r5.getString(1));
            }
            
            tabla1.getRowSorter().toggleSortOrder(1);

            txtTotal.setText(signo_moneda+" "+String.format("%.2f",total));
            gananciaTotal=total-totalBrutoFact;
            margenGanancia=(gananciaTotal *100)/total;
            
            txtGananciaNeta.setText(signo_moneda+" "+String.format("%.2f",gananciaTotal));
            txtMargenGanancia.setText(String.format("%.2f",margenGanancia)+" %");
            txtTotalBruto.setText(signo_moneda+" "+String.format("%.2f",totalBrutoFact));
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
    }
    
    public void cargarTablaFacturas22(){
         try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();

            //ResultSet r= consulta.executeQuery("SELECT f.fecha, sum(f.total_con_iva) FROM referencia_factura ff, factura f WHERE f.cod_factura=ff.cod_factura and f.fecha >= '"+fecha1+"' AND f.fecha <= '"+fecha2+"' group by f.fecha order by f.fecha ASC");
            ResultSet r= consulta.executeQuery("SELECT f.fecha, sum(f.total_con_iva) FROM factura f WHERE f.fecha >= '"+fecha1+"' AND f.fecha <= '"+fecha2+"' group by f.fecha order by f.fecha ASC");
            total=0.0;
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla2.getModel();
            tabla2.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null});
                tabla2.setValueAt(r.getString(1),i,0);
                tabla2.setValueAt(r.getString(2),i,1);
                tabla2.setValueAt("FACTURA ",i,2);

                total=2+total+ Double.parseDouble(r.getString(2));
                i++;
            }
            r.close();
            tabla2.getRowSorter().toggleSortOrder(0);
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
    }
    
    public void cargarTablaFacturasMes(){
         try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();

            ResultSet r= consulta.executeQuery("SELECT f.cod_factura, f.fecha, f.tipo_pago,f.total_con_iva,rr.totalBruto, f.tipo_factura FROM referencia_factura rr, factura f WHERE f.cod_factura=rr.cod_factura and MONTH(f.fecha) >= '"+fecha1+"' and YEAR(f.fecha) >= '"+añoDesde+"' AND MONTH(f.fecha) <= '"+fecha2+"' and YEAR(f.fecha) <= '"+añoHasta+"' group by f.cod_factura");
            total=0.0;
            totalBrutoFact=0.0;
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
            tabla1.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null,null,null});
                tabla1.setValueAt(r.getString(1),i,0);
                tabla1.setValueAt(r.getString(2),i,1);
                tabla1.setValueAt(r.getString(3),i,2);
                tabla1.setValueAt("FACTURA "+r.getString(6),i,3);
                tabla1.setValueAt(r.getString(5),i,4);
                tabla1.setValueAt(r.getString(4),i,5);//total bruto de la factura
                
                total=total+ Double.parseDouble(r.getString(4));
                
                totalBrutoFact=totalBrutoFact+ Double.parseDouble(r.getString(5));          
                i++;
            }
            r.close();
            
            Statement consulta1=conn.createStatement();
            ResultSet r5= consulta1.executeQuery("select SUM(total_con_iva) from factura where MONTH(fecha) >= '"+fecha1+"'AND YEAR(fecha) >= '"+añoDesde+"' AND MONTH(fecha) <= '"+fecha2+"' AND YEAR(fecha) <= '"+añoHasta+"'");
            while(r5.next()){
               total=Double.parseDouble(r5.getString(1));
            }
            
            tabla1.getRowSorter().toggleSortOrder(1);

            txtTotal.setText(signo_moneda+" "+String.format("%.2f",total));
            
            gananciaTotal=total-totalBrutoFact;
            margenGanancia=(gananciaTotal *100)/total;
            
            txtGananciaNeta.setText(signo_moneda+" "+String.format("%.2f",gananciaTotal));
            txtMargenGanancia.setText(String.format("%.2f",margenGanancia)+" %");
            txtTotalBruto.setText(signo_moneda+" "+String.format("%.2f",totalBrutoFact));
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
    }
    public void cargarTablaFacturasMes22(){
         try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();

            ResultSet r= consulta.executeQuery("SELECT MONTH(f.fecha),YEAR(f.fecha), sum(f.total_con_iva) FROM factura f WHERE MONTH(f.fecha) >= '"+fecha1+"' and YEAR(f.fecha) >= '"+añoDesde+"' AND MONTH(f.fecha) <= '"+fecha2+"' and MONTH(f.fecha) <= '"+añoHasta+"' group by MONTH(f.fecha)");
            total=0.0;
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla2.getModel();
            tabla2.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null});
                int mesFecha=Integer.parseInt(r.getString(1));
                if(mesFecha<10){
                    tabla2.setValueAt("0"+r.getString(1)+"/"+r.getString(2),i,0);
                }else{
                   tabla2.setValueAt(r.getString(1)+"/"+r.getString(2),i,0); 
                }
                tabla2.setValueAt(r.getString(3),i,1);
                tabla2.setValueAt("FACTURA",i,2);
              
                String cad= r.getString(2);
                String numeroNuevo = cad.replace(",", "."); //----REMPLAZO LA COMA QUE VIENE DE LA BD POR UN PUNTO XQ SINO ME TIRA ERROR DE FORMATO
                total=2+total+ Double.parseDouble(numeroNuevo);
                i++;
            }
            r.close();
            tabla2.getRowSorter().toggleSortOrder(0);
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
    }

    public void obtenerFechas(){
        int año = calendario1.getCalendar().get(Calendar.YEAR);
        int mes = calendario1.getCalendar().get(Calendar.MONTH);
        int dia = calendario1.getCalendar().get(Calendar.DAY_OF_MONTH);

        if((mes+1)<10 && (dia>=10)){
            fecha1 = (año+"/0"+(mes+1)+"/"+dia);
        }else{
            if(((mes+1)<10 && (dia<10))){
                fecha1 = (año+"/0"+(mes+1)+"/0"+dia);
            }else{
                fecha1= (año+"/"+(mes+1)+"/"+dia);
            }
        }
        
        int año2 = calendario2.getCalendar().get(Calendar.YEAR);
        int mes2 = calendario2.getCalendar().get(Calendar.MONTH);
        int dia2 = calendario2.getCalendar().get(Calendar.DAY_OF_MONTH);

        if((mes2+1)<10 && (dia2>=10)){
            fecha2 = (año2+"/0"+(mes2+1)+"/"+dia2);
        }else{
            if(((mes2+1)<10 && (dia2<10))){
                fecha2 = (año2+"/0"+(mes2+1)+"/0"+dia2);
            }else{
                fecha2= (año2+"/"+(mes2+1)+"/"+dia2);
            }
        }
    }
    
    public void obtenerFechaMes(){       
        int mes = calendarioMes.getMonth()+1;
        int añoDesde1= calendarioAñoDesde.getYear();
        añoDesde=String.valueOf(añoDesde1);
        if(mes<10){
            fecha1=("0"+mes);
        }else{
           fecha1=(""+mes);
        }
        
        int añoHasta1= calendarioAñoHasta.getYear();
        añoHasta=String.valueOf(añoHasta1);
        int mes2 = calendarioMes1.getMonth()+1;
        if(mes2<10){
            fecha2=("0"+mes2);
        }else{
           fecha2=(""+mes2);
        }       
    }
    
    public void graficarDias(){
        DefaultCategoryDataset dtsc=new DefaultCategoryDataset();
        for(int i=0; i<tabla2.getRowCount();i++){
            dtsc.setValue(Double.parseDouble(tabla2.getValueAt(i,1).toString()),tabla2.getValueAt(i,0).toString(),tabla2.getValueAt(i,2).toString() );
        }
        JFreeChart ch =ChartFactory.createBarChart3D("GRÁFICA DE VENTAS POR DÍA", "DÍAS", "MONTO", dtsc, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel cp= new ChartPanel(ch);
        panelEstadistica.add(cp);
        cp.setBounds(0,0,630,420);
    }
    
    public void graficarArticulos(){
        DefaultCategoryDataset dtsc=new DefaultCategoryDataset();
        for(int i=0; i<tabla2.getRowCount();i++){
            dtsc.setValue(Double.parseDouble(tabla2.getValueAt(i,1).toString()),tabla2.getValueAt(i,0).toString(),tabla2.getValueAt(i,2).toString() );
        }
        JFreeChart ch =ChartFactory.createBarChart3D("GRÁFICA DE VENTAS POR DÍA", "DÍAS", "MONTO", dtsc, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel cp= new ChartPanel(ch);
        panelEstadistica.add(cp);
        cp.setBounds(0,0,630,420);
    }
    
    public void graficarMeses(){
        DefaultCategoryDataset dtsc=new DefaultCategoryDataset();
        for(int i=0; i<tabla2.getRowCount();i++){
            dtsc.setValue(Double.parseDouble(tabla2.getValueAt(i,1).toString().replace(',','.')),tabla2.getValueAt(i,0).toString(),tabla2.getValueAt(i,2).toString() );
        }
        JFreeChart ch =ChartFactory.createBarChart3D("GRÁFICA DE VENTAS POR MES", "MESES", "MONTO", dtsc, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel cp= new ChartPanel(ch);
        panelEstadistica.add(cp);
        cp.setBounds(0,0,630,420);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FiltrarResultados = new javax.swing.ButtonGroup();
        grupoEstadisticas = new javax.swing.ButtonGroup();
        dialogoMenu = new javax.swing.JDialog();
        jLabel8 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        VerFactura = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        panelEstadistica = new javax.swing.JPanel();
        panelTope = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        botonAtras1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panelEsta = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        txtGananciaNeta = new javax.swing.JTextField();
        labelGananciaNeta = new javax.swing.JLabel();
        txtMargenGanancia = new javax.swing.JTextField();
        labelMargenGanancia = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtTotalBruto = new javax.swing.JTextField();
        labelTotalBruto = new javax.swing.JLabel();
        btnMasDetalles = new javax.swing.JButton();
        btnMenosDetalles = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        panelDia = new javax.swing.JPanel();
        soloRemitos = new javax.swing.JRadioButton();
        soloFacturas = new javax.swing.JRadioButton();
        remitosYFacturas = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        calendario2 = new com.toedter.calendar.JDateChooser();
        calendario1 = new com.toedter.calendar.JDateChooser();
        btnConsulta2 = new javax.swing.JButton();
        panelMes = new javax.swing.JPanel();
        remitosPorMes = new javax.swing.JRadioButton();
        facturasPorMes = new javax.swing.JRadioButton();
        remitosYFacturasPorMes = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        calendarioMes = new com.toedter.calendar.JMonthChooser();
        jLabel1 = new javax.swing.JLabel();
        calendarioMes1 = new com.toedter.calendar.JMonthChooser();
        btnConsulta1 = new javax.swing.JButton();
        calendarioAñoHasta = new com.toedter.calendar.JYearChooser();
        calendarioAñoDesde = new com.toedter.calendar.JYearChooser();
        panelDetalles = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        botonAtras = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        dialogoMenu.setBackground(new java.awt.Color(255, 255, 255));
        dialogoMenu.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 35)); // NOI18N
        jLabel8.setText("Ventas Por Dia");
        dialogoMenu.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 420, 240, 30));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXLHover.png"))); // NOI18N
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setFocusPainted(false);
        jButton7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXL.png"))); // NOI18N
        jButton7.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXL.png"))); // NOI18N
        jButton7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXL.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        dialogoMenu.getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, -1, -1));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXLHover.png"))); // NOI18N
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setFocusPainted(false);
        jButton9.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXL.png"))); // NOI18N
        jButton9.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXL.png"))); // NOI18N
        jButton9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXL.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        dialogoMenu.getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 240, -1, -1));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 35)); // NOI18N
        jLabel9.setText("Ventas Por Mes");
        dialogoMenu.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 420, 240, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoBlanco-01.png"))); // NOI18N
        dialogoMenu.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -10, 3235, 1880));

        VerFactura.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        VerFactura.setText("MOSTRAR ");
        VerFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerFacturaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(VerFactura);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ESTADÍSTICAS");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelEstadistica.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelEstadisticaLayout = new javax.swing.GroupLayout(panelEstadistica);
        panelEstadistica.setLayout(panelEstadisticaLayout);
        panelEstadisticaLayout.setHorizontalGroup(
            panelEstadisticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        panelEstadisticaLayout.setVerticalGroup(
            panelEstadisticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        jPanel1.add(panelEstadistica, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 640, 430));

        panelTope.setBackground(new java.awt.Color(204, 204, 204));
        panelTope.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoEsta.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        panelTope.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXLHover.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXL.png"))); // NOI18N
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXL.png"))); // NOI18N
        jButton2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXL.png"))); // NOI18N
        jButton2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXL.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        panelTope.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, -1, -1));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 33)); // NOI18N
        jLabel10.setText("ESTADÍSTICA DE VENTAS");
        panelTope.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        jLabel11.setText("Ir al Menú");
        panelTope.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 650, -1, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXLHover.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXL.png"))); // NOI18N
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXL.png"))); // NOI18N
        jButton3.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXL.png"))); // NOI18N
        jButton3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonEstadisticaXL.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        panelTope.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, -1, -1));

        botonAtras1.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        botonAtras1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtras.png"))); // NOI18N
        botonAtras1.setBorder(null);
        botonAtras1.setBorderPainted(false);
        botonAtras1.setContentAreaFilled(false);
        botonAtras1.setFocusPainted(false);
        botonAtras1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtrasHover.png"))); // NOI18N
        botonAtras1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtrasHover.png"))); // NOI18N
        botonAtras1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtrasHover.png"))); // NOI18N
        botonAtras1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtrasHover.png"))); // NOI18N
        botonAtras1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAtras1ActionPerformed(evt);
            }
        });
        panelTope.add(botonAtras1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 620, -1, 90));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        jLabel12.setText("Ventas por Mes");
        panelTope.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, -1, -1));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        jLabel7.setText("Ventas por Día");
        panelTope.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, -1, -1));

        jPanel1.add(panelTope, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 740));

        panelEsta.setBackground(new java.awt.Color(255, 255, 255));
        panelEsta.setLayout(null);

        tabla2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "FECHA", "VALOR NETO", "TIPO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla2.setRowHeight(25);
        tabla2.setRowMargin(4);
        jScrollPane2.setViewportView(tabla2);

        panelEsta.add(jScrollPane2);
        jScrollPane2.setBounds(10, 50, 650, 130);

        txtGananciaNeta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtGananciaNeta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panelEsta.add(txtGananciaNeta);
        txtGananciaNeta.setBounds(230, 190, 140, 40);

        labelGananciaNeta.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        labelGananciaNeta.setText("Ganancia Neta ");
        panelEsta.add(labelGananciaNeta);
        labelGananciaNeta.setBounds(90, 190, 130, 40);

        txtMargenGanancia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMargenGanancia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panelEsta.add(txtMargenGanancia);
        txtMargenGanancia.setBounds(230, 240, 140, 40);

        labelMargenGanancia.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        labelMargenGanancia.setText("Margen ");
        panelEsta.add(labelMargenGanancia);
        labelMargenGanancia.setBounds(140, 240, 100, 40);

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel15.setText("Total Venta");
        panelEsta.add(jLabel15);
        jLabel15.setBounds(380, 240, 140, 40);

        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panelEsta.add(txtTotal);
        txtTotal.setBounds(490, 240, 170, 40);

        txtTotalBruto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTotalBruto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panelEsta.add(txtTotalBruto);
        txtTotalBruto.setBounds(490, 190, 170, 40);

        labelTotalBruto.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        labelTotalBruto.setText("Total Costo");
        panelEsta.add(labelTotalBruto);
        labelTotalBruto.setBounds(380, 190, 140, 40);

        btnMasDetalles.setBackground(new java.awt.Color(5, 52, 99));
        btnMasDetalles.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        btnMasDetalles.setForeground(new java.awt.Color(255, 255, 255));
        btnMasDetalles.setText("Más Detalles");
        btnMasDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasDetallesActionPerformed(evt);
            }
        });
        panelEsta.add(btnMasDetalles);
        btnMasDetalles.setBounds(10, 10, 170, 30);

        btnMenosDetalles.setBackground(new java.awt.Color(5, 52, 99));
        btnMenosDetalles.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        btnMenosDetalles.setForeground(new java.awt.Color(255, 255, 255));
        btnMenosDetalles.setText("Menos Detalles");
        btnMenosDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosDetallesActionPerformed(evt);
            }
        });
        panelEsta.add(btnMenosDetalles);
        btnMenosDetalles.setBounds(10, 10, 170, 30);

        jPanel1.add(panelEsta, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 430, 670, 310));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDia.setBackground(new java.awt.Color(255, 255, 255));
        panelDia.setOpaque(false);
        panelDia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        grupoEstadisticas.add(soloRemitos);
        soloRemitos.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        soloRemitos.setText("Solo Remitos");
        soloRemitos.setOpaque(false);
        soloRemitos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soloRemitosActionPerformed(evt);
            }
        });
        panelDia.add(soloRemitos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        grupoEstadisticas.add(soloFacturas);
        soloFacturas.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        soloFacturas.setText("Solo Facturas");
        soloFacturas.setOpaque(false);
        panelDia.add(soloFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));

        grupoEstadisticas.add(remitosYFacturas);
        remitosYFacturas.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        remitosYFacturas.setText("Remitos y Facturas");
        remitosYFacturas.setOpaque(false);
        panelDia.add(remitosYFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel6.setText("Hasta");
        panelDia.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 70, -1));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel5.setText("Desde");
        panelDia.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 70, -1));

        calendario2.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        panelDia.add(calendario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 150, 44));

        calendario1.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        panelDia.add(calendario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 140, 44));

        btnConsulta2.setBackground(new java.awt.Color(5, 52, 99));
        btnConsulta2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        btnConsulta2.setForeground(new java.awt.Color(255, 255, 255));
        btnConsulta2.setText("Mostrar Gráfica");
        btnConsulta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulta2ActionPerformed(evt);
            }
        });
        panelDia.add(btnConsulta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 278, 61));

        jPanel2.add(panelDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 460, 220));

        panelMes.setBackground(new java.awt.Color(255, 255, 255));
        panelMes.setOpaque(false);
        panelMes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        grupoEstadisticas.add(remitosPorMes);
        remitosPorMes.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        remitosPorMes.setText("Solo Remitos");
        remitosPorMes.setOpaque(false);
        panelMes.add(remitosPorMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        grupoEstadisticas.add(facturasPorMes);
        facturasPorMes.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        facturasPorMes.setText("Solo Facturas");
        facturasPorMes.setOpaque(false);
        panelMes.add(facturasPorMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));

        grupoEstadisticas.add(remitosYFacturasPorMes);
        remitosYFacturasPorMes.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        remitosYFacturasPorMes.setText("Remitos y Facturas ");
        remitosYFacturasPorMes.setOpaque(false);
        panelMes.add(remitosYFacturasPorMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel4.setText("Desde");
        panelMes.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 70, -1));

        calendarioMes.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        panelMes.add(calendarioMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 140, 40));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel1.setText("Hasta");
        panelMes.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 70, -1));

        calendarioMes1.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        panelMes.add(calendarioMes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 140, 40));

        btnConsulta1.setBackground(new java.awt.Color(5, 52, 99));
        btnConsulta1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        btnConsulta1.setForeground(new java.awt.Color(255, 255, 255));
        btnConsulta1.setText("Mostrar Gráfica");
        btnConsulta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulta1ActionPerformed(evt);
            }
        });
        panelMes.add(btnConsulta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 278, 61));
        panelMes.add(calendarioAñoHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 140, 36));
        panelMes.add(calendarioAñoDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 140, 34));

        jPanel2.add(panelMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 460, 240));

        panelDetalles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° ", "FECHA", "PAGO", "TIPO", "COSTO", "VENTA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla1.setComponentPopupMenu(jPopupMenu1);
        tabla1.setRowHeight(25);
        tabla1.setRowMargin(4);
        tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla1);

        panelDetalles.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 460, 190));

        jPanel2.add(panelDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, -1, -1));

        botonAtras.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        botonAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtras.png"))); // NOI18N
        botonAtras.setBorder(null);
        botonAtras.setBorderPainted(false);
        botonAtras.setContentAreaFilled(false);
        botonAtras.setFocusPainted(false);
        botonAtras.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtrasHover.png"))); // NOI18N
        botonAtras.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtrasHover.png"))); // NOI18N
        botonAtras.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtrasHover.png"))); // NOI18N
        botonAtras.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtrasHover.png"))); // NOI18N
        botonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAtrasActionPerformed(evt);
            }
        });
        jPanel2.add(botonAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, 90));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        jLabel3.setText("Ir hacia atrás");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 37, 170, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 740));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked
                // TODO add your handling code here:
    }//GEN-LAST:event_tabla1MouseClicked

    

    private void btnMasDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasDetallesActionPerformed
        panelDetalles.setVisible(true);
        btnMasDetalles.setVisible(false);
        btnMenosDetalles.setVisible(true);
    }//GEN-LAST:event_btnMasDetallesActionPerformed

    private void btnMenosDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosDetallesActionPerformed
        panelDetalles.setVisible(false);
        btnMasDetalles.setVisible(true);
        btnMenosDetalles.setVisible(false);
    }//GEN-LAST:event_btnMenosDetallesActionPerformed

    private void soloRemitosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soloRemitosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soloRemitosActionPerformed

    private void btnConsulta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulta1ActionPerformed
        txtGananciaNeta.setText("");
        txtMargenGanancia.setText("");
        txtMargenGanancia.setVisible(false);
        txtGananciaNeta.setVisible(false);
        txtTotalBruto.setVisible(false);
        labelMargenGanancia.setVisible(false);
        labelGananciaNeta.setVisible(false);
        labelTotalBruto.setVisible(false);
        panelEsta.setVisible(true); 
        if(remitosPorMes.isSelected()){
            obtenerFechaMes();
            cargarTablaRemitosMes();
            cargarTablaRemitosMes22();
            graficarMeses();
            txtMargenGanancia.setVisible(true);
            txtGananciaNeta.setVisible(true);
            txtTotalBruto.setVisible(true);
            labelMargenGanancia.setVisible(true);
            labelGananciaNeta.setVisible(true);
            labelTotalBruto.setVisible(true);
        }else{
            if(facturasPorMes.isSelected()){
                  obtenerFechaMes();
                  cargarTablaFacturasMes();
                  cargarTablaFacturasMes22();
                  graficarMeses();
                  txtMargenGanancia.setVisible(true);
                  txtGananciaNeta.setVisible(true);
                  txtTotalBruto.setVisible(true);
                  labelMargenGanancia.setVisible(true);
                  labelGananciaNeta.setVisible(true);
                  labelTotalBruto.setVisible(true);
            }else{
                if(remitosYFacturasPorMes.isSelected()){
                    obtenerFechaMes();
                    cargarTablaRemitosYFacturasMes();
                    cargarTablaRemitosYFacturasMes22();
                    graficarMeses();
                    txtMargenGanancia.setVisible(true);
                    txtGananciaNeta.setVisible(true);
                    txtTotalBruto.setVisible(true);
                    labelMargenGanancia.setVisible(true);
                    labelGananciaNeta.setVisible(true);
                    labelTotalBruto.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_btnConsulta1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        dialogoMenu.setVisible(false);
        panelMes.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        dialogoMenu.setVisible(false);
        panelMes.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void btnConsulta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulta2ActionPerformed
        txtGananciaNeta.setText("");
        txtMargenGanancia.setText("");
        txtMargenGanancia.setVisible(false);
        txtGananciaNeta.setVisible(false);
        txtTotalBruto.setVisible(false);
        labelMargenGanancia.setVisible(false);
        labelGananciaNeta.setVisible(false);
        labelTotalBruto.setVisible(false);
        panelEsta.setVisible(true);        
        if(soloRemitos.isSelected()){
            obtenerFechas();
            cargarTablaRemitos();
            cargarTablaRemitos22();
            graficarDias();
            txtMargenGanancia.setVisible(true);
            txtGananciaNeta.setVisible(true);
            txtTotalBruto.setVisible(true);
            labelMargenGanancia.setVisible(true);
            labelGananciaNeta.setVisible(true);
            labelTotalBruto.setVisible(true);
        }else{
            if(soloFacturas.isSelected()){
                obtenerFechas();
                cargarTablaFacturas();
                cargarTablaFacturas22();
                graficarDias();
                txtMargenGanancia.setVisible(true);
                txtGananciaNeta.setVisible(true);
                txtTotalBruto.setVisible(true);
                labelMargenGanancia.setVisible(true);
                labelGananciaNeta.setVisible(true);
                labelTotalBruto.setVisible(true);
            }else{
                if(remitosYFacturas.isSelected()){
                    obtenerFechas();
                    cargarTablaRemitosYFacturas();
                    cargarTablaRemitosYFacturas22();
                    graficarDias();
                    txtMargenGanancia.setVisible(true);
                    txtGananciaNeta.setVisible(true);
                    txtTotalBruto.setVisible(true);
                    labelMargenGanancia.setVisible(true);
                    labelGananciaNeta.setVisible(true);
                    labelTotalBruto.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_btnConsulta2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       panelTope.setVisible(false);
       panelDia.setVisible(true);
       soloFacturas.setSelected(true);
       botonAtras.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       panelTope.setVisible(false);
       panelMes.setVisible(true);
       facturasPorMes.setSelected(true);
       botonAtras.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void botonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAtrasActionPerformed
        panelTope.setVisible(true);
        panelDia.setVisible(false);
        panelMes.setVisible(false);
        panelDetalles.setVisible(false);
        botonAtras.setVisible(false);
    }//GEN-LAST:event_botonAtrasActionPerformed

    private void botonAtras1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAtras1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonAtras1ActionPerformed

    private void VerFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerFacturaActionPerformed
        Select=tabla1.getSelectedRow();
        String tipo="";
        Connection miconexion = conexion.ObtenerConexion();
        Map parametros = new HashMap();
        String codigoFactura= tabla1.getValueAt(Select,0).toString(); 
            
        tipo=tabla1.getValueAt(Select,3).toString();
        if(tipo.equals("FACTURA A") || tipo.equals("FACTURA B") || tipo.equals("FACTURA C")){
            
            parametros.put("codf",codigoFactura);
            try{     
                String reporte="factura.jasper";
                JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                JasperViewer ventanavisor=new JasperViewer(informe,false);
                ventanavisor.setTitle("Reporte de Factura");
                ventanavisor.setVisible(true); 
                //this.setVisible(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } 
        }else{
             parametros.put("codf",codigoFactura);
            try{     
                String reporte="remitoa.jasper";
                JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                JasperViewer ventanavisor=new JasperViewer(informe,false);
                ventanavisor.setTitle("Reporte de Remito");
                ventanavisor.setVisible(true); 
                //this.setVisible(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_VerFacturaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Estadistica_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Estadistica_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Estadistica_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Estadistica_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Estadistica_Ventas dialog = new Estadistica_Ventas(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup FiltrarResultados;
    private javax.swing.JMenuItem VerFactura;
    private javax.swing.JButton botonAtras;
    private javax.swing.JButton botonAtras1;
    private javax.swing.JButton btnConsulta1;
    private javax.swing.JButton btnConsulta2;
    private javax.swing.JButton btnMasDetalles;
    private javax.swing.JButton btnMenosDetalles;
    private com.toedter.calendar.JDateChooser calendario1;
    private com.toedter.calendar.JDateChooser calendario2;
    private com.toedter.calendar.JYearChooser calendarioAñoDesde;
    private com.toedter.calendar.JYearChooser calendarioAñoHasta;
    private com.toedter.calendar.JMonthChooser calendarioMes;
    private com.toedter.calendar.JMonthChooser calendarioMes1;
    private javax.swing.JDialog dialogoMenu;
    private javax.swing.JRadioButton facturasPorMes;
    private javax.swing.ButtonGroup grupoEstadisticas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelGananciaNeta;
    private javax.swing.JLabel labelMargenGanancia;
    private javax.swing.JLabel labelTotalBruto;
    private javax.swing.JPanel panelDetalles;
    private javax.swing.JPanel panelDia;
    private javax.swing.JPanel panelEsta;
    private javax.swing.JPanel panelEstadistica;
    private javax.swing.JPanel panelMes;
    private javax.swing.JPanel panelTope;
    private javax.swing.JRadioButton remitosPorMes;
    private javax.swing.JRadioButton remitosYFacturas;
    private javax.swing.JRadioButton remitosYFacturasPorMes;
    private javax.swing.JRadioButton soloFacturas;
    private javax.swing.JRadioButton soloRemitos;
    private javax.swing.JTable tabla1;
    private javax.swing.JTable tabla2;
    private javax.swing.JTextField txtGananciaNeta;
    private javax.swing.JTextField txtMargenGanancia;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalBruto;
    // End of variables declaration//GEN-END:variables
}
