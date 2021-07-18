
package Vistas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;

public class Configurar_Porcentajes_Articulo extends javax.swing.JFrame {
    
    String codigo,referencia,cantidad,marca,valor;
    String NOMBRE;

    public Configurar_Porcentajes_Articulo() {
        initComponents();
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        mostrarDatos();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public void mostrarDatos(){
        txtRecibe.setVisible(false);
                
        comboIva.addItem("19%");
        comboIva.addItem("0%");
        
        comboIvaCompra.addItem("19%");
        comboIvaCompra.addItem("0%");
        
        try{
            Connection conn =conexion.ObtenerConexion();
            Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet r= consulta1.executeQuery("select * from porcentajes");

            while(r.next()){
                comboIvaCompra.setSelectedItem(r.getString(3)); //porcentaje iva compra por defecto
                comboIva.setSelectedItem(r.getString(4)); //porcentaje iva venta por defecto
                txtGanancia.setText(r.getString(2));
            }
        }catch(Exception e){
            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtGanancia = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        comboIva = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        txtRecibe = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        comboIvaCompra = new javax.swing.JComboBox();

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

        jLabel5.setBackground(new java.awt.Color(239, 255, 239));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Porcentaje de Ganancia");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 260, 40));

        txtGanancia.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtGanancia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGanancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGananciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGananciaKeyTyped(evt);
            }
        });
        jPanel1.add(txtGanancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 170, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoReportesXs.png"))); // NOI18N
        jLabel1.setText("Configurar porcentajes");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 390, -1));

        jButton1.setBackground(new java.awt.Color(5, 52, 99));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 150, -1));

        btnAceptar.setBackground(new java.awt.Color(5, 52, 99));
        btnAceptar.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 140, -1));

        comboIva.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        comboIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIvaActionPerformed(evt);
            }
        });
        jPanel1.add(comboIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 170, 40));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText(" Porcentaje de  IVA compra");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 300, 40));
        jPanel1.add(txtRecibe, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 50, 50));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText(" Porcentaje de  IVA venta");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 280, 40));

        comboIvaCompra.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        comboIvaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIvaCompraActionPerformed(evt);
            }
        });
        jPanel1.add(comboIvaCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 170, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void txtGananciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaKeyReleased
        txtGanancia.setText (txtGanancia.getText().toUpperCase());
    }//GEN-LAST:event_txtGananciaKeyReleased

    private void txtGananciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaKeyTyped
        char []p={'1','2','3','4','5','6','7','8','9','0','.',','};
        int b=0;
        for(int i=0;i<=11;i++){
            if (p[i]==evt.getKeyChar()){
                b=1;
            }
        }
        if(b==0){
            evt.consume();  
            getToolkit().beep(); 
        }
    }//GEN-LAST:event_txtGananciaKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try{
            Connection conn =conexion.ObtenerConexion();
            Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
            consulta1.executeUpdate("UPDATE porcentajes SET porcentaje_ganancia='"+txtGanancia.getText().replace(",",".")+"', porcentaje_iva_compra='"+comboIvaCompra.getSelectedItem().toString()+"', porcentaje_iva_venta='"+comboIva.getSelectedItem().toString()+"' WHERE id='1'");  
        }catch(Exception e){
            
        }
        
        if(txtRecibe.getText().equals("1")){
            Registrar_Articulos.comboIvaCompra.setSelectedItem(comboIvaCompra.getSelectedItem());
            Registrar_Articulos.comboIva.setSelectedItem(comboIva.getSelectedItem());         
            Registrar_Articulos.txtPorcentaje.setText(txtGanancia.getText().replace(",","."));
        }else if(txtRecibe.getText().equals("2")){
                    Agregar_Articulo_Ventas.comboIva.setSelectedItem(comboIva.getSelectedItem());//combo iva venta
                    Agregar_Articulo_Ventas.comboIvaCompra.setSelectedItem(comboIvaCompra.getSelectedItem());//combo iva compra
                    Agregar_Articulo_Ventas.txtPorcentaje.setText(txtGanancia.getText().replace(",","."));
                }else if(txtRecibe.getText().equals("3")){
                        Agregar_Articulo_Compras.comboIva.setSelectedItem(comboIva.getSelectedItem()); //combo iva venta
                        Agregar_Articulo_Compras.comboIvaCompra.setSelectedItem(comboIvaCompra.getSelectedItem());//combo iva compra
                        Agregar_Articulo_Compras.txtPorcentaje.setText(txtGanancia.getText().replace(",","."));
                       }
        
        
                
        this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void comboIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIvaActionPerformed

    }//GEN-LAST:event_comboIvaActionPerformed

    private void comboIvaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIvaCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboIvaCompraActionPerformed

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
            java.util.logging.Logger.getLogger(Configurar_Porcentajes_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configurar_Porcentajes_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configurar_Porcentajes_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configurar_Porcentajes_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Configurar_Porcentajes_Articulo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    public static javax.swing.JComboBox comboIva;
    public static javax.swing.JComboBox comboIvaCompra;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JTextField txtGanancia;
    public static javax.swing.JTextField txtRecibe;
    // End of variables declaration//GEN-END:variables

    private void deshabilitar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
