/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vistas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import static Vistas.Frm_cliente.txtcodigo;
import static Vistas.Frm_cliente.txtnombre;
import static Vistas.Frm_cliente.txtapellido;
import static Vistas.Frm_cliente.txttelefono;
import static Vistas.Frm_cliente.txtdireccion;

/**
 *
 * @author Manuel
 */
public class Frm_Provedor extends javax.swing.JDialog {
             int Select;
             String codigo,referencia,cantidad,marca,valor;
             String bd ="ventas";
             String NOMBRE;
             String login = "root";
             String password = "";
             String url = "jdbc:mysql://localhost/"+bd; // esta es la conexion
    /**
     * Creates new form Frm_Provedor
     */
    public Frm_Provedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();
        setLocationRelativeTo(null);
        setLocation(250,150);
        MostrarProveedor();
        desactivar();
    }
    
public void desactivar(){
    
            jPanel1.setVisible(false);
    
            txtnombre.setEditable(false);
            txtcedula.setEditable(false);
            txtcbu.setEditable(false);
            txtdireccion.setEditable(false);
            txtcorreo.setEditable(false);
            txttelefono.setEditable(false);
            txtcuit.setEditable(false);
            txtciudad.setEditable(false);
            
            btn_guardara.setVisible(false);
            btn_cancelara.setVisible(false);
            btn_eliminara.setVisible(false);
}  
public void nuevoo(){
    
            jPanel1.setVisible(true);

            txtnombre.setText("");
            txtcedula.setText("");
            txtcedula.requestFocus();
            txtcbu.setText("");
            txtdireccion.setText("");
            txtcorreo.setText("");
            txttelefono.setText("");
            txtcuit.setText("");
            txtciudad.setText("");
            txtnombre.setEditable(true);
            txtcedula.setEditable(true);
            txtcbu.setEditable(true);
            txtdireccion.setEditable(true);
            txtcorreo.setEditable(true);
            txttelefono.setEditable(true);
            txtcuit.setEditable(true);
            txtciudad.setEditable(true);
            
            btn_guardara.setVisible(true);
            btn_cancelara.setVisible(true);
            btn_eliminara.setVisible(true);
}

