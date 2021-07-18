
package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class mostrar_compras extends javax.swing.JFrame {
    int Select;
    String codigo,nombre,apellido,fecha,empleado;
    boolean banderaFacturado=false;
    String NOMBRE, signo_moneda;

    public mostrar_compras() {
        initComponents();
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        } 
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            ResultSet r2= consulta.executeQuery("select signo_moneda from  monedas where seleccion_moneda like 'SELECCIONADA'");
            while(r2.next()){
                signo_moneda=r2.getString(1);
            }
        }catch(Exception e){
            
        }
        
        setLocationRelativeTo(null);
        txtRecibe.setVisible(false);
        txtCodigoProveedor.setVisible(false);
        txtBanderaRemito.setVisible(false);
        cargarRemitoNoFacturado();   
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        iniciar();
        JTableHeader th; 
        th = tabla.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        JTableHeader thh; 
        thh = tabla2.getTableHeader(); 
        Font fuente2 = new Font("Calibri", Font.BOLD, 20); 
        thh.setFont(fuente2); 
        thh.setBackground(new Color(93,116,163));
        thh.setForeground(new Color(255,255,255));
    }    
    
    
    public void cargarRemitoNoFacturado(){
        TableColumn  column = null;    
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta.executeQuery("select * from compra where condicion='ACTIVA' order by cod_compra");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            tabla.setRowSorter(new TableRowSorter(modelo));
            
            tabla.getColumnModel().getColumn(0).setMaxWidth(120);
            tabla.getColumnModel().getColumn(0).setMinWidth(120);
            tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(120);
            tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(120);
            
            tabla.getColumnModel().getColumn(1).setMaxWidth(80);
            tabla.getColumnModel().getColumn(1).setMinWidth(80);
            tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(80);
            tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(80);
            
            tabla.getColumnModel().getColumn(2).setMaxWidth(120);
            tabla.getColumnModel().getColumn(2).setMinWidth(120);
            tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(120);
            tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(120);
            
            tabla.getColumnModel().getColumn(4).setMaxWidth(0);
            tabla.getColumnModel().getColumn(4).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
            
            tabla.getColumnModel().getColumn(5).setMaxWidth(150);
            tabla.getColumnModel().getColumn(5).setMinWidth(150);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(150);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(150);
                           

            
            modelo.setNumRows(0);
            while(r.next()){
                String codigoCliente=r.getString(3);
                                
                modelo.addRow( new Object [] {null,null,null,null,null,null});
                tabla.setValueAt(r.getString(1),i,0);

                Statement consulta2=conn.createStatement();
                ResultSet r2= consulta2.executeQuery("select * from proveedor order by cod_proveedor");
                String nombreProveedor="N° "+codigoCliente,apellidoCliente="", cuitProveedor="";
                while(r2.next()){
                    if((r2.getString("cod_proveedor")).equals(r.getString("cod_proveedor"))){
                        nombreProveedor=r2.getString(2);
                        cuitProveedor=r2.getString(8);
                    }
                }
                tabla.setValueAt(r.getString(7),i,1);
                tabla.setValueAt(r.getString(2),i,2);
                tabla.setValueAt(nombreProveedor,i,3);  
                tabla.setValueAt(cuitProveedor,i,4);
                tabla.setValueAt(r.getString(9),i,5);
                i++;
                if(i==5)break;//version demo
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ;
            System.out.println(e);
        }
    }
    
    
    public void iniciar(){
        panelRemito.setVisible(false);
        checkNombreCliente.setSelected(true);
        txtBuscar.setText("Ingrese su búsqueda");
        txtBuscar.setForeground(Color.gray);

        txtCodigoEmpleado1.setVisible(false);
        labelCodigoCliente.setVisible(false);
        labelCodigoEmpleado.setVisible(false);
        txtNombreApellidoEmpleado.setVisible(false);
        txtNombreApellidoProveedor.setVisible(true);
        txtTotalFactura.setVisible(false);
        
        txtNumeroRemito.setEditable(false);
        txtFechaRemito.setEditable(false);
        txtTotalFactura2.setEditable(false);
    }
    
    public void MostrarRemito(){  
        TableColumn  column = null;

        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            Statement consulta2=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta.executeQuery("select * from referencia_compra where cod_compra="+txtNumeroRemito.getText());
            ResultSet r2= consulta2.executeQuery("select ivaDiscriminado, total_con_iva from compra where cod_compra="+txtNumeroRemito.getText());
            int i,j;
            i=0;
            j=0;
            tabla2.getColumnModel().getColumn(0).setMaxWidth(120);
            tabla2.getColumnModel().getColumn(0).setMinWidth(120);
            tabla2.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(120);
            tabla2.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(120);
            
            tabla2.getColumnModel().getColumn(1).setMaxWidth(110);
            tabla2.getColumnModel().getColumn(1).setMinWidth(110);
            tabla2.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(110);
            tabla2.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(110);
            
            tabla2.getColumnModel().getColumn(2).setMaxWidth(450);
            tabla2.getColumnModel().getColumn(2).setMinWidth(450);
            tabla2.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(450);
            tabla2.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(450);
            
            tabla2.getColumnModel().getColumn(4).setMaxWidth(110);
            tabla2.getColumnModel().getColumn(4).setMinWidth(110);
            tabla2.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(110);
            tabla2.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(110);
            
            DefaultTableModel modelo=(DefaultTableModel)tabla2.getModel();
            tabla2.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            String total="";
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null,null,null});
                tabla2.setValueAt(r.getString(4),i,0);//cantidad
                tabla2.setValueAt(r.getString(2),i,1);//codigo articulo
                tabla2.setValueAt(r.getString(3),i,2);//referencia
                tabla2.setValueAt(r.getString(7),i,3);//valor unitario
                tabla2.setValueAt(r.getString(10),i,4);//descuento
                tabla2.setValueAt(r.getString(8),i,5);//total     
                i++;
            }
            while(r2.next()){
                txtIva.setText(r2.getString(1));
                txtTotalFactura.setText(r2.getString(2));
                txtTotalFactura2.setText(signo_moneda+" "+r2.getString(2));
            }
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
        }
    }
      


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        checkRemito = new javax.swing.JRadioButton();
        checkNombreCliente = new javax.swing.JRadioButton();
        checkFecha = new javax.swing.JRadioButton();
        panelRemito = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        txtNumeroRemito = new javax.swing.JTextField();
        txtFechaRemito = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombreApellidoProveedor = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTipoFactura = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCuitProveedor = new javax.swing.JTextField();
        txtNombreApellidoEmpleado = new javax.swing.JTextField();
        labelCodigoCliente = new javax.swing.JLabel();
        labelCodigoEmpleado = new javax.swing.JLabel();
        txtCodigoEmpleado1 = new javax.swing.JTextField();
        txtCodigoProveedor = new javax.swing.JTextField();
        txtBanderaRemito = new javax.swing.JTextField();
        txtTotalFactura = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTotalFactura2 = new javax.swing.JTextField();
        btnFacturarRemito = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtFacturaNumero1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtRecibe = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "N° COMPRA", "TIPO", "FECHA", "PROVEEDOR", "cuit proveedor", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setRowHeight(25);
        tabla.setRowMargin(4);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 970, 240));

        txtBuscar.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtBuscar.setBorder(null);
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFocusLost(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 240, 40));

        buttonGroup1.add(checkRemito);
        checkRemito.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkRemito.setText("N° Fact");
        checkRemito.setOpaque(false);
        checkRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkRemitoActionPerformed(evt);
            }
        });
        jPanel1.add(checkRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, -1, 30));

        buttonGroup1.add(checkNombreCliente);
        checkNombreCliente.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkNombreCliente.setText("Cliente");
        checkNombreCliente.setOpaque(false);
        checkNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkNombreClienteActionPerformed(evt);
            }
        });
        jPanel1.add(checkNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, -1, 30));

        buttonGroup1.add(checkFecha);
        checkFecha.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkFecha.setText("Fecha");
        checkFecha.setOpaque(false);
        checkFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkFechaActionPerformed(evt);
            }
        });
        jPanel1.add(checkFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, -1, 30));

        panelRemito.setBackground(new java.awt.Color(255, 255, 255));
        panelRemito.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla2.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Cantidad", "Codigo", "Descripcion", "Valor unitario", "Descuento", "Valor total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla2.setRowHeight(25);
        tabla2.setRowMargin(4);
        jScrollPane2.setViewportView(tabla2);

        panelRemito.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 970, 220));

        txtNumeroRemito.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtNumeroRemito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumeroRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroRemitoActionPerformed(evt);
            }
        });
        panelRemito.add(txtNumeroRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 80, 42));

        txtFechaRemito.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtFechaRemito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaRemitoActionPerformed(evt);
            }
        });
        panelRemito.add(txtFechaRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 110, 42));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel3.setText("N° Fact");
        panelRemito.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, 40));

        txtNombreApellidoProveedor.setFont(new java.awt.Font("Calibri", 0, 17)); // NOI18N
        txtNombreApellidoProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreApellidoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreApellidoProveedorActionPerformed(evt);
            }
        });
        panelRemito.add(txtNombreApellidoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 220, 40));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel9.setText("Tipo");
        panelRemito.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 60, 40));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel10.setText("Cuit");
        panelRemito.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 50, 40));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel11.setText("Fecha");
        panelRemito.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, 40));

        txtTipoFactura.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtTipoFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTipoFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoFacturaActionPerformed(evt);
            }
        });
        panelRemito.add(txtTipoFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 50, 40));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel12.setText("Proveedor");
        panelRemito.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, 40));

        txtCuitProveedor.setFont(new java.awt.Font("Calibri", 0, 17)); // NOI18N
        txtCuitProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuitProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuitProveedorActionPerformed(evt);
            }
        });
        panelRemito.add(txtCuitProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, 130, 40));
        panelRemito.add(txtNombreApellidoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 30, 30));

        labelCodigoCliente.setText("COD_PROVEED");
        panelRemito.add(labelCodigoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        labelCodigoEmpleado.setText("COD_EMPLEADO");
        panelRemito.add(labelCodigoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, -1, -1));

        txtCodigoEmpleado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoEmpleado1ActionPerformed(evt);
            }
        });
        panelRemito.add(txtCodigoEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 40, 20));

        txtCodigoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoProveedorActionPerformed(evt);
            }
        });
        panelRemito.add(txtCodigoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 30, 20));
        panelRemito.add(txtBanderaRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 30, 30));

        txtTotalFactura.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        panelRemito.add(txtTotalFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 20, 20));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel2.setText("% IVA Disc");
        panelRemito.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 120, 42));

        txtIva.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panelRemito.add(txtIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, 150, 42));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel5.setText("TOTAL");
        panelRemito.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 290, 80, 42));

        txtTotalFactura2.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtTotalFactura2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panelRemito.add(txtTotalFactura2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, 150, 42));

        btnFacturarRemito.setBackground(new java.awt.Color(5, 52, 99));
        btnFacturarRemito.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnFacturarRemito.setForeground(new java.awt.Color(255, 255, 255));
        btnFacturarRemito.setText("MOSTRAR COMPRA");
        btnFacturarRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarRemitoActionPerformed(evt);
            }
        });
        panelRemito.add(btnFacturarRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 290, 240, 40));

        jPanel1.add(panelRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 358, -1, 340));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoRemitosXs-01.png"))); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 50));

        txtFacturaNumero1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        txtFacturaNumero1.setText("COMPRAS");
        jPanel1.add(txtFacturaNumero1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 70, 260, 10));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, -1, 80));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel4.setText("Buscar por");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, 30));
        jPanel1.add(txtRecibe, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 100, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 730));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    DefaultTableModel dm;
    /* Método filtro*/
    private void filtro2(String consulta, JTable jtableBuscar){
        dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter(dm);
        jtableBuscar.setRowSorter(tr);

        int columna=0;
        // Identificamos cual es el JRadioButton seleccionado para filtrar el
        // resultado de acuerdo a los datos de la columna elegida
        if (checkRemito.isSelected()) {
            columna = 0;
        }else if (checkNombreCliente.isSelected()) {
                    columna = 3;
              }else if (checkFecha.isSelected()) {
                        columna = 2;
                    }
        tr.setRowFilter(RowFilter.regexFilter(consulta, columna));
    }
    
    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if (buttonGroup1.getSelection()==null) {
            // Si ninguno de los JRadioButtons está seleccionado, evitamos que se
            // escriba algo dentro del JTextField y mostramos un mensaje de error
            evt.consume();
            JOptionPane.showMessageDialog(this, "Debe seleccionar una opción para filtrar los resultados", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            txtBuscar.setText("");
            txtBuscar.transferFocus();
        }else{
            filtro2(txtBuscar.getText().toUpperCase(), tabla);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void checkRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkRemitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkRemitoActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        String codigoProveedor="";
        txtBanderaRemito.setText("0");
        panelRemito.setVisible(true);
        int fila= tabla.getSelectedRow();

        if(fila>=0){
            Select=tabla.getSelectedRow();
            txtNumeroRemito.setText(tabla.getValueAt(Select,0).toString());
            txtTipoFactura.setText(tabla.getValueAt(Select,1).toString());
            txtFechaRemito.setText(tabla.getValueAt(Select,2).toString());
            txtNombreApellidoProveedor.setText(tabla.getValueAt(Select,3).toString());
            txtCuitProveedor.setText(tabla.getValueAt(Select,4).toString());
            try{
                Connection miconexion2 = conexion.ObtenerConexion();
                Statement consulta2=miconexion2.createStatement();
                ResultSet r2= consulta2.executeQuery("select cod_proveedor from proveedor where nombre_firma='"+tabla.getValueAt(Select,3).toString()+"' and cuit='"+tabla.getValueAt(Select,4).toString()+"'");
                
                 while(r2.next()){
                     codigoProveedor=r2.getString(1);
                }
                txtCodigoProveedor.setText(codigoProveedor);
            }catch(Exception e){
                
            }
            MostrarRemito();      
        }else{
            JOptionPane.showMessageDialog(null,"No selecciono ninguna fila");
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void txtNumeroRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroRemitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroRemitoActionPerformed

    private void txtCodigoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoProveedorActionPerformed

    private void txtFechaRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaRemitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaRemitoActionPerformed

    private void txtCodigoEmpleado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoEmpleado1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoEmpleado1ActionPerformed

    private void txtNombreApellidoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreApellidoProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreApellidoProveedorActionPerformed

    private void checkFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkFechaActionPerformed

    private void checkNombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkNombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkNombreClienteActionPerformed

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
        if(txtBuscar.getText().equals("Ingrese su búsqueda")){
            txtBuscar.setText("");
        }
        txtBuscar.setForeground(Color.black);
    }//GEN-LAST:event_txtBuscarFocusGained

    private void txtBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusLost
        if(txtBuscar.getText().equals("")){
            txtBuscar.setText("Ingrese su búsqueda");
        }
        txtBuscar.setForeground(Color.gray);
    }//GEN-LAST:event_txtBuscarFocusLost

    private void btnFacturarRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarRemitoActionPerformed
       String contribuyente="",direccion="";
       String tipo=txtTipoFactura.getText();
       
       if (tipo.equals("A")){
            tipo="FACTURA A";
       }else if (tipo.equals("B")){
                tipo="FACTURA B";
            }
       Connection miconexion = conexion.ObtenerConexion();
       if(txtRecibe.getText().equals("1")){
            Map parametros = new HashMap();
            Select=tabla.getSelectedRow();
            String codigoFactura= txtNumeroRemito.getText(); 
            parametros.put("codf",codigoFactura);
            try{     
                String reporte="compra.jasper";
                JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                JasperViewer ventanavisor=new JasperViewer(informe,false);
                ventanavisor.setTitle("Reporte de Compra");
                ventanavisor.setVisible(true); 
                //this.setVisible(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } 
       }else if(txtRecibe.getText().equals("2")){
           
           
            String porcIva="";
            if(txtIva.getText().equals("19.00")){
                porcIva="19%";
            }if(txtIva.getText().equals("0.00")){
                        porcIva="0%";
                    }
            try{     
                Connection miconexion2 = conexion.ObtenerConexion();
                Statement consulta2=miconexion2.createStatement();
                ResultSet r2= consulta2.executeQuery("select * from proveedor where cod_proveedor='"+txtCodigoProveedor.getText()+"'");
                
                 while(r2.next()){
                     contribuyente=r2.getString(13);
                     direccion=r2.getString(4)+", "+r2.getString(5);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            
            Factura_Nota_Credito_Compra.txtNroFactura.setText(txtNumeroRemito.getText());
            Factura_Nota_Credito_Compra.txtProveedor.setText(txtNombreApellidoProveedor.getText());
            Factura_Nota_Credito_Compra.txtCodigoProveedor.setText(txtCodigoProveedor.getText());
            Factura_Nota_Credito_Compra.txtCuit.setText(txtCuitProveedor.getText());
            Factura_Nota_Credito_Compra.comboContribuyente.setSelectedItem(contribuyente);
            Factura_Nota_Credito_Compra.txtDomicilio.setText(direccion);
            Factura_Nota_Credito_Compra.comboFactura.setSelectedItem(tipo);
            Factura_Nota_Credito_Compra.comboIva.setSelectedItem(porcIva);
            Factura_Nota_Credito_Compra.txtNombreArticulo.setText("");
            Factura_Nota_Credito_Compra.txtPrecioArticulo.setText("");
            this.dispose();
       }
           
    }//GEN-LAST:event_btnFacturarRemitoActionPerformed

    private void txtTipoFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoFacturaActionPerformed

    private void txtCuitProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuitProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuitProveedorActionPerformed

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
            java.util.logging.Logger.getLogger(mostrar_compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mostrar_compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mostrar_compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mostrar_compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new mostrar_compras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFacturarRemito;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton checkFecha;
    private javax.swing.JRadioButton checkNombreCliente;
    private javax.swing.JRadioButton checkRemito;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelCodigoCliente;
    private javax.swing.JLabel labelCodigoEmpleado;
    private javax.swing.JPanel panelRemito;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla2;
    private javax.swing.JTextField txtBanderaRemito;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigoEmpleado1;
    private javax.swing.JTextField txtCodigoProveedor;
    private javax.swing.JTextField txtCuitProveedor;
    private javax.swing.JLabel txtFacturaNumero1;
    private javax.swing.JTextField txtFechaRemito;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtNombreApellidoEmpleado;
    private javax.swing.JTextField txtNombreApellidoProveedor;
    private javax.swing.JTextField txtNumeroRemito;
    public static javax.swing.JTextField txtRecibe;
    private javax.swing.JTextField txtTipoFactura;
    private javax.swing.JTextField txtTotalFactura;
    private javax.swing.JTextField txtTotalFactura2;
    // End of variables declaration//GEN-END:variables
}
