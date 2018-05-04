

package Vistas;
import static Vistas.Frm_Articulo.txtCantidad;
import static Vistas.Frm_Articulo.txtCodigo;
import static Vistas.Frm_Articulo.txtMarca;
import static Vistas.Frm_Articulo.txtPrecioBruto;
import static Vistas.Frm_Articulo.txtPrecioNeto;
import static Vistas.Frm_Articulo.txtProducto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;


/**
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static proyectogrado.empleado.tx1;
import static proyectogrado.empleado.tx2;
import static proyectogrado.empleado.tx3;
import static proyectogrado.empleado.tx4;
import static proyectogrado.empleado.tx5;
impor
/**
 *
 * @author Manuel
 */


public class Frm_Empleado extends javax.swing.JDialog {

    /**
     * Creates new form Frm_Empleado
     */
    int Select;
    String codigo,referencia,cantidad,marca,valor;
    String bd ="ventas";
             String NOMBRE;
             String login = "root";
             String password = "";
             String url = "jdbc:mysql://localhost/"+bd; // esta es la conexion
    
    public Frm_Empleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        deshabilitar();
        MostrarEmpleados();
        setLocationRelativeTo(null);
        setLocation(250,150);
    }

    
    
    public void nuevoem(){
            
            jPanel1.setVisible(true);
        
            txtnombre.setText("");
            txtapellido.setText("");
            txtcodigo.setText("");
            txtcodigo.requestFocus();
            txttelefono.setText("");
            txtdireccion.setText("");
            txtnombre.setEditable(true);
            txtapellido.setEditable(true);
            txtcodigo.setEditable(true);
            txttelefono.setEditable(true);
            txtdireccion.setEditable(true);
            
            btn_guardara.setVisible(true);
            btn_cancelara.setVisible(true);
            btn_eliminara.setVisible(true);
    }
    public void deshabilitar(){
        
            jPanel1.setVisible(false);
        
            txtnombre.setText("");
            txtapellido.setText("");
            txtcodigo.setText("");
            txttelefono.setText("");
            txtdireccion.setText("");
            txtnombre.setEditable(false);
            txtapellido.setEditable(false);
            txtcodigo.setEditable(false);
            txttelefono.setEditable(false);
            txtdireccion.setEditable(false);
            
            btn_guardara.setVisible(false);
            btn_cancelara.setVisible(false);
            btn_eliminara.setVisible(false);
    }
    
    public void MostrarEmpleados(){
  
        TableColumn  column = null;
        
         try{
                            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
                            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
                            Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql

                            //Connection conn=(Connection) DriverManager.getConnection(url,usuario,contraseña); // esta es la verificación de la conexión con mysql
                            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql


                            ResultSet r= consulta.executeQuery("select * from empleado order by cod_empleado");
                            int i,j;
                            i=0;
                            j=0;
                            //jTable1.getModel().
                            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                            tabla.setRowSorter(new TableRowSorter(modelo));
                            //   for(j=0;j<modelo.getRowCount();j++){
                            //     modelo.removeRow(0);
                            /// }
                            modelo.setNumRows(0);
                            while(r.next()){
                                modelo.addRow( new Object [] {null,null,null,null,null});
                                tabla.setValueAt(r.getString(1),i,0);
                                tabla.setValueAt(r.getString(2),i,1);
                                tabla.setValueAt(r.getString(3),i,2);
                                tabla.setValueAt(r.getString(5),i,3);
                                tabla.setValueAt(r.getString(4),i,4);
                                //    tabla.setValueAt(r.getString(3),i,2);
                                i++;
                            }
                        } catch(SQLException e){
                            JOptionPane.showMessageDialog(null,"Este empleado Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
                            //t2.setText("");


                        } catch(ClassNotFoundException e){
                            JOptionPane.showMessageDialog(null,"Error en la Base de Datos") ; // esto aparece cuando hay problemas con la conexión con mysql

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

        FiltrarResultados = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        BorrarEmpleado = new javax.swing.JMenuItem();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        txtapellido = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();
        txttelefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        panelImage3 = new org.edisoncor.gui.panel.PanelImage();
        panelImage4 = new org.edisoncor.gui.panel.PanelImage();
        btn_nuevoa = new org.edisoncor.gui.button.ButtonSeven();
        btn_guardara = new org.edisoncor.gui.button.ButtonSeven();
        btn_eliminara = new org.edisoncor.gui.button.ButtonSeven();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        checkCodigo = new javax.swing.JRadioButton();
        checkReferencia = new javax.swing.JRadioButton();
        checkMarca = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        btn_cancelara = new org.edisoncor.gui.button.ButtonSeven();

        BorrarEmpleado.setText("jMenuItem1");
        jPopupMenu1.add(BorrarEmpleado);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INGRESO DE EMPLEADOS");

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoMenumar2.png"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jLabel1.setText("INGRESO DE EMPLEADOS");
        panelImage1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 410, -1));

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(239, 255, 239));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setText(" Nombre");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));

        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 220, 40));

        txtapellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtapellidoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellidoKeyTyped(evt);
            }
        });
        jPanel1.add(txtapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 220, 40));

        jLabel8.setBackground(new java.awt.Color(239, 255, 239));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setText("Apellido");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 90, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setText("Código");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 70, 30));

        txtcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoKeyTyped(evt);
            }
        });
        jPanel1.add(txtcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 220, 40));

        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });
        jPanel1.add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 220, 40));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setText(" Teléfono");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdireccionKeyReleased(evt);
            }
        });
        jPanel1.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 220, 40));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setText(" Dirección");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 90, -1));

        panelImage1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 540, 490));

        panelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/linea.png"))); // NOI18N

        javax.swing.GroupLayout panelImage3Layout = new javax.swing.GroupLayout(panelImage3);
        panelImage3.setLayout(panelImage3Layout);
        panelImage3Layout.setHorizontalGroup(
            panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        panelImage3Layout.setVerticalGroup(
            panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        panelImage1.add(panelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 10, 60));

        panelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/profes1.png"))); // NOI18N

        javax.swing.GroupLayout panelImage4Layout = new javax.swing.GroupLayout(panelImage4);
        panelImage4.setLayout(panelImage4Layout);
        panelImage4Layout.setHorizontalGroup(
            panelImage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        panelImage4Layout.setVerticalGroup(
            panelImage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        panelImage1.add(panelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 110, 90));

        btn_nuevoa.setBackground(new java.awt.Color(0, 102, 204));
        btn_nuevoa.setText("Nuevo");
        btn_nuevoa.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_nuevoa.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btn_nuevoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoaActionPerformed(evt);
            }
        });
        panelImage1.add(btn_nuevoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 650, 130, 70));

        btn_guardara.setBackground(new java.awt.Color(204, 204, 204));
        btn_guardara.setText("Guardar");
        btn_guardara.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_guardara.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btn_guardara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardaraActionPerformed(evt);
            }
        });
        panelImage1.add(btn_guardara, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 650, 130, 70));

        btn_eliminara.setBackground(new java.awt.Color(204, 204, 204));
        btn_eliminara.setText("Eliminar");
        btn_eliminara.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_eliminara.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btn_eliminara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminaraActionPerformed(evt);
            }
        });
        panelImage1.add(btn_eliminara, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 650, 140, 70));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "APELLIDO", "TELEFONO", "DIRECCION"
            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
        }

        panelImage1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, 810, 560));

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        panelImage1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 670, 280, 50));

        FiltrarResultados.add(checkCodigo);
        checkCodigo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCodigo.setText("Código");
        checkCodigo.setOpaque(false);
        checkCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCodigoActionPerformed(evt);
            }
        });
        panelImage1.add(checkCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 630, -1, -1));

        FiltrarResultados.add(checkReferencia);
        checkReferencia.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkReferencia.setText("Nombre");
        checkReferencia.setOpaque(false);
        panelImage1.add(checkReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 630, -1, -1));

        FiltrarResultados.add(checkMarca);
        checkMarca.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkMarca.setText("Apellido");
        checkMarca.setOpaque(false);
        panelImage1.add(checkMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 630, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Buscar por");
        panelImage1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 680, -1, -1));

        btn_cancelara.setBackground(new java.awt.Color(204, 204, 204));
        btn_cancelara.setText("Cancelar");
        btn_cancelara.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_cancelara.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btn_cancelara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelaraActionPerformed(evt);
            }
        });
        panelImage1.add(btn_cancelara, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 650, 140, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 1604, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nuevoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoaActionPerformed
nuevoem() ;       
    }//GEN-LAST:event_btn_nuevoaActionPerformed

    private void btn_guardaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardaraActionPerformed
        // guardar los datos
        if (txtcodigo.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Falta la Cedula o Nit del Empleado","Advertencia",JOptionPane.WARNING_MESSAGE);
                    }else
        
        if (txtnombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta el Nombre del Empleado","Advertencia",JOptionPane.WARNING_MESSAGE);
            txtnombre.requestFocus();
        }else {
            if (txtapellido.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan los Apellidos del Empleado","Advertencia",JOptionPane.WARNING_MESSAGE);
                txtapellido.requestFocus();
            }else {
                 
                        if (txttelefono.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Falta el Telefono del Empleado","Advertencia",JOptionPane.WARNING_MESSAGE);
                        }else
                            if (txtdireccion.getText().equals("")) {
                                JOptionPane.showMessageDialog(null, "Falta la Direccion del Empleado");
                            }else        
                                try {
                    String bd = "ventas";
                    String login = "root";
                    String password = "";
                    String url = "jdbc:mysql://localhost/" + bd;
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Frm_Articulo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Connection conn = (Connection) DriverManager.getConnection(url,login,password);
                    Statement consulta = conn.createStatement();
                    
                    
                    
                    Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
                ResultSet r= consulta1.executeQuery("select cod_empleado from empleado");
                int bandera=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
                while(r.next()){
                    //JOptionPane.showMessageDialog(null,"entro a la consulta1");
                    if(txtcodigo.getText().equals(r.getString(1))){//si el codigo de la tabla es igual a algun codigo de la base de datos
                        bandera=1;
                        //JOptionPane.showMessageDialog(null,"BANDERA= "+bandera);
                        
                    }
                }
                //JOptionPane.showMessageDialog(null,"salio de la consulta1");
              
                if(bandera==0){//Si el codigo no esta inserto una nuevo registro a la base de datos
                    //JOptionPane.showMessageDialog(null,"esta por hacer un insert");
                    consulta.executeUpdate("insert into empleado (cod_empleado,nombres,apellidos,dirrecion,telefono)  values('"+txtcodigo.getText()+"','"+txtnombre.getText()+"','"+txtapellido.getText()+"','"+txtdireccion.getText()+"','"+txttelefono.getText()+"')");
                    
                }else{//si el codigo esta,actualizo la informacion del producto
                    //JOptionPane.showMessageDialog(null,"esta por hacerun update");
                     consulta.executeUpdate("UPDATE empleado SET nombres='"+txtnombre.getText()+"',apellidos='"+txtapellido.getText()+"',dirrecion='"+txtdireccion.getText()+"',telefono='"+txttelefono.getText()+"'WHERE cod_empleado='"+txtcodigo.getText()+"'");               
                }
                JOptionPane.showMessageDialog(null, "Los datos han sido Guardados Correctamente");
                nuevoem();
                MostrarEmpleados();
                } 
                 catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,"El EMPLEADO"+" "+ txtcodigo.getText()+" "+"DE NOMBRE"+" "+txtnombre.getText()+" "+"YA EXISTE");
                        System.out.println(ex);
			}
                                }}
        

      
                 
    }//GEN-LAST:event_btn_guardaraActionPerformed

    private void btn_eliminaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminaraActionPerformed

        try{
            String bd = "ventas";
            String login = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/" + bd; // esta es la conexion
            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
            Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            //// hasta aquí es el mismo código del guardar///////

            //ResultSet res= consulta.executeQuery("select * from programas where nombre = '"+t2.getText()+"'");
            consulta.executeUpdate("DELETE FROM empleado WHERE cod_empleado='"+txtcodigo.getText()+"'");
            JOptionPane.showMessageDialog(null,"Empleado Eliminado","Advertencia",JOptionPane.ERROR_MESSAGE) ;
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"No hay conexion con Mysql") ;
        }
        MostrarEmpleados();
        nuevoem();


// TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminaraActionPerformed

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased
        txtnombre.setText (txtnombre.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreKeyReleased

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped
        Character c;
        c = evt.getKeyChar();
        if(!Character.isLetter(c) && c != com.sun.glass.events.KeyEvent.VK_SPACE){
            evt.consume();
        }// TODO add your handling code here:
    }//GEN-LAST:event_txtnombreKeyTyped

    private void txtapellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidoKeyReleased
        txtapellido.setText (txtapellido.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellidoKeyReleased

    private void txtapellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidoKeyTyped
        Character c;
        c = evt.getKeyChar();
        if(!Character.isLetter(c) && c != com.sun.glass.events.KeyEvent.VK_SPACE){
            evt.consume();
        }// TODO add your handling code here:
    }//GEN-LAST:event_txtapellidoKeyTyped

    private void txtcodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyTyped
       char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigoKeyTyped

    private void txttelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyTyped
      char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();// TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoKeyTyped

    private void txtdireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyReleased
        txtdireccion.setText (txtdireccion.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionKeyReleased

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        nuevoem();
        Select=tabla.getSelectedRow();
        txtcodigo.setText( tabla.getValueAt(Select,0).toString());
        txtnombre.setText( tabla.getValueAt(Select,1).toString());
        txtapellido.setText( tabla.getValueAt(Select,2).toString());
        txttelefono.setText( tabla.getValueAt(Select,3).toString());
        txtdireccion.setText( tabla.getValueAt(Select,4).toString());
    }//GEN-LAST:event_tablaMouseClicked

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    
    DefaultTableModel dm;

