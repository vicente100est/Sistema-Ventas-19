package Vistas;
import com.port.PortController;
import com.port.PortSettings;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
import java.util.Calendar;
import java.sql.*;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import util.AppSettings;
import java.util.regex.*;  

public class Factura_Venta extends javax.swing.JFrame {
    
    //VARIABLES GLOBALES
    String fecha="";
    String cantidadActual="";
    double subtotal=0, subtotalBruto=0, subTotalBrutoArticulo=0;
             String codigoproveedor;
             String nomproveedor;
             String codigoproducto;
             String codigoproducto_e;
             double cant_ex=0.0;
             String precio,precioBruto;
             double nueva_cant =0.0;
             String nomproducto;
             int fila;
             int columna;
             String cod_empleado;
             String nomempleado;
             String codigocliente;
             String nomcliente;
             String NOMBRE;
             String dia, mes, año;
    
    public void cerrar(){
        this.setVisible(false);  
    }
      
    public Factura_Venta() {  

        initComponents(); 
        performRead();
        setLocationRelativeTo(null);

        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        cargarCombos();
        nuevaFactura();
        
        //COLOR CABECERA TABLA 
        JTableHeader th; 
        th = tabla.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        //ANCHO COLUMNAS
        tabla.getColumnModel().getColumn(0).setMaxWidth(90);
        tabla.getColumnModel().getColumn(0).setMinWidth(90);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(90);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(90);
        
        tabla.getColumnModel().getColumn(1).setMaxWidth(120);
        tabla.getColumnModel().getColumn(1).setMinWidth(120);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(120);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(120);
        
        tabla.getColumnModel().getColumn(2).setMaxWidth(450);
        tabla.getColumnModel().getColumn(2).setMinWidth(450);
        tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(450);
        tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(450);
        
        tabla.getColumnModel().getColumn(4).setMaxWidth(0);
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        
        
        tabla.getColumnModel().getColumn(5).setMaxWidth(80);
        tabla.getColumnModel().getColumn(5).setMinWidth(80);
        tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(80);
        tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(80);
        
        tabla.getColumnModel().getColumn(7).setMaxWidth(0);
        tabla.getColumnModel().getColumn(7).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        
        tabla.getColumnModel().getColumn(8).setMaxWidth(0);
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);  
        
