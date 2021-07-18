
package Vistas;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.sql.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class Registrar_Clientes extends javax.swing.JDialog {
    //VARIABLES GLOBALES
    int Select,contador,SelectPapelera,seleccionarTodos=0;
    boolean TodosSeleccionados=false;
    String codigo,referencia,cantidad,marca,valor;
             
    public Registrar_Clientes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        deshabilitar();
        
        cargarContribuyente();
        MostrarClientes();
        
        if (Menu_Principal.lb_tipo.getText().equals("Facturador")){
             btn_eliminara.setEnabled(false);     
        }
         
        MostrarClientes();
        
        Papelera.setSize(990,750);
        Papelera.setLocationRelativeTo(null);
        MostrarClientesPapelera();
         
        JTableHeader th; 
        th = tabla.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        JTableHeader th2; 
        th2 = tablaPapelera.getTableHeader(); 
        Font fuente2 = new Font("Calibri", Font.BOLD, 20); 
        th2.setFont(fuente2); 
        th2.setBackground(new Color(93,116,163));
        th2.setForeground(new Color(255,255,255));
    }
    
    public void deshabilitar(){
       jPanel1.setVisible(false);
       Papelera.setVisible(false);
       
       checkNombre.setSelected(true); 
       checkNombrePapelera.setSelected(true);
        
       txtBuscar.setText("Ingrese su búsqueda");
       txtBuscar.setForeground(Color.gray); 
       txtcodigo.setVisible(false);
       txtnombre.setText("");
       txtLocalidad.setText("");
       txtProvincia.setText("");
       txtcodigo.setText("");
       txtcodigo.requestFocus();
       txttelefono.setText("");
       txtdireccion.setText("");
       txtDni.setText("");
       txtCuit.setText("");
       
       txtnombre.setEditable(false);
       txtLocalidad.setEditable(false);
       txtcodigo.setEditable(false);
       txttelefono.setEditable(false);
       txtdireccion.setEditable(false);
       txtDni.setEditable(false);
       txtCuit.setEditable(false);
     
       btn_guardara1.setEnabled(false);
       btn_cancelara.setEnabled(false);
       btn_eliminara.setEnabled(false);
       
    }
    
    public void nuevoc(){
        comboContribuyente.setSelectedItem("Seleccionar");
        jPanel1.setVisible(true);  
        
        txtnombre.setText("");
        txtLocalidad.setText("");
        txtProvincia.setText("");
        txtcodigo.setText("");
        txtnombre.requestFocus();
        txttelefono.setText("");
        txtdireccion.setText("");
        txtDni.setText("");
        txtCuit.setText("");
     
        txtnombre.setEditable(true);
        txtLocalidad.setEditable(true);
        txtcodigo.setEditable(true);
        txttelefono.setEditable(true);
        txtdireccion.setEditable(true);
        txtDni.setEditable(true);
        txtCuit.setEditable(true);
     
        btn_guardara1.setEnabled(true);
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
    
    public void MostrarClientes(){    
        TableColumn  column = null;
         try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet r= consulta.executeQuery("select * from cliente  WHERE cod_cliente>0 AND estado='ACTIVO' order by nombres" );
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            tabla.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
             
           //TAMAÑO ANCHO DE LAS COLUMNAS 
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
            
            tabla.getColumnModel().getColumn(1).setMaxWidth(280);
            tabla.getColumnModel().getColumn(1).setMinWidth(280);
            tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(280);
            tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(280);
            
            tabla.getColumnModel().getColumn(2).setMaxWidth(250);
            tabla.getColumnModel().getColumn(2).setMinWidth(250);
            tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(250);
            tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(250);
            
            tabla.getColumnModel().getColumn(3).setMaxWidth(230);
            tabla.getColumnModel().getColumn(3).setMinWidth(230);
            tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(230);
            tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(230);
            
            
            tabla.getColumnModel().getColumn(4).setMaxWidth(180);
            tabla.getColumnModel().getColumn(4).setMinWidth(180);
            tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(180);
            tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(180);
                            
            tabla.getColumnModel().getColumn(5).setMaxWidth(180);
            tabla.getColumnModel().getColumn(5).setMinWidth(180);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(180);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(180);
                            
            tabla.getColumnModel().getColumn(6).setMaxWidth(150);
            tabla.getColumnModel().getColumn(6).setMinWidth(150);
            tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(150);
            tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(150);
                            
            tabla.getColumnModel().getColumn(7).setMaxWidth(150);
            tabla.getColumnModel().getColumn(7).setMinWidth(150);
            tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(150);
            tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(150);
                            
            tabla.getColumnModel().getColumn(8).setMaxWidth(70);
            tabla.getColumnModel().getColumn(8).setMinWidth(70);
            tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(70);
            tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(70);
            
            tabla.getColumnModel().getColumn(9).setMaxWidth(300);
            tabla.getColumnModel().getColumn(9).setMinWidth(300);
            tabla.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(300);
            tabla.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(300);
            
            tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tabla.doLayout();
            while(r.next()){
                if(r.getString(6).equals("ACTIVO")){
                    modelo.addRow( new Object [] {null,null,null,null,null,null,null,null});
                    tabla.setValueAt(r.getString(1),i,0);
                    tabla.setValueAt(r.getString(2),i,1);
                    tabla.setValueAt(r.getString(4),i,2);//DIRECCION
                    tabla.setValueAt(r.getString(3),i,3);//LOCALIDAD
                    tabla.setValueAt(r.getString(10),i,4);//PROVINCIA
                    tabla.setValueAt(r.getString(5),i,5);//TELEFONO
                    tabla.setValueAt(r.getString(7),i,6);//DNI
                    tabla.setValueAt(r.getString(8),i,7);//CUIT
                    tabla.setValueAt(r.getString(11),i,9);//MAIL
                    String contri;
                    if (r.getString(9).equals("Responsable Monotributo")){
                        contri="M";
                    }else
                        if (r.getString(9).equals("Responsable Inscripto")){
                            contri="RI";
                        }else
                            if (r.getString(9).equals("Responsable no Inscripto")){
                                contri="RNI";
                            }else
                                if (r.getString(9).equals("Consumidor Final")){
                                    contri="CF";
                                }else   
                                    if (r.getString(9).equals("Excento")){
                                        contri="E";
                                    }else
                                        if (r.getString(9).equals("No Responsable")){
                                            contri="NR";
                                        }else
                                            contri="";
                    
                     tabla.setValueAt(contri,i,8);
                     i++;
                }
  
            }
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Ocurrio un problema para conectarce a la base de datos") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
        }
    }
    
    //PAPELERA CLIENTES
    DefaultTableModel modelo2 = new DefaultTableModel();
    private boolean[] editable = {true,false,false,false,true,false,false,false,false,false,false,false,false};
    public void MostrarClientesPapelera(){
         Object[] datos= new Object[7];
         try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            ResultSet r2= consulta.executeQuery("select * from  cliente order by nombres");
            int i,j;
            i=0;
            j=0;
            Render3 r= new Render3();
            tablaPapelera.setDefaultRenderer(Object.class,r);
            DefaultTableModel modelo = new DefaultTableModel(new String[]{"SELECCIONAR","COD", "APELLIDO Y NOMBRE","DIRECCION","LOCALIDAD","TELEFONO","DNI"}, 0) {
                Class[] types = new Class[]{
                java.lang.Boolean.class,java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
                    if(r2.getString(6).equals("INACTIVO")){
                        datos[0]=true;
                        datos[1]=r2.getObject(1);
                        datos[2]=r2.getObject(2);
                        datos[3]=r2.getObject(4);
                        datos[4]=r2.getObject(3);
                        datos[5]=r2.getObject(4);
                        datos[6]=r2.getObject(5);
                        modelo.addRow(datos);
                    }         
                } 
                seleccionarTodos=0;
            }else{
                while(r2.next()){
                    if(r2.getString(6).equals("INACTIVO")){
                        datos[0]=false;
                        datos[1]=r2.getObject(1);
                        datos[2]=r2.getObject(2);
                        datos[3]=r2.getObject(4);
                        datos[4]=r2.getObject(3);
                        datos[5]=r2.getObject(4);
                        datos[6]=r2.getObject(5);
                        modelo.addRow(datos);
                    }       
                }
            }
            tablaPapelera.setModel(modelo);
            r2.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }     
    }
    
    public void restaurarCliente(){    
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            contador=0;
            for(int i=0;i<tablaPapelera.getRowCount();i++){  
                String pago2= tablaPapelera.getValueAt(i,0).toString();
                if(pago2.equals("true")){ 
                    consulta.executeUpdate("UPDATE cliente SET estado='ACTIVO' where cod_cliente='"+tablaPapelera.getValueAt(i,1).toString()+"'");
                    contador++;
                }
            }
            if (contador==1 && TodosSeleccionados==false){
                JOptionPane.showMessageDialog(null,"EL CLIENTE SE RESTAURO");
            }else{
                if (contador>=1 && TodosSeleccionados==false){
                    JOptionPane.showMessageDialog(null,"LOS CLIENTES SE RESTAURARON");
                }
                else{
                    if (contador==1 && TodosSeleccionados==true){
                        JOptionPane.showMessageDialog(null,"EL CLIENTE SE RESTAURO");
                    }else{
                        if (contador>=1 && TodosSeleccionados==true){
                            JOptionPane.showMessageDialog(null,"LOS CLIENTES SE RESTAURARON");
                        }
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarClientesPapelera();
        MostrarClientes();
    }
    
    public void eliminarCliente(){     
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            contador=0;
            for(int i=0;i<tablaPapelera.getRowCount();i++){  
                String pago2= tablaPapelera.getValueAt(i,0).toString();
                if(pago2.equals("true")){ 
                   consulta.executeUpdate("DELETE FROM cliente WHERE cod_cliente='"+tablaPapelera.getValueAt(i,1).toString()+"'");
                   contador++;
                }
            }
            if (contador==1 && TodosSeleccionados==false){
                JOptionPane.showMessageDialog(null,"EL CLIENTE FUE ELIMINADO DEL SISTEMA");
            }else{
                if (contador>=1 && TodosSeleccionados==false){
                    JOptionPane.showMessageDialog(null,"LOS CLIENTES FUERON ELIMINADOS DEL SISTEMA");
                }
                else{
                    if (contador==1 && TodosSeleccionados==true){
                        JOptionPane.showMessageDialog(null,"EL CLIENTE FUE ELIMINADO DEL SISTEMA");
                    }else{
                        if (contador>=1 && TodosSeleccionados==true){
                            JOptionPane.showMessageDialog(null,"LOS CLIENTES FUERON ELIMINADOS DEL SISTEMA");
                        }
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarClientesPapelera();
        MostrarClientes();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FiltrarResultados = new javax.swing.ButtonGroup();
        Papelera = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPapelera = new javax.swing.JTable();
        checkLocalidadPapelera = new javax.swing.JRadioButton();
        checkNombrePapelera = new javax.swing.JRadioButton();
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
        jMenuEliminar = new javax.swing.JPopupMenu();
        itemEliminar = new javax.swing.JMenuItem();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        txttelefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCuit = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        comboContribuyente = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtLocalidad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtProvincia = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        checkDni = new javax.swing.JRadioButton();
        checkNombre = new javax.swing.JRadioButton();
        checkLocalidad = new javax.swing.JRadioButton();
        btn_nuevoa = new javax.swing.JButton();
        btn_eliminara = new javax.swing.JButton();
        btn_cancelara = new javax.swing.JButton();
        btn_guardara1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        checkCuit = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();

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

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 960, 360));

        FiltrarResultadosPapelera.add(checkLocalidadPapelera);
        checkLocalidadPapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkLocalidadPapelera.setText("Localidad");
        checkLocalidadPapelera.setOpaque(false);
        jPanel2.add(checkLocalidadPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 50, -1, -1));

        FiltrarResultadosPapelera.add(checkNombrePapelera);
        checkNombrePapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkNombrePapelera.setText("Apellido yNombre");
        checkNombrePapelera.setOpaque(false);
        checkNombrePapelera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkNombrePapeleraActionPerformed(evt);
            }
        });
        jPanel2.add(checkNombrePapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, -1, -1));

        FiltrarResultadosPapelera.add(checkCodigoPapelera);
        checkCodigoPapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCodigoPapelera.setText("Código");
        checkCodigoPapelera.setOpaque(false);
        checkCodigoPapelera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCodigoPapeleraActionPerformed(evt);
            }
        });
        jPanel2.add(checkCodigoPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, -1, -1));

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
        jPanel2.add(txtBuscarPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 280, 37));

        jLabel24.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel24.setText("PAPELERA DE RECICLAJE");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, -1));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 303, 17));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, -1, 80));

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
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 140, 208, -1));

        jLabel26.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel26.setText("Eliminar");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 650, -1, -1));

        jLabel27.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
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
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, -1, 102));

        jLabel29.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel29.setText("Restaurar");
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, -1, -1));

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
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1003, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        PapeleraLayout.setVerticalGroup(
            PapeleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        itemEliminar.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        itemEliminar.setText("Mover a la papelera");
        itemEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEliminarActionPerformed(evt);
            }
        });
        jMenuEliminar.add(itemEliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ingreso de clientes");
        setBackground(new java.awt.Color(153, 204, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage1.setBackground(new java.awt.Color(153, 204, 255));
        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoMenumar22.png"))); // NOI18N
        panelImage1.setOpaque(false);
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel10.setText("C.C");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 50, -1));

        txtcodigo.setBackground(new java.awt.Color(204, 204, 204));
        txtcodigo.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtcodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoKeyTyped(evt);
            }
        });
        jPanel1.add(txtcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 40, 40));

        jLabel5.setBackground(new java.awt.Color(239, 255, 239));
        jLabel5.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel5.setText(" Apellido y Nombre");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 210, 40));

        txtnombre.setBackground(new java.awt.Color(204, 204, 204));
        txtnombre.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtnombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 240, 40));

        txttelefono.setBackground(new java.awt.Color(204, 204, 204));
        txttelefono.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txttelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });
        jPanel1.add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 240, 40));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel7.setText(" Teléfono");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 120, -1));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel6.setText(" Dirección");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 110, -1));

        txtdireccion.setBackground(new java.awt.Color(204, 204, 204));
        txtdireccion.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtdireccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdireccionKeyReleased(evt);
            }
        });
        jPanel1.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 240, 40));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesXs-01.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 50, 40));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 32)); // NOI18N
        jLabel2.setText("INGRESO DE CLIENTES");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 380, 40));

        txtCuit.setBackground(new java.awt.Color(204, 204, 204));
        txtCuit.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtCuit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCuitKeyReleased(evt);
            }
        });
        jPanel1.add(txtCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 240, 40));

        txtDni.setBackground(new java.awt.Color(204, 204, 204));
        txtDni.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtDni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDniKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });
        jPanel1.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 240, 40));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel11.setText("Contribuyente");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 160, -1));

        comboContribuyente.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        comboContribuyente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboContribuyenteActionPerformed(evt);
            }
        });
        jPanel1.add(comboContribuyente, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 240, 40));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel17.setText("NIT/RUT");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 100, -1));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel39.setText("*");
        jPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 30, 40));

        txtLocalidad.setBackground(new java.awt.Color(204, 204, 204));
        txtLocalidad.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtLocalidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLocalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLocalidadActionPerformed(evt);
            }
        });
        txtLocalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocalidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLocalidadKeyTyped(evt);
            }
        });
        jPanel1.add(txtLocalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 240, 40));

        jLabel8.setBackground(new java.awt.Color(239, 255, 239));
        jLabel8.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel8.setText("Departamento");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 160, -1));

        txtProvincia.setBackground(new java.awt.Color(204, 204, 204));
        txtProvincia.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtProvincia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProvinciaActionPerformed(evt);
            }
        });
        txtProvincia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProvinciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProvinciaKeyTyped(evt);
            }
        });
        jPanel1.add(txtProvincia, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 240, 40));

        jLabel21.setBackground(new java.awt.Color(239, 255, 239));
        jLabel21.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel21.setText("Email");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 450, 70, -1));

        txtEmail.setBackground(new java.awt.Color(204, 204, 204));
        txtEmail.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKeyTyped(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 450, 240, 40));

        jLabel22.setBackground(new java.awt.Color(239, 255, 239));
        jLabel22.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel22.setText("Ciudad");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 110, -1));

        panelImage1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 460, 510));

        tabla.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COD", "APELLIDO Y NOMBRE", "DIRECCION", "LOCALIDAD", "PROVINCIA", "TELEFONO", "DNI", "CUIT", "TIPO", "EMAIL"
            }
        ));
        tabla.setComponentPopupMenu(jMenuEliminar);
        tabla.setRowHeight(25);
        tabla.setRowMargin(8);
        tabla.setRowSelectionAllowed(false);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        panelImage1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 580, 400));

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
        panelImage1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 30, 200, 40));

        FiltrarResultados.add(checkDni);
        checkDni.setFont(new java.awt.Font("Calibri", 1, 21)); // NOI18N
        checkDni.setText("C.C");
        checkDni.setOpaque(false);
        checkDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDniActionPerformed(evt);
            }
        });
        panelImage1.add(checkDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 50, -1, -1));

        FiltrarResultados.add(checkNombre);
        checkNombre.setFont(new java.awt.Font("Calibri", 1, 21)); // NOI18N
        checkNombre.setText("Apellido y Nombre");
        checkNombre.setOpaque(false);
        checkNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkNombreActionPerformed(evt);
            }
        });
        panelImage1.add(checkNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, -1, -1));

        FiltrarResultados.add(checkLocalidad);
        checkLocalidad.setFont(new java.awt.Font("Calibri", 1, 21)); // NOI18N
        checkLocalidad.setText("Ciudad");
        checkLocalidad.setOpaque(false);
        panelImage1.add(checkLocalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, -1));

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
        panelImage1.add(btn_nuevoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 140, 140));

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
        panelImage1.add(btn_eliminara, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 510, 160, 140));

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
        panelImage1.add(btn_cancelara, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 510, 150, 140));

        btn_guardara1.setBackground(new java.awt.Color(51, 153, 255));
        btn_guardara1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_guardara1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardar.png"))); // NOI18N
        btn_guardara1.setBorder(null);
        btn_guardara1.setBorderPainted(false);
        btn_guardara1.setContentAreaFilled(false);
        btn_guardara1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        panelImage1.add(btn_guardara1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 510, 170, 140));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        panelImage1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, -1, 80));
        panelImage1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, 200, 10));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 32)); // NOI18N
        jLabel1.setText("INGRESO DE CLIENTES");
        panelImage1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 380, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesXs-01.png"))); // NOI18N
        panelImage1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 50, 50));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel9.setText("Guardar");
        panelImage1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 630, -1, 40));

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel15.setText("Cancelar");
        panelImage1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 640, -1, 20));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel4.setText("Nuevo");
        panelImage1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 630, 70, 40));

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel16.setText("Mover");
        panelImage1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 630, 80, 40));

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
        panelImage1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 520, 100, 100));

        jLabel30.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel30.setText("Papelera");
        panelImage1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 610, -1, -1));

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel18.setText("de reciclaje");
        panelImage1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 630, -1, -1));

        FiltrarResultados.add(checkCuit);
        checkCuit.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkCuit.setText("NIT/RUT");
        checkCuit.setOpaque(false);
        checkCuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCuitActionPerformed(evt);
            }
        });
        panelImage1.add(checkCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, -1));

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel19.setText("a papelera");
        panelImage1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 650, 120, 40));

        getContentPane().add(panelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyTyped
       char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();// TODO add your handling code here:
    }//GEN-LAST:event_txtcodigoKeyTyped

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased
        txtnombre.setText (txtnombre.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreKeyReleased

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped
        Character c;
        c = evt.getKeyChar();
        if(!Character.isLetter(c) && c != com.sun.glass.events.KeyEvent.VK_SPACE){
            evt.consume();
        }// TODO add your handling code here:
    }//GEN-LAST:event_txtnombreKeyTyped

    private void txttelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();
    }//GEN-LAST:event_txttelefonoKeyTyped

    private void txtdireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyReleased
        txtdireccion.setText (txtdireccion.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionKeyReleased

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        //CUANDOCLICKEO LA TABLA SETEO LOS TEXTFIELDS
        nuevoc();
        Select=tabla.getSelectedRow();
        txtcodigo.setText( tabla.getValueAt(Select,0).toString());
        txtnombre.setText( tabla.getValueAt(Select,1).toString());
        txtLocalidad.setText( tabla.getValueAt(Select,3).toString());
        txtProvincia.setText( tabla.getValueAt(Select,4).toString());
        
        txttelefono.setText( tabla.getValueAt(Select,5).toString());
        txtdireccion.setText( tabla.getValueAt(Select,2).toString());
        txtDni.setText( tabla.getValueAt(Select,6).toString());
        txtCuit.setText( tabla.getValueAt(Select,7).toString());
        
        
        
        String contri;
        if (tabla.getValueAt(Select,8).toString().equals("RI")){
            contri="Responsable Inscripto";
        }else
            if (tabla.getValueAt(Select,8).toString().equals("RNI")){
                contri="Responsable NO-Inscripto";
            }else
                if (tabla.getValueAt(Select,8).toString().equals("M")){
                    contri="Responsable Monotributo";
                }else
                    if (tabla.getValueAt(Select,8).toString().equals("CF")){
                        contri="Consumidor Final";
                    }else
                        if (tabla.getValueAt(Select,8).toString().equals("E")){
                            contri="Excento";
                        }else
                            if (tabla.getValueAt(Select,8).toString().equals("NR")){
                                contri="No Responsable";
                            }else
                                contri=""; 
        
        comboContribuyente.setSelectedItem(contri);  
    }//GEN-LAST:event_tablaMouseClicked

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    
    DefaultTableModel dm;
    /* Método filtro*/
    private void filtro2(String consulta, JTable jtableBuscar){  
        dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter(dm);
        jtableBuscar.setRowSorter(tr);

        int columna=0;
        // Identificamos cual es el JRadioButton seleccionado para filtrar el
        // resultado de acuerdo a los datos de la columna elegida
        if (checkDni.isSelected()) {
            columna = 6;
        }else 
            if (checkCuit.isSelected()) {
                columna = 7;
            }else 
                if (checkNombre.isSelected()) {
                     columna = 1;
                }else
                    if (checkLocalidad.isSelected()) {
                        columna = 3;
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
            filtro2(txtBuscar.getText().toUpperCase(), tabla);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void checkDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkDniActionPerformed

    private void btn_nuevoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoaActionPerformed
        nuevoc();
    }//GEN-LAST:event_btn_nuevoaActionPerformed

    private void btn_eliminaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminaraActionPerformed
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            consulta.executeUpdate("UPDATE cliente SET estado='INACTIVO' where cod_cliente='"+txtcodigo.getText()+"'");
            JOptionPane.showMessageDialog(null,"EL CLIENTE SE MOVIÓ A LA PAPELERA DE RECICLAJE");
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarClientes();
        MostrarClientesPapelera();
        nuevoc();
        txtcodigo.requestFocus();
    }//GEN-LAST:event_btn_eliminaraActionPerformed

    private void btn_cancelaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelaraActionPerformed
        deshabilitar();
    }//GEN-LAST:event_btn_cancelaraActionPerformed

    private void btn_guardara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardara1ActionPerformed
        //GUARDO LOS CLIENTES
        txtnombre.setBackground(new Color(204,204,204));
        txtLocalidad.setBackground(new Color(204,204,204));
     
        if (txtnombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta el Nombre y Apellido del Cliente","Advertencia",JOptionPane.WARNING_MESSAGE);
            txtnombre.setBackground(Color.yellow);
            txtnombre.requestFocus();
        }else{
            try{
                Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta = conn.createStatement();                   
                Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
                ResultSet r= consulta1.executeQuery("select cod_cliente from cliente");
                int bandera=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
                while(r.next()){
                    if(txtcodigo.getText().equals(r.getString(1))){//si el codigo de la tabla es igual a algun codigo de la base de datos
                        bandera=1;                      
                    }
                }
                String contribuyente;
                if(comboContribuyente.getSelectedItem().toString().equals("Seleccionar")){
                    contribuyente="";
                }else{
                   contribuyente=comboContribuyente.getSelectedItem().toString();
                }
                
                if(bandera==0){//Si el codigo no esta inserto una nuevo registro a la base de datos
                    consulta.executeUpdate("insert into cliente (nombres,localidad,dirrecion,telefono,estado,dni,cuit,contribuyente,provincia,email)  values('"+txtnombre.getText()+"','"+txtLocalidad.getText()+"','"+txtdireccion.getText()+"','"+txttelefono.getText()+"','ACTIVO','"+txtDni.getText()+"','"+txtCuit.getText()+"','"+contribuyente+"','"+txtProvincia.getText()+"','"+txtEmail.getText()+"')");     
                }else{//si el codigo esta,actualizo la informacion del producto
                     consulta.executeUpdate("UPDATE cliente SET nombres='"+txtnombre.getText()+"',localidad='"+txtLocalidad.getText()+"',dirrecion='"+txtdireccion.getText()+"',telefono='"+txttelefono.getText()+"' ,dni='"+txtDni.getText()+"' ,cuit='"+txtCuit.getText()+"',contribuyente='"+contribuyente+"', provincia='"+txtProvincia.getText()+"', email='"+txtEmail.getText()+"' WHERE cod_cliente='"+txtcodigo.getText()+"'");               
                }
                JOptionPane.showMessageDialog(null, "Los datos han sido Guardados Correctamente");
                nuevoc();
                MostrarClientes();
            } 
            catch(SQLException ex) {
                JOptionPane.showMessageDialog(null,ex);
                JOptionPane.showMessageDialog(null,"El CLIENTE"+" "+ txtcodigo.getText()+" "+"DE NOMBRE"+" "+txtnombre.getText()+" "+"YA EXISTE");
                System.out.println(ex);
            }
        }        
    }//GEN-LAST:event_btn_guardara1ActionPerformed

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
                if (checkLocalidadPapelera.isSelected()) {
                    columna = 4;
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

        restaurarCliente();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        TodosSeleccionados=true;
        seleccionarTodos=1;
        MostrarClientesPapelera();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        eliminarCliente();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        Papelera.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void itemEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEliminarActionPerformed
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            consulta.executeUpdate("UPDATE cliente SET estado='INACTIVO' where cod_cliente='"+txtcodigo.getText()+"'");
            JOptionPane.showMessageDialog(null,"EL CLIENTE SE MOVIÓ A LA PAPELERA DE RECICLAJE");
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarClientes();
        MostrarClientesPapelera();
        nuevoc();
        txtcodigo.requestFocus();
    }//GEN-LAST:event_itemEliminarActionPerformed

    private void checkCuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCuitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCuitActionPerformed

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
    char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtDniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyReleased
        txtDni.setText (txtDni.getText().toUpperCase()); 
    }//GEN-LAST:event_txtDniKeyReleased

    private void txtCuitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuitKeyReleased
    txtCuit.setText (txtCuit.getText().toUpperCase()); 
    }//GEN-LAST:event_txtCuitKeyReleased

    private void comboContribuyenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboContribuyenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboContribuyenteActionPerformed

    private void txtLocalidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalidadKeyTyped
        Character c;
        c = evt.getKeyChar();
        if(!Character.isLetter(c) && c != com.sun.glass.events.KeyEvent.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_txtLocalidadKeyTyped

    private void txtLocalidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalidadKeyReleased
        txtLocalidad.setText (txtLocalidad.getText().toUpperCase());    
    }//GEN-LAST:event_txtLocalidadKeyReleased

    private void txtLocalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocalidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocalidadActionPerformed

    private void checkNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkNombreActionPerformed

    private void checkNombrePapeleraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkNombrePapeleraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkNombrePapeleraActionPerformed
    //ES PARA IMPORTAR CLIENTES (ESTA ACTALMENTE EN DESUSO)
    JFileChooser selecArchivo = new JFileChooser();
    File archivo;
    public void AgregarFiltro(){
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
    }
    /////////////////////////////////
    
    public static boolean esNumerico(String cadena){
        try{
            Integer.parseInt(cadena);
                return true;
        }catch(NumberFormatException e){
                return false;
        }
    }
    
    private void txtProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProvinciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProvinciaActionPerformed

    private void txtProvinciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProvinciaKeyReleased
        txtProvincia.setText(txtProvincia.getText().toUpperCase());  
    }//GEN-LAST:event_txtProvinciaKeyReleased

    private void txtProvinciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProvinciaKeyTyped
        Character c;
        c = evt.getKeyChar();
        if(!Character.isLetter(c) && c != com.sun.glass.events.KeyEvent.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_txtProvinciaKeyTyped

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailKeyTyped

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        /* this.setVisible(false);
        MostrarArticulosPapelera();
        Papelera.setVisible(true);*/
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(Registrar_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrar_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrar_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrar_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Registrar_Clientes dialog = new Registrar_Clientes(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_cancelara;
    private javax.swing.JButton btn_eliminara;
    private javax.swing.JButton btn_guardara1;
    private javax.swing.JButton btn_nuevoa;
    private javax.swing.JRadioButton checkCodigoPapelera;
    private javax.swing.JRadioButton checkCuit;
    private javax.swing.JRadioButton checkDni;
    private javax.swing.JRadioButton checkLocalidad;
    private javax.swing.JRadioButton checkLocalidadPapelera;
    private javax.swing.JRadioButton checkNombre;
    private javax.swing.JRadioButton checkNombrePapelera;
    public static javax.swing.JComboBox comboContribuyente;
    private javax.swing.JMenuItem itemEliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPopupMenu jMenuEliminar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tablaPapelera;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarPapelera;
    private javax.swing.JTextField txtCuit;
    private javax.swing.JTextField txtDni;
    public static javax.swing.JTextField txtEmail;
    public static javax.swing.JTextField txtLocalidad;
    public static javax.swing.JTextField txtProvincia;
    public static javax.swing.JTextField txtcodigo;
    public static javax.swing.JTextField txtdireccion;
    public static javax.swing.JTextField txtnombre;
    public static javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
