
package Vistas;

import Vistas.Factura_Venta;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Agregar_Articulo_Compras extends javax.swing.JFrame {
    
    String codigo,referencia,cantidad,marca,valor;
    String NOMBRE;

    public Agregar_Articulo_Compras() {
        initComponents();
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        recibeCliente.setVisible(false);
        porcentaje.setSelected(true);
        cargarComboProveedores();
        txtCodigo.requestFocus();
    }

    public void habilitara(){
       txtCostoSinIva.setText("");    
       txtPrecioCostoIva.setText("");
       txtPrecioVentaSinIva.setText("");
       txtProducto.setText("");
       txtCodigo.setText("");
       txtCodigo.requestFocus();
    }
    
     public void cargarComboProveedores(){
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet   res= consulta.executeQuery("select * from proveedor order by nombre_firma");
               
            int i;
            comboProveedor.removeAllItems();
            comboProveedor.addItem("SELECCIONE PROVEEDOR");
            i=0;
            while(res.next()){
                comboProveedor.addItem(res.getString(2));
                i++;
            }
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        comboIvaCompra.addItem("19%");
        comboIvaCompra.addItem("0%");
        
        comboIva.addItem("19%");
        comboIva.addItem("0%");
        
        
        comboCategoria.removeAllItems();
        comboCategoria.addItem("ARTICULOS");
        comboCategoria.addItem("MATERIA PRIMA");
        comboCategoria.addItem("INSUMOS");
        comboCategoria.addItem("OTROS");
            
        
        try{
            Connection conn =conexion.ObtenerConexion();
            Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet r= consulta1.executeQuery("select * from porcentajes");

            while(r.next()){
                comboIvaCompra.setSelectedItem(r.getString(3));
                comboIva.setSelectedItem(r.getString(4));
                txtPorcentaje.setText(r.getString(2));
            }
        }catch(Exception e){
            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCostoSinIva = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        recibeCliente = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtPrecioCostoIva = new javax.swing.JTextField();
        txtPrecioVentaSinIva = new javax.swing.JTextField();
        txtPorcentaje = new javax.swing.JTextField();
        porcentaje = new javax.swing.JRadioButton();
        txtCodigo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCodigoProvisorio = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        comboProveedor = new javax.swing.JComboBox();
        txtPrecioVentaConIva = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        comboIva = new javax.swing.JComboBox();
        comboCategoria = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        comboIvaCompra = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

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

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Código Prov");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 110, -1));

        txtProducto.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProductoKeyTyped(evt);
            }
        });
        jPanel1.add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 290, 40));

        jLabel5.setBackground(new java.awt.Color(239, 255, 239));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText(" Costo sin IVA");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 402, 140, 40));

        txtCostoSinIva.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtCostoSinIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCostoSinIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostoSinIvaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoSinIvaKeyTyped(evt);
            }
        });
        jPanel1.add(txtCostoSinIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 400, 180, 40));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Costo con IVA");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 130, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("  Neto Con IVA");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 130, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesXs-01.png"))); // NOI18N
        jLabel1.setText("Agregar Articulo");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));
        jPanel1.add(recibeCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 40, 40));

        jButton1.setBackground(new java.awt.Color(5, 52, 99));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 630, 140, -1));

        jButton2.setBackground(new java.awt.Color(5, 52, 99));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 630, 140, -1));

        txtPrecioCostoIva.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtPrecioCostoIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioCostoIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioCostoIvaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioCostoIvaKeyTyped(evt);
            }
        });
        jPanel1.add(txtPrecioCostoIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 450, 180, 40));

        txtPrecioVentaSinIva.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtPrecioVentaSinIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioVentaSinIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioVentaSinIvaActionPerformed(evt);
            }
        });
        txtPrecioVentaSinIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioVentaSinIvaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioVentaSinIvaKeyTyped(evt);
            }
        });
        jPanel1.add(txtPrecioVentaSinIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 500, 290, 40));

        txtPorcentaje.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtPorcentaje.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPorcentaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPorcentajeActionPerformed(evt);
            }
        });
        txtPorcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPorcentajeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajeKeyTyped(evt);
            }
        });
        jPanel1.add(txtPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 450, 100, 40));

        porcentaje.setBackground(new java.awt.Color(204, 204, 204));
        porcentaje.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        porcentaje.setForeground(new java.awt.Color(255, 255, 255));
        porcentaje.setText("% Ganancia");
        porcentaje.setOpaque(false);
        porcentaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porcentajeActionPerformed(evt);
            }
        });
        jPanel1.add(porcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 450, -1, 40));

        txtCodigo.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 290, 40));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Proveedor");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, 100, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("*");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 460, 20, 30));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("*");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, 20, 30));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("*");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 20, 30));

        txtMarca.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtMarca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMarcaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaKeyTyped(evt);
            }
        });
        jPanel1.add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 290, 40));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Descripción");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 110, -1));

        txtCodigoProvisorio.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtCodigoProvisorio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoProvisorio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoProvisorioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoProvisorioKeyTyped(evt);
            }
        });
        jPanel1.add(txtCodigoProvisorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 290, 40));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Categoria");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 100, 40));

        comboProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(comboProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 290, 40));

        txtPrecioVentaConIva.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtPrecioVentaConIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioVentaConIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioVentaConIvaActionPerformed(evt);
            }
        });
        txtPrecioVentaConIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioVentaConIvaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioVentaConIvaKeyTyped(evt);
            }
        });
        jPanel1.add(txtPrecioVentaConIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 550, 180, 40));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("  Neto Sin IVA");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 510, 130, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Marca");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 60, -1));

        comboIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIvaActionPerformed(evt);
            }
        });
        jPanel1.add(comboIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 550, 100, 40));

        comboCategoria.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        comboCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(comboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 290, 40));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Código");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 70, -1));

        comboIvaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIvaCompraActionPerformed(evt);
            }
        });
        jPanel1.add(comboIvaCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 400, 100, 40));

        jMenu1.setText("Opciones");

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem1.setText("Porcentajes predeterminados");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem2.setText("Cerrar ventana");
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductoKeyTyped

    private void txtCostoSinIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoSinIvaKeyReleased
        //ES DEL TEXTFIEL DE VALOR COSTO SIN IVA
        String ivaCompra=comboIvaCompra.getSelectedItem().toString();
        Double porcentajeIvaCompra=0.0;
        if(ivaCompra.equals("19%")){
            porcentajeIvaCompra=1.19;
        }else
            if(ivaCompra.equals("0%")){
                porcentajeIvaCompra=1.00;
            }
        
        String iva=comboIva.getSelectedItem().toString();
        Double porcentajeIva=0.0;
        if(iva.equals("19%")){
            porcentajeIva=1.19;
        }else
            if(iva.equals("0%")){
               porcentajeIva=1.00;
            }
        Double brutoSinIva=0.0,bruto=0.0,porcent=0.0;
        
        if(!txtCostoSinIva.getText().equals("")){
            try{                                
                brutoSinIva=(double)Integer.parseInt(txtCostoSinIva.getText().replace(",", "."));
            }catch (NumberFormatException e){
                brutoSinIva=Double.parseDouble(txtCostoSinIva.getText().replace(",", "."));
            }
            
        }
        Double brutoConIva= brutoSinIva*porcentajeIvaCompra;
        brutoConIva=Math.round(brutoConIva * 100.0)/ 100.0;
        txtPrecioCostoIva.setText(String.format("%.2f", brutoConIva).replace(",", "."));
        
        String porcentajeGanancia=txtPorcentaje.getText();        
        if(porcentajeGanancia.equals("")){
           porcentajeGanancia="0.00" ;
        }

        String valorBruto="",porcentajee="";
        
        bruto=Double.parseDouble(txtPrecioCostoIva.getText().replace(",", "."));
        porcent=Double.parseDouble(porcentajeGanancia.replace(",", "."));  
                    
        valorBruto=(String.format("%.2f", bruto));
        porcentajee=(String.format("%.2f", porcent));
                
        Double precioneto = bruto + (bruto * porcent)/100;

        String precion=(String.format("%.2f", precioneto));
        txtPrecioVentaSinIva.setText(precion.replace(",", "."));
        String precioConIva=(String.format("%.2f", precioneto*porcentajeIva).replace(",", "."));
         txtPrecioVentaConIva.setText(precioConIva);
    }//GEN-LAST:event_txtCostoSinIvaKeyReleased

    private void txtCostoSinIvaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoSinIvaKeyTyped
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
    }//GEN-LAST:event_txtCostoSinIvaKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Double ivaCombo2=0.00;
        String ultimaActualizacion, total_con_iva="0.00";
        int bandera=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
        Calendar fecha = new GregorianCalendar();
         //Obtenemos el valor del año, mes, día, hora, minuto y segundo del sistema.
         //Usando el método get y el parámetro correspondiente.
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        if((mes+1)<10 && (dia>=10)){
            ultimaActualizacion = (dia+"/0"+(mes+1)+"/"+año); 
        }else{
            if(((mes+1)<10 && (dia<10))){
                ultimaActualizacion =("0"+dia+"/0"+(mes+1)+"/"+año);
            }else{
                ultimaActualizacion = (dia+"/"+(mes+1)+"/"+año);
            }
        }
        
        String ivaCombo=comboIva.getSelectedItem().toString();
        if(ivaCombo.equals("19%")){
            ivaCombo="19.00";
            ivaCombo2=19.00;
        }else
            if(ivaCombo.equals("0%")){
                ivaCombo="0.00";
                ivaCombo2=0.00;
            }
        
        String ivaCompra=comboIvaCompra.getSelectedItem().toString();
        if(ivaCompra.equals("19%")){
            ivaCompra="19.00";
        }else
            if(ivaCompra.equals("0%")){
                ivaCompra="0.00";
            }
        
        total_con_iva=txtPrecioVentaConIva.getText().replace(",",".");

        try {
            Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql                 
            Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet r= consulta1.executeQuery("select cod_articulo, referencia from articulo");
            
            while(r.next()){
                if(txtCodigo.getText().equals(r.getString(1))){//si el codigo de la tabla es igual a algun codigo de la base de datos
                    bandera=1;
                }
            }              
        }catch (SQLException ex) {
            System.out.println(ex);
        }            

        txtCodigo.setBackground(new Color(255,255,255));
        txtProducto.setBackground(new Color(255,255,255));
        txtPrecioCostoIva.setBackground(new Color(255,255,255));
        txtPrecioVentaSinIva.setBackground(new Color(255,255,255));
        
        if (bandera==1) {
            JOptionPane.showMessageDialog(null, "Ya existe un articulo con codigo "+txtCodigo.getText(),"Advertencia",JOptionPane.WARNING_MESSAGE);
            txtCodigo.setBackground(Color.yellow);
            txtCodigo.requestFocus();
        }else{
          if (txtCodigo.getText().equals("")) {
              JOptionPane.showMessageDialog(null, "Debe ingresar el codigo del articulo","Advertencia",JOptionPane.WARNING_MESSAGE);
              txtCodigo.setBackground(Color.yellow);
              txtCodigo.requestFocus();
          }else{
            if (txtProducto.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe ingresar la descripcion de articulo","Advertencia",JOptionPane.WARNING_MESSAGE);
                txtProducto.setBackground(Color.yellow);
                txtProducto.requestFocus();
            }else{
                if (txtPrecioCostoIva.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar el precio de costo del articulo","Advertencia",JOptionPane.WARNING_MESSAGE);
                    txtPrecioCostoIva.setBackground(Color.yellow);
                    txtPrecioCostoIva.requestFocus();
                }else{
                    if (txtPrecioVentaSinIva.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Debe inresar el precio NETO del articulo","Advertencia",JOptionPane.WARNING_MESSAGE);
                        txtPrecioVentaSinIva.setBackground(Color.yellow);
                        txtPrecioVentaSinIva.requestFocus();
                    }else{
                        Double bruto=0.0,neto=0.0;
                                String valorBruto="",valorNeto="";
                                try{
                                    bruto=(double)Integer.parseInt(txtPrecioCostoIva.getText().replace(",", "."));
                                    neto=(double)Integer.parseInt(txtPrecioVentaSinIva.getText().replace(",", "."));
                                }catch (NumberFormatException e){
                                    bruto=Double.parseDouble(txtPrecioCostoIva.getText().replace(",", "."));
                                    neto=Double.parseDouble(txtPrecioVentaSinIva.getText().replace(",", "."));
                                }

                                valorBruto=(String.format("%.2f", bruto));
                                valorNeto=(String.format("%.2f", neto));
                                valorBruto=valorBruto.replace(",", ".");
                                valorNeto=valorNeto.replace(",", ".");
                                //GUARDO EL CODIGO DEL PROVEEDOR PARA QUE CUANDO CAMBIE EL NOMBRE DEL PROVEEDOR CAMBIE EN EL PRODUCTO
                                String proveedor=comboProveedor.getSelectedItem().toString();
                                if(proveedor.equals("SELECCIONE PROVEEDOR")){
                                    proveedor="";
                                }
                   
                                
                        try {                          
                            Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                            
                            Statement consulta2 = conn.createStatement();
                            ResultSet res2= consulta2.executeQuery("select * from proveedor where nombre_firma like '"+proveedor+"'");
                            int codigoProveedor=0;
                                    
                            while(res2.next()){
                                codigoProveedor=Integer.parseInt(res2.getString(1));
                            }
                            
                            Statement consulta = conn.createStatement();
                            consulta.executeUpdate("insert into articulo (cod_articulo,referencia,cantidad,proveedor,valor,valor_bruto,estado,marca,ultima_actualizacion,codigo_provisorio,total_con_iva,porcentaje_ganancia,iva,categoria,valor_bruto_sin_iva,ivaCompra,impresion_etiqueta)  values('"+txtCodigo.getText()+"','"+txtProducto.getText()+"','0','"+codigoProveedor+"','"+valorNeto+"','"+valorBruto+"','ACTIVO','"+txtMarca.getText()+"','"+ultimaActualizacion+"','"+txtCodigoProvisorio.getText()+"','"+total_con_iva+"','"+txtPorcentaje.getText()+"','"+ivaCombo+"','"+comboCategoria.getSelectedItem().toString()+"','"+txtCostoSinIva.getText().replace(",",".")+"','"+ivaCompra+"','NO')");          
                        }
                        catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null,"El ARTICULO "+" "+ txtProducto.getText()+" "+"DE STOCK"+" "+txtCostoSinIva.getText()+" "+"YA EXISTE");
                            System.out.println(ex);
                        }

                        Factura_Compra.comboArticulos.addItem(txtProducto.getText());
                        Factura_Compra.comboArticulos.setSelectedItem(txtProducto.getText());
                        Factura_Compra.comboCategoria.setSelectedItem(comboCategoria.getSelectedItem().toString());
                        this.setVisible(false);            
                        
                    }
                }
            }
         }
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtPrecioCostoIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCostoIvaKeyReleased
        //ES PARA QUE A MEDIDA QUE VOY INGRESANDO EL VALOR DE LOS PRODUCTOS HAGA LOS CALCULOS Y SETEO LOS TEXTFIELD DE PRECIO NETO ETC
        
        String ivaCompra=comboIvaCompra.getSelectedItem().toString();
        Double porcentajeIvaCompra=0.0;
        if(ivaCompra.equals("19%")){
            porcentajeIvaCompra=1.19;
        }else
            if(ivaCompra.equals("0%")){
               porcentajeIvaCompra=1.00;
            }
        
        Double brutoConIva=0.0;
        
        if(!txtPrecioCostoIva.getText().equals("")){
            try{                                
                brutoConIva=(double)Integer.parseInt(txtPrecioCostoIva.getText().replace(",", "."));
            }catch (NumberFormatException e){
                brutoConIva=Double.parseDouble(txtPrecioCostoIva.getText().replace(",", "."));
            }
            
        }
        Double brutoSinIva= brutoConIva/porcentajeIvaCompra;
        brutoSinIva=Math.round(brutoSinIva * 100.0)/ 100.0;
        txtCostoSinIva.setText(String.format("%.2f", brutoSinIva).replace(",", "."));
        
        String iva=comboIva.getSelectedItem().toString();
        Double porcentajeIva=0.0;
        if(iva.equals("19%")){
            porcentajeIva=1.19;
        }else
            if(iva.equals("0%")){
                 porcentajeIva=1.00;
            }
        
        if(!porcentaje.isSelected()){
            txtPrecioVentaSinIva.setText("");
            txtPrecioVentaSinIva.setEditable(true);
            txtPorcentaje.setEditable(false);
            txtPorcentaje.setText("");
        }else{
            if(txtPorcentaje.getText().equals("")){
                txtPrecioVentaSinIva.setEditable(true);
                txtPorcentaje.setEditable(true);
                txtPorcentaje.setText("");
                txtPrecioVentaSinIva.setText("");
            }
            else{
                if(!txtPrecioCostoIva.getText().equals("") && !txtPorcentaje.getText().equals("")){
                    Double bruto=0.0,porcent=0.0;
                    String valorBruto="",porcentajee="";
                    try{                                
                        bruto=(double)Integer.parseInt(txtPrecioCostoIva.getText().replace(",", "."));
                        porcent=(double)Integer.parseInt(txtPorcentaje.getText().replace(",", "."));
                    }catch (NumberFormatException e){
                        bruto=Double.parseDouble(txtPrecioCostoIva.getText().replace(",", "."));
                        porcent=Double.parseDouble(txtPorcentaje.getText().replace(",", "."));  
                    }
                    valorBruto=(String.format("%.2f", bruto));
                    porcentajee=(String.format("%.2f", porcent));
                
                    Double precioneto = bruto + (bruto * porcent)/100;

                    String precion=(String.format("%.2f", precioneto));
                    txtPrecioVentaSinIva.setText(precion.replace(",", "."));
                    String precioConIva=(String.format("%.2f", precioneto*porcentajeIva).replace(",", "."));
                    txtPrecioVentaConIva.setText(precioConIva);
                }else{
                    txtPrecioVentaSinIva.setText("");
                    txtPrecioVentaConIva.setText("");
                }
            }
        }
    }//GEN-LAST:event_txtPrecioCostoIvaKeyReleased

    private void txtPrecioCostoIvaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCostoIvaKeyTyped
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
    }//GEN-LAST:event_txtPrecioCostoIvaKeyTyped

    private void txtPrecioVentaSinIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioVentaSinIvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioVentaSinIvaActionPerformed

    private void txtPrecioVentaSinIvaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaSinIvaKeyTyped
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
    }//GEN-LAST:event_txtPrecioVentaSinIvaKeyTyped

    private void txtPorcentajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPorcentajeActionPerformed

    }//GEN-LAST:event_txtPorcentajeActionPerformed

    private void txtPorcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyReleased
        //PARA QUE LOS PRECIOS CAMBIEN SEGUN CAMBIO EL PORCENTAJE DE GANANCIA
       
        String iva=comboIva.getSelectedItem().toString();
        Double porcentajeIva=0.0;
        if(iva.equals("19%")){
            porcentajeIva=1.19;
        }else
            if(iva.equals("0%")){
                  porcentajeIva=1.00;
            }
        
        if(!porcentaje.isSelected()){
            txtPrecioVentaSinIva.setText("");
            txtPrecioVentaSinIva.setEditable(true);
            txtPorcentaje.setEditable(false);
            txtPorcentaje.setText("");
        }else{
            String porcentajeVenta;
            if(txtPorcentaje.getText().equals("")){
                porcentajeVenta="0.00";
            }
            else{
                porcentajeVenta=txtPorcentaje.getText().replace(",",".");
            }
                
            if(txtPrecioCostoIva.getText().equals("")){
                txtPrecioCostoIva.setText("0.00"); 
            }

            Double bruto=0.0,porcent=0.0;
            String valorBruto="",porcentajee="";
            try{                                
                bruto=(double)Integer.parseInt(txtPrecioCostoIva.getText().replace(",", "."));
                porcent=(double)Integer.parseInt(porcentajeVenta.replace(",", "."));
            }catch (NumberFormatException e){
                bruto=Double.parseDouble(txtPrecioCostoIva.getText().replace(",", "."));
                porcent=Double.parseDouble(porcentajeVenta.replace(",", "."));  
            }
            valorBruto=(String.format("%.2f", bruto));
            porcentajee=(String.format("%.2f", porcent));
                
            Double precioneto = bruto + (bruto * porcent)/100;

            String precion=(String.format("%.2f", precioneto));
            txtPrecioVentaSinIva.setText(precion.replace(",", "."));
            String precioConIva=(String.format("%.2f", precioneto*porcentajeIva).replace(",", "."));
            txtPrecioVentaConIva.setText(precioConIva);   
        }

    }//GEN-LAST:event_txtPorcentajeKeyReleased

    private void txtPorcentajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyTyped
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
    }//GEN-LAST:event_txtPorcentajeKeyTyped

    private void porcentajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porcentajeActionPerformed
        if(!porcentaje.isSelected()){
            txtPrecioVentaSinIva.setEditable(true);
            txtPorcentaje.setEditable(false);
            txtPorcentaje.setEnabled(false);
            txtPorcentaje.setText("0");
        }else{
            txtPorcentaje.setEditable(true);
            txtPorcentaje.setEnabled(true);
            txtPorcentaje.requestFocus();
        }
    }//GEN-LAST:event_porcentajeActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();// TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoKeyReleased
       txtProducto.setText (txtProducto.getText().toUpperCase());
    }//GEN-LAST:event_txtProductoKeyReleased

    private void txtMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyReleased
        txtMarca.setText (txtMarca.getText().toUpperCase());
    }//GEN-LAST:event_txtMarcaKeyReleased

    private void txtMarcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaKeyTyped

    private void txtCodigoProvisorioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProvisorioKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();// TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoProvisorioKeyTyped

    private void comboProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProveedorActionPerformed

    }//GEN-LAST:event_comboProveedorActionPerformed

    private void txtPrecioVentaConIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioVentaConIvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioVentaConIvaActionPerformed

    private void txtPrecioVentaConIvaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaConIvaKeyTyped
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
    }//GEN-LAST:event_txtPrecioVentaConIvaKeyTyped

    private void comboIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIvaActionPerformed
       String iva=comboIva.getSelectedItem().toString();
        Double porcentajeIva=0.00;
        if(iva.equals("19%")){
            porcentajeIva=1.19;
        }else
            if(iva.equals("0%")){
                porcentajeIva=1.00;
            }
        if(!txtPrecioVentaSinIva.getText().equals("")){
            Double precioNeto= Double.parseDouble(txtPrecioVentaSinIva.getText().replace(",", "."));
            Double netoConIva= precioNeto*porcentajeIva;
            String precion=(String.format("%.2f", netoConIva));
            txtPrecioVentaConIva.setText(precion.replace(",", "."));
        }
    }//GEN-LAST:event_comboIvaActionPerformed

    private void txtPrecioVentaSinIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaSinIvaKeyReleased
        if(porcentaje.isSelected()){
            String iva=comboIva.getSelectedItem().toString();
            Double porcentajeIva=0.0;
            if(iva.equals("19%")){
                porcentajeIva=1.19;
            }else
                if(iva.equals("0%")){
                    porcentajeIva=1.00;
                }
            String precioNetoCadena=txtPrecioVentaSinIva.getText().replace(",", ".");
            if (precioNetoCadena.equals("")){
                precioNetoCadena="0.00";
            }
            Double precioNeto= Double.parseDouble(precioNetoCadena);//Precio venta sin iva
            Double netoConIva= precioNeto*porcentajeIva;
            String precion=(String.format("%.2f", netoConIva).replace(",", "."));
            txtPrecioVentaConIva.setText(precion.replace(",", "."));

            //SACO EL PORCENTAJE DE LA VENTA
            String porcentajeVenta=txtPorcentaje.getText();
            if (porcentajeVenta.equals("")){
                porcentajeVenta="0.00";
            }
            Double porcentajeV=Double.parseDouble(porcentajeVenta.replace(",","."))/100+1;//Calculo el porcentaje de venta (si es 65 me queda 1,65);

            Double precioCostoIvaCalculado=precioNeto/porcentajeV;
            txtPrecioCostoIva.setText(String.format("%.2f", precioCostoIvaCalculado).replace(",", "."));//SETEO EL COSTO CON IVA


            String ivaCompra=comboIvaCompra.getSelectedItem().toString();
            Double porcentajeIvaCompra=0.0;
            if(ivaCompra.equals("19%")){
                porcentajeIvaCompra=1.19;
            }else
                if(ivaCompra.equals("0%")){
                   porcentajeIvaCompra=1.00;
                }

            Double brutoConIva=0.0;

            if(!txtPrecioCostoIva.getText().equals("")){
                try{                                
                    brutoConIva=(double)Integer.parseInt(txtPrecioCostoIva.getText().replace(",", "."));
                }catch (NumberFormatException e){
                    brutoConIva=Double.parseDouble(txtPrecioCostoIva.getText().replace(",", "."));
                }

            }
            Double brutoSinIva= brutoConIva/porcentajeIvaCompra;
            brutoSinIva=Math.round(brutoSinIva * 100.0)/ 100.0;
            txtCostoSinIva.setText(String.format("%.2f", brutoSinIva).replace(",", "."));
        }
    }//GEN-LAST:event_txtPrecioVentaSinIvaKeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Configurar_Porcentajes_Articulo repor = new Configurar_Porcentajes_Articulo();
        repor.txtRecibe.setText("3");
        repor .setVisible(true);
        repor.setLocationRelativeTo(this);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void comboCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCategoriaActionPerformed

    private void comboIvaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIvaCompraActionPerformed
        String ivaCompra=comboIvaCompra.getSelectedItem().toString();
        Double porcentajeIvaCompra=0.0;
        if(ivaCompra.equals("19%")){
            porcentajeIvaCompra=1.19;
        }else
            if(ivaCompra.equals("0%")){
               porcentajeIvaCompra=1.00;
            }
        
        if(!txtCostoSinIva.getText().equals("")){
            String iva=comboIva.getSelectedItem().toString();
            Double porcentajeIva=0.0;
            if(iva.equals("19%")){
            porcentajeIvaCompra=1.19;
        }else
            if(iva.equals("0%")){
               porcentajeIvaCompra=1.00;
            }
            Double brutoSinIva=0.0,bruto=0.0,porcent=0.0;

            if(!txtCostoSinIva.getText().equals("")){
                try{                                
                    brutoSinIva=(double)Integer.parseInt(txtCostoSinIva.getText().replace(",", "."));
                }catch (NumberFormatException e){
                    brutoSinIva=Double.parseDouble(txtCostoSinIva.getText().replace(",", "."));
                }

            }
            Double brutoConIva= brutoSinIva*porcentajeIvaCompra;
            brutoConIva=Math.round(brutoConIva * 100.0)/ 100.0;
            txtPrecioCostoIva.setText(String.format("%.2f", brutoConIva).replace(",", "."));

            String porcentajeGanancia=txtPorcentaje.getText();        
            if(porcentajeGanancia.equals("")){
               porcentajeGanancia="0.00" ;
            }

            String valorBruto="",porcentajee="";

            bruto=Double.parseDouble(txtPrecioCostoIva.getText().replace(",", "."));
            porcent=Double.parseDouble(porcentajeGanancia.replace(",", "."));  

            valorBruto=(String.format("%.2f", bruto));
            porcentajee=(String.format("%.2f", porcent));

            Double precioneto = bruto + (bruto * porcent)/100;

            String precion=(String.format("%.2f", precioneto));
            txtPrecioVentaSinIva.setText(precion.replace(",", "."));
            String precioConIva=(String.format("%.2f", precioneto*porcentajeIva).replace(",", "."));
            txtPrecioVentaConIva.setText(precioConIva);
        }
    }//GEN-LAST:event_comboIvaCompraActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtCodigoProvisorioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProvisorioKeyReleased
        
    }//GEN-LAST:event_txtCodigoProvisorioKeyReleased

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void txtPrecioVentaConIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaConIvaKeyReleased
        if(porcentaje.isSelected()){
            String iva=comboIva.getSelectedItem().toString();
            Double porcentajeIva=0.0;
            if(iva.equals("19%")){
                porcentajeIva=1.19;
            }else
                if(iva.equals("0%")){
                    porcentajeIva=1.00;
                }
            String precioNetoCadena=txtPrecioVentaConIva.getText().replace(",", ".");//precio venta con iva
            if (precioNetoCadena.equals("")){
                precioNetoCadena="0.00";
            }
            Double precioNetoConIva= Double.parseDouble(precioNetoCadena);//Precio venta con iva
            Double netoSinIva= precioNetoConIva/porcentajeIva;
            String precion=(String.format("%.2f", netoSinIva).replace(",", "."));
            txtPrecioVentaSinIva.setText(precion.replace(",", "."));

            Double precioNeto= Double.parseDouble(txtPrecioVentaSinIva.getText().replace(",","."));//Precio venta sin iva

            //SACO EL PORCENTAJE DE LA VENTA
            String porcentajeVenta=txtPorcentaje.getText();
            if (porcentajeVenta.equals("")){
                porcentajeVenta="0.00";
            }
            Double porcentajeV=Double.parseDouble(porcentajeVenta.replace(",","."))/100+1;//Calculo el porcentaje de venta (si es 65 me queda 1,65);

            Double precioCostoIvaCalculado=precioNeto/porcentajeV;
            txtPrecioCostoIva.setText(String.format("%.2f", precioCostoIvaCalculado).replace(",", "."));//SETEO EL COSTO CON IVA


            String ivaCompra=comboIvaCompra.getSelectedItem().toString();
            Double porcentajeIvaCompra=0.0;
                if(ivaCompra.equals("19%")){
                porcentajeIvaCompra=1.19;
            }else
                if(ivaCompra.equals("0%")){
                   porcentajeIvaCompra=1.00;
                }

            Double brutoConIva=0.0;

            if(!txtPrecioCostoIva.getText().equals("")){
                try{                                
                    brutoConIva=(double)Integer.parseInt(txtPrecioCostoIva.getText().replace(",", "."));
                }catch (NumberFormatException e){
                    brutoConIva=Double.parseDouble(txtPrecioCostoIva.getText().replace(",", "."));
                }

            }
            Double brutoSinIva= brutoConIva/porcentajeIvaCompra;
            brutoSinIva=Math.round(brutoSinIva * 100.0)/ 100.0;
            txtCostoSinIva.setText(String.format("%.2f", brutoSinIva).replace(",", "."));
        }
    }//GEN-LAST:event_txtPrecioVentaConIvaKeyReleased

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
            java.util.logging.Logger.getLogger(Agregar_Articulo_Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agregar_Articulo_Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agregar_Articulo_Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agregar_Articulo_Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Agregar_Articulo_Compras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox comboCategoria;
    public static javax.swing.JComboBox comboIva;
    public static javax.swing.JComboBox comboIvaCompra;
    public static javax.swing.JComboBox comboProveedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton porcentaje;
    public static javax.swing.JTextField recibeCliente;
    public static javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtCodigoProvisorio;
    public static javax.swing.JTextField txtCostoSinIva;
    public static javax.swing.JTextField txtMarca;
    public static javax.swing.JTextField txtPorcentaje;
    public static javax.swing.JTextField txtPrecioCostoIva;
    public static javax.swing.JTextField txtPrecioVentaConIva;
    public static javax.swing.JTextField txtPrecioVentaSinIva;
    public static javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables

    private void deshabilitar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
