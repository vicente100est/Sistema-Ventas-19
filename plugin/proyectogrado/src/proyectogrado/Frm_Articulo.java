/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectogrado;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Frm_Articulo extends javax.swing.JDialog {
Icon guardar;
Icon alerta;
Icon error;
    /**
     * Creates new form Frm_Articulo
     */
    public Frm_Articulo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         setLocationRelativeTo(null);
        setTitle("Ingreso de Aerticulo");
        guardar = new ImageIcon("proyectogrado/src/proyectogrado/img/guardar.png");
        alerta = new ImageIcon("src/manuelromero/imagenes/alerta.png");
         alerta = new ImageIcon("src/manuelromero/imagenes/eliminar.png");
       
    }

    public void nuevo(){
        
        tx1.setText("");
        tx2.setText("");
        tx3.setText("");
        tx4.setText("");
        tx5.setText("");
        
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tx1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tx2 = new javax.swing.JTextField();
        tx5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tx3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tx4 = new javax.swing.JTextField();
        panelImage3 = new org.edisoncor.gui.panel.PanelImage();
        panelImage4 = new org.edisoncor.gui.panel.PanelImage();
        btn_guardara1 = new org.edisoncor.gui.button.ButtonSeven();
        btn_buscar_a1 = new org.edisoncor.gui.button.ButtonSeven();
        btn_editara1 = new org.edisoncor.gui.button.ButtonSeven();
        btn_eliminara1 = new org.edisoncor.gui.button.ButtonSeven();
        btn_nuevoa2 = new org.edisoncor.gui.button.ButtonSeven();
        panelImage5 = new org.edisoncor.gui.panel.PanelImage();
        panelImage6 = new org.edisoncor.gui.panel.PanelImage();
        panelImage7 = new org.edisoncor.gui.panel.PanelImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fn.png"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/separador.png"))); // NOI18N
        panelImage2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelImage1.add(panelImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 570, 60));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setText("INGRESO DE ARTICULOS");
        panelImage1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tx1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx1KeyTyped(evt);
            }
        });
        jPanel1.add(tx1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 270, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Codigo");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 94, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Cantidad");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 63, -1));

        tx2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx2KeyTyped(evt);
            }
        });
        jPanel1.add(tx2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 110, -1));

        tx5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tx5ActionPerformed(evt);
            }
        });
        tx5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tx5KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx5KeyTyped(evt);
            }
        });
        jPanel1.add(tx5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 180, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Marca");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 45, -1));

        tx3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tx3ActionPerformed(evt);
            }
        });
        tx3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tx3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx3KeyTyped(evt);
            }
        });
        jPanel1.add(tx3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 230, 20));

        jLabel8.setBackground(new java.awt.Color(239, 255, 239));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Descripcion");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Valor");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 80, -1));

        tx4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx4KeyTyped(evt);
            }
        });
        jPanel1.add(tx4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 190, -1));

        panelImage1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 380, 190));

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

        panelImage1.add(panelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 10, 50));

        panelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/A1.png"))); // NOI18N

        javax.swing.GroupLayout panelImage4Layout = new javax.swing.GroupLayout(panelImage4);
        panelImage4.setLayout(panelImage4Layout);
        panelImage4Layout.setHorizontalGroup(
            panelImage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panelImage4Layout.setVerticalGroup(
            panelImage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        panelImage1.add(panelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 100, 80));

        btn_guardara1.setBackground(new java.awt.Color(51, 255, 0));
        btn_guardara1.setText("Guardar");
        btn_guardara1.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_guardara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardara1ActionPerformed(evt);
            }
        });
        panelImage1.add(btn_guardara1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 82, -1));

        btn_buscar_a1.setBackground(new java.awt.Color(51, 255, 0));
        btn_buscar_a1.setText("Buscar");
        btn_buscar_a1.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_buscar_a1.setColorDeSombra(java.awt.SystemColor.control);
        btn_buscar_a1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_a1ActionPerformed(evt);
            }
        });
        panelImage1.add(btn_buscar_a1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 82, -1));

        btn_editara1.setBackground(new java.awt.Color(255, 255, 51));
        btn_editara1.setText("Editar");
        btn_editara1.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_editara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editara1ActionPerformed(evt);
            }
        });
        panelImage1.add(btn_editara1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, 82, -1));

        btn_eliminara1.setBackground(new java.awt.Color(255, 51, 51));
        btn_eliminara1.setText("Eliminar");
        btn_eliminara1.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_eliminara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminara1ActionPerformed(evt);
            }
        });
        panelImage1.add(btn_eliminara1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 370, 82, -1));

        btn_nuevoa2.setBackground(new java.awt.Color(51, 255, 0));
        btn_nuevoa2.setText("Nuevo");
        btn_nuevoa2.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_nuevoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoa2ActionPerformed(evt);
            }
        });
        panelImage1.add(btn_nuevoa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 82, -1));

        panelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pies.jpg"))); // NOI18N

        javax.swing.GroupLayout panelImage5Layout = new javax.swing.GroupLayout(panelImage5);
        panelImage5.setLayout(panelImage5Layout);
        panelImage5Layout.setHorizontalGroup(
            panelImage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        panelImage5Layout.setVerticalGroup(
            panelImage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        panelImage1.add(panelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 650, 80));

        panelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ras.png"))); // NOI18N
        panelImage6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelImage1.add(panelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 290, 540, 80));

        panelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mouse.png"))); // NOI18N

        javax.swing.GroupLayout panelImage7Layout = new javax.swing.GroupLayout(panelImage7);
        panelImage7.setLayout(panelImage7Layout);
        panelImage7Layout.setHorizontalGroup(
            panelImage7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        panelImage7Layout.setVerticalGroup(
            panelImage7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );

        panelImage1.add(panelImage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, 170, 190));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tx1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx1KeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(tx1.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Ingrese el Dato");
            else
            tx2.requestFocus();
        }
        char c = evt.getKeyChar(); //este codigo se utiliza para solo numero en el tefil y espacio
        if (!((Character.isDigit(c))||(c==KeyEvent.VK_ENTER)||(c==KeyEvent.VK_BACK_SPACE))) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo deben digitarse Numeros");
        }
    }//GEN-LAST:event_tx1KeyTyped

    private void tx2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx2KeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(tx2.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Ingrese el Dato");
            else
            tx3.requestFocus();
        }
        char c = evt.getKeyChar(); //este codigo se utiliza para solo numero en el tefil y espacio
        if (!((Character.isDigit(c))||(c==KeyEvent.VK_ENTER)||(c==KeyEvent.VK_BACK_SPACE))) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo deben digitarse Numeros");
        }// TODO add your handling code here:
    }//GEN-LAST:event_tx2KeyTyped

    private void tx5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx5KeyReleased
        tx5.setText (tx5.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_tx5KeyReleased

    private void tx5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx5KeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(tx5.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Ingrese el Dato");
            else
            tx4.requestFocus();
        }                 // TODO add your handling code here:
    }//GEN-LAST:event_tx5KeyTyped

    private void tx3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tx3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tx3ActionPerformed

    private void tx3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx3KeyReleased
        tx3.setText (tx3.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_tx3KeyReleased

    private void tx3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx3KeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(tx3.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Ingrese el Dato");
            else
            tx5.requestFocus();
        }              // TODO add your handling code here:
    }//GEN-LAST:event_tx3KeyTyped

    private void tx4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx4KeyTyped
        char c = evt.getKeyChar(); //este codigo se utiliza para solo numero en el tefil y espacio
        if (!((Character.isDigit(c))||(c==KeyEvent.VK_ENTER)||(c==KeyEvent.VK_BACK_SPACE))) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo deben digitarse Numeros");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tx4KeyTyped

    private void tx5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tx5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tx5ActionPerformed

    private void btn_guardara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardara1ActionPerformed

