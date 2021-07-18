
package Vistas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Caja_Ingreso extends javax.swing.JFrame {


    public Caja_Ingreso() {
        initComponents();
        setearFecha() ;
        cargarCombos();
        
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
            setTitle("Ingreso de efectivo");
        setLocationRelativeTo(null);
    }
    
    public void cargarCombos(){
        //CARGO LOS COMBOS CON LA BD
        calendario.setVisible(false);
        txtCodVendedor.setVisible(false);
        txtCodCaja.setVisible(false);
        txtCaja.setEditable(false);
        //icono del sistema
        txtCodVendedor.setText("");
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            Statement consulta2=conn.createStatement(); // crea una variable que se encargue del código de sql
 
            /***********SELECCIONO VENDEDOR ACTIVO******************/
            ResultSet  res2= consulta.executeQuery("select nombre_usuario from ingreso_usuarios WHERE estado='ACTIVO' ");
            String usuarioActivo= "";
            while(res2.next()){
               usuarioActivo= res2.getString(1);     
            }


            Statement consulta4=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet  res3= consulta4.executeQuery("select * from empleado WHERE estado='ACTIVO' order by nombres");  
            comboVendedor.removeAllItems();
            comboVendedor.addItem("SELECCIONE VENDEDOR");
            int bandera=1;
            while(res3.next()){
               comboVendedor.addItem(res3.getString(2)+" "+res3.getString(3));
               String vendedor =res3.getString(2)+" "+res3.getString(3);
               if(vendedor.equals(usuarioActivo)){
                    bandera=1;
               }

            }
            if (bandera==1){
                comboVendedor.setSelectedItem(usuarioActivo);
            }
            /************************************/
            
                
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
        jMenuItem2 = new javax.swing.JMenuItem();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        comboVendedor = new javax.swing.JComboBox();
        btnBuscarVendedor = new javax.swing.JButton();
        calendario = new com.toedter.calendar.JDateChooser();
        rSLabelHora2 = new rojeru_san.RSLabelHora();
        rSLabelFecha1 = new rojeru_san.RSLabelFecha();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        Guardar = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        b42 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        txtCodVendedor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtConcepto = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        txtCaja = new javax.swing.JTextField();
        txtCodCaja = new javax.swing.JTextField();
        btnBuscarVendedor1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

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

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 44)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("INGRESO DE EFECTIVO");
        panelImage1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 430, 50));

        jButton3.setBackground(new java.awt.Color(0, 204, 204));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoCajaIngreso.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        panelImage1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 140, 90));

        jLabel14.setBackground(new java.awt.Color(239, 255, 239));
        jLabel14.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel14.setText("Vendedor");
        panelImage1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 100, 50));

        comboVendedor.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        comboVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboVendedorActionPerformed(evt);
            }
        });
        panelImage1.add(comboVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 410, 50));

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
        panelImage1.add(btnBuscarVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 210, 50, 50));

        calendario.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        panelImage1.add(calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 630, 110, 40));

        rSLabelHora2.setForeground(new java.awt.Color(93, 116, 163));
        rSLabelHora2.setFont(new java.awt.Font("Roboto Bold", 1, 24)); // NOI18N
        panelImage1.add(rSLabelHora2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 160, -1));

        rSLabelFecha1.setForeground(new java.awt.Color(93, 116, 163));
        rSLabelFecha1.setFont(new java.awt.Font("Roboto Bold", 1, 24)); // NOI18N
        panelImage1.add(rSLabelFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 150, -1));

        jLabel15.setBackground(new java.awt.Color(239, 255, 239));
        jLabel15.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel15.setText("Concepto");
        panelImage1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 110, 50));

        jLabel16.setBackground(new java.awt.Color(239, 255, 239));
        jLabel16.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel16.setText("Caja");
        panelImage1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 60, 50));

        txtMonto.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtMonto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMonto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });
        panelImage1.add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, 470, 50));

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
        panelImage1.add(Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 540, -1, -1));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel17.setText("Guardar");
        panelImage1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 640, -1, -1));

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
        panelImage1.add(b42, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 540, -1, -1));

        jLabel34.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel34.setText("Cancelar");
        panelImage1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 640, -1, -1));
        panelImage1.add(txtCodVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, 50, 50));

        txtConcepto.setColumns(20);
        txtConcepto.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtConcepto.setRows(5);
        txtConcepto.setMargin(new java.awt.Insets(5, 5, 5, 5));
        txtConcepto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConceptoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtConcepto);

        panelImage1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, 470, 110));

        jLabel18.setBackground(new java.awt.Color(239, 255, 239));
        jLabel18.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel18.setText("Monto a ingresar");
        panelImage1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 190, 50));

        txtCaja.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtCaja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCaja.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCajaActionPerformed(evt);
            }
        });
        txtCaja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCajaKeyTyped(evt);
            }
        });
        panelImage1.add(txtCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 270, 410, 50));
        panelImage1.add(txtCodCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 270, 50, 50));

        btnBuscarVendedor1.setBackground(new java.awt.Color(93, 116, 163));
        btnBuscarVendedor1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoLupaF3.png"))); // NOI18N
        btnBuscarVendedor1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarVendedor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVendedor1ActionPerformed(evt);
            }
        });
        btnBuscarVendedor1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBuscarVendedor1KeyPressed(evt);
            }
        });
        panelImage1.add(btnBuscarVendedor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 270, 50, 50));

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Opciones");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem1.setText("Buscar vendedor ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem3.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem3.setText("Buscar caja ");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator2);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem4.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem4.setText("Ver Ingresos de efectivo");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false); 
    }//GEN-LAST:event_formWindowClosing

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void comboVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboVendedorActionPerformed
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            String nomempleado = (String)comboVendedor.getSelectedItem();
            String cod_empleado=null;

            String cadena=comboVendedor.getSelectedItem().toString();
            int i=0;
            while(cadena.charAt(i)!=' ') {
                i++;
            }
            String SubCadenaNombreEmpleado = cadena.substring(0,i);
            String SubCadenaApellidoEmpleado = cadena.substring(i+1,cadena.length());
            //SEGUN EL NOMBRE DEL VENDEDOR SELECCIONADO EN EL COMBO BUSCO EN LA BD SU CODIGO PARA GUARDARLO EN LA TABLA DE LA BD FACTURA
            ResultSet rs = consulta.executeQuery("SELECT cod_empleado FROM empleado WHERE (nombres = '"+SubCadenaNombreEmpleado+"' AND apellidos = '"+SubCadenaApellidoEmpleado+"')");
            while (rs.next()) {
                cod_empleado= rs.getString(1);
            }
            if(txtCodVendedor.getText().equals("")){
                txtCodVendedor.setText(cod_empleado);
            }
            txtMonto.requestFocus();
            conn.close();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }

    }//GEN-LAST:event_comboVendedorActionPerformed

    private void btnBuscarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVendedorActionPerformed
        //BUSCO EL VENDEDOR
        enviar_empleado form= new enviar_empleado ();
        form.setVisible(true);
        form.toFront();
        enviar_empleado.txt_recibeEmpleado.setText("16");
        enviar_empleado.txt_recibeEmpleado.setVisible(false);
        enviar_empleado.enviarEmpleado.setVisible(false);
    }//GEN-LAST:event_btnBuscarVendedorActionPerformed

    private void btnBuscarVendedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarVendedorKeyPressed

    }//GEN-LAST:event_btnBuscarVendedorKeyPressed
    public void guardarCaja(){
        String monto =txtMonto.getText();
        if(monto.equals("")){
            monto="0.00";
        }
        String vendedor=comboVendedor.getSelectedItem().toString();
        
        if(vendedor.equals("SELECCIONE VENDEDOR")){
            JOptionPane.showMessageDialog(null,"Debe seleccionar el vendedor que va a ingresar el monto") ;
        }else{ if(txtCaja.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe seleccionar la caja a la que le va a ingresar el monto") ;
        }else{ if(monto.equals("0.00")){
                    JOptionPane.showMessageDialog(null,"El monto de efectivo ingresado debe ser mayor a cero") ;
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

                        int i=consulta.executeUpdate("insert into ingreso_efectivo(cod_caja,monto_ingresado,concepto,vendedor,fecha,hora) VALUES('"+txtCodCaja.getText()+"','"+monto+"','"+txtConcepto.getText()+"','"+txtCodVendedor.getText()+"','"+fecha+"','"+hora+"')");

                        if(i>0){              
                            ResultSet r= consulta.executeQuery("select cod_ingreso from ingreso_efectivo where hora='"+hora+"' and fecha='"+fecha+"'");
                            r.next();
                            String codIngreso=r.getString(1);
                            //IMPRIMO EL REPORTE
                            Connection miconexion = conexion.ObtenerConexion();
                            Map parametros = new HashMap();
                            parametros.put("codf",codIngreso);

                            int op=JOptionPane.showConfirmDialog(null, "INGRESO DE EFECTIVO REALIZADO CORRECTAMENTE ¿DESEA VER EL COMPROBANTE?","SELECCIONE UN OPCION" ,JOptionPane.YES_NO_OPTION);
                            if (op==JOptionPane.YES_NO_OPTION){
                                try {
                                    this.setVisible(false);
                                    String reporte="ingresoEfectivo1.jasper";
                                    JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                                    JasperViewer ventanavisor=new JasperViewer(informe,false);
                                    ventanavisor.setTitle("Reporte de ingreso de efectivo");
                                    ventanavisor.setVisible(true);
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(this, e.getMessage());
                                }
                           }else
                                {
                                 // no hacer nada
                                }
                        }
                        this.dispose();
                    }
                    catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,"ERROR AL GUARDAR LA CAJA");
                        System.out.println(ex);
                    }
                }        
            }   
        }
    }
    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed

        guardarCaja();
        
        
    }//GEN-LAST:event_GuardarActionPerformed

    private void b42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b42ActionPerformed
        this.dispose();
    }//GEN-LAST:event_b42ActionPerformed

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        char []p={'1','2','3','4','5','6','7','8','9','0','.',','}; //PARA PODER INGRESAR SOLO NUMEROS O PUNTOS O COMAS
        int b=0;
        for(int i=0;i<=11;i++){
            if (p[i]==evt.getKeyChar()){
                b=1;
            }
        }
        if(b==0){evt.consume();  getToolkit().beep(); }
    }//GEN-LAST:event_txtMontoKeyTyped

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        guardarCaja();
    }//GEN-LAST:event_txtMontoActionPerformed

    private void txtCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCajaActionPerformed

    private void txtCajaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCajaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCajaKeyTyped

    private void btnBuscarVendedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVendedor1ActionPerformed
        enviar_caja_abierta form= new enviar_caja_abierta ();
        form.setVisible(true);
        form.toFront();
        enviar_caja_abierta .txt_recibe.setText("1");
    }//GEN-LAST:event_btnBuscarVendedor1ActionPerformed

    private void btnBuscarVendedor1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarVendedor1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarVendedor1KeyPressed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        enviar_caja_abierta form= new enviar_caja_abierta ();
        form.setVisible(true);
        form.toFront();
        enviar_caja_abierta .txt_recibe.setText("1");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //BUSCO EL VENDEDOR
        enviar_empleado form= new enviar_empleado ();
        form.setVisible(true);
        form.toFront();
        enviar_empleado.txt_recibeEmpleado.setText("16");
        enviar_empleado.txt_recibeEmpleado.setVisible(false);
        enviar_empleado.enviarEmpleado.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txtConceptoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConceptoKeyReleased
        txtConcepto.setText(txtConcepto.getText().toUpperCase());
    }//GEN-LAST:event_txtConceptoKeyReleased

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        //BUSCO EL VENDEDOR
        Consultar_Ingreso_Efectivo form= new Consultar_Ingreso_Efectivo ();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
            java.util.logging.Logger.getLogger(Caja_Ingreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Ingreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Ingreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Ingreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Caja_Ingreso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Guardar;
    private javax.swing.JButton b42;
    private javax.swing.JButton btnBuscarVendedor;
    private javax.swing.JButton btnBuscarVendedor1;
    private com.toedter.calendar.JDateChooser calendario;
    public static javax.swing.JComboBox comboVendedor;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private rojeru_san.RSLabelFecha rSLabelFecha1;
    private rojeru_san.RSLabelHora rSLabelHora1;
    private rojeru_san.RSLabelHora rSLabelHora2;
    public static javax.swing.JTextField txtCaja;
    public static javax.swing.JTextField txtCodCaja;
    public static javax.swing.JTextField txtCodVendedor;
    private javax.swing.JTextArea txtConcepto;
    public static javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
