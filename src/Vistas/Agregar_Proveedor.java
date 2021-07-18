
package Vistas;

import Vistas.Factura_Venta;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Agregar_Proveedor extends javax.swing.JFrame {
    
    String codigo,referencia,cantidad,marca,valor;
    String NOMBRE;
 
    public Agregar_Proveedor() {
        initComponents();
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        setLocationRelativeTo(null);
        cargarContribuyente();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        recibeProveedor.setVisible(false);
    }
    
    public void cargarContribuyente(){
        comboContribuyente.addItem("Seleccionar");
        comboContribuyente.addItem("Consumidor Final");
        comboContribuyente.addItem("Responsable Monotributo");
        comboContribuyente.addItem("Responsable Inscripto");
        comboContribuyente.addItem("Responsable no Inscripto");   
        comboContribuyente.addItem("No Responsable"); 
        comboContribuyente.addItem("Exento");  
    }

    public void habilitara(){
       txtnombre.setText("");    
       txtLocalidad.setText("");
       txtIngresosBrutos.setText("");
       txtIngresosBrutos.requestFocus();
       txtTelefono.setText("");
       txtdireccion.setText("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtIngresosBrutos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtLocalidad = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        recibeProveedor = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        comboContribuyente = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtCuit = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtProvincia = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCbu = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtResponsable = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtPostal = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(93, 116, 163));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIngresosBrutos.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtIngresosBrutos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIngresosBrutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIngresosBrutosActionPerformed(evt);
            }
        });
        txtIngresosBrutos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIngresosBrutosKeyTyped(evt);
            }
        });
        jPanel1.add(txtIngresosBrutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 580, 300, 40));

        jLabel5.setBackground(new java.awt.Color(239, 255, 239));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Responsable");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 130, 120, 40));

        txtnombre.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtnombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 300, 40));

        jLabel8.setBackground(new java.awt.Color(239, 255, 239));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Departamento");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 140, -1));

        txtLocalidad.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
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
        jPanel1.add(txtLocalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 300, 40));

        txtTelefono.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 300, 40));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText(" Teléfono");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 110, 40));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("NIT");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 440, 40, -1));

        txtdireccion.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtdireccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdireccionKeyReleased(evt);
            }
        });
        jPanel1.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 300, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesXs-01.png"))); // NOI18N
        jLabel1.setText("Agregar Proveedor");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));
        jPanel1.add(recibeProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 40, 40));

        jButton1.setBackground(new java.awt.Color(5, 52, 99));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 650, 150, -1));

        jButton2.setBackground(new java.awt.Color(5, 52, 99));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 650, 140, -1));

        comboContribuyente.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        comboContribuyente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboContribuyenteActionPerformed(evt);
            }
        });
        jPanel1.add(comboContribuyente, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 480, 300, 40));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("CBU");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 540, 60, -1));

        txtCuit.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtCuit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCuitKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuitKeyTyped(evt);
            }
        });
        jPanel1.add(txtCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 300, 40));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText(" Dirección");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 100, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Ingresos Brutos");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, 150, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("*");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 20, 30));

        txtProvincia.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
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
        jPanel1.add(txtProvincia, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 300, 40));

        jLabel10.setBackground(new java.awt.Color(239, 255, 239));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Cod Postal");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 100, 40));

        txtCbu.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtCbu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCbuKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCbuKeyTyped(evt);
            }
        });
        jPanel1.add(txtCbu, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 530, 300, 40));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Contribuyente");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, 130, -1));

        txtResponsable.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtResponsable.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtResponsable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtResponsableKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtResponsableKeyTyped(evt);
            }
        });
        jPanel1.add(txtResponsable, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 300, 40));

        jLabel15.setBackground(new java.awt.Color(239, 255, 239));
        jLabel15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Nombre Firma");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 90, 130, 30));

        txtPostal.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtPostal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPostal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPostalActionPerformed(evt);
            }
        });
        txtPostal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPostalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPostalKeyTyped(evt);
            }
        });
        jPanel1.add(txtPostal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 300, 40));

        jLabel16.setBackground(new java.awt.Color(239, 255, 239));
        jLabel16.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Ciudad");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 70, 20));

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jMenuItem1.setText("Cerrar ventana");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIngresosBrutosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngresosBrutosKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
        evt.consume();
    }//GEN-LAST:event_txtIngresosBrutosKeyTyped

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased
        txtnombre.setText (txtnombre.getText().toUpperCase());    
    }//GEN-LAST:event_txtnombreKeyReleased

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped
        Character c;
        c = evt.getKeyChar();
        if(!Character.isLetter(c) && c != com.sun.glass.events.KeyEvent.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_txtnombreKeyTyped

    private void txtLocalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocalidadActionPerformed

    }//GEN-LAST:event_txtLocalidadActionPerformed

    private void txtLocalidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalidadKeyReleased
        txtLocalidad.setText (txtLocalidad.getText().toUpperCase());      
    }//GEN-LAST:event_txtLocalidadKeyReleased

    private void txtLocalidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalidadKeyTyped
        Character c;
        c = evt.getKeyChar();
        if(!Character.isLetter(c) && c != com.sun.glass.events.KeyEvent.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_txtLocalidadKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
        evt.consume();
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtdireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyReleased
        txtdireccion.setText (txtdireccion.getText().toUpperCase());
    }//GEN-LAST:event_txtdireccionKeyReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String contribuyente="", codigoCliente="",domicilio="";
        String cuit=txtCuit.getText();
        txtnombre.setBackground(new Color(255,255,255));
        txtLocalidad.setBackground(new Color(255,255,255));
        txtCuit.setBackground(new Color(255,255,255));
        if (txtnombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta el Nombre del Cliente","Advertencia",JOptionPane.WARNING_MESSAGE);
            txtnombre.setBackground(Color.yellow);
            txtnombre.setText("");
            txtnombre.requestFocus();
        }else{ if ((comboContribuyente.getSelectedItem().toString().equals("Responsable Inscripto") || comboContribuyente.getSelectedItem().toString().equals("Responsable Monotributo")) && txtCuit.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Si el tipo de contribuyente es Responsable Inscripto o Responsable Monotributo debe ingresar un numero de CUIT de 11 digitos","Advertencia",JOptionPane.WARNING_MESSAGE);
                txtCuit.setBackground(Color.yellow);
                txtCuit.setText("");
                txtCuit.requestFocus();
            }else { if ((comboContribuyente.getSelectedItem().toString().equals("Responsable Inscripto") || comboContribuyente.getSelectedItem().toString().equals("Responsable Monotributo")) && txtCuit.getText().length()!=11) {
                JOptionPane.showMessageDialog(null, "El número de CUIT debe tener 11 digitos","Advertencia",JOptionPane.WARNING_MESSAGE);
                txtCuit.setBackground(Color.yellow);
                txtCuit.requestFocus();
            }else {
                 if (!txtCuit.getText().equals("") && txtCuit.getText().length()!=11) {
                    JOptionPane.showMessageDialog(null, "El número de CUIT debe tener 11 digitos","Advertencia",JOptionPane.WARNING_MESSAGE);
                    txtCuit.setBackground(Color.yellow);
                    txtCuit.requestFocus();
                }else {
            try {
                Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta = conn.createStatement();
                             
                if(comboContribuyente.getSelectedItem().toString().equals("Seleccionar")){
                    contribuyente="Consumidor Final";
                }else{
                   contribuyente=comboContribuyente.getSelectedItem().toString();
                }

                consulta.executeUpdate("insert into proveedor (nombre_firma,responsable_firma,direccion_firma,ciudad_firma,cod_postal_firma,provincia_firma,cuit,ingresos_brutos,cbu,telefono_firma,estado,condicion)  values('"+txtnombre.getText()+"','"+txtResponsable.getText()+"','"+txtdireccion.getText()+"','"+txtLocalidad.getText()+"','"+txtPostal.getText()+"','"+txtProvincia.getText()+"','"+txtCuit.getText()+"','"+txtIngresosBrutos.getText()+"','"+txtCbu.getText()+"','"+txtTelefono.getText()+"','ACTIVO','"+contribuyente+"')");
                ResultSet r =consulta.executeQuery("SELECT cod_proveedor,direccion_firma,ciudad_firma FROM proveedor WHERE nombre_firma='"+txtnombre.getText()+"' AND responsable_firma='"+txtResponsable.getText()+"' AND direccion_firma='"+txtdireccion.getText()+"'");
                r.next();
                codigoCliente = r.getString(1);
                domicilio= r.getString(2)+", "+r.getString(3);
            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"El PROVEEDOR DE NOMBRE "+txtnombre.getText()+" "+"YA EXISTE");
                JOptionPane.showMessageDialog(null,codigoCliente);
                System.out.println(ex);
            }
            
            if(recibeProveedor.getText().equals("1")){
               Factura_Compra.comboProveedor.addItem(txtnombre.getText());
                Factura_Compra.comboProveedor.setSelectedItem(txtnombre.getText());
                //Frm_facturap.txtCodigoCliente.setText(txtDni.getText());
                Factura_Compra.txtCodigoProveedor.setText(codigoCliente);
                Factura_Compra.txtTipo.setText(contribuyente);
                Factura_Compra.txtCuit.setText(cuit);
                Factura_Compra.txtDomicilio.setText(domicilio);
                Factura_Compra.comboContribuyente.setSelectedItem(contribuyente);
                Factura_Compra.tipoFactura(); 
                this.setVisible(false); 
            }
                
  
            }
           }
          }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboContribuyenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboContribuyenteActionPerformed

    }//GEN-LAST:event_comboContribuyenteActionPerformed

    private void txtCuitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuitKeyReleased

    }//GEN-LAST:event_txtCuitKeyReleased

    private void txtIngresosBrutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIngresosBrutosActionPerformed

    }//GEN-LAST:event_txtIngresosBrutosActionPerformed

    private void txtProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProvinciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProvinciaActionPerformed

    private void txtProvinciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProvinciaKeyReleased
        txtProvincia.setText (txtProvincia.getText().toUpperCase());
    }//GEN-LAST:event_txtProvinciaKeyReleased

    private void txtProvinciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProvinciaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProvinciaKeyTyped

    private void txtCbuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCbuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCbuKeyReleased

    private void txtCuitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuitKeyTyped
        if(txtCuit.getText().length()>=11){
            evt.consume();
        }
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
        evt.consume();
    }//GEN-LAST:event_txtCuitKeyTyped

    private void txtResponsableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResponsableKeyReleased
        txtResponsable.setText (txtResponsable.getText().toUpperCase()); 
    }//GEN-LAST:event_txtResponsableKeyReleased

    private void txtResponsableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResponsableKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResponsableKeyTyped

    private void txtPostalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPostalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPostalActionPerformed

    private void txtPostalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPostalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPostalKeyReleased

    private void txtPostalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPostalKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
        evt.consume();
    }//GEN-LAST:event_txtPostalKeyTyped

    private void txtCbuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCbuKeyTyped
         char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
        evt.consume();
    }//GEN-LAST:event_txtCbuKeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(Agregar_Proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agregar_Proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agregar_Proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agregar_Proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Agregar_Proveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox comboContribuyente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JTextField recibeProveedor;
    public static javax.swing.JTextField txtCbu;
    public static javax.swing.JTextField txtCuit;
    public static javax.swing.JTextField txtIngresosBrutos;
    public static javax.swing.JTextField txtLocalidad;
    public static javax.swing.JTextField txtPostal;
    public static javax.swing.JTextField txtProvincia;
    public static javax.swing.JTextField txtResponsable;
    public static javax.swing.JTextField txtTelefono;
    public static javax.swing.JTextField txtdireccion;
    public static javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables

    private void deshabilitar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