// codigo para guardar
        
        if (tx1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta el Codigo","Datos Guardados",JOptionPane.WARNING_MESSAGE,guardar);
            tx1.requestFocus();
            
        }else {
            if (tx2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan la cantidad de articulo");
                tx2.requestFocus();
            }else {
                if (tx3.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Falta la referencia del articulo");
                }else
                if (tx4.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Falta la marca del articulo");
                }else
                if (tx5.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Falta el valor del articulo");
                }else

                try {
                    String bd = "rodamientos";
                    String login= "manuel";
                    String password = "12345";
                    String url = "jdbc:mysql://localhost/" + bd;
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = (Connection) DriverManager.getConnection(url,login,password);
                    Statement consulta = conn.createStatement();
                    consulta.executeUpdate("insert into articulo (cod_articulo,referencia,cantidad,marca,valor)  values('"+tx1.getText()+"','"+tx3.getText()+"','"+tx2.getText()+"','"+tx5.getText()+"','"+tx4.getText()+"')");
                    JOptionPane.showMessageDialog(null, "Los datos han sido Guardados Correctamente");
                    nuevo();
                } catch (SQLException ex) {
                    Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
          
        
        
        
        
        
    }//GEN-LAST:event_btn_guardara1ActionPerformed

    private void btn_buscar_a1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_a1ActionPerformed
        // codigo de buscar
        int num=0;
        try{
            String bd ="rodamientos";
            String login= "manuel";
            String password = "12345";
            String url = "jdbc:mysql://localhost/"+bd;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=(Connection) DriverManager.getConnection(url,login,password);
            Statement consulta=conn.createStatement();
            ResultSet res= consulta.executeQuery("select * from articulo where cod_articulo= '"+tx1.getText()+"'");

            while(res.next()){
                tx3.setText(res.getString(2));
                tx2.setText(res.getString(3));
                tx5.setText(res.getString(4));
                tx4.setText(res.getString(5));
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

    }//GEN-LAST:event_btn_buscar_a1ActionPerformed

    private void btn_editara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editara1ActionPerformed
        // editar registros
        try{
            String bd ="rodamientos";
            String login= "manuel";
            String password = "12345";
            String url = "jdbc:mysql://localhost/" + bd;
            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
            Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            consulta.executeUpdate("UPDATE articulo SET referencia='"+tx3.getText()+"',cantidad='"+tx2.getText()+"',marca='"+tx5.getText()+"',valor='"+tx4.getText()+"'WHERE cod_articulo='"+tx1.getText()+"'");
            JOptionPane.showMessageDialog(null,"articulo modificado");
            nuevo();

        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL");
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"No hay conexion con Mysql");

        }
    }//GEN-LAST:event_btn_editara1ActionPerformed

    private void btn_eliminara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminara1ActionPerformed
        // eliminar registro
        
        try{
            String bd ="rodamientos";
            String login= "manuel";
            String password = "12345";
            String url = "jdbc:mysql://localhost/" + bd; // esta es la conexion
            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
            Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            consulta.executeUpdate("DELETE FROM articulo WHERE cod_articulo='"+tx1.getText()+"'");
            JOptionPane.showMessageDialog(null,"Articulo Eliminado") ;
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"No hay conexion con Mysql") ;
        }
       nuevo();

        
        
    }//GEN-LAST:event_btn_eliminara1ActionPerformed

    private void btn_nuevoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoa2ActionPerformed

     nuevo();    

// TODO add your handling code here:
    }//GEN-LAST:event_btn_nuevoa2ActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Frm_Articulo dialog = new Frm_Articulo(new javax.swing.JFrame(), true);
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
    private org.edisoncor.gui.button.ButtonSeven btn_buscar_a1;
    private org.edisoncor.gui.button.ButtonSeven btn_editara1;
    private org.edisoncor.gui.button.ButtonSeven btn_eliminara1;
    private org.edisoncor.gui.button.ButtonSeven btn_guardara1;
    private org.edisoncor.gui.button.ButtonSeven btn_nuevoa2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    private org.edisoncor.gui.panel.PanelImage panelImage3;
    private org.edisoncor.gui.panel.PanelImage panelImage4;
    private org.edisoncor.gui.panel.PanelImage panelImage5;
    private org.edisoncor.gui.panel.PanelImage panelImage6;
    private org.edisoncor.gui.panel.PanelImage panelImage7;
    public static javax.swing.JTextField tx1;
    public static javax.swing.JTextField tx2;
    public static javax.swing.JTextField tx3;
    public static javax.swing.JTextField tx4;
    public static javax.swing.JTextField tx5;
    // End of variables declaration//GEN-END:variables
}
