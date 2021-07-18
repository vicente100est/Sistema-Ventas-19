/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vistas;
import static Vistas.enviar_producto.txt_recibe;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;


public class Registrar_Articulos extends javax.swing.JFrame {
    //VARIABLES GLOBALES
    int Select,SelectPapelera,contador,seleccionarTodos=0,todoCorrecto=0,banderaPrecio=0, preciosBaseDatos=0;
    boolean TodosSeleccionados=false,aceptado=false;
    String codigo,referencia,cantidad,marca,valor,checkPrimero,checkSegundo,checkTercero;
    private int ancho,alto;
    
    public Registrar_Articulos(java.awt.Frame parent, boolean modal) {
        
        initComponents();//SE INICIIAN TODOS LOS COMPONENTES
        
        setTitle("Ingreso de Articulos");  //TITULO DEL FRAME
        setLocationRelativeTo(null); //PONGO LA VENTANA AL CENTRO DE LA PANTALLA
        this.toFront();
        
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());//ICONO DEL SISTEMA
        }catch(Exception e){
            
        }
        
        cargarComboProveedores(); // CARGO LOS PROVEEDORES
        
        
        
        Papelera.setSize(990,750); //EL TAMAÑO DEL PANEL DE LA PAPELERA LO TENGO QUE SETEAR INDIVIDUALMENTE
        Papelera.setLocationRelativeTo(null);
        
        
        JTableHeader th;  //LE CAMBIO EL COLOR Y LOS ESTILOS A LAS CABECERAS DE LAS TABLAS
        th = tabla.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
              
        JTableHeader th2; //LE CAMBIO EL COLOR Y LOS ESTILOS A LAS CABECERAS DE LAS TABLAS
        th2 = tablaPapelera.getTableHeader(); 
        Font fuente2 = new Font("Calibri", Font.BOLD, 20); 
        th2.setFont(fuente2); 
        th2.setBackground(new Color(93,116,163));
        th2.setForeground(new Color(255,255,255));
        
        JTableHeader th3; //LE CAMBIO EL COLOR Y LOS ESTILOS A LAS CABECERAS DE LAS TABLAS
        th3 = tablaReferenciaArticulo.getTableHeader(); 
        Font fuente3 = new Font("Calibri", Font.BOLD, 20); 
        th3.setFont(fuente3); 
        th3.setBackground(new Color(93,116,163));
        th3.setForeground(new Color(255,255,255));
        
        AdvertenciaExcel.setVisible(false); //ESTA PORCION ESTA EN DESUSO ACTUALMENTE
        AdvertenciaExcel.setSize(830,750);//ESTA PORCION ESTA EN DESUSO ACTUALMENTE
        AdvertenciaExcel.setLocationRelativeTo(null);//ESTA PORCION ESTA EN DESUSO ACTUALMENTE
        AdvertenciaExcel.setModal(true);//ESTA PORCION ESTA EN DESUSO ACTUALMENTE
            
        if (Menu_Principal.lb_tipo.getText().equals("Facturador")){
          btn_cancelara.setEnabled(false); 
        }
        
         deshabilitar(); //LE DIGO CUALES COMPONENTES MOSTRAR Y CUALES NO
         MostrarArticulos(); //CARGO LA TABLA ARTICULOS
         MostrarArticulosPapelera(); //MUESTRO LA  PAPELERA
         
         
        
    }

    
    public void cargarComboProveedores(){ //CARGO LOS PROVEEDORES
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet   res= consulta.executeQuery("select * from proveedor WHERE estado='ACTIVO' order by nombre_firma");
               
            comboProveedor.removeAllItems();
            comboProveedor.addItem("SELECCIONE PROVEEDOR");
            comboProveedor1.removeAllItems();
            comboProveedor1.addItem("SELECCIONE PROVEEDOR");
            
            while(res.next()){
                comboProveedor.addItem(res.getString(2));
                comboProveedor1.addItem(res.getString(2));
            }
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        
        //CARGO EL COMBO DEL IVA VENTA
        comboIva.addItem("19%");
        comboIva.addItem("0%");
        
        //CARGO EL COMBO DEL IVA COMPRA
        comboIvaCompra.addItem("19%");
        comboIvaCompra.addItem("0%");
        
        //CARGO EL COMBO CATEGORIA
        comboCategoria.removeAllItems();
        comboCategoria.addItem("ARTICULOS");
        comboCategoria.addItem("MATERIA PRIMA");
        comboCategoria.addItem("INSUMOS");
        comboCategoria.addItem("OTROS");
            
    }
    
    public void cargarReferenciaProveedor(){ //CARGA LA TABLA QUE GUARDALOS PROVEEDORES DE CADAARTICULO (PUEDE HABER MAS DE 1 PROVEEDOR POR ARTICULO)
        
        txtArticuloReferencia.setText(txtProducto.getText());
        txtCodigoReferencia.setText(txtCodigo.getText());
        txtPrecioReferencia.setText("");
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            Statement consulta2=conn.createStatement(); // crea una variable que se encargue del código de sql
            Statement consulta3=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet   res1= consulta.executeQuery("select * from proveedor WHERE estado='ACTIVO' order by nombre_firma");
            
            
            comboReferenciaProveedor.removeAllItems(); //CARGO EL COMBO DE LOS PROVEEDORES
            comboReferenciaProveedor.addItem("SELECCIONE PROVEEDOR");
            while(res1.next()){
                comboReferenciaProveedor.addItem(res1.getString(2));
            }            
            
            //EMPIEZO A CARGAR LA TABLA CON EL PROVEEDOR QUE TIENE POR DEFECTO EL ARTICULO
            DefaultTableModel modelo=(DefaultTableModel)tablaReferenciaArticulo.getModel(); //CREO EL MODELO DE LA TABLA
            modelo.setNumRows(0);
            modelo.addRow( new Object [] {null,null,null}); //PONGO LA CANTDAD DE COLUMNAS QUE VA A TENER LA TABLA
            
            tablaReferenciaArticulo.setRowSorter(new TableRowSorter(modelo)); //PARA QUE LAS TABLA SE PUEDAN ORDENAR AL TOCAR LA CABECERA
           
            /////////////EN ESTA PORCION LLENO LA PRIMERA COLUMNA DE LA TABLA REFERENCIA PROVEEDOR CON LOS DATOS DEL ARTICULO
            int i=0;        
            tablaReferenciaArticulo.setValueAt(txtArticuloReferencia.getText(),i,0);
                    
            String prov = comboProveedor.getSelectedItem().toString();
            if(prov.equals("SELECCIONE PROVEEDOR")){
                prov="";
            }           
            tablaReferenciaArticulo.setValueAt(prov,i,1);
            tablaReferenciaArticulo.setValueAt(txtPrecioCostoIva.getText(),i,2);
            i++; //AVANZO A LA PROXIMA FILA
            ////////////////////
            
            ResultSet  res3=consulta.executeQuery("select * from articulo_referencia where cod_articulo='"+txtCodigoReferencia.getText()+"'");
            
            while (res3.next()){
               modelo.addRow( new Object [] {null,null,null}); //AGREGO OTRA FILA
               
               //MUESTRO EL NOMBRE DEL ARTICULO
               ResultSet  res33= consulta2.executeQuery("select referencia from articulo where cod_articulo='"+res3.getString(2)+"'");
               String nombreArticulo="";      
               while(res33.next()){
                  nombreArticulo=res33.getString(1);//NOMBRE DEL ARTICULO EN LA BD
              }
              tablaReferenciaArticulo.setValueAt(nombreArticulo,i,0); //NOMBRE DEL ARTICULO
             
               //MUESTRO EL PROVEEDOR
               ResultSet  res22= consulta3.executeQuery("select * from proveedor where cod_proveedor='"+res3.getString(3)+"'");
               String nombreProveedor="";      
               while(res22.next()){
                  nombreProveedor=res22.getString(2); //NOMBRE DEL PROVEEDOR EN LA BD
              }                         
              tablaReferenciaArticulo.setValueAt(nombreProveedor,i,1); //NOMBRE DEL PROVEEDOR
              
              //MUESTRO EL VALOR BRUTO
              tablaReferenciaArticulo.setValueAt(res3.getString(4),i,2);     
              i++;
            }
            
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        
    }
    
    public void eliminarReferenciaProveedor(String proveedor,String referencia){      //CUANDO QUIERO ELIMINAR UN PROVEEDOR DEL ARTICULO
        try{
            Connection conn=conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            
            //MUESTRO EL PROVEEDOR
            ResultSet  res22= consulta.executeQuery("select cod_proveedor from proveedor where nombre_firma='"+proveedor+"'");
            String codigoProveedor="";      
            while(res22.next()){
               codigoProveedor=res22.getString(1);
            } 
            
            //MUESTRO EL articulo
            ResultSet  res44= consulta.executeQuery("select cod_articulo from articulo where referencia='"+referencia+"'");
            String codigoArticulo="";      
            while(res44.next()){
               codigoArticulo=res44.getString(1);
            }

            int resp=consulta.executeUpdate("DELETE FROM articulo_referencia WHERE proveedor='"+codigoProveedor+"' AND cod_articulo='"+codigoArticulo+"'");
            
            if(resp>0){
                JOptionPane.showMessageDialog(null,"El proveedor fue eliminado correctamente") ;
                cargarReferenciaProveedor();
            }else{
                JOptionPane.showMessageDialog(null,"No se pudo eliminar el proveedor") ;
            }
         
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
    }
        
    public void MostrarArticulos(){   //CARGO LA TABLA ARTICULOS 
        TableColumn  column = null; 
        
        //Render3 r3= new Render3(); //EL RENER ES PARA LOS COLORES (AMARILLO, ROJO) SEGUN DISPONIBILIDAD DE STOCK (CONSULTAR CLASE Render3.java)
        //tabla.setDefaultRenderer(Object.class,r3);
            
        //SETEO EL TAMAÑO DE TODAS LAS COLUMNAS MANUALMENTE
        tabla.getColumnModel().getColumn(0).setMaxWidth(180);
        tabla.getColumnModel().getColumn(0).setMinWidth(180);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(180);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(180);
        
        tabla.getColumnModel().getColumn(1).setMaxWidth(420);
        tabla.getColumnModel().getColumn(1).setMinWidth(420);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(420);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(420);
        
        tabla.getColumnModel().getColumn(2).setMaxWidth(200);
        tabla.getColumnModel().getColumn(2).setMinWidth(200);
        tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(200);
        tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(200);

        tabla.getColumnModel().getColumn(3).setMaxWidth(200);
        tabla.getColumnModel().getColumn(3).setMinWidth(200);
        tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(200);
        tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(200);
        
        tabla.getColumnModel().getColumn(4).setMaxWidth(160);
        tabla.getColumnModel().getColumn(4).setMinWidth(160);
        tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(160);
        tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(160);
        
        tabla.getColumnModel().getColumn(5).setMaxWidth(120);
        tabla.getColumnModel().getColumn(5).setMinWidth(120);
        tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(120);
        tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(120);
        
        tabla.getColumnModel().getColumn(6).setMaxWidth(120);
        tabla.getColumnModel().getColumn(6).setMinWidth(120);
        tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(120);
        tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(120);
        
        tabla.getColumnModel().getColumn(7).setMaxWidth(230);
        tabla.getColumnModel().getColumn(7).setMinWidth(230);
        tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(230);
        tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(230);
        
        tabla.getColumnModel().getColumn(8).setMaxWidth(210);
        tabla.getColumnModel().getColumn(8).setMinWidth(210);
        tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(210);
        tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(210);
        
        tabla.getColumnModel().getColumn(9).setMaxWidth(200);
        tabla.getColumnModel().getColumn(9).setMinWidth(200);
        tabla.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(200);
        tabla.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(200);
        
        tabla.getColumnModel().getColumn(10).setMaxWidth(0);
        tabla.getColumnModel().getColumn(10).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(0);
        
        tabla.getColumnModel().getColumn(11).setMaxWidth(0);
        tabla.getColumnModel().getColumn(11).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(11).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(11).setMaxWidth(0);
        
        tabla.getColumnModel().getColumn(12).setMaxWidth(0);
        tabla.getColumnModel().getColumn(12).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(12).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(12).setMaxWidth(0);
        
        tabla.getColumnModel().getColumn(13).setMaxWidth(200);
        tabla.getColumnModel().getColumn(13).setMinWidth(200);
        tabla.getTableHeader().getColumnModel().getColumn(13).setMaxWidth(200);
        tabla.getTableHeader().getColumnModel().getColumn(13).setMaxWidth(200);
        
        tabla.getColumnModel().getColumn(14).setMaxWidth(0);
        tabla.getColumnModel().getColumn(14).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(14).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(14).setMaxWidth(0);
        
        tabla.getColumnModel().getColumn(15).setMaxWidth(0);
        tabla.getColumnModel().getColumn(15).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(15).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(15).setMaxWidth(0);
        
        
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //LE DIGO QUE LA TABLA NO SE PUEDA AUTOAJUSTAR
        tabla.doLayout();
        
         try{
            Connection conn=conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            Statement consulta2=conn.createStatement();
            Statement consulta3=conn.createStatement();
            
            ResultSet r= consulta.executeQuery("select * from articulo order by referencia");
            int i=0,j=0;

            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            modelo.setNumRows(0);
            
            tabla.setRowSorter(new TableRowSorter(modelo)); //PARA ORDENAR LAS FILAS
            
            while(r.next()){
                if(r.getString(7).equals("ACTIVO")){
                    modelo.addRow( new Object [] {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null});
                    tabla.setValueAt(r.getString(1),i,0); //codigo_articulo
                    tabla.setValueAt(r.getString(2),i,1);//descripcion
                    tabla.setValueAt(r.getString(8),i,2);// marca
                    tabla.setValueAt(r.getString(10),i,3);// codigo_provisorio
                    tabla.setValueAt(r.getString(3),i,4);//stock
                    tabla.setValueAt(r.getString(6),i,5);//precio_COSTO
                    tabla.setValueAt(r.getString(12),i,6);//precio_VENTA_CON_IVA
                    tabla.setValueAt(r.getString(9),i,7);//ultima_actualizacion
                    
                    tabla.setValueAt(r.getString(13),i,10);//ganancia
                    tabla.setValueAt(r.getString(14),i,11);//porcentaje ivaVenta del articulo
                    tabla.setValueAt(r.getString(5),i,12);//PRECIO_VENTA_SIN_IVA
                    tabla.setValueAt(r.getString(15),i,13);//categoria
                    tabla.setValueAt(r.getString(16),i,14);//precio_costo_sin_iva
                    tabla.setValueAt(r.getString(17),i,15);//porcentaje ivaCompra del articulo
             
                    
                    //GUARDO EL CODIGO DEL PROVEEDOR PARA QUE CUANDO CAMBIE EL NOMBRE DEL PROVEEDOR CAMBIE EN EL PRODUCTO
                    ResultSet  res22= consulta2.executeQuery("select * from proveedor where cod_proveedor='"+r.getString(4)+"'");
                    String nombreProveedor="";
                    
                    while(res22.next()){
                        nombreProveedor=res22.getString(2);
                    }
                                    
                    tabla.setValueAt(nombreProveedor,i,8);
                    
                    
                    //EN ARTICULO REFERENCIA SE GUARDAN TODOS LOS PROVEEDORES DE CADA ARTICULO
                    ResultSet  res33= consulta3.executeQuery("select * from articulo_referencia where cod_articulo='"+r.getString(1)+"'");
                    int k=0;
                    while(res33.next()){
                        k++;
                    }
                    if(k>0){
                        tabla.setValueAt("+ PROVEEDORES",i,9); //SI EL ARTICULO TIENE MAS DE UN PROVEDDOR ESCRIBO + PROVEEDORES
                    }
                    i++;
                }
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la base de datos (articulo 387)") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
            System.out.println(e);
        }
        
        //PARA LOS COLORES AMARILLO Y ROJO
        Render_Color_Articulos r= new Render_Color_Articulos(4);
        tabla.setDefaultRenderer(Object.class,r);
    }
    
    DefaultTableModel dm; //VARIABLE GLOBAL QUE USO PARA FILTAR LOS RESULTADOS
    /* Método filtro*/
    
    private void filtro2(String consulta, JTable jtableBuscar){
        dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter(dm);
        jtableBuscar.setRowSorter(tr);
        int columna=0;
        // Identificamos cual es el JRadioButton seleccionado para filtrar el
        // resultado de acuerdo a los datos de la columna elegida
        if (checkCodigo.isSelected()) {
            columna = 0;
        }else if (checkReferencia.isSelected()) {
             columna = 1;
        }else if (checkMarca.isSelected()) {
            columna = 2;
        }else if (checkCategoria.isSelected()) {
            columna = 13;
        }
        tr.setRowFilter(RowFilter.regexFilter(consulta, columna));
    }
                               
    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {                                       
       if(txt_recibe.getText().equals("1")){
          Factura_Venta.comboArticulo.setSelectedItem(referencia);
          this.setVisible(false);
       }
       if(txt_recibe.getText().equals("2")){
          Registrar_Articulos.txtCodigo.setText(codigo);
          Registrar_Articulos.txtCantidad.setText(cantidad);
          Registrar_Articulos.txtProducto.setText(referencia);
          Registrar_Articulos.txtPrecioVentaSinIva.setText(marca);
          this.setVisible(false);
       }
    }
    
    public void deshabilitar(){ //SETEO CUALES COMPONENTES MUESTRO Y CUALES NO
        panelTabla1.setVisible(false);
        panelReferencia.setVisible(false);
        panelActualizarPrecios.setVisible(false);
        jPanel1.setVisible(false);            
        Papelera.setVisible(false);
        txtCodigoArticulo.setVisible(false);
        txtUnicod.setVisible(false);
        
        txtBuscar.setText("Ingrese su búsqueda");
        txtBuscar.setForeground(Color.gray);    
        txtCodigo.setText("");
        txtCantidad.setText("");
        txtProducto.setText("");
        txtPrecioCostoIva.setText("");
        txtPrecioVentaSinIva.setText("");

        
        try{
            Connection conn =conexion.ObtenerConexion();
            Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet r= consulta1.executeQuery("select * from porcentajes");

            while(r.next()){
                comboIvaCompra.setSelectedItem(r.getString(3));//porcentaje iva compra por defecto
                comboIva.setSelectedItem(r.getString(4));//porcentaje iva venta por defecto
                txtPorcentaje.setText(r.getString(2));
            }
        }catch(Exception e){
            
        }
       
        
        txtCodigo.setEditable(false);
        txtCantidad.setEditable(false);
        txtProducto.setEditable(false);
        txtPrecioCostoIva.setEditable(false);
        txtPrecioVentaSinIva.setEditable(false);
        porcentaje.setSelected(true);
        
        btn_guardara1.setEnabled(false);
        btn_eliminara.setEnabled(false);
        btn_cancelara.setEnabled(false);
        btnNuevo.setEnabled(true);
        
        
        checkReferencia.setSelected(true);
        checkDescripcionPapelera.setSelected(true);
        
        txtArticuloReferencia.setEditable(false);
        txtCodigoReferencia.setEditable(false);
        
        txtCantidad.setEditable(false);
        
    }
    
     public void deshabilitarPrecio(){  //DESABILITO EL PANEL ACTUALIZACION DE PRECIOS
        panelElementos.setVisible(false);
        panelInfo.setVisible(true);
        
        btnAplicarPrecio.setVisible(false);
        btnCancelarPrecio.setEnabled(true);
        
        checkReferencia.setSelected(true);
        
        flechaArriba.setVisible(false);
        flechaAbajo.setVisible(false);
        
        txtDescripcion.setText("");
        txtPorcentaje.setText("");
          
        comboProveedor.setSelectedIndex(0);
    }
     
    public void habilitarPrecio(){ //HABILITO ELPANEL ACTYALIZACION DE PRECIOS
        panelElementos.setVisible(true);
        panelInfo.setVisible(false);
        
        btnAplicarPrecio.setVisible(true);
        btnAplicarPrecio.setEnabled(true);
        
        txtDescripcion.setEnabled(true); 
        txtPorcentaje.setEnabled(true);
        
        comboProveedor.setEnabled(true);
             
        flechaArriba.setVisible(false);
        flechaAbajo.setVisible(false);
    }
    
    public void verificarElementos(){ //VERIFICO LOS CHECK EN LA ACTUALIZACION DE PRECIOS
        if((checkSoloVenta.isSelected()||checkCostoYVenta.isSelected())&&(checkPorMarca.isSelected() || checkPorProveedor.isSelected()||checkPorDescripcion.isSelected()||checkProveedorYDescripcion.isSelected())&&(checkIncrementar.isSelected()||checkDecrementar.isSelected())){
            habilitarPrecio();
            todoCorrecto=1;
        }else{
            deshabilitarPrecio();
        }
    }
    
    public void mostrarFlecha(){ //FLECHASVERDE Y ROJA ACTUALIZACION PRECIOS
        if(checkIncrementar.isSelected()){
            flechaArriba.setVisible(true);
            flechaAbajo.setVisible(false);
        }else
            if(checkDecrementar.isSelected()){
                flechaArriba.setVisible(false);
                flechaAbajo.setVisible(true);
            }
    }
    
    public void nuevoPrecio(){ //INICIO LA ACTUALIZACION DE PRECIOS
        
                
        panelElementos.setVisible(false);
        comboProveedor.setSelectedIndex(0);
        txtDescripcion.setText("");
        txtPorcentaje.setText("");

        btnAplicarPrecio.setEnabled(false);
        btnAplicarPrecio.setVisible(false);
        btnCancelarPrecio.setEnabled(true);
        btnNuevoPrecio.setEnabled(true);
        
        
        FiltrarPrecios.clearSelection();
        FiltrarProductos.clearSelection();
        IncrementoDecremento.clearSelection();
        
        txtDescripcion.setEnabled(true); 
        comboProveedor.setEnabled(true);
        txtPorcentaje.setEnabled(true);
        
        checkSoloVenta.setSelected(false);
        checkCostoYVenta.setSelected(false);
        
    }
    
    public void evaluarCheck(){ //EVALUO LOS CHECK Y ARMO EL CARTEL EN LA ACTUALIZACION DE PRECIOS
        checkPrimero=""; checkSegundo=""; checkTercero="";   
        
        if(checkSoloVenta.isSelected()){
            checkPrimero=" PRECIO DE VENTA ";
        }else if(checkCostoYVenta.isSelected()){
                checkPrimero=" PRECIO DE COSTO Y PRECIO DE VENTA ";
            }
        
        if(checkPorProveedor.isSelected())
            checkSegundo= " PROVEEDOR ";
        else if(checkPorDescripcion.isSelected())
                checkSegundo= " DESCRIPCIÓN ";
              else if(checkProveedorYDescripcion.isSelected())
                    checkSegundo= " PROVEEDOR Y DESCRIPCIÓN ";
                   else if(checkPorMarca.isSelected())
                        checkSegundo= " MARCA "; 
        
        if(checkIncrementar.isSelected())
            checkTercero=" INCREMENTO ";
        else
            checkTercero=" DECREMENTO ";
    }
    
    public void nuevo(){    //BOTON NUEVO ARTICULO
        
        jPanel1.setVisible(true);
        
        try{
            Connection conn =conexion.ObtenerConexion();
            Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet r= consulta1.executeQuery("select * from porcentajes");

            while(r.next()){
                comboIvaCompra.setSelectedItem(r.getString(3));
                comboIva.setSelectedItem(r.getString(4));
                txtPorcentaje.setText(r.getString(2));
            }
        }catch(Exception e){
            
        }
        
        txtCodigo.setText("");
        txtCodigoArticulo.setText("");
        txtCodigo.requestFocus();
        txtCodigoProvisorio.setText("");
        txtUnicod.setText("");
        txtCantidad.setText("0");
        txtCantidad.setEditable(false);
        txtProducto.setText("");
        txtCostoSinIva.setText("0.00");
        txtPrecioCostoIva.setText("0.00");
        txtPrecioVentaSinIva.setText("0.00");
        txtPrecioVentaConIva.setText("0.00");
        txtMarca.setText("");
        txtAgregarCantidad.setText("");

        txtCodigo.setEditable(true);
        txtProducto.setEditable(true);
        txtPrecioCostoIva.setEditable(true);
        txtPrecioVentaSinIva.setEditable(true);
      
        btn_guardara1.setEnabled(true);
        btn_eliminara.setEnabled(true);
        btn_cancelara.setEnabled(true);
        btnNuevo.setEnabled(true);
        
        comboProveedor.setSelectedIndex(0);     
    }
    
     DefaultTableModel modelo2 = new DefaultTableModel(); //ES PARA LA TABLA PAPELERA
     private boolean[] editable = {true,false,false,false,true,false,false,false,false,false,false,false,false};
     
     public void MostrarArticulosPapelera(){
        Object[] datos= new Object[5]; //LE DIGO QUE TIENE 5 COLUMNAS
        try{       
            Connection conn=conexion.ObtenerConexion();
            Statement consulta=conn.createStatement();
            Statement consulta2=conn.createStatement();
            
            ResultSet r2= consulta.executeQuery("select * from  articulo order by referencia");
            int i=0,j=0;

            Render3 r= new Render3(); // PARA LOS COLORES
             
            //MUESTRO LOS ART EN LA PAPELERA
            tablaPapelera.setDefaultRenderer(Object.class,r);
            DefaultTableModel modelo = new DefaultTableModel(new String[]{"SELECCIONAR","CODIGO", "      DESCRIPCION      ", "MARCA","STOCK"}, 0) {   
                Class[] types = new Class[]{
                    java.lang.Boolean.class,java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                };

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                 public boolean isCellEditable(int row, int column){
                     return editable[column];
                }
            };
                    
            if(seleccionarTodos==1){ //SI APRETO EL BOTON SELECCIONAR TODOS, SETEO TODOS LOS CHHECK EN HABILITADOS
               while(r2.next()){
                    if(r2.getString(7).equals("INACTIVO")){
                        datos[0]=true;
                        datos[1]=r2.getObject(1);
                        datos[2]=r2.getObject(2);
                        datos[3]=r2.getObject(8);    
                        datos[4]=r2.getObject(3);  
                        
                        modelo.addRow(datos);
                    }      
                } 
                seleccionarTodos=0;
            }else{
                while(r2.next()){ //DESABILITO TODOS LOS CHECK
                    if(r2.getString(7).equals("INACTIVO")){
                        datos[0]=false;
                        datos[1]=r2.getObject(1);
                        datos[2]=r2.getObject(2);
                        datos[3]=r2.getObject(8);
                        datos[4]=r2.getObject(3);
                        modelo.addRow(datos);
                    }     
                }
            }
            tablaPapelera.setModel(modelo);
            
            r2.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Ocurrio un error en la base de datos (pepelera articulos 701)") ;
        }      
    }
     
    public void restaurarArticulo(){  //restauro los art de la papelera
        try{     
            Connection conn=conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            contador=0;
            for(int i=0;i<tablaPapelera.getRowCount();i++){  
                String pago2= tablaPapelera.getValueAt(i,0).toString();
                if(pago2.equals("true")){ 
                   consulta.executeUpdate("UPDATE articulo SET estado='ACTIVO' where cod_articulo='"+tablaPapelera.getValueAt(i,1).toString()+"'");
                   contador++;
                }
            }
            
            if (contador==1 && TodosSeleccionados==false){
                JOptionPane.showMessageDialog(null,"EL ARTICULO SE RESTAURO");
            }else{
                if (contador>=1 && TodosSeleccionados==false){
                    JOptionPane.showMessageDialog(null,"LOS ARTICULOS SE RESTAURARON");
                }
                 else{
                    if (contador==1 && TodosSeleccionados==true){
                        JOptionPane.showMessageDialog(null,"EL ARTICULO SE RESTAURO");
                    }else{
                        if (contador>=1 && TodosSeleccionados==true){
                            JOptionPane.showMessageDialog(null,"LOS ARTICULOS SE RESTAURARON");
                        }
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarArticulosPapelera();
        MostrarArticulos();
    }
    
     public void eliminarArticulo(){   //ELIMINO LOS ARTICULOS SELECCIONADOS EN LA PAPELERA   
        try{
            Connection conn=conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            contador=0;
            for(int i=0;i<tablaPapelera.getRowCount();i++){  
                String pago2= tablaPapelera.getValueAt(i,0).toString();
                
                if(pago2.equals("true")){ 
                    consulta.executeUpdate("DELETE FROM articulo WHERE cod_articulo='"+tablaPapelera.getValueAt(i,1).toString()+"'");
                    contador++;
                }
            }
            
            if (contador==1 && TodosSeleccionados==false){
                JOptionPane.showMessageDialog(null,"EL ARTICULO FUE ELIMINADO DEL SISTEMA");
            }else{
                if (contador>=1 && TodosSeleccionados==false){
                    JOptionPane.showMessageDialog(null,"LOS ARTICULOS FUERON ELIMINADOS DEL SISTEMA");
                }
                 else{
                    if (contador==1 && TodosSeleccionados==true){
                        JOptionPane.showMessageDialog(null,"EL ARTICULO FUE ELIMINADO DEL SISTEMA");
                    }else{
                        if (contador>=1 && TodosSeleccionados==true){
                            JOptionPane.showMessageDialog(null,"LOS ARTICULOS FUERON ELIMINADOS DEL SISTEMA");
                        }
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarArticulosPapelera();
        MostrarArticulos();
    }
    
     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FiltrarResultados = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItemBorrar = new javax.swing.JMenuItem();
        Papelera = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPapelera = new javax.swing.JTable();
        checkProveedorPapelera = new javax.swing.JRadioButton();
        checkDescripcionPapelera = new javax.swing.JRadioButton();
        checkCodigoPapelera = new javax.swing.JRadioButton();
        txtBuscarPapelera = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        FiltrarResultadosPapelera = new javax.swing.ButtonGroup();
        AdvertenciaExcel = new javax.swing.JDialog();
        jLabel32 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        FiltrarPrecios = new javax.swing.ButtonGroup();
        FiltrarProductos = new javax.swing.ButtonGroup();
        IncrementoDecremento = new javax.swing.ButtonGroup();
        popMenuEliminarProvReferencia = new javax.swing.JPopupMenu();
        eliminarProveedorReferencia = new javax.swing.JMenuItem();
        FiltrarCostoNeto = new javax.swing.ButtonGroup();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        txtCodigo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPrecioVentaSinIva = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtPrecioCostoIva = new javax.swing.JTextField();
        txtPorcentaje = new javax.swing.JTextField();
        porcentaje = new javax.swing.JRadioButton();
        comboProveedor = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        txtCodigoProvisorio = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txtCodigoArticulo = new javax.swing.JTextField();
        txtUnicod = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txtAgregarCantidad = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txtPrecioVentaConIva = new javax.swing.JTextField();
        comboIva = new javax.swing.JComboBox();
        jLabel51 = new javax.swing.JLabel();
        comboCategoria = new javax.swing.JComboBox();
        txtCostoSinIva = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        comboIvaCompra = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        btn_cancelara = new javax.swing.JButton();
        btn_guardara1 = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btn_eliminara = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        panelActualizarPrecios = new javax.swing.JPanel();
        checkPorProveedor = new javax.swing.JRadioButton();
        checkPorDescripcion = new javax.swing.JRadioButton();
        checkProveedorYDescripcion = new javax.swing.JRadioButton();
        checkDecrementar = new javax.swing.JRadioButton();
        checkCostoYVenta = new javax.swing.JRadioButton();
        panelElementos = new javax.swing.JPanel();
        comboProveedor1 = new javax.swing.JComboBox();
        labelDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        txtPorcentajeActualizable = new javax.swing.JTextField();
        labelPorcentaje = new javax.swing.JLabel();
        labelPorcentaje1 = new javax.swing.JLabel();
        flechaArriba = new javax.swing.JLabel();
        flechaAbajo = new javax.swing.JLabel();
        labelProveedor = new javax.swing.JLabel();
        txtPorMarca = new javax.swing.JTextField();
        labelMarca1 = new javax.swing.JLabel();
        btnCancelarPrecio = new javax.swing.JButton();
        btnAplicarPrecio = new javax.swing.JButton();
        btnNuevoPrecio = new javax.swing.JButton();
        panelInfo = new javax.swing.JPanel();
        labelInfo1 = new javax.swing.JLabel();
        labelInfo2 = new javax.swing.JLabel();
        checkPorMarca = new javax.swing.JRadioButton();
        jLabel52 = new javax.swing.JLabel();
        checkSoloVenta = new javax.swing.JRadioButton();
        checkIncrementar = new javax.swing.JRadioButton();
        btnActualizarPrecios = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        panelTablas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        checkMarca = new javax.swing.JRadioButton();
        checkReferencia = new javax.swing.JRadioButton();
        checkCodigo = new javax.swing.JRadioButton();
        checkCategoria = new javax.swing.JRadioButton();
        panelTabla1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        panelReferencia = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaReferenciaArticulo = new javax.swing.JTable();
        comboReferenciaProveedor = new javax.swing.JComboBox();
        txtPrecioReferencia = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtArticuloReferencia = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        btnGuardarReferenciaArticulo = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        txtCodigoReferencia = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        btnImportar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenuItemBorrar.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItemBorrar.setText("Mover a la papelera");
        jMenuItemBorrar.setName(""); // NOI18N
        jMenuItemBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemBorrar);
        jMenuItemBorrar.getAccessibleContext().setAccessibleName("");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaPapelera.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tablaPapelera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaPapelera.setRowHeight(25);
        tablaPapelera.setRowMargin(4);
        tablaPapelera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPapeleraMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaPapelera);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 930, 360));

        FiltrarResultadosPapelera.add(checkProveedorPapelera);
        checkProveedorPapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkProveedorPapelera.setText("Proveedor");
        checkProveedorPapelera.setOpaque(false);
        checkProveedorPapelera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkProveedorPapeleraActionPerformed(evt);
            }
        });
        jPanel2.add(checkProveedorPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, -1, -1));

        FiltrarResultadosPapelera.add(checkDescripcionPapelera);
        checkDescripcionPapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkDescripcionPapelera.setText("Descripción");
        checkDescripcionPapelera.setOpaque(false);
        jPanel2.add(checkDescripcionPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, -1, -1));

        FiltrarResultadosPapelera.add(checkCodigoPapelera);
        checkCodigoPapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCodigoPapelera.setText("Código");
        checkCodigoPapelera.setOpaque(false);
        checkCodigoPapelera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCodigoPapeleraActionPerformed(evt);
            }
        });
        jPanel2.add(checkCodigoPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, -1, -1));

        txtBuscarPapelera.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtBuscarPapelera.setBorder(null);
        txtBuscarPapelera.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarPapeleraFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarPapeleraFocusLost(evt);
            }
        });
        txtBuscarPapelera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarPapeleraActionPerformed(evt);
            }
        });
        txtBuscarPapelera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarPapeleraKeyReleased(evt);
            }
        });
        jPanel2.add(txtBuscarPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, 280, 37));

        jLabel24.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel24.setText("PAPELERA DE RECICLAJE");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, -1, -1));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, 303, 17));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, -1, 80));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtras.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtrasHover.png"))); // NOI18N
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtrasHover.png"))); // NOI18N
        jButton2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtrasHover.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 560, 130, 102));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonRestaurar.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonRestaurar.png"))); // NOI18N
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonRestaurarHover.png"))); // NOI18N
        jButton3.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonRestaurarHover.png"))); // NOI18N
        jButton3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonRestaurarHover.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 170, 102));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonSelecTodos.png"))); // NOI18N
        jButton4.setToolTipText("");
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusPainted(false);
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonSelectTodosHover.png"))); // NOI18N
        jButton4.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonSelectTodosHover.png"))); // NOI18N
        jButton4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonSelectTodosHover.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 208, -1));

        jLabel26.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel26.setText("Eliminar");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 660, -1, -1));

        jLabel27.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel27.setText("Ir hacia atras");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 650, -1, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBorrar.png"))); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setFocusPainted(false);
        jButton5.setRequestFocusEnabled(false);
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBorrarHover.png"))); // NOI18N
        jButton5.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBorrarHover.png"))); // NOI18N
        jButton5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBorrarHover.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 570, -1, 102));

        jLabel29.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel29.setText("Restaurar");
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, -1, -1));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoPapelera.png"))); // NOI18N
        jButton7.setToolTipText("");
        jButton7.setBorder(null);
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setFocusPainted(false);
        jButton7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoPapeleraHover.png"))); // NOI18N
        jButton7.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoPapeleraHover.png"))); // NOI18N
        jButton7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoPapeleraHover.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 100));

        javax.swing.GroupLayout PapeleraLayout = new javax.swing.GroupLayout(Papelera.getContentPane());
        Papelera.getContentPane().setLayout(PapeleraLayout);
        PapeleraLayout.setHorizontalGroup(
            PapeleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 961, Short.MAX_VALUE)
        );
        PapeleraLayout.setVerticalGroup(
            PapeleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
        );

        AdvertenciaExcel.setBackground(new java.awt.Color(255, 255, 255));
        AdvertenciaExcel.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/advertenciaExcel.png"))); // NOI18N
        AdvertenciaExcel.getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 410, -1));

        jPanel3.setBackground(new java.awt.Color(255, 153, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setBackground(new java.awt.Color(255, 153, 51));
        jLabel33.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel33.setText("DE IMPORTAR LA TABLA PARA EVITAR INCONSISTENCIA EN SU BASE DE DATOS.");
        jPanel3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, 40));

        jLabel35.setFont(new java.awt.Font("Calibri", 1, 35)); // NOI18N
        jLabel35.setText("¡ ATENCIÓN !");
        jPanel3.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        jLabel36.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel36.setText("DEBERÁ RESPETAR EL ORDEN DE LAS COLUMNAS EN SU DOCUMENTO DE EXCEL. ");
        jPanel3.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, 40));

        jLabel40.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel40.setText("PARA ASEGURARCE QUE LOS DATOS SE CARGUEN CORRECTAMENTE   ");
        jPanel3.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, 40));

        jLabel54.setBackground(new java.awt.Color(255, 153, 51));
        jLabel54.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel54.setText("ADÉMAS DEBERÁ VERIFICAR QUE LOS VALORES ESTEN CORRECTOS ANTES ");
        jPanel3.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, -1, 40));

        AdvertenciaExcel.getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 180));

        jPanel4.setBackground(new java.awt.Color(51, 255, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel34.setText("↑  QUE DEBEN TENER LOS DATOS DE LOS ARTÍCULOS   ↑");
        jPanel4.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, -1, 53));

        jLabel55.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel55.setText("↑  LA IMAGEN MUESTRA LA ESTRUCTURA CORRECTA  ↑");
        jPanel4.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        AdvertenciaExcel.getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 840, 90));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jButton6.setFont(new java.awt.Font("Calibri", 1, 34)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonRestaurar.png"))); // NOI18N
        jButton6.setText("Aceptar");
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonRestaurarHover.png"))); // NOI18N
        jButton6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonRestaurarHover.png"))); // NOI18N
        jButton6.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonRestaurarHover.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(333, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(312, 312, 312))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(437, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addContainerGap())
        );

        AdvertenciaExcel.getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 830, 520));

        eliminarProveedorReferencia.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        eliminarProveedorReferencia.setText("Eliminar proveedor");
        eliminarProveedorReferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarProveedorReferenciaActionPerformed(evt);
            }
        });
        popMenuEliminarProvReferencia.add(eliminarProveedorReferencia);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage1.setBackground(new java.awt.Color(255, 255, 255));
        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoMenumar22.png"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCodigo.setBackground(new java.awt.Color(204, 204, 204));
        txtCodigo.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 320, 40));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel10.setText("Proveedor");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 100, 40));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel9.setText("Quitar S.");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, 90, 20));

        txtCantidad.setBackground(new java.awt.Color(51, 204, 0));
        txtCantidad.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidad.setBorder(null);
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });
        jPanel1.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 100, 40));

        jLabel8.setBackground(new java.awt.Color(239, 255, 239));
        jLabel8.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel8.setText("Cat");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 50, 40));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel7.setText("compra con IVA");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 140, 40));

        txtPrecioVentaSinIva.setBackground(new java.awt.Color(204, 204, 204));
        txtPrecioVentaSinIva.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtPrecioVentaSinIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioVentaSinIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioVentaSinIvaActionPerformed(evt);
            }
        });
        txtPrecioVentaSinIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioVentaSinIvaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioVentaSinIvaKeyTyped(evt);
            }
        });
        jPanel1.add(txtPrecioVentaSinIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 320, 40));

        txtProducto.setBackground(new java.awt.Color(204, 204, 204));
        txtProducto.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductoKeyReleased(evt);
            }
        });
        jPanel1.add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 320, 40));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel11.setText("venta con IVA");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 130, 40));

        txtPrecioCostoIva.setBackground(new java.awt.Color(204, 204, 204));
        txtPrecioCostoIva.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtPrecioCostoIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioCostoIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioCostoIvaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioCostoIvaKeyTyped(evt);
            }
        });
        jPanel1.add(txtPrecioCostoIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, 180, 40));

        txtPorcentaje.setBackground(new java.awt.Color(204, 204, 204));
        txtPorcentaje.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtPorcentaje.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPorcentaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPorcentajeActionPerformed(evt);
            }
        });
        txtPorcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPorcentajeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajeKeyTyped(evt);
            }
        });
        jPanel1.add(txtPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 360, 90, 40));

        porcentaje.setBackground(new java.awt.Color(204, 204, 204));
        porcentaje.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        porcentaje.setText("%");
        porcentaje.setOpaque(false);
        porcentaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porcentajeActionPerformed(evt);
            }
        });
        jPanel1.add(porcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 50, 40));

        comboProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(comboProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 250, 40));

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel19.setText("Marca");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 70, 40));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel37.setText("*");
        jPanel1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 360, 20, 40));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel38.setText("*");
        jPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 30, 40));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel39.setText("*");
        jPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 30, 40));

        jButton10.setBackground(new java.awt.Color(93, 116, 163));
        jButton10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("+ ");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 60, 40));

        txtCodigoProvisorio.setBackground(new java.awt.Color(204, 204, 204));
        txtCodigoProvisorio.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtCodigoProvisorio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoProvisorio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoProvisorioKeyTyped(evt);
            }
        });
        jPanel1.add(txtCodigoProvisorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 90, 40));

        jLabel47.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel47.setText("Código");
        jPanel1.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 80, 40));

        txtMarca.setBackground(new java.awt.Color(204, 204, 204));
        txtMarca.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtMarca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarcaActionPerformed(evt);
            }
        });
        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMarcaKeyReleased(evt);
            }
        });
        jPanel1.add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 320, 40));

        jLabel48.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel48.setText("Cód Prov");
        jPanel1.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 100, 40));

        txtCodigoArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoArticuloActionPerformed(evt);
            }
        });
        jPanel1.add(txtCodigoArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 60, -1));
        jPanel1.add(txtUnicod, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 30, 30));

        jLabel49.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel49.setText("compra sin IVA");
        jPanel1.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 140, 40));

        txtAgregarCantidad.setBackground(new java.awt.Color(204, 204, 204));
        txtAgregarCantidad.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtAgregarCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAgregarCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgregarCantidadActionPerformed(evt);
            }
        });
        txtAgregarCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAgregarCantidadKeyTyped(evt);
            }
        });
        jPanel1.add(txtAgregarCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 120, 40));

        jLabel50.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel50.setText("venta sin IVA");
        jPanel1.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 120, 40));

        txtPrecioVentaConIva.setBackground(new java.awt.Color(204, 204, 204));
        txtPrecioVentaConIva.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtPrecioVentaConIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioVentaConIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioVentaConIvaActionPerformed(evt);
            }
        });
        txtPrecioVentaConIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioVentaConIvaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioVentaConIvaKeyTyped(evt);
            }
        });
        jPanel1.add(txtPrecioVentaConIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, 180, 40));

        comboIva.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        comboIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIvaActionPerformed(evt);
            }
        });
        jPanel1.add(comboIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 460, 120, 40));

        jLabel51.setBackground(new java.awt.Color(239, 255, 239));
        jLabel51.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel51.setText("Descripción");
        jPanel1.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 120, 40));

        comboCategoria.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        comboCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(comboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 190, 40));

        txtCostoSinIva.setBackground(new java.awt.Color(204, 204, 204));
        txtCostoSinIva.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtCostoSinIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCostoSinIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostoSinIvaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoSinIvaKeyTyped(evt);
            }
        });
        jPanel1.add(txtCostoSinIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 180, 40));

        jLabel53.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel53.setText("Stock actual");
        jPanel1.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 120, 40));

        comboIvaCompra.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        comboIvaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIvaCompraActionPerformed(evt);
            }
        });
        jPanel1.add(comboIvaCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, 120, 40));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel13.setText("Añadir /");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 90, 20));

        panelImage1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 490, 510));

        btn_cancelara.setBackground(new java.awt.Color(51, 153, 255));
        btn_cancelara.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_cancelara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelar.png"))); // NOI18N
        btn_cancelara.setBorder(null);
        btn_cancelara.setBorderPainted(false);
        btn_cancelara.setContentAreaFilled(false);
        btn_cancelara.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cancelara.setFocusCycleRoot(true);
        btn_cancelara.setFocusable(false);
        btn_cancelara.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btn_cancelara.setRequestFocusEnabled(false);
        btn_cancelara.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btn_cancelara.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btn_cancelara.setVerifyInputWhenFocusTarget(false);
        btn_cancelara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelaraActionPerformed(evt);
            }
        });
        panelImage1.add(btn_cancelara, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 500, 140, 120));

        btn_guardara1.setBackground(new java.awt.Color(51, 153, 255));
        btn_guardara1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_guardara1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardar.png"))); // NOI18N
        btn_guardara1.setBorder(null);
        btn_guardara1.setBorderPainted(false);
        btn_guardara1.setContentAreaFilled(false);
        btn_guardara1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_guardara1.setFocusCycleRoot(true);
        btn_guardara1.setFocusable(false);
        btn_guardara1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btn_guardara1.setRequestFocusEnabled(false);
        btn_guardara1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btn_guardara1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btn_guardara1.setVerifyInputWhenFocusTarget(false);
        btn_guardara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardara1ActionPerformed(evt);
            }
        });
        panelImage1.add(btn_guardara1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 500, 170, 120));

        btnNuevo.setBackground(new java.awt.Color(51, 153, 255));
        btnNuevo.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnNuevo11.png"))); // NOI18N
        btnNuevo.setBorder(null);
        btnNuevo.setBorderPainted(false);
        btnNuevo.setContentAreaFilled(false);
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setFocusCycleRoot(true);
        btnNuevo.setFocusable(false);
        btnNuevo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnNuevo11.png"))); // NOI18N
        btnNuevo.setRequestFocusEnabled(false);
        btnNuevo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnNuevoHover11.png"))); // NOI18N
        btnNuevo.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnNuevoHover11.png"))); // NOI18N
        btnNuevo.setVerifyInputWhenFocusTarget(false);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        panelImage1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 500, 210, 120));

        btn_eliminara.setBackground(new java.awt.Color(51, 153, 255));
        btn_eliminara.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_eliminara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnEliminar.png"))); // NOI18N
        btn_eliminara.setBorder(null);
        btn_eliminara.setBorderPainted(false);
        btn_eliminara.setContentAreaFilled(false);
        btn_eliminara.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_eliminara.setFocusCycleRoot(true);
        btn_eliminara.setFocusable(false);
        btn_eliminara.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnEliminarHover.png"))); // NOI18N
        btn_eliminara.setRequestFocusEnabled(false);
        btn_eliminara.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnEliminarHover.png"))); // NOI18N
        btn_eliminara.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnEliminarHover.png"))); // NOI18N
        btn_eliminara.setVerifyInputWhenFocusTarget(false);
        btn_eliminara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminaraActionPerformed(evt);
            }
        });
        panelImage1.add(btn_eliminara, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 500, 150, 120));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel4.setText("Nuevo");
        panelImage1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 610, 70, -1));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel5.setText("Guardar");
        panelImage1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 610, 90, -1));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel12.setText("Precios");
        panelImage1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 630, 80, 30));

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel14.setText("Importar");
        panelImage1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 610, 90, -1));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 32)); // NOI18N
        jLabel17.setText(" ARTICULOS");
        panelImage1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 170, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoArticulosXs-01.png"))); // NOI18N
        panelImage1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 50, 50));

        jLabel23.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel23.setText("Mover");
        panelImage1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 610, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoPapelera.png"))); // NOI18N
        jButton1.setToolTipText("");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoPapeleraHover.png"))); // NOI18N
        jButton1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoPapeleraHover.png"))); // NOI18N
        jButton1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoPapeleraHover.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelImage1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 500, 90, 90));

        jLabel30.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel30.setText("de reciclaje");
        panelImage1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 610, -1, 20));

        jLabel31.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel31.setText("Papelera");
        panelImage1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 580, -1, -1));

        panelActualizarPrecios.setBackground(new java.awt.Color(255, 255, 255));
        panelActualizarPrecios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FiltrarProductos.add(checkPorProveedor);
        checkPorProveedor.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkPorProveedor.setForeground(new java.awt.Color(0, 0, 153));
        checkPorProveedor.setText("Proveedor");
        checkPorProveedor.setOpaque(false);
        checkPorProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPorProveedorActionPerformed(evt);
            }
        });
        panelActualizarPrecios.add(checkPorProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        FiltrarProductos.add(checkPorDescripcion);
        checkPorDescripcion.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkPorDescripcion.setForeground(new java.awt.Color(0, 0, 153));
        checkPorDescripcion.setText("Descripcion");
        checkPorDescripcion.setOpaque(false);
        checkPorDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPorDescripcionActionPerformed(evt);
            }
        });
        panelActualizarPrecios.add(checkPorDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, -1, -1));

        FiltrarProductos.add(checkProveedorYDescripcion);
        checkProveedorYDescripcion.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkProveedorYDescripcion.setForeground(new java.awt.Color(0, 0, 153));
        checkProveedorYDescripcion.setText("Prov y Desc");
        checkProveedorYDescripcion.setOpaque(false);
        checkProveedorYDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkProveedorYDescripcionActionPerformed(evt);
            }
        });
        panelActualizarPrecios.add(checkProveedorYDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, -1));

        IncrementoDecremento.add(checkDecrementar);
        checkDecrementar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkDecrementar.setForeground(new java.awt.Color(0, 153, 0));
        checkDecrementar.setText("Decrementar precio");
        checkDecrementar.setOpaque(false);
        checkDecrementar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDecrementarActionPerformed(evt);
            }
        });
        panelActualizarPrecios.add(checkDecrementar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        FiltrarCostoNeto.add(checkCostoYVenta);
        checkCostoYVenta.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkCostoYVenta.setForeground(new java.awt.Color(255, 0, 51));
        checkCostoYVenta.setText("precio de costo y de venta");
        checkCostoYVenta.setOpaque(false);
        checkCostoYVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCostoYVentaActionPerformed(evt);
            }
        });
        panelActualizarPrecios.add(checkCostoYVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));

        panelElementos.setBackground(new java.awt.Color(255, 255, 255));
        panelElementos.setLayout(null);

        comboProveedor1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboProveedor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProveedor1ActionPerformed(evt);
            }
        });
        panelElementos.add(comboProveedor1);
        comboProveedor1.setBounds(130, 10, 340, 40);

        labelDescripcion.setBackground(new java.awt.Color(239, 255, 239));
        labelDescripcion.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        labelDescripcion.setText("Descripción");
        panelElementos.add(labelDescripcion);
        labelDescripcion.setBounds(0, 64, 130, 30);

        txtDescripcion.setBackground(new java.awt.Color(204, 204, 204));
        txtDescripcion.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtDescripcion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });
        panelElementos.add(txtDescripcion);
        txtDescripcion.setBounds(130, 60, 340, 40);

        txtPorcentajeActualizable.setBackground(new java.awt.Color(204, 204, 204));
        txtPorcentajeActualizable.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtPorcentajeActualizable.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPorcentajeActualizable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPorcentajeActualizableActionPerformed(evt);
            }
        });
        txtPorcentajeActualizable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPorcentajeActualizableKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajeActualizableKeyTyped(evt);
            }
        });
        panelElementos.add(txtPorcentajeActualizable);
        txtPorcentajeActualizable.setBounds(130, 160, 140, 40);

        labelPorcentaje.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        labelPorcentaje.setText("%");
        panelElementos.add(labelPorcentaje);
        labelPorcentaje.setBounds(280, 160, 30, 40);

        labelPorcentaje1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        labelPorcentaje1.setText("Porcentaje");
        panelElementos.add(labelPorcentaje1);
        labelPorcentaje1.setBounds(0, 160, 120, 30);

        flechaArriba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/flecha verde.png"))); // NOI18N
        panelElementos.add(flechaArriba);
        flechaArriba.setBounds(320, 160, 20, 30);

        flechaAbajo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/flecha roja.png"))); // NOI18N
        panelElementos.add(flechaAbajo);
        flechaAbajo.setBounds(320, 160, 20, 40);

        labelProveedor.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        labelProveedor.setText("Proveedor");
        panelElementos.add(labelProveedor);
        labelProveedor.setBounds(10, 10, 120, 33);

        txtPorMarca.setBackground(new java.awt.Color(204, 204, 204));
        txtPorMarca.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtPorMarca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPorMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPorMarcaKeyReleased(evt);
            }
        });
        panelElementos.add(txtPorMarca);
        txtPorMarca.setBounds(130, 110, 340, 40);

        labelMarca1.setBackground(new java.awt.Color(239, 255, 239));
        labelMarca1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        labelMarca1.setText("Marca");
        panelElementos.add(labelMarca1);
        labelMarca1.setBounds(50, 114, 70, 30);

        panelActualizarPrecios.add(panelElementos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 480, 210));

        btnCancelarPrecio.setBackground(new java.awt.Color(93, 116, 163));
        btnCancelarPrecio.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        btnCancelarPrecio.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarPrecio.setText("Cancelar");
        btnCancelarPrecio.setBorder(null);
        btnCancelarPrecio.setBorderPainted(false);
        btnCancelarPrecio.setContentAreaFilled(false);
        btnCancelarPrecio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarPrecio.setFocusCycleRoot(true);
        btnCancelarPrecio.setFocusable(false);
        btnCancelarPrecio.setOpaque(true);
        btnCancelarPrecio.setRequestFocusEnabled(false);
        btnCancelarPrecio.setVerifyInputWhenFocusTarget(false);
        btnCancelarPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPrecioActionPerformed(evt);
            }
        });
        panelActualizarPrecios.add(btnCancelarPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 400, 130, 40));

        btnAplicarPrecio.setBackground(new java.awt.Color(93, 116, 163));
        btnAplicarPrecio.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        btnAplicarPrecio.setForeground(new java.awt.Color(255, 255, 255));
        btnAplicarPrecio.setText("Aplicar");
        btnAplicarPrecio.setBorder(null);
        btnAplicarPrecio.setBorderPainted(false);
        btnAplicarPrecio.setContentAreaFilled(false);
        btnAplicarPrecio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAplicarPrecio.setFocusCycleRoot(true);
        btnAplicarPrecio.setFocusable(false);
        btnAplicarPrecio.setOpaque(true);
        btnAplicarPrecio.setRequestFocusEnabled(false);
        btnAplicarPrecio.setVerifyInputWhenFocusTarget(false);
        btnAplicarPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAplicarPrecioActionPerformed(evt);
            }
        });
        panelActualizarPrecios.add(btnAplicarPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 130, 40));

        btnNuevoPrecio.setBackground(new java.awt.Color(93, 116, 163));
        btnNuevoPrecio.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        btnNuevoPrecio.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevoPrecio.setText("Reiniciar");
        btnNuevoPrecio.setBorder(null);
        btnNuevoPrecio.setBorderPainted(false);
        btnNuevoPrecio.setContentAreaFilled(false);
        btnNuevoPrecio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoPrecio.setFocusCycleRoot(true);
        btnNuevoPrecio.setFocusable(false);
        btnNuevoPrecio.setOpaque(true);
        btnNuevoPrecio.setRequestFocusEnabled(false);
        btnNuevoPrecio.setVerifyInputWhenFocusTarget(false);
        btnNuevoPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPrecioActionPerformed(evt);
            }
        });
        panelActualizarPrecios.add(btnNuevoPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 130, 40));

        panelInfo.setBackground(new java.awt.Color(51, 204, 0));

        labelInfo1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        labelInfo1.setForeground(new java.awt.Color(255, 255, 255));
        labelInfo1.setText("Para actualizar el precio de los productos");

        labelInfo2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        labelInfo2.setForeground(new java.awt.Color(255, 255, 255));
        labelInfo2.setText("debe seleccionar una opción de cada color");

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelInfo2, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInfo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelInfo2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelActualizarPrecios.add(panelInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 460, -1));

        FiltrarProductos.add(checkPorMarca);
        checkPorMarca.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkPorMarca.setForeground(new java.awt.Color(0, 0, 153));
        checkPorMarca.setText("Marca");
        checkPorMarca.setOpaque(false);
        checkPorMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPorMarcaActionPerformed(evt);
            }
        });
        panelActualizarPrecios.add(checkPorMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, -1));

        jLabel52.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel52.setText("ACTUALIZAR PRECIOS POR:");
        panelActualizarPrecios.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        FiltrarCostoNeto.add(checkSoloVenta);
        checkSoloVenta.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkSoloVenta.setForeground(new java.awt.Color(255, 0, 0));
        checkSoloVenta.setText("Solo precio de venta");
        checkSoloVenta.setOpaque(false);
        checkSoloVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSoloVentaActionPerformed(evt);
            }
        });
        panelActualizarPrecios.add(checkSoloVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, -1, -1));

        IncrementoDecremento.add(checkIncrementar);
        checkIncrementar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkIncrementar.setForeground(new java.awt.Color(51, 153, 0));
        checkIncrementar.setText("Incrementar precio");
        checkIncrementar.setOpaque(false);
        checkIncrementar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkIncrementarActionPerformed(evt);
            }
        });
        panelActualizarPrecios.add(checkIncrementar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, -1));

        panelImage1.add(panelActualizarPrecios, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 480, 470));

        btnActualizarPrecios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnPrecio.png"))); // NOI18N
        btnActualizarPrecios.setBorderPainted(false);
        btnActualizarPrecios.setContentAreaFilled(false);
        btnActualizarPrecios.setFocusPainted(false);
        btnActualizarPrecios.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnPrecioHover.png"))); // NOI18N
        btnActualizarPrecios.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnPrecioHover.png"))); // NOI18N
        btnActualizarPrecios.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnPrecioHover.png"))); // NOI18N
        btnActualizarPrecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPreciosActionPerformed(evt);
            }
        });
        panelImage1.add(btnActualizarPrecios, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 500, -1, 120));

        jLabel41.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel41.setText("a papelera");
        panelImage1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 630, -1, 30));

        jLabel42.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel42.setText("Actualizar");
        panelImage1.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 610, -1, -1));

        panelTablas.setBackground(new java.awt.Color(255, 255, 255));
        panelTablas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "MARCA", "COD PROVISORIO", "STOCK", "P. COSTO", "P. VENTA", "ULTIMA ACTUALIZACION", "PROVEEDOR", "OBSERVACIÓN", "ganancia", "iva", "netoConIva", "CATEGORIA", "valorBrutoSinIva", "ivaCompra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false, false, false, false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setComponentPopupMenu(jPopupMenu1);
        tabla.setRowHeight(25);
        tabla.setRowMargin(4);
        tabla.setSelectionBackground(new java.awt.Color(0, 51, 51));
        tabla.setSelectionForeground(new java.awt.Color(255, 255, 51));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        panelTablas.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 580, 390));

        txtBuscar.setFont(new java.awt.Font("Calibri", 0, 17)); // NOI18N
        txtBuscar.setBorder(null);
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFocusLost(evt);
            }
        });
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        panelTablas.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 240, 40));
        panelTablas.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 250, 10));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        panelTablas.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, -1, 80));

        FiltrarResultados.add(checkMarca);
        checkMarca.setFont(new java.awt.Font("Calibri", 1, 21)); // NOI18N
        checkMarca.setText("Marca");
        checkMarca.setOpaque(false);
        panelTablas.add(checkMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 100, -1));

        FiltrarResultados.add(checkReferencia);
        checkReferencia.setFont(new java.awt.Font("Calibri", 1, 21)); // NOI18N
        checkReferencia.setText("Descripción");
        checkReferencia.setOpaque(false);
        panelTablas.add(checkReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        FiltrarResultados.add(checkCodigo);
        checkCodigo.setFont(new java.awt.Font("Calibri", 1, 21)); // NOI18N
        checkCodigo.setText("Código");
        checkCodigo.setOpaque(false);
        checkCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCodigoActionPerformed(evt);
            }
        });
        panelTablas.add(checkCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        FiltrarResultados.add(checkCategoria);
        checkCategoria.setFont(new java.awt.Font("Calibri", 1, 21)); // NOI18N
        checkCategoria.setText("Categoria");
        checkCategoria.setOpaque(false);
        panelTablas.add(checkCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, -1, -1));

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "CODIGO", "DESCRIPCION"
            }
        ));
        jScrollPane2.setViewportView(tabla1);

        javax.swing.GroupLayout panelTabla1Layout = new javax.swing.GroupLayout(panelTabla1);
        panelTabla1.setLayout(panelTabla1Layout);
        panelTabla1Layout.setHorizontalGroup(
            panelTabla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTabla1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        panelTabla1Layout.setVerticalGroup(
            panelTabla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTabla1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelTablas.add(panelTabla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 460, 590, 40));

        panelImage1.add(panelTablas, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 580, 480));

        panelReferencia.setBackground(new java.awt.Color(255, 255, 255));
        panelReferencia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaReferenciaArticulo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ARTICULO", "PROVEEDOR", "PRECIO DE COMPRA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaReferenciaArticulo.setComponentPopupMenu(popMenuEliminarProvReferencia);
        tablaReferenciaArticulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaReferenciaArticuloMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaReferenciaArticulo);

        panelReferencia.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 480, 190));

        comboReferenciaProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboReferenciaProveedorActionPerformed(evt);
            }
        });
        panelReferencia.add(comboReferenciaProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 240, 30));

        txtPrecioReferencia.setBackground(new java.awt.Color(204, 204, 204));
        txtPrecioReferencia.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtPrecioReferencia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioReferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioReferenciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioReferenciaKeyTyped(evt);
            }
        });
        panelReferencia.add(txtPrecioReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 240, 40));

        jLabel43.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel43.setText("Precio ");
        panelReferencia.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 90, 40));

        txtArticuloReferencia.setBackground(new java.awt.Color(204, 204, 204));
        txtArticuloReferencia.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtArticuloReferencia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtArticuloReferencia.setBorder(null);
        txtArticuloReferencia.setOpaque(false);
        txtArticuloReferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtArticuloReferenciaKeyReleased(evt);
            }
        });
        panelReferencia.add(txtArticuloReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 320, 40));

        jLabel44.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel44.setText("Código");
        panelReferencia.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 90, 40));

        jLabel46.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel46.setText("Proveedor");
        panelReferencia.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 110, 30));

        btnGuardarReferenciaArticulo.setBackground(new java.awt.Color(93, 116, 163));
        btnGuardarReferenciaArticulo.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        btnGuardarReferenciaArticulo.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarReferenciaArticulo.setText("Guardar");
        btnGuardarReferenciaArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarReferenciaArticuloActionPerformed(evt);
            }
        });
        panelReferencia.add(btnGuardarReferenciaArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 160, 40));

        jButton9.setBackground(new java.awt.Color(93, 116, 163));
        jButton9.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Regresar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        panelReferencia.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 370, 160, 40));

        txtCodigoReferencia.setBackground(new java.awt.Color(204, 204, 204));
        txtCodigoReferencia.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtCodigoReferencia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoReferencia.setBorder(null);
        txtCodigoReferencia.setOpaque(false);
        txtCodigoReferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoReferenciaKeyTyped(evt);
            }
        });
        panelReferencia.add(txtCodigoReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 240, 40));

        jLabel45.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel45.setText("Articulo");
        panelReferencia.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 90, 40));

        panelImage1.add(panelReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 670, 450));

        btnImportar.setBackground(new java.awt.Color(51, 153, 255));
        btnImportar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnImportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonImportar.png"))); // NOI18N
        btnImportar.setBorder(null);
        btnImportar.setBorderPainted(false);
        btnImportar.setContentAreaFilled(false);
        btnImportar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImportar.setFocusCycleRoot(true);
        btnImportar.setFocusable(false);
        btnImportar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonImportarHover.png"))); // NOI18N
        btnImportar.setRequestFocusEnabled(false);
        btnImportar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonImportarHover.png"))); // NOI18N
        btnImportar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonImportarHover.png"))); // NOI18N
        btnImportar.setVerifyInputWhenFocusTarget(false);
        btnImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarActionPerformed(evt);
            }
        });
        panelImage1.add(btnImportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 490, 120, 140));

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel15.setText("Cancelar");
        panelImage1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 610, 90, -1));

        getContentPane().add(panelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 680));

        jMenu1.setText("Opciones");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem1.setText("Porcentajes predeterminados");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        /*char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();*/
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char c = evt.getKeyChar(); //PARA QUE SOLO ME DEJE INGRESAR NUMEROS EL TEXTFIELD
        if(c<'0'|| c>'9')
           evt.consume();// TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtPrecioVentaSinIvaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaSinIvaKeyTyped
        char []p={'1','2','3','4','5','6','7','8','9','0','.',','}; //PARA PODER INGRESAR SOLO NUMEROS O PUNTOS O COMAS
        int b=0;
        for(int i=0;i<=11;i++){
            if (p[i]==evt.getKeyChar()){
                b=1;
            }
        }
        if(b==0){evt.consume();  getToolkit().beep(); }
    }//GEN-LAST:event_txtPrecioVentaSinIvaKeyTyped

    private void txtProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoKeyReleased
      txtProducto.setText (txtProducto.getText().toUpperCase()); 
    }//GEN-LAST:event_txtProductoKeyReleased

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void checkCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCodigoActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if (FiltrarResultados.getSelection()==null) {
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

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        
        //PARA CUANDO HAGO CLICK EN UN ELEMENTO DE LA TABLA SE MUESTRE EN LOS TEXTFIELD DE LOS ARTICULOS
        Select=tabla.getSelectedRow();
        if(banderaPrecio==0){
            nuevo();
            txtCodigo.setText( tabla.getValueAt(Select,0).toString());
            txtCodigoProvisorio.setText( tabla.getValueAt(Select,3).toString());
            txtProducto.setText( tabla.getValueAt(Select,1).toString());
            txtMarca.setText( tabla.getValueAt(Select,2).toString());
            
            txtCantidad.setText( tabla.getValueAt(Select,4).toString());
            txtCantidad.setEditable(false);
            comboProveedor.setSelectedItem(tabla.getValueAt(Select,8).toString());
            txtPrecioCostoIva.setText( tabla.getValueAt(Select,5).toString());
            txtPrecioVentaSinIva.setText( tabla.getValueAt(Select,12).toString());
            txtCodigoArticulo.setText( tabla.getValueAt(Select,0).toString());
            txtPorcentaje.setText( tabla.getValueAt(Select,10).toString());
            txtCostoSinIva.setText(tabla.getValueAt(Select,14).toString());
            comboCategoria.setSelectedItem(tabla.getValueAt(Select,13).toString());
            
            String ivaVenta =tabla.getValueAt(Select,11).toString();
            
            txtPrecioVentaConIva.setText( tabla.getValueAt(Select,6).toString());
            if(ivaVenta.equals("19.00")){
                comboIva.setSelectedItem("19%");
            }else
                if(ivaVenta.equals("0.00")){
                     comboIva.setSelectedItem("0%");
                }
            
            String ivaCompra =tabla.getValueAt(Select,15).toString();
            
            if(ivaCompra.equals("19.00")){
                comboIvaCompra.setSelectedItem("19%");
            }else
                if(ivaCompra.equals("0.00")){
                    comboIvaCompra.setSelectedItem("0%");
                }
            
        }else{
          txtPorMarca.setText( tabla.getValueAt(Select,2).toString());
          txtDescripcion.setText( tabla.getValueAt(Select,1).toString());
          comboProveedor1.setSelectedItem(tabla.getValueAt(Select,8).toString());  
        } 
        preciosBaseDatos=1;
    }//GEN-LAST:event_tablaMouseClicked
 
    /////////////////////////////////////////ES PARA LA IMPORTACION DE EXCEL (ACTUALMENTE ESTA PORCION ESTA EN DESUSO)
    JFileChooser selecArchivo = new JFileChooser();  
    File archivo;
    public void AgregarFiltro(){
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
    }
    ////////////////////////////////////////
    
    public static boolean esNumerico(String cadena){
        try{
            Integer.parseInt(cadena);
                return true;
        }catch(NumberFormatException e){
                return false;
        }
    }
    
    
    
    private void txtPrecioCostoIvaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCostoIvaKeyTyped
        char []p={'1','2','3','4','5','6','7','8','9','0','.',','};
        int b=0;
        for(int i=0;i<=11;i++){
            if (p[i]==evt.getKeyChar()){
                b=1;
            }
        }
        if(b==0){
            evt.consume();  
            getToolkit().beep(); 
        }
    }//GEN-LAST:event_txtPrecioCostoIvaKeyTyped

    private void porcentajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porcentajeActionPerformed
        if(!porcentaje.isSelected()){
            txtPrecioVentaSinIva.setEditable(true);
            txtPorcentaje.setEditable(false);
            txtPorcentaje.setEnabled(false);
            txtPorcentaje.setText("0");
        }else{
            txtPorcentaje.setEditable(true);
            txtPorcentaje.setEnabled(true);
            txtPorcentaje.requestFocus();
        }
    }//GEN-LAST:event_porcentajeActionPerformed

    private void txtPorcentajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPorcentajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeActionPerformed

    private void txtPrecioCostoIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCostoIvaKeyReleased
        //ES PARA QUE A MEDIDA QUE VOY INGRESANDO EL VALOR DE LOS PRODUCTOS HAGA LOS CALCULOS Y SETEO LOS TEXTFIELD DE PRECIO NETO ETC
        
        String ivaCompra=comboIvaCompra.getSelectedItem().toString();
        Double porcentajeIvaCompra=0.0;
        if(ivaCompra.equals("19%")){
            porcentajeIvaCompra=1.19;
        }else
            if(ivaCompra.equals("0%")){
                porcentajeIvaCompra=1.00;
           }
        
        Double brutoConIva=0.0;
        
        if(!txtPrecioCostoIva.getText().equals("")){
            try{                                
                brutoConIva=(double)Integer.parseInt(txtPrecioCostoIva.getText().replace(",", "."));
            }catch (NumberFormatException e){
                brutoConIva=Double.parseDouble(txtPrecioCostoIva.getText().replace(",", "."));
            }
            
        }
        Double brutoSinIva= brutoConIva/porcentajeIvaCompra;
        brutoSinIva=Math.round(brutoSinIva * 100.0)/ 100.0;
        txtCostoSinIva.setText(String.format("%.2f", brutoSinIva).replace(",", "."));
        
        String iva=comboIva.getSelectedItem().toString();
        Double porcentajeIva=0.0;
        if(iva.equals("19%")){
            porcentajeIva=1.19;
        }else
            if(iva.equals("0%")){
                 porcentajeIva=1.00;
            }
        
        if(!porcentaje.isSelected()){
            txtPrecioVentaSinIva.setText("");
            txtPrecioVentaSinIva.setEditable(true);
            txtPorcentaje.setEditable(false);
            txtPorcentaje.setText("");
        }else{
            if(txtPorcentaje.getText().equals("")){
                txtPrecioVentaSinIva.setEditable(true);
                txtPorcentaje.setEditable(true);
                txtPorcentaje.setText("");
                txtPrecioVentaSinIva.setText("");
            }
            else{
                if(!txtPrecioCostoIva.getText().equals("") && !txtPorcentaje.getText().equals("")){
                    Double bruto=0.0,porcent=0.0;
                    String valorBruto="",porcentajee="";
                    try{                                
                        bruto=(double)Integer.parseInt(txtPrecioCostoIva.getText().replace(",", "."));
                        porcent=(double)Integer.parseInt(txtPorcentaje.getText().replace(",", "."));
                    }catch (NumberFormatException e){
                        bruto=Double.parseDouble(txtPrecioCostoIva.getText().replace(",", "."));
                        porcent=Double.parseDouble(txtPorcentaje.getText().replace(",", "."));  
                    }
                    valorBruto=(String.format("%.2f", bruto));
                    porcentajee=(String.format("%.2f", porcent));
                
                    Double precioneto = bruto + (bruto * porcent)/100;

                    String precion=(String.format("%.2f", precioneto));
                    txtPrecioVentaSinIva.setText(precion.replace(",", "."));
                    String precioConIva=(String.format("%.2f", precioneto*porcentajeIva).replace(",", "."));
                    txtPrecioVentaConIva.setText(precioConIva);
                }else{
                    txtPrecioVentaSinIva.setText("");
                    txtPrecioVentaConIva.setText("");
                }
            }
        }
    }//GEN-LAST:event_txtPrecioCostoIvaKeyReleased

    private void txtPorcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyReleased
       //PARA QUE LOS PRECIOS CAMBIEN SEGUN CAMBIO EL PORCENTAJE DE GANANCIA
       
        String iva=comboIva.getSelectedItem().toString();
        Double porcentajeIva=0.0;
        if(iva.equals("19%")){
            porcentajeIva=1.19;
        }else
            if(iva.equals("0%")){
                 porcentajeIva=1.00;
            }
        
        if(!porcentaje.isSelected()){
            txtPrecioVentaSinIva.setText("");
            txtPrecioVentaSinIva.setEditable(true);
            txtPorcentaje.setEditable(false);
            txtPorcentaje.setText("");
        }else{
            String porcentajeVenta;
            if(txtPorcentaje.getText().equals("")){
                porcentajeVenta="0.00";
            }
            else{
                porcentajeVenta=txtPorcentaje.getText().replace(",",".");
            }
                
            if(txtPrecioCostoIva.getText().equals("")){
                txtPrecioCostoIva.setText("0.00"); 
            }

            Double bruto=0.0,porcent=0.0;
            String valorBruto="",porcentajee="";
            try{                                
                bruto=(double)Integer.parseInt(txtPrecioCostoIva.getText().replace(",", "."));
                porcent=(double)Integer.parseInt(porcentajeVenta.replace(",", "."));
            }catch (NumberFormatException e){
                bruto=Double.parseDouble(txtPrecioCostoIva.getText().replace(",", "."));
                porcent=Double.parseDouble(porcentajeVenta.replace(",", "."));  
            }
            valorBruto=(String.format("%.2f", bruto));
            porcentajee=(String.format("%.2f", porcent));
                
            Double precioneto = bruto + (bruto * porcent)/100;

            String precion=(String.format("%.2f", precioneto));
            txtPrecioVentaSinIva.setText(precion.replace(",", "."));
            String precioConIva=(String.format("%.2f", precioneto*porcentajeIva).replace(",", "."));
            txtPrecioVentaConIva.setText(precioConIva);   
        }
    }//GEN-LAST:event_txtPorcentajeKeyReleased

    private void jMenuItemBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBorrarActionPerformed
        //MANDO EL ARTICULO A LA PAPELRA 
        try{
            Connection conn=conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            consulta.executeUpdate("UPDATE articulo SET estado='INACTIVO' where cod_articulo='"+txtCodigo.getText()+"'");
            JOptionPane.showMessageDialog(null,"EL ARTICULO SE MOVIÓ A LA PAPELERA DE RECICLAJE");
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarArticulos();
        nuevo();
    }//GEN-LAST:event_jMenuItemBorrarActionPerformed

    private void txtPorcentajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyTyped
        char []p={'1','2','3','4','5','6','7','8','9','0','.',','};
        int b=0;
        for(int i=0;i<=11;i++){
            if (p[i]==evt.getKeyChar()){
                b=1;
            }
        }
        if(b==0){
            evt.consume();  
            getToolkit().beep(); 
        }
    }//GEN-LAST:event_txtPorcentajeKeyTyped

    private void btn_cancelaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelaraActionPerformed
        deshabilitar();
        banderaPrecio=0;
    }//GEN-LAST:event_btn_cancelaraActionPerformed

    private void btn_guardara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardara1ActionPerformed
        /*int cantArticulos=0;
        try{     
            Connection conn =conexion.ObtenerConexion();
            Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet r= consulta1.executeQuery("select COUNT(codigo_unico) from articulo WHERE estado like 'ACTIVO'");
            while(r.next()){
              cantArticulos=Integer.parseInt(r.getString(1));
            }
        }catch(Exception e){
                                        
        }*/
     
        Double ivaCombo2=0.00;
        String total_con_iva="0.00";
        double cantidadAgregada=0.0;
        txtCodigo.setBackground(new Color(204,204,204));
        txtProducto.setBackground(new Color(204,204,204));
        txtPrecioVentaSinIva.setBackground(new Color(204,204,204));
        txtPrecioCostoIva.setBackground(new Color(204,204,204));
        
        String ultimaActualizacion;
        
        String proveedor=comboProveedor.getSelectedItem().toString();
        if(proveedor.equals("SELECCIONE PROVEEDOR")){
            proveedor="SIN PROVEEDOR";
        }
     
        if (txtCodigo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta el Codigo ","Advertencia",JOptionPane.WARNING_MESSAGE);
            txtCodigo.setBackground(Color.yellow);
            txtCodigo.requestFocus();
        }else{
            if(txtProducto.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Falta la descripcion del articulo","Advertencia",JOptionPane.WARNING_MESSAGE);
                txtProducto.setBackground(Color.yellow);
                txtProducto.requestFocus();
            }else{

                    if (txtPrecioCostoIva.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Falta el precio de costo del articulo","Advertencia",JOptionPane.WARNING_MESSAGE);
                        txtPrecioCostoIva.setBackground(Color.yellow);
                        txtPrecioCostoIva.requestFocus();
                    }else{
                        if (txtPrecioVentaSinIva.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Falta el precio de venta del articulo","Advertencia",JOptionPane.WARNING_MESSAGE);
                            txtPrecioVentaSinIva.setBackground(Color.yellow);
                            txtPrecioVentaSinIva.requestFocus();
                        }else{
                            /*if (cantArticulos>=9){
                               JOptionPane.showMessageDialog(null, "Esta es una version de prueba, para ingresar mas articulos solicita la version premium al numero +549 2302 532220","Advertencia",JOptionPane.WARNING_MESSAGE); 
                            }else{*/
                            try{     
                                Connection conn =conexion.ObtenerConexion();
                                Statement consulta = conn.createStatement();
                                Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
                                ResultSet r= consulta1.executeQuery("select cod_articulo, referencia from articulo");
                                int bandera=0,banderaDescripcion=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
                                while(r.next()){
                                    if(txtCodigo.getText().equals(r.getString(1))){//si el codigo de la tabla es igual a algun codigo de la base de datos
                                        bandera=1;
                                    }
                                    if(txtProducto.getText().equals(r.getString(2))){
                                         banderaDescripcion=1;
                                    }
                                }
                                Double bruto=0.0,neto=0.0;
                                String valorBruto="",valorNeto="";
                                try{
                                    bruto=(double)Integer.parseInt(txtPrecioCostoIva.getText().replace(",", "."));
                                    neto=(double)Integer.parseInt(txtPrecioVentaSinIva.getText().replace(",", "."));
                                }catch (NumberFormatException e){
                                    bruto=Double.parseDouble(txtPrecioCostoIva.getText().replace(",", "."));
                                    neto=Double.parseDouble(txtPrecioVentaSinIva.getText().replace(",", "."));
                                }

                                valorBruto=(String.format("%.2f", bruto));
                                valorNeto=(String.format("%.2f", neto));
                                valorBruto=valorBruto.replace(",", ".");
                                valorNeto=valorNeto.replace(",", ".");
                                //GUARDO EL CODIGO DEL PROVEEDOR PARA QUE CUANDO CAMBIE EL NOMBRE DEL PROVEEDOR CAMBIE EN EL PRODUCTO
                                ResultSet res= consulta.executeQuery("select * from proveedor where nombre_firma like '"+proveedor+"'");
                                int codigoProveedor=0;
                                    
                                while(res.next()){
                                    codigoProveedor=Integer.parseInt(res.getString(1));
                                }

                                     Calendar fecha = new GregorianCalendar();
                                    //Obtenemos el valor del año, mes, día, hora, minuto y segundo del sistema.
                                    //Usando el método get y el parámetro correspondiente.
                                    int año = fecha.get(Calendar.YEAR);
                                    int mes = fecha.get(Calendar.MONTH);
                                    int dia = fecha.get(Calendar.DAY_OF_MONTH);

                                    if((mes+1)<10 && (dia>=10)){
                                        ultimaActualizacion = (dia+"/0"+(mes+1)+"/"+año); 
                                    }else{
                                        if(((mes+1)<10 && (dia<10))){
                                              ultimaActualizacion =("0"+dia+"/0"+(mes+1)+"/"+año);
                                        }else{
                                               ultimaActualizacion = (dia+"/"+(mes+1)+"/"+año);
                                        }
                                    }
                                String codigoUnico=txtCodigoArticulo.getText();
                                
                                
                                
                                String ivaCombo=comboIva.getSelectedItem().toString();
                                if(ivaCombo.equals("19%")){
                                    ivaCombo="19.00";
                                    ivaCombo2=19.00;
                                }else       
                                    if(ivaCombo.equals("0%")){
                                        ivaCombo="0.00";
                                        ivaCombo2=0.00;
                                    }
                                
                                String ivaCompra=comboIvaCompra.getSelectedItem().toString();
                                if(ivaCompra.equals("19%")){
                                    ivaCompra="19.00";
                                }else
                                    if(ivaCompra.equals("0%")){
                                          ivaCompra="0.00";
                                    }
                                
                                total_con_iva=txtPrecioVentaConIva.getText().replace(",",".");
                                if(txtAgregarCantidad.getText().equals("")){
                                    cantidadAgregada=0.0;
                                    txtAgregarCantidad.setText("0");
                                }
                                cantidadAgregada=Double.parseDouble(txtAgregarCantidad.getText().replace(',','.'));
                               

                                double cantidadActual=Double.parseDouble(txtCantidad.getText().replace(',','.'));
                                
                                double cantidadTotal= cantidadAgregada + cantidadActual;
                                
                                String brutoSinIva= txtCostoSinIva.getText().replace(",","."); //EL VALOR BRUTO SIN IVA DEL PRODUCTO
                                if(brutoSinIva.equals("")){
                                    brutoSinIva="0.00";
                                }
                                        
                                if(bandera==0 && codigoUnico.equals("")){//Si el codigo no esta inserto una nuevo registro a la base de datos
                                    if(banderaDescripcion==0){
                                        consulta.executeUpdate("insert into articulo (cod_articulo,referencia,cantidad,proveedor,valor,valor_bruto,estado,marca,ultima_actualizacion,codigo_provisorio,total_con_iva,porcentaje_ganancia,iva,categoria,valor_bruto_sin_iva,ivaCompra,impresion_etiqueta)  values('"+txtCodigo.getText()+"','"+txtProducto.getText()+"','"+cantidadTotal+"','"+codigoProveedor+"','"+valorNeto+"','"+valorBruto+"','ACTIVO','"+txtMarca.getText()+"','"+ultimaActualizacion+"','"+txtCodigoProvisorio.getText()+"','"+total_con_iva+"','"+txtPorcentaje.getText().replace(",",".")+"','"+ivaCombo+"','"+comboCategoria.getSelectedItem().toString()+"','"+brutoSinIva+"','"+ivaCompra+"','NO')");
                                        JOptionPane.showMessageDialog(null, "Los datos han sido Guardados Correctamente");
                                        nuevo();
                                        
                                        MostrarArticulos();
                                        cargarReferenciaProveedor();
                                        panelTablas.setVisible(true);
                                        panelReferencia.setVisible(false);
                                    }else{
                                        JOptionPane.showMessageDialog(null,"Ya existe un articulo con esa descripción");
                                        txtProducto.setBackground(Color.yellow);
                                        txtProducto.requestFocus();
                                    }
                                }else if(bandera==1 && codigoUnico.equals("") ){
                                            JOptionPane.showMessageDialog(null, "El codigo ya esta siendo utilizado por otro articulo","Advertencia",JOptionPane.WARNING_MESSAGE);
                                            txtCodigo.setBackground(Color.yellow);
                                            txtCodigo.requestFocus();
                                      }else{//si el codigo esta,actualizo la informacion del producto
                                            int bandera2=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
                                            if(!txtCodigo.getText().equals(txtCodigoArticulo.getText())){
                                                Statement consulta3=conn.createStatement(); // crea una variable que se encargue del código de sql
                                                ResultSet r3= consulta3.executeQuery("select cod_articulo, referencia from articulo");
                                                while(r3.next()){
                                                    if(txtCodigo.getText().equals(r3.getString(1)))//si el codigo de la tabla es igual a algun codigo de la base de datos
                                                        bandera2=1;
                                                }
                                            }
                                    
                                            if (bandera2==0){
                                                consulta.executeUpdate("UPDATE articulo SET cod_articulo='"+txtCodigo.getText()+"', referencia='"+txtProducto.getText()+"',cantidad='"+cantidadTotal+"',proveedor='"+codigoProveedor+"',valor='"+valorNeto+"',valor_bruto='"+valorBruto+"',marca='"+txtMarca.getText()+"',ultima_actualizacion='"+ultimaActualizacion+"',codigo_provisorio='"+txtCodigoProvisorio.getText()+"',total_con_iva='"+total_con_iva+"', porcentaje_ganancia='"+txtPorcentaje.getText().replace(",",".")+"', iva='"+ivaCombo+"', categoria='"+comboCategoria.getSelectedItem().toString()+"', valor_bruto_sin_iva='"+brutoSinIva+"', ivaCompra='"+ivaCompra+"' WHERE cod_articulo='"+txtCodigoArticulo.getText()+"'");                 
                                                JOptionPane.showMessageDialog(null, "Los datos han sido Modificados Correctamente");
                                                nuevo();
                                                MostrarArticulos();
                                                panelTablas.setVisible(true);
                                                panelReferencia.setVisible(false);
                                            }else{
                                                JOptionPane.showMessageDialog(null, "El código ya esta siendo utilizado por otro articulo","Advertencia",JOptionPane.WARNING_MESSAGE);
                                                txtCodigo.setBackground(Color.yellow);
                                                txtCodigo.requestFocus();
                                            }
                                    
                                      }
                                
                            }catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null,"Error en la base de datos "+ex);
                                System.out.println(ex);
                            }
                        } 
                    //}
                }
            }
        }
    }//GEN-LAST:event_btn_guardara1ActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        jPanel1.setVisible(true);
        nuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btn_eliminaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminaraActionPerformed
        try{
            Connection conn=conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            consulta.executeUpdate("UPDATE articulo SET estado='INACTIVO' where cod_articulo='"+txtCodigo.getText()+"'");
            JOptionPane.showMessageDialog(null,"EL ARTICULO SE MOVIÓ A LA PAPELERA DE RECICLAJE");
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarArticulos();
        nuevo();
    }//GEN-LAST:event_btn_eliminaraActionPerformed

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
       if(txtBuscar.getText().equals("Ingrese su búsqueda")){
            txtBuscar.setText("");
        }
        txtBuscar.setForeground(Color.black);
    }//GEN-LAST:event_txtBuscarFocusGained

    private void txtBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusLost
        if(txtBuscar.getText().equals("")){
            txtBuscar.setText("Ingrese su búsqueda");
        }
        txtBuscar.setForeground(Color.gray);
    }//GEN-LAST:event_txtBuscarFocusLost

    private void txtPrecioVentaSinIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioVentaSinIvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioVentaSinIvaActionPerformed

    private void comboProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProveedorActionPerformed
        
    }//GEN-LAST:event_comboProveedorActionPerformed

    private void tablaPapeleraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPapeleraMouseClicked

    }//GEN-LAST:event_tablaPapeleraMouseClicked

    private void checkCodigoPapeleraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCodigoPapeleraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCodigoPapeleraActionPerformed

    private void txtBuscarPapeleraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarPapeleraFocusGained
        if(txtBuscar.getText().equals("Ingrese su búsqueda")){
            txtBuscar.setText("");
        }
        txtBuscar.setForeground(Color.black);
    }//GEN-LAST:event_txtBuscarPapeleraFocusGained

    private void txtBuscarPapeleraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarPapeleraFocusLost
        if(txtBuscar.getText().equals("")){
            txtBuscar.setText("Ingrese su búsqueda");
        }
        txtBuscar.setForeground(Color.gray);
    }//GEN-LAST:event_txtBuscarPapeleraFocusLost

    private void txtBuscarPapeleraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarPapeleraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarPapeleraActionPerformed

    private void txtBuscarPapeleraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPapeleraKeyReleased
        if (FiltrarResultadosPapelera.getSelection()==null) {
            // Si ninguno de los JRadioButtons está seleccionado, evitamos que se
            // escriba algo dentro del JTextField y mostramos un mensaje de error
            evt.consume();
            JOptionPane.showMessageDialog(this, "Debe seleccionar una opción para filtrar los resultados", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            txtBuscarPapelera.setText("");
            txtBuscarPapelera.transferFocus();
        }else{
            filtroPapelera(txtBuscarPapelera.getText().toUpperCase(), tablaPapelera);
        }
    }//GEN-LAST:event_txtBuscarPapeleraKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Papelera.setVisible(false);
        this.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    DefaultTableModel dm2;
    /* Método filtro*/
    private void filtroPapelera(String consulta, JTable jtableBuscar){  
        dm2 = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter(dm2);
        jtableBuscar.setRowSorter(tr);

        int columna=0;
        // Identificamos cual es el JRadioButton seleccionado para filtrar el
        // resultado de acuerdo a los datos de la columna elegida
        if (checkCodigoPapelera.isSelected()) {
            columna = 1;
        }else if (checkDescripcionPapelera.isSelected()) {
                columna = 2;
            }else if (checkProveedorPapelera.isSelected()) {
                    columna = 4;
            }
        tr.setRowFilter(RowFilter.regexFilter(consulta, columna));
    }    
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        restaurarArticulo();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        TodosSeleccionados=true;
        seleccionarTodos=1;
        MostrarArticulosPapelera();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        eliminarArticulo();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        MostrarArticulosPapelera();
        Papelera.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void checkProveedorPapeleraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkProveedorPapeleraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkProveedorPapeleraActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
      AdvertenciaExcel.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void checkPorProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPorProveedorActionPerformed
        //ESTOS CHECK SON DE ACTUALIZACION DE PRECIOS
        verificarElementos();
        mostrarFlecha();
        labelProveedor.setVisible(true);
        comboProveedor1.setVisible(true);
        comboProveedor1.setEnabled(true);
        comboProveedor1.setSelectedIndex(0);
        labelDescripcion.setVisible(false);
        txtDescripcion.setVisible(false);
        txtDescripcion.setEnabled(false);
        txtDescripcion.setText("");
        txtPorMarca.setText("");
        labelMarca1.setVisible(false);
        txtPorMarca.setVisible(false);
    }//GEN-LAST:event_checkPorProveedorActionPerformed

    private void checkPorDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPorDescripcionActionPerformed
        //ESTOS CHECK SON DE ACTUALIZACION DE PRECIOS
        verificarElementos();
        mostrarFlecha();
        labelProveedor.setVisible(false);
        comboProveedor1.setVisible(false);
        comboProveedor1.setEnabled(false);
        comboProveedor1.setSelectedIndex(0);
        labelDescripcion.setVisible(true);
        txtDescripcion.setVisible(true);
        txtDescripcion.setEnabled(true);
        txtDescripcion.setText("");
        txtPorMarca.setText("");
        labelMarca1.setVisible(false);
        txtPorMarca.setVisible(false);
    }//GEN-LAST:event_checkPorDescripcionActionPerformed

    private void checkProveedorYDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkProveedorYDescripcionActionPerformed
        verificarElementos();
        mostrarFlecha();
        labelProveedor.setVisible(true);
        comboProveedor1.setVisible(true);
        comboProveedor1.setEnabled(true);
        comboProveedor1.setSelectedIndex(0);
        labelDescripcion.setVisible(true);
        txtDescripcion.setVisible(true);
        txtDescripcion.setEnabled(true);
        txtDescripcion.setText("");
        txtPorMarca.setText("");
        labelMarca1.setVisible(false);
        txtPorMarca.setVisible(false);
    }//GEN-LAST:event_checkProveedorYDescripcionActionPerformed

    private void checkDecrementarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDecrementarActionPerformed
        verificarElementos();
      mostrarFlecha();
      
    }//GEN-LAST:event_checkDecrementarActionPerformed

    private void checkCostoYVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCostoYVentaActionPerformed
        verificarElementos();
       mostrarFlecha();
       
    }//GEN-LAST:event_checkCostoYVentaActionPerformed

    private void comboProveedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProveedor1ActionPerformed

    }//GEN-LAST:event_comboProveedor1ActionPerformed

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        txtDescripcion.setText (txtDescripcion.getText().toUpperCase());
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void txtPorcentajeActualizableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPorcentajeActualizableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeActualizableActionPerformed

    private void txtPorcentajeActualizableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeActualizableKeyReleased

    }//GEN-LAST:event_txtPorcentajeActualizableKeyReleased

    private void txtPorcentajeActualizableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeActualizableKeyTyped
        char []p={'1','2','3','4','5','6','7','8','9','0','.',','};
        int b=0;
        for(int i=0;i<=11;i++){
            if (p[i]==evt.getKeyChar()){
                b=1;
            }
        }
        if(b==0){
            evt.consume();
            getToolkit().beep(); 
        }
    }//GEN-LAST:event_txtPorcentajeActualizableKeyTyped

    private void btnCancelarPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPrecioActionPerformed
        panelActualizarPrecios.setVisible(false);
        deshabilitarPrecio();
        banderaPrecio=0;
    }//GEN-LAST:event_btnCancelarPrecioActionPerformed

    private void btnAplicarPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplicarPrecioActionPerformed
        //boton aplicar precio de actualizacion de precios
        String signo="",precio="",actualizar="",descripcion="",nombreProveedor="",codigoProveedor="0",porcent="",marca="";
        Double porcentajeActualizable=0.00;
        int c=0;
        evaluarCheck();
        verificarElementos();
        if(todoCorrecto==1 && !txtPorcentajeActualizable.getText().equals("")){          
            Object[] opciones ={"Aceptar","Cancelar"};
            int eleccion = JOptionPane.showOptionDialog(rootPane,"se realizara un "+checkTercero+" del "+checkPrimero+" del/los producto/s según su "+checkSegundo+". ¿Acepta esta actualización de precios?","Atención",

            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
            if(eleccion ==JOptionPane.YES_OPTION){
                
                if(checkIncrementar.isSelected()){
                    signo="+";
                }else{
                    signo="-";
                }
                
                try {
                    Connection conn =conexion.ObtenerConexion();
                    Statement consulta = conn.createStatement();

                    porcentajeActualizable=Double.parseDouble(txtPorcentajeActualizable.getText().replace(",","."));
                    nombreProveedor=comboProveedor1.getSelectedItem().toString();
                    descripcion=txtDescripcion.getText();
                    marca=txtPorMarca.getText();

                    Statement consulta2 = conn.createStatement();
                    ResultSet r2= consulta2.executeQuery("select cod_proveedor from proveedor where nombre_firma='"+nombreProveedor+"'");
                    while(r2.next()){
                        codigoProveedor=r2.getString(1);
                    }


                    if(checkPorProveedor.isSelected()){ //SI PRECIO ES VACIO ES PORQUE ESTA SELECCIONADO PRECIO BRUTO Y PRECIO NETO
                        Statement consultaArticulo = conn.createStatement();
                        ResultSet rA= consultaArticulo.executeQuery("select valor_bruto,valor,total_con_iva,porcentaje_ganancia,iva,cod_articulo,valor_bruto_sin_iva,ivaCompra from articulo where proveedor='"+codigoProveedor+"'");
                        Double valorCostoSinIva=0.0, porcentajeIvaCompra=0.0,valorCosto=0.0,valorCostoSinActualizar=0.0,netoSinIva=0.0,netoConIva=0.0,porcentajeGanancia=0.0,porcentajeIva=0.0, nuevoPorcentajeGanancia=0.0;
                        String codigoArticulo="";
                        
                        while(rA.next()){
                            valorCostoSinIva=Double.parseDouble(rA.getString(7).replace(",","."));
                            porcentajeIvaCompra=Double.parseDouble(rA.getString(8).replace(",","."));
                            valorCosto=Double.parseDouble(rA.getString(1).replace(",","."));
                            valorCostoSinActualizar=Double.parseDouble(rA.getString(1).replace(",","."));//EN EL CAS QUE SOLO QUIERA ACTUALIZAR EL PRECIO DE VENTA, PARA HACER EL CALCULO DE LA NUEVA GANANCIA
                            netoSinIva=Double.parseDouble(rA.getString(2).replace(",","."));
                            netoConIva=Double.parseDouble(rA.getString(3).replace(",","."));
                            porcentajeGanancia=Double.parseDouble(rA.getString(4).replace(",","."));
                            porcentajeIva=Double.parseDouble(rA.getString(5).replace(",","."));
                            codigoArticulo=rA.getString(6);
                            
                            if(signo.equals("+")){
                                valorCostoSinIva= valorCostoSinIva+(valorCostoSinIva*porcentajeActualizable/100);
                                valorCosto=valorCosto+(valorCosto*porcentajeActualizable/100);                      
                                netoSinIva=valorCosto+(valorCosto*porcentajeGanancia/100);
                                netoConIva=netoSinIva+(netoSinIva*porcentajeIva/100);    
                            }else{
                                valorCostoSinIva= valorCostoSinIva-(valorCostoSinIva*porcentajeActualizable/100);
                                valorCosto=valorCosto-(valorCosto*porcentajeActualizable/100);         
                                netoSinIva=valorCosto+(valorCosto*porcentajeGanancia/100);
                                netoConIva=netoSinIva+(netoSinIva*porcentajeIva/100);
                            }
                            
                            valorCostoSinIva=Math.round(valorCostoSinIva * 100.0)/ 100.0;
                            valorCosto=Math.round(valorCosto * 100.0)/ 100.0;
                            netoSinIva=Math.round(netoSinIva * 100.0)/ 100.0;
                            netoConIva=Math.round(netoConIva * 100.0)/ 100.0;
                            
                            Statement consultaArticulo11 = conn.createStatement();
                            
                            if(checkSoloVenta.isSelected()){ //SOLO ACTUALIZO EL PRECIO DE VENTA, POR LO QUE CAMBIA EL PORCENTAJE DE GANANCIA
                                   porcentajeGanancia= ((netoSinIva-valorCostoSinActualizar)/valorCostoSinActualizar)*100; //CALCULO EL NUEVO PROCENTAJE DEGANANCIA
                                   porcentajeGanancia=Math.round(porcentajeGanancia * 100.0)/ 100.0;
                                   c=consultaArticulo11.executeUpdate("UPDATE articulo SET valor="+netoSinIva+", total_con_iva="+netoConIva+", porcentaje_ganancia="+porcentajeGanancia+" WHERE cod_articulo="+codigoArticulo+"");                     
                            }else if (checkCostoYVenta.isSelected()){
                                        c=consultaArticulo11.executeUpdate("UPDATE articulo SET valor_bruto_sin_iva="+valorCostoSinIva+", valor_bruto="+valorCosto+", valor="+netoSinIva+", total_con_iva="+netoConIva+", porcentaje_ganancia="+porcentajeGanancia+" WHERE cod_articulo="+codigoArticulo+"");              
                            }
                        }
                        
                    }else if(checkPorDescripcion.isSelected()){
                        
                            Statement consultaArticulo2 = conn.createStatement();
                            ResultSet rA2= consultaArticulo2.executeQuery("select valor_bruto,valor,total_con_iva,porcentaje_ganancia,iva, cod_articulo,valor_bruto_sin_iva,ivaCompra from articulo  WHERE referencia like '%"+descripcion+"%'");
                            Double valorCostoSinIva=0.0, porcentajeIvaCompra=0.0,valorCosto=0.0,valorCostoSinActualizar=0.0,netoSinIva=0.0,netoConIva=0.0,porcentajeGanancia=0.0,porcentajeIva=0.0, nuevoPorcentajeGanancia=0.0;
                            String codigoArticulo="";
                            while(rA2.next()){
                                valorCostoSinIva=Double.parseDouble(rA2.getString(7).replace(",","."));
                                porcentajeIvaCompra=Double.parseDouble(rA2.getString(8).replace(",","."));
                                valorCosto=Double.parseDouble(rA2.getString(1).replace(",","."));
                                valorCostoSinActualizar=Double.parseDouble(rA2.getString(1).replace(",","."));//EN EL CAS QUE SOLO QUIERA ACTUALIZAR EL PRECIO DE VENTA, PARA HACER EL CALCULO DE LA NUEVA GANANCIA
                                netoSinIva=Double.parseDouble(rA2.getString(2).replace(",","."));
                                netoConIva=Double.parseDouble(rA2.getString(3).replace(",","."));
                                porcentajeGanancia=Double.parseDouble(rA2.getString(4).replace(",","."));
                                porcentajeIva=Double.parseDouble(rA2.getString(5).replace(",","."));
                                codigoArticulo=rA2.getString(6);

                                if(signo.equals("+")){
                                    valorCostoSinIva= valorCostoSinIva+(valorCostoSinIva*porcentajeActualizable/100); 
                                    valorCosto=valorCosto+(valorCosto*porcentajeActualizable/100);                                     
                                    netoSinIva=valorCosto+(valorCosto*porcentajeGanancia/100);
                                    netoConIva=netoSinIva+(netoSinIva*porcentajeIva/100);
                                }else{
                                    valorCostoSinIva= valorCostoSinIva-(valorCostoSinIva*porcentajeActualizable/100); 
                                    valorCosto=valorCosto-(valorCosto*porcentajeActualizable/100);
                                    netoSinIva=valorCosto+(valorCosto*porcentajeGanancia/100);
                                    netoConIva=netoSinIva+(netoSinIva*porcentajeIva/100);
                                }
                                
                                valorCostoSinIva=Math.round(valorCostoSinIva * 100.0)/ 100.0;
                                valorCosto=Math.round(valorCosto * 100.0)/ 100.0;
                                netoSinIva=Math.round(netoSinIva * 100.0)/ 100.0;
                                netoConIva=Math.round(netoConIva * 100.0)/ 100.0;
                                     
                                Statement consultaArticulo22 = conn.createStatement();
                                
                                if(checkSoloVenta.isSelected()){//SOLO ACTUALIZO EL PRECIO DE VENTA, POR LO QUE CAMBIA EL PORCENTAJE DE GANANCIA
                                    porcentajeGanancia= ((netoSinIva-valorCostoSinActualizar)/valorCostoSinActualizar)*100;
                                    porcentajeGanancia=Math.round(porcentajeGanancia * 100.0)/ 100.0;
                                    c=consultaArticulo22.executeUpdate("UPDATE articulo SET valor="+netoSinIva+", total_con_iva="+netoConIva+", porcentaje_ganancia="+porcentajeGanancia+" WHERE cod_articulo="+codigoArticulo+"");                 
                                }else if (checkCostoYVenta.isSelected()){
                                           c=consultaArticulo22.executeUpdate("UPDATE articulo SET valor_bruto_sin_iva="+valorCostoSinIva+", valor_bruto="+valorCosto+", valor="+netoSinIva+", total_con_iva="+netoConIva+", porcentaje_ganancia="+porcentajeGanancia+" WHERE cod_articulo="+codigoArticulo+"");               
                                       }
                            }
                            
                            }else if(checkProveedorYDescripcion.isSelected()){
     
                                        Statement consultaArticulo3 = conn.createStatement();
                                        ResultSet rA3= consultaArticulo3.executeQuery("select valor_bruto,valor,total_con_iva,porcentaje_ganancia,iva, cod_articulo,valor_bruto_sin_iva,ivaCompra  from articulo WHERE referencia like '%"+descripcion+"%' AND proveedor="+codigoProveedor+"");
                                        Double valorCostoSinIva=0.0,valorCosto=0.0,valorCostoSinActualizar=0.0,netoSinIva=0.0,netoConIva=0.0,porcentajeGanancia=0.0,porcentajeIva=0.0,porcentajeIvaCompra=0.0, nuevoPorcentajeGanancia=0.0;
                                        String codigoArticulo="";
                                        
                                        while(rA3.next()){
                                            valorCostoSinIva=Double.parseDouble(rA3.getString(7).replace(",","."));
                                            porcentajeIvaCompra=Double.parseDouble(rA3.getString(8).replace(",","."));
                                            valorCosto=Double.parseDouble(rA3.getString(1).replace(",","."));
                                            valorCostoSinActualizar=Double.parseDouble(rA3.getString(1).replace(",","."));//EN EL CAS QUE SOLO QUIERA ACTUALIZAR EL PRECIO DE VENTA, PARA HACER EL CALCULO DE LA NUEVA GANANCIA
                                            netoSinIva=Double.parseDouble(rA3.getString(2).replace(",","."));
                                            netoConIva=Double.parseDouble(rA3.getString(3).replace(",","."));
                                            porcentajeGanancia=Double.parseDouble(rA3.getString(4).replace(",","."));
                                            porcentajeIva=Double.parseDouble(rA3.getString(5).replace(",","."));
                                            codigoArticulo=rA3.getString(6);

                                            if(signo.equals("+")){
                                                valorCosto=valorCosto+(valorCosto*porcentajeActualizable/100);
                                                valorCostoSinIva= valorCostoSinIva+(valorCostoSinIva*porcentajeActualizable/100); 
                                                netoSinIva=valorCosto+(valorCosto*porcentajeGanancia/100);
                                                netoConIva=netoSinIva+(netoSinIva*porcentajeIva/100);
                                            }else{
                                                valorCosto=valorCosto-(valorCosto*porcentajeActualizable/100);
                                                valorCostoSinIva= valorCostoSinIva-(valorCostoSinIva*porcentajeActualizable/100); 
                                                netoSinIva=valorCosto+(valorCosto*porcentajeGanancia/100);
                                                netoConIva=netoSinIva+(netoSinIva*porcentajeIva/100);
                                            }
                                            
                                            valorCostoSinIva=Math.round(valorCostoSinIva * 100.0)/ 100.0;
                                            valorCosto=Math.round(valorCosto * 100.0)/ 100.0;
                                            netoSinIva=Math.round(netoSinIva * 100.0)/ 100.0;
                                            netoConIva=Math.round(netoConIva * 100.0)/ 100.0;
                                            
                                            Statement consultaArticulo33 = conn.createStatement();
                                            
                                            if(checkSoloVenta.isSelected()){//SOLO ACTUALIZO EL PRECIO DE VENTA, POR LO QUE CAMBIA EL PORCENTAJE DE GANANCIA
                                                porcentajeGanancia= ((netoSinIva-valorCostoSinActualizar)/valorCostoSinActualizar)*100; //CALCULOEL NUEVO PROCENTAJE DEGANANCIA
                                                porcentajeGanancia=Math.round(porcentajeGanancia * 100.0)/ 100.0;
                                                c=consultaArticulo33.executeUpdate("UPDATE articulo SET valor="+netoSinIva+", total_con_iva="+netoConIva+", porcentaje_ganancia="+porcentajeGanancia+" WHERE cod_articulo="+codigoArticulo+"");    
                                            }else if (checkCostoYVenta.isSelected()){
                                                     c=consultaArticulo33.executeUpdate("UPDATE articulo SET valor_bruto_sin_iva="+valorCostoSinIva+",valor_bruto="+valorCosto+", valor="+netoSinIva+", total_con_iva="+netoConIva+", porcentaje_ganancia="+porcentajeGanancia+" WHERE cod_articulo="+codigoArticulo+"");
                                                  }       
                                        }
                                    }else if(checkPorMarca.isSelected()){
                           
                                            Statement consultaArticulo4 = conn.createStatement();
                                            ResultSet rA4= consultaArticulo4.executeQuery("select valor_bruto,valor,total_con_iva,porcentaje_ganancia,iva, cod_articulo,valor_bruto_sin_iva,ivaCompra from articulo  WHERE marca like '"+marca+"'");
                                            Double valorCostoSinActualizar=0.0,valorCostoSinIva=0.0,valorCosto=0.0,netoSinIva=0.0,netoConIva=0.0,porcentajeGanancia=0.0,porcentajeIva=0.0,porcentajeIvaCompra=0.0, nuevoPorcentajeGanancia=0.0;
                                            String codigoArticulo="";
                                            while(rA4.next()){
                                                 valorCostoSinIva=Double.parseDouble(rA4.getString(7).replace(",","."));
                                                 porcentajeIvaCompra=Double.parseDouble(rA4.getString(8).replace(",","."));
                                                 valorCosto=Double.parseDouble(rA4.getString(1).replace(",","."));
                                                 valorCostoSinActualizar=Double.parseDouble(rA4.getString(1).replace(",","."));//EN EL CAS QUE SOLO QUIERA ACTUALIZAR EL PRECIO DE VENTA, PARA HACER EL CALCULO DE LA NUEVA GANANCIA
                                                 netoSinIva=Double.parseDouble(rA4.getString(2).replace(",","."));
                                                 netoConIva=Double.parseDouble(rA4.getString(3).replace(",","."));
                                                 porcentajeGanancia=Double.parseDouble(rA4.getString(4).replace(",","."));
                                                 porcentajeIva=Double.parseDouble(rA4.getString(5).replace(",","."));
                                                 codigoArticulo=rA4.getString(6);

                                                 if(signo.equals("+")){
                                                     valorCostoSinIva= valorCostoSinIva+(valorCostoSinIva*porcentajeActualizable/100);
                                                     valorCosto=valorCosto+(valorCosto*porcentajeActualizable/100);
                                                     netoSinIva=valorCosto+(valorCosto*porcentajeGanancia/100);
                                                     netoConIva=netoSinIva+(netoSinIva*porcentajeIva/100);
                                                 }else{
                                                     valorCostoSinIva= valorCostoSinIva-(valorCostoSinIva*porcentajeActualizable/100);
                                                     valorCosto=valorCosto-(valorCosto*porcentajeActualizable/100);
                                                     netoSinIva=valorCosto+(valorCosto*porcentajeGanancia/100);
                                                     netoConIva=netoSinIva+(netoSinIva*porcentajeIva/100);
                                                 }
                                                 
                                                 valorCostoSinIva=Math.round(valorCostoSinIva * 100.0)/ 100.0;
                                                 valorCosto=Math.round(valorCosto * 100.0)/ 100.0;
                                                 netoSinIva=Math.round(netoSinIva * 100.0)/ 100.0;
                                                 netoConIva=Math.round(netoConIva * 100.0)/ 100.0;
                                                 
                                                 Statement consultaArticulo44 = conn.createStatement();
                                                 
                                                 if(checkSoloVenta.isSelected()){//SOLO ACTUALIZO EL PRECIO DE VENTA, POR LO QUE CAMBIA EL PORCENTAJE DE GANANCIA
                                                     porcentajeGanancia= ((netoSinIva-valorCostoSinActualizar)/valorCostoSinActualizar)*100; //CALCULOEL NUEVO PROCENTAJE DEGANANCIA
                                                     porcentajeGanancia=Math.round(porcentajeGanancia * 100.0)/ 100.0;
                                                     c=consultaArticulo44.executeUpdate("UPDATE articulo SET valor="+netoSinIva+", total_con_iva="+netoConIva+", porcentaje_ganancia="+porcentajeGanancia+" WHERE cod_articulo="+codigoArticulo+"");
                                                 }else if (checkCostoYVenta.isSelected()){
                                                            c=consultaArticulo44.executeUpdate("UPDATE articulo SET valor_bruto_sin_iva="+valorCostoSinIva+",valor_bruto="+valorCosto+", valor="+netoSinIva+", total_con_iva="+netoConIva+", porcentaje_ganancia="+porcentajeGanancia+" WHERE cod_articulo="+codigoArticulo+"");
                                                 }          
                                            }
                                    }

                    if (c>0){
                        JOptionPane.showMessageDialog(null, "Los precios se actualizaron correctamente");
                        txtDescripcion.setText("");
                        txtPorcentajeActualizable.setText("");
                        comboProveedor1.setSelectedIndex(0);
                    }else{
                        JOptionPane.showMessageDialog(null, "Los datos ingresados no coinciden con ningún producto o falta ingresar ingresar algun dato");
                    }
                    
                    MostrarArticulos();

                }catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                    System.out.println(ex);
                }
            }else{ 
                
            }
        }else{
            JOptionPane.showMessageDialog(null, "Los datos ingresados no coinciden con ningún producto o falta ingresar ingresar algun dato");
        }
    }//GEN-LAST:event_btnAplicarPrecioActionPerformed

    private void btnNuevoPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPrecioActionPerformed
        nuevoPrecio();
    }//GEN-LAST:event_btnNuevoPrecioActionPerformed

    private void btnActualizarPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPreciosActionPerformed
        panelActualizarPrecios.setVisible(true);   
        panelTablas.setVisible(true);
        panelReferencia.setVisible(false);
        nuevoPrecio();
        banderaPrecio=1;
        jPanel1.setVisible(false);
    }//GEN-LAST:event_btnActualizarPreciosActionPerformed

    private void comboReferenciaProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboReferenciaProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboReferenciaProveedorActionPerformed

    private void txtPrecioReferenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioReferenciaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioReferenciaKeyReleased

    private void txtPrecioReferenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioReferenciaKeyTyped
        char []p={'1','2','3','4','5','6','7','8','9','0','.',','};
        int b=0;
        for(int i=0;i<=11;i++){
            if (p[i]==evt.getKeyChar()){
                b=1;
            }
        }
        if(b==0){
            evt.consume();  
            getToolkit().beep(); 
        }
    }//GEN-LAST:event_txtPrecioReferenciaKeyTyped

    private void txtArticuloReferenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArticuloReferenciaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtArticuloReferenciaKeyReleased

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        panelTablas.setVisible(false);
        panelReferencia.setVisible(true);
        cargarReferenciaProveedor();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        panelTablas.setVisible(true);
        panelReferencia.setVisible(false);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txtCodigoReferenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoReferenciaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoReferenciaKeyTyped

    private void btnGuardarReferenciaArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarReferenciaArticuloActionPerformed
        //BOTON PARA GUARDAR UN NUEVO PROVEEDOR DEL ARTICULO
        int banderaProvArti=0,banderaProveedor=0;
        String proveedor=comboReferenciaProveedor.getSelectedItem().toString();
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta5=conn.createStatement(); // crea una variable que se encargue del código de sql
            
            //MUESTRO EL PROVEEDOR
            ResultSet  res22= consulta5.executeQuery("select cod_proveedor from proveedor where nombre_firma='"+proveedor+"'");
            String codigoProveedor="";      
            while(res22.next()){
               codigoProveedor=res22.getString(1);
            } 
            
            ResultSet r= consulta5.executeQuery("select proveedor from articulo_referencia where cod_articulo='"+txtCodigoReferencia.getText()+"'");
            
            banderaProveedor=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
            while(r.next()){
                if(codigoProveedor.equals(r.getString(1))){//si el codigo de la tabla es igual a algun codigo de la base de datos
                    banderaProveedor=1;             
                }
            }
            ResultSet r2= consulta5.executeQuery("select proveedor from articulo where cod_articulo='"+txtCodigoReferencia.getText()+"'");
            banderaProvArti=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
            while(r2.next()){
                if(codigoProveedor.equals(r2.getString(1))){//si el codigo de la tabla es igual a algun codigo de la base de datos
                    banderaProvArti=1;             
                }
            }
            
                
         }catch(SQLException e){
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null,"Error en el SQL") ;
          }
        
        txtPrecioReferencia.setBackground(new Color(204,204,204));
       
        if(proveedor.equals("SELECCIONE PROVEEDOR")){
            proveedor="";
        }
        
        if(proveedor.equals("")){
            JOptionPane.showMessageDialog(null, "Falta seleccionar el proveedor","Advertencia",JOptionPane.WARNING_MESSAGE);

        }else if(banderaProvArti==1){
                    JOptionPane.showMessageDialog(null, "El proveedor que desea guardar debe ser Editado desde la ventana principal del articulo","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else if(txtPrecioReferencia.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "Falta el precio bruto","Advertencia",JOptionPane.WARNING_MESSAGE);
                            txtPrecioReferencia.setBackground(Color.yellow);
                            txtPrecioReferencia.requestFocus();
                        }else{
                            try{
                                Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                                Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql


                                //MUESTRO EL PROVEEDOR
                                   ResultSet  res22= consulta.executeQuery("select * from proveedor where nombre_firma='"+proveedor+"'");
                                   String codigoProveedor="";      
                                   while(res22.next()){
                                      codigoProveedor=res22.getString(1);
                                  }                         
                                if(banderaProveedor==0){
                                    consulta.executeUpdate("insert into articulo_referencia (cod_articulo,proveedor,precio)  values('"+txtCodigoReferencia.getText()+"','"+codigoProveedor+"','"+txtPrecioReferencia.getText()+"')");
                                    JOptionPane.showMessageDialog(null, "Los datos han sido Guardados Correctamente");
                                }else{
                                    consulta.executeUpdate("UPDATE articulo_referencia SET precio='"+txtPrecioReferencia.getText()+"' WHERE proveedor='"+codigoProveedor+"'");                 
                                    JOptionPane.showMessageDialog(null, "Los datos han sido Modificados Correctamente");
                                }
                                cargarReferenciaProveedor();
                                MostrarArticulos();
                            }catch(SQLException e){
                                System.out.println(e);
                                JOptionPane.showMessageDialog(null,"Error en el SQL") ;
                            }
                        }
    }//GEN-LAST:event_btnGuardarReferenciaArticuloActionPerformed

    private void tablaReferenciaArticuloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaReferenciaArticuloMouseClicked
        Select=tablaReferenciaArticulo.getSelectedRow();
        if(Select>0){
            comboReferenciaProveedor.setSelectedItem(tablaReferenciaArticulo.getValueAt(Select,1).toString());
            txtPrecioReferencia.setText( tablaReferenciaArticulo.getValueAt(Select,2).toString());
           
        }else{
            JOptionPane.showMessageDialog(null,"Esta fila no se puede borrar ni editar, es el proveedor que tiene actualmente este producto") ; 
        } 
    }//GEN-LAST:event_tablaReferenciaArticuloMouseClicked

    private void eliminarProveedorReferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarProveedorReferenciaActionPerformed
        Select=tablaReferenciaArticulo.getSelectedRow();
        if(Select>=0){          
            String proveedor= tablaReferenciaArticulo.getValueAt(Select,1).toString();
            String articulo= tablaReferenciaArticulo.getValueAt(Select,0).toString();
            Object[] opciones ={"Aceptar","Cancelar"};
            int eleccion = JOptionPane.showOptionDialog(rootPane,"¿Desea eliminar el proveedor '"+proveedor+"'?","Atención",

                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
            if(eleccion ==JOptionPane.YES_OPTION){
                
                eliminarReferenciaProveedor(proveedor,articulo);   
                MostrarArticulos();
            }else{
                        
            }
        }else{
            JOptionPane.showMessageDialog(null,"No ha seleccionado ninguna fila") ; 
        } 
    }//GEN-LAST:event_eliminarProveedorReferenciaActionPerformed

    private void txtCodigoProvisorioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProvisorioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoProvisorioKeyTyped

    private void txtMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyReleased
        txtMarca.setText (txtMarca.getText().toUpperCase());
    }//GEN-LAST:event_txtMarcaKeyReleased

    private void txtCodigoArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoArticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoArticuloActionPerformed

    private void txtAgregarCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgregarCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgregarCantidadActionPerformed

    private void txtAgregarCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAgregarCantidadKeyTyped
        char []p={'1','2','3','4','5','6','7','8','9','0','-'};
        int b=0;
        for(int i=0;i<=10;i++){
            if (p[i]==evt.getKeyChar()){
                b=1;
            }
        }
        if(b==0){
            evt.consume();  
            getToolkit().beep(); 
        }
    }//GEN-LAST:event_txtAgregarCantidadKeyTyped

    private void txtPrecioVentaConIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioVentaConIvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioVentaConIvaActionPerformed

    private void txtPrecioVentaConIvaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaConIvaKeyTyped
        char []p={'1','2','3','4','5','6','7','8','9','0','.',','};
        int b=0;
        for(int i=0;i<=11;i++){
            if (p[i]==evt.getKeyChar()){
                b=1;
            }
        }
        if(b==0){
            evt.consume();  
            getToolkit().beep(); 
        }
    }//GEN-LAST:event_txtPrecioVentaConIvaKeyTyped

    private void comboIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIvaActionPerformed
        preciosBaseDatos++;
        if(preciosBaseDatos==3){
            String iva=comboIva.getSelectedItem().toString();
            Double porcentajeIva=0.00;
            if(iva.equals("19%")){
                porcentajeIva=1.19;
            }else
                if(iva.equals("0%")){
                   porcentajeIva=1.00;
                }
            if(!txtPrecioVentaSinIva.getText().equals("")){
                Double precioNeto= Double.parseDouble(txtPrecioVentaSinIva.getText().replace(",", "."));
                Double netoConIva= precioNeto*porcentajeIva;
                String precion=(String.format("%.2f", netoConIva));
                txtPrecioVentaConIva.setText(precion.replace(",", "."));
            }
            preciosBaseDatos=1;
        }
        
        
    }//GEN-LAST:event_comboIvaActionPerformed

    private void txtPrecioVentaSinIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaSinIvaKeyReleased
        if(porcentaje.isSelected()){
            String iva=comboIva.getSelectedItem().toString();
            Double porcentajeIva=0.0;
            if(iva.equals("19%")){
                porcentajeIva=1.19;
            }else
                if(iva.equals("0%")){
                   porcentajeIva=1.00;
                }
            String precioNetoCadena=txtPrecioVentaSinIva.getText().replace(",", ".");
            if (precioNetoCadena.equals("")){
                precioNetoCadena="0.00";
            }
            Double precioNeto= Double.parseDouble(precioNetoCadena);//Precio venta sin iva
            Double netoConIva= precioNeto*porcentajeIva;
            String precion=(String.format("%.2f", netoConIva).replace(",", "."));
            txtPrecioVentaConIva.setText(precion.replace(",", "."));

            //SACO EL PORCENTAJE DE LA VENTA
            String porcentajeVenta=txtPorcentaje.getText();
            if (porcentajeVenta.equals("")){
                porcentajeVenta="0.00";
            }
            Double porcentajeV=Double.parseDouble(porcentajeVenta.replace(",","."))/100+1;//Calculo el porcentaje de venta (si es 65 me queda 1,65);

            Double precioCostoIvaCalculado=precioNeto/porcentajeV;
            txtPrecioCostoIva.setText(String.format("%.2f", precioCostoIvaCalculado).replace(",", "."));//SETEO EL COSTO CON IVA


            String ivaCompra=comboIvaCompra.getSelectedItem().toString();
            Double porcentajeIvaCompra=0.0;
            if(ivaCompra.equals("19%")){
                porcentajeIvaCompra=1.19;
            }else
                if(ivaCompra.equals("0%")){
                    porcentajeIvaCompra=1.00;
                }

            Double brutoConIva=0.0;

            if(!txtPrecioCostoIva.getText().equals("")){
                try{                                
                    brutoConIva=(double)Integer.parseInt(txtPrecioCostoIva.getText().replace(",", "."));
                }catch (NumberFormatException e){
                    brutoConIva=Double.parseDouble(txtPrecioCostoIva.getText().replace(",", "."));
                }

            }
            Double brutoSinIva= brutoConIva/porcentajeIvaCompra;
            brutoSinIva=Math.round(brutoSinIva * 100.0)/ 100.0;
            txtCostoSinIva.setText(String.format("%.2f", brutoSinIva).replace(",", "."));
        }
        
    }//GEN-LAST:event_txtPrecioVentaSinIvaKeyReleased

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
       
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       Configurar_Porcentajes_Articulo repor = new Configurar_Porcentajes_Articulo();
       repor.txtRecibe.setText("1");
       repor .setVisible(true);
       repor.setLocationRelativeTo(this);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaActionPerformed

    private void comboCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCategoriaActionPerformed

    private void checkPorMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPorMarcaActionPerformed
        verificarElementos();
        mostrarFlecha();
        labelProveedor.setVisible(false);
        comboProveedor1.setVisible(false);
        comboProveedor1.setEnabled(false);
        comboProveedor1.setSelectedIndex(0);
        labelDescripcion.setVisible(false);
        txtDescripcion.setVisible(false);
        txtDescripcion.setEnabled(false);
        txtDescripcion.setText("");
        labelMarca1.setVisible(false);
        txtPorMarca.setText("");
        txtPorMarca.setVisible(true);
    }//GEN-LAST:event_checkPorMarcaActionPerformed

    private void txtPorMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorMarcaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorMarcaKeyReleased

    private void txtCostoSinIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoSinIvaKeyReleased
         //ES DEL TEXTFIEL DE VALOR COSTO SIN IVA
        String ivaCompra=comboIvaCompra.getSelectedItem().toString();
        Double porcentajeIvaCompra=0.0;
        if(ivaCompra.equals("19%")){
            porcentajeIvaCompra=1.19;
        }else
            if(ivaCompra.equals("0%")){
                 porcentajeIvaCompra=1.00;
            }
        
        String iva=comboIva.getSelectedItem().toString();
        Double porcentajeIva=0.0;
        if(iva.equals("19%")){
            porcentajeIva=1.19;
        }else
            if(iva.equals("0%")){
               porcentajeIva=1.00;
            }
        Double brutoSinIva=0.0,bruto=0.0,porcent=0.0;
        
        if(!txtCostoSinIva.getText().equals("")){
            try{                                
                brutoSinIva=(double)Integer.parseInt(txtCostoSinIva.getText().replace(",", "."));
            }catch (NumberFormatException e){
                brutoSinIva=Double.parseDouble(txtCostoSinIva.getText().replace(",", "."));
            }
            
        }
        Double brutoConIva= brutoSinIva*porcentajeIvaCompra;
        brutoConIva=Math.round(brutoConIva * 100.0)/ 100.0;
        txtPrecioCostoIva.setText(String.format("%.2f", brutoConIva).replace(",", "."));
        
        String porcentajeGanancia=txtPorcentaje.getText();        
        if(porcentajeGanancia.equals("")){
           porcentajeGanancia="0.00" ;
        }

        String valorBruto="",porcentajee="";
        
        bruto=Double.parseDouble(txtPrecioCostoIva.getText().replace(",", "."));
        porcent=Double.parseDouble(porcentajeGanancia.replace(",", "."));  
                    
        valorBruto=(String.format("%.2f", bruto));
        porcentajee=(String.format("%.2f", porcent));
                
        Double precioneto = bruto + (bruto * porcent)/100;

        String precion=(String.format("%.2f", precioneto));
        txtPrecioVentaSinIva.setText(precion.replace(",", "."));
        String precioConIva=(String.format("%.2f", precioneto*porcentajeIva).replace(",", "."));
         txtPrecioVentaConIva.setText(precioConIva);
    }//GEN-LAST:event_txtCostoSinIvaKeyReleased

    private void txtCostoSinIvaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoSinIvaKeyTyped
        char []p={'1','2','3','4','5','6','7','8','9','0','.',','};
        int b=0;
        for(int i=0;i<=11;i++){
            if (p[i]==evt.getKeyChar()){
                b=1;
            }
        }
        if(b==0){
            evt.consume();  
            getToolkit().beep(); 
        }
    }//GEN-LAST:event_txtCostoSinIvaKeyTyped

    private void comboIvaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIvaCompraActionPerformed
        //DEL COMBO IVA VENTA
        preciosBaseDatos++;
        if(preciosBaseDatos==3){
            String ivaCompra=comboIvaCompra.getSelectedItem().toString();
            Double porcentajeIvaCompra=0.0;
            if(ivaCompra.equals("19%")){
                porcentajeIvaCompra=1.19;
            }else
                if(ivaCompra.equals("0%")){
                    porcentajeIvaCompra=1.0;
                }

            if(!txtCostoSinIva.getText().equals("")){
                String iva=comboIva.getSelectedItem().toString();
                Double porcentajeIva=0.0;
                if(iva.equals("19%")){
                    porcentajeIva=1.19;
                }else
                    if(iva.equals("0%")){
                         porcentajeIva=1.00;
                    }
                Double brutoSinIva=0.0,bruto=0.0,porcent=0.0;

                if(!txtCostoSinIva.getText().equals("")){
                    try{                                
                        brutoSinIva=(double)Integer.parseInt(txtCostoSinIva.getText().replace(",", "."));
                    }catch (NumberFormatException e){
                        brutoSinIva=Double.parseDouble(txtCostoSinIva.getText().replace(",", "."));
                    }

                }
                Double brutoConIva= brutoSinIva*porcentajeIvaCompra;
                brutoConIva=Math.round(brutoConIva * 100.0)/ 100.0;
                txtPrecioCostoIva.setText(String.format("%.2f", brutoConIva).replace(",", "."));

                String porcentajeGanancia=txtPorcentaje.getText();        
                if(porcentajeGanancia.equals("")){
                   porcentajeGanancia="0.00" ;
                }

                String valorBruto="",porcentajee="";

                bruto=Double.parseDouble(txtPrecioCostoIva.getText().replace(",", "."));
                porcent=Double.parseDouble(porcentajeGanancia.replace(",", "."));  

                valorBruto=(String.format("%.2f", bruto));
                porcentajee=(String.format("%.2f", porcent));

                Double precioneto = bruto + (bruto * porcent)/100;

                String precion=(String.format("%.2f", precioneto));
                txtPrecioVentaSinIva.setText(precion.replace(",", "."));
                String precioConIva=(String.format("%.2f", precioneto*porcentajeIva).replace(",", "."));
                txtPrecioVentaConIva.setText(precioConIva);
            }
            preciosBaseDatos=1;
        }
    }//GEN-LAST:event_comboIvaCompraActionPerformed

    private void checkSoloVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSoloVentaActionPerformed
        verificarElementos();
        mostrarFlecha();
    }//GEN-LAST:event_checkSoloVentaActionPerformed

    private void checkIncrementarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkIncrementarActionPerformed
        verificarElementos();
        mostrarFlecha();
    }//GEN-LAST:event_checkIncrementarActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        /* this.setVisible(false);
        MostrarArticulosPapelera();
        Papelera.setVisible(true);*/
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        txtCodigo.setText (txtCodigo.getText().toUpperCase()); 
    }//GEN-LAST:event_txtCodigoKeyReleased
    
    public void GuardarTabla(){ //GUARDO LOS ARTICULOS EN LA BD
         //this.panelTabla1.setVisible(true);
        String valorBruto="",valorNeto="";
        try {          
            Connection conn = conexion.ObtenerConexion();
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            Statement consulta1=conn.createStatement();
            
            for(int i=0;i<tabla1.getRowCount();i++){
                ResultSet r= consulta1.executeQuery("select cod_articulo from articulo");
                int bandera=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
                while(r.next()){
                    if(tabla1.getValueAt(i,0).toString().equals(r.getString(1))){//si el codigo_unico de la tabla es igual a algun codigo_unico de la base de datos
                        bandera=1;
                        //JOptionPane.showMessageDialog(null,"BANDERA= "+bandera);     
                    }
                }
                /*Double bruto=0.0,neto=0.0;
                
                //EL TRY CATCH ES POR SI LOS VALORES QUE HAY EN LA TABLA SON ENTEROS O DOBLES
                try{ 
                    bruto=(double)Integer.parseInt(tabla1.getValueAt(i,5).toString().toUpperCase().toString().replace(",", "."));//PRECIO BRUTO
                    neto=(double)Integer.parseInt(tabla1.getValueAt(i,6).toString().toUpperCase().replace(",", "."));//PRECIO NETO
                }catch (NumberFormatException e){
                    bruto=Double.parseDouble(tabla1.getValueAt(i,5).toString().toUpperCase().replace(",", "."));
                    neto=Double.parseDouble(tabla1.getValueAt(i,6).toString().toUpperCase().replace(",", "."));  
                }
  
                valorBruto=(String.format("%.2f", bruto)); // "%.2f" PARA QUE QUEDE CON 2 DECIMALES
                valorNeto=(String.format("%.2f", neto));*/
                
                String ultimaActualizacion="";
                
                Calendar fecha = new GregorianCalendar();
                //Obtenemos el valor del año, mes, día, hora, minuto y segundo del sistema.
                //Usando el método get y el parámetro correspondiente.

                int año = fecha.get(Calendar.YEAR);
                int mes = fecha.get(Calendar.MONTH);
                int dia = fecha.get(Calendar.DAY_OF_MONTH);
                
                if((mes+1)<10 && (dia>=10)){
                    ultimaActualizacion = (dia+"/0"+(mes+1)+"/"+año); 
                }else{
                    if(((mes+1)<10 && (dia<10))){
                        ultimaActualizacion =("0"+dia+"/0"+(mes+1)+"/"+año);
                    }else{
                        ultimaActualizacion = (dia+"/"+(mes+1)+"/"+año);
                    }
                }
              
                if(bandera==0){//Si el codigo no esta inserto una nuevo registro a la base de datos
                     consulta.executeUpdate("insert into articulo (cod_articulo,referencia,cantidad,proveedor,valor,valor_bruto,estado,marca,ultima_actualizacion,codigo_provisorio,total_con_iva,porcentaje_ganancia,iva,categoria,valor_bruto_sin_iva,ivaCompra,impresion_etiqueta)  values('"+tabla1.getValueAt(i,0).toString().toUpperCase()+"','"+tabla1.getValueAt(i,1).toString().toUpperCase()+"','0','0','0.00','0.00','ACTIVO','','"+ultimaActualizacion+"','','0.00','65.00','0.00','Productos','0.00','0.00','NO')");
                    //consulta.executeUpdate("insert into articulo (cod_articulo,referencia,cantidad,marca,valor,valor_bruto,estado,ultima_actualizacion,codigo_provisorio)  values ('"+tabla1.getValueAt(i,0).toString().toUpperCase()+"','"+tabla1.getValueAt(i,1).toString().toUpperCase()+"','"+tabla1.getValueAt(i,4).toString().toUpperCase()+"','"+tabla1.getValueAt(i,2).toString().toUpperCase()+"','"+valorNeto.replace(",", ".")+"','"+valorBruto.replace(",", ".")+"','ACTIVO','"+ultimaActualizacion+"','"+tabla1.getValueAt(i,3).toString().toUpperCase()+"')");
                
                }else{//si el codigo esta,actualizo la informacion del producto
                    
                   /*consulta.executeUpdate("UPDATE articulo SET referencia='"+tabla1.getValueAt(i,1).toString().toUpperCase()+"' WHERE cod_articulo='"+tabla1.getValueAt(i,0).toString().toUpperCase()+"'");    
                    String marca=tabla1.getValueAt(i,2).toString().toUpperCase();
                    if(!marca.equals("")){
                         consulta.executeUpdate("UPDATE articulo SET marca='"+tabla1.getValueAt(i,2).toString().toUpperCase()+"' WHERE cod_articulo='"+tabla1.getValueAt(i,0).toString().toUpperCase()+"'");    
                    }
                    
                    String codigoProvisorio=tabla1.getValueAt(i,3).toString().toUpperCase();
                    if(!codigoProvisorio.equals("")){
                         consulta.executeUpdate("UPDATE articulo SET codigo_provisorio='"+tabla1.getValueAt(i,3).toString().toUpperCase()+"' WHERE cod_articulo='"+tabla1.getValueAt(i,0).toString().toUpperCase()+"'");    
                    }
                    
                    String stock=tabla1.getValueAt(i,4).toString().toUpperCase();
                    if(!stock.equals("")){
                         consulta.executeUpdate("UPDATE articulo SET cantidad='"+tabla1.getValueAt(i,4).toString().toUpperCase()+"' WHERE cod_articulo='"+tabla1.getValueAt(i,0).toString().toUpperCase()+"'");    
                    }    
                    String valorBruto2= tabla1.getValueAt(i,5).toString();

                    if(!valorBruto2.equals("0")){
                         consulta.executeUpdate("UPDATE articulo SET valor_bruto='"+valorBruto.replace(",", ".")+"' WHERE cod_articulo='"+tabla1.getValueAt(i,0).toString().toUpperCase()+"'");    
                    }
                    String valorNeto2= tabla1.getValueAt(i,6).toString();

                    if(!valorNeto2.equals("0")){
                         consulta.executeUpdate("UPDATE articulo SET valor='"+valorNeto.replace(",", ".")+"' WHERE cod_articulo='"+tabla1.getValueAt(i,0).toString().toUpperCase()+"'");    
                    }*/
                }  
                
            }
            deshabilitar();
            JOptionPane.showMessageDialog(null,"LOS ARTICULOS SE CARGARON CORRECTAMENTE"); 
            MostrarArticulos();
        } 
        catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"ERROR AL CONECTARCE A LA BASE DE DATOS");
             System.out.println(ex);
                    
	}
    }
    
    private void btnImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarActionPerformed

        //BOTON IMPORTAR DESDE EXCEL (ESTE CODIGO ESTA ACTUALMENTE EN DESUSO)
        AdvertenciaExcel.setVisible(true);
        ModeloExcel modeloE = new ModeloExcel();
        int contAccion=0;

        contAccion++;
        if(contAccion==1)AgregarFiltro();

        try{
            if(evt.getSource() == btnImportar){
                if(selecArchivo.showDialog(null, "Seleccionar archivo")==JFileChooser.APPROVE_OPTION){
                    archivo=selecArchivo.getSelectedFile();
                    if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                        JOptionPane.showMessageDialog(null,
                            modeloE.Importar(archivo,tabla1) + "\n Formato ."+ archivo.getName().substring(archivo.getName().lastIndexOf(".")+1),
                            "IMPORTAR EXCEL", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Elija un formato valido.");
                    }
                    GuardarTabla();
                }
            }
            TableColumn  column = null;
        }catch(Exception e){

        }
    }//GEN-LAST:event_btnImportarActionPerformed

    private void txtPrecioVentaConIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaConIvaKeyReleased
         if(porcentaje.isSelected()){
            String iva=comboIva.getSelectedItem().toString();
            Double porcentajeIva=0.0;
            if(iva.equals("19%")){
                porcentajeIva=1.19;
            }else
                if(iva.equals("0%")){
                     porcentajeIva=1.00;
                }
            String precioNetoCadena=txtPrecioVentaConIva.getText().replace(",", ".");//precio venta con iva
            if (precioNetoCadena.equals("")){
                precioNetoCadena="0.00";
            }
            Double precioNetoConIva= Double.parseDouble(precioNetoCadena);//Precio venta con iva
            Double netoSinIva= precioNetoConIva/porcentajeIva;
            String precion=(String.format("%.2f", netoSinIva).replace(",", "."));
            txtPrecioVentaSinIva.setText(precion.replace(",", "."));

            Double precioNeto= Double.parseDouble(txtPrecioVentaSinIva.getText().replace(",","."));//Precio venta sin iva

            //SACO EL PORCENTAJE DE LA VENTA
            String porcentajeVenta=txtPorcentaje.getText();
            if (porcentajeVenta.equals("")){
                porcentajeVenta="0.00";
            }
            Double porcentajeV=Double.parseDouble(porcentajeVenta.replace(",","."))/100+1;//Calculo el porcentaje de venta (si es 65 me queda 1,65);

            Double precioCostoIvaCalculado=precioNeto/porcentajeV;
            txtPrecioCostoIva.setText(String.format("%.2f", precioCostoIvaCalculado).replace(",", "."));//SETEO EL COSTO CON IVA


            String ivaCompra=comboIvaCompra.getSelectedItem().toString();
            Double porcentajeIvaCompra=0.0;
            if(ivaCompra.equals("19%")){
                porcentajeIvaCompra=1.19;
            }else
                if(ivaCompra.equals("0%")){
                    porcentajeIvaCompra=1.00;
                }

            Double brutoConIva=0.0;

            if(!txtPrecioCostoIva.getText().equals("")){
                try{                                
                    brutoConIva=(double)Integer.parseInt(txtPrecioCostoIva.getText().replace(",", "."));
                }catch (NumberFormatException e){
                    brutoConIva=Double.parseDouble(txtPrecioCostoIva.getText().replace(",", "."));
                }

            }
            Double brutoSinIva= brutoConIva/porcentajeIvaCompra;
            brutoSinIva=Math.round(brutoSinIva * 100.0)/ 100.0;
            txtCostoSinIva.setText(String.format("%.2f", brutoSinIva).replace(",", "."));
        }
    }//GEN-LAST:event_txtPrecioVentaConIvaKeyReleased


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
            java.util.logging.Logger.getLogger(Registrar_Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrar_Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrar_Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrar_Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Registrar_Articulos dialog = new Registrar_Articulos(new javax.swing.JFrame(), true);
                 
                
                
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
    private javax.swing.JDialog AdvertenciaExcel;
    private javax.swing.ButtonGroup FiltrarCostoNeto;
    private javax.swing.ButtonGroup FiltrarPrecios;
    private javax.swing.ButtonGroup FiltrarProductos;
    private javax.swing.ButtonGroup FiltrarResultados;
    private javax.swing.ButtonGroup FiltrarResultadosPapelera;
    private javax.swing.ButtonGroup IncrementoDecremento;
    private javax.swing.JDialog Papelera;
    private javax.swing.JButton btnActualizarPrecios;
    private javax.swing.JButton btnAplicarPrecio;
    private javax.swing.JButton btnCancelarPrecio;
    private javax.swing.JButton btnGuardarReferenciaArticulo;
    private javax.swing.JButton btnImportar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevoPrecio;
    private javax.swing.JButton btn_cancelara;
    private javax.swing.JButton btn_eliminara;
    private javax.swing.JButton btn_guardara1;
    private javax.swing.JRadioButton checkCategoria;
    private javax.swing.JRadioButton checkCodigo;
    private javax.swing.JRadioButton checkCodigoPapelera;
    private javax.swing.JRadioButton checkCostoYVenta;
    private javax.swing.JRadioButton checkDecrementar;
    private javax.swing.JRadioButton checkDescripcionPapelera;
    private javax.swing.JRadioButton checkIncrementar;
    private javax.swing.JRadioButton checkMarca;
    private javax.swing.JRadioButton checkPorDescripcion;
    private javax.swing.JRadioButton checkPorMarca;
    private javax.swing.JRadioButton checkPorProveedor;
    private javax.swing.JRadioButton checkProveedorPapelera;
    private javax.swing.JRadioButton checkProveedorYDescripcion;
    private javax.swing.JRadioButton checkReferencia;
    private javax.swing.JRadioButton checkSoloVenta;
    public static javax.swing.JComboBox comboCategoria;
    public static javax.swing.JComboBox comboIva;
    public static javax.swing.JComboBox comboIvaCompra;
    public static javax.swing.JComboBox comboProveedor;
    public static javax.swing.JComboBox comboProveedor1;
    public static javax.swing.JComboBox comboReferenciaProveedor;
    private javax.swing.JMenuItem eliminarProveedorReferencia;
    private javax.swing.JLabel flechaAbajo;
    private javax.swing.JLabel flechaArriba;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemBorrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelDescripcion;
    private javax.swing.JLabel labelInfo1;
    private javax.swing.JLabel labelInfo2;
    private javax.swing.JLabel labelMarca1;
    private javax.swing.JLabel labelPorcentaje;
    private javax.swing.JLabel labelPorcentaje1;
    private javax.swing.JLabel labelProveedor;
    private javax.swing.JPanel panelActualizarPrecios;
    private javax.swing.JPanel panelElementos;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelReferencia;
    private javax.swing.JPanel panelTabla1;
    private javax.swing.JPanel panelTablas;
    private javax.swing.JPopupMenu popMenuEliminarProvReferencia;
    private javax.swing.JRadioButton porcentaje;
    public javax.swing.JTable tabla;
    private javax.swing.JTable tabla1;
    private javax.swing.JTable tablaPapelera;
    private javax.swing.JTable tablaReferenciaArticulo;
    public static javax.swing.JTextField txtAgregarCantidad;
    public static javax.swing.JTextField txtArticuloReferencia;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarPapelera;
    public static javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigoArticulo;
    public static javax.swing.JTextField txtCodigoProvisorio;
    public static javax.swing.JTextField txtCodigoReferencia;
    public static javax.swing.JTextField txtCostoSinIva;
    public static javax.swing.JTextField txtDescripcion;
    public static javax.swing.JTextField txtMarca;
    public static javax.swing.JTextField txtPorMarca;
    public static javax.swing.JTextField txtPorcentaje;
    private javax.swing.JTextField txtPorcentajeActualizable;
    public static javax.swing.JTextField txtPrecioCostoIva;
    public static javax.swing.JTextField txtPrecioReferencia;
    public static javax.swing.JTextField txtPrecioVentaConIva;
    public static javax.swing.JTextField txtPrecioVentaSinIva;
    public static javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtUnicod;
    // End of variables declaration//GEN-END:variables
}
