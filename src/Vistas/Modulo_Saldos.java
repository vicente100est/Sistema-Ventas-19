
package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class Modulo_Saldos extends javax.swing.JFrame {
    int Select;
    String codigo,nombre,apellido,fecha,empleado,id_referencia_cuenta;
    boolean banderaFacturado=false;
    String NOMBRE, signo_moneda;
    Double sumaRemitos=0.0, sumaActualRemitos=0.0; //suma de los remitos con el precio que esta en el remito y suma con el precio actual de los articulos (para sacar el valor de la actualizacion)

    public Modulo_Saldos() {
        initComponents();
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
         }catch(Exception e){
            
        }  
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            ResultSet r2= consulta.executeQuery("select signo_moneda from  monedas where seleccion_moneda like 'SELECCIONADA'");
            while(r2.next()){
                signo_moneda=r2.getString(1);
            }
        }catch(Exception e){
            
        }
        
                
        setLocationRelativeTo(null);
        botonTodosLosRemitos.setVisible(false);
        txtCodigoCliente1.setVisible(false);
        txtBanderaRemito.setVisible(false);
        panelSaldo.setVisible(false);
        
        panelPagos.setVisible(false);
        btnOcultarPagos.setVisible(false);
        btnFacturarRemito2.setVisible(false);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        iniciar();
        JTableHeader th; 
        th = tabla.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        JTableHeader thh; 
        thh = tabla2.getTableHeader(); 
        Font fuente2 = new Font("Calibri", Font.BOLD, 20); 
        thh.setFont(fuente2); 
        thh.setBackground(new Color(93,116,163));
        thh.setForeground(new Color(255,255,255));
        
        JTableHeader thhh; 
        thhh = tablaPagos.getTableHeader(); 
        Font fuente22 = new Font("Calibri", Font.BOLD, 20); 
        thhh.setFont(fuente22); 
        thhh.setBackground(new Color(93,116,163));
        thhh.setForeground(new Color(255,255,255));
        
    }    
    
    public void cargarComboCliente(){
        Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
        try{
            Statement consulta=conn.createStatement();
            Statement consulta2=conn.createStatement();
            ResultSet r= consulta.executeQuery("select c.nombres from cliente c, remito r where c.cod_cliente=r.cod_cliente AND c.cod_cliente>0 AND r.cod_remito>0 AND r.cod_cliente>0 group by r.cod_cliente");
            
            comboCliente.removeAllItems();
            comboCliente.addItem("SELECCIONE CLIENTE");
            
            int i=0;

            while(r.next()){
                comboCliente.addItem(r.getString(1));
                i++;
            }
            comboCliente.setSelectedIndex(0);
            
            ResultSet r2= consulta2.executeQuery("select nombre_caja from cajas where estado='ABIERTA' order by cod_caja");
            
            comboCaja.addItem("CAJA CERRADA");
            int i2=0;
            while(r2.next()){
                if(i2==0){
                    comboCaja.removeItem("CAJA CERRADA");
                    i2++;
                }
                comboCaja.addItem(r2.getString(1)); //NOMBRE DELA CAJA

            }
            r2.close();
        
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void cargarRemitoNoFacturado(String codCliente){
        sumaRemitos=0.0;
        sumaActualRemitos=0.0;
        TableColumn  column = null;    
        String codigoRemito="";
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta.executeQuery("select * from remito where condicion='NO FACTURADO' AND cod_remito>0 AND cod_cliente='"+codCliente+"' order by cod_remito");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            
            
            tabla.setRowSorter(new TableRowSorter(modelo));
            tabla.getColumnModel().getColumn(4).setMaxWidth(0);
            tabla.getColumnModel().getColumn(4).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
            
            tabla.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla.getColumnModel().getColumn(1).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
                           
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
            
            modelo.setNumRows(0);
            while(r.next()){
                String codigoCliente=r.getString(3),
                codigoEmpleado=r.getString(4);
                                
                modelo.addRow( new Object [] {null,null,null,null,null,null,null});
                tabla.setValueAt(r.getString(1),i,0); //codigo remito
                codigoRemito=r.getString(1);

                Statement consulta2=conn.createStatement();
                ResultSet r2= consulta2.executeQuery("select * from cliente order by cod_cliente");
                String nombreCliente="N° "+codigoCliente,apellidoCliente="";
                while(r2.next()){
                    if((r2.getString("cod_cliente")).equals(r.getString("cod_cliente"))){
                        nombreCliente=r2.getString(2);
                        apellidoCliente=r2.getString(3);      
                    }
                }
                tabla.setValueAt(nombreCliente,i,1);
                tabla.setValueAt(r.getString(2),i,2);

                Statement consulta3=conn.createStatement();
                ResultSet r3= consulta3.executeQuery("select * from empleado order by cod_empleado");
                String nombreEmpleado="N° "+codigoEmpleado,apellidoEmpleado="";
                while(r3.next()){
                    if(r3.getString(1).equals(codigoEmpleado)){
                        nombreEmpleado=r3.getString(2);
                        apellidoEmpleado=r3.getString(3);         
                    }
                }
                tabla.setValueAt(nombreEmpleado+" "+apellidoEmpleado,i,3);
                tabla.setValueAt(codigoCliente,i,4);
                tabla.setValueAt(codigoEmpleado,i,5);
                
                Statement consulta4=conn.createStatement();
                ResultSet r4= consulta4.executeQuery("select total, cod_articulo, cantidad from referencia_remito WHERE cod_remito='"+codigoRemito+"'");
                String valorRemito="", codArticulo="", cantidadArticulo="",precioActualArticulo="";
                Double valorActualRemito=0.0;
                while(r4.next()){ //SELECCIONO EL TOTAL DEL REMITO, EL CODIGO DE CADA ART YLA CANTIDAD
                    valorRemito=r4.getString(1);
                    codArticulo=r4.getString(2);
                    cantidadArticulo=r4.getString(3);
                    
                    Statement consulta5=conn.createStatement();
                    ResultSet r5= consulta5.executeQuery("select total_con_iva from articulo WHERE cod_articulo='"+codArticulo+"'");

                    while(r5.next()){ //SELECCIONO EL PRECIO ACTUAL DEL ARTICULO
                        precioActualArticulo=r5.getString(1);
                    }
                    valorActualRemito= valorActualRemito + Double.parseDouble(cantidadArticulo)*Double.parseDouble(precioActualArticulo);
                }
                tabla.setValueAt(valorRemito,i,6);
                
                sumaRemitos=sumaRemitos+Double.parseDouble(valorRemito);
                
                tabla.setValueAt(String.format("%.2f", valorActualRemito).replace(",", "."),i,7);
                sumaActualRemitos=sumaActualRemitos+valorActualRemito;
                
                i++;
                
                
            }
            Statement consulta5=conn.createStatement();
            
            ResultSet r5= consulta5.executeQuery("select * from cuenta_corriente WHERE cod_cliente='"+codCliente+"'");
            String ultimoPago="",pagoTotal="", fechaPago="", codigoCuenta="";
            while(r5.next()){
                codigoCuenta=r5.getString(1);
                ultimoPago=r5.getString(3);
                fechaPago=r5.getString(4);
                pagoTotal=r5.getString(5);
            }
            txtNroCuentaCorriente.setText(codigoCuenta);
            txtUltimoPago.setText(ultimoPago);
            txtFechaPago.setText(fechaPago);
            txtPagoRealizado.setText(pagoTotal);
            
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ;
            System.out.println(e);
        }
    }
    
    public void realizarPago(){
        Calendar fecha = new GregorianCalendar();
        String ultimoPago="";
        //Obtenemos el valor del año, mes, día, hora, minuto y segundo del sistema.
        //Usando el método get y el parámetro correspondiente.
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        if((mes+1)<10 && (dia>=10)){
            ultimoPago = (año+"-0"+(mes+1)+"-"+dia); 
        }else{
            if(((mes+1)<10 && (dia<10))){
                ultimoPago =(año+"-0"+(mes+1)+"-0"+dia);
            }else{
                 ultimoPago= (año+"-"+(mes+1)+"-"+dia);
            }
        }
        if(txtRealizarPago.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Debe ingresar el monto a abonar");
            txtRealizarPago.requestFocus();
        }else{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            try{
                Double pago= Double.parseDouble(txtRealizarPago.getText());
                Double saldoRestante=Double.parseDouble(txtSaldoTotal.getText());
                Double totalAbonado=Double.parseDouble(txtPagoRealizado.getText());
                totalAbonado=totalAbonado+pago;
                Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                Statement consulta2=conn.createStatement();
                Statement consulta3=conn.createStatement();
                Statement consulta4=conn.createStatement();
                Statement consulta5=conn.createStatement();
                
                consulta.executeUpdate("UPDATE cuenta_corriente SET ultimo_pago='"+pago+"', fecha_ultimo_pago='"+ultimoPago+"', total_pago= total_pago+'"+pago+"', saldo_restante='"+(saldoRestante-pago)+"' WHERE cod_cuenta='"+txtNroCuentaCorriente.getText()+"' ");
                
                ResultSet r5= consulta5.executeQuery("select total_pago from cuenta_corriente WHERE cod_cuenta='"+txtNroCuentaCorriente.getText()+"'");
                String pagoAcumulado="";
                while(r5.next()){
                    pagoAcumulado=r5.getString(1);
                }
                
                String codigoCaja="0";
                try{
                    Connection conn2= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                    consulta4=conn2.createStatement(); // crea una variable que se encargue del código de sql
                                                                
                    //SELECCIONO EL CODIGO DE LA CAJA ABIERTA SELECCIONADA EN EL comboCaja PARA GUARDARLA EN EL PAGO DE EFECTIVO
                    ResultSet  res22= consulta2.executeQuery("select cod_caja from apertura_caja WHERE estado='ABIERTA' AND nombre_caja='"+comboCaja.getSelectedItem()+"'");
                    res22.next();
                    while(res22.next()){
                        codigoCaja=res22.getString(1);
                    }
                    

                }catch(SQLException e){
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Error en el SQL") ;
               }
                
                consulta2.executeUpdate("INSERT INTO referencia_cuenta_corriente (cod_cuenta,fecha,pago_abonado,total_pago,saldo_restante,total_remitos,actualizacion_precios,cod_caja)VALUES('"+txtNroCuentaCorriente.getText()+"','"+ultimoPago+"','"+pago+"','"+pagoAcumulado+"','"+(saldoRestante-pago)+"','"+sumaRemitos+"','"+txtActualizacionPrecios.getText()+"','"+codigoCaja+"')");
                
                id_referencia_cuenta="";
                String saldoRest="";
                ResultSet r4= consulta3.executeQuery("select MAX(id)from referencia_cuenta_corriente");
                while(r4.next()){
                    id_referencia_cuenta=r4.getString(1);
                }
                
                ResultSet r3= consulta3.executeQuery("select saldo_restante from referencia_cuenta_corriente WHERE id='"+id_referencia_cuenta+"'");
                
                
                while(r3.next()){
                    saldoRest=r3.getString(1);
                }
                /*Connection miconexion = conexion.ObtenerConexion();
                Map parametros = new HashMap();
                parametros.put("codf",id_referencia_cuenta);
                parametros.put("codCliente",txtCodigoCliente1.getText());

     
                int op=JOptionPane.showConfirmDialog(null, "PAGO REALIZADO SATISFACTORIAMENTE ¿DESEA IMPRIMIR EL COMPROBANTE?","SELECCIONE UN OPCION" ,JOptionPane.YES_NO_OPTION);
                if (op==JOptionPane.YES_NO_OPTION){
   
                    try {
                        String reporte="comprobantePago.jasper";
                        JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                        JasperViewer ventanavisor=new JasperViewer(informe,false);
                        ventanavisor.setTitle("Comprobante de pago");
                        ventanavisor.setVisible(true);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    } 
                }else{
                                        
                }*/
                
                btnRealizarPago.setVisible(true);
                panelSaldo.setVisible(false);
                btnVerPagos.setVisible(true);
                btnOcultarPagos.setVisible(true);
            }catch(Exception e){
                   System.out.println(e);
            }
            
        }
        
    }
    
    public void cargarTablaPagos(){
        TableColumn  column = null; 
        int i,j;
        i=0;
        j=0;
        DefaultTableModel modelo=(DefaultTableModel)tablaPagos.getModel();
        tablaPagos.setRowSorter(new TableRowSorter(modelo));
        
        tablaPagos.getColumnModel().getColumn(0).setMaxWidth(90);
        tablaPagos.getColumnModel().getColumn(0).setMinWidth(90);
        tablaPagos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(90);
        tablaPagos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(90);
        
        tablaPagos.getColumnModel().getColumn(1).setMaxWidth(110);
        tablaPagos.getColumnModel().getColumn(1).setMinWidth(110);
        tablaPagos.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(110);
        tablaPagos.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(110);
        
        tablaPagos.getColumnModel().getColumn(2).setMaxWidth(90);
        tablaPagos.getColumnModel().getColumn(2).setMinWidth(90);
        tablaPagos.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(90);
        tablaPagos.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(90);

        Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
        
        try{
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet r= consulta.executeQuery("SELECT * from referencia_cuenta_corriente WHERE cod_cuenta='"+txtNroCuentaCorriente.getText()+"'");
            
            while(r.next()){
                                
                modelo.addRow( new Object [] {null,null,null,null});
                tablaPagos.setValueAt(r.getString(1),i,0);//id referencia_cuenta_corriente
                tablaPagos.setValueAt(r.getString(3),i,1);//fecha
                tablaPagos.setValueAt(r.getString(7),i,2);//MONTO TOAL DE LOS REMITOS
                tablaPagos.setValueAt(r.getString(4),i,3);//monto abonado
                tablaPagos.setValueAt(r.getString(6),i,4);//monto restante
                i++;
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error enla base de datos") ;
            System.out.println(e);
        }
    }
    
    public void cargarRemitoFacturado(){
        TableColumn  column = null;     
         try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta.executeQuery("select * from remito where condicion='FACTURADO' AND cod_remito>0 order by cod_remito");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            tabla.setRowSorter(new TableRowSorter(modelo));

            tabla.getColumnModel().getColumn(4).setMaxWidth(0);
            tabla.getColumnModel().getColumn(4).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
                            
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
            
            modelo.setNumRows(0);
            while(r.next()){
                String codigoCliente=r.getString(3),
                codigoEmpleado=r.getString(4);
                                
                modelo.addRow( new Object [] {null,null,null,null,null,null,null});
                tabla.setValueAt(r.getString(1),i,0);
                Statement consulta2=conn.createStatement();
                ResultSet r2= consulta2.executeQuery("select * from cliente order by cod_cliente");
                String nombreCliente="N° "+codigoCliente,apellidoCliente="";
                while(r2.next()){
                    if((r2.getString("cod_cliente")).equals(r.getString("cod_cliente"))){
                        nombreCliente=r2.getString(2);
                        apellidoCliente=r2.getString(3);                       
                    }
                }
                tabla.setValueAt(nombreCliente,i,1);
                tabla.setValueAt(r.getString(2),i,2);
                                
                Statement consulta3=conn.createStatement();
                ResultSet r3= consulta3.executeQuery("select * from empleado order by cod_empleado");
                String nombreEmpleado="N° "+codigoEmpleado,apellidoEmpleado="";
                while(r3.next()){
                    if(r3.getString(1).equals(codigoEmpleado)){
                        nombreEmpleado=r3.getString(2);
                        apellidoEmpleado=r3.getString(3);      
                    }
                }
                tabla.setValueAt(nombreEmpleado+" "+apellidoEmpleado,i,3);
                tabla.setValueAt(codigoCliente,i,4);
                tabla.setValueAt(codigoEmpleado,i,5);
                i++;
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error enla base de datos") ;
            System.out.println(e);
        }
    }
    
    public void eliminarRemitoFacturado(){     
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                   
            consulta.executeUpdate("delete from referencia_remito where cod_remito="+txtNumeroRemito.getText());
            consulta.executeUpdate("delete from remito where cod_remito="+txtNumeroRemito.getText());
            JOptionPane.showMessageDialog(null,"El remito fue eliminado del sistema") ;    
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        cargarRemitoFacturado();
    }
    
    public void iniciar(){
        panelVerPagos.setVisible(false);
        panelRemito.setVisible(false);
        checkRemito.setSelected(true);
        txtBuscar.setText("Ingrese su búsqueda");
        txtBuscar.setForeground(Color.gray);

        txtCodigoEmpleado1.setVisible(false);
        txtNombreApellidoEmpleado.setVisible(false);
        txtNombreApellidoCliente.setVisible(true);
        txtTotalRemito.setVisible(false);
        
        txtNumeroRemito.setEditable(false);
        txtFechaRemito.setEditable(false);
        txtTotalRemito2.setEditable(false);
    
        txtPagoRealizado.setText("0.00");
        txtNroCuentaCorriente.setVisible(false);
        
        cargarComboCliente();
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta.executeQuery("select actualizacionPrecios from porcentajes");
            String opcion="";
            while(r.next()){
                opcion=r.getString(1);
            }
            if(opcion.equals("ACTIVO")){
                checkActualizacion.setSelected(true);
            }else{
                checkActualizacion.setSelected(false);
            }
        }catch(Exception e){
            
        }
    }
    
    public void MostrarRemito(){  
        TableColumn  column = null;
        
        tabla2.getColumnModel().getColumn(0).setMaxWidth(100);
        tabla2.getColumnModel().getColumn(0).setMinWidth(100);
        tabla2.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(100);
        tabla2.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(100);
        
        tabla2.getColumnModel().getColumn(1).setMaxWidth(110);
        tabla2.getColumnModel().getColumn(1).setMinWidth(110);
        tabla2.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(110);
        tabla2.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(110);
        
        tabla2.getColumnModel().getColumn(2).setMaxWidth(80);
        tabla2.getColumnModel().getColumn(2).setMinWidth(80);
        tabla2.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(80);
        tabla2.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(80);
        
        tabla2.getColumnModel().getColumn(4).setMaxWidth(400);
        tabla2.getColumnModel().getColumn(4).setMinWidth(400);
        tabla2.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(400);
        tabla2.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(400);

        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta.executeQuery("select * from referencia_remito where cod_remito="+txtNumeroRemito.getText());
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla2.getModel();
            tabla2.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            String total="";
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null,null,null,null});
                tabla2.setValueAt(r.getString(1),i,0);
                tabla2.setValueAt(txtFechaRemito.getText(),i,1);
                tabla2.setValueAt(r.getString(6),i,2);
                tabla2.setValueAt(r.getString(4),i,3);
                tabla2.setValueAt(r.getString(5),i,4);
                tabla2.setValueAt(r.getString(2),i,5);
                tabla2.setValueAt(r.getString(3),i,6);
                txtTotalRemito.setText(r.getString(7));
                txtTotalRemito2.setText(signo_moneda+" "+r.getString(7));
                i++;
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
        }
    }

    public void MostrarTodosLosRemitos(){     
        Double contador=0.0;
        TableColumn  column = null;

        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta.executeQuery("select rr.cantidad, rr.cod_articulo, rr.referencia, rr.valor_unitario, rr.valor_total, rr.cod_remito, r.fecha from referencia_remito rr, remito r where r.condicion='NO FACTURADO' AND r.cod_remito=rr.cod_remito AND r.cod_cliente='"+txtCodigoCliente1.getText()+"'");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla2.getModel();
            tabla2.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            String total="";
                            
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null,null,null,null});
                tabla2.setValueAt(r.getString(6),i,0);
                tabla2.setValueAt(r.getString(7),i,1);
                tabla2.setValueAt(r.getString(1),i,2);
                tabla2.setValueAt(r.getString(2),i,3);
                tabla2.setValueAt(r.getString(3),i,4);
                tabla2.setValueAt(r.getString(4),i,5);
                tabla2.setValueAt(r.getString(5),i,6);
                contador=contador+ Double.parseDouble(tabla2.getValueAt(i,6).toString().replace(",","."));
                String contador2=String.format("%.2f", contador).replace(".",",");
                txtTotalRemito.setText(contador2);
                txtTotalRemito2.setText(signo_moneda+" "+contador2);
                i++;
            }
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
        }
    }    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        popMenuPagos = new javax.swing.JPopupMenu();
        mostrarPago = new javax.swing.JMenuItem();
        popRemitos = new javax.swing.JPopupMenu();
        mostrarRemitos = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        checkRemito = new javax.swing.JRadioButton();
        checkEmpleado = new javax.swing.JRadioButton();
        panelRemito = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        txtNumeroRemito = new javax.swing.JTextField();
        txtFechaRemito = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCodigoEmpleado1 = new javax.swing.JTextField();
        txtNombreApellidoCliente = new javax.swing.JTextField();
        txtTotalRemito = new javax.swing.JTextField();
        txtNombreApellidoEmpleado = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTotalRemito2 = new javax.swing.JTextField();
        txtBanderaRemito = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        botonTodosLosRemitos = new javax.swing.JButton();
        btnFacturarRemito = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtFacturaNumero1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        checkFecha = new javax.swing.JRadioButton();
        buscare1 = new javax.swing.JButton();
        txtCodigoCliente1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSumaRemitos = new javax.swing.JTextField();
        txtActualizacionPrecios = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        comboCliente = new javax.swing.JComboBox();
        txtTotalActualizado = new javax.swing.JTextField();
        checkActualizacion = new javax.swing.JCheckBox();
        panelSaldo = new javax.swing.JPanel();
        txtRealizarPago = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnRealizarPago1 = new javax.swing.JButton();
        btnRealizarPago2 = new javax.swing.JButton();
        comboCaja = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtPagoRealizado = new javax.swing.JTextField();
        txtSaldoTotal = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        txtNroCuentaCorriente = new javax.swing.JTextField();
        btnFacturarRemito2 = new javax.swing.JButton();
        panelPagos = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPagos = new javax.swing.JTable();
        panelVerPagos = new javax.swing.JPanel();
        btnRealizarPago = new javax.swing.JButton();
        btnVerPagos = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtFechaPago = new javax.swing.JTextField();
        txtUltimoPago = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnOcultarPagos = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        mostrarPago.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        mostrarPago.setText("MOSTRAR PAGO");
        mostrarPago.setActionCommand("VER PAGO");
        mostrarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarPagoActionPerformed(evt);
            }
        });
        popMenuPagos.add(mostrarPago);

        mostrarRemitos.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        mostrarRemitos.setText("MOSTRAR REMITO");
        mostrarRemitos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarRemitosActionPerformed(evt);
            }
        });
        popRemitos.add(mostrarRemitos);

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N° REMITO", "CLIENTE", "FECHA", "VENDEDOR", "cod cliente", "cod empleado", "VALOR  EN REMITO", "VALOR ACTUAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setComponentPopupMenu(popRemitos);
        tabla.setRowHeight(25);
        tabla.setRowMargin(4);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 980, 180));

        txtBuscar.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtBuscar.setBorder(null);
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFocusLost(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 60, 220, 40));

        buttonGroup1.add(checkRemito);
        checkRemito.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkRemito.setText("N° Remito");
        checkRemito.setOpaque(false);
        checkRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkRemitoActionPerformed(evt);
            }
        });
        jPanel1.add(checkRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, -1, -1));

        buttonGroup1.add(checkEmpleado);
        checkEmpleado.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkEmpleado.setText("Vendedor");
        checkEmpleado.setOpaque(false);
        checkEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkEmpleadoActionPerformed(evt);
            }
        });
        jPanel1.add(checkEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 20, -1, -1));

        panelRemito.setBackground(new java.awt.Color(204, 204, 204));
        panelRemito.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "N° Remito", "Fecha", "Cant", "Cod", "Descripcion", "Valor unitario", "Valor total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla2.setRowHeight(25);
        tabla2.setRowMargin(4);
        jScrollPane2.setViewportView(tabla2);

        panelRemito.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1160, 190));

        txtNumeroRemito.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtNumeroRemito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumeroRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroRemitoActionPerformed(evt);
            }
        });
        panelRemito.add(txtNumeroRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 118, 40));

        txtFechaRemito.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtFechaRemito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaRemitoActionPerformed(evt);
            }
        });
        panelRemito.add(txtFechaRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 130, 40));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel2.setText("TOTAL");
        panelRemito.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 250, 80, 40));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel3.setText("N° Remito");
        panelRemito.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txtCodigoEmpleado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoEmpleado1ActionPerformed(evt);
            }
        });
        panelRemito.add(txtCodigoEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 40, 20));

        txtNombreApellidoCliente.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtNombreApellidoCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreApellidoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreApellidoClienteActionPerformed(evt);
            }
        });
        panelRemito.add(txtNombreApellidoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 320, 40));

        txtTotalRemito.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        panelRemito.add(txtTotalRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 50, 20));
        panelRemito.add(txtNombreApellidoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 50, 42));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel8.setText("Cliente");
        panelRemito.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, -1, 40));

        txtTotalRemito2.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtTotalRemito2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panelRemito.add(txtTotalRemito2, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 250, 190, 42));
        panelRemito.add(txtBanderaRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 50, 20));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel9.setText("Fecha");
        panelRemito.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, 40));

        botonTodosLosRemitos.setBackground(new java.awt.Color(5, 52, 99));
        botonTodosLosRemitos.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        botonTodosLosRemitos.setForeground(new java.awt.Color(255, 255, 255));
        botonTodosLosRemitos.setText("TODOS LOS REMITOS");
        botonTodosLosRemitos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonTodosLosRemitosActionPerformed(evt);
            }
        });
        panelRemito.add(botonTodosLosRemitos, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 10, 200, 40));

        btnFacturarRemito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonFacturarRemito.png"))); // NOI18N
        btnFacturarRemito.setBorder(null);
        btnFacturarRemito.setBorderPainted(false);
        btnFacturarRemito.setContentAreaFilled(false);
        btnFacturarRemito.setFocusPainted(false);
        btnFacturarRemito.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonFacturarRemitoHover.png"))); // NOI18N
        btnFacturarRemito.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonFacturarRemitoHover.png"))); // NOI18N
        btnFacturarRemito.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonFacturarRemitoHover.png"))); // NOI18N
        btnFacturarRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarRemitoActionPerformed(evt);
            }
        });
        panelRemito.add(btnFacturarRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 190, 42));

        jPanel1.add(panelRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 980, 10));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoRemitosXs-01.png"))); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 50));

        txtFacturaNumero1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        txtFacturaNumero1.setText("SALDOS");
        jPanel1.add(txtFacturaNumero1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 540, 400, 10));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, -1, 80));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel4.setText("Cliente");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 90, -1));

        buttonGroup1.add(checkFecha);
        checkFecha.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkFecha.setText("Fecha");
        checkFecha.setOpaque(false);
        checkFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkFechaActionPerformed(evt);
            }
        });
        jPanel1.add(checkFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, -1, -1));

        buscare1.setBackground(new java.awt.Color(93, 116, 163));
        buscare1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoLupaF3.png"))); // NOI18N
        buscare1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscare1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscare1ActionPerformed(evt);
            }
        });
        jPanel1.add(buscare1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, 50, 39));

        txtCodigoCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoCliente1ActionPerformed(evt);
            }
        });
        jPanel1.add(txtCodigoCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 60, 40));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel6.setText("SALDO RESTANTE");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 560, 200, -1));

        txtSumaRemitos.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtSumaRemitos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txtSumaRemitos, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 340, 180, 42));

        txtActualizacionPrecios.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtActualizacionPrecios.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txtActualizacionPrecios, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, 180, 42));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel10.setText("Subtotal Remitos");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 340, 170, 40));

        comboCliente.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        comboCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClienteActionPerformed(evt);
            }
        });
        jPanel1.add(comboCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 320, 39));

        txtTotalActualizado.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtTotalActualizado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txtTotalActualizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 440, 180, 42));

        checkActualizacion.setBackground(new java.awt.Color(255, 255, 255));
        checkActualizacion.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkActualizacion.setText("Actualización Precios");
        checkActualizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkActualizacionActionPerformed(evt);
            }
        });
        jPanel1.add(checkActualizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 390, -1, 40));

        panelSaldo.setBackground(new java.awt.Color(255, 255, 255));
        panelSaldo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtRealizarPago.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtRealizarPago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRealizarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRealizarPagoActionPerformed(evt);
            }
        });
        txtRealizarPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRealizarPagoKeyTyped(evt);
            }
        });
        panelSaldo.add(txtRealizarPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 220, -1));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel13.setText("Caja");
        panelSaldo.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 70, -1));

        btnRealizarPago1.setBackground(new java.awt.Color(93, 116, 163));
        btnRealizarPago1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        btnRealizarPago1.setForeground(new java.awt.Color(255, 255, 255));
        btnRealizarPago1.setText("CANCELAR");
        btnRealizarPago1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRealizarPago1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRealizarPago1MouseExited(evt);
            }
        });
        btnRealizarPago1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarPago1ActionPerformed(evt);
            }
        });
        panelSaldo.add(btnRealizarPago1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 190, 38));

        btnRealizarPago2.setBackground(new java.awt.Color(93, 116, 163));
        btnRealizarPago2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        btnRealizarPago2.setForeground(new java.awt.Color(255, 255, 255));
        btnRealizarPago2.setText("REALIZAR PAGO");
        btnRealizarPago2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRealizarPago2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRealizarPago2MouseExited(evt);
            }
        });
        btnRealizarPago2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarPago2ActionPerformed(evt);
            }
        });
        panelSaldo.add(btnRealizarPago2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 190, 38));

        comboCaja.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        comboCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCajaActionPerformed(evt);
            }
        });
        panelSaldo.add(comboCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 220, 39));

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel16.setText("Monto a pagar");
        panelSaldo.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 170, -1));

        jPanel1.add(panelSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 570, 160));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel11.setText("Total Remitos");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 440, 150, 40));

        txtPagoRealizado.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtPagoRealizado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txtPagoRealizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 490, 180, -1));

        txtSaldoTotal.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtSaldoTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txtSaldoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 550, 180, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 100, 230, 10));

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel15.setText("Total Pago Realizado");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 490, 230, 30));

        txtNroCuentaCorriente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNroCuentaCorrienteActionPerformed(evt);
            }
        });
        jPanel1.add(txtNroCuentaCorriente, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 60, 40));

        btnFacturarRemito2.setBackground(new java.awt.Color(93, 116, 163));
        btnFacturarRemito2.setFont(new java.awt.Font("Calibri", 1, 17)); // NOI18N
        btnFacturarRemito2.setForeground(new java.awt.Color(255, 255, 255));
        btnFacturarRemito2.setText("FACTURAR  REMITOS");
        btnFacturarRemito2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFacturarRemito2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFacturarRemito2MouseExited(evt);
            }
        });
        btnFacturarRemito2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarRemito2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnFacturarRemito2, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 600, 190, 38));

        panelPagos.setBackground(new java.awt.Color(255, 255, 255));
        panelPagos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaPagos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tablaPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° COMP", "FECHA", "SALDO", "MONTO ABONADO", "MONTO RESTANTE"
            }
        ));
        tablaPagos.setComponentPopupMenu(popMenuPagos);
        tablaPagos.setRowHeight(25);
        tablaPagos.setRowMargin(4);
        jScrollPane3.setViewportView(tablaPagos);

        panelPagos.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 130));

        jPanel1.add(panelPagos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 590, 170));

        panelVerPagos.setBackground(new java.awt.Color(255, 255, 255));
        panelVerPagos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRealizarPago.setBackground(new java.awt.Color(93, 116, 163));
        btnRealizarPago.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        btnRealizarPago.setForeground(new java.awt.Color(255, 255, 255));
        btnRealizarPago.setText("REALIZAR PAGO");
        btnRealizarPago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRealizarPagoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRealizarPagoMouseExited(evt);
            }
        });
        btnRealizarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarPagoActionPerformed(evt);
            }
        });
        panelVerPagos.add(btnRealizarPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 190, 38));

        btnVerPagos.setBackground(new java.awt.Color(93, 116, 163));
        btnVerPagos.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        btnVerPagos.setForeground(new java.awt.Color(255, 255, 255));
        btnVerPagos.setText("VER PAGOS");
        btnVerPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVerPagosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVerPagosMouseExited(evt);
            }
        });
        btnVerPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPagosActionPerformed(evt);
            }
        });
        panelVerPagos.add(btnVerPagos, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 190, 38));

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel14.setText("Fecha del último Pago");
        panelVerPagos.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 240, 30));

        txtFechaPago.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtFechaPago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panelVerPagos.add(txtFechaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 190, -1));

        txtUltimoPago.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtUltimoPago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panelVerPagos.add(txtUltimoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 190, -1));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel12.setText("Ultimo pago realizado");
        panelVerPagos.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 40));

        btnOcultarPagos.setBackground(new java.awt.Color(93, 116, 163));
        btnOcultarPagos.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        btnOcultarPagos.setForeground(new java.awt.Color(255, 255, 255));
        btnOcultarPagos.setText("OCULTAR PAGOS");
        btnOcultarPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnOcultarPagosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnOcultarPagosMouseExited(evt);
            }
        });
        btnOcultarPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOcultarPagosActionPerformed(evt);
            }
        });
        panelVerPagos.add(btnOcultarPagos, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 190, 38));

        jPanel1.add(panelVerPagos, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 530, 150));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, -1));

        jMenu1.setText("Opciones");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem1.setText("Actualización ON/OFF");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem3.setText("Buscar cliente");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkActualizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkActualizacionActionPerformed
        if(checkActualizacion.isSelected()){
            txtSumaRemitos.setText(String.format("%.2f", sumaRemitos).replace(",", "."));
            txtActualizacionPrecios.setText(String.format("%.2f", sumaActualRemitos-sumaRemitos).replace(",", "."));
            txtTotalActualizado.setText(String.format("%.2f", sumaActualRemitos).replace(",", "."));
            Double saldoTotal=sumaActualRemitos- Double.parseDouble(txtPagoRealizado.getText());
            txtSaldoTotal.setText(String.format("%.2f", saldoTotal).replace(",", "."));
        }else{
            txtSumaRemitos.setText(String.format("%.2f", sumaRemitos).replace(",", "."));
            txtActualizacionPrecios.setText("0.00");
            txtTotalActualizado.setText(String.format("%.2f", sumaRemitos).replace(",", "."));
            Double saldoTotal=sumaRemitos- Double.parseDouble(txtPagoRealizado.getText());
            txtSaldoTotal.setText(String.format("%.2f", saldoTotal).replace(",", "."));
        }
    }//GEN-LAST:event_checkActualizacionActionPerformed

    private void comboClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClienteActionPerformed

        if(!txtCodigoCliente1.getText().equals("")){
            cargarRemitoNoFacturado(txtCodigoCliente1.getText());
            MostrarTodosLosRemitos();
            cargarTablaPagos();
            panelVerPagos.setVisible(true);
        }

        if(checkActualizacion.isSelected()){
            txtSumaRemitos.setText(String.format("%.2f", sumaRemitos).replace(",", "."));
            txtActualizacionPrecios.setText(String.format("%.2f", sumaActualRemitos-sumaRemitos).replace(",", "."));
            txtTotalActualizado.setText(String.format("%.2f", sumaActualRemitos).replace(",", "."));
            
            Double saldoTotal=sumaActualRemitos- Double.parseDouble(txtPagoRealizado.getText());
            txtSaldoTotal.setText(String.format("%.2f", saldoTotal).replace(",", "."));
        }else{
            txtSumaRemitos.setText(String.format("%.2f", sumaRemitos).replace(",", "."));
            txtActualizacionPrecios.setText("0.00");
            txtTotalActualizado.setText(String.format("%.2f", sumaRemitos).replace(",", "."));
            
            Double saldoTotal=sumaRemitos- Double.parseDouble(txtPagoRealizado.getText());
            txtSaldoTotal.setText(String.format("%.2f", saldoTotal).replace(",", "."));
        }
        if(txtSaldoTotal.getText().equals("0.00")){
            btnFacturarRemito2.setVisible(true);
        }else{
            btnFacturarRemito2.setVisible(false);
        }
    }//GEN-LAST:event_comboClienteActionPerformed

    private void txtCodigoCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoCliente1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoCliente1ActionPerformed

    private void buscare1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscare1ActionPerformed
        enviar_clienteRemito form= new enviar_clienteRemito ();
        form.setVisible(true);
        form.btnVerFactura.setVisible(false);
        form.enviar.setVisible(false);
        form.recibe.setText("1");
        form.toFront();
    }//GEN-LAST:event_buscare1ActionPerformed

    private void checkFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkFechaActionPerformed

    private void botonTodosLosRemitosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTodosLosRemitosActionPerformed
        MostrarTodosLosRemitos();
        txtBanderaRemito.setText("1");
    }//GEN-LAST:event_botonTodosLosRemitosActionPerformed

    private void txtNombreApellidoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreApellidoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreApellidoClienteActionPerformed

    private void btnFacturarRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarRemitoActionPerformed
        String domicilioCliete="";
        this.dispose();
        Frm_facturarRemito facturarRemito= new Frm_facturarRemito();

        Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql

        try{
            Statement consultaCliente=conn.createStatement();
            ResultSet resCliente= consultaCliente.executeQuery("select * from cliente where cod_cliente="+txtCodigoCliente1.getText());

            while(resCliente.next()){ //TRAIGO LOS DATOS DE LA EMPRESA PARA PASARCELOS A LA FACTURA
                domicilioCliete= (resCliente.getString(4)+", "+resCliente.getString(3));
            }
        }catch(Exception e){

        }

        if(txtBanderaRemito.getText().equals("0")){
            facturarRemito.txtCodigoCliente.setText(txtCodigoCliente1.getText());
            facturarRemito.txtCodigoEmpleado.setText(txtCodigoEmpleado1.getText());
            facturarRemito.txtCliente.setText(txtNombreApellidoCliente.getText());
            facturarRemito.txtDomicilio.setText(domicilioCliete);
            try{
                facturarRemito.cargarComboEmpleado();
            } catch(ClassNotFoundException e){
                JOptionPane.showMessageDialog(null,"No hay conexion con Mysql") ;

            }
            facturarRemito.comboVendedor.setSelectedItem(txtNombreApellidoEmpleado.getText());
            facturarRemito.txtCodigoRemito.setText(txtNumeroRemito.getText());
            facturarRemito.txtCodiRemito.setText(txtNumeroRemito.getText());
            facturarRemito.txtRecibeRemito.setText(txtBanderaRemito.getText());
        }else{
            try{
                facturarRemito.cargarComboEmpleado();
            } catch(ClassNotFoundException e){
                JOptionPane.showMessageDialog(null,"No hay conexion con Mysql") ;

            }
            facturarRemito.txtDomicilio.setText(domicilioCliete);
            facturarRemito.txtCodigoCliente.setText(txtCodigoCliente1.getText());
            facturarRemito.txtCliente.setText(txtNombreApellidoCliente.getText());
            facturarRemito.txtRecibeRemito.setText(txtBanderaRemito.getText());
        }
    }//GEN-LAST:event_btnFacturarRemitoActionPerformed

    private void txtCodigoEmpleado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoEmpleado1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoEmpleado1ActionPerformed

    private void txtFechaRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaRemitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaRemitoActionPerformed

    private void txtNumeroRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroRemitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroRemitoActionPerformed

    private void checkEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkEmpleadoActionPerformed

    private void checkRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkRemitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkRemitoActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if (buttonGroup1.getSelection()==null) {
            // Si ninguno de los JRadioButtons está seleccionado, evitamos que se
            // escriba algo dentro del JTextField y mostramos un mensaje de error
            evt.consume();
            JOptionPane.showMessageDialog(this, "Debe seleccionar una opción para filtrar los resultados", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            txtBuscar.setText("");
            txtBuscar.transferFocus();
        }else{
            filtro2(txtBuscar.getText().toUpperCase(), tabla);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusLost
        if(txtBuscar.getText().equals("")){
            txtBuscar.setText("Ingrese su búsqueda");
        }
        txtBuscar.setForeground(Color.gray);
    }//GEN-LAST:event_txtBuscarFocusLost

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
        if(txtBuscar.getText().equals("Ingrese su búsqueda")){
            txtBuscar.setText("");
        }
        txtBuscar.setForeground(Color.black);
    }//GEN-LAST:event_txtBuscarFocusGained

                                                  

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if(banderaFacturado==true){
            botonTodosLosRemitos.setVisible(false);
        }else
        botonTodosLosRemitos.setVisible(true);

        txtBanderaRemito.setText("0");
        //panelRemito.setVisible(true);
        int fila= tabla.getSelectedRow();

        if(fila>=0){
            Select=tabla.getSelectedRow();
            txtNumeroRemito.setText(tabla.getValueAt(Select,0).toString());
            txtFechaRemito.setText(tabla.getValueAt(Select,2).toString());
            txtCodigoCliente1.setText(tabla.getValueAt(Select,4).toString());
            txtCodigoEmpleado1.setText(tabla.getValueAt(Select,5).toString());
            txtNombreApellidoEmpleado.setText(tabla.getValueAt(Select,3).toString());
            txtNombreApellidoCliente.setText(tabla.getValueAt(Select,1).toString());
            MostrarRemito();
        }else{
            JOptionPane.showMessageDialog(null,"No selecciono ninguna fila");
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void btnRealizarPagoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRealizarPagoMouseEntered

    }//GEN-LAST:event_btnRealizarPagoMouseEntered

    private void btnRealizarPagoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRealizarPagoMouseExited

    }//GEN-LAST:event_btnRealizarPagoMouseExited

    private void btnRealizarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarPagoActionPerformed
        
        panelSaldo.setVisible(true);
        btnRealizarPago.setVisible(false);
        txtRealizarPago.requestFocus();
        
        btnVerPagos.setVisible(false);
        btnOcultarPagos.setVisible(false);
    }//GEN-LAST:event_btnRealizarPagoActionPerformed

    private void btnRealizarPago1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRealizarPago1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRealizarPago1MouseEntered

    private void btnRealizarPago1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRealizarPago1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRealizarPago1MouseExited

    private void btnRealizarPago1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarPago1ActionPerformed
        btnRealizarPago.setVisible(true);
        panelSaldo.setVisible(false);
        
        btnVerPagos.setVisible(true);
        btnOcultarPagos.setVisible(true);
    }//GEN-LAST:event_btnRealizarPago1ActionPerformed

    private void btnRealizarPago2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRealizarPago2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRealizarPago2MouseEntered

    private void btnRealizarPago2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRealizarPago2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRealizarPago2MouseExited

    private void btnRealizarPago2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarPago2ActionPerformed
      if(txtRealizarPago.getText().equals("") || txtRealizarPago.getText().equals("0") || txtRealizarPago.getText().equals("0.0")|| txtRealizarPago.getText().equals("0.00")){
          JOptionPane.showMessageDialog(null,"Debe ingresar un monto MAYOR a cero y MENOR O IGUAL al saldo restante");
          txtRealizarPago.requestFocus();
      }else if(Double.parseDouble(txtRealizarPago.getText()) > Double.parseDouble(txtSaldoTotal.getText())){
            JOptionPane.showMessageDialog(null,"El monto que desea ingresar es mayor al saldo restante");
            txtRealizarPago.requestFocus();
        }else if(!txtSaldoTotal.getText().equals("0.00")){
                  realizarPago();
                  cargarTablaPagos();
                  txtRealizarPago.setText("");
                  if(!txtCodigoCliente1.getText().equals("")){
                    cargarRemitoNoFacturado(txtCodigoCliente1.getText());
                  }

                if(checkActualizacion.isSelected()){
                    txtSumaRemitos.setText(String.format("%.2f", sumaRemitos).replace(",", "."));
                    txtActualizacionPrecios.setText(String.format("%.2f", sumaActualRemitos-sumaRemitos).replace(",", "."));
                    txtTotalActualizado.setText(String.format("%.2f", sumaActualRemitos).replace(",", "."));

                    Double saldoTotal=sumaActualRemitos- Double.parseDouble(txtPagoRealizado.getText());
                    txtSaldoTotal.setText(String.format("%.2f", saldoTotal).replace(",", "."));
                }else{
                    txtSumaRemitos.setText(String.format("%.2f", sumaRemitos).replace(",", "."));
                    txtActualizacionPrecios.setText("0.00");
                    txtTotalActualizado.setText(String.format("%.2f", sumaRemitos).replace(",", "."));

                    Double saldoTotal=sumaRemitos- Double.parseDouble(txtPagoRealizado.getText());
                    txtSaldoTotal.setText(String.format("%.2f", saldoTotal).replace(",", "."));
                }
                //SI EL SALDO RESTANTE ES CERO ME MANDA A FACTURAR TODOS LOS REMITOS, SINO ME MANDA A IMPRIMIR EL COMPROVANTE DE PAGO
                if(txtSaldoTotal.getText().equals("0.00") || txtSaldoTotal.getText().equals("-0.00")){
                    btnFacturarRemito2.setVisible(true);
                    JOptionPane.showMessageDialog(null,"EL SALDO DEL CLIENTE FUE CANCELADO,DEBE REALIZAR LA FACTURA CORRESPONDIENTE");
                    facturarSaldo();
                }else{
                    Connection miconexion = conexion.ObtenerConexion();
                    Map parametros = new HashMap();
                    parametros.put("codf",id_referencia_cuenta);
                    parametros.put("codCliente",txtCodigoCliente1.getText());


                    int op=JOptionPane.showConfirmDialog(null, "PAGO REALIZADO SATISFACTORIAMENTE ¿DESEA IMPRIMIR EL COMPROBANTE?","SELECCIONE UN OPCION" ,JOptionPane.YES_NO_OPTION);
                    if (op==JOptionPane.YES_NO_OPTION){

                        try {
                            String reporte="comprobantePago.jasper";
                            JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                            JasperViewer ventanavisor=new JasperViewer(informe,false);
                            ventanavisor.setTitle("Comprobante de pago");
                            ventanavisor.setVisible(true);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, e.getMessage());
                        } 
                    }else{

                    }
                }
            }else{
                  JOptionPane.showMessageDialog(null,"No puede realizar el pago porque el saldo restante es de '"+signo_moneda+" '0.00");
            }

    }//GEN-LAST:event_btnRealizarPago2ActionPerformed

    private void txtNroCuentaCorrienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroCuentaCorrienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNroCuentaCorrienteActionPerformed

    private void btnFacturarRemito2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFacturarRemito2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFacturarRemito2MouseEntered

    private void btnFacturarRemito2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFacturarRemito2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFacturarRemito2MouseExited
    public void facturarSaldo(){
        String domicilioCliente="", cuitCliente="";
        String nombreCliente=tabla.getValueAt(0,1).toString();
        String nombreEmpleado=tabla.getValueAt(0,3).toString();
        String codigoEmpleado=tabla.getValueAt(0,5).toString();
        
        this.dispose();
        Frm_facturarRemito facturarRemito= new Frm_facturarRemito();

        Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql

        try{
            Statement consultaCliente=conn.createStatement();
            ResultSet resCliente= consultaCliente.executeQuery("select * from cliente where cod_cliente="+txtCodigoCliente1.getText());

            while(resCliente.next()){ //TRAIGO LOS DATOS DE LA EMPRESA PARA PASARCELOS A LA FACTURA
                if(!resCliente.getString(4).equals("") || !resCliente.getString(3).equals("")){
                    domicilioCliente= (resCliente.getString(4)+", "+resCliente.getString(3));
                }
                cuitCliente=resCliente.getString(8);
                
            }
        }catch(Exception e){

        }

       // if(txtBanderaRemito.getText().equals("0")){
            facturarRemito.txtCodigoCliente.setText(txtCodigoCliente1.getText());
            facturarRemito.txtCodigoEmpleado.setText(codigoEmpleado);
            facturarRemito.txtCliente.setText(nombreCliente);
            facturarRemito.txtCuit.setText(cuitCliente);
            facturarRemito.txtDomicilio.setText(domicilioCliente);
            try{
                facturarRemito.cargarComboEmpleado();
            } catch(ClassNotFoundException e){
                JOptionPane.showMessageDialog(null,"No hay conexion con Mysql") ;

            }
            facturarRemito.comboVendedor.setSelectedItem(nombreEmpleado);
            facturarRemito.comboPago.setSelectedItem("SALDO CANCELADO");
            /*facturarRemito.txtCodigoRemito.setText(txtNumeroRemito.getText());
            facturarRemito.txtCodiRemito.setText(txtNumeroRemito.getText());
            facturarRemito.txtRecibeRemito.setText(txtBanderaRemito.getText());
        //*
        /*}else{
            try{
                facturarRemito.cargarComboEmpleado();
            } catch(ClassNotFoundException e){
                JOptionPane.showMessageDialog(null,"No hay conexion con Mysql") ;

            }
            facturarRemito.txtDomicilio.setText(domicilioCliete);
            facturarRemito.txtCodigoCliente.setText(txtCodigoCliente1.getText());
            facturarRemito.txtCliente.setText(txtNombreApellidoCliente.getText());
            facturarRemito.txtRecibeRemito.setText(txtBanderaRemito.getText());
        }*/
    }
    private void btnFacturarRemito2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarRemito2ActionPerformed
        facturarSaldo();
    }//GEN-LAST:event_btnFacturarRemito2ActionPerformed

    private void btnVerPagosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerPagosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerPagosMouseEntered

    private void btnVerPagosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerPagosMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerPagosMouseExited

    private void btnVerPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPagosActionPerformed
        panelPagos.setVisible(true);
        btnVerPagos.setVisible(false);
        btnOcultarPagos.setVisible(true);
        
    }//GEN-LAST:event_btnVerPagosActionPerformed

    private void btnOcultarPagosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOcultarPagosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOcultarPagosMouseEntered

    private void btnOcultarPagosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOcultarPagosMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOcultarPagosMouseExited

    private void btnOcultarPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcultarPagosActionPerformed
        panelPagos.setVisible(false);
        btnOcultarPagos.setVisible(false);
        btnVerPagos.setVisible(true);
        
    }//GEN-LAST:event_btnOcultarPagosActionPerformed

    private void txtRealizarPagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRealizarPagoKeyTyped
        char []p={'1','2','3','4','5','6','7','8','9','0','.',',','-'};
        int b=0;
        for(int i=0;i<=12;i++){
            if (p[i]==evt.getKeyChar()){
                b=1;
            }
        }
        if(b==0){
            evt.consume();  
            getToolkit().beep(); 
        }
    }//GEN-LAST:event_txtRealizarPagoKeyTyped

    private void mostrarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarPagoActionPerformed
        Select=tablaPagos.getSelectedRow();
        String codigoCliente="";
        Connection miconexion = conexion.ObtenerConexion();
        Map parametros = new HashMap();
        String codigoPago= tablaPagos.getValueAt(Select,0).toString(); 
            
        codigoCliente=txtCodigoCliente1.getText();

             parametros.put("codf",codigoPago);
             parametros.put("codCliente",codigoCliente);
            try{     
                String reporte="comprobantePago.jasper";
                JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                JasperViewer ventanavisor=new JasperViewer(informe,false);
                ventanavisor.setTitle("Comprobante de pago");
                ventanavisor.setVisible(true); 
                //this.setVisible(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        
    }//GEN-LAST:event_mostrarPagoActionPerformed

    private void mostrarRemitosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarRemitosActionPerformed
        Select=tabla.getSelectedRow();
        String codigoCliente="";
        Connection miconexion = conexion.ObtenerConexion();
        Map parametros = new HashMap();
        String codigoPago= tabla.getValueAt(Select,0).toString(); 
            

             parametros.put("codf",codigoPago);
            try{     
                String reporte="remitoa.jasper";
                JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                JasperViewer ventanavisor=new JasperViewer(informe,false);
                ventanavisor.setTitle("Reporte de remito");
                ventanavisor.setVisible(true); 
                //this.setVisible(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
    }//GEN-LAST:event_mostrarRemitosActionPerformed

    private void txtRealizarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRealizarPagoActionPerformed
     if(txtRealizarPago.getText().equals("") || txtRealizarPago.getText().equals("0") || txtRealizarPago.getText().equals("0.0")|| txtRealizarPago.getText().equals("0.00")){
         JOptionPane.showMessageDialog(null,"Debe ingresar un mmonto MAYOR a cero y MENOR O IGUAL al saldo restante");
         txtRealizarPago.requestFocus();
     }else if(Double.parseDouble(txtRealizarPago.getText()) > Double.parseDouble(txtSaldoTotal.getText())){
          JOptionPane.showMessageDialog(null,"El monto que desea ingresar es mayor al saldo restante");
          txtRealizarPago.requestFocus();
        }else if(!txtSaldoTotal.getText().equals("0.00")){
                  realizarPago();
                  cargarTablaPagos();
                  txtRealizarPago.setText("");
                  if(!txtCodigoCliente1.getText().equals("")){
                    cargarRemitoNoFacturado(txtCodigoCliente1.getText());
                  }

                if(checkActualizacion.isSelected()){
                    txtSumaRemitos.setText(String.format("%.2f", sumaRemitos).replace(",", "."));
                    txtActualizacionPrecios.setText(String.format("%.2f", sumaActualRemitos-sumaRemitos).replace(",", "."));
                    txtTotalActualizado.setText(String.format("%.2f", sumaActualRemitos).replace(",", "."));

                    Double saldoTotal=sumaActualRemitos- Double.parseDouble(txtPagoRealizado.getText());
                    txtSaldoTotal.setText(String.format("%.2f", saldoTotal).replace(",", "."));
                }else{
                    txtSumaRemitos.setText(String.format("%.2f", sumaRemitos).replace(",", "."));
                    txtActualizacionPrecios.setText("0.00");
                    txtTotalActualizado.setText(String.format("%.2f", sumaRemitos).replace(",", "."));

                    Double saldoTotal=sumaRemitos- Double.parseDouble(txtPagoRealizado.getText());
                    txtSaldoTotal.setText(String.format("%.2f", saldoTotal).replace(",", "."));
                }
                if(txtSaldoTotal.getText().equals("0.00") || txtSaldoTotal.getText().equals("-0.00")){
                    btnFacturarRemito2.setVisible(true);
                }
              }else{
                  JOptionPane.showMessageDialog(null,"No puede realizar el pago porque el saldo restante es de '"+signo_moneda+" '0.00");
              }
    }//GEN-LAST:event_txtRealizarPagoActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        enviar_clienteRemito form= new enviar_clienteRemito ();
        form.setVisible(true);
        form.btnVerFactura.setVisible(false);
        form.enviar.setVisible(false);
        form.recibe.setText("1");
        form.toFront();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       Configurar_Actualizacion_Precios_Saldos repor = new Configurar_Actualizacion_Precios_Saldos();
       repor .setVisible(true);
       repor.setLocationRelativeTo(this);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void comboCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCajaActionPerformed
    
    DefaultTableModel dm;
    /* Método filtro*/
    private void filtro2(String consulta, JTable jtableBuscar){
        dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter(dm);
        jtableBuscar.setRowSorter(tr);

        int columna=0;
        // Identificamos cual es el JRadioButton seleccionado para filtrar el
        // resultado de acuerdo a los datos de la columna elegida
        if (checkRemito.isSelected()) {
            columna = 0;
        }else if (checkEmpleado.isSelected()) {
                    columna = 3;
              }else if (checkFecha.isSelected()) {
                        columna = 2;
                    }
        tr.setRowFilter(RowFilter.regexFilter(consulta, columna));
    }
    
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
            java.util.logging.Logger.getLogger(Modulo_Saldos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modulo_Saldos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modulo_Saldos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modulo_Saldos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modulo_Saldos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonTodosLosRemitos;
    private javax.swing.JButton btnFacturarRemito;
    private javax.swing.JButton btnFacturarRemito2;
    private javax.swing.JButton btnOcultarPagos;
    private javax.swing.JButton btnRealizarPago;
    private javax.swing.JButton btnRealizarPago1;
    private javax.swing.JButton btnRealizarPago2;
    private javax.swing.JButton btnVerPagos;
    private javax.swing.JButton buscare1;
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JCheckBox checkActualizacion;
    private javax.swing.JRadioButton checkEmpleado;
    private javax.swing.JRadioButton checkFecha;
    private javax.swing.JRadioButton checkRemito;
    public static javax.swing.JComboBox comboCaja;
    public static javax.swing.JComboBox comboCliente;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JMenuItem mostrarPago;
    private javax.swing.JMenuItem mostrarRemitos;
    private javax.swing.JPanel panelPagos;
    private javax.swing.JPanel panelRemito;
    private javax.swing.JPanel panelSaldo;
    private javax.swing.JPanel panelVerPagos;
    private javax.swing.JPopupMenu popMenuPagos;
    private javax.swing.JPopupMenu popRemitos;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla2;
    private javax.swing.JTable tablaPagos;
    private javax.swing.JTextField txtActualizacionPrecios;
    private javax.swing.JTextField txtBanderaRemito;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtCodigoCliente1;
    private javax.swing.JTextField txtCodigoEmpleado1;
    private javax.swing.JLabel txtFacturaNumero1;
    private javax.swing.JTextField txtFechaPago;
    private javax.swing.JTextField txtFechaRemito;
    private javax.swing.JTextField txtNombreApellidoCliente;
    private javax.swing.JTextField txtNombreApellidoEmpleado;
    public static javax.swing.JTextField txtNroCuentaCorriente;
    private javax.swing.JTextField txtNumeroRemito;
    private javax.swing.JTextField txtPagoRealizado;
    private javax.swing.JTextField txtRealizarPago;
    private javax.swing.JTextField txtSaldoTotal;
    private javax.swing.JTextField txtSumaRemitos;
    private javax.swing.JTextField txtTotalActualizado;
    private javax.swing.JTextField txtTotalRemito;
    private javax.swing.JTextField txtTotalRemito2;
    private javax.swing.JTextField txtUltimoPago;
    // End of variables declaration//GEN-END:variables
}
