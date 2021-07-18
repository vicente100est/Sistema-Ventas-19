
package Vistas;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Agregar_TarjetaYEfectivo_Compra extends javax.swing.JFrame {    
    String codigo,referencia,cantidad,marca,valor;
     
    public Agregar_TarjetaYEfectivo_Compra() {
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

            ResultSet r= consulta1.executeQuery("select * from compra_mixta where cod_compra='"+txtCodFactura.getText()+"'");    
            
            if(txtTotal.equals("")){
                txtTotal.setText("0.00");  
            } 
            if(txtSaldo.equals("")){
                txtSaldo.setText("0.00");  
            }
            //txtVuelto.setText("0.00");
            /*while(r.next()){
                txtTotalCredito.setText(r.getString(3));
                txtAcuenta.setText(r.getString(4));
                txtSaldo.setText(r.getString(5));           
            }*/
            //txtVuelto.requestFocus();
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
        txtTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JTextField();
        txtVuelto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtRecibe = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtCodFactura = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTarjeta = new javax.swing.JTextField();
        txtEfectivo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
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
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 110, 40));

        txtTotal.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalKeyTyped(evt);
            }
        });
        jPanel1.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 280, 40));

        jLabel8.setBackground(new java.awt.Color(239, 255, 239));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Vuelto");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 80, 40));

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
        jPanel1.add(txtSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 280, 40));

        txtVuelto.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtVuelto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVuelto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVueltoActionPerformed(evt);
            }
        });
        txtVuelto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVueltoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVueltoKeyTyped(evt);
            }
        });
        jPanel1.add(txtVuelto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 280, 40));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tarjeta");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 80, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registro de Pago Credito");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 320, -1));
        jPanel1.add(txtRecibe, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 40, 30));

        jButton1.setBackground(new java.awt.Color(5, 52, 99));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 440, -1, -1));

        jButton2.setBackground(new java.awt.Color(5, 52, 99));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, -1, -1));

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
        jPanel1.add(txtCodFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 280, 40));

        jLabel6.setBackground(new java.awt.Color(239, 255, 239));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 60, 40));

        txtTarjeta.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtTarjeta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTarjetaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTarjetaKeyTyped(evt);
            }
        });
        jPanel1.add(txtTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 280, 40));

        txtEfectivo.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtEfectivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEfectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEfectivoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEfectivoKeyTyped(evt);
            }
        });
        jPanel1.add(txtEfectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 280, 40));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Efectivo");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 90, 40));

        jLabel10.setBackground(new java.awt.Color(239, 255, 239));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Saldo restante");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 140, 40));
        jPanel1.add(txtCodCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 40, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyReleased
        double totall=0.00, saldo=0.00, aCuenta=0.00;
        if(txtTotal.getText().equals("")){
            totall=0.0;
        }else{
            
            try{                                
                totall=(double)Integer.parseInt(txtTotal.getText().replace(",", "."));
            }catch (NumberFormatException e){
                totall=Double.parseDouble(txtTotal.getText().replace(",", "."));
            }
        }
        
        if(txtVuelto.getText().equals("")){
            aCuenta=0.0;
        }else{
            
            try{                                
                aCuenta=(double)Integer.parseInt(txtVuelto.getText().replace(",", "."));
            }catch (NumberFormatException e){
                aCuenta=Double.parseDouble(txtVuelto.getText().replace(",", "."));
            }
        }
        
        saldo= totall-aCuenta;
        txtSaldo.setText(String.format("%.2f", saldo).replace(",", "."));
        
    }//GEN-LAST:event_txtTotalKeyReleased

    private void txtTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyTyped
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
    }//GEN-LAST:event_txtTotalKeyTyped

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

    private void txtVueltoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVueltoKeyTyped
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
    }//GEN-LAST:event_txtVueltoKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String contribuyente="", codigoCliente="";
        txtTotal.setBackground(new Color(255,255,255));
        txtSaldo.setBackground(new Color(255,255,255));
        
        if (txtTotal.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el total a pagar","Advertencia",JOptionPane.WARNING_MESSAGE);
            txtTotal.setBackground(Color.yellow);
            txtTotal.setText("");
            txtTotal.requestFocus();
        }else{
            try{
                Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta = conn.createStatement();
                ResultSet r= consulta.executeQuery("select cod_compra from compra_mixta where cod_compra='"+txtCodFactura.getText()+"'");
                int banderaCredito=0;
                
                while(r.next()){
                    banderaCredito=1;
                }
                int x=0;
                if (banderaCredito==0){
                    x=consulta.executeUpdate("insert into compra_mixta(cod_compra,total,monto_efectivo,monto_tarjeta,saldo_restante,vuelto,cod_caja) VALUES('"+txtCodFactura.getText()+"','"+txtTotal.getText().replace(',','.')+"','"+txtEfectivo.getText().replace(',','.')+"','"+txtTarjeta.getText().replace(',','.')+"','"+txtSaldo.getText().replace(',','.')+"','"+txtVuelto.getText().replace(',','.')+"','"+txtCodCaja.getText()+"')");
                }else{
                    x=consulta.executeUpdate("UPDATE compra_mixta SET total='"+txtTotal.getText().replace(',','.')+"',monto_efectivo='"+txtEfectivo.getText().replace(',','.')+"',monto_tarjeta='"+txtTarjeta.getText().replace(',','.')+"' ,saldo_restante='"+txtSaldo.getText().replace(',','.')+"' ,vuelto='"+txtVuelto.getText().replace(',','.')+"', cod_caja='"+txtCodCaja.getText()+"' WHERE cod_compra='"+txtCodFactura.getText()+"'");
                }
                
                //BORRO LA FORMA DE PAGO EN EL CASO QUE EN LA MISMA FACTURA SELECCIONEN PRIMERO UNA FORMA DE PAGO Y LUGO OTRA(QUEDA REGISTRADA LA ULTIMA)
                if(x>0){
                    consulta.executeUpdate("DELETE FROM compra_efectivo WHERE cod_compra='"+txtCodFactura.getText()+"'");
                    consulta.executeUpdate("DELETE FROM compra_credito WHERE cod_compra='"+txtCodFactura.getText()+"'");
                    consulta.executeUpdate("DELETE FROM compra_deposito WHERE cod_compra='"+txtCodFactura.getText()+"'");
                    consulta.executeUpdate("DELETE FROM compra_cheque WHERE cod_compra='"+txtCodFactura.getText()+"'"); 
                }
                
                //double totalCred=Double.parseDouble(txtTotal.getText().replace(',','.'));
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

    private void txtVueltoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVueltoKeyReleased
        double total=0.00, saldo=0.00, aCuenta=0.00;
        if(txtTotal.getText().equals("")){
            total=0.0;
        }else{
            
            try{                                
                total=(double)Integer.parseInt(txtTotal.getText().replace(",", "."));
            }catch (NumberFormatException e){
                total=Double.parseDouble(txtTotal.getText().replace(",", "."));
            }
        }
        
        if(txtVuelto.getText().equals("")){
            aCuenta=0.0;
        }else{
            
            try{                                
                aCuenta=(double)Integer.parseInt(txtVuelto.getText().replace(",", "."));
            }catch (NumberFormatException e){
                aCuenta=Double.parseDouble(txtVuelto.getText().replace(",", "."));
            }
        }
        
        saldo= total-aCuenta;
        txtSaldo.setText(String.format("%.2f", saldo).replace(",", "."));
    }//GEN-LAST:event_txtVueltoKeyReleased

    private void txtTarjetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarjetaKeyReleased
        String total=txtTotal.getText();
        if(total.equals("")){
            total="0.00";
        }
        double total2= Double.parseDouble(total.replace(',','.'));
        
        String efectivo=txtEfectivo.getText();
        if(efectivo.equals("")){
            efectivo="0.00";
        }
        double efectivo2= Double.parseDouble(efectivo.replace(',','.'));
        
        String tarjeta=txtTarjeta.getText();
        if(tarjeta.equals("")){
            tarjeta="0.00";
        }
        double tarjeta2= Double.parseDouble(tarjeta.replace(',','.'));
        
        double montoPagado=efectivo2+tarjeta2;
        double saldoRestante=0.0, vuelto=0.0;
        
        if(montoPagado <= total2){
            saldoRestante=total2-montoPagado;
            vuelto=montoPagado-total2;
        }else{
            saldoRestante=0.00;
            vuelto=montoPagado-total2;
        }
        txtSaldo.setText(String.format("%.2f", saldoRestante).replace(',','.'));
        txtVuelto.setText(String.format("%.2f", vuelto).replace(',','.'));
    }//GEN-LAST:event_txtTarjetaKeyReleased

    private void txtTarjetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarjetaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTarjetaKeyTyped

    private void txtEfectivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyReleased
        
        String total=txtTotal.getText();
        if(total.equals("")){
            total="0.00";
        }
        double total2= Double.parseDouble(total.replace(',','.'));
        
        String efectivo=txtEfectivo.getText();
        if(efectivo.equals("")){
            efectivo="0.00";
        }
        double efectivo2= Double.parseDouble(efectivo.replace(',','.'));
        
        String tarjeta=txtTarjeta.getText();
        if(tarjeta.equals("")){
            tarjeta="0.00";
        }
        double tarjeta2= Double.parseDouble(tarjeta.replace(',','.'));
        
        double montoPagado=efectivo2+tarjeta2;
        double saldoRestante=0.0, vuelto=0.0;
        
        if(montoPagado <= total2){
            saldoRestante=total2-montoPagado;
            vuelto=montoPagado-total2;
        }else{
            saldoRestante=0.00;
            vuelto=montoPagado-total2;
        }
        txtSaldo.setText(String.format("%.2f", saldoRestante).replace(',','.'));
        txtVuelto.setText(String.format("%.2f", vuelto).replace(',','.'));
        
    }//GEN-LAST:event_txtEfectivoKeyReleased

    private void txtEfectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEfectivoKeyTyped

    private void txtVueltoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVueltoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVueltoActionPerformed

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
            java.util.logging.Logger.getLogger(Agregar_TarjetaYEfectivo_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agregar_TarjetaYEfectivo_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agregar_TarjetaYEfectivo_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agregar_TarjetaYEfectivo_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Agregar_TarjetaYEfectivo_Compra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JTextField txtCodCaja;
    public static javax.swing.JTextField txtCodFactura;
    public static javax.swing.JTextField txtEfectivo;
    public static javax.swing.JTextField txtRecibe;
    public static javax.swing.JTextField txtSaldo;
    public static javax.swing.JTextField txtTarjeta;
    public static javax.swing.JTextField txtTotal;
    public static javax.swing.JTextField txtVuelto;
    // End of variables declaration//GEN-END:variables

    private void deshabilitar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