public void MostrarProveedor(){

        TableColumn  column = null;
        /*column = tabla.getColumnModel().getColumn(0);
        column.setPreferredWidth(60);
        column = tabla.getColumnModel().getColumn(1);
        column.setPreferredWidth(260);
        column = tabla.getColumnModel().getColumn(2);
        column.setPreferredWidth(100);
        column = tabla.getColumnModel().getColumn(3);
        column.setPreferredWidth(60);
         column = tabla.getColumnModel().getColumn(4);
        column.setPreferredWidth(60);*/
        
         try{
                            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
                            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
                            Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql

                            //Connection conn=(Connection) DriverManager.getConnection(url,usuario,contraseña); // esta es la verificación de la conexión con mysql
                            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql


                            ResultSet r= consulta.executeQuery("select * from proveedor order by cod_proveedor");
                            int i,j;
                            i=0;
                            j=0;
                            //jTable1.getModel().
                            DefaultTableModel modelo=(DefaultTableModel)tablaproveedores.getModel();
                            tablaproveedores.setRowSorter(new TableRowSorter(modelo));
                            //   for(j=0;j<modelo.getRowCount();j++){
                            //     modelo.removeRow(0);
                            /// }
                            modelo.setNumRows(0);
                            while(r.next()){
                                modelo.addRow( new Object [] {null,null,null,null,null,null,null,null});
                                tablaproveedores.setValueAt(r.getString(1),i,0);
                                tablaproveedores.setValueAt(r.getString(2),i,1);
                                tablaproveedores.setValueAt(r.getString(3),i,2);
                                tablaproveedores.setValueAt(r.getString(4),i,3);
                                tablaproveedores.setValueAt(r.getString(5),i,4);
                                tablaproveedores.setValueAt(r.getString(6),i,5);
                                tablaproveedores.setValueAt(r.getString(7),i,6);
                                tablaproveedores.setValueAt(r.getString(8),i,7);
                                //    tabla.setValueAt(r.getString(3),i,2);
                                i++;
                            }
                        } catch(SQLException e){
                            JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
                            //t2.setText("");


                        } catch(ClassNotFoundException e){
                            JOptionPane.showMessageDialog(null,"Error en la Base de Datos") ; // esto aparece cuando hay problemas con la conexión con mysql

                        }

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        menueliminar = new javax.swing.JMenuItem();
        FiltrarResultados = new javax.swing.ButtonGroup();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtcedula = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtcbu = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        txtciudad = new javax.swing.JTextField();
        txtcorreo = new javax.swing.JTextField();
        txtcuit = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        panelImage3 = new org.edisoncor.gui.panel.PanelImage();
        panelImage4 = new org.edisoncor.gui.panel.PanelImage();
        btn_nuevoa = new org.edisoncor.gui.button.ButtonSeven();
        btn_guardara = new org.edisoncor.gui.button.ButtonSeven();
        btn_eliminara = new org.edisoncor.gui.button.ButtonSeven();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaproveedores = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        checkCodigo = new javax.swing.JRadioButton();
        checkReferencia = new javax.swing.JRadioButton();
        checkMarca = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        btn_cancelara = new org.edisoncor.gui.button.ButtonSeven();

        menueliminar.setText("Eliminar");
        menueliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menueliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menueliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INGRESO DE DATOS DEL PROVEDOR");

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoMenumar2.png"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(239, 255, 239));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setText(" Nombre");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 220, 40));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setText("Código");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, 20));

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
        jPanel1.add(txtcedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 220, 40));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setText("Correo");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, 20));

        txtcbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcbuKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcbuKeyTyped(evt);
            }
        });
        jPanel1.add(txtcbu, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 220, 40));

        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdireccionKeyReleased(evt);
            }
        });
        jPanel1.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 220, 40));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setText(" Dirección");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 100, -1));

        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });
        jPanel1.add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 220, 40));

        txtciudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtciudadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtciudadKeyTyped(evt);
            }
        });
        jPanel1.add(txtciudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 220, 40));

        txtcorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcorreoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcorreoKeyTyped(evt);
            }
        });
        jPanel1.add(txtcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 220, 40));

        txtcuit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcuitKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcuitKeyTyped(evt);
            }
        });
        jPanel1.add(txtcuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 220, 40));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setText("CBU");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, -1, 20));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("Ciudad");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, -1, 20));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setText(" Teléfono");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, 20));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setText("CUIT");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 50, 30));

        panelImage1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 460, 480));

        panelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/linea.png"))); // NOI18N

        javax.swing.GroupLayout panelImage3Layout = new javax.swing.GroupLayout(panelImage3);
        panelImage3.setLayout(panelImage3Layout);
        panelImage3Layout.setHorizontalGroup(
            panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        panelImage3Layout.setVerticalGroup(
            panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panelImage1.add(panelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 10, 70));

        panelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/profes1.png"))); // NOI18N

        javax.swing.GroupLayout panelImage4Layout = new javax.swing.GroupLayout(panelImage4);
        panelImage4.setLayout(panelImage4Layout);
        panelImage4Layout.setHorizontalGroup(
            panelImage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        panelImage4Layout.setVerticalGroup(
            panelImage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        panelImage1.add(panelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, 90));

        btn_nuevoa.setBackground(new java.awt.Color(0, 102, 204));
        btn_nuevoa.setText("Nuevo");
        btn_nuevoa.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_nuevoa.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btn_nuevoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoaActionPerformed(evt);
            }
        });
        panelImage1.add(btn_nuevoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 640, 130, 70));

        btn_guardara.setBackground(new java.awt.Color(204, 204, 204));
        btn_guardara.setText("Guardar");
        btn_guardara.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_guardara.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btn_guardara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardaraActionPerformed(evt);
            }
        });
        panelImage1.add(btn_guardara, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 640, 130, 70));

        btn_eliminara.setBackground(new java.awt.Color(204, 204, 204));
        btn_eliminara.setText("Eliminar");
        btn_eliminara.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_eliminara.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btn_eliminara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminaraActionPerformed(evt);
            }
        });
        panelImage1.add(btn_eliminara, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 640, 130, 70));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jLabel1.setText("INGRESO DE PROVEEDORES");
        panelImage1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 410, -1));

        tablaproveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Direccion", "Telefono", "Ciudad", "Correo", "CUIT", "CBU"
            }
        ));
        tablaproveedores.setComponentPopupMenu(jPopupMenu1);
        tablaproveedores.setOpaque(false);
        tablaproveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaproveedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaproveedores);
        if (tablaproveedores.getColumnModel().getColumnCount() > 0) {
            tablaproveedores.getColumnModel().getColumn(1).setResizable(false);
            tablaproveedores.getColumnModel().getColumn(2).setResizable(false);
            tablaproveedores.getColumnModel().getColumn(3).setResizable(false);
            tablaproveedores.getColumnModel().getColumn(4).setResizable(false);
            tablaproveedores.getColumnModel().getColumn(5).setResizable(false);
            tablaproveedores.getColumnModel().getColumn(6).setResizable(false);
        }

        panelImage1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, 870, 570));

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
        panelImage1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 670, 280, 50));

        FiltrarResultados.add(checkCodigo);
        checkCodigo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCodigo.setText("Código");
        checkCodigo.setOpaque(false);
        checkCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCodigoActionPerformed(evt);
            }
        });
        panelImage1.add(checkCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 630, -1, -1));

        FiltrarResultados.add(checkReferencia);
        checkReferencia.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkReferencia.setText("Nombre");
        checkReferencia.setOpaque(false);
        panelImage1.add(checkReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 630, -1, -1));

        FiltrarResultados.add(checkMarca);
        checkMarca.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkMarca.setText("Ciudad");
        checkMarca.setOpaque(false);
        panelImage1.add(checkMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 630, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Buscar por");
        panelImage1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 680, -1, -1));

        btn_cancelara.setBackground(new java.awt.Color(204, 204, 204));
        btn_cancelara.setText("Cancelar");
        btn_cancelara.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_cancelara.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btn_cancelara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelaraActionPerformed(evt);
            }
        });
        panelImage1.add(btn_cancelara, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 640, 130, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 1604, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nuevoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoaActionPerformed
nuevoo();
    }//GEN-LAST:event_btn_nuevoaActionPerformed

    private void btn_guardaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardaraActionPerformed
    
