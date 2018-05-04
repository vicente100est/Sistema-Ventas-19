/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vistas;
import static Vistas.enviar_producto.txt_recibe;
import Vistas.Render;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;


public class Frm_Articulo extends javax.swing.JFrame {
Icon guardar;
Icon alerta;
Icon error;
int Select;

String codigo,referencia,cantidad,marca,valor;
String bd ="ventas";
             String NOMBRE;
             String login = "root";
             String password = "";
             String url = "jdbc:mysql://localhost/"+bd; // esta es la conexion
    /**
     * Creates new form Frm_Articulo
     */
    public Frm_Articulo(java.awt.Frame parent, boolean modal) {
        //super(parent, modal);
        
        initComponents();
        setLocationRelativeTo(null);
        setLocation(250,150);
        this.panelTabla1.setVisible(false);
        this.MostrarArticulos();
         
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
         //this.setMinimumSize(new Dimension(1550, 720)); 
        setTitle("Ingreso de Articulos");
        guardar = new ImageIcon("proyectogrado/src/proyectogrado/img/guardar.png");
        alerta = new ImageIcon("src/manuelromero/imagenes/alerta.png");
         alerta = new ImageIcon("src/manuelromero/imagenes/eliminar.png");
       
          if (menu.lb_tipo.getText().equals("Facturador")){
          btn_cancelara.setEnabled(false); 
        }
         deshabilitar(); 
        
    }

