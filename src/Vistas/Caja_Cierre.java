
package Vistas;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Caja_Cierre extends javax.swing.JFrame {


    public Caja_Cierre() {
        initComponents();
        setearFecha() ;
        cargarCombos();
        iniciarDatos();
        
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        setTitle("Cierre de caja");
        setLocationRelativeTo(null);
    }
    
    String signo_moneda="";
    
    public void cargarCombos(){
        //CARGO LOS COMBOS CON LA BD

        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            Statement consulta2=conn.createStatement(); // crea una variable que se encargue del código de sql
 
            /***********SELECCIONO VENDEDOR ACTIVO******************/
            ResultSet  res4= consulta.executeQuery("select nombre_usuario from ingreso_usuarios WHERE estado='ACTIVO' ");
            String usuarioActivo= "";
            while(res4.next()){
               usuarioActivo= res4.getString(1);     
            }


            Statement consulta3=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet  res3= consulta3.executeQuery("select * from empleado WHERE estado='ACTIVO' order by nombres");  
            comboVendedor.removeAllItems();
            comboVendedor.addItem("SELECCIONE VENDEDOR");
            int bandera=1;
            String codVendedor="";
            while(res3.next()){
               comboVendedor.addItem(res3.getString(2)+" "+res3.getString(3));
               String vendedor =res3.getString(2)+" "+res3.getString(3);
               codVendedor=res3.getString(1);
               if(vendedor.equals(usuarioActivo)){
                    bandera=1;
               }

            }
            if (bandera==1){
                comboVendedor.setSelectedItem(usuarioActivo);
            }
            txtCodVendedor.setText(codVendedor);
            /************************************/
            
            comboCaja.removeAllItems();
            comboCaja.addItem("SELECCIONE CAJA");
            
            ResultSet  res2= consulta.executeQuery("select * from cajas WHERE estado='ABIERTA' ORDER BY cod_caja");
            int i=0;
            while(res2.next()){
                comboCaja.addItem(res2.getString(2));
                i++;
            }
            if (i==0){
                comboCaja.addItem("NO HAY CAJAS ABIERTAS");
            }
            
            ResultSet r2= consulta.executeQuery("select signo_moneda from  monedas where seleccion_moneda like 'SELECCIONADA'");
            while(r2.next()){
                signo_moneda=r2.getString(1);
            }
                
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
        rSLabelHora2 = new rojeru_san.RSLabelHora();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtFechaApertura = new javax.swing.JTextField();
        Guardar = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        b42 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        txtCodVendedor = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtCodCaja = new javax.swing.JTextField();
        btnBuscarVendedor1 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtCompras = new javax.swing.JTextField();
        txtVentas = new javax.swing.JTextField();
        txtCuentasPagadas = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtCuentasCobradas = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtDevolucionCompra = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtDevolucionVenta = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtIngresoEfectivo = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtCajaFinal = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        txtRetiroEfectivo = new javax.swing.JTextField();
        comboCaja = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtCajaInicial = new javax.swing.JTextField();
        calendario = new com.toedter.calendar.JDateChooser();
        jLabel28 = new javax.swing.JLabel();
        txtHoraApertura = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
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

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("EGRESOS");
        panelImage1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 250, 230, 50));

        jButton3.setBackground(new java.awt.Color(0, 204, 204));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoCajaCierre.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        panelImage1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 140, 90));

        jLabel14.setBackground(new java.awt.Color(239, 255, 239));
        jLabel14.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel14.setText("Vendedor");
        panelImage1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 110, 40));

        comboVendedor.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        comboVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboVendedorActionPerformed(evt);
            }
        });
        panelImage1.add(comboVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 310, 40));

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
        panelImage1.add(btnBuscarVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 20, 50, 40));

        rSLabelHora2.setForeground(new java.awt.Color(93, 116, 163));
        rSLabelHora2.setFont(new java.awt.Font("Roboto Bold", 1, 24)); // NOI18N
        panelImage1.add(rSLabelHora2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 650, 160, -1));

        jLabel15.setBackground(new java.awt.Color(239, 255, 239));
        jLabel15.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel15.setText("Compras en efectivo");
        panelImage1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 200, 40));

        jLabel16.setBackground(new java.awt.Color(239, 255, 239));
        jLabel16.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel16.setText("Caja");
        panelImage1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 60, 40));

        txtFechaApertura.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtFechaApertura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaApertura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtFechaApertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaAperturaActionPerformed(evt);
            }
        });
        txtFechaApertura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaAperturaKeyTyped(evt);
            }
        });
        panelImage1.add(txtFechaApertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 120, 140, 40));

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
        panelImage1.add(Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, -1, -1));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel17.setText("Cerrar Caja");
        panelImage1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 650, 140, -1));

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
        panelImage1.add(b42, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 550, -1, -1));

        jLabel34.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel34.setText("Cancelar");
        panelImage1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 650, -1, -1));
        panelImage1.add(txtCodVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 20, 50, 40));

        jLabel18.setBackground(new java.awt.Color(239, 255, 239));
        jLabel18.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel18.setText("Ventas en efectivo");
        panelImage1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 180, 40));
        panelImage1.add(txtCodCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 70, 50, 40));

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
        panelImage1.add(btnBuscarVendedor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 70, 50, 40));

        jLabel19.setBackground(new java.awt.Color(239, 255, 239));
        jLabel19.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel19.setText("Caja Final");
        panelImage1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 530, 100, 40));

        txtCompras.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtCompras.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCompras.setText("0.00");
        txtCompras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComprasActionPerformed(evt);
            }
        });
        txtCompras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtComprasKeyTyped(evt);
            }
        });
        panelImage1.add(txtCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 300, 230, 40));

        txtVentas.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtVentas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVentas.setText("0.00");
        txtVentas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVentasActionPerformed(evt);
            }
        });
        txtVentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVentasKeyTyped(evt);
            }
        });
        panelImage1.add(txtVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 230, 40));

        txtCuentasPagadas.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtCuentasPagadas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuentasPagadas.setText("0.00");
        txtCuentasPagadas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCuentasPagadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuentasPagadasActionPerformed(evt);
            }
        });
        txtCuentasPagadas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuentasPagadasKeyTyped(evt);
            }
        });
        panelImage1.add(txtCuentasPagadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 400, 230, 40));

        jLabel20.setBackground(new java.awt.Color(239, 255, 239));
        jLabel20.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel20.setText("Cuentas pagadas");
        panelImage1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 400, 170, 40));

        jLabel21.setBackground(new java.awt.Color(239, 255, 239));
        jLabel21.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel21.setText("Devoluciones de compra");
        panelImage1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 240, 40));

        txtCuentasCobradas.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtCuentasCobradas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuentasCobradas.setText("0.00");
        txtCuentasCobradas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCuentasCobradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuentasCobradasActionPerformed(evt);
            }
        });
        txtCuentasCobradas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuentasCobradasKeyTyped(evt);
            }
        });
        panelImage1.add(txtCuentasCobradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, 230, 40));

        jLabel22.setBackground(new java.awt.Color(239, 255, 239));
        jLabel22.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel22.setText("Devoluciones de venta");
        panelImage1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 350, 220, 40));

        txtDevolucionCompra.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtDevolucionCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDevolucionCompra.setText("0.00");
        txtDevolucionCompra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDevolucionCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDevolucionCompraActionPerformed(evt);
            }
        });
        txtDevolucionCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDevolucionCompraKeyTyped(evt);
            }
        });
        panelImage1.add(txtDevolucionCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, 230, 40));

        jLabel23.setBackground(new java.awt.Color(239, 255, 239));
        jLabel23.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel23.setText("Cuentas cobradas");
        panelImage1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 180, 40));

        txtDevolucionVenta.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtDevolucionVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDevolucionVenta.setText("0.00");
        txtDevolucionVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDevolucionVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDevolucionVentaActionPerformed(evt);
            }
        });
        txtDevolucionVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDevolucionVentaKeyTyped(evt);
            }
        });
        panelImage1.add(txtDevolucionVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 350, 230, 40));

        jLabel24.setBackground(new java.awt.Color(239, 255, 239));
        jLabel24.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel24.setText("Ingresos de efectivo");
        panelImage1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 200, 40));

        txtIngresoEfectivo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtIngresoEfectivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIngresoEfectivo.setText("0.00");
        txtIngresoEfectivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtIngresoEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIngresoEfectivoActionPerformed(evt);
            }
        });
        txtIngresoEfectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIngresoEfectivoKeyTyped(evt);
            }
        });
        panelImage1.add(txtIngresoEfectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 230, 40));

        jLabel25.setBackground(new java.awt.Color(239, 255, 239));
        jLabel25.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel25.setText("Retiros de efectivo");
        panelImage1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 180, 40));

        txtCajaFinal.setBackground(new java.awt.Color(0, 204, 0));
        txtCajaFinal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtCajaFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCajaFinal.setText("0.00");
        txtCajaFinal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCajaFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCajaFinalActionPerformed(evt);
            }
        });
        txtCajaFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCajaFinalKeyTyped(evt);
            }
        });
        panelImage1.add(txtCajaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 530, 230, 40));
        panelImage1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 1020, 20));
        panelImage1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 1020, 20));

        jLabel26.setBackground(new java.awt.Color(239, 255, 239));
        jLabel26.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel26.setText("Hora");
        panelImage1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 120, 60, 40));

        txtRetiroEfectivo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtRetiroEfectivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRetiroEfectivo.setText("0.00");
        txtRetiroEfectivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtRetiroEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRetiroEfectivoActionPerformed(evt);
            }
        });
        txtRetiroEfectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRetiroEfectivoKeyTyped(evt);
            }
        });
        panelImage1.add(txtRetiroEfectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 450, 230, 40));

        comboCaja.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        comboCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCajaActionPerformed(evt);
            }
        });
        panelImage1.add(comboCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 70, 310, 40));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 44)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CIERRE DE CAJA");
        panelImage1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 310, 50));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("INGRESOS");
        panelImage1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, 230, 50));

        jLabel27.setBackground(new java.awt.Color(239, 255, 239));
        jLabel27.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel27.setText("Caja Inicial");
        panelImage1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 110, 40));

        txtCajaInicial.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtCajaInicial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCajaInicial.setText("0.00");
        txtCajaInicial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCajaInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCajaInicialActionPerformed(evt);
            }
        });
        txtCajaInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCajaInicialKeyTyped(evt);
            }
        });
        panelImage1.add(txtCajaInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 230, 40));

        calendario.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        panelImage1.add(calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 650, 150, 40));

        jLabel28.setBackground(new java.awt.Color(239, 255, 239));
        jLabel28.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel28.setText("Fecha de apertura");
        panelImage1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, 180, 40));

        txtHoraApertura.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtHoraApertura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraApertura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtHoraApertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoraAperturaActionPerformed(evt);
            }
        });
        txtHoraApertura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHoraAperturaKeyTyped(evt);
            }
        });
        panelImage1.add(txtHoraApertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 160, 40));

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
        jMenu1.add(jSeparator3);

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
        jMenu1.add(jSeparator4);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem4.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem4.setText("Ver cierres de caja");
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 1071, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
            txtFechaApertura.requestFocus();
            conn.close();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }

    }//GEN-LAST:event_comboVendedorActionPerformed
    
    public void iniciarDatos(){
        txtCodCaja.setText("");
        
        rSLabelHora2.setVisible(false);
        calendario.setVisible(false);
        txtCodVendedor.setVisible(false);
        txtCodCaja.setVisible(false);
        rSLabelHora2.setVisible(false);
        txtHoraApertura.setEditable(false);
        txtFechaApertura.setEditable(false);
        txtCajaInicial.setEditable(false);
        txtCajaFinal.setEditable(false);
        txtVentas.setEditable(false);
        txtCompras.setEditable(false);
        txtDevolucionVenta.setEditable(false);
        txtDevolucionCompra.setEditable(false);
        txtCuentasCobradas.setEditable(false);       
        txtCuentasPagadas.setEditable(false);
        txtIngresoEfectivo.setEditable(false);
        txtRetiroEfectivo.setEditable(false);
        
        txtHoraApertura.setText("");      
        txtFechaApertura.setText("");
        txtCajaInicial.setText("0.00");
        txtCajaFinal.setText("0.00");
        txtVentas.setText("0.00");
        txtCompras.setText("0.00");
        txtDevolucionVenta.setText("0.00");
        txtDevolucionCompra.setText("0.00");
        txtCuentasCobradas.setText("0.00");       
        txtCuentasPagadas.setText("0.00");
        txtIngresoEfectivo.setText("0.00");
        txtRetiroEfectivo.setText("0.00");
        
    }
    String codigoCaja="0",fecha="",hora="",monto_apertura="0.00",venta_efectivo="0.00",venta_credito="0.00",venta_mixta="0.00";
    String compra_efectivo="0.00",compra_credito="0.00",compra_mixta="0.00";
    String devolucion_venta="0.00",devolucion_compra="0.00", cuenta_cobrada="0.00", ingreso_efectivo="0.00",retiro_efectivo="0.00",cuenta_pagada="0.00";
    double totalVentaEfectivo=0.00,ingresos=0.00,egresos=0.00,caja_final=0.00,totalCompraEfectivo=0.00;
        
    public void cargarCaja(){
        codigoCaja="0";fecha="";hora="";monto_apertura="0.00";venta_efectivo="0.00";venta_credito="0.00";venta_mixta="0.00";
        compra_efectivo="0.00";compra_credito="0.00";compra_mixta="0.00";
        devolucion_venta="0.00";devolucion_compra="0.00"; cuenta_cobrada="0.00"; ingreso_efectivo="0.00";retiro_efectivo="0.00";
        totalVentaEfectivo=0.00; totalCompraEfectivo=0.00;ingresos=0.00;egresos=0.00;caja_final=0.00;
        String pattern = "###,###,###.00";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String str="";
        try{
            Connection conn2= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta2=conn2.createStatement(); // crea una variable que se encargue del código de sql
                                                                
            //SELECCIONO EL CODIGO DE LA CAJA ABIERTA SELECCIONADA EN EL comboCaja PARA GUARDARLA EN EL PAGO DE EFECTIVO
            ResultSet  res22= consulta2.executeQuery("select cod_caja, fecha,hora,monto_apertura from apertura_caja WHERE estado='ABIERTA' AND nombre_caja='"+comboCaja.getSelectedItem()+"'");
            while(res22.next()){
                codigoCaja=res22.getString(1);
                fecha=res22.getString(2);
                hora=res22.getString(3);
                monto_apertura=res22.getString(4);
                txtCodCaja.setText(codigoCaja);
                txtFechaApertura.setText(fecha);
                txtHoraApertura.setText(hora);
                
                double value = Double.parseDouble(monto_apertura);
                myFormatter = new DecimalFormat(pattern);
                str = myFormatter.format(value);
                if(str.equals(",00")){
                        str="0,00";
                }
                txtCajaInicial.setText(signo_moneda+" "+str);
            }

        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }  
        if(!codigoCaja.equals("0")){
            try{
                Connection conn2= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta2=conn2.createStatement(); // crea una variable que se encargue del código de sql
                
                /*--------------------------------------INICIO VENTAS EN EFECTIVO-----------------------------------------------------------------------*/
                
                //SELECCIONO EL TOTAL DE LAS VENTAS EN EFECTIVO
                venta_efectivo="0.00";
                ResultSet  res22= consulta2.executeQuery("SELECT SUM(total) FROM venta_efectivo WHERE cod_caja='"+codigoCaja+"'");
                while (res22.next()){
                    venta_efectivo=res22.getString(1);
                }
                if(venta_efectivo==null){
                   venta_efectivo="0.00";
                }
                res22.close();
                
                //SELECCIONO EL TOTAL EN EFECTIVO QUE SEPAGO EN LAS VENTAS A CREDITO
                venta_credito="0.00";
                res22= consulta2.executeQuery("SELECT SUM(acuenta) FROM venta_credito WHERE cod_caja='"+codigoCaja+"'");
                while (res22.next()){
                    venta_credito=res22.getString(1);
                }
                if(venta_credito==null){
                   venta_credito="0.00";
                }
                res22.close();
                
                //SELECCIONO EL TOTAL EN EFECTIVO QUE SEPAGO EN LAS VENTAS CON TARJETA Y EFECTIVO
                venta_mixta="0.00";
                res22= consulta2.executeQuery("SELECT SUM(monto_efectivo) FROM venta_mixta WHERE cod_caja='"+codigoCaja+"'");
                while (res22.next()){
                    venta_mixta=res22.getString(1);
                }
                if(venta_mixta==null){
                   venta_mixta="0.00";
                }
                res22.close();
                
                totalVentaEfectivo= Double.parseDouble(venta_efectivo)+Double.parseDouble(venta_credito)+Double.parseDouble(venta_mixta);
                
                double value = totalVentaEfectivo;
                myFormatter = new DecimalFormat(pattern);
                str = myFormatter.format(value);
                if(str.equals(",00")){
                        str="0,00";
                }
                txtVentas.setText(signo_moneda+" "+str);
                
                /*-----------------------------------------FIN VENTAS EN EFECTIVO--------------------------------------------------------------------*/
                
                /*--------------------------------------INICIO COMPRAS EN EFECTIVO-----------------------------------------------------------------------*/
                
                //SELECCIONO EL TOTAL DE LAS COMPRAS EN EFECTIVO
                
                res22= consulta2.executeQuery("SELECT SUM(total) FROM compra_efectivo WHERE cod_caja='"+codigoCaja+"'");
                while (res22.next()){
                    compra_efectivo=res22.getString(1);   
                }
                if(compra_efectivo==null){
                   compra_efectivo="0.00";
                }
                res22.close();
                
                //SELECCIONO EL TOTAL EN EFECTIVO QUE SE PAGO EN LAS COMPRAS A CREDITO
                
                res22= consulta2.executeQuery("SELECT SUM(acuenta) FROM compra_credito WHERE cod_caja='"+codigoCaja+"'");
                while (res22.next()){
                    compra_credito=res22.getString(1);
                }
                if(compra_credito==null){
                    compra_credito="0.00";
                }
                res22.close();
                
                //SELECCIONO EL TOTAL EN EFECTIVO QUE SE PAGO EN LAS COMPRAS CON TARJETA Y EFECTIVO
                
                res22= consulta2.executeQuery("SELECT SUM(monto_efectivo) FROM  compra_mixta WHERE cod_caja='"+codigoCaja+"'");
                while (res22.next()){
                    compra_mixta=res22.getString(1);  
                }
                if(compra_mixta==null){
                   compra_mixta="0.00";
                }
                res22.close();
                
                totalCompraEfectivo= Double.parseDouble(compra_efectivo)+Double.parseDouble(compra_credito)+Double.parseDouble(compra_mixta);
                
                value = totalCompraEfectivo;
                myFormatter = new DecimalFormat(pattern);
                str = myFormatter.format(value);
                if(str.equals(",00")){
                        str="0,00";
                }
                txtCompras.setText(signo_moneda+" "+str);
               
                /*-----------------------------------------FIN COMPRAS EN EFECTIVO--------------------------------------------------------------------*/
               
                /*--------------------------------------INICIO DEVOLUCION VENTAS-----------------------------------------------------------------------*/
                
                //SELECCIONO EL TOTAL DE LAS COMPRAS EN EFECTIVO
                
                res22= consulta2.executeQuery("SELECT SUM(monto_efectivo) FROM devolucion_venta WHERE cod_caja='"+codigoCaja+"'");
                while (res22.next()){
                    devolucion_venta=res22.getString(1);   
                }
                if(devolucion_venta==null){
                   devolucion_venta="0.00";
                }
                res22.close();
                
                value = Double.parseDouble(devolucion_venta);
                myFormatter = new DecimalFormat(pattern);
                str = myFormatter.format(value);
                if(str.equals(",00")){
                        str="0,00";
                }
                txtDevolucionVenta.setText(signo_moneda+" "+str);
                
                /*-----------------------------------------FIN DEVOLUCION VENTAS--------------------------------------------------------------------*/
               
                /*--------------------------------------INICIO DEVOLUCION COMPRAS-----------------------------------------------------------------------*/
                
                //SELECCIONO EL TOTAL DE LAS COMPRAS EN EFECTIVO
                
                res22= consulta2.executeQuery("SELECT SUM(monto_efectivo) FROM devolucion_compra WHERE cod_caja='"+codigoCaja+"'");
                while (res22.next()){
                    devolucion_compra=res22.getString(1);   
                }
                if(devolucion_compra==null){
                   devolucion_compra="0.00";
                }
                res22.close();
                
                value = Double.parseDouble(devolucion_compra);
                myFormatter = new DecimalFormat(pattern);
                str = myFormatter.format(value);
                if(str.equals(",00")){
                        str="0,00";
                }
                txtDevolucionCompra.setText(signo_moneda+" "+str);
                
                /*-----------------------------------------FIN DEVOLUCION COMPRAS--------------------------------------------------------------------*/
               
                /*-----------------------------------------INICIO CUENTAS COBRADAS--------------------------------------------------------------------*/
                
                res22= consulta2.executeQuery("SELECT SUM(pago_abonado) FROM referencia_cuenta_corriente WHERE cod_caja='"+codigoCaja+"'");
                while (res22.next()){
                    cuenta_cobrada=res22.getString(1);   
                }
                if(cuenta_cobrada==null){
                   cuenta_cobrada="0.00";
                }
                res22.close();
                
                value = Double.parseDouble(cuenta_cobrada);
                myFormatter = new DecimalFormat(pattern);
                str = myFormatter.format(value);
                if(str.equals(",00")){
                        str="0,00";
                }
                txtCuentasCobradas.setText(signo_moneda+" "+str);
                
                /*-----------------------------------------FIN CUENTAS COBRADAS--------------------------------------------------------------------*/
                
                /*-----------------------------------------INICIO CUENTAS PAGADAS--------------------------------------------------------------------*/
                
                //NO ESTA IMPLEMENTADO ELMODULO DE CUENTAS PAGADAS
                txtCuentasPagadas.setText(signo_moneda+" "+"0,00");
                //SE PUEDE SIMULAR UTILIZANDO EL RETIRO DE DINERO
                
                /*-----------------------------------------FIN CUENTAS PAGADAS--------------------------------------------------------------------*/
                
                /*-----------------------------------------INICIO INGRESO DE EFECTIVO--------------------------------------------------------------------*/
                
                    res22= consulta2.executeQuery("SELECT SUM(monto_ingresado) FROM ingreso_efectivo WHERE cod_caja='"+codigoCaja+"'");
                    while (res22.next()){
                        ingreso_efectivo=res22.getString(1);   
                    }
                    if(ingreso_efectivo==null){
                       ingreso_efectivo="0.00";
                    }
                    res22.close();

                    value = Double.parseDouble(ingreso_efectivo);
                    myFormatter = new DecimalFormat(pattern);
                    str = myFormatter.format(value);
                    if(str.equals(",00")){
                            str="0,00";
                    }
                    txtIngresoEfectivo.setText(signo_moneda+" "+str);
                
                /*-----------------------------------------FIN INGRESO DE EFECTIVO--------------------------------------------------------------------*/
                
                /*-----------------------------------------INICIO INGRESO DE EFECTIVO--------------------------------------------------------------------*/
                
                    res22= consulta2.executeQuery("SELECT SUM(monto_retirado) FROM retiro_efectivo WHERE cod_caja='"+codigoCaja+"'");
                    while (res22.next()){
                        retiro_efectivo=res22.getString(1);   
                    }
                    if(retiro_efectivo==null){
                       retiro_efectivo="0.00";
                    }
                    res22.close();

                    value = Double.parseDouble(retiro_efectivo);
                    myFormatter = new DecimalFormat(pattern);
                    str = myFormatter.format(value);
                    if(str.equals(",00")){
                            str="0,00";
                    }
                    txtRetiroEfectivo.setText(signo_moneda+" "+str);
                
                /*-----------------------------------------FIN INGRESO DE EFECTIVO--------------------------------------------------------------------*/
                
                /*-----------------------------------------INICIA CAJA FINAL--------------------------------------------------------------------*/
                ingresos= Double.parseDouble(monto_apertura) + totalVentaEfectivo + Double.parseDouble(devolucion_compra) + Double.parseDouble(cuenta_cobrada) + Double.parseDouble(ingreso_efectivo);
                
                egresos= totalCompraEfectivo + Double.parseDouble(devolucion_venta) + Double.parseDouble(retiro_efectivo);
                
                caja_final= ingresos-egresos;
                
                value = caja_final;
                myFormatter = new DecimalFormat(pattern);
                str = myFormatter.format(value);
                if(str.equals(",00")){
                    str="0,00";
                }
                txtCajaFinal.setText(signo_moneda+" "+str);
                
                
                /*-----------------------------------------FIN CAJA FINAL--------------------------------------------------------------------*/
                
                
            }catch(SQLException e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Error en el SQL") ;
            }
        }
    }
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
        String vendedor=comboVendedor.getSelectedItem().toString();
        
        if(vendedor.equals("SELECCIONE VENDEDOR")){
            JOptionPane.showMessageDialog(null,"Debe seleccionar el vendedor que realiza la apertura de caja") ;
        }else{ if(comboCaja.getSelectedItem().equals("SELECCIONE CAJA")){
                    JOptionPane.showMessageDialog(null,"Debe seleccionar la caja que desea cerrar") ;
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

                        int i=consulta.executeUpdate("insert into cierre_caja(cod_caja,nombre_caja,monto_apertura,monto_compras,monto_ventas,monto_ingresos,monto_retiros,monto_saldos_cobrados,monto_saldos_pagados,monto_devolucion_ventas,monto_devolucion_compras,total_caja,vendedor,fecha,hora) VALUES('"+codigoCaja+"','"+comboCaja.getSelectedItem()+"','"+monto_apertura+"','"+totalCompraEfectivo+"','"+totalVentaEfectivo+"','"+ingreso_efectivo+"','"+retiro_efectivo+"','"+cuenta_cobrada+"','"+cuenta_pagada+"','"+devolucion_venta+"','"+devolucion_compra+"','"+caja_final+"','"+txtCodVendedor.getText()+"','"+fecha+"','"+hora+"')");

                        if(i>0){
                            consulta.executeUpdate("UPDATE cajas SET estado='CERRADA' WHERE nombre_caja='"+comboCaja.getSelectedItem().toString()+"'");                 
                            consulta.executeUpdate("UPDATE apertura_caja SET estado='CERRADA' WHERE cod_caja='"+codigoCaja+"'");  
                            
                            /*SI ESTAN TODAS LAS CAJAS CERRADAS MUESTRO EL CARTEL DE CAJAS CERRADAS*/
                            ResultSet r2= consulta.executeQuery("select nombre_caja from cajas where estado='ABIERTA' order by cod_caja");
            
                                int i2=0;
                                while(r2.next()){
                                    if(i2==0){
                                        i2++;
                                    }
                                }
                                r2.close();

                                if(i2>0){
                                    Menu_Principal.labelCajaCerrada.setVisible(false);
                                    Menu_Principal.btnAbrirCaja.setVisible(false);
                                }else{
                                    Menu_Principal.labelCajaCerrada.setVisible(true);
                                    Menu_Principal.btnAbrirCaja.setVisible(true);
                                }

                            //***********************************************************************************************************************************************************************************************************************************************************************************************************
                            //IMPRIMO EL REPORTE
                            Connection miconexion = conexion.ObtenerConexion();
                            Map parametros = new HashMap();
                            parametros.put("codf",codigoCaja);

                            int op=JOptionPane.showConfirmDialog(null, "CAJA CERRADA CORRECTAMENTE ¿DESEA VER EL COMPROBANTE?","SELECCIONE UN OPCION" ,JOptionPane.YES_NO_OPTION);
                            if (op==JOptionPane.YES_NO_OPTION){
                                try {
                                    this.setVisible(false);
                                    String reporte="cierreCaja.jasper";
                                    JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                                    JasperViewer ventanavisor=new JasperViewer(informe,false);
                                    ventanavisor.setTitle("Reporte de cierre de caja");
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
    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        int op=JOptionPane.showConfirmDialog(null, "¿DESEA REALIZAR EL CIERRE DE CAJA?","SELECCIONE UN OPCION" ,JOptionPane.YES_NO_OPTION);
        if (op==JOptionPane.YES_NO_OPTION){
            guardarCaja();
        }

    }//GEN-LAST:event_GuardarActionPerformed

    private void b42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b42ActionPerformed
        this.dispose();
    }//GEN-LAST:event_b42ActionPerformed

    private void txtFechaAperturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaAperturaKeyTyped
        char []p={'1','2','3','4','5','6','7','8','9','0','.',','}; //PARA PODER INGRESAR SOLO NUMEROS O PUNTOS O COMAS
        int b=0;
        for(int i=0;i<=11;i++){
            if (p[i]==evt.getKeyChar()){
                b=1;
            }
        }
        if(b==0){evt.consume();  getToolkit().beep(); }
    }//GEN-LAST:event_txtFechaAperturaKeyTyped

    private void txtFechaAperturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaAperturaActionPerformed
        guardarCaja();
    }//GEN-LAST:event_txtFechaAperturaActionPerformed

    private void btnBuscarVendedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVendedor1ActionPerformed
        enviar_caja_abierta form= new enviar_caja_abierta ();
        form.setVisible(true);
        form.toFront();
        enviar_caja_abierta .txt_recibe.setText("3");
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

    private void txtComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComprasActionPerformed

    private void txtComprasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComprasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComprasKeyTyped

    private void txtVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVentasActionPerformed

    private void txtVentasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVentasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVentasKeyTyped

    private void txtCuentasPagadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuentasPagadasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuentasPagadasActionPerformed

    private void txtCuentasPagadasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuentasPagadasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuentasPagadasKeyTyped

    private void txtCuentasCobradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuentasCobradasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuentasCobradasActionPerformed

    private void txtCuentasCobradasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuentasCobradasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuentasCobradasKeyTyped

    private void txtDevolucionCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDevolucionCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDevolucionCompraActionPerformed

    private void txtDevolucionCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDevolucionCompraKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDevolucionCompraKeyTyped

    private void txtDevolucionVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDevolucionVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDevolucionVentaActionPerformed

    private void txtDevolucionVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDevolucionVentaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDevolucionVentaKeyTyped

    private void txtIngresoEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIngresoEfectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIngresoEfectivoActionPerformed

    private void txtIngresoEfectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngresoEfectivoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIngresoEfectivoKeyTyped

    private void txtCajaFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCajaFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCajaFinalActionPerformed

    private void txtCajaFinalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCajaFinalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCajaFinalKeyTyped

    private void txtRetiroEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRetiroEfectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRetiroEfectivoActionPerformed

    private void txtRetiroEfectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRetiroEfectivoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRetiroEfectivoKeyTyped

    private void comboCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCajaActionPerformed
            cargarCaja();
    }//GEN-LAST:event_comboCajaActionPerformed

    private void txtCajaInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCajaInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCajaInicialActionPerformed

    private void txtCajaInicialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCajaInicialKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCajaInicialKeyTyped

    private void txtHoraAperturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoraAperturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraAperturaActionPerformed

    private void txtHoraAperturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraAperturaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraAperturaKeyTyped

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Consultar_Cierre_Caja form= new Consultar_Cierre_Caja ();
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
            java.util.logging.Logger.getLogger(Caja_Cierre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Cierre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Cierre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Cierre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Cierre().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Guardar;
    private javax.swing.JButton b42;
    private javax.swing.JButton btnBuscarVendedor;
    private javax.swing.JButton btnBuscarVendedor1;
    private com.toedter.calendar.JDateChooser calendario;
    public static javax.swing.JComboBox comboCaja;
    public static javax.swing.JComboBox comboVendedor;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private rojeru_san.RSLabelHora rSLabelHora1;
    private rojeru_san.RSLabelHora rSLabelHora2;
    public static javax.swing.JTextField txtCajaFinal;
    public static javax.swing.JTextField txtCajaInicial;
    public static javax.swing.JTextField txtCodCaja;
    public static javax.swing.JTextField txtCodVendedor;
    public static javax.swing.JTextField txtCompras;
    public static javax.swing.JTextField txtCuentasCobradas;
    public static javax.swing.JTextField txtCuentasPagadas;
    public static javax.swing.JTextField txtDevolucionCompra;
    public static javax.swing.JTextField txtDevolucionVenta;
    public static javax.swing.JTextField txtFechaApertura;
    public static javax.swing.JTextField txtHoraApertura;
    public static javax.swing.JTextField txtIngresoEfectivo;
    public static javax.swing.JTextField txtRetiroEfectivo;
    public static javax.swing.JTextField txtVentas;
    // End of variables declaration//GEN-END:variables
}