if (txtnombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta el nombre del provedor ");
            txtnombre.requestFocus();
        }else {
            if (txtcedula.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan la cedula o nit del provedor");
                txtcedula.requestFocus();
            }else {
                 if (txtdireccion.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Falta la direccion del provedor");
                    }else
                            if (txttelefono.getText().equals("")) {
                                                       JOptionPane.showMessageDialog(null, "Falta el telefono del provedor");
                                                   }else
                                if (txtciudad.getText().equals("")) {
                                                       JOptionPane.showMessageDialog(null, "Falta la ciudad del provedor");
                                                   }else
                                    if (txtcorreo.getText().equals("")) {
                                                       JOptionPane.showMessageDialog(null, "Falta el correo del provedor");
                                                   }else
                                        if (txtcuit.getText().equals("")) {
                                                       JOptionPane.showMessageDialog(null, "Falta el CUIT del provedor");
                                                   }else
                                            if (txtcbu.getText().equals("")) {
                                                            JOptionPane.showMessageDialog(null, "Falta el CBU del provedor");
                                                        }else
        try {
                    String bd = "ventas";
                    String login = "root";
                    String password = "";
                    String url = "jdbc:mysql://localhost/" + bd;
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Frm_Articulo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Connection conn = (Connection) DriverManager.getConnection(url,login,password);
                    Statement consulta = conn.createStatement();
                    
                    
                    
                    Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
                ResultSet r= consulta1.executeQuery("select cod_proveedor from proveedor");
                int bandera=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
                while(r.next()){
                    //JOptionPane.showMessageDialog(null,"entro a la consulta1");
                    if(txtcedula.getText().equals(r.getString(1))){//si el codigo de la tabla es igual a algun codigo de la base de datos
                        bandera=1;
                        //JOptionPane.showMessageDialog(null,"BANDERA= "+bandera);
                        
                    }
                }
                //JOptionPane.showMessageDialog(null,"salio de la consulta1");
              
                if(bandera==0){//Si el codigo no esta inserto una nuevo registro a la base de datos
                    //JOptionPane.showMessageDialog(null,"esta por hacer un insert");
                    consulta.executeUpdate("insert into proveedor (cod_proveedor,nombre,dirrecion,telefono,ciudad,correo,cuit,cbu)  values('"+txtcedula.getText()+"','"+txtnombre.getText()+"','"+txtdireccion.getText()+"','"+txttelefono.getText()+"','"+txtciudad.getText()+"','"+txtcorreo.getText()+"','"+txtcuit.getText()+"','"+txtcbu.getText()+"')");
                    
                }else{//si el codigo esta,actualizo la informacion del producto
                    //JOptionPane.showMessageDialog(null,"esta por hacerun update");
                     consulta.executeUpdate("UPDATE proveedor SET nombre='"+txtnombre.getText()+"',dirrecion='"+txtdireccion.getText()+"',telefono='"+txttelefono.getText()+"',ciudad='"+txtciudad.getText()+"',correo='"+txtcorreo.getText()+"',cuit='"+txtcuit.getText()+"',cbu='"+txtcbu.getText()+"'WHERE cod_proveedor='"+txtcedula.getText()+"'");               
                }
                JOptionPane.showMessageDialog(null, "Los datos han sido Guardados Correctamente");
                nuevoo();
                MostrarProveedor();
                } 
                 catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,"El PROVEEDOR"+" "+ txtcedula.getText()+" "+"DE NOMBRE"+" "+txtnombre.getText()+" "+"YA EXISTE");
                        System.out.println(ex);
			}
            }
           }
        
    }//GEN-LAST:event_btn_guardaraActionPerformed

    private void btn_eliminaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminaraActionPerformed
try{
            String bd = "ventas";
            String login = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/" + bd; // esta es la conexion
            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
            Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            //// hasta aquí es el mismo código del guardar///////

            //ResultSet res= consulta.executeQuery("select * from programas where nombre = '"+t2.getText()+"'");
            consulta.executeUpdate("DELETE FROM proveedor WHERE cod_proveedor='"+txtcedula.getText()+"'");
            JOptionPane.showMessageDialog(null,"proveedor Eliminado") ;
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"No hay conexion con Mysql") ;
        }
        nuevoo();
        MostrarProveedor();
 




// TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminaraActionPerformed

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

    private void txtcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();// TODO add your handling code here:
    }//GEN-LAST:event_txtcedulaKeyTyped

    private void txtcbuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcbuKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();// TODO add your handling code here:
    }//GEN-LAST:event_txtcbuKeyTyped

    private void txtdireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyReleased
        txtdireccion.setText (txtdireccion.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionKeyReleased

    private void txtcedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcedulaKeyPressed

    private void txtcedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcedulaKeyReleased

    private void txtnombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreKeyPressed

    private void txttelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();
    }//GEN-LAST:event_txttelefonoKeyTyped

    private void txtciudadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtciudadKeyTyped
          Character c;
        c = evt.getKeyChar();
        if(!Character.isLetter(c) && c != com.sun.glass.events.KeyEvent.VK_SPACE){
            evt.consume();
        }// TODO add your handling code here:
    }//GEN-LAST:event_txtciudadKeyTyped

    private void txtcorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorreoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcorreoKeyTyped

    private void txtcuitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcuitKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcuitKeyTyped

    private void menueliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menueliminarActionPerformed
        if(JOptionPane.showConfirmDialog(null,"Desea eliminar el proveedor?","Eliminar Proveedor",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
        try{
            String bd = "ventas";
            String login = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/" + bd; // esta es la conexion
            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
            Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            consulta.executeUpdate("DELETE FROM proveedor WHERE cod_proveedor='"+txtcedula.getText()+"'");
            //JOptionPane.showMessageDialog(null,"Articulo Eliminado") ;
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL","Error",JOptionPane.ERROR_MESSAGE) ;
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"No hay conexion con Mysql","Error",JOptionPane.ERROR_MESSAGE) ;
        }
       nuevoo();
       MostrarProveedor();
      }
    }//GEN-LAST:event_menueliminarActionPerformed

    private void tablaproveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaproveedoresMouseClicked
        nuevoo();
        Select=tablaproveedores.getSelectedRow();
        txtcedula.setText( tablaproveedores.getValueAt(Select,0).toString());
        txtnombre.setText( tablaproveedores.getValueAt(Select,1).toString());
        txtdireccion.setText( tablaproveedores.getValueAt(Select,2).toString());
        txttelefono.setText( tablaproveedores.getValueAt(Select,3).toString());
        txtciudad.setText( tablaproveedores.getValueAt(Select,4).toString());
        txtcorreo.setText( tablaproveedores.getValueAt(Select,5).toString());
        txtcuit.setText( tablaproveedores.getValueAt(Select,6).toString());
        txtcbu.setText( tablaproveedores.getValueAt(Select,7).toString());
    }//GEN-LAST:event_tablaproveedoresMouseClicked

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
    if (checkCodigo.isSelected()) {
        columna = 0;
    } 
    if (checkReferencia.isSelected()) {
         columna = 1;
    }
    if (checkMarca.isSelected()) {
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

    private void checkCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCodigoActionPerformed

    private void txtciudadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtciudadKeyReleased
        txtciudad.setText (txtciudad.getText().toUpperCase());
    }//GEN-LAST:event_txtciudadKeyReleased

    private void txtcorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorreoKeyReleased
        txtcorreo.setText (txtcorreo.getText().toUpperCase());
    }//GEN-LAST:event_txtcorreoKeyReleased

    private void txtcuitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcuitKeyReleased
        txtcuit.setText (txtcuit.getText().toUpperCase());
    }//GEN-LAST:event_txtcuitKeyReleased

    private void txtcbuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcbuKeyReleased
        txtcbu.setText (txtcbu.getText().toUpperCase());
    }//GEN-LAST:event_txtcbuKeyReleased

    private void txttelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyReleased
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(txttelefono.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Ingrese el Dato");
            else
            txttelefono.requestFocus();
        }
        char c = evt.getKeyChar(); //este codigo se utiliza para solo numero en el tefil y espacio
        if (!((Character.isDigit(c))||(c==KeyEvent.VK_ENTER)||(c==KeyEvent.VK_BACK_SPACE))) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo deben digitarse Numeros");
        }// TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoKeyReleased

    private void btn_cancelaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelaraActionPerformed
        desactivar();
    }//GEN-LAST:event_btn_cancelaraActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_Provedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Provedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Provedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Provedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Frm_Provedor dialog = new Frm_Provedor(new javax.swing.JFrame(), true);
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
    private org.edisoncor.gui.button.ButtonSeven btn_cancelara;
    private org.edisoncor.gui.button.ButtonSeven btn_eliminara;
    private org.edisoncor.gui.button.ButtonSeven btn_guardara;
    private org.edisoncor.gui.button.ButtonSeven btn_nuevoa;
    private javax.swing.JRadioButton checkCodigo;
    private javax.swing.JRadioButton checkMarca;
    private javax.swing.JRadioButton checkReferencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem menueliminar;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage3;
    private org.edisoncor.gui.panel.PanelImage panelImage4;
    private javax.swing.JTable tablaproveedores;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtcbu;
    public static javax.swing.JTextField txtcedula;
    public static javax.swing.JTextField txtciudad;
    public static javax.swing.JTextField txtcorreo;
    public static javax.swing.JTextField txtcuit;
    public static javax.swing.JTextField txtdireccion;
    public static javax.swing.JTextField txtnombre;
    public static javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables

    private void nuevoc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