    public void MostrarArticulos(){
        
        
        TableColumn  column = null;
        /*column = tabla.getColumnModel().getColumn(0);
        column.setPreferredWidth(60);
        column = tabla.getColumnModel().getColumn(1);
        column.setPreferredWidth(260);
        column = tabla.getColumnModel().getColumn(2);
        column.setPreferredWidth(100);
        column = tabla.getColumnModel().getColumn(3);
        column.setPreferredWidth(60);
         column = tabla.getColumnModel().getColumn(4);
        column.setPreferredWidth(60);*/
        
         try{
                            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
                            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
                            Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql

                            //Connection conn=(Connection) DriverManager.getConnection(url,usuario,contraseña); // esta es la verificación de la conexión con mysql
                            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql


                            ResultSet r= consulta.executeQuery("select * from articulo order by cod_articulo");
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
                                modelo.addRow( new Object [] {null,null,null,null,null,null});
                                tabla.setValueAt(r.getString(1),i,0);
                                tabla.setValueAt(r.getString(2),i,1);
                                tabla.setValueAt(r.getString(3),i,2);
                                tabla.setValueAt(r.getString(4),i,3);
                                tabla.setValueAt(r.getString(6),i,4);
                                tabla.setValueAt(r.getString(5),i,5);
                                //    tabla.setValueAt(r.getString(3),i,2);
                                i++;
                            }
                        } catch(SQLException e){
                            JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
                            //t2.setText("");


                        } catch(ClassNotFoundException e){
                            JOptionPane.showMessageDialog(null,"Error en la Base de Datos") ; // esto aparece cuando hay problemas con la conexión con mysql

                        }
          Render r= new Render(2);
        tabla.setDefaultRenderer(Object.class,r);

    }
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
        columna = 3;
    }

    tr.setRowFilter(RowFilter.regexFilter(consulta, columna));
}
    
    
    
    
    
    
    /*private void tablaMouseClicked(java.awt.event.MouseEvent evt) {                                   
        Select=tabla.getSelectedRow();
        codigo= tabla.getValueAt(Select,0).toString();
        referencia= tabla.getValueAt(Select,1).toString();
        cantidad= tabla.getValueAt(Select,2).toString();
        marca= tabla.getValueAt(Select,3).toString();
        valor= tabla.getValueAt(Select,4).toString();
    }   */                               

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {                                       
      if(txt_recibe.getText().equals("1")){
      Frm_facturap.cb3.setSelectedItem(referencia);
        this.setVisible(false);
      }
      if(txt_recibe.getText().equals("2")){
          Frm_Articulo.txtCodigo.setText(codigo);
          Frm_Articulo.txtCantidad.setText(cantidad);
          Frm_Articulo.txtProducto.setText(referencia);
          Frm_Articulo.txtPrecioNeto.setText(marca);
          Frm_Articulo.txtMarca.setText(valor);
        this.setVisible(false);
      }
    }
    
    public void deshabilitar(){
        
        jPanel1.setVisible(false);
        
        txtCodigo.setText("");
        txtCantidad.setText("");
        txtProducto.setText("");
        txtPrecioBruto.setText("");
        txtPrecioNeto.setText("");
        txtMarca.setText("");
        
        txtCodigo.setEditable(false);
        txtCantidad.setEditable(false);
        txtProducto.setEditable(false);
        txtPrecioBruto.setEditable(false);
        txtPrecioNeto.setEditable(false);
        txtMarca.setEditable(false);
        porcentaje.setSelected(true);
        
        btn_guardara1.setVisible(false);
        btn_eliminara.setVisible(false);
        btn_cancelara.setVisible(false);
    }
    public void nuevo(){
        
        jPanel1.setVisible(true);
        
        txtCodigo.setText("");
        txtCodigo.requestFocus();
        txtCantidad.setText("");
        txtProducto.setText("");
        txtPrecioBruto.setText("");
        txtPrecioNeto.setText("");
        txtMarca.setText("");
        txtCodigo.setEditable(true);
        txtCantidad.setEditable(true);
        txtProducto.setEditable(true);
        txtPrecioBruto.setEditable(true);
        txtPrecioNeto.setEditable(true);
        txtMarca.setEditable(true); 
        
        btn_guardara1.setVisible(true);
        btn_eliminara.setVisible(true);
        btn_cancelara.setVisible(true);
        
        }
     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FiltrarResultados = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItemBorrar = new javax.swing.JMenuItem();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        txtCodigo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPrecioNeto = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtPrecioBruto = new javax.swing.JTextField();
        txtPorcentaje = new javax.swing.JTextField();
        porcentaje = new javax.swing.JRadioButton();
        panelImage3 = new org.edisoncor.gui.panel.PanelImage();
        btn_guardara1 = new org.edisoncor.gui.button.ButtonSeven();
        btnImportar = new org.edisoncor.gui.button.ButtonSeven();
        btn_cancelara = new org.edisoncor.gui.button.ButtonSeven();
        btn_nuevoa2 = new org.edisoncor.gui.button.ButtonSeven();
        jLabel1 = new javax.swing.JLabel();
        panelTabla1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        checkCodigo = new javax.swing.JRadioButton();
        checkMarca = new javax.swing.JRadioButton();
        checkReferencia = new javax.swing.JRadioButton();
        btnExportar = new org.edisoncor.gui.button.ButtonSeven();
        btn_buscar_a3 = new org.edisoncor.gui.button.ButtonSeven();
        btn_eliminara = new org.edisoncor.gui.button.ButtonSeven();
        jLabel2 = new javax.swing.JLabel();

        jMenuItemBorrar.setText("Eliminar articulo");
        jMenuItemBorrar.setName(""); // NOI18N
        jMenuItemBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemBorrar);
        jMenuItemBorrar.getAccessibleContext().setAccessibleName("");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelImage1.setBackground(new java.awt.Color(255, 255, 255));
        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoMenumar2.png"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 190, 40));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setText("Codigo");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 70, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("Stock");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 70, -1));

        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });
        jPanel1.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 190, 40));

        txtMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarcaActionPerformed(evt);
            }
        });
        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMarcaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaKeyTyped(evt);
            }
        });
        jPanel1.add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 190, 40));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setText("Marca");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 60, -1));

        jLabel8.setBackground(new java.awt.Color(239, 255, 239));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setText("     Producto");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setText("Precio Bruto");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        txtPrecioNeto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioNetoKeyTyped(evt);
            }
        });
        jPanel1.add(txtPrecioNeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 190, 40));

        txtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductoKeyReleased(evt);
            }
        });
        jPanel1.add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 190, 40));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setText("Precio Neto");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, -1));

        txtPrecioBruto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioBrutoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioBrutoKeyTyped(evt);
            }
        });
        jPanel1.add(txtPrecioBruto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 190, 40));

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
        jPanel1.add(txtPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, 40, 40));

        porcentaje.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        porcentaje.setText("%");
        porcentaje.setOpaque(false);
        porcentaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porcentajeActionPerformed(evt);
            }
        });
        jPanel1.add(porcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 320, -1, 40));

        panelImage1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 590, 450));

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

        panelImage1.add(panelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 10, 60));

        btn_guardara1.setBackground(new java.awt.Color(204, 204, 204));
        btn_guardara1.setText("Guardar");
        btn_guardara1.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_guardara1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btn_guardara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardara1ActionPerformed(evt);
            }
        });
        panelImage1.add(btn_guardara1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 630, 140, 80));

        btnImportar.setBackground(new java.awt.Color(204, 204, 204));
        btnImportar.setBorder(null);
        btnImportar.setText("Importar");
        btnImportar.setColorBrillo(new java.awt.Color(51, 51, 51));
        btnImportar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarActionPerformed(evt);
            }
        });
        panelImage1.add(btnImportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 630, 130, 80));

        btn_cancelara.setBackground(new java.awt.Color(204, 204, 204));
        btn_cancelara.setText("Cancelar");
        btn_cancelara.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_cancelara.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btn_cancelara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelaraActionPerformed(evt);
            }
        });
        panelImage1.add(btn_cancelara, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 630, 140, 80));

        btn_nuevoa2.setBackground(new java.awt.Color(102, 153, 255));
        btn_nuevoa2.setText("Nuevo");
        btn_nuevoa2.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_nuevoa2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btn_nuevoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoa2ActionPerformed(evt);
            }
        });
        panelImage1.add(btn_nuevoa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 630, 140, 80));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jLabel1.setText("INGRESO DE ARTICULOS");
        panelImage1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 380, -1));

        tabla1.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title5"
            }
        ));
        jScrollPane2.setViewportView(tabla1);
        if (tabla1.getColumnModel().getColumnCount() > 0) {
            tabla1.getColumnModel().getColumn(0).setResizable(false);
            tabla1.getColumnModel().getColumn(1).setResizable(false);
            tabla1.getColumnModel().getColumn(2).setResizable(false);
            tabla1.getColumnModel().getColumn(3).setResizable(false);
            tabla1.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout panelTabla1Layout = new javax.swing.GroupLayout(panelTabla1);
        panelTabla1.setLayout(panelTabla1Layout);
        panelTabla1Layout.setHorizontalGroup(
            panelTabla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
        );
        panelTabla1Layout.setVerticalGroup(
            panelTabla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTabla1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelImage1.add(panelTabla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 860, 510));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "PRODUCTO", "STOCK", "MARCA", "PRECIO BRUTO", "PRECIO NETO"
            }
        ));
        tabla.setComponentPopupMenu(jPopupMenu1);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(5).setResizable(false);
        }

        panelImage1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, 860, 550));

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
        panelImage1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 650, 280, 60));

        FiltrarResultados.add(checkCodigo);
        checkCodigo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCodigo.setText("Codigo");
        checkCodigo.setOpaque(false);
        checkCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCodigoActionPerformed(evt);
            }
        });
        panelImage1.add(checkCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 610, -1, -1));

        FiltrarResultados.add(checkMarca);
        checkMarca.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkMarca.setText("Marca");
        checkMarca.setOpaque(false);
        panelImage1.add(checkMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 610, -1, -1));

        FiltrarResultados.add(checkReferencia);
        checkReferencia.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkReferencia.setText("Producto");
        checkReferencia.setOpaque(false);
        panelImage1.add(checkReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 610, -1, -1));

        btnExportar.setBackground(new java.awt.Color(204, 204, 204));
        btnExportar.setBorder(null);
        btnExportar.setText("Exportar");
        btnExportar.setColorBrillo(new java.awt.Color(51, 51, 51));
        btnExportar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        panelImage1.add(btnExportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 630, 130, 80));

        btn_buscar_a3.setBackground(new java.awt.Color(204, 204, 204));
        btn_buscar_a3.setBorder(null);
        btn_buscar_a3.setText("Guardar Tabla");
        btn_buscar_a3.setColorBrillo(new java.awt.Color(51, 51, 51));
        btn_buscar_a3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btn_buscar_a3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_a3ActionPerformed(evt);
            }
        });
        panelImage1.add(btn_buscar_a3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 630, 140, 80));

        btn_eliminara.setBackground(new java.awt.Color(204, 204, 204));
        btn_eliminara.setText("Eliminar");
        btn_eliminara.setColorBrillo(new java.awt.Color(255, 255, 255));
        btn_eliminara.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btn_eliminara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminaraActionPerformed(evt);
            }
        });
        panelImage1.add(btn_eliminara, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 630, 140, 80));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Buscar por");
        panelImage1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 660, -1, -1));

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

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();// TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyReleased
        txtMarca.setText (txtMarca.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaKeyReleased

    private void txtMarcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyTyped
        if (evt.getKeyChar()==evt.VK_ENTER){
            if(txtMarca.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Ingrese el Dato","Advertencia",JOptionPane.WARNING_MESSAGE);
            else
            txtPrecioNeto.requestFocus();
        }                 // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaKeyTyped

    private void txtPrecioNetoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioNetoKeyTyped
       char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();     // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioNetoKeyTyped

    private void txtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaActionPerformed

    private void btn_guardara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardara1ActionPerformed
 
// codigo para guardar
        
        if (txtCodigo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta el Codigo","Datos Guardados",JOptionPane.WARNING_MESSAGE);
            txtCodigo.requestFocus();
            
        }else {
            if (txtCantidad.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan la cantidad de articulo","Advertencia",JOptionPane.WARNING_MESSAGE);
                txtCantidad.requestFocus();
            }else {
                if (txtProducto.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Falta la referencia del articulo","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else
                if (txtPrecioNeto.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Falta la marca del articulo","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else
                if (txtMarca.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Falta el valor del articulo","Advertencia",JOptionPane.WARNING_MESSAGE);
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
                ResultSet r= consulta1.executeQuery("select cod_articulo from articulo");
                int bandera=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
                while(r.next()){
                    //JOptionPane.showMessageDialog(null,"entro a la consulta1");
                    if(txtCodigo.getText().equals(r.getString(1))){//si el codigo de la tabla es igual a algun codigo de la base de datos
                        bandera=1;
                        //JOptionPane.showMessageDialog(null,"BANDERA= "+bandera);
                        
                    }
                }
                //JOptionPane.showMessageDialog(null,"salio de la consulta1");
              
                if(bandera==0){//Si el codigo no esta inserto una nuevo registro a la base de datos
                    //JOptionPane.showMessageDialog(null,"esta por hacer un insert");
                    consulta.executeUpdate("insert into articulo (cod_articulo,referencia,cantidad,marca,valor,valor_bruto)  values('"+txtCodigo.getText()+"','"+txtProducto.getText()+"','"+txtCantidad.getText()+"','"+txtMarca.getText()+"','"+txtPrecioNeto.getText()+"','"+txtPrecioBruto.getText()+"')");
                    
                }else{//si el codigo esta,actualizo la informacion del producto
                    //JOptionPane.showMessageDialog(null,"esta por hacerun update");
                     consulta.executeUpdate("UPDATE articulo SET referencia='"+txtProducto.getText()+"',cantidad='"+txtCantidad.getText()+"',marca='"+txtMarca.getText()+"',valor='"+txtPrecioNeto.getText()+"',valor_bruto='"+txtPrecioBruto.getText()+"'WHERE cod_articulo='"+txtCodigo.getText()+"'");               
                }
                JOptionPane.showMessageDialog(null, "Los datos han sido Guardados Correctamente");
                nuevo();
                MostrarArticulos();
                } 
                 catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,"El PRODUCTO"+" "+ txtCodigo.getText()+" "+"DE NOMBRE"+" "+txtProducto.getText()+" "+"YA EXISTE");
                        
			}
            }
        }
          
        
        
        
        
        
    }//GEN-LAST:event_btn_guardara1ActionPerformed

    private void btn_cancelaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelaraActionPerformed
        // eliminar registro
      deshabilitar();
            
    }//GEN-LAST:event_btn_cancelaraActionPerformed

    private void btn_nuevoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoa2ActionPerformed
     
     nuevo();   
