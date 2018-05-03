/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * registrarse.java
 *
 * Created on 13/05/2010, 11:51:03 AM
 */

package Vistas;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.*;
public class registrarse extends javax.swing.JFrame {

     public registrarse() {
        initComponents();
         setLocationRelativeTo(null);
         
         bloquear();
    }
     
     
     public  void bloquear(){
     
       
        clave.setEnabled(false);
        clave1.setEnabled(false);
        nombre.setEnabled(false);
        apellido.setEnabled(false);
        direccion.setEnabled(false);
        telefono.setEnabled(false);
        cbo_estado.setEnabled(false);
        cbo_acceso.setEnabled(false);
        btn_guardar.setEnabled(false);
        btn_inactivar.setEnabled(false);
     
     }
      public  void desbloquear(){
     
       
        clave.setEnabled(true);
        clave1.setEnabled(true);
        nombre.setEnabled(true);
        apellido.setEnabled(true);
        direccion.setEnabled(true);
        telefono.setEnabled(true);
        cbo_estado.setEnabled(true);
        cbo_acceso.setEnabled(true);
        btn_guardar.setEnabled(true);
        
     
     }
     
     
     
     


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDeslizante1 = new org.edisoncor.gui.varios.PanelDeslizante();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        panelImage3 = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        clave = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        clave1 = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        apellido = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        direccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbo_acceso = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        cbo_estado = new javax.swing.JComboBox();
        btn_guardar = new org.edisoncor.gui.button.ButtonSeven();
        btn_nuevo = new org.edisoncor.gui.button.ButtonSeven();
        jLabel1 = new javax.swing.JLabel();
        btn_inactivar = new org.edisoncor.gui.button.ButtonSeven();
        btn_buscar = new org.edisoncor.gui.button.ButtonSeven();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(" FORMULARIO PARA REGISTRARSE");
        setMinimumSize(new java.awt.Dimension(700, 400));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menumar.png"))); // NOI18N
        panelImage2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/linea.png"))); // NOI18N

