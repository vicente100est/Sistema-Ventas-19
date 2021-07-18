
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

public class Editar_Articulo extends javax.swing.JFrame {
    
    String codigo,referencia,cantidad,marca,valor;
    String NOMBRE;

    public Editar_Articulo() {
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
        //cargarComboProveedores();

    }

    public void habilitara(){
       txtStock.setText("");    
       txtPrecioBruto.setText("");
       txtPrecioNeto.setText("");
       txtProducto.setText("");
       txtCodigo.setText("");
       txtCodigo.requestFocus();
    }
    
     public static void cargarComboProveedores(){
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
        comboIva.addItem("21%");
        comboIva.addItem("10,5%");
        comboIva.addItem("27%");
        comboIva.addItem("0%");
        
        comboIvaCompra.addItem("21%");
        comboIvaCompra.addItem("10,5%");
        comboIvaCompra.addItem("27%");
        comboIvaCompra.addItem("0%");
        
        comboCategoria.removeAllItems();
        comboCategoria.addItem("Articulos");
        comboCategoria.addItem("Materia prima");
        comboCategoria.addItem("insumos");
        comboCategoria.addItem("Otros");
            
        
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
    public static void CargarArticulo(String codigo){
        String codigoArticulo=codigo;
        try{
            Connection conn =conexion.ObtenerConexion();
            Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
            Statement consulta2=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet r= consulta1.executeQuery("select * from articulo where cod_articulo='"+codigoArticulo+"'");

            while(r.next()){
                comboCategoria.setSelectedItem(r.getString(15));
                txtCodigo.setText(r.getString(1));
                txtCodigoProvisorio.setText(r.getString(10));
                txtProducto.setText(r.getString(2));
                txtMarca.setText(r.getString(8));
                
                ResultSet r2= consulta2.executeQuery("select * from proveedor where cod_proveedor='"+r.getString(4)+"'");
                String nombreProveedor="";
                while(r2.next()){
                    nombreProveedor=r2.getString(2);
                }
                comboProveedor.setSelectedItem(nombreProveedor);
               txtStock.setText(r.getString(3));
               txtBrutoSinIva.setText(r.getString(16));
               
               String ivaCompra="";
               if(r.getString(17).equals("0.00")){
                   ivaCompra="0%";
               }else if (r.getString(17).equals("21.00")){
                        ivaCompra="21%";
                     }else if (r.getString(17).equals("27.00")){
                                ivaCompra="27%";
                            }else if (r.getString(17).equals("10.05")){
                                        ivaCompra="10,5%";
                                    }
               comboIvaCompra.setSelectedItem(ivaCompra);
               
               txtPrecioBruto.setText(r.getString(6));
               txtPorcentaje.setText(r.getString(13));
               txtPrecioNeto.setText(r.getString(5));
               txtPrecioNetoIva.setText(r.getString(12));
               
               String ivaVenta="";
               if(r.getString(14).equals("0.00")){
                   ivaVenta="0%";
               }else if (r.getString(14).equals("21.00")){
                        ivaVenta="21%";
                     }else if (r.getString(14).equals("27.00")){
                                ivaVenta="27%";
                            }else if (r.getString(14).equals("10.05")){
                                        ivaVenta="10,5%";
                                    }
               comboIva.setSelectedItem(ivaVenta);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    } 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        recibeCliente = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtPrecioBruto = new javax.swing.JTextField();
        txtPrecioNeto = new javax.swing.JTextField();
        txtPorcentaje = new javax.swing.JTextField();
        porcentaje = new javax.swing.JRadioButton();
        txtCodigo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCodigoProvisorio = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        comboProveedor = new javax.swing.JComboBox();
        txtPrecioNetoIva = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        comboIva = new javax.swing.JComboBox();
        comboCategoria = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        txtBrutoSinIva = new javax.swing.JTextField();
        comboIvaCompra = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
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
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 110, -1));

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
        jPanel1.add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 290, 40));

        jLabel5.setBackground(new java.awt.Color(239, 255, 239));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Stock");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 60, -1));

        txtStock.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtStock.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStockKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockKeyTyped(evt);
            }
        });
        jPanel1.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 290, 40));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Costo sin IVA");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 130, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("  Neto Con IVA");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 580, 130, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesXs-01.png"))); // NOI18N
        jLabel1.setText("Editar Articulo");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));
        jPanel1.add(recibeCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 60, 40));

        jButton1.setBackground(new java.awt.Color(5, 52, 99));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 640, 140, -1));

        jButton2.setBackground(new java.awt.Color(5, 52, 99));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton2.setText("Actualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 640, 140, -1));

        txtPrecioBruto.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtPrecioBruto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioBruto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioBrutoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioBrutoKeyTyped(evt);
            }
        });
        jPanel1.add(txtPrecioBruto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 470, 180, 40));

        txtPrecioNeto.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtPrecioNeto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioNeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioNetoActionPerformed(evt);
            }
        });
        txtPrecioNeto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioNetoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioNetoKeyTyped(evt);
            }
        });
        jPanel1.add(txtPrecioNeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 520, 290, 40));

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
        jPanel1.add(txtPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 470, 100, 40));

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
        jPanel1.add(porcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 460, -1, 50));

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
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 290, 40));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Proveedor");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 100, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("*");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 480, 20, 30));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("*");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 20, 30));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("*");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, 20, 30));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("*");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, 20, 30));

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
        jPanel1.add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 290, 40));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Descripción");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 110, -1));

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
        jPanel1.add(txtCodigoProvisorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 290, 40));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Categoria");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 100, 40));

        comboProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(comboProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 290, 40));

        txtPrecioNetoIva.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtPrecioNetoIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioNetoIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioNetoIvaActionPerformed(evt);
            }
        });
        txtPrecioNetoIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioNetoIvaKeyTyped(evt);
            }
        });
        jPanel1.add(txtPrecioNetoIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 570, 180, 40));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("  Neto Sin IVA");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 130, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Marca");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 60, -1));

        comboIva.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        comboIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIvaActionPerformed(evt);
            }
        });
        jPanel1.add(comboIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 570, 100, 40));

        comboCategoria.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        comboCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(comboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 290, 40));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Código");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 70, -1));

        txtBrutoSinIva.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtBrutoSinIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBrutoSinIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBrutoSinIvaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBrutoSinIvaKeyTyped(evt);
            }
        });
        jPanel1.add(txtBrutoSinIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 420, 180, 40));

        comboIvaCompra.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        comboIvaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIvaCompraActionPerformed(evt);
            }
        });
        jPanel1.add(comboIvaCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 420, 100, 40));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Costo con IVA");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, 130, -1));

        jMenu1.setText("Opciones");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductoKeyTyped

    private void txtStockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyReleased
        txtStock.setText (txtStock.getText().toUpperCase());  
    }//GEN-LAST:event_txtStockKeyReleased

    private void txtStockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyTyped
       char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
        evt.consume(); // TODO add your handling code here:
    }//GEN-LAST:event_txtStockKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Double ivaCombo2=0.00, brutoSinIva=0.00;
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
        if(ivaCombo.equals("21%")){
            ivaCombo="21.00";
            ivaCombo2=21.00;
        }else
            if(ivaCombo.equals("10,5%")){
                ivaCombo="10.50";
                ivaCombo2=10.50;
            }else
                if(ivaCombo.equals("27%")){
                    ivaCombo="27.00";
                    ivaCombo2=27.00;
                }else
                    if(ivaCombo.equals("0%")){
                        ivaCombo="0.00";
                        ivaCombo2=0.00;
                    }
        
        String ivaCompra=comboIvaCompra.getSelectedItem().toString();
        if(ivaCompra.equals("21%")){
            ivaCompra="21.00";
        }else
            if(ivaCompra.equals("10,5%")){
                ivaCompra="10.50";
            }else
                if(ivaCompra.equals("27%")){
                    ivaCompra="27.00";
                }else
                    if(ivaCompra.equals("0%")){
                        ivaCompra="0.00";
                    }
        
            total_con_iva=txtPrecioNetoIva.getText().replace(",",".");

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
        txtStock.setBackground(new Color(255,255,255));
        txtPrecioBruto.setBackground(new Color(255,255,255));
        
       /* if (bandera==1) {
            JOptionPane.showMessageDialog(null, "Ya existe un articulo con codigo "+txtCodigo.getText(),"Advertencia",JOptionPane.WARNING_MESSAGE);
            txtCodigo.setBackground(Color.yellow);
            txtCodigo.requestFocus();
        }else{*/
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
                if (txtStock.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar el Stock del articulo","Advertencia",JOptionPane.WARNING_MESSAGE);
                    txtStock.setBackground(Color.yellow);
                    txtStock.requestFocus();
                }else{
                    if (txtPrecioBruto.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Debe inresar el precio de COSTO del articulo","Advertencia",JOptionPane.WARNING_MESSAGE);
                        txtPrecioBruto.setBackground(Color.yellow);
                        txtPrecioBruto.requestFocus();
                    }else{
                        Double bruto=0.0,neto=0.0;
                                String valorBruto="",valorNeto="";
                                try{
                                    bruto=(double)Integer.parseInt(txtPrecioBruto.getText().replace(",", "."));
                                    neto=(double)Integer.parseInt(txtPrecioNeto.getText().replace(",", "."));
                                }catch (NumberFormatException e){
                                    bruto=Double.parseDouble(txtPrecioBruto.getText().replace(",", "."));
                                    neto=Double.parseDouble(txtPrecioNeto.getText().replace(",", "."));
                                }
                                
                                if(!txtBrutoSinIva.getText().equals("")){
                                    try{                                
                                        brutoSinIva=(double)Integer.parseInt(txtBrutoSinIva.getText().replace(",", "."));
                                    }catch (NumberFormatException e){
                                        brutoSinIva=Double.parseDouble(txtBrutoSinIva.getText().replace(",", "."));
                                    }

                                }

                                valorBruto=(String.format("%.2f", bruto));
                                valorNeto=(String.format("%.2f", neto));
                                valorBruto=valorBruto.replace(",", ".");
                                valorNeto=valorNeto.replace(",", ".");
                                //GUARDO EL CODIGO DEL PROVEEDOR PARA QUE CUANDO CAMBIE EL NOMBRE DEL PROVEEDOR CAMBIE EN EL PRODUCTO
                                String proveedor=comboProveedor.getSelectedItem().toString();
                                if(proveedor.equals("SELECCIONE PROVEEDOR")){
                                    proveedor="SIN PROVEEDOR";
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
                            
                           // consulta.executeUpdate("insert into articulo (cod_articulo,referencia,cantidad,proveedor,valor,valor_bruto,estado,marca,ultima_actualizacion,codigo_provisorio,total_con_iva,porcentaje_ganancia,iva,categoria,valor_bruto_sin_iva,ivaCompra)  values('"+txtCodigo.getText()+"','"+txtProducto.getText()+"','"+txtStock.getText()+"','"+codigoProveedor+"','"+valorNeto.replace(",",".")+"','"+valorBruto.replace(",",".")+"','ACTIVO','"+txtMarca.getText()+"','"+ultimaActualizacion+"','"+txtCodigoProvisorio.getText()+"','"+total_con_iva.replace(",",".")+"','"+txtPorcentaje.getText().replace(",",".")+"','"+ivaCombo+"','"+comboCategoria.getSelectedItem().toString()+"','"+txtBrutoSinIva.getText().replace(",",".")+"','"+ivaCompra+"')");          
                            consulta.executeUpdate("UPDATE articulo SET cod_articulo='"+txtCodigo.getText()+"', referencia='"+txtProducto.getText()+"',cantidad='"+txtStock.getText()+"',proveedor='"+codigoProveedor+"',valor='"+valorNeto+"',valor_bruto='"+valorBruto+"',marca='"+txtMarca.getText()+"',ultima_actualizacion='"+ultimaActualizacion+"',codigo_provisorio='"+txtCodigoProvisorio.getText()+"',total_con_iva='"+total_con_iva+"', porcentaje_ganancia='"+txtPorcentaje.getText().replace(",",".")+"', iva='"+ivaCombo+"', categoria='"+comboCategoria.getSelectedItem().toString()+"', valor_bruto_sin_iva='"+brutoSinIva+"', ivaCompra='"+ivaCompra+"' WHERE cod_articulo='"+txtCodigo.getText()+"'");   
                        }
                        catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null,"El ARTICULO "+" "+ txtProducto.getText()+" "+"DE STOCK"+" "+txtStock.getText()+" "+"YA EXISTE");
                            System.out.println(ex);
                        }
      
                        if(recibeCliente.getText().equals("3")){
                            Factura_Venta.comboArticulo.addItem(txtProducto.getText());
                            Factura_Venta.comboArticulo.setSelectedItem(txtProducto.getText());
                            this.setVisible(false);
                        }else{
                           if(recibeCliente.getText().equals("4")){
                                Factura_Remito.comboArticulos.addItem(txtProducto.getText());
                                Factura_Remito.comboArticulos.setSelectedItem(txtProducto.getText());
                                this.setVisible(false);
                            }else{
                                if(recibeCliente.getText().equals("5")){
                                    Factura_Presupuesto.comboArticulos.addItem(txtProducto.getText());
                                    Factura_Presupuesto.comboArticulos.setSelectedItem(txtProducto.getText());
                                    this.setVisible(false);
                                }else{
                                    if(recibeCliente.getText().equals("6")){
                                        Factura_Compra.comboArticulos.addItem(txtProducto.getText());
                                        Factura_Compra.comboArticulos.setSelectedItem(txtProducto.getText());
                                        Factura_Compra.comboCategoria.setSelectedItem(comboCategoria.getSelectedItem().toString());
                                        this.setVisible(false);
                                    }  
                                }
                            }
                        }
                    //}
                }
            }
         }
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtPrecioBrutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioBrutoKeyReleased
        String ivaCompra=comboIvaCompra.getSelectedItem().toString();
        Double porcentajeIvaCompra=0.0;
        if(ivaCompra.equals("21%")){
            porcentajeIvaCompra=1.21;
        }else
            if(ivaCompra.equals("10,5%")){
                porcentajeIvaCompra=1.105;
            }else
                if(ivaCompra.equals("27%")){
                    porcentajeIvaCompra=1.27;
                }else
                    if(ivaCompra.equals("0%")){
                        porcentajeIvaCompra=1.0;
                    }
        
        Double brutoConIva=0.0,bruto=0.0,porcent=0.0;
        
        if(!txtPrecioBruto.getText().equals("")){
            try{                                
                brutoConIva=(double)Integer.parseInt(txtPrecioBruto.getText().replace(",", "."));
            }catch (NumberFormatException e){
                brutoConIva=Double.parseDouble(txtPrecioBruto.getText().replace(",", "."));
            }
            
        }
        Double brutoSinIva= brutoConIva/porcentajeIvaCompra;
        brutoSinIva=Math.round(brutoSinIva * 100.0)/ 100.0;
        txtBrutoSinIva.setText(String.format("%.2f", brutoSinIva).replace(",", "."));
        
        
        String iva=comboIva.getSelectedItem().toString();
        Double porcentajeIva=0.0;
        if(iva.equals("21%")){
            porcentajeIva=1.21;
        }else
            if(iva.equals("10,5%")){
                porcentajeIva=1.105;
            }else
                if(iva.equals("27%")){
                    porcentajeIva=1.27;
                }else
                    if(iva.equals("0%")){
                        porcentajeIva=1.00;
                    }
        
        if(!porcentaje.isSelected()){
            txtPrecioNeto.setText("");
            txtPrecioNeto.setEditable(true);
            txtPorcentaje.setEditable(false);
            txtPorcentaje.setText("");
        }else{
            if(txtPorcentaje.getText().equals("")){
                txtPrecioNeto.setEditable(true);
                txtPorcentaje.setEditable(true);
                txtPorcentaje.setText("");
                txtPrecioNeto.setText("");
            }
            else{
                if(!txtPrecioBruto.getText().equals("") && !txtPorcentaje.getText().equals("")){
                    bruto=0.0;
                    porcent=0.0;
                    String valorBruto="",porcentajee="";
                    try{                                
                        bruto=(double)Integer.parseInt(txtPrecioBruto.getText().replace(",", "."));
                        porcent=(double)Integer.parseInt(txtPorcentaje.getText().replace(",", "."));
                    }catch (NumberFormatException e){
                        bruto=Double.parseDouble(txtPrecioBruto.getText().replace(",", "."));
                        porcent=Double.parseDouble(txtPorcentaje.getText().replace(",", "."));  
                    }
                    valorBruto=(String.format("%.2f", bruto));
                    porcentajee=(String.format("%.2f", porcent));
                
                    Double precioneto = bruto + (bruto * porcent)/100;

                    String precion=(String.format("%.2f", precioneto));
                    txtPrecioNeto.setText(precion.replace(",", "."));
                    String precioConIva=(String.format("%.2f", precioneto*porcentajeIva).replace(",", "."));
                    txtPrecioNetoIva.setText(precioConIva);
                }else{
                    txtPrecioNeto.setText("");
                    txtPrecioNetoIva.setText("");
                }
            }
        }
    }//GEN-LAST:event_txtPrecioBrutoKeyReleased

    private void txtPrecioBrutoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioBrutoKeyTyped
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
    }//GEN-LAST:event_txtPrecioBrutoKeyTyped

    private void txtPrecioNetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioNetoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioNetoActionPerformed

    private void txtPrecioNetoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioNetoKeyTyped
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
    }//GEN-LAST:event_txtPrecioNetoKeyTyped

    private void txtPorcentajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPorcentajeActionPerformed

    }//GEN-LAST:event_txtPorcentajeActionPerformed

    private void txtPorcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyReleased
        String iva=comboIva.getSelectedItem().toString();
        Double porcentajeIva=0.0;
        if(iva.equals("21%")){
            porcentajeIva=1.21;
        }else
            if(iva.equals("10,5%")){
                porcentajeIva=1.105;
            }else
                if(iva.equals("27%")){
                    porcentajeIva=1.27;
                }else
                    if(iva.equals("0%")){
                        porcentajeIva=1.00;
                    }
        
        if(!porcentaje.isSelected()){
            txtPrecioNeto.setText("");
            txtPrecioNeto.setEditable(true);
            txtPorcentaje.setEditable(false);
            txtPorcentaje.setText("");
        }else{
            if(txtPorcentaje.getText().equals("")){
                txtPrecioNeto.setEditable(true);
                txtPorcentaje.setEditable(true);
                txtPorcentaje.setText("");
                Double precioBruto=(double)Integer.parseInt(txtPrecioBruto.getText().replace(",", "."));
                txtPrecioNeto.setText(String.format("%.2f", precioBruto));
                precioBruto=precioBruto*porcentajeIva;  
                txtPrecioNetoIva.setText(String.format("%.2f", precioBruto));
            }
            else{
                if(!txtPrecioBruto.getText().equals("") && !txtPorcentaje.getText().equals("")){
                    Double bruto=0.0,porcent=0.0;
                    String valorBruto="",porcentajee="";
                    try{                                
                        bruto=(double)Integer.parseInt(txtPrecioBruto.getText().replace(",", "."));
                        porcent=(double)Integer.parseInt(txtPorcentaje.getText().replace(",", "."));
                    }catch (NumberFormatException e){
                        bruto=Double.parseDouble(txtPrecioBruto.getText().replace(",", "."));
                        porcent=Double.parseDouble(txtPorcentaje.getText().replace(",", "."));  
                    }
                    valorBruto=(String.format("%.2f", bruto));
                    porcentajee=(String.format("%.2f", porcent));
                
                    Double precioneto = bruto + (bruto * porcent)/100;

                    String precion=(String.format("%.2f", precioneto));
                    txtPrecioNeto.setText(precion.replace(",", "."));
                    String precioConIva=(String.format("%.2f", precioneto*porcentajeIva).replace(",", "."));
                    txtPrecioNetoIva.setText(precioConIva);
                }else{
                    txtPrecioNeto.setText("");
                    txtPrecioNetoIva.setText("");
                }
            }
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
            txtPrecioNeto.setEditable(true);
            txtPorcentaje.setEditable(false);
            txtPorcentaje.setText("");
        }else{
            if(txtPorcentaje.getText().equals("")){
                txtPorcentaje.setEditable(true);
                txtPorcentaje.setText("");
            }
        }
    }//GEN-LAST:event_porcentajeActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
        evt.consume();
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
        evt.consume();
    }//GEN-LAST:event_txtCodigoProvisorioKeyTyped

    private void comboProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProveedorActionPerformed

    }//GEN-LAST:event_comboProveedorActionPerformed

    private void txtPrecioNetoIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioNetoIvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioNetoIvaActionPerformed

    private void txtPrecioNetoIvaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioNetoIvaKeyTyped
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
    }//GEN-LAST:event_txtPrecioNetoIvaKeyTyped

    private void comboIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIvaActionPerformed
       String iva=comboIva.getSelectedItem().toString();
        Double porcentajeIva=0.00;
        if(iva.equals("21%")){
            porcentajeIva=1.21;
        }else
            if(iva.equals("10,5%")){
                porcentajeIva=1.105;
            }else
                if(iva.equals("27%")){
                    porcentajeIva=1.27;
                }else
                    if(iva.equals("0%")){
                        porcentajeIva=1.00;
                    }
        if(!txtPrecioNeto.getText().equals("")){
            Double precioNeto= Double.parseDouble(txtPrecioNeto.getText().replace(",", "."));
            Double netoConIva= precioNeto*porcentajeIva;
            String precion=(String.format("%.2f", netoConIva));
            txtPrecioNetoIva.setText(precion.replace(",", "."));
        }
    }//GEN-LAST:event_comboIvaActionPerformed

    private void txtPrecioNetoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioNetoKeyReleased
        String ivaCompra=comboIvaCompra.getSelectedItem().toString();
        Double porcentajeIvaCompra=0.0;
        if(ivaCompra.equals("21%")){
            porcentajeIvaCompra=1.21;
        }else
            if(ivaCompra.equals("10,5%")){
                porcentajeIvaCompra=1.105;
            }else
                if(ivaCompra.equals("27%")){
                    porcentajeIvaCompra=1.27;
                }else
                    if(ivaCompra.equals("0%")){
                        porcentajeIvaCompra=1.00;
                    }
        
        Double brutoConIva=0.0,bruto=0.0,porcent=0.0;
        
        if(!txtPrecioBruto.getText().equals("")){
            try{                                
                brutoConIva=(double)Integer.parseInt(txtPrecioBruto.getText().replace(",", "."));
            }catch (NumberFormatException e){
                brutoConIva=Double.parseDouble(txtPrecioBruto.getText().replace(",", "."));
            }
            
        }
        Double brutoSinIva= brutoConIva/porcentajeIvaCompra;
        brutoSinIva=Math.round(brutoSinIva * 100.0)/ 100.0;
        txtBrutoSinIva.setText(String.format("%.2f", brutoSinIva).replace(",", "."));
        
        String iva=comboIva.getSelectedItem().toString();
        Double porcentajeIva=0.0;
        if(iva.equals("21%")){
            porcentajeIva=1.21;
        }else
            if(iva.equals("10,5%")){
                porcentajeIva=1.105;
            }else
                if(iva.equals("27%")){
                    porcentajeIva=1.27;
                }else
                    if(iva.equals("0%")){
                        porcentajeIva=1.00;
                    }
        
        String precioNetoCadena=txtPrecioNeto.getText().replace(",", ".");
        if(precioNetoCadena.equals("")){
            precioNetoCadena="0.00";
        }
        Double precioNeto= Double.parseDouble(precioNetoCadena);
        Double netoConIva= precioNeto*porcentajeIva;
        String precion=(String.format("%.2f", netoConIva));
        txtPrecioNetoIva.setText(precion.replace(",", "."));
    }//GEN-LAST:event_txtPrecioNetoKeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Configurar_Porcentajes_Articulo repor = new Configurar_Porcentajes_Articulo();
        repor.txtRecibe.setText("2");
        repor .setVisible(true);
        repor.setLocationRelativeTo(this);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void comboCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCategoriaActionPerformed

    private void txtBrutoSinIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBrutoSinIvaKeyReleased
        String ivaCompra=comboIvaCompra.getSelectedItem().toString();
        Double porcentajeIvaCompra=0.0;
        if(ivaCompra.equals("21%")){
            porcentajeIvaCompra=1.21;
        }else
            if(ivaCompra.equals("10,5%")){
                porcentajeIvaCompra=1.105;
            }else
                if(ivaCompra.equals("27%")){
                    porcentajeIvaCompra=1.27;
                }else
                    if(ivaCompra.equals("0%")){
                        porcentajeIvaCompra=1.0;
                    }
        
        String iva=comboIva.getSelectedItem().toString();
        Double porcentajeIva=0.0;
        if(iva.equals("21%")){
            porcentajeIva=1.21;
        }else
            if(iva.equals("10,5%")){
                porcentajeIva=1.105;
            }else
                if(iva.equals("27%")){
                    porcentajeIva=1.27;
                }else
                    if(iva.equals("0%")){
                        porcentajeIva=1.00;
                    }
        Double brutoSinIva=0.0,bruto=0.0,porcent=0.0;
        
        if(!txtBrutoSinIva.getText().equals("")){
            try{                                
                brutoSinIva=(double)Integer.parseInt(txtBrutoSinIva.getText().replace(",", "."));
            }catch (NumberFormatException e){
                brutoSinIva=Double.parseDouble(txtBrutoSinIva.getText().replace(",", "."));
            }
            
        }
        Double brutoConIva= brutoSinIva*porcentajeIvaCompra;
        brutoConIva=Math.round(brutoConIva * 100.0)/ 100.0;
        txtPrecioBruto.setText(String.format("%.2f", brutoConIva).replace(",", "."));
        
        String porcentajeGanancia=txtPorcentaje.getText();        
        if(porcentajeGanancia.equals("")){
           porcentajeGanancia="0.00" ;
        }

        String valorBruto="",porcentajee="";
        
        bruto=Double.parseDouble(txtPrecioBruto.getText().replace(",", "."));
        porcent=Double.parseDouble(porcentajeGanancia.replace(",", "."));  
                    
        valorBruto=(String.format("%.2f", bruto));
        porcentajee=(String.format("%.2f", porcent));
                
        Double precioneto = bruto + (bruto * porcent)/100;

        String precion=(String.format("%.2f", precioneto));
        txtPrecioNeto.setText(precion.replace(",", "."));
        String precioConIva=(String.format("%.2f", precioneto*porcentajeIva).replace(",", "."));
         txtPrecioNetoIva.setText(precioConIva);
    }//GEN-LAST:event_txtBrutoSinIvaKeyReleased

    private void txtBrutoSinIvaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBrutoSinIvaKeyTyped
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
    }//GEN-LAST:event_txtBrutoSinIvaKeyTyped

    private void comboIvaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIvaCompraActionPerformed
        double brutoSinIva=0.0,bruto=0.0,porcent=0.0;
        String ivaCompra=comboIvaCompra.getSelectedItem().toString();
        Double porcentajeIvaCompra=0.0;
        if(ivaCompra.equals("21%")){
            porcentajeIvaCompra=1.21;
        }else
            if(ivaCompra.equals("10,5%")){
                porcentajeIvaCompra=1.105;
            }else
                if(ivaCompra.equals("27%")){
                    porcentajeIvaCompra=1.27;
                }else
                    if(ivaCompra.equals("0%")){
                        porcentajeIvaCompra=1.0;
                    }
        
        if(!txtBrutoSinIva.getText().equals("")){
            String iva=comboIva.getSelectedItem().toString();
            Double porcentajeIva=0.0;
            if(iva.equals("21%")){
                porcentajeIva=1.21;
            }else
                if(iva.equals("10,5%")){
                    porcentajeIva=1.105;
                }else
                    if(iva.equals("27%")){
                        porcentajeIva=1.27;
                    }else
                        if(iva.equals("0%")){
                            porcentajeIva=1.0;
                        }
            

            if(!txtBrutoSinIva.getText().equals("")){
                try{                                
                    brutoSinIva=(double)Integer.parseInt(txtBrutoSinIva.getText().replace(",", "."));
                }catch (NumberFormatException e){
                    brutoSinIva=Double.parseDouble(txtBrutoSinIva.getText().replace(",", "."));
                }

            }
            Double brutoConIva= brutoSinIva*porcentajeIvaCompra;
            brutoConIva=Math.round(brutoConIva * 100.0)/ 100.0;
            txtPrecioBruto.setText(String.format("%.2f", brutoConIva).replace(",","."));

            String porcentajeGanancia=txtPorcentaje.getText();        
            if(porcentajeGanancia.equals("")){
               porcentajeGanancia="0.00" ;
            }

            String valorBruto="",porcentajee="";

            bruto=Double.parseDouble(txtPrecioBruto.getText().replace(",", "."));
            porcent=Double.parseDouble(porcentajeGanancia.replace(",", "."));  

            valorBruto=(String.format("%.2f", bruto));
            porcentajee=(String.format("%.2f", porcent));

            Double precioneto = bruto + (bruto * porcent)/100;

            String precion=(String.format("%.2f", precioneto));
            txtPrecioNeto.setText(precion.replace(",", "."));
            String precioConIva=(String.format("%.2f", precioneto*porcentajeIva).replace(",","."));
            txtPrecioNetoIva.setText(precioConIva);
        }
    }//GEN-LAST:event_comboIvaCompraActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtCodigoProvisorioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProvisorioKeyReleased
        
    }//GEN-LAST:event_txtCodigoProvisorioKeyReleased

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(Editar_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Editar_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Editar_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editar_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Editar_Articulo().setVisible(true);
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
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton porcentaje;
    public static javax.swing.JTextField recibeCliente;
    public static javax.swing.JTextField txtBrutoSinIva;
    public static javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtCodigoProvisorio;
    public static javax.swing.JTextField txtMarca;
    public static javax.swing.JTextField txtPorcentaje;
    public static javax.swing.JTextField txtPrecioBruto;
    public static javax.swing.JTextField txtPrecioNeto;
    public static javax.swing.JTextField txtPrecioNetoIva;
    public static javax.swing.JTextField txtProducto;
    public static javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables

    private void deshabilitar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
