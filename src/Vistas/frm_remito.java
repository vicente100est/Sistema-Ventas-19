/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;


import javax.swing.JFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Hugo
 */
public class frm_remito extends javax.swing.JFrame {

    /**
     * Creates new form frm_remito
     */
    public void cerrar(){ 
        this.setVisible(false);  
    }
    
    public void inabilita(){
    cbEmpleado.setEnabled(false);
    cbCliente.setEnabled(false);
    cbArticulo.setEnabled(false);
    buscare.setEnabled(false);
    buscare1.setEnabled(false);
    buscare2.setEnabled(false);
    cant1.setEnabled(false);
     btnAgregar.setEnabled(false);
     b41.setEnabled(false);
     //b43.setEnabled(false);
     b42.setEnabled(false);
     btn_guardara.setEnabled(false);
     sub.setEnabled(false);
     iva.setEnabled(false);
    total.setEnabled(false);
    
    btn_guardara.setVisible(false);
    b42.setVisible(false);
    
    }
    public void habilitar(){
    cant1.requestFocus();
    
     cbEmpleado.setEnabled(true);
    cbCliente.setEnabled(true);
    cbArticulo.setEnabled(true);
    buscare.setEnabled(true);
    buscare1.setEnabled(true);
    buscare2.setEnabled(true);
    cant1.setEnabled(true);
     btnAgregar.setEnabled(true);
     b41.setEnabled(true);
     //b43.setEnabled(true);
     b42.setEnabled(true);
     btn_guardara.setEnabled(true);
     sub.setEnabled(true);
     iva.setEnabled(true);
    total.setEnabled(true);
    
    btn_guardara.setVisible(true);
    b42.setVisible(true);
    }
    
   
    
    
    
    public frm_remito() {
        initComponents();
        inabilita();
        setLocationRelativeTo(null);
        

        b42.setEnabled(false);

Calendar c1 = Calendar.getInstance();

dia = (Integer.toString(c1.get(Calendar.DATE)));
if (dia.length()==1)
{
dia="0"+dia;
}

mes = (Integer.toString(c1.get(Calendar.MONTH)+1));
if (mes.length()==1)
{
mes="0"+mes;
}

año = (Integer.toString(c1.get(Calendar.YEAR)));

fec.setText(año+"/"+mes+"/"+dia);


            try{
                Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
                Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
                Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql
                //Connection conn=(Connection) DriverManager.getConnection(url,usuario,contraseña); // esta es la verificación de la conexión con mysql
                Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                //// hasta aquí es el mismo código del guardar///////
                ResultSet   res= consulta.executeQuery("select * from articulo order by referencia");

                int i;
                cbArticulo.removeAllItems();
                cbArticulo.addItem("SELECCIONE ARTICULO");
                i=0;
                while(res.next()){
                    cbArticulo.addItem(res.getString(2));
                    i++;
                }

                res= consulta.executeQuery("select * from cliente order by nombres");


                cbCliente.removeAllItems();
                cbCliente.addItem("SELECCIONE CLIENTE");
                i=0;
                while(res.next()){
                    cbCliente.addItem(res.getString(2)+" "+res.getString(3));
                    i++;
                }

                res= consulta.executeQuery("select * from empleado order by nombres");


                cbEmpleado.removeAllItems();
                cbEmpleado.addItem("SELECCIONE EMPLEADO");
                i=0;
                while(res.next()){
                    cbEmpleado.addItem(res.getString(2)+" "+res.getString(3));
                    i++;
                }

                res.close();
            } catch(ClassNotFoundException e){
                JOptionPane.showMessageDialog(null,"No hay conexion con Mysql") ;

            }catch(SQLException e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Error en el SQL") ;
            }

        
        
        
        
        
        
    }
    String bd ="ventas";
             int subtotal=0;
             String codigoproveedor;
             String nomproveedor;
             String codigoproducto;
             String codigoproducto_e;
             int cant_ex=0;
             String precio;
             int nueva_cant =0;
             String nomproducto;
             int fila;
             int columna;
             String cod_empleado;
             String nomempleado;
             String codigocliente;
             String nomcliente;
             String NOMBRE;
             String dia, mes, año;
             String login = "root";
             String password = "";
             String url = "jdbc:mysql://localhost/"+bd;

