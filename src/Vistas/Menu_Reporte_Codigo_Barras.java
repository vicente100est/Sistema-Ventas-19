
package Vistas;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class Menu_Reporte_Codigo_Barras extends javax.swing.JFrame {

    public Menu_Reporte_Codigo_Barras() {
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
        btnTodos = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnSeleccionados = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

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
        panelImage1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 210, 210));

        btnTodos.setBackground(new java.awt.Color(0, 204, 204));
        btnTodos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnTodos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarra.png"))); // NOI18N
        btnTodos.setBorder(null);
        btnTodos.setBorderPainted(false);
        btnTodos.setContentAreaFilled(false);
        btnTodos.setFocusPainted(false);
        btnTodos.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        btnTodos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        btnTodos.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        btnTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodosActionPerformed(evt);
            }
        });
        panelImage1.add(btnTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 240, 220, 210));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 40)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("REPORTE DE CODIGO DE BARRAS DE ARTICULOS");
        panelImage1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1050, 50));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 33)); // NOI18N
        jLabel8.setText("Todos los articulos");
        panelImage1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 440, 260, 50));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 33)); // NOI18N
        jLabel9.setText("Articulos seleccionados");
        panelImage1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 440, 340, 50));

        btnSeleccionados.setBackground(new java.awt.Color(0, 204, 204));
        btnSeleccionados.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnSeleccionados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarra.png"))); // NOI18N
        btnSeleccionados.setBorder(null);
        btnSeleccionados.setBorderPainted(false);
        btnSeleccionados.setContentAreaFilled(false);
        btnSeleccionados.setFocusPainted(false);
        btnSeleccionados.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        btnSeleccionados.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        btnSeleccionados.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigoBarraHover.png"))); // NOI18N
        btnSeleccionados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionadosActionPerformed(evt);
            }
        });
        panelImage1.add(btnSeleccionados, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 210, 210));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 33)); // NOI18N
        jLabel10.setText("Por articulo individual");
        panelImage1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 380, 50));

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
        Menu_Reporte_Codigo_Barras_Individual articulo = new Menu_Reporte_Codigo_Barras_Individual();
        articulo.setVisible(true); 
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodosActionPerformed
        Menu_Reporte_Codigo_Barras_Todos articulo = new Menu_Reporte_Codigo_Barras_Todos();
        articulo.setVisible(true); 
        this.dispose();
    }//GEN-LAST:event_btnTodosActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);  
    }//GEN-LAST:event_formWindowClosing

    private void btnSeleccionadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionadosActionPerformed
        Menu_Reporte_Codigo_Barras_Seleccionados articulo = new Menu_Reporte_Codigo_Barras_Seleccionados();
        articulo.setVisible(true); 
        this.dispose();
    }//GEN-LAST:event_btnSeleccionadosActionPerformed

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
            java.util.logging.Logger.getLogger(Menu_Reporte_Codigo_Barras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Reporte_Codigo_Barras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Reporte_Codigo_Barras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Reporte_Codigo_Barras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Reporte_Codigo_Barras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeleccionados;
    private javax.swing.JButton btnTodos;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    // End of variables declaration//GEN-END:variables
}