        javax.swing.GroupLayout panelImage3Layout = new javax.swing.GroupLayout(panelImage3);
        panelImage3.setLayout(panelImage3Layout);
        panelImage3Layout.setHorizontalGroup(
            panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        panelImage3Layout.setVerticalGroup(
            panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        panelImage2.add(panelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 10, 50));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel2.setText("USUARIO");

        usuario.setHorizontalAlignment(javax.swing.JTextField.LEFT);
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

        jLabel3.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel3.setText("CLAVE");

        clave.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        clave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claveActionPerformed(evt);
            }
        });
        clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel5.setText("REPETIR CLAVE");

        clave1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        clave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clave1ActionPerformed(evt);
            }
        });
        clave1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                clave1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clave, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clave1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(0, 17, Short.MAX_VALUE))
        );

        panelImage2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 500, 90));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel6.setText("NOMBRE");

        nombre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
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

        jLabel7.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel7.setText("APELLIDO");

        apellido.setHorizontalAlignment(javax.swing.JTextField.LEFT);
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

        jLabel8.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel8.setText("DIRECCION");

        direccion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
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

        jLabel9.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel9.setText("TELEFONO");

        telefono.setHorizontalAlignment(javax.swing.JTextField.LEFT);
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

        jLabel4.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel4.setText("ESTADO");

        jLabel11.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel11.setText("ACCESO");

        cbo_acceso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Administrador", "Facturador" }));
        cbo_acceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_accesoActionPerformed(evt);
            }
        });

        cbo_estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "A", "I" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel9))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(telefono, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addComponent(cbo_acceso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbo_estado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_acceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        panelImage2.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 410, 250));

        btn_guardar.setBackground(new java.awt.Color(51, 51, 255));
        btn_guardar.setText("GUARDAR");
        btn_guardar.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        panelImage2.add(btn_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 90, -1));

        btn_nuevo.setBackground(new java.awt.Color(51, 51, 255));
        btn_nuevo.setText("NUEVO");
        btn_nuevo.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });
        panelImage2.add(btn_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 90, -1));

        jLabel1.setFont(new java.awt.Font("Cambria Math", 0, 40)); // NOI18N
        jLabel1.setText("REGISTRAR USUARIO");
        panelImage2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        btn_inactivar.setBackground(new java.awt.Color(255, 51, 51));
        btn_inactivar.setText("INACTIVAR");
        btn_inactivar.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_inactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inactivarActionPerformed(evt);
            }
        });
        panelImage2.add(btn_inactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 280, 90, -1));

        btn_buscar.setBackground(new java.awt.Color(51, 51, 255));
        btn_buscar.setText("BUSCAR");
        btn_buscar.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });
        panelImage2.add(btn_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, 90, -1));

        getContentPane().add(panelImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        usuario.setText("");
        clave.setText("");
        clave1.setText("");
        nombre.setText("");
        apellido.setText("");
        direccion.setText("");
        telefono.setText("");
//        codemple.setText("");
        cbo_estado.setSelectedIndex(0);
        cbo_acceso.setSelectedIndex(0);
        usuario.requestFocus();
        desbloquear();
        



// TODO add your handling code here:
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        if (cbo_estado.getSelectedItem().equals("Seleccione")) {
            JOptionPane.showMessageDialog(null, "Escoja el Estado","Advertencia",JOptionPane.WARNING_MESSAGE);
           cbo_estado.requestFocus();
        }else {
             if (cbo_acceso.getSelectedItem().equals("Seleccione")) {
                JOptionPane.showMessageDialog(null, "Seleccione el Tipo de Acceso","Advertencia",JOptionPane.WARNING_MESSAGE);
                cbo_acceso.requestFocus();
            }else
        //--------------------
        if (usuario.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta el nombre del Usuario","Advertencia",JOptionPane.WARNING_MESSAGE);
            usuario.requestFocus();
        }else {
            if (clave.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan digitar la Clave","Advertencia",JOptionPane.WARNING_MESSAGE);
                clave.requestFocus();
            }else {
                if (clave1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "las claves no coinciden","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else
                if (nombre.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Falta el nombre del usuario","Advertencia",JOptionPane.WARNING_MESSAGE);
                }
                
                if(clave.getText().equals(clave1.getText())){
                    try{
                        String bd = "ventas";
                        String login = "root";
                        String password = "";
                        String url = "jdbc:mysql://localhost/"+bd;
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql
                        Statement consulta=conn.createStatement();
                        //Statement consulta1=conn.createStatement();
                        /* consulta1.executeUpdate("select * from empleado where cod_empleado='"+codemple.getText()+"' and nombres='"+nombre.getText()+"'");
                        int d=consulta1.getMaxRows();
                        if(d==0){
                            JOptionPane.showMessageDialog(null,"Usted no Es Empleado de la Empresa");
                        }else{*/
                            consulta.executeUpdate("insert into usuarios(clave,usuario,nombre,apellido,direccion,telefono,estado,acceso) values('"+clave.getText()+"','"+usuario.getText()+"','"+nombre.getText()+"','"+apellido.getText()+"','"+direccion.getText()+"','"+telefono.getText()+"','"+cbo_estado.getSelectedItem()+"','"+cbo_acceso.getSelectedItem()+"')");
                            JOptionPane.showMessageDialog(null,"Los datos han sido Gurdados Correctamente") ;// esto aparece cuando se ha guardado correctamente
                            usuario.requestFocus();
                            usuario.setText("");
                            clave.setText("");
                            clave1.setText("");
                            nombre.setText("");
                            apellido.setText("");
                            direccion.setText("");
                            telefono.setText("");
                            //codemple.setText("");
                             cbo_estado.setSelectedIndex(0);
                             cbo_acceso.setSelectedIndex(0);
                           
                 
                            /*}*/
                    } catch(SQLException e){
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null,"este nombre ya esta en uso") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
                        usuario.setText("");
                        usuario.requestFocus();

                    } catch(ClassNotFoundException e){
                        JOptionPane.showMessageDialog(null,"Error en la Base de Datos") ; // esto aparece cuando hay problemas con la conexión con mysql

                    }

                }
                else{
                    JOptionPane.showMessageDialog(null,"las contraseñas no coincide","Error",JOptionPane.ERROR_MESSAGE) ;
                }}}
        }
                // TODO add your handling code here:
    }//GEN-LAST:event_btn_guardarActionPerformed

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
        direccion.setText (direccion.getText().toUpperCase());         // TODO add your handling code here:
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
        apellido.setText (apellido.getText().toUpperCase());         // TODO add your handling code here:
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
        nombre.setText (nombre.getText().toUpperCase());         // TODO add your handling code here:
    }//GEN-LAST:event_nombreKeyReleased

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed

    }//GEN-LAST:event_nombreActionPerformed

    private void clave1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clave1KeyTyped
        
    }//GEN-LAST:event_clave1KeyTyped

    private void clave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clave1ActionPerformed

    }//GEN-LAST:event_clave1ActionPerformed

    private void claveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_claveKeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(clave.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Ingrese el Dato");
            else
            clave1.requestFocus();
        }
    }//GEN-LAST:event_claveKeyTyped

    private void claveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claveActionPerformed

    }//GEN-LAST:event_claveActionPerformed

    private void usuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioKeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(usuario.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Ingrese el Dato");
            else
            clave.requestFocus();
        }
    }//GEN-LAST:event_usuarioKeyTyped

    private void usuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioKeyReleased
        //        usuario.setText(usuario.getText().toUpperCase());

        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioKeyReleased

    private void usuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioKeyPressed

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed

    }//GEN-LAST:event_usuarioActionPerformed

    private void cbo_accesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_accesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_accesoActionPerformed

    private void btn_inactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inactivarActionPerformed
