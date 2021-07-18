
package Vistas;

import com.port.PortController;
import com.port.PortSettings;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.sql.*;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import util.AppSettings;


public class Factura_Remito extends javax.swing.JFrame {
    
    //VARIABLES GLOBALES
    int banderaArticuloSeleccionado=0;
    String fecha="";
    Double subtotal=0.0;
    String codigoproveedor;
    String nomproveedor;
    String codigoproducto;
    String codigoproducto_e;
    double cant_ex=0.0;
    String precio,precioBruto, unitarioSinIva;
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
    String signo_moneda;
    

    
    public void cerrar(){ 
        this.setVisible(false);  
    }
    
    public void inabilita(){
        //PARA INHABILITAR LOS COMPONENTES QUE USO EN EL REMITO
        setearFecha();
        calendario.setEnabled(false);   

        comboVendedor.setEnabled(false);
        comboClientes.setEnabled(false);
        comboArticulos.setEnabled(false);
        btnBuscarVendedor.setEnabled(false);
        btnBuscarCliente.setEnabled(false);
        btnBuscarArticulo.setEnabled(false);
        cant33.setEditable(false);
        btnQuitarArticulo.setEnabled(false);
        btnCancelarRemito.setEnabled(false);
        btnGuardarRemito.setEnabled(false);
        sub.setEnabled(false);
        total.setEnabled(false);

        total2.setVisible(false);
        txtRemitoNumero.setText("REMITO");
        txtCodigo_Cliente.setVisible(false);
        txtCodigo_Empleado.setVisible(false);
        txtTotalBruto.setVisible(false);  
        total3.setVisible(false); 
        fact.setVisible(false);    
    }
    
    public void habilitar(){
        //PARA HABILITAR LOS COMPONENTES QUE USO EN EL REMITO
        calendario.setEnabled(true);
        comboVendedor.setEnabled(true);
        comboClientes.setEnabled(true);
        comboArticulos.setEnabled(true);
        btnBuscarVendedor.setEnabled(true);
        btnBuscarCliente.setEnabled(true);
        btnBuscarArticulo.setEnabled(true);
        cant33.setEditable(true);
        botonAgregarCliente.setEnabled(true);
        btnQuitarArticulo.setEnabled(true);
        btnCancelarRemito.setEnabled(true);
        btnGuardarRemito.setEnabled(true);
        sub.setEnabled(true);
        total.setEnabled(true);
    }
    
