
package Vistas;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.lang3.text.WordUtils;
import java.util.Date;

public class Registrar_Empresa extends javax.swing.JDialog {

    public Registrar_Empresa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        CargarCombo();
        MostrarDatos();
        txtimagen.setVisible(false);
        txtCodigo.setVisible(false);
        setLocationRelativeTo(null);
        

    }
    
    public void MostrarDatos(){
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet r= consulta.executeQuery("select * from empresa");
            while(r.next()){
                txtCodigo.setText(r.getString(1));
                txtNombreTitular.setText(r.getString(2));
                txtNombre.setText(r.getString(3));
                txtCuit.setText(r.getString(4));
                comboTipo.setSelectedItem(r.getString(5));        
                txtDireccion.setText(r.getString(6));
                txtLocalidad.setText(r.getString(7));
                txtProvincia.setText(r.getString(8));
                txtTelefono.setText(r.getString(9));
                txtCorreo.setText(r.getString(10));
                txtimagen.setText(r.getString(11)); 
                txtIngresosBrutos.setText(r.getString(13)); 
                txtPuntoVenta.setText(r.getString(14)); 
                try{
                    String dateValue =r.getString(12);
                    java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateValue); 
                    calendario.setDate(date);
                }catch(Exception e){

                }
                
            }
            lblfoto.setIcon(new ImageIcon(txtimagen.getText()));
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Este Usuario Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
        }
    }

    public void CargarCombo(){
        comboTipo.addItem("Seleccionar");
        comboTipo.addItem("Consumidor Final");
        comboTipo.addItem("Exento");
        comboTipo.addItem("Responsable Monotributo");
        comboTipo.addItem("No responsable");
        comboTipo.addItem("Responsable inscripto"); 
        comboTipo.addItem("Responsable no Inscripto");   
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImage5 = new org.edisoncor.gui.panel.PanelImage();
        jTextField7 = new javax.swing.JTextField();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        jLabel8 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtLocalidad = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCuit = new javax.swing.JTextField();
        txtimagen = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Guardar = new javax.swing.JButton();
        lblfoto = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        comboTipo = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNombreTitular = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtIngresosBrutos = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        calendario = new com.toedter.calendar.JDateChooser();
        jLabel21 = new javax.swing.JLabel();
        txtProvincia = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtPuntoVenta = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        b42 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();

        panelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ras.png"))); // NOI18N
        panelImage5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField7.setBackground(new java.awt.Color(204, 204, 204));
        jTextField7.setBorder(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos de la empresa");
        setFocusable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoMenumar22.png"))); // NOI18N
        panelImage2.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 26)); // NOI18N
        jLabel8.setText("(Los datos con * van impresos en las facturas)");
        panelImage2.add(jLabel8);
        jLabel8.setBounds(380, 10, 510, 30);

        txtNombre.setBackground(new java.awt.Color(204, 204, 204));
        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre.setBorder(null);
        panelImage2.add(txtNombre);
        txtNombre.setBounds(320, 100, 267, 40);

        txtDireccion.setBackground(new java.awt.Color(204, 204, 204));
        txtDireccion.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txtDireccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDireccion.setBorder(null);
        panelImage2.add(txtDireccion);
        txtDireccion.setBounds(320, 150, 267, 40);

        txtLocalidad.setBackground(new java.awt.Color(204, 204, 204));
        txtLocalidad.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txtLocalidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLocalidad.setBorder(null);
        panelImage2.add(txtLocalidad);
        txtLocalidad.setBounds(320, 200, 267, 40);

        txtTelefono.setBackground(new java.awt.Color(204, 204, 204));
        txtTelefono.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txtTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefono.setBorder(null);
        panelImage2.add(txtTelefono);
        txtTelefono.setBounds(320, 300, 267, 40);

        txtCuit.setBackground(new java.awt.Color(204, 204, 204));
        txtCuit.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txtCuit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuit.setBorder(null);
        panelImage2.add(txtCuit);
        txtCuit.setBounds(320, 350, 267, 40);

        txtimagen.setBackground(new java.awt.Color(204, 204, 204));
        txtimagen.setBorder(null);
        panelImage2.add(txtimagen);
        txtimagen.setBounds(730, 600, 130, 40);

        txtCorreo.setBackground(new java.awt.Color(204, 204, 204));
        txtCorreo.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txtCorreo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCorreo.setBorder(null);
        panelImage2.add(txtCorreo);
        txtCorreo.setBounds(320, 500, 267, 40);

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel10.setText("Logo");
        panelImage2.add(jLabel10);
        jLabel10.setBounds(660, 250, 60, 30);

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel11.setText("Dirección");
        panelImage2.add(jLabel11);
        jLabel11.setBounds(200, 150, 110, 30);

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel12.setText("Departamento");
        panelImage2.add(jLabel12);
        jLabel12.setBounds(150, 250, 150, 30);

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel13.setText("Teléfono");
        panelImage2.add(jLabel13);
        jLabel13.setBounds(210, 300, 89, 30);

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel14.setText("Contribuyente");
        panelImage2.add(jLabel14);
        jLabel14.setBounds(150, 450, 160, 40);

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel15.setText("Guardar");
        panelImage2.add(jLabel15);
        jLabel15.setBounds(410, 660, 90, 30);

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
        panelImage2.add(Guardar);
        Guardar.setBounds(380, 530, 150, 150);

        lblfoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblfoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelImage2.add(lblfoto);
        lblfoto.setBounds(660, 50, 240, 190);

        jButton2.setBackground(new java.awt.Color(5, 52, 99));
        jButton2.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Seleccionar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        panelImage2.add(jButton2);
        jButton2.setBounds(720, 250, 180, 30);

        comboTipo.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        comboTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoActionPerformed(evt);
            }
        });
        panelImage2.add(comboTipo);
        comboTipo.setBounds(320, 450, 270, 40);

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel16.setText("Ingresos Brutos");
        panelImage2.add(jLabel16);
        jLabel16.setBounds(140, 400, 180, 30);

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        panelImage2.add(txtCodigo);
        txtCodigo.setBounds(910, 30, 50, 40);

        jLabel1.setText("Tamaño recomendado: 128 X 128 px");
        panelImage2.add(jLabel1);
        jLabel1.setBounds(650, 290, 280, 20);

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel17.setText("Nombre del Titular");
        panelImage2.add(jLabel17);
        jLabel17.setBounds(100, 50, 210, 30);

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel18.setText("N° Punto de venta");
        panelImage2.add(jLabel18);
        jLabel18.setBounds(660, 350, 230, 40);

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel9.setText("DATOS DE LA EMPRESA ");
        panelImage2.add(jLabel9);
        jLabel9.setBounds(100, 10, 261, 30);

        txtNombreTitular.setBackground(new java.awt.Color(204, 204, 204));
        txtNombreTitular.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txtNombreTitular.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreTitular.setBorder(null);
        panelImage2.add(txtNombreTitular);
        txtNombreTitular.setBounds(320, 50, 267, 40);

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel19.setText("Nombre Empresa");
        panelImage2.add(jLabel19);
        jLabel19.setBounds(110, 100, 200, 30);

        txtIngresosBrutos.setBackground(new java.awt.Color(204, 204, 204));
        txtIngresosBrutos.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txtIngresosBrutos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIngresosBrutos.setBorder(null);
        panelImage2.add(txtIngresosBrutos);
        txtIngresosBrutos.setBounds(320, 400, 267, 40);

        jLabel20.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel20.setText("NIT");
        panelImage2.add(jLabel20);
        jLabel20.setBounds(250, 350, 60, 30);

        calendario.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        panelImage2.add(calendario);
        calendario.setBounds(650, 500, 250, 40);

        jLabel21.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel21.setText("*");
        panelImage2.add(jLabel21);
        jLabel21.setBounds(910, 400, 20, 40);

        txtProvincia.setBackground(new java.awt.Color(204, 204, 204));
        txtProvincia.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txtProvincia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProvincia.setBorder(null);
        panelImage2.add(txtProvincia);
        txtProvincia.setBounds(320, 250, 267, 40);

        jLabel22.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel22.setText("Ciudad");
        panelImage2.add(jLabel22);
        jLabel22.setBounds(220, 200, 70, 30);

        jLabel23.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel23.setText("Correo");
        panelImage2.add(jLabel23);
        jLabel23.setBounds(230, 500, 90, 40);

        jLabel24.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel24.setText("*");
        panelImage2.add(jLabel24);
        jLabel24.setBounds(600, 60, 20, 20);

        jLabel25.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel25.setText("*");
        panelImage2.add(jLabel25);
        jLabel25.setBounds(600, 110, 20, 20);

        jLabel26.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel26.setText("*");
        panelImage2.add(jLabel26);
        jLabel26.setBounds(600, 210, 20, 20);

        jLabel27.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel27.setText("*");
        panelImage2.add(jLabel27);
        jLabel27.setBounds(600, 310, 20, 20);

        jLabel28.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel28.setText("*");
        panelImage2.add(jLabel28);
        jLabel28.setBounds(600, 360, 20, 30);

        jLabel29.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel29.setText("*");
        panelImage2.add(jLabel29);
        jLabel29.setBounds(600, 410, 20, 30);

        jLabel30.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel30.setText("*");
        panelImage2.add(jLabel30);
        jLabel30.setBounds(600, 460, 20, 30);

        jLabel31.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel31.setText("*");
        panelImage2.add(jLabel31);
        jLabel31.setBounds(600, 160, 20, 20);

        txtPuntoVenta.setBackground(new java.awt.Color(204, 204, 204));
        txtPuntoVenta.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txtPuntoVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPuntoVenta.setBorder(null);
        panelImage2.add(txtPuntoVenta);
        txtPuntoVenta.setBounds(650, 400, 250, 40);

        jLabel32.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel32.setText("Inicio de actividades");
        panelImage2.add(jLabel32);
        jLabel32.setBounds(660, 450, 230, 40);

        jLabel33.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel33.setText("*");
        panelImage2.add(jLabel33);
        jLabel33.setBounds(910, 510, 20, 30);

        b42.setBackground(new java.awt.Color(51, 153, 255));
        b42.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        b42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelar.png"))); // NOI18N
        b42.setBorder(null);
        b42.setBorderPainted(false);
        b42.setContentAreaFilled(false);
        b42.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        panelImage2.add(b42);
        b42.setBounds(580, 530, 107, 150);

        jLabel34.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel34.setText("Cancelar");
        panelImage2.add(jLabel34);
        jLabel34.setBounds(590, 650, 93, 50);

        getContentPane().add(panelImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        String codigo,nombre,nombreTitular,direccion,localidad,telefono,cuit,tipo,correo,logo, provincia,ingresosBrutos,fecha,puntoVenta;
        
        codigo=txtCodigo.getText();
        nombreTitular=txtNombreTitular.getText();
        nombreTitular = WordUtils.capitalize(nombreTitular);
        nombre=txtNombre.getText();
        direccion = txtDireccion.getText();
        localidad = txtLocalidad.getText();
        telefono = txtTelefono.getText();
        cuit = txtCuit.getText();
        tipo = comboTipo.getSelectedItem().toString();
        correo = txtCorreo.getText();
        logo = txtimagen.getText();
        provincia= txtProvincia.getText();
        ingresosBrutos= txtIngresosBrutos.getText();
        puntoVenta= txtPuntoVenta.getText();
                
        int año = calendario.getCalendar().get(Calendar.YEAR);
        int mes = calendario.getCalendar().get(Calendar.MONTH);
        int dia = calendario.getCalendar().get(Calendar.DAY_OF_MONTH);

        if((mes+1)<10 && (dia>=10)){
            fecha = (dia+"/0"+(mes+1)+"/"+año);
        }else{
            if(((mes+1)<10 && (dia<10))){
                fecha = ("0"+dia+"/0"+(mes+1)+"/"+año);
            }else{
                fecha = (dia+"/"+(mes+1)+"/"+año);
            }
        }
        
        int n;
        String contribuyente=comboTipo.getSelectedItem().toString();
        
        txtNombreTitular.setBackground(new Color(204,204,204));
        txtNombre.setBackground(new Color(204,204,204));
        txtDireccion.setBackground(new Color(204,204,204));
        txtLocalidad.setBackground(new Color(204,204,204));
        txtLocalidad.setBackground(new Color(204,204,204));
        txtTelefono.setBackground(new Color(204,204,204));
        txtCuit.setBackground(new Color(204,204,204));
        txtIngresosBrutos.setBackground(new Color(204,204,204));
        txtPuntoVenta.setBackground(new Color(204,204,204));
        
        if (txtNombreTitular.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta ingresar el Nombre y Apellido del titular de la empresa","Advertencia",JOptionPane.WARNING_MESSAGE);
            txtNombreTitular.setBackground(Color.yellow);
            txtNombreTitular.requestFocus();
        }else{
            if (txtNombre.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Falta ingresar el Nombre de la empresa","Advertencia",JOptionPane.WARNING_MESSAGE);
                txtNombre.setBackground(Color.yellow);
                txtNombre.requestFocus();
            }else{
                if (txtDireccion.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Falta ingresar la direccion la empresa","Advertencia",JOptionPane.WARNING_MESSAGE);
                    txtDireccion.setBackground(Color.yellow);
                    txtDireccion.requestFocus();
                }else{
                    if (txtLocalidad.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Falta ingresar la direccion la empresa","Advertencia",JOptionPane.WARNING_MESSAGE);
                        txtLocalidad.setBackground(Color.yellow);
                        txtLocalidad.requestFocus();
                    }else{
                        if (txtTelefono.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Falta ingresar el telefono de la empresa","Advertencia",JOptionPane.WARNING_MESSAGE);
                            txtTelefono.setBackground(Color.yellow);
                            txtTelefono.requestFocus();
                        }else{  
                            if (txtCuit.getText().equals("")) {
                                JOptionPane.showMessageDialog(null, "Falta ingresar el numero de CUIT","Advertencia",JOptionPane.WARNING_MESSAGE);
                                txtCuit.setBackground(Color.yellow);
                                txtCuit.requestFocus();
                            }else{ 
                                if (txtIngresosBrutos.getText().equals("")) {
                                    JOptionPane.showMessageDialog(null, "Falta ingresar elnumero de Ingresos Brutos","Advertencia",JOptionPane.WARNING_MESSAGE);
                                    txtIngresosBrutos.setBackground(Color.yellow);
                                    txtIngresosBrutos.requestFocus();
                                }else{ 
                                    if (txtPuntoVenta.getText().equals("")) {
                                        JOptionPane.showMessageDialog(null, "Falta ingresar el N° de punto de venta","Advertencia",JOptionPane.WARNING_MESSAGE);
                                        txtPuntoVenta.setBackground(Color.yellow);
                                        txtPuntoVenta.requestFocus();
                                    }else{ 
                                        if(contribuyente.equals("Seleccionar")){
                                            JOptionPane.showMessageDialog(null,"Falta seleccionar el contribuyente") ;
                                        }else{
                                          try {
                                            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql


                                            String sql5="UPDATE empresa SET "
                                                + "nombreTitular = ?,"
                                                + "nombre = ?,"
                                                + "direccion = ?,"
                                                + "localidad = ?,"
                                                + "provincia = ?,"
                                                + "telefono = ?,"
                                                + "cuit = ?,"
                                                + "tipo = ?,"
                                                + "correo = ?,"
                                                + "imagen = ?,"
                                                + "fechaInicio = ?,"
                                                + "ingresosBrutos = ?,"
                                                + "punto_de_venta = ?"
                                                + "WHERE cod_empresa = '"+codigo+"'";
                                            PreparedStatement pst4 = conn.prepareStatement(sql5);

                                            pst4.setString(1,nombreTitular);
                                            pst4.setString(2,nombre);
                                            pst4.setString(3,direccion);
                                            pst4.setString(4,localidad);
                                            pst4.setString(5,provincia);
                                            pst4.setString(6,telefono);
                                            pst4.setString(7,cuit);
                                            pst4.setString(8,tipo);
                                            pst4.setString(9,correo);
                                            pst4.setString(10,logo);
                                            pst4.setString(11,fecha);
                                            pst4.setString(12,ingresosBrutos);
                                            pst4.setString(13,puntoVenta);
                                            n= pst4.executeUpdate();

                                            if(n > 0){
                                                JOptionPane.showMessageDialog(null, "Datos Modificados Correctamente");
                                                this.dispose();
                                            }else
                                                JOptionPane.showMessageDialog(null, "Falló al guardar los datos");
                                          }catch (SQLException ex) {
                                            System.out.println(ex);
                                        }
                                      }
                                    
                                    }
                                }
                            }
                        }
                    }
                }     
            }
        }
    }//GEN-LAST:event_GuardarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Arcghivos JPEG(*.JPG;*.JPEG,.*PNG)","jpg","jpeg","png");
        JFileChooser archivo = new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Archivo");
        File ruta = new File("C:\\Users\\Hugo Flores\\Desktop\\Logos");
        archivo.setCurrentDirectory(ruta);
        int ventana = archivo.showOpenDialog(null);
        if(ventana==JFileChooser.APPROVE_OPTION){
            File file = archivo.getSelectedFile();
            txtimagen.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(txtimagen.getText());
            foto = foto.getScaledInstance(120,120,Image.SCALE_DEFAULT);
            lblfoto.setIcon(new ImageIcon(foto));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void comboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoActionPerformed
  
    }//GEN-LAST:event_comboTipoActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void b42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b42ActionPerformed
        this.dispose();
    }//GEN-LAST:event_b42ActionPerformed

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
            java.util.logging.Logger.getLogger(Registrar_Empresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrar_Empresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrar_Empresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrar_Empresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Registrar_Empresa dialog = new Registrar_Empresa(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton Guardar;
    private javax.swing.JButton b42;
    private com.toedter.calendar.JDateChooser calendario;
    public static javax.swing.JComboBox comboTipo;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel lblfoto;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    private org.edisoncor.gui.panel.PanelImage panelImage5;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtCuit;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIngresosBrutos;
    private javax.swing.JTextField txtLocalidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreTitular;
    private javax.swing.JTextField txtProvincia;
    private javax.swing.JTextField txtPuntoVenta;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtimagen;
    // End of variables declaration//GEN-END:variables
}
