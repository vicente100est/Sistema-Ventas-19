
package Vistas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

public class Menu_Principal extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    
    public Menu_Principal() {
        initComponents(); 
        calendario.setVisible(false);
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
            }catch(Exception e){
                                                                
        }
        try{
            Connection conn =conexion.ObtenerConexion();
            Statement consulta2=conn.createStatement(); // crea una variable que se encargue del código de sql            
            ResultSet r2= consulta2.executeQuery("select nombre_caja from cajas where estado='ABIERTA' order by cod_caja");
            
            int i=0;
            while(r2.next()){
                if(i==0){
                    i++;
                }
            }
            r2.close();
                                                                 
            if(i>0){
                labelCajaCerrada.setVisible(false);
                btnAbrirCaja.setVisible(false);
            }else{
                labelCajaCerrada.setVisible(true);
                btnAbrirCaja.setVisible(true);
            }
        }catch(Exception e){
                                                                
        }
        
        //PARA CAMBIARLE EL FONDO Y FUENTE A TODOS LOS JOPTIONPANE
        UIManager UI=new UIManager(); 
        
        UI.put("OptionPane.background",new ColorUIResource(255,255,255));
        UI.put("Panel.background",new ColorUIResource(255,255,255)); 
        UI.put("Button.background",new Color(93,116,163)); 
        UI.put("Button.foreground",Color.white);
        UI.put("OptionPane.font", new Font("Tahoma", Font.PLAIN, 22));
        UI.put("Button.font", new Font("Tahoma", Font.BOLD, 22));
        UI.put("OptionPane.messageFont", new FontUIResource(new Font(  
          "Tahoma", Font.PLAIN, 22)));  
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setSize(pantalla.width, pantalla.height);
        M_conectar.setEnabled(false); 
        
         //Tomo el tamaño de la pantalla  PARA ACOMODAR EL PANEL CON LOSICONOS DEL MENU
        Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize(); 
        //Al ancho de la pantalla lo divido en 2 y le resto la mitad del ancho de mi ventana, con eso queda centrada en el eje X, para el eje Y es lo mismo pero con el alto: 
        panelIconosMenu.setLocation((pantallaTamano.width/2)-(panelIconosMenu.getWidth()/2), (pantallaTamano.height/2)-(panelIconosMenu.getHeight()/2)-100); //-100 ES PARA QUE ME QUEDE BIEN CENTRADO EN LA PANTALLA
        panelIconosMenu.setOpaque(false); //LE PONGO EL BACKGROUND TRANSPARENTE
        
        this.setTitle("Sistema Contable Administrativo Universal Software ®");
        conectado.setVisible(true);
        labelFecha.setLocation(20,pantallaTamano.height-200);
        conectado.setLocation(35,pantallaTamano.height-230);
        lb_tipo.setVisible(false);
    }
    
    
    
 
 
    public void setearFecha() {   
        //SETEO LA FECHA ACTUAL
        Calendar c2 = new GregorianCalendar();
        calendario.setCalendar(c2);  

    }

    public void iniciar(){
        //botonArticulos.setBackground(new java.awt.Color(0,0,0));
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator29 = new javax.swing.JSeparator();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jSeparator31 = new javax.swing.JSeparator();
        jSeparator34 = new javax.swing.JSeparator();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        conectado = new javax.swing.JLabel();
        lb_tipo = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        panelIconosMenu = new javax.swing.JPanel();
        botonArticulos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        botonClientes = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        botonProveedores = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        botonFacturasVenta = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        botonRemitos = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        botonPresupuestos = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        labelFecha = new rojeru_san.RSLabelFecha();
        btnAbrirCaja = new javax.swing.JButton();
        labelCajaCerrada = new javax.swing.JLabel();
        calendario = new com.toedter.calendar.JDateChooser();
        jMenuBar1 = new javax.swing.JMenuBar();
        M_deinicio = new javax.swing.JMenu();
        M_conectar = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        M_desconectar = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        jMenuItem13 = new javax.swing.JMenuItem();
        M_registrar = new javax.swing.JMenu();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        btnArt = new javax.swing.JMenuItem();
        jSeparator24 = new javax.swing.JPopupMenu.Separator();
        Clientes = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        menu_empleados = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        Proveedores = new javax.swing.JMenuItem();
        jSeparator27 = new javax.swing.JPopupMenu.Separator();
        jMenuItem25 = new javax.swing.JMenuItem();
        M_consultar = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator28 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        M_acerca = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jSeparator26 = new javax.swing.JPopupMenu.Separator();
        jMenuItem28 = new javax.swing.JMenuItem();
        jSeparator32 = new javax.swing.JPopupMenu.Separator();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Facturas = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator20 = new javax.swing.JPopupMenu.Separator();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator30 = new javax.swing.JPopupMenu.Separator();
        jMenuItem23 = new javax.swing.JMenuItem();
        jSeparator33 = new javax.swing.JPopupMenu.Separator();
        jMenuItem30 = new javax.swing.JMenuItem();
        M_reportes = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator36 = new javax.swing.JPopupMenu.Separator();
        jMenuItem31 = new javax.swing.JMenuItem();
        jSeparator21 = new javax.swing.JPopupMenu.Separator();
        jMenuItem20 = new javax.swing.JMenuItem();
        jSeparator35 = new javax.swing.JPopupMenu.Separator();
        jMenuItem33 = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenuItem21 = new javax.swing.JMenuItem();
        jSeparator22 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator19 = new javax.swing.JPopupMenu.Separator();
        jMenuItem24 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        jMenuItem14 = new javax.swing.JMenuItem();
        jSeparator37 = new javax.swing.JPopupMenu.Separator();
        jMenuItem29 = new javax.swing.JMenuItem();
        mantenimiento = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        Empresa = new javax.swing.JMenuItem();
        jSeparator23 = new javax.swing.JPopupMenu.Separator();
        jMenuItem15 = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JPopupMenu.Separator();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();

        jMenuItem12.setText("jMenuItem12");

        jMenu3.setText("jMenu3");

        jMenuItem17.setText("jMenuItem17");

        jMenuItem26.setText("jMenuItem26");

        jMenuItem27.setText("jMenuItem27");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Universal Software ");
        setMinimumSize(new java.awt.Dimension(700, 400));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelImage1.setBackground(new java.awt.Color(0, 0, 0));
        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/principalfondo.png"))); // NOI18N
        panelImage1.setLayout(null);

        conectado.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        conectado.setForeground(new java.awt.Color(204, 204, 204));
        conectado.setText("labelConectado");
        panelImage1.add(conectado);
        conectado.setBounds(70, 660, 470, 50);

        lb_tipo.setFont(new java.awt.Font("Cambria", 0, 17)); // NOI18N
        lb_tipo.setText("tipo");
        panelImage1.add(lb_tipo);
        lb_tipo.setBounds(70, 640, 40, 21);

        panelBotones.setBackground(new java.awt.Color(255, 255, 255));
        panelBotones.setOpaque(false);
        panelBotones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelImage1.add(panelBotones);
        panelBotones.setBounds(0, 0, 0, 0);

        panelIconosMenu.setBackground(new java.awt.Color(0, 102, 255));
        panelIconosMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonArticulos.setBackground(new java.awt.Color(51, 153, 255));
        botonArticulos.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        botonArticulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoArticulos.png"))); // NOI18N
        botonArticulos.setBorder(null);
        botonArticulos.setBorderPainted(false);
        botonArticulos.setContentAreaFilled(false);
        botonArticulos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonArticulos.setFocusCycleRoot(true);
        botonArticulos.setFocusable(false);
        botonArticulos.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoArticulosHover.png"))); // NOI18N
        botonArticulos.setRequestFocusEnabled(false);
        botonArticulos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoArticulosHover.png"))); // NOI18N
        botonArticulos.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoArticulosHover.png"))); // NOI18N
        botonArticulos.setVerifyInputWhenFocusTarget(false);
        botonArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonArticulosActionPerformed(evt);
            }
        });
        panelIconosMenu.add(botonArticulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 140));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Articulos");
        panelIconosMenu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        botonClientes.setBackground(new java.awt.Color(51, 153, 255));
        botonClientes.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        botonClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientes.png"))); // NOI18N
        botonClientes.setBorder(null);
        botonClientes.setBorderPainted(false);
        botonClientes.setContentAreaFilled(false);
        botonClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonClientes.setFocusCycleRoot(true);
        botonClientes.setFocusable(false);
        botonClientes.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesHover.png"))); // NOI18N
        botonClientes.setRequestFocusEnabled(false);
        botonClientes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesHover.png"))); // NOI18N
        botonClientes.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesHover.png"))); // NOI18N
        botonClientes.setVerifyInputWhenFocusTarget(false);
        botonClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClientesActionPerformed(evt);
            }
        });
        panelIconosMenu.add(botonClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 170, 140));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Clientes");
        panelIconosMenu.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, -1, -1));

        botonProveedores.setBackground(new java.awt.Color(51, 153, 255));
        botonProveedores.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        botonProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoProveedores.png"))); // NOI18N
        botonProveedores.setBorder(null);
        botonProveedores.setBorderPainted(false);
        botonProveedores.setContentAreaFilled(false);
        botonProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonProveedores.setFocusCycleRoot(true);
        botonProveedores.setFocusable(false);
        botonProveedores.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoProveedoresHover.png"))); // NOI18N
        botonProveedores.setRequestFocusEnabled(false);
        botonProveedores.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoProveedoresHover.png"))); // NOI18N
        botonProveedores.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoProveedoresHover.png"))); // NOI18N
        botonProveedores.setVerifyInputWhenFocusTarget(false);
        botonProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonProveedoresActionPerformed(evt);
            }
        });
        panelIconosMenu.add(botonProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 170, 140));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Proveedores");
        panelIconosMenu.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, -1, -1));

        botonFacturasVenta.setBackground(new java.awt.Color(51, 153, 255));
        botonFacturasVenta.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        botonFacturasVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturas.png"))); // NOI18N
        botonFacturasVenta.setBorder(null);
        botonFacturasVenta.setBorderPainted(false);
        botonFacturasVenta.setContentAreaFilled(false);
        botonFacturasVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonFacturasVenta.setFocusCycleRoot(true);
        botonFacturasVenta.setFocusable(false);
        botonFacturasVenta.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasHover.png"))); // NOI18N
        botonFacturasVenta.setRequestFocusEnabled(false);
        botonFacturasVenta.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasHover.png"))); // NOI18N
        botonFacturasVenta.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasHover.png"))); // NOI18N
        botonFacturasVenta.setVerifyInputWhenFocusTarget(false);
        botonFacturasVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFacturasVentaActionPerformed(evt);
            }
        });
        panelIconosMenu.add(botonFacturasVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 190, 180, 140));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Facturas");
        panelIconosMenu.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, 40));

        botonRemitos.setBackground(new java.awt.Color(51, 153, 255));
        botonRemitos.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        botonRemitos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoRemitos.png"))); // NOI18N
        botonRemitos.setBorder(null);
        botonRemitos.setBorderPainted(false);
        botonRemitos.setContentAreaFilled(false);
        botonRemitos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonRemitos.setFocusCycleRoot(true);
        botonRemitos.setFocusable(false);
        botonRemitos.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoRemitosHover.png"))); // NOI18N
        botonRemitos.setRequestFocusEnabled(false);
        botonRemitos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoRemitosHover.png"))); // NOI18N
        botonRemitos.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoRemitosHover.png"))); // NOI18N
        botonRemitos.setVerifyInputWhenFocusTarget(false);
        botonRemitos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRemitosActionPerformed(evt);
            }
        });
        panelIconosMenu.add(botonRemitos, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 150, 140));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Remitos");
        panelIconosMenu.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, -1, 40));

        botonPresupuestos.setBackground(new java.awt.Color(51, 153, 255));
        botonPresupuestos.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        botonPresupuestos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoPresupuestos.png"))); // NOI18N
        botonPresupuestos.setBorder(null);
        botonPresupuestos.setBorderPainted(false);
        botonPresupuestos.setContentAreaFilled(false);
        botonPresupuestos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonPresupuestos.setFocusCycleRoot(true);
        botonPresupuestos.setFocusable(false);
        botonPresupuestos.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoPresupuestosHover.png"))); // NOI18N
        botonPresupuestos.setRequestFocusEnabled(false);
        botonPresupuestos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoPresupuestosHover.png"))); // NOI18N
        botonPresupuestos.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoPresupuestosHover.png"))); // NOI18N
        botonPresupuestos.setVerifyInputWhenFocusTarget(false);
        botonPresupuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPresupuestosActionPerformed(evt);
            }
        });
        panelIconosMenu.add(botonPresupuestos, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 140, 140));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Presupuestos");
        panelIconosMenu.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, -1, 40));

        panelImage1.add(panelIconosMenu);
        panelIconosMenu.setBounds(310, 110, 500, 380);

        labelFecha.setForeground(new java.awt.Color(204, 204, 204));
        labelFecha.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        labelFecha.setFormato("EEEE dd MMMM yyyy");
        panelImage1.add(labelFecha);
        labelFecha.setBounds(40, 690, 300, 40);

        btnAbrirCaja.setBackground(new java.awt.Color(5, 52, 99));
        btnAbrirCaja.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        btnAbrirCaja.setForeground(new java.awt.Color(255, 255, 255));
        btnAbrirCaja.setText("ABRIR CAJA");
        btnAbrirCaja.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAbrirCaja.setDefaultCapable(false);
        btnAbrirCaja.setFocusPainted(false);
        btnAbrirCaja.setFocusable(false);
        btnAbrirCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCajaActionPerformed(evt);
            }
        });
        panelImage1.add(btnAbrirCaja);
        btnAbrirCaja.setBounds(60, 50, 230, 50);

        labelCajaCerrada.setBackground(new java.awt.Color(255, 255, 51));
        labelCajaCerrada.setFont(new java.awt.Font("Calibri", 1, 34)); // NOI18N
        labelCajaCerrada.setForeground(new java.awt.Color(255, 255, 0));
        labelCajaCerrada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCajaCerrada.setText("NO HAY CAJAS ABIERTAS");
        panelImage1.add(labelCajaCerrada);
        labelCajaCerrada.setBounds(10, 10, 360, 40);

        calendario.setDateFormatString("yyy/MM/dd HH:mm:ss");
        calendario.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        panelImage1.add(calendario);
        calendario.setBounds(60, 610, 153, 22);

        jMenuBar1.setBackground(new java.awt.Color(68, 98, 150));
        jMenuBar1.setBorder(null);
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N

        M_deinicio.setForeground(new java.awt.Color(255, 255, 255));
        M_deinicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoInicioXs-01.png"))); // NOI18N
        M_deinicio.setText("Inicio");
        M_deinicio.setToolTipText("Inicio");
        M_deinicio.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        M_deinicio.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoInicioXs-01.png"))); // NOI18N
        M_deinicio.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoInicioXsHover-01.png"))); // NOI18N
        M_deinicio.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoInicioXsHover-01.png"))); // NOI18N
        M_deinicio.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoInicioXsHover-01.png"))); // NOI18N

        M_conectar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK));
        M_conectar.setBackground(new java.awt.Color(255, 255, 255));
        M_conectar.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        M_conectar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoConectarXs-01.png"))); // NOI18N
        M_conectar.setText("Conectar");
        M_conectar.setToolTipText("conectar");
        M_conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M_conectarActionPerformed(evt);
            }
        });
        M_deinicio.add(M_conectar);
        M_deinicio.add(jSeparator13);

        M_desconectar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.SHIFT_MASK));
        M_desconectar.setBackground(new java.awt.Color(255, 255, 255));
        M_desconectar.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        M_desconectar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoConectarXs-01.png"))); // NOI18N
        M_desconectar.setText("Desconectar");
        M_desconectar.setToolTipText("Desconectar");
        M_desconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M_desconectarActionPerformed(evt);
            }
        });
        M_deinicio.add(M_desconectar);
        M_deinicio.add(jSeparator14);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem13.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem13.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoSalirXs-01.png"))); // NOI18N
        jMenuItem13.setText("Salir");
        jMenuItem13.setToolTipText("Salir");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        M_deinicio.add(jMenuItem13);

        jMenuBar1.add(M_deinicio);

        M_registrar.setForeground(new java.awt.Color(255, 255, 255));
        M_registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoRegistrarXs-01-01.png"))); // NOI18N
        M_registrar.setText("Registrar");
        M_registrar.setToolTipText("");
        M_registrar.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        M_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M_registrarActionPerformed(evt);
            }
        });
        M_registrar.add(jSeparator3);

        btnArt.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        btnArt.setBackground(new java.awt.Color(255, 255, 255));
        btnArt.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnArt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoArticulosXs-01.png"))); // NOI18N
        btnArt.setText("Articulos");
        btnArt.setToolTipText("");
        btnArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArtActionPerformed(evt);
            }
        });
        M_registrar.add(btnArt);
        M_registrar.add(jSeparator24);

        Clientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        Clientes.setBackground(new java.awt.Color(255, 255, 255));
        Clientes.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesXs-01.png"))); // NOI18N
        Clientes.setText("Clientes");
        Clientes.setToolTipText("");
        Clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClientesActionPerformed(evt);
            }
        });
        M_registrar.add(Clientes);
        M_registrar.add(jSeparator4);

        menu_empleados.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        menu_empleados.setBackground(new java.awt.Color(255, 255, 255));
        menu_empleados.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        menu_empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoEmpleadosXs-01.png"))); // NOI18N
        menu_empleados.setText("Vendedores");
        menu_empleados.setToolTipText("");
        menu_empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_empleadosActionPerformed(evt);
            }
        });
        M_registrar.add(menu_empleados);
        M_registrar.add(jSeparator5);

        Proveedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        Proveedores.setBackground(new java.awt.Color(255, 255, 255));
        Proveedores.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Proveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoProveedoresXs-01.png"))); // NOI18N
        Proveedores.setText("Proveedores");
        Proveedores.setToolTipText("");
        Proveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProveedoresActionPerformed(evt);
            }
        });
        M_registrar.add(Proveedores);
        M_registrar.add(jSeparator27);

        jMenuItem25.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem25.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem25.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        jMenuItem25.setText("Caja");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        M_registrar.add(jMenuItem25);

        jMenuBar1.add(M_registrar);

        M_consultar.setBackground(new java.awt.Color(0, 204, 255));
        M_consultar.setForeground(new java.awt.Color(255, 255, 255));
        M_consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoConsultarrrrrrrXs-01.png"))); // NOI18N
        M_consultar.setText("Consultar");
        M_consultar.setToolTipText("");
        M_consultar.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem4.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoArticulosXs-01.png"))); // NOI18N
        jMenuItem4.setText("Articulos");
        jMenuItem4.setToolTipText("Consultar Articulos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        M_consultar.add(jMenuItem4);
        M_consultar.add(jSeparator28);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesXs-01.png"))); // NOI18N
        jMenuItem1.setText("Clientes");
        jMenuItem1.setToolTipText("Consultar Clientes");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        M_consultar.add(jMenuItem1);
        M_consultar.add(jSeparator7);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem2.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoEmpleadosXs-01.png"))); // NOI18N
        jMenuItem2.setText("Vendedores");
        jMenuItem2.setToolTipText("Consultar Empleados");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        M_consultar.add(jMenuItem2);
        M_consultar.add(jSeparator8);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem3.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoProveedoresXs-01.png"))); // NOI18N
        jMenuItem3.setText("Provedores");
        jMenuItem3.setToolTipText("Consultar Proveedores");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        M_consultar.add(jMenuItem3);

        jMenuBar1.add(M_consultar);

        M_acerca.setBackground(new java.awt.Color(153, 153, 153));
        M_acerca.setForeground(new java.awt.Color(255, 255, 255));
        M_acerca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoArticulosBlancoXs-01.png"))); // NOI18N
        M_acerca.setText("Compras");
        M_acerca.setToolTipText("");
        M_acerca.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        M_acerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M_acercaActionPerformed(evt);
            }
        });

        jMenuItem18.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem18.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem18.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        jMenuItem18.setText("Registrar compra");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        M_acerca.add(jMenuItem18);
        M_acerca.add(jSeparator9);

        jMenu4.setBackground(new java.awt.Color(255, 255, 255));
        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoArticulosXs-01.png"))); // NOI18N
        jMenu4.setText("Devoluciones");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem16.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem16.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoRemitosXs-01.png"))); // NOI18N
        jMenuItem16.setText("Notas de credito");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem16);

        M_acerca.add(jMenu4);
        M_acerca.add(jSeparator26);

        jMenuItem28.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem28.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem28.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        jMenuItem28.setText("Pagos a proveedores");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        M_acerca.add(jMenuItem28);
        M_acerca.add(jSeparator32);

        jMenuItem32.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem32.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem32.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        jMenuItem32.setText("Credito de Compras");
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        M_acerca.add(jMenuItem32);

        jMenuBar1.add(M_acerca);

        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturarXs-01.png"))); // NOI18N
        jMenu2.setText("Ventas");
        jMenu2.setToolTipText("");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        Facturas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        Facturas.setBackground(new java.awt.Color(255, 255, 255));
        Facturas.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Facturas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        Facturas.setText(" Facturas");
        Facturas.setToolTipText("");
        Facturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FacturasActionPerformed(evt);
            }
        });
        jMenu2.add(Facturas);
        jMenu2.add(jSeparator15);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem10.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem10.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoRemitosXs-01.png"))); // NOI18N
        jMenuItem10.setText("Remitos");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);
        jMenu2.add(jSeparator2);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem11.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem11.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoPresupuestosXs-01.png"))); // NOI18N
        jMenuItem11.setText("Presupuestos");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);
        jMenu2.add(jSeparator20);

        jMenu5.setBackground(new java.awt.Color(255, 255, 255));
        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        jMenu5.setText("Devoluciones");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        jMenuItem19.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem19.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem19.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoRemitosXs-01.png"))); // NOI18N
        jMenuItem19.setText("Notas de credito");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem19);

        jMenu2.add(jMenu5);
        jMenu2.add(jSeparator16);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem8.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem8.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoPresupuestosXs-01.png"))); // NOI18N
        jMenuItem8.setText("Creditos de remitos");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);
        jMenu2.add(jSeparator30);

        jMenuItem23.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem23.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem23.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        jMenuItem23.setText("Pagos de clientes");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem23);
        jMenu2.add(jSeparator33);

        jMenuItem30.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem30.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem30.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        jMenuItem30.setText("Credito de facturas");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem30);

        jMenuBar1.add(jMenu2);

        M_reportes.setBackground(new java.awt.Color(0, 204, 255));
        M_reportes.setForeground(new java.awt.Color(255, 255, 255));
        M_reportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoReportesXs.png"))); // NOI18N
        M_reportes.setText("Reportes");
        M_reportes.setToolTipText("");
        M_reportes.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        jMenuItem5.setText("Ventas");
        jMenuItem5.setToolTipText("");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        M_reportes.add(jMenuItem5);
        M_reportes.add(jSeparator36);

        jMenuItem31.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem31.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem31.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        jMenuItem31.setText("Crédito de ventas (facturas) ");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        M_reportes.add(jMenuItem31);
        M_reportes.add(jSeparator21);

        jMenuItem20.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem20.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem20.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoArticulosXs-01.png"))); // NOI18N
        jMenuItem20.setText("Compras");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        M_reportes.add(jMenuItem20);
        M_reportes.add(jSeparator35);

        jMenuItem33.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem33.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem33.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoArticulosXs-01.png"))); // NOI18N
        jMenuItem33.setText("Credito de compras");
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        M_reportes.add(jMenuItem33);
        M_reportes.add(jSeparator10);

        jMenuItem21.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem21.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem21.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoPresupuestosXs-01.png"))); // NOI18N
        jMenuItem21.setText("Facturación Final");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        M_reportes.add(jMenuItem21);
        M_reportes.add(jSeparator22);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem6.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoArticulosXs-01.png"))); // NOI18N
        jMenuItem6.setText("Articulos");
        jMenuItem6.setToolTipText("Reportes De Articulos");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        M_reportes.add(jMenuItem6);
        M_reportes.add(jSeparator19);

        jMenuItem24.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem24.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem24.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoCodigoBarrasXs-01.png"))); // NOI18N
        jMenuItem24.setText("Codigos de barra");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        M_reportes.add(jMenuItem24);
        M_reportes.add(jSeparator11);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem7.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesXs-01.png"))); // NOI18N
        jMenuItem7.setText("Clientes");
        jMenuItem7.setToolTipText("Reportes De Clientes");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        M_reportes.add(jMenuItem7);
        M_reportes.add(jSeparator12);

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem14.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem14.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoPresupuestosXs-01.png"))); // NOI18N
        jMenuItem14.setText("Estadísticas de Venta");
        jMenuItem14.setToolTipText("");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        M_reportes.add(jMenuItem14);
        M_reportes.add(jSeparator37);

        jMenuItem29.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem29.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem29.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jMenuItem29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoPresupuestosXs-01.png"))); // NOI18N
        jMenuItem29.setText("Estadísticas de Compra");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        M_reportes.add(jMenuItem29);

        jMenuBar1.add(M_reportes);

        mantenimiento.setForeground(new java.awt.Color(255, 255, 255));
        mantenimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoMantenimientoXs-01-01.png"))); // NOI18N
        mantenimiento.setText("Configuración");
        mantenimiento.setToolTipText("");
        mantenimiento.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        mantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mantenimientoActionPerformed(evt);
            }
        });

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem9.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoAccesoXs-01.png"))); // NOI18N
        jMenuItem9.setText("Usuario y Acceso");
        jMenuItem9.setToolTipText("Registrar Usuario");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        mantenimiento.add(jMenuItem9);
        mantenimiento.add(jSeparator6);

        Empresa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        Empresa.setBackground(new java.awt.Color(255, 255, 255));
        Empresa.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Empresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoEmpresa33-01.png"))); // NOI18N
        Empresa.setText("Empresa");
        Empresa.setToolTipText("");
        Empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmpresaActionPerformed(evt);
            }
        });
        mantenimiento.add(Empresa);
        mantenimiento.add(jSeparator23);

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem15.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem15.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesXs-01.png"))); // NOI18N
        jMenuItem15.setText("Localidades");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        mantenimiento.add(jMenuItem15);
        mantenimiento.add(jSeparator17);

        jMenuItem22.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem22.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem22.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuItem22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoConectarXs-01.png"))); // NOI18N
        jMenuItem22.setText("Monedas");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        mantenimiento.add(jMenuItem22);

        jMenuBar1.add(mantenimiento);

        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoAcercaDeXs-01.png"))); // NOI18N
        jMenu1.setText("Acerca de");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 2904, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 1645, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClientesActionPerformed
        //este frame muestra un cartel de cargando y despues carga los clientes
       new ventana_carga_clientes().setVisible(true); 
}//GEN-LAST:event_ClientesActionPerformed

    private void menu_empleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_empleadosActionPerformed
       //este frame muestra un cartel de cargando y despues carga los vendedores
       new ventana_carga_vendedores().setVisible(true);
}//GEN-LAST:event_menu_empleadosActionPerformed

    private void ProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProveedoresActionPerformed
       //este frame muestra un cartel de cargando y despues carga los proveedores
       new ventana_carga_proveedores().setVisible(true);                          
}//GEN-LAST:event_ProveedoresActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Consultar_Clientes cc = new Consultar_Clientes(this, true);
        cc.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Consultar_Vendedores ce=new Consultar_Vendedores(this, true);
        ce.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Consultar_Proveedores cp = new Consultar_Proveedores(this, true);
        cp.setVisible(true);    
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Consultar_Articulos ca= new Consultar_Articulos(this, true);
        ca.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
       Menu_Reporte_Ventas repor = new Menu_Reporte_Ventas();
       repor .setVisible(true);
       repor.setLocationRelativeTo(this);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
       Menu_Reporte_Articulos articulo = new Menu_Reporte_Articulos();
       articulo.setVisible(true);     
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
       Menu_Reporte_Clientes cliente = new Menu_Reporte_Clientes();
       cliente.setVisible(true);        
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Object[] opciones ={"Aceptar","Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(rootPane,"En Realidad desea Salir de la Aplicacion","Alerta",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
        if(eleccion ==JOptionPane.YES_OPTION){
            try{
                Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql  
                setearFecha();
                String fecha=((JTextField)calendario.getDateEditor().getUiComponent()).getText();
                consulta.executeUpdate("UPDATE ingreso_usuarios SET estado='INACTIVO', fecha_salida='"+fecha+"' WHERE estado='ACTIVO' ");
                System.exit(0);
            }catch(Exception e){
                System.out.println(e);
            }
            System.exit(0);
        }else{

        }
    }//GEN-LAST:event_formWindowClosing

    private void M_acercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_acercaActionPerformed
        Modulo_Informacion_Creador a = new Modulo_Informacion_Creador(this, true);
        a.setVisible(true);
        a.setLocationRelativeTo(this);
    }//GEN-LAST:event_M_acercaActionPerformed

    private void mantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mantenimientoActionPerformed

    }//GEN-LAST:event_mantenimientoActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        Registrar_Usuarios r = new Registrar_Usuarios();
        r.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        Object[]opciones={"Aceptar","Cancelar"};
        int mensa= JOptionPane.showOptionDialog(rootPane,"DESEA SALIR DEL SISTEMA??", "ADVERTENCIA",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, opciones,"Aceptar");
        if (mensa ==JOptionPane.YES_OPTION )
        {
            try{
                Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql  
                setearFecha();
                String fecha=((JTextField)calendario.getDateEditor().getUiComponent()).getText();
                consulta.executeUpdate("UPDATE ingreso_usuarios SET estado='INACTIVO', fecha_salida='"+fecha+"' WHERE estado='ACTIVO' ");
                System.exit(0);
            }catch(Exception e){
                System.out.println(e);
            }
            System.exit(0);
        }
        else 
        { }
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void M_desconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_desconectarActionPerformed
        M_desconectar.setEnabled(false);
        M_registrar.setEnabled(false);
        M_consultar.setEnabled(false);
        M_reportes.setEnabled(false);
        mantenimiento.setEnabled(false);
        M_acerca.setEnabled(false);
        M_conectar.setEnabled(true);
    }//GEN-LAST:event_M_desconectarActionPerformed

    private void M_conectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_conectarActionPerformed
        Login_ingreso usuarios = new Login_ingreso();
        usuarios.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_M_conectarActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
       /* try {  //Mostrar pdf ayuda
            File path = new File ("MANUAL.pdf");
            Desktop.getDesktop().open(path);
        }catch (IOException ex) {
            ex.printStackTrace();
        }*/
       Modulo_Informacion_Creador acerca = new Modulo_Informacion_Creador(this,true);
       acerca.setVisible(true);
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        new Menu_Remitos ().setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void FacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FacturasActionPerformed
        new Factura_Venta ().setVisible(true);  
    }//GEN-LAST:event_FacturasActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        new Factura_Presupuesto ().setVisible(true); 
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void botonArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonArticulosActionPerformed

        //este frame muestra un cartel de cargando y despues carga los articulos
       new ventana_carga_articulos().setVisible(true);

      
    }//GEN-LAST:event_botonArticulosActionPerformed

    private void botonProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonProveedoresActionPerformed
       //este frame muestra un cartel de cargando y despues carga los proveedores
       new ventana_carga_proveedores().setVisible(true);
        
    }//GEN-LAST:event_botonProveedoresActionPerformed

    private void botonClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonClientesActionPerformed
        //este frame muestra un cartel de cargando y despues carga los clientes
       new ventana_carga_clientes().setVisible(true);
        
    }//GEN-LAST:event_botonClientesActionPerformed

    private void botonFacturasVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFacturasVentaActionPerformed
        new Factura_Venta ().setVisible(true);
    }//GEN-LAST:event_botonFacturasVentaActionPerformed

    private void botonRemitosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRemitosActionPerformed
        new Menu_Remitos ().setVisible(true);
    }//GEN-LAST:event_botonRemitosActionPerformed

    private void botonPresupuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPresupuestosActionPerformed
        new Factura_Presupuesto ().setVisible(true); 
    }//GEN-LAST:event_botonPresupuestosActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        //new mostrar_facturas_no_registradas_afip ().setVisible(true);

    }//GEN-LAST:event_jMenu2ActionPerformed

    private void EmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmpresaActionPerformed
      Registrar_Empresa emp = new Registrar_Empresa(this,true);
      emp.setVisible(true);
    }//GEN-LAST:event_EmpresaActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        new Menu_Estadisticas ().setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
         Registrar_Localidades localidad = new Registrar_Localidades(this, true);
         localidad.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
         new Factura_Compra ().setVisible(true);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        new Factura_Nota_Credito_Venta ().setVisible(true);  
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        Menu_Reporte_Compras repor = new Menu_Reporte_Compras();
       repor .setVisible(true);
       repor.setLocationRelativeTo(this);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        new Factura_Nota_Credito_Compra ().setVisible(true);  
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
       frm_posicionIVA repor = new frm_posicionIVA();
       repor .setVisible(true);
       repor.setLocationRelativeTo(this);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        new Modulo_Saldos ().setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        Registrar_Monedas monedas = new Registrar_Monedas(this, true);  
        monedas.setVisible(true);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void M_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_registrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_M_registrarActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
         new Consultar_Pagos_Ventas ().setVisible(true);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        Menu_Reporte_Codigo_Barras articulo = new Menu_Reporte_Codigo_Barras();
        articulo.setVisible(true); 
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
       Menu_Caja caja = new Menu_Caja();
       caja.setVisible(true);     
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        new Consultar_Pagos_Compras ().setVisible(true);
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void btnAbrirCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCajaActionPerformed
        Caja_Apertura form= new Caja_Apertura();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_btnAbrirCajaActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
        new Consultar_Credito_Compras ().setVisible(true);
    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        new Consultar_Credito_Ventas ().setVisible(true);
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        Menu_Reporte_Credito_Venta credito = new Menu_Reporte_Credito_Venta();
        credito.setVisible(true);
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        Menu_Reporte_Credito_Compra credito = new Menu_Reporte_Credito_Compra();
        credito.setVisible(true);
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        new Menu_Estadisticas_Compra ().setVisible(true);
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void btnArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArtActionPerformed
        //este frame muestra un cartel de cargando y despues carga los articulos
       new ventana_carga_articulos().setVisible(true);

    }//GEN-LAST:event_btnArtActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Principal().setVisible(true);                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JMenuItem Clientes;
    private javax.swing.JMenuItem Empresa;
    private javax.swing.JMenuItem Facturas;
    public static javax.swing.JMenu M_acerca;
    private javax.swing.JMenuItem M_conectar;
    private javax.swing.JMenu M_consultar;
    private javax.swing.JMenu M_deinicio;
    private javax.swing.JMenuItem M_desconectar;
    public static javax.swing.JMenu M_registrar;
    public static javax.swing.JMenu M_reportes;
    public static javax.swing.JMenuItem Proveedores;
    public static javax.swing.JButton botonArticulos;
    public static javax.swing.JButton botonClientes;
    private javax.swing.JButton botonFacturasVenta;
    private javax.swing.JButton botonPresupuestos;
    public static javax.swing.JButton botonProveedores;
    private javax.swing.JButton botonRemitos;
    public static javax.swing.JButton btnAbrirCaja;
    public static javax.swing.JMenuItem btnArt;
    private com.toedter.calendar.JDateChooser calendario;
    public static javax.swing.JLabel conectado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JPopupMenu.Separator jSeparator19;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator20;
    private javax.swing.JPopupMenu.Separator jSeparator21;
    private javax.swing.JPopupMenu.Separator jSeparator22;
    private javax.swing.JPopupMenu.Separator jSeparator23;
    private javax.swing.JPopupMenu.Separator jSeparator24;
    private javax.swing.JPopupMenu.Separator jSeparator26;
    private javax.swing.JPopupMenu.Separator jSeparator27;
    private javax.swing.JPopupMenu.Separator jSeparator28;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator30;
    private javax.swing.JSeparator jSeparator31;
    private javax.swing.JPopupMenu.Separator jSeparator32;
    private javax.swing.JPopupMenu.Separator jSeparator33;
    private javax.swing.JSeparator jSeparator34;
    private javax.swing.JPopupMenu.Separator jSeparator35;
    private javax.swing.JPopupMenu.Separator jSeparator36;
    private javax.swing.JPopupMenu.Separator jSeparator37;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    public static javax.swing.JLabel labelCajaCerrada;
    private rojeru_san.RSLabelFecha labelFecha;
    public static javax.swing.JLabel lb_tipo;
    public static javax.swing.JMenu mantenimiento;
    public static javax.swing.JMenuItem menu_empleados;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelIconosMenu;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    // End of variables declaration//GEN-END:variables

}