    public void nuevoRemito(){
        comboClientes.setSelectedIndex(1);
        comboArticulos.setSelectedIndex(0);
        cant33.setText("");
        fact.setText("");
        sub.setText("");
        total.setText("");
        btnCancelarRemito.setEnabled(false);
        habilitar();
           
        int fil = tabla1.getRowCount();
        int x;
        //RESETEO LA TABLA
        for (x=fil-1;x>=0;x--) {
            DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
            modelo.removeRow(x);
        }
        try{
            //PARA CARGAR EL NUMERO DE REMITO
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            Statement consulta2=conn.createStatement();
            ResultSet r= consulta.executeQuery("select cod_remito from  remito order by cod_remito desc");
            fact.setText("1");
            while (r.next()){
                fact.setText(r.getString(1));
            }
            
            int f;
            f=Integer.parseInt(fact.getText())+1;
            fact.setText(String.valueOf(f));
            ResultSet r2= consulta2.executeQuery("select signo_moneda from  monedas where seleccion_moneda like 'SELECCIONADA'");
            while(r2.next()){
                signo_moneda=r2.getString(1);
            }
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
        txtRemitoNumero.setText("REMITO N° "+fact.getText());
        txtCodigo_Articulo.requestFocus();
    }
    
    
   public void setearFecha() {  
       //AGREGO LA FECHA ACTUAL
        Calendar c2 = new GregorianCalendar();
        calendario.setCalendar(c2);    
    }
    
    
    
    public Factura_Remito() {
        initComponents();
        performRead();
        setLocationRelativeTo(null);
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        inabilita();
        
        //COLOR DE LA CABECERA DE LA TABLA
        JTableHeader th; 
        th = tabla1.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        //ANCHE DE LAS COLUMNAS DE LA TABLA
        tabla1.getColumnModel().getColumn(1).setMaxWidth(120);
        tabla1.getColumnModel().getColumn(1).setMinWidth(120);
        tabla1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(120);
        tabla1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(120);
        
        tabla1.getColumnModel().getColumn(2).setMaxWidth(450);
        tabla1.getColumnModel().getColumn(2).setMinWidth(450);
        tabla1.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(450);
        tabla1.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(450);
        
        tabla1.getColumnModel().getColumn(5).setMaxWidth(0);
        tabla1.getColumnModel().getColumn(5).setMinWidth(0);
        tabla1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        tabla1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        
        tabla1.getColumnModel().getColumn(6).setMaxWidth(0);
        tabla1.getColumnModel().getColumn(6).setMinWidth(0);
        tabla1.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
        tabla1.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
        
        tabla1.getColumnModel().getColumn(7).setMaxWidth(0);
        tabla1.getColumnModel().getColumn(7).setMinWidth(0);
        tabla1.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        tabla1.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        
        btnCancelarRemito.setEnabled(false);

        try{
            //cargo los combo box 
                
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql 
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet   res= consulta.executeQuery("select * from articulo WHERE estado='ACTIVO' order by referencia");    
            int i;
            comboArticulos.removeAllItems();
            comboArticulos.addItem("SELECCIONE ARTICULO");
            i=0;
            while(res.next()){
                comboArticulos.addItem(res.getString(2));
                i++;
            }

            comboClientes.removeAllItems();
            comboClientes.addItem("SELECCIONE CLIENTE");
            
            i=0;
            res= consulta.executeQuery("select * from cliente WHERE cod_cliente=0");
            while(res.next()){
                comboClientes.addItem(res.getString(2));
                i++;
            }

            i=0;
            res= consulta.executeQuery("select * from cliente WHERE cod_cliente>0 order by nombres");
            while(res.next()){
                comboClientes.addItem(res.getString(2));
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
            
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }    
        nuevoRemito();  
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
             
    Factura_Remito(Menu_Principal aThis, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        QuitarArticulo = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        comboVendedor = new javax.swing.JComboBox();
        btnBuscarVendedor = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        comboClientes = new javax.swing.JComboBox();
        btnBuscarCliente = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        comboArticulos = new javax.swing.JComboBox();
        btnBuscarArticulo = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cant33 = new javax.swing.JTextField();
        btnAgregarArticulo = new javax.swing.JButton();
        btnQuitarArticulo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        botonAgregarCliente = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        sub = new javax.swing.JTextField();
        btnGuardarRemito = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        btnCancelarRemito = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        botonAgregarArticulo = new javax.swing.JButton();
        txtTotalBruto = new javax.swing.JTextField();
        total3 = new javax.swing.JTextField();
        txtCodigo_Articulo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCodigo_Cliente = new javax.swing.JTextField();
        txtCodigo_Empleado = new javax.swing.JTextField();
        total2 = new javax.swing.JTextField();
        fact = new javax.swing.JTextField();
        btnEditarArticulo = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        calendario = new com.toedter.calendar.JDateChooser();
        txtRemitoNumero = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtPesoBascula = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemBuscarVendedor = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemBuscarCliente = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        itemAgregarCliente = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        itemBuscarArticulo = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        itemAgregarArticulo = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem1.setText(" QUITAR ARTICULO ");
        jMenuItem1.setToolTipText("");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        QuitarArticulo.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrar venta");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage1.setBackground(new java.awt.Color(255, 255, 255));
        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoMenumar22.png"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(239, 255, 239));
        jLabel5.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel5.setText("Vendedor");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, -1, 40));

        comboVendedor.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboVendedorActionPerformed(evt);
            }
        });
        jPanel1.add(comboVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 250, 40));

        btnBuscarVendedor.setBackground(new java.awt.Color(93, 116, 163));
        btnBuscarVendedor.setForeground(new java.awt.Color(255, 255, 255));
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
        jPanel1.add(btnBuscarVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 20, 60, 40));

        jLabel7.setBackground(new java.awt.Color(239, 255, 239));
        jLabel7.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel7.setText("Cliente");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 90, -1));

        comboClientes.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClientesActionPerformed(evt);
            }
        });
        jPanel1.add(comboClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 310, 42));

        btnBuscarCliente.setBackground(new java.awt.Color(93, 116, 163));
        btnBuscarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoLupaF3.png"))); // NOI18N
        btnBuscarCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });
        btnBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnBuscarClienteKeyTyped(evt);
            }
        });
        jPanel1.add(btnBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 60, 42));

        jLabel9.setBackground(new java.awt.Color(239, 255, 239));
        jLabel9.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel9.setText("Cod Art");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 90, 50));

        comboArticulos.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        comboArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboArticulosActionPerformed(evt);
            }
        });
        jPanel1.add(comboArticulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 310, 45));

        btnBuscarArticulo.setBackground(new java.awt.Color(93, 116, 163));
        btnBuscarArticulo.setForeground(new java.awt.Color(255, 255, 255));
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
        jPanel1.add(btnBuscarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, 60, 45));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel6.setText("Cantidad");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, 40));

        cant33.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cant33.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cant33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        cant33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cant33ActionPerformed(evt);
            }
        });
        cant33.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cant33KeyTyped(evt);
            }
        });
        jPanel1.add(cant33, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 120, 41));

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
        jPanel1.add(btnAgregarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 150, 41));

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
        jPanel1.add(btnQuitarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 150, 42));

        tabla1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CANTIDAD", "Cod", "DESCRIPCION", "V. UNITARIO", "V. TOTAL", "unitario bruto", "total bruto", "unitario_sin_iva"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, true, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla1.setComponentPopupMenu(QuitarArticulo);
        tabla1.setRowHeight(25);
        tabla1.setRowMargin(4);
        tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla1MouseClicked(evt);
            }
        });
        tabla1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabla1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 1000, 150));

        botonAgregarCliente.setBackground(new java.awt.Color(93, 116, 163));
        botonAgregarCliente.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        botonAgregarCliente.setForeground(new java.awt.Color(255, 255, 255));
        botonAgregarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoAgregarF4.png"))); // NOI18N
        botonAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(botonAgregarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 50, 42));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel11.setText("SubTotal");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 110, 50));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel13.setText("Total");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 80, 40));

        total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 170, 40));

        sub.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sub.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(sub, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 35, 170, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 410, 310, 160));

        btnGuardarRemito.setBackground(new java.awt.Color(51, 153, 255));
        btnGuardarRemito.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnGuardarRemito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardar.png"))); // NOI18N
        btnGuardarRemito.setBorder(null);
        btnGuardarRemito.setBorderPainted(false);
        btnGuardarRemito.setContentAreaFilled(false);
        btnGuardarRemito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarRemito.setFocusCycleRoot(true);
        btnGuardarRemito.setFocusable(false);
        btnGuardarRemito.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btnGuardarRemito.setRequestFocusEnabled(false);
        btnGuardarRemito.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btnGuardarRemito.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btnGuardarRemito.setVerifyInputWhenFocusTarget(false);
        btnGuardarRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarRemitoActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardarRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 160, 155));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel17.setText("Guardar");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 540, -1, -1));

        btnCancelarRemito.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelarRemito.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnCancelarRemito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelar.png"))); // NOI18N
        btnCancelarRemito.setBorder(null);
        btnCancelarRemito.setBorderPainted(false);
        btnCancelarRemito.setContentAreaFilled(false);
        btnCancelarRemito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarRemito.setFocusCycleRoot(true);
        btnCancelarRemito.setFocusable(false);
        btnCancelarRemito.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarRemito.setRequestFocusEnabled(false);
        btnCancelarRemito.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarRemito.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarRemito.setVerifyInputWhenFocusTarget(false);
        btnCancelarRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarRemitoActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelarRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 400, -1, 150));

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel19.setText("Cancelar");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 540, 100, -1));

        botonAgregarArticulo.setBackground(new java.awt.Color(93, 116, 163));
        botonAgregarArticulo.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        botonAgregarArticulo.setForeground(new java.awt.Color(255, 255, 255));
        botonAgregarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoAgregarF6.png"))); // NOI18N
        botonAgregarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarArticuloActionPerformed(evt);
            }
        });
        jPanel1.add(botonAgregarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 50, 45));
        jPanel1.add(txtTotalBruto, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 520, 40, 40));
        jPanel1.add(total3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 520, 40, 40));

        txtCodigo_Articulo.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txtCodigo_Articulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigo_Articulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtCodigo_ArticuloMouseReleased(evt);
            }
        });
        txtCodigo_Articulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigo_ArticuloActionPerformed(evt);
            }
        });
        txtCodigo_Articulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigo_ArticuloKeyReleased(evt);
            }
        });
        jPanel1.add(txtCodigo_Articulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 320, 40));

        jLabel10.setBackground(new java.awt.Color(239, 255, 239));
        jLabel10.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel10.setText("Articulo");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 90, 60));
        jPanel1.add(txtCodigo_Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 470, 40, 40));
        jPanel1.add(txtCodigo_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 470, 40, 40));
        jPanel1.add(total2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 430, 40, 40));

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
        jPanel1.add(fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, 40, -1));

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
        jPanel1.add(btnEditarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 110, 38));

        panelImage1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1020, 590));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel8.setText("Fecha");
        panelImage1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, 70, 40));

        calendario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        panelImage1.add(calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, 140, 40));

        txtRemitoNumero.setFont(new java.awt.Font("Calibri", 1, 33)); // NOI18N
        txtRemitoNumero.setText("REMITO N°");
        panelImage1.add(txtRemitoNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 380, 50));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        panelImage1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 50, 50));

        txtPesoBascula.setBackground(new java.awt.Color(0, 204, 0));
        txtPesoBascula.setFont(new java.awt.Font("Courier New", 1, 30)); // NOI18N
        txtPesoBascula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panelImage1.add(txtPesoBascula, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 30, 140, -1));

        jLabel25.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Balanza");
        panelImage1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, 140, 20));

        getContentPane().add(panelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 710));

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setBorder(null);

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Opciones");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N

        itemBuscarVendedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itemBuscarVendedor.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        itemBuscarVendedor.setText("Buscar vendedor");
        itemBuscarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarVendedorActionPerformed(evt);
            }
        });
        jMenu1.add(itemBuscarVendedor);
        jMenu1.add(jSeparator1);

        itemBuscarCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        itemBuscarCliente.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        itemBuscarCliente.setText("Buscar cliente");
        itemBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarClienteActionPerformed(evt);
            }
        });
        jMenu1.add(itemBuscarCliente);
        jMenu1.add(jSeparator2);

        itemAgregarCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        itemAgregarCliente.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        itemAgregarCliente.setText("Agregar cliente");
        itemAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarClienteActionPerformed(evt);
            }
        });
        jMenu1.add(itemAgregarCliente);
        jMenu1.add(jSeparator3);

        itemBuscarArticulo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        itemBuscarArticulo.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        itemBuscarArticulo.setText("Buscar articulo");
        itemBuscarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarArticuloActionPerformed(evt);
            }
        });
        jMenu1.add(itemBuscarArticulo);
        jMenu1.add(jSeparator4);

        itemAgregarArticulo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        itemAgregarArticulo.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        itemAgregarArticulo.setText("Agregar articulo");
        itemAgregarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarArticuloActionPerformed(evt);
            }
        });
        jMenu1.add(itemAgregarArticulo);
        jMenu1.add(jSeparator5);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        jMenuItem2.setText("Ancho de impresión");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboVendedorActionPerformed
        try{
            //SEGUN EL NOMBRE QUE SELECCIONE CUANDO APRETO EL COMBO VENDEDOR, BUSCO EN LA BD EL CODIGO DE ESE VENDEDOR PARA LUEGO GUARDARLO EN EL REMITO
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
       
            ResultSet rs = consulta.executeQuery("SELECT cod_empleado FROM empleado WHERE (nombres = '"+SubCadenaNombreEmpleado+"' AND apellidos = '"+SubCadenaApellidoEmpleado+"' )");
            while (rs.next()) {
                cod_empleado= rs.getString(1);
            }
            txtCodigo_Empleado.setText(cod_empleado);
            conn.close();  
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
        //RESTRICCIONES QUE HABILITAN LA CARGA DEL ARTICULO SOLO CUANDO ESTAN SELECCIONADOS UN CLIENTE Y UN VENDEDOR
        
        txtCodigo_Articulo.requestFocus();
    }//GEN-LAST:event_comboVendedorActionPerformed

    private void btnBuscarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVendedorActionPerformed
        //LLAMO AL FORM QUE MUESTRA LOS VENDEDORES
        enviar_empleado form= new enviar_empleado ();
        form.setVisible(true);
        form.toFront();
        enviar_empleado.txt_recibeEmpleado.setText("9");
    }//GEN-LAST:event_btnBuscarVendedorActionPerformed

    private void btnBuscarVendedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarVendedorKeyPressed

    }//GEN-LAST:event_btnBuscarVendedorKeyPressed

    private void comboClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClientesActionPerformed
        //SEGUN EL NOMBRE DE CLIENTE SELECCIONADO EN EL COMBO BUSCO SU CODIGO EN LA BD
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            nomcliente = (String)comboClientes.getSelectedItem();
            codigocliente=null;
            
            ResultSet rs = consulta.executeQuery("SELECT cod_cliente FROM cliente WHERE nombres = '"+nomcliente+"'");
            while (rs.next()) {
                codigocliente= rs.getString(1);
            }
            txtCodigo_Cliente.setText(codigocliente);
            conn.close();
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
        //RESTRICCIONES QUE ME HABILITAN LA CARGA DE ARTICULOS SOLO SI HAY SELECCIONADO UN CLIENTE Y UN VENDEDOR
        
        //LE DOY FOCO AL TEXTFIELD COD ART POR SI QUIERO INGRESAR EL CODIGO CON UN LECTOR DE BARRAS
        txtCodigo_Articulo.requestFocus();
    }//GEN-LAST:event_comboClientesActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        //LLAMO AL FORM QUE ME MUESTRA LOS CLIENTES
        enviar_cliente form= new enviar_cliente ();
        form.setVisible(true);
        form.toFront();
        enviar_cliente.recibe.setText("4");
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnBuscarClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarClienteKeyTyped

    }//GEN-LAST:event_btnBuscarClienteKeyTyped

    private void comboArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboArticulosActionPerformed
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            nomproducto = (String)comboArticulos.getSelectedItem();
            codigoproducto=null;
            //SEGUN EL NOMBRE DE ARTICULO SELECCIONADO EN EL COMBO TRAIGO SU CODIGO DE LA BD PARAGUARDARLO EN LA FACTURA
            ResultSet rs = consulta.executeQuery("SELECT cod_articulo FROM articulo WHERE (referencia = '"+comboArticulos.getSelectedItem()+"')");
            while (rs.next()) {
                codigoproducto= rs.getString(1);
            }
            txtCodigo_Articulo.setText(codigoproducto);
            banderaArticuloSeleccionado=1;
            conn.close();  // Cierra la conexión
           
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
        
        String balanza=txtPesoBascula.getText();
        if(balanza.equals("")||balanza.equals("0")){
            cant33.setText("");
        }else{
            double balanzaD=Double.parseDouble(txtPesoBascula.getText().replace(',','.'));
            cant33.setText(""+balanzaD);      
        }
        cant33.requestFocus();
    }//GEN-LAST:event_comboArticulosActionPerformed

    private void btnBuscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarArticuloActionPerformed
        //PARA BUSCAR LOS ARTICULOS Y TRAERLOS A LA FACTURA
        enviar_producto form= new enviar_producto ();
        form.setVisible(true);
        form.toFront();
        //LOS TXT RECIBE SON PORQUE UN MODULO LLEVA DATOS A VARIOS SEGUN EL VALOS DE SU TXT RECIBE
        enviar_producto.txt_recibe.setText("10");
    }//GEN-LAST:event_btnBuscarArticuloActionPerformed

    private void btnBuscarArticuloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarArticuloKeyTyped

    }//GEN-LAST:event_btnBuscarArticuloKeyTyped

    private void cant33KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cant33KeyTyped
        char []p={'1','2','3','4','5','6','7','8','9','0','.',','}; //PARA PODER INGRESAR SOLO NUMEROS O PUNTOS O COMAS
        int b=0;
        for(int i=0;i<=11;i++){
            if (p[i]==evt.getKeyChar()){
                b=1;
            }
        }
        if(b==0){evt.consume();  getToolkit().beep(); }    
    }//GEN-LAST:event_cant33KeyTyped

    private void btnAgregarArticuloMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarArticuloMouseEntered

    }//GEN-LAST:event_btnAgregarArticuloMouseEntered

    private void btnAgregarArticuloMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarArticuloMouseExited

    }//GEN-LAST:event_btnAgregarArticuloMouseExited

    public void agregarArticulo(){
        //AGREGO EL ARTICULO A LA TABLA
        Double subTotalBrutoArt=0.0;
        int pe=0,fil = tabla1.getRowCount();
        //ME FIJO SI EL PRODUCTO YA FUE INGRESADO EN LA TABLA
        for (int x=0;x<=fil-1;x++) {
            String aux= (String) (tabla1.getValueAt(x,2));
            if(aux.equals(comboArticulos.getSelectedItem()))
            {
                pe=1;
            }
        }
        if(pe==1)
        {
            System.out.println(pe);
            JOptionPane.showMessageDialog(null,"Este Articulo Ya Existe") ;
        }else{
            if (comboArticulos.getSelectedItem().equals("SELECCIONE ARTICULO"))
            {
                JOptionPane.showMessageDialog(null, "Falta elegir el articulo");
            }else
            {
                if (cant33.getText().equals("") || cant33.getText().equals("0") || cant33.getText().equals("0.00") )
                {
                    JOptionPane.showMessageDialog(null, "Debe Digitar la Cantidad de articulo a Llevar");
                    cant33.requestFocus();
                }else
                {
                    try{
                        Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                        Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                        ResultSet   res= consulta.executeQuery("select cantidad from articulo where cod_articulo = '"+txtCodigo_Articulo.getText()+"'");
                        //BUSCO EL STOCK DEL ARTICULO
                        while(res.next()){
                            cant_ex=Double.parseDouble(res.getString(1));
                            break;
                        }
                        res.close();
                    }catch(SQLException e){
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null,"Error en el SQL") ;
                    }
                    //SI DESEA INGRESAR UNA CANTIDAD QUE NO ESTA EN STOCK MUESTRO UB MENSAJE Y SETEO EL TEXTFIELD CANT33 CON LA CANTIDAD DESIPONIBLE EN STOCK
                    double cantidad=Double.parseDouble(cant33.getText().replace(',','.'));
                    if (cant_ex < cantidad)
                    {
                        JOptionPane.showMessageDialog(null, "No existe Cantidad Disponible de Dicho Articulo, La Cantidad Dispomible es: "+cant_ex);
                        cant33.setText(""+cant_ex);
                        cant33.requestFocus();
                    }
                    else
                    {
                        fila = tabla1.getRowCount();
                        columna = tabla1.getColumnCount();
                        //AGREGO EL ARTICULO A LA TABLA
                        DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
                        modelo.addRow( new Object [] {null,null,null,null,null,null,null});
                                    
                        tabla1.setValueAt(cantidad,fila,0);
                        //EL banderaArticuloSeleccionado ES PORQUE SI TRAIGO EL ARTICULO ATRAVEZ DEL BOTON BUSCAR SETEO EL CODIGO EN UN TEXTFIELD, SI LO TRAIGO
                        //DESPUES DE SELECCIONAR EL COMBOBOX DE ARTICULO, TRAE EL CODIGO DE LA BD Y LO GUARDA EN codigoproducto
                        if(banderaArticuloSeleccionado==0){
                            tabla1.setValueAt(txtCodigo_Articulo.getText(),fila,1);
                        }else{
                            tabla1.setValueAt(codigoproducto,fila,1);
                            banderaArticuloSeleccionado=0;
                        }
                        tabla1.setValueAt(nomproducto,fila,2);

                        try{
                            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                            //TRAIGO LOS PRECIOS UNITARIOS CON Y SIN IVA DE LA BD
                            ResultSet r= consulta.executeQuery("select total_con_iva, valor_bruto, valor_bruto_sin_iva from articulo where cod_articulo= '"+txtCodigo_Articulo.getText()+"'");
                            while(r.next()){
                                precio=r.getString(1);
                                precioBruto=r.getString(2);
                                unitarioSinIva=r.getString(3);
                            }

                        } catch(SQLException e){
                            System.out.println(e);
                            JOptionPane.showMessageDialog(null,"Este Articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
                        }

                        tabla1.setValueAt(precio,fila,3);//VALOR UNITARIO DE VENTA
                        tabla1.setValueAt(precioBruto,fila,5); //ACA VA EL VALOR COSTO DEL PRODUCTO
                        tabla1.setValueAt(unitarioSinIva,fila,7); //ACA VA EL VALOR COSTO SIN IVA DEL PRODUCTO
                        
                        
                        int fila2 = tabla1.getRowCount();
                        subtotal=0.0;
                        for (int x=0;x<=fila2-1;x++) {
                            double totalsub=Double.parseDouble((tabla1.getValueAt(x,3).toString().replace(",", ".")))* Double.parseDouble((tabla1.getValueAt(x,0).toString())); //a total sub le multiplico la cantidad por el precio de la fila
                            double totalsubArticuloBruto=(Double.parseDouble(tabla1.getValueAt(x,5).toString().replace(",", "."))) * Double.parseDouble(tabla1.getValueAt(x,0).toString());
                            subTotalBrutoArt=subTotalBrutoArt + totalsubArticuloBruto;//sumo el total en bruto de todos los articulos que voy agregando
                            
                            subtotal=totalsub+subtotal;
                            tabla1.setValueAt(String.format("%.2f", totalsub).replace(".", ","),x,4);
                            tabla1.setValueAt(String.format("%.2f", subTotalBrutoArt).replace(".", ","),x,6);//precio total bruto de los articulos                         
                        }     
                        sub.setText(signo_moneda+" "+String.format("%.2f", subtotal));

                        Double totals=subtotal;
                        String totals1=String.format("%.2f", totals);
                        
                        total.setText(signo_moneda+" "+totals1); //TOTAL MUESTRA EL SIGNO $ PERO EL QUE GUARDA EL VALOR PARA ALMACENARLO EN LA BD ES TOTAL2
                        total2.setText(totals1);
                        total3.setText(totals1);
                        txtTotalBruto.setText(String.format("%.2f", subTotalBrutoArt));
                        comboArticulos.setSelectedIndex(0);
                        cant33.setText("");
                    }
                }
            }
        }
        txtCodigo_Articulo.requestFocus();
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
        Double subTotalBrutoArt=0.0;
        int Select=tabla1.getSelectedRow();
        
        if(Select>=0){
            try
            {


                DefaultTableModel modelo2=(DefaultTableModel)tabla1.getModel();    
                modelo2.removeRow(Select);

                int pe=0,fil = tabla1.getRowCount();
                double acum=0.0;
                for (int x=0;x<=fil-1;x++) {

                    Double unitarioBruto=Double.parseDouble(tabla1.getValueAt(x, 5).toString().replace(",","."))*Double.parseDouble(tabla1.getValueAt(x, 0).toString().replace(",","."));
                    acum=acum+unitarioBruto;
                    acum=Math.round(acum * 100.0)/ 100.0;
                    tabla1.setValueAt(acum,x,6);
                }
                int fila2 = tabla1.getRowCount();
                subtotal=0.0;
                for (int x=0;x<=fila2-1;x++) {
                    double totalsub=Double.parseDouble((tabla1.getValueAt(x,3).toString().replace(",", ".")))* Double.parseDouble((tabla1.getValueAt(x,0).toString())); //a total sub le multiplico la cantidad por el precio de la fila
                    double totalsubArticuloBruto=(Double.parseDouble(tabla1.getValueAt(x,5).toString().replace(",", "."))) * Double.parseDouble(tabla1.getValueAt(x,0).toString());
                    subTotalBrutoArt=subTotalBrutoArt + totalsubArticuloBruto;//sumo el total en bruto de todos los articulos que voy agregando
                            
                    subtotal=totalsub+subtotal;
                    tabla1.setValueAt(String.format("%.2f", totalsub).replace(".", ","),x,4);
                    tabla1.setValueAt(String.format("%.2f", subTotalBrutoArt).replace(".", ","),x,6);//precio total bruto de los articulos                         
                }  
                
                sub.setText(signo_moneda+" "+String.format("%.2f", subtotal));

                Double totals=subtotal;
                String totals1=String.format("%.2f", totals);
                        
                total.setText(signo_moneda+" "+totals1); //TOTAL MUESTRA EL SIGNO $ PERO EL QUE GUARDA EL VALOR PARA ALMACENARLO EN LA BD ES TOTAL2
                total2.setText(totals1);
                total3.setText(totals1);
                txtTotalBruto.setText(String.format("%.2f", subTotalBrutoArt));

                //modelo.removeRow(fila);
                fila-=1;
                int filat=tabla1.getRowCount();
                if (filat==0){
                    comboVendedor.setEnabled(true);
                    comboClientes.setEnabled(true);
                }
                        
            }catch(Exception e){
                    
            }

        //HAGO FOCO EN COD ART PARA INGRESAR OTRO ARTICULO
        txtCodigo_Articulo.requestFocus();
        }else{
             int filat=tabla1.getRowCount();
            if(filat==0){
                JOptionPane.showMessageDialog(null, "NO HAY PRODUCTOS PARA QUITAR"); 
            }else{
                JOptionPane.showMessageDialog(null, "SELECCIONE LA FILA QUE DESEA QUITAR");   
            }
        }
    }//GEN-LAST:event_btnQuitarArticuloActionPerformed

    private void botonAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarClienteActionPerformed
        //LLAMO AL FORM PARA AGREGAR UN CLIENTE A LA FACTURA
        Agregar_Cliente form= new Agregar_Cliente ();
        form.setVisible(true);
        Agregar_Cliente.recibeCliente.setText("4");
        form.toFront();
    }//GEN-LAST:event_botonAgregarClienteActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed
    public void modificarArticuloFactura(){
        //CUANDO EDITO LA CANTIDAD O EL PRECIO UNITARIO DEL PRODUCTO ESTANDO YA ADENTRO DE LA TABLA
        int Select=tabla1.getSelectedRow(), bandera=0;
        Double subTotalBrutoArticulo=0.0;     
        int fila2 = tabla1.getRowCount();
        subtotal=0.0;
        for (int x=0;x<=fila2-1;x++) {
            try{
                Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                ResultSet   res= consulta.executeQuery("select cantidad from articulo where referencia = '"+tabla1.getValueAt(Select,2).toString()+"'");
                while(res.next()){
                    cant_ex=Double.parseDouble(res.getString(1));
                    break;
                }
                res.close();
            }catch(SQLException e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Error en el SQL") ;
            }
            //SI QUIERE INGRESAR UNA CANTIDAD QUENO ESTA DISPONIBLE EN STOCK
 
            if (cant_ex < Double.parseDouble(tabla1.getValueAt(Select,0).toString()) && bandera==0){
                JOptionPane.showMessageDialog(null, "¡ATENCION! La Cantidad Disponible de Dicho Articulo es "+cant_ex+" unidades");
                bandera=1;
                tabla1.setValueAt(cant_ex,Select,0);
            }
            //ESTOS CALCULOS SON PARA CUANDO CAMBIO LA CANTIDAD O EL PRECIO SE ACTUALICEN LOS VALORES TOTALES
            Double totalsub=Double.parseDouble((tabla1.getValueAt(x,3).toString().replace(",",".")))* Double.parseDouble((tabla1.getValueAt(x,0).toString())); //a total sub le multiplico la cantidad por el precio de la fila
            subtotal=totalsub+subtotal;
            Double totalsubBrutoArt=(Double.parseDouble(tabla1.getValueAt(x,5).toString().replace(",","."))) * Double.parseDouble(tabla1.getValueAt(x,0).toString());
            subTotalBrutoArticulo=subTotalBrutoArticulo+totalsubBrutoArt;
            tabla1.setValueAt(String.format("%.2f", totalsub),x,4);   
            tabla1.setValueAt(String.format("%.2f", totalsubBrutoArt),x,6);
        }
         //ACTUALIZO LOS TOTALES GENERALES               
        sub.setText(signo_moneda+" "+String.format("%.2f", subtotal).replace(".",","));

        String totals2=String.format("%.2f", subtotal).replace(".",",");
        total.setText(signo_moneda+" "+totals2);
        total2.setText(totals2);
        total3.setText(totals2);
        txtTotalBruto.setText(String.format("%.2f", subTotalBrutoArticulo).replace(".",","));

        comboArticulos.setSelectedIndex(0);
        cant33.setText("");
        //HAGO FOCO EN COD ART PARA VOLVER A INGRESAR OTRO ARTICULO
        txtCodigo_Articulo.requestFocus();
    }
    private void tabla1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla1KeyReleased
        modificarArticuloFactura();
    }//GEN-LAST:event_tabla1KeyReleased

    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked
        modificarArticuloFactura();
    }//GEN-LAST:event_tabla1MouseClicked

    private void cant33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cant33ActionPerformed
        agregarArticulo();
    }//GEN-LAST:event_cant33ActionPerformed

    private void btnGuardarRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarRemitoActionPerformed
          //GUARDO EL REMITO EN LA BD
        try {
            if (comboVendedor.getSelectedItem().equals("SELECCIONE VENDEDOR")) {
                JOptionPane.showMessageDialog(null, "Falta elegir el Vendedor","Advertencia",JOptionPane.WARNING_MESSAGE);
            }else {
                if (comboClientes.getSelectedItem().equals("SELECCIONE CLIENTE")) {
                    JOptionPane.showMessageDialog(null, "Falta elegir el Cliente","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else {
                    if (calendario.getDate() == null) {
                        JOptionPane.showMessageDialog(null, "La fecha estaba vacia","Advertencia",JOptionPane.WARNING_MESSAGE);
                        setearFecha();
                    }else {
                        if (fact.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Debe Digitar Numero de la Factura","Advertencia",JOptionPane.WARNING_MESSAGE);
                            fact.requestFocus();
                        }else {
                            if ( tabla1.getRowCount() <= 0 ) {
                                JOptionPane.showMessageDialog(null, "No Hay Ningun Producto a Facturar","Advertencia",JOptionPane.ERROR_MESSAGE);
                            }else {
                                try{
                                    Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                                    Statement consulta=conn.createStatement();
                                    Statement consulta1=conn.createStatement();
                                    int fil = tabla1.getRowCount();
                                    int col = tabla1.getColumnCount();
                                    
                                    //TRAIGO LA FECHA DEL REMITO PARA GUARDARLE EN LA BD
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

                                    int x,y;
                                    
                                    //INSERTO LOS DATOS GENERALES EN LA TABLA REMITO DE LA BD
                                    consulta.executeUpdate("insert into remito (cod_remito,fecha,cod_cliente,cod_empleado,tipo_pago,condicion,total) values('"+fact.getText()+"','"+fecha+"','"+txtCodigo_Cliente.getText()+"','"+txtCodigo_Empleado.getText()+"','CUENTA CORRIENTE','NO FACTURADO','"+total2.getText().replace(",",".")+"')");
                                    //INSERTO CADA ARTICULO EN REFERENCIA REMITO EN LA BD
                                    for (x=0;x<=fil-1;x++) {
                                        consulta1.executeUpdate("insert into referencia_remito (cod_remito,valor_unitario,valor_total,cod_articulo,referencia,cantidad,total,unitarioBruto,totalBruto,unitario_sin_iva) values('"+fact.getText()+"'  ,'"+tabla1.getValueAt(x,3)+"','"+tabla1.getValueAt(x,4).toString().replace(",",".")+"','"+tabla1.getValueAt(x,1)+"','"+tabla1.getValueAt(x,2)+"','"+tabla1.getValueAt(x,0)+"','"+total2.getText().replace(",",".")+"','"+tabla1.getValueAt(x,5)+"','"+txtTotalBruto.getText().replace(",",".")+"','"+tabla1.getValueAt(x,7)+"')"); 
                                    }
                                    //ESTA PARTE ES PARA AGREGAR LOS DATOS AL MODULO CUENTA CORRIENTE, PARA CONTROLAR LOS SALDOS DEL CLIENTE
                                    
                                    Statement consulta3=conn.createStatement(); //BUSCO EL CLIENTE EN LA CUENTA CORRIENTE Y SI NO ESTA LO AGREGO
                                    ResultSet r3= consulta3.executeQuery("select * from cuenta_corriente WHERE cod_cliente='"+txtCodigo_Cliente.getText()+"'");
                                    int banderaClienteCorriente=0;
                                    while(r3.next()){
                                        banderaClienteCorriente=1;
                                    }
                                    //SIEL CLIENTE NO ESTA EN CUENTA CORRIENTE LO AGREGO
                                    if(banderaClienteCorriente==0){
                                         consulta3.executeUpdate("insert into cuenta_corriente (cod_cliente,ultimo_pago,fecha_ultimo_pago,total_pago,saldo_restante) values('"+txtCodigo_Cliente.getText()+"','0.00','"+fecha+"','0.00','0.00')");
                                    }
                                    //REINICIO LOS COMBOS Y TEXTFIELDS
                                    Seleccionar_Tamano_Factura form= new Seleccionar_Tamano_Factura ();
                                    form.setVisible(true);
                                    form.toFront();
                                    Seleccionar_Tamano_Factura.fact.setText(fact.getText());
                                    Seleccionar_Tamano_Factura.txtRecibe.setText("2");
                                    
                                    inabilita();
                                    comboClientes.setSelectedIndex(0);
                                    comboArticulos.setSelectedIndex(0);
                                    sub.setText("");
                                    total.setText("");
                                    cant33.setText("");

                                    /*Connection miconexion = conexion.ObtenerConexion();
                                    Map parametros = new HashMap();
                                    parametros.put("codf",fact.getText());
                                    
                                    //IMPRIMO EL REPORTE
                                    int op=JOptionPane.showConfirmDialog(null, "REMITO AGREGADO ¿DESEA IMPRIMIRLO?","SELECCIONE UN OPCION" ,JOptionPane.YES_NO_OPTION);
                                    if (op==JOptionPane.YES_NO_OPTION){
   
                                        try {
                                            this.setVisible(false);
                                            String reporte="remitoa.jasper";
                                            JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                                            JasperViewer ventanavisor=new JasperViewer(informe,false);
                                            ventanavisor.setTitle("Reporte de remito");
                                            ventanavisor.setVisible(true);
                                        } catch (Exception e) {
                                            JOptionPane.showMessageDialog(this, e.getMessage());
                                        } 
                                    }else
                                    {
                                    // no hacer nada

                                    }*/
                                    
                                    for (x=0;x<=fil-1;x++) {
                                        String aux= (tabla1.getValueAt(x,0).toString().replace(',','.'));

                                        ResultSet   res= consulta.executeQuery("select cantidad from articulo where referencia='"+tabla1.getValueAt(x,2)+"'");
                                        while(res.next()){
                                            cant_ex=Double.parseDouble(res.getString(1));

                                        }
                                        //RESTO LA CANTIDAD DE CADA ARTICULO VENDIDO AL STOCK
                                        nueva_cant = cant_ex - Double.parseDouble(aux);
                                        consulta.executeUpdate("UPDATE articulo SET cantidad='"+nueva_cant+"' WHERE referencia='"+tabla1.getValueAt(x,2)+"'");
                                    }
                                    
                                    //SETEO LA TABLA EN VACIO
                                    for (x=0;x<=fil-1;x++) {
                                        for (y=0;y<=col-1;y++) {
                                            tabla1.setValueAt("",x,y);
                                        }
                                    }
                                    //REMUEVO LASFILAS
                                    for (x=fil-1;x>=0;x--) {
                                        DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
                                        modelo.removeRow(x);
                                    }
                                    //PARA PASAR AL REMITO SIGUIENTE
                                    nuevoRemito();
                                } catch(SQLException e){
                                    JOptionPane.showMessageDialog(null,"Este Producto Ya Existes") ;
                                    System.out.println(e);
                                }
                            }
                        }
                    }
                }
            }
            
        }catch (ArrayIndexOutOfBoundsException e) {
            
        }
    }//GEN-LAST:event_btnGuardarRemitoActionPerformed

    private void btnCancelarRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarRemitoActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarRemitoActionPerformed

    private void txtCodigo_ArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigo_ArticuloActionPerformed
        //PARA QUE A MEDIDA QUE VOY INGRESANDO EL CODIGO EN EL CAMPO COD ART SE SETEE EL COMBOBOX SI ES QUE ESTA EL ARTICULO, Y LUEGO HAGA FOCO
        //EN LA CANTIDAD PARA AGREGAR EL ARTICULO A LA FACTURA
        int bandera=0;
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            codigoproducto=txtCodigo_Articulo.getText();
            String nombreArticulo="";
            
            //SEGUN SI ESTAEL CODIGO SETEO EL COMBOBOX DE ARTICULO
            ResultSet rs = consulta.executeQuery("SELECT referencia FROM articulo WHERE cod_articulo = '"+txtCodigo_Articulo.getText()+"'");
            while (rs.next()) {
                nombreArticulo= rs.getString(1);
                bandera=1;
            }
            if(bandera==1){
                comboArticulos.setSelectedItem(nombreArticulo);
            }
            
            conn.close();  // Cierra la conexión

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
        if(bandera==1){
           cant33.requestFocus();
        }else{
            JOptionPane.showMessageDialog(null, "El codigo ingresado no pertenece a ningun articulo del inventario"); 
        }
    }//GEN-LAST:event_txtCodigo_ArticuloActionPerformed

    private void botonAgregarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarArticuloActionPerformed
        //LLAMO AL FORM PARA AGREGAR AL ARTICULO
        Agregar_Articulo_Ventas form= new Agregar_Articulo_Ventas ();
        form.setVisible(true);
        Agregar_Articulo_Ventas.recibeCliente.setText("4");
        form.toFront();
    }//GEN-LAST:event_botonAgregarArticuloActionPerformed

    private void itemAgregarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAgregarArticuloActionPerformed
        //ESTOS ITEMS SON PARA PODER LLAMAR LOS FORM CON LOS ACCESOS RAPIDOS
        Agregar_Articulo_Ventas form= new Agregar_Articulo_Ventas ();
        form.setVisible(true);
        Agregar_Articulo_Ventas.recibeCliente.setText("4");
        form.toFront();
    }//GEN-LAST:event_itemAgregarArticuloActionPerformed

    private void itemBuscarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarVendedorActionPerformed
        //ESTOS ITEMS SON PARA PODER LLAMAR LOS FORM CON LOS ACCESOS RAPIDOS
        enviar_empleado form= new enviar_empleado ();
        form.setVisible(true);
        form.toFront();
        enviar_empleado.txt_recibeEmpleado.setText("9");
    }//GEN-LAST:event_itemBuscarVendedorActionPerformed

    private void itemBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarClienteActionPerformed
        enviar_cliente form= new enviar_cliente ();
        form.setVisible(true);
        form.toFront();
        enviar_cliente.recibe.setText("4");
    }//GEN-LAST:event_itemBuscarClienteActionPerformed

    private void itemAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAgregarClienteActionPerformed
        //ESTOS ITEMS SON PARA PODER LLAMAR LOS FORM CON LOS ACCESOS RAPIDOS
        Agregar_Cliente form= new Agregar_Cliente ();
        form.setVisible(true);
        Agregar_Cliente.recibeCliente.setText("4");
        form.toFront();
    }//GEN-LAST:event_itemAgregarClienteActionPerformed

    private void itemBuscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarArticuloActionPerformed
        //ESTOS ITEMS SON PARA PODER LLAMAR LOS FORM CON LOS ACCESOS RAPIDOS
        enviar_producto form= new enviar_producto ();
        form.setVisible(true);
        form.toFront();
        enviar_producto.txt_recibe.setText("10");
    }//GEN-LAST:event_itemBuscarArticuloActionPerformed

    private void txtCodigo_ArticuloMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCodigo_ArticuloMouseReleased
        
    }//GEN-LAST:event_txtCodigo_ArticuloMouseReleased

    private void txtCodigo_ArticuloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo_ArticuloKeyReleased
        
    }//GEN-LAST:event_txtCodigo_ArticuloKeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //BOTON PARA QUITAR EL ULTIMO ARTICULO INGRESADO
        Double subTotalBrutoArt=0.0;
        int Select=tabla1.getSelectedRow();
        
        if(Select>=0){
            try
            {


                DefaultTableModel modelo2=(DefaultTableModel)tabla1.getModel();    
                modelo2.removeRow(Select);

                int pe=0,fil = tabla1.getRowCount();
                double acum=0.0;
                for (int x=0;x<=fil-1;x++) {

                    Double unitarioBruto=Double.parseDouble(tabla1.getValueAt(x, 5).toString().replace(",","."))*Double.parseDouble(tabla1.getValueAt(x, 0).toString().replace(",","."));
                    acum=acum+unitarioBruto;
                    acum=Math.round(acum * 100.0)/ 100.0;
                    tabla1.setValueAt(acum,x,6);
                }
                int fila2 = tabla1.getRowCount();
                subtotal=0.0;
                for (int x=0;x<=fila2-1;x++) {
                    double totalsub=Double.parseDouble((tabla1.getValueAt(x,3).toString().replace(",", ".")))* Double.parseDouble((tabla1.getValueAt(x,0).toString())); //a total sub le multiplico la cantidad por el precio de la fila
                    double totalsubArticuloBruto=(Double.parseDouble(tabla1.getValueAt(x,5).toString().replace(",", "."))) * Double.parseDouble(tabla1.getValueAt(x,0).toString());
                    subTotalBrutoArt=subTotalBrutoArt + totalsubArticuloBruto;//sumo el total en bruto de todos los articulos que voy agregando
                            
                    subtotal=totalsub+subtotal;
                    tabla1.setValueAt(String.format("%.2f", totalsub).replace(".", ","),x,4);
                    tabla1.setValueAt(String.format("%.2f", subTotalBrutoArt).replace(".", ","),x,6);//precio total bruto de los articulos                         
                }  
                
                sub.setText(signo_moneda+" "+String.format("%.2f", subtotal));

                Double totals=subtotal;
                String totals1=String.format("%.2f", totals);
                        
                total.setText(signo_moneda+" "+totals1); //TOTAL MUESTRA EL SIGNO $ PERO EL QUE GUARDA EL VALOR PARA ALMACENARLO EN LA BD ES TOTAL2
                total2.setText(totals1);
                total3.setText(totals1);
                txtTotalBruto.setText(String.format("%.2f", subTotalBrutoArt));

                //modelo.removeRow(fila);
                fila-=1;
                int filat=tabla1.getRowCount();
                if (filat==0){
                    comboVendedor.setEnabled(true);
                    comboClientes.setEnabled(true);
                }
                        
            }catch(Exception e){
                    
            }

        //HAGO FOCO EN COD ART PARA INGRESAR OTRO ARTICULO
        txtCodigo_Articulo.requestFocus();
        }else{
             int filat=tabla1.getRowCount();
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
        if(txtCodigo_Articulo.getText().equals("")){
            JOptionPane.showMessageDialog(null,"PRIMERO SELECCIONE EL ARTICULO");
            txtCodigo_Articulo.requestFocus();
        }else{
            int i=0;
            try{
                Connection conn =conexion.ObtenerConexion();
                Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
                ResultSet r= consulta1.executeQuery("select * from articulo where cod_articulo='"+txtCodigo_Articulo.getText()+"'");
                
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
                 Editar_Articulo.recibeCliente.setText("4");
                 Editar_Articulo.cargarComboProveedores();
                 Editar_Articulo.CargarArticulo(txtCodigo_Articulo.getText());
                 form.toFront(); 
            }else{
                 JOptionPane.showMessageDialog(null,"El codigo ingresado no pertenece a ningun articulo del inventario");
                 txtCodigo_Articulo.requestFocus();
            }
        }
    }//GEN-LAST:event_btnEditarArticuloActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        //SON LOS ITEM QUE HACEN FUNCIONAR LOS ACCESOS RAPIDOS F3 F5 F7 ETC
        Configurar_Impresion form= new Configurar_Impresion ();
        form.setVisible(true);
        form.toFront();
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
            java.util.logging.Logger.getLogger(Factura_Remito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Factura_Remito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Factura_Remito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Factura_Remito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Factura_Remito().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu QuitarArticulo;
    private javax.swing.JButton botonAgregarArticulo;
    private javax.swing.JButton botonAgregarCliente;
    private javax.swing.JButton btnAgregarArticulo;
    private javax.swing.JButton btnBuscarArticulo;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarVendedor;
    private javax.swing.JButton btnCancelarRemito;
    private javax.swing.JButton btnEditarArticulo;
    private javax.swing.JButton btnGuardarRemito;
    private javax.swing.JButton btnQuitarArticulo;
    private com.toedter.calendar.JDateChooser calendario;
    private javax.swing.JTextField cant33;
    public static javax.swing.JComboBox comboArticulos;
    public static javax.swing.JComboBox comboClientes;
    public static javax.swing.JComboBox comboVendedor;
    private javax.swing.JTextField fact;
    private javax.swing.JMenuItem itemAgregarArticulo;
    private javax.swing.JMenuItem itemAgregarCliente;
    private javax.swing.JMenuItem itemBuscarArticulo;
    private javax.swing.JMenuItem itemBuscarCliente;
    private javax.swing.JMenuItem itemBuscarVendedor;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel25;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    public static javax.swing.JTextField sub;
    private javax.swing.JTable tabla1;
    public static javax.swing.JTextField total;
    private javax.swing.JTextField total2;
    private javax.swing.JTextField total3;
    public static javax.swing.JTextField txtCodigo_Articulo;
    public static javax.swing.JTextField txtCodigo_Cliente;
    public static javax.swing.JTextField txtCodigo_Empleado;
    public static javax.swing.JTextField txtPesoBascula;
    private javax.swing.JLabel txtRemitoNumero;
    private javax.swing.JTextField txtTotalBruto;
    // End of variables declaration//GEN-END:variables
}
