
package Vistas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;


public class Registrar_Usuarios extends javax.swing.JFrame {
    int Select,SelectPapelera,contador, seleccionarTodos=0;
    boolean TodosSeleccionados=false;
    String codigo,referencia,cantidad,marca,valor;
    String NOMBRE;

    public Registrar_Usuarios() {
        initComponents();
        setLocationRelativeTo(null);
        
        MostrarUsuarios();
         
        Papelera.setSize(990,750);
        Papelera.setLocationRelativeTo(null);
        MostrarUsuariosPapelera();
         
        bloquear();
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
     
     
    public  void bloquear(){  
        jPanel2.setVisible(false);
        checkReferencia.setSelected(true); 
        checkNombrePapelera.setSelected(true);

        txtBuscar.setText("Ingrese su búsqueda");
        txtBuscar.setForeground(Color.gray); 
    }
     
    public  void desbloquear(){  
       jPanel2.setVisible(true);   
       nombre.setText("");
       nombre.requestFocus();
       apellido.setText("");
       direccion.setText("");
       telefono.setText("");
       cbo_acceso.setSelectedItem("Administrador");
       usuario.setText("");
       contraseña.setText("");
       contraseña1.setText("");  
       
        usuario.setText("");
        txtCodigo.setText("");
        contraseña.setText("");
        contraseña1.setText("");
        nombre.setText("");
        apellido.setText("");
        direccion.setText("");
        telefono.setText("");
        usuario.requestFocus();
     }
      
    public void MostrarUsuarios(){   
        txtCodigo.setVisible(false);
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        TableColumn  column = null;
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql                      
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta.executeQuery("select * from usuarios");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            tabla.setRowSorter(new TableRowSorter(modelo));

            modelo.setNumRows(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
            
            tabla.getColumnModel().getColumn(1).setMaxWidth(200);
            tabla.getColumnModel().getColumn(1).setMinWidth(200);
            tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(200);
            tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(200);
            
            tabla.getColumnModel().getColumn(2).setMaxWidth(200);
            tabla.getColumnModel().getColumn(2).setMinWidth(200);
            tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(200);
            tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(200);
            
            tabla.getColumnModel().getColumn(3).setMaxWidth(200);
            tabla.getColumnModel().getColumn(3).setMinWidth(200);
            tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(200);
            tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(200);
            
            tabla.getColumnModel().getColumn(4).setMaxWidth(200);
            tabla.getColumnModel().getColumn(4).setMinWidth(200);
            tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(200);
            tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(200);
            
            tabla.getColumnModel().getColumn(5).setMaxWidth(200);
            tabla.getColumnModel().getColumn(5).setMinWidth(200);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(200);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(200);
            
            tabla.getColumnModel().getColumn(6).setMaxWidth(200);
            tabla.getColumnModel().getColumn(6).setMinWidth(200);
            tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(200);
            tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(200);
            
            tabla.getColumnModel().getColumn(7).setMaxWidth(0);
            tabla.getColumnModel().getColumn(7).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);


            tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tabla.doLayout();
            while(r.next()){
                if(r.getString(7).equals("ACTIVO")){
                    modelo.addRow( new Object [] {null,null,null,null,null,null,null,null});
                    tabla.setValueAt(r.getString(1),i,0);
                    tabla.setValueAt(r.getString(2),i,1);
                    tabla.setValueAt(r.getString(3),i,2);
                    tabla.setValueAt(r.getString(4),i,3);
                    tabla.setValueAt(r.getString(5),i,4);
                    tabla.setValueAt(r.getString(6),i,5);
                    tabla.setValueAt(r.getString(8),i,6);
                    tabla.setValueAt(r.getString(9),i,7);
                    i++;
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Este Usuario Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
        }
    }
     
    DefaultTableModel modelo2 = new DefaultTableModel();
    private boolean[] editable = {true,false,false,false,true,false,false,false,false,false,false,false,false};
    public void MostrarUsuariosPapelera(){
        Object[] datos= new Object[7];
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            ResultSet r2= consulta.executeQuery("select * from usuarios order by nombre");
            int i,j;
            i=0;
            j=0;

            Render3 r= new Render3();
            tablaPapelera.setDefaultRenderer(Object.class,r);
            DefaultTableModel modelo = new DefaultTableModel(new String[]{"SELECCIONAR","USUARIO", "NOMBRE", "APELLIDO", "DIRECCION","TELEFONO","ACCESO"}, 0) {
 
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
                    if(r2.getString(7).equals("INACTIVO")){
                        datos[0]=true;
                        datos[1]=r2.getObject(2);
                        datos[2]=r2.getObject(3);
                        datos[3]=r2.getObject(4);
                        datos[4]=r2.getObject(5);
                        datos[5]=r2.getObject(6);
                        datos[6]=r2.getObject(8);
                        modelo.addRow(datos);
                    } 
                } 
                seleccionarTodos=0;
            }else{
                while(r2.next()){
                    if(r2.getString(7).equals("INACTIVO")){
                        datos[0]=false;
                        datos[1]=r2.getObject(2);
                        datos[2]=r2.getObject(3);
                        datos[3]=r2.getObject(4);
                        datos[4]=r2.getObject(5);
                        datos[5]=r2.getObject(6);
                        datos[6]=r2.getObject(8);
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
    
    public void restaurarUsuario(){    
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            contador=0;
            for(int i=0;i<tablaPapelera.getRowCount();i++){  
                String pago2= tablaPapelera.getValueAt(i,0).toString();
                   if(pago2.equals("true")){ 
                        consulta.executeUpdate("UPDATE usuarios SET estado='ACTIVO' where usuario='"+tablaPapelera.getValueAt(i,1).toString()+"'");
                        contador++;
                   }
            }
            if (contador==1 && TodosSeleccionados==false){
                JOptionPane.showMessageDialog(null,"EL USUARIO SE RESTAURO");
            }else{
                 if (contador>=1 && TodosSeleccionados==false){
                    JOptionPane.showMessageDialog(null,"LOS USUARIOS SE RESTAURARON");
                }
                 else{
                     if (contador==1 && TodosSeleccionados==true){
                        JOptionPane.showMessageDialog(null,"EL USUARIO SE RESTAURO");
                    }else{
                        if (contador>=1 && TodosSeleccionados==true){
                            JOptionPane.showMessageDialog(null,"LOS USUARIOS SE RESTAURARON");
                        }
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarUsuariosPapelera();
        MostrarUsuarios();
    }
    
    public void eliminarUsuario(){      
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            contador=0;
            for(int i=0;i<tablaPapelera.getRowCount();i++){  
                String pago2= tablaPapelera.getValueAt(i,0).toString();
                if(pago2.equals("true")){ 
                    consulta.executeUpdate("DELETE FROM usuarios WHERE usuario='"+tablaPapelera.getValueAt(i,1).toString()+"'");
                    contador++;
                }
            }
            if (contador==1 && TodosSeleccionados==false){
                JOptionPane.showMessageDialog(null,"EL USUARIO FUE ELIMINADO DEL SISTEMA");
            }else{
                if (contador>=1 && TodosSeleccionados==false){
                    JOptionPane.showMessageDialog(null,"LOS USUARIOS FUERON ELIMINADOS DEL SISTEMA");
                }
                 else{
                    if (contador==1 && TodosSeleccionados==true){
                        JOptionPane.showMessageDialog(null,"EL USUARIO FUE ELIMINADO DEL SISTEMA");
                    }else{
                        if (contador>=1 && TodosSeleccionados==true){
                            JOptionPane.showMessageDialog(null,"LOS USUARIOS FUERON ELIMINADOS DEL SISTEMA");
                        }
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarUsuariosPapelera();
        MostrarUsuarios();
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDeslizante1 = new org.edisoncor.gui.varios.PanelDeslizante();
        FiltrarResultados = new javax.swing.ButtonGroup();
        Papelera = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPapelera = new javax.swing.JTable();
        checkApellidoPapelera = new javax.swing.JRadioButton();
        checkNombrePapelera = new javax.swing.JRadioButton();
        checkCodigoPapelera = new javax.swing.JRadioButton();
        txtBuscarPapelera = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        FiltrarResultadosPapelera = new javax.swing.ButtonGroup();
        JPopEliminarEmpleado = new javax.swing.JPopupMenu();
        eliminar = new javax.swing.JMenuItem();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        apellido = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        direccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cbo_acceso = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        contraseña = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        contraseña1 = new javax.swing.JPasswordField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        btn_nuevo = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_inactivar = new javax.swing.JButton();
        btn_cancelara = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        checkCodigo = new javax.swing.JRadioButton();
        checkReferencia = new javax.swing.JRadioButton();
        checkMarca = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        Papelera.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaPapelera.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
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

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 970, 360));

        FiltrarResultadosPapelera.add(checkApellidoPapelera);
        checkApellidoPapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkApellidoPapelera.setText("Apellido");
        checkApellidoPapelera.setOpaque(false);
        jPanel3.add(checkApellidoPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 60, -1, -1));

        FiltrarResultadosPapelera.add(checkNombrePapelera);
        checkNombrePapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkNombrePapelera.setText("Nombre");
        checkNombrePapelera.setOpaque(false);
        jPanel3.add(checkNombrePapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, -1, -1));

        FiltrarResultadosPapelera.add(checkCodigoPapelera);
        checkCodigoPapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCodigoPapelera.setText("Código");
        checkCodigoPapelera.setOpaque(false);
        checkCodigoPapelera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCodigoPapeleraActionPerformed(evt);
            }
        });
        jPanel3.add(checkCodigoPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, -1, -1));

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
        jPanel3.add(txtBuscarPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 280, 37));

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel16.setText("PAPELERA DE RECICLAJE");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 140, 303, 17));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, -1, 80));

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
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 560, 130, 102));

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
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 560, 170, 102));

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
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 170, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel4.setText("Eliminar");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 660, -1, -1));

        jLabel23.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel23.setText("Ir hacia atras");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 660, -1, -1));

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
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 560, -1, 110));

        jLabel25.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel25.setText("Restaurar");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 660, -1, -1));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoPapelera.png"))); // NOI18N
        jButton6.setToolTipText("");
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setFocusPainted(false);
        jButton6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoPapeleraHover.png"))); // NOI18N
        jButton6.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoPapeleraHover.png"))); // NOI18N
        jButton6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoPapeleraHover.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 100, 100));

        Papelera.getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 720));

        eliminar.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        eliminar.setText("Mover a la papelera");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        JPopEliminarEmpleado.add(eliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar usuarios del sistema");
        setMinimumSize(new java.awt.Dimension(700, 400));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoMenumar22.png"))); // NOI18N
        panelImage2.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel6.setText("Nombre");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 110, 40));

        nombre.setBackground(new java.awt.Color(204, 204, 204));
        nombre.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreKeyTyped(evt);
            }
        });
        jPanel2.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 230, 40));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel7.setText("Apellido");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 110, 40));

        apellido.setBackground(new java.awt.Color(204, 204, 204));
        apellido.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        apellido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoActionPerformed(evt);
            }
        });
        apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                apellidoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidoKeyTyped(evt);
            }
        });
        jPanel2.add(apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 230, 40));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel8.setText("Dirección");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 120, 40));

        direccion.setBackground(new java.awt.Color(204, 204, 204));
        direccion.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        direccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direccionActionPerformed(evt);
            }
        });
        direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                direccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                direccionKeyTyped(evt);
            }
        });
        jPanel2.add(direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 230, 40));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel9.setText("Teléfono");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 120, 40));

        telefono.setBackground(new java.awt.Color(204, 204, 204));
        telefono.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        telefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoActionPerformed(evt);
            }
        });
        telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefonoKeyTyped(evt);
            }
        });
        jPanel2.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 230, -1));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel11.setText("Acceso");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 100, 30));

        cbo_acceso.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbo_acceso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Administrador", "Facturador" }));
        cbo_acceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_accesoActionPerformed(evt);
            }
        });
        jPanel2.add(cbo_acceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 230, 35));
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 336, -1, -1));

        usuario.setBackground(new java.awt.Color(204, 204, 204));
        usuario.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });
        usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usuarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usuarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usuarioKeyTyped(evt);
            }
        });
        jPanel2.add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 230, 38));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel2.setText("Usuario");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel3.setText("Contraseña");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, 40));

        contraseña.setBackground(new java.awt.Color(204, 204, 204));
        contraseña.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        contraseña.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contraseñaActionPerformed(evt);
            }
        });
        contraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                contraseñaKeyTyped(evt);
            }
        });
        jPanel2.add(contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 230, 42));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel5.setText("Repetir contr");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, -1, -1));

        contraseña1.setBackground(new java.awt.Color(204, 204, 204));
        contraseña1.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        contraseña1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        contraseña1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contraseña1ActionPerformed(evt);
            }
        });
        contraseña1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                contraseña1KeyTyped(evt);
            }
        });
        jPanel2.add(contraseña1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, 230, 40));

        jLabel26.setFont(new java.awt.Font("Calibri", 1, 32)); // NOI18N
        jLabel26.setText("INGRESO DE USUARIOS");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 320, 50));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesXs-01.png"))); // NOI18N
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 50, 50));

        txtCodigo.setBackground(new java.awt.Color(204, 204, 204));
        txtCodigo.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });
        jPanel2.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 60, 30));

        jLabel39.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel39.setText("*");
        jPanel2.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, 30, 30));

        jLabel40.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel40.setText("*");
        jPanel2.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, 30, 30));

        jLabel41.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel41.setText("*");
        jPanel2.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 400, 30, 30));

        jLabel42.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel42.setText("*");
        jPanel2.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 30, 30));

        panelImage2.add(jPanel2);
        jPanel2.setBounds(20, 10, 390, 530);

        btn_nuevo.setBackground(new java.awt.Color(51, 153, 255));
        btn_nuevo.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnNuevo11.png"))); // NOI18N
        btn_nuevo.setBorder(null);
        btn_nuevo.setBorderPainted(false);
        btn_nuevo.setContentAreaFilled(false);
        btn_nuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_nuevo.setFocusCycleRoot(true);
        btn_nuevo.setFocusable(false);
        btn_nuevo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnNuevo11.png"))); // NOI18N
        btn_nuevo.setRequestFocusEnabled(false);
        btn_nuevo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnNuevoHover11.png"))); // NOI18N
        btn_nuevo.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnNuevoHover11.png"))); // NOI18N
        btn_nuevo.setVerifyInputWhenFocusTarget(false);
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });
        panelImage2.add(btn_nuevo);
        btn_nuevo.setBounds(-20, 510, 210, 160);

        btn_guardar.setBackground(new java.awt.Color(51, 153, 255));
        btn_guardar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardar.png"))); // NOI18N
        btn_guardar.setBorder(null);
        btn_guardar.setBorderPainted(false);
        btn_guardar.setContentAreaFilled(false);
        btn_guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_guardar.setFocusCycleRoot(true);
        btn_guardar.setFocusable(false);
        btn_guardar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btn_guardar.setRequestFocusEnabled(false);
        btn_guardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btn_guardar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btn_guardar.setVerifyInputWhenFocusTarget(false);
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        panelImage2.add(btn_guardar);
        btn_guardar.setBounds(140, 510, 140, 160);

        btn_inactivar.setBackground(new java.awt.Color(51, 153, 255));
        btn_inactivar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_inactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnEliminar.png"))); // NOI18N
        btn_inactivar.setBorder(null);
        btn_inactivar.setBorderPainted(false);
        btn_inactivar.setContentAreaFilled(false);
        btn_inactivar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_inactivar.setFocusCycleRoot(true);
        btn_inactivar.setFocusable(false);
        btn_inactivar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnEliminarHover.png"))); // NOI18N
        btn_inactivar.setRequestFocusEnabled(false);
        btn_inactivar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnEliminarHover.png"))); // NOI18N
        btn_inactivar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnEliminarHover.png"))); // NOI18N
        btn_inactivar.setVerifyInputWhenFocusTarget(false);
        btn_inactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inactivarActionPerformed(evt);
            }
        });
        panelImage2.add(btn_inactivar);
        btn_inactivar.setBounds(270, 510, 140, 160);

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
        panelImage2.add(btn_cancelara);
        btn_cancelara.setBounds(400, 520, 140, 140);

        tabla.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLAVE", "USUARIO", "NOMBRE", "APELLIDO", "DIRECCION", "TELEFONO", "PERMISOS", "codigo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setComponentPopupMenu(JPopEliminarEmpleado);
        tabla.setRowHeight(25);
        tabla.setRowMargin(4);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        panelImage2.add(jScrollPane1);
        jScrollPane1.setBounds(410, 85, 570, 410);

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel19.setText("Cancelar");
        panelImage2.add(jLabel19);
        jLabel19.setBounds(430, 640, 87, 30);

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel18.setText("a papelera");
        panelImage2.add(jLabel18);
        jLabel18.setBounds(290, 660, 120, 30);

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel17.setText("Guardar");
        panelImage2.add(jLabel17);
        jLabel17.setBounds(170, 640, 83, 30);

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel13.setText("Nuevo");
        panelImage2.add(jLabel13);
        jLabel13.setBounds(50, 640, 65, 30);

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 32)); // NOI18N
        jLabel10.setText("INGRESO DE USUARIOS");
        panelImage2.add(jLabel10);
        jLabel10.setBounds(80, 40, 320, 40);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesXs-01.png"))); // NOI18N
        panelImage2.add(jLabel14);
        jLabel14.setBounds(30, 40, 35, 32);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        panelImage2.add(jLabel15);
        jLabel15.setBounds(570, 20, 59, 59);

        FiltrarResultados.add(checkCodigo);
        checkCodigo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCodigo.setText("Usuario");
        checkCodigo.setOpaque(false);
        checkCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCodigoActionPerformed(evt);
            }
        });
        panelImage2.add(checkCodigo);
        checkCodigo.setBounds(630, 0, 95, 31);

        FiltrarResultados.add(checkReferencia);
        checkReferencia.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkReferencia.setText("Nombre");
        checkReferencia.setOpaque(false);
        panelImage2.add(checkReferencia);
        checkReferencia.setBounds(730, 0, 97, 31);

        FiltrarResultados.add(checkMarca);
        checkMarca.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkMarca.setText("Apellido");
        checkMarca.setOpaque(false);
        panelImage2.add(checkMarca);
        checkMarca.setBounds(840, 0, 97, 31);

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
        panelImage2.add(txtBuscar);
        txtBuscar.setBounds(650, 30, 300, 40);
        panelImage2.add(jSeparator1);
        jSeparator1.setBounds(650, 70, 280, 10);

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
        panelImage2.add(jButton1);
        jButton1.setBounds(870, 550, 45, 60);

        jLabel30.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel30.setText("Papelera");
        panelImage2.add(jLabel30);
        jLabel30.setBounds(850, 620, 95, 33);

        jLabel20.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel20.setText("de reciclaje");
        panelImage2.add(jLabel20);
        jLabel20.setBounds(840, 640, 122, 33);

        jLabel21.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel21.setText("Mover");
        panelImage2.add(jLabel21);
        jLabel21.setBounds(310, 640, 88, 30);

        getContentPane().add(panelImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoKeyTyped
        char c = evt.getKeyChar();
        if (!((Character.isDigit(c))||(c==KeyEvent.VK_ENTER)||(c==KeyEvent.VK_BACK_SPACE))) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo deben digitarse Numeros","Advertencia",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_telefonoKeyTyped

    private void telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoActionPerformed

    }//GEN-LAST:event_telefonoActionPerformed

    private void direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direccionKeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(direccion.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Ingrese el Dato","Advertencia",JOptionPane.WARNING_MESSAGE);
            else
            telefono.requestFocus();
        }
    }//GEN-LAST:event_direccionKeyTyped

    private void direccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direccionKeyReleased
        direccion.setText (direccion.getText().toUpperCase()); 
    }//GEN-LAST:event_direccionKeyReleased

    private void direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direccionActionPerformed

    }//GEN-LAST:event_direccionActionPerformed

    private void apellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoKeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(apellido.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Ingrese el Dato","Advertencia",JOptionPane.WARNING_MESSAGE);
            else
            direccion.requestFocus();
        }
        char c = evt.getKeyChar();
        if (!((Character.isLetter(c))||(c==KeyEvent.VK_DELETE)||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_SPACE) ))
        {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo deben Digitarse Letras","Advertencia",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_apellidoKeyTyped

    private void apellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoKeyReleased
        apellido.setText (apellido.getText().toUpperCase()); 
    }//GEN-LAST:event_apellidoKeyReleased

    private void apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoActionPerformed

    }//GEN-LAST:event_apellidoActionPerformed

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(nombre.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Ingrese el Dato","Advertencia",JOptionPane.WARNING_MESSAGE);
            else
            apellido.requestFocus();
        }
        char c = evt.getKeyChar();
        if (!((Character.isLetter(c))||(c==KeyEvent.VK_DELETE)||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_SPACE) ))
        {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo deben Digitarse Letras","Advertencia",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_nombreKeyTyped

    private void nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyReleased
        nombre.setText (nombre.getText().toUpperCase());   
    }//GEN-LAST:event_nombreKeyReleased

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed

    }//GEN-LAST:event_nombreActionPerformed

    private void contraseña1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contraseña1KeyTyped
        
    }//GEN-LAST:event_contraseña1KeyTyped

    private void contraseña1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contraseña1ActionPerformed

    }//GEN-LAST:event_contraseña1ActionPerformed

    private void contraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contraseñaKeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(contraseña.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Ingrese el Dato");
            else
            contraseña1.requestFocus();
        }
    }//GEN-LAST:event_contraseñaKeyTyped

    private void contraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contraseñaActionPerformed

    }//GEN-LAST:event_contraseñaActionPerformed

    private void usuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioKeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(usuario.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Ingrese el Dato");
            else
            contraseña.requestFocus();
        }
    }//GEN-LAST:event_usuarioKeyTyped

    private void usuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioKeyReleased

    }//GEN-LAST:event_usuarioKeyReleased

    private void usuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioKeyPressed

    }//GEN-LAST:event_usuarioKeyPressed

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed

    }//GEN-LAST:event_usuarioActionPerformed

    private void cbo_accesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_accesoActionPerformed

    }//GEN-LAST:event_cbo_accesoActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed

        desbloquear();
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed

        if (cbo_acceso.getSelectedItem().equals("Seleccione")) {
            JOptionPane.showMessageDialog(null, "Seleccione el Tipo de Acceso","Advertencia",JOptionPane.WARNING_MESSAGE);
            cbo_acceso.requestFocus();
        }else
            if (usuario.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Falta el nickname del usuario","Advertencia",JOptionPane.WARNING_MESSAGE);
                usuario.requestFocus();
            }else {
                if (contraseña.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Faltan digitar la Clave","Advertencia",JOptionPane.WARNING_MESSAGE);
                    contraseña.requestFocus();
                }else {
                    if (contraseña1.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "las claves no coinciden","Advertencia",JOptionPane.WARNING_MESSAGE);
                        contraseña.requestFocus();
                    }else
                            if(contraseña.getText().equals(contraseña1.getText())){
                                try{
                                    Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                                    Statement consulta=conn.createStatement();
                                    Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
                                    ResultSet r= consulta1.executeQuery("select usuario, cod_usuario from usuarios WHERE estado='ACTIVO'");
                                    int banderaUsuario=0,banderaCodigo=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
                                    String usuarioMayus=usuario.getText().toUpperCase();
                                    String usuarioMinus=usuario.getText().toLowerCase();
                                    while(r.next()){
                                        if(usuarioMayus.equals(r.getString(1)) || usuarioMinus.equals(r.getString(1))){//si el codigo de la tabla es igual a algun codigo de la base de datos
                                            banderaUsuario=1;
                                        }
                                        if(txtCodigo.getText().equals(r.getString(2))){//si el codigo de la tabla es igual a algun codigo de la base de datos
                                            banderaCodigo=1;
                                        }

                                    }
                                    if(banderaUsuario==1 && txtCodigo.getText().equals("")){
                                        JOptionPane.showMessageDialog(null,"El nombre de usuario ya esta en uso") ;
                                        usuario.requestFocus();
                                    }else if(banderaCodigo==1){
                                          consulta.executeUpdate("UPDATE usuarios SET nombre='"+nombre.getText()+"', apellido='"+apellido.getText()+"',direccion='"+direccion.getText()+"',telefono='"+telefono.getText()+"',usuario='"+usuario.getText()+"',clave='"+contraseña.getText()+"',acceso='"+cbo_acceso.getSelectedItem()+"' WHERE cod_usuario='"+txtCodigo.getText()+"'");                 
                                          JOptionPane.showMessageDialog(null,"El usuario fue modificado correctamente") ;
                     
                                    }else{
                                        consulta.executeUpdate("insert into usuarios(clave,usuario,nombre,apellido,direccion,telefono,acceso,estado) values('"+contraseña.getText()+"','"+usuario.getText()+"','"+nombre.getText()+"','"+apellido.getText()+"','"+direccion.getText()+"','"+telefono.getText()+"','"+cbo_acceso.getSelectedItem()+"','ACTIVO')");
                                        consulta.executeUpdate("insert into empleado (nombres,apellidos,dirrecion,telefono,estado,dni,localidad)  values('"+nombre.getText()+"','"+apellido.getText()+"','"+direccion.getText()+"','"+telefono.getText()+"','ACTIVO','','')");
                    
                                        JOptionPane.showMessageDialog(null,"Los datos han sido Gurdados Correctamente") ;// esto aparece cuando se ha guardado correctamente
                                        usuario.requestFocus();
                                        usuario.setText("");
                                        contraseña.setText("");
                                        contraseña1.setText("");
                                        nombre.setText("");
                                        apellido.setText("");
                                        direccion.setText("");
                                        telefono.setText("");      
                                        cbo_acceso.setSelectedIndex(0);
                                        
                                        
                                    }
                                    
                                } catch(SQLException e){
                                    System.out.println(e);
                                    JOptionPane.showMessageDialog(null,"este nombre ya esta en uso") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
                                    usuario.setText("");
                                    usuario.requestFocus();

                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"las contraseñas no coincide","Error",JOptionPane.ERROR_MESSAGE) ;
                            }
                    
            }
        }  
        MostrarUsuarios();
        desbloquear();
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_inactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inactivarActionPerformed
    try{
        Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
        Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

        consulta.executeUpdate("UPDATE usuarios SET estado='INACTIVO' where cod_usuario='"+txtCodigo.getText()+"'");
        JOptionPane.showMessageDialog(null,"EL USUARIO SE ENVIO A LA PAPELERA");         
    }catch(SQLException e){
        System.out.println(e);
        JOptionPane.showMessageDialog(null,"Error en el SQL");
    }
    MostrarUsuarios();
    MostrarUsuariosPapelera();
    desbloquear();
    }//GEN-LAST:event_btn_inactivarActionPerformed

    private void btn_cancelaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelaraActionPerformed
        bloquear(); 
    }//GEN-LAST:event_btn_cancelaraActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        jPanel2.setVisible(true);
        Select=tabla.getSelectedRow();
        contraseña.setText( tabla.getValueAt(Select,0).toString());
        contraseña1.setText( tabla.getValueAt(Select,0).toString());
        usuario.setText( tabla.getValueAt(Select,1).toString());
        nombre.setText( tabla.getValueAt(Select,2).toString());
        apellido.setText( tabla.getValueAt(Select,3).toString());
        direccion.setText( tabla.getValueAt(Select,4).toString());
        telefono.setText( tabla.getValueAt(Select,5).toString());
        txtCodigo.setText( tabla.getValueAt(Select,7).toString());
        cbo_acceso.setSelectedItem (tabla.getValueAt(Select,6).toString());
    }//GEN-LAST:event_tablaMouseClicked

    private void checkCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCodigoActionPerformed

    }//GEN-LAST:event_checkCodigoActionPerformed

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

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed

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
        if (checkCodigo.isSelected()) {
            columna = 1;
        }else if (checkReferencia.isSelected()) {
                    columna = 2;
               }else if (checkMarca.isSelected()) {
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
            filtro2(txtBuscar.getText(), tabla);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tablaPapeleraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPapeleraMouseClicked

    }//GEN-LAST:event_tablaPapeleraMouseClicked

    private void checkCodigoPapeleraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCodigoPapeleraActionPerformed

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

        restaurarUsuario();
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        TodosSeleccionados=true;
        seleccionarTodos=1;
        MostrarUsuariosPapelera();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        eliminarUsuario();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        Papelera.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        try{
            Connection conn= conexion.ObtenerConexion();    
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            consulta.executeUpdate("UPDATE usuarios SET estado='INACTIVO' where cod_usuario='"+txtCodigo.getText()+"'");
            JOptionPane.showMessageDialog(null,"EL USUARIO SE ENVIO A LA PAPELERA");
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL");
        }
        MostrarUsuarios();
        MostrarUsuariosPapelera();
        desbloquear();
    }//GEN-LAST:event_eliminarActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       /* this.setVisible(false);
        MostrarArticulosPapelera();
        Papelera.setVisible(true);*/
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoKeyTyped

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrar_Usuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup FiltrarResultados;
    private javax.swing.ButtonGroup FiltrarResultadosPapelera;
    private javax.swing.JPopupMenu JPopEliminarEmpleado;
    private javax.swing.JDialog Papelera;
    private javax.swing.JTextField apellido;
    private javax.swing.JButton btn_cancelara;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_inactivar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JComboBox cbo_acceso;
    private javax.swing.JRadioButton checkApellidoPapelera;
    private javax.swing.JRadioButton checkCodigo;
    private javax.swing.JRadioButton checkCodigoPapelera;
    private javax.swing.JRadioButton checkMarca;
    private javax.swing.JRadioButton checkNombrePapelera;
    private javax.swing.JRadioButton checkReferencia;
    private javax.swing.JPasswordField contraseña;
    private javax.swing.JPasswordField contraseña1;
    private javax.swing.JTextField direccion;
    private javax.swing.JMenuItem eliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField nombre;
    private org.edisoncor.gui.varios.PanelDeslizante panelDeslizante1;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tablaPapelera;
    private javax.swing.JTextField telefono;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarPapelera;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables

}
