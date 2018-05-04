/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vistas;

/**
 *
 * @author Manuel
 */

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


public class Frm_facturap extends javax.swing.JFrame {

    /**
     * Creates new form Frm_facturap
     */
    public void cerrar(){
    
    this.setVisible(false);
    
    
    }
   
   
    
    
    
    public Frm_facturap() {
        
        initComponents(); 
        cargarCombos();
        
        setLocationRelativeTo(null);
        b42.setEnabled(false);
        
        inabilita();

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

    Frm_facturap(menu aThis, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public void inabilita(){
    
    cb1.setEnabled(false);
    cb2.setEnabled(false);
    cb3.setEnabled(false);
    buscare.setEnabled(false);
    buscare1.setEnabled(false);
    buscare2.setEnabled(false);
    cant.setEditable(false);
     b40.setEnabled(false);
     b41.setEnabled(false);
     //b43.setEnabled(false);
     b42.setEnabled(false);
     btn_guardara.setEnabled(false);
     
     btn_guardara.setVisible(false);
     botonAgregarCliente.setEnabled(false);
     b42.setVisible(false);
     
     sub.setEnabled(false);
     iva.setEnabled(false);
    total.setEnabled(false);
    
    }
    public void habilitar(){
    
     cb1.setEnabled(true);
    cb2.setEnabled(true);
    cb3.setEnabled(true);
    buscare.setEnabled(true);
    buscare1.setEnabled(true);
    buscare2.setEnabled(true);
    cant.setEnabled(true);
    cant.setEditable(true);
     b40.setEnabled(true);
     b41.setEnabled(true);
     //b43.setEnabled(true);
     b42.setEnabled(true);
     
     btn_guardara.setVisible(true);
     botonAgregarCliente.setEnabled(true);
     b42.setVisible(true);
     
     btn_guardara.setEnabled(true);
     sub.setEnabled(true);
     iva.setEnabled(true);
    total.setEnabled(true);
    }
    
        
    public void cargarCombos(){
        try{
                Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
                Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
                Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql
                //Connection conn=(Connection) DriverManager.getConnection(url,usuario,contraseña); // esta es la verificación de la conexión con mysql
                Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                //// hasta aquí es el mismo código del guardar///////
                ResultSet   res= consulta.executeQuery("select * from articulo order by referencia");

                int i;
                cb3.removeAllItems();
                cb3.addItem("SELECCIONE ARTICULO");
                i=0;
                while(res.next()){
                    cb3.addItem(res.getString(2));
                    i++;
                }

                res= consulta.executeQuery("select * from cliente order by nombres");


                cb2.removeAllItems();
                cb2.addItem("SELECCIONE CLIENTE");
                i=0;
                while(res.next()){
                    cb2.addItem(res.getString(2)+" "+res.getString(3));
                    i++;
                }

                res= consulta.executeQuery("select * from empleado order by nombres");


                cb1.removeAllItems();
                cb1.addItem("SELECCIONE EMPLEADO");
                i=0;
                while(res.next()){
                    cb1.addItem(res.getString(2)+" "+res.getString(3));
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
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel4 = new javax.swing.JPanel();
        fec = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cb3 = new javax.swing.JComboBox();
        buscare2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cant = new javax.swing.JTextField();
        b40 = new javax.swing.JButton();
        b41 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        buscare1 = new javax.swing.JButton();
        buscare = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cb2 = new javax.swing.JComboBox();
        cb1 = new javax.swing.JComboBox();
        btn_nuevo = new org.edisoncor.gui.button.ButtonSeven();
        btn_guardara = new org.edisoncor.gui.button.ButtonSeven();
        b42 = new org.edisoncor.gui.button.ButtonSeven();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        iva = new javax.swing.JTextField();
        sub = new javax.swing.JTextField();
        botonAgregarCliente = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        panelImage5 = new org.edisoncor.gui.panel.PanelImage();
        fact = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Facturacion");
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

        jPanel4.setBackground(new java.awt.Color(153, 204, 255));
        jPanel4.setLayout(null);

        fec.setEditable(false);
        fec.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
        jPanel4.add(fec);
        fec.setBounds(784, 32, 160, 44);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setText("Fecha");
        jPanel4.add(jLabel8);
        jLabel8.setBounds(705, 44, 73, 22);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);

        jLabel9.setBackground(new java.awt.Color(239, 255, 239));
        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("Articulo");

        cb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb3ActionPerformed(evt);
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

        cant.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        cant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantKeyTyped(evt);
            }
        });

        b40.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        b40.setText("AGREGAR");
        b40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b40MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b40MouseExited(evt);
            }
        });
        b40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b40ActionPerformed(evt);
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

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Articulo", "Valor Unitario", "Valor Total"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jLabel7.setBackground(new java.awt.Color(239, 255, 239));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setText("Cliente");

        buscare1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoLupa.png"))); // NOI18N
        buscare1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscare1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscare1ActionPerformed(evt);
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

        jLabel5.setBackground(new java.awt.Color(239, 255, 239));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setText("Empleado");

        cb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb2ActionPerformed(evt);
            }
        });

        cb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb1ActionPerformed(evt);
            }
        });

        btn_nuevo.setBackground(new java.awt.Color(204, 204, 204));
        btn_nuevo.setText("Nueva");
        btn_nuevo.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_nuevo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });

        btn_guardara.setBackground(new java.awt.Color(204, 204, 204));
        btn_guardara.setText("Guardar");
        btn_guardara.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_guardara.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btn_guardara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardaraActionPerformed(evt);
            }
        });

        b42.setBackground(new java.awt.Color(204, 204, 204));
        b42.setText("Cancelar");
        b42.setColorBrillo(new java.awt.Color(255, 255, 255));
        b42.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        b42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b42ActionPerformed(evt);
            }
        });

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
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(iva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sub, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(sub, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iva, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(29, Short.MAX_VALUE))
        );

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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb3, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buscare2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(cant, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b40, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(b41, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cb2, 0, 244, Short.MAX_VALUE)
                            .addComponent(cb1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(buscare, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(buscare1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_guardara, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(b42, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buscare2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buscare1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cb3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addComponent(jLabel7)
                                .addComponent(cb2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(botonAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buscare, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cant, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(b40, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(b41, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b42, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_guardara, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7))
        );

        jPanel4.add(jPanel1);
        jPanel1.setBounds(37, 113, 919, 555);

        jPanel2.setOpaque(false);

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

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 30)); // NOI18N
        jLabel10.setText(" N°");

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 30)); // NOI18N
        jLabel2.setText("FACTURA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(panelImage5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addComponent(jLabel10)
                .addGap(31, 31, 31)
                .addComponent(fact, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(fact, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panelImage5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.add(jPanel2);
        jPanel2.setBounds(15, 16, 423, 60);

        panelImage1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 690));

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

    private void btn_guardaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardaraActionPerformed
        // declaramos las vaariables strin
        try {

            if (cb1.getSelectedItem().equals("SELECCIONE EMPLEADO")) {
                JOptionPane.showMessageDialog(null, "Falta elegir el Empleado","Advertencia",JOptionPane.WARNING_MESSAGE);
            }else {
                if (cb2.getSelectedItem().equals("SELECCIONE CLIENTE")) {
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
                            if ( tabla.getRowCount() <= 0 ) {
                                JOptionPane.showMessageDialog(null, "No Hay Ningun Producto a Facturar","Advertencia",JOptionPane.ERROR_MESSAGE);
                            }else {
                                //
                                try{
                                    Class.forName("com.mysql.jdbc.Driver");
                                    Connection conn=(Connection) DriverManager.getConnection(url,login,password);
                                    Statement consulta=conn.createStatement();
                                    Statement consulta1=conn.createStatement();
                                    int fil = tabla.getRowCount();
                                    int col = tabla.getColumnCount();

                                    int x,y;

                                    //dd
                                    consulta.executeUpdate("insert into factura (cod_factura, fecha,cod_cliente,cod_empleado) values('"+fact.getText()+"','"+fec.getText()+"','"+codigocliente+"','"+cod_empleado+"')");
                                    for (x=0;x<=fil-1;x++) {

                                        consulta1.executeUpdate("insert into referencia_factura (cod_factura,valor_unitario,valor_total,referencia,cantidad,Total) values('"+fact.getText()+"'  ,'"+tabla.getValueAt(x,2)+"','"+tabla.getValueAt(x,3)+"','"+tabla.getValueAt(x,1)+"','"+tabla.getValueAt(x,0)+"',    '"+total.getText()+"'                                            )");

                                    }

                                    JOptionPane.showMessageDialog(null,"Factura Adicionada") ;
                                    cb1.setSelectedIndex(0);
                                    cb2.setSelectedIndex(0);
                                    cb3.setSelectedIndex(0);
                                    sub.setText("");
                                    iva.setText("");
                                    total.setText("");
                                    cant.setText("");
                                    
                                    //-------------------------------
                            
                                    Connection miconexion = conexion.GetConnection();
     Map parametros = new HashMap();
     parametros.put("codf",fact.getText());
    // parametros.put("LOGO",this.getClass().getResourceAsStream("/img/repot.png"));
        
    // if (fact=""){
     
   int op=JOptionPane.showConfirmDialog(null, "DESEA IRMPRIMIR LA FACTURA","SELECCIONE UN OPCION" ,JOptionPane.YES_NO_OPTION);
   if (op==JOptionPane.YES_NO_OPTION){
   
   try {
       
           String reporte="factura.jasper";
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
                                        String aux= (String) (tabla.getValueAt(x,0));

                                        ResultSet   res= consulta.executeQuery("select cantidad from articulo where referencia='"+tabla.getValueAt(x,1)+"'");
                                        while(res.next()){
                                            cant_ex=Integer.parseInt(res.getString(1));

                                        }

                                        nueva_cant = cant_ex - Integer.parseInt(aux);

                                        consulta.executeUpdate("UPDATE articulo SET cantidad='"+nueva_cant+"' WHERE referencia='"+tabla.getValueAt(x,1)+"'");

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

                                } catch(ClassNotFoundException e){
                                    JOptionPane.showMessageDialog(null,"Error en la Base de Datos") ;                               }

                            }
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        inabilita();
    }//GEN-LAST:event_btn_guardaraActionPerformed

    private void b42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b42ActionPerformed
        // TODO add your handling code here:

       /* int cantidad=0, total_cantidad=0;

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
                int fil = tabla.getRowCount();
                int col = tabla.getColumnCount();
                //JOptionPane.showMessageDialog(null, fil);
                int x,y;

                //dd

                for (x=0;x<=fil-1;x++) {
                    String aux = (String) tabla.getValueAt(x,0);
                    total_cantidad=Integer.parseInt(aux);
                    ResultSet r= consulta.executeQuery("select cantidad from articulo where referencia = '"+tabla.getValueAt(x,1) +"'");

                    while(r.next()){
                        cantidad=Integer.parseInt(r.getString(1));

                    }

                    total_cantidad=cantidad+total_cantidad;
                    String total_cantidad2=String.valueOf(total_cantidad);
                    consulta.executeUpdate("UPDATE articulo SET cantidad='"+total_cantidad2+"' WHERE referencia='"+tabla.getValueAt(x,1)+"'");

                }

                cb1.setSelectedIndex(0);
                cb2.setSelectedIndex(0);
                cb3.setSelectedIndex(0);
                cant.setText("");
                fact.setText("");
                sub.setText("");
                iva.setText("");
                total.setText("");
                b42.setEnabled(false);
                //b43.setEnabled(true);
                b40.setEnabled(true);
                b41.setEnabled(true);

                fil = tabla.getRowCount();

                for (x=fil-1;x>=0;x--) {
                    DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                    modelo.removeRow(x);
                }

                cb1.setSelectedIndex(0);
                cb2.setSelectedIndex(0);
                cant.setText("");
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

    private void cb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb1ActionPerformed

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=(Connection) DriverManager.getConnection(url,login,password);
            Statement consulta=conn.createStatement();
            nomempleado = (String)cb1.getSelectedItem();
            cod_empleado=null;
            
            String cadena=cb1.getSelectedItem().toString();
            int i=0;
  
            
            while(cadena.charAt(i)!=' ') { 
               i++;
            }  
            String SubCadenaNombreEmpleado = cadena.substring(0,i);
            String SubCadenaApellidoEmpleado = cadena.substring(i+1,cadena.length());
            
            ResultSet rs = consulta.executeQuery("SELECT cod_empleado FROM empleado WHERE (nombres = '"+SubCadenaNombreEmpleado+"' AND apellidos = '"+SubCadenaApellidoEmpleado+"')");
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
    }//GEN-LAST:event_cb1ActionPerformed

    private void buscareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscareActionPerformed
enviar_empleado form= new enviar_empleado ();
form.setVisible(true);
form.toFront();
enviar_empleado.txt_recibeEmpleado.setText("1");
enviar_empleado.txt_recibeEmpleado.setVisible(false);
enviar_empleado.enviarEmpleado.setVisible(false);


    }//GEN-LAST:event_buscareActionPerformed

    private void cb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb2ActionPerformed
        try{

            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=(Connection) DriverManager.getConnection(url,login,password);
            Statement consulta=conn.createStatement();
            nomcliente = (String)cb2.getSelectedItem();
            codigocliente=null;
            String cadena=cb2.getSelectedItem().toString();
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
    }//GEN-LAST:event_cb2ActionPerformed

    private void buscare1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscare1ActionPerformed
enviar_cliente form= new enviar_cliente ();
form.setVisible(true);
form.btnVerFactura.setVisible(false);
form.enviar.setVisible(false);
enviar_cliente.recibe.setText("1");
form.toFront();



//// TODO add your handling code here:
    }//GEN-LAST:event_buscare1ActionPerformed

    private void cb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb3ActionPerformed
        try{

            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
            Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql

            //Connection conn=(Connection) DriverManager.getConnection(url,usuario,contraseña); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            nomproducto = (String)cb3.getSelectedItem();

            codigoproducto=null;
            //

            ResultSet rs = consulta.executeQuery("SELECT cod_articulo FROM articulo WHERE (referencia = '"+cb3.getSelectedItem()+"')");
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
        cant.requestFocus();
    }//GEN-LAST:event_cb3ActionPerformed

    private void buscare2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscare2ActionPerformed
enviar_producto form= new enviar_producto ();
form.setVisible(true);
form.toFront();
enviar_producto.txt_recibe.setText("1");
enviar_producto.txt_recibe.setVisible(false);
enviar_producto.enviarProducto.setVisible(false);


//        new tabla_articulo().setVisible(true);
//        tabla_articulo.txb.setText("1");    // TODO add your handling code here:
    }//GEN-LAST:event_buscare2ActionPerformed

    private void cantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantKeyTyped
        char c = evt.getKeyChar(); //este codigo se utiliza para solo numero en el tefil y espacio
        if (!((Character.isDigit(c))||(c==KeyEvent.VK_ENTER)||(c==KeyEvent.VK_BACK_SPACE))) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo deben digitarse Numeros");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cantKeyTyped

    private void fecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fecActionPerformed

    private void fecKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fecKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_fecKeyTyped

    private void b40MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b40MouseEntered
        //p40.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_b40MouseEntered

    private void b40MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b40MouseExited

        //p40.setBackground(new java.awt.Color(153,204,255));
    }//GEN-LAST:event_b40MouseExited

    private void b40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b40ActionPerformed
        //cb1.setEnabled(false);
        //cb2.setEnabled(false);
        int pe=0,fil = tabla.getRowCount();
        for (int x=0;x<=fil-1;x++) {
            String aux= (String) (tabla.getValueAt(x,1));
            if(aux.equals(cb3.getSelectedItem()))
            {

                pe=1;
            }
        }
        if(pe==1)
        {
            JOptionPane.showMessageDialog(null,"Este Articulo Ya Existe") ;
        }else{
            if (cb3.getSelectedItem().equals("SELECCIONE ARTICULO"))
            {
                JOptionPane.showMessageDialog(null, "Falta elegir el articulo");
            }else
            {
                if (cant.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Debe Digitar la Cantidad de articulo a Llevar");
                    cant.requestFocus();
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

                    if (cant_ex < Integer.parseInt(cant.getText()))
                    {
                        JOptionPane.showMessageDialog(null, "No existe Cantidad Disponible de Dicho Articulo, La Cantidad Dispomible es: "+cant_ex);
                    }
                    else
                    {
                        fila = tabla.getRowCount();
                        columna = tabla.getColumnCount();
                        //JOptionPane.showMessageDialog(null, fila+" "+columna);
                        DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                        modelo.addRow( new Object [] {null,null,null,null,null});
                        tabla.setValueAt(cant.getText(),fila,0);
                        tabla.setValueAt(nomproducto,fila,1);

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
                            JOptionPane.showMessageDialog(null,"Este Articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
                            //t2.setText("");

                        } catch(ClassNotFoundException e){
                            JOptionPane.showMessageDialog(null,"Error en la Base de Datos") ; // esto aparece cuando hay problemas con la conexión con mysql

                        }

                        tabla.setValueAt(precio,fila,2);
                        int totalsub=Integer.parseInt(precio)*Integer.parseInt(cant.getText());
                        subtotal=totalsub+subtotal;
                        tabla.setValueAt(totalsub,fila,3);
                        sub.setText(""+subtotal);
                        int ivas=(int) (subtotal * 0);
                        iva.setText(""+ivas);
                        int totals=subtotal+ivas;
                        total.setText(""+totals);

                        cb3.setSelectedIndex(0);
                        cant.setText("");
                    }
                }
            }
        }
    }//GEN-LAST:event_b40ActionPerformed

    private void b41MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b41MouseEntered
        //p41.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_b41MouseEntered

    private void b41MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b41MouseExited

        //p41.setBackground(new java.awt.Color(153,204,255));
    }//GEN-LAST:event_b41MouseExited

    private void b41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b41ActionPerformed
        try
        {
            String aux= String.valueOf((tabla.getValueAt(fila, 3)));
            int totalsub=Integer.parseInt(aux);
            subtotal=subtotal-totalsub;
            sub.setText(""+subtotal);
            int ivas=(int) (subtotal * 0.16);
            iva.setText(""+ivas);
            int totals=subtotal-ivas;
            total.setText(""+totals);
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            modelo.removeRow(fila);
            fila-=1;
            int filat=tabla.getRowCount();
            if (filat==0){
                cb1.setEnabled(true);
                cb2.setEnabled(true);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No Hay Mas Productos Que Quitar");

        }
    }//GEN-LAST:event_b41ActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        // TODO add your handling code here:

        
        cb1.setSelectedIndex(0);
        cb2.setSelectedIndex(0);
        cb3.setSelectedIndex(0);
        cant.setText("");
        fact.setText("");
        sub.setText("");
        iva.setText("");
        total.setText("");
        b42.setEnabled(false);
        habilitar();
        
        int fil = tabla.getRowCount();
        int x;
        for (x=fil-1;x>=0;x--) {
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
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
            ResultSet r= consulta.executeQuery("select cod_factura from  factura order by cod_factura desc");
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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrar();
// TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void buscareKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscareKeyPressed
if (evt.getKeyChar()==evt.VK_ENTER){
            
//       new tabla_empleado().setVisible(true);
//        tabla_empleado.txb.setText("1");        
}



    



// TODO add your handling code here:
    }//GEN-LAST:event_buscareKeyPressed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            
                JOptionPane.showMessageDialog(null,"Ingrese el Dato");
}


// TODO add your handling code here:
    }//GEN-LAST:event_formKeyTyped

    private void buscare2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscare2KeyTyped
if (evt.getKeyChar()==evt.VK_ENTER){
//         new tabla_articulo().setVisible(true);
//        tabla_articulo.txb.setText("1");     
           
}
                // TODO add your handling code here:
    }//GEN-LAST:event_buscare2KeyTyped

    private void botonAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarClienteActionPerformed
        agregarcliente form= new agregarcliente ();
        form.setVisible(true);
        agregarcliente.recibeCliente.setText("3");
        form.toFront();
    }//GEN-LAST:event_botonAgregarClienteActionPerformed

    
    
    
    
    
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
            java.util.logging.Logger.getLogger(Frm_facturap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_facturap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_facturap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_facturap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_facturap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b40;
    private javax.swing.JButton b41;
    private org.edisoncor.gui.button.ButtonSeven b42;
    private javax.swing.JButton botonAgregarCliente;
    private org.edisoncor.gui.button.ButtonSeven btn_guardara;
    private org.edisoncor.gui.button.ButtonSeven btn_nuevo;
    private javax.swing.JButton buscare;
    private javax.swing.JButton buscare1;
    private javax.swing.JButton buscare2;
    private javax.swing.JTextField cant;
    public static javax.swing.JComboBox cb1;
    public static javax.swing.JComboBox cb2;
    public static javax.swing.JComboBox cb3;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage5;
    public static javax.swing.JTextField sub;
    private javax.swing.JTable tabla;
    public static javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
}
