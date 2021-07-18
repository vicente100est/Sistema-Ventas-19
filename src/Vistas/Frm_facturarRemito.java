
package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
import java.util.Calendar;
import java.sql.*;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class Frm_facturarRemito extends javax.swing.JFrame {
    //VARIABLES GLOBALES
    Double subtotal=0.0;
    String codigoproveedor;     
    String nomproveedor;
    String codigoproducto;
    String codigoproducto_e;
    double cant_ex=0.0;
    String precio;
    double nueva_cant =0.0;
    String nomproducto;
    int fila;
    int columna;
    String cod_empleado;
    String nomempleado;
    String codigocliente;
    String nomcliente;
    String NOMBRE;
    String dia, mes, año;
    String codigoo="";
    String fecha="";
    String cantidadActual="";
    String totalOriginal;
    String porcentajeIva="0.00";
    
    public void cerrar(){   
        this.setVisible(false);   
    }
     
    public Frm_facturarRemito() {  
        
        initComponents(); 
        setLocationRelativeTo(null);
        //ESTE PANEL SE VA A MANTENER OCULTO HASTA QUE EL USUARIO SELECCIONE SI DESEA ACTUALIZAR LOS PRECIOS O NO (UN CARTEL QUE SE MUESTRA ANTES DE FACTURAR REMITOS)
        this.setVisible(false);
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        //ESTE ES EL JDIALOG QUE SE MUESTRA Y ME PREGUNTA SI DESEO ACTUALIZAR LOS PRECIOS     
        dialogActualizar.setVisible(true); 
        dialogActualizar.setSize(636,294);
        dialogActualizar.setLocationRelativeTo(null);
        
        cargarCombos();
               
        btnCancelarFactura.setEnabled(false);
           
        nuevaFactura();  
        
        //COLOR CABECERA TABLA
        JTableHeader th; 
        th = tabla33.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        //ANCHO DE LAS COLUMNAS
        tabla33.getColumnModel().getColumn(0).setMaxWidth(60);
        tabla33.getColumnModel().getColumn(0).setMinWidth(60);
        tabla33.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(60);
        tabla33.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(60);
        
        tabla33.getColumnModel().getColumn(1).setMaxWidth(120);
        tabla33.getColumnModel().getColumn(1).setMinWidth(120);
        tabla33.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(120);
        tabla33.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(120);
        
        tabla33.getColumnModel().getColumn(2).setMaxWidth(450);
        tabla33.getColumnModel().getColumn(2).setMinWidth(450);
        tabla33.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(450);
        tabla33.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(450);
        
        tabla33.getColumnModel().getColumn(4).setMaxWidth(0);
        tabla33.getColumnModel().getColumn(4).setMinWidth(0);
        tabla33.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        tabla33.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0); 
             
        tabla33.getColumnModel().getColumn(5).setMaxWidth(80);
        tabla33.getColumnModel().getColumn(5).setMinWidth(80);
        tabla33.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(80);
        tabla33.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(80);
        
        tabla33.getColumnModel().getColumn(7).setMaxWidth(0);
        tabla33.getColumnModel().getColumn(7).setMinWidth(0);
        tabla33.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        tabla33.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        
        tabla33.getColumnModel().getColumn(8).setMaxWidth(0);
        tabla33.getColumnModel().getColumn(8).setMinWidth(0);
        tabla33.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
        tabla33.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0); 
        
        tabla33.getColumnModel().getColumn(9).setMaxWidth(0);
        tabla33.getColumnModel().getColumn(9).setMinWidth(0);
        tabla33.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
        tabla33.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0); 
        
        tabla33.getColumnModel().getColumn(10).setMaxWidth(0);
        tabla33.getColumnModel().getColumn(10).setMinWidth(0);
        tabla33.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(0);
        tabla33.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(0); 
        
        
    }
    
    Frm_facturarRemito(Menu_Principal aThis, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
    public void cargarDatos(){
        //TRAIGO LOS DATOS DEL CLIENTE SELECCIONADO
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet res= consulta.executeQuery("select cuit, contribuyente from cliente where cod_cliente='"+txtCodigoCliente.getText()+"'");
            res.next();
            txtCuit.setText(res.getString(1));
            txtContribuyente.setText(res.getString(2));
            res.close();                       
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
    }
        
    public void cargarComboEmpleado() throws ClassNotFoundException{
        //CARGO EL COMBO VENDEDORES
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet res= consulta.executeQuery("select * from empleado order by nombres");

            comboVendedor.removeAllItems();
            comboVendedor.addItem("SELECCIONE EMPLEADO");
            int i=0;
            while(res.next()){
                comboVendedor.addItem(res.getString(2)+" "+res.getString(3));
                i++;
            }
            res.close();
            comboVendedor.setSelectedIndex(1);
            
            
                                
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }  
    }
    
    public void nuevaFactura(){
        //INICIALIZO LOS COMPNENTE SPARA UNA NUEVA FACTURA
        
        checkIva.setVisible(false);
        txtIva.setVisible(false);
        total3.setVisible(false);
        txtTotalBruto.setVisible(false);
        checkIva.setSelected(true);
        txtIva.setEnabled(true);
        txtIva.setText(porcentajeIva);
        
        checkDescuento.setSelected(false);
        txtDescuento.setEnabled(false);
        txtDescuento.setText("0.00");
        
        fact.setText("");

        txtIva2.setText("");
        total.setText("");
        btnCancelarFactura.setEnabled(false);
        habilitar();
              
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            //PARA SABER QUE NUMERO DE FACTURA ES
            ResultSet r= consulta.executeQuery("select cod_factura from  factura order by cod_factura desc");
            r.next();
            fact.setText(r.getString(1));
            int f;
            
            f=Integer.parseInt(fact.getText())+1;
            fact.setText(String.valueOf(f));
            r.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
        txtFacturaNumero.setText("FACTURA N° "+fact.getText());
    }
    
    public void tipoFactura(){
        //ESTE METODO SEGUN SI LA FACTURA ES A O B INGRESA LOS PRODUCTOS CON O SIN EL IVA DISCRIMINADO
        if(txtContribuyente.getText().equals("Responsable Inscripto")){
            comboFactura.setSelectedItem("FACTURA A");
        }else{
            comboFactura.setSelectedItem("FACTURA B");
        }
        
        double variableIva=0.0; 
        String ivaFact=""; 
        
        //SON LAS VARIABLES QUE USO PARA REALIZAR LOS CALCULOS  
        if(comboFactura.getSelectedItem().equals("FACTURA A")){//------------> SI ES FACTURA A TENGO QUE INGRESAR LOS PRODUCTOS SIN IVA Y DISCRIMINAR EL IVA EN EL TOTAL Y SUBTOTAL
            if(comboIva.getSelectedItem().toString().equals("19%")){
                variableIva=1.19;
                ivaFact="19.00";
            }else if(comboIva.getSelectedItem().toString().equals("0%")){
                        variableIva=1.00;
                        ivaFact="0.00";
                   }
            
            int Select=tabla33.getSelectedRow(), bandera=0;
            Double subtotal2=0.0,subTotalBrutoArticulo=0.0,subtotalBruto=0.0; 
            
            String desc=txtDescuento.getText().replace(",",".");
            if (desc.equals("")){
                desc="0.00";
            }
            Double descuentoo= Double.parseDouble(desc);

            int fila2 = tabla33.getRowCount();
            subtotal=0.0;
            //INGRESO TODOS LOS ARTICULOS A LA TABLA (ESTO SE HACE DESDE EL FRAME ENVIAR REMITO)
            for (int x=0;x<=fila2-1;x++) {
                //ESTOS SON LOS CALCULOS QUE HAGO PARA SUMAR EL SUBTOTAL Y TOTAL
                
                double valorSinIva= Double.parseDouble(tabla33.getValueAt(x,10).toString().replace(",", "."));
                tabla33.setValueAt(String.format("%.2f", valorSinIva).replace(",", "."),x,3);
                
                double porcent=(descuentoo * Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")))/100.00;
                double totalsub=(Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla33.getValueAt(x,0).toString());        
                double totalsubBrutoArt=(Double.parseDouble(tabla33.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla33.getValueAt(x,0).toString());

                subTotalBrutoArticulo=subTotalBrutoArticulo+totalsubBrutoArt;           
                subtotalBruto=Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla33.getValueAt(x,0).toString());
                subtotal=totalsub+subtotal;
                subtotal2 =subtotal2 +subtotalBruto;

                Double descuento=Double.parseDouble(tabla33.getValueAt(x,5).toString());
                tabla33.setValueAt(String.format("%.2f", descuentoo).replace(",", "."),x,5);
                tabla33.setValueAt(String.format("%.2f", subtotalBruto ).replace(",", "."),x,4);
                tabla33.setValueAt(String.format("%.2f", totalsub).replace(",", "."),x,6);
                tabla33.setValueAt(String.format("%.2f", totalsubBrutoArt).replace(",", "."),x,8);
            }

            Double ivaa=Double.parseDouble(txtIva2.getText());
            Double subBruto=subtotal;
            Double subtotalNeto= subtotal +(subtotal*ivaa)/100;

            sub.setText(String.format("%.2f",subBruto).replace(",", "."));

            total.setText(String.format("%.2f",subtotalNeto).replace(",", "."));
            total3.setText(""+String.format("%.2f",subtotal).replace(",", "."));
            txtTotalBruto.setText(String.format("%.2f",subTotalBrutoArticulo).replace(",", "."));
            totalOriginal= sub.getText().toString();
            
                     
            total.setText(totalOriginal);
        
            Double ivaa2=Double.parseDouble(ivaFact);
            String original= totalOriginal;
            String originalConPunto= original.replace(",",".");//---remplazo la coma por un punto para que no me tire error de formato
            Double total= Double.parseDouble(originalConPunto) + (ivaa2  * Double.parseDouble(originalConPunto) / 100);
            String totall=total.toString();
            txtIva2.setText(ivaFact);
            Frm_facturarRemito.total.setText(String.format("%.2f",total).replace(",","."));
            
            valorIva.setText(String.format("%.2f",total-subBruto).replace(",","."));
            
            
        }else if(comboFactura.getSelectedItem().equals("FACTURA B")){ //------------> SI ES FACTURA B TENGO QUE INGRESAR LOS ARTICULOS CON EL IVA YA CARGADO, SIN DISCRIMINARLO EN EL TOTAL Y SUBTOTAL
                   
                    //SON LAS VARIABLES QUE USO PARA REALIZAR LOS CALCULOS
                    if(comboIva.getSelectedItem().toString().equals("19%")){
                        variableIva=1.19;
                        ivaFact="19.00";
                    }else if(comboIva.getSelectedItem().toString().equals("0%")){
                               variableIva=1.00;
                               ivaFact="0.00";
                           }

                    int Select=tabla33.getSelectedRow(), bandera=0;
                    Double subtotal2=0.0,subTotalBrutoArticulo=0.0,subtotalBruto=0.0; 

                    String desc=txtDescuento.getText().replace(",",".");
                    if (desc.equals("")){
                        desc="0.00";
                    }
                    Double descuentoo= Double.parseDouble(desc);

                    int fila2 = tabla33.getRowCount();
                    subtotal=0.0;
                    //INGRESO TODOS LOS ARTICULOS A LA TABLA (ESTO SE HACE DESDE EL FRAME ENVIAR REMITO)
                    for (int x=0;x<=fila2-1;x++) {
                        //ESTOS SON LOS CALCULOS QUE HAGO PARA SUMAR EL SUBTOTAL Y TOTAL
                        double valorSinIva= Double.parseDouble(tabla33.getValueAt(x,9).toString().replace(",", "."));
                        tabla33.setValueAt(String.format("%.2f", valorSinIva).replace(",", "."),x,3);
                        
                        double porcent=(descuentoo * Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")))/100.00;
                        double totalsub=(Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla33.getValueAt(x,0).toString());        
                        double totalsubBrutoArt=(Double.parseDouble(tabla33.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla33.getValueAt(x,0).toString());

                        subTotalBrutoArticulo=subTotalBrutoArticulo+totalsubBrutoArt;           
                        subtotalBruto=Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla33.getValueAt(x,0).toString());
                        subtotal=totalsub+subtotal;
                        subtotal2 =subtotal2 +subtotalBruto;

                        Double descuento=Double.parseDouble(tabla33.getValueAt(x,5).toString());
                        tabla33.setValueAt(String.format("%.2f", descuentoo).replace(",", "."),x,5);
                        tabla33.setValueAt(String.format("%.2f", subtotalBruto ).replace(",", "."),x,4);
                        tabla33.setValueAt(String.format("%.2f", totalsub).replace(",", "."),x,6);
                        tabla33.setValueAt(String.format("%.2f", totalsubBrutoArt).replace(",", "."),x,8);
                    }

                    Double ivaa=Double.parseDouble(txtIva2.getText());
                    Double subBruto=subtotal;
                    Double subtotalNeto= subtotal +(subtotal*ivaa)/100;

                    sub.setText(String.format("%.2f",subBruto).replace(",", "."));

                    total.setText(String.format("%.2f",subtotalNeto).replace(",", "."));
                    total3.setText(""+String.format("%.2f",subtotal).replace(",", "."));
                    txtTotalBruto.setText(String.format("%.2f",subTotalBrutoArticulo).replace(",", "."));
                    totalOriginal= sub.getText().toString();


                    total.setText(totalOriginal);

                    Double ivaa2=0.00;
                    String original= totalOriginal;
                    String originalConPunto= original.replace(",",".");//---remplazo la coma por un punto para que no me tire error de formato
                    Double total= Double.parseDouble(originalConPunto) + (ivaa2  * Double.parseDouble(originalConPunto) / 100);
                    String totall=total.toString();
                    txtIva2.setText("0.00");
                    Frm_facturarRemito.total.setText(String.format("%.2f",total).replace(",","."));
                    
                    valorIva.setText(String.format("%.2f",total-subBruto).replace(",","."));
            
        } 
    }
    
    public void MostrarPreciosAntiguos(){
        //SI EL USUARIO SELECCIONA QUE NO DESEA ACTUALIZAR LOS PRECIOS
        if(txtRecibeRemito.getText().equals("0")){
            MostrarRemito();//---> SI HAY SOLO SELECCIONADO UN REMITO
            
        }else{
            MostrarTodosLosRemitos(); //---> SI ESTAN SELECCIONADOS TODOS LOS REMITOS
        }
    }
    
    public void MostrarPreciosNuevos(){
        //SI EL USUARIO SELECCIONA QUE SI DESEA ACTUALIZAR LOS PRECIOS DEL O LOS REMITSO DEL CLIENTE
        if(txtRecibeRemito.getText().equals("0")){
            MostrarRemitoActual();//---> SI HAY SOLO SELECCIONADO UN REMITO
        }else{
            MostrarTodosLosRemitosActual();//---> SI ESTAN SELECCIONADOS TODOS LOS REMITOS
        }
    }
    
    public void MostrarRemito(){
        //SI EL USUARIO SELECCIONA QUE NO DESEA ACTUALIZAR LOS PRECIOS Y HAY SOLO SELECCIONADO UN REMITO DEL CLIENTE
        Double sumaPrecioBruto=0.0;
        String totalSinIva="";
          
        TableColumn  column = null;    
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            //SELECCIONO LOS DATOS DEL ARTICULO DE LA TABLA REFERENCIA REMITO
            ResultSet r= consulta.executeQuery("select * from referencia_remito where cod_remito= "+txtCodiRemito.getText());
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla33.getModel();
            tabla33.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            String total="";
            sumaPrecioBruto=0.0;
            
            
            
            while(r.next()){
                //AGREGO LOS ARTICULOS A LA TABLA
                modelo.addRow( new Object [] {null,null,null,null,null,null,null,null,null,null});
                tabla33.setValueAt(r.getString(6),i,0); //CANTIDAD
                tabla33.setValueAt(r.getString(4),i,1); //CODIGO
                tabla33.setValueAt(r.getString(5),i,2); //DESCRIPCION
                tabla33.setValueAt(r.getString(2).replace(",","."),i,3); //VALOR UNITARIO 
                tabla33.setValueAt(r.getString(2).replace(",","."),i,9); //VALOR UNITARIO original para cuando cambio el iva no se distorcione
                tabla33.setValueAt(r.getString(10).replace(",","."),i,10); //VALOR UNITARIO SIN IVA original para cuando cambio el iva no se distorcione
                tabla33.setValueAt(r.getString(3).replace(",","."),i,4); //TOTAL UNITARIO
                
                tabla33.setValueAt("0.00",i,5); //DESC
                tabla33.setValueAt(r.getString(3).replace(",","."),i,6);//VALOR TOTAL
                tabla33.setValueAt(r.getString(8).replace(",","."),i,7); //UNITARIO BRUTO
                
                double cantidad=Double.parseDouble(r.getString(6).replace(',','.'));
                Double precioBruto=Double.parseDouble(r.getString(8).replace(",","."));
                tabla33.setValueAt(""+(cantidad * precioBruto),i,8);//TOTAL PRECIO DE COSTO
                sumaPrecioBruto=sumaPrecioBruto +(cantidad * precioBruto); //VALOR TOTAL DE COSTO
                
                totalSinIva=r.getString(7).replace(",",".");
                
                i++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Fallo al cargar la tabla remito") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
            System.out.println(e);
        }
        txtTotalBruto.setText(String.format("%.2f", sumaPrecioBruto));
        
            Double sinIva= Double.parseDouble(totalSinIva);
            Double totalConIva= sinIva + (sinIva * Double.parseDouble(porcentajeIva)) /100;
            
            sub.setText(String.valueOf(totalSinIva).replace(",",".")); //seteo el subtotal sin iva
            
            total.setText(String.format("%.2f",totalConIva).replace(",",".")); //seteo el total con el de iva
            totalOriginal= sub.getText(); //el total original sin iva
            txtIva2.setText(porcentajeIva);  
           
    }
    
    public void MostrarTodosLosRemitos(){
        //SI EL USUARIO SELECCIONA QUE NO DESEA ACTUALIZAR LOS PRECIOS Y ESTAN SELECCIONADOS TODOS LOS REMITOS DEL CLIENTE
        Double contador=0.0,contadorBruto=0.0;
        txtIva2.setText(txtDescuento.getText());
               
        TableColumn  column = null;    
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            //CARGO LOS ARTICULOS QUE ESTAN EN TODOS LOS REMITOS
            ResultSet r= consulta.executeQuery("select rr.cantidad,rr.cod_articulo, rr.referencia, rr.valor_unitario, rr.valor_total, rr.unitarioBruto, rr.unitario_sin_iva from referencia_remito rr, remito r where r.cod_remito=rr.cod_remito AND r.condicion='NO FACTURADO' AND r.cod_cliente='"+txtCodigoCliente.getText()+"'");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla33.getModel();
            tabla33.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            String total="";
            while(r.next()){
                //AGREGO LOS ARTICULO A LA TABLA
                modelo.addRow( new Object [] {null,null,null,null,null,null,null,null,null,null});
                tabla33.setValueAt(r.getString(1),i,0);//cant
                tabla33.setValueAt(r.getString(2),i,1);//cod   
                tabla33.setValueAt(r.getString(3),i,2);//descripcion
                tabla33.setValueAt(r.getString(4).replace(",","."),i,3);//v unitario
                tabla33.setValueAt(r.getString(4).replace(",","."),i,9);//VALOR UNITARIO original para cuando cambio el iva no se distorcione
                tabla33.setValueAt(r.getString(7).replace(",","."),i,10); //VALOR UNITARIO SIN IVA original para cuando cambio el iva no se distorcione
                tabla33.setValueAt(r.getString(5).replace(",","."),i,4);//total bruto                
                tabla33.setValueAt("0.00",i,5);//desc    
                tabla33.setValueAt(r.getString(5).replace(",","."),i,6); //valor total
                tabla33.setValueAt(r.getString(6).replace(",","."),i,7); //valor unitario bruto
                
                Double totalBrutoProducto=Double.parseDouble(r.getString(1)) * Double.parseDouble(r.getString(6).replace(",","."));//multiplico la cantidad por el precio bruto del producto
                tabla33.setValueAt(String.format("%.2f", totalBrutoProducto).replace(".",","),i,8);//guardo el resultado en la fila del total bruto 
                                    
                contador=contador + Double.parseDouble(tabla33.getValueAt(i,6).toString().replace(",",".")); //VA SUMANDO EL TOTAL
                contadorBruto=contadorBruto+Double.parseDouble(tabla33.getValueAt(i,8).toString().replace(",","."));//VA SUMANDO EL TOTAL DE COSTO
                i++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Fallo al cargar la tabla remito") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
            System.out.println(e);
        }
        txtIva2.setText(porcentajeIva);
        String contador2=String.format("%.2f", contador).replace(",","."); //SUMA DEL TOTAL
        String contadorBruto2=String.format("%.2f", contadorBruto).replace(",","."); //SUMA DEL TOTAL DE COSTO
       
        txtTotalBruto.setText(contadorBruto2);
        sub.setText(contador2);
        
         Double totalSinIva= Double.parseDouble(contador2.replace(",","."));
         Double totalConIva= totalSinIva + (totalSinIva * Double.parseDouble(porcentajeIva)) /100;
         
        valorIva.setText(String.format("%.2f",totalConIva-totalSinIva).replace(",",".")); //seteo el total con el de iva
            
        total.setText(String.format("%.2f",totalConIva).replace(",",".")); //seteo el total con el 21 de iva

        totalOriginal= sub.getText().replace(",",".");
    }
    
    public void MostrarRemitoActual(){    
        //SI EL USUARIO SELECCIONA QUE SI DESEA ACTUALIZAR LOS PRECIOS Y HAY SOLO SELECCIONADO UN REMITO DEL CLIENTE
        txtIva2.setText(txtDescuento.getText());     
        TableColumn  column = null;
        Double subTotal=0.0,subTotalBruto=0.0;
        
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            Statement consulta2=conn.createStatement();
            ResultSet r= consulta.executeQuery("select * from referencia_remito where cod_remito= "+txtCodiRemito.getText());
                           
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla33.getModel();
            tabla33.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            String total="";
            Double cantidadArticulo=0.0;
            Double precioActual=0.0, precioBrutoActual=0.0, precioXcantidad=0.0,precioBrutoXcantidad=0.0;
            String cadenaPrecio,cadenaPrecioBruto;
            while(r.next()){   
                int bandera=0;
                //AGREGO LOS ARTICULOS A LA TABLA
                modelo.addRow( new Object [] {null,null,null,null,null,null,null,null,null,null});
                tabla33.setValueAt(r.getString(6),i,0); //CANTIDAD
                cantidadArticulo= Double.parseDouble(tabla33.getValueAt(i,0).toString());
                tabla33.setValueAt(r.getString(4),i,1);    //CODIGO ARTICULO
                tabla33.setValueAt(r.getString(5),i,2);    //REFERENCIA   
                
                tabla33.setValueAt("0.00",i,5);    //DESCUENTO 
                ResultSet r2= consulta2.executeQuery("select * from articulo");
                //SELECCIONO EL PRECIO ACTUAL DEL PRODUCTO Y NO EL PRECIO QUE FIGURA EN EL REMITO
                while(r2.next()){
                    if(r2.getString(1).equals(r.getString(4)) && bandera==0){  //SI EL CODIGO DEL ARTICULO ES IGUAL EN LAS TABLAS REFERENCIA_REMITO Y ARTICULO, ACTUALIZA EL PRECIO EN LA TABLA
                        tabla33.setValueAt(r2.getString(12).replace(",","."),i,3);//SETEO LA TABLA PRECIO UNITARIO CON EL VALOR ACTUAL CON IVA
                        
                        tabla33.setValueAt(r2.getString(12).replace(",","."),i,9);//SETEO LA TABLA PRECIO UNITARIO ORIGINAL CON EL VALOR ACTUAL CON IVA
                        tabla33.setValueAt(r2.getString(5).replace(",","."),i,10);//SETEO LA TABLA PRECIO UNITARIO ORIGINAL CON EL VALOR ACTUAL (SIN) IVA
                        
                        tabla33.setValueAt(r2.getString(6).replace(",","."),i,7);//---TRAIGO EL PRECIO BRUTO DE LA TABLA ARTICULO
                        precioActual= Double.parseDouble(tabla33.getValueAt(i,3).toString().replace(",","."));
                        precioBrutoActual= Double.parseDouble(tabla33.getValueAt(i,7).toString().replace(",","."));
                        bandera=1;
                    }                                   
                }
                
                if(bandera==0){//SI BANDERA ES 0 ES XQ NO COINCIDEN LOS CODIGOS, PUEDE SER PORQUE EL ARTICULO FUE ELIMINADO DE LA BASE DE DATOS
                    tabla33.setValueAt(r.getString(2).replace(",","."),i,3);
                    tabla33.setValueAt(r.getString(8).replace(",","."),i,7);//---------TRAIGO EL PRECIO BRUTO DE LA TABLA REFERENCIA REMITO
                                        
                    precioActual= Double.parseDouble(tabla33.getValueAt(i,3).toString().replace(",","."));
                    precioBrutoActual= Double.parseDouble(tabla33.getValueAt(i,7).toString().replace(",","."));
                }
                
                precioXcantidad=precioActual*cantidadArticulo;
                cadenaPrecio=String.format("%.2f",precioXcantidad).replace(".",",");
                tabla33.setValueAt(cadenaPrecio.replace(",","."),i,4); //valor total bruto (sin descuento)                       
                tabla33.setValueAt(cadenaPrecio.replace(",","."),i,6); //valor total
                                        
                precioBrutoXcantidad=precioBrutoActual*cantidadArticulo;
                cadenaPrecioBruto=String.format("%.2f",precioBrutoXcantidad).replace(".",",");
                tabla33.setValueAt(cadenaPrecioBruto.replace(",","."),i,8);
                i++;
            }
            subTotal=0.0;
            subTotalBruto=0.0;
            int fila2 = tabla33.getRowCount();
            for (int x=0;x<=fila2-1;x++){
                Double totalsub=Double.parseDouble((tabla33.getValueAt(x,3).toString().replace(",",".")))*Double.parseDouble((tabla33.getValueAt(x,0).toString())); //a total sub le multiplico la cantidad por el precio de la fila
                subTotal=totalsub+subTotal;   
                subTotalBruto=subTotalBruto+Double.parseDouble(tabla33.getValueAt(x,8).toString().replace(",","."));
            }
            String subTotalString=String.format("%.2f",subTotal).replace(",",".");
            sub.setText(subTotalString);
            
            Double totalSinIva= Double.parseDouble(subTotalString.replace(",","."));
            Double totalConIva= totalSinIva + (totalSinIva * Double.parseDouble(porcentajeIva)) /100;
            
            valorIva.setText(String.format("%.2f",totalConIva-totalSinIva).replace(",",".")); //seteo el total con el de iva
            
            Frm_facturarRemito.total.setText(String.format("%.2f",totalConIva).replace(",","."));                        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Fallo al cargar la tabla remito") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
             System.out.println(e);
        }
        int fila=tabla33.getRowCount();
        String subTotalBrutoString=String.format("%.2f",subTotalBruto).replace(",",".");
        txtTotalBruto.setText(subTotalBrutoString);
        totalOriginal= sub.getText().replace(",",".");
        txtIva2.setText(porcentajeIva);
    }
    
    public void MostrarTodosLosRemitosActual(){
        //SI EL USUARIO SELECCIONA QUE SI DESEA ACTUALIZAR LOS PRECIOS Y ESTAN SELECCIONADOS TODOS LOS REMITOS DEL CLIENTE
        Double precioTotal=0.0,contadorTotal=0.0,contadorBrutoTotal=0.0;
        txtIva2.setText(txtDescuento.getText());          
        TableColumn  column = null;   
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet r= consulta.executeQuery("select rr.cantidad, rr.cod_articulo, rr.referencia, a.total_con_iva, a.valor_bruto, a.valor from articulo a, referencia_remito rr, remito r where rr.cod_articulo=a.cod_articulo AND r.cod_remito=rr.cod_remito AND r.condicion='NO FACTURADO' AND  r.cod_cliente='"+txtCodigoCliente.getText()+"'");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla33.getModel();
            tabla33.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            String total="";
            while(r.next()){
                //AGREGO LOS ARTICULOS A LA TABLA (EL PRECIO DE LOS MISMOS LO TRAIGO DE LA TABLA ARTICULOS NO DE LA REFERENCIA REMITOS (OSEA QUE SON LOS PRECIOS ACTUALES)
                modelo.addRow( new Object [] {null,null,null,null,null,null,null,null,null,null});
                tabla33.setValueAt(r.getString(1),i,0);//CANTIDAD
                tabla33.setValueAt(r.getString(2),i,1); //CODIGO ARTICULO      
                tabla33.setValueAt(r.getString(3),i,2); //DESCRIPCION
                tabla33.setValueAt(r.getString(4).replace(",","."),i,3); //VALOR UNITARIO ACTUAL
                tabla33.setValueAt(r.getString(4).replace(",","."),i,9); //VALOR UNITARIO ACTUAL ORIGINAL
                
                tabla33.setValueAt(r.getString(6).replace(",","."),i,10); //VALOR UNITARIO ACTUAL ORIGINAL SIN IVA
                
                
                tabla33.setValueAt("0.00",i,5); //DESCUENTO
                
                precioTotal= Double.parseDouble(r.getString(1)) * Double.parseDouble(r.getString(4).replace(",",".")); //CANTIDAD POR VALOR ACTUAL DEL ARTICULO
                String precTot=String.format("%.2f",precioTotal).replace(",",".");
                
                tabla33.setValueAt(precTot.replace(",","."),i,4); //total bruto
                tabla33.setValueAt(precTot.replace(",","."),i,6); //VALOR TOTAL
                tabla33.setValueAt(r.getString(5).replace(",","."),i,7); //valor unitario bruto del producto
                
                Double valorBrutoTotal= Double.parseDouble(r.getString(1)) * Double.parseDouble(r.getString(5).replace(",","."));//multiplico la cantidad por el valor unitario bruto de cada producto
                String brutoTot=String.format("%.2f",valorBrutoTotal).replace(".",",");
                tabla33.setValueAt(brutoTot.replace(",","."),i,8);//lo guardo en la fila de valor bruto total
                                    
                contadorTotal=contadorTotal + precioTotal;
                                    
                contadorBrutoTotal=contadorBrutoTotal+valorBrutoTotal;
                i++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Fallo al cargar la tabla remito") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
            System.out.println(e);
        }
        txtIva2.setText(porcentajeIva);
        String contTot=String.format("%.2f",contadorTotal);
        String contBrutoTot=String.format("%.2f",contadorBrutoTotal);
        txtTotalBruto.setText(contBrutoTot.replace(",","."));
        sub.setText(contTot.replace(",","."));
        
        Double totalSinIva= Double.parseDouble(contTot.replace(",","."));
        Double totalConIva= totalSinIva + (totalSinIva * Double.parseDouble(porcentajeIva)) /100;
        
        valorIva.setText(String.format("%.2f",totalConIva-totalSinIva).replace(",",".")); //seteo el total con el  de iva
                    
        total.setText(String.format("%.2f",totalConIva).replace(",",".")); 
        totalOriginal= sub.getText().replace(",",".");
    }
    
    public void setearFecha() {  
      //TRAIGO LA FECHA ACTUAL DELA FACTURA
       Calendar c2 = new GregorianCalendar();
       calendario.setCalendar(c2);    
    }
     
    public void inabilita(){
        //INHABILITO LOS COMPONENTES QUE VOY A USAR EN LA FACTURA
        setearFecha();
        calendario.setEnabled(false);
        comboPago.setEnabled(false);

        btnCancelarFactura.setEnabled(false);
        btnGuardarFactura.setEnabled(false);
        btnCancelarFactura.setEnabled(false);

        txtIva2.setEnabled(false);
        total.setEnabled(false);

        total2.setVisible(false);
        fact.setVisible(false);
        txtCodigoEmpleado.setVisible(false);
        txtCodigoCliente.setVisible(false);
        txtFacturaNumero.setText("FACTURA"); 
    }
    
    public void habilitar(){
        //HABILITO LOS COMPONENTES QUE VOY A USAR EN LA FACTURA
        setearFecha();    
        total.setEditable(false);   
        txtCliente.setEditable(true);
        txtCuit.setEditable(true);
        txtContribuyente.setEditable(true);
        txtCodiRemito.setVisible(false);
        txtCodigoRemito.setVisible(false);
        txtCodigoCliente.setVisible(false);
        txtCodigoEmpleado.setVisible(false);
        fact.setVisible(false);
        calendario.setEnabled(true);
        txtRecibeRemito.setVisible(false);

        comboPago.setEnabled(true);

        btnCancelarFactura.setEnabled(true);
        btnGuardarFactura.setEnabled(true);

        txtIva2.setEnabled(true);
        total.setEnabled(true);
        txtCodigoRemito.setEnabled(true);
    }
        
    public void cargarCombos(){    
            //CARGO LOS COMBOBOX 
            
            comboPago.addItem("EFECTIVO");
            comboPago.addItem("EFECTIVO Y TARJETA");
            comboPago.addItem("CREDITO");
            comboPago.addItem("DEPOSITO");
            comboPago.addItem("CHEQUE");
            comboPago.addItem("SALDO CANCELADO");
            
            comboFactura.addItem("SELECCIONAR");
            comboFactura.addItem("FACTURA A");
            comboFactura.addItem("FACTURA B");

            comboIva.addItem("19%");
            comboIva.addItem("0%");
            
            comboTipoVenta.addItem("PRODUCTOS");
            comboTipoVenta.addItem("SERVICIO");
            comboTipoVenta.setSelectedItem("PRODUCTOS");
            
            try{
                Connection conn =conexion.ObtenerConexion();
                Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
                Statement consulta2=conn.createStatement(); // crea una variable que se encargue del código de sql
                ResultSet r= consulta1.executeQuery("select * from porcentajes");

                while(r.next()){
                    comboIva.setSelectedItem(r.getString(6)); //porcentaje iva factura por defecto

                }
                r.close();
                ResultSet r2= consulta2.executeQuery("select nombre_caja from cajas where estado='ABIERTA' order by cod_caja");
            
                comboCaja.removeAllItems();
                comboCaja.addItem("CAJA CERRADA");
                int i=0;
                while(r2.next()){
                    if(i==0){
                        comboCaja.removeItem("CAJA CERRADA");
                        i++;
                    }
                    comboCaja.addItem(r2.getString(1)); //NOMBRE DELA CAJA

                }
                r2.close();
            }catch(Exception e){

            }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogActualizar = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        botonNoActualizo = new javax.swing.JButton();
        botnSiActualizo = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtIva2 = new javax.swing.JTextField();
        total = new javax.swing.JTextField();
        sub = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        valorIva = new javax.swing.JTextField();
        comboPago = new javax.swing.JComboBox();
        total2 = new javax.swing.JTextField();
        btnGuardarFactura = new javax.swing.JButton();
        btnCancelarFactura = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla33 = new javax.swing.JTable();
        checkDescuento = new javax.swing.JCheckBox();
        txtDescuento = new javax.swing.JTextField();
        txtContribuyente = new javax.swing.JTextField();
        txtCuit = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtRecibeRemito = new javax.swing.JTextField();
        txtTotalBruto = new javax.swing.JTextField();
        total3 = new javax.swing.JTextField();
        checkIva = new javax.swing.JCheckBox();
        txtIva = new javax.swing.JTextField();
        txtDomicilio = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCodigoCliente = new javax.swing.JTextField();
        fact = new javax.swing.JTextField();
        txtCodigoEmpleado = new javax.swing.JTextField();
        txtCodigoRemito = new javax.swing.JTextField();
        txtCodiRemito = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        comboFactura = new javax.swing.JComboBox();
        comboIva = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        comboTipoVenta = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        comboCaja = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        txtFacturaNumero = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        calendario = new com.toedter.calendar.JDateChooser();
        labelCodigo = new javax.swing.JLabel();
        comboVendedor = new javax.swing.JComboBox();
        btnBuscarVendedor = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        itemBuscarVendedor = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();

        dialogActualizar.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel1.setText("¿Desea actualizar el precio de los productos ?");

        botonNoActualizo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonNo.png"))); // NOI18N
        botonNoActualizo.setBorder(null);
        botonNoActualizo.setBorderPainted(false);
        botonNoActualizo.setContentAreaFilled(false);
        botonNoActualizo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonNoHover.png"))); // NOI18N
        botonNoActualizo.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonNoHover.png"))); // NOI18N
        botonNoActualizo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonNoHover.png"))); // NOI18N
        botonNoActualizo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNoActualizoActionPerformed(evt);
            }
        });

        botnSiActualizo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonSi.png"))); // NOI18N
        botnSiActualizo.setBorder(null);
        botnSiActualizo.setBorderPainted(false);
        botnSiActualizo.setContentAreaFilled(false);
        botnSiActualizo.setFocusPainted(false);
        botnSiActualizo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonSiHover.png"))); // NOI18N
        botnSiActualizo.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonSiHover.png"))); // NOI18N
        botnSiActualizo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonSiHover.png"))); // NOI18N
        botnSiActualizo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botnSiActualizoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialogActualizarLayout = new javax.swing.GroupLayout(dialogActualizar.getContentPane());
        dialogActualizar.getContentPane().setLayout(dialogActualizarLayout);
        dialogActualizarLayout.setHorizontalGroup(
            dialogActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogActualizarLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(botnSiActualizo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonNoActualizo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(163, 163, 163))
            .addGroup(dialogActualizarLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel1)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        dialogActualizarLayout.setVerticalGroup(
            dialogActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogActualizarLayout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(dialogActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonNoActualizo)
                    .addComponent(botnSiActualizo))
                .addGap(77, 77, 77))
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Registrar venta");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menumar.png"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel8.setText("Fecha");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 80, 40));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);

        jLabel7.setBackground(new java.awt.Color(239, 255, 239));
        jLabel7.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel7.setText("Cond");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(300, 70, 60, 40);

        jLabel5.setBackground(new java.awt.Color(239, 255, 239));
        jLabel5.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel5.setText("Tipo de factura");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(550, 20, 160, 40);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel13.setText("% IVA Disc");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 120, 40));

        txtIva2.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        txtIva2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIva2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(txtIva2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 80, 36));

        total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 160, 35));

        sub.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sub.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(sub, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 160, 36));

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel15.setText("Total");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 70, 40));

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel16.setText("Subtotal");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 100, 40));

        valorIva.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        valorIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(valorIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 160, 40));

        jPanel1.add(jPanel3);
        jPanel3.setBounds(600, 400, 410, 170);

        comboPago.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        comboPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPagoActionPerformed(evt);
            }
        });
        jPanel1.add(comboPago);
        comboPago.setBounds(740, 350, 270, 39);
        jPanel1.add(total2);
        total2.setBounds(790, 700, 110, 22);

        btnGuardarFactura.setBackground(new java.awt.Color(51, 153, 255));
        btnGuardarFactura.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnGuardarFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardar.png"))); // NOI18N
        btnGuardarFactura.setBorder(null);
        btnGuardarFactura.setBorderPainted(false);
        btnGuardarFactura.setContentAreaFilled(false);
        btnGuardarFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarFactura.setFocusCycleRoot(true);
        btnGuardarFactura.setFocusable(false);
        btnGuardarFactura.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btnGuardarFactura.setRequestFocusEnabled(false);
        btnGuardarFactura.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btnGuardarFactura.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btnGuardarFactura.setVerifyInputWhenFocusTarget(false);
        btnGuardarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarFacturaActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardarFactura);
        btnGuardarFactura.setBounds(0, 400, 170, 150);

        btnCancelarFactura.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelarFactura.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnCancelarFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelar.png"))); // NOI18N
        btnCancelarFactura.setBorder(null);
        btnCancelarFactura.setBorderPainted(false);
        btnCancelarFactura.setContentAreaFilled(false);
        btnCancelarFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCancelarFactura.setFocusCycleRoot(true);
        btnCancelarFactura.setFocusable(false);
        btnCancelarFactura.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarFactura.setRequestFocusEnabled(false);
        btnCancelarFactura.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarFactura.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarFactura.setVerifyInputWhenFocusTarget(false);
        btnCancelarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarFacturaActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelarFactura);
        btnCancelarFactura.setBounds(160, 400, 107, 150);

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel19.setText("Cancelar");
        jPanel1.add(jLabel19);
        jLabel19.setBounds(170, 530, 87, 30);

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel17.setText("Facturar");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(40, 530, 100, 33);

        txtCliente.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txtCliente);
        txtCliente.setBounds(120, 20, 420, 40);

        tabla33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabla33.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CANT", "COD", "DESCRIPCION", "V.UNITARIO", "Total Bruto", "DESC %", "V. TOTAL", "unitario Bruto", "total Bruto", "unitario Original", "uni sin iva"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla33.setRowHeight(25);
        tabla33.setRowMargin(4);
        tabla33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla33MouseClicked(evt);
            }
        });
        tabla33.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabla33KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabla33);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(10, 170, 1000, 170);

        checkDescuento.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        checkDescuento.setText("%Descuento");
        checkDescuento.setOpaque(false);
        checkDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDescuentoActionPerformed(evt);
            }
        });
        jPanel1.add(checkDescuento);
        checkDescuento.setBounds(550, 70, 160, 39);

        txtDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescuentoActionPerformed(evt);
            }
        });
        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyReleased(evt);
            }
        });
        jPanel1.add(txtDescuento);
        txtDescuento.setBounds(710, 70, 90, 40);

        txtContribuyente.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtContribuyente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContribuyenteActionPerformed(evt);
            }
        });
        jPanel1.add(txtContribuyente);
        txtContribuyente.setBounds(360, 70, 180, 40);

        txtCuit.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtCuit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txtCuit);
        txtCuit.setBounds(120, 70, 170, 40);

        jLabel9.setBackground(new java.awt.Color(239, 255, 239));
        jLabel9.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel9.setText("Domicilio");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(10, 120, 110, 40);

        jLabel10.setBackground(new java.awt.Color(239, 255, 239));
        jLabel10.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel10.setText("NIT");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(70, 70, 50, 40);
        jPanel1.add(txtRecibeRemito);
        txtRecibeRemito.setBounds(380, 510, 50, 30);
        jPanel1.add(txtTotalBruto);
        txtTotalBruto.setBounds(320, 510, 50, 30);
        jPanel1.add(total3);
        total3.setBounds(440, 510, 50, 30);

        checkIva.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        checkIva.setText("%Iva");
        checkIva.setOpaque(false);
        checkIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkIvaActionPerformed(evt);
            }
        });
        jPanel1.add(checkIva);
        checkIva.setBounds(310, 390, 77, 50);

        txtIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIvaActionPerformed(evt);
            }
        });
        txtIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIvaKeyReleased(evt);
            }
        });
        jPanel1.add(txtIva);
        txtIva.setBounds(320, 430, 50, 30);

        txtDomicilio.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtDomicilio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txtDomicilio);
        txtDomicilio.setBounds(120, 120, 420, 40);

        jLabel11.setBackground(new java.awt.Color(239, 255, 239));
        jLabel11.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel11.setText("Cliente");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(40, 20, 80, 40);

        txtCodigoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoClienteActionPerformed(evt);
            }
        });
        jPanel1.add(txtCodigoCliente);
        txtCodigoCliente.setBounds(440, 430, 50, 30);

        fact.setEditable(false);
        fact.setBackground(new java.awt.Color(51, 255, 204));
        fact.setFont(new java.awt.Font("Cambria", 1, 30)); // NOI18N
        fact.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fact.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fact.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        fact.setEnabled(false);
        fact.setOpaque(false);
        fact.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        fact.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel1.add(fact);
        fact.setBounds(380, 430, 50, 30);

        txtCodigoEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoEmpleadoActionPerformed(evt);
            }
        });
        jPanel1.add(txtCodigoEmpleado);
        txtCodigoEmpleado.setBounds(320, 470, 50, 22);
        jPanel1.add(txtCodigoRemito);
        txtCodigoRemito.setBounds(380, 470, 50, 30);

        txtCodiRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodiRemitoActionPerformed(evt);
            }
        });
        jPanel1.add(txtCodiRemito);
        txtCodiRemito.setBounds(440, 470, 50, 30);

        jLabel6.setBackground(new java.awt.Color(239, 255, 239));
        jLabel6.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel6.setText("Tipo de venta");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(560, 120, 140, 40);

        comboFactura.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        comboFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFacturaActionPerformed(evt);
            }
        });
        jPanel1.add(comboFactura);
        comboFactura.setBounds(710, 20, 300, 40);

        comboIva.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        comboIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIvaActionPerformed(evt);
            }
        });
        jPanel1.add(comboIva);
        comboIva.setBounds(900, 70, 110, 40);

        jLabel18.setBackground(new java.awt.Color(239, 255, 239));
        jLabel18.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel18.setText("% iVA");
        jPanel1.add(jLabel18);
        jLabel18.setBounds(830, 70, 70, 40);

        comboTipoVenta.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        comboTipoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoVentaActionPerformed(evt);
            }
        });
        jPanel1.add(comboTipoVenta);
        comboTipoVenta.setBounds(710, 120, 300, 40);

        jLabel12.setBackground(new java.awt.Color(239, 255, 239));
        jLabel12.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel12.setText("Tipo de pago");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(600, 350, 140, 40);

        comboCaja.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCajaActionPerformed(evt);
            }
        });
        jPanel1.add(comboCaja);
        comboCaja.setBounds(10, 350, 270, 40);

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1020, 580));

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtFacturaNumero.setFont(new java.awt.Font("Calibri", 1, 33)); // NOI18N
        txtFacturaNumero.setText("FACTURA DE VENTA");
        jPanel2.add(txtFacturaNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 290, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, -1));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 60));

        calendario.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jPanel4.add(calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 160, 40));
        jPanel4.add(labelCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 110, 30));

        comboVendedor.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        comboVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboVendedorActionPerformed(evt);
            }
        });
        jPanel4.add(comboVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 240, 40));

        btnBuscarVendedor.setBackground(new java.awt.Color(93, 116, 163));
        btnBuscarVendedor.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoLupaF1.png"))); // NOI18N
        btnBuscarVendedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVendedorActionPerformed(evt);
            }
        });
        btnBuscarVendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBuscarVendedorKeyPressed(evt);
            }
        });
        jPanel4.add(btnBuscarVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, -1, 40));

        jLabel14.setBackground(new java.awt.Color(239, 255, 239));
        jLabel14.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel14.setText("Vendedor");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 120, 40));

        panelImage1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 660));

        jMenuBar2.setBackground(new java.awt.Color(255, 255, 255));

        jMenu3.setBackground(new java.awt.Color(255, 255, 255));
        jMenu3.setText("Opciones");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N

        itemBuscarVendedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itemBuscarVendedor.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        itemBuscarVendedor.setText("Buscar vendedor");
        itemBuscarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarVendedorActionPerformed(evt);
            }
        });
        jMenu3.add(itemBuscarVendedor);
        jMenu3.add(jSeparator1);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem1.setText("Ancho de la impresión");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar2.add(jMenu3);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            JOptionPane.showMessageDialog(null,"Ingrese el Dato");
        }

    }//GEN-LAST:event_formKeyTyped

    private void comboPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPagoActionPerformed
        String pago= comboPago.getSelectedItem().toString();
        if(pago.equals("CREDITO")){
            Agregar_Credito form= new Agregar_Credito ();
            Agregar_Credito.txtRecibe.setText("1");
            Agregar_Credito.txtCodFactura.setText(fact.getText());
            Agregar_Credito.txtTotalCredito.setText(total.getText());
            Agregar_Credito.txtSaldo.setText(total.getText());
            Agregar_Credito.cargarCredito();
            
            try{
                Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql                             

                Statement consulta2=conn.createStatement(); // crea una variable que se encargue del código de sql

                String codigoCaja="";                                        
                //SELECCIONO EL CODIGO DE LA CAJA ABIERTA SELECCIONADA EN EL comboCaja PARA GUARDARLA EN EL PAGO DE EFECTIVO
                ResultSet  res22= consulta2.executeQuery("select cod_caja from apertura_caja WHERE estado='ABIERTA' AND nombre_caja='"+comboCaja.getSelectedItem()+"'");
                res22.next();
                codigoCaja=res22.getString(1);
                Agregar_Credito.txtCodCaja.setText(codigoCaja);

            } 
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"ERROR EN LA BASE DE DATOS");
                 System.out.println(ex);
            }
            
            form.setVisible(true);
            form.toFront();
        }else if(pago.equals("DEPOSITO")){
                Agregar_Deposito form= new Agregar_Deposito ();
                Agregar_Deposito.txtRecibe.setText("1");
                Agregar_Deposito.txtCodFactura.setText(fact.getText());
                Agregar_Deposito.txtTotal.setText(total.getText());
                Agregar_Deposito.cargarDeposito();
                Calendar c2 = new GregorianCalendar();
                Agregar_Deposito.calendario.setCalendar(c2);
                try{
                    Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql                             
                    Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql

                    ResultSet r= consulta1.executeQuery("select fecha from venta_deposito where cod_venta='"+fact.getText()+"'");    

                    while(r.next()){
                        String fechaDeposito=r.getString(1);
                        int año=Integer.parseInt(fechaDeposito.substring(0,4));
                        int mes=Integer.parseInt(fechaDeposito.substring(5,7));
                        int dia=Integer.parseInt(fechaDeposito.substring(8,10));
                        Calendar c3 = new GregorianCalendar(año,mes-1,dia);
                        Agregar_Deposito.calendario.setCalendar(c3);

                    }

                } 
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"ERROR EN LA BASE DE DATOS");
                    System.out.println(ex);
                }
    
                form.setVisible(true);
                form.toFront();
            }else if(pago.equals("EFECTIVO Y TARJETA")){
                    Agregar_TarjetaYEfectivo form= new Agregar_TarjetaYEfectivo  ();
                    form.txtCodFactura.setText(fact.getText());
                    form.txtTotal.setText(total.getText());
                    
                    form.txtEfectivo.setText("0.00");
                    form.txtTarjeta.setText("0.00");
                    form.txtEfectivo.setText("0.00");
                    form.txtSaldo.setText(total.getText());
                    form.txtVuelto.setText("0.00");

                    try{
                        Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql                             
                        Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
                        Statement consulta2=conn.createStatement(); // crea una variable que se encargue del código de sql

                        ResultSet r= consulta1.executeQuery("select * from venta_mixta where cod_venta='"+fact.getText()+"'");    
                        while(r.next()){
                          form.txtEfectivo.setText(r.getString(4));
                          form.txtTarjeta.setText(r.getString(4));
                          form.txtEfectivo.setText(r.getString(5));
                          form.txtSaldo.setText(r.getString(6));
                          form.txtVuelto.setText(r.getString(7));
                        }
                        
                        String codigoCaja="";                                        
                        //SELECCIONO EL CODIGO DE LA CAJA ABIERTA SELECCIONADA EN EL comboCaja PARA GUARDARLA EN EL PAGO DE EFECTIVO
                        ResultSet  res22= consulta2.executeQuery("select cod_caja from apertura_caja WHERE estado='ABIERTA' AND nombre_caja='"+comboCaja.getSelectedItem()+"'");
                        res22.next();
                        codigoCaja=res22.getString(1);
                        form.txtCodCaja.setText(codigoCaja);

                    } 
                    catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,"ERROR EN LA BASE DE DATOS");
                        System.out.println(ex);
                    }

                    form.setVisible(true);
                    form.toFront();
                    form.txtEfectivo.requestFocus();
                }else if(pago.equals("CHEQUE")){
                        Agregar_Cheque form= new Agregar_Cheque ();
                        Agregar_Cheque.txtFecha.setText("2");
                        Agregar_Cheque.txtRecibe.setText("1");
                        Agregar_Cheque.txtCodFactura.setText(fact.getText());
                        Agregar_Cheque.txtTotal.setText(total.getText());
                        Agregar_Cheque.cargarDeposito();
                        Calendar c2 = new GregorianCalendar();
                        Agregar_Cheque.calendario.setCalendar(c2);
                        Agregar_Cheque.calendarioVencimiento.setCalendar(c2);
                        try{
                            Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql                             
                            Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql

                            ResultSet r= consulta1.executeQuery("select fecha_emision, fecha_vencimiento from venta_cheque where cod_venta='"+fact.getText()+"'");    

                            while(r.next()){
                                String fechaDeposito=r.getString(1);
                                int año=Integer.parseInt(fechaDeposito.substring(0,4));
                                int mes=Integer.parseInt(fechaDeposito.substring(5,7));
                                int dia=Integer.parseInt(fechaDeposito.substring(8,10));
                                Calendar c3 = new GregorianCalendar(año,mes-1,dia);
                                Agregar_Cheque.calendario.setCalendar(c3);
                                
                                String fechaVencimiento=r.getString(2);
                                int año2=Integer.parseInt(fechaVencimiento.substring(0,4));
                                int mes2=Integer.parseInt(fechaVencimiento.substring(5,7));
                                int dia2=Integer.parseInt(fechaVencimiento.substring(8,10));
                                Calendar c4 = new GregorianCalendar(año2,mes2-1,dia2);  
                                Agregar_Cheque.calendarioVencimiento.setCalendar(c4);
                                Agregar_Cheque.txtTotal.setText(total.getText());

                            }

                        } 
                        catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null,"ERROR EN LA BASE DE DATOS");
                            System.out.println(ex);
                        }

                        form.setVisible(true);
                        form.toFront();
                    }
    }//GEN-LAST:event_comboPagoActionPerformed

    private void txtCodigoEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoEmpleadoActionPerformed

    private void txtCodigoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoClienteActionPerformed

    private void btnGuardarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarFacturaActionPerformed
       //GUARDO LA FACTURA EN LA BD 
        
        String tipoFacturaString="",titularEmpresa="", nombreEmpresa="",puntoVenta="",direccionEmpresa="",cuitEmpresa="",contribuyenteEmpresa="",telefonoEmpresa="",ingresosBrutos="",inicioActividades="";
        String nombreCliente="",cuitCliente="",tipoCliente="",direccionCliente="";
        int tipoFacturaInt=0;
        try{
            if (calendario.getDate() == null) {
                JOptionPane.showMessageDialog(null, "La fecha estaba vacia","Advertencia",JOptionPane.WARNING_MESSAGE);
                setearFecha();
            }else{
                if(comboVendedor.getSelectedItem().equals("SELECCIONE EMPLEADO")) {
                    JOptionPane.showMessageDialog(null, "Debe Seleccionar un vendedor","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else{
                    if( tabla33.getRowCount() <= 0 ) {
                        JOptionPane.showMessageDialog(null, "No Hay Ningun Producto a Facturar","Advertencia",JOptionPane.ERROR_MESSAGE);
                    }else{
                        try{
                            
                            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                            Statement consulta=conn.createStatement();
                            Statement consulta1=conn.createStatement();
                            
                            Statement consultaEmpresa=conn.createStatement();
                            ResultSet resEmpresa= consultaEmpresa.executeQuery("select * from empresa");
                                    
                                    
                            while(resEmpresa.next()){ //TRAIGO LOS DATOS DE LA EMPRESA PARA PASARCELOS A LA FACTURA
                                titularEmpresa= (resEmpresa.getString(2));
                                nombreEmpresa= (resEmpresa.getString(3));
                                cuitEmpresa= (resEmpresa.getString(4));
                                direccionEmpresa= (resEmpresa.getString(6)+", "+resEmpresa.getString(7));
                                contribuyenteEmpresa= (resEmpresa.getString(2));
                                ingresosBrutos= (resEmpresa.getString(13));
                                telefonoEmpresa= (resEmpresa.getString(9));
                                inicioActividades= (resEmpresa.getString(12));
                                puntoVenta= (resEmpresa.getString(14));
                                           
                            }
                            
                            int fil = tabla33.getRowCount();
                            int col = tabla33.getColumnCount();
                                    
                            int año = calendario.getCalendar().get(Calendar.YEAR);
                            int mes = calendario.getCalendar().get(Calendar.MONTH);
                            int dia = calendario.getCalendar().get(Calendar.DAY_OF_MONTH);

                            //ACOMODO LA FECHA PARA LA BD
                            if((mes+1)<10 && (dia>=10)){
                                fecha = (año+"/0"+(mes+1)+"/"+dia);
                            }else{
                                if(((mes+1)<10 && (dia<10))){
                                    fecha = (año+"/0"+(mes+1)+"/0"+dia);
                                }else{
                                    fecha = (año+"/"+(mes+1)+"/"+dia);
                                }
                            }

                            Double variableIva=0.00, porcentIva=0.0,porcentajeIvaFactura=0.0;
                            int tipoPorcentajeIva=0; 
                            String ivaFact="";
                            //SEGUN EL VALOR DEL IVA SELECCIONADO SETEO LAS VARIABLES QUE REALIZAN LOS CALCULOS
                            if(comboIva.getSelectedItem().toString().equals("19%")){
                                variableIva=1.19;
                                porcentIva=0.19;
                                ivaFact="19.00";
                                porcentajeIvaFactura=19.0;
                                tipoPorcentajeIva=5; //CODIGO EN LA TABLA DE EXCEL DEL IVA 
                            }else if(comboIva.getSelectedItem().toString().equals("0%")){
                                        variableIva=1.00;
                                        porcentIva=0.00;
                                        ivaFact="0.00";
                                        porcentajeIvaFactura=0.0;
                                        tipoPorcentajeIva=3;//CODIGO EN LA TABLA DE EXCEL DEL IVA 0
                                   }
                            Double subTotalNeto=Double.parseDouble(sub.getText());
                            String subTotalNetoString="";
                            
                            //
                            if (comboFactura.getSelectedItem().toString().equals("FACTURA A")){
                                tipoFacturaString="A";  
                                tipoFacturaInt=1;//LO USO EN LA FACT ELECTRONICA
                                
                                subTotalNeto=subTotalNeto;
                                subTotalNeto=Math.round(subTotalNeto * 100.0)/ 100.0;
                            }else if (comboFactura.getSelectedItem().toString().equals("FACTURA B")){
                                       subTotalNeto=subTotalNeto/variableIva;
                                       subTotalNeto=Math.round(subTotalNeto * 100.0)/ 100.0;
                                       tipoFacturaString="B";
                                       tipoFacturaInt=6;//LO USO EN LA FACT ELECTRONICA
                                  }
                            subTotalNetoString=String.format("%.2f", subTotalNeto).replace(",", ".");
                            int x,y;
                                    
                            String ivaFactura=txtIva2.getText(); //GUARDO EL IVA QUE TIENE LA FACTURA
                            if(ivaFactura.equals("0.00") && comboFactura.getSelectedItem().toString().equals("FACTURA B")){
                                ivaFactura=ivaFact;
                            }

                            
                            //*****************************************************************************************************************************************
                            Double montoIvaa=Double.parseDouble(txtIva2.getText());
                            montoIvaa=Math.round(montoIvaa * 100.0)/ 100.0;
                                    
                            Double montoTotal=Double.parseDouble(total.getText());
                            montoTotal=Math.round(montoTotal * 100.0)/ 100.0;
                                    
                            Double ivaCalculado=0.0;
                            if (comboFactura.getSelectedItem().toString().equals("FACTURA A")){
                                        ivaCalculado=subTotalNeto*porcentIva;
                            }else if (comboFactura.getSelectedItem().toString().equals("FACTURA B")){
                                        ivaCalculado=montoTotal-subTotalNeto;
                            }
                                    
                            ivaCalculado=Math.round(ivaCalculado * 100.0)/ 100.0;
                                                               

                            
                        //***********INICIO PARTE FACTURACION ELECTRONICA *******************************************************************************************************
                            /*Connection miconexion = conexion.ObtenerConexion();
                            FacturaElectronica fe=new FacturaElectronica(); 
                            FacturableE facti=(FacturableE) new FacturaElectronica(); 
                            ArrayList listadoIva=new ArrayList(); 
                            Double montoIva=0.00; //PARA DETERMINAR SI HAY QUE CARGAR IVA O NO 
                            if(montoTotal > subTotalNeto){                    
                                float subT=Float.parseFloat(String.valueOf(subTotalNeto));     //subtotal sin iva               
                                float totT=Float.parseFloat(String.valueOf(ivaCalculado));  //total del iva
                                System.out.println(subT+" "+totT+" IVA");
               
                                TiposIva iva=new TiposIva(tipoPorcentajeIva,subT,totT,porcentajeIvaFactura); //TIPOS IVA (ID TIPO DE IVA SEGUN TABLA ADJUNTA,SUBTOTAL,TOTAL,MONTOIVA)                    
                                listadoIva.add(iva);   
                                montoIva=ivaCalculado; 
                                montoIva=Math.round(montoIva * 100.0)/ 100.0;
                            }else{                    
                                listadoIva=null;                
                            }                
                            //TRIBUTOS LISTADO IDEM IVA PERO PARA IMPUESTOS DE EXPORTACION 
                            ArrayList listadoTrib=null;                
                            ArrayList <DetalleFacturas> listadoDetalle=new ArrayList();
            
                            DetalleFacturas detalle;                
                            double precio=0.00;
                                    
                            for (x=0;x<=fil-1;x++) {
                                //artic=tabla.getValueAt(x,1).toString();          
                                detalle=new DetalleFacturas();  
                                
                                String codigoProducto=tabla33.getValueAt(x,1).toString();
                                if(codigoProducto.length()>9){
                                    codigoProducto = codigoProducto.substring(0,5);
                                }
                                    
                                detalle.setCodigo(codigoProducto);  //codigo producto 
                                        
                                detalle.setDescripcion(tabla33.getValueAt(x,2).toString()); //descripcion producto                   
                                detalle.setCantidadS(tabla33.getValueAt(x,0).toString()); //cantidad producto  
  
                                detalle.setDescuentoS(tabla33.getValueAt(x,5).toString()); //descuento producto   
                                        
                                        
                                precio=Math.round(Double.parseDouble(tabla33.getValueAt(x,3).toString()) * 100.0) / 100.0;    //precio unitario                 
                                detalle.setPrecioUnitarioS(String.valueOf(precio));    //agrego el precio 
                                detalle.setPrecioTotalS(tabla33.getValueAt(x,6).toString()); //valor total 2  
                                listadoDetalle.add(detalle); //agrego la fila
                            }
                              
                            int condicion=1;                
                            int ptoVta=Integer.parseInt(puntoVenta);  //PUNTO DE VENTA VENDEDOR REGISTRADO PARA WEBSERVICE   (3 es este certificado)           
                            
                            String tipoDeVenta=comboTipoVenta.getSelectedItem().toString();
                            if(tipoDeVenta.equals("PRODUCTOS")){
                                tipoDeVenta="1";
                            }else{
                                tipoDeVenta="2";
                            }
                            int tipoVta=Integer.parseInt(tipoDeVenta); //SEGUN TABLA 1-PRODUCTOS 2- SERVICIOS
                                                        
                            String cuitVendedor=cuitEmpresa;
                            
                            Integer idPed=2; //1 homologacion 2 produccion
                                
                               //TRANSACCIONABLE INTERFAZ PARA EL OBJETO CONECCIONES, EN MI CASO LO MANEJÉ ASÍ, LO IMPORTANTE ES OBTENER LA CONEXION Y PASARLA COMO PARÁMETRO EN LA FUNCIÓN Transaccionable trr=new Conecciones(); /*                LA LIBRERÍA RESPONDE CON EL NÚMERO DE COMPROBANTE GENERADO Y ALMACENA Y ABRE EN LA CARPETA FACTURAS ELECTRONICAS EL ARCHIVO POR TRIPLICADO COMO RESULTADO DE LA OPERACIÓN YA AUTORIZADA, EN CASO DE PRODUCIRSE UN ERROR LO INFORMA POR MEDIO DE JOPTIONPANE SIENDO EL PROCESO EXITOSO SE ALMACENA EN LA TABLA FISCAL LOS DATOS DEL COMPROBANTE AUTORIZADO */                
                            /*nombreCliente=txtCliente.getText(); //NOMBRE DEL CLIENTE        
                            cuitCliente=txtCuit.getText(); //CUIT DEL CLIENTE
                            tipoCliente=txtContribuyente.getText(); //TIPO DE CONTRIBUYENTE CLIENTE
                                    
                            if(tipoCliente==""){
                                tipoCliente="Consumidor Final";
                            }
                            direccionCliente=txtDomicilio.getText();//DIRECCION CLIENTE       
                            
                            Integer codigoCliente = Integer.valueOf(fact.getText()); //EN CODIGO CLIENTE GUARDO EL COD_FACTURA PARA ENLAZAR LA TABLA FACTURA CON LA TABLA FISCAL   
                            
                            cuitCliente=txtCuit.getText().toString();
                            if(cuitCliente.equals("") || txtContribuyente.getText().equals("Consumidor Final")){
                                cuitCliente="0";
                            }
                                    
                            int tipoComprobante=tipoFacturaInt;   //6 fACTURA B....1 FACTURA A...

                            facti.generar(miconexion, condicion,"clave.key","certificado.crt",codigoCliente, cuitCliente,tipoComprobante, montoTotal, subTotalNeto, montoIva, ptoVta, cuitVendedor, tipoVta, listadoIva, listadoTrib, nombreCliente, direccionCliente, tipoCliente, listadoDetalle,idPed,nombreEmpresa,titularEmpresa,contribuyenteEmpresa, direccionEmpresa,telefonoEmpresa,ingresosBrutos,inicioActividades);
                           
                           // TRAIGO EL NUMERO DE FACTURA QUE SE GENERO EN LA TABLA FISCAL
                            Statement consultaFiscal=conn.createStatement();
                            ResultSet resFiscal= consultaFiscal.executeQuery("select numero from fiscal where idcliente='"+fact.getText()+"'");
                            String codigoFacturaFiscal="";
                            while(resFiscal.next()){
                                 codigoFacturaFiscal=resFiscal.getString(1);
                            }
                            /**********FINAL PARTE FACTURACION ELECTRONICA**************************************/
                            
                            //INSERTO LOS DATOS EN LA TABLA FACTURA EN LA BD
                            String pago=comboPago.getSelectedItem().toString();
                            //PARA QUE SI EL SALDO FUE CANCELADO SE GUARDE COMO PAGO EN EFECTIVO EN LA FACTURA
                            if (pago.equals("SALDO CANCELADO")){
                                pago="EFECTIVO";
                            }
                            consulta.executeUpdate("insert into factura (cod_factura, fecha,cod_cliente,cod_empleado,tipo_pago,condicion,iva,tipo_factura,total_con_iva,total_sin_iva,ivaDiscriminado,estado_afip) values('"+fact.getText()+"','"+fecha+"','"+txtCodigoCliente.getText()+"','"+txtCodigoEmpleado.getText()+"','"+pago+"','ACTIVA','"+ivaFactura+"','"+tipoFacturaString+"','"+total.getText().replace(",",".")+"','"+subTotalNetoString+"','"+txtIva2.getText().replace(",",".")+"','FACTURADA')");
                            //INSERTO LOS ARTICULOS EN LA TABLA REFERENCIA FACTURA EN LA BD
                            for (x=0;x<=fil-1;x++) {
                                consulta1.executeUpdate("insert into referencia_factura (cod_factura,codigo_producto,valor_unitario,valor_total,referencia,cantidad,Total,unitarioBruto,totalBruto,descuento) values('"+fact.getText()+"','"+tabla33.getValueAt(x,1)+"'  ,'"+tabla33.getValueAt(x,3)+"','"+tabla33.getValueAt(x,6).toString().replace(",",".")+"','"+tabla33.getValueAt(x,2)+"','"+tabla33.getValueAt(x,0)+"','"+total.getText().replace(",",".")+"','"+tabla33.getValueAt(x,7)+"','"+txtTotalBruto.getText().replace(",",".")+"','"+tabla33.getValueAt(x,5)+"')");
                            }
                            
                            //SETEO E REMITO COMO FACTURADO (DESPUES SE PUEDE BORRRAR PERO NO ES CONVENIENTE HACERLO AUTOMATICAMENTE)
                            if(txtRecibeRemito.getText().equals("0")){
                                consulta.executeUpdate("UPDATE remito SET condicion='FACTURADO' where cod_remito='"+txtCodigoRemito.getText()+"'");
                            }else
                                consulta.executeUpdate("UPDATE remito SET condicion='FACTURADO' where cod_cliente='"+txtCodigoCliente.getText()+"'");
                            
                            //SI EL TOTAL DE LA FACTURA ES IGUAL AL MONTO DE TODOS LOS REMITOS, DEJO LA CUENTA CORRIENTE DEL CLIENTE EN CERO
                            Statement consulta3=conn.createStatement();
                            String saldoRest="", total_pago="";
                            ResultSet r3= consulta3.executeQuery("select total_pago,saldo_restante from cuenta_corriente WHERE cod_cliente='"+txtCodigoCliente.getText()+"'");
                
                
                            while(r3.next()){
                                saldoRest=r3.getString(2);     
                            }
                            
                            //SI EL TOTAL DE LA FACTURA ES IGUAL AL MONTO DE TODOS LOS REMITOS, DEJO LA CUENTA CORRIENTE DEL CLIENTE EN CERO
                            Statement consulta4=conn.createStatement();

                            if((saldoRest.equals("0.00"))){
                                //consulta4.executeUpdate("UPDATE cuenta_corriente SET ultimo_pago='0.00', fecha_ultimo_pago='"+fecha+"', total_pago='0.00', saldo_restante='0.00' WHERE cod_cliente='"+txtCodigoCliente.getText()+"' ");
                                consulta4.executeUpdate("UPDATE cuenta_corriente SET saldo_restante='0.00',total_pago='0.00' WHERE cod_cliente='"+txtCodigoCliente.getText()+"' ");
                            }else{
                                //SI EL SALDO RESTANTE NO ES CERO, RESTO EL SALDO PAGADO POR EL CLIENTE AL SALDO RESTANTE DE SU CUEMTA CORRIENTE
                                consulta4.executeUpdate("UPDATE cuenta_corriente SET ultimo_pago='"+Double.parseDouble(total.getText().replace(",","."))+"', saldo_restante=saldo_restante-('"+Double.parseDouble(total.getText().replace(",","."))+"') WHERE cod_cliente='"+txtCodigoCliente.getText()+"' ");
                                
                            }
                            
                            String tipoPago=comboPago.getSelectedItem().toString();
                            if(tipoPago.equals("EFECTIVO")){
                                String codigoCaja="";
                                try{
                                   Connection conn2= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                                   Statement consulta2=conn2.createStatement(); // crea una variable que se encargue del código de sql
                                                                
                                  //SELECCIONO EL CODIGO DE LA CAJA ABIERTA SELECCIONADA EN EL comboCaja PARA GUARDARLA EN EL PAGO DE EFECTIVO
                                    ResultSet  res22= consulta2.executeQuery("select cod_caja from apertura_caja WHERE estado='ABIERTA' AND nombre_caja='"+comboCaja.getSelectedItem()+"'");
                                    res22.next();
                                    codigoCaja=res22.getString(1);

                                }catch(SQLException e){
                                    System.out.println(e);
                                    JOptionPane.showMessageDialog(null,"Error en el SQL") ;
                                }
                                int xx=0;
                                xx=consulta.executeUpdate("INSERT INTO venta_efectivo (cod_venta,subtotal,iva,total,cod_caja) VALUES ('"+fact.getText()+"','"+sub.getText().replace(',','.')+"','"+valorIva.getText().replace(',','.')+"','"+total.getText().replace(',','.')+"','"+codigoCaja+"')");
                           
                                //BORRO LA FORMA DE PAGO EN EL CASO QUE EN LA MISMA FACTURA SELECCIONEN PRIMERO UNA FORMA DE PAGO Y LUGO OTRA(QUEDA REGISTRADA LA ULTIMA)
                                if(xx>0){
                                    consulta.executeUpdate("DELETE FROM venta_mixta WHERE cod_venta='"+fact.getText()+"'");
                                    consulta.executeUpdate("DELETE FROM venta_credito WHERE cod_venta='"+fact.getText()+"'");
                                    consulta.executeUpdate("DELETE FROM venta_deposito WHERE cod_venta='"+fact.getText()+"'");
                                    consulta.executeUpdate("DELETE FROM venta_cheque WHERE cod_venta='"+fact.getText()+"'"); 
                                }
                            }
                                                        
                            Seleccionar_Tamano_Factura form= new Seleccionar_Tamano_Factura ();
                            form.setVisible(true);
                            form.toFront();
                            Seleccionar_Tamano_Factura.fact.setText(fact.getText());
                            Seleccionar_Tamano_Factura.txtRecibe.setText("1");

                            inabilita();
                                                           
                           //***********************************************************************************************************************************************************************************************************************************************************************************************************
                                           

                            txtIva2.setText("");
                            total.setText("");
                            
                            /*Connection miconexion = conexion.ObtenerConexion();
                            Map parametros = new HashMap();
                            parametros.put("codf",fact.getText());
                            
                            //MUESTRO EL REPORTE
                            int op=JOptionPane.showConfirmDialog(null, "FACTURA AGREGADA ¿DESEA IMPRIMIRLA?","SELECCIONE UN OPCION" ,JOptionPane.YES_NO_OPTION);
                            if (op==JOptionPane.YES_NO_OPTION){
                                try {
                                    this.setVisible(false);
                                    String reporte="factura.jasper";
                                    JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                                    JasperViewer ventanavisor=new JasperViewer(informe,false);
                                    ventanavisor.setTitle("Reporte de factura");
                                    ventanavisor.setVisible(true);
                                }catch (Exception e) {
                                    JOptionPane.showMessageDialog(this, e.getMessage());
                                }   
                            }else{
                                this.setVisible(false);
                            }                                             
                            //VACIO LA TABLA
                            for (x=0;x<=fil-1;x++) {
                                for (y=0;y<=col-1;y++) {
                                    tabla33.setValueAt("",x,y);
                                }
                            }*/
                            //BORRO LAS FILAS DE LA TABLA
                           for (x=fil-1;x>=0;x--) {
                                DefaultTableModel modelo=(DefaultTableModel)tabla33.getModel();
                                modelo.removeRow(x);
                            }

                        }catch(SQLException e){
                            JOptionPane.showMessageDialog(null,"La factura no se pudo realizar") ;
                            System.out.println(e);
                        }
                    }               
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
            
        }
        this.dispose();
    }//GEN-LAST:event_btnGuardarFacturaActionPerformed

    private void btnCancelarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarFacturaActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarFacturaActionPerformed

    private void txtCodiRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodiRemitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodiRemitoActionPerformed

    private void botonNoActualizoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNoActualizoActionPerformed
        //ESTE ES EL BOTON DE NO ACEPTAR ACTUALIZACION DE PRECIOS QUE SE ENCUENTRA EN ELL JDIALOG ACTUALIZAR (IR A LA INTERFAZ/NAVIGATOR/AOTROS COMPONENTES/DIALOGO ACTUALIZAR)
        this.setVisible(true);
        dialogActualizar.setVisible(false);
        MostrarPreciosAntiguos();
        cargarDatos();
        tipoFactura();
    }//GEN-LAST:event_botonNoActualizoActionPerformed

    private void botnSiActualizoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botnSiActualizoActionPerformed
        //ESTE ES EL BOTON DE SI ACEPTAR ACTUALIZACION DE PRECIOS QUE SE ENCUENTRA EN ELL JDIALOG ACTUALIZAR (IR A LA INTERFAZ/NAVIGATOR/AOTROS COMPONENTES/DIALOGO ACTUALIZAR)
        this.setVisible(true);
        dialogActualizar.setVisible(false);
        MostrarPreciosNuevos();
        cargarDatos();
        tipoFactura();
    }//GEN-LAST:event_botnSiActualizoActionPerformed

    private void txtDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyReleased
                  
    }//GEN-LAST:event_txtDescuentoKeyReleased

   
    private void txtDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescuentoActionPerformed
        //ESTE ES EL TEXTFIELD QUE APLICA UN DESCUENTO GENERAL A LA FACTURA
        int Select=tabla33.getSelectedRow(), bandera=0;
        Double subtotal2=0.0,subTotalBrutoArticulo=0.0,subtotalBruto=0.0; 
        
        Double descuentoo= Double.parseDouble(txtDescuento.getText().replace(",","."));
        
        int fila2 = tabla33.getRowCount();
        subtotal=0.0;
        for (int x=0;x<=fila2-1;x++) {
           //RECORRO TODOS LOS ARTICULOS, Y APLICO EL DESCUENTO
            double porcent=(descuentoo * Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")))/100.00;
            double totalsub=(Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla33.getValueAt(x,0).toString());        
            double totalsubBrutoArt=(Double.parseDouble(tabla33.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla33.getValueAt(x,0).toString());
           
            subTotalBrutoArticulo=subTotalBrutoArticulo+totalsubBrutoArt;           
            subtotalBruto=Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla33.getValueAt(x,0).toString());
            subtotal=totalsub+subtotal;
            subtotal2 =subtotal2 +subtotalBruto;
            
            Double descuento=Double.parseDouble(tabla33.getValueAt(x,5).toString());
            tabla33.setValueAt(String.format("%.2f", descuentoo).replace(",", "."),x,5);
            tabla33.setValueAt(String.format("%.2f", subtotalBruto ).replace(",", "."),x,4);
            tabla33.setValueAt(String.format("%.2f", totalsub).replace(",", "."),x,6);
            tabla33.setValueAt(String.format("%.2f", totalsubBrutoArt).replace(",", "."),x,8);
        }

        Double ivaa=Double.parseDouble(txtIva2.getText());
        Double subBruto=subtotal;
        Double subtotalNeto= subtotal +(subtotal*ivaa)/100;
        
        //SETEO LOS NUEVOS TOTALES YA CON CON EL DESCUENTO
        sub.setText(String.format("%.2f",subBruto).replace(",", "."));
        valorIva.setText(String.format("%.2f",subtotalNeto-subBruto).replace(",", "."));
        total.setText(String.format("%.2f",subtotalNeto).replace(",", "."));
        total3.setText(""+String.format("%.2f",subtotal).replace(",", "."));
        txtTotalBruto.setText(String.format("%.2f",subTotalBrutoArticulo).replace(",", "."));
        totalOriginal= sub.getText().toString();
    }//GEN-LAST:event_txtDescuentoActionPerformed

    private void txtContribuyenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContribuyenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContribuyenteActionPerformed

    private void comboVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboVendedorActionPerformed
        //CUANDO SELECCIONO UN VENDEDOR ATRAVEZ DLE COMBOBOX
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            nomempleado = (String)comboVendedor.getSelectedItem();
            cod_empleado=null;

            String cadena=comboVendedor.getSelectedItem().toString();
            int i=0;
            //DIVIDO EL NOMBRE Y APELLIDO DEL VENDEDOR EN 2 CADENAS PORQUE EN LA BD ESTAN SEPARADOS EL NOMBRE Y APELLIDO
            while(cadena.charAt(i)!=' ') {
                i++;
            }
            String SubCadenaNombreEmpleado = cadena.substring(0,i);
            String SubCadenaApellidoEmpleado = cadena.substring(i+1,cadena.length());
            //BUSCO SU CODIGO EN LA BD
            ResultSet rs = consulta.executeQuery("SELECT cod_empleado FROM empleado WHERE (nombres = '"+SubCadenaNombreEmpleado+"' AND apellidos = '"+SubCadenaApellidoEmpleado+"' )");
            while (rs.next()) {
                cod_empleado= rs.getString(1);
            }
            txtCodigoEmpleado.setText(cod_empleado);
            conn.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }      
    }//GEN-LAST:event_comboVendedorActionPerformed

    private void btnBuscarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVendedorActionPerformed
        //LLAMO AL FORM DE VENDEDORES
        enviar_empleado form= new enviar_empleado ();
        form.setVisible(true);
        form.toFront();
        enviar_empleado.txt_recibeEmpleado.setText("9");
    }//GEN-LAST:event_btnBuscarVendedorActionPerformed

    private void btnBuscarVendedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarVendedorKeyPressed

    }//GEN-LAST:event_btnBuscarVendedorKeyPressed

    private void checkDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDescuentoActionPerformed
        //SI DESELECCIONO EL CHECK DE DESCUENTO VUELVEN LOS VALORES ORIGINALES
        if (!checkDescuento.isSelected()){
            txtDescuento.setEnabled(false);
            txtDescuento.setText("0.00");
            
            // actualizo los descuentos de todos los productos a cero
            int Select=tabla33.getSelectedRow(), bandera=0;
            Double subtotal2=0.0,subTotalBrutoArticulo=0.0,subtotalBruto=0.0; 

            Double descuentoo= 0.00;

            int fila2 = tabla33.getRowCount();
            subtotal=0.0;
            for (int x=0;x<=fila2-1;x++) {
                //SACO EL DESCUENTO Y APLICO LOS NUEVOS VALORES
                double porcent=(descuentoo * Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")))/100.00;
                double totalsub=(Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla33.getValueAt(x,0).toString());        
                double totalsubBrutoArt=(Double.parseDouble(tabla33.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla33.getValueAt(x,0).toString());

                subTotalBrutoArticulo=subTotalBrutoArticulo+totalsubBrutoArt;           
                subtotalBruto=Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla33.getValueAt(x,0).toString());
                subtotal=totalsub+subtotal;
                subtotal2 =subtotal2 +subtotalBruto;

                Double descuento=Double.parseDouble(tabla33.getValueAt(x,5).toString());
                tabla33.setValueAt(String.format("%.2f", descuentoo).replace(",", "."),x,5);
                tabla33.setValueAt(String.format("%.2f", subtotalBruto ).replace(",", "."),x,4);
                tabla33.setValueAt(String.format("%.2f", totalsub).replace(",", "."),x,6);
                tabla33.setValueAt(String.format("%.2f", totalsubBrutoArt).replace(",", "."),x,8);
            }

            Double ivaa=Double.parseDouble(txtIva2.getText());
            Double subBruto=subtotal;
            Double subtotalNeto= subtotal +(subtotal*ivaa)/100;

            sub.setText(String.format("%.2f",subBruto).replace(",", "."));

            total.setText(String.format("%.2f",subtotalNeto).replace(",", "."));
            total3.setText(""+String.format("%.2f",subtotal).replace(",", "."));
            txtTotalBruto.setText(String.format("%.2f",subTotalBrutoArticulo).replace(",", "."));
            totalOriginal= sub.getText().toString();
            }else{
                txtDescuento.setEnabled(true);
                txtDescuento.requestFocus();
            }
    }//GEN-LAST:event_checkDescuentoActionPerformed

    private void tabla33KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla33KeyReleased
        //CUANDO EDITO LA CANTIDAD O PRECIO UNITARIO DENTRO DE LA TABLA
        int Select=tabla33.getSelectedRow(), bandera=0;
        Double subtotal2=0.0,subTotalBrutoArticulo=0.0,subtotalBruto=0.0; 
        
        int fila2 = tabla33.getRowCount();
        subtotal=0.0;
        for (int x=0;x<=fila2-1;x++) {
            try{
                Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                ResultSet   res= consulta.executeQuery("select cantidad from articulo where referencia = '"+tabla33.getValueAt(Select,2).toString()+"'");
                //REVISO SI EXISTE LA CANTIDAD QUE DESEA INGRESAR
                while(res.next()){
                    cant_ex=Double.parseDouble(res.getString(1));
                    break;
                }
                res.close();
                
            }catch(SQLException e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Error en el SQL") ;
            }
            //SI NO HAY ESA CANTIDAD MUESTRO UN CARTEL
            if (cant_ex < Double.parseDouble(tabla33.getValueAt(Select,0).toString()) && bandera==0){
                JOptionPane.showMessageDialog(null, "¡ATENCION! La Cantidad Disponible de Dicho Articulo es "+cant_ex+" unidades");
                bandera=1;
                tabla33.setValueAt(cant_ex,Select,0);
            }
            
            //REALIZO NUEVAMENTE LOS CALCULOS CON LOS NUEVOS VALORES INGRESADOS
            double porcent=(Double.parseDouble(tabla33.getValueAt(x,5).toString().replace(",", "."))* Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")))/100.00;
            double totalsub=(Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla33.getValueAt(x,0).toString());        
            double totalsubBrutoArt=(Double.parseDouble(tabla33.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla33.getValueAt(x,0).toString());
           
            subTotalBrutoArticulo=subTotalBrutoArticulo+totalsubBrutoArt;           
            subtotalBruto=Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla33.getValueAt(x,0).toString());
            subtotal=totalsub+subtotal;
            subtotal2 =subtotal2 +subtotalBruto;
            
            Double descuento=Double.parseDouble(tabla33.getValueAt(x,5).toString());
            tabla33.setValueAt(String.format("%.2f", descuento).replace(",", "."),x,5);
            tabla33.setValueAt(String.format("%.2f", subtotalBruto ).replace(",", "."),x,4);
            tabla33.setValueAt(String.format("%.2f", totalsub).replace(",", "."),x,6);
            tabla33.setValueAt(String.format("%.2f", totalsubBrutoArt).replace(",", "."),x,8);
        }

        Double ivaa=Double.parseDouble(txtIva2.getText());
        Double subBruto=subtotal;
        Double subtotalNeto=0.0;
        if(comboFactura.getSelectedItem().equals("FACTURA A")){
            subtotalNeto= subtotal +(subtotal*ivaa)/100;
        }else{
            subtotalNeto= subtotal;
        }
        
        //CAMBIO LOS TOTAL Y SUBTOTAL CON LOS NUEVOS VALORES
        sub.setText(String.format("%.2f",subBruto).replace(",", "."));
        valorIva.setText(String.format("%.2f",subtotalNeto-subBruto).replace(",", "."));
        total.setText(String.format("%.2f",subtotalNeto).replace(",", "."));
        total3.setText(""+String.format("%.2f",subtotal).replace(",", "."));
        txtTotalBruto.setText(String.format("%.2f",subTotalBrutoArticulo).replace(",", "."));
        totalOriginal= sub.getText().toString();

    }//GEN-LAST:event_tabla33KeyReleased

    private void tabla33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla33MouseClicked
        //CUANDO EDITO LA CANTIDAD O PRECIO UNITARIO DENTRO DE LA TABLA
        int Select=tabla33.getSelectedRow(), bandera=0;
        Double subtotal2=0.0,subTotalBrutoArticulo=0.0,subtotalBruto=0.0; 
        
        int fila2 = tabla33.getRowCount();
        subtotal=0.0;
        for (int x=0;x<=fila2-1;x++) {
            try{
                Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                ResultSet   res= consulta.executeQuery("select cantidad from articulo where referencia = '"+tabla33.getValueAt(Select,2).toString()+"'");
                //REVISO SI EXISTE LA CANTIDAD QUE DESEA INGRESAR
                while(res.next()){
                    cant_ex=Double.parseDouble(res.getString(1));
                    break;
                }
                res.close();
                
            }catch(SQLException e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Error en el SQL") ;
            }
            //SI NO HAY ESA CANTIDAD MUESTRO UN CARTEL
            if (cant_ex < Double.parseDouble(tabla33.getValueAt(Select,0).toString()) && bandera==0){
                JOptionPane.showMessageDialog(null, "¡ATENCION! La Cantidad Disponible de Dicho Articulo es "+cant_ex+" unidades");
                bandera=1;
                tabla33.setValueAt(cant_ex,Select,0);
            }
            
            //REALIZO NUEVAMENTE LOS CALCULOS CON LOS NUEVOS VALORES INGRESADOS
            double porcent=(Double.parseDouble(tabla33.getValueAt(x,5).toString().replace(",", "."))* Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")))/100.00;
            double totalsub=(Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla33.getValueAt(x,0).toString());        
            double totalsubBrutoArt=(Double.parseDouble(tabla33.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla33.getValueAt(x,0).toString());
           
            subTotalBrutoArticulo=subTotalBrutoArticulo+totalsubBrutoArt;           
            subtotalBruto=Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla33.getValueAt(x,0).toString());
            subtotal=totalsub+subtotal;
            subtotal2 =subtotal2 +subtotalBruto;
            
            Double descuento=Double.parseDouble(tabla33.getValueAt(x,5).toString());
            tabla33.setValueAt(String.format("%.2f", descuento).replace(",", "."),x,5);
            tabla33.setValueAt(String.format("%.2f", subtotalBruto ).replace(",", "."),x,4);
            tabla33.setValueAt(String.format("%.2f", totalsub).replace(",", "."),x,6);
            tabla33.setValueAt(String.format("%.2f", totalsubBrutoArt).replace(",", "."),x,8);
        }

        Double ivaa=Double.parseDouble(txtIva2.getText());
        Double subBruto=subtotal;
        Double subtotalNeto=0.0;
        if(comboFactura.getSelectedItem().equals("FACTURA A")){
            subtotalNeto= subtotal +(subtotal*ivaa)/100;
        }else{
            subtotalNeto= subtotal;
        }
        
        //CAMBIO LOS TOTAL Y SUBTOTAL CON LOS NUEVOS VALORES
        sub.setText(String.format("%.2f",subBruto).replace(",", "."));
        valorIva.setText(String.format("%.2f",subtotalNeto-subBruto).replace(",", "."));
        total.setText(String.format("%.2f",subtotalNeto).replace(",", "."));
        total3.setText(""+String.format("%.2f",subtotal).replace(",", "."));
        txtTotalBruto.setText(String.format("%.2f",subTotalBrutoArticulo).replace(",", "."));
        totalOriginal= sub.getText().toString();
    }//GEN-LAST:event_tabla33MouseClicked

    private void checkIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkIvaActionPerformed
        //ESTE CHECK ESTA OCULTO PERO IGUAL REALIZA LOS CALCULOS (NO BORRAR)
        if (!checkIva.isSelected()){
            txtIva.setEnabled(false);
            txtIva.setText("0.00");
            txtIva2.setText("0.00");
            total.setText(totalOriginal);
        }else{
            txtIva.setEnabled(true);
            txtIva.setText("19.00");
            txtIva2.setText("19.00");
            Double total=0.0;
            total= Double.parseDouble(totalOriginal.replace(",", ".")) +(Double.parseDouble(totalOriginal.replace(",", "."))* Double.parseDouble(txtIva2.getText()))/100;
            Frm_facturarRemito.total.setText(String.format("%.2f",total).replace(",", "."));
            txtIva.requestFocus();
        }
    }//GEN-LAST:event_checkIvaActionPerformed

    private void txtIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIvaActionPerformed

    private void txtIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaKeyReleased
        //ESTE CHECK ESTA OCULTO PERO IGUAL REALIZA LOS CALCULOS (NO BORRAR)
        if(txtIva.getText().equals("")){           
            total.setText(totalOriginal);
            txtIva2.setText("0.00");
        }else{
             Double ivaa=Double.parseDouble(txtIva.getText().replace(",","."));
             String original= totalOriginal;
             String originalConPunto= original.replace(",",".");//---remplazo la coma por un punto para que no me tire error de formato
             Double total= Double.parseDouble(originalConPunto) + (ivaa  * Double.parseDouble(originalConPunto) / 100);
             String totall=total.toString();
             Double ivaaa=Double.parseDouble(txtIva.getText().replace(",","."));
             txtIva2.setText(String.format("%.2f",ivaaa).replace(",","."));
             Frm_facturarRemito.total.setText(String.format("%.2f",total).replace(",","."));
        } 
    }//GEN-LAST:event_txtIvaKeyReleased

    private void comboFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFacturaActionPerformed
        double variableIva=0.0; 
        String ivaFact="";
        
        //SEGUN EL TIPO DE FACTURA SELECCIONADA SETEO LAS VARIABLES PARA REALIZAR LOS CALCULOS
        
        if(comboFactura.getSelectedItem().equals("FACTURA A")){//-------SI ES FACTURA A TENGO QUE INGRESAR LOS PRECIOS SIN IVA Y DISCRIMINAR EL IVA AL FINAL
            if(comboIva.getSelectedItem().toString().equals("19%")){
                variableIva=1.19;
                ivaFact="19.00";
            }else if(comboIva.getSelectedItem().toString().equals("0%")){
                       variableIva=1.00;
                       ivaFact="0.00";
                   }
            
            int Select=tabla33.getSelectedRow(), bandera=0;
            Double subtotal2=0.0,subTotalBrutoArticulo=0.0,subtotalBruto=0.0; 
            
            String desc=txtDescuento.getText().replace(",",".");
            if (desc.equals("")){
                desc="0.00";
            }
            Double descuentoo= Double.parseDouble(desc);

            int fila2 = tabla33.getRowCount();
            subtotal=0.0;
            for (int x=0;x<=fila2-1;x++) {
                double valorSinIva= Double.parseDouble(tabla33.getValueAt(x,10).toString().replace(",", "."));
                tabla33.setValueAt(String.format("%.2f", valorSinIva).replace(",", "."),x,3);
                
                double porcent=(descuentoo * Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")))/100.00;
                double totalsub=(Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla33.getValueAt(x,0).toString());        
                double totalsubBrutoArt=(Double.parseDouble(tabla33.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla33.getValueAt(x,0).toString());

                subTotalBrutoArticulo=subTotalBrutoArticulo+totalsubBrutoArt;           
                subtotalBruto=Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla33.getValueAt(x,0).toString());
                subtotal=totalsub+subtotal;
                subtotal2 =subtotal2 +subtotalBruto;

                Double descuento=Double.parseDouble(tabla33.getValueAt(x,5).toString());
                tabla33.setValueAt(String.format("%.2f", descuentoo).replace(",", "."),x,5);
                tabla33.setValueAt(String.format("%.2f", subtotalBruto ).replace(",", "."),x,4);
                tabla33.setValueAt(String.format("%.2f", totalsub).replace(",", "."),x,6);
                tabla33.setValueAt(String.format("%.2f", totalsubBrutoArt).replace(",", "."),x,8);
            }

            Double ivaa=Double.parseDouble(txtIva2.getText());
            Double subBruto=subtotal;
            Double subtotalNeto= subtotal +(subtotal*ivaa)/100;

            sub.setText(String.format("%.2f",subBruto).replace(",", "."));

            total.setText(String.format("%.2f",subtotalNeto).replace(",", "."));
            total3.setText(""+String.format("%.2f",subtotal).replace(",", "."));
            txtTotalBruto.setText(String.format("%.2f",subTotalBrutoArticulo).replace(",", "."));
            totalOriginal= sub.getText().toString();
            
                     
            total.setText(totalOriginal);
        
            Double ivaa2=Double.parseDouble(ivaFact);
            String original= totalOriginal;
            String originalConPunto= original.replace(",",".");//---remplazo la coma por un punto para que no me tire error de formato
            Double total= Double.parseDouble(originalConPunto) + (ivaa2  * Double.parseDouble(originalConPunto) / 100);
            String totall=total.toString();
            txtIva2.setText(ivaFact);
            Frm_facturarRemito.total.setText(String.format("%.2f",total).replace(",","."));
            
            
        }else if(comboFactura.getSelectedItem().equals("FACTURA B")){//-------SI ES FACTURA B TENGO QUE INGRESAR LOS PRECIOS CON EL IVA AGREGADO Y NO LO DISCRIMINO EN EL TOTAL Y SUBTOTAL
            
                    if(comboIva.getSelectedItem().toString().equals("19%")){
                        variableIva=1.19;
                        ivaFact="19.00";
                    }else if(comboIva.getSelectedItem().toString().equals("0%")){
                               variableIva=1.00;
                               ivaFact="0.00";
                           }

                    int Select=tabla33.getSelectedRow(), bandera=0;
                    Double subtotal2=0.0,subTotalBrutoArticulo=0.0,subtotalBruto=0.0; 

                    String desc=txtDescuento.getText().replace(",",".");
                    if (desc.equals("")){
                        desc="0.00";
                    }
                    Double descuentoo= Double.parseDouble(desc);

                    int fila2 = tabla33.getRowCount();
                    subtotal=0.0;
                    for (int x=0;x<=fila2-1;x++) {
                        double valorSinIva= Double.parseDouble(tabla33.getValueAt(x,9).toString().replace(",", "."));
                        tabla33.setValueAt(String.format("%.2f", valorSinIva).replace(",", "."),x,3);
                        
                        double porcent=(descuentoo * Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")))/100.00;
                        double totalsub=(Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla33.getValueAt(x,0).toString());        
                        double totalsubBrutoArt=(Double.parseDouble(tabla33.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla33.getValueAt(x,0).toString());

                        subTotalBrutoArticulo=subTotalBrutoArticulo+totalsubBrutoArt;           
                        subtotalBruto=Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla33.getValueAt(x,0).toString());
                        subtotal=totalsub+subtotal;
                        subtotal2 =subtotal2 +subtotalBruto;

                        Double descuento=Double.parseDouble(tabla33.getValueAt(x,5).toString());
                        tabla33.setValueAt(String.format("%.2f", descuentoo).replace(",", "."),x,5);
                        tabla33.setValueAt(String.format("%.2f", subtotalBruto ).replace(",", "."),x,4);
                        tabla33.setValueAt(String.format("%.2f", totalsub).replace(",", "."),x,6);
                        tabla33.setValueAt(String.format("%.2f", totalsubBrutoArt).replace(",", "."),x,8);
                    }

                    Double ivaa=Double.parseDouble(txtIva2.getText());
                    Double subBruto=subtotal;
                    Double subtotalNeto= subtotal +(subtotal*ivaa)/100;

                    sub.setText(String.format("%.2f",subBruto).replace(",", "."));

                    total.setText(String.format("%.2f",subtotalNeto).replace(",", "."));
                    total3.setText(""+String.format("%.2f",subtotal).replace(",", "."));
                    txtTotalBruto.setText(String.format("%.2f",subTotalBrutoArticulo).replace(",", "."));
                    totalOriginal= sub.getText().toString();


                    total.setText(totalOriginal);

                    Double ivaa2=0.00;
                    String original= totalOriginal;
                    String originalConPunto= original.replace(",",".");//---remplazo la coma por un punto para que no me tire error de formato
                    Double total= Double.parseDouble(originalConPunto) + (ivaa2  * Double.parseDouble(originalConPunto) / 100);
                    String totall=total.toString();
                    txtIva2.setText("0.00");
                    Frm_facturarRemito.total.setText(String.format("%.2f",total).replace(",","."));
            
        }
        
    }//GEN-LAST:event_comboFacturaActionPerformed

    private void comboIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIvaActionPerformed
        double variableIva=0.0; 
        String ivaFact="";
        
        //SEGUN EL VALOR QUE SELECCIONO EN EL COMBO DEL IVA TENGO QUE CAMBIAR EL VALOR DE LOS PRODUCTOS  SI ES FACTURA A
        
        if(comboFactura.getSelectedItem().equals("FACTURA A")){
            if(comboIva.getSelectedItem().toString().equals("19%")){
                variableIva=1.19;
                ivaFact="19.00";
            }else if(comboIva.getSelectedItem().toString().equals("0%")){
                        variableIva=1.00;
                        ivaFact="0.00";
                   }
            
            String desc=txtDescuento.getText().replace(",",".");
                    if (desc.equals("")){
                        desc="0.00";
                    }
                    Double descuentoo= Double.parseDouble(desc);

                    int fila2 = tabla33.getRowCount();
                    subtotal=0.0;
                    double subTotalBrutoArticulo=0.0, subtotalBruto=0.0, subtotal2=0.0;
                    
                    for (int x=0;x<=fila2-1;x++) {
                        //REALIZO LOS CALCULOS APLICANDO LAS VARIABLES DE IVA INGRESADO
                        double valorSinIva= Double.parseDouble(tabla33.getValueAt(x,9).toString().replace(",", "."));
                        tabla33.setValueAt(String.format("%.2f", valorSinIva).replace(",", "."),x,3);
                        
                        double porcent=(descuentoo * Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")))/100.00;
                        double totalsub=(Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla33.getValueAt(x,0).toString());        
                        double totalsubBrutoArt=(Double.parseDouble(tabla33.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla33.getValueAt(x,0).toString());

                        subTotalBrutoArticulo=subTotalBrutoArticulo+totalsubBrutoArt;           
                        subtotalBruto=Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla33.getValueAt(x,0).toString());
                        subtotal=totalsub+subtotal;
                        subtotal2 =subtotal2 +subtotalBruto;

                        Double descuento=Double.parseDouble(tabla33.getValueAt(x,5).toString());
                        tabla33.setValueAt(String.format("%.2f", descuentoo).replace(",", "."),x,5);
                        tabla33.setValueAt(String.format("%.2f", subtotalBruto ).replace(",", "."),x,4);
                        tabla33.setValueAt(String.format("%.2f", totalsub).replace(",", "."),x,6);
                        tabla33.setValueAt(String.format("%.2f", totalsubBrutoArt).replace(",", "."),x,8);
                    }
                    
            Double subTotal= Double.parseDouble(sub.getText());
            Double Total= subTotal* variableIva;
            total.setText(String.format("%.2f",Total).replace(",", "."));
            total3.setText(""+String.format("%.2f",Total).replace(",", "."));
            txtTotalBruto.setText(String.format("%.2f",subTotalBrutoArticulo).replace(",", "."));
            totalOriginal= sub.getText().toString();
            
            txtIva2.setText(ivaFact);
            txtIva.setText(ivaFact);
            
            valorIva.setText(String.format("%.2f",Total-subTotal).replace(",", "."))  ;       

            
            
        }else if(comboFactura.getSelectedItem().equals("FACTURA B")){ //----SI ES FACTURA B NO DISCRIMINO EL IVA EN EL VALOR DEL PRODUCTO
            
                    if(comboIva.getSelectedItem().toString().equals("19%")){
                        variableIva=1.19;
                        ivaFact="19.00";
                    }else if(comboIva.getSelectedItem().toString().equals("0%")){
                                variableIva=1.00;
                                ivaFact="0.00";
                           }

                    int Select=tabla33.getSelectedRow(), bandera=0;
                    Double subtotal2=0.0,subTotalBrutoArticulo=0.0,subtotalBruto=0.0; 

                    String desc=txtDescuento.getText().replace(",",".");
                    if (desc.equals("")){
                        desc="0.00";
                    }
                    Double descuentoo= Double.parseDouble(desc);

                    int fila2 = tabla33.getRowCount();
                    subtotal=0.0;
                    for (int x=0;x<=fila2-1;x++) {
                        double valorSinIva= Double.parseDouble(tabla33.getValueAt(x,9).toString().replace(",", "."));
                        tabla33.setValueAt(String.format("%.2f", valorSinIva).replace(",", "."),x,3);
                        
                        double porcent=(descuentoo * Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")))/100.00;
                        double totalsub=(Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla33.getValueAt(x,0).toString());        
                        double totalsubBrutoArt=(Double.parseDouble(tabla33.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla33.getValueAt(x,0).toString());

                        subTotalBrutoArticulo=subTotalBrutoArticulo+totalsubBrutoArt;           
                        subtotalBruto=Double.parseDouble(tabla33.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla33.getValueAt(x,0).toString());
                        subtotal=totalsub+subtotal;
                        subtotal2 =subtotal2 +subtotalBruto;

                        Double descuento=Double.parseDouble(tabla33.getValueAt(x,5).toString());
                        tabla33.setValueAt(String.format("%.2f", descuentoo).replace(",", "."),x,5);
                        tabla33.setValueAt(String.format("%.2f", subtotalBruto ).replace(",", "."),x,4);
                        tabla33.setValueAt(String.format("%.2f", totalsub).replace(",", "."),x,6);
                        tabla33.setValueAt(String.format("%.2f", totalsubBrutoArt).replace(",", "."),x,8);
                    }

                    Double ivaa=Double.parseDouble(txtIva2.getText());
                    Double subBruto=subtotal;
                    Double subtotalNeto= subtotal +(subtotal*ivaa)/100;

                    sub.setText(String.format("%.2f",subBruto).replace(",", "."));

                    total.setText(String.format("%.2f",subtotalNeto).replace(",", "."));
                    total3.setText(""+String.format("%.2f",subtotal).replace(",", "."));
                    txtTotalBruto.setText(String.format("%.2f",subTotalBrutoArticulo).replace(",", "."));
                    totalOriginal= sub.getText().toString();

                    valorIva.setText(String.format("%.2f",subBruto-subBruto).replace(",", "."));
                    total.setText(totalOriginal);

                    Double ivaa2=0.00;
                    String original= totalOriginal;
                    String originalConPunto= original.replace(",",".");//---remplazo la coma por un punto para que no me tire error de formato
                    Double total= Double.parseDouble(originalConPunto) + (ivaa2  * Double.parseDouble(originalConPunto) / 100);
                    String totall=total.toString();
                    txtIva2.setText("0.00");
                    Frm_facturarRemito.total.setText(String.format("%.2f",total).replace(",","."));
            
        }
    }//GEN-LAST:event_comboIvaActionPerformed

    private void itemBuscarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarVendedorActionPerformed
        //ESTE ITEM ES PARA EL ACCESO RAPIDOS F1
        enviar_empleado form= new enviar_empleado ();
        form.setVisible(true);
        form.toFront();
        enviar_empleado.txt_recibeEmpleado.setText("9");
    }//GEN-LAST:event_itemBuscarVendedorActionPerformed

    private void comboTipoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTipoVentaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //SON LOS ITEM QUE HACEN FUNCIONAR LOS ACCESOS RAPIDOS F3 F5 F7 ETC
        Configurar_Impresion form= new Configurar_Impresion ();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void comboCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCajaActionPerformed
     
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
            java.util.logging.Logger.getLogger(Frm_facturarRemito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_facturarRemito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_facturarRemito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_facturarRemito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_facturarRemito().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botnSiActualizo;
    private javax.swing.JButton botonNoActualizo;
    private javax.swing.JButton btnBuscarVendedor;
    private javax.swing.JButton btnCancelarFactura;
    private javax.swing.JButton btnGuardarFactura;
    private com.toedter.calendar.JDateChooser calendario;
    private javax.swing.JCheckBox checkDescuento;
    private javax.swing.JCheckBox checkIva;
    public static javax.swing.JComboBox comboCaja;
    public static javax.swing.JComboBox comboFactura;
    public static javax.swing.JComboBox comboIva;
    public static javax.swing.JComboBox comboPago;
    public static javax.swing.JComboBox comboTipoVenta;
    public static javax.swing.JComboBox comboVendedor;
    private javax.swing.JDialog dialogActualizar;
    private javax.swing.JTextField fact;
    private javax.swing.JMenuItem itemBuscarVendedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    public static javax.swing.JLabel labelCodigo;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    public static javax.swing.JTextField sub;
    private javax.swing.JTable tabla33;
    public static javax.swing.JTextField total;
    private javax.swing.JTextField total2;
    private javax.swing.JTextField total3;
    public static javax.swing.JTextField txtCliente;
    public static javax.swing.JTextField txtCodiRemito;
    public static javax.swing.JTextField txtCodigoCliente;
    public static javax.swing.JTextField txtCodigoEmpleado;
    public static javax.swing.JTextField txtCodigoRemito;
    private javax.swing.JTextField txtContribuyente;
    public static javax.swing.JTextField txtCuit;
    private javax.swing.JTextField txtDescuento;
    public static javax.swing.JTextField txtDomicilio;
    private javax.swing.JLabel txtFacturaNumero;
    private javax.swing.JTextField txtIva;
    public static javax.swing.JTextField txtIva2;
    public static javax.swing.JTextField txtRecibeRemito;
    private javax.swing.JTextField txtTotalBruto;
    public static javax.swing.JTextField valorIva;
    // End of variables declaration//GEN-END:variables
}