btn_buscar.setEnabled(true);

        try{
            String bd = "ventas";
            String login = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/" + bd;
            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
            Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            consulta.executeUpdate("UPDATE usuarios SET estado='I' where clave='"+clave.getText()+"'");
            JOptionPane.showMessageDialog(null,"EMPLEADO INACTIVO");
            btn_inactivar.setEnabled(false);
            
        }

        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL");
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"No hay conexion con Mysql");



        }
















// TODO add your handling code here:
    }//GEN-LAST:event_btn_inactivarActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed

       btn_inactivar.setEnabled(true);
        btn_buscar.setEnabled(false);
        int num=0;
        try{
            String bd = "ventas";
            String login = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/"+bd;
            Class.forName("com.mysql.jdbc.Driver");
            //Connection conexion=DriverManager.getConnection(BD,"monteria","monteria1");
            Connection conn=(Connection) DriverManager.getConnection(url,login,password);//ojo verificar (Connection)

            Statement consulta=conn.createStatement();
            ResultSet res= consulta.executeQuery("select * from usuarios where usuario= '"+usuario.getText()+"'");

            while(res.next()){
                clave.setText(res.getString(1));
                clave1.setText(res.getString(1));
                nombre.setText(res.getString(2));
                apellido.setText(res.getString(3));
                direccion.setText(res.getString(4));
                telefono.setText(res.getString(5));
                cbo_estado.setSelectedItem(res.getString(6));
                cbo_estado.setSelectedItem(res.getString(7));
                
                
                num=1;
                break;
            }
            if (num==0) {
                JOptionPane.showMessageDialog(null,"no se encuentra") ;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al Buscar") ;
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error en la Base de Datos") ; }


        
        
        
        
        
    }//GEN-LAST:event_btn_buscarActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registrarse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellido;
    private org.edisoncor.gui.button.ButtonSeven btn_buscar;
    private org.edisoncor.gui.button.ButtonSeven btn_guardar;
    private org.edisoncor.gui.button.ButtonSeven btn_inactivar;
    private org.edisoncor.gui.button.ButtonSeven btn_nuevo;
    private javax.swing.JComboBox cbo_acceso;
    private javax.swing.JComboBox cbo_estado;
    private javax.swing.JPasswordField clave;
    private javax.swing.JPasswordField clave1;
    private javax.swing.JTextField direccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nombre;
    private org.edisoncor.gui.varios.PanelDeslizante panelDeslizante1;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    private org.edisoncor.gui.panel.PanelImage panelImage3;
    private javax.swing.JTextField telefono;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables

}
