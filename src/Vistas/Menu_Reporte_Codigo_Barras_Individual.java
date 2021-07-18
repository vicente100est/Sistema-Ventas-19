
package Vistas;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class Menu_Reporte_Codigo_Barras_Individual extends javax.swing.JFrame {

    public Menu_Reporte_Codigo_Barras_Individual() {
        initComponents();

        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jButton88 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton44 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton1212 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jButton1616 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Reporte de articulos");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoMenumar22.png"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(0, 204, 204));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarra.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        panelImage1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 210, 210));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 40)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("POR ARTICULO INDIVIDUAL");
        panelImage1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1050, 70));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 33)); // NOI18N
        jLabel10.setText("Tanda de 1");
        panelImage1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, 160, 40));

        jButton22.setBackground(new java.awt.Color(0, 204, 204));
        jButton22.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarra.png"))); // NOI18N
        jButton22.setBorder(null);
        jButton22.setBorderPainted(false);
        jButton22.setContentAreaFilled(false);
        jButton22.setFocusPainted(false);
        jButton22.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton22.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton22.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        panelImage1.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 210, 210));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 33)); // NOI18N
        jLabel11.setText("Tanda de 2");
        panelImage1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 390, 160, 40));

        jButton88.setBackground(new java.awt.Color(0, 204, 204));
        jButton88.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarra.png"))); // NOI18N
        jButton88.setBorder(null);
        jButton88.setBorderPainted(false);
        jButton88.setContentAreaFilled(false);
        jButton88.setFocusPainted(false);
        jButton88.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton88.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton88.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton88.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton88ActionPerformed(evt);
            }
        });
        panelImage1.add(jButton88, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 430, 210, 210));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 33)); // NOI18N
        jLabel12.setText("Tanda de 8");
        panelImage1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 630, 160, 40));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 33)); // NOI18N
        jLabel13.setText("Tanda de 4");
        panelImage1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 390, 160, 40));

        jButton44.setBackground(new java.awt.Color(0, 204, 204));
        jButton44.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarra.png"))); // NOI18N
        jButton44.setBorder(null);
        jButton44.setBorderPainted(false);
        jButton44.setContentAreaFilled(false);
        jButton44.setFocusPainted(false);
        jButton44.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton44.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton44.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });
        panelImage1.add(jButton44, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 210, 210));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 40)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("REPORTE DE CODIGO DE BARRAS ");
        panelImage1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1050, 70));

        jButton1212.setBackground(new java.awt.Color(0, 204, 204));
        jButton1212.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton1212.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarra.png"))); // NOI18N
        jButton1212.setBorder(null);
        jButton1212.setBorderPainted(false);
        jButton1212.setContentAreaFilled(false);
        jButton1212.setFocusPainted(false);
        jButton1212.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton1212.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton1212.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton1212.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1212ActionPerformed(evt);
            }
        });
        panelImage1.add(jButton1212, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 430, 210, 210));

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 33)); // NOI18N
        jLabel14.setText("Tanda de 12");
        panelImage1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 630, 170, 40));

        jButton1616.setBackground(new java.awt.Color(0, 204, 204));
        jButton1616.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton1616.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarra.png"))); // NOI18N
        jButton1616.setBorder(null);
        jButton1616.setBorderPainted(false);
        jButton1616.setContentAreaFilled(false);
        jButton1616.setFocusPainted(false);
        jButton1616.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton1616.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton1616.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        jButton1616.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1616ActionPerformed(evt);
            }
        });
        panelImage1.add(jButton1616, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 430, 210, 210));

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 33)); // NOI18N
        jLabel15.setText("Tanda de 16");
        panelImage1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 630, 170, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        enviar_producto form= new enviar_producto ();
        form.setVisible(true);
        form.toFront();
        enviar_producto.txt_recibe.setText("24");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);  
    }//GEN-LAST:event_formWindowClosing

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        enviar_producto form= new enviar_producto ();
        form.setVisible(true);
        form.toFront();
        enviar_producto.txt_recibe.setText("25");
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton88ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton88ActionPerformed
        enviar_producto form= new enviar_producto ();
        form.setVisible(true);
        form.toFront();
        enviar_producto.txt_recibe.setText("27");
    }//GEN-LAST:event_jButton88ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        enviar_producto form= new enviar_producto ();
        form.setVisible(true);
        form.toFront();
        enviar_producto.txt_recibe.setText("26");
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton1212ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1212ActionPerformed
        enviar_producto form= new enviar_producto ();
        form.setVisible(true);
        form.toFront();
        enviar_producto.txt_recibe.setText("28");
    }//GEN-LAST:event_jButton1212ActionPerformed

    private void jButton1616ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1616ActionPerformed
        enviar_producto form= new enviar_producto ();
        form.setVisible(true);
        form.toFront();
        enviar_producto.txt_recibe.setText("29");
    }//GEN-LAST:event_jButton1616ActionPerformed

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
            java.util.logging.Logger.getLogger(Menu_Reporte_Codigo_Barras_Individual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Reporte_Codigo_Barras_Individual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Reporte_Codigo_Barras_Individual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Reporte_Codigo_Barras_Individual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new Menu_Reporte_Codigo_Barras_Individual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1212;
    private javax.swing.JButton jButton1616;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton88;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    // End of variables declaration//GEN-END:variables
}
