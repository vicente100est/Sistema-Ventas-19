
package Vistas;

import static Vistas.Agregar_Deposito.txtCodFactura;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Agregar_Credito_Compra extends javax.swing.JFrame {    
    String codigo,referencia,cantidad,marca,valor;
     
    public Agregar_Credito_Compra() {
        initComponents();
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        txtRecibe.setVisible(false);
        txtCodCaja.setVisible(false);
        //txtTotalCredito.setEditable(false);
        
    }
    
    public static void cargarCredito(){
        try{
            Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql                             
            Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta1.executeQuery("select * from compra_credito where cod_compra='"+txtCodFactura.getText()+"'");    
            
            if(txtTotalCredito.equals("")){
                txtTotalCredito.setText("0.00");  
            } 
            if(txtSaldo.equals("")){
                txtSaldo.setText("0.00");  
            }
            txtAcuenta.setText("0.00");
            /*while(r.next()){
                txtTotalCredito.setText(r.getString(3));
                txtAcuenta.setText(r.getString(4));
                txtSaldo.setText(r.getString(5));           
            }*/
            txtAcuenta.requestFocus();
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ERROR EN LA BASE DE DATOS");
            System.out.println(ex);
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtTotalCredito = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JTextField();
        txtAcuenta = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtRecibe = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtCodFactura = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCodCaja = new javax.swing.JTextField();

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
        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cod factura");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 110, 40));

        txtTotalCredito.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtTotalCredito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalCreditoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalCreditoKeyTyped(evt);
            }
        });
        jPanel1.add(txtTotalCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 280, 40));

        jLabel8.setBackground(new java.awt.Color(239, 255, 239));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Saldo restante");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 242, 140, 40));

        txtSaldo.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtSaldo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaldoActionPerformed(evt);
            }
        });
        txtSaldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSaldoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSaldoKeyTyped(evt);
            }
        });
        jPanel1.add(txtSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 280, 40));

        txtAcuenta.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtAcuenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAcuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAcuentaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAcuentaKeyTyped(evt);
            }
        });
        jPanel1.add(txtAcuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 280, 40));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("A cuenta");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 90, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registro de Pago Credito");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 320, -1));
        jPanel1.add(txtRecibe, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 40, 40));

        jButton1.setBackground(new java.awt.Color(5, 52, 99));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, -1, -1));

        jButton2.setBackground(new java.awt.Color(5, 52, 99));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, -1, -1));

        txtCodFactura.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtCodFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodFacturaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodFacturaKeyTyped(evt);
            }
        });
        jPanel1.add(txtCodFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 280, 40));

        jLabel6.setBackground(new java.awt.Color(239, 255, 239));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 140, 60, 40));
        jPanel1.add(txtCodCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 40, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTotalCreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalCreditoKeyReleased
        double totall=0.00, saldo=0.00, aCuenta=0.00;
        if(txtTotalCredito.getText().equals("")){
            totall=0.0;
        }else{
            
            try{                                
                totall=(double)Integer.parseInt(txtTotalCredito.getText().replace(",", "."));
            }catch (NumberFormatException e){
                totall=Double.parseDouble(txtTotalCredito.getText().replace(",", "."));
            }
        }
        
        if(txtAcuenta.getText().equals("")){
            aCuenta=0.0;
        }else{
            
            try{                                
                aCuenta=(double)Integer.parseInt(txtAcuenta.getText().replace(",", "."));
            }catch (NumberFormatException e){
                aCuenta=Double.parseDouble(txtAcuenta.getText().replace(",", "."));
            }
        }
        
        saldo= totall-aCuenta;
        txtSaldo.setText(String.format("%.2f", saldo).replace(",", "."));
        
    }//GEN-LAST:event_txtTotalCreditoKeyReleased

    private void txtTotalCreditoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalCreditoKeyTyped
        char c = evt.getKeyChar(); //este codigo se utiliza para solo numero en el tefil y espacio
        if (!((Character.isDigit(c))||(c==KeyEvent.VK_ENTER)||(c==KeyEvent.VK_BACK_SPACE))) {
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
        }
    }//GEN-LAST:event_txtTotalCreditoKeyTyped

    private void txtSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoActionPerformed

    private void txtSaldoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoKeyReleased

    }//GEN-LAST:event_txtSaldoKeyReleased

    private void txtSaldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoKeyTyped
        char c = evt.getKeyChar(); //este codigo se utiliza para solo numero en el tefil y espacio
        if (!((Character.isDigit(c))||(c==KeyEvent.VK_ENTER)||(c==KeyEvent.VK_BACK_SPACE))) {
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
        }
    }//GEN-LAST:event_txtSaldoKeyTyped

    private void txtAcuentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAcuentaKeyTyped
        char c = evt.getKeyChar(); //este codigo se utiliza para solo numero en el tefil y espacio
        if (!((Character.isDigit(c))||(c==KeyEvent.VK_ENTER)||(c==KeyEvent.VK_BACK_SPACE))) {
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
        }
    }//GEN-LAST:event_txtAcuentaKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String contribuyente="", codigoCliente="";
        txtTotalCredito.setBackground(new Color(255,255,255));
        txtSaldo.setBackground(new Color(255,255,255));
        
        if (txtTotalCredito.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el total a pagar","Advertencia",JOptionPane.WARNING_MESSAGE);
            txtTotalCredito.setBackground(Color.yellow);
            txtTotalCredito.setText("");
            txtTotalCredito.requestFocus();
        }else{
            try{
                Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta = conn.createStatement();
                ResultSet r= consulta.executeQuery("select cod_compra from compra_credito where cod_compra='"+txtCodFactura.getText()+"'");
                int banderaCredito=0;
                
                while(r.next()){
                    banderaCredito=1;
                }
                int xx=0;
                if (banderaCredito==0){
                    xx=consulta.executeUpdate("insert into compra_credito(cod_compra,total,acuenta,saldo_restante,cod_caja) VALUES('"+txtCodFactura.getText()+"','"+txtTotalCredito.getText().replace(',','.')+"','"+txtAcuenta.getText().replace(',','.')+"','"+txtSaldo.getText().replace(',','.')+"','"+txtCodCaja.getText()+"')");
                }else{
                    xx=consulta.executeUpdate("UPDATE compra_credito SET total='"+txtTotalCredito.getText().replace(',','.')+"',acuenta='"+txtAcuenta.getText().replace(',','.')+"',saldo_restante='"+txtSaldo.getText().replace(',','.')+"', cod_caja='"+txtCodCaja.getText()+"' WHERE cod_compra='"+txtCodFactura.getText()+"'");
                }
                //BORRO LA FORMA DE PAGO EN EL CASO QUE EN LA MISMA FACTURA SELECCIONEN PRIMERO UNA FORMA DE PAGO Y LUGO OTRA(QUEDA REGISTRADA LA ULTIMA)
                if(xx>0){
                    consulta.executeUpdate("DELETE FROM compra_mixta WHERE cod_compra='"+txtCodFactura.getText()+"'");
                    consulta.executeUpdate("DELETE FROM compra_deposito WHERE cod_compra='"+txtCodFactura.getText()+"'");
                    consulta.executeUpdate("DELETE FROM compra_efectivo WHERE cod_compra='"+txtCodFactura.getText()+"'");
                    consulta.executeUpdate("DELETE FROM compra_cheque WHERE cod_compra='"+txtCodFactura.getText()+"'"); 
                }
                
                double totalCred=Double.parseDouble(txtTotalCredito.getText().replace(',','.'));
                /*if(txtRecibe.equals("1")){
                    Factura_Venta.sub.setText(String.format("%.2f",totalCred).replace(",", "."));
                    Factura_Venta.txtDescTotal.setText("0.00");
                    Factura_Venta.total.setText(String.format("%.2f",totalCred).replace(",", "."));
                }else if(txtRecibe.equals("2")){
                    Frm_facturarRemito.sub.setText(String.format("%.2f",totalCred).replace(",", "."));
                    Frm_facturarRemito.txtDescTotal.setText("0.00");
                    Frm_facturarRemito.total.setText(String.format("%.2f",totalCred).replace(",", "."));
                }*/
                
                this.dispose();
            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,codigoCliente);
                System.out.println(ex);
            }
     

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCodFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodFacturaKeyReleased

    }//GEN-LAST:event_txtCodFacturaKeyReleased

    private void txtCodFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodFacturaKeyTyped

    }//GEN-LAST:event_txtCodFacturaKeyTyped

    private void txtAcuentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAcuentaKeyReleased
        double total=0.00, saldo=0.00, aCuenta=0.00;
        if(txtTotalCredito.getText().equals("")){
            total=0.0;
        }else{
            
            try{                                
                total=(double)Integer.parseInt(txtTotalCredito.getText().replace(",", "."));
            }catch (NumberFormatException e){
                total=Double.parseDouble(txtTotalCredito.getText().replace(",", "."));
            }
        }
        
        if(txtAcuenta.getText().equals("")){
            aCuenta=0.0;
        }else{
            
            try{                                
                aCuenta=(double)Integer.parseInt(txtAcuenta.getText().replace(",", "."));
            }catch (NumberFormatException e){
                aCuenta=Double.parseDouble(txtAcuenta.getText().replace(",", "."));
            }
        }
        
        saldo= total-aCuenta;
        txtSaldo.setText(String.format("%.2f", saldo).replace(",", "."));
    }//GEN-LAST:event_txtAcuentaKeyReleased

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
            java.util.logging.Logger.getLogger(Agregar_Credito_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agregar_Credito_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agregar_Credito_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agregar_Credito_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Agregar_Credito_Compra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JTextField txtAcuenta;
    public static javax.swing.JTextField txtCodCaja;
    public static javax.swing.JTextField txtCodFactura;
    public static javax.swing.JTextField txtRecibe;
    public static javax.swing.JTextField txtSaldo;
    public static javax.swing.JTextField txtTotalCredito;
    // End of variables declaration//GEN-END:variables

    private void deshabilitar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
