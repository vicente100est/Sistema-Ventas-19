
package Vistas;

import javax.swing.ImageIcon;


public class Modulo_Informacion_Creador extends javax.swing.JDialog {


    public Modulo_Informacion_Creador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //jLabel3.setVisible(false);
        
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

        panelImage5 = new org.edisoncor.gui.panel.PanelImage();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        panelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ras.png"))); // NOI18N
        panelImage5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Acerca del desarrollador");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoMenumar22.png"))); // NOI18N
        panelImage2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel8.setText("SISTEMA CONTABLE ADMINISTRATIVO - VENTAS");
        panelImage2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, -1, 26));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel10.setText("DESARROLLADO POR  UNIVERSAL SOFTWARE S.A.S");
        panelImage2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 580, 26));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel3.setText("WWW.UNIVERSALSOFTWARE.COM.CO");
        panelImage2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 460, 440, 26));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 250)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(93, 116, 163));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/MilogoUnisfot.png"))); // NOI18N
        jLabel7.setText("HS");
        panelImage2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 510, 220));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel4.setText("TÉLEFONO: +57 350 388 27 22");
        panelImage2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 520, 340, 26));

        getContentPane().add(panelImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Modulo_Informacion_Creador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modulo_Informacion_Creador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modulo_Informacion_Creador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modulo_Informacion_Creador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Modulo_Informacion_Creador dialog = new Modulo_Informacion_Creador(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    private org.edisoncor.gui.panel.PanelImage panelImage5;
    // End of variables declaration//GEN-END:variables
}