        //PARA QUE HAGA FOCO EN EL CODIGO DEL ARTICULO
        txtCodigoArticulo1.requestFocus();
    }
          
    Factura_Venta(Menu_Principal aThis, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    Timer tmrLecturas = null;
    PortController puerto = null;
    AppSettings aSettings = null;
    String weightPattern = null;
    
    private void performRead(){
        try {
            if(tmrLecturas == null){
                
                aSettings = util.Util.getAppSettings();
                PortSettings pt = aSettings.getPortSettings();
                int milliSeconds = (int)(Double.parseDouble(pt.getInterval()) * 1000);
                weightPattern = pt.getStringPattern();
                
                puerto = new PortController(pt);
                puerto.preparePort();
                puerto.openPort();
              
                tmrLecturas = new Timer(milliSeconds, new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        puerto.readString();
                        
                        String weight = puerto.getData();
                        if(weightPattern != null && weightPattern.trim().compareTo("")!=0){
                            Pattern pattern = Pattern.compile(weightPattern);
                            Matcher match = pattern.matcher(weight);
                            if(match.find()){
                                weight = match.group();
                            }
                        }
                        
                        txtPesoBascula.setText(weight);
                        //txtBascula.setText(weight);
                    }
                });

                tmrLecturas.setRepeats(true);
                tmrLecturas.start();
                
                //txtResult.setText("");
                //btnPrueba.setText("Cerrar puerto");
                
                //btnEnviarComando.setEnabled(true);
                //txtComando.setEnabled(true);
                
            }else{
                
                tmrLecturas.stop();
                tmrLecturas = null;
                
                puerto.closePort();
                puerto = null;

                //btnPrueba.setText("Abrir puerto");
                //btnEnviarComando.setEnabled(false);
                //txtComando.setEnabled(false);
                
            }
        } catch (Exception ex) {
           // Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void nuevaFactura(){ 

        comboContribuyente.addItem("Consumidor Final");
        comboContribuyente.addItem("Responsable Monotributo");
        comboContribuyente.addItem("Responsable Inscripto");
        comboContribuyente.addItem("Responsable no Inscripto");   
        comboContribuyente.addItem("No Responsable"); 
        comboContribuyente.addItem("Exento"); 
        
        comboIva.addItem("19%");
        comboIva.addItem("0%");
        
        comboTipoVenta.addItem("PRODUCTOS");
        comboTipoVenta.addItem("SERVICIO");
        comboTipoVenta.setSelectedItem("PRODUCTOS");
        
        try{
            //CARGO EL COMBO IVA SEGUN LA TABLA PORCENTAJES DE LA BD
            Connection conn =conexion.ObtenerConexion();
            Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
            Statement consulta2=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet r= consulta1.executeQuery("select * from porcentajes");

            while(r.next()){
                comboIva.setSelectedItem(r.getString(6)); //porcentaje iva factura por defecto

            }
            r.close();
            ResultSet r2= consulta2.executeQuery("select nombre_caja from cajas where estado='ABIERTA' order by cod_caja");
            
            comboCaja.removeAllItems();
            comboCaja.addItem("CAJA CERRADA");
            int i=0;
            while(r2.next()){
                if(i==0){
                    comboCaja.removeItem("CAJA CERRADA");
                    i++;
                }
                comboCaja.addItem(r2.getString(1)); //NOMBRE DELA CAJA

            }
            r2.close();
        }catch(Exception e){
            
        }
        
        setearFecha();
        checkDescuento.setSelected(false);
        checkIva1.setSelected(true);
        checkDescuentoGeneral.setSelected(false);
        txtDescuentoGeneral.setEnabled(false);
        
        checkIva1.setVisible(false);
        
        fact.setVisible(false);
        txtCodigoEmpleado.setVisible(false);
        txtCodigoCliente.setVisible(false);
        txtIvaFactura.setVisible(false);
        txtTotalBrutoArt.setVisible(false);
        total2.setVisible(false);
        txtIva.setVisible(false);
        txtTipo.setVisible(false);
        txtDescuento.setText("");
        txtDomicilio.setText("");
        txtDescuento.setEditable(false);       
        txtCuit.setText("");
        txtTipo.setText("");
        
        txtCodigoArticulo1.requestFocus();
        
        comboCliente.setSelectedIndex(1);
        comboArticulo.setSelectedIndex(0);
        comboIva.setEnabled(true);
        
        txtIva.setText("0.00");
        txtIva2.setText("0.00");
        cant.setText("");
        fact.setText("");
        sub.setText("0.00");
        total.setText("0.00");
        habilitar();
        
        int fil = tabla.getRowCount();
        int x;
        
        //LIMPIO LA TABLA POR SI CONTIENE ALGUN VALOR
        for (x=fil-1;x>=0;x--) {
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            modelo.removeRow(x);
        }
        try{
            //PARA SETEAR EL NUMERO DE FACTURA
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            ResultSet r= consulta.executeQuery("select cod_factura from  factura order by cod_factura desc");
            r.next();
            fact.setText(r.getString(1));
            int f;         
            f=Integer.parseInt(fact.getText())+1;
            fact.setText(String.valueOf(f));
            r.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la base de datos factura 210") ;
        }
        //txtFacturaNumero.setText("FACTURA N° "+fact.getText());
    }
    
    public static void tipoFactura(){ 
        //SI ES FACTURA A SETEO el combo iva en 19 sino en 0.00
        if(comboContribuyente.getSelectedItem().equals("Responsable Inscripto")){
            comboFactura.setSelectedItem("FACTURA A");
        }else{
            comboFactura.setSelectedItem("FACTURA B");
        }
        if (comboFactura.getSelectedItem().equals("FACTURA B")){
            txtIva.setEnabled(true);
            txtIva.setEditable(false);
            txtIva.setText("0.00");
            txtIva2.setText("0.00");
            comboIva.setEnabled(true);
        }else if (comboFactura.getSelectedItem().equals("FACTURA A")){
            txtIva.setEnabled(true);
            txtIva.setEditable(true);
            txtIva.setText("19.00");
            txtIva2.setText("19.00");
            comboIva.setEnabled(true);
        }

       txtCodigoArticulo1.requestFocus();
    }
    
    public void inabilita(){
        //seteo cuales componentes se vana amostrar y cuales no
        setearFecha();
    
        calendario.setEnabled(false);

        comboVendedor.setEnabled(false);
        comboCliente.setEnabled(false);
        comboArticulo.setEnabled(false);
        
        txtDescuento.setEnabled(false);
        comboPago.setEnabled(false);
        btnBuscarVendedor.setEnabled(false);
        btnBuscarCliente.setEnabled(false);
        btnBuscarArticulo.setEnabled(true);
        cant.setEditable(true);
        btnAgregarArticulo.setEnabled(true);
        btnQuitarArticulo.setEnabled(true);

        btnCancelarFactura.setEnabled(false);
        btnAgregarFactura.setEnabled(false);
        botonAgregarCliente.setEnabled(true);
        btnCancelarFactura.setEnabled(false);
        sub.setEnabled(false);
        txtIva.setEnabled(false);
        total.setEnabled(false);

       
        fact.setVisible(false);
        txtCodigoEmpleado.setVisible(false);
        txtCodigoCliente.setVisible(false);
        //txtFacturaNumero.setText("FACTURA");  
        

    }
    
    public void habilitar(){
        //PARA HABILITAR LOS COMPONENTES QUE USO EN LA FACTURA
        calendario.setEnabled(true); 

        txtDescuento.requestFocus();
        comboVendedor.setEnabled(true);
        comboCliente.setEnabled(true);
        comboArticulo.setEnabled(true);
        comboPago.setEnabled(true);
        
        btnBuscarVendedor.setEnabled(true);
        btnBuscarCliente.setEnabled(true);
        btnBuscarArticulo.setEnabled(true);

        cant.setEditable(true);
        btnAgregarArticulo.setEnabled(true);
        btnQuitarArticulo.setEnabled(true);
        btnCancelarFactura.setEnabled(true);
        botonAgregarCliente.setEnabled(true);  
        btnAgregarFactura.setEnabled(true);
        
        sub.setEnabled(true);
        txtIva.setEnabled(true);
        total.setEnabled(true);
    }
    
     
    public void setearFecha() {   
        //SETEO LA FECHA ACTUAL
        Calendar c2 = new GregorianCalendar();
        calendario.setCalendar(c2);    
    }
    
    public void cargarCombos(){
        //CARGO LOS COMBOS CON LA BD
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet   res= consulta.executeQuery("select * from articulo WHERE estado='ACTIVO' order by referencia");
                
            comboPago.addItem("EFECTIVO");
            comboPago.addItem("EFECTIVO Y TARJETA");
            comboPago.addItem("CREDITO");
            comboPago.addItem("DEPOSITO");
            comboPago.addItem("CHEQUE");
              
            comboFactura.addItem("SELECCIONAR");
            comboFactura.addItem("FACTURA A");
            comboFactura.addItem("FACTURA B");
            //comboFactura.addItem("FACTURA C");//
            
            int i;
            comboArticulo.removeAllItems();
            comboArticulo.addItem("SELECCIONE ARTICULO");
            i=0;
            while(res.next()){
                comboArticulo.addItem(res.getString(2));
                i++;
            }

            
            comboCliente.removeAllItems();
            comboCliente.addItem("SELECCIONE CLIENTE");
            
            i=0;
            res= consulta.executeQuery("select * from cliente WHERE cod_cliente=0");
            while(res.next()){
                comboCliente.addItem(res.getString(2));
                i++;
            }

            i=0;
            res= consulta.executeQuery("select * from cliente WHERE cod_cliente>0 order by nombres");
            while(res.next()){
                comboCliente.addItem(res.getString(2));
                i++;
            }

            /***********SELECCIONO VENDEDOR ACTIVO******************/
            ResultSet  res2= consulta.executeQuery("select nombre_usuario,tipo_usuario from ingreso_usuarios WHERE estado='ACTIVO' ");
            String usuarioActivo= "", tipoUsuario="";
            while(res2.next()){
               usuarioActivo= res2.getString(1);    
               tipoUsuario= res2.getString(2);    
            }
            
            if(tipoUsuario.equals("Facturador")){
                botonAgregarArticulo.setEnabled(false);
                btnEditarArticulo.setEnabled(false);
            }


            Statement consulta2=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet  res3= consulta2.executeQuery("select * from empleado WHERE estado='ACTIVO' order by nombres");  
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
                
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtIva3 = new javax.swing.JTextField();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        QuitarArticulo = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        comboArticulo = new javax.swing.JComboBox();
        btnBuscarArticulo = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cant = new javax.swing.JTextField();
        btnAgregarArticulo = new javax.swing.JButton();
        btnQuitarArticulo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnBuscarCliente = new javax.swing.JButton();
        comboCliente = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        sub = new javax.swing.JTextField();
        txtIva2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        valorIva = new javax.swing.JTextField();
        botonAgregarCliente = new javax.swing.JButton();
        btnAgregarFactura = new javax.swing.JButton();
        btnCancelarFactura = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        botonAgregarArticulo = new javax.swing.JButton();
        comboPago = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        checkDescuento = new javax.swing.JCheckBox();
        txtTipo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtCuit = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        comboFactura = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        txtTotalBrutoArt = new javax.swing.JTextField();
        txtIvaFactura = new javax.swing.JTextField();
        total2 = new javax.swing.JTextField();
        checkIva1 = new javax.swing.JCheckBox();
        txtIva = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtDomicilio = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        comboIva = new javax.swing.JComboBox();
        comboContribuyente = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        checkDescuentoGeneral = new javax.swing.JCheckBox();
        txtDescuentoGeneral = new javax.swing.JTextField();
        txtDtoGral = new javax.swing.JTextField();
        fact = new javax.swing.JTextField();
        txtCodigoEmpleado = new javax.swing.JTextField();
        txtCodigoCliente = new javax.swing.JTextField();
        txtCodigoArticulo1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        btnEditarArticulo = new javax.swing.JButton();
        comboTipoVenta = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        comboCaja = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        comboVendedor = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        btnBuscarVendedor = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        txtPesoBascula = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        calendario = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        itemIvaDefecto = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemBuscarArticulo = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        itemAgregarArticulo = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        itemBuscarCliente = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        itemAgregarCliente = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        itemBuscarVendedor = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();

        txtIva3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIva3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jMenuItem2.setText("jMenuItem2");

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem1.setText(" QUITAR ARTICULO ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        QuitarArticulo.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Registrar venta");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menumar.png"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setBackground(new java.awt.Color(239, 255, 239));
        jLabel9.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel9.setText("Cod Art");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 90, 40));

        comboArticulo.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboArticuloActionPerformed(evt);
            }
        });
        jPanel1.add(comboArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 320, 39));

        btnBuscarArticulo.setBackground(new java.awt.Color(93, 116, 163));
        btnBuscarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoLupaF5.png"))); // NOI18N
        btnBuscarArticulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarArticuloActionPerformed(evt);
            }
        });
        btnBuscarArticulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnBuscarArticuloKeyTyped(evt);
            }
        });
        jPanel1.add(btnBuscarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 50, 39));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel6.setText("Cantidad");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 100, 40));

        cant.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        cant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cant.setAlignmentX(2.0F);
        cant.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        cant.setMargin(new java.awt.Insets(2, 6, 2, 6));
        cant.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                cantComponentMoved(evt);
            }
        });
        cant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantActionPerformed(evt);
            }
        });
        cant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cantKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantKeyTyped(evt);
            }
        });
        jPanel1.add(cant, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 120, 39));

        btnAgregarArticulo.setBackground(new java.awt.Color(93, 116, 163));
        btnAgregarArticulo.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        btnAgregarArticulo.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarArticulo.setText("AGREGAR");
        btnAgregarArticulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarArticuloMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarArticuloMouseExited(evt);
            }
        });
        btnAgregarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarArticuloActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 150, 38));

        btnQuitarArticulo.setBackground(new java.awt.Color(93, 116, 163));
        btnQuitarArticulo.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        btnQuitarArticulo.setForeground(new java.awt.Color(255, 255, 255));
        btnQuitarArticulo.setText("QUITAR");
        btnQuitarArticulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnQuitarArticuloMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnQuitarArticuloMouseExited(evt);
            }
        });
        btnQuitarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarArticuloActionPerformed(evt);
            }
        });
        jPanel1.add(btnQuitarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 150, 38));

        tabla.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CANT", "COD", "DESCRIPCION", "V. UNITARIO", "Total Bruto", "DTO %", "V. TOTAL", "unitario Bruto", "total unitario Bruto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setComponentPopupMenu(QuitarArticulo);
        tabla.setRowHeight(25);
        tabla.setRowMargin(4);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 1000, 130));

        jLabel7.setBackground(new java.awt.Color(239, 255, 239));
        jLabel7.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel7.setText("Cond");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 60, 40));

        btnBuscarCliente.setBackground(new java.awt.Color(93, 116, 163));
        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoLupaF3.png"))); // NOI18N
        btnBuscarCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 50, 39));

        comboCliente.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClienteActionPerformed(evt);
            }
        });
        jPanel1.add(comboCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 320, 39));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel11.setText("% IVA Disc");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 40));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel13.setText("Importe Total");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 150, 40));

        total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 180, 40));

        sub.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sub.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(sub, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 180, 40));

        txtIva2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        txtIva2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIva2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(txtIva2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 60, 40));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel12.setText("Subtotal");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 3, -1, 50));

        valorIva.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        valorIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(valorIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 180, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 450, 400, 160));

        botonAgregarCliente.setBackground(new java.awt.Color(93, 116, 163));
        botonAgregarCliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        botonAgregarCliente.setForeground(new java.awt.Color(255, 255, 255));
        botonAgregarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoAgregarF4.png"))); // NOI18N
        botonAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(botonAgregarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 50, 39));

        btnAgregarFactura.setBackground(new java.awt.Color(51, 153, 255));
        btnAgregarFactura.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAgregarFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardar.png"))); // NOI18N
        btnAgregarFactura.setBorder(null);
        btnAgregarFactura.setBorderPainted(false);
        btnAgregarFactura.setContentAreaFilled(false);
        btnAgregarFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregarFactura.setFocusCycleRoot(true);
        btnAgregarFactura.setFocusable(false);
        btnAgregarFactura.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btnAgregarFactura.setRequestFocusEnabled(false);
        btnAgregarFactura.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btnAgregarFactura.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btnAgregarFactura.setVerifyInputWhenFocusTarget(false);
        btnAgregarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarFacturaActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 440, 210, 130));

        btnCancelarFactura.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelarFactura.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnCancelarFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelar.png"))); // NOI18N
        btnCancelarFactura.setBorder(null);
        btnCancelarFactura.setBorderPainted(false);
        btnCancelarFactura.setContentAreaFilled(false);
        btnCancelarFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCancelarFactura.setFocusCycleRoot(true);
        btnCancelarFactura.setFocusable(false);
        btnCancelarFactura.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarFactura.setRequestFocusEnabled(false);
        btnCancelarFactura.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarFactura.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarFactura.setVerifyInputWhenFocusTarget(false);
        btnCancelarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarFacturaActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelarFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 440, -1, 130));

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel19.setText("Cancelar");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 560, -1, 30));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel17.setText("Facturar");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 90, 30));

        botonAgregarArticulo.setBackground(new java.awt.Color(93, 116, 163));
        botonAgregarArticulo.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        botonAgregarArticulo.setForeground(new java.awt.Color(255, 255, 255));
        botonAgregarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoAgregarF6.png"))); // NOI18N
        botonAgregarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarArticuloActionPerformed(evt);
            }
        });
        jPanel1.add(botonAgregarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, 50, 39));

        comboPago.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPagoActionPerformed(evt);
            }
        });
        jPanel1.add(comboPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 400, 260, 40));

        jLabel10.setBackground(new java.awt.Color(239, 255, 239));
        jLabel10.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel10.setText("Domicilio");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 110, 40));

        txtDescuento.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtDescuento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescuentoActionPerformed(evt);
            }
        });
        jPanel1.add(txtDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 160, 90, 40));

        checkDescuento.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        checkDescuento.setText("%Desc");
        checkDescuento.setOpaque(false);
        checkDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDescuentoActionPerformed(evt);
            }
        });
        jPanel1.add(checkDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, 100, 40));

        txtTipo.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtTipo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoActionPerformed(evt);
            }
        });
        jPanel1.add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, 30, 30));

        jLabel15.setBackground(new java.awt.Color(239, 255, 239));
        jLabel15.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel15.setText("Cliente");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 80, 40));

        txtCuit.setFont(new java.awt.Font("Calibri", 0, 17)); // NOI18N
        txtCuit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuitActionPerformed(evt);
            }
        });
        txtCuit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuitKeyTyped(evt);
            }
        });
        jPanel1.add(txtCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 150, 40));

        jLabel16.setBackground(new java.awt.Color(239, 255, 239));
        jLabel16.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel16.setText("NIT");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 50, 40));

        comboFactura.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFacturaActionPerformed(evt);
            }
        });
        jPanel1.add(comboFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, 290, 40));

        jLabel18.setBackground(new java.awt.Color(239, 255, 239));
        jLabel18.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel18.setText("% iVA");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 160, 80, 40));

        txtTotalBrutoArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalBrutoArtActionPerformed(evt);
            }
        });
        jPanel1.add(txtTotalBrutoArt, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 30, 30));
        jPanel1.add(txtIvaFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 30, 30));
        jPanel1.add(total2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 490, 30, 30));

        checkIva1.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        checkIva1.setText("% Iva");
        checkIva1.setOpaque(false);
        checkIva1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkIva1ActionPerformed(evt);
            }
        });
        jPanel1.add(checkIva1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, 100, 40));

        txtIva.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIvaActionPerformed(evt);
            }
        });
        txtIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIvaKeyReleased(evt);
            }
        });
        jPanel1.add(txtIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 30, 30));

        jLabel21.setBackground(new java.awt.Color(239, 255, 239));
        jLabel21.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel21.setText("Tipo de factura");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 160, 40));

        txtDomicilio.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        txtDomicilio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDomicilioActionPerformed(evt);
            }
        });
        jPanel1.add(txtDomicilio, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 290, 40));

        jLabel22.setBackground(new java.awt.Color(239, 255, 239));
        jLabel22.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel22.setText("Tipo de venta");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 150, 40));

        comboIva.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIvaActionPerformed(evt);
            }
        });
        jPanel1.add(comboIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 160, 110, 40));

        comboContribuyente.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboContribuyente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboContribuyenteActionPerformed(evt);
            }
        });
        jPanel1.add(comboContribuyente, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 220, 40));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        checkDescuentoGeneral.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        checkDescuentoGeneral.setText("%Descuento General");
        checkDescuentoGeneral.setOpaque(false);
        checkDescuentoGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDescuentoGeneralActionPerformed(evt);
            }
        });
        jPanel5.add(checkDescuentoGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 40));

        txtDescuentoGeneral.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtDescuentoGeneral.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescuentoGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescuentoGeneralActionPerformed(evt);
            }
        });
        jPanel5.add(txtDescuentoGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 130, 40));

        txtDtoGral.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        txtDtoGral.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDtoGral.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.add(txtDtoGral, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 170, 40));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 450, 250, 160));

        fact.setEditable(false);
        fact.setBackground(new java.awt.Color(51, 255, 204));
        fact.setFont(new java.awt.Font("Cambria", 1, 30)); // NOI18N
        fact.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fact.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fact.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        fact.setEnabled(false);
        fact.setOpaque(false);
        fact.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        fact.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel1.add(fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 30, 30));

        txtCodigoEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoEmpleadoActionPerformed(evt);
            }
        });
        jPanel1.add(txtCodigoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 30, 30));

        txtCodigoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoClienteActionPerformed(evt);
            }
        });
        jPanel1.add(txtCodigoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 30, 30));

        txtCodigoArticulo1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtCodigoArticulo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoArticulo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoArticulo1ActionPerformed(evt);
            }
        });
        txtCodigoArticulo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoArticulo1KeyReleased(evt);
            }
        });
        jPanel1.add(txtCodigoArticulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 320, 40));

        jLabel23.setBackground(new java.awt.Color(239, 255, 239));
        jLabel23.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel23.setText("Articulo");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 90, 40));

        btnEditarArticulo.setBackground(new java.awt.Color(93, 116, 163));
        btnEditarArticulo.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        btnEditarArticulo.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarArticulo.setText("EDITAR");
        btnEditarArticulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditarArticuloMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditarArticuloMouseExited(evt);
            }
        });
        btnEditarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarArticuloActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 110, 38));

        comboTipoVenta.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboTipoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoVentaActionPerformed(evt);
            }
        });
        jPanel1.add(comboTipoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, 290, 40));

        jLabel24.setBackground(new java.awt.Color(239, 255, 239));
        jLabel24.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel24.setText("Tipo de pago");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 400, 130, 40));

        comboCaja.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCajaActionPerformed(evt);
            }
        });
        jPanel1.add(comboCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, 250, 40));

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1020, 620));

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 50, -1));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 50));

        comboVendedor.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboVendedorActionPerformed(evt);
            }
        });
        jPanel4.add(comboVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 230, 38));

        jLabel14.setBackground(new java.awt.Color(239, 255, 239));
        jLabel14.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel14.setText("Vendedor");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 100, 40));

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
        jPanel4.add(btnBuscarVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 50, 38));

        jLabel25.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Balanza");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 0, 140, 20));

        txtPesoBascula.setBackground(new java.awt.Color(0, 204, 0));
        txtPesoBascula.setFont(new java.awt.Font("Courier New", 1, 30)); // NOI18N
        txtPesoBascula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(txtPesoBascula, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 20, 140, -1));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel8.setText("VENTAS");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 80, 40));

        calendario.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jPanel4.add(calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 150, 40));

        jLabel26.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel26.setText("Fecha");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 80, 40));

        panelImage1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 700));

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setBorderPainted(false);
        jMenuBar1.setPreferredSize(new java.awt.Dimension(23, 31));

        jMenu2.setBackground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("Opciones");
        jMenu2.setAlignmentX(0.1F);
        jMenu2.setAlignmentY(0.1F);
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        itemIvaDefecto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        itemIvaDefecto.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        itemIvaDefecto.setText("Iva por defecto");
        itemIvaDefecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemIvaDefectoActionPerformed(evt);
            }
        });
        jMenu2.add(itemIvaDefecto);
        jMenu2.add(jSeparator1);

        itemBuscarArticulo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        itemBuscarArticulo.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        itemBuscarArticulo.setText("Buscar Articulo");
        itemBuscarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarArticuloActionPerformed(evt);
            }
        });
        jMenu2.add(itemBuscarArticulo);
        jMenu2.add(jSeparator2);

        itemAgregarArticulo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        itemAgregarArticulo.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        itemAgregarArticulo.setText("Agregar articulo");
        itemAgregarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarArticuloActionPerformed(evt);
            }
        });
        jMenu2.add(itemAgregarArticulo);
        jMenu2.add(jSeparator3);

        itemBuscarCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        itemBuscarCliente.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        itemBuscarCliente.setText("Buscar cliente");
        itemBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarClienteActionPerformed(evt);
            }
        });
        jMenu2.add(itemBuscarCliente);
        jMenu2.add(jSeparator5);

        itemAgregarCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        itemAgregarCliente.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        itemAgregarCliente.setText("Agregar cliente");
        itemAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarClienteActionPerformed(evt);
            }
        });
        jMenu2.add(itemAgregarCliente);
        jMenu2.add(jSeparator6);

        itemBuscarVendedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itemBuscarVendedor.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        itemBuscarVendedor.setText("Buscar vendedor");
        itemBuscarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarVendedorActionPerformed(evt);
            }
        });
        jMenu2.add(itemBuscarVendedor);
        jMenu2.add(jSeparator7);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem3.setText("Ancho de la impresión");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboVendedorActionPerformed
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            nomempleado = (String)comboVendedor.getSelectedItem();
            cod_empleado=null;
            
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
            txtCodigoEmpleado.setText(cod_empleado);
            conn.close();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
        //RESTRICCIONES (SINO ESTAN SELECCIONADOS UN CLIENTE Y UN VENDEDOR NO SE HABILITA LA CARGA DE ARTICULOS)
        
        txtCodigoArticulo1.requestFocus();
    }//GEN-LAST:event_comboVendedorActionPerformed

    private void btnBuscarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVendedorActionPerformed
        //BUSCO EL VENDEDOR
        enviar_empleado form= new enviar_empleado ();
        form.setVisible(true);
        form.toFront();
        enviar_empleado.txt_recibeEmpleado.setText("1");
        enviar_empleado.txt_recibeEmpleado.setVisible(false);
        enviar_empleado.enviarEmpleado.setVisible(false);
    }//GEN-LAST:event_btnBuscarVendedorActionPerformed

    private void comboClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClienteActionPerformed
        //SON RESTRICCIONES PARA DEPENDE QUE VALOR ESTE SELECCIONADO SETEE LAS VARIABLES QUE ACTUAN EN LA FACTURA
        comboContribuyente.addItem("SELECCIONAR");
        comboContribuyente.setSelectedIndex(0);
        
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            nomcliente = (String)comboCliente.getSelectedItem();
            codigocliente=null;
            String tipoContribuyente="",domicilioCliente="";
            ResultSet rs = consulta.executeQuery("SELECT cod_cliente, cuit, contribuyente, dirrecion, localidad FROM cliente WHERE nombres = '"+nomcliente+"'" );
            while (rs.next()) {
                codigocliente= rs.getString(1);
                domicilioCliente=(rs.getString(4)+rs.getString(5));
                if(!domicilioCliente.equals("")){
                    txtDomicilio.setText(rs.getString(4)+", "+rs.getString(5));
                }
                txtCuit.setText(rs.getString(2));
                if(rs.getString(3).equals("")){
                    tipoContribuyente="Consumidor Final";
                }else{
                    tipoContribuyente=rs.getString(3);
                }
                
               //txtTipo.setText(rs.getString(3));
            }
            comboContribuyente.setSelectedItem(tipoContribuyente);
                   
            txtCodigoCliente.setText(codigocliente);
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error SQL ");
        }
        
        //RESTRICCIONES (SINO ESTAN SELECCIONADOS UN CLIENTE Y UN VENDEDOR NO SE HABILITA LA CARGA DE ARTICULOS)
        
        if(comboContribuyente.getSelectedItem().equals("Responsable Inscripto")){
                comboFactura.setSelectedItem("FACTURA A");
            }else{
                comboFactura.setSelectedItem("FACTURA B");
            }
            if (comboFactura.getSelectedItem().equals("FACTURA B")){
                txtIva.setEnabled(true);
                txtIva.setEditable(false);
                txtIva.setText("0.00");
                txtIva2.setText("0.00");
                comboIva.setEnabled(true);
            }else if (comboFactura.getSelectedItem().equals("FACTURA A")){
                txtIva.setEnabled(true);
                txtIva.setEditable(true);
                txtIva.setText("19.00");
                txtIva2.setText("19.00");
                comboIva.setEnabled(true);
            }
            comboContribuyente.removeItem("SELECCIONAR");
            txtCodigoArticulo1.requestFocus();
    }//GEN-LAST:event_comboClienteActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        //BUSCO LOS CLIENTES
        enviar_cliente form= new enviar_cliente ();
        form.setVisible(true);
        enviar_cliente.recibe.setText("1");
        form.toFront();
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    public void cargarComboEmpleado(){
        //CARGO EL COMBO VENDEDOR CON LOS DATOS DE LA BD
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            nomempleado = (String)comboVendedor.getSelectedItem();
            cod_empleado=null;
            
            String cadena=comboVendedor.getSelectedItem().toString();
            int i=0;       
            while(cadena.charAt(i)!=' ') { 
               i++;
            }  
            String SubCadenaNombreEmpleado = cadena.substring(0,i);
            String SubCadenaApellidoEmpleado = cadena.substring(i+1,cadena.length());
            ResultSet rs = consulta.executeQuery("SELECT cod_empleado FROM empleado WHERE (nombres = '"+SubCadenaNombreEmpleado+"' AND apellidos = '"+SubCadenaApellidoEmpleado+"')");
            while (rs.next()) {
                cod_empleado= rs.getString(1);
            }
            conn.close();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
    }
    
    private void comboArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboArticuloActionPerformed
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            nomproducto = (String)comboArticulo.getSelectedItem();
            codigoproducto=null;
            //DEPENDE EL NOMBRE DEL ARTICULO QUE SELECCIONO EN LA BD TRAIGO SU CODIGO PARA GUARDARLO EN LA FACTURA
            ResultSet rs = consulta.executeQuery("SELECT cod_articulo FROM articulo WHERE (referencia = '"+comboArticulo.getSelectedItem()+"')");
            while (rs.next()) {
                codigoproducto= rs.getString(1);
            }
            txtCodigoArticulo1.setText(codigoproducto);
            conn.close();  // Cierra la conexión

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
        String balanza=txtPesoBascula.getText();
        
        if(balanza.equals("")||balanza.equals("0")){
            cant.setText("");
        }else{
            double balanzaD=Double.parseDouble(txtPesoBascula.getText().replace(',','.'));
            cant.setText(""+balanzaD);      
        }
        cant.requestFocus();
        
    }//GEN-LAST:event_comboArticuloActionPerformed

    private void btnBuscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarArticuloActionPerformed
        //BUSCO EL ARTICULO
        enviar_producto form= new enviar_producto ();
        form.setVisible(true);
        form.toFront();
        enviar_producto.txt_recibe.setText("1");
        enviar_producto.txt_recibe.setVisible(false);
        enviar_producto.enviarProducto.setVisible(false);
    }//GEN-LAST:event_btnBuscarArticuloActionPerformed

    private void btnAgregarArticuloMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarArticuloMouseEntered

    }//GEN-LAST:event_btnAgregarArticuloMouseEntered

    private void btnAgregarArticuloMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarArticuloMouseExited

    }//GEN-LAST:event_btnAgregarArticuloMouseExited
    public void agregarArticulo(){
        //BOTON AGREGAR ARTICULO A LA FACTURA
        Double variableIva=1.00, precioConVariable=0.00;
 
        if(txtIva2.getText().equals("19.00")){
            variableIva=1.19;
        }else if(txtIva2.getText().equals("0.00")){
                    variableIva=1.00;
                }
        
        
        Double subtotal2=0.0,subTotalBrutoArt=0.0, porcentIva=0.0;
        int pe=0,fil = tabla.getRowCount();
        for (int x=0;x<=fil-1;x++) {
            String aux= (String) (tabla.getValueAt(x,2));
            if(aux.equals(comboArticulo.getSelectedItem()))
            {
                pe=1;
            }
        }
        if(pe==1){
            JOptionPane.showMessageDialog(null,"Este Articulo Ya Existe en la factura, solo debe modificar la cantidad") ;
        }else{
            if (comboArticulo.getSelectedItem().equals("SELECCIONE ARTICULO")){
                JOptionPane.showMessageDialog(null, "Falta elegir el articulo");
            }else{
                if (cant.getText().equals("") || cant.getText().equals("0") || cant.getText().equals("00") || cant.getText().equals("0.00")){
                    JOptionPane.showMessageDialog(null, "Debe Digitar la Cantidad de articulo a Llevar");
                    cant.requestFocus();
                }else{
                    try{
                        Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                        Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                        
                        //PARA ASEGURARME QUE LA CANTIDAD DE ARTICULOS QUE DESEA INGRESAR SE ENCUENTRE DISPONIBLE EN EL STOCK
                        ResultSet   res= consulta.executeQuery("select cantidad from articulo where cod_articulo = '"+txtCodigoArticulo1.getText()+"'");
                        while(res.next()){
                            cant_ex=Double.parseDouble(res.getString(1));
                            break;
                        }
                        res.close();
                        
                    }catch(SQLException e){
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null,"Error en el SQL") ;
                    }
                    
                    //SI LA CANTIDAD INGRESADA ES MAYOR AL STOCK MUESTRA UN CARTEL Y SETEA EL TEXTFIELD CATIDAD CON EL STOCK ACTUAL DISPONIBLE DEL ARTICULO
                    double cantidad=Double.parseDouble(cant.getText().replace(',','.'));
                    if (cant_ex < cantidad)
                    {
                        JOptionPane.showMessageDialog(null, "No existe Cantidad Disponible de Dicho Articulo, La Cantidad Dispomible es: "+cant_ex);
                        cant.setText(""+cant_ex);
                        cant.requestFocus();
                    }
                    else
                    {
                        fila = tabla.getRowCount();
                        columna = tabla.getColumnCount();
                        DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                        modelo.addRow( new Object [] {null,null,null,null,null,null,null,null,null});
                        tabla.setValueAt(cantidad,fila,0);
                        tabla.setValueAt(codigoproducto,fila,1);
                        tabla.setValueAt(nomproducto,fila,2);
                        try{
                            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

                            ResultSet r= consulta.executeQuery("select total_con_iva,valor_bruto from articulo where cod_articulo= '"+txtCodigoArticulo1.getText()+"'");
                            while(r.next()){
                                precio=r.getString(1);
                                precioBruto=r.getString(2);
                            }
                            
                            precioConVariable = Double.parseDouble(precio);  //TRANSFORMO EL PRECIO CON IVA ( SI ES FACTURA A DEBITO EL PORCENTAJE DE IVA) SI ES FACTURA B DIVIDE POR CERO
                            precioConVariable=precioConVariable/variableIva;

                        }catch(SQLException e){
                            JOptionPane.showMessageDialog(null,"Este Articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
                        }

                       tabla.setValueAt(String.format("%.2f", precioConVariable).replace(",", "."),fila,3);
                        tabla.setValueAt(precioBruto,fila,7); //ACA VA EL VALOR BUTO DEL PRODUCTO
                        
                        String desc,desc2, porcentajeDesc;
                        if(txtDescuento.getText().equals("")){
                            desc="0.00";
                            desc2="0.00";
                        }else
                            desc = txtDescuento.getText().replace(".", ",")+".00";
                            desc2 = txtDescuento.getText().replace(".", ",")+".00";
                        tabla.setValueAt(desc,fila,5);
                        
                        porcentajeDesc= desc2;
                        
                        int fila2 = tabla.getRowCount();
                        Double subtotal33=0.0, subtotalConIva=0.0;
                        //A MEDIDA QUE INGRESO LOS ARTICULOS HAGO LOS CALCULOS 
                        for (int x=0;x<=fila2-1;x++) {

                            double porcent=(Double.parseDouble(tabla.getValueAt(x,5).toString().replace(",", "."))* Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")))/100.00;
                            double totalsub=(Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla.getValueAt(x,0).toString());
                            
                            double totalsubArticuloBruto=(Double.parseDouble(tabla.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla.getValueAt(x,0).toString());
                            subtotalBruto=Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla.getValueAt(x,0).toString());
                            
                            subtotal33=totalsub+subtotal33;
                            subtotal2 =subtotal2 +subtotalBruto;
                            subTotalBrutoArt=subTotalBrutoArt + totalsubArticuloBruto;
                            
                            tabla.setValueAt(String.format("%.2f", subtotalBruto ).replace(",", "."),x,4);
                            tabla.setValueAt(String.format("%.2f", totalsub).replace(",", "."),x,6);
                            tabla.setValueAt(String.format("%.2f", subTotalBrutoArt),x,8);                       
                        }
                        
                        porcentIva=Double.parseDouble(txtIva2.getText().replace(",", "."))/100;

                        subtotalConIva= subtotal33*(1+porcentIva);
                        sub.setText(String.format("%.2f",subtotal33).replace(",", "."));
                        valorIva.setText(String.format("%.2f",subtotalConIva-subtotal33).replace(",", "."));
                        total.setText(String.format("%.2f",subtotalConIva).replace(",", "."));
                        total2.setText(""+String.format("%.2f",subtotalConIva));
                        txtTotalBrutoArt.setText(""+String.format("%.2f",subTotalBrutoArt));
                        
                        comboArticulo.setSelectedIndex(0);
                        cant.setText("");
                        txtCodigoArticulo1.requestFocus();
                    }
                }
            }
        }
    }
    private void btnAgregarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarArticuloActionPerformed
                                        
        agregarArticulo();
    }//GEN-LAST:event_btnAgregarArticuloActionPerformed

    private void btnQuitarArticuloMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuitarArticuloMouseEntered

    }//GEN-LAST:event_btnQuitarArticuloMouseEntered

    private void btnQuitarArticuloMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuitarArticuloMouseExited

    }//GEN-LAST:event_btnQuitarArticuloMouseExited

    private void btnQuitarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarArticuloActionPerformed
        //BOTON PARA QUITAR EL ULTIMO ARTICULO INGRESADO
        Double subtotal2=0.0,subTotalBrutoArt=0.0, porcentIva=0.0;
        Double variableIva=0.00;
        if(txtIva2.getText().equals("19.00")){
               variableIva=1.19;
           }else if(txtIva2.getText().equals("0.00")){
                      variableIva=1.00;
                 }
        int Select=tabla.getSelectedRow();
        
        if(Select>=0){
            try
            {


                DefaultTableModel modelo2=(DefaultTableModel)tabla.getModel();    
                modelo2.removeRow(Select);

                int pe=0,fil = tabla.getRowCount();
                double acum=0.0;
                for (int x=0;x<=fil-1;x++) {

                    Double unitarioBruto=Double.parseDouble(tabla.getValueAt(x, 7).toString().replace(",","."))*Double.parseDouble(tabla.getValueAt(x, 0).toString().replace(",","."));
                    acum=acum+unitarioBruto;
                    acum=Math.round(acum * 100.0)/ 100.0;
                    tabla.setValueAt(acum,x,8);
                }

                int fila2 = tabla.getRowCount();
                Double subtotal33=0.0, subtotalConIva=0.0;
                            //A MEDIDA QUE INGRESO LOS ARTICULOS HAGO LOS CALCULOS 
                for (int x=0;x<=fila2-1;x++) {

                    double porcent=(Double.parseDouble(tabla.getValueAt(x,5).toString().replace(",", "."))* Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")))/100.00;
                    double totalsub=(Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla.getValueAt(x,0).toString());

                    double totalsubArticuloBruto=(Double.parseDouble(tabla.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla.getValueAt(x,0).toString());
                    subtotalBruto=Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla.getValueAt(x,0).toString());

                    subtotal33=totalsub+subtotal33;
                    subtotal2 =subtotal2 +subtotalBruto;
                    subTotalBrutoArt=subTotalBrutoArt + totalsubArticuloBruto;

                }

                porcentIva=Double.parseDouble(txtIva2.getText().replace(",", "."))/100;

                subtotalConIva= subtotal33*(1+porcentIva);
                sub.setText(String.format("%.2f",subtotal33).replace(",", "."));
                valorIva.setText(String.format("%.2f",subtotalConIva-subtotal33).replace(",", "."));
                total.setText(String.format("%.2f",subtotalConIva).replace(",", "."));
                total2.setText(""+String.format("%.2f",subtotalConIva));
                txtTotalBrutoArt.setText(""+String.format("%.2f",subTotalBrutoArt));


                DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
               // modelo.removeRow(fila);
                fila-=1;
                int filat=tabla.getRowCount();
                if(filat==0){
                    txtTotalBrutoArt.setText("0.00");
                }else{
                    txtTotalBrutoArt.setText(tabla.getValueAt(fila, 8).toString());
                }
                if (filat==0){
                    comboVendedor.setEnabled(true);
                    comboCliente.setEnabled(true);
                }
            }catch (ArrayIndexOutOfBoundsException e) {        
                //JOptionPane.showMessageDialog(null, "NO HAY PRODUCTOS PARA QUITAR");          
            }
            txtCodigoArticulo1.requestFocus();
        }else{
            int filat=tabla.getRowCount();
            if(filat==0){
                JOptionPane.showMessageDialog(null, "NO HAY PRODUCTOS PARA QUITAR"); 
            }else{
                JOptionPane.showMessageDialog(null, "SELECCIONE LA FILA QUE DESEA QUITAR");   
            }
        }
    }//GEN-LAST:event_btnQuitarArticuloActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void btnBuscarVendedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarVendedorKeyPressed

    }//GEN-LAST:event_btnBuscarVendedorKeyPressed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
           JOptionPane.showMessageDialog(null,"Ingrese el Dato");
        }
    }//GEN-LAST:event_formKeyTyped

    private void btnBuscarArticuloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarArticuloKeyTyped

    }//GEN-LAST:event_btnBuscarArticuloKeyTyped

    private void botonAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarClienteActionPerformed
        //llamo al form de agregar articulo 
        Agregar_Cliente form= new Agregar_Cliente();
        form.setVisible(true);
        Agregar_Cliente.recibeCliente.setText("3");
        form.toFront();
    }//GEN-LAST:event_botonAgregarClienteActionPerformed
    public void modificarArticuloFactura(){
        //CUANDO MODIFICO EL PRECUO UNITARIO O CANTIDAD DEL ARTICULO ADENTRO DE LA TABLA QUE CONTIENE LOS ARTICULOS INGRESADOS (CUANDO LE HAGO CLIC A UN RENGLON DE LA TABLA)
        int Select=tabla.getSelectedRow(), bandera=0;
        Double subtotal2=0.0;   
        subTotalBrutoArticulo=0.0;
        int fila2 = tabla.getRowCount();
        subtotal=0;
        for (int x=0;x<=fila2-1;x++) {
            try{
                Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                ResultSet   res= consulta.executeQuery("select cantidad from articulo where cod_articulo = '"+tabla.getValueAt(Select,1).toString()+"'");
                while(res.next()){
                    cant_ex=Double.parseDouble(res.getString(1));
                    break;
                }
                res.close();
                
            }catch(SQLException e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Error en el SQL") ;
            }

            if (cant_ex < Double.parseDouble(tabla.getValueAt(Select,0).toString()) && bandera==0){
                JOptionPane.showMessageDialog(null, "¡ATENCION! La Cantidad Disponible de Dicho Articulo es "+cant_ex+" unidades");
                bandera=1;
                tabla.setValueAt(cant_ex,Select,0);
            }

            //ESTOS SON LOS CALCULOS PARA CUANDO CAMBIO EL VALOR UNITARIO O A CANTIDAD SE CAMBIEN LOS VALORES TOTALES
            double porcent=(Double.parseDouble(tabla.getValueAt(x,5).toString().replace(",", "."))* Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")))/100.00;
            double totalsub=(Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla.getValueAt(x,0).toString());        
            double totalsubBrutoArt=(Double.parseDouble(tabla.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla.getValueAt(x,0).toString());
           
            subTotalBrutoArticulo=subTotalBrutoArticulo+totalsubBrutoArt;           
            subtotalBruto=Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla.getValueAt(x,0).toString());
            subtotal=totalsub+subtotal;
            subtotal2 =subtotal2 +subtotalBruto;

            tabla.setValueAt(String.format("%.2f", subtotalBruto ).replace(",", "."),x,4);
            tabla.setValueAt(String.format("%.2f", totalsub).replace(",", "."),x,6);
            tabla.setValueAt(String.format("%.2f", totalsubBrutoArt).replace(",", "."),x,8);
        }
        
        int fil = tabla.getRowCount(); //CALCULO DEVUELTA EL ACUMULADO BRUTO
        double acum=0.0;
            for (int x=0;x<=fil-1;x++) {

            Double unitarioBruto=Double.parseDouble(tabla.getValueAt(x, 7).toString().replace(",","."))*Double.parseDouble(tabla.getValueAt(x, 0).toString().replace(",","."));
            acum=acum+unitarioBruto;
            acum=Math.round(acum * 100.0)/ 100.0;
            tabla.setValueAt(acum,x,8);
        }

        Double ivaa=Double.parseDouble(txtIva.getText());
        Double subBruto=subtotal;
        if(comboFactura.getSelectedItem().equals("FACTURA A")){
            subtotal= subtotal +(subtotal*ivaa)/100; //si es factura a le sumo el iva al subtotal
        }else{
            
        }

        sub.setText(String.format("%.2f",subBruto).replace(",", "."));
        valorIva.setText(String.format("%.2f",subtotal-subBruto).replace(",", "."));
        total.setText(String.format("%.2f",subtotal).replace(",", "."));
        total2.setText(""+String.format("%.2f",subtotal).replace(",", "."));
        txtTotalBrutoArt.setText(String.format("%.2f",subTotalBrutoArticulo).replace(",", "."));
        
        comboArticulo.setSelectedIndex(0);
        cant.setText(""); 
        txtCodigoArticulo1.requestFocus();
    }
    private void tablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyReleased
        modificarArticuloFactura();
    }//GEN-LAST:event_tablaKeyReleased

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        modificarArticuloFactura();
    }//GEN-LAST:event_tablaMouseClicked

    private void comboPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPagoActionPerformed
        String pago= comboPago.getSelectedItem().toString();
        if(pago.equals("CREDITO")){
            Agregar_Credito form= new Agregar_Credito ();
            Agregar_Credito.txtRecibe.setText("1");
            Agregar_Credito.txtCodFactura.setText(fact.getText());
            Agregar_Credito.txtTotalCredito.setText(total.getText());
            Agregar_Credito.txtSaldo.setText(total.getText());
            Agregar_Credito.cargarCredito();
            
            try{
                Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql                             

                Statement consulta2=conn.createStatement(); // crea una variable que se encargue del código de sql

                String codigoCaja="0";                                        
                //SELECCIONO EL CODIGO DE LA CAJA ABIERTA SELECCIONADA EN EL comboCaja PARA GUARDARLA EN EL PAGO DE EFECTIVO
                ResultSet  res22= consulta2.executeQuery("select cod_caja from apertura_caja WHERE estado='ABIERTA' AND nombre_caja='"+comboCaja.getSelectedItem()+"'");
                while(res22.next()){
                   codigoCaja=res22.getString(1);
                }
                Agregar_Credito.txtCodCaja.setText(codigoCaja);

            } 
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"ERROR EN LA BASE DE DATOS");
                 System.out.println(ex);
            }
                        
            form.setVisible(true);
            form.toFront();
        }else if(pago.equals("DEPOSITO")){
                Agregar_Deposito form= new Agregar_Deposito ();
                Agregar_Deposito.txtRecibe.setText("1");
                Agregar_Deposito.txtCodFactura.setText(fact.getText());
                Agregar_Deposito.txtTotal.setText(total.getText());
                Agregar_Deposito.cargarDeposito();
                Calendar c2 = new GregorianCalendar();
                Agregar_Deposito.calendario.setCalendar(c2);
                try{
                    Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql                             
                    Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql

                    ResultSet r= consulta1.executeQuery("select fecha from venta_deposito where cod_venta='"+fact.getText()+"'");    

                    while(r.next()){
                        String fechaDeposito=r.getString(1);
                        int año=Integer.parseInt(fechaDeposito.substring(0,4));
                        int mes=Integer.parseInt(fechaDeposito.substring(5,7));
                        int dia=Integer.parseInt(fechaDeposito.substring(8,10));
                        Calendar c3 = new GregorianCalendar(año,mes-1,dia);
                        Agregar_Deposito.calendario.setCalendar(c3);

                    }

                } 
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"ERROR EN LA BASE DE DATOS");
                    System.out.println(ex);
                }
    
                form.setVisible(true);
                form.toFront();
            }else if(pago.equals("EFECTIVO Y TARJETA")){
                    Agregar_TarjetaYEfectivo form= new Agregar_TarjetaYEfectivo  ();
                    form.txtCodFactura.setText(fact.getText());
                    form.txtTotal.setText(total.getText());
                    
                    form.txtEfectivo.setText("0.00");
                    form.txtTarjeta.setText("0.00");
                    form.txtEfectivo.setText("0.00");
                    form.txtSaldo.setText(total.getText());
                    form.txtVuelto.setText("0.00");

                    try{
                        Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql                             
                        Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
                        Statement consulta2=conn.createStatement(); // crea una variable que se encargue del código de sql

                        ResultSet r= consulta1.executeQuery("select * from venta_mixta where cod_venta='"+fact.getText()+"'");    
                        while(r.next()){
                          form.txtEfectivo.setText(r.getString(4));
                          form.txtTarjeta.setText(r.getString(4));
                          form.txtEfectivo.setText(r.getString(5));
                          form.txtSaldo.setText(r.getString(6));
                          form.txtVuelto.setText(r.getString(7));
                        }
                        
                        String codigoCaja="0";                                        
                        //SELECCIONO EL CODIGO DE LA CAJA ABIERTA SELECCIONADA EN EL comboCaja PARA GUARDARLA EN EL PAGO DE EFECTIVO
                        ResultSet  res22= consulta2.executeQuery("select cod_caja from apertura_caja WHERE estado='ABIERTA' AND nombre_caja='"+comboCaja.getSelectedItem()+"'");
                        while(res22.next()){
                            codigoCaja=res22.getString(1);
                         }
                        form.txtCodCaja.setText(codigoCaja);

                    } 
                    catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,"ERROR EN LA BASE DE DATOS");
                        System.out.println(ex);
                    }

                    form.setVisible(true);
                    form.toFront();
                    form.txtEfectivo.requestFocus();
                }else if(pago.equals("CHEQUE")){
                        Agregar_Cheque form= new Agregar_Cheque ();
                        Agregar_Cheque.txtFecha.setText("2");
                        Agregar_Cheque.txtRecibe.setText("1");
                        Agregar_Cheque.txtCodFactura.setText(fact.getText());
                        Agregar_Cheque.txtTotal.setText(total.getText());
                        Agregar_Cheque.cargarDeposito();
                        Calendar c2 = new GregorianCalendar();
                        Agregar_Cheque.calendario.setCalendar(c2);
                        Agregar_Cheque.calendarioVencimiento.setCalendar(c2);
                        try{
                            Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql                             
                            Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql

                            ResultSet r= consulta1.executeQuery("select fecha_emision, fecha_vencimiento from venta_cheque where cod_venta='"+fact.getText()+"'");    

                            while(r.next()){
                                String fechaDeposito=r.getString(1);
                                int año=Integer.parseInt(fechaDeposito.substring(0,4));
                                int mes=Integer.parseInt(fechaDeposito.substring(5,7));
                                int dia=Integer.parseInt(fechaDeposito.substring(8,10));
                                Calendar c3 = new GregorianCalendar(año,mes-1,dia);
                                Agregar_Cheque.calendario.setCalendar(c3);
                                
                                String fechaVencimiento=r.getString(2);
                                int año2=Integer.parseInt(fechaVencimiento.substring(0,4));
                                int mes2=Integer.parseInt(fechaVencimiento.substring(5,7));
                                int dia2=Integer.parseInt(fechaVencimiento.substring(8,10));
                                Calendar c4 = new GregorianCalendar(año2,mes2-1,dia2);  
                                Agregar_Cheque.calendarioVencimiento.setCalendar(c4);
                                Agregar_Cheque.txtTotal.setText(total.getText());

                            }

                        } 
                        catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null,"ERROR EN LA BASE DE DATOS");
                            System.out.println(ex);
                        }

                        form.setVisible(true);
                        form.toFront();
                    }
    }//GEN-LAST:event_comboPagoActionPerformed

    private void cantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantKeyTyped
        char []p={'1','2','3','4','5','6','7','8','9','0','.',','}; //PARA PODER INGRESAR SOLO NUMEROS O PUNTOS O COMAS
        int b=0;
        for(int i=0;i<=11;i++){
            if (p[i]==evt.getKeyChar()){
                b=1;
            }
        }
        if(b==0){evt.consume();  getToolkit().beep(); } 
    }//GEN-LAST:event_cantKeyTyped

    private void cantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantKeyPressed

    }//GEN-LAST:event_cantKeyPressed

    private void cantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantActionPerformed
       agregarArticulo();
    }//GEN-LAST:event_cantActionPerformed

    private void txtCodigoEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoEmpleadoActionPerformed

    private void txtCodigoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoClienteActionPerformed

    public void guardarFacturaEstandar(){
        //BOTON PARA GUARDAR LA FACTURA    
        int banderaFactura=0;
        String tipoFacturaString="",titularEmpresa="", nombreEmpresa="",puntoVenta="",direccionEmpresa="",cuitEmpresa="",contribuyenteEmpresa="",telefonoEmpresa="",ingresosBrutos="",inicioActividades="";
        String nombreCliente="",cuitCliente="",tipoCliente="",direccionCliente="";
        int tipoFacturaInt=0;
        txtCuit.setBackground(new Color(255,255,255));
        try {
            if (comboVendedor.getSelectedItem().equals("SELECCIONE EMPLEADO")) {
                 banderaFactura=1;
                JOptionPane.showMessageDialog(null, "Falta elegir el Empleado","Advertencia",JOptionPane.WARNING_MESSAGE);
            }else {
                if (comboCliente.getSelectedItem().equals("SELECCIONE CLIENTE")) {
                     banderaFactura=1;
                    JOptionPane.showMessageDialog(null, "Falta elegir el Cliente","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else {

                                        if (calendario.getDate() == null) {
                                             banderaFactura=1;
                                            JOptionPane.showMessageDialog(null, "La fecha estaba vacia","Advertencia",JOptionPane.WARNING_MESSAGE);
                                            setearFecha();
                                        }else {
                                            if (fact.getText().equals("")) {
                                                 banderaFactura=1;
                                                JOptionPane.showMessageDialog(null, "Debe Digitar Numero de la Factura","Advertencia",JOptionPane.WARNING_MESSAGE);
                                                fact.requestFocus();
                                            }else {
                                                if ( tabla.getRowCount() <= 0 ) {
                                                     banderaFactura=1;
                                                    JOptionPane.showMessageDialog(null, "No Hay Ningun Producto a Facturar","Advertencia",JOptionPane.ERROR_MESSAGE);
                                                }else {
                                                    try{                                  
                                                        Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                                                        Statement consulta=conn.createStatement();
                                                        Statement consulta1=conn.createStatement();

                                                        Statement consultaEmpresa=conn.createStatement();
                                                        ResultSet resEmpresa= consultaEmpresa.executeQuery("select * from empresa");


                                                        while(resEmpresa.next()){ //TRAIGO LOS DATOS DE LA EMPRESA PARA PASARCELOS A LA FACTURA
                                                            titularEmpresa= (resEmpresa.getString(2));
                                                            nombreEmpresa= (resEmpresa.getString(3));
                                                            cuitEmpresa= (resEmpresa.getString(4));
                                                            direccionEmpresa= (resEmpresa.getString(6)+", "+resEmpresa.getString(7));
                                                            contribuyenteEmpresa= (resEmpresa.getString(2));
                                                            ingresosBrutos= (resEmpresa.getString(13));
                                                            telefonoEmpresa= (resEmpresa.getString(9));
                                                            inicioActividades= (resEmpresa.getString(12));
                                                            puntoVenta= (resEmpresa.getString(14));

                                                        }

                                                        Statement consultaCliente=conn.createStatement();

                                                        Integer codigoCliente = Integer.valueOf(fact.getText()); //EN CODIGO CLIENTE GUARDO EL COD_FACTURA PARA ENLAZAR LA TABLA FACTURA CON LA TABLA FISCAL

                                                        nombreCliente=comboCliente.getSelectedItem().toString(); //NOMBRE DEL CLIENTE

                                                        tipoFacturaString=comboFactura.getSelectedItem().toString(); //TIPO DE FACTURA 1.A,6.B
                                                        if (tipoFacturaString.equals("FACTURA A")){
                                                            tipoFacturaString="A";
                                                            tipoFacturaInt=1;
                                                        }else{
                                                            tipoFacturaString="B";  
                                                            tipoFacturaInt=6;
                                                        }

                                                        cuitCliente=txtCuit.getText(); //CUIT DEL CLIENTE
                                                        tipoCliente=comboContribuyente.getSelectedItem().toString(); //TIPO DE CONTRIBUYENTE CLIENTE

                                                        if(tipoCliente==""){
                                                            tipoCliente="Consumidor Final";
                                                        }
                                                        direccionCliente=txtDomicilio.getText();//DIRECCION CLIENTE


                                                        Integer codigoClientee=0;
                                                        int fil = tabla.getRowCount();
                                                        int col = tabla.getColumnCount();
                                                        
                                                        //OBTENGO LA FECHA DE LA FACTURA PARA GUARDARLA EN LA BD
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
                                                        
                                                        //SEGUN EL COMBO SELECCIONADO SETEO LAS VARIABLES QUE ME VAN A CALCULAR EL IVA
                                                        Double variableIva=0.00, porcentIva=0.0,porcentajeIvaFactura=0.0;
                                                        int tipoPorcentajeIva=0; 
                                                        String ivaFact="";
                                                        
                                                        if(comboIva.getSelectedItem().toString().equals("19%")){
                                                            variableIva=1.19;
                                                            porcentIva=0.19;
                                                            ivaFact="19.00";
                                                            porcentajeIvaFactura=19.0;
                                                            tipoPorcentajeIva=5; //CODIGO EN LA TABLA DE EXCEL DEL IVA 
                                                        }else if(comboIva.getSelectedItem().toString().equals("0%")){
                                                                    variableIva=1.00;
                                                                    porcentIva=0.00;
                                                                    ivaFact="0.00";
                                                                    porcentajeIvaFactura=0.0;
                                                                    tipoPorcentajeIva=3;//CODIGO EN LA TABLA DE EXCEL DEL IVA 0
                                                                }
                                                        
                                                        Double subTotalNeto=Double.parseDouble(sub.getText());
                                                        String subTotalNetoString="";

                                                        if (comboFactura.getSelectedItem().toString().equals("FACTURA A")){ //CALCULO EL SUBTOTAL NETO,SI ES FACT A LO DEJO IGUAL, SI ES FACT B LE RESTO EL IVA
                                                            subTotalNeto=subTotalNeto;
                                                            subTotalNeto=Math.round(subTotalNeto * 100.0)/ 100.0;
                                                        }else if (comboFactura.getSelectedItem().toString().equals("FACTURA B")){
                                                            subTotalNeto=subTotalNeto/variableIva;
                                                            subTotalNeto=Math.round(subTotalNeto * 100.0)/ 100.0;
                                                        }
                                                        subTotalNetoString=String.format("%.2f", subTotalNeto).replace(",", ".");
                                                        int x,y;

                                                        String ivaFactura=txtIva2.getText(); //GUARDO EL IVA QUE TIENE LA FACTURA
                                                        if(ivaFactura.equals("0.00") && comboFactura.getSelectedItem().toString().equals("FACTURA B")){
                                                            ivaFactura=ivaFact;
                                                        }

                                                        Double montoIvaa=Double.parseDouble(txtIva2.getText());
                                                        montoIvaa=Math.round(montoIvaa * 100.0)/ 100.0;

                                                        Double montoTotal=Double.parseDouble(total.getText());
                                                        montoTotal=Math.round(montoTotal * 100.0)/ 100.0;

                                                        Double ivaCalculado=0.0;
                                                        if (comboFactura.getSelectedItem().toString().equals("FACTURA A")){
                                                            ivaCalculado=subTotalNeto*porcentIva;
                                                        }else if (comboFactura.getSelectedItem().toString().equals("FACTURA B")){
                                                            ivaCalculado=montoTotal-subTotalNeto;
                                                        }

                                                        ivaCalculado=Math.round(ivaCalculado * 100.0)/ 100.0;

                                                        //INSERTO LOS DATOS EN LA TABLA FACTURA DE LA BD
                                                        consulta.executeUpdate("insert into factura (cod_factura, fecha,cod_cliente,cod_empleado,tipo_pago,condicion,iva,tipo_factura,total_con_iva,total_sin_iva,ivaDiscriminado,estado_afip) values('"+fact.getText()+"','"+fecha+"','"+txtCodigoCliente.getText()+"','"+txtCodigoEmpleado.getText()+"','"+comboPago.getSelectedItem()+"','ACTIVA','"+ivaFactura+"','"+tipoFacturaString+"','"+total2.getText().replace(",",".")+"','"+subTotalNetoString+"','"+txtIva2.getText().replace(",",".")+"','ESTANDAR')");
                                                        //INSERTO CADA ARTICULO INDIVIDUAL EN LA TABLA REFERENCIA_FACTURA DE LA BD
                                                        for (x=0;x<=fil-1;x++) {
                                                            consulta1.executeUpdate("insert into referencia_factura (cod_factura,codigo_producto,valor_unitario,valor_total,referencia,cantidad,Total,unitarioBruto,totalBruto,descuento) values('"+fact.getText()+"','"+tabla.getValueAt(x,1)+"'  ,'"+tabla.getValueAt(x,3)+"','"+tabla.getValueAt(x,6).toString().replace(",",".")+"','"+tabla.getValueAt(x,2)+"','"+tabla.getValueAt(x,0)+"','"+total2.getText().replace(",",".")+"','"+tabla.getValueAt(x,7)+"','"+txtTotalBrutoArt.getText().replace(",",".")+"','"+tabla.getValueAt(x,5)+"')");
                                                        }
                                                        
                                                        String tipoPago=comboPago.getSelectedItem().toString();
                                                        if(tipoPago.equals("EFECTIVO")){
                                                            String codigoCaja="";
                                                            try{
                                                                Connection conn2= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                                                                Statement consulta2=conn2.createStatement(); // crea una variable que se encargue del código de sql
                                                                
                                                                //SELECCIONO EL CODIGO DE LA CAJA ABIERTA SELECCIONADA EN EL comboCaja PARA GUARDARLA EN EL PAGO DE EFECTIVO
                                                                ResultSet  res22= consulta2.executeQuery("select cod_caja from apertura_caja WHERE estado='ABIERTA' AND nombre_caja='"+comboCaja.getSelectedItem()+"'");
                                                                while(res22.next()){
                                                                    codigoCaja=res22.getString(1);

                                                                }
                                                                
                                                            }catch(SQLException e){
                                                                System.out.println(e);
                                                                JOptionPane.showMessageDialog(null,"Error en el SQL 22") ;
                                                            }
                                                            
                                                            //BORRO LA FORMA DE PAGO EN EL CASO QUE EN LA MISMA FACTURA SELECCIONEN PRIMERO UNA FORMA DE PAGO Y LUGO OTRA(QUEDA REGISTRADA LA ULTIMA)
                                                            int xx=0;
                                                            xx=consulta.executeUpdate("INSERT INTO venta_efectivo (cod_venta,subtotal,iva,total,cod_caja) VALUES ('"+fact.getText()+"','"+sub.getText().replace(',','.')+"','"+valorIva.getText().replace(',','.')+"','"+total2.getText().replace(",",".")+"','"+codigoCaja+"')");
                                                            if(xx>0){
                                                                consulta.executeUpdate("DELETE FROM venta_mixta WHERE cod_venta='"+fact.getText()+"'");
                                                                consulta.executeUpdate("DELETE FROM venta_credito WHERE cod_venta='"+fact.getText()+"'");
                                                                consulta.executeUpdate("DELETE FROM venta_deposito WHERE cod_venta='"+fact.getText()+"'");
                                                                consulta.executeUpdate("DELETE FROM venta_cheque WHERE cod_venta='"+fact.getText()+"'"); 
                                                            }
                                                        }else if (tipoPago.equals("CREDITO")){
                                                                   //ESTA PARTE ES PARA AGREGAR LOS DATOS AL MODULO CUENTA CREDITO VENTA, PARA CONTROLAR LOS SALDOS DEL CLIENTE
                                                                    Statement consulta3=conn.createStatement(); //BUSCO EL CLIENTE EN LA CUENTA CORRIENTE Y SI NO ESTA LO AGREGO
                                                                    Statement consulta4=conn.createStatement(); //BUSCO EL CLIENTE EN LA CUENTA CORRIENTE Y SI NO ESTA LO AGREGO

                                                                    ResultSet r3= consulta3.executeQuery("select * from cuenta_credito_venta WHERE cod_cliente='"+txtCodigoCliente.getText()+"'");
                                                                    int banderaClienteCorriente=0;
                                                                    while(r3.next()){
                                                                        banderaClienteCorriente=1;
                                                                    }
                                                                    ResultSet r4= consulta4.executeQuery("select acuenta,saldo_restante from venta_credito WHERE cod_venta='"+fact.getText()+"'");
                                                                    String pago="0.00",saldoRestante="0.00",codCuentaCredito="0";
                                                                    while(r4.next()){
                                                                        pago=r4.getString(1);
                                                                        saldoRestante=r4.getString(2);//saldo restante de la venta a credito
                                                                    }

                                                                    //SIEL CLIENTE NO ESTA EN CUENTA CREDITO VENTA LO AGREGO
                                                                    if(banderaClienteCorriente==0){
                                                                         consulta3.executeUpdate("insert into cuenta_credito_venta (cod_cliente,ultimo_pago,fecha_ultimo_pago,total_pago,saldo_restante) values('"+txtCodigoCliente.getText()+"','"+pago+"','"+fecha+"','"+pago+"','"+saldoRestante+"')");

                                                                        //BUSCO EL CODIGO DE LA CUENTA CREDITO VENTA
                                                                         ResultSet r5= consulta4.executeQuery("select cod_cuenta from cuenta_credito_venta WHERE cod_cliente='"+txtCodigoCliente.getText()+"'");

                                                                         while(r5.next()){
                                                                             codCuentaCredito=r5.getString(1);
                                                                         }
                                                                    }else{

                                                                        //BUSCO EL CODIGO DE LA CUENTA CREDITO VENTA
                                                                        ResultSet r5= consulta4.executeQuery("select cod_cuenta from cuenta_credito_venta WHERE cod_cliente='"+txtCodigoCliente.getText()+"'");

                                                                        while(r5.next()){
                                                                            codCuentaCredito=r5.getString(1);
                                                                        }
                                                                        //ACTUALIZO LOS DATOS DE LA CUENTA CREDITO VENTA
                                                                        consulta3.executeUpdate("UPDATE cuenta_credito_venta SET ultimo_pago='"+pago+"', fecha_ultimo_pago='"+fecha+"', total_pago= total_pago+'"+pago+"', saldo_restante=saldo_restante+'"+saldoRestante+"' WHERE cod_cuenta='"+codCuentaCredito+"' ");

                                                                    }
                                                                    ResultSet r6= consulta4.executeQuery("select total_pago, total_pago,saldo_restante from cuenta_credito_venta WHERE cod_cuenta='"+codCuentaCredito+"'");
                                                                    String pagoAcumulado="", saldoRestanteCuentaCredito="0.0", totalPagoCuentaCredito="0.0";
                                                                    while(r6.next()){
                                                                       pagoAcumulado=r6.getString(1);
                                                                       totalPagoCuentaCredito=r6.getString(2);
                                                                       saldoRestanteCuentaCredito=r6.getString(3);
                                                                    }

                                                                    consulta4.executeUpdate("INSERT INTO referencia_cuenta_credito_venta (cod_cuenta,fecha,pago_abonado,total_pago,saldo_restante,total_factura,cod_factura)VALUES('"+codCuentaCredito+"','"+fecha+"','"+pago+"','"+total.getText().replace(",",".")+"','"+(Double.parseDouble(total.getText().replace(",",".")) - Double.parseDouble(pago))+"','"+total.getText().replace(",",".")+"','"+fact.getText()+"')");

                                                                }
     
                                                                Seleccionar_Tamano_Factura form= new Seleccionar_Tamano_Factura ();
                                                                form.setVisible(true);
                                                                form.toFront();
                                                                Seleccionar_Tamano_Factura.fact.setText(fact.getText());
                                                                Seleccionar_Tamano_Factura.txtRecibe.setText("1");
                                                                this.setVisible(false);

                                                                inabilita();


                                                                for (x=0;x<=fil-1;x++) {
                                                                    String aux=(tabla.getValueAt(x,0)).toString();

                                                                    ResultSet   res= consulta.executeQuery("select cantidad from articulo where cod_articulo='"+tabla.getValueAt(x,1)+"'");
                                                                    while(res.next()){
                                                                        cant_ex=Double.parseDouble(res.getString(1));
                                                                    }
                                                                    //DEBITO LA CANTIDAD AGREGADA DECADA ARTICULO EN EL STOCK DEL ARTICULO EN LA BD
                                                                    nueva_cant = cant_ex - Double.parseDouble(aux);
                                                                    consulta.executeUpdate("UPDATE articulo SET cantidad='"+nueva_cant+"' WHERE cod_articulo='"+tabla.getValueAt(x,1)+"'");
                                                                }

                                                                for (x=0;x<=fil-1;x++) {
                                                                    for (y=0;y<=col-1;y++) {
                                                                        tabla.setValueAt("",x,y);
                                                                    }
                                                                }

                                                                for (x=fil-1;x>=0;x--) {
                                                                    DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                                                                    modelo.removeRow(x);
                                                                }

                                                            } catch(SQLException e){
                                                                //JOptionPane.showMessageDialog(null,"Ocurrio un error, la factura no se realizó") ;
                                                                System.out.println(e);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }

                        if (banderaFactura==0){ //UNA VEZ REALIZADAS TODAS LAS OPERACIONES PASO A UNA NUEVA FACTURA
                            nuevaFactura();
                        }
            
        }catch (ArrayIndexOutOfBoundsException e) {
            
        }
    }
    
    
    
    private void btnAgregarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarFacturaActionPerformed
       
        //BOTON PARA GUARDAR LA FACTURA    
        int banderaFactura=0;

        txtCuit.setBackground(new Color(255,255,255));

            if (comboVendedor.getSelectedItem().equals("SELECCIONE EMPLEADO")) {
                JOptionPane.showMessageDialog(null, "Falta elegir el Empleado","Advertencia",JOptionPane.WARNING_MESSAGE);
            }else 
                if (comboCliente.getSelectedItem().equals("SELECCIONE CLIENTE")) {
                    JOptionPane.showMessageDialog(null, "Falta elegir el Cliente","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else 
                    if (comboFactura.getSelectedItem().equals("SELECCIONAR")) {
                        JOptionPane.showMessageDialog(null, "Falta elegir el tipo de factura","Advertencia",JOptionPane.WARNING_MESSAGE);
                    }else if (comboContribuyente.getSelectedItem().equals("SELECCIONAR")) {
                            JOptionPane.showMessageDialog(null, "Falta elegir el tipo de contribuyente","Advertencia",JOptionPane.WARNING_MESSAGE);
                        }else 
                            if (comboFactura.getSelectedItem().equals("FACTURA A") && !comboContribuyente.getSelectedItem().equals("Responsable Inscripto")) {
                               JOptionPane.showMessageDialog(null, "Las facturas de tipo A solo se pueden realizar a otros Responsables Inscriptos","Advertencia",JOptionPane.WARNING_MESSAGE);
                            }else 
                                if (comboFactura.getSelectedItem().equals("FACTURA B") && comboContribuyente.getSelectedItem().equals("Responsable Inscripto")) {
                                   JOptionPane.showMessageDialog(null, "Las facturas de tipo B solo se pueden realizar a Responsables Monotributo o Consumidores finales","Advertencia",JOptionPane.WARNING_MESSAGE);
                                }else
                                    if (calendario.getDate() == null) {
                                            JOptionPane.showMessageDialog(null, "La fecha estaba vacia","Advertencia",JOptionPane.WARNING_MESSAGE);
                                            setearFecha();
                                        }else 
                                            if (fact.getText().equals("")) {
                                                JOptionPane.showMessageDialog(null, "Debe Digitar Numero de la Factura","Advertencia",JOptionPane.WARNING_MESSAGE);
                                                fact.requestFocus();
                                            }else
                                                if ( tabla.getRowCount() <= 0 ) {
                                                    JOptionPane.showMessageDialog(null, "No Hay Ningun Producto a Facturar","Advertencia",JOptionPane.ERROR_MESSAGE);
                                                }else { 
                                                    guardarFacturaEstandar();                 
                                                }
                                                    
    }//GEN-LAST:event_btnAgregarFacturaActionPerformed

    private void btnCancelarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarFacturaActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarFacturaActionPerformed

    private void botonAgregarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarArticuloActionPerformed
        //llamo al form de agregar articulo
        Agregar_Articulo_Ventas form= new Agregar_Articulo_Ventas ();
        form.setVisible(true);
        Agregar_Articulo_Ventas.recibeCliente.setText("3");
        form.toFront();
    }//GEN-LAST:event_botonAgregarArticuloActionPerformed

    private void comboFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFacturaActionPerformed
        //APLICO LAS RESTRICCIONES PARA QUE ME DEJE AGREGAR ARTICULOS
        String ivaFactura="19.00";
          
        
        try{
            if(comboIva.getSelectedItem().equals("19%")){
                ivaFactura="19.00";
            }else if(comboIva.getSelectedItem().equals("0%")){
                     ivaFactura="0.00";
                  }
        }catch(Exception e){
            
        }
        //DEPENDE SI COMBO FACTURA ESTA SETEADO EN FACTURA A O FACTURA B CAMBIO LOS VALORES DEL IVA
        if (comboFactura.getSelectedItem().equals("FACTURA B")){
            txtIva.setEnabled(true);
            txtIva.setEditable(false);
            txtIva.setText("0.00");
            txtIva2.setText("0.00");
            comboIva.setEnabled(true);
        }else if (comboFactura.getSelectedItem().equals("FACTURA A")){
            txtIva.setEnabled(true);
            txtIva.setEditable(true);
            txtIva.setText(ivaFactura);
            txtIva2.setText(ivaFactura);
            comboIva.setEnabled(true);
        }
        int fil = tabla.getRowCount();
        int x;
        for (x=fil-1;x>=0;x--) {
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            modelo.removeRow(x);
        }
        cant.setText("");
        sub.setText("0.00");
        valorIva.setText("0.00");
        total.setText("0.00");
        txtCodigoArticulo1.requestFocus();
    }//GEN-LAST:event_comboFacturaActionPerformed

    private void txtTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoActionPerformed

    private void txtCuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuitActionPerformed

    private void checkDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDescuentoActionPerformed
        //HABILITO O DESHABILITO EL TEXFIEDL DESCUENTO INDIVIDUAL SEGUN ESTE O NO SELECCIONADO EL CHECK DE DESCUENTO INDIVIDUAL
        if (!checkDescuento.isSelected()){
            txtDescuento.setEnabled(false);
            txtDescuento.setEditable(false);
            txtDescuento.setText("");
        }else{
            txtDescuento.setEnabled(true);
            txtDescuento.setEditable(true);
            txtDescuento.requestFocus();
        }
    }//GEN-LAST:event_checkDescuentoActionPerformed

    private void txtCodigoArticulo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoArticulo1ActionPerformed
        //CUANDO APRETO ENTER AL TEXTFIELD CODIGO DE ARTICULO
        int bandera=0;
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

           // nomproducto = (String)cb3.getSelectedItem();
            codigoproducto=txtCodigoArticulo1.getText();
            String nombreArticulo="";
            
            ResultSet rs = consulta.executeQuery("SELECT referencia FROM articulo WHERE cod_articulo = '"+txtCodigoArticulo1.getText()+"'");
            while (rs.next()) {
                nombreArticulo= rs.getString(1);
                bandera=1;
            }
            if(bandera==1){
                comboArticulo.setSelectedItem(nombreArticulo);
            }
            
            conn.close();  // Cierra la conexión

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
        if(bandera==1){
           cant.requestFocus();
        }else{
            JOptionPane.showMessageDialog(null, "El codigo ingresado no pertenece a ningun articulo del inventario");
        }
        
        
    }//GEN-LAST:event_txtCodigoArticulo1ActionPerformed

    private void txtTotalBrutoArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalBrutoArtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalBrutoArtActionPerformed

    private void txtDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescuentoActionPerformed

    private void cantComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_cantComponentMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_cantComponentMoved

    private void checkIva1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkIva1ActionPerformed
        //ESTE CHECK ESTA OCULTO PERO IGUAL REALIZA LOS CALCULOS (NO BORRAR)
        if (!checkIva1.isSelected()){
            txtIva.setEnabled(true);
            txtIva.setEditable(false);
            txtIva.setText("0.00");
            txtIva2.setText("0.00");
        }else{
            txtIva.setEnabled(true);
            txtIva.setEditable(true);
            txtIva.setText("19.00");
            txtIva2.setText("19.00");
        }
        Double bruto=0.0,porcent=0.0;
        String valorBruto="",porcentajee="";
        try{                                
            bruto=(double)Integer.parseInt(sub.getText().replace(",", "."));
            porcent=(double)Integer.parseInt(txtIva.getText().replace(",", "."));
        }catch (NumberFormatException e){
            bruto=Double.parseDouble(sub.getText().replace(",", "."));
            porcent=Double.parseDouble(txtIva.getText().replace(",", "."));  
        }
        valorBruto=(String.format("%.2f", bruto));
        porcentajee=(String.format("%.2f", porcent));

        Double precioneto = bruto + (bruto * porcent)/100;

        String precion=(String.format("%.2f", precioneto));
        total.setText(precion.replace(",", "."));
    }//GEN-LAST:event_checkIva1ActionPerformed

    private void txtIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIvaActionPerformed
        
    }//GEN-LAST:event_txtIvaActionPerformed

    private void txtIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaKeyReleased
        //ESTE TXT ESTA OCULTO PERO IGUAL REALIZA LOS CALCULOS (NO BORRAR)
        if(!checkIva1.isSelected()){
            txtIva.setEditable(true);
            txtIva.setText("0.00");
            txtIva2.setText(txtIva.getText());
        }else{
            if(txtIva.getText().equals("")){
                txtIva.setEditable(true);
            }
            else{
                if(!sub.getText().equals("") && !txtIva.getText().equals("")){
                    Double bruto=0.0,porcent=0.0;
                    String valorBruto="",porcentajee="";
                    try{                                
                        bruto=(double)Integer.parseInt(sub.getText().replace(",", "."));
                        porcent=(double)Integer.parseInt(txtIva.getText().replace(",", "."));
                   }catch (NumberFormatException e){
                        bruto=Double.parseDouble(sub.getText().replace(",", "."));
                        porcent=Double.parseDouble(txtIva.getText().replace(",", "."));  
                   }
                   valorBruto=(String.format("%.2f", bruto));
                   porcentajee=(String.format("%.2f", porcent));

                   Double precioneto = bruto + (bruto * porcent)/100;

                   String precion=(String.format("%.2f", precioneto));
                   total.setText(precion.replace(",", "."));   
                   txtIva2.setText(txtIva.getText());
                }else{

                }
            }
        }     
    }//GEN-LAST:event_txtIvaKeyReleased

    private void txtDomicilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDomicilioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDomicilioActionPerformed

    private void comboIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIvaActionPerformed
        //SEGUN QUE VALOR DEL COMBO IVA ESTE SELECCIONADO SETEO LAS VARIABLES CON LAS QUE REALIZO LOS CALCULOS
        String ivaFactura="";
        if(comboIva.getSelectedItem().equals("19%")){
            txtIva.setText("19.00");
            ivaFactura="19.00";
        }else if(comboIva.getSelectedItem().equals("0%")){
                    txtIva.setText("0.00");
                    ivaFactura="0.00";
              }
        if(comboFactura.getSelectedItem().equals("FACTURA A")){
            if(!sub.getText().equals("") && !txtIva.getText().equals("")){
                    Double bruto=0.0,porcent=0.0;
                    String valorBruto="",porcentajee="";
                    try{                                
                        bruto=(double)Integer.parseInt(sub.getText().replace(",", "."));
                        bruto=Math.round(bruto * 100.0)/ 100.0;
                        porcent=(double)Integer.parseInt(txtIva.getText().replace(",", "."));
                   }catch (NumberFormatException e){
                        bruto=Double.parseDouble(sub.getText().replace(",", "."));
                        bruto=Math.round(bruto * 100.0)/ 100.0;
                        porcent=Double.parseDouble(txtIva.getText().replace(",", "."));  
                   }
                   valorBruto=(String.format("%.2f", bruto));
                   porcentajee=(String.format("%.2f", porcent));

                   Double precioneto = bruto + (bruto * porcent)/100;

                   String precion=(String.format("%.2f", precioneto));
                                      
                   valorIva.setText(String.format("%.2f", precioneto-bruto));
                   total.setText(precion.replace(",", "."));   
                   txtIva2.setText(txtIva.getText());
                }else{

                }
        }
        txtCodigoArticulo1.requestFocus();
    }//GEN-LAST:event_comboIvaActionPerformed

    private void comboContribuyenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboContribuyenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboContribuyenteActionPerformed

    private void checkDescuentoGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDescuentoGeneralActionPerformed
        //SI EL CHECK DESCUENTO GENERAL ESTA SELECCIONADO APLICO EL DESCUENTO A TODOS LOS ARTICULOS QUE ESTEN DENTRO DE LA TABLA
        if (!checkDescuentoGeneral.isSelected()){
            txtDescuentoGeneral.setEnabled(false);
            txtDescuentoGeneral.setEditable(false);
            txtDescuentoGeneral.setText("");
            
            int bandera=0;
            String desc,desc2, porcentajeDesc ;
            double valorDescontado=0.0;
            fila = tabla.getRowCount();
            columna = tabla.getColumnCount();

            desc="0.00";
            desc2="0.00";

            porcentajeDesc= desc2;
            double subtotal2=0.00,subTotalBrutoArt=0.0, porcentIva=0.0;

            int fila2 = tabla.getRowCount();
            Double subtotal33=0.0, subtotalConIva=0.0;
            for (int x=0;x<=fila2-1;x++) {
                tabla.setValueAt(desc,x,5);

                double porcent=(Double.parseDouble(tabla.getValueAt(x,5).toString().replace(",", "."))* Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")))/100.00;
                porcent=Math.round(porcent * 100.0)/ 100.0;
                double totalsub=(Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla.getValueAt(x,0).toString());
                totalsub=Math.round(totalsub * 100.0)/ 100.0;
                valorDescontado=valorDescontado + (porcent * Double.parseDouble(tabla.getValueAt(x,0).toString()));                
                valorDescontado=Math.round(valorDescontado * 100.0)/ 100.0;
                double totalsubArticuloBruto=(Double.parseDouble(tabla.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla.getValueAt(x,0).toString());
                totalsubArticuloBruto=Math.round(totalsubArticuloBruto * 100.0)/ 100.0;
                subtotalBruto=Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla.getValueAt(x,0).toString());

                subtotal33=totalsub+subtotal33;
                subtotal2 =subtotal2 +subtotalBruto;
                subTotalBrutoArt=subTotalBrutoArt + totalsubArticuloBruto;

                tabla.setValueAt(String.format("%.2f", subtotalBruto ).replace(",", "."),x,4);
                tabla.setValueAt(String.format("%.2f", totalsub).replace(",", "."),x,6);
                tabla.setValueAt(String.format("%.2f", subTotalBrutoArt),x,8);                       
            }

            porcentIva=Double.parseDouble(txtIva.getText().replace(",", "."))/100;

            subtotalConIva= subtotal33*(1+porcentIva);
            sub.setText(String.format("%.2f",subtotal33).replace(",", "."));
            valorIva.setText(String.format("%.2f",subtotalConIva-subtotal33).replace(",", "."));
            total.setText(String.format("%.2f",subtotalConIva).replace(",", "."));
            total2.setText(""+String.format("%.2f",subtotalConIva));
            txtTotalBrutoArt.setText(""+String.format("%.2f",subTotalBrutoArt));
            txtDtoGral.setText("$"+String.format("%.2f",valorDescontado).replace(",", "."));
            txtDtoGral.setVisible(false);
        }else{
            txtDescuentoGeneral.setEnabled(true);
            txtDescuentoGeneral.setEditable(true);
            txtDescuentoGeneral.requestFocus(); 
            txtDtoGral.setVisible(true);
            txtDtoGral.setText("$0.00");
        }
    }//GEN-LAST:event_checkDescuentoGeneralActionPerformed

    private void txtDescuentoGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescuentoGeneralActionPerformed
        //SI EL CHECK DESCUENTO GENERAL ESTA SELECCIONADO APLICO EL DESCUENTO A TODOS LOS ARTICULOS QUE ESTEN DENTRO DE LA TABLA
        int bandera=0;
        String desc,desc2, porcentajeDesc ;
        double valorDescontado=0.0;
        fila = tabla.getRowCount();
        columna = tabla.getColumnCount();
        
        if(txtDescuentoGeneral.getText().equals("")){
            desc="0.00";
            
            desc2="0.00";
        }else{
            double desc22 = Double.parseDouble(txtDescuentoGeneral.getText().replace(",", "."));
            desc=String.format("%.2f",desc22).replace(",", "."); 
            desc2 = String.format("%.2f",desc22).replace(",", ".");
        }
                        
        
                        
        porcentajeDesc= desc2;
        double subtotal2=0.00,subTotalBrutoArt=0.0, porcentIva=0.0;
                        
        int fila2 = tabla.getRowCount();
        Double subtotal33=0.0, subtotalConIva=0.0;
        for (int x=0;x<=fila2-1;x++) {
            tabla.setValueAt(desc,x,5);

            double porcent=(Double.parseDouble(tabla.getValueAt(x,5).toString().replace(",", "."))* Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")))/100.00;
            double totalsub=(Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla.getValueAt(x,0).toString());
            valorDescontado=valorDescontado + (porcent * Double.parseDouble(tabla.getValueAt(x,0).toString()));                
            double totalsubArticuloBruto=(Double.parseDouble(tabla.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla.getValueAt(x,0).toString());
            subtotalBruto=Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla.getValueAt(x,0).toString());
                            
            subtotal33=totalsub+subtotal33;
            subtotal2 =subtotal2 +subtotalBruto;
            subTotalBrutoArt=subTotalBrutoArt + totalsubArticuloBruto;
                            
            tabla.setValueAt(String.format("%.2f", subtotalBruto ).replace(",", "."),x,4);
            tabla.setValueAt(String.format("%.2f", totalsub).replace(",", "."),x,6);
            tabla.setValueAt(String.format("%.2f", subTotalBrutoArt),x,8);                       
        }
                        
        porcentIva=Double.parseDouble(txtIva.getText().replace(",", "."))/100;

        subtotalConIva= subtotal33*(1+porcentIva);
        sub.setText(String.format("%.2f",subtotal33).replace(",", "."));
        valorIva.setText(String.format("%.2f",subtotalConIva-subtotal33).replace(",", "."));
        total.setText(String.format("%.2f",subtotalConIva).replace(",", "."));
        total2.setText(""+String.format("%.2f",subtotalConIva));
        txtTotalBrutoArt.setText(""+String.format("%.2f",subTotalBrutoArt));
        txtDtoGral.setText("$"+String.format("%.2f",valorDescontado).replace(",", "."));
        
        txtCodigoArticulo1.requestFocus();
    }//GEN-LAST:event_txtDescuentoGeneralActionPerformed

    private void txtCuitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuitKeyTyped
        if(txtCuit.getText().length()>=11){
            evt.consume();
        }
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
        evt.consume();
    }//GEN-LAST:event_txtCuitKeyTyped

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void itemBuscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarArticuloActionPerformed
        //SON LOS ITEM QUE HACEN FUNCIONAR LOS ACCESOS RAPIDOS F3 F5 F7 ETC
        enviar_producto form= new enviar_producto ();
        form.setVisible(true);
        form.toFront();
        enviar_producto.txt_recibe.setText("1");
        enviar_producto.txt_recibe.setVisible(false);
        enviar_producto.enviarProducto.setVisible(false);
    }//GEN-LAST:event_itemBuscarArticuloActionPerformed

    private void itemAgregarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAgregarArticuloActionPerformed
        //SON LOS ITEM QUE HACEN FUNCIONAR LOS ACCESOS RAPIDOS F3 F5 F7 ETC
        Agregar_Articulo_Ventas form= new Agregar_Articulo_Ventas ();
        form.setVisible(true);
        Agregar_Articulo_Ventas.recibeCliente.setText("3");
        form.toFront();
    }//GEN-LAST:event_itemAgregarArticuloActionPerformed

    private void itemBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarClienteActionPerformed
        //SON LOS ITEM QUE HACEN FUNCIONAR LOS ACCESOS RAPIDOS F3 F5 F7 ETC
        enviar_cliente form= new enviar_cliente ();
        form.setVisible(true);
        enviar_cliente.recibe.setText("1");
        form.toFront();
                       
    }//GEN-LAST:event_itemBuscarClienteActionPerformed

    private void itemAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAgregarClienteActionPerformed
        //SON LOS ITEM QUE HACEN FUNCIONAR LOS ACCESOS RAPIDOS F3 F5 F7 ETC
        Agregar_Cliente form= new Agregar_Cliente();
        form.setVisible(true);
        Agregar_Cliente.recibeCliente.setText("3");
        form.toFront();
    }//GEN-LAST:event_itemAgregarClienteActionPerformed

    private void itemBuscarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarVendedorActionPerformed
        //SON LOS ITEM QUE HACEN FUNCIONAR LOS ACCESOS RAPIDOS F3 F5 F7 ETC
        enviar_empleado form= new enviar_empleado ();
        form.setVisible(true);
        form.toFront();
        enviar_empleado.txt_recibeEmpleado.setText("1");
        enviar_empleado.txt_recibeEmpleado.setVisible(false);
        enviar_empleado.enviarEmpleado.setVisible(false);
    }//GEN-LAST:event_itemBuscarVendedorActionPerformed

    private void txtCodigoArticulo1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoArticulo1KeyReleased

    }//GEN-LAST:event_txtCodigoArticulo1KeyReleased

    private void itemIvaDefectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemIvaDefectoActionPerformed
        //SON LOS ITEM QUE HACEN FUNCIONAR LOS ACCESOS RAPIDOS F3 F5 F7 ETC
        Configurar_Porcentaje_Factura_Venta form= new Configurar_Porcentaje_Factura_Venta ();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_itemIvaDefectoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //BOTON PARA QUITAR EL ULTIMO ARTICULO INGRESADO
        Double subtotal2=0.0,subTotalBrutoArt=0.0, porcentIva=0.0;
        Double variableIva=0.00;
        if(txtIva2.getText().equals("19.00")){
               variableIva=1.19;
           }else if(txtIva2.getText().equals("0.00")){
                       variableIva=1.00;
                  }
        int Select=tabla.getSelectedRow();
        
        if(Select>=0){
            try
            {


                DefaultTableModel modelo2=(DefaultTableModel)tabla.getModel();    
                modelo2.removeRow(Select);

                int pe=0,fil = tabla.getRowCount();
                double acum=0.0;
                for (int x=0;x<=fil-1;x++) {

                    Double unitarioBruto=Double.parseDouble(tabla.getValueAt(x, 7).toString().replace(",","."))*Double.parseDouble(tabla.getValueAt(x, 0).toString().replace(",","."));
                    acum=acum+unitarioBruto;
                    acum=Math.round(acum * 100.0)/ 100.0;
                    tabla.setValueAt(acum,x,8);
                }

                int fila2 = tabla.getRowCount();
                Double subtotal33=0.0, subtotalConIva=0.0;
                            //A MEDIDA QUE INGRESO LOS ARTICULOS HAGO LOS CALCULOS 
                for (int x=0;x<=fila2-1;x++) {

                    double porcent=(Double.parseDouble(tabla.getValueAt(x,5).toString().replace(",", "."))* Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")))/100.00;
                    double totalsub=(Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla.getValueAt(x,0).toString());

                    double totalsubArticuloBruto=(Double.parseDouble(tabla.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla.getValueAt(x,0).toString());
                    subtotalBruto=Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla.getValueAt(x,0).toString());

                    subtotal33=totalsub+subtotal33;
                    subtotal2 =subtotal2 +subtotalBruto;
                    subTotalBrutoArt=subTotalBrutoArt + totalsubArticuloBruto;

                }

                porcentIva=Double.parseDouble(txtIva2.getText().replace(",", "."))/100;

                subtotalConIva= subtotal33*(1+porcentIva);
                sub.setText(String.format("%.2f",subtotal33).replace(",", "."));
                valorIva.setText(String.format("%.2f",subtotalConIva-subtotal33).replace(",", "."));
                total.setText(String.format("%.2f",subtotalConIva).replace(",", "."));
                total2.setText(""+String.format("%.2f",subtotalConIva));
                txtTotalBrutoArt.setText(""+String.format("%.2f",subTotalBrutoArt));


                DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
               // modelo.removeRow(fila);
                fila-=1;
                int filat=tabla.getRowCount();
                if(filat==0){
                    txtTotalBrutoArt.setText("0.00");
                }else{
                    txtTotalBrutoArt.setText(tabla.getValueAt(fila, 8).toString());
                }
                if (filat==0){
                    comboVendedor.setEnabled(true);
                    comboCliente.setEnabled(true);
                }
            }catch (ArrayIndexOutOfBoundsException e) {        
                //JOptionPane.showMessageDialog(null, "NO HAY PRODUCTOS PARA QUITAR");          
            }
            txtCodigoArticulo1.requestFocus();
        }else{
            int filat=tabla.getRowCount();
            if(filat==0){
                JOptionPane.showMessageDialog(null, "NO HAY PRODUCTOS PARA QUITAR"); 
            }else{
                JOptionPane.showMessageDialog(null, "SELECCIONE LA FILA QUE DESEA QUITAR");   
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnEditarArticuloMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarArticuloMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarArticuloMouseEntered

    private void btnEditarArticuloMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarArticuloMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarArticuloMouseExited

    private void btnEditarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarArticuloActionPerformed
        if(txtCodigoArticulo1.getText().equals("")){
            JOptionPane.showMessageDialog(null,"PRIMERO SELECCIONE EL ARTICULO");
            txtCodigoArticulo1.requestFocus();
        }else{
            int i=0;
            try{
                Connection conn =conexion.ObtenerConexion();
                Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
                ResultSet r= consulta1.executeQuery("select * from articulo where cod_articulo='"+txtCodigoArticulo1.getText()+"'");
                
                while(r.next()){
                    i++;
                }
            }catch(Exception e){
                System.out.println(e);
            }
            if(i>=1){
                //llamo al form de agregar articulo
                 Editar_Articulo form= new Editar_Articulo ();
                 form.setVisible(true);
                 Editar_Articulo.recibeCliente.setText("3");
                 Editar_Articulo.cargarComboProveedores();
                 Editar_Articulo.CargarArticulo(txtCodigoArticulo1.getText());
                 form.toFront(); 
            }else{
                 JOptionPane.showMessageDialog(null,"El codigo ingresado no pertenece a ningun articulo del inventario");
                 txtCodigoArticulo1.requestFocus();
            }
        }
    }//GEN-LAST:event_btnEditarArticuloActionPerformed

    private void comboTipoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTipoVentaActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        //SON LOS ITEM QUE HACEN FUNCIONAR LOS ACCESOS RAPIDOS F3 F5 F7 ETC
        Configurar_Impresion form= new Configurar_Impresion ();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void comboCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCajaActionPerformed


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
            java.util.logging.Logger.getLogger(Factura_Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Factura_Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Factura_Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Factura_Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Factura_Venta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu QuitarArticulo;
    private javax.swing.JButton botonAgregarArticulo;
    private javax.swing.JButton botonAgregarCliente;
    private javax.swing.JButton btnAgregarArticulo;
    private javax.swing.JButton btnAgregarFactura;
    private javax.swing.JButton btnBuscarArticulo;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarVendedor;
    private javax.swing.JButton btnCancelarFactura;
    private javax.swing.JButton btnEditarArticulo;
    private javax.swing.JButton btnQuitarArticulo;
    private com.toedter.calendar.JDateChooser calendario;
    private javax.swing.JTextField cant;
    private javax.swing.JCheckBox checkDescuento;
    private javax.swing.JCheckBox checkDescuentoGeneral;
    private javax.swing.JCheckBox checkIva1;
    public static javax.swing.JComboBox comboArticulo;
    public static javax.swing.JComboBox comboCaja;
    public static javax.swing.JComboBox comboCliente;
    public static javax.swing.JComboBox comboContribuyente;
    public static javax.swing.JComboBox comboFactura;
    public static javax.swing.JComboBox comboIva;
    public static javax.swing.JComboBox comboPago;
    public static javax.swing.JComboBox comboTipoVenta;
    public static javax.swing.JComboBox comboVendedor;
    private javax.swing.JTextField fact;
    private javax.swing.JMenuItem itemAgregarArticulo;
    private javax.swing.JMenuItem itemAgregarCliente;
    private javax.swing.JMenuItem itemBuscarArticulo;
    private javax.swing.JMenuItem itemBuscarCliente;
    private javax.swing.JMenuItem itemBuscarVendedor;
    private javax.swing.JMenuItem itemIvaDefecto;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    public static javax.swing.JTextField sub;
    private javax.swing.JTable tabla;
    public static javax.swing.JTextField total;
    private javax.swing.JTextField total2;
    public static javax.swing.JTextField txtCodigoArticulo1;
    public static javax.swing.JTextField txtCodigoCliente;
    public static javax.swing.JTextField txtCodigoEmpleado;
    public static javax.swing.JTextField txtCuit;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtDescuentoGeneral;
    public static javax.swing.JTextField txtDomicilio;
    private javax.swing.JTextField txtDtoGral;
    public static javax.swing.JTextField txtIva;
    public static javax.swing.JTextField txtIva2;
    public static javax.swing.JTextField txtIva3;
    private javax.swing.JTextField txtIvaFactura;
    public static javax.swing.JTextField txtPesoBascula;
    public static javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtTotalBrutoArt;
    public static javax.swing.JTextField valorIva;
    // End of variables declaration//GEN-END:variables
}
