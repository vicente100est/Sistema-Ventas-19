
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


public class Factura_Compra extends javax.swing.JFrame {
    //VARIABLES GLOBALES
    String fecha="";
    String cantidadActual="";
    double subtotal=0, subtotalBruto=0, subTotalBrutoArticulo=0;
             String codigoproveedor;
             String nomproveedor;
             String codigoproducto;
             String codigoproducto_e;
             double cant_ex=0;
             String precio,precioBruto;
             double nueva_cant =0;
             String nomproducto;
             int fila;
             int columna;
             String cod_empleado;
             String nomempleado;
             String codigoProveedor;
             String nomProveedor;
             String NOMBRE;
             String dia, mes, año;
    
    public void cerrar(){
        this.setVisible(false);  
    }
      
    public Factura_Compra() {    
        initComponents();
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
        
        //ANCHO COLUMNAS TABLA
        tabla.getColumnModel().getColumn(0).setMaxWidth(80);
        tabla.getColumnModel().getColumn(0).setMinWidth(80);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(80);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(80);
        
        tabla.getColumnModel().getColumn(1).setMaxWidth(110);
        tabla.getColumnModel().getColumn(1).setMinWidth(110);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(110);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(110);
        
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
          
    Factura_Compra(Menu_Principal aThis, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        
        setearFecha();
        checkDescuento.setSelected(false);
        checkIva1.setSelected(true);
        checkDescuentoGeneral.setSelected(false);
        txtDescuentoGeneral.setEnabled(false);
        
        checkIva1.setVisible(false);
        
        fact.setVisible(false);
        txtCodigoEmpleado.setVisible(false);
        txtCodigoProveedor.setVisible(false);
        txtIvaFactura.setVisible(false);
        txtCodigoArticulo1.setVisible(false);
        txtTotalBrutoArt.setVisible(false);
        total2.setVisible(false);
        txtIva.setVisible(false);
        txtTipo.setVisible(false);
        txtDescuento.setText("");
        txtDomicilio.setText("");
        txtDescuento.setEditable(false);       
        txtCuit.setText("");
        txtTipo.setText("");
        
        comboCategoria.setSelectedIndex(0);
        comboProveedor.setSelectedIndex(0);
        comboArticulos.setSelectedIndex(0);
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
        for (x=fil-1;x>=0;x--) {
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            modelo.removeRow(x);
        }
        try{
            //PARA CARGAR EL NUMERO DE FACTURA 
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            ResultSet r= consulta.executeQuery("select cod_compra from  compra order by cod_compra desc");
            r.next();
            fact.setText(r.getString(1));
            int f;         
            f=Integer.parseInt(fact.getText())+1;
            fact.setText(String.valueOf(f));
            r.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
        //txtFacturaNumero.setText("COMPRA N° "+fact.getText());
    }
    
    public static void tipoFactura(){      
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
            comboIva.setEnabled(false);
        }else if (comboFactura.getSelectedItem().equals("FACTURA A")){
            txtIva.setEnabled(true);
            txtIva.setEditable(true);
            txtIva.setText("19.00");
            txtIva2.setText("19.00");
            comboIva.setEnabled(true);
        }
    }
    
    public void inabilita(){
        setearFecha();
    
        calendario.setEnabled(false);

        comboCategoria.setEnabled(false);
        comboProveedor.setEnabled(false);
        comboArticulos.setEnabled(false);
        
        txtDescuento.setEnabled(false);
        comboPago.setEnabled(false);

        buscare1.setEnabled(false);
        buscare2.setEnabled(false);
        cant.setEditable(true);
        btnAgregarArticulo.setEnabled(false);
        btnQuitarArticulo.setEnabled(false);

        btnCancelarCompra.setEnabled(false);
        btn_guardarCompra.setEnabled(false);
        botonAgregarCliente.setEnabled(false);
        botonAgregarArticulo.setEnabled(false);
        btnCancelarCompra.setEnabled(false);
        sub.setEnabled(false);
        txtIva.setEnabled(false);
        total.setEnabled(false);

        total2.setVisible(false);
        fact.setVisible(false);
        txtCodigoEmpleado.setVisible(false);
        txtCodigoProveedor.setVisible(false);
        txtFacturaNumero.setText("COMPRA");  
    }
    
    public void habilitar(){
        calendario.setEnabled(true); 

        txtDescuento.requestFocus();
        comboCategoria.setEnabled(true);
        comboProveedor.setEnabled(true);
        comboArticulos.setEnabled(false);
        comboPago.setEnabled(true);
        

        buscare1.setEnabled(true);
        buscare2.setEnabled(false);

        cant.setEditable(true);
        btnAgregarArticulo.setEnabled(false);
        btnQuitarArticulo.setEnabled(false);
        btnCancelarCompra.setEnabled(true);
        botonAgregarCliente.setEnabled(true);  
        btn_guardarCompra.setEnabled(true);
        
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
            comboArticulos.removeAllItems();
            comboArticulos.addItem("SELECCIONE ARTICULO");
            i=0;
            while(res.next()){
                comboArticulos.addItem(res.getString(2));
                i++;
            }

            res= consulta.executeQuery("select * from proveedor order by nombre_firma");

            comboProveedor.removeAllItems();
            comboProveedor.addItem("SELECCIONE PROVEEDOR");
            i=0;
            while(res.next()){
                comboProveedor.addItem(res.getString(2));
                i++;
            }

            comboCategoria.removeAllItems();
            comboCategoria.addItem("ARTICULOS");
            comboCategoria.addItem("MATERIA PRIMA");
            comboCategoria.addItem("INSUMOS");
            comboCategoria.addItem("OTROS");
            
            i=0;

            res.close();
            
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
        comboArticulos = new javax.swing.JComboBox();
        buscare2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cant = new javax.swing.JTextField();
        btnAgregarArticulo = new javax.swing.JButton();
        btnQuitarArticulo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        buscare1 = new javax.swing.JButton();
        comboProveedor = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        sub = new javax.swing.JTextField();
        txtIva2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        botonAgregarCliente = new javax.swing.JButton();
        btn_guardarCompra = new javax.swing.JButton();
        btnCancelarCompra = new javax.swing.JButton();
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
        btnEditarArticulo = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        comboCategoria = new javax.swing.JComboBox();
        comboCaja = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        txtFacturaNumero = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        calendario = new com.toedter.calendar.JDateChooser();
        txtCodigoEmpleado = new javax.swing.JTextField();
        txtCodigoProveedor = new javax.swing.JTextField();
        fact = new javax.swing.JTextField();
        txtCodigoArticulo1 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuItemBuscarProveedor = new javax.swing.JMenuItem();
        menuItemAgregarProveedor = new javax.swing.JMenuItem();
        menuItemBuscarArticulo = new javax.swing.JMenuItem();
        menuItemAgregarArticulo = new javax.swing.JMenuItem();

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem1.setText(" QUITAR ARTICULO ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        QuitarArticulo.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Registrar compra");
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

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel8.setText("Fecha");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 70, 40));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setBackground(new java.awt.Color(239, 255, 239));
        jLabel9.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel9.setText("Articulo");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 90, 40));

        comboArticulos.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboArticulosActionPerformed(evt);
            }
        });
        jPanel1.add(comboArticulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 310, 39));

        buscare2.setBackground(new java.awt.Color(93, 116, 163));
        buscare2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoLupaF5.png"))); // NOI18N
        buscare2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscare2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscare2ActionPerformed(evt);
            }
        });
        buscare2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscare2KeyTyped(evt);
            }
        });
        jPanel1.add(buscare2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 60, 39));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel6.setText("Cantidad");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 100, 30));

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
        jPanel1.add(cant, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 100, 39));

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
        jPanel1.add(btnAgregarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 110, 38));

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
        jPanel1.add(btnQuitarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 100, 38));

        tabla.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CANT", "COD", "DESCRIPCION", "V. UNITARIO", "Total Bruto", "DTO %", "V. TOTAL", "UNITARIO COSTO", "total unitario Bruto"
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 1000, 150));

        jLabel7.setBackground(new java.awt.Color(239, 255, 239));
        jLabel7.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel7.setText("Cond");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 60, 40));

        buscare1.setBackground(new java.awt.Color(93, 116, 163));
        buscare1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoLupaF3.png"))); // NOI18N
        buscare1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscare1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscare1ActionPerformed(evt);
            }
        });
        jPanel1.add(buscare1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 60, 39));

        comboProveedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(comboProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 310, 39));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel11.setText("IVA Discriminado");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 53, -1, 50));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel13.setText("Importe Total");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 150, 40));

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

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 420, 410, 160));

        botonAgregarCliente.setBackground(new java.awt.Color(93, 116, 163));
        botonAgregarCliente.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        botonAgregarCliente.setForeground(new java.awt.Color(255, 255, 255));
        botonAgregarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoAgregarF4.png"))); // NOI18N
        botonAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(botonAgregarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 50, 39));

        btn_guardarCompra.setBackground(new java.awt.Color(51, 153, 255));
        btn_guardarCompra.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_guardarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardar.png"))); // NOI18N
        btn_guardarCompra.setBorder(null);
        btn_guardarCompra.setBorderPainted(false);
        btn_guardarCompra.setContentAreaFilled(false);
        btn_guardarCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_guardarCompra.setFocusCycleRoot(true);
        btn_guardarCompra.setFocusable(false);
        btn_guardarCompra.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btn_guardarCompra.setRequestFocusEnabled(false);
        btn_guardarCompra.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btn_guardarCompra.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btn_guardarCompra.setVerifyInputWhenFocusTarget(false);
        btn_guardarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarCompraActionPerformed(evt);
            }
        });
        jPanel1.add(btn_guardarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 150, 130));

        btnCancelarCompra.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelarCompra.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnCancelarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelar.png"))); // NOI18N
        btnCancelarCompra.setBorder(null);
        btnCancelarCompra.setBorderPainted(false);
        btnCancelarCompra.setContentAreaFilled(false);
        btnCancelarCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCancelarCompra.setFocusCycleRoot(true);
        btnCancelarCompra.setFocusable(false);
        btnCancelarCompra.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarCompra.setRequestFocusEnabled(false);
        btnCancelarCompra.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarCompra.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarCompra.setVerifyInputWhenFocusTarget(false);
        btnCancelarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCompraActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, -1, 130));

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel19.setText("Cancelar");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 530, -1, -1));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel17.setText("Facturar");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, 110, -1));

        botonAgregarArticulo.setBackground(new java.awt.Color(93, 116, 163));
        botonAgregarArticulo.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        botonAgregarArticulo.setForeground(new java.awt.Color(255, 255, 255));
        botonAgregarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoAgregarF6.png"))); // NOI18N
        botonAgregarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarArticuloActionPerformed(evt);
            }
        });
        jPanel1.add(botonAgregarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 50, 39));

        comboPago.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPagoActionPerformed(evt);
            }
        });
        jPanel1.add(comboPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 370, 270, 40));

        jLabel10.setBackground(new java.awt.Color(239, 255, 239));
        jLabel10.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel10.setText("Domicilio");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 110, 40));

        txtDescuento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDescuento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescuentoActionPerformed(evt);
            }
        });
        jPanel1.add(txtDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, 80, 40));

        checkDescuento.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        checkDescuento.setText("%Desc");
        checkDescuento.setOpaque(false);
        checkDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDescuentoActionPerformed(evt);
            }
        });
        jPanel1.add(checkDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, -1, 40));

        txtTipo.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtTipo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoActionPerformed(evt);
            }
        });
        jPanel1.add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 530, 40, 40));

        jLabel15.setBackground(new java.awt.Color(239, 255, 239));
        jLabel15.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel15.setText("Proveedor");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        txtCuit.setFont(new java.awt.Font("Calibri", 0, 17)); // NOI18N
        txtCuit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuitActionPerformed(evt);
            }
        });
        jPanel1.add(txtCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 170, 40));

        jLabel16.setBackground(new java.awt.Color(239, 255, 239));
        jLabel16.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel16.setText("NIT");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 50, 40));

        comboFactura.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFacturaActionPerformed(evt);
            }
        });
        jPanel1.add(comboFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, 270, 40));

        jLabel18.setBackground(new java.awt.Color(239, 255, 239));
        jLabel18.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel18.setText("% iVA");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 160, 70, 40));

        txtTotalBrutoArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalBrutoArtActionPerformed(evt);
            }
        });
        jPanel1.add(txtTotalBrutoArt, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 460, 40, 40));
        jPanel1.add(txtIvaFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 510, 40, 40));
        jPanel1.add(total2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 530, 50, 40));

        checkIva1.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        checkIva1.setText("% Iva");
        checkIva1.setOpaque(false);
        checkIva1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkIva1ActionPerformed(evt);
            }
        });
        jPanel1.add(checkIva1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 460, 100, 40));

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
        jPanel1.add(txtIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 480, 50, 40));

        jLabel21.setBackground(new java.awt.Color(239, 255, 239));
        jLabel21.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel21.setText("Tipo de factura");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, -1, 40));

        txtDomicilio.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        txtDomicilio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDomicilioActionPerformed(evt);
            }
        });
        jPanel1.add(txtDomicilio, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 270, 40));

        jLabel22.setBackground(new java.awt.Color(239, 255, 239));
        jLabel22.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel22.setText("Tipo de pago");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 370, 150, 40));

        comboIva.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIvaActionPerformed(evt);
            }
        });
        jPanel1.add(comboIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 160, 100, 40));

        comboContribuyente.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboContribuyente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboContribuyenteActionPerformed(evt);
            }
        });
        jPanel1.add(comboContribuyente, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 210, 40));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
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
        jPanel5.add(txtDescuentoGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 130, 40));

        txtDtoGral.setBackground(new java.awt.Color(204, 204, 204));
        txtDtoGral.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        txtDtoGral.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDtoGral.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.add(txtDtoGral, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 170, 40));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 250, 160));

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
        jPanel1.add(btnEditarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 100, 38));

        jLabel14.setBackground(new java.awt.Color(239, 255, 239));
        jLabel14.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel14.setText("Categoria ");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 110, 40));

        comboCategoria.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(comboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 110, 270, 40));

        comboCaja.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCajaActionPerformed(evt);
            }
        });
        jPanel1.add(comboCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 370, 250, 40));

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 1020, 600));

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtFacturaNumero.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        txtFacturaNumero.setText("FACTURA DE COMPRA");
        jPanel2.add(txtFacturaNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 370, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 0, 50, -1));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 60));

        calendario.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jPanel4.add(calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 220, 40));

        txtCodigoEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoEmpleadoActionPerformed(evt);
            }
        });
        jPanel4.add(txtCodigoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 20, 30));

        txtCodigoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoProveedorActionPerformed(evt);
            }
        });
        jPanel4.add(txtCodigoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 30, 30));

        fact.setEditable(false);
        fact.setBackground(new java.awt.Color(255, 255, 255));
        fact.setFont(new java.awt.Font("Cambria", 1, 30)); // NOI18N
        fact.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fact.setBorder(new javax.swing.border.MatteBorder(null));
        fact.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        fact.setEnabled(false);
        fact.setOpaque(false);
        fact.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        fact.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel4.add(fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 20, 30));

        txtCodigoArticulo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoArticulo1ActionPerformed(evt);
            }
        });
        jPanel4.add(txtCodigoArticulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 20, 30));

        panelImage1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 680));

        jMenuBar1.setBackground(new java.awt.Color(204, 204, 204));

        jMenu1.setBackground(new java.awt.Color(204, 204, 204));
        jMenu1.setForeground(new java.awt.Color(204, 204, 204));
        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        menuItemBuscarProveedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        menuItemBuscarProveedor.setText("Buscar proveedor");
        menuItemBuscarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemBuscarProveedorActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemBuscarProveedor);

        menuItemAgregarProveedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        menuItemAgregarProveedor.setText("Agregar proveedor");
        menuItemAgregarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAgregarProveedorActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemAgregarProveedor);

        menuItemBuscarArticulo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        menuItemBuscarArticulo.setText("Buscar articulo");
        menuItemBuscarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemBuscarArticuloActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemBuscarArticulo);

        menuItemAgregarArticulo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        menuItemAgregarArticulo.setText("Agregar articulo");
        menuItemAgregarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAgregarArticuloActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemAgregarArticulo);

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

    private void comboCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCategoriaActionPerformed
        //para que no se habilite la carga de productos hasta que no se ingrese el proveedor    
        if(comboProveedor.getSelectedItem().equals("SELECCIONE PROVEEDOR")){
            comboArticulos.setEnabled(false);
            cant.setEditable(false);
            btnAgregarArticulo.setEnabled(false);
            btnQuitarArticulo.setEnabled(false);
            botonAgregarArticulo.setEnabled(false);
            buscare2.setEnabled(false);
        }else{
            comboArticulos.setEnabled(true);
            cant.setEditable(true);
            btnAgregarArticulo.setEnabled(true);
            btnQuitarArticulo.setEnabled(true);
            botonAgregarArticulo.setEnabled(true);
            buscare2.setEnabled(true);
        }
    }//GEN-LAST:event_comboCategoriaActionPerformed

    private void comboProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProveedorActionPerformed
        comboContribuyente.addItem("SELECCIONAR");
        comboContribuyente.setSelectedIndex(0);
        
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            nomProveedor= (String)comboProveedor.getSelectedItem();
            codigoProveedor=null;
            String tipoContribuyente="";
            
            //PARA LLENAR LOS CAMPOS DE LA FACTURA DEPENDIENDO EL PROVEEDOR QUE SE SELECCIONA
            ResultSet rs = consulta.executeQuery("SELECT cod_proveedor, cuit, condicion, direccion_firma, ciudad_firma FROM proveedor WHERE nombre_firma = '"+nomProveedor+"'" );
            while (rs.next()) {
                codigoProveedor= rs.getString(1);
                txtDomicilio.setText(rs.getString(4)+", "+rs.getString(5));
                txtCuit.setText(rs.getString(2));
                if(rs.getString(3).equals("")){
                    tipoContribuyente="Consumidor Final";
                }else{
                    tipoContribuyente=rs.getString(3);
                }

            }
            comboContribuyente.setSelectedItem(tipoContribuyente);
                   
            txtCodigoProveedor.setText(codigoProveedor);
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error SQL ");
        }
        
        //HASTA QUE NO ESTAN SELECCIONADOS EL PROVEEDOR Y EL TIPO DE FACTURA NO HABILITO EL INGRESO DE ARTICULOS
        if(comboProveedor.getSelectedItem().equals("SELECCIONE PROVEEDOR") || comboFactura.getSelectedItem().equals("SELECCIONAR")){
            comboArticulos.setEnabled(false);
            cant.setEditable(false);
            btnAgregarArticulo.setEnabled(false);
            btnQuitarArticulo.setEnabled(false);
            botonAgregarArticulo.setEnabled(false);
            buscare2.setEnabled(false);
        }else{
            comboArticulos.setEnabled(true);
            cant.setEditable(true);
            btnAgregarArticulo.setEnabled(true);
            btnQuitarArticulo.setEnabled(true);
            botonAgregarArticulo.setEnabled(true);
            buscare2.setEnabled(true);
        }
        
        //SI SELECCIONA FACTURA A EL IVA COMPRA ES 0.00 SINO LO SETEO EN 21.00
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
                comboIva.setSelectedItem("0%");
                comboIva.setEnabled(false);
            }else if (comboFactura.getSelectedItem().equals("FACTURA A")){
                txtIva.setEnabled(true);
                txtIva.setEditable(true);
                txtIva.setText("19.00");
                txtIva2.setText("19.00");
                comboIva.setEnabled(true);
                comboIva.setSelectedItem("19%");
            }
            comboContribuyente.removeItem("SELECCIONAR");
    }//GEN-LAST:event_comboProveedorActionPerformed

    private void buscare1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscare1ActionPerformed
        //BOTON BUSCAR PROVEEDOR
        enviar_proveedor cp = new enviar_proveedor(this, true);
        cp.setVisible(true);   
        cp.txtRecibe.setText("1");
        cp.toFront();
    }//GEN-LAST:event_buscare1ActionPerformed

    public void cargarComboEmpleado(){
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            nomempleado = (String)comboCategoria.getSelectedItem();
            cod_empleado=null;
            
            String cadena=comboCategoria.getSelectedItem().toString();
            int i=0;       
            while(cadena.charAt(i)!=' ') { 
               i++;
            }  
            String SubCadenaNombreEmpleado = cadena.substring(0,i);
            String SubCadenaApellidoEmpleado = cadena.substring(i+1,cadena.length());
            //CARGO EL COMBO DE VENDEDORES
            ResultSet rs = consulta.executeQuery("SELECT cod_empleado FROM empleado WHERE (nombres = '"+SubCadenaNombreEmpleado+"' AND apellidos = '"+SubCadenaApellidoEmpleado+"')");
            while (rs.next()) {
                cod_empleado= rs.getString(1);
            }
            conn.close();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
    }
    
    private void comboArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboArticulosActionPerformed
        //CARGO EL COMBO DE ARTICULOS
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            nomproducto = (String)comboArticulos.getSelectedItem();
            codigoproducto=null;
            ResultSet rs = consulta.executeQuery("SELECT cod_articulo FROM articulo WHERE (referencia = '"+comboArticulos.getSelectedItem()+"')");
            while (rs.next()) {
                codigoproducto= rs.getString(1);
            }
            txtCodigoArticulo1.setText(codigoproducto);
            conn.close();  // Cierra la conexión

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
        cant.requestFocus();
    }//GEN-LAST:event_comboArticulosActionPerformed

    private void buscare2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscare2ActionPerformed
        //BOTON BUSCAR PRODUCTO
        enviar_producto form= new enviar_producto ();
        form.setVisible(true);
        form.toFront();
        enviar_producto.txt_recibe.setText("2");
        enviar_producto.txt_recibe.setVisible(false);
        enviar_producto.enviarProducto.setVisible(false);
    }//GEN-LAST:event_buscare2ActionPerformed

    private void btnAgregarArticuloMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarArticuloMouseEntered

    }//GEN-LAST:event_btnAgregarArticuloMouseEntered

    private void btnAgregarArticuloMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarArticuloMouseExited

    }//GEN-LAST:event_btnAgregarArticuloMouseExited
    
    public void agregarArticulo(){
        //CUANDO A LA CANTIDAD DEL PRODUCTO LE DOY ENTER HACE LO MISMO QUE EL BOTON AGREGAR
        
        Double variableIva=1.00, precioConVariable=0.00;
        
        //SEGUN EL IVA QUE ESTE SELECCIONADO SETEO LAS VARIABLES PARA  REALIZAR LAS CUENTAS
        if(txtIva.getText().equals("19.00")){
            variableIva=1.19;
        }
        else if(txtIva.getText().equals("0.00")){
                variableIva=1.00;
            }
        
        
        Double subtotal2=0.0,subTotalBrutoArt=0.0, porcentIva=0.0;
        int pe=0,fil = tabla.getRowCount();
        //PARA SABER SI EL ARTICULO YA FUE INGRESADO ENLA FACTURA
        for (int x=0;x<=fil-1;x++) {
            String aux= (String) (tabla.getValueAt(x,2));
            if(aux.equals(comboArticulos.getSelectedItem()))
            {
                pe=1;
            }
        }
        if(pe==1){
            JOptionPane.showMessageDialog(null,"Este Articulo Ya Existe, modifique su cantidad en la tabla") ;
        }else{
            if (comboArticulos.getSelectedItem().equals("SELECCIONE ARTICULO")){
                JOptionPane.showMessageDialog(null, "Falta elegir el articulo");
            }else{
                if (cant.getText().equals("") || cant.getText().equals("0") || cant.getText().equals("0.0")){
                    JOptionPane.showMessageDialog(null, "Debe Digitar la Cantidad de articulo a Llevar");
                    cant.requestFocus();
                }else{
                    try{
                        Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                        Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                        
                        
                    }catch(SQLException e){
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null,"Error en el SQL") ;
                    }

                        fila = tabla.getRowCount();
                        columna = tabla.getColumnCount();
                        //creo el modelo de la tabla y le empiezo a ingresar los datos del articulo
                        DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                        modelo.addRow( new Object [] {null,null,null,null,null,null,null,null,null});
                        double cantidad= Double.parseDouble(cant.getText().replace(',','.'));
                        tabla.setValueAt(cantidad,fila,0);
                        tabla.setValueAt(codigoproducto,fila,1);
                        tabla.setValueAt(nomproducto,fila,2);
                        try{
                            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

                            ResultSet r= consulta.executeQuery("select valor_bruto, valor_bruto_sin_iva from articulo where cod_articulo= '"+txtCodigoArticulo1.getText()+"'");
                            Double brutoSinIva=0.0;
                            while(r.next()){
                                precio=r.getString(1); //LOS PRECIOS SON IGUALES PORQUE SINO HACIA MAL LAS CUENTAS
                                precioBruto=r.getString(1);//LOS PRECIOS SON IGUALES PORQUE SINO HACIA MAL LAS CUENTAS
                                brutoSinIva=Double.parseDouble(r.getString(2));
                            }
                            
                            Double precioCosto=0.0;
                            if(comboFactura.getSelectedItem().equals("FACTURA A")){ //SI ES FACTURA B, PASE LO QUE PASE CON EL COMBO IVA NO CARGO EL IVA EN LA COMPRA
                                precioCosto=brutoSinIva* variableIva; 
                            }else if(comboFactura.getSelectedItem().equals("FACTURA B")){
                                        precioCosto=Double.parseDouble(precioBruto); 
                                    }

                            precioCosto=Math.round(precioCosto * 100.0)/ 100.0;
                            tabla.setValueAt(precioCosto,fila,7); //ACA VA EL VALOR DE COSTO DEL PRODUCTO
                            
                            precioConVariable = Double.parseDouble(precio);  //TRANSFORMO EL PRECIO CON IVA ( SI ES FACTURA A DEBITO EL PORCENTAJE DE IVA) SI ES FACTURA B DIVIDE POR UNO
                            precioConVariable=precioConVariable/variableIva;
                            precioConVariable=Math.round(precioConVariable * 100.0)/ 100.0;

                        }catch(SQLException e){
                            JOptionPane.showMessageDialog(null,"Este Articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
                        }

                        tabla.setValueAt(String.format("%.2f", precioConVariable).replace(",", "."),fila,3); //VALOR UNITARIODEL PRODUCTO CON LA VARIABLE DE IVA
                        
                        
                        //CAMPO DESCUENTO
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
                        for (int x=0;x<=fila2-1;x++) {
                             //CALCULO EL VALOR DEL DESCUENTO
                            double porcent=(Double.parseDouble(tabla.getValueAt(x,5).toString().replace(",", "."))* Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")))/100.00;
                            porcent=Math.round(porcent * 100.0)/ 100.0;
                            
                            //AL SUBTOTAL DEL PRODUCTO LO SETEO CON EL VALOR UNITARIO MENOS(-) EL DESCUENTO POR(*) LA CANTIDAD
                            double totalsub=(Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla.getValueAt(x,0).toString());
                            totalsub=Math.round(totalsub * 100.0)/ 100.0;
                            
                            //PRECIO DE COSTO UNITARIO CON LA VARUABLE DE IVA POR LA CANTIDAD (ESTOS DATOS SON PARA GUARDAR LO QUE ME SALE LA COMPRA CON IVA)
                            double totalsubArticuloBruto=(Double.parseDouble(tabla.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla.getValueAt(x,0).toString());
                            totalsubArticuloBruto=Math.round(totalsubArticuloBruto * 100.0)/ 100.0;
                            //VALOR UNITARIO SIN VARIABLE IVA POR CANTIDAD (ESTOS DATOS SON PARA GUARDAR LO QUE ME SALE LA COMPRA CON IVA)
                            subtotalBruto=Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla.getValueAt(x,0).toString());
                            subtotalBruto=Math.round(subtotalBruto * 100.0)/ 100.0;
                            
                            subtotal33=totalsub+subtotal33;
                            subtotal2 =subtotal2 +subtotalBruto;
                            subTotalBrutoArt=subTotalBrutoArt + totalsubArticuloBruto;
                            
                            tabla.setValueAt(String.format("%.2f", subtotalBruto ).replace(",", "."),x,4);
                            tabla.setValueAt(String.format("%.2f", totalsub).replace(",", "."),x,6);
                            tabla.setValueAt(String.format("%.2f", subTotalBrutoArt),x,8);                       
                        }
                        
                        //SETEO LOS TEXTFIELD QUE VAN MOSTRANDO EL TOTAL DE LA COMPRA
                        porcentIva=Double.parseDouble(txtIva.getText().replace(",", "."))/100;
                        subtotalConIva= subtotal33*variableIva;
                        sub.setText(String.format("%.2f",subtotal33).replace(",", "."));
                        total.setText(String.format("%.2f",subtotalConIva).replace(",", "."));
                        total2.setText(""+String.format("%.2f",subtotalConIva));
                        txtTotalBrutoArt.setText(""+String.format("%.2f",subTotalBrutoArt));

                        comboArticulos.setSelectedIndex(0);
                        cant.setText("");
                    
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
        //boton para quitar articulos de la factura
        Double variableIva=0.00;
        if(txtIva.getText().equals("19.00")){
               variableIva=1.19;
           }else if(txtIva.getText().equals("0.00")){
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
                for (int x=0;x<=fila2-1;x++) {
                    //CALCULO EL VALOR DEL DESCUENTO
                    double porcent=(Double.parseDouble(tabla.getValueAt(x,5).toString().replace(",", "."))* Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")))/100.00;
                    porcent=Math.round(porcent * 100.0)/ 100.0;
                            
                    //AL SUBTOTAL DEL PRODUCTO LO SETEO CON EL VALOR UNITARIO MENOS(-) EL DESCUENTO POR(*) LA CANTIDAD
                    double totalsub=(Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla.getValueAt(x,0).toString());
                    totalsub=Math.round(totalsub * 100.0)/ 100.0;
                            
                    //PRECIO DE COSTO UNITARIO CON LA VARUABLE DE IVA POR LA CANTIDAD (ESTOS DATOS SON PARA GUARDAR LO QUE ME SALE LA COMPRA CON IVA)
                    double totalsubArticuloBruto=(Double.parseDouble(tabla.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla.getValueAt(x,0).toString());
                    totalsubArticuloBruto=Math.round(totalsubArticuloBruto * 100.0)/ 100.0;
                    //VALOR UNITARIO SIN VARIABLE IVA POR CANTIDAD (ESTOS DATOS SON PARA GUARDAR LO QUE ME SALE LA COMPRA CON IVA)
                    subtotalBruto=Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla.getValueAt(x,0).toString());
                    subtotalBruto=Math.round(subtotalBruto * 100.0)/ 100.0;
                            
                    subtotal33=totalsub+subtotal33;
                    subtotal2 =subtotal2 +subtotalBruto;
                    subTotalBrutoArt=subTotalBrutoArt + totalsubArticuloBruto;
                            
                    tabla.setValueAt(String.format("%.2f", subtotalBruto ).replace(",", "."),x,4);
                    tabla.setValueAt(String.format("%.2f", totalsub).replace(",", "."),x,6);
                    tabla.setValueAt(String.format("%.2f", subTotalBrutoArt),x,8);                       
                }
                        
                //SETEO LOS TEXTFIELD QUE VAN MOSTRANDO EL TOTAL DE LA COMPRA
                porcentIva=Double.parseDouble(txtIva.getText().replace(",", "."))/100;
                subtotalConIva= subtotal33*variableIva;
                sub.setText(String.format("%.2f",subtotal33).replace(",", "."));
                total.setText(String.format("%.2f",subtotalConIva).replace(",", "."));
                total2.setText(""+String.format("%.2f",subtotalConIva));
                txtTotalBrutoArt.setText(""+String.format("%.2f",subTotalBrutoArt));
        
   
                DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();

                fila-=1;
                int filat=tabla.getRowCount();
                if(filat==0){
                    txtTotalBrutoArt.setText("0,00");
                }else{
                    txtTotalBrutoArt.setText(tabla.getValueAt(fila, 8).toString());
                }
                if (filat==0){
                    comboCategoria.setEnabled(true);
                    comboProveedor.setEnabled(true);
                }
            }catch (ArrayIndexOutOfBoundsException e) {        
                //JOptionPane.showMessageDialog(null, "NO HAY PRODUCTOS PARA QUITAR");       
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

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
           JOptionPane.showMessageDialog(null,"Ingrese el Dato");
        }
    }//GEN-LAST:event_formKeyTyped

    private void buscare2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscare2KeyTyped

    }//GEN-LAST:event_buscare2KeyTyped

    private void botonAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarClienteActionPerformed
        Agregar_Proveedor form= new Agregar_Proveedor ();
        form.setVisible(true);
        Agregar_Proveedor.recibeProveedor.setText("1");
        form.toFront();
    }//GEN-LAST:event_botonAgregarClienteActionPerformed

    private void tablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyReleased
        int Select=tabla.getSelectedRow(), bandera=0;
        Double subtotal2=0.0;   
        subTotalBrutoArticulo=0.0;
        int fila2 = tabla.getRowCount();
        subtotal=0;
        
        Double variableIva=0.00;
        String ivaFact="";
        if(comboIva.getSelectedItem().toString().equals("19%")){
            variableIva=1.19;
            ivaFact="19.00";
        }else if(comboIva.getSelectedItem().toString().equals("0%")){
                    variableIva=1.00;
                    ivaFact="0.00";
                }
        
        for (int x=0;x<=fila2-1;x++) {
            try{
                Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                ResultSet   res= consulta.executeQuery("select cantidad from articulo where referencia = '"+tabla.getValueAt(Select,2).toString()+"'");
                while(res.next()){
                    cant_ex=Double.parseDouble(res.getString(1));
                    break;
                }
                res.close();
                
            }catch(SQLException e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Error en el SQL") ;
            }
            
            
            double porcent=(Double.parseDouble(tabla.getValueAt(x,5).toString().replace(",", "."))* Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")))/100.00;
            double totalsub=(Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla.getValueAt(x,0).toString());        
            
            tabla.setValueAt(String.format("%.2f",Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", "."))*variableIva).replace(",", "."),x,7);
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
        total.setText(String.format("%.2f",subtotal).replace(",", "."));
        total2.setText(""+String.format("%.2f",subtotal).replace(",", "."));
        txtTotalBrutoArt.setText(String.format("%.2f",subTotalBrutoArticulo).replace(",", "."));
        
        comboArticulos.setSelectedIndex(0);
        cant.setText(""); 
    }//GEN-LAST:event_tablaKeyReleased

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        //CUANDO EDITO LOS VALORES DE ADENTRO DE LA TABLA (POR EJEMPLO LA CANTIDAD O EL PRECIO DEL ARTICULO) QUE VAYA CAMBIANDO SEGUN AGREGO O QUITO PRECO O CANTIDAD
        int Select=tabla.getSelectedRow(), bandera=0;
        Double subtotal2=0.0;   
        subTotalBrutoArticulo=0.0;
        int fila2 = tabla.getRowCount();
        subtotal=0;
        
        Double variableIva=0.00;
        String ivaFact="";
        if(comboIva.getSelectedItem().toString().equals("19%")){
            variableIva=1.19;
            ivaFact="19.00";
        }else if(comboIva.getSelectedItem().toString().equals("0%")){
                   variableIva=1.00;
                   ivaFact="0.00";
               }
        
        for (int x=0;x<=fila2-1;x++) {
            try{
                Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                ResultSet   res= consulta.executeQuery("select cantidad from articulo where referencia = '"+tabla.getValueAt(Select,2).toString()+"'");
                while(res.next()){
                    cant_ex=Double.parseDouble(res.getString(1));
                    break;
                }
                res.close();
                
            }catch(SQLException e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Error en el SQL") ;
            }
            
            //ESTOS SON LOS CALCULOS NECESARIOS PARA CUANDO CAMBIO PRECIO O CANTIDAD DE UN ARTICULO DENTRO DE LA TABLA SE CAMBIEN LOS VALORES TOTALES
            double porcent=(Double.parseDouble(tabla.getValueAt(x,5).toString().replace(",", "."))* Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")))/100.00;
            double totalsub=(Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla.getValueAt(x,0).toString());        
            
            tabla.setValueAt(String.format("%.2f",Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", "."))*variableIva).replace(",", "."),x,7);
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
        //SETEO LOS TEXTFIELD QUE ME MUESTRAN LOS TOTALES
        sub.setText(String.format("%.2f",subBruto).replace(",", "."));
        total.setText(String.format("%.2f",subtotal).replace(",", "."));
        total2.setText(""+String.format("%.2f",subtotal).replace(",", "."));
        txtTotalBrutoArt.setText(String.format("%.2f",subTotalBrutoArticulo).replace(",", "."));
        
        comboArticulos.setSelectedIndex(0);
        cant.setText("");     
    }//GEN-LAST:event_tablaMouseClicked

    private void comboPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPagoActionPerformed
        String pago= comboPago.getSelectedItem().toString();
        if(pago.equals("CREDITO")){
            Agregar_Credito_Compra form= new Agregar_Credito_Compra ();
            Agregar_Credito_Compra.txtRecibe.setText("1");
            Agregar_Credito_Compra.txtCodFactura.setText(fact.getText());
            Agregar_Credito_Compra.txtTotalCredito.setText(total.getText());
            Agregar_Credito_Compra.txtSaldo.setText(total.getText());
            Agregar_Credito_Compra.cargarCredito();
            
            try{
                Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql                             

                Statement consulta2=conn.createStatement(); // crea una variable que se encargue del código de sql

                String codigoCaja="0";                                        
                //SELECCIONO EL CODIGO DE LA CAJA ABIERTA SELECCIONADA EN EL comboCaja PARA GUARDARLA EN EL PAGO DE EFECTIVO
                ResultSet  res22= consulta2.executeQuery("select cod_caja from apertura_caja WHERE estado='ABIERTA' AND nombre_caja='"+comboCaja.getSelectedItem()+"'");
                while(res22.next()){
                   codigoCaja=res22.getString(1);
                }
                Agregar_Credito_Compra.txtCodCaja.setText(codigoCaja);

            } 
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"ERROR EN LA BASE DE DATOS");
                 System.out.println(ex);
            }
                        
            form.setVisible(true);
            form.toFront();
        }else if(pago.equals("DEPOSITO")){
                Agregar_Deposito_Compra form= new Agregar_Deposito_Compra ();
                Agregar_Deposito_Compra.txtRecibe.setText("1");
                Agregar_Deposito_Compra.txtCodFactura.setText(fact.getText());
                Agregar_Deposito_Compra.txtTotal.setText(total.getText());
                Agregar_Deposito_Compra.cargarDeposito();
                Calendar c2 = new GregorianCalendar();
                Agregar_Deposito_Compra.calendario.setCalendar(c2);
                try{
                    Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql                             
                    Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql

                    ResultSet r= consulta1.executeQuery("select fecha from compra_deposito where cod_compra='"+fact.getText()+"'");    

                    while(r.next()){
                        String fechaDeposito=r.getString(1);
                        int año=Integer.parseInt(fechaDeposito.substring(0,4));
                        int mes=Integer.parseInt(fechaDeposito.substring(5,7));
                        int dia=Integer.parseInt(fechaDeposito.substring(8,10));
                        Calendar c3 = new GregorianCalendar(año,mes-1,dia);
                        Agregar_Deposito_Compra.calendario.setCalendar(c3);

                    }

                } 
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"ERROR EN LA BASE DE DATOS");
                    System.out.println(ex);
                }
    
                form.setVisible(true);
                form.toFront();
            }else if(pago.equals("EFECTIVO Y TARJETA")){
                    Agregar_TarjetaYEfectivo_Compra form= new Agregar_TarjetaYEfectivo_Compra  ();
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

                        ResultSet r= consulta1.executeQuery("select * from compra_mixta where cod_compra='"+fact.getText()+"'");    
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
                        Agregar_Cheque_Compra form= new Agregar_Cheque_Compra ();
                        Agregar_Cheque_Compra.txtFecha.setText("2");
                        Agregar_Cheque_Compra.txtRecibe.setText("1");
                        Agregar_Cheque_Compra.txtCodFactura.setText(fact.getText());
                        Agregar_Cheque_Compra.txtTotal.setText(total.getText());
                        Agregar_Cheque_Compra.cargarDeposito();
                        Calendar c2 = new GregorianCalendar();
                        Agregar_Cheque_Compra.calendario.setCalendar(c2);
                        Agregar_Cheque_Compra.calendarioVencimiento.setCalendar(c2);
                        try{
                            Connection conn = conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql                             
                            Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql

                            ResultSet r= consulta1.executeQuery("select fecha_emision, fecha_vencimiento from compra_cheque where cod_compra='"+fact.getText()+"'");    

                            while(r.next()){
                                String fechaDeposito=r.getString(1);
                                int año=Integer.parseInt(fechaDeposito.substring(0,4));
                                int mes=Integer.parseInt(fechaDeposito.substring(5,7));
                                int dia=Integer.parseInt(fechaDeposito.substring(8,10));
                                Calendar c3 = new GregorianCalendar(año,mes-1,dia);
                                Agregar_Cheque_Compra.calendario.setCalendar(c3);
                                
                                String fechaVencimiento=r.getString(2);
                                int año2=Integer.parseInt(fechaVencimiento.substring(0,4));
                                int mes2=Integer.parseInt(fechaVencimiento.substring(5,7));
                                int dia2=Integer.parseInt(fechaVencimiento.substring(8,10));
                                Calendar c4 = new GregorianCalendar(año2,mes2-1,dia2);  
                                Agregar_Cheque_Compra.calendarioVencimiento.setCalendar(c4);
                                Agregar_Cheque_Compra.txtTotal.setText(total.getText());

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
        char []p={'1','2','3','4','5','6','7','8','9','0','.',',','-'}; //PARA PODER INGRESAR SOLO NUMEROS O PUNTOS O COMAS
        int b=0;
        for(int i=0;i<=12;i++){
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

    private void txtCodigoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoProveedorActionPerformed

    private void btn_guardarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarCompraActionPerformed
        //BOTON PARA GUARDAR LA COMRA
        int banderaFactura=0;
        String tipoFacturaString="",titularEmpresa="", nombreEmpresa="",direccionEmpresa="",cuitEmpresa="",contribuyenteEmpresa="",telefonoEmpresa="",ingresosBrutos="",inicioActividades="";
        String nombreCliente="",cuitCliente="",tipoCliente="",direccionCliente="";
        int tipoFacturaInt=0;
        txtCuit.setBackground(new Color(255,255,255));
        try {
            if (comboCategoria.getSelectedItem().equals("SELECCIONE CATEGORIA")) {
                 banderaFactura=1;
                JOptionPane.showMessageDialog(null, "Falta elegir la categoria de la compra","Advertencia",JOptionPane.WARNING_MESSAGE);
            }else {
                if (comboProveedor.getSelectedItem().equals("SELECCIONE PROVEEDOR")) {
                     banderaFactura=1;
                    JOptionPane.showMessageDialog(null, "Falta elegir el Proveedor","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else {
                    if (comboFactura.getSelectedItem().equals("SELECCIONAR")) {
                         banderaFactura=1;
                        JOptionPane.showMessageDialog(null, "Falta elegir el tipo de factura","Advertencia",JOptionPane.WARNING_MESSAGE);
                    }else if (comboContribuyente.getSelectedItem().equals("SELECCIONAR")) {
                            banderaFactura=1;
                            JOptionPane.showMessageDialog(null, "Falta elegir el tipo de contribuyente","Advertencia",JOptionPane.WARNING_MESSAGE);
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
                                                            //SI ESTAN TODOS LOS DATOS BIEN INGRESADOS PROCEDO A GUARDAR LA COMPRA EN LA BD
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

                                                                }


                                                                nombreCliente=comboProveedor.getSelectedItem().toString(); //NOMBRE DEL CLIENTE
                                                                
                                                                //PARA GUARDAR EL TIPO DE FACTURA
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

                                                                //SACO LA FECHA QUE TIENE EL COMBO CALENDARIO PARA GUARDARLA ENLA BD
                                                                int fil = tabla.getRowCount();
                                                                int col = tabla.getColumnCount();

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

                                                                Double variableIva=0.00;
                                                                Double ivaCompraArticulo=0.0;
                                                                if(comboIva.getSelectedItem().toString().equals("19%")){
                                                                    variableIva=1.19;
                                                                    ivaCompraArticulo=19.00;
                                                                }else if(comboIva.getSelectedItem().toString().equals("0%")){
                                                                           variableIva=1.00;
                                                                           ivaCompraArticulo=0.00;
                                                                        }
                                                                Double subTotalNeto=Double.parseDouble(sub.getText());
                                                                String subTotalNetoString="";


                                                                subTotalNetoString=String.format("%.2f", subTotalNeto).replace(",", ".");
                                                                int x,y;

                                                                String ivaFactura=txtIva2.getText(); //GUARDO EL IVA QUE TIENE LA FACTURA

                                                                //*****************!!!************************************! EN LA COMPRA LAS FACTURAS B NO CARGAN EL IVA !***************************************!!!!!!!!!!!!
                                                                //INSERTO LOS DATOSEN LA TABLA COMPRA DE LA BD
                                                                consulta.executeUpdate("insert into compra (cod_compra, fecha,cod_proveedor,condicion,categoria,tipo_pago,tipo_factura,iva,total_con_iva,total_sin_iva,ivaDiscriminado) values('"+fact.getText()+"','"+fecha+"','"+txtCodigoProveedor.getText()+"','ACTIVA','"+comboCategoria.getSelectedItem()+"','"+comboPago.getSelectedItem()+"','"+tipoFacturaString+"','"+ivaFactura+"','"+total2.getText().replace(",",".")+"','"+subTotalNetoString+"','"+txtIva2.getText().replace(",",".")+"')");
                                                                Connection con= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                                                                Statement consultaArticulo=con.createStatement();

                                                                //RECORRO LA TABLA CON LOS ARTICULOS CARGADOS YLOS GUARDO EN REFERENCIA_COMPRA EN LA BD
                                                                for (x=0;x<=fil-1;x++) {
                                                                    consulta1.executeUpdate("insert into referencia_compra (cod_compra,codigo_producto,referencia,cantidad,unitario_costo,total_costo,valor_unitario,valor_total,total_compra,descuento,unitario_sin_iva) values('"+fact.getText()+"','"+tabla.getValueAt(x,1)+"'  ,'"+tabla.getValueAt(x,2)+"','"+tabla.getValueAt(x,0).toString().replace(",",".")+"','"+tabla.getValueAt(x,7)+"','"+txtTotalBrutoArt.getText().replace(",",".")+"','"+tabla.getValueAt(x,3).toString().replace(",",".")+"','"+tabla.getValueAt(x,6)+"','"+total.getText().replace(",",".")+"','"+tabla.getValueAt(x,5)+"','"+tabla.getValueAt(x,3)+"')");
                                                                    //Double NetoCosto=Double.parseDouble(tabla.getValueAt(x,7).toString().replace(",","."));

                                                                    //ACTUALIZO EL PRECIO DEL ARTICULO
                                                                    String ultimaActualizacion;
                                                                     Calendar fecha = new GregorianCalendar();
                                                                    //Obtenemos el valor del año, mes, día, hora, minuto y segundo del sistema.
                                                                    //Usando el método get y el parámetro correspondiente.
                                                                    int añoA = fecha.get(Calendar.YEAR);
                                                                    int mesA = fecha.get(Calendar.MONTH);
                                                                    int diaA = fecha.get(Calendar.DAY_OF_MONTH);
                                                                    
                                                                    //PARA GUARDAR LA ULTIMA ACTUALIZACION DEL ARTICULO
                                                                    if((mesA+1)<10 && (diaA>=10)){
                                                                        ultimaActualizacion = (diaA+"/0"+(mesA+1)+"/"+añoA); 
                                                                    }else{
                                                                        if(((mesA+1)<10 && (diaA<10))){
                                                                              ultimaActualizacion =("0"+diaA+"/0"+(mesA+1)+"/"+añoA);
                                                                        }else{
                                                                               ultimaActualizacion = (diaA+"/"+(mesA+1)+"/"+añoA);
                                                                        }
                                                                    }
                                                                    //ESTO ES PARA SETEAR EL NUEVO VALOR DEL ARTICULO RESPETANDO LOS PORCENTAJES DE IVA Y DE GANANCIA INGRESADOS AL CARGAR EL ARTICULO EN EL MODULO DE CARGA
                                                                    ResultSet rArt= consultaArticulo.executeQuery("SELECT porcentaje_ganancia, iva FROM articulo WHERE cod_articulo='"+tabla.getValueAt(x,1)+"'");
                                                                    Double precio=0.0,precioCosto=0.0,precioConIva=0.0,ganancia=0.0, variableIvaVenta=0.0;
                                                                    while(rArt.next()){
                                                                        ganancia=Double.parseDouble(rArt.getString(1).replace(",","."));   //GANANCIA 65.00
                                                                        variableIvaVenta=Double.parseDouble(rArt.getString(2).replace(",",".")); //IVA   0.00
                                                                    }

                                                                    precioCosto=Double.parseDouble(tabla.getValueAt(x,7).toString().replace(",","."));
                                                                    precioCosto=Math.round(precioCosto * 100.0)/ 100.0;

                                                                    precio= precioCosto + (precioCosto * ganancia / 100); 
                                                                    precio=Math.round(precio * 100.0)/ 100.0;

                                                                    precioConIva=precio+(precio*variableIvaVenta/100); //AJUSTO EL PRECIO CON EL IVA DE LA VENTA DEL ARTICULO REGISTRADO EN LA BASE DE DATOS
                                                                    precioConIva=Math.round(precioConIva * 100.0)/ 100.0;
                                                                    //ACTUALIZO LOS NUEVOS PRECIOS DEL ARTICULO
                                                                    consulta1.executeUpdate("UPDATE articulo SET valor_bruto='"+precioCosto+"', valor='"+precio+"', total_con_iva='"+precioConIva+"', ultima_actualizacion='"+ultimaActualizacion+"',ivaCompra='"+ivaCompraArticulo+"', valor_bruto_sin_iva='"+tabla.getValueAt(x,3).toString().replace(",",".")+"' WHERE cod_articulo='"+tabla.getValueAt(x,1)+"'");
                                                                }
                                                                
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

                                                                    }catch(SQLException e){
                                                                        System.out.println(e);
                                                                        JOptionPane.showMessageDialog(null,"Error en el SQL") ;
                                                                    }

                                                                    //BORRO LA FORMA DE PAGO EN EL CASO QUE EN LA MISMA FACTURA SELECCIONEN PRIMERO UNA FORMA DE PAGO Y LUGO OTRA(QUEDA REGISTRADA LA ULTIMA)
                                                                    int xx=0;
                                                                    xx=consulta.executeUpdate("INSERT INTO compra_efectivo (cod_compra,subtotal,iva,total,cod_caja) VALUES ('"+fact.getText()+"','"+subTotalNetoString+"','"+ivaFactura+"','"+total2.getText().replace(",",".")+"','"+codigoCaja+"')");
                                                                    if(xx>0){
                                                                        consulta.executeUpdate("DELETE FROM compra_mixta WHERE cod_compra='"+fact.getText()+"'");
                                                                        consulta.executeUpdate("DELETE FROM compra_credito WHERE cod_compra='"+fact.getText()+"'");
                                                                        consulta.executeUpdate("DELETE FROM compra_deposito WHERE cod_compra='"+fact.getText()+"'");
                                                                        consulta.executeUpdate("DELETE FROM compra_cheque WHERE cod_compra='"+fact.getText()+"'"); 
                                                                    }
                                                                }else if (tipoPago.equals("CREDITO")){
                                                                            //ESTA PARTE ES PARA AGREGAR LOS DATOS AL MODULO CUENTA CREDITO VENTA, PARA CONTROLAR LOS SALDOS DEL CLIENTE
                                                                            Statement consulta3=conn.createStatement(); //BUSCO EL CLIENTE EN LA CUENTA CORRIENTE Y SI NO ESTA LO AGREGO
                                                                            Statement consulta4=conn.createStatement(); //BUSCO EL CLIENTE EN LA CUENTA CORRIENTE Y SI NO ESTA LO AGREGO

                                                                            ResultSet r3= consulta3.executeQuery("select * from cuenta_credito_compra WHERE cod_proveedor='"+txtCodigoProveedor.getText()+"'");
                                                                            int banderaClienteCorriente=0;
                                                                            while(r3.next()){
                                                                                banderaClienteCorriente=1;
                                                                            }
                                                                            ResultSet r4= consulta4.executeQuery("select acuenta,saldo_restante from compra_credito WHERE cod_compra='"+fact.getText()+"'");
                                                                            String pagoCompraCredito="0.00",saldoRestanteCompraCredito="0.00",codCuentaCredito="0";
                                                                            while(r4.next()){
                                                                                pagoCompraCredito=r4.getString(1);
                                                                                saldoRestanteCompraCredito=r4.getString(2);//saldo restante de la venta a credito
                                                                            }

                                                                            //SIEL CLIENTE NO ESTA EN CUENTA CREDITO VENTA LO AGREGO
                                                                            if(banderaClienteCorriente==0){
                                                                                 consulta3.executeUpdate("insert into cuenta_credito_compra (cod_proveedor,ultimo_pago,fecha_ultimo_pago,total_pago,saldo_restante) values('"+txtCodigoProveedor.getText()+"','"+pagoCompraCredito+"','"+fecha+"','"+pagoCompraCredito+"','"+saldoRestanteCompraCredito+"')");

                                                                                //BUSCO EL CODIGO DE LA CUENTA CREDITO VENTA
                                                                                 ResultSet r5= consulta4.executeQuery("select cod_cuenta from cuenta_credito_compra WHERE cod_proveedor='"+txtCodigoProveedor.getText()+"'");

                                                                                 while(r5.next()){
                                                                                     codCuentaCredito=r5.getString(1);
                                                                                 }
                                                                            }else{

                                                                                //BUSCO EL CODIGO DE LA CUENTA CREDITO VENTA
                                                                                ResultSet r5= consulta4.executeQuery("select cod_cuenta from cuenta_credito_compra WHERE cod_proveedor='"+txtCodigoProveedor.getText()+"'");

                                                                                while(r5.next()){
                                                                                    codCuentaCredito=r5.getString(1);
                                                                                }
                                                                                //ACTUALIZO LOS DATOS DE LA CUENTA CREDITO VENTA
                                                                                consulta3.executeUpdate("UPDATE cuenta_credito_compra SET ultimo_pago='"+pagoCompraCredito+"', fecha_ultimo_pago='"+fecha+"', total_pago= total_pago+'"+pagoCompraCredito+"', saldo_restante=saldo_restante+'"+saldoRestanteCompraCredito+"' WHERE cod_cuenta='"+codCuentaCredito+"' ");

                                                                            }
                                                                            ResultSet r6= consulta4.executeQuery("select total_pago, total_pago,saldo_restante from cuenta_credito_compra WHERE cod_cuenta='"+codCuentaCredito+"'");
                                                                            String pagoAcumulado="", saldoRestanteCuentaCredito="0.0", totalPagoCuentaCredito="0.0";
                                                                            while(r6.next()){
                                                                               pagoAcumulado=r6.getString(1);
                                                                               totalPagoCuentaCredito=r6.getString(2);
                                                                               saldoRestanteCuentaCredito=r6.getString(3);
                                                                            }

                                                                            //consulta4.executeUpdate("INSERT INTO referencia_cuenta_credito_compra (cod_cuenta,fecha,pago_abonado,total_pago,saldo_restante,total_factura)VALUES('"+codCuentaCredito+"','"+fecha+"','"+pagoCompraCredito+"','"+total.getText().replace(",",".")+"','"+saldoRestanteCuentaCredito+"','"+(Double.parseDouble(saldoRestanteCuentaCredito)+Double.parseDouble(totalPagoCuentaCredito))+"')");
                                                                            consulta4.executeUpdate("INSERT INTO referencia_cuenta_credito_compra (cod_cuenta,fecha,pago_abonado,total_pago,saldo_restante,total_factura,cod_factura)VALUES('"+codCuentaCredito+"','"+fecha+"','"+pagoCompraCredito+"','"+total.getText().replace(",",".")+"','"+(Double.parseDouble(total.getText().replace(",",".")) - Double.parseDouble(pagoCompraCredito))+"','"+total.getText().replace(",",".")+"','"+fact.getText()+"')");

                                                                        }

                                                                inabilita();

                                                                //***********************************************************************************************************************************************************************************************************************************************************************************************************
                                                                //IMPRIMO EL REPORTE SI EL USUARIO DESEA MOSTRAR LA FACTURA DE COMPRA
                                                                Connection miconexion= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                                                                Map parametros = new HashMap();
                                                                //LE PASO AL REPORTE EL CODIGO DE LA FACTURA DE COMPRA
                                                                parametros.put("codf",fact.getText());

                                                                int op=JOptionPane.showConfirmDialog(null, "DESEA IMPRIMIR LA FACTURA","SELECCIONE UN OPCION" ,JOptionPane.YES_NO_OPTION);
                                                                if (op==JOptionPane.YES_NO_OPTION){
                                                                    try {
                                                                        this.setVisible(false);
                                                                        String reporte="compra.jasper";
                                                                        JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                                                                        JasperViewer ventanavisor=new JasperViewer(informe,false);
                                                                        ventanavisor.setTitle("Reporte de compra");
                                                                        ventanavisor.setVisible(true);
                                                                    } catch (Exception e) {
                                                                        JOptionPane.showMessageDialog(this, e.getMessage());
                                                                    }
                                                                }else
                                                                {
                                                                // no hacer nada
                                                                }                                                       
                                                                
                                                                //ESTE PEDAZO ES PARA SUMAR AL STOCK DE CADA ARTICULO LA CANTIDAD INGRESADA EN LA COMPRA
                                                                for (x=0;x<=fil-1;x++) {
                                                                    String aux=(tabla.getValueAt(x,0)).toString();

                                                                    ResultSet   res= consulta.executeQuery("select cantidad from articulo where cod_articulo='"+tabla.getValueAt(x,1)+"'");
                                                                    while(res.next()){
                                                                        cant_ex=Double.parseDouble(res.getString(1));
                                                                    }

                                                                    nueva_cant = cant_ex + Double.parseDouble(aux);
                                                                    consulta.executeUpdate("UPDATE articulo SET cantidad='"+nueva_cant+"' WHERE cod_articulo='"+tabla.getValueAt(x,1)+"'");
                                                                }

                                                                for (x=0;x<=fil-1;x++) {
                                                                    for (y=0;y<=col-1;y++) {
                                                                        tabla.setValueAt("",x,y);
                                                                    }
                                                                }
                                                                //VACIA LA TABLA DE LA FACTURA
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
            if (banderaFactura==0){ //PARA QUE PASE A UNA NUEVA FACTURA UNA VEZ QUE SE GUARDE LA ACTUAL
                nuevaFactura();
            }
            
        }catch (ArrayIndexOutOfBoundsException e) {
            
        }
    }//GEN-LAST:event_btn_guardarCompraActionPerformed

    private void btnCancelarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCompraActionPerformed
        //BOTON CANCELAR COMPRA
        this.dispose();
    }//GEN-LAST:event_btnCancelarCompraActionPerformed

    private void botonAgregarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarArticuloActionPerformed
        //BOTON PARA AGREGAR UN NUEVO ARTICULO DESDE LA COMPRA
        Agregar_Articulo_Compras form= new Agregar_Articulo_Compras ();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_botonAgregarArticuloActionPerformed

    private void comboFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFacturaActionPerformed
        //DEPENDE EL TIPO DE FACTURA INGRESADO HABILITO O DESHABILITO LOS COMPONENTES PARA INGRESAR UN NUEVO ARTICULO A LA COMPRA
        if(comboFactura.getSelectedItem().equals("SELECCIONAR") || comboProveedor.getSelectedItem().equals("SELECCIONE PROVEEDOR")){
            comboArticulos.setEnabled(false);
            cant.setEditable(false);
            btnAgregarArticulo.setEnabled(false);
            btnQuitarArticulo.setEnabled(false);
            botonAgregarArticulo.setEnabled(false);
            buscare2.setEnabled(false);
        }else{
            comboArticulos.setEnabled(true);
            cant.setEditable(true);
            btnAgregarArticulo.setEnabled(true);
            btnQuitarArticulo.setEnabled(true);
            botonAgregarArticulo.setEnabled(true);
            buscare2.setEnabled(true);
        }
        
        if (comboFactura.getSelectedItem().equals("FACTURA B")){
            txtIva.setEnabled(true);
            txtIva.setEditable(false);
            txtIva.setText("0.00");
            txtIva2.setText("0.00");       
            comboIva.setSelectedItem("0%");
            comboIva.setEnabled(false);
        }else if (comboFactura.getSelectedItem().equals("FACTURA A")){
            txtIva.setEnabled(true);
            txtIva.setEditable(true);
            txtIva.setText("19.00");
            txtIva2.setText("19.00");
            comboIva.setEnabled(true);
            comboIva.setSelectedItem("19%");
        }
        int fil = tabla.getRowCount();
        int x;
        //SI CAMBIO EL TIPO DE IVA VACIO TODA LA TABLA PORQUE SINO ES COMPLICADO ACTUALIZAR TODOS LOS DATOS DENTRO DE LA MISMA
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
        //HABILITO O DESHABILITO EL TEXTFIELD DESCUENTO
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
        // TODO add your handling code here:
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
        //ESTE CHECK ESTA INVISIBLE PERO IGUAL REALIZA LOS CALCULOS (NO BORRAR)
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
        //ESTE CHECK ESTA INVISIBLE PERO IGUAL REALIZA LOS CALCULOS (NO BORRAR)
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
        //SEGUN QUE VALOR DE IVA ESTA SELECCIONADO CAMBIO EL TOTAL Y SUBTOTAL DE LA COMPRA
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
                   total2.setText(precion.replace(",", ".")); 
                   txtIva2.setText(txtIva.getText());
                }else{

                }
        int fil = tabla.getRowCount();
        int x;
        for (x=fil-1;x>=0;x--) {
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            modelo.removeRow(x);
        }
        cant.setText("");
        sub.setText("0.00");
        total.setText("0.00");
        total2.setText("0.00");
        }
    }//GEN-LAST:event_comboIvaActionPerformed

    private void comboContribuyenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboContribuyenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboContribuyenteActionPerformed

    private void checkDescuentoGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDescuentoGeneralActionPerformed
        //PARA CUANDO DESABILITO EL CHECK DEL DESCUENTO GENERAL VUELVAN LOS ARTICULOS AL VALOR INICIAL
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
        //ESTE ES EL DESCUENTO GENERAL (SEGUN EL VALOR INGRESADO AL DAR ENTER APLICA EL DESCUENTO A LOS ARTICULOS QUE ESTAN DENTRO DE LA TABLA)
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

    private void menuItemBuscarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemBuscarProveedorActionPerformed
        //son items ocultos en una menu bar para que anden las teclas de acceso rapido f3, f5, etc
        enviar_proveedor cp = new enviar_proveedor(this, true);
        cp.setVisible(true);   
        cp.txtRecibe.setText("1");
        cp.toFront();
    }//GEN-LAST:event_menuItemBuscarProveedorActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void menuItemAgregarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAgregarProveedorActionPerformed
        //son items ocultos en una menu bar para que anden las teclas de acceso rapido f3, f5, etc
        Agregar_Proveedor form= new Agregar_Proveedor ();
        form.setVisible(true);
        Agregar_Proveedor.recibeProveedor.setText("1");
        form.toFront();
    }//GEN-LAST:event_menuItemAgregarProveedorActionPerformed

    private void menuItemBuscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemBuscarArticuloActionPerformed
        //son items ocultos en una menu bar para que anden las teclas de acceso rapido f3, f5, etc
        enviar_producto form= new enviar_producto ();
        form.setVisible(true);
        form.toFront();
        enviar_producto.txt_recibe.setText("2");
        enviar_producto.txt_recibe.setVisible(false);
        enviar_producto.enviarProducto.setVisible(false);
    }//GEN-LAST:event_menuItemBuscarArticuloActionPerformed

    private void menuItemAgregarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAgregarArticuloActionPerformed
        //son items ocultos en una menu bar para que anden las teclas de acceso rapido f3, f5, etc
        Agregar_Articulo_Compras form= new Agregar_Articulo_Compras ();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_menuItemAgregarArticuloActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Double subtotal2=0.0,subTotalBrutoArt=0.0, porcentIva=0.0;
        //boton para quitar articulos de la factura
        Double variableIva=0.00;
        if(txtIva.getText().equals("19.00")){
               variableIva=1.19;
           }else if(txtIva.getText().equals("0.00")){
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
                for (int x=0;x<=fila2-1;x++) {
                    //CALCULO EL VALOR DEL DESCUENTO
                    double porcent=(Double.parseDouble(tabla.getValueAt(x,5).toString().replace(",", "."))* Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")))/100.00;
                    porcent=Math.round(porcent * 100.0)/ 100.0;
                            
                    //AL SUBTOTAL DEL PRODUCTO LO SETEO CON EL VALOR UNITARIO MENOS(-) EL DESCUENTO POR(*) LA CANTIDAD
                    double totalsub=(Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) - porcent) * Double.parseDouble(tabla.getValueAt(x,0).toString());
                    totalsub=Math.round(totalsub * 100.0)/ 100.0;
                            
                    //PRECIO DE COSTO UNITARIO CON LA VARUABLE DE IVA POR LA CANTIDAD (ESTOS DATOS SON PARA GUARDAR LO QUE ME SALE LA COMPRA CON IVA)
                    double totalsubArticuloBruto=(Double.parseDouble(tabla.getValueAt(x,7).toString().replace(",", "."))) * Double.parseDouble(tabla.getValueAt(x,0).toString());
                    totalsubArticuloBruto=Math.round(totalsubArticuloBruto * 100.0)/ 100.0;
                    //VALOR UNITARIO SIN VARIABLE IVA POR CANTIDAD (ESTOS DATOS SON PARA GUARDAR LO QUE ME SALE LA COMPRA CON IVA)
                    subtotalBruto=Double.parseDouble(tabla.getValueAt(x,3).toString().replace(",", ".")) * Double.parseDouble(tabla.getValueAt(x,0).toString());
                    subtotalBruto=Math.round(subtotalBruto * 100.0)/ 100.0;
                            
                    subtotal33=totalsub+subtotal33;
                    subtotal2 =subtotal2 +subtotalBruto;
                    subTotalBrutoArt=subTotalBrutoArt + totalsubArticuloBruto;
                            
                    tabla.setValueAt(String.format("%.2f", subtotalBruto ).replace(",", "."),x,4);
                    tabla.setValueAt(String.format("%.2f", totalsub).replace(",", "."),x,6);
                    tabla.setValueAt(String.format("%.2f", subTotalBrutoArt),x,8);                       
                }
                        
                //SETEO LOS TEXTFIELD QUE VAN MOSTRANDO EL TOTAL DE LA COMPRA
                porcentIva=Double.parseDouble(txtIva.getText().replace(",", "."))/100;
                subtotalConIva= subtotal33*variableIva;
                sub.setText(String.format("%.2f",subtotal33).replace(",", "."));
                total.setText(String.format("%.2f",subtotalConIva).replace(",", "."));
                total2.setText(""+String.format("%.2f",subtotalConIva));
                txtTotalBrutoArt.setText(""+String.format("%.2f",subTotalBrutoArt));
        
   
                DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();

                fila-=1;
                int filat=tabla.getRowCount();
                if(filat==0){
                    txtTotalBrutoArt.setText("0,00");
                }else{
                    txtTotalBrutoArt.setText(tabla.getValueAt(fila, 8).toString());
                }
                if (filat==0){
                    comboCategoria.setEnabled(true);
                    comboProveedor.setEnabled(true);
                }
            }catch (ArrayIndexOutOfBoundsException e) {        
                //JOptionPane.showMessageDialog(null, "NO HAY PRODUCTOS PARA QUITAR");       
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
                Editar_Articulo.recibeCliente.setText("6");
                Editar_Articulo.cargarComboProveedores();
                Editar_Articulo.CargarArticulo(txtCodigoArticulo1.getText());
                form.toFront();
            }else{
                JOptionPane.showMessageDialog(null,"El codigo ingresado no pertenece a ningun articulo del inventario");
                txtCodigoArticulo1.requestFocus();
            }
        }

    }//GEN-LAST:event_btnEditarArticuloActionPerformed

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
            java.util.logging.Logger.getLogger(Factura_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Factura_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Factura_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Factura_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Factura_Compra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu QuitarArticulo;
    private javax.swing.JButton botonAgregarArticulo;
    private javax.swing.JButton botonAgregarCliente;
    private javax.swing.JButton btnAgregarArticulo;
    private javax.swing.JButton btnCancelarCompra;
    private javax.swing.JButton btnEditarArticulo;
    private javax.swing.JButton btnQuitarArticulo;
    private javax.swing.JButton btn_guardarCompra;
    private javax.swing.JButton buscare1;
    private javax.swing.JButton buscare2;
    private com.toedter.calendar.JDateChooser calendario;
    private javax.swing.JTextField cant;
    private javax.swing.JCheckBox checkDescuento;
    private javax.swing.JCheckBox checkDescuentoGeneral;
    private javax.swing.JCheckBox checkIva1;
    public static javax.swing.JComboBox comboArticulos;
    public static javax.swing.JComboBox comboCaja;
    public static javax.swing.JComboBox comboCategoria;
    public static javax.swing.JComboBox comboContribuyente;
    public static javax.swing.JComboBox comboFactura;
    public static javax.swing.JComboBox comboIva;
    public static javax.swing.JComboBox comboPago;
    public static javax.swing.JComboBox comboProveedor;
    private javax.swing.JTextField fact;
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
    private javax.swing.JMenuItem menuItemAgregarArticulo;
    private javax.swing.JMenuItem menuItemAgregarProveedor;
    private javax.swing.JMenuItem menuItemBuscarArticulo;
    private javax.swing.JMenuItem menuItemBuscarProveedor;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    public static javax.swing.JTextField sub;
    private javax.swing.JTable tabla;
    public static javax.swing.JTextField total;
    private javax.swing.JTextField total2;
    public static javax.swing.JTextField txtCodigoArticulo1;
    public static javax.swing.JTextField txtCodigoEmpleado;
    public static javax.swing.JTextField txtCodigoProveedor;
    public static javax.swing.JTextField txtCuit;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtDescuentoGeneral;
    public static javax.swing.JTextField txtDomicilio;
    private javax.swing.JTextField txtDtoGral;
    private javax.swing.JLabel txtFacturaNumero;
    public static javax.swing.JTextField txtIva;
    public static javax.swing.JTextField txtIva2;
    private javax.swing.JTextField txtIvaFactura;
    public static javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtTotalBrutoArt;
    // End of variables declaration//GEN-END:variables
}
