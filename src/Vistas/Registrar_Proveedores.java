
package Vistas;
import java.awt.Color;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.Font;
import javax.swing.table.JTableHeader;

public class Registrar_Proveedores extends javax.swing.JDialog {
    //VARIABLES GLOBALES
    int Select, SelectPapelera,contador, seleccionarTodos=0;
    boolean TodosSeleccionados=false;
    String codigo,referencia,cantidad,marca,valor;

    public Registrar_Proveedores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();
        setLocationRelativeTo(null); //AL CENTRO DE LA PANTALLA
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
          
        cargarComboLocalidad();
        cargarContribuyente();
        MostrarProveedor();
        
        Papelera.setSize(990,750);
        Papelera.setLocationRelativeTo(null);   
        MostrarProveedoresPapelera();    
        
        desactivar();
        
        //CAMBIO LOS COLORES DE LAS CABECERAS DE LA TABLA
        JTableHeader th; 
        th = tablaproveedores.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 19); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        JTableHeader th2; 
        th2 = tablaPapelera.getTableHeader(); 
        Font fuente2 = new Font("Calibri", Font.BOLD, 20); 
        th2.setFont(fuente2); 
        th2.setBackground(new Color(93,116,163));
        th2.setForeground(new Color(255,255,255));
        
        JTableHeader th3; 
        th3 = tablaRepresentante.getTableHeader(); 
        Font fuente3 = new Font("Calibri", Font.BOLD, 19); 
        th3.setFont(fuente3); 
        th3.setBackground(new Color(93,116,163));
        th3.setForeground(new Color(255,255,255));
    }
    
    public void desactivar(){
        mostrarRepresentante(0);
        Papelera.setVisible(false);
        panelRepresentante.setVisible(false); 
        
        txtCodigoEmpresaRepresentante.setVisible(false);
        txtCodigoRepresentante.setVisible(false);
        txtNombreEmpresaRepresentante.setEditable(false);
        
        comboLocalidad.setSelectedIndex(0);
        checkEmpresa.setSelected(true); 
        checkNombrePapelera.setSelected(true);
        
        txtBuscar.setText("Ingrese su búsqueda");
        txtBuscar.setForeground(Color.gray);
        jPanel1.setVisible(false);
    
        txtCodigoPostal.setEditable(false);
        txtProvincia.setEditable(false);
        txtnombreEmpresa.setEditable(false);
        txtcedula.setEditable(false);
        txtResponsableEmpresa.setEditable(false);
        txtProvincia.setEditable(false);
        txtDireccionEmpresa.setEditable(false);
        txtCuit.setEditable(false);
        txtCodigoPostal.setEditable(false);
        txtIngresosBrutos.setEditable(false);
        txtCbu.setEditable(false);
        txtTelefonoEmpresa.setEditable(false);
                  
        btn_guardara.setEnabled(false);
        btn_cancelara.setEnabled(false);
        btn_eliminara.setEnabled(false);
            
        txtcedula.setVisible(false);
    }  

    public void nuevoo(){
        mostrarRepresentante(0);
        jPanel1.setVisible(true);
        
        comboLocalidad.setSelectedIndex(0);
        
        txtnombreEmpresa.setText("");
        txtcedula.setText("");
        txtnombreEmpresa.requestFocus();
        txtResponsableEmpresa.setText("");
        txtProvincia.setText("");
        txtDireccionEmpresa.setText("");
        txtCuit.setText("");
        txtCodigoPostal.setText("");
        txtIngresosBrutos.setText("");
        txtCbu.setText("");
        txtTelefonoEmpresa.setText("");
        txtnombreEmpresa.setEditable(true);
        txtcedula.setEditable(true);
        txtResponsableEmpresa.setEditable(true);
        txtDireccionEmpresa.setEditable(true);
        txtCuit.setEditable(true);
        txtIngresosBrutos.setEditable(true);
        txtCbu.setEditable(true);
        txtTelefonoEmpresa.setEditable(true);

        btn_guardara.setEnabled(true);
        btn_cancelara.setEnabled(true);
        btn_eliminara.setEnabled(true);
    }
    
    public void cargarContribuyente(){
        comboContribuyente.addItem("Consumidor Final");
        comboContribuyente.addItem("Responsable Monotributo");
        comboContribuyente.addItem("Responsable Inscripto");
        comboContribuyente.addItem("Responsable no Inscripto");   
        comboContribuyente.addItem("No Responsable"); 
        comboContribuyente.addItem("Exento");  
    }
    
   public static void cargarComboLocalidad(){
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet   res= consulta.executeQuery("select * from localidad order by localidad");  
                
            int i;
            comboLocalidad.removeAllItems();
            comboLocalidad.addItem("SELECCIONE LOCALIDAD");
            i=0;
            while(res.next()){
                comboLocalidad.addItem(res.getString(2));
                i++;
            }
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
    }
   
    public void MostrarProveedor(){
        TableColumn  column = null;

        //SETEO EL ANCHO DE LAS FILAS
        tablaproveedores.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaproveedores.getColumnModel().getColumn(0).setMinWidth(0);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        
        tablaRepresentante.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaRepresentante.getColumnModel().getColumn(0).setMinWidth(0);
        tablaRepresentante.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tablaRepresentante.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        
        tablaproveedores.getColumnModel().getColumn(1).setMaxWidth(400);
        tablaproveedores.getColumnModel().getColumn(1).setMinWidth(400);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(400);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(400);
        
        tablaproveedores.getColumnModel().getColumn(2).setMaxWidth(270);
        tablaproveedores.getColumnModel().getColumn(2).setMinWidth(270);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(270);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(270);
        
        tablaproveedores.getColumnModel().getColumn(3).setMaxWidth(250);
        tablaproveedores.getColumnModel().getColumn(3).setMinWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(250);
        
        tablaproveedores.getColumnModel().getColumn(4).setMaxWidth(250);
        tablaproveedores.getColumnModel().getColumn(4).setMinWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(250);
        
        tablaproveedores.getColumnModel().getColumn(5).setMaxWidth(210);
        tablaproveedores.getColumnModel().getColumn(5).setMinWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(210);
        
        tablaproveedores.getColumnModel().getColumn(6).setMaxWidth(210);
        tablaproveedores.getColumnModel().getColumn(6).setMinWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(210);
        
        tablaproveedores.getColumnModel().getColumn(7).setMaxWidth(210);
        tablaproveedores.getColumnModel().getColumn(7).setMinWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(210);
        
        tablaproveedores.getColumnModel().getColumn(8).setMaxWidth(210);
        tablaproveedores.getColumnModel().getColumn(8).setMinWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(210);
        
        tablaproveedores.getColumnModel().getColumn(9).setMaxWidth(210);
        tablaproveedores.getColumnModel().getColumn(9).setMinWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(210);
        
        tablaproveedores.getColumnModel().getColumn(10).setMaxWidth(210);
        tablaproveedores.getColumnModel().getColumn(10).setMinWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(210);
        
        
        tablaproveedores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tablaproveedores.doLayout();
            
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet r= consulta.executeQuery("select * from proveedor where cod_proveedor>0 AND estado='ACTIVO' order by nombre_firma");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tablaproveedores.getModel();
            tablaproveedores.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            while(r.next()){
                if(r.getString(12).equals("ACTIVO")){
                    modelo.addRow( new Object [] {null,null,null,null,null,null,null,null,null,null,null});
                    tablaproveedores.setValueAt(r.getString(1),i,0);
                    tablaproveedores.setValueAt(r.getString(2),i,1);
                    tablaproveedores.setValueAt(r.getString(3),i,2);
                    tablaproveedores.setValueAt(r.getString(4),i,3);
                    tablaproveedores.setValueAt(r.getString(5),i,4);
                    tablaproveedores.setValueAt(r.getString(6),i,5);
                    tablaproveedores.setValueAt(r.getString(7),i,6);
                    tablaproveedores.setValueAt(r.getString(8),i,7);
                    tablaproveedores.setValueAt(r.getString(9),i,8);
                    tablaproveedores.setValueAt(r.getString(10),i,9);
                    tablaproveedores.setValueAt(r.getString(11),i,10);
                    i++;
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
        }
    }

    //TABLA DE PAPELERA
    DefaultTableModel modelo2 = new DefaultTableModel();
    private boolean[] editable = {true,false,false,false,true,false,false,false,false};
    public void MostrarProveedoresPapelera(){
        Object[] datos= new Object[9];
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            ResultSet r2= consulta.executeQuery("select * from  proveedor order by nombre_firma");
            int i,j;
            i=0;
            j=0;
            Render3 r= new Render3();
            tablaPapelera.setDefaultRenderer(Object.class,r);
            DefaultTableModel modelo = new DefaultTableModel(new String[]{"SELECCIONAR","CODIGO", "PROVEEDOR","DIRECCION","CIUDAD","COD POSTAL","PROVINCIA","CUIT/L","TELEFONO"}, 0) {
                Class[] types = new Class[]{
                java.lang.Boolean.class,java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                 public boolean isCellEditable(int row, int column){
                     return editable[column];
                }
            };
                             
            if(seleccionarTodos==1){
               while(r2.next()){
                    if(r2.getString(12).equals("INACTIVO")){
                        datos[0]=true;
                        datos[1]=r2.getObject(1);
                        datos[2]=r2.getObject(2);
                        datos[3]=r2.getObject(4);
                        datos[4]=r2.getObject(5);
                        datos[5]=r2.getObject(6);
                        datos[6]=r2.getObject(7);
                        datos[7]=r2.getObject(8);
                        datos[8]=r2.getObject(11);
                        modelo.addRow(datos);
                    }            
                } 
                seleccionarTodos=0;
            }else{
                while(r2.next()){
                    if(r2.getString(12).equals("INACTIVO")){
                        datos[0]=false;
                        datos[1]=r2.getObject(1);
                        datos[2]=r2.getObject(2);
                        datos[3]=r2.getObject(4);
                        datos[4]=r2.getObject(5);
                        datos[5]=r2.getObject(6);
                        datos[6]=r2.getObject(7);
                        datos[7]=r2.getObject(8);
                        datos[8]=r2.getObject(11);
                        modelo.addRow(datos);
                    }          
                }
            }
            tablaPapelera.setModel(modelo);
            r2.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la Base de Datos") ;
        }       
    }
     
    public void mostrarRepresentante(int codigoEmpresa){ //TABLA REPRESENTANTE
        TableColumn  column = null;
        tablaRepresentante.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaRepresentante.getColumnModel().getColumn(0).setMinWidth(0);
        tablaRepresentante.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tablaRepresentante.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);     
        
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            Statement consulta2=conn.createStatement();

            ResultSet r= consulta.executeQuery("select * from representante_empresa where empresa='"+codigoEmpresa+"' order by nombre_apellido");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tablaRepresentante.getModel();
            tablaRepresentante.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null,null});
                tablaRepresentante.setValueAt(r.getString(1),i,0);
     
                ResultSet r2= consulta2.executeQuery("select nombre_firma from proveedor where cod_proveedor='"+r.getString(5)+"'");
                String nombreEmpresa="";
                while(r2.next()){
                    nombreEmpresa=r2.getString(1);
                }
                tablaRepresentante.setValueAt(nombreEmpresa,i,1);  
                tablaRepresentante.setValueAt(r.getString(2),i,2);
                tablaRepresentante.setValueAt(r.getString(3),i,3);
                tablaRepresentante.setValueAt(r.getString(4),i,4);
                i++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la Base de Datos") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
        }
    }
    
    public void restaurarProveedor(){    
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            contador=0;
            for(int i=0;i<tablaPapelera.getRowCount();i++){  
                String pago2= tablaPapelera.getValueAt(i,0).toString();
                if(pago2.equals("true")){ 
                     consulta.executeUpdate("UPDATE proveedor SET estado='ACTIVO' where cod_proveedor='"+tablaPapelera.getValueAt(i,1).toString()+"'");
                     contador++;
                }
            }
            if (contador==1 && TodosSeleccionados==false){
                JOptionPane.showMessageDialog(null,"EL PROVEEDOR SE RESTAURO");
            }else{
                 if (contador>=1 && TodosSeleccionados==false){
                    JOptionPane.showMessageDialog(null,"LOS PROVEEDORES SE RESTAURARON");
                }
                 else{
                    if (contador==1 && TodosSeleccionados==true){
                        JOptionPane.showMessageDialog(null,"EL PROVEEDOR SE RESTAURO");
                    }else{
                        if (contador>=1 && TodosSeleccionados==true){
                            JOptionPane.showMessageDialog(null,"LOS PROVEEDORES SE RESTAURARON");
                        }
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarProveedoresPapelera();
        MostrarProveedor();
    }
    
    public void eliminarProveedor(){      
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            contador=0;
            for(int i=0;i<tablaPapelera.getRowCount();i++){  
                String pago2= tablaPapelera.getValueAt(i,0).toString();
                if(pago2.equals("true")){ 
                    consulta.executeUpdate("DELETE FROM proveedor WHERE cod_proveedor='"+tablaPapelera.getValueAt(i,1).toString()+"'");
                    contador++;
                }
            }
            if (contador==1 && TodosSeleccionados==false){
                JOptionPane.showMessageDialog(null,"EL PROVEEDOR FUE ELIMINADO DEL SISTEMA");
            }else{
                if (contador>=1 && TodosSeleccionados==false){
                    JOptionPane.showMessageDialog(null,"LOS PROVEEDORES FUERON ELIMINADOS DEL SISTEMA");
                }
                else{
                    if (contador==1 && TodosSeleccionados==true){
                        JOptionPane.showMessageDialog(null,"EL PROVEEDOR FUE ELIMINADO DEL SISTEMA");
                    }else{
                        if (contador>=1 && TodosSeleccionados==true){
                            JOptionPane.showMessageDialog(null,"LOS PROVEEDORES FUERON ELIMINADOS DEL SISTEMA");
                        }
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarProveedoresPapelera();
        MostrarProveedor();
    }
    
    public void eliminarRepresentante(int codigoRepresentante){ 
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql          
            consulta.executeUpdate("DELETE FROM representante_empresa WHERE cod_representante='"+codigoRepresentante+"'");                                       
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        menueliminar = new javax.swing.JMenuItem();
        FiltrarResultados = new javax.swing.ButtonGroup();
        Papelera = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPapelera = new javax.swing.JTable();
        checkApellidoPapelera = new javax.swing.JRadioButton();
        checkNombrePapelera = new javax.swing.JRadioButton();
        checkCodigoPapelera = new javax.swing.JRadioButton();
        txtBuscarPapelera = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        FiltrarResultadosPapelera = new javax.swing.ButtonGroup();
        PopMenuRepresentante = new javax.swing.JPopupMenu();
        modificarRepresentante = new javax.swing.JMenuItem();
        eliminarRepresentante = new javax.swing.JMenuItem();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtnombreEmpresa = new javax.swing.JTextField();
        txtcedula = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtResponsableEmpresa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDireccionEmpresa = new javax.swing.JTextField();
        txtCodigoPostal = new javax.swing.JTextField();
        txtProvincia = new javax.swing.JTextField();
        txtCuit = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        comboLocalidad = new javax.swing.JComboBox();
        txtIngresosBrutos = new javax.swing.JTextField();
        txtCbu = new javax.swing.JTextField();
        txtTelefonoEmpresa = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        btnAgregarRepresentante = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        comboContribuyente = new javax.swing.JComboBox();
        jLabel43 = new javax.swing.JLabel();
        btn_nuevoa = new javax.swing.JButton();
        btn_eliminara = new javax.swing.JButton();
        btn_guardara = new javax.swing.JButton();
        btn_cancelara = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        panelTablas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaproveedores = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaRepresentante = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        checkCuit = new javax.swing.JRadioButton();
        checkEmpresa = new javax.swing.JRadioButton();
        checkLocalidad = new javax.swing.JRadioButton();
        panelRepresentante = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        txtCodigoEmpresaRepresentante = new javax.swing.JTextField();
        txtCodigoRepresentante = new javax.swing.JTextField();
        txtNombreEmpresaRepresentante = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtnombreRepresentante = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtTelefonoRepresentante = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtEmailRepresentante = new javax.swing.JTextField();
        btn_guardara1 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        btn_eliminara1 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        btn_cancelara1 = new javax.swing.JButton();

        menueliminar.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        menueliminar.setText("Eliminar");
        menueliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menueliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menueliminar);

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
        jScrollPane2.setViewportView(tablaPapelera);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 990, 360));

        FiltrarResultadosPapelera.add(checkApellidoPapelera);
        checkApellidoPapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkApellidoPapelera.setText("Apellido");
        checkApellidoPapelera.setOpaque(false);
        jPanel2.add(checkApellidoPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 60, -1, -1));

        FiltrarResultadosPapelera.add(checkNombrePapelera);
        checkNombrePapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkNombrePapelera.setText("Nombre");
        checkNombrePapelera.setOpaque(false);
        jPanel2.add(checkNombrePapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, -1, -1));

        FiltrarResultadosPapelera.add(checkCodigoPapelera);
        checkCodigoPapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCodigoPapelera.setText("Código");
        checkCodigoPapelera.setOpaque(false);
        checkCodigoPapelera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCodigoPapeleraActionPerformed(evt);
            }
        });
        jPanel2.add(checkCodigoPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, -1, -1));

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
        jPanel2.add(txtBuscarPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 280, 37));

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel14.setText("PAPELERA DE RECICLAJE");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, -1));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 140, 260, 17));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, -1, 80));

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
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 560, 130, 102));

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
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 560, 170, 102));

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

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel2.setText("Eliminar");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 650, -1, -1));

        jLabel23.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel23.setText("Ir hacia atras");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 650, -1, -1));

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
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 560, -1, 102));

        jLabel25.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel25.setText("Restaurar");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, -1, -1));

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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
        );
        PapeleraLayout.setVerticalGroup(
            PapeleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        modificarRepresentante.setText("MODIFICAR");
        modificarRepresentante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarRepresentanteActionPerformed(evt);
            }
        });
        PopMenuRepresentante.add(modificarRepresentante);

        eliminarRepresentante.setText("ELIMINAR");
        eliminarRepresentante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarRepresentanteActionPerformed(evt);
            }
        });
        PopMenuRepresentante.add(eliminarRepresentante);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ingreso de provedores");

        panelImage1.setBackground(new java.awt.Color(255, 255, 255));
        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoMenumar22.png"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(239, 255, 239));
        jLabel5.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel5.setText("Empresa");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 100, -1));

        txtnombreEmpresa.setBackground(new java.awt.Color(204, 204, 204));
        txtnombreEmpresa.setFont(new java.awt.Font("Calibri", 0, 21)); // NOI18N
        txtnombreEmpresa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnombreEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreEmpresaActionPerformed(evt);
            }
        });
        txtnombreEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnombreEmpresaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreEmpresaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreEmpresaKeyTyped(evt);
            }
        });
        jPanel1.add(txtnombreEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 250, 30));

        txtcedula.setBackground(new java.awt.Color(204, 204, 204));
        txtcedula.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcedulaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcedulaKeyTyped(evt);
            }
        });
        jPanel1.add(txtcedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 500, 40, 20));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel7.setText("Contribuyente");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 150, 30));

        txtResponsableEmpresa.setBackground(new java.awt.Color(204, 204, 204));
        txtResponsableEmpresa.setFont(new java.awt.Font("Calibri", 0, 21)); // NOI18N
        txtResponsableEmpresa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtResponsableEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtResponsableEmpresaKeyReleased(evt);
            }
        });
        jPanel1.add(txtResponsableEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 250, 30));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel6.setText("Responsable");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 160, -1));

        txtDireccionEmpresa.setBackground(new java.awt.Color(204, 204, 204));
        txtDireccionEmpresa.setFont(new java.awt.Font("Calibri", 0, 21)); // NOI18N
        txtDireccionEmpresa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDireccionEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionEmpresaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionEmpresaKeyTyped(evt);
            }
        });
        jPanel1.add(txtDireccionEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 250, 30));

        txtCodigoPostal.setBackground(new java.awt.Color(204, 204, 204));
        txtCodigoPostal.setFont(new java.awt.Font("Calibri", 0, 21)); // NOI18N
        txtCodigoPostal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoPostal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoPostalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoPostalKeyTyped(evt);
            }
        });
        jPanel1.add(txtCodigoPostal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 250, 30));

        txtProvincia.setBackground(new java.awt.Color(204, 204, 204));
        txtProvincia.setFont(new java.awt.Font("Calibri", 0, 21)); // NOI18N
        txtProvincia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProvincia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProvinciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProvinciaKeyTyped(evt);
            }
        });
        jPanel1.add(txtProvincia, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 250, 30));

        txtCuit.setBackground(new java.awt.Color(204, 204, 204));
        txtCuit.setFont(new java.awt.Font("Calibri", 0, 21)); // NOI18N
        txtCuit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCuitKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuitKeyTyped(evt);
            }
        });
        jPanel1.add(txtCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 250, 30));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel9.setText("Ciudad");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 80, 30));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel11.setText("Dirección");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 110, 30));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel12.setText("Código postal");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 150, 30));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 32)); // NOI18N
        jLabel4.setText("INGRESO DE PROVEEDORES");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 380, 40));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoProveedoresXs-01.png"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel40.setText("*");
        jPanel1.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 30, 20));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel41.setText("*");
        jPanel1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 30, 20));

        comboLocalidad.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        comboLocalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLocalidadActionPerformed(evt);
            }
        });
        jPanel1.add(comboLocalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 250, 30));

        txtIngresosBrutos.setBackground(new java.awt.Color(204, 204, 204));
        txtIngresosBrutos.setFont(new java.awt.Font("Calibri", 0, 21)); // NOI18N
        txtIngresosBrutos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIngresosBrutos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIngresosBrutosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIngresosBrutosKeyTyped(evt);
            }
        });
        jPanel1.add(txtIngresosBrutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 250, 30));

        txtCbu.setBackground(new java.awt.Color(204, 204, 204));
        txtCbu.setFont(new java.awt.Font("Calibri", 0, 21)); // NOI18N
        txtCbu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCbuKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCbuKeyTyped(evt);
            }
        });
        jPanel1.add(txtCbu, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 250, 30));

        txtTelefonoEmpresa.setBackground(new java.awt.Color(204, 204, 204));
        txtTelefonoEmpresa.setFont(new java.awt.Font("Calibri", 0, 21)); // NOI18N
        txtTelefonoEmpresa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefonoEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoEmpresaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoEmpresaKeyTyped(evt);
            }
        });
        jPanel1.add(txtTelefonoEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, 250, 30));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel10.setText("NIT/RUT");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 100, 30));

        jLabel28.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel28.setText("I. Brutos");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 110, 30));

        jLabel29.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel29.setText("CBU");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 60, 30));

        jLabel30.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel30.setText("Télefono");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 110, 30));

        btnAgregarRepresentante.setBackground(new java.awt.Color(93, 116, 163));
        btnAgregarRepresentante.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btnAgregarRepresentante.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarRepresentante.setText("+");
        btnAgregarRepresentante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarRepresentanteActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarRepresentante, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 500, 50, 40));

        jLabel36.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel36.setText("Representante");
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 500, 170, 30));

        comboContribuyente.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        comboContribuyente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboContribuyenteActionPerformed(evt);
            }
        });
        jPanel1.add(comboContribuyente, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 250, 30));

        jLabel43.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel43.setText("Provincia");
        jPanel1.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 100, 30));

        panelImage1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 430, 540));

        btn_nuevoa.setBackground(new java.awt.Color(51, 153, 255));
        btn_nuevoa.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        btn_nuevoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnNuevo11.png"))); // NOI18N
        btn_nuevoa.setBorder(null);
        btn_nuevoa.setBorderPainted(false);
        btn_nuevoa.setContentAreaFilled(false);
        btn_nuevoa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_nuevoa.setFocusCycleRoot(true);
        btn_nuevoa.setFocusable(false);
        btn_nuevoa.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnNuevo11.png"))); // NOI18N
        btn_nuevoa.setRequestFocusEnabled(false);
        btn_nuevoa.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnNuevoHover11.png"))); // NOI18N
        btn_nuevoa.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnNuevoHover11.png"))); // NOI18N
        btn_nuevoa.setVerifyInputWhenFocusTarget(false);
        btn_nuevoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoaActionPerformed(evt);
            }
        });
        panelImage1.add(btn_nuevoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 540, 210, 120));

        btn_eliminara.setBackground(new java.awt.Color(51, 153, 255));
        btn_eliminara.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_eliminara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnEliminar.png"))); // NOI18N
        btn_eliminara.setBorder(null);
        btn_eliminara.setBorderPainted(false);
        btn_eliminara.setContentAreaFilled(false);
        btn_eliminara.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        panelImage1.add(btn_eliminara, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 540, 120, 120));

        btn_guardara.setBackground(new java.awt.Color(51, 153, 255));
        btn_guardara.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_guardara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardar.png"))); // NOI18N
        btn_guardara.setBorder(null);
        btn_guardara.setBorderPainted(false);
        btn_guardara.setContentAreaFilled(false);
        btn_guardara.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_guardara.setFocusCycleRoot(true);
        btn_guardara.setFocusable(false);
        btn_guardara.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btn_guardara.setRequestFocusEnabled(false);
        btn_guardara.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btn_guardara.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btn_guardara.setVerifyInputWhenFocusTarget(false);
        btn_guardara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardaraActionPerformed(evt);
            }
        });
        panelImage1.add(btn_guardara, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 170, 120));

        btn_cancelara.setBackground(new java.awt.Color(51, 153, 255));
        btn_cancelara.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_cancelara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelar.png"))); // NOI18N
        btn_cancelara.setBorder(null);
        btn_cancelara.setBorderPainted(false);
        btn_cancelara.setContentAreaFilled(false);
        btn_cancelara.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        panelImage1.add(btn_cancelara, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 540, 170, 120));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel13.setText("Nuevo");
        panelImage1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 650, -1, 30));

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel16.setText("Guardar");
        panelImage1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 640, -1, 50));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel17.setText("a papelera");
        panelImage1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 660, -1, 50));

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel18.setText("Cancelar");
        panelImage1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 650, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoProveedoresXs-01.png"))); // NOI18N
        panelImage1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 50, 50));

        jLabel20.setFont(new java.awt.Font("Calibri", 1, 32)); // NOI18N
        jLabel20.setText("INGRESO DE PROVEEDORES");
        panelImage1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 380, 40));

        jLabel21.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel21.setText("Mover");
        panelImage1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 640, -1, 40));

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
        panelImage1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 540, 100, 100));

        jLabel26.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel26.setText("Papelera");
        panelImage1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 630, 100, -1));

        jLabel27.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel27.setText("de reciclaje");
        panelImage1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 650, -1, -1));

        panelTablas.setBackground(new java.awt.Color(255, 255, 255));
        panelTablas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaproveedores.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        tablaproveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COD", "EMPRESA", "RESPONSABLE", "DIRECCIÓN", "LOCALIDAD", "C.P", "PROV", "CUIT/L", "I BRUTOS", "CBU", "TEL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaproveedores.setComponentPopupMenu(jPopupMenu1);
        tablaproveedores.setOpaque(false);
        tablaproveedores.setRowHeight(25);
        tablaproveedores.setRowMargin(4);
        tablaproveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaproveedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaproveedores);

        panelTablas.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 620, 290));

        tablaRepresentante.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        tablaRepresentante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "EMPRESA", "NOMBRE", "TELEFONO", "EMAIL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaRepresentante.setComponentPopupMenu(PopMenuRepresentante);
        tablaRepresentante.setRowHeight(25);
        tablaRepresentante.setRowMargin(4);
        tablaRepresentante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaRepresentanteMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaRepresentante);

        panelTablas.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 620, 110));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel8.setText("Representantes");
        panelTablas.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 170, 40));

        jTextField1.setText("jTextField1");
        panelTablas.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 250, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        panelTablas.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, 80));

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
        panelTablas.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 260, 40));
        panelTablas.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 260, 10));

        FiltrarResultados.add(checkCuit);
        checkCuit.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkCuit.setText("NIT/RUT");
        checkCuit.setOpaque(false);
        checkCuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCuitActionPerformed(evt);
            }
        });
        panelTablas.add(checkCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        FiltrarResultados.add(checkEmpresa);
        checkEmpresa.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkEmpresa.setText("Empresa");
        checkEmpresa.setOpaque(false);
        panelTablas.add(checkEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        FiltrarResultados.add(checkLocalidad);
        checkLocalidad.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkLocalidad.setText("Ciudad");
        checkLocalidad.setOpaque(false);
        panelTablas.add(checkLocalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        panelImage1.add(panelTablas, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 640, 550));

        panelRepresentante.setBackground(new java.awt.Color(255, 255, 255));
        panelRepresentante.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesXs-01.png"))); // NOI18N
        jLabel37.setText(" Representante");
        panelRepresentante.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, -1));

        txtCodigoEmpresaRepresentante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoEmpresaRepresentanteActionPerformed(evt);
            }
        });
        panelRepresentante.add(txtCodigoEmpresaRepresentante, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 50, 40));

        txtCodigoRepresentante.setBackground(new java.awt.Color(204, 204, 204));
        txtCodigoRepresentante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoRepresentanteActionPerformed(evt);
            }
        });
        panelRepresentante.add(txtCodigoRepresentante, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 50, 40));

        txtNombreEmpresaRepresentante.setBackground(new java.awt.Color(204, 204, 204));
        txtNombreEmpresaRepresentante.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtNombreEmpresaRepresentante.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreEmpresaRepresentante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreEmpresaRepresentanteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreEmpresaRepresentanteKeyTyped(evt);
            }
        });
        panelRepresentante.add(txtNombreEmpresaRepresentante, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 280, 40));

        jLabel31.setBackground(new java.awt.Color(239, 255, 239));
        jLabel31.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel31.setText("Empresa");
        panelRepresentante.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 80, 30));

        jLabel32.setBackground(new java.awt.Color(239, 255, 239));
        jLabel32.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel32.setText("Apellido y Nombre");
        panelRepresentante.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, 30));

        txtnombreRepresentante.setBackground(new java.awt.Color(204, 204, 204));
        txtnombreRepresentante.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtnombreRepresentante.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnombreRepresentante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreRepresentanteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreRepresentanteKeyTyped(evt);
            }
        });
        panelRepresentante.add(txtnombreRepresentante, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 280, 40));

        jLabel35.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        jLabel35.setText("*");
        panelRepresentante.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, 20, 30));

        txtTelefonoRepresentante.setBackground(new java.awt.Color(204, 204, 204));
        txtTelefonoRepresentante.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtTelefonoRepresentante.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefonoRepresentante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoRepresentanteKeyTyped(evt);
            }
        });
        panelRepresentante.add(txtTelefonoRepresentante, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 280, 40));

        jLabel33.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel33.setText(" Teléfono");
        panelRepresentante.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 90, 30));

        jLabel38.setBackground(new java.awt.Color(239, 255, 239));
        jLabel38.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel38.setText("Email");
        panelRepresentante.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 50, -1));

        txtEmailRepresentante.setBackground(new java.awt.Color(204, 204, 204));
        txtEmailRepresentante.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtEmailRepresentante.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmailRepresentante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailRepresentanteActionPerformed(evt);
            }
        });
        txtEmailRepresentante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailRepresentanteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailRepresentanteKeyTyped(evt);
            }
        });
        panelRepresentante.add(txtEmailRepresentante, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 280, 40));

        btn_guardara1.setBackground(new java.awt.Color(51, 153, 255));
        btn_guardara1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_guardara1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonRestaurar.png"))); // NOI18N
        btn_guardara1.setBorder(null);
        btn_guardara1.setBorderPainted(false);
        btn_guardara1.setContentAreaFilled(false);
        btn_guardara1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_guardara1.setFocusCycleRoot(true);
        btn_guardara1.setFocusable(false);
        btn_guardara1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonRestaurarHover.png"))); // NOI18N
        btn_guardara1.setRequestFocusEnabled(false);
        btn_guardara1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonRestaurarHover.png"))); // NOI18N
        btn_guardara1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonRestaurarHover.png"))); // NOI18N
        btn_guardara1.setVerifyInputWhenFocusTarget(false);
        btn_guardara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardara1ActionPerformed(evt);
            }
        });
        panelRepresentante.add(btn_guardara1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 370, 210, 90));

        jLabel39.setBackground(new java.awt.Color(239, 255, 239));
        jLabel39.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel39.setText("Guardar");
        panelRepresentante.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 470, 70, -1));

        btn_eliminara1.setBackground(new java.awt.Color(51, 153, 255));
        btn_eliminara1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_eliminara1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBorrar.png"))); // NOI18N
        btn_eliminara1.setBorder(null);
        btn_eliminara1.setBorderPainted(false);
        btn_eliminara1.setContentAreaFilled(false);
        btn_eliminara1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_eliminara1.setFocusCycleRoot(true);
        btn_eliminara1.setFocusable(false);
        btn_eliminara1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBorrarHover.png"))); // NOI18N
        btn_eliminara1.setRequestFocusEnabled(false);
        btn_eliminara1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBorrarHover.png"))); // NOI18N
        btn_eliminara1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBorrarHover.png"))); // NOI18N
        btn_eliminara1.setVerifyInputWhenFocusTarget(false);
        btn_eliminara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminara1ActionPerformed(evt);
            }
        });
        panelRepresentante.add(btn_eliminara1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 210, 90));

        jLabel34.setBackground(new java.awt.Color(239, 255, 239));
        jLabel34.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel34.setText("Eliminar");
        panelRepresentante.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 470, 90, -1));

        jLabel42.setBackground(new java.awt.Color(239, 255, 239));
        jLabel42.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel42.setText("Atrás");
        panelRepresentante.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 470, 60, -1));

        btn_cancelara1.setBackground(new java.awt.Color(51, 153, 255));
        btn_cancelara1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_cancelara1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtras.png"))); // NOI18N
        btn_cancelara1.setBorder(null);
        btn_cancelara1.setBorderPainted(false);
        btn_cancelara1.setContentAreaFilled(false);
        btn_cancelara1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_cancelara1.setFocusCycleRoot(true);
        btn_cancelara1.setFocusable(false);
        btn_cancelara1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtrasHover.png"))); // NOI18N
        btn_cancelara1.setRequestFocusEnabled(false);
        btn_cancelara1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtrasHover.png"))); // NOI18N
        btn_cancelara1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonAtrasHover.png"))); // NOI18N
        btn_cancelara1.setVerifyInputWhenFocusTarget(false);
        btn_cancelara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelara1ActionPerformed(evt);
            }
        });
        panelRepresentante.add(btn_cancelara1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 370, 210, 90));

        panelImage1.add(panelRepresentante, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 630, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 1098, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnombreEmpresaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreEmpresaKeyReleased
        txtnombreEmpresa.setText (txtnombreEmpresa.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreEmpresaKeyReleased

    private void txtnombreEmpresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreEmpresaKeyTyped
        Character c;
        c = evt.getKeyChar();
        if(!Character.isLetter(c) && c != com.sun.glass.events.KeyEvent.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_txtnombreEmpresaKeyTyped

    private void txtcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();// TODO add your handling code here:
    }//GEN-LAST:event_txtcedulaKeyTyped

    private void txtResponsableEmpresaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResponsableEmpresaKeyReleased
        txtResponsableEmpresa.setText (txtResponsableEmpresa.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtResponsableEmpresaKeyReleased

    private void txtcedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyPressed

    }//GEN-LAST:event_txtcedulaKeyPressed

    private void txtcedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyReleased

    }//GEN-LAST:event_txtcedulaKeyReleased

    private void txtnombreEmpresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreEmpresaKeyPressed

    }//GEN-LAST:event_txtnombreEmpresaKeyPressed

    private void txtDireccionEmpresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionEmpresaKeyTyped

    }//GEN-LAST:event_txtDireccionEmpresaKeyTyped

    private void txtCodigoPostalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoPostalKeyTyped
          Character c;
        c = evt.getKeyChar();
        if(!Character.isLetter(c) && c != com.sun.glass.events.KeyEvent.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoPostalKeyTyped

    private void txtProvinciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProvinciaKeyTyped

    }//GEN-LAST:event_txtProvinciaKeyTyped

    private void txtCuitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuitKeyTyped
         char c = evt.getKeyChar();
         if(c<'0'|| c>'9')
           evt.consume();
    }//GEN-LAST:event_txtCuitKeyTyped

    private void menueliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menueliminarActionPerformed
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            consulta.executeUpdate("UPDATE proveedor SET estado='INACTIVO' where cod_proveedor='"+txtcedula.getText()+"'");
            JOptionPane.showMessageDialog(null,"EL PROVEEDOR SE MOVIÓ A LA PAPELERA DE RECICLAJE");
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        nuevoo();
        MostrarProveedor();
        MostrarProveedoresPapelera();     
    }//GEN-LAST:event_menueliminarActionPerformed

    private void tablaproveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaproveedoresMouseClicked
        //CUANDO CLICKEO LA TABLA  PROVEEDORES SETEO LOS TEXTFIELD
        nuevoo();
        
        Select=tablaproveedores.getSelectedRow();
        txtcedula.setText( tablaproveedores.getValueAt(Select,0).toString());
        txtnombreEmpresa.setText( tablaproveedores.getValueAt(Select,1).toString());
        txtResponsableEmpresa.setText( tablaproveedores.getValueAt(Select,2).toString());
        txtDireccionEmpresa.setText( tablaproveedores.getValueAt(Select,3).toString());
        comboLocalidad.setSelectedItem(tablaproveedores.getValueAt(Select,4).toString());
        txtCodigoPostal.setText( tablaproveedores.getValueAt(Select,5).toString());
        txtProvincia.setText( tablaproveedores.getValueAt(Select,6).toString());    
        txtCuit.setText( tablaproveedores.getValueAt(Select,7).toString());
        txtIngresosBrutos.setText( tablaproveedores.getValueAt(Select,8).toString());
        txtCbu.setText( tablaproveedores.getValueAt(Select,9).toString());
        txtTelefonoEmpresa.setText( tablaproveedores.getValueAt(Select,10).toString());
        
        mostrarRepresentante(Integer.parseInt(txtcedula.getText()));

    }//GEN-LAST:event_tablaproveedoresMouseClicked

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed

    }//GEN-LAST:event_txtBuscarActionPerformed

    
    //FILTRO DE BUSQUEDA 
    DefaultTableModel dm;
    /* Método filtro*/
    private void filtro2(String consulta, JTable jtableBuscar){  
        dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter(dm);
        jtableBuscar.setRowSorter(tr);

        int columna=0;
        // Identificamos cual es el JRadioButton seleccionado para filtrar el
        // resultado de acuerdo a los datos de la columna elegida
        if (checkCuit.isSelected()) {
            columna = 7;
        }else 
            if (checkEmpresa.isSelected()) {
                 columna = 1;
            }else
                if (checkLocalidad.isSelected()) {
                    columna = 4;
                }
        tr.setRowFilter(RowFilter.regexFilter(consulta, columna));
    }
    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if (FiltrarResultados.getSelection()==null) {
            // Si ninguno de los JRadioButtons está seleccionado, evitamos que se
            // escriba algo dentro del JTextField y mostramos un mensaje de error
            evt.consume();
            JOptionPane.showMessageDialog(this, "Debe seleccionar una opción para filtrar los resultados", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            txtBuscar.setText("");
            txtBuscar.transferFocus();
        }else{
            filtro2(txtBuscar.getText().toUpperCase(), tablaproveedores);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void checkCuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCuitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCuitActionPerformed

    private void txtCodigoPostalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoPostalKeyReleased
        txtCodigoPostal.setText (txtCodigoPostal.getText().toUpperCase());
    }//GEN-LAST:event_txtCodigoPostalKeyReleased

    private void txtProvinciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProvinciaKeyReleased
        txtProvincia.setText (txtProvincia.getText().toUpperCase());
    }//GEN-LAST:event_txtProvinciaKeyReleased

    private void txtCuitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuitKeyReleased
        txtCuit.setText (txtCuit.getText().toUpperCase());
    }//GEN-LAST:event_txtCuitKeyReleased

    private void txtDireccionEmpresaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionEmpresaKeyReleased
        txtDireccionEmpresa.setText(txtDireccionEmpresa.getText().toUpperCase());  
    }//GEN-LAST:event_txtDireccionEmpresaKeyReleased

    private void btn_nuevoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoaActionPerformed
        nuevoo();
    }//GEN-LAST:event_btn_nuevoaActionPerformed

    private void btn_eliminaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminaraActionPerformed
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            consulta.executeUpdate("UPDATE proveedor SET estado='INACTIVO' where cod_proveedor='"+txtcedula.getText()+"'");
            JOptionPane.showMessageDialog(null,"EL PROVEEDOR SE MOVIÓ A LA PAPELERA DE RECICLAJE");
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        nuevoo();
        MostrarProveedor();
        MostrarProveedoresPapelera();
    }//GEN-LAST:event_btn_eliminaraActionPerformed

    private void btn_guardaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardaraActionPerformed
        //GUARDO LOS PROVEEDORES
        txtnombreEmpresa.setBackground(new Color(204,204,204));
        txtResponsableEmpresa.setBackground(new Color(204,204,204));
        txtDireccionEmpresa.setBackground(new Color(204,204,204));
        
        if (txtnombreEmpresa.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta el nombre de la empresa ");
            txtnombreEmpresa.setBackground(Color.yellow);
            txtnombreEmpresa.requestFocus();
        }else
            if (txtDireccionEmpresa.getText().equals("")) {
               JOptionPane.showMessageDialog(null, "Falta la direccion del proveedor");
               txtDireccionEmpresa.setBackground(Color.yellow);
               txtDireccionEmpresa.requestFocus();
            }else
                try {
                    Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                    Statement consulta = conn.createStatement();        
                    Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
                    ResultSet r= consulta1.executeQuery("select cod_proveedor from proveedor");
                    int bandera=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
                    while(r.next()){
                        if(txtcedula.getText().equals(r.getString(1))){//si el codigo de la tabla es igual a algun codigo de la base de datos
                            bandera=1;
                        }
                    }
                    String localidad="";
                    if(!comboLocalidad.getSelectedItem().equals("SELECCIONE LOCALIDAD")){
                         localidad=comboLocalidad.getSelectedItem().toString();
                    }
                    String contribuyente;
                    if(comboContribuyente.getSelectedItem().toString().equals("Seleccionar")){
                        contribuyente="";
                    }else{
                       contribuyente=comboContribuyente.getSelectedItem().toString();
                    }
                
                    if(bandera==0){//Si el codigo no esta inserto una nuevo registro a la base de datos
                        consulta.executeUpdate("insert into proveedor (nombre_firma,responsable_firma,direccion_firma,ciudad_firma,cod_postal_firma,provincia_firma,cuit,ingresos_brutos,cbu,telefono_firma,estado,condicion)  values('"+txtnombreEmpresa.getText()+"','"+txtResponsableEmpresa.getText()+"','"+txtDireccionEmpresa.getText()+"','"+localidad+"','"+txtCodigoPostal.getText()+"','"+txtProvincia.getText()+"','"+txtCuit.getText()+"','"+txtIngresosBrutos.getText()+"','"+txtCbu.getText()+"','"+txtTelefonoEmpresa.getText()+"','ACTIVO','"+contribuyente+"')");      
                    }else{//si el codigo esta,actualizo la informacion del producto
                         consulta.executeUpdate("UPDATE proveedor SET nombre_firma='"+txtnombreEmpresa.getText()+"',responsable_firma='"+txtResponsableEmpresa.getText()+"',direccion_firma='"+txtDireccionEmpresa.getText()+"',ciudad_firma='"+localidad+"',cod_postal_firma='"+txtCodigoPostal.getText()+"',provincia_firma='"+txtProvincia.getText()+"',cuit='"+txtCuit.getText()+"',ingresos_brutos='"+txtIngresosBrutos.getText()+"',cbu='"+txtCbu.getText()+"',telefono_firma='"+txtTelefonoEmpresa.getText()+"' ,condicion='"+contribuyente+"' WHERE cod_proveedor='"+txtcedula.getText()+"'");               
                    }
                    JOptionPane.showMessageDialog(null, "Los datos han sido Guardados Correctamente");
                    nuevoo();
                    MostrarProveedor();
                }catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"El PROVEEDOR"+" "+ txtcedula.getText()+" "+"DE NOMBRE"+" "+txtnombreEmpresa.getText()+" "+"YA EXISTE");
                    System.out.println(ex);
                }
    }//GEN-LAST:event_btn_guardaraActionPerformed

    private void btn_cancelaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelaraActionPerformed
        desactivar();
    }//GEN-LAST:event_btn_cancelaraActionPerformed

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

    //FILTRO BUSQUEDA PAPELERA
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
        }else 
            if (checkNombrePapelera.isSelected()) {
                 columna = 2;
            }else
                if (checkApellidoPapelera.isSelected()) {
                    columna = 3;
                }
        tr.setRowFilter(RowFilter.regexFilter(consulta, columna));
    }
    
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        restaurarProveedor();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        TodosSeleccionados=true;
        seleccionarTodos=1;
        MostrarProveedoresPapelera();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        eliminarProveedor();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        Papelera.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboLocalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLocalidadActionPerformed
        String localidad= comboLocalidad.getSelectedItem().toString();
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet   res= consulta.executeQuery("select * from localidad where localidad like '"+localidad+"'");    
            while(res.next()){
                txtCodigoPostal.setText(res.getString(4));
                txtProvincia.setText(res.getString(3));
            }
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
    }//GEN-LAST:event_comboLocalidadActionPerformed

    private void txtIngresosBrutosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngresosBrutosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIngresosBrutosKeyReleased

    private void txtIngresosBrutosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngresosBrutosKeyTyped
         char c = evt.getKeyChar();
         if(c<'0'|| c>'9')
           evt.consume();
    }//GEN-LAST:event_txtIngresosBrutosKeyTyped

    private void txtCbuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCbuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCbuKeyReleased

    private void txtCbuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCbuKeyTyped
         char c = evt.getKeyChar();
         if(c<'0'|| c>'9')
           evt.consume();
    }//GEN-LAST:event_txtCbuKeyTyped

    private void txtTelefonoEmpresaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoEmpresaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoEmpresaKeyReleased

    private void txtTelefonoEmpresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoEmpresaKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();
    }//GEN-LAST:event_txtTelefonoEmpresaKeyTyped

    private void txtnombreEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreEmpresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreEmpresaActionPerformed

    private void btnAgregarRepresentanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarRepresentanteActionPerformed
        //AGREGO UN REPRESENTANTE      
        txtnombreEmpresa.setBackground(new Color(204,204,204));
        txtResponsableEmpresa.setBackground(new Color(204,204,204));
        txtDireccionEmpresa.setBackground(new Color(204,204,204));
        
        if (txtnombreEmpresa.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Falta el nombre del proveedor ");
            txtnombreEmpresa.setBackground(Color.yellow);
            txtnombreEmpresa.requestFocus();
        }else{
            if (txtDireccionEmpresa.getText().equals("")) {
               JOptionPane.showMessageDialog(null, "Falta la direccion del proveedor");
               txtDireccionEmpresa.setBackground(Color.yellow);
               txtDireccionEmpresa.requestFocus();
            }else{
                try {
                    Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                    Statement consulta = conn.createStatement();
                                 
                    Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
                    ResultSet r= consulta1.executeQuery("select cod_proveedor from proveedor");
                    int bandera=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
                    while(r.next()){
                        if(txtcedula.getText().equals(r.getString(1))){//si el codigo de la tabla es igual a algun codigo de la base de datos
                            bandera=1;
                        }
                    }
                    r.close();
                    
                    String localidad="";
                    if(!comboLocalidad.getSelectedItem().equals("SELECCIONE LOCALIDAD")){
                        localidad=comboLocalidad.getSelectedItem().toString();
                    }
                    String contribuyente;
                    if(comboContribuyente.getSelectedItem().toString().equals("Seleccionar")){
                        contribuyente="";
                    }else{
                       contribuyente=comboContribuyente.getSelectedItem().toString();
                    }

                    if(bandera==0){//Si el codigo no esta inserto una nuevo registro a la base de datos
                        consulta.executeUpdate("insert into proveedor (nombre_firma,responsable_firma,direccion_firma,ciudad_firma,cod_postal_firma,provincia_firma,cuit,ingresos_brutos,cbu,telefono_firma,estado,condicion)  values('"+txtnombreEmpresa.getText()+"','"+txtResponsableEmpresa.getText()+"','"+txtDireccionEmpresa.getText()+"','"+localidad+"','"+txtCodigoPostal.getText()+"','"+txtProvincia.getText()+"','"+txtCuit.getText()+"','"+txtIngresosBrutos.getText()+"','"+txtCbu.getText()+"','"+txtTelefonoEmpresa.getText()+"','ACTIVO','"+contribuyente+"')");      
                    }else{//si el codigo esta,actualizo la informacion del producto
                         consulta.executeUpdate("UPDATE proveedor SET nombre_firma='"+txtnombreEmpresa.getText()+"',responsable_firma='"+txtResponsableEmpresa.getText()+"',direccion_firma='"+txtDireccionEmpresa.getText()+"',ciudad_firma='"+localidad+"',cod_postal_firma='"+txtCodigoPostal.getText()+"',provincia_firma='"+txtProvincia.getText()+"',cuit='"+txtCuit.getText()+"',ingresos_brutos='"+txtIngresosBrutos.getText()+"',cbu='"+txtCbu.getText()+"',telefono_firma='"+txtTelefonoEmpresa.getText()+"' ,condicion='"+contribuyente+"' WHERE cod_proveedor='"+txtcedula.getText()+"'");               
                    }
                    
                    Statement consulta2=conn.createStatement(); // crea una variable que se encargue del código de sql
                    ResultSet r22= consulta2.executeQuery("select cod_proveedor from proveedor where nombre_firma like'"+txtnombreEmpresa.getText()+"' and direccion_firma like'"+txtDireccionEmpresa.getText()+"'");
                    String codigo_proveedor="";
                    while(r22.next()){
                        codigo_proveedor=r22.getString(1);
                    }
                    r22.close();
                    txtCodigoEmpresaRepresentante.setText(codigo_proveedor);
                    txtNombreEmpresaRepresentante.setText(txtnombreEmpresa.getText());  
                    txtnombreRepresentante.setText("");
                    txtTelefonoRepresentante.setText("");
                    txtEmailRepresentante.setText("");
                                     
                    panelTablas.setVisible(false);
                    panelRepresentante.setVisible(true);
                    txtnombreRepresentante.requestFocus();                 
                } 
                catch (SQLException ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null,"ERROR EN LA BASE DE DATOS,VUELVA A INTENATRLO NUEVAMENTE");
                }
            }
        }       
    }//GEN-LAST:event_btnAgregarRepresentanteActionPerformed

    private void txtEmailRepresentanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailRepresentanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailRepresentanteActionPerformed

    private void txtEmailRepresentanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailRepresentanteKeyReleased
        txtEmailRepresentante.setText (txtEmailRepresentante.getText().toLowerCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailRepresentanteKeyReleased

    private void txtEmailRepresentanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailRepresentanteKeyTyped

    }//GEN-LAST:event_txtEmailRepresentanteKeyTyped

    private void txtTelefonoRepresentanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoRepresentanteKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
            evt.consume();
    }//GEN-LAST:event_txtTelefonoRepresentanteKeyTyped

    private void txtnombreRepresentanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreRepresentanteKeyReleased
        txtnombreRepresentante.setText (txtnombreRepresentante.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreRepresentanteKeyReleased

    private void txtnombreRepresentanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreRepresentanteKeyTyped
        Character c;
        c = evt.getKeyChar();
        if(!Character.isLetter(c) && c != com.sun.glass.events.KeyEvent.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_txtnombreRepresentanteKeyTyped

    private void txtNombreEmpresaRepresentanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreEmpresaRepresentanteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreEmpresaRepresentanteKeyReleased

    private void txtNombreEmpresaRepresentanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreEmpresaRepresentanteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreEmpresaRepresentanteKeyTyped

    private void btn_guardara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardara1ActionPerformed
        txtnombreRepresentante.setBackground(new Color(204,204,204));
        txtTelefonoRepresentante.setBackground(new Color(204,204,204));
        
        if(txtnombreRepresentante.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta el nombre del representante ");
            txtnombreRepresentante.setBackground(Color.yellow);
            txtnombreRepresentante.requestFocus();
        }else
            if(txtTelefonoRepresentante.getText().equals("")) {
               JOptionPane.showMessageDialog(null, "Falta el télefono del representante");
               txtTelefonoRepresentante.setBackground(Color.yellow);
               txtTelefonoRepresentante.requestFocus();
            }else
                try{
                    Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                    Statement consulta = conn.createStatement();
                                 
                    Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
                    ResultSet r= consulta1.executeQuery("select cod_representante from representante_empresa");
                    int bandera=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
                    while(r.next()){
                        if(txtCodigoRepresentante.getText().equals(r.getString(1))){//si el codigo de la tabla es igual a algun codigo de la base de datos
                            bandera=1;
                        }
                    }
                    if(bandera==0){//Si el codigo no esta inserto una nuevo registro a la base de datos
                        consulta.executeUpdate("insert into representante_empresa (nombre_apellido,telefono,email,empresa)  values('"+txtnombreRepresentante.getText()+"','"+txtTelefonoRepresentante.getText()+"','"+txtEmailRepresentante.getText().toLowerCase()+"','"+txtCodigoEmpresaRepresentante.getText()+"')");
                    }else{//si el codigo esta,actualizo la informacion del producto
                         consulta.executeUpdate("UPDATE representante_empresa SET nombre_apellido='"+txtnombreRepresentante.getText()+"',telefono='"+txtTelefonoRepresentante.getText()+"',email='"+txtEmailRepresentante.getText().toLowerCase()+"',empresa='"+txtCodigoEmpresaRepresentante.getText()+"' WHERE cod_representante='"+txtCodigoRepresentante.getText()+"'");               
                    }
                    JOptionPane.showMessageDialog(null, "Los datos han sido Guardados Correctamente");

                    MostrarProveedor();
                    mostrarRepresentante(Integer.parseInt(txtCodigoEmpresaRepresentante.getText()));
                    
                    panelRepresentante.setVisible(false);
                    panelTablas.setVisible(true);     
                } 
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"El PROVEEDOR"+" "+ txtcedula.getText()+" "+"DE NOMBRE"+" "+txtnombreEmpresa.getText()+" "+"YA EXISTE");
                    System.out.println(ex);
                }
    }//GEN-LAST:event_btn_guardara1ActionPerformed

    private void btn_cancelara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelara1ActionPerformed
        panelRepresentante.setVisible(false);
        panelTablas.setVisible(true);
        mostrarRepresentante(Integer.parseInt(txtcedula.getText()));     
    }//GEN-LAST:event_btn_cancelara1ActionPerformed

    private void txtCodigoRepresentanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoRepresentanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoRepresentanteActionPerformed

    private void txtCodigoEmpresaRepresentanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoEmpresaRepresentanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoEmpresaRepresentanteActionPerformed

    private void modificarRepresentanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarRepresentanteActionPerformed
      Select=tablaRepresentante.getSelectedRow();
        if(Select<0){
            JOptionPane.showMessageDialog(null,"No se ha seleccionado ninguna fila");
        }else{
            panelTablas.setVisible(false);
            panelRepresentante.setVisible(true);
        }
    }//GEN-LAST:event_modificarRepresentanteActionPerformed

    private void tablaRepresentanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRepresentanteMouseClicked
        Select=tablaRepresentante.getSelectedRow();
        if(Select<0){
            JOptionPane.showMessageDialog(null,"No se ha seleccionado ninguna fila");
        }else{
            txtCodigoRepresentante.setText( tablaRepresentante.getValueAt(Select,0).toString());
            txtCodigoEmpresaRepresentante.setText(txtcedula.getText());
            txtnombreRepresentante.setText( tablaRepresentante.getValueAt(Select,2).toString());       
            txtNombreEmpresaRepresentante.setText( tablaRepresentante.getValueAt(Select,1).toString());
            txtTelefonoRepresentante.setText( tablaRepresentante.getValueAt(Select,3).toString());
            txtEmailRepresentante.setText( tablaRepresentante.getValueAt(Select,4).toString());
            panelTablas.setVisible(false);
            panelRepresentante.setVisible(true);
            txtnombreRepresentante.requestFocus();        
        }
    }//GEN-LAST:event_tablaRepresentanteMouseClicked

    private void eliminarRepresentanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarRepresentanteActionPerformed
      
    }//GEN-LAST:event_eliminarRepresentanteActionPerformed

    private void btn_eliminara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminara1ActionPerformed
            int codigo=Integer.parseInt(txtCodigoRepresentante.getText());
            Object[] opciones ={"Aceptar","Cancelar"};
            int eleccion = JOptionPane.showOptionDialog(rootPane,"¿Esta seguro que desea eliminar el Representante?","Alerta",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
            if(eleccion ==JOptionPane.YES_OPTION){
                eliminarRepresentante(codigo);              
                panelRepresentante.setVisible(false);
                panelTablas.setVisible(true);
                int codigoEmpresa=Integer.parseInt(txtcedula.getText());
                mostrarRepresentante(codigoEmpresa);
            }else{

            }
    }//GEN-LAST:event_btn_eliminara1ActionPerformed

    private void comboContribuyenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboContribuyenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboContribuyenteActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        /* this.setVisible(false);
        MostrarArticulosPapelera();
        Papelera.setVisible(true);*/
    }//GEN-LAST:event_jButton7ActionPerformed


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
            java.util.logging.Logger.getLogger(Registrar_Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrar_Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrar_Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrar_Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Registrar_Proveedores dialog = new Registrar_Proveedores(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup FiltrarResultadosPapelera;
    private javax.swing.JDialog Papelera;
    private javax.swing.JPopupMenu PopMenuRepresentante;
    private javax.swing.JButton btnAgregarRepresentante;
    private javax.swing.JButton btn_cancelara;
    private javax.swing.JButton btn_cancelara1;
    private javax.swing.JButton btn_eliminara;
    private javax.swing.JButton btn_eliminara1;
    private javax.swing.JButton btn_guardara;
    private javax.swing.JButton btn_guardara1;
    private javax.swing.JButton btn_nuevoa;
    private javax.swing.JRadioButton checkApellidoPapelera;
    private javax.swing.JRadioButton checkCodigoPapelera;
    private javax.swing.JRadioButton checkCuit;
    private javax.swing.JRadioButton checkEmpresa;
    private javax.swing.JRadioButton checkLocalidad;
    private javax.swing.JRadioButton checkNombrePapelera;
    public static javax.swing.JComboBox comboContribuyente;
    public static javax.swing.JComboBox comboLocalidad;
    private javax.swing.JMenuItem eliminarRepresentante;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenuItem menueliminar;
    private javax.swing.JMenuItem modificarRepresentante;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel panelRepresentante;
    private javax.swing.JPanel panelTablas;
    private javax.swing.JTable tablaPapelera;
    private javax.swing.JTable tablaRepresentante;
    private javax.swing.JTable tablaproveedores;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarPapelera;
    public static javax.swing.JTextField txtCbu;
    private javax.swing.JTextField txtCodigoEmpresaRepresentante;
    public static javax.swing.JTextField txtCodigoPostal;
    private javax.swing.JTextField txtCodigoRepresentante;
    public static javax.swing.JTextField txtCuit;
    public static javax.swing.JTextField txtDireccionEmpresa;
    public static javax.swing.JTextField txtEmailRepresentante;
    public static javax.swing.JTextField txtIngresosBrutos;
    public static javax.swing.JTextField txtNombreEmpresaRepresentante;
    public static javax.swing.JTextField txtProvincia;
    public static javax.swing.JTextField txtResponsableEmpresa;
    public static javax.swing.JTextField txtTelefonoEmpresa;
    public static javax.swing.JTextField txtTelefonoRepresentante;
    public static javax.swing.JTextField txtcedula;
    public static javax.swing.JTextField txtnombreEmpresa;
    public static javax.swing.JTextField txtnombreRepresentante;
    // End of variables declaration//GEN-END:variables

    private void nuevoc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
