
package Vistas;
import static Vistas.Factura_Venta.comboArticulo;
import static Vistas.Factura_Venta.comboCliente;
import static Vistas.Factura_Venta.comboFactura;
import static Vistas.Factura_Venta.comboPago;
import static Vistas.Factura_Venta.comboVendedor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Agregar_Credito_Compra_Manual extends javax.swing.JFrame {


    public Agregar_Credito_Compra_Manual() {
        initComponents();
        setearFecha() ;
        cargarCombos();
        calendario.setVisible(false);
        txtCodProveedor.setVisible(false);
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        setTitle("Apertura de caja");
        setLocationRelativeTo(null);
    }
    
    public void cargarCombos(){
        //CARGO LOS COMBOS CON LA BD
        txtCodProveedor.setText("");
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            Statement consulta2=conn.createStatement(); // crea una variable que se encargue del código de sql
 
            ResultSet  res= consulta.executeQuery("select * from proveedor WHERE estado='ACTIVO' order by nombre_firma");

            comboProveedor.removeAllItems();
            comboProveedor.addItem("SELECCIONE PROVEEDOR");
            while(res.next()){
                comboProveedor.addItem(res.getString(2));

            }
            res.close();
     
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
    }

    public void setearFecha() {   
        //SETEO LA FECHA ACTUAL
        Calendar c2 = new GregorianCalendar();
        calendario.setCalendar(c2);    
    }
    public String obtenerHora(){
        String hora="";
        hora=rSLabelHora1.getHora();
        return hora;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSLabelHora1 = new rojeru_san.RSLabelHora();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        comboProveedor = new javax.swing.JComboBox();
        btnBuscarVendedor = new javax.swing.JButton();
        calendario = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        txtAgregarSaldo = new javax.swing.JTextField();
        Guardar = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        b42 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        txtCodProveedor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Reporte de clientes");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoMenumar22.png"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("QUE LA EMPRESA DEBE PROVEEDOR )");
        panelImage1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 500, 40));

        jLabel14.setBackground(new java.awt.Color(239, 255, 239));
        jLabel14.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel14.setText("Proveedor");
        panelImage1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 120, 50));

        comboProveedor.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        comboProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProveedorActionPerformed(evt);
            }
        });
        panelImage1.add(comboProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 410, 50));

        btnBuscarVendedor.setBackground(new java.awt.Color(93, 116, 163));
        btnBuscarVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoLupaF1.png"))); // NOI18N
        btnBuscarVendedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVendedorActionPerformed(evt);
            }
        });
        btnBuscarVendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBuscarVendedorKeyPressed(evt);
            }
        });
        panelImage1.add(btnBuscarVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, 50, 50));

        calendario.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        panelImage1.add(calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 610, 120, 40));

        jLabel15.setBackground(new java.awt.Color(239, 255, 239));
        jLabel15.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel15.setText("Saldo restante");
        panelImage1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 160, 50));

        txtAgregarSaldo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtAgregarSaldo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAgregarSaldo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtAgregarSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgregarSaldoActionPerformed(evt);
            }
        });
        txtAgregarSaldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAgregarSaldoKeyTyped(evt);
            }
        });
        panelImage1.add(txtAgregarSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, 410, 50));

        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardar.png"))); // NOI18N
        Guardar.setToolTipText("");
        Guardar.setBorder(null);
        Guardar.setBorderPainted(false);
        Guardar.setContentAreaFilled(false);
        Guardar.setFocusPainted(false);
        Guardar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        Guardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        Guardar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        panelImage1.add(Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 490, -1, -1));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel17.setText("Guardar");
        panelImage1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 600, -1, -1));

        b42.setBackground(new java.awt.Color(51, 153, 255));
        b42.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        b42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelar.png"))); // NOI18N
        b42.setBorder(null);
        b42.setBorderPainted(false);
        b42.setContentAreaFilled(false);
        b42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b42.setFocusCycleRoot(true);
        b42.setFocusable(false);
        b42.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        b42.setRequestFocusEnabled(false);
        b42.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        b42.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        b42.setVerifyInputWhenFocusTarget(false);
        b42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b42ActionPerformed(evt);
            }
        });
        panelImage1.add(b42, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 490, -1, -1));

        jLabel34.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel34.setText("Cancelar");
        panelImage1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 600, -1, -1));
        panelImage1.add(txtCodProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 230, 50, 50));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 40)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("AGREGAR CRÉDITO DE COMPRA MANUALMENTE ");
        panelImage1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 890, 50));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("( ESTE MONTO SE SUMA AL SALDO RESTANTE ");
        panelImage1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 500, 40));

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Opciones");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem2.setText("Buscar proveedor");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 976, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false); 
    }//GEN-LAST:event_formWindowClosing

    private void comboProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProveedorActionPerformed
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            String nomProveedor= (String)comboProveedor.getSelectedItem();
            String codigoProveedor="0";

            
            //PARA LLENAR LOS CAMPOS DE LA FACTURA DEPENDIENDO EL PROVEEDOR QUE SE SELECCIONA
            ResultSet rs = consulta.executeQuery("SELECT cod_proveedor FROM proveedor WHERE nombre_firma = '"+nomProveedor+"'" );
            while (rs.next()) {
                codigoProveedor= rs.getString(1);


            }      
            txtCodProveedor.setText(codigoProveedor);
            txtAgregarSaldo.requestFocus();
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error SQL ");
        }

    }//GEN-LAST:event_comboProveedorActionPerformed

    private void btnBuscarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVendedorActionPerformed
        //BUSCO LOS CLIENTES
        enviar_proveedor cp = new enviar_proveedor(this, true);
        cp.txtRecibe.setText("2");
        cp.toFront();
    }//GEN-LAST:event_btnBuscarVendedorActionPerformed

    private void btnBuscarVendedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarVendedorKeyPressed

    }//GEN-LAST:event_btnBuscarVendedorKeyPressed

    private void txtAgregarSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgregarSaldoActionPerformed
       // guardarCaja();
    }//GEN-LAST:event_txtAgregarSaldoActionPerformed

    private void txtAgregarSaldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAgregarSaldoKeyTyped
        char []p={'1','2','3','4','5','6','7','8','9','0','.',','}; //PARA PODER INGRESAR SOLO NUMEROS O PUNTOS O COMAS
        int b=0;
        for(int i=0;i<=11;i++){
            if (p[i]==evt.getKeyChar()){
                b=1;
            }
        }
        if(b==0){evt.consume();  getToolkit().beep(); } 
    }//GEN-LAST:event_txtAgregarSaldoKeyTyped
   
    public void guardarCredito(){

        
        if(comboProveedor.getSelectedItem().equals("SELECCIONE PROVEEDOR")){
            JOptionPane.showMessageDialog(null,"Debe seleccionar el proveedor") ;
        }else{ if(txtAgregarSaldo.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Debe ingresar un monto") ;
                    txtAgregarSaldo.requestFocus();
                }else{ 
                            int año = calendario.getCalendar().get(Calendar.YEAR);
                            int mes = calendario.getCalendar().get(Calendar.MONTH);
                            int dia = calendario.getCalendar().get(Calendar.DAY_OF_MONTH);
                            String fecha="";
                            if((mes+1)<10 && (dia>=10)){
                                fecha = (año+"/0"+(mes+1)+"/"+dia);
                            }else{
                                if(((mes+1)<10 && (dia<10))){
                                      fecha = (año+"/0"+(mes+1)+"/0"+dia);
                                }else{
                                      fecha = (año+"/"+(mes+1)+"/"+dia);
                                }
                            }

                             String hora= obtenerHora();

                             try{
                                Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                                Statement consulta = conn.createStatement();

                                Statement consulta3=conn.createStatement(); //BUSCO EL CLIENTE EN LA CUENTA CORRIENTE Y SI NO ESTA LO AGREGO
                                Statement consulta4=conn.createStatement(); //BUSCO EL CLIENTE EN LA CUENTA CORRIENTE Y SI NO ESTA LO AGREGO

                                ResultSet r3= consulta3.executeQuery("select * from cuenta_credito_compra WHERE cod_proveedor='"+txtCodProveedor.getText()+"'");
                                int banderaClienteCorriente=0;
                                while(r3.next()){
                                    banderaClienteCorriente=1;
                                }
                                String pago="0.00", saldoRestante="0.00",codCuentaCredito="0";
                                saldoRestante=txtAgregarSaldo.getText();


                               //SIEL CLIENTE NO ESTA EN CUENTA CREDITO VENTA LO AGREGO
                                if(banderaClienteCorriente==0){
                                    consulta3.executeUpdate("insert into cuenta_credito_compra (cod_proveedor,ultimo_pago,fecha_ultimo_pago,total_pago,saldo_restante) values('"+txtCodProveedor.getText()+"','"+pago+"','"+fecha+"','0.00','"+saldoRestante+"')");

                                    //BUSCO EL CODIGO DE LA CUENTA CREDITO VENTA
                                    ResultSet r5= consulta4.executeQuery("select cod_cuenta from cuenta_credito_compra WHERE cod_proveedor='"+txtCodProveedor.getText()+"'");

                                    while(r5.next()){
                                        codCuentaCredito=r5.getString(1);
                                    }
                                }else{

                                    //BUSCO EL CODIGO DE LA CUENTA CREDITO VENTA
                                    ResultSet r5= consulta4.executeQuery("select cod_cuenta from cuenta_credito_compra WHERE cod_proveedor='"+txtCodProveedor.getText()+"'");

                                    while(r5.next()){
                                        codCuentaCredito=r5.getString(1);
                                    }
                                    //ACTUALIZO LOS DATOS DE LA CUENTA CREDITO VENTA
                                    consulta3.executeUpdate("UPDATE cuenta_credito_compra SET ultimo_pago='"+pago+"', fecha_ultimo_pago='"+fecha+"', saldo_restante=saldo_restante+'"+saldoRestante+"' WHERE cod_cuenta='"+codCuentaCredito+"' ");

                                }
                                ResultSet r6= consulta4.executeQuery("select total_pago, total_pago,saldo_restante from cuenta_credito_compra WHERE cod_cuenta='"+codCuentaCredito+"'");
                                String pagoAcumulado="", saldoRestanteCuentaCredito="0.0", totalPagoCuentaCredito="0.0";
                                while(r6.next()){
                                    pagoAcumulado=r6.getString(1);
                                    totalPagoCuentaCredito=r6.getString(2);
                                    saldoRestanteCuentaCredito=r6.getString(3);
                                }

                                //int j=consulta4.executeUpdate("INSERT INTO referencia_cuenta_credito_compra (cod_cuenta,fecha,pago_abonado,total_pago,saldo_restante,total_factura)VALUES('"+codCuentaCredito+"','"+fecha+"','"+pago+"','"+saldoRestante+"','"+saldoRestanteCuentaCredito+"','"+(Double.parseDouble(saldoRestanteCuentaCredito)+Double.parseDouble(totalPagoCuentaCredito))+"')");

                                ResultSet r7= consulta4.executeQuery("select MAX(cod_compra) from compra");
                                int codigoFactura=0;
                                while (r7.next()){
                                    codigoFactura=Integer.parseInt(r7.getString(1));
                                }
                                codigoFactura=codigoFactura+1;
                                //consulta.executeUpdate("insert into compras (cod_compra,cod_recepcion,cod_proveedor, fecha,hora,tipo_pago,total_sin_iva,total_con_iva,iva,acuenta,saldo_restante,tipo) values('"+codigoFactura+"','0','"+txtCodProveedor.getText()+"','"+fecha+"','"+hora+"','CREDITO','"+saldoRestante+"','"+saldoRestante+"','0.00','0.00','"+saldoRestante+"','CREDITO MANUAL')");
                                //consulta.executeUpdate("insert into referencia_compras (cod_compra,cod_recepcion,cod_articulo,cantidad,referencia,envase,unidades,total_unidades,unidad) values('"+codigoFactura+"','0','0','0.00','CREDITO AGREGADO MANUALMENTE','-','0.00','0.00','-')");
                                
                                consulta.executeUpdate("insert into compra (cod_compra, fecha,cod_proveedor,condicion,categoria,tipo_pago,tipo_factura,iva,total_con_iva,total_sin_iva,ivaDiscriminado) values('"+codigoFactura+"','"+fecha+"','"+txtCodProveedor.getText()+"','ACTIVA','ARTICULOS','CREDITO','B','0.00','"+saldoRestante+"','"+saldoRestante+"','0.00')");
                                consulta.executeUpdate("insert into referencia_compra (cod_compra,codigo_producto,referencia,cantidad,unitario_costo,total_costo,valor_unitario,valor_total,total_compra,descuento,unitario_sin_iva) values('"+codigoFactura+"','000'  ,'CREDITO MANUAL','0.0','"+saldoRestante+"','"+saldoRestante+"','"+saldoRestante+"','"+saldoRestante+"','"+saldoRestante+"','0.00','"+saldoRestante+"')");
                                                                                                 
                                ResultSet r8= consulta4.executeQuery("select cod_caja from apertura_caja where estado='ABIERTA'");
                                String codCaja="0";
                                while (r8.next()){
                                    codCaja=r8.getString(1);
                                }
                                
                                consulta.executeUpdate("insert into compra_credito(cod_compra,total,acuenta,saldo_restante,cod_caja) VALUES('"+codigoFactura+"','"+txtAgregarSaldo.getText().replace(',','.')+"','0.00','"+txtAgregarSaldo.getText().replace(',','.')+"','"+codCaja+"')");
                                   
                                int j=consulta4.executeUpdate("INSERT INTO referencia_cuenta_credito_venta (cod_cuenta,fecha,pago_abonado,total_pago,saldo_restante,total_factura,cod_factura)VALUES('"+codCuentaCredito+"','"+fecha+"','0.00','"+saldoRestanteCuentaCredito+"','"+saldoRestanteCuentaCredito+"','"+saldoRestanteCuentaCredito+"','"+codigoFactura+"')");
                                
                                if(j>0){
                                    JOptionPane.showMessageDialog(null,"SALDO GUARDADO CORRECTAMENTE");
                                    this.dispose();
                                    new Consultar_Credito_Compras ().setVisible(true);
                                }
                            }
                            catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null,"ERROR AL GUARDAR SALDO");
                                System.out.println(ex);
                            }
                       
                }
            
        }
    }
    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed

        guardarCredito();
        
        
    }//GEN-LAST:event_GuardarActionPerformed

    private void b42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b42ActionPerformed
        this.dispose();
        new Consultar_Credito_Ventas ().setVisible(true);
    }//GEN-LAST:event_b42ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        //BUSCO LOS CLIENTES
        enviar_cliente form= new enviar_cliente ();
        form.setVisible(true);
        enviar_cliente.recibe.setText("22");
        form.toFront();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(Agregar_Credito_Compra_Manual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agregar_Credito_Compra_Manual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agregar_Credito_Compra_Manual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agregar_Credito_Compra_Manual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Agregar_Credito_Compra_Manual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Guardar;
    private javax.swing.JButton b42;
    private javax.swing.JButton btnBuscarVendedor;
    private com.toedter.calendar.JDateChooser calendario;
    public static javax.swing.JComboBox comboProveedor;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private rojeru_san.RSLabelHora rSLabelHora1;
    private javax.swing.JTextField txtAgregarSaldo;
    public static javax.swing.JTextField txtCodProveedor;
    // End of variables declaration//GEN-END:variables
}
