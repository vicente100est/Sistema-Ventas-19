
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


    public class Factura_Presupuesto extends javax.swing.JFrame {
        //VARIABLES GLOBALES
        String fecha="";
        Double subtotal=0.0;
        String codigoproveedor;
        String nomproveedor;
        String codigoproducto;
        String codigoproducto_e;
        double cant_ex=0.0;
        String precio;
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
             
    public Factura_Presupuesto() {    
        initComponents(); 
        this.setVisible(true);
        setLocationRelativeTo(null);
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        
        btnCancelarPresupuesto.setEnabled(false);
            
        //color cabecera de la tabla
        JTableHeader th; 
        th = tabla.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        inabilita();

        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            ResultSet  res= consulta.executeQuery("select * from articulo WHERE estado='ACTIVO' order by referencia");
            int i;
            //CARGO EL COMBO CON LOS ARTICULOS
            comboArticulos.removeAllItems();
            comboArticulos.addItem("SELECCIONE ARTICULO");
            i=0;
            while(res.next()){
                comboArticulos.addItem(res.getString(2));
                i++;
            }
            //cargo el combo con los clientes 
            comboClientes.removeAllItems();
            comboClientes.addItem("SELECCIONE CLIENTE");
            
            i=0;
            //EL PRIMER CLIENTE ES CONSUMIDOR FINAL, LUEGO CARGO LOS DEMAS
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
            String usuarioActivo= "",tipoUsuario="";
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
        nuevoPresupuesto();     
    }
    
             

    Factura_Presupuesto(Menu_Principal aThis, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void nuevoPresupuesto(){
        //INICIALIZO LOS COMPONENTES
        comboClientes.setSelectedIndex(1);
        comboArticulos.setSelectedIndex(0);
        cant.setText("");
        fact.setText("");
        sub.setText("");
        total.setText("");
        btnCancelarPresupuesto.setEnabled(false);
        habilitar();
        
        tabla.getColumnModel().getColumn(1).setMaxWidth(450);
        tabla.getColumnModel().getColumn(1).setMinWidth(450);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(450);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(450);
        
        int fil = tabla.getRowCount();
        int x;
        //RESETEO LA TABLA
        for (x=fil-1;x>=0;x--) {
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            modelo.removeRow(x);
        }
        try{
            //ESTO ES PARA SABER QUE NUMERO DE PRESUPUESTO ES
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            ResultSet r= consulta.executeQuery("select cod_presupuesto from  presupuesto order by cod_presupuesto desc");
            r.next(); 
            fact.setText(r.getString(1));
            int f;
            f=Integer.parseInt(fact.getText())+1;
            fact.setText(String.valueOf(f));
            r.close();
            ResultSet r2= consulta.executeQuery("select signo_moneda from  monedas where seleccion_moneda like 'SELECCIONADA'");
            while(r2.next()){
                signo_moneda=r2.getString(1);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
            System.out.println(e);
        }    
        txtPresupuestoNumero.setText("PRESUPUESTO N° "+fact.getText());
 }
    
    public void inabilita(){
        //INHABILITO LOS COMPONENTES
        setearFecha();
        calendario.setEnabled(false);
        
        comboVendedor.setEnabled(false);
        comboClientes.setEnabled(false);
        comboArticulos.setEnabled(false);
        
        btnBuscarVendedor.setEnabled(false);
        btnBuscarCliente.setEnabled(false);
        btnBuscarArticulo.setEnabled(false);
        cant.setEditable(false);
        
        btnAgregarArticulo.setEnabled(false);
        btnQuitarArticulo.setEnabled(false);
        btnCancelarPresupuesto.setEnabled(false);
        btnGuardarPresupuesto.setEnabled(false);


        sub.setEnabled(false);
        total.setEnabled(false);
        total2.setVisible(false);

        txtPresupuestoNumero.setText("PRESUPUESTO");

        fact.setVisible(false);
        txtCod_Cliente.setVisible(false);
        txtCod_Cliente.setText("0");
        txtCod_Empleado.setVisible(false);
        txtCodigoArticulo.setVisible(true);
    }
    
    public void habilitar(){
        //HABILITO LOS COMPONENTES
        calendario.setEnabled(true);
        comboVendedor.setEnabled(true);
        comboClientes.setEnabled(true);
        comboArticulos.setEnabled(true);
        
        btnBuscarVendedor.setEnabled(true);
        btnBuscarCliente.setEnabled(true);
        btnBuscarArticulo.setEnabled(true);
        cant.setEditable(true);
        
        btnAgregarArticulo.setEnabled(true);
        btnQuitarArticulo.setEnabled(true);
        btnCancelarPresupuesto.setEnabled(true);

        btnGuardarPresupuesto.setEnabled(true);
        sub.setEnabled(true);
        total.setEnabled(true);
        fact.setVisible(false);
        
    }
    
    public void setearFecha() { 
        //TRAIGO A FECHA ACTUAL
        Calendar c2 = new GregorianCalendar();
        calendario.setCalendar(c2);    
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        QuitarArticulo = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        comboArticulos = new javax.swing.JComboBox();
        btnBuscarArticulo = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cant = new javax.swing.JTextField();
        btnAgregarArticulo = new javax.swing.JButton();
        btnQuitarArticulo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        sub = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        comboClientes = new javax.swing.JComboBox();
        btnBuscarCliente = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        comboVendedor = new javax.swing.JComboBox();
        btnBuscarVendedor = new javax.swing.JButton();
        botonAgregarCliente = new javax.swing.JButton();
        total2 = new javax.swing.JTextField();
        btnCancelarPresupuesto = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnGuardarPresupuesto = new javax.swing.JButton();
        botonAgregarArticulo = new javax.swing.JButton();
        txtCod_Empleado = new javax.swing.JTextField();
        txtCodigoArticulo = new javax.swing.JTextField();
        txtCod_Cliente = new javax.swing.JTextField();
        fact = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnEditarArticulo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtPresupuestoNumero = new javax.swing.JLabel();
        calendario = new com.toedter.calendar.JDateChooser();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemBuscarVendedor = new javax.swing.JMenuItem();
        itemBuscarCliente = new javax.swing.JMenuItem();
        itemAgregarCliente = new javax.swing.JMenuItem();
        itemBuscarArticulo = new javax.swing.JMenuItem();
        itemAgregarArticulo = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem2.setText(" QUITAR ARTICULO ");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        QuitarArticulo.add(jMenuItem2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Registrar presupuesto");
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
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 80, 40));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);

        jLabel9.setBackground(new java.awt.Color(239, 255, 239));
        jLabel9.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel9.setText("Cod Art");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(570, 80, 80, 40);

        comboArticulos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboArticulosActionPerformed(evt);
            }
        });
        jPanel1.add(comboArticulos);
        comboArticulos.setBounds(110, 80, 300, 40);

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
        jPanel1.add(btnBuscarArticulo);
        btnBuscarArticulo.setBounds(420, 80, 60, 39);

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel6.setText("Cantidad");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(10, 140, 97, 40);

        cant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cant.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        cant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantActionPerformed(evt);
            }
        });
        cant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cantKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantKeyTyped(evt);
            }
        });
        jPanel1.add(cant);
        cant.setBounds(110, 140, 120, 40);

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
        jPanel1.add(btnAgregarArticulo);
        btnAgregarArticulo.setBounds(240, 140, 150, 38);

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
        jPanel1.add(btnQuitarArticulo);
        btnQuitarArticulo.setBounds(400, 140, 140, 38);

        tabla.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CANTIDAD", "DESCRIPCION", "V. UNITARIO", "V. TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, false
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
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 210, 990, 160);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel11.setText("SubTotal");

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel13.setText("TOTAL");

        total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        total.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        sub.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sub.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(28, 28, 28)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(total, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(sub))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sub, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(680, 400, 320, 150);

        jLabel7.setBackground(new java.awt.Color(239, 255, 239));
        jLabel7.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel7.setText("Cliente");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(30, 20, 80, 33);

        comboClientes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClientesActionPerformed(evt);
            }
        });
        jPanel1.add(comboClientes);
        comboClientes.setBounds(110, 20, 300, 40);

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
        jPanel1.add(btnBuscarCliente);
        btnBuscarCliente.setBounds(420, 20, 60, 39);

        jLabel5.setBackground(new java.awt.Color(239, 255, 239));
        jLabel5.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel5.setText("Vendedor");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(550, 20, 110, 40);

        comboVendedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboVendedorActionPerformed(evt);
            }
        });
        jPanel1.add(comboVendedor);
        comboVendedor.setBounds(660, 20, 270, 40);

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
        jPanel1.add(btnBuscarVendedor);
        btnBuscarVendedor.setBounds(940, 20, 50, 38);

        botonAgregarCliente.setBackground(new java.awt.Color(93, 116, 163));
        botonAgregarCliente.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        botonAgregarCliente.setForeground(new java.awt.Color(255, 255, 255));
        botonAgregarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoAgregarF4.png"))); // NOI18N
        botonAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(botonAgregarCliente);
        botonAgregarCliente.setBounds(490, 20, 50, 39);

        total2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total2ActionPerformed(evt);
            }
        });
        jPanel1.add(total2);
        total2.setBounds(600, 490, 50, 40);

        btnCancelarPresupuesto.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelarPresupuesto.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnCancelarPresupuesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelar.png"))); // NOI18N
        btnCancelarPresupuesto.setBorder(null);
        btnCancelarPresupuesto.setBorderPainted(false);
        btnCancelarPresupuesto.setContentAreaFilled(false);
        btnCancelarPresupuesto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarPresupuesto.setFocusCycleRoot(true);
        btnCancelarPresupuesto.setFocusable(false);
        btnCancelarPresupuesto.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarPresupuesto.setRequestFocusEnabled(false);
        btnCancelarPresupuesto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarPresupuesto.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnCancelarHover.png"))); // NOI18N
        btnCancelarPresupuesto.setVerifyInputWhenFocusTarget(false);
        btnCancelarPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPresupuestoActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelarPresupuesto);
        btnCancelarPresupuesto.setBounds(180, 370, 107, 160);

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel19.setText("Cancelar");
        jPanel1.add(jLabel19);
        jLabel19.setBounds(190, 510, 93, 33);

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel17.setText("Guardar");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(60, 510, 89, 33);

        btnGuardarPresupuesto.setBackground(new java.awt.Color(51, 153, 255));
        btnGuardarPresupuesto.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnGuardarPresupuesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardar.png"))); // NOI18N
        btnGuardarPresupuesto.setBorder(null);
        btnGuardarPresupuesto.setBorderPainted(false);
        btnGuardarPresupuesto.setContentAreaFilled(false);
        btnGuardarPresupuesto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarPresupuesto.setFocusCycleRoot(true);
        btnGuardarPresupuesto.setFocusable(false);
        btnGuardarPresupuesto.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btnGuardarPresupuesto.setRequestFocusEnabled(false);
        btnGuardarPresupuesto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btnGuardarPresupuesto.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnGuardarHover.png"))); // NOI18N
        btnGuardarPresupuesto.setVerifyInputWhenFocusTarget(false);
        btnGuardarPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPresupuestoActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardarPresupuesto);
        btnGuardarPresupuesto.setBounds(0, 370, 210, 160);

        botonAgregarArticulo.setBackground(new java.awt.Color(93, 116, 163));
        botonAgregarArticulo.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        botonAgregarArticulo.setForeground(new java.awt.Color(255, 255, 255));
        botonAgregarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoAgregarF6.png"))); // NOI18N
        botonAgregarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarArticuloActionPerformed(evt);
            }
        });
        jPanel1.add(botonAgregarArticulo);
        botonAgregarArticulo.setBounds(490, 80, 50, 40);

        txtCod_Empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCod_EmpleadoActionPerformed(evt);
            }
        });
        jPanel1.add(txtCod_Empleado);
        txtCod_Empleado.setBounds(540, 490, 50, 40);

        txtCodigoArticulo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtCodigoArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoArticuloActionPerformed(evt);
            }
        });
        jPanel1.add(txtCodigoArticulo);
        txtCodigoArticulo.setBounds(660, 80, 210, 40);

        txtCod_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCod_ClienteActionPerformed(evt);
            }
        });
        jPanel1.add(txtCod_Cliente);
        txtCod_Cliente.setBounds(540, 440, 50, 40);

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
        jPanel1.add(fact);
        fact.setBounds(600, 430, 50, 40);

        jLabel10.setBackground(new java.awt.Color(239, 255, 239));
        jLabel10.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel10.setText("Articulo");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(20, 70, 90, 50);

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
        jPanel1.add(btnEditarArticulo);
        btnEditarArticulo.setBounds(880, 80, 110, 40);

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1010, 590));

        jPanel2.setOpaque(false);

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N

        txtPresupuestoNumero.setFont(new java.awt.Font("Calibri", 1, 33)); // NOI18N
        txtPresupuestoNumero.setText("PRESUPUESTO ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPresupuestoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPresupuestoNumero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 70));

        calendario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel4.add(calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 210, 40));

        panelImage1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 680));

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        itemBuscarVendedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itemBuscarVendedor.setText("Buscar vendedor");
        itemBuscarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarVendedorActionPerformed(evt);
            }
        });
        jMenu1.add(itemBuscarVendedor);

        itemBuscarCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        itemBuscarCliente.setText("Buscar ciente");
        itemBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarClienteActionPerformed(evt);
            }
        });
        jMenu1.add(itemBuscarCliente);

        itemAgregarCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        itemAgregarCliente.setText("Agregar cliente");
        itemAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarClienteActionPerformed(evt);
            }
        });
        jMenu1.add(itemAgregarCliente);

        itemBuscarArticulo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        itemBuscarArticulo.setText("Buscar articulo");
        itemBuscarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarArticuloActionPerformed(evt);
            }
        });
        jMenu1.add(itemBuscarArticulo);

        itemAgregarArticulo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        itemAgregarArticulo.setText("Agregar articulo");
        itemAgregarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarArticuloActionPerformed(evt);
            }
        });
        jMenu1.add(itemAgregarArticulo);

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
        //SI SELECCIONO EL VENDEDOR DESDE EL COMBOBOX , BUSCO SU CODIGO EN LA BD Y LO GUARDO EN UN TEXTFIELD PARA LUEO INGRESARLO EN LE PRESUPUETO
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            nomempleado = (String)comboVendedor.getSelectedItem();
            cod_empleado=null;
            String cadena=comboVendedor.getSelectedItem().toString();
            int i=0;
            //ES PARA DIVIDIR EL NOMBRE Y EL APELLIDO QUE EN EL COMBO ESTAN JUNTOS PERO EN LA BD ESTAN SEPARADOS
            //CREO UNA SUBCADENA CON EL NOMBRE Y OTRACON EL APELLIDO
            while(cadena.charAt(i)!=' ') { 
               i++;
            }  
            String SubCadenaNombreEmpleado = cadena.substring(0,i);
            String SubCadenaApellidoEmpleado = cadena.substring(i+1,cadena.length());
            
            //TRAIGO EL CODIGO DEL VENDEDOR
            ResultSet rs = consulta.executeQuery("SELECT cod_empleado FROM empleado WHERE (nombres = '"+SubCadenaNombreEmpleado+"' AND apellidos = '"+SubCadenaApellidoEmpleado+"')");
            while (rs.next()) {
                cod_empleado= rs.getString(1);
            }
            txtCod_Empleado.setText(cod_empleado);
            conn.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
        
        //RESTRICCIONES QUE NO DESHABILTAN EL INGRESO DE ARTICULOS SI NO HAY SELECCIONADO UN CLIENTE Y UN VENDEDOR
        
    }//GEN-LAST:event_comboVendedorActionPerformed

    private void btnBuscarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVendedorActionPerformed
        //BUSCO EL FORM QUE MUESTRA LOS VENDEDORES
        enviar_empleado form= new enviar_empleado ();
        form.setVisible(true);
        form.toFront();
        enviar_empleado.txt_recibeEmpleado.setText("14");
    }//GEN-LAST:event_btnBuscarVendedorActionPerformed

    private void comboClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClientesActionPerformed
        //SI SELECCIONO EL CLIENTE DESDE EL COMBOBOX , BUSCO SU CODIGO EN LA BD Y LO GUARDO EN UN TEXTFIELD PARA LUEO INGRESARLO EN LE PRESUPUETO
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            nomcliente = (String)comboClientes.getSelectedItem();
            codigocliente=null;
            ResultSet rs = consulta.executeQuery("SELECT cod_cliente FROM cliente WHERE nombres = '"+nomcliente+"'");
            while (rs.next()) {
                codigocliente= rs.getString(1);
              
            }
            txtCod_Cliente.setText(codigocliente);
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
        
        //RESTRICCIONES QUE NO DESHABILTAN EL INGRESO DE ARTICULOS SI NO HAY SELECCIONADO UN CLIENTE Y UN VENDEDOR
        
        
        
    }//GEN-LAST:event_comboClientesActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        //LLAMO AL FORM DE LOS CLIENTES
        enviar_cliente form= new enviar_cliente ();
        form.setVisible(true);
        form.toFront();
        enviar_cliente.recibe.setText("15");
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void comboArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboArticulosActionPerformed
        //SI SELECCIONO EL ARTICULO DESDE EL COMBOBOX , BUSCO SU CODIGO EN LA BD Y LO GUARDO EN UN TEXTFIELD PARA LUEO INGRESARLO EN LE PRESUPUETO
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            nomproducto = (String)comboArticulos.getSelectedItem();
            codigoproducto=null;

            ResultSet rs = consulta.executeQuery("SELECT cod_articulo FROM articulo WHERE (referencia = '"+comboArticulos.getSelectedItem()+"')");
            while (rs.next()) {
                codigoproducto= rs.getString(1);
            }
            txtCodigoArticulo.setText(codigoproducto);
            conn.close();  // Cierra la conexión

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
        cant.requestFocus();
    }//GEN-LAST:event_comboArticulosActionPerformed

    private void btnBuscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarArticuloActionPerformed
        //LLAMO AL FORM QUEME MUESTRA LOS PRODUCTOS
        enviar_producto form= new enviar_producto ();
        form.setVisible(true);
        form.toFront();
        enviar_producto.txt_recibe.setText("13");
    }//GEN-LAST:event_btnBuscarArticuloActionPerformed

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

    private void btnAgregarArticuloMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarArticuloMouseEntered

    }//GEN-LAST:event_btnAgregarArticuloMouseEntered

    private void btnAgregarArticuloMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarArticuloMouseExited

    }//GEN-LAST:event_btnAgregarArticuloMouseExited
    public void agregarArticulo(){
        //AGREGO LOS ARTICULOS A LA TABLA
        int pe=0,fil = tabla.getRowCount();
        //ME FIJO SI EL ARTICULO YA FUE INGRESADO EN LA TABLA
        for (int x=0;x<=fil-1;x++) {
            String aux= (String) (tabla.getValueAt(x,1));
            if(aux.equals(comboArticulos.getSelectedItem()))
            {
                pe=1;
            }
        }
        //SI FUE INGRESADO LE DIGO QUE MODIFIQUE LA CANTIDAD
        if(pe==1)
        {
            JOptionPane.showMessageDialog(null,"Este Articulo ye esta ingresado en el presupuesto, modifique su cantidad en la tabla") ;
        }else{
            if (comboArticulos.getSelectedItem().equals("SELECCIONE ARTICULO"))
            {
                JOptionPane.showMessageDialog(null, "Falta elegir el articulo");
            }else
            {
                if (cant.getText().equals("")||cant.getText().equals("0")||cant.getText().equals("0.0"))
                {
                    JOptionPane.showMessageDialog(null, "Debe Digitar la Cantidad de articulo a ingresar");
                    cant.requestFocus();
                }else{
                    try{
                        Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                        Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                        ResultSet   res= consulta.executeQuery("select cantidad from articulo where cod_articulo = '"+txtCodigoArticulo.getText()+"'");
                        while(res.next()){
                            cant_ex=Double.parseDouble(res.getString(1));
                            break;
                        }
                        res.close();
                    }catch(SQLException e){
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null,"Error en el SQL") ;
                    }
                    //si la cantidad que desea ingresar es menor que el stock disponible muestro un mensaje y seteo cant con la cantidad disponible
                    double cantidad=Double.parseDouble(cant.getText().replace(',','.'));
                    if (cant_ex < cantidad)
                    {
                        JOptionPane.showMessageDialog(null, "¡ATENCION! La Cantidad Disponible de Dicho Articulo es "+cant_ex+" unidades");
                    }
                    
                    fila = tabla.getRowCount();
                    columna = tabla.getColumnCount();
                    DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                    //AGREGO EL ARTICULO A LA TABLA
                    modelo.addRow( new Object [] {null,null,null,null,null});
                    tabla.setValueAt(cantidad,fila,0);
                    tabla.setValueAt(nomproducto,fila,1);

                    try{
                        Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                        Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                        
                        //TRAIGO EL PRECIO DEL ARTICULO DE LA BD
                        ResultSet r= consulta.executeQuery("select valor from articulo where cod_articulo= '"+txtCodigoArticulo.getText()+"'");
                        while(r.next()){
                            precio=r.getString(1);
                        }
                    }catch(SQLException e){
                        JOptionPane.showMessageDialog(null,"Este Articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
                    } 
                        
                    tabla.setValueAt(precio,fila,2);//VALOR UNITARIO
                         
                    int fila2 = tabla.getRowCount();
                    subtotal=0.0;
                    //REALIZO LOS CALCULOS PRA SUMAR EL TOTAL Y SUBTOTAL GENERAL
                    for (int x=0;x<=fila2-1;x++) {
                        Double totalsub=Double.parseDouble((tabla.getValueAt(x,2).toString().replace(",", ".")))*Double.parseDouble((tabla.getValueAt(x,0).toString())); //a total sub le multiplico la cantidad por el precio de la fila
                        subtotal=totalsub+subtotal;
                        tabla.setValueAt(String.format("%.2f",totalsub),x,3);         
                    }
                     
                   //SETEO LOS TEXTFIELD SUBTOTAL Y TOTAL 
                    sub.setText(signo_moneda+String.format("%.2f",subtotal).replace(".", ","));

                    Double totals=subtotal;
                    String totals2=String.format("%.2f",subtotal).replace(".", ",");
                    total.setText(signo_moneda+totals2);
                    total2.setText(""+totals2);
                    
                    //REINICIO EL COMBO ARTICULO Y LA ULTIMA CANTIDAD INGRESADA
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
        //ELIMINO EL ULTIMO ARTICULO INGRESADO DE LA TABLA
        int Select=tabla.getSelectedRow();
        
        if(Select>=0){
            try
            {

                DefaultTableModel modelo2=(DefaultTableModel)tabla.getModel();    
                modelo2.removeRow(Select);
                
                int fila2 = tabla.getRowCount();
                subtotal=0.0;
                //REALIZO LOS CALCULOS PRA SUMAR EL TOTAL Y SUBTOTAL GENERAL
                for (int x=0;x<=fila2-1;x++) {
                    Double totalsub=Double.parseDouble((tabla.getValueAt(x,2).toString().replace(",", ".")))*Double.parseDouble((tabla.getValueAt(x,0).toString())); //a total sub le multiplico la cantidad por el precio de la fila
                    subtotal=totalsub+subtotal;
                     tabla.setValueAt(String.format("%.2f",totalsub),x,3);         
                 }
                     
                //SETEO LOS TEXTFIELD SUBTOTAL Y TOTAL 
                sub.setText(signo_moneda+String.format("%.2f",subtotal).replace(".", ","));

                Double totals=subtotal;
                String totals2=String.format("%.2f",subtotal).replace(".", ",");
                total.setText(signo_moneda+totals2);
                total2.setText(""+totals2);

                fila-=1;
                int filat=tabla.getRowCount();
                if (filat==0){
                    comboVendedor.setEnabled(true);
                    comboClientes.setEnabled(true);
                }
            }catch (ArrayIndexOutOfBoundsException e) {
                //JOptionPane.showMessageDialog(null, "NO HAY PRODUCTOS PARA QUITAR"); 
                System.out.println(e);
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

    private void btnBuscarClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarClienteKeyTyped

    }//GEN-LAST:event_btnBuscarClienteKeyTyped

    private void btnBuscarArticuloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarArticuloKeyTyped

    }//GEN-LAST:event_btnBuscarArticuloKeyTyped

    private void botonAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarClienteActionPerformed
        //LLAMO AL FORM PARA AGREGAR UN CLIENTE
        Agregar_Cliente form= new  Agregar_Cliente ();
        form.setVisible(true);
        Agregar_Cliente.recibeCliente.setText("5");
        form.toFront();
    }//GEN-LAST:event_botonAgregarClienteActionPerformed
    public void modificarArticuloFactura(){
        //CUANDO EDITO LA CANTIDAD O EL PRECIO UNITARIO DENTRO DE LA TABLA
        int Select=tabla.getSelectedRow(), bandera=0;      
        int fila2 = tabla.getRowCount();
        subtotal=0.0;
        for (int x=0;x<=fila2-1;x++) {
            try{
                Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                ResultSet   res= consulta.executeQuery("select cantidad from articulo where referencia = '"+tabla.getValueAt(Select,1).toString()+"'");
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
            }
            //REALIZO LOS CALCULOS PARA ACTUALIZAR LOS NUEVOS VALORES
            Double totalsub=Double.parseDouble((tabla.getValueAt(x,2).toString().replace(",", ".")))*Double.parseDouble((tabla.getValueAt(x,0).toString())); //a total sub le multiplico la cantidad por el precio de la fila
            subtotal=totalsub+subtotal;
            tabla.setValueAt(String.format("%.2f", totalsub).replace(".", ","),x,3);        
        }
        //SETEO LOS TEXTFIELD SUBTOTAL Y TOTAL CON LOS NUEVOS VALORES               
        sub.setText(signo_moneda+String.format("%.2f",subtotal).replace(".", ","));

        Double totals=subtotal;
        String totals2=String.format("%.2f",totals).replace(".", ",");
        total.setText(signo_moneda+totals2);
        total2.setText(totals2);

        comboArticulos.setSelectedIndex(0);
        cant.setText("");
    }
    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        modificarArticuloFactura();
    }//GEN-LAST:event_tablaMouseClicked

    private void tablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyPressed
        
    }//GEN-LAST:event_tablaKeyPressed

    private void tablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyReleased
       modificarArticuloFactura();
    }//GEN-LAST:event_tablaKeyReleased

    private void cantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantActionPerformed
        agregarArticulo();
    }//GEN-LAST:event_cantActionPerformed

    private void cantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cantKeyReleased

    private void total2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total2ActionPerformed

    private void txtCod_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCod_ClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCod_ClienteActionPerformed

    private void txtCod_EmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCod_EmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCod_EmpleadoActionPerformed

    private void btnCancelarPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPresupuestoActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarPresupuestoActionPerformed

    private void btnGuardarPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPresupuestoActionPerformed
        //GUARDO ELPRESUPUESTO ENLA BD
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
                            if ( tabla.getRowCount() <= 0 ) {
                                JOptionPane.showMessageDialog(null, "No Hay Ningun Producto a Facturar","Advertencia",JOptionPane.ERROR_MESSAGE);
                            }else {
                                try{
                                    Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                                    Statement consulta=conn.createStatement();
                                    Statement consulta1=conn.createStatement();
                                    int fil = tabla.getRowCount();
                                    int col = tabla.getColumnCount();
                                    
                                    //TRAIGO LA FECHA INGRESADA EN EL PRESUPUESTO
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

                                    int x,y;
                                    //INSERTO LOS DATOS EN LA TABLA PRESUPUESTO EN LA BD
                                    consulta.executeUpdate("insert into presupuesto (cod_presupuesto, fecha,cod_cliente,cod_empleado) values('"+fact.getText()+"','"+fecha+"','"+txtCod_Cliente.getText()+"','"+txtCod_Empleado.getText()+"')");
                                    //INSERTO CADA ARTICULO EN REFERENCIA_REMITO
                                    for (x=0;x<=fil-1;x++) {
                                        consulta1.executeUpdate("insert into referencia_presupuesto (cod_presupuesto,valor_unitario,valor_total,referencia,cantidad,Total) values('"+fact.getText()+"','"+tabla.getValueAt(x,2)+"','"+tabla.getValueAt(x,3).toString().replace(",",".")+"','"+tabla.getValueAt(x,1)+"','"+tabla.getValueAt(x,0)+"','"+total2.getText().replace(",",".")+"')");
                                    }
                                    inabilita();
                                    //RESETEO LOS COMBOBOX
                                    comboClientes.setSelectedIndex(0);
                                    comboArticulos.setSelectedIndex(0);
                                    sub.setText("");

                                    total.setText("");
                                    cant.setText("");
                                    
                                   Connection miconexion = conexion.ObtenerConexion();
                                   Map parametros = new HashMap();
                                   parametros.put("codf",fact.getText());
                                    
                                   //MUESTRO EL REPORTE
                                    int op=JOptionPane.showConfirmDialog(null, "PRESUPUESTO AGREGADO ¿DESEA IMPRIMIRLO?","SELECCIONE UN OPCION" ,JOptionPane.YES_NO_OPTION);
                                    if (op==JOptionPane.YES_NO_OPTION){
                                        try {
                                            this.setVisible(false);
                                            String reporte="presupuestos.jasper";
                                            JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                                            JasperViewer ventanavisor=new JasperViewer(informe,false);
                                            ventanavisor.setTitle("Reporte de presupuesto");
                                            ventanavisor.setVisible(true);
                                        } catch (Exception e) {
                                            JOptionPane.showMessageDialog(this, e.getMessage());
                                        }
                                    }else{
                                        // no hacer nada
                                    }          
                                    //RESETEO LA TABLA ENVACIO
                                    for (x=0;x<=fil-1;x++) {
                                        for (y=0;y<=col-1;y++) {
                                            tabla.setValueAt("",x,y);
                                        }
                                    }
                                    //BORRAO LAS FILAS
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
            //PASO AL PRESUPUESTO SIGUIENTE
            nuevoPresupuesto(); 
            }
        }catch (ArrayIndexOutOfBoundsException e) {
            
        }
    }//GEN-LAST:event_btnGuardarPresupuestoActionPerformed

    private void botonAgregarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarArticuloActionPerformed
        //LLAMO AL FORM PARA AGREGAR UN CLIENTE
        Agregar_Articulo_Ventas form= new Agregar_Articulo_Ventas();
        form.setVisible(true);
        Agregar_Articulo_Ventas.recibeCliente.setText("5");
        form.toFront();
    }//GEN-LAST:event_botonAgregarArticuloActionPerformed

    private void itemAgregarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAgregarArticuloActionPerformed
        //ESTOS ITEMS SON PARA AGREGARLOS ACCESOS RAPIDOS F3 F5 ECT
        Agregar_Articulo_Ventas form= new Agregar_Articulo_Ventas ();
        form.setVisible(true);
        Agregar_Articulo_Ventas.recibeCliente.setText("5");
        form.toFront();
    }//GEN-LAST:event_itemAgregarArticuloActionPerformed

    private void itemBuscarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarVendedorActionPerformed
        //ESTOS ITEMS SON PARA AGREGARLOS ACCESOS RAPIDOS F3 F5 ECT
        enviar_empleado form= new enviar_empleado ();
        form.setVisible(true);
        form.toFront();
        enviar_empleado.txt_recibeEmpleado.setText("14");
    }//GEN-LAST:event_itemBuscarVendedorActionPerformed

    private void itemBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarClienteActionPerformed
        //ESTOS ITEMS SON PARA AGREGARLOS ACCESOS RAPIDOS F3 F5 ECT
        enviar_cliente form= new enviar_cliente ();
        form.setVisible(true);
        form.toFront();
        enviar_cliente.recibe.setText("15");
    }//GEN-LAST:event_itemBuscarClienteActionPerformed

    private void itemAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAgregarClienteActionPerformed
        //ESTOS ITEMS SON PARA AGREGARLOS ACCESOS RAPIDOS F3 F5 ECT
        Agregar_Cliente form= new  Agregar_Cliente ();
        form.setVisible(true);
        Agregar_Cliente.recibeCliente.setText("5");
        form.toFront();
    }//GEN-LAST:event_itemAgregarClienteActionPerformed

    private void itemBuscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarArticuloActionPerformed
        //ESTOS ITEMS SON PARA AGREGARLOS ACCESOS RAPIDOS F3 F5 ECT
        enviar_producto form= new enviar_producto ();
        form.setVisible(true);
        form.toFront();
        enviar_producto.txt_recibe.setText("13");
    }//GEN-LAST:event_itemBuscarArticuloActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        //ELIMINO EL ULTIMO ARTICULO INGRESADO DE LA TABLA
        int Select=tabla.getSelectedRow();
        
        if(Select>=0){
            try
            {

                DefaultTableModel modelo2=(DefaultTableModel)tabla.getModel();    
                modelo2.removeRow(Select);
                
                int fila2 = tabla.getRowCount();
                subtotal=0.0;
                //REALIZO LOS CALCULOS PRA SUMAR EL TOTAL Y SUBTOTAL GENERAL
                for (int x=0;x<=fila2-1;x++) {
                    Double totalsub=Double.parseDouble((tabla.getValueAt(x,2).toString().replace(",", ".")))*Double.parseDouble((tabla.getValueAt(x,0).toString())); //a total sub le multiplico la cantidad por el precio de la fila
                    subtotal=totalsub+subtotal;
                     tabla.setValueAt(String.format("%.2f",totalsub),x,3);         
                 }
                     
                //SETEO LOS TEXTFIELD SUBTOTAL Y TOTAL 
                sub.setText(signo_moneda+String.format("%.2f",subtotal).replace(".", ","));

                Double totals=subtotal;
                String totals2=String.format("%.2f",subtotal).replace(".", ",");
                total.setText(signo_moneda+totals2);
                total2.setText(""+totals2);

                fila-=1;
                int filat=tabla.getRowCount();
                if (filat==0){
                    comboVendedor.setEnabled(true);
                    comboClientes.setEnabled(true);
                }
            }catch (ArrayIndexOutOfBoundsException e) {
                //JOptionPane.showMessageDialog(null, "NO HAY PRODUCTOS PARA QUITAR"); 
                System.out.println(e);
            }
        }else{
            int filat=tabla.getRowCount();
            if(filat==0){
                JOptionPane.showMessageDialog(null, "NO HAY PRODUCTOS PARA QUITAR"); 
            }else{
                JOptionPane.showMessageDialog(null, "SELECCIONE LA FILA QUE DESEA QUITAR");   
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnEditarArticuloMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarArticuloMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarArticuloMouseEntered

    private void btnEditarArticuloMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarArticuloMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarArticuloMouseExited

    private void btnEditarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarArticuloActionPerformed
        
        if(txtCodigoArticulo.getText().equals("")){
            JOptionPane.showMessageDialog(null,"PRIMERO SELECCIONE EL ARTICULO");
            txtCodigoArticulo.requestFocus();
        }else{
            int i=0;
            try{
                Connection conn =conexion.ObtenerConexion();
                Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
                ResultSet r= consulta1.executeQuery("select * from articulo where cod_articulo='"+txtCodigoArticulo.getText()+"'");
                
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
                 Editar_Articulo.recibeCliente.setText("5");
                 Editar_Articulo.cargarComboProveedores();
                 Editar_Articulo.CargarArticulo(txtCodigoArticulo.getText());
                 form.toFront(); 
            }else{
                 JOptionPane.showMessageDialog(null,"El codigo ingresado no pertenece a ningun articulo del inventario");
                 txtCodigoArticulo.requestFocus();
            }
        }            
        
    }//GEN-LAST:event_btnEditarArticuloActionPerformed

    private void txtCodigoArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoArticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoArticuloActionPerformed
   
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
            java.util.logging.Logger.getLogger(Factura_Presupuesto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Factura_Presupuesto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Factura_Presupuesto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Factura_Presupuesto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Factura_Presupuesto().setVisible(true);
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
    private javax.swing.JButton btnCancelarPresupuesto;
    private javax.swing.JButton btnEditarArticulo;
    private javax.swing.JButton btnGuardarPresupuesto;
    private javax.swing.JButton btnQuitarArticulo;
    private com.toedter.calendar.JDateChooser calendario;
    private javax.swing.JTextField cant;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    public static javax.swing.JTextField sub;
    private javax.swing.JTable tabla;
    public static javax.swing.JTextField total;
    private javax.swing.JTextField total2;
    public static javax.swing.JTextField txtCod_Cliente;
    public static javax.swing.JTextField txtCod_Empleado;
    public static javax.swing.JTextField txtCodigoArticulo;
    private javax.swing.JLabel txtPresupuestoNumero;
    // End of variables declaration//GEN-END:variables
}
