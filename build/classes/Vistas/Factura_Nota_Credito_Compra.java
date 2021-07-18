
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


public class Factura_Nota_Credito_Compra extends javax.swing.JFrame {

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
             String codigocliente;
             String nomcliente;
             String NOMBRE;
             String dia, mes, año;
    
    public void cerrar(){
        this.setVisible(false);  
    }
      
    public Factura_Nota_Credito_Compra() {    
        initComponents(); 
        setLocationRelativeTo(null);
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        cargarCombos();
        nuevaFactura();
        
        //color cabecera tabla
        JTableHeader th; 
        th = tabla.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        //ancho columnas tabla
        tabla.getColumnModel().getColumn(0).setMaxWidth(60);
        tabla.getColumnModel().getColumn(0).setMinWidth(60);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(60);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(60);
        
        tabla.getColumnModel().getColumn(1).setMaxWidth(90);
        tabla.getColumnModel().getColumn(1).setMinWidth(90);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(90);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(90);
        
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
          
    Factura_Nota_Credito_Compra(Menu_Principal aThis, boolean b) {
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
        txtCodigoArticulo.setVisible(true);
        txtTotalBrutoArt.setVisible(false);
        total2.setVisible(false);
        txtIva.setVisible(false);
        txtTipo.setVisible(false);
        txtDescuento.setText("");
        txtDomicilio.setText("");
        txtDescuento.setEditable(false); 
        txtNroFactura.setEditable(false);
        txtPrecioArticulo.setVisible(false);
        txtCodigoArticulo.setVisible(false);
        //txtCuit.setEditable(false);
        //txtTipo.setEditable(false);
        txtCuit.setText("");
        txtTipo.setText("");
        comboArticulo.setSelectedIndex(0);
        comboArticulo.setVisible(false);
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
        //PARA AGREGAR EL NUMERO DE FACTURA
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            ResultSet r= consulta.executeQuery("select cod_nota_credito_compra from  nota_credito_compra order by cod_nota_credito_compra desc");
            r.next();
            fact.setText(r.getString(1));
            int f;         
            f=Integer.parseInt(fact.getText())+1;
            fact.setText(String.valueOf(f));
            r.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
        //txtFacturaNumero.setText("NOTA DE CREDITO COMPRA N° "+fact.getText());
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
        setearFecha();
    
        calendario.setEnabled(false);

        comboVendedor.setEnabled(false);
        comboArticulo.setEnabled(false);
        
        txtDescuento.setEnabled(false);
        comboPago.setEnabled(false);
        buscarVendedor.setEnabled(false);
        buscarArticulo.setEnabled(false);
        cant.setEditable(true);
        btnAgregarArticulo.setEnabled(false);
        btnQuitarArticulo.setEnabled(false);

        btnCancelarNota.setEnabled(false);
        btnGuardarNota.setEnabled(false);
        btnCancelarNota.setEnabled(false);
        sub.setEnabled(false);
        txtIva.setEnabled(false);
        total.setEnabled(false);

        total2.setVisible(false);
        fact.setVisible(false);
        txtCodigoEmpleado.setVisible(false);
        txtCodigoProveedor.setVisible(false);
        txtFacturaNumero.setText("NOTA DE CREDITO COMPRA");  
    }
    
    public void habilitar(){
        calendario.setEnabled(true); 

        txtDescuento.requestFocus();
        comboVendedor.setEnabled(true);
        comboArticulo.setEnabled(false);
        comboPago.setEnabled(true);
        
        buscarVendedor.setEnabled(true);
        buscarArticulo.setEnabled(false);

        cant.setEditable(true);
        btnAgregarArticulo.setEnabled(false);
        btnQuitarArticulo.setEnabled(false);
        btnCancelarNota.setEnabled(true);
        btnGuardarNota.setEnabled(true);
        
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
            
            int i;
            comboArticulo.removeAllItems();
            comboArticulo.addItem("SELECCIONE ARTICULO");
            i=0;
            while(res.next()){
                comboArticulo.addItem(res.getString(2));
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
        buscarArticulo = new javax.swing.JButton();
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
        btnGuardarNota = new javax.swing.JButton();
        btnCancelarNota = new javax.swing.JButton();
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
        txtProveedor = new javax.swing.JTextField();
        txtNombreArticulo = new javax.swing.JTextField();
        txtCodigoEmpleado = new javax.swing.JTextField();
        txtPrecioArticulo = new javax.swing.JTextField();
        comboCaja = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        txtFacturaNumero = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtFacturaNumero1 = new javax.swing.JLabel();
        txtNroFactura = new javax.swing.JTextField();
        buscarFactura = new javax.swing.JButton();
        calendario = new com.toedter.calendar.JDateChooser();
        txtCodigoProveedor = new javax.swing.JTextField();
        fact = new javax.swing.JTextField();
        comboVendedor = new javax.swing.JComboBox();
        buscarVendedor = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        comboArticulo = new javax.swing.JComboBox();
        txtCodigoArticulo = new javax.swing.JTextField();
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
        setTitle("Devolución de compra");
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
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 0, 80, 40));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setBackground(new java.awt.Color(239, 255, 239));
        jLabel9.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel9.setText("Articulo");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 100, 40));

        buscarArticulo.setBackground(new java.awt.Color(93, 116, 163));
        buscarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoLupaF5.png"))); // NOI18N
        buscarArticulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarArticuloActionPerformed(evt);
            }
        });
        buscarArticulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscarArticuloKeyTyped(evt);
            }
        });
        jPanel1.add(buscarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 60, 39));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel6.setText("Cantidad");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 110, 40));

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
        jPanel1.add(cant, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 120, 39));

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
        jPanel1.add(btnAgregarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 140, 38));

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
        jPanel1.add(btnQuitarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, 140, 38));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 1000, 140));

        jLabel7.setBackground(new java.awt.Color(239, 255, 239));
        jLabel7.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel7.setText("Cond");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 60, 40));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel11.setText("IVA Discriminado");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 63, 200, 40));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel13.setText("Importe Total");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 170, 40));

        total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 200, 40));

        sub.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sub.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(sub, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 200, 40));

        txtIva2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIva2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtIva2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 200, 40));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel12.setText("Subtotal");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 13, 110, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 410, 410, 160));

        btnGuardarNota.setBackground(new java.awt.Color(51, 153, 255));
        btnGuardarNota.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnGuardarNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardar.png"))); // NOI18N
        btnGuardarNota.setBorder(null);
        btnGuardarNota.setBorderPainted(false);
        btnGuardarNota.setContentAreaFilled(false);
        btnGuardarNota.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarNota.setFocusCycleRoot(true);
        btnGuardarNota.setFocusable(false);
        btnGuardarNota.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btnGuardarNota.setRequestFocusEnabled(false);
        btnGuardarNota.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btnGuardarNota.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btnGuardarNota.setVerifyInputWhenFocusTarget(false);
        btnGuardarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarNotaActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardarNota, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 120, 160));

        btnCancelarNota.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelarNota.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnCancelarNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelar.png"))); // NOI18N
        btnCancelarNota.setBorder(null);
        btnCancelarNota.setBorderPainted(false);
        btnCancelarNota.setContentAreaFilled(false);
        btnCancelarNota.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCancelarNota.setFocusCycleRoot(true);
        btnCancelarNota.setFocusable(false);
        btnCancelarNota.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarNota.setRequestFocusEnabled(false);
        btnCancelarNota.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarNota.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarNota.setVerifyInputWhenFocusTarget(false);
        btnCancelarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarNotaActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelarNota, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, -1, 160));

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel19.setText("Cancelar");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 510, 90, 40));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel17.setText("Facturar");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, -1, 40));

        comboPago.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        comboPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPagoActionPerformed(evt);
            }
        });
        jPanel1.add(comboPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 360, 280, 40));

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
        jPanel1.add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 40, 40));

        jLabel15.setBackground(new java.awt.Color(239, 255, 239));
        jLabel15.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel15.setText("Proveedor");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 40));

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
        jPanel1.add(txtCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 160, 40));

        jLabel16.setBackground(new java.awt.Color(239, 255, 239));
        jLabel16.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel16.setText("NIT");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 60, 40));

        comboFactura.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        comboFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFacturaActionPerformed(evt);
            }
        });
        jPanel1.add(comboFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, 300, 40));

        jLabel18.setBackground(new java.awt.Color(239, 255, 239));
        jLabel18.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel18.setText("% iVA");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 110, 80, 40));

        txtTotalBrutoArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalBrutoArtActionPerformed(evt);
            }
        });
        jPanel1.add(txtTotalBrutoArt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 40, 40));
        jPanel1.add(txtIvaFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 40, 40));
        jPanel1.add(total2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 40, 40));

        checkIva1.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        checkIva1.setText("% Iva");
        checkIva1.setOpaque(false);
        checkIva1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkIva1ActionPerformed(evt);
            }
        });
        jPanel1.add(checkIva1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 80, 40));

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
        jPanel1.add(txtIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 40, 40));

        jLabel21.setBackground(new java.awt.Color(239, 255, 239));
        jLabel21.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel21.setText("Tipo de factura");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, -1, 40));

        txtDomicilio.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        txtDomicilio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDomicilioActionPerformed(evt);
            }
        });
        jPanel1.add(txtDomicilio, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 300, 40));

        jLabel22.setBackground(new java.awt.Color(239, 255, 239));
        jLabel22.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel22.setText("Tipo de pago");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 360, 150, 40));

        comboIva.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        comboIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIvaActionPerformed(evt);
            }
        });
        jPanel1.add(comboIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 110, 100, 40));

        comboContribuyente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboContribuyenteActionPerformed(evt);
            }
        });
        jPanel1.add(comboContribuyente, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 190, 40));

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

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 410, 250, 160));

        txtProveedor.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(txtProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 420, 40));

        txtNombreArticulo.setFont(new java.awt.Font("Calibri", 0, 17)); // NOI18N
        txtNombreArticulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreArticuloActionPerformed(evt);
            }
        });
        jPanel1.add(txtNombreArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 350, 40));

        txtCodigoEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoEmpleadoActionPerformed(evt);
            }
        });
        jPanel1.add(txtCodigoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 40, 30));

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
        jPanel1.add(txtPrecioArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 40, 40));

        comboCaja.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCajaActionPerformed(evt);
            }
        });
        jPanel1.add(comboCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 360, 250, 40));

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1020, 580));

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtFacturaNumero.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        txtFacturaNumero.setText("NOTA DE CREDITO DE COMPRA");
        jPanel2.add(txtFacturaNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 510, 30));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 40));

        txtFacturaNumero1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        txtFacturaNumero1.setText("N° de factura");
        jPanel2.add(txtFacturaNumero1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 160, 40));

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
        jPanel2.add(txtNroFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 150, 40));

        buscarFactura.setBackground(new java.awt.Color(93, 116, 163));
        buscarFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoLupaF3.png"))); // NOI18N
        buscarFactura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarFacturaActionPerformed(evt);
            }
        });
        jPanel2.add(buscarFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 60, 39));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 570, 100));

        calendario.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jPanel4.add(calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 210, 40));

        txtCodigoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoProveedorActionPerformed(evt);
            }
        });
        jPanel4.add(txtCodigoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 40, 30));

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
        jPanel4.add(fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 30, 30));

        comboVendedor.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        comboVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboVendedorActionPerformed(evt);
            }
        });
        jPanel4.add(comboVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, 300, 38));

        buscarVendedor.setBackground(new java.awt.Color(93, 116, 163));
        buscarVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoLupaF1.png"))); // NOI18N
        buscarVendedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarVendedorActionPerformed(evt);
            }
        });
        buscarVendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscarVendedorKeyPressed(evt);
            }
        });
        jPanel4.add(buscarVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, 60, 38));

        jLabel14.setBackground(new java.awt.Color(239, 255, 239));
        jLabel14.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel14.setText("Vendedor");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 120, 40));

        comboArticulo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        comboArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboArticuloActionPerformed(evt);
            }
        });
        jPanel4.add(comboArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 60, -1));

        txtCodigoArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoArticuloActionPerformed(evt);
            }
        });
        jPanel4.add(txtCodigoArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 30, 40));

        panelImage1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 690));

        jMenuBar1.setBackground(new java.awt.Color(204, 204, 204));

        jMenu1.setBackground(new java.awt.Color(204, 204, 204));
        jMenu1.setForeground(new java.awt.Color(204, 204, 204));
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
        //SEGUN EL NOMBRE QUE TENGO SELECCIONADO EN EL COMBO VENDEDOR, CARGO EL CODIGO DEL VENDEDOR QUE VA EN LA TABLA NOTA_CREDITO_COMPRA
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
        
        //RESTRICCIONES SI NO HAY UN VENDEDOR SELECCIONADO NO DEJA CARGAR ARTICULOS
        if(comboVendedor.getSelectedItem().equals("SELECCIONE EMPLEADO")){
            comboArticulo.setEnabled(false);
            cant.setEditable(false);
            btnAgregarArticulo.setEnabled(false);
            btnQuitarArticulo.setEnabled(false);
            buscarArticulo.setEnabled(false);
        }else{
            comboArticulo.setEnabled(true);
            cant.setEditable(true);
            btnAgregarArticulo.setEnabled(true);
            btnQuitarArticulo.setEnabled(true);
            buscarArticulo.setEnabled(true);
        }
    }//GEN-LAST:event_comboVendedorActionPerformed

    private void buscarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarVendedorActionPerformed
        enviar_empleado form= new enviar_empleado ();
        form.setVisible(true);
        form.toFront();
        enviar_empleado.txt_recibeEmpleado.setText("1");
        enviar_empleado.txt_recibeEmpleado.setVisible(false);
        enviar_empleado.enviarEmpleado.setVisible(false);
    }//GEN-LAST:event_buscarVendedorActionPerformed

    public void cargarComboEmpleado(){
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            nomempleado = (String)comboVendedor.getSelectedItem();
            cod_empleado=null;
            
            //TRAIGO EL NOMBRE Y APELLIDO DEL VENDEDOR DESDE EL COMBO, LO DIVIDO EN 2 CADENAS PORQUE EN LA BD ESTAN SEPARADOS Y BUSCO SU CODIGO EN LA BD PARA LUEGO GUARDARLOS EN L A FACTURA
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
        //SEGUN SELECCIONE EL ARTICULO EN EL COMBO BOX TRAIGO EL CODIGO DEL MISMO
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

    private void buscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarArticuloActionPerformed
        enviar_producto_credito_compra form= new enviar_producto_credito_compra ();
        form.setVisible(true);
        form.toFront();
        form.txt_recibe.setText(txtNroFactura.getText());
        form.cargarTabla(txtNroFactura.getText(),"COMPRA");
        form.labelFactura.setText("ARTICULOS DE LA FACTURA N° "+txtNroFactura.getText());
        form.enviarProducto.setVisible(false);
    }//GEN-LAST:event_buscarArticuloActionPerformed

    private void btnAgregarArticuloMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarArticuloMouseEntered

    }//GEN-LAST:event_btnAgregarArticuloMouseEntered

    private void btnAgregarArticuloMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarArticuloMouseExited

    }//GEN-LAST:event_btnAgregarArticuloMouseExited

    public void agregarArticulo(){
        //AGREGO EL ARTICULO A LA FACTURA
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
                        ResultSet   res= consulta.executeQuery("select cantidad from referencia_compra where codigo_producto = '"+txtCodigoArticulo.getText()+"' AND cod_compra='"+txtNroFactura.getText()+"'");
                        while(res.next()){
                            cant_ex=Double.parseDouble(res.getString(1));
                            break;
                        }
                        res.close();
                        
                    }catch(SQLException e){
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null,"Error en el SQL") ;
                    }
                    double cantidad = Double.parseDouble(cant.getText().replace(',','.'));
                    if (cant_ex < cantidad)
                    {
                        JOptionPane.showMessageDialog(null, "En la factura de compra N° "+txtNroFactura.getText()+" no existe la cantidad ingresada del articulo. La Cantidad Dispomible es: "+cant_ex);
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
                        tabla.setValueAt(txtCodigoArticulo.getText(),fila,1);
                        tabla.setValueAt(txtNombreArticulo.getText(),fila,2);
                        try{
                            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

                            ResultSet r= consulta.executeQuery("select valor_unitario, unitario_costo from referencia_compra where (codigo_producto= '"+txtCodigoArticulo.getText()+"' OR referencia like '"+txtNombreArticulo.getText()+"') AND cod_compra='"+txtNroFactura.getText()+"'");
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

                       tabla.setValueAt(txtPrecioArticulo.getText(),fila,3);
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
                        total.setText(String.format("%.2f",subtotalConIva).replace(",", "."));
                        total2.setText(""+String.format("%.2f",subtotalConIva));
                        txtTotalBrutoArt.setText(""+String.format("%.2f",subTotalBrutoArt));
                        
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
        //QUITO EL ULTIMO ARTICULO AGREGADO A LA FACTURA
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
                    comboVendedor.setEnabled(true);
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

    private void buscarVendedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarVendedorKeyPressed

    }//GEN-LAST:event_buscarVendedorKeyPressed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
           JOptionPane.showMessageDialog(null,"Ingrese el Dato");
        }
    }//GEN-LAST:event_formKeyTyped

    private void buscarArticuloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarArticuloKeyTyped

    }//GEN-LAST:event_buscarArticuloKeyTyped
    
    public void modificarArticuloFactura(){
        //CUANDO CAMBIO LA CANTIDAD O EL PRECIO DEL ARTICULO CLICKEANDO LA TABLA..
        int Select=tabla.getSelectedRow(), bandera=0;
        Double subtotal2=0.0;   
        subTotalBrutoArticulo=0.0;
        int fila2 = tabla.getRowCount();
        subtotal=0;
        for (int x=0;x<=fila2-1;x++) {
            try{
                Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                 ResultSet   res= consulta.executeQuery("select cantidad from referencia_compra where codigo_producto = '"+tabla.getValueAt(Select,1).toString()+"' AND cod_compra='"+txtNroFactura.getText()+"'");
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
                JOptionPane.showMessageDialog(null, "¡ATENCION! La Cantidad Disponible de Dicho Articulo en la factura de compra N° "+txtNroFactura.getText()+" es "+cant_ex+" unidades");
                bandera=1;
                tabla.setValueAt(cant_ex,Select,0);
            }

            
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
        total.setText(String.format("%.2f",subtotal).replace(",", "."));
        total2.setText(""+String.format("%.2f",subtotal).replace(",", "."));
        txtTotalBrutoArt.setText(String.format("%.2f",subTotalBrutoArticulo).replace(",", "."));
        
        comboArticulo.setSelectedIndex(0);
        cant.setText("");
    }
    private void tablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyReleased
        modificarArticuloFactura(); 
    }//GEN-LAST:event_tablaKeyReleased

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

    private void txtCodigoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoProveedorActionPerformed

    private void btnGuardarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarNotaActionPerformed
        //GUARDO LA NOTA DE CREDITO EN LA BD
        int banderaFactura=0;
        String tipoFacturaString="",titularEmpresa="", nombreEmpresa="",direccionEmpresa="",cuitEmpresa="",contribuyenteEmpresa="",telefonoEmpresa="",ingresosBrutos="",inicioActividades="";

        txtCuit.setBackground(new Color(255,255,255));
        txtNroFactura.setBackground(new Color(255,255,255));
        try {
            if (comboVendedor.getSelectedItem().equals("SELECCIONE EMPLEADO")) {
                 banderaFactura=1;
                JOptionPane.showMessageDialog(null, "Falta elegir el Empleado","Advertencia",JOptionPane.WARNING_MESSAGE);
            }else {
                if (txtProveedor.getText().equals("")) {
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
                                                            }

                                                            tipoFacturaString=comboFactura.getSelectedItem().toString(); //TIPO DE FACTURA 1.A,6.B
                                                            if (tipoFacturaString.equals("FACTURA A")){
                                                                tipoFacturaString="A";

                                                            }else{
                                                                tipoFacturaString="B";  

                                                            }

                                                            int fil = tabla.getRowCount();
                                                            int col = tabla.getColumnCount();

                                                            int año = calendario.getCalendar().get(Calendar.YEAR);
                                                            int mes = calendario.getCalendar().get(Calendar.MONTH);
                                                            int dia = calendario.getCalendar().get(Calendar.DAY_OF_MONTH);
                                                            
                                                            //GUARDO LA FECHA DE LA FACTURA
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

                                                            if(comboIva.getSelectedItem().toString().equals("19%")){
                                                                variableIva=1.19;
                                                                porcentIva=0.19;
                                                                porcentajeIvaFactura=19.0;

                                                            }else if(comboIva.getSelectedItem().toString().equals("0%")){
                                                                        variableIva=1.00;
                                                                        porcentIva=0.0;
                                                                        porcentajeIvaFactura=0.0;

                                                                    }
                                                            Double subTotalNeto=Double.parseDouble(sub.getText());
                                                            subTotalNeto=Math.round(subTotalNeto * 100.0)/ 100.0;

                                                            Double TotalNeto=Double.parseDouble(total2.getText().replace(",","."));
                                                            String TotalNetoString=String.format("%.2f", TotalNeto).replace(",", ".");


                                                            String subTotalNetoString=String.format("%.2f", subTotalNeto).replace(",", ".");
                                                            int x,y;

                                                            String ivaFactura=txtIva2.getText(); //GUARDO EL IVA QUE TIENE LA FACTURA
                                                            
                                                            //GUARDO LOS DATOS EN LA TABLA NOTA_CREDITO_COMPRA EN LA BD
                                                            consulta.executeUpdate("insert into nota_credito_compra (cod_nota_credito_compra, fecha,cod_proveedor,cod_empleado,tipo_pago,condicion,iva,tipo_nota_credito,total_con_iva,total_sin_iva,ivaDiscriminado,referencia_factura) values('"+fact.getText()+"','"+fecha+"','"+txtCodigoProveedor.getText()+"','"+txtCodigoEmpleado.getText()+"','"+comboPago.getSelectedItem()+"','ACTIVA','"+ivaFactura+"','"+tipoFacturaString+"','"+TotalNetoString+"','"+subTotalNetoString+"','"+txtIva2.getText().replace(",",".")+"','"+txtNroFactura.getText().replace(",",".")+"')");
                                                           //GUARDO CADA ARTICULO ENREFERENCIA_NOTA_CREDITO-COMPRA
                                                            for (x=0;x<=fil-1;x++) {
                                                                consulta1.executeUpdate("insert into referencia_nota_credito_compra (cod_nota_credito_compra,codigo_producto,valor_unitario,valor_total,referencia,cantidad,Total,unitarioBruto,totalBruto,descuento) values('"+fact.getText()+"','"+tabla.getValueAt(x,1)+"'  ,'"+tabla.getValueAt(x,3)+"','"+tabla.getValueAt(x,6).toString().replace(",",".")+"','"+tabla.getValueAt(x,2)+"','"+tabla.getValueAt(x,0)+"','"+total2.getText().replace(",",".")+"','"+tabla.getValueAt(x,7)+"','"+txtTotalBrutoArt.getText().replace(",",".")+"','"+tabla.getValueAt(x,5)+"')");
                                                                
                                                            }
                                                            
                                                            //SI LA DEVOLUCION SE REALIZA EN EFECTIVO LO GUARDO EN LA TABLA DEVOLUCION COMPRA PARA QUE APAREZCA EN EL CIERREDE CAJA
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

                                                                    consulta.executeUpdate("INSERT INTO devolucion_compra (cod_caja,cod_compra,fecha,monto_efectivo) VALUES ('"+codigoCaja+"','"+codigoCaja+"','"+fecha+"','"+TotalNetoString+"')");

                                                                }catch(SQLException e){
                                                                    System.out.println(e);
                                                                    JOptionPane.showMessageDialog(null,"Error en el SQL") ;
                                                                }

                                                            }
                                                            inabilita();

                                                            //***********************************************************************************************************************************************************************************************************************************************************************************************************
                                                            //IMPRIMO EL REPORTE
                                                            Connection miconexion = conexion.ObtenerConexion();
                                                            Map parametros = new HashMap();
                                                            parametros.put("codf",fact.getText());

                                                            int op=JOptionPane.showConfirmDialog(null, "DESEA IMPRIMIR LA NOTA DE CREDITO","SELECCIONE UN OPCION" ,JOptionPane.YES_NO_OPTION);
                                                            if (op==JOptionPane.YES_NO_OPTION){
                                                                try {
                                                                    this.setVisible(false);
                                                                    String reporte="notaCreditoCompra1.jasper";
                                                                    JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                                                                    JasperViewer ventanavisor=new JasperViewer(informe,false);
                                                                    ventanavisor.setTitle("Reporte de nota de credito");
                                                                    ventanavisor.setVisible(true);
                                                                } catch (Exception e) {
                                                                    JOptionPane.showMessageDialog(this, e.getMessage());
                                                                }
                                                            }else
                                                            {
                                                            // no hacer nada
                                                            }                                                       
                                                            
                                                            //DECREENTO LA CANTIDAD DE CADA ARTICULO YA QUE SE LO DEVOLVI AL PROVEEDOR
                                                            for (x=0;x<=fil-1;x++) {
                                                                String aux=(tabla.getValueAt(x,0)).toString();

                                                                ResultSet   res= consulta.executeQuery("select cantidad from articulo where cod_articulo='"+tabla.getValueAt(x,1)+"'");
                                                                while(res.next()){
                                                                    cant_ex=Double.parseDouble(res.getString(1));
                                                                }

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
            if (banderaFactura==0){ //PARA QUE AL REALIZAR TODO CAMBIE A UNA NUEVA FACTURA
                nuevaFactura();
            }
            
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"error en la base de datos (nota credito compra 1832)") ;
        }
    }//GEN-LAST:event_btnGuardarNotaActionPerformed

    private void btnCancelarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarNotaActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarNotaActionPerformed

    private void comboFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFacturaActionPerformed
        
        if(comboFactura.getSelectedItem().equals("SELECCIONAR") || comboVendedor.getSelectedItem().equals("SELECCIONE EMPLEADO")){
            comboArticulo.setEnabled(false);
            cant.setEditable(false);
            btnAgregarArticulo.setEnabled(false);
            btnQuitarArticulo.setEnabled(false);
            buscarArticulo.setEnabled(false);
        }else{
            comboArticulo.setEnabled(true);
            cant.setEditable(true);
            btnAgregarArticulo.setEnabled(true);
            btnQuitarArticulo.setEnabled(true);
            buscarArticulo.setEnabled(true);
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
        int fil = tabla.getRowCount();
        int x;
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
        //este check esta invisible pero igual realiza los calculos y cambia las varuables NO BORRAR
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
        //este txt esta invisible pero igual realiza los calculos y cambia las varuables NO BORRAR
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
        //SEGUN EL IVA SELECCIONADO EN EL COMBO CAMBIO LOS VALORES DE TOTAL Y SUBTOTAL
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
                   txtIva2.setText(txtIva.getText());
                }else{

                }
        }
    }//GEN-LAST:event_comboIvaActionPerformed

    private void comboContribuyenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboContribuyenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboContribuyenteActionPerformed

    private void checkDescuentoGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDescuentoGeneralActionPerformed
        //SI DESACTIVO EL DESCUENTO GENERAL LOS VALORES VUELVEN A COMO ESTABAN EN LA TABLA
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
                //String aux= (String) (tabla.getValueAt(x,1));
                //double totalsub=Double.parseDouble((tabla.getValueAt(x,2).toString())+(porcentajeIva))*Integer.parseInt((tabla.getValueAt(x,0).toString())); //a total sub le multiplico la cantidad por el precio de la fila
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
            /*double doble = Double.parseDouble(txtIva.getText().replace(",", "."));*/
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
        //SEGUN EL VALOR QUE INGRESE LO APLICA COMO DESCUENTO GENERAL A LA TABLA DONDE ESTAN LOS ARTICULOS EN LA FACTURA
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

    private void buscarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarFacturaActionPerformed
       //BOTON BUSCAR FACTURA
        new mostrar_compras ().setVisible(true);
        mostrar_compras.txtRecibe.setText("2");
    }//GEN-LAST:event_buscarFacturaActionPerformed

    private void txtProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProveedorActionPerformed

    private void txtNombreArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreArticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreArticuloActionPerformed

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

    private void itemBuscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarArticuloActionPerformed
        //item oculto en un jmenu bar para que funcionen los accesos rapidos f5,f3 etc
        enviar_producto_credito_compra form= new enviar_producto_credito_compra ();
        form.setVisible(true);
        form.toFront();
        form.txt_recibe.setText(txtNroFactura.getText());
        form.cargarTabla(txtNroFactura.getText(),"COMPRA");
        form.labelFactura.setText("ARTICULOS DE LA FACTURA N° "+txtNroFactura.getText());
        //enviar_producto.txt_recibe.setVisible(false);
        form.enviarProducto.setVisible(false);
    }//GEN-LAST:event_itemBuscarArticuloActionPerformed

    private void itemBuscarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarVendedorActionPerformed
        //item oculto en un jmenu bar para que funcionen los accesos rapidos f5,f3 etc
        enviar_empleado form= new enviar_empleado ();
        form.setVisible(true);
        form.toFront();
        enviar_empleado.txt_recibeEmpleado.setText("1");
        enviar_empleado.txt_recibeEmpleado.setVisible(false);
        enviar_empleado.enviarEmpleado.setVisible(false);
    }//GEN-LAST:event_itemBuscarVendedorActionPerformed

    private void itemBuscarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarFacturaActionPerformed
        //item oculto en un jmenu bar para que funcionen los accesos rapidos f5,f3 etc
        new mostrar_compras ().setVisible(true);
        mostrar_compras.txtRecibe.setText("2");
    }//GEN-LAST:event_itemBuscarFacturaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Double subtotal2=0.0,subTotalBrutoArt=0.0, porcentIva=0.0;
        //QUITO EL ULTIMO ARTICULO AGREGADO A LA FACTURA
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
                    comboVendedor.setEnabled(true);
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
            java.util.logging.Logger.getLogger(Factura_Nota_Credito_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Factura_Nota_Credito_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Factura_Nota_Credito_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Factura_Nota_Credito_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Factura_Nota_Credito_Compra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu QuitarArticulo;
    private javax.swing.JButton btnAgregarArticulo;
    private javax.swing.JButton btnCancelarNota;
    private javax.swing.JButton btnGuardarNota;
    private javax.swing.JButton btnQuitarArticulo;
    private javax.swing.JButton buscarArticulo;
    private javax.swing.JButton buscarFactura;
    private javax.swing.JButton buscarVendedor;
    private com.toedter.calendar.JDateChooser calendario;
    public static javax.swing.JTextField cant;
    private javax.swing.JCheckBox checkDescuento;
    private javax.swing.JCheckBox checkDescuentoGeneral;
    private javax.swing.JCheckBox checkIva1;
    public static javax.swing.JComboBox comboArticulo;
    public static javax.swing.JComboBox comboCaja;
    public static javax.swing.JComboBox comboContribuyente;
    public static javax.swing.JComboBox comboFactura;
    public static javax.swing.JComboBox comboIva;
    public static javax.swing.JComboBox comboPago;
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
    public static javax.swing.JTextField txtCodigoArticulo;
    public static javax.swing.JTextField txtCodigoEmpleado;
    public static javax.swing.JTextField txtCodigoProveedor;
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
    public static javax.swing.JTextField txtProveedor;
    public static javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtTotalBrutoArt;
    // End of variables declaration//GEN-END:variables
}
