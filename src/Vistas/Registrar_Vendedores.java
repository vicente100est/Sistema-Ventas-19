
package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;


public class Registrar_Vendedores extends javax.swing.JDialog {

    int Select, SelectPapelera,contador, seleccionarTodos=0;
    boolean TodosSeleccionados=false;
    String codigo,referencia,cantidad,marca,valor;
  
    public Registrar_Vendedores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null); 
        
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        deshabilitar();
        
        MostrarEmpleados();
        
        Papelera.setSize(990,750);
        Papelera.setLocationRelativeTo(null);
        MostrarEmpleadosPapelera();
        
        //COLOR DE LA CABECERA D ELA TABLA
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
  
    public void nuevoem(){  //CUANDO APRETO NUEVO
        jPanel1.setVisible(true);
        
        txtnombre.setText("");
        txtapellido.setText("");
        txtcodigo.setText("");
        txtnombre.requestFocus();
        txttelefono.setText("");
        txtdireccion.setText("");
        txtDni.setText("");
        txtLocalidad.setText("");
        txtnombre.setEditable(true);
        txtapellido.setEditable(true);
        txtcodigo.setEditable(true);
        txttelefono.setEditable(true);
        txtdireccion.setEditable(true);
            
        btn_guardara.setEnabled(true);
        btn_cancelara.setEnabled(true);
        btn_eliminara.setEnabled(true);
    }
    
    public void deshabilitar(){ //SETEO COMO QUIERO QUE SE MUESTREN LOS COMPNENTES
        checkReferencia.setSelected(true); 
        checkNombrePapelera.setSelected(true);
        
        txtBuscar.setText("Ingrese su búsqueda");
        txtBuscar.setForeground(Color.gray);
        
        jPanel1.setVisible(false);
        Papelera.setVisible(false);
        
        txtnombre.setText("");
        txtapellido.setText("");
        txtcodigo.setText("");
        txttelefono.setText("");
        txtdireccion.setText("");
        txtDni.setText("");
        txtLocalidad.setText("");
        txtnombre.setEditable(false);
        txtapellido.setEditable(false);
        txtcodigo.setVisible(false);
        txttelefono.setEditable(false);
        txtdireccion.setEditable(false);
            
        btn_guardara.setEnabled(false);
        btn_cancelara.setEnabled(false);
        btn_eliminara.setEnabled(false);
    }
    
    public void MostrarEmpleados(){ //MUESTRO LOS EMPLEADOS
        TableColumn  column = null;
        
        //SETEO EL TAMAÑO DE LAS COLUMNAS
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        
        tabla.getColumnModel().getColumn(1).setMaxWidth(250);
        tabla.getColumnModel().getColumn(1).setMinWidth(250);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(250);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(250);
        
        tabla.getColumnModel().getColumn(2).setMaxWidth(250);
        tabla.getColumnModel().getColumn(2).setMinWidth(250);
        tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(250);
        tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(250);
        
        tabla.getColumnModel().getColumn(3).setMaxWidth(250);
        tabla.getColumnModel().getColumn(3).setMinWidth(250);
        tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(250);
        tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(250);
        
        tabla.getColumnModel().getColumn(4).setMaxWidth(250);
        tabla.getColumnModel().getColumn(4).setMinWidth(250);
        tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(250);
        tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(250);
        
        tabla.getColumnModel().getColumn(5).setMaxWidth(250);
        tabla.getColumnModel().getColumn(5).setMinWidth(250);
        tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(250);
        tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(250);
        
        tabla.getColumnModel().getColumn(6).setMaxWidth(250);
        tabla.getColumnModel().getColumn(6).setMinWidth(250);
        tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(250);
        tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(250);
        
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.doLayout();
        
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet r= consulta.executeQuery("select * from empleado where estado='ACTIVO' order by nombres");
            int i,j;
            i=0;
            j=0;
            
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            tabla.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            while(r.next()){
                if(r.getString(6).equals("ACTIVO")){
                    modelo.addRow( new Object [] {null,null,null,null,null,null,null});
                    tabla.setValueAt(r.getString(1),i,0);
                    tabla.setValueAt(r.getString(2),i,1);
                    tabla.setValueAt(r.getString(3),i,2);
                    tabla.setValueAt(r.getString(5),i,3);
                    tabla.setValueAt(r.getString(4),i,4);
                    tabla.setValueAt(r.getString(8),i,5);
                    tabla.setValueAt(r.getString(7),i,6);
                    i++;
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Este empleado Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
        }
    }
    
    //PAPELERA DE EMPLEADOS
    DefaultTableModel modelo2 = new DefaultTableModel();
    private boolean[] editable = {true,false,false,false,true,false,false,false,false,false,false,false,false};
    public void MostrarEmpleadosPapelera(){
        Object[] datos= new Object[6];
        try{   
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            ResultSet r2= consulta.executeQuery("select * from  empleado order by nombres");
            int i,j;
            i=0;
            j=0;
            
            Render3 r= new Render3();
            
            tablaPapelera.setDefaultRenderer(Object.class,r);
            DefaultTableModel modelo = new DefaultTableModel(new String[]{"SELECCIONAR","CODIGO", "NOMBRE", "APELLIDO", "DIRECCION","TELEFONO"}, 0) {
 
                Class[] types = new Class[]{
                java.lang.Boolean.class,java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
                        datos[3]=r2.getObject(3);
                        datos[4]=r2.getObject(4);
                        datos[5]=r2.getObject(5);
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
                        datos[3]=r2.getObject(3);
                        datos[4]=r2.getObject(4);
                        datos[5]=r2.getObject(5);
                        modelo.addRow(datos);
                    }  
                }
            }
            tablaPapelera.setModel(modelo);
            r2.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }     
    }
    
    public void restaurarEmpleado(){  //RESTAURO LOS EMPLEADOS SELECCIONADOS
        try{    
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql;
            contador=0;
            for(int i=0;i<tablaPapelera.getRowCount();i++){  
                String pago2= tablaPapelera.getValueAt(i,0).toString();
                if(pago2.equals("true")){ 
                    consulta.executeUpdate("UPDATE empleado SET estado='ACTIVO' where cod_empleado='"+tablaPapelera.getValueAt(i,1).toString()+"'");
                    contador++;
                }
            }
            if (contador==1 && TodosSeleccionados==false){
                JOptionPane.showMessageDialog(null,"EL EMPLEADO SE RESTAURO");
            }else{
                if (contador>=1 && TodosSeleccionados==false){
                    JOptionPane.showMessageDialog(null,"LOS EMPLEADOS SE RESTAURARON");
                }
                else{
                    if (contador==1 && TodosSeleccionados==true){
                        JOptionPane.showMessageDialog(null,"EL EMPLEADO SE RESTAURO");
                    }else{
                        if (contador>=1 && TodosSeleccionados==true){
                            JOptionPane.showMessageDialog(null,"LOS EMPLEADOS SE RESTAURARON");
                        }
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarEmpleadosPapelera();
        MostrarEmpleados();
    }
    
    public void eliminarEmpleado(){ //ELIMINO LOS EMPLEADOS SELECCIONADOS
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            contador=0;
            for(int i=0;i<tablaPapelera.getRowCount();i++){  
                String pago2= tablaPapelera.getValueAt(i,0).toString();
                if(pago2.equals("true")){ 
                    consulta.executeUpdate("DELETE FROM empleado WHERE cod_empleado='"+tablaPapelera.getValueAt(i,1).toString()+"'");
                    contador++;
                }
            }
            if (contador==1 && TodosSeleccionados==false){
                JOptionPane.showMessageDialog(null,"EL EMPLEADO FUE ELIMINADO DEL SISTEMA");
            }else{
                if (contador>=1 && TodosSeleccionados==false){
                    JOptionPane.showMessageDialog(null,"LOS EMPLEADOS FUERON ELIMINADOS DEL SISTEMA");
                }
                else{
                    if (contador==1 && TodosSeleccionados==true){
                        JOptionPane.showMessageDialog(null,"EL EMPLEADO FUE ELIMINADO DEL SISTEMA");
                    }else{
                        if (contador>=1 && TodosSeleccionados==true){
                            JOptionPane.showMessageDialog(null,"LOS EMPLEADOS FUERON ELIMINADOS DEL SISTEMA");
                        }
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarEmpleadosPapelera();
        MostrarEmpleados();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FiltrarResultados = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        BorrarEmpleado = new javax.swing.JMenuItem();
        Papelera = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPapelera = new javax.swing.JTable();
        checkApellidoPapelera = new javax.swing.JRadioButton();
        checkNombrePapelera = new javax.swing.JRadioButton();
        checkCodigoPapelera = new javax.swing.JRadioButton();
        txtBuscarPapelera = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        FiltrarResultadosPapelera = new javax.swing.ButtonGroup();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        txtapellido = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();
        txttelefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtLocalidad = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        checkCodigo = new javax.swing.JRadioButton();
        checkReferencia = new javax.swing.JRadioButton();
        checkMarca = new javax.swing.JRadioButton();
        btn_cancelara = new javax.swing.JButton();
        btn_eliminara = new javax.swing.JButton();
        btn_guardara = new javax.swing.JButton();
        btn_nuevoa = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();

        BorrarEmpleado.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        BorrarEmpleado.setText("Mover a la Papelera");
        BorrarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarEmpleadoActionPerformed(evt);
            }
        });
        jPopupMenu1.add(BorrarEmpleado);

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

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 970, 350));

        FiltrarResultadosPapelera.add(checkApellidoPapelera);
        checkApellidoPapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkApellidoPapelera.setText("Apellido");
        checkApellidoPapelera.setOpaque(false);
        jPanel2.add(checkApellidoPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 70, -1, -1));

        FiltrarResultadosPapelera.add(checkNombrePapelera);
        checkNombrePapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkNombrePapelera.setText("Nombre");
        checkNombrePapelera.setOpaque(false);
        jPanel2.add(checkNombrePapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 70, -1, -1));

        FiltrarResultadosPapelera.add(checkCodigoPapelera);
        checkCodigoPapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCodigoPapelera.setText("Código");
        checkCodigoPapelera.setOpaque(false);
        checkCodigoPapelera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCodigoPapeleraActionPerformed(evt);
            }
        });
        jPanel2.add(checkCodigoPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, -1, -1));

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
        jPanel2.add(txtBuscarPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, 280, 37));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel11.setText("PAPELERA DE RECICLAJE");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, 50));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, 303, 17));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, -1, 80));

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
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 570, 130, 102));

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
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 570, 170, 102));

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
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 208, -1));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel2.setText("Eliminar");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 670, -1, -1));

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel14.setText("Ir hacia atras");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 670, -1, -1));

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
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 570, -1, 102));

        jLabel22.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel22.setText("Restaurar");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 670, -1, -1));

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
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1013, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        PapeleraLayout.setVerticalGroup(
            PapeleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ingreso de vendedores");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoMenumar22.png"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(239, 255, 239));
        jLabel5.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel5.setText(" Nombre");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 120, -1));

        txtnombre.setBackground(new java.awt.Color(204, 204, 204));
        txtnombre.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtnombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 250, 40));

        txtapellido.setBackground(new java.awt.Color(204, 204, 204));
        txtapellido.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtapellido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtapellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtapellidoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellidoKeyTyped(evt);
            }
        });
        jPanel1.add(txtapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 250, 40));

        jLabel8.setBackground(new java.awt.Color(239, 255, 239));
        jLabel8.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel8.setText("Apellido");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 120, -1));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel10.setText("C.C");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 50, 30));

        txtcodigo.setBackground(new java.awt.Color(204, 204, 204));
        txtcodigo.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoKeyTyped(evt);
            }
        });
        jPanel1.add(txtcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, 40, 40));

        txttelefono.setBackground(new java.awt.Color(204, 204, 204));
        txttelefono.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txttelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });
        jPanel1.add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 250, 40));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel7.setText(" Teléfono");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, -1));

        txtdireccion.setBackground(new java.awt.Color(204, 204, 204));
        txtdireccion.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtdireccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdireccionKeyReleased(evt);
            }
        });
        jPanel1.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 250, 40));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel6.setText("Ciudad");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 80, -1));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 32)); // NOI18N
        jLabel9.setText("INGRESO DE VENDEDORES");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 380, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoEmpleadosXs-01.png"))); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 50));

        txtDni.setBackground(new java.awt.Color(204, 204, 204));
        txtDni.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtDni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDniKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });
        jPanel1.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 250, 40));

        jLabel39.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel39.setText("*");
        jPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 30, 30));

        jLabel40.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel40.setText("*");
        jPanel1.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 30, 30));

        txtLocalidad.setBackground(new java.awt.Color(204, 204, 204));
        txtLocalidad.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtLocalidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLocalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocalidadKeyReleased(evt);
            }
        });
        jPanel1.add(txtLocalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 250, 40));

        jLabel25.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel25.setText(" Dirección");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 120, -1));

        panelImage1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 430, 410));

        tabla.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "APELLIDO", "TELEFONO", "DIRECCION", "LOCALIDAD", "DNI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setComponentPopupMenu(jPopupMenu1);
        tabla.setRowHeight(25);
        tabla.setRowMargin(4);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(6).setResizable(false);
        }

        panelImage1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 600, 390));

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
        panelImage1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 240, 50));

        FiltrarResultados.add(checkCodigo);
        checkCodigo.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkCodigo.setText("Código");
        checkCodigo.setOpaque(false);
        checkCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCodigoActionPerformed(evt);
            }
        });
        panelImage1.add(checkCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, -1, -1));

        FiltrarResultados.add(checkReferencia);
        checkReferencia.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkReferencia.setText("Nombre");
        checkReferencia.setOpaque(false);
        panelImage1.add(checkReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, -1, -1));

        FiltrarResultados.add(checkMarca);
        checkMarca.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkMarca.setText("Apellido");
        checkMarca.setOpaque(false);
        panelImage1.add(checkMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, -1, -1));

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
        panelImage1.add(btn_cancelara, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 500, 120, 140));

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
        panelImage1.add(btn_eliminara, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 500, 130, 140));

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
        panelImage1.add(btn_guardara, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 490, 170, 150));

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
        panelImage1.add(btn_nuevoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 160, 150));
        panelImage1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 70, 240, 10));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        panelImage1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, -1, 80));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 32)); // NOI18N
        jLabel4.setText("INGRESO DE VENDEDORES");
        panelImage1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 380, 40));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel13.setText("Nuevo");
        panelImage1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 620, -1, 30));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel17.setText("Guardar");
        panelImage1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 620, -1, 40));

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel18.setText("de reciclaje");
        panelImage1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 620, 120, 40));

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel19.setText("Cancelar");
        panelImage1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 620, -1, 40));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoEmpleadosXs-01.png"))); // NOI18N
        panelImage1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 50, 50));

        jLabel21.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel21.setText("Mover ");
        panelImage1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 620, -1, 40));

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
        panelImage1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 520, 100, 90));

        jLabel23.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel23.setText("a papelera");
        panelImage1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 650, -1, -1));

        jLabel24.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel24.setText("Papelera");
        panelImage1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 600, -1, -1));

        getContentPane().add(panelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased
        txtnombre.setText (txtnombre.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreKeyReleased

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped
        Character c;
        c = evt.getKeyChar();
        if(!Character.isLetter(c) && c != com.sun.glass.events.KeyEvent.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_txtnombreKeyTyped

    private void txtapellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidoKeyReleased
        txtapellido.setText (txtapellido.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellidoKeyReleased

    private void txtapellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidoKeyTyped
        Character c;
        c = evt.getKeyChar();
        if(!Character.isLetter(c) && c != com.sun.glass.events.KeyEvent.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_txtapellidoKeyTyped

    private void txtcodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyTyped
       char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();
    }//GEN-LAST:event_txtcodigoKeyTyped

    private void txttelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyTyped
      char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();
    }//GEN-LAST:event_txttelefonoKeyTyped

    private void txtdireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyReleased
        txtdireccion.setText (txtdireccion.getText().toUpperCase());  
    }//GEN-LAST:event_txtdireccionKeyReleased

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        //CUANDO CLICKEO LA TABLA LLENO LOS TEXTFIELDS
        nuevoem();
        Select=tabla.getSelectedRow();
        txtcodigo.setText( tabla.getValueAt(Select,0).toString());
        txtnombre.setText( tabla.getValueAt(Select,1).toString());
        txtapellido.setText( tabla.getValueAt(Select,2).toString());
        txttelefono.setText( tabla.getValueAt(Select,3).toString());
        txtdireccion.setText( tabla.getValueAt(Select,4).toString());
        txtLocalidad.setText( tabla.getValueAt(Select,5).toString());
        txtDni.setText( tabla.getValueAt(Select,6).toString());
    }//GEN-LAST:event_tablaMouseClicked

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
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
        if (checkCodigo.isSelected()) {
            columna = 0;
        }else if (checkReferencia.isSelected()) {
                    columna = 1;
              }else if (checkMarca.isSelected()) {
                         columna = 2;
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

    private void checkCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCodigoActionPerformed

    private void btn_cancelaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelaraActionPerformed
        deshabilitar();
    }//GEN-LAST:event_btn_cancelaraActionPerformed

    private void btn_eliminaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminaraActionPerformed
       //MANDO EL CLIENTE A LA PAPELERA
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            consulta.executeUpdate("UPDATE empleado SET estado='INACTIVO' where cod_empleado='"+txtcodigo.getText()+"'");
            JOptionPane.showMessageDialog(null,"EL EMPLEADO SE MOVIÓ A LA PAPELERA DE RECICLAJE");
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarEmpleados();
        MostrarEmpleadosPapelera();
        nuevoem();

    }//GEN-LAST:event_btn_eliminaraActionPerformed

    private void btn_guardaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardaraActionPerformed
        //GUARDO LOS CLIENTES EN LA BD
        txtnombre.setBackground(new Color(204,204,204));
        txtapellido.setBackground(new Color(204,204,204));
        if (txtnombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta el Nombre del Empleado","Advertencia",JOptionPane.WARNING_MESSAGE);
            txtnombre.setBackground(Color.yellow);
            txtnombre.requestFocus();
        }else {
            if (txtapellido.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan los Apellidos del Empleado","Advertencia",JOptionPane.WARNING_MESSAGE);
                txtapellido.setBackground(Color.yellow);
                txtapellido.requestFocus();
            }else{   
                try {
                    Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                    Statement consulta = conn.createStatement();   
                    Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
                    ResultSet r= consulta1.executeQuery("select cod_empleado from empleado");
                    int bandera=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
                    while(r.next()){
                        if(txtcodigo.getText().equals(r.getString(1))){//si el codigo de la tabla es igual a algun codigo de la base de datos
                            bandera=1;       
                        }
                    }             
                    if(bandera==0){//Si el codigo no esta inserto una nuevo registro a la base de datos
                        consulta.executeUpdate("insert into empleado (nombres,apellidos,dirrecion,telefono,estado,dni,localidad)  values('"+txtnombre.getText()+"','"+txtapellido.getText()+"','"+txtdireccion.getText()+"','"+txttelefono.getText()+"','ACTIVO','"+txtDni.getText()+"','"+txtLocalidad.getText()+"')");
                    }else{//si el codigo esta,actualizo la informacion del producto
                         consulta.executeUpdate("UPDATE empleado SET nombres='"+txtnombre.getText()+"',apellidos='"+txtapellido.getText()+"',dirrecion='"+txtdireccion.getText()+"',telefono='"+txttelefono.getText()+"',dni='"+txtDni.getText()+"',localidad='"+txtLocalidad.getText()+"' WHERE cod_empleado='"+txtcodigo.getText()+"'");               
                    }
                    JOptionPane.showMessageDialog(null, "Los datos han sido Guardados Correctamente");
                    nuevoem();
                    MostrarEmpleados();
                }catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"El EMPLEADO"+" "+ txtcodigo.getText()+" "+"DE NOMBRE"+" "+txtnombre.getText()+" "+"YA EXISTE");
                    System.out.println(ex);
		}
            }
        }
    }//GEN-LAST:event_btn_guardaraActionPerformed

    private void btn_nuevoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoaActionPerformed
        nuevoem() ;
    }//GEN-LAST:event_btn_nuevoaActionPerformed

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    this.setVisible(false);
    MostrarEmpleadosPapelera();
    Papelera.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

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

    }//GEN-LAST:event_txtBuscarPapeleraActionPerformed

    DefaultTableModel dm2;
    /* Método filtro PAPELERA*/
    private void filtroPapelera(String consulta, JTable jtableBuscar){  
        dm2 = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter(dm2);
        jtableBuscar.setRowSorter(tr);

        int columna=0;
        // Identificamos cual es el JRadioButton seleccionado para filtrar el
        // resultado de acuerdo a los datos de la columna elegida
        if (checkCodigoPapelera.isSelected()) {
            columna = 1;
        }else if (checkNombrePapelera.isSelected()) {
                columna = 2;
              }else if (checkApellidoPapelera.isSelected()) {
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
        restaurarEmpleado();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void BorrarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarEmpleadoActionPerformed
        //ELIMINO EL EMPLEADO DE LA BD
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            consulta.executeUpdate("UPDATE empleado SET estado='INACTIVO' where cod_empleado='"+txtcodigo.getText()+"'");
            JOptionPane.showMessageDialog(null,"EL EMPLEADO SE MOVIÓ A LA PAPELERA DE RECICLAJE");
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarEmpleados();
        MostrarEmpleadosPapelera();
        nuevoem();        
    }//GEN-LAST:event_BorrarEmpleadoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        TodosSeleccionados=true;
        seleccionarTodos=1;
        MostrarEmpleadosPapelera();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        eliminarEmpleado();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtDniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniKeyReleased

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
    char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtLocalidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalidadKeyReleased
        txtLocalidad.setText(txtLocalidad.getText().toUpperCase());  
    }//GEN-LAST:event_txtLocalidadKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(Registrar_Vendedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrar_Vendedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrar_Vendedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrar_Vendedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Registrar_Vendedores dialog = new Registrar_Vendedores(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuItem BorrarEmpleado;
    private javax.swing.ButtonGroup FiltrarResultados;
    private javax.swing.ButtonGroup FiltrarResultadosPapelera;
    private javax.swing.JDialog Papelera;
    private javax.swing.JButton btn_cancelara;
    private javax.swing.JButton btn_eliminara;
    private javax.swing.JButton btn_guardara;
    private javax.swing.JButton btn_nuevoa;
    private javax.swing.JRadioButton checkApellidoPapelera;
    private javax.swing.JRadioButton checkCodigo;
    private javax.swing.JRadioButton checkCodigoPapelera;
    private javax.swing.JRadioButton checkMarca;
    private javax.swing.JRadioButton checkNombrePapelera;
    private javax.swing.JRadioButton checkReferencia;
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
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tablaPapelera;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarPapelera;
    public static javax.swing.JTextField txtDni;
    public static javax.swing.JTextField txtLocalidad;
    public static javax.swing.JTextField txtapellido;
    public static javax.swing.JTextField txtcodigo;
    public static javax.swing.JTextField txtdireccion;
    public static javax.swing.JTextField txtnombre;
    public static javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
