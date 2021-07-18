
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


public class mostrar_facturas extends javax.swing.JFrame {
    int Select;
    String codigo,nombre,apellido,fecha,empleado;
    boolean banderaFacturado=false;
    String NOMBRE,signo_moneda;

    public mostrar_facturas() {
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
        txtCodigoCliente1.setVisible(false);
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

            ResultSet r= consulta.executeQuery("select * from factura where condicion='ACTIVA' order by cod_factura");
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
            
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
                           
            tabla.getColumnModel().getColumn(6).setMaxWidth(0);
            tabla.getColumnModel().getColumn(6).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
            
            tabla.getColumnModel().getColumn(7).setMaxWidth(0);
            tabla.getColumnModel().getColumn(7).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
            
            tabla.getColumnModel().getColumn(8).setMaxWidth(120);
            tabla.getColumnModel().getColumn(8).setMinWidth(120);
            tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(120);
            tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(120);
            
            tabla.getColumnModel().getColumn(9).setMaxWidth(150);
            tabla.getColumnModel().getColumn(9).setMinWidth(150);
            tabla.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(150);
            tabla.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(150);
            
            modelo.setNumRows(0);
            while(r.next()){
                String codigoCliente=r.getString(3),
                codigoEmpleado=r.getString(4);
                                
                modelo.addRow( new Object [] {null,null,null,null,null,null,null,null});
                tabla.setValueAt(r.getString(1),i,0);

                Statement consulta2=conn.createStatement();
                ResultSet r2= consulta2.executeQuery("select * from cliente order by cod_cliente");
                String nombreCliente="N° "+codigoCliente,apellidoCliente="", cuitCliente="";
                while(r2.next()){
                    if((r2.getString("cod_cliente")).equals(r.getString("cod_cliente"))){
                        nombreCliente=r2.getString(2);
                        apellidoCliente=r2.getString(3);     
                        cuitCliente=r2.getString(8);
                    }
                }
                tabla.setValueAt(r.getString(8),i,1);
                tabla.setValueAt(r.getString(2),i,2);
                tabla.setValueAt(nombreCliente,i,3);
                

                Statement consulta3=conn.createStatement();
                ResultSet r3= consulta3.executeQuery("select * from empleado order by cod_empleado");
                String nombreEmpleado="N° "+codigoEmpleado,apellidoEmpleado="";
                while(r3.next()){
                    if(r3.getString(1).equals(codigoEmpleado)){
                        nombreEmpleado=r3.getString(2);
                        apellidoEmpleado=r3.getString(3);         
                    }
                }
                tabla.setValueAt(nombreEmpleado+" "+apellidoEmpleado,i,4);
                tabla.setValueAt(codigoCliente,i,5);
                tabla.setValueAt(codigoEmpleado,i,6);
                tabla.setValueAt(cuitCliente,i,7);
                tabla.setValueAt(r.getString(9),i,8);
                tabla.setValueAt(r.getString(12),i,9);
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
        txtNombreApellidoCliente.setVisible(true);
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

            ResultSet r= consulta.executeQuery("select * from referencia_factura where cod_factura="+txtNumeroRemito.getText());
            ResultSet r2= consulta2.executeQuery("select iva, total_con_iva from factura where cod_factura="+txtNumeroRemito.getText());
            int i,j;
            i=0;
            j=0;
            tabla2.getColumnModel().getColumn(0).setMaxWidth(90);
            tabla2.getColumnModel().getColumn(0).setMinWidth(90);
            tabla2.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(90);
            tabla2.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(90);
            
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
                tabla2.setValueAt(r.getString(6),i,0);//cantidad
                tabla2.setValueAt(r.getString(2),i,1);//codigo articulo
                tabla2.setValueAt(r.getString(5),i,2);//referencia
                tabla2.setValueAt(r.getString(3),i,3);//valor unitario
                tabla2.setValueAt(r.getString(10),i,4);//descuento
                tabla2.setValueAt(r.getString(4),i,5);//total     
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
        txtCodigoCliente1 = new javax.swing.JTextField();
        txtFechaRemito = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelCodigoCliente = new javax.swing.JLabel();
        txtNombreApellidoCliente = new javax.swing.JTextField();
        txtTotalFactura = new javax.swing.JTextField();
        txtNombreApellidoEmpleado = new javax.swing.JTextField();
        txtTotalFactura2 = new javax.swing.JTextField();
        btnFacturarRemito = new javax.swing.JButton();
        txtBanderaRemito = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTipoFactura = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCuitCliente = new javax.swing.JTextField();
        txtCodigoEmpleado1 = new javax.swing.JTextField();
        labelCodigoEmpleado = new javax.swing.JLabel();
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

        tabla.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N° FACTURA", "TIPO", "FECHA", "CLIENTE", "VENDEDOR", "cod cliente", "cod empleado", "cuit cliente", "TOTAL", "CONDICION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, true, true, true
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
        jPanel1.add(checkRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, -1, -1));

        buttonGroup1.add(checkNombreCliente);
        checkNombreCliente.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkNombreCliente.setText("Cliente");
        checkNombreCliente.setOpaque(false);
        checkNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkNombreClienteActionPerformed(evt);
            }
        });
        jPanel1.add(checkNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, -1, -1));

        buttonGroup1.add(checkFecha);
        checkFecha.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkFecha.setText("Fecha");
        checkFecha.setOpaque(false);
        checkFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkFechaActionPerformed(evt);
            }
        });
        jPanel1.add(checkFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, -1, -1));

        panelRemito.setBackground(new java.awt.Color(255, 255, 255));
        panelRemito.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
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
        panelRemito.add(txtNumeroRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 80, 42));

        txtCodigoCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoCliente1ActionPerformed(evt);
            }
        });
        panelRemito.add(txtCodigoCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 40, 20));

        txtFechaRemito.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtFechaRemito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaRemitoActionPerformed(evt);
            }
        });
        panelRemito.add(txtFechaRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 120, 42));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel2.setText("% IVA Disc");
        panelRemito.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 120, 42));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel3.setText("N° Fact");
        panelRemito.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, 40));

        labelCodigoCliente.setText("COD_CLIENTE");
        panelRemito.add(labelCodigoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        txtNombreApellidoCliente.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        txtNombreApellidoCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreApellidoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreApellidoClienteActionPerformed(evt);
            }
        });
        panelRemito.add(txtNombreApellidoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 220, 40));

        txtTotalFactura.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        panelRemito.add(txtTotalFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 20, -1));
        panelRemito.add(txtNombreApellidoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 30, 30));

        txtTotalFactura2.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtTotalFactura2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panelRemito.add(txtTotalFactura2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, 150, 42));

        btnFacturarRemito.setBackground(new java.awt.Color(5, 52, 99));
        btnFacturarRemito.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnFacturarRemito.setForeground(new java.awt.Color(255, 255, 255));
        btnFacturarRemito.setText("MOSTRAR FACTURA");
        btnFacturarRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarRemitoActionPerformed(evt);
            }
        });
        panelRemito.add(btnFacturarRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 290, 240, 40));
        panelRemito.add(txtBanderaRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 30, 50));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel9.setText("Tipo");
        panelRemito.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 60, 40));

        txtIva.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panelRemito.add(txtIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, 150, 42));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel5.setText("TOTAL");
        panelRemito.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 290, 80, 42));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel10.setText("Cuit");
        panelRemito.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, -1, 40));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel11.setText("Fecha");
        panelRemito.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 70, 40));

        txtTipoFactura.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtTipoFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTipoFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoFacturaActionPerformed(evt);
            }
        });
        panelRemito.add(txtTipoFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 50, 40));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel12.setText("Cliente");
        panelRemito.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, 40));

        txtCuitCliente.setFont(new java.awt.Font("Calibri", 0, 17)); // NOI18N
        txtCuitCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuitCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuitClienteActionPerformed(evt);
            }
        });
        panelRemito.add(txtCuitCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, 140, 40));

        txtCodigoEmpleado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoEmpleado1ActionPerformed(evt);
            }
        });
        panelRemito.add(txtCodigoEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 40, 20));

        labelCodigoEmpleado.setText("COD_EMPLEADO");
        panelRemito.add(labelCodigoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, -1, -1));

        jPanel1.add(panelRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, 340));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoRemitosXs-01.png"))); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 50));

        txtFacturaNumero1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        txtFacturaNumero1.setText("FACTURAS");
        jPanel1.add(txtFacturaNumero1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 70, 260, 10));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, -1, 80));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel4.setText("Buscar por");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));
        jPanel1.add(txtRecibe, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 80, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 700));

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

        txtBanderaRemito.setText("0");
        panelRemito.setVisible(true);
        int fila= tabla.getSelectedRow();

        if(fila>=0){
            Select=tabla.getSelectedRow();
            txtNumeroRemito.setText(tabla.getValueAt(Select,0).toString());
            txtTipoFactura.setText(tabla.getValueAt(Select,1).toString());
            txtFechaRemito.setText(tabla.getValueAt(Select,2).toString());
            txtCodigoCliente1.setText(tabla.getValueAt(Select,5).toString());
            txtCodigoEmpleado1.setText(tabla.getValueAt(Select,6).toString());
            txtNombreApellidoEmpleado.setText(tabla.getValueAt(Select,4).toString());
            txtNombreApellidoCliente.setText(tabla.getValueAt(Select,3).toString());
            txtCuitCliente.setText(tabla.getValueAt(Select,7).toString());
            MostrarRemito();      
        }else{
            JOptionPane.showMessageDialog(null,"No selecciono ninguna fila");
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void txtNumeroRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroRemitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroRemitoActionPerformed

    private void txtCodigoCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoCliente1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoCliente1ActionPerformed

    private void txtFechaRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaRemitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaRemitoActionPerformed

    private void txtCodigoEmpleado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoEmpleado1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoEmpleado1ActionPerformed

    private void txtNombreApellidoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreApellidoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreApellidoClienteActionPerformed

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
       Connection miconexion = conexion.ObtenerConexion();
       if(txtRecibe.getText().equals("1")){
        Map parametros = new HashMap();
        Select=tabla.getSelectedRow();
        String codigoFactura= txtNumeroRemito.getText(); 
        parametros.put("codf",codigoFactura);
        try{     
            String reporte="factura.jasper";
            JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
            JasperViewer ventanavisor=new JasperViewer(informe,false);
            ventanavisor.setTitle("Reporte de Factura");
            ventanavisor.setVisible(true); 
            //this.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } 
           
       }else if(txtRecibe.getText().equals("2")){
           String tipo=txtTipoFactura.getText();
           if (tipo.equals("A")){
                tipo="FACTURA A";
           }else if (tipo.equals("B")){
                    tipo="FACTURA B";
                }
            String porcIva="";
            if(txtIva.getText().equals("19.00")){
                porcIva="19%";
            }else if(txtIva.getText().equals("0.00")){
                        porcIva="0%";
                  }
            try{     
                Connection miconexion2 = conexion.ObtenerConexion();
                Statement consulta2=miconexion2.createStatement();
                ResultSet r2= consulta2.executeQuery("select * from cliente where cod_cliente="+txtCodigoCliente1.getText());
                
                 while(r2.next()){
                     contribuyente=r2.getString(9);
                     direccion=r2.getString(4)+", "+r2.getString(3);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            
            Factura_Nota_Credito_Venta.txtNroFactura.setText(txtNumeroRemito.getText());
            Factura_Nota_Credito_Venta.comboCliente.setSelectedItem(txtNombreApellidoCliente.getText());
            Factura_Nota_Credito_Venta.txtCliente.setText(txtNombreApellidoCliente.getText());
            Factura_Nota_Credito_Venta.txtCuit.setText(txtCuitCliente.getText());
            Factura_Nota_Credito_Venta.comboContribuyente.setSelectedItem(contribuyente);
            Factura_Nota_Credito_Venta.txtDomicilio.setText(direccion);
            Factura_Nota_Credito_Venta.comboFactura.setSelectedItem(tipo);
            Factura_Nota_Credito_Venta.comboIva.setSelectedItem(porcIva);
            Factura_Nota_Credito_Venta.txtNombreArticulo.setText("");
            Factura_Nota_Credito_Venta.txtPrecioArticulo.setText("");
            this.dispose();
           
       }
         
    }//GEN-LAST:event_btnFacturarRemitoActionPerformed

    private void txtTipoFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoFacturaActionPerformed

    private void txtCuitClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuitClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuitClienteActionPerformed

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
            java.util.logging.Logger.getLogger(mostrar_facturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mostrar_facturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mostrar_facturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mostrar_facturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mostrar_facturas().setVisible(true);
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
    private javax.swing.JTextField txtCodigoCliente1;
    private javax.swing.JTextField txtCodigoEmpleado1;
    private javax.swing.JTextField txtCuitCliente;
    private javax.swing.JLabel txtFacturaNumero1;
    private javax.swing.JTextField txtFechaRemito;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtNombreApellidoCliente;
    private javax.swing.JTextField txtNombreApellidoEmpleado;
    private javax.swing.JTextField txtNumeroRemito;
    public static javax.swing.JTextField txtRecibe;
    private javax.swing.JTextField txtTipoFactura;
    private javax.swing.JTextField txtTotalFactura;
    private javax.swing.JTextField txtTotalFactura2;
    // End of variables declaration//GEN-END:variables
}