/* Método filtro*/
private void filtro2(String consulta, JTable jtableBuscar){
    
    
    
    dm = (DefaultTableModel) jtableBuscar.getModel();
    TableRowSorter<DefaultTableModel> tr = new TableRowSorter(dm);
    jtableBuscar.setRowSorter(tr);
    
    int columna=0;
    // Identificamos cual es el JRadioButton seleccionado para filtrar el
    // resultado de acuerdo a los datos de la columna elegida
    if (checkCodigo.isSelected()) {
        columna = 0;
    } 
    if (checkReferencia.isSelected()) {
         columna = 1;
    }
    if (checkMarca.isSelected()) {
        columna = 2;
    }

    tr.setRowFilter(RowFilter.regexFilter(consulta, columna));
}
    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if (FiltrarResultados.getSelection()==null) {
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

    private void checkCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCodigoActionPerformed

    private void btn_cancelaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelaraActionPerformed
        deshabilitar();
    }//GEN-LAST:event_btn_cancelaraActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Frm_Empleado dialog = new Frm_Empleado(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem BorrarEmpleado;
    private javax.swing.ButtonGroup FiltrarResultados;
    private org.edisoncor.gui.button.ButtonSeven btn_cancelara;
    private org.edisoncor.gui.button.ButtonSeven btn_eliminara;
    private org.edisoncor.gui.button.ButtonSeven btn_guardara;
    private org.edisoncor.gui.button.ButtonSeven btn_nuevoa;
    private javax.swing.JRadioButton checkCodigo;
    private javax.swing.JRadioButton checkMarca;
    private javax.swing.JRadioButton checkReferencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage3;
    private org.edisoncor.gui.panel.PanelImage panelImage4;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtapellido;
    public static javax.swing.JTextField txtcodigo;
    public static javax.swing.JTextField txtdireccion;
    public static javax.swing.JTextField txtnombre;
    public static javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
