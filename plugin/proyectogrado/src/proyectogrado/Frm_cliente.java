
package proyectogrado;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manuel
 */
public class Frm_cliente extends javax.swing.JDialog {

    /**
     * Creates new form Frm_cliente
     */
    public Frm_cliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         setLocationRelativeTo(null);
        
    }
    public void nuevoc(){
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
        jLabel10 = new javax.swing.JLabel();
        tx3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tx1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tx2 = new javax.swing.JTextField();
        tx4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tx5 = new javax.swing.JTextField();
        panelImage3 = new org.edisoncor.gui.panel.PanelImage();
        panelImage4 = new org.edisoncor.gui.panel.PanelImage();
        btn_nuevoa = new org.edisoncor.gui.button.ButtonSeven();
        btn_guardara = new org.edisoncor.gui.button.ButtonSeven();
        btn_buscar_a = new org.edisoncor.gui.button.ButtonSeven();
        btn_editara = new org.edisoncor.gui.button.ButtonSeven();
        btn_eliminara = new org.edisoncor.gui.button.ButtonSeven();
        panelImage5 = new org.edisoncor.gui.panel.PanelImage();
        panelImage6 = new org.edisoncor.gui.panel.PanelImage();
        panelImage7 = new org.edisoncor.gui.panel.PanelImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fn.png"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/separador.png"))); // NOI18N
        panelImage2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelImage1.add(panelImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 600, 60));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setText("INGRESO DE CLIENTE");
        panelImage1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setOpaque(false);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText(" NIT");

        tx3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx3KeyTyped(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(239, 255, 239));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText(" Nombre");

        tx1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tx1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx1KeyTyped(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(239, 255, 239));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Apellidos");

        tx2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tx2ActionPerformed(evt);
            }
        });
        tx2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tx2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx2KeyTyped(evt);
            }
        });

        tx4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx4KeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText(" Telefono");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText(" Direccion");

        tx5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tx5KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(tx3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tx1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tx2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(tx4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(tx5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(tx3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(tx1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(tx2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(tx4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(tx5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        panelImage1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 420, 170));

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

        panelImage1.add(panelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 10, 50));

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

        panelImage1.add(panelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 110, 90));

        btn_nuevoa.setBackground(new java.awt.Color(102, 255, 0));
        btn_nuevoa.setText("Nuevo");
        btn_nuevoa.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_nuevoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoaActionPerformed(evt);
            }
        });
        panelImage1.add(btn_nuevoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 82, -1));

        btn_guardara.setBackground(new java.awt.Color(102, 255, 0));
        btn_guardara.setText("Guardar");
        btn_guardara.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_guardara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardaraActionPerformed(evt);
            }
        });
        panelImage1.add(btn_guardara, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 82, -1));

        btn_buscar_a.setBackground(new java.awt.Color(102, 255, 0));
        btn_buscar_a.setText("Buscar");
        btn_buscar_a.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_buscar_a.setColorDeSombra(java.awt.SystemColor.control);
        btn_buscar_a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_aActionPerformed(evt);
            }
        });
        panelImage1.add(btn_buscar_a, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 82, -1));

        btn_editara.setBackground(new java.awt.Color(255, 255, 51));
        btn_editara.setText("Editar");
        btn_editara.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_editara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editaraActionPerformed(evt);
            }
        });
        panelImage1.add(btn_editara, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 82, -1));

        btn_eliminara.setBackground(new java.awt.Color(255, 51, 51));
        btn_eliminara.setText("Eliminar");
        btn_eliminara.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_eliminara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminaraActionPerformed(evt);
            }
        });
        panelImage1.add(btn_eliminara, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, 82, -1));

        panelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ras.png"))); // NOI18N
        panelImage5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelImage1.add(panelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 270, 540, 80));

        panelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clientes.png"))); // NOI18N

        javax.swing.GroupLayout panelImage6Layout = new javax.swing.GroupLayout(panelImage6);
        panelImage6.setLayout(panelImage6Layout);
        panelImage6Layout.setHorizontalGroup(
            panelImage6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        panelImage6Layout.setVerticalGroup(
            panelImage6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );

        panelImage1.add(panelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 190, 190));

        panelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pies.jpg"))); // NOI18N

        javax.swing.GroupLayout panelImage7Layout = new javax.swing.GroupLayout(panelImage7);
        panelImage7.setLayout(panelImage7Layout);
        panelImage7Layout.setHorizontalGroup(
            panelImage7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        panelImage7Layout.setVerticalGroup(
            panelImage7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panelImage1.add(panelImage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 690, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nuevoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoaActionPerformed
        
    }//GEN-LAST:event_btn_nuevoaActionPerformed

    private void btn_guardaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardaraActionPerformed
        // CODIGO GUARDAR
        if (tx1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta el nombre del cliente");
            tx1.requestFocus();
        }else {
            if (tx2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan los Apellidos del cliente");
                tx2.requestFocus();
            }else {
                 if (tx3.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Falta la cedula o nit del cliente");
                    }else
                        if (tx4.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Falta el Telefono del cliente");
                        }else
                            if (tx5.getText().equals("")) {
                                JOptionPane.showMessageDialog(null, "Falta la dreccion del cliente");
                            }else
        try {
            String bd = "rodamientos";
            String login = "manuel";
             String password = "12345";
            String url = "jdbc:mysql://localhost/" + bd; // esta es la conexion
            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
            Connection conn = (Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql
            Statement consulta = conn.createStatement(); // crea una variable que se encargue del código de sql
            consulta.executeUpdate("insert into cliente (cod_cliente,nombres,apellidos,dirrecion,telefono)  values('"+tx3.getText()+"','"+tx1.getText()+"','"+tx2.getText()+"','"+tx5.getText()+"','"+tx4.getText()+"')");
            JOptionPane.showMessageDialog(null, "Los datos han sido Guardados Correctamente"); // esto aparece cuando se ha guardado correctamente
      nuevoc();
 }

 catch (SQLException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        // TODO add your handling code here:
       }
                }
       }
       

    }//GEN-LAST:event_btn_guardaraActionPerformed

    private void btn_buscar_aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_aActionPerformed
      
int num=0;
        try{
            String bd ="rodamientos";
           String login = "manuel";
             String password = "12345";
            String url = "jdbc:mysql://localhost/"+bd;
            Class.forName("com.mysql.jdbc.Driver");
            //Connection conexion=DriverManager.getConnection(BD,"monteria","monteria1");
            Connection conn=(Connection) DriverManager.getConnection(url,login,password);//ojo verificar (Connection)

            Statement consulta=conn.createStatement();
            ResultSet res= consulta.executeQuery("select * from cliente where cod_cliente= '"+tx3.getText()+"'");

            while(res.next()){
                tx1.setText(res.getString(2));
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



    

           
    
}                                   

    private void bt6ActionPerformed(java.awt.event.ActionEvent evt) {                                    
try{
            String bd ="rodamientos";
            String login = "manuel";
             String password = "12345";
            String url = "jdbc:mysql://localhost/" + bd;
            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
            Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            consulta.executeUpdate("UPDATE cliente SET nombres='"+tx1.getText()+"',apellidos='"+tx2.getText()+"',telefono='"+tx4.getText()+"',dirrecion='"+tx5.getText()+"'WHERE cod_cliente='"+tx3.getText()+"'");
            JOptionPane.showMessageDialog(null,"cliente modificado");
         tx1.setText("");
tx2.setText("");
tx3.setText("");
tx4.setText("");
tx5.setText("");
}

catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL");
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"No hay conexion con Mysql");



    }
    }//GEN-LAST:event_btn_buscar_aActionPerformed

    private void btn_editaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editaraActionPerformed
        // TODO add your handling code here:
        try{
            String bd ="rodamientos";
            String login = "manuel";
             String password = "12345";
            String url = "jdbc:mysql://localhost/" + bd;
            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
            Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            consulta.executeUpdate("UPDATE cliente SET nombres='"+tx1.getText()+"',apellidos='"+tx2.getText()+"',telefono='"+tx4.getText()+"',dirrecion='"+tx5.getText()+"'WHERE cod_cliente='"+tx3.getText()+"'");
            JOptionPane.showMessageDialog(null,"cliente modificado");
nuevoc();
}

catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL");
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"No hay conexion con Mysql");



    }

        
        
        
        
    }//GEN-LAST:event_btn_editaraActionPerformed

    private void btn_eliminaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminaraActionPerformed
        // TODO add your handling code here:
        try{
            String bd ="rodamientos";
            String login = "manuel";
             String password = "12345";
            String url = "jdbc:mysql://localhost/" + bd; // esta es la conexion
            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
            Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            //// hasta aquí es el mismo código del guardar///////

            //ResultSet res= consulta.executeQuery("select * from programas where nombre = '"+t2.getText()+"'");
            consulta.executeUpdate("DELETE FROM cliente WHERE cod_cliente='"+tx3.getText()+"'");
            JOptionPane.showMessageDialog(null,"Cliente Eliminado") ;
              }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"No hay conexion con Mysql") ;
        }
       nuevoc();
    


    }//GEN-LAST:event_btn_eliminaraActionPerformed

    private void tx3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx3KeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(tx3.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Ingrese el Dato");
            else
            tx4.requestFocus();
        }
        char c = evt.getKeyChar(); //este codigo se utiliza para solo numero en el tefil y espacio
        if (!((Character.isDigit(c))||(c==KeyEvent.VK_ENTER)||(c==KeyEvent.VK_BACK_SPACE))) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo deben digitarse Numeros");
        }// TODO add your handling code here:
    }//GEN-LAST:event_tx3KeyTyped

    private void tx1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx1KeyReleased
        tx1.setText (tx1.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_tx1KeyReleased

    private void tx1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx1KeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(tx1.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Ingrese el Dato");
            else
            tx2.requestFocus();
        }
        char c = evt.getKeyChar();
        if (!((Character.isLetter(c))||(c==KeyEvent.VK_DELETE)||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_SPACE) ))
        {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo deben Digitarse Letras");
        }// TODO add your handling code here:
    }//GEN-LAST:event_tx1KeyTyped

    private void tx2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tx2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tx2ActionPerformed

    private void tx2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx2KeyReleased
        tx2.setText (tx2.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_tx2KeyReleased

    private void tx2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx2KeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(tx2.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Ingrese el Dato");
            else
            tx3.requestFocus();
        }
        char c = evt.getKeyChar();
        if (!((Character.isLetter(c))||(c==KeyEvent.VK_DELETE)||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_SPACE) ))
        {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo deben Digitarse Letras");
        }// TODO add your handling code here:
    }//GEN-LAST:event_tx2KeyTyped

    private void tx4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx4KeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(tx4.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Ingrese el Dato");
            else
            tx5.requestFocus();
        }
        char c = evt.getKeyChar(); //este codigo se utiliza para solo numero en el tefil y espacio
        if (!((Character.isDigit(c))||(c==KeyEvent.VK_ENTER)||(c==KeyEvent.VK_BACK_SPACE))) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo deben digitarse Numeros");
        }
    }//GEN-LAST:event_tx4KeyTyped

    private void tx5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx5KeyReleased
        tx5.setText (tx5.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_tx5KeyReleased

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
            java.util.logging.Logger.getLogger(Frm_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Frm_cliente dialog = new Frm_cliente(new javax.swing.JFrame(), true);
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
    private org.edisoncor.gui.button.ButtonSeven btn_buscar_a;
    private org.edisoncor.gui.button.ButtonSeven btn_editara;
    private org.edisoncor.gui.button.ButtonSeven btn_eliminara;
    private org.edisoncor.gui.button.ButtonSeven btn_guardara;
    private org.edisoncor.gui.button.ButtonSeven btn_nuevoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