    frm_remito(menu aThis, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
                          

   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        btn_guardara = new org.edisoncor.gui.button.ButtonSeven();
        b42 = new org.edisoncor.gui.button.ButtonSeven();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cbEmpleado = new javax.swing.JComboBox();
        buscare = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox();
        buscare1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cbArticulo = new javax.swing.JComboBox();
        buscare2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cant1 = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        b41 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        botonAgregarCliente = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        iva = new javax.swing.JTextField();
        sub = new javax.swing.JTextField();
        btn_nuevo = new org.edisoncor.gui.button.ButtonSeven();
        fec = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        fact = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        panelImage5 = new org.edisoncor.gui.panel.PanelImage();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelImage1.setBackground(new java.awt.Color(153, 204, 255));
        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoMenumar2.png"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_guardara.setBackground(new java.awt.Color(204, 204, 204));
        btn_guardara.setText("Guardar");
        btn_guardara.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_guardara.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btn_guardara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardaraActionPerformed(evt);
            }
        });
        panelImage1.add(btn_guardara, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 570, 150, 60));

        b42.setBackground(new java.awt.Color(204, 204, 204));
        b42.setText("Cancelar");
        b42.setColorBrillo(new java.awt.Color(255, 255, 255));
        b42.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        b42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b42ActionPerformed(evt);
            }
        });
        panelImage1.add(b42, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 570, 150, 60));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);

        jLabel5.setBackground(new java.awt.Color(239, 255, 239));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setText("Empleado");

        cbEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEmpleadoActionPerformed(evt);
            }
        });

        buscare.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoLupa.png"))); // NOI18N
        buscare.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscareActionPerformed(evt);
            }
        });
        buscare.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscareKeyPressed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(239, 255, 239));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setText("Cliente");

        cbCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbClienteActionPerformed(evt);
            }
        });

        buscare1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoLupa.png"))); // NOI18N
        buscare1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscare1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscare1ActionPerformed(evt);
            }
        });
        buscare1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscare1KeyTyped(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(239, 255, 239));
        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("Articulo");

        cbArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbArticuloActionPerformed(evt);
            }
        });

        buscare2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoLupa.png"))); // NOI18N
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

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setText("Cantidad");

        cant1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        cant1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cant1KeyTyped(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnAgregar.setText("AGREGAR");
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarMouseExited(evt);
            }
        });
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        b41.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        b41.setText("QUITAR");
        b41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b41MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b41MouseExited(evt);
            }
        });
        b41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b41ActionPerformed(evt);
            }
        });

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Articulo", "Valor Unitario", "Valor Total"
            }
        ));
        jScrollPane1.setViewportView(tabla1);

        botonAgregarCliente.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        botonAgregarCliente.setText("+");
        botonAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(19, 19, 19))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cant1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b41, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buscare2)))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbCliente, 0, 218, Short.MAX_VALUE)
                            .addComponent(cbEmpleado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buscare, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buscare1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(buscare2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9)))))
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(buscare1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
                        .addGap(22, 22, 22)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(cant1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b41, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buscare, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        panelImage1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 110, -1, 360));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setText("SubTotal");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setText("I.V.A");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setText("TOTAL");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sub, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sub, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iva, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel12))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap())
        );

        panelImage1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 500, 250, 160));

        btn_nuevo.setBackground(new java.awt.Color(102, 153, 255));
        btn_nuevo.setText("Nuevo");
        btn_nuevo.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_nuevo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });
        panelImage1.add(btn_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, 150, 60));

        fec.setEditable(false);
        fec.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        fec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecActionPerformed(evt);
            }
        });
        fec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fecKeyTyped(evt);
            }
        });
        panelImage1.add(fec, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, 160, 40));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setText("Fecha");
        panelImage1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(684, 40, 70, -1));

        fact.setEditable(false);
        fact.setBackground(new java.awt.Color(51, 255, 204));
        fact.setFont(new java.awt.Font("Cambria", 1, 30)); // NOI18N
        fact.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fact.setBorder(null);
        fact.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        fact.setEnabled(false);
        fact.setOpaque(false);
        fact.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        fact.setSelectionColor(new java.awt.Color(0, 0, 0));
        panelImage1.add(fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 50, 30));

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 30)); // NOI18N
        jLabel2.setText("Remito");
        panelImage1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        panelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/factura_1.png"))); // NOI18N

        javax.swing.GroupLayout panelImage5Layout = new javax.swing.GroupLayout(panelImage5);
        panelImage5.setLayout(panelImage5Layout);
        panelImage5Layout.setHorizontalGroup(
            panelImage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 79, Short.MAX_VALUE)
        );
        panelImage5Layout.setVerticalGroup(
            panelImage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelImage1.add(panelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 69));

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 30)); // NOI18N
        jLabel10.setText(" N°");
        panelImage1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardaraActionPerformed
        
        try {

            if (cbEmpleado.getSelectedItem().equals("SELECCIONE EMPLEADO")) {
                JOptionPane.showMessageDialog(null, "Falta elegir el Empleado","Advertencia",JOptionPane.WARNING_MESSAGE);
            }else {
                if (cbCliente.getSelectedItem().equals("SELECCIONE CLIENTE")) {
                    JOptionPane.showMessageDialog(null, "Falta elegir el Cliente","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else {
                    if (fec.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Debe Digitar la Fecha","Advertencia",JOptionPane.WARNING_MESSAGE);
                        fec.requestFocus();
                    }else {
                        if (fact.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Debe Digitar Numero de la Factura","Advertencia",JOptionPane.WARNING_MESSAGE);
                            fact.requestFocus();
                        }else {
                            if ( tabla1.getRowCount() <= 0 ) {
                                JOptionPane.showMessageDialog(null, "No Hay Ningun Producto a Facturar","Advertencia",JOptionPane.ERROR_MESSAGE);
                            }else {
                                //
                                try{
                                    Class.forName("com.mysql.jdbc.Driver");
                                    Connection conn=(Connection) DriverManager.getConnection(url,login,password);
                                    Statement consulta=conn.createStatement();
                                    Statement consulta1=conn.createStatement();
                                    int fil = tabla1.getRowCount();
                                    int col = tabla1.getColumnCount();

                                    int x,y;

                                    //dd
                                    JOptionPane.showMessageDialog(null,"ejecuta insert remito") ;
                                    consulta.executeUpdate("insert into remito (cod_remito,fecha,cod_cliente,cod_empleado) values('"+fact.getText()+"','"+fec.getText()+"','"+codigocliente+"','"+cod_empleado+"')");
                                    JOptionPane.showMessageDialog(null,"yaa ejecuto insert remito") ;
                                    for (x=0;x<=fil-1;x++) {
                                        JOptionPane.showMessageDialog(null,"ejecutainsert referncia remito") ;
                                        consulta1.executeUpdate("insert into referencia_remito (cod_remito,valor_unitario,valor_total,referencia,cantidad,total) values('"+fact.getText()+"'  ,'"+tabla1.getValueAt(x,2)+"','"+tabla1.getValueAt(x,3)+"','"+tabla1.getValueAt(x,1)+"','"+tabla1.getValueAt(x,0)+"','"+total.getText()+"')");

                                    }

                                    JOptionPane.showMessageDialog(null,"Remito Adicionado") ;
                                    cbEmpleado.setSelectedIndex(0);
                                    cbCliente.setSelectedIndex(0);
                                    cbArticulo.setSelectedIndex(0);
                                    sub.setText("");
                                    iva.setText("");
                                    total.setText("");
                                    cant1.setText("");
                                    
                                    //-------------------------------
                            
                                    Connection miconexion = conexion.GetConnection();
     Map parametros = new HashMap();
     parametros.put("codr",fact.getText());
    // parametros.put("LOGO",this.getClass().getResourceAsStream("/img/repot.png"));
        
    // if (fact=""){
     
   int op=JOptionPane.showConfirmDialog(null, "DESEA IRMPRIMIR EL REMITO","SELECCIONE UN OPCION" ,JOptionPane.YES_NO_OPTION);
   if (op==JOptionPane.YES_NO_OPTION){
   
   try {
       
            String reporte="remito.jasper";
            JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
            JasperViewer ventanavisor=new JasperViewer(informe,false);
            ventanavisor.setTitle("Reporte cliente");
            ventanavisor.setVisible(true);
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
   
   }else
   {
   // no hacer nada
       
   }
                                    
                                     //-------------------------------
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    

                                    for (x=0;x<=fil-1;x++) {
                                        String aux= (String) (tabla1.getValueAt(x,0));

                                        ResultSet   res= consulta.executeQuery("select cantidad from articulo where referencia='"+tabla1.getValueAt(x,1)+"'");
                                        while(res.next()){
                                            cant_ex=Integer.parseInt(res.getString(1));

                                        }

                                        nueva_cant = cant_ex - Integer.parseInt(aux);

                                        consulta.executeUpdate("UPDATE articulo SET cantidad='"+nueva_cant+"' WHERE referencia='"+tabla1.getValueAt(x,1)+"'");

                                    }

                                    for (x=0;x<=fil-1;x++) {
                                        for (y=0;y<=col-1;y++) {
                                            tabla1.setValueAt("",x,y);
                                        }
                                    }

                                    for (x=fil-1;x>=0;x--) {
                                        DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
                                        modelo.removeRow(x);
                                    }

                                } catch(SQLException e){
                                    JOptionPane.showMessageDialog(null,"Este Producto Ya Existessssssssssss") ;
                                    System.out.println(e);

                                } catch(ClassNotFoundException e){
                                    JOptionPane.showMessageDialog(null,"Error en la Base de Datos") ;                               }

                            }
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }//GEN-LAST:event_btn_guardaraActionPerformed

    private void b42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b42ActionPerformed
        // TODO add your handling code here:

        /*int cantidad=0, total_cantidad=0;

        int k =JOptionPane.showConfirmDialog(this,"¿Esta Seguro de Eliminar Esta Factura?","F A C T U R A",JOptionPane.YES_NO_OPTION);
        if(k==0){

            int num=0;
            try{
                Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
                Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql
                //Connection conn=(Connection) DriverManager.getConnection(url,usuario,contraseña); // esta es la verificación de la conexión con mysql
                Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                //// hasta aquí es el mismo código del guardar///////

                //ResultSet res= consulta.executeQuery("select * from programas where nombre = '"+t2.getText()+"'");
                consulta.executeUpdate("DELETE FROM factura WHERE cod_factura='"+fact.getText()+"'");
                JOptionPane.showMessageDialog(null,"Factura Eliminada") ;

                //aqui i
                int fil = tabla1.getRowCount();
                int col = tabla1.getColumnCount();
                //JOptionPane.showMessageDialog(null, fil);
                int x,y;

                //dd

                for (x=0;x<=fil-1;x++) {
                    String aux = (String) tabla1.getValueAt(x,0);
                    total_cantidad=Integer.parseInt(aux);
                    ResultSet r= consulta.executeQuery("select cantidad from articulo where referencia = '"+tabla1.getValueAt(x,1) +"'");

                    while(r.next()){
                        cantidad=Integer.parseInt(r.getString(1));

                    }

                    total_cantidad=cantidad+total_cantidad;
                    String total_cantidad2=String.valueOf(total_cantidad);
                    consulta.executeUpdate("UPDATE articulo SET cantidad='"+total_cantidad2+"' WHERE referencia='"+tabla1.getValueAt(x,1)+"'");

                }

                cbEmpleado.setSelectedIndex(0);
                cbCliente.setSelectedIndex(0);
                cbArticulo.setSelectedIndex(0);
                cant1.setText("");
                fact.setText("");
                sub.setText("");
                iva.setText("");
                total.setText("");
                b42.setEnabled(false);
               // b43.setEnabled(true);
                btnAgregar.setEnabled(true);
                b41.setEnabled(true);

                fil = tabla1.getRowCount();

                for (x=fil-1;x>=0;x--) {
                    DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
                    modelo.removeRow(x);
                }

                cbEmpleado.setSelectedIndex(0);
                cbCliente.setSelectedIndex(0);
                cant1.setText("");
                fec.setText(año+"/"+mes+"/"+dia);

            }catch(SQLException e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Error en el SQL") ;
            } catch(ClassNotFoundException e){
                JOptionPane.showMessageDialog(null,"No hay conexion con Mysql") ;
            }
        }*/
        inabilita();
    }//GEN-LAST:event_b42ActionPerformed

    private void cbEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEmpleadoActionPerformed

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=(Connection) DriverManager.getConnection(url,login,password);
            Statement consulta=conn.createStatement();
            nomempleado = (String)cbEmpleado.getSelectedItem();
            cod_empleado=null;
            
            String cadena=cbEmpleado.getSelectedItem().toString();
            int i=0;
  
            
            while(cadena.charAt(i)!=' ') { 
               i++;
            }  
            String SubCadenaNombreEmpleado = cadena.substring(0,i);
            String SubCadenaApellidoEmpleado = cadena.substring(i+1,cadena.length());
       
            ResultSet rs = consulta.executeQuery("SELECT cod_empleado FROM empleado WHERE (nombres = '"+SubCadenaNombreEmpleado+"' AND apellidos = '"+SubCadenaApellidoEmpleado+"' )");
            while (rs.next()) {
                cod_empleado= rs.getString("cod_empleado");
            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error Conexion");
            ex.printStackTrace();
        }	  catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
    }//GEN-LAST:event_cbEmpleadoActionPerformed

    private void buscareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscareActionPerformed
        enviar_empleado form= new enviar_empleado ();
        form.setVisible(true);
        form.toFront();
        enviar_empleado.txt_recibeEmpleado.setText("9");

    }//GEN-LAST:event_buscareActionPerformed

    private void buscareKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscareKeyPressed
        if (evt.getKeyChar()==evt.VK_ENTER){

            //       new tabla_empleado().setVisible(true);
            //        tabla_empleado.txb.setText("1");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_buscareKeyPressed

    private void cbClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbClienteActionPerformed
        try{

            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=(Connection) DriverManager.getConnection(url,login,password);
            Statement consulta=conn.createStatement();
            nomcliente = (String)cbCliente.getSelectedItem();
            codigocliente=null;
            
            String cadena=cbCliente.getSelectedItem().toString();
            int i=0;
  
            
            while(cadena.charAt(i)!=' ') { 
               i++;
            }  
            String SubCadenaNombreCliente = cadena.substring(0,i);
            String SubCadenaApellidoCliente = cadena.substring(i+1,cadena.length());
            
            ResultSet rs = consulta.executeQuery("SELECT cod_cliente FROM cliente WHERE (nombres = '"+SubCadenaNombreCliente+"' AND apellidos = '"+SubCadenaApellidoCliente+"')");
            while (rs.next()) {
                codigocliente= rs.getString("cod_cliente");
            }
            conn.close();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error Conexion");
            e.printStackTrace();
        }	  catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
    }//GEN-LAST:event_cbClienteActionPerformed

    private void buscare1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscare1ActionPerformed
        enviar_cliente form= new enviar_cliente ();
        form.setVisible(true);
        form.toFront();
        enviar_cliente.recibe.setText("4");

        //// TODO add your handling code here:
    }//GEN-LAST:event_buscare1ActionPerformed

    private void buscare1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscare1KeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){

            //        new tabla_cliente().setVisible(true);
            //       tabla_cliente.txb.setText("1");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_buscare1KeyTyped

    private void cbArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbArticuloActionPerformed
        try{

            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
            Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql

            //Connection conn=(Connection) DriverManager.getConnection(url,usuario,contraseña); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            nomproducto = (String)cbArticulo.getSelectedItem();

            codigoproducto=null;
            //

            ResultSet rs = consulta.executeQuery("SELECT cod_articulo FROM articulo WHERE (referencia = '"+cbArticulo.getSelectedItem()+"')");
            while (rs.next()) {
                codigoproducto= rs.getString("cod_articulo");
            }

            conn.close();  // Cierra la conexión

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error Conexion");
            ex.printStackTrace();
        }	  catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error SQL");
        }
        
    }//GEN-LAST:event_cbArticuloActionPerformed

    private void buscare2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscare2ActionPerformed
        enviar_producto form= new enviar_producto ();
        form.setVisible(true);
        form.toFront();
        enviar_producto.txt_recibe.setText("10");

        //        new tabla_articulo().setVisible(true);
        //        tabla_articulo.txb.setText("1");    // TODO add your handling code here:
    }//GEN-LAST:event_buscare2ActionPerformed

    private void buscare2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscare2KeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            //         new tabla_articulo().setVisible(true);
            //        tabla_articulo.txb.setText("1");

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_buscare2KeyTyped

    private void cant1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cant1KeyTyped
        char c = evt.getKeyChar(); //este codigo se utiliza para solo numero en el tefil y espacio
        if (!((Character.isDigit(c))||(c==KeyEvent.VK_ENTER)||(c==KeyEvent.VK_BACK_SPACE))) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo deben digitarse Numeros");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cant1KeyTyped

    private void fecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fecActionPerformed

    private void fecKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fecKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_fecKeyTyped

    private void btnAgregarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseEntered
        //p40.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnAgregarMouseEntered

    private void btnAgregarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseExited

        //p40.setBackground(new java.awt.Color(153,204,255));
    }//GEN-LAST:event_btnAgregarMouseExited

    
    
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        cbEmpleado.setEnabled(false);
        cbCliente.setEnabled(false);
        int pe=0,fil = tabla1.getRowCount();
        for (int x=0;x<=fil-1;x++) {
            String aux= (String) (tabla1.getValueAt(x,1));
            if(aux.equals(cbArticulo.getSelectedItem()))
            {

                pe=1;
            }
        }
        if(pe==1)
        {
            JOptionPane.showMessageDialog(null,"Este Articulo Ya Existe") ;
        }else{
            if (cbArticulo.getSelectedItem().equals("SELECCIONE ARTICULO"))
            {
                JOptionPane.showMessageDialog(null, "Falta elegir el articulo");
            }else
            {
                if (cant1.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Debe Digitar la Cantidad de articulo a Llevar");
                    cant1.requestFocus();
                }else
                {

                    try{
                        Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
                        Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
                        Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql
                        //Connection conn=(Connection) DriverManager.getConnection(url,usuario,contraseña); // esta es la verificación de la conexión con mysql
                        Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                        //// hasta aquí es el mismo código del guardar///////
                        ResultSet   res= consulta.executeQuery("select cantidad from articulo where cod_articulo = '"+codigoproducto+"'");
                        while(res.next()){
                            cant_ex=Integer.parseInt(res.getString(1));
                            break;
                        }
                        res.close();
                    } catch(ClassNotFoundException e){
                        JOptionPane.showMessageDialog(null,"No hay conexion con Mysql") ;

                    }catch(SQLException e){
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null,"Error en el SQL") ;
                    }

                    if (cant_ex < Integer.parseInt(cant1.getText()))
                    {
                        JOptionPane.showMessageDialog(null, "No existe Cantidad Disponible de Dicho Articulo, La Cantidad Dispomible es: "+cant_ex);
                    }
                    else
                    {
                        fila = tabla1.getRowCount();
                        columna = tabla1.getColumnCount();
                        //JOptionPane.showMessageDialog(null, fila+" "+columna);
                        DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
                        modelo.addRow( new Object [] {null,null,null,null,null});
                        tabla1.setValueAt(cant1.getText(),fila,0);
                        tabla1.setValueAt(nomproducto,fila,1);

                        try{
                            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
                            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
                            Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql

                            //Connection conn=(Connection) DriverManager.getConnection(url,usuario,contraseña); // esta es la verificación de la conexión con mysql
                            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

                            ResultSet r= consulta.executeQuery("select valor from articulo where cod_articulo= '"+codigoproducto+"'");
                            while(r.next()){

                                precio=r.getString(1);
                            }

                        } catch(SQLException e){
                            JOptionPane.showMessageDialog(null,"Este Articulo Ya Existeeeeeeeeeeeeee") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
                            //t2.setText("");

                        } catch(ClassNotFoundException e){
                            JOptionPane.showMessageDialog(null,"Error en la Base de Datos") ; // esto aparece cuando hay problemas con la conexión con mysql

                        }

                        tabla1.setValueAt(precio,fila,2);
                        int totalsub=Integer.parseInt(precio)*Integer.parseInt(cant1.getText());
                        subtotal=totalsub+subtotal;
                        tabla1.setValueAt(totalsub,fila,3);
                        sub.setText(""+subtotal);
                        int ivas=(int) (subtotal * 0);
                        iva.setText(""+ivas);
                        int totals=subtotal+ivas;
                        total.setText(""+totals);

                        cbArticulo.setSelectedIndex(0);
                        cant1.setText("");
                    }
                }
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void b41MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b41MouseEntered
        //p41.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_b41MouseEntered

    private void b41MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b41MouseExited

        //p41.setBackground(new java.awt.Color(153,204,255));
    }//GEN-LAST:event_b41MouseExited

    private void b41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b41ActionPerformed
        try
        {
            String aux= String.valueOf((tabla1.getValueAt(fila, 3)));
            int totalsub=Integer.parseInt(aux);
            subtotal=subtotal-totalsub;
            sub.setText(""+subtotal);
            int ivas=(int) (subtotal * 0.16);
            iva.setText(""+ivas);
            int totals=subtotal-ivas;
            total.setText(""+totals);
            DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
            modelo.removeRow(fila);
            fila-=1;
            int filat=tabla1.getRowCount();
            if (filat==0){
                cbEmpleado.setEnabled(true);
                cbCliente.setEnabled(true);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No Hay Mas Productos Que Quitar");

        }
    }//GEN-LAST:event_b41ActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        // TODO add your handling code here:

        cbEmpleado.setSelectedIndex(0);
        cbCliente.setSelectedIndex(0);
        cbArticulo.setSelectedIndex(0);
        cant1.setText("");
        fact.setText("");
        sub.setText("");
        iva.setText("");
        total.setText("");
        b42.setEnabled(false);
        habilitar();

        int fil = tabla1.getRowCount();
        int x;
        for (x=fil-1;x>=0;x--) {
            DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
            modelo.removeRow(x);
        }
        try{
            String bd = "ventas";
            String login = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/"+bd;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=(Connection) DriverManager.getConnection(url,login,password);
            Statement consulta=conn.createStatement();
            ResultSet r= consulta.executeQuery("select cod_remito from  remito order by cod_remito desc");
            r.next();
            fact.setText(r.getString(1));
            int f;
            f=Integer.parseInt(fact.getText())+1;
            fact.setText(String.valueOf(f));
            r.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error en la Base de Datos") ;
        }
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void botonAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarClienteActionPerformed
        agregarcliente form= new agregarcliente ();
        form.setVisible(true);
        agregarcliente.recibeCliente.setText("4");
        form.toFront();
    }//GEN-LAST:event_botonAgregarClienteActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        cerrar();
    }//GEN-LAST:event_formWindowClosed
    
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
            java.util.logging.Logger.getLogger(frm_remito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_remito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_remito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_remito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_remito().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b41;
    private org.edisoncor.gui.button.ButtonSeven b42;
    private javax.swing.JButton botonAgregarCliente;
    private javax.swing.JButton btnAgregar;
    private org.edisoncor.gui.button.ButtonSeven btn_guardara;
    private org.edisoncor.gui.button.ButtonSeven btn_nuevo;
    private javax.swing.JButton buscare;
    private javax.swing.JButton buscare1;
    private javax.swing.JButton buscare2;
    private javax.swing.JTextField cant1;
    public static javax.swing.JComboBox cbArticulo;
    public static javax.swing.JComboBox cbCliente;
    public static javax.swing.JComboBox cbEmpleado;
    private javax.swing.JTextField fact;
    private javax.swing.JTextField fec;
    public static javax.swing.JTextField iva;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage5;
    public static javax.swing.JTextField sub;
    private javax.swing.JTable tabla1;
    public static javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
}
