
package Vistas;

import static Vistas.Agregar_Deposito.txtCodFactura;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Agregar_Cheque_Compra extends javax.swing.JFrame {    
    String codigo,referencia,cantidad,marca,valor, fechaDeposito,fechaVencimiento;
    
     
    public Agregar_Cheque_Compra() {
        initComponents();
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        txtFecha.setVisible(false);
        txtRecibe.setVisible(false);
        txtFechaVencimiento.setVisible(false);
        setearFecha();        

    }
    
    public void setearFecha() {   
        //SETEO LA FECHA ACTUAL
        Calendar c2 = new GregorianCalendar();
        calendario.setCalendar(c2);    
    }
    
    public static void cargarDeposito(){
        try{
            Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql                             
            Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta1.executeQuery("select * from compra_cheque where cod_compra='"+txtCodFactura.getText()+"'");    

            txtNumCheque.setText("");    
            if(txtMontoCheque.getText().equals("")){
                txtMontoCheque.setText("0.00");
            }
            
            while(r.next()){
                txtNumCheque.setText(r.getString(3));
                //txtTotal.setText(r.getString(5));         
                txtFecha.setText(r.getString(4)); 
                txtFechaVencimiento.setText(r.getString(5)); 
                txtMontoCheque.setText(r.getString(6)); 
                 
            }
            txtNumCheque.requestFocus();
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ERROR EN LA BASE DE DATOS");
            System.out.println(ex);
        }
    }
    
    public void cargarCalendario(){
        fechaDeposito=txtFecha.getText();
        int año=Integer.parseInt(fechaDeposito.substring(0,4));
        int mes=Integer.parseInt(fechaDeposito.substring(5,7));
        int dia=Integer.parseInt(fechaDeposito.substring(8,10));
        Calendar c3 = new GregorianCalendar(año,mes-1,dia);
        calendario.setCalendar(c3); 

        
        fechaVencimiento=txtFechaVencimiento.getText();
        año=Integer.parseInt(fechaVencimiento.substring(0,4));
        mes=Integer.parseInt(fechaVencimiento.substring(5,7));
        dia=Integer.parseInt(fechaVencimiento.substring(8,10));
        Calendar c4 = new GregorianCalendar(año,mes-1,dia);
        calendarioVencimiento.setCalendar(c4); 
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtNumCheque = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtCodFactura = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        calendario = new com.toedter.calendar.JDateChooser();
        txtRecibe = new javax.swing.JTextField();
        calendarioVencimiento = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        txtMontoCheque = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtFechaVencimiento = new javax.swing.JTextField();

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
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 110, 40));

        txtNumCheque.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtNumCheque.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumCheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumChequeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumChequeKeyTyped(evt);
            }
        });
        jPanel1.add(txtNumCheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 280, 40));

        jLabel8.setBackground(new java.awt.Color(239, 255, 239));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Monto compra");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 130, 40));

        txtTotal.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalKeyTyped(evt);
            }
        });
        jPanel1.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 280, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registro de Pago Cheque");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 320, -1));
        jPanel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 40, 40));

        jButton1.setBackground(new java.awt.Color(5, 52, 99));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 440, -1, -1));

        jButton2.setBackground(new java.awt.Color(5, 52, 99));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, -1, -1));

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
        jPanel1.add(txtCodFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 280, 40));

        jLabel6.setBackground(new java.awt.Color(239, 255, 239));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("N°de cheque");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 120, 40));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Monto cheque");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 150, 40));

        calendario.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jPanel1.add(calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 280, 40));
        jPanel1.add(txtRecibe, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 40, 40));

        calendarioVencimiento.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jPanel1.add(calendarioVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 280, 40));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Fecha emisión");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 140, 40));

        txtMontoCheque.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtMontoCheque.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMontoCheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoChequeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoChequeKeyTyped(evt);
            }
        });
        jPanel1.add(txtMontoCheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 280, 40));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Fecha vencimiento");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 180, 40));
        jPanel1.add(txtFechaVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 40, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNumChequeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumChequeKeyReleased
        txtNumCheque.setText(txtNumCheque.getText().toUpperCase());
        
    }//GEN-LAST:event_txtNumChequeKeyReleased

    private void txtNumChequeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumChequeKeyTyped

    }//GEN-LAST:event_txtNumChequeKeyTyped

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyReleased

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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String contribuyente="", codigoCliente="";
        txtNumCheque.setBackground(new Color(255,255,255));
        txtTotal.setBackground(new Color(255,255,255));
        
        if (txtNumCheque.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el total a pagar","Advertencia",JOptionPane.WARNING_MESSAGE);
            txtNumCheque.setBackground(Color.yellow);
            txtNumCheque.setText("");
            txtNumCheque.requestFocus();
        }else{
            try{
                 String fecha="";
                 int año = calendario.getCalendar().get(Calendar.YEAR);
                 int mes = calendario.getCalendar().get(Calendar.MONTH);
                 int dia = calendario.getCalendar().get(Calendar.DAY_OF_MONTH);

                 if((mes+1)<10 && (dia>=10)){
                    fecha = (año+"/0"+(mes+1)+"/"+dia);
                 }else{
                     if(((mes+1)<10 && (dia<10))){
                         fecha = (año+"/0"+(mes+1)+"/0"+dia);
                     }else{
                          fecha = (año+"/"+(mes+1)+"/"+dia);
                     }
                }
                 
                 String fechaVencimiento="";
                 int año2 = calendarioVencimiento.getCalendar().get(Calendar.YEAR);
                 int mes2 = calendarioVencimiento.getCalendar().get(Calendar.MONTH);
                 int dia2 = calendarioVencimiento.getCalendar().get(Calendar.DAY_OF_MONTH);

                 if((mes2+1)<10 && (dia2>=10)){
                    fechaVencimiento = (año2+"/0"+(mes2+1)+"/"+dia2);
                 }else{
                     if(((mes2+1)<10 && (dia2<10))){
                         fechaVencimiento = (año2+"/0"+(mes2+1)+"/0"+dia2);
                     }else{
                          fechaVencimiento = (año2+"/"+(mes2+1)+"/"+dia2);
                     }
                }
                                                        
                Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta = conn.createStatement();
                ResultSet r= consulta.executeQuery("select cod_compra from compra_cheque where cod_compra='"+txtCodFactura.getText()+"'");
                int banderaCredito=0;
                
                while(r.next()){
                    banderaCredito=1;
                }
                int xx=0;
                if (banderaCredito==0){
                    xx=consulta.executeUpdate("insert into compra_cheque(cod_compra,num_cheque,fecha_emision,fecha_vencimiento,monto) VALUES('"+txtCodFactura.getText()+"','"+txtNumCheque.getText().replace(',','.')+"','"+fecha+"','"+fechaVencimiento+"','"+txtMontoCheque.getText().replace(',','.')+"')");
                }else{
                    xx=consulta.executeUpdate("UPDATE compra_cheque SET num_cheque='"+txtNumCheque.getText().replace(',','.')+"',monto='"+txtMontoCheque.getText().replace(',','.')+"',fecha_emision='"+fecha+"',fecha_vencimiento='"+fechaVencimiento+"' WHERE cod_compra='"+txtCodFactura.getText()+"'");
                }
                
                //BORRO LA FORMA DE PAGO EN EL CASO QUE EN LA MISMA FACTURA SELECCIONEN PRIMERO UNA FORMA DE PAGO Y LUGO OTRA(QUEDA REGISTRADA LA ULTIMA)       
                if(xx>0){
                    consulta.executeUpdate("DELETE FROM compra_mixta WHERE cod_compra='"+txtCodFactura.getText()+"'");
                    consulta.executeUpdate("DELETE FROM compra_credito WHERE cod_compra='"+txtCodFactura.getText()+"'");
                    consulta.executeUpdate("DELETE FROM compra_efectivo WHERE cod_compra='"+txtCodFactura.getText()+"'");
                    consulta.executeUpdate("DELETE FROM compra_deposito WHERE cod_compra='"+txtCodFactura.getText()+"'"); 
                }
                
                double totalCred=Double.parseDouble(txtTotal.getText().replace(',','.'));
                /*if(txtRecibe.getText().equals("1")){
                    Factura_Venta.sub.setText(String.format("%.2f",totalCred).replace(",", "."));
                    //Factura_Venta.txtDescTotal.setText("0.00");
                    Factura_Venta.total.setText(String.format("%.2f",totalCred).replace(",", "."));
                }else if(txtRecibe.getText().equals("2")){
                        Frm_facturarRemito.sub.setText(String.format("%.2f",totalCred).replace(",", "."));
                        //Frm_facturarRemito.txtDescTotal.setText("0.00");
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

    private void txtMontoChequeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoChequeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoChequeKeyReleased

    private void txtMontoChequeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoChequeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoChequeKeyTyped

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
            java.util.logging.Logger.getLogger(Agregar_Cheque_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agregar_Cheque_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agregar_Cheque_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agregar_Cheque_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Agregar_Cheque_Compra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static com.toedter.calendar.JDateChooser calendario;
    public static com.toedter.calendar.JDateChooser calendarioVencimiento;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JTextField txtCodFactura;
    public static javax.swing.JTextField txtFecha;
    public static javax.swing.JTextField txtFechaVencimiento;
    public static javax.swing.JTextField txtMontoCheque;
    public static javax.swing.JTextField txtNumCheque;
    public static javax.swing.JTextField txtRecibe;
    public static javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    private void deshabilitar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