// TODO add your handling code here:
    }//GEN-LAST:event_btn_nuevoa2ActionPerformed

    private void txtProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoKeyReleased

         txtProducto.setText (txtProducto.getText().toUpperCase()); 

// TODO add your handling code here:
    }//GEN-LAST:event_txtProductoKeyReleased

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void checkCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCodigoActionPerformed

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

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        nuevo();
        Select=tabla.getSelectedRow();
        txtCodigo.setText( tabla.getValueAt(Select,0).toString());
        txtProducto.setText( tabla.getValueAt(Select,1).toString());
        txtCantidad.setText( tabla.getValueAt(Select,2).toString());
        txtMarca.setText( tabla.getValueAt(Select,3).toString());
        txtPrecioBruto.setText( tabla.getValueAt(Select,4).toString());
        txtPrecioNeto.setText( tabla.getValueAt(Select,5).toString());
    }//GEN-LAST:event_tablaMouseClicked

    
    JFileChooser selecArchivo = new JFileChooser();
    File archivo;
    public void AgregarFiltro(){
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
    }
    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        ModeloExcel modeloE = new ModeloExcel(); 
        int contAccion=0;   
   
        contAccion++;
        if(contAccion==1)AgregarFiltro();
        
        if(evt.getSource() == btnExportar){
            if(selecArchivo.showDialog(null, "Exportar")==JFileChooser.APPROVE_OPTION){
                archivo=selecArchivo.getSelectedFile();
                if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                    JOptionPane.showMessageDialog(null, modeloE.Exportar(archivo,tabla) + "\n Formato ."+ archivo.getName().substring(archivo.getName().lastIndexOf(".")+1));
                }else{
                    JOptionPane.showMessageDialog(null, "Elija un formato valido.");
                }
            }
        }
    }//GEN-LAST:event_btnExportarActionPerformed

    public static boolean esNumerico(String cadena){
        try{
            Integer.parseInt(cadena);
                return true;
        }catch(NumberFormatException e){
                return false;
        }
    }
    private void btnImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarActionPerformed
         ModeloExcel modeloE = new ModeloExcel(); 
        int contAccion=0;  
   
        contAccion++;
        if(contAccion==1)AgregarFiltro();
        
       try{
        if(evt.getSource() == btnImportar){
            if(selecArchivo.showDialog(null, "Seleccionar archivo")==JFileChooser.APPROVE_OPTION){
                archivo=selecArchivo.getSelectedFile();
                if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                    JOptionPane.showMessageDialog(null, 
                           modeloE.Importar(archivo,tabla1) + "\n Formato ."+ archivo.getName().substring(archivo.getName().lastIndexOf(".")+1), 
                           "IMPORTAR EXCEL", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Elija un formato valido.");
                }
            }
        }
        TableColumn  column = null;
       }catch(Exception e){
           
       }
       GuardarTabla();
    }//GEN-LAST:event_btnImportarActionPerformed

    public void GuardarTabla(){
        this.panelTabla1.setVisible(false);
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
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            Statement consulta2=conn.createStatement();
           // Connection conn = (Connection) DriverManager.getConnection(url,login,password);
            //Statement consulta = conn.createStatement();
            
            Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
            
            
            for(int i=0;i<tabla1.getRowCount();i++){
                //JOptionPane.showMessageDialog(null,"entroa la tabla");
                ResultSet r= consulta1.executeQuery("select cod_articulo from articulo");
                int bandera=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
                while(r.next()){
                    //JOptionPane.showMessageDialog(null,"entro a la consulta1");
                    if(tabla1.getValueAt(i,0).toString().equals(r.getString(1))){//si el codigo de la tabla es igual a algun codigo de la base de datos
                        bandera=1;
                        //JOptionPane.showMessageDialog(null,"BANDERA= "+bandera);
                        
                    }
                }
                //JOptionPane.showMessageDialog(null,"salio de la consulta1");
              
                if(bandera==0){//Si el codigo no esta inserto una nuevo registro a la base de datos
                    //JOptionPane.showMessageDialog(null,"esta por hacer un insert");
                    consulta2.executeUpdate("insert into articulo (cod_articulo,referencia,cantidad,marca,valor,valor_bruto)  values('"+tabla1.getValueAt(i,0).toString().toUpperCase()+"','"+tabla1.getValueAt(i,1).toString().toUpperCase()+"','"+tabla1.getValueAt(i,2).toString().toUpperCase()+"','"+tabla1.getValueAt(i,3).toString().toUpperCase()+"','"+tabla1.getValueAt(i,5).toString().toUpperCase()+"','"+tabla1.getValueAt(i,4).toString().toUpperCase()+"')");
                }else{//si el codigo esta,actualizo la informacion del producto
                    //JOptionPane.showMessageDialog(null,"esta por hacerun update");
                    consulta.executeUpdate("UPDATE articulo SET referencia='"+tabla1.getValueAt(i,1).toString().toUpperCase()+"',cantidad='"+tabla1.getValueAt(i,2).toString().toUpperCase()+"',marca='"+tabla1.getValueAt(i,3).toString().toUpperCase()+"',valor='"+tabla1.getValueAt(i,5).toString().toUpperCase()+"',valor_bruto='"+tabla1.getValueAt(i,4).toString().toUpperCase()+"'WHERE cod_articulo='"+tabla1.getValueAt(i,0).toString().toUpperCase()+"'");    
                }  
            }
            nuevo();
            JOptionPane.showMessageDialog(null,"LA TABLA SE GUARDO CORRECTAMENTE"); 
            MostrarArticulos();
        } 
        catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"ERROR AL CONECTARCE A LA BASE DE DATOS");
	}
    }
    
    
    private void btn_buscar_a3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_a3ActionPerformed
        this.panelTabla1.setVisible(false);
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
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            Statement consulta2=conn.createStatement();
           // Connection conn = (Connection) DriverManager.getConnection(url,login,password);
            //Statement consulta = conn.createStatement();
            
            Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
            
            
            for(int i=0;i<tabla.getRowCount();i++){
                //JOptionPane.showMessageDialog(null,"entroa la tabla");
                ResultSet r= consulta1.executeQuery("select cod_articulo from articulo");
                int bandera=0;//para saber si el codigo esta o no en la base de datos (si sale con cero no esta)
                while(r.next()){
                    //JOptionPane.showMessageDialog(null,"entro a la consulta1");
                    if(tabla.getValueAt(i,0).toString().equals(r.getString(1))){//si el codigo de la tabla es igual a algun codigo de la base de datos
                        bandera=1;
                        //JOptionPane.showMessageDialog(null,"BANDERA= "+bandera);
                        
                    }
                }
                //JOptionPane.showMessageDialog(null,"salio de la consulta1");
                if(bandera==0){//Si el codigo no esta inserto una nuevo registro a la base de datos
                    //JOptionPane.showMessageDialog(null,"esta por hacer un insert");
                    consulta2.executeUpdate("insert into articulo (cod_articulo,referencia,cantidad,marca,valor)  values('"+tabla.getValueAt(i,0).toString().toUpperCase()+"','"+tabla.getValueAt(i,1).toString().toUpperCase()+"','"+tabla.getValueAt(i,2).toString().toUpperCase()+"','"+tabla.getValueAt(i,3).toString().toUpperCase()+"','"+tabla.getValueAt(i,4).toString().toUpperCase()+"')");
                }else{//si el codigo esta,actualizo la informacion del producto
                    //JOptionPane.showMessageDialog(null,"esta por hacerun update");
                    consulta.executeUpdate("UPDATE articulo SET referencia='"+tabla.getValueAt(i,1).toString().toUpperCase()+"',cantidad='"+tabla.getValueAt(i,2).toString().toUpperCase()+"',marca='"+tabla.getValueAt(i,3).toString().toUpperCase()+"',valor='"+tabla.getValueAt(i,4).toString().toUpperCase()+"'WHERE cod_articulo='"+tabla.getValueAt(i,0).toString().toUpperCase()+"'");
                    
                }
            }
            
            JOptionPane.showMessageDialog(null,"LA TABLA SE GUARDO CORRECTAMENTE");
            nuevo();
            MostrarArticulos();
        } 
        catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"ERROR AL CONECTARCE A LA BASE DE DATOS");
	}
    }//GEN-LAST:event_btn_buscar_a3ActionPerformed

    private void txtPrecioBrutoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioBrutoKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume(); 
    }//GEN-LAST:event_txtPrecioBrutoKeyTyped

    private void porcentajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porcentajeActionPerformed
        if(!porcentaje.isSelected()){
            //txtPrecioNeto.setText("");
            txtPrecioNeto.setEditable(true);
            txtPorcentaje.setEditable(false);
            txtPorcentaje.setText("");
        }else{
            if(txtPorcentaje.getText().equals("")){
                txtPrecioNeto.setEditable(false);
                txtPorcentaje.setEditable(true);
                txtPorcentaje.setText("");
                /* txtPrecioNeto.setEditable(true);*/
                //txtPrecioNeto.setText("");
            }else{
               txtPrecioNeto.setEditable(false); 
            }
        }
    }//GEN-LAST:event_porcentajeActionPerformed

    private void txtPorcentajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPorcentajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeActionPerformed

    private void txtPrecioBrutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioBrutoKeyReleased
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
                /*txtPrecioNeto.setEditable(true);*/
                txtPrecioNeto.setText("");
            }
            else{
                if(!txtPrecioBruto.getText().equals("") && !txtPorcentaje.getText().equals("")){
                    txtPrecioNeto.setEditable(false);
                    int preciob = Integer.parseInt(txtPrecioBruto.getText());
                    int porcentaje = Integer.parseInt(txtPorcentaje.getText());
                    int precioneto = preciob + (preciob * porcentaje)/100;
                    String precion = String.valueOf(precioneto);
                    txtPrecioNeto.setText(precion);
                }else{
                    txtPrecioNeto.setEditable(false);
                    txtPrecioNeto.setText("");
                }
            }
        }
    }//GEN-LAST:event_txtPrecioBrutoKeyReleased

    private void txtPorcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyReleased
        if(!porcentaje.isSelected()){
            txtPrecioNeto.setEditable(true);
            txtPrecioNeto.setText("");
            txtPorcentaje.setEditable(false);
            //txtPorcentaje.setText("");
        }else{
            if(txtPorcentaje.getText().equals("")){
                txtPorcentaje.setEditable(true);
                txtPorcentaje.setText("");
                txtPrecioNeto.setEditable(true);
                //txtPrecioNeto.setText("");
            }
        else{
            if(!txtPrecioBruto.getText().equals("") && !txtPorcentaje.getText().equals("")){
                txtPrecioNeto.setEditable(false);
                int preciob = Integer.parseInt(txtPrecioBruto.getText());
                int porcentaje = Integer.parseInt(txtPorcentaje.getText());
                int precioneto = preciob + (preciob * porcentaje)/100;
                String precion = String.valueOf(precioneto);
                txtPrecioNeto.setText(precion);
            }else{
                txtPrecioNeto.setEditable(false);
                //txtPrecioNeto.setText("");
            }
        }
        }
        
    }//GEN-LAST:event_txtPorcentajeKeyReleased

    private void jMenuItemBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBorrarActionPerformed
        if(JOptionPane.showConfirmDialog(null,"Desea eliminar el articulo?","Eliminar articulo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
        try{
            String bd = "ventas";
            String login = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/" + bd; // esta es la conexion
            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
            Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            consulta.executeUpdate("DELETE FROM articulo WHERE cod_articulo='"+txtCodigo.getText()+"'");
            //JOptionPane.showMessageDialog(null,"Articulo Eliminado") ;
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL","Error",JOptionPane.ERROR_MESSAGE) ;
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"No hay conexion con Mysql","Error",JOptionPane.ERROR_MESSAGE) ;
        }
       nuevo();
       MostrarArticulos();
      }
    }//GEN-LAST:event_jMenuItemBorrarActionPerformed

    private void txtPorcentajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')
           evt.consume();
    }//GEN-LAST:event_txtPorcentajeKeyTyped

    private void btn_eliminaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminaraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminaraActionPerformed


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
            java.util.logging.Logger.getLogger(Frm_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Frm_Articulo dialog = new Frm_Articulo(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup FiltrarResultados;
    private org.edisoncor.gui.button.ButtonSeven btnExportar;
    private org.edisoncor.gui.button.ButtonSeven btnImportar;
    private org.edisoncor.gui.button.ButtonSeven btn_buscar_a3;
    public static org.edisoncor.gui.button.ButtonSeven btn_cancelara;
    public static org.edisoncor.gui.button.ButtonSeven btn_eliminara;
    private org.edisoncor.gui.button.ButtonSeven btn_guardara1;
    private org.edisoncor.gui.button.ButtonSeven btn_nuevoa2;
    private javax.swing.JRadioButton checkCodigo;
    private javax.swing.JRadioButton checkMarca;
    private javax.swing.JRadioButton checkReferencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItemBorrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage3;
    private javax.swing.JPanel panelTabla1;
    private javax.swing.JRadioButton porcentaje;
    public javax.swing.JTable tabla;
    private javax.swing.JTable tabla1;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtPorcentaje;
    public static javax.swing.JTextField txtPrecioBruto;
    public static javax.swing.JTextField txtPrecioNeto;
    public static javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables
}
