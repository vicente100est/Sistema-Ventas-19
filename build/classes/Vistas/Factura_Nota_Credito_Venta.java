
package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
import java.util.Calendar;
import java.sql.*;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;



public class Factura_Nota_Credito_Venta extends javax.swing.JFrame {
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
      
    public Factura_Nota_Credito_Venta() {    
        initComponents(); 
        setLocationRelativeTo(null);
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        cargarCombos();
        nuevaFactura();
        
        //COLOR CABECERA DE LA TABLA
        JTableHeader th; 
        th = tabla.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        //ANCHO DE LAS COLUMNAS
        tabla.getColumnModel().getColumn(0).setMaxWidth(60);
        tabla.getColumnModel().getColumn(0).setMinWidth(60);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(60);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(60);
        
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
    }
          
    Factura_Nota_Credito_Venta(Menu_Principal aThis, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void nuevaFactura(){ 
        
        //INICIALIZO LOS COMPONENTES PARA UNA NUEVA FACTURA 
        
        comboContribuyente.addItem("Consumidor Final");
        comboContribuyente.addItem("Responsable Monotributo");
        comboContribuyente.addItem("Responsable Inscripto");
        comboContribuyente.addItem("Responsable no Inscripto");   
        comboContribuyente.addItem("No Responsable"); 
        comboContribuyente.addItem("Exento"); 
        
        comboIva.addItem("19%");
        comboIva.addItem("0%");
        
        //SETEO LA FECHA ACTUAL
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
        txtCodigoArticulo.setVisible(false);
        txtTotalBrutoArt.setVisible(false);
        comboArticulo.setVisible(false);
        txtPrecioArticulo.setVisible(false);
        total2.setVisible(false);
        txtIva.setVisible(false);
        txtTipo.setVisible(false);
        txtDescuento.setText("");
        txtDomicilio.setText("");
        txtDescuento.setEditable(false); 
        txtNroFactura.setEditable(false);
        txtCuit.setText("");
        txtTipo.setText("");
        
        comboCliente.setSelectedIndex(0);
        comboCliente.setVisible(false);
        comboArticulo.setSelectedIndex(0);
        comboIva.setEnabled(false);
        
        txtIva.setText("0.00");
        txtIva2.setText("0.00");
        cant.setText("");
        fact.setText("");
        sub.setText("0.00");
        total.setText("0.00");
        habilitar();
        
        int fil = tabla.getRowCount();
        int x;
        //BORRO LA TABLA POR SI TUVIERA ALGUN VALOR
        for (x=fil-1;x>=0;x--) {
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            modelo.removeRow(x);
        }
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            //PARA SABER EL NUMERO DE NOTA DE CREDITO
            ResultSet r= consulta.executeQuery("select cod_nota_credito from  nota_credito order by cod_nota_credito desc");
            r.next();
            fact.setText(r.getString(1));
            int f;         
            f=Integer.parseInt(fact.getText())+1;
            fact.setText(String.valueOf(f));
            r.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
       // txtFacturaNumero.setText("NOTA DE CREDITO VENTA N° "+fact.getText());
    }
    
    public static void tipoFactura(){      
        if(comboContribuyente.getSelectedItem().equals("Responsable Inscripto")){
            comboFactura.setSelectedItem("FACTURA A");
        }else{
            comboFactura.setSelectedItem("FACTURA B");
        }
        //SEGUN EL TIPO DE FACTURA SETEO LAS VARIABES DE IVA (DESPUES LAS PUEDO CAMBIAR SEGUN QUE VALOR DE IVA APRETO)
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

    }
    
    public void inabilita(){
        //habilito los componentes que voy a usar
        setearFecha();
    
        calendario.setEnabled(false);

        comboVendedor.setEnabled(false);
        comboCliente.setEnabled(false);
        comboArticulo.setEnabled(false);
        
        txtDescuento.setEnabled(false);
        comboPago.setEnabled(false);
        btnBuscarVendedor.setEnabled(false);
        btnBuscarArticulo.setEnabled(false);
        cant.setEditable(true);
        btnAgregarArticulo.setEnabled(false);
        btnQuitarArticulo.setEnabled(false);

        b42.setEnabled(false);
        btn_guardara.setEnabled(false);
        b42.setEnabled(false);
        sub.setEnabled(false);
        txtIva.setEnabled(false);
        total.setEnabled(false);

        total2.setVisible(false);
        fact.setVisible(false);
        txtCodigoEmpleado.setVisible(false);
        txtCodigoCliente.setVisible(false);
        txtFacturaNumero.setText("NOTA DE CREDITO VENTA");  
    }
    
    public void habilitar(){
        //deshabilito los componentes
        calendario.setEnabled(true); 

        txtDescuento.requestFocus();
        comboVendedor.setEnabled(true);
        comboCliente.setEnabled(true);
        comboArticulo.setEnabled(false);
        comboPago.setEnabled(true);
        
        btnBuscarVendedor.setEnabled(true);
        btnBuscarArticulo.setEnabled(false);

        cant.setEditable(true);
        btnAgregarArticulo.setEnabled(false);
        btnQuitarArticulo.setEnabled(false);
        b42.setEnabled(true);
        btn_guardara.setEnabled(true);
        
        sub.setEnabled(true);
        txtIva.setEnabled(true);
        total.setEnabled(true);
    }
    
     
    public void setearFecha() {      
        Calendar c2 = new GregorianCalendar();
        calendario.setCalendar(c2);    
    }
    
    public void cargarCombos(){
        
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            Statement consulta2=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet   res= consulta.executeQuery("select * from articulo order by referencia");
                
            comboPago.addItem("EFECTIVO");
            comboPago.addItem("EFECTIVO Y TARJETA");
            comboPago.addItem("CREDITO");
            comboPago.addItem("DEPOSITO");
            comboPago.addItem("CHEQUE");
              
            comboFactura.addItem("SELECCIONAR");
            comboFactura.addItem("FACTURA A");
            comboFactura.addItem("FACTURA B");
            //comboFactura.addItem("FACTURA C");//
            
            comboTipoVenta.addItem("PRODUCTOS");
            comboTipoVenta.addItem("SERVICIO");
            comboTipoVenta.setSelectedItem("PRODUCTOS");
            
            int i;
            //CARGO LOS COMBOS CON LOS VALORES DE LA BD
            comboArticulo.removeAllItems();
            comboArticulo.addItem("SELECCIONE ARTICULO");
            i=0;
            while(res.next()){
                comboArticulo.addItem(res.getString(2));
                i++;
            }

            res= consulta.executeQuery("select * from cliente order by nombres");
            //CARGO LOS COMBOS CON LOS VALORES DE LA BD
            comboCliente.removeAllItems();
            comboCliente.addItem("SELECCIONE CLIENTE");
            i=0;
            while(res.next()){
                comboCliente.addItem(res.getString(2));
                i++;
            }

            /***********SELECCIONO VENDEDOR ACTIVO******************/
            ResultSet  res2= consulta.executeQuery("select nombre_usuario from ingreso_usuarios WHERE estado='ACTIVO' ");
            String usuarioActivo= "";
            while(res2.next()){
               usuarioActivo= res2.getString(1);     
            }


            Statement consulta3=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet  res3= consulta3.executeQuery("select * from empleado WHERE estado='ACTIVO' order by nombres");  
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
            
            //CARGO EL COMBO CAJA CON LAS CAJAS QUE ESTAN ABIERTAS
            ResultSet r2= consulta2.executeQuery("select nombre_caja from cajas where estado='ABIERTA' order by cod_caja"); 
            comboCaja.removeAllItems();
            comboCaja.addItem("CAJA CERRADA");
            int i2=0;
            while(r2.next()){
                if(i2==0){
                    comboCaja.removeItem("CAJA CERRADA");
                    i2++;
                }
                comboCaja.addItem(r2.getString(1)); //NOMBRE DELA CAJA

            }
            r2.close();
                
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        QuitarArticulo = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btnBuscarArticulo = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cant = new javax.swing.JTextField();
        btnAgregarArticulo = new javax.swing.JButton();
        btnQuitarArticulo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        sub = new javax.swing.JTextField();
        txtIva2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btn_guardara = new javax.swing.JButton();
        b42 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
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
        txtCliente = new javax.swing.JTextField();
        txtNombreArticulo = new javax.swing.JTextField();
        txtCodigoEmpleado = new javax.swing.JTextField();
        txtCodigoCliente = new javax.swing.JTextField();
        fact = new javax.swing.JTextField();
        comboCliente = new javax.swing.JComboBox();
        comboArticulo = new javax.swing.JComboBox();
        comboCaja = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        txtFacturaNumero = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtFacturaNumero1 = new javax.swing.JLabel();
        txtNroFactura = new javax.swing.JTextField();
        btnBuscarFactura = new javax.swing.JButton();
        comboTipoVenta = new javax.swing.JComboBox();
        calendario = new com.toedter.calendar.JDateChooser();
        comboVendedor = new javax.swing.JComboBox();
        btnBuscarVendedor = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtCodigoArticulo = new javax.swing.JTextField();
        txtPrecioArticulo = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemBuscarVendedor = new javax.swing.JMenuItem();
        itemBuscarFactura = new javax.swing.JMenuItem();
        itemBuscarArticulo = new javax.swing.JMenuItem();

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem1.setText(" QUITAR ARTICULO ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        QuitarArticulo.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Devolución de venta");
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

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel8.setText("Fecha");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 80, 40));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setBackground(new java.awt.Color(239, 255, 239));
        jLabel9.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel9.setText("Articulo");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 90, 40));

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
        jPanel1.add(btnBuscarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 60, 39));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel6.setText("Cantidad");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 100, 30));

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
        jPanel1.add(cant, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 100, 39));

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
        jPanel1.add(btnAgregarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 150, 38));

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
        jPanel1.add(btnQuitarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 140, 38));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 990, 140));

        jLabel7.setBackground(new java.awt.Color(239, 255, 239));
        jLabel7.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel7.setText("Cond");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 60, 40));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel11.setText("IVA Discriminado");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 53, -1, 50));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel13.setText("Importe Total");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 150, -1));

        total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 190, 40));

        sub.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sub.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(sub, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 190, 40));

        txtIva2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIva2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtIva2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 190, 40));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel12.setText("Subtotal");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 3, -1, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 410, 410, 160));

        btn_guardara.setBackground(new java.awt.Color(51, 153, 255));
        btn_guardara.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_guardara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardar.png"))); // NOI18N
        btn_guardara.setBorder(null);
        btn_guardara.setBorderPainted(false);
        btn_guardara.setContentAreaFilled(false);
        btn_guardara.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_guardara.setFocusCycleRoot(true);
        btn_guardara.setFocusable(false);
        btn_guardara.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btn_guardara.setRequestFocusEnabled(false);
        btn_guardara.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btn_guardara.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btn_guardara.setVerifyInputWhenFocusTarget(false);
        btn_guardara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardaraActionPerformed(evt);
            }
        });
        jPanel1.add(btn_guardara, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 150, 140));

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
        jPanel1.add(b42, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, -1, 140));

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel19.setText("Cancelar");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 520, 100, -1));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel17.setText("Facturar");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, -1, -1));

        comboPago.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        comboPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPagoActionPerformed(evt);
            }
        });
        jPanel1.add(comboPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 360, 270, 40));

        jLabel10.setBackground(new java.awt.Color(239, 255, 239));
        jLabel10.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel10.setText("Domicilio");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 110, 40));

        txtDescuento.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtDescuento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescuentoActionPerformed(evt);
            }
        });
        jPanel1.add(txtDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 110, 90, 40));

        checkDescuento.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        checkDescuento.setText("%Desc");
        checkDescuento.setOpaque(false);
        checkDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDescuentoActionPerformed(evt);
            }
        });
        jPanel1.add(checkDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, -1, 40));

        txtTipo.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtTipo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoActionPerformed(evt);
            }
        });
        jPanel1.add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 310, 40, 30));

        jLabel15.setBackground(new java.awt.Color(239, 255, 239));
        jLabel15.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel15.setText("Cliente");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 80, -1));

        txtCuit.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
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

        comboFactura.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        comboFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFacturaActionPerformed(evt);
            }
        });
        jPanel1.add(comboFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, 290, 40));

        jLabel18.setBackground(new java.awt.Color(239, 255, 239));
        jLabel18.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel18.setText("% iVA");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 110, 90, 40));

        txtTotalBrutoArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalBrutoArtActionPerformed(evt);
            }
        });
        jPanel1.add(txtTotalBrutoArt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 40, 30));
        jPanel1.add(txtIvaFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 40, 30));
        jPanel1.add(total2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, 40, 30));

        checkIva1.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        checkIva1.setText("% Iva");
        checkIva1.setOpaque(false);
        checkIva1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkIva1ActionPerformed(evt);
            }
        });
        jPanel1.add(checkIva1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 100, 40));

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
        jPanel1.add(txtIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 40, 30));

        jLabel21.setBackground(new java.awt.Color(239, 255, 239));
        jLabel21.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel21.setText("Tipo de factura");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 160, 40));

        txtDomicilio.setFont(new java.awt.Font("Calibri", 0, 17)); // NOI18N
        txtDomicilio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDomicilioActionPerformed(evt);
            }
        });
        jPanel1.add(txtDomicilio, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 290, 40));

        jLabel22.setBackground(new java.awt.Color(239, 255, 239));
        jLabel22.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel22.setText("Tipo de pago");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 360, 140, 40));

        comboIva.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        comboIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIvaActionPerformed(evt);
            }
        });
        jPanel1.add(comboIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 110, 110, 40));

        comboContribuyente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboContribuyenteActionPerformed(evt);
            }
        });
        jPanel1.add(comboContribuyente, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 210, 40));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        checkDescuentoGeneral.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkDescuentoGeneral.setText("%Descuento General");
        checkDescuentoGeneral.setOpaque(false);
        checkDescuentoGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDescuentoGeneralActionPerformed(evt);
            }
        });
        jPanel5.add(checkDescuentoGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));

        txtDescuentoGeneral.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtDescuentoGeneral.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescuentoGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescuentoGeneralActionPerformed(evt);
            }
        });
        jPanel5.add(txtDescuentoGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 130, 40));

        txtDtoGral.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        txtDtoGral.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDtoGral.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.add(txtDtoGral, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 170, 40));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, 250, 160));

        txtCliente.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteActionPerformed(evt);
            }
        });
        jPanel1.add(txtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 420, 40));

        txtNombreArticulo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtNombreArticulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreArticulo.setAlignmentX(2.0F);
        txtNombreArticulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        txtNombreArticulo.setMargin(new java.awt.Insets(2, 6, 2, 6));
        txtNombreArticulo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                txtNombreArticuloComponentMoved(evt);
            }
        });
        txtNombreArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreArticuloActionPerformed(evt);
            }
        });
        txtNombreArticulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreArticuloKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreArticuloKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombreArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 350, 40));

        txtCodigoEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoEmpleadoActionPerformed(evt);
            }
        });
        jPanel1.add(txtCodigoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 40, -1));

        txtCodigoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoClienteActionPerformed(evt);
            }
        });
        jPanel1.add(txtCodigoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 40, 30));

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
        jPanel1.add(fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 40, -1));

        comboCliente.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        comboCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClienteActionPerformed(evt);
            }
        });
        jPanel1.add(comboCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 60, 39));

        comboArticulo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        comboArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboArticuloActionPerformed(evt);
            }
        });
        jPanel1.add(comboArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 60, 39));

        comboCaja.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCajaActionPerformed(evt);
            }
        });
        jPanel1.add(comboCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 250, 40));

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1010, 590));

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtFacturaNumero.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        txtFacturaNumero.setText("NOTA DE CREDITO DE VENTA");
        jPanel2.add(txtFacturaNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 500, 30));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 50, 50));

        txtFacturaNumero1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        txtFacturaNumero1.setText("N° de factura");
        jPanel2.add(txtFacturaNumero1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 150, 40));

        txtNroFactura.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        txtNroFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNroFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNroFacturaActionPerformed(evt);
            }
        });
        txtNroFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroFacturaKeyTyped(evt);
            }
        });
        jPanel2.add(txtNroFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 110, 40));

        btnBuscarFactura.setBackground(new java.awt.Color(93, 116, 163));
        btnBuscarFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoLupaF3.png"))); // NOI18N
        btnBuscarFactura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFacturaActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscarFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 60, 39));

        comboTipoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoVentaActionPerformed(evt);
            }
        });
        jPanel2.add(comboTipoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 190, 40));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 630, 100));

        calendario.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jPanel4.add(calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 200, 40));

        comboVendedor.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboVendedorActionPerformed(evt);
            }
        });
        jPanel4.add(comboVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 230, 38));

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
        jPanel4.add(btnBuscarVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 50, 60, 38));

        jLabel14.setBackground(new java.awt.Color(239, 255, 239));
        jLabel14.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel14.setText("Vendedor");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, 110, 40));

        txtCodigoArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoArticuloActionPerformed(evt);
            }
        });
        jPanel4.add(txtCodigoArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 40, 40));

        txtPrecioArticulo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtPrecioArticulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioArticulo.setAlignmentX(2.0F);
        txtPrecioArticulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        txtPrecioArticulo.setMargin(new java.awt.Insets(2, 6, 2, 6));
        txtPrecioArticulo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                txtPrecioArticuloComponentMoved(evt);
            }
        });
        txtPrecioArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioArticuloActionPerformed(evt);
            }
        });
        txtPrecioArticulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioArticuloKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioArticuloKeyTyped(evt);
            }
        });
        jPanel4.add(txtPrecioArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 40, 40));

        panelImage1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 700));

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("File");

        itemBuscarVendedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itemBuscarVendedor.setText("Buscar vendedor");
        itemBuscarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarVendedorActionPerformed(evt);
            }
        });
        jMenu1.add(itemBuscarVendedor);

        itemBuscarFactura.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        itemBuscarFactura.setText("Buscar factura");
        itemBuscarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarFacturaActionPerformed(evt);
            }
        });
        jMenu1.add(itemBuscarFactura);

        itemBuscarArticulo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        itemBuscarArticulo.setText("Buscar articulo");
        itemBuscarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarArticuloActionPerformed(evt);
            }
        });
        jMenu1.add(itemBuscarArticulo);

        jMenuBar1.add(jMenu1);

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
        //SEGUN EL NOMBRE QUE TENGO SELECCIONADO EN EL COMBO VENDEDOR, CARGO EL CODIGO DEL VENDEDOR QUE VA EN LA TABLA NOTA_CREDITO
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
            txtCodigoEmpleado.setText(cod_empleado);
            conn.close();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
        //RESTRICCIONES SI NO HAY UN VENDEDOR Y UN CLIENTE SELECCIONADO NO DEJA CARGAR ARTICULOS
        if(comboCliente.getSelectedItem().equals("SELECCIONE CLIENTE") || comboVendedor.getSelectedItem().equals("SELECCIONE EMPLEADO")){
            comboArticulo.setEnabled(false);
            cant.setEditable(false);
            btnAgregarArticulo.setEnabled(false);
            btnQuitarArticulo.setEnabled(false);
            btnBuscarArticulo.setEnabled(false);
        }else{
            comboArticulo.setEnabled(true);
            cant.setEditable(true);
            btnAgregarArticulo.setEnabled(true);
            btnQuitarArticulo.setEnabled(true);
            btnBuscarArticulo.setEnabled(true);
        }
    }//GEN-LAST:event_comboVendedorActionPerformed

    private void btnBuscarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVendedorActionPerformed
        //LLAMO AL FORM DE LOS VENDEDORES
        enviar_empleado form= new enviar_empleado ();
        form.setVisible(true);
        form.toFront();
        enviar_empleado.txt_recibeEmpleado.setText("1");
        enviar_empleado.txt_recibeEmpleado.setVisible(false);
        enviar_empleado.enviarEmpleado.setVisible(false);
    }//GEN-LAST:event_btnBuscarVendedorActionPerformed

    private void comboClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClienteActionPerformed
        comboContribuyente.addItem("SELECCIONAR");
        comboContribuyente.setSelectedIndex(0);
        //SEGUN EL NOMBRE QUE TENGO SELECCIONADO EN EL COMBO CLIENTE, CARGO EL CODIGO DEL CLIENTE QUE VA EN LA TABLA NOTA_CREDITO
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            nomcliente = (String)comboCliente.getSelectedItem();
            codigocliente=null;
            String tipoContribuyente="";
            ResultSet rs = consulta.executeQuery("SELECT cod_cliente, cuit, contribuyente, dirrecion, localidad FROM cliente WHERE nombres = '"+nomcliente+"'" );
            while (rs.next()) {
                codigocliente= rs.getString(1);
                txtDomicilio.setText(rs.getString(4)+", "+rs.getString(5));
                txtCuit.setText(rs.getString(2));
                if(rs.getString(3).equals("")){
                    tipoContribuyente="Consumidor Final";
                }else{
                    tipoContribuyente=rs.getString(3);
                }
            }
            comboContribuyente.setSelectedItem(tipoContribuyente);
                   
            txtCodigoCliente.setText(codigocliente);
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error SQL ");
        }
        //RESTRICCIONES PARA DEJARME CARGAR UN ARTICULO
        if(comboCliente.getSelectedItem().equals("SELECCIONE CLIENTE") || comboVendedor.getSelectedItem().equals("SELECCIONE EMPLEADO") || comboFactura.getSelectedItem().equals("SELECCIONAR")){
            comboArticulo.setEnabled(false);
            cant.setEditable(false);
            btnAgregarArticulo.setEnabled(false);
            btnQuitarArticulo.setEnabled(false);
            btnBuscarArticulo.setEnabled(false);
        }else{
            comboArticulo.setEnabled(true);
            cant.setEditable(true);
            btnAgregarArticulo.setEnabled(true);
            btnQuitarArticulo.setEnabled(true);
            btnBuscarArticulo.setEnabled(true);
        }
        
        //SEGUN EL TIPO DE FACTURA QUE SELECCIONO SE SETEAN LOS TEXTFIELD DE IVA
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
    }//GEN-LAST:event_comboClienteActionPerformed

    public void cargarComboEmpleado(){
       
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            nomempleado = (String)comboVendedor.getSelectedItem();
            cod_empleado=null;
            
            String cadena=comboVendedor.getSelectedItem().toString();
            //TRAIGO EL NOMBRE Y APELLIDO DEL VENDEDOR DESDE EL COMBO, LO DIVIDO EN 2 CADENAS PORQUE EN LA BD ESTAN SEPARADOS Y BUSCO SU CODIGO EN LA BD PARA LUEGO GUARDARLOS EN L A FACTURA
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
        //SEGUN EL NOMBRE QUE TENGO SELECCIONADO EN EL COMBO ARTICUKO, CARGO EL CODIGO DEL ARTICULO QUE VA EN LA TABLA NOTA_CREDITO
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            nomproducto = (String)comboArticulo.getSelectedItem();
            codigoproducto=null;
            ResultSet rs = consulta.executeQuery("SELECT cod_articulo FROM articulo WHERE (referencia = '"+comboArticulo.getSelectedItem()+"')");
            while (rs.next()) {
                codigoproducto= rs.getString(1);
            }
            txtCodigoArticulo.setText(codigoproducto);
            conn.close();  // Cierra la conexión

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
        cant.requestFocus();
    }//GEN-LAST:event_comboArticuloActionPerformed

    private void btnBuscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarArticuloActionPerformed
        //BUSCO LAS FACTURAS QUE SE REALIZARON PARA REALIZAR LA DEVOLUCION DEL PRODUCTO
        enviar_producto_credito_compra form= new enviar_producto_credito_compra ();
        form.setVisible(true);
        form.toFront();
        form.txt_recibe.setText(txtNroFactura.getText());
        form.cargarTabla(txtNroFactura.getText(),"VENTA");
        form.labelFactura.setText("ARTICULOS DE LA FACTURA N° "+txtNroFactura.getText());
        form.enviarProducto.setVisible(false);
    }//GEN-LAST:event_btnBuscarArticuloActionPerformed

    private void btnAgregarArticuloMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarArticuloMouseEntered

    }//GEN-LAST:event_btnAgregarArticuloMouseEntered

    private void btnAgregarArticuloMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarArticuloMouseExited

    }//GEN-LAST:event_btnAgregarArticuloMouseExited
    public void agregarArticulo(){
        //BOTON PARA AGREGAR LOS ARTICULOS A LA TABLA
        
        Double variableIva=1.00, precioConVariable=0.00;
        
        //SEGUN EL VALOR DEL IVA SETEO LAS VARIABLES QUE VAN A REALIZAR LOS CALCULOS
        if(txtIva2.getText().equals("19.00")){
            variableIva=1.19;
        }else if(txtIva2.getText().equals("0.00")){
                     variableIva=1.00;
              }
        
        
        Double subtotal2=0.0,subTotalBrutoArt=0.0, porcentIva=0.0;
        int pe=0,fil = tabla.getRowCount();
        //VERIFICO SI EL ARTICULO YA HA SIDO INGRESADO EN LA FACTURA
        for (int x=0;x<=fil-1;x++) {
            String aux= (String) (tabla.getValueAt(x,2));
            if(aux.equals(txtNombreArticulo.getText()))
            {
                pe=1;
            }
        }
        if(pe==1){
            JOptionPane.showMessageDialog(null,"Este Articulo Ya Existe en la factura, solo debe modificar la cantidad") ;
        }else{
            if (txtNombreArticulo.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Falta elegir el articulo");
            }else{
                if (cant.getText().equals("") || cant.getText().equals("0") || cant.getText().equals("0.0")){
                    JOptionPane.showMessageDialog(null, "Debe Digitar la Cantidad de articulo a Llevar");
                    cant.requestFocus();
                }else{
                    try{
                        Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                        Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                        ResultSet   res= consulta.executeQuery("select cantidad from referencia_factura where codigo_producto = '"+txtCodigoArticulo.getText()+"' AND cod_factura='"+txtNroFactura.getText()+"'");
                        while(res.next()){
                            cant_ex=Double.parseDouble(res.getString(1));
                            break;
                        }
                        res.close();
                        
                    }catch(SQLException e){
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null,"Error en el SQL") ;
                    }
                    //SI LA CANTIDAD QUE DESEA INGRESAR DEL ARTICULO ES MAYOR A LA CANTIDAD REGISTRADA EN LA FACTURA MUESTRA UN MENSAJE Y SETEA CANT CON 
                    //LA CATIDAD QUE ESTABA REGISTRADA EN LA FACTURA
                    double cantidad= Double.parseDouble(cant.getText().replace(',','.'));
                    if (cant_ex < cantidad)
                    {
                        JOptionPane.showMessageDialog(null, "En la factura de venta N° "+txtNroFactura.getText()+" no existe la cantidad ingresada del articulo. La Cantidad Dispomible es: "+cant_ex);
                        cant.setText(""+cant_ex);
                        cant.requestFocus();
                    }
                    else
                    {
                        fila = tabla.getRowCount();
                        columna = tabla.getColumnCount();
                        DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                        //AGREGO EL ARTICULO A LA TABLA
                        modelo.addRow( new Object [] {null,null,null,null,null,null,null,null,null});
                        tabla.setValueAt(cantidad,fila,0);
                        tabla.setValueAt(txtCodigoArticulo.getText(),fila,1);
                        tabla.setValueAt(txtNombreArticulo.getText(),fila,2);
                        try{
                            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

                            ResultSet r= consulta.executeQuery("select valor_unitario, unitarioBruto from referencia_factura where (codigo_producto= '"+txtCodigoArticulo.getText()+"' OR referencia like '"+txtNombreArticulo.getText()+"') AND cod_factura='"+txtNroFactura.getText()+"'");
                            precio="0.0";precioBruto="0.0";
                            while(r.next()){
                                precio=r.getString(1);
                                precioBruto=r.getString(2);
                            }
                            
                            precioConVariable = Double.parseDouble(precio);  //TRANSFORMO EL PRECIO CON IVA ( SI ES FACTURA A DEBITO EL PORCENTAJE DE IVA) SI ES FACTURA B DIVIDE POR CERO
                            precioConVariable=precioConVariable/variableIva;

                        }catch(SQLException e){
                            JOptionPane.showMessageDialog(null,"Este Articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
                        }

                        tabla.setValueAt(txtPrecioArticulo.getText(),fila,3); //VALOR UNITARIO
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
                        //SON LOS CALCULOS PARA REGISTRAR LOS TOTALES
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
                        
                        //ACTAULIZO LOS VALORES TOTAL Y SUBTOTAL
                        sub.setText(String.format("%.2f",subtotal33).replace(",", "."));
                        total.setText(String.format("%.2f",subtotalConIva).replace(",", "."));
                        total2.setText(""+String.format("%.2f",subtotalConIva));
                        txtTotalBrutoArt.setText(""+String.format("%.2f",subTotalBrutoArt));
                        
                        //RESETEO LOS COMBOS
                        comboArticulo.setSelectedIndex(0);
                        cant.setText("");
                        txtNombreArticulo.setText("");
                        txtPrecioArticulo.setText("");
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
        Double subtotal2=0.0,subTotalBrutoArt=0.0, porcentIva=0.0;
        //BOTON PARA ELIMINAR EL ULTIMO ARTICULO INGRESADO
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
                //SON LOS CALCULOS PARA REGISTRAR LOS TOTALES
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
                        
                //ACTAULIZO LOS VALORES TOTAL Y SUBTOTAL
                sub.setText(String.format("%.2f",subtotal33).replace(",", "."));
                total.setText(String.format("%.2f",subtotalConIva).replace(",", "."));
                total2.setText(""+String.format("%.2f",subtotalConIva));
                txtTotalBrutoArt.setText(""+String.format("%.2f",subTotalBrutoArt));

                fila-=1;
                int filat=tabla.getRowCount();
                if(filat==0){
                    txtTotalBrutoArt.setText("0,00");
                }else{
                    txtTotalBrutoArt.setText(tabla.getValueAt(fila, 8).toString());
                }
                if (filat==0){
                    comboVendedor.setEnabled(true);
                    comboCliente.setEnabled(true);
                }
            }catch (ArrayIndexOutOfBoundsException e) {        
                //JOptionPane.showMessageDialog(null, "No Hay Mas Productos Que Quitar");          
            }
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

    private void tablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyReleased
        modificarArticuloFactura();
    }//GEN-LAST:event_tablaKeyReleased
    
    public void modificarArticuloFactura(){
        //CUANDO EDITO LA CANTIDAD O  EL VALOR UNITARIO DENTRO DE LA TABLA
        int Select=tabla.getSelectedRow(), bandera=0;
        Double subtotal2=0.0;   
        subTotalBrutoArticulo=0.0;
        int fila2 = tabla.getRowCount();
        subtotal=0;
        for (int x=0;x<=fila2-1;x++) {
            try{
                Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                ResultSet   res= consulta.executeQuery("select cantidad from referencia_factura where codigo_producto = '"+tabla.getValueAt(Select,1).toString()+"' AND cod_factura='"+txtNroFactura.getText()+"'");
                //SI INTENTO INGRESAR UNA CANTIDAD QUE NO ESTA EN STOCK NO ME DEJA
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
                JOptionPane.showMessageDialog(null, "¡ATENCION! La Cantidad Disponible de Dicho Articulo en la factura N° "+txtNroFactura.getText()+" es "+cant_ex+" unidades");
                bandera=1;
                tabla.setValueAt(cant_ex,Select,0);
            }

            //REALIZO NUEVAMENTE LOS CALCULOS CON LOS NUEVIS VALORES INGRESADOS
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
        
        //SETEO LOS SUBTOTAL Y TOTAL CON LOS NUEVOS VALORES
        sub.setText(String.format("%.2f",subBruto).replace(",", "."));
        total.setText(String.format("%.2f",subtotal).replace(",", "."));
        total2.setText(""+String.format("%.2f",subtotal).replace(",", "."));
        txtTotalBrutoArt.setText(String.format("%.2f",subTotalBrutoArticulo).replace(",", "."));
        
        comboArticulo.setSelectedIndex(0);
        cant.setText(""); 
    }
    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        modificarArticuloFactura();
    }//GEN-LAST:event_tablaMouseClicked

    private void comboPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPagoActionPerformed

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

    private void btn_guardaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardaraActionPerformed
       //GUARDO LA NOTA DE CREDITO EN LA BD
        int banderaFactura=0;
        String tipoFacturaString="",titularEmpresa="", nombreEmpresa="",direccionEmpresa="",cuitEmpresa="",contribuyenteEmpresa="",telefonoEmpresa="",ingresosBrutos="",inicioActividades="";
        String nombreCliente="",cuitCliente="",tipoCliente="",direccionCliente="", puntoVenta="";
        int tipoFacturaInt=0;
        txtCuit.setBackground(new Color(255,255,255));
        txtNroFactura.setBackground(new Color(255,255,255));
        try {
            if (comboVendedor.getSelectedItem().equals("SELECCIONE EMPLEADO")) {
                 banderaFactura=1;
                JOptionPane.showMessageDialog(null, "Falta elegir el Empleado","Advertencia",JOptionPane.WARNING_MESSAGE);
            }else {
                if (comboCliente.getSelectedItem().equals("SELECCIONE CLIENTE")) {
                     banderaFactura=1;
                    JOptionPane.showMessageDialog(null, "Falta elegir el Cliente","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else {
                    if (comboFactura.getSelectedItem().equals("SELECCIONAR")) {
                         banderaFactura=1;
                        JOptionPane.showMessageDialog(null, "Falta elegir el tipo de factura","Advertencia",JOptionPane.WARNING_MESSAGE);
                    }else if (comboContribuyente.getSelectedItem().equals("SELECCIONAR")) {
                            banderaFactura=1;
                            JOptionPane.showMessageDialog(null, "Falta elegir el tipo de contribuyente","Advertencia",JOptionPane.WARNING_MESSAGE);
                         }else { 
                            if (comboFactura.getSelectedItem().equals("FACTURA A") && !comboContribuyente.getSelectedItem().equals("Responsable Inscripto")) {
                               banderaFactura=1;
                               JOptionPane.showMessageDialog(null, "Las notas de credito de tipo A solo se pueden realizar a otros Responsables Inscriptos","Advertencia",JOptionPane.WARNING_MESSAGE);
                            }else { 
                                if (comboFactura.getSelectedItem().equals("FACTURA B") && comboContribuyente.getSelectedItem().equals("Responsable Inscripto")) {
                                   banderaFactura=1;
                                   JOptionPane.showMessageDialog(null, "Las notas de credito de tipo B solo se pueden realizar a Responsables Monotributo o Consumidores finales","Advertencia",JOptionPane.WARNING_MESSAGE);
                                }else  {
                                    if (txtCuit.getText().length()!=11 && !comboContribuyente.getSelectedItem().equals("Consumidor Final")) {
                                        banderaFactura=1;
                                        JOptionPane.showMessageDialog(null, "El campo CUIT debe contener 11 digitos","Advertencia",JOptionPane.WARNING_MESSAGE);
                                        txtCuit.setBackground(Color.yellow);
                                        txtCuit.requestFocus();
                                    }else { 
                                        if (calendario.getDate() == null) {
                                             banderaFactura=1;
                                            JOptionPane.showMessageDialog(null, "La fecha estaba vacia","Advertencia",JOptionPane.WARNING_MESSAGE);
                                            setearFecha();
                                        }else {
                                            if (txtNroFactura.getText().equals("")) {
                                                 banderaFactura=1;
                                                JOptionPane.showMessageDialog(null, "Debe Digitar Numero de la Factura con la que se relaciona la nota de credito","Advertencia",JOptionPane.WARNING_MESSAGE);
                                                txtNroFactura.setBackground(Color.yellow);
                                                txtNroFactura.requestFocus();
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
                                                        
                                                        //ESTOS DATOS SON PARA PONER EL LA FACTURA
                                                        tipoFacturaString=comboFactura.getSelectedItem().toString(); //TIPO DE FACTURA 1.A,6.B
                                                        if (tipoFacturaString.equals("FACTURA A")){
                                                            tipoFacturaString="A";
                                                            tipoFacturaInt=3; //nota de credito A (FACT ELECTRONICA)
                                                        }else{
                                                            tipoFacturaString="B";  
                                                            tipoFacturaInt=8;//nota de credito B (FACT ELECTRONICA)
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

                                                        int año = calendario.getCalendar().get(Calendar.YEAR);
                                                        int mes = calendario.getCalendar().get(Calendar.MONTH);
                                                        int dia = calendario.getCalendar().get(Calendar.DAY_OF_MONTH);
                                                        
                                                        //ACOMODO LA FECHA PARA GUARDARLA EN LA BD
                                                        if((mes+1)<10 && (dia>=10)){
                                                            fecha = (año+"/0"+(mes+1)+"/"+dia);
                                                        }else{
                                                            if(((mes+1)<10 && (dia<10))){
                                                                  fecha = (año+"/0"+(mes+1)+"/0"+dia);
                                                            }else{
                                                                   fecha = (año+"/"+(mes+1)+"/"+dia);
                                                            }
                                                        }

                                                        Double variableIva=0.00, porcentIva=0.0,porcentajeIvaFactura=0.0;
                                                        int tipoPorcentajeIva=0; 
                                                        String ivaFact="";
                                                        //SEGUN QUE TIPO DE IVA ESTE SELECCIONADO SETEO LAS VARIABLES CON LAS QUE VOY A REALIZAR LOS CALCULOS
                                                        if(comboIva.getSelectedItem().toString().equals("19%")){
                                                            variableIva=1.19;
                                                            porcentIva=0.19;
                                                            ivaFact="19.00";
                                                            porcentajeIvaFactura=19.0;
                                                            tipoPorcentajeIva=5; //CODIGO EN LA TABLA DE EXCEL DEL IVA (FACT ELECTRONICA)
                                                        }else if(comboIva.getSelectedItem().toString().equals("0%")){
                                                                    variableIva=1.00;
                                                                    porcentIva=0.00;
                                                                    ivaFact="0.00";
                                                                    porcentajeIvaFactura=0.0;
                                                                    tipoPorcentajeIva=3;//CODIGO EN LA TABLA DE EXCEL DEL IVA 0 (FACT ELECTRONICA)
                                                               }
                                                        Double subTotalNeto=Double.parseDouble(sub.getText());

                                                        Double TotalNeto=Double.parseDouble(total2.getText().replace(",","."));
                                                        String TotalNetoString=String.format("%.2f", TotalNeto).replace(",", ".");//multiplico por -1 para guardarlo como numero negativo

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

                                                        inabilita();

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


                                                       
                                                        //GUARDO LOS DATOS EN LA TABLA NOTA CREDITO EN LA BD
                                                        consulta.executeUpdate("insert into nota_credito (cod_nota_credito, fecha,cod_cliente,cod_empleado,tipo_pago,condicion,iva,tipo_nota_credito,total_con_iva,total_sin_iva,ivaDiscriminado,referencia_factura) values('"+fact.getText()+"','"+fecha+"','"+txtCodigoCliente.getText()+"','"+txtCodigoEmpleado.getText()+"','"+comboPago.getSelectedItem()+"','ACTIVA','"+ivaFactura+"','"+tipoFacturaString+"','"+TotalNetoString+"','"+subTotalNetoString+"','"+txtIva2.getText().replace(",",".")+"','"+txtNroFactura.getText().replace(",",".")+"')");
                                                        //GUARDO LOS ARTICULOS DE LA TABLA EN REFERENCIA NOTA CREDITO EN LA BD
                                                        for (x=0;x<=fil-1;x++) {
                                                            consulta1.executeUpdate("insert into referencia_nota_credito (cod_nota_credito,codigo_producto,valor_unitario,valor_total,referencia,cantidad,Total,unitarioBruto,totalBruto,descuento) values('"+fact.getText()+"','"+tabla.getValueAt(x,1)+"'  ,'"+tabla.getValueAt(x,3)+"','"+tabla.getValueAt(x,6).toString().replace(",",".")+"','"+tabla.getValueAt(x,2)+"','"+tabla.getValueAt(x,0)+"','"+total2.getText().replace(",",".")+"','"+tabla.getValueAt(x,7)+"','"+txtTotalBrutoArt.getText().replace(",",".")+"','"+tabla.getValueAt(x,5)+"')");
                                                        }
                                                        
                                                        //SI LA DEVOLUCION SE REALIZA EN EFECTIVO LO GUARDO EN LA TABLA DEVOLUCION VENTA PARA QUE APAREZCA EN EL CIERREDE CAJA
                                                        String tipoPago=comboPago.getSelectedItem().toString();
                                                        if(tipoPago.equals("EFECTIVO")){
                                                            String codigoCaja="";
                                                            try{
                                                                Connection conn2= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                                                                Statement consulta2=conn2.createStatement(); // crea una variable que se encargue del código de sql
                                                                
                                                                //SELECCIONO EL CODIGO DE LA CAJA ABIERTA SELECCIONADA EN EL comboCaja PARA GUARDARLA EN EL PAGO DE EFECTIVO
                                                                ResultSet  res22= consulta2.executeQuery("select cod_caja from apertura_caja WHERE estado='ABIERTA' AND nombre_caja='"+comboCaja.getSelectedItem()+"'");
                                                                res22.next();
                                                                codigoCaja=res22.getString(1);
                                                                
                                                                consulta.executeUpdate("INSERT INTO devolucion_venta (cod_caja,cod_venta,fecha,monto_efectivo) VALUES ('"+codigoCaja+"','"+codigoCaja+"','"+fecha+"','"+TotalNetoString+"')");

                                                            }catch(SQLException e){
                                                                System.out.println(e);
                                                                JOptionPane.showMessageDialog(null,"Error en el SQL") ;
                                                            }

                                                        }
                                                        //********FIN PARTE FACTURACION ELECTRONICA***************************************************************************************************************************************************************************************************************************************************************************************************

                                                        Connection miconexion = conexion.ObtenerConexion();
                                                        Map parametros = new HashMap();
                                                        parametros.put("codf",fact.getText());
                                                        //IMPRIMO EL REPORTE
                                                        int op=JOptionPane.showConfirmDialog(null, "DESEA IMPRIMIR LA NOTA DE CREDITO","SELECCIONE UN OPCION" ,JOptionPane.YES_NO_OPTION);
                                                        if (op==JOptionPane.YES_NO_OPTION){
                                                           try {
                                                                this.setVisible(false);
                                                                String reporte="notaCredito.jasper";
                                                                JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                                                                JasperViewer ventanavisor=new JasperViewer(informe,false);
                                                                ventanavisor.setTitle("Reporte de nota de credito de venta");
                                                                ventanavisor.setVisible(true);
                                                            } catch (Exception e) {
                                                                JOptionPane.showMessageDialog(this, e.getMessage());
                                                            }
                                                        }else
                                                        {
                                                        // no hacer nada
                                                        }                                                   

                                                        for (x=0;x<=fil-1;x++) {
                                                            String aux=(tabla.getValueAt(x,0)).toString();

                                                            ResultSet   res= consulta.executeQuery("select cantidad from articulo where cod_articulo='"+tabla.getValueAt(x,1)+"'");
                                                            while(res.next()){
                                                                cant_ex=Double.parseDouble(res.getString(1));
                                                            }
                                                            //AGREGO EL ARTICULO QUE ME DEVOLVIERON AL STOCK
                                                            nueva_cant = cant_ex + Double.parseDouble(aux);
                                                            consulta.executeUpdate("UPDATE articulo SET cantidad='"+nueva_cant+"' WHERE cod_articulo='"+tabla.getValueAt(x,1)+"'");
                                                        }
                                                        
                                                        //VACIO LA TABLA
                                                        for (x=0;x<=fil-1;x++) {
                                                            for (y=0;y<=col-1;y++) {
                                                                tabla.setValueAt("",x,y);
                                                            }
                                                        }
                                                        //BORRO LAS FILAS DE LA TABLA
                                                        for (x=fil-1;x>=0;x--) {
                                                            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                                                            modelo.removeRow(x);
                                                        }

                                                    } catch(SQLException e){
                                                        JOptionPane.showMessageDialog(null,"Este Producto Ya Existe") ;
                                                        System.out.println(e);
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
            if (banderaFactura==0){
                nuevaFactura(); //ARRANCO UNA NUEVA FACTURA
            }
            
        }catch (ArrayIndexOutOfBoundsException e) {
            
        }
    }//GEN-LAST:event_btn_guardaraActionPerformed

    private void b42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b42ActionPerformed
        this.dispose();
    }//GEN-LAST:event_b42ActionPerformed

    private void comboFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFacturaActionPerformed
        //RESTRICCIONES PARA PERMITIRME LA CARGA DE ARTICULOS
        if(comboFactura.getSelectedItem().equals("SELECCIONAR") || comboCliente.getSelectedItem().equals("SELECCIONE CLIENTE") || comboVendedor.getSelectedItem().equals("SELECCIONE EMPLEADO")){
            comboArticulo.setEnabled(false);
            cant.setEditable(false);
            btnAgregarArticulo.setEnabled(false);
            btnQuitarArticulo.setEnabled(false);
            btnBuscarArticulo.setEnabled(false);
        }else{
            comboArticulo.setEnabled(true);
            cant.setEditable(true);
            btnAgregarArticulo.setEnabled(true);
            btnQuitarArticulo.setEnabled(true);
            btnBuscarArticulo.setEnabled(true);
        }
        //DEPENDE EL TIPO DE FACTURA SELECCIONADA SETEO LOS IVA (DESP SECAMBIA AL CAMBIAR EL IVA DEL COMBOBOX)
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
        int fil = tabla.getRowCount();
        int x;
        //BORRA TODAS LAS FILAS
        for (x=fil-1;x>=0;x--) {
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            modelo.removeRow(x);
        }
        cant.setText("");
        sub.setText("0.00");
        total.setText("0.00");
    }//GEN-LAST:event_comboFacturaActionPerformed

    private void txtTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoActionPerformed

    private void txtCuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuitActionPerformed

    private void checkDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDescuentoActionPerformed
        //SEGUN ESTE HABILITADO EL DESCUENTO INDIVIDUAL ACTIVO O DESACTIVO EL TEXTFIELD DESCUENTO
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

    private void txtCodigoArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoArticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoArticuloActionPerformed

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
        //ESTE TXT  ESTA OCULTO PERO IGUAL REALIZA LOS CALCULOS (NO BORRAR)
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
        //SEGUN EL IVA QUE TENGO SELECCIONADO SETEA EL TXT IVA QUE DESPUES USO PARA REALIZAR LOS CALCULOS
        String ivaFactura="";
        if(comboIva.getSelectedItem().equals("19%")){
            txtIva.setText("19.00");
            ivaFactura="19.00";
        }else if(comboIva.getSelectedItem().equals("0%")){
                   txtIva.setText("0.00");
                   ivaFactura="0.00";
               }
        //SI ES FACTURA A TEGO QUE DISCRIMINAR EL IVA
        if(comboFactura.getSelectedItem().equals("FACTURA A")){
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
    }//GEN-LAST:event_comboIvaActionPerformed

    private void comboContribuyenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboContribuyenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboContribuyenteActionPerformed

    private void checkDescuentoGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDescuentoGeneralActionPerformed
        //ESTO ES PARA CUANDO DESELECCIONO EL CHECK DE DESCUENTO GENERAL LOS VALORES DE LOS ARTICULOS VUELVAN A COMO ESTABAN SIN DESCUENTO
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
        //PARA APLICAR EL DESCUENTO GENERAL INGRESADO A TODOS LOS ARTICULOS QUE ESTAN EN LA TABLA
        int bandera=0;
        String desc,desc2, porcentajeDesc ;
        double valorDescontado=0.0;
        fila = tabla.getRowCount();
        columna = tabla.getColumnCount();
        
        if(txtDescuentoGeneral.getText().equals("")){
            desc="0.00";
            desc2="0.00";
        }else{
            desc = txtDescuentoGeneral.getText().replace(".", ",")+".00";
            desc2 = txtDescuentoGeneral.getText().replace(".", ",")+".00";
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
        total.setText(String.format("%.2f",subtotalConIva).replace(",", "."));
        total2.setText(""+String.format("%.2f",subtotalConIva));
        txtTotalBrutoArt.setText(""+String.format("%.2f",subTotalBrutoArt));
        txtDtoGral.setText("$"+String.format("%.2f",valorDescontado).replace(",", "."));
    }//GEN-LAST:event_txtDescuentoGeneralActionPerformed

    private void txtCuitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuitKeyTyped
        if(txtCuit.getText().length()>=11){
            evt.consume();
        }
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
        evt.consume();
    }//GEN-LAST:event_txtCuitKeyTyped

    private void txtNroFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNroFacturaActionPerformed

    private void txtNroFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroFacturaKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
        evt.consume();
    }//GEN-LAST:event_txtNroFacturaKeyTyped

    private void btnBuscarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFacturaActionPerformed
        //LLAMO AL FORM QUEME MUESTRA LAS FACTURAS REALIZADAD
        new mostrar_facturas ().setVisible(true);
        mostrar_facturas.txtRecibe.setText("2");
    }//GEN-LAST:event_btnBuscarFacturaActionPerformed

    private void txtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteActionPerformed

    private void txtPrecioArticuloComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_txtPrecioArticuloComponentMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioArticuloComponentMoved

    private void txtPrecioArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioArticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioArticuloActionPerformed

    private void txtPrecioArticuloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioArticuloKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioArticuloKeyPressed

    private void txtPrecioArticuloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioArticuloKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioArticuloKeyTyped

    private void txtNombreArticuloComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_txtNombreArticuloComponentMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreArticuloComponentMoved

    private void txtNombreArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreArticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreArticuloActionPerformed

    private void txtNombreArticuloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreArticuloKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreArticuloKeyPressed

    private void txtNombreArticuloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreArticuloKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreArticuloKeyTyped

    private void itemBuscarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarVendedorActionPerformed
       //ESTOS ITEMS SON PARA AGREGARLOS ACCESOS RAPIDOS F3 F5 ETC
        enviar_empleado form= new enviar_empleado ();
        form.setVisible(true);
        form.toFront();
        enviar_empleado.txt_recibeEmpleado.setText("1");
        enviar_empleado.txt_recibeEmpleado.setVisible(false);
        enviar_empleado.enviarEmpleado.setVisible(false);
    }//GEN-LAST:event_itemBuscarVendedorActionPerformed

    private void itemBuscarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarFacturaActionPerformed
        //ESTOS ITEMS SON PARA AGREGARLOS ACCESOS RAPIDOS F3 F5 ETC
        new mostrar_facturas ().setVisible(true);
        mostrar_facturas.txtRecibe.setText("2");
    }//GEN-LAST:event_itemBuscarFacturaActionPerformed

    private void itemBuscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarArticuloActionPerformed
        //ESTOS ITEMS SON PARA AGREGARLOS ACCESOS RAPIDOS F3 F5 ETC
        enviar_producto_credito_compra form= new enviar_producto_credito_compra ();
        form.setVisible(true);
        form.toFront();
        form.txt_recibe.setText(txtNroFactura.getText());
        form.cargarTabla(txtNroFactura.getText(),"VENTA");
        form.labelFactura.setText("ARTICULOS DE LA FACTURA N° "+txtNroFactura.getText());
        form.enviarProducto.setVisible(false);
    }//GEN-LAST:event_itemBuscarArticuloActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Double subtotal2=0.0,subTotalBrutoArt=0.0, porcentIva=0.0;
        //BOTON PARA ELIMINAR EL ULTIMO ARTICULO INGRESADO
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
                //SON LOS CALCULOS PARA REGISTRAR LOS TOTALES
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
                        
                //ACTAULIZO LOS VALORES TOTAL Y SUBTOTAL
                sub.setText(String.format("%.2f",subtotal33).replace(",", "."));
                total.setText(String.format("%.2f",subtotalConIva).replace(",", "."));
                total2.setText(""+String.format("%.2f",subtotalConIva));
                txtTotalBrutoArt.setText(""+String.format("%.2f",subTotalBrutoArt));

                fila-=1;
                int filat=tabla.getRowCount();
                if(filat==0){
                    txtTotalBrutoArt.setText("0,00");
                }else{
                    txtTotalBrutoArt.setText(tabla.getValueAt(fila, 8).toString());
                }
                if (filat==0){
                    comboVendedor.setEnabled(true);
                    comboCliente.setEnabled(true);
                }
            }catch (ArrayIndexOutOfBoundsException e) {        
                //JOptionPane.showMessageDialog(null, "No Hay Mas Productos Que Quitar");          
            }
        }else{
            int filat=tabla.getRowCount();
            if(filat==0){
                JOptionPane.showMessageDialog(null, "NO HAY PRODUCTOS PARA QUITAR"); 
            }else{
                JOptionPane.showMessageDialog(null, "SELECCIONE LA FILA QUE DESEA QUITAR");   
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void comboTipoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTipoVentaActionPerformed

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
            java.util.logging.Logger.getLogger(Factura_Nota_Credito_Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Factura_Nota_Credito_Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Factura_Nota_Credito_Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Factura_Nota_Credito_Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Factura_Nota_Credito_Venta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu QuitarArticulo;
    private javax.swing.JButton b42;
    private javax.swing.JButton btnAgregarArticulo;
    private javax.swing.JButton btnBuscarArticulo;
    private javax.swing.JButton btnBuscarFactura;
    private javax.swing.JButton btnBuscarVendedor;
    private javax.swing.JButton btnQuitarArticulo;
    private javax.swing.JButton btn_guardara;
    private com.toedter.calendar.JDateChooser calendario;
    public static javax.swing.JTextField cant;
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
    private javax.swing.JMenuItem itemBuscarArticulo;
    private javax.swing.JMenuItem itemBuscarFactura;
    private javax.swing.JMenuItem itemBuscarVendedor;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    public static javax.swing.JTextField sub;
    private javax.swing.JTable tabla;
    public static javax.swing.JTextField total;
    private javax.swing.JTextField total2;
    public static javax.swing.JTextField txtCliente;
    public static javax.swing.JTextField txtCodigoArticulo;
    public static javax.swing.JTextField txtCodigoCliente;
    public static javax.swing.JTextField txtCodigoEmpleado;
    public static javax.swing.JTextField txtCuit;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtDescuentoGeneral;
    public static javax.swing.JTextField txtDomicilio;
    private javax.swing.JTextField txtDtoGral;
    private javax.swing.JLabel txtFacturaNumero;
    private javax.swing.JLabel txtFacturaNumero1;
    public static javax.swing.JTextField txtIva;
    public static javax.swing.JTextField txtIva2;
    private javax.swing.JTextField txtIvaFactura;
    public static javax.swing.JTextField txtNombreArticulo;
    public static javax.swing.JTextField txtNroFactura;
    public static javax.swing.JTextField txtPrecioArticulo;
    public static javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtTotalBrutoArt;
    // End of variables declaration//GEN-END:variables
}
