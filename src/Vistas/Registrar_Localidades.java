
package Vistas;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.Font;
import javax.swing.table.JTableHeader;

public class Registrar_Localidades extends javax.swing.JDialog {
    int Select, SelectPapelera,contador, seleccionarTodos=0;
    boolean TodosSeleccionados=false;
    String codigo,referencia,cantidad,marca,valor;

    public Registrar_Localidades(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        setLocationRelativeTo(null);
        MostrarLocalidad();
        
        Papelera.setSize(990,750);
        Papelera.setLocationRelativeTo(null);
        MostrarLocalidadesPapelera();
        
        desactivar();
        
        JTableHeader th; 
        th = tablaLocalidad.getTableHeader(); 
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
    
    public void desactivar(){
        jPanel1.setVisible(false);                   
        Papelera.setVisible(false);
        
        checkLocalidad.setSelected(true); 
        checkLocalidadPapelera.setSelected(true);
        
        txtBuscar.setText("Ingrese su búsqueda");
        txtBuscar.setForeground(Color.gray);
    
        txtLocalidad.setEditable(false);
        txtCodigoLocalidad.setEditable(false);
        txtProvincia.setEditable(false);
        txtCodigoPostal.setEditable(false);
            
        btn_guardara.setEnabled(false);
        btn_cancelara.setEnabled(false);
        btn_eliminara.setEnabled(false);

        txtCodigoLocalidad.setVisible(false);
    } 
    
    public void nuevoo(){
        jPanel1.setVisible(true);

        txtLocalidad.setText("");
        txtCodigoLocalidad.setText("");
        txtLocalidad.requestFocus();
        txtProvincia.setText("");
        txtCodigoPostal.setText("");
        txtLocalidad.setEditable(true);
        txtCodigoLocalidad.setEditable(true);
        txtProvincia.setEditable(true);
        txtCodigoPostal.setEditable(true);
        
        btn_guardara.setEnabled(true);
        btn_cancelara.setEnabled(true);
         btn_eliminara.setEnabled(true);
    }

    public void MostrarLocalidad(){
        TableColumn  column = null;
        tablaLocalidad.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaLocalidad.getColumnModel().getColumn(0).setMinWidth(0);
        tablaLocalidad.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tablaLocalidad.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);     
         try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet r= consulta.executeQuery("select * from localidad order by localidad");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tablaLocalidad.getModel();
            tablaLocalidad.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            while(r.next()){
                if(r.getString(5).equals("ACTIVO")){
                    modelo.addRow( new Object [] {null,null,null,null});
                    tablaLocalidad.setValueAt(r.getString(1),i,0);
                    tablaLocalidad.setValueAt(r.getString(2),i,1);
                    tablaLocalidad.setValueAt(r.getString(3),i,2);
                    tablaLocalidad.setValueAt(r.getString(4),i,3);;
                    i++;
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Este Localidad Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
        } 
    }

    DefaultTableModel modelo2 = new DefaultTableModel();
    private boolean[] editable = {true,false,false,false,false};
    public void MostrarLocalidadesPapelera(){
        Object[] datos= new Object[5];
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            ResultSet r2= consulta.executeQuery("select * from  localidad order by localidad");
            int i,j;
            i=0;
            j=0;
            Render3 r= new Render3();
            tablaPapelera.setDefaultRenderer(Object.class,r);
            DefaultTableModel modelo = new DefaultTableModel(new String[]{"SELECCIONAR","CODIGO", "LOCALIDAD", "PROVINCIA", "CODIGO POSTAL"}, 0) {
 
                Class[] types = new Class[]{
                    java.lang.Boolean.class,java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
                    if(r2.getString(5).equals("INACTIVO")){
                        datos[0]=true;
                        datos[1]=r2.getObject(1);
                        datos[2]=r2.getObject(2);
                        datos[3]=r2.getObject(3);
                        datos[4]=r2.getObject(4);
                        modelo.addRow(datos);
                    }
                } 
                seleccionarTodos=0;
            }else{
                while(r2.next()){
                    if(r2.getString(5).equals("INACTIVO")){
                        datos[0]=false;
                        datos[1]=r2.getObject(1);
                        datos[2]=r2.getObject(2);
                        datos[3]=r2.getObject(3);
                        datos[4]=r2.getObject(4);
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
    
    public void restaurarLocalidad(){       
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            contador=0;
            for(int i=0;i<tablaPapelera.getRowCount();i++){  
                String pago2= tablaPapelera.getValueAt(i,0).toString();
                if(pago2.equals("true")){ 
                    consulta.executeUpdate("UPDATE localidad SET estado='ACTIVO' where cod_localidad='"+tablaPapelera.getValueAt(i,1).toString()+"'");
                    contador++;
                }
            }
            if (contador==1 && TodosSeleccionados==false){
                JOptionPane.showMessageDialog(null,"LA LOCALIDAD SE RESTAURO");
            }else{
                if (contador>=1 && TodosSeleccionados==false){
                    JOptionPane.showMessageDialog(null,"LAS LOCALIDADES SE RESTAURARON");
                }
                else{
                    if (contador==1 && TodosSeleccionados==true){
                        JOptionPane.showMessageDialog(null,"LA LOCALIDAD SE RESTAURO");
                    }else{
                        if (contador>=1 && TodosSeleccionados==true){
                           JOptionPane.showMessageDialog(null,"LAS LOCALIDADES SE RESTAURARON");
                        }
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarLocalidadesPapelera();
        MostrarLocalidad();
    }
    
     public void eliminarLocalidad(){      
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            contador=0;
            for(int i=0;i<tablaPapelera.getRowCount();i++){  
                String pago2= tablaPapelera.getValueAt(i,0).toString();
                if(pago2.equals("true")){ 
                    consulta.executeUpdate("DELETE FROM localidad WHERE cod_localidad='"+tablaPapelera.getValueAt(i,1).toString()+"'");
                    contador++;
                }
            }
            if (contador==1 && TodosSeleccionados==false){
                JOptionPane.showMessageDialog(null,"LA LOCALIDAD FUE ELIMINADA DEL SISTEMA");
            }else{
                if (contador>=1 && TodosSeleccionados==false){
                    JOptionPane.showMessageDialog(null,"LAS LOCALIDADES FUERON ELIMINADAS DEL SISTEMA");
                }
                else{
                    if (contador==1 && TodosSeleccionados==true){
                        JOptionPane.showMessageDialog(null,"LA LOCALIDAD FUE ELIMINADA DEL SISTEMA");
                    }else{
                        if (contador>=1 && TodosSeleccionados==true){
                            JOptionPane.showMessageDialog(null,"LAS LOCALIDADES FUERON ELIMINADAS DEL SISTEMA");
                        }
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        MostrarLocalidadesPapelera();
        MostrarLocalidad();
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
        checkProvinciaPapelera = new javax.swing.JRadioButton();
        checkLocalidadPapelera = new javax.swing.JRadioButton();
        checkCodigoPostalPapelera = new javax.swing.JRadioButton();
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
        jButton6 = new javax.swing.JButton();
        FiltrarResultadosPapelera = new javax.swing.ButtonGroup();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        txtLocalidad = new javax.swing.JTextField();
        txtCodigoLocalidad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtProvincia = new javax.swing.JTextField();
        txtCodigoPostal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaLocalidad = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        checkCodigoPostal = new javax.swing.JRadioButton();
        checkLocalidad = new javax.swing.JRadioButton();
        checkProvincia = new javax.swing.JRadioButton();
        btn_nuevoa = new javax.swing.JButton();
        btn_eliminara = new javax.swing.JButton();
        btn_guardara = new javax.swing.JButton();
        btn_cancelara = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
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

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 960, 360));

        FiltrarResultadosPapelera.add(checkProvinciaPapelera);
        checkProvinciaPapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkProvinciaPapelera.setText("Provincia");
        checkProvinciaPapelera.setOpaque(false);
        checkProvinciaPapelera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkProvinciaPapeleraActionPerformed(evt);
            }
        });
        jPanel2.add(checkProvinciaPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 70, -1, -1));

        FiltrarResultadosPapelera.add(checkLocalidadPapelera);
        checkLocalidadPapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkLocalidadPapelera.setText("Localidad");
        checkLocalidadPapelera.setOpaque(false);
        checkLocalidadPapelera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkLocalidadPapeleraActionPerformed(evt);
            }
        });
        jPanel2.add(checkLocalidadPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 70, -1, -1));

        FiltrarResultadosPapelera.add(checkCodigoPostalPapelera);
        checkCodigoPostalPapelera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCodigoPostalPapelera.setText("Código postal");
        checkCodigoPostalPapelera.setOpaque(false);
        checkCodigoPostalPapelera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCodigoPostalPapeleraActionPerformed(evt);
            }
        });
        jPanel2.add(checkCodigoPostalPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, -1, -1));

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
        jPanel2.add(txtBuscarPapelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, 300, 37));

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel14.setText("PAPELERA DE RECICLAJE");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 44, -1, 50));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, 303, 17));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, -1, 80));

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
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 170, 102));

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

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel2.setText("Eliminar");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 650, -1, -1));

        jLabel23.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel23.setText("Ir hacia atras");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 650, -1, -1));

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
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 560, -1, 102));

        jLabel25.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel25.setText("Restaurar");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 650, -1, -1));

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
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 100, 100));

        javax.swing.GroupLayout PapeleraLayout = new javax.swing.GroupLayout(Papelera.getContentPane());
        Papelera.getContentPane().setLayout(PapeleraLayout);
        PapeleraLayout.setHorizontalGroup(
            PapeleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE)
        );
        PapeleraLayout.setVerticalGroup(
            PapeleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ingreso de localidades");

        panelImage1.setBackground(new java.awt.Color(255, 255, 255));
        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoMenumar22.png"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtLocalidad.setBackground(new java.awt.Color(204, 204, 204));
        txtLocalidad.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtLocalidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLocalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLocalidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocalidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLocalidadKeyTyped(evt);
            }
        });
        jPanel1.add(txtLocalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 230, 40));

        txtCodigoLocalidad.setBackground(new java.awt.Color(204, 204, 204));
        txtCodigoLocalidad.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtCodigoLocalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoLocalidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoLocalidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoLocalidadKeyTyped(evt);
            }
        });
        jPanel1.add(txtCodigoLocalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 50, 30));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel7.setText("Departamento");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 140, 40));

        txtProvincia.setBackground(new java.awt.Color(204, 204, 204));
        txtProvincia.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtProvincia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProvincia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProvinciaKeyReleased(evt);
            }
        });
        jPanel1.add(txtProvincia, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 230, 40));

        txtCodigoPostal.setBackground(new java.awt.Color(204, 204, 204));
        txtCodigoPostal.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtCodigoPostal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoPostal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoPostalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoPostalKeyTyped(evt);
            }
        });
        jPanel1.add(txtCodigoPostal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 230, 40));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel9.setText("Ciudad");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 120, 40));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel12.setText("Codigo postal");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 40));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 32)); // NOI18N
        jLabel4.setText("INGRESO DE LOCALIDADES");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 380, 40));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoProveedoresXs-01.png"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel40.setText("*");
        jPanel1.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, 30, 30));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel41.setText("*");
        jPanel1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 30, 30));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel42.setText("*");
        jPanel1.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 30, 30));

        panelImage1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 410, 450));

        tablaLocalidad.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        tablaLocalidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "LOCALIDAD", "PROVINCIA", "CODIGO POSTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaLocalidad.setComponentPopupMenu(jPopupMenu1);
        tablaLocalidad.setOpaque(false);
        tablaLocalidad.setRowHeight(25);
        tablaLocalidad.setRowMargin(4);
        tablaLocalidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaLocalidadMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaLocalidad);

        panelImage1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 530, 360));

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
        panelImage1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 280, 50));

        FiltrarResultados.add(checkCodigoPostal);
        checkCodigoPostal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCodigoPostal.setText("Código postal");
        checkCodigoPostal.setOpaque(false);
        checkCodigoPostal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCodigoPostalActionPerformed(evt);
            }
        });
        panelImage1.add(checkCodigoPostal, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 170, -1));

        FiltrarResultados.add(checkLocalidad);
        checkLocalidad.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkLocalidad.setText("Ciudad");
        checkLocalidad.setOpaque(false);
        panelImage1.add(checkLocalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, -1, -1));

        FiltrarResultados.add(checkProvincia);
        checkProvincia.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkProvincia.setText("Departamento");
        checkProvincia.setOpaque(false);
        panelImage1.add(checkProvincia, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, -1, -1));

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
        panelImage1.add(btn_nuevoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 510, 150, 150));

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
        panelImage1.add(btn_eliminara, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 510, 140, 150));

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
        panelImage1.add(btn_guardara, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 510, 120, 150));

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
        panelImage1.add(btn_cancelara, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 510, 150, 160));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        panelImage1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, -1, 80));
        panelImage1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 280, 10));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel13.setText("Nuevo");
        panelImage1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 630, -1, 50));

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel16.setText("Guardar");
        panelImage1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 630, -1, 50));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel17.setText("a papelera");
        panelImage1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 660, -1, 40));

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel18.setText("Cancelar");
        panelImage1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 630, -1, 60));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoProveedoresXs-01.png"))); // NOI18N
        panelImage1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 50, 50));

        jLabel20.setFont(new java.awt.Font("Calibri", 1, 32)); // NOI18N
        jLabel20.setText("INGRESO DE LOCALIDADES");
        panelImage1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 380, 40));

        jLabel21.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel21.setText("Mover");
        panelImage1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 630, -1, 50));

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
        panelImage1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 530, 100, 90));

        jLabel26.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel26.setText("Papelera");
        panelImage1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 610, -1, -1));

        jLabel27.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel27.setText("de reciclaje");
        panelImage1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 630, -1, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menueliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menueliminarActionPerformed
        Select=tablaLocalidad.getSelectedRow();
        if(Select>=0){          
            String cod_localidad= tablaLocalidad.getValueAt(Select,0).toString();
            try{
                Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

                consulta.executeUpdate("UPDATE localidad SET estado='INACTIVO' where cod_localidad='"+cod_localidad+"'");
                JOptionPane.showMessageDialog(null,"LA LOCALIDAD SE MOVIÓ A LA PAPELERA DE RECICLAJE");
            }catch(SQLException e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Error en el SQL") ;
            }
        }else{
            JOptionPane.showMessageDialog(null,"No se selecciono ninguna fila") ;
        }
        nuevoo();
        MostrarLocalidad();
        MostrarLocalidadesPapelera();     
    }//GEN-LAST:event_menueliminarActionPerformed

    private void tablaLocalidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaLocalidadMouseClicked
        nuevoo();
        Select=tablaLocalidad.getSelectedRow();
        txtCodigoLocalidad.setText( tablaLocalidad.getValueAt(Select,0).toString());
        txtLocalidad.setText( tablaLocalidad.getValueAt(Select,1).toString());
        txtProvincia.setText( tablaLocalidad.getValueAt(Select,2).toString());
        txtCodigoPostal.setText( tablaLocalidad.getValueAt(Select,3).toString());
    }//GEN-LAST:event_tablaLocalidadMouseClicked

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
        if (checkCodigoPostal.isSelected()) {
            columna = 3;
        }else  if (checkLocalidad.isSelected()) {
                  columna = 1;
                }else if (checkProvincia.isSelected()) {
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
            filtro2(txtBuscar.getText().toUpperCase(), tablaLocalidad);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void checkCodigoPostalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCodigoPostalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCodigoPostalActionPerformed

    private void btn_nuevoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoaActionPerformed
        nuevoo();
    }//GEN-LAST:event_btn_nuevoaActionPerformed

    private void btn_eliminaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminaraActionPerformed
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            consulta.executeUpdate("UPDATE localidad SET estado='INACTIVO' where cod_localidad='"+txtCodigoLocalidad.getText()+"'");
            JOptionPane.showMessageDialog(null,"LA LOCALIDAD SE MOVIÓ A LA PAPELERA DE RECICLAJE");
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        nuevoo();
        MostrarLocalidad();
        MostrarLocalidadesPapelera();
    }//GEN-LAST:event_btn_eliminaraActionPerformed

    private void btn_guardaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardaraActionPerformed
        txtLocalidad.setBackground(new Color(204,204,204));
        txtProvincia.setBackground(new Color(204,204,204));
        txtCodigoPostal.setBackground(new Color(204,204,204));
        if (txtLocalidad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta el nombre de la localidad ");
            txtLocalidad.setBackground(Color.yellow);
            txtLocalidad.requestFocus();
        }else
            if (txtProvincia.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Falta el nombre de la provincia");
                txtProvincia.setBackground(Color.yellow);
                txtProvincia.requestFocus();
            }else
                if (txtCodigoPostal.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Falta el código postal");
                    txtCodigoPostal.setBackground(Color.yellow);
                    txtCodigoPostal.requestFocus();
                }else
                    try {
                        Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                        Statement consulta = conn.createStatement();                            
                        Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
                        ResultSet r= consulta1.executeQuery("select cod_localidad from localidad");
                        int bandera=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
                        while(r.next()){
                            if(txtCodigoLocalidad.getText().equals(r.getString(1))){//si el codigo de la tabla es igual a algun codigo de la base de datos
                                bandera=1;
                            }
                        }           
                        if(bandera==0){//Si el codigo no esta inserto una nuevo registro a la base de datos
                            consulta.executeUpdate("insert into localidad (localidad,provincia,codigo_postal,estado)  values('"+txtLocalidad.getText()+"','"+txtProvincia.getText()+"','"+txtCodigoPostal.getText()+"','ACTIVO')");          
                        }else{//si el codigo esta,actualizo la informacion del producto
                             consulta.executeUpdate("UPDATE localidad SET localidad='"+txtLocalidad.getText()+"',provincia='"+txtProvincia.getText()+"',codigo_postal='"+txtCodigoPostal.getText()+"' WHERE cod_localidad='"+txtCodigoLocalidad.getText()+"'");               
                        }
                        JOptionPane.showMessageDialog(null, "Los datos han sido Guardados Correctamente");
                        nuevoo();
                        MostrarLocalidad();
                    } 
                    catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,"LA LOCALIDAD "+txtLocalidad.getText()+" "+"YA EXISTE");
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

    private void checkCodigoPostalPapeleraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCodigoPostalPapeleraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCodigoPostalPapeleraActionPerformed

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

    DefaultTableModel dm2;
    /* Método filtro*/
    private void filtroPapelera(String consulta, JTable jtableBuscar){ 
        dm2 = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter(dm2);
        jtableBuscar.setRowSorter(tr);

        int columna=0;
        // Identificamos cual es el JRadioButton seleccionado para filtrar el
        // resultado de acuerdo a los datos de la columna elegida
        if (checkCodigoPostalPapelera.isSelected()) {
            columna = 4;
        }else 
            if (checkLocalidadPapelera.isSelected()) {
               columna = 2;
            }else
                if (checkProvinciaPapelera.isSelected()) {
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
        restaurarLocalidad();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        TodosSeleccionados=true;
        seleccionarTodos=1;
        MostrarLocalidadesPapelera();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        eliminarLocalidad();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        Papelera.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCodigoLocalidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoLocalidadKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
        evt.consume();// TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoLocalidadKeyTyped

    private void txtCodigoLocalidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoLocalidadKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoLocalidadKeyReleased

    private void txtCodigoLocalidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoLocalidadKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoLocalidadKeyPressed

    private void txtCodigoPostalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoPostalKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
        evt.consume();
    }//GEN-LAST:event_txtCodigoPostalKeyTyped

    private void txtCodigoPostalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoPostalKeyReleased
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(txtCodigoPostal.getText().equals(""))
                JOptionPane.showMessageDialog(null,"Ingrese el Dato");
            else
                txtCodigoPostal.requestFocus();
        }
        char c = evt.getKeyChar(); //este codigo se utiliza para solo numero en el tefil y espacio
        if (!((Character.isDigit(c))||(c==KeyEvent.VK_ENTER)||(c==KeyEvent.VK_BACK_SPACE))) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo deben digitarse Numeros");
        }
    }//GEN-LAST:event_txtCodigoPostalKeyReleased

    private void txtProvinciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProvinciaKeyReleased
        txtProvincia.setText (txtProvincia.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtProvinciaKeyReleased

    private void txtLocalidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalidadKeyTyped
        Character c;
        c = evt.getKeyChar();
        if(!Character.isLetter(c) && c != com.sun.glass.events.KeyEvent.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_txtLocalidadKeyTyped

    private void txtLocalidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalidadKeyReleased
        txtLocalidad.setText (txtLocalidad.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocalidadKeyReleased

    private void txtLocalidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalidadKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocalidadKeyPressed

    private void checkProvinciaPapeleraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkProvinciaPapeleraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkProvinciaPapeleraActionPerformed

    private void checkLocalidadPapeleraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkLocalidadPapeleraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkLocalidadPapeleraActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        /* this.setVisible(false);
        MostrarArticulosPapelera();
        Papelera.setVisible(true);*/
    }//GEN-LAST:event_jButton6ActionPerformed


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
            java.util.logging.Logger.getLogger(Registrar_Localidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrar_Localidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrar_Localidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrar_Localidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Registrar_Localidades dialog = new Registrar_Localidades(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_guardara;
    private javax.swing.JButton btn_nuevoa;
    private javax.swing.JRadioButton checkCodigoPostal;
    private javax.swing.JRadioButton checkCodigoPostalPapelera;
    private javax.swing.JRadioButton checkLocalidad;
    private javax.swing.JRadioButton checkLocalidadPapelera;
    private javax.swing.JRadioButton checkProvincia;
    private javax.swing.JRadioButton checkProvinciaPapelera;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JMenuItem menueliminar;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JTable tablaLocalidad;
    private javax.swing.JTable tablaPapelera;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarPapelera;
    public static javax.swing.JTextField txtCodigoLocalidad;
    public static javax.swing.JTextField txtCodigoPostal;
    public static javax.swing.JTextField txtLocalidad;
    public static javax.swing.JTextField txtProvincia;
    // End of variables declaration//GEN-END:variables

    private void nuevoc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
