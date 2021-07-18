

package Vistas;
import java.awt.Color;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class enviar_producto extends javax.swing.JFrame {
    int Select;
    String codigo,referencia,cantidad,marca,valor,valorBruto;
    String NOMBRE;
    String orden="NOMBRE";
    String forma="ASC";

    public enviar_producto() {
        initComponents();
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        txt_recibe.setVisible(false);
         
        setLocationRelativeTo(null);
        checkCodigo.setSelected(true);
        txtBuscar.requestFocus();
        
        enviarProducto.setVisible(false);
        
        JTableHeader th; 
        th = tabla.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
  
        TableColumn  column = null;
        column = tabla.getColumnModel().getColumn(0);
        column.setPreferredWidth(60);
        
        tabla.getColumnModel().getColumn(1).setMaxWidth(450);
        tabla.getColumnModel().getColumn(1).setMinWidth(450);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(450);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(450);
        
        tabla.getColumnModel().getColumn(3).setMaxWidth(80);
        tabla.getColumnModel().getColumn(3).setMinWidth(80);
        tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(80);
        tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(80);
        
        tabla.getColumnModel().getColumn(5).setMaxWidth(0);
        tabla.getColumnModel().getColumn(5).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);

        column = tabla.getColumnModel().getColumn(2);
        column.setPreferredWidth(100);

        column = tabla.getColumnModel().getColumn(4);
        column.setPreferredWidth(60);
        
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            
            ResultSet r= consulta.executeQuery("select cod_articulo,referencia,marca,cantidad,total_con_iva,valor_bruto from articulo where estado='ACTIVO' and categoria='Articulos' order by referencia");
            int i;
            i=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            tabla.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null,null,null});
                tabla.setValueAt(r.getString(1),i,0);
                tabla.setValueAt(r.getString(2),i,1);
                tabla.setValueAt(r.getString(3),i,2);
                tabla.setValueAt(r.getString(4),i,3);
                tabla.setValueAt(r.getString(5),i,4);
                tabla.setValueAt(r.getString(6),i,5);
                i++;
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
        }
        Render_Color_Articulos r= new Render_Color_Articulos(3);
        tabla.setDefaultRenderer(Object.class,r);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FiltrarResultados = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        checkMarca = new javax.swing.JRadioButton();
        checkDescripcion = new javax.swing.JRadioButton();
        checkCodigo = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        enviarProducto = new javax.swing.JButton();
        txt_recibe = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscar.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtBuscar.setBorder(null);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 310, 50));

        FiltrarResultados.add(checkMarca);
        checkMarca.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkMarca.setText("Marca");
        checkMarca.setOpaque(false);
        jPanel1.add(checkMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        FiltrarResultados.add(checkDescripcion);
        checkDescripcion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkDescripcion.setText("Descripción");
        checkDescripcion.setOpaque(false);
        jPanel1.add(checkDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        FiltrarResultados.add(checkCodigo);
        checkCodigo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCodigo.setText("Codigo");
        checkCodigo.setOpaque(false);
        jPanel1.add(checkCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));

        tabla.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "CODIGO", "DESCRIPCION", "MARCA", "STOCK", "VALOR VENTA", "VALOR COSTO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 990, 510));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel9.setText("ARTICULOS");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 40, -1, 60));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoArticulosXs-01.png"))); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 50, 70));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 320, 20));

        enviarProducto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        enviarProducto.setText("ENVIAR");
        enviarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarProductoActionPerformed(evt);
            }
        });
        jPanel1.add(enviarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 630, 87, 33));
        jPanel1.add(txt_recibe, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 630, 44, -1));

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem1.setText("Cerrar ventana");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1032, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyTyped

     
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
        }else  if (checkDescripcion.isSelected()) {
                    columna = 1;
                }else if (checkMarca.isSelected()) {
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
            filtro2(txtBuscar.getText().toUpperCase(),tabla);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void enviarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarProductoActionPerformed
        if(txt_recibe.getText().equals("1")){
            Factura_Venta.comboArticulo.setSelectedItem(referencia);
            Factura_Venta.comboArticulo.setSelectedItem(referencia);
            this.setVisible(false);
        }
        if(txt_recibe.getText().equals("2")){
            Registrar_Articulos.txtCodigo.setText(codigo);
            Registrar_Articulos.txtCantidad.setText(cantidad);
            Registrar_Articulos.txtProducto.setText(referencia);
            Registrar_Articulos.txtPrecioVentaSinIva.setText(marca);
            this.setVisible(false);
        }
    }//GEN-LAST:event_enviarProductoActionPerformed

    private void tablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyPressed
        int fila= tabla.getSelectedRow();

        if(fila>=0){
            Select=tabla.getSelectedRow();
            codigo= tabla.getValueAt(Select,0).toString();
            referencia= tabla.getValueAt(Select,1).toString();
            cantidad= tabla.getValueAt(Select,2).toString();
            valor= tabla.getValueAt(Select,4).toString();        
        }else{
            JOptionPane.showMessageDialog(null,"No selecciono ninguna fila");
        }
        
        if(txt_recibe.getText().equals("1")){
            Factura_Venta.comboArticulo.setSelectedItem(referencia);
            Factura_Venta.txtCodigoArticulo1.setText(codigo);
            this.setVisible(false);
        }else{
            if(txt_recibe.getText().equals("10")){
                Factura_Remito.comboArticulos.setSelectedItem(referencia);
                Factura_Remito.txtCodigo_Articulo.setText(codigo);
                this.setVisible(false);
            }else{
                if(txt_recibe.getText().equals("11")){
                    Factura_Presupuesto.comboArticulos.setSelectedItem(referencia);
                    Factura_Presupuesto.txtCodigoArticulo.setText(codigo);
                    this.setVisible(false);
                }else{
                    if(txt_recibe.getText().equals("2")){
                        Factura_Compra.comboArticulos.setSelectedItem(referencia);
                        Factura_Compra.txtCodigoArticulo1.setText(codigo);
                        this.setVisible(false);
                    }else{
                        if(txt_recibe.getText().equals("22")){
                            Map parametros = new HashMap();

                            Select=tabla.getSelectedRow();
                            String codigoArticulo= tabla.getValueAt(Select,0).toString();

                            parametros.put("codigo",codigoArticulo);
                            Connection miconexion = conexion.ObtenerConexion();

                            try {
                                String reporte="sarticulo.jasper";
                                JasperPrint informe =JasperFillManager.fillReport(reporte,parametros,miconexion);
                                JasperViewer ventanavisor=new JasperViewer(informe,false);
                                ventanavisor.setTitle("Reporte articulo");
                                ventanavisor.setVisible(true);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, e.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tablaKeyPressed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        int fila= tabla.getSelectedRow();
        String valorCosto="";
        if(fila>=0){
            Select=tabla.getSelectedRow();
            codigo= tabla.getValueAt(Select,0).toString();
            referencia= tabla.getValueAt(Select,1).toString();
            cantidad= tabla.getValueAt(Select,2).toString();
            valor= tabla.getValueAt(Select,4).toString(); 
        }else{
            JOptionPane.showMessageDialog(null,"No selecciono ninguna fila");
        }
        
        if(txt_recibe.getText().equals("1")){
            Factura_Venta.comboArticulo.setSelectedItem(referencia);
            Factura_Venta.txtCodigoArticulo1.setText(codigo);
            this.setVisible(false);
        }else{ if(txt_recibe.getText().equals("2")){
                Factura_Compra.comboArticulos.setSelectedItem(referencia);
                Factura_Compra.txtCodigoArticulo1.setText(codigo);
                this.setVisible(false);
            }else{
                if(txt_recibe.getText().equals("10")){
                    Factura_Remito.comboArticulos.setSelectedItem(referencia);
                    Factura_Remito.txtCodigo_Articulo.setText(codigo);
                    this.setVisible(false);
                }else{
                    if(txt_recibe.getText().equals("11")){
                        Factura_Nota_Credito_Compra.comboArticulo.setSelectedItem(referencia);
                        Factura_Nota_Credito_Compra.txtCodigoArticulo.setText(codigo);
                        this.setVisible(false);
                    }else{ if(txt_recibe.getText().equals("12")){
                            Factura_Nota_Credito_Venta.comboArticulo.setSelectedItem(referencia);
                            Factura_Nota_Credito_Venta.txtCodigoArticulo.setText(codigo);
                            this.setVisible(false);
                        }else{
                            if(txt_recibe.getText().equals("13")){
                                Factura_Presupuesto.comboArticulos.setSelectedItem(referencia);
                                Factura_Presupuesto.txtCodigoArticulo.setText(codigo);
                                this.setVisible(false);
                            }else{
                                    if(txt_recibe.getText().equals("22")){               
                                        Map parametros = new HashMap();
                                        Select=tabla.getSelectedRow();
                                        String codigoArticulo= tabla.getValueAt(Select,0).toString();

                                        parametros.put("codigo",codigoArticulo);
                                        Connection miconexion = conexion.ObtenerConexion();

                                        try{
                                            String reporte="sarticulo.jasper";
                                            JasperPrint informe =JasperFillManager.fillReport(reporte,parametros,miconexion);
                                            JasperViewer ventanavisor=new JasperViewer(informe,false);
                                            ventanavisor.setTitle("Reporte articulo");
                                            ventanavisor.setVisible(true);
                                        }catch (Exception e) {
                                            JOptionPane.showMessageDialog(this, e.getMessage());
                                        }
                                    }else{
                                        if(txt_recibe.getText().equals("22")){               
                                        Map parametros = new HashMap();
                                        Select=tabla.getSelectedRow();
                                        String codigoArticulo= tabla.getValueAt(Select,0).toString();

                                        parametros.put("codigo",codigoArticulo);
                                        Connection miconexion = conexion.ObtenerConexion();

                                        try{
                                            String reporte="sarticulo.jasper";
                                            JasperPrint informe =JasperFillManager.fillReport(reporte,parametros,miconexion);
                                            JasperViewer ventanavisor=new JasperViewer(informe,false);
                                            ventanavisor.setTitle("Reporte articulo");
                                            ventanavisor.setVisible(true);
                                        }catch (Exception e) {
                                            JOptionPane.showMessageDialog(this, e.getMessage());
                                        }
                                    }else{

                                        if(txt_recibe.getText().equals("24")){               
                                            Map parametros = new HashMap();
                                            Select=tabla.getSelectedRow();
                                            String codigoArticulo= tabla.getValueAt(Select,0).toString();

                                            parametros.put("codigo",codigoArticulo);
                                            Connection miconexion = conexion.ObtenerConexion();

                                            try{
                                                String reporte="codigoBarrasIndividual.jasper";
                                                JasperPrint informe =JasperFillManager.fillReport(reporte,parametros,miconexion);
                                                JasperViewer ventanavisor=new JasperViewer(informe,false);
                                                ventanavisor.setTitle("Reporte de codigo de barras individual TANDA DE 1");
                                                ventanavisor.setVisible(true);
                                            }catch (Exception e) {
                                                JOptionPane.showMessageDialog(this, e.getMessage());
                                            }
                                        }else{

                                            if(txt_recibe.getText().equals("25")){               
                                                Map parametros = new HashMap();
                                                Select=tabla.getSelectedRow();
                                                String codigoArticulo= tabla.getValueAt(Select,0).toString();

                                                parametros.put("codigo",codigoArticulo);
                                                Connection miconexion = conexion.ObtenerConexion();

                                                try{
                                                    String reporte="codigoBarrasX2Individual.jasper";
                                                    JasperPrint informe =JasperFillManager.fillReport(reporte,parametros,miconexion);
                                                    JasperViewer ventanavisor=new JasperViewer(informe,false);
                                                    ventanavisor.setTitle("Reporte de codigo de barras individual TANDA DE 2");
                                                    ventanavisor.setVisible(true);
                                                }catch (Exception e) {
                                                    JOptionPane.showMessageDialog(this, e.getMessage());
                                                }
                                            }else{

                                                if(txt_recibe.getText().equals("26")){               
                                                    Map parametros = new HashMap();
                                                    Select=tabla.getSelectedRow();
                                                    String codigoArticulo= tabla.getValueAt(Select,0).toString();

                                                    parametros.put("codigo",codigoArticulo);
                                                    Connection miconexion = conexion.ObtenerConexion();

                                                    try{
                                                        String reporte="codigoBarrasX4Individual.jasper";
                                                        JasperPrint informe =JasperFillManager.fillReport(reporte,parametros,miconexion);
                                                        JasperViewer ventanavisor=new JasperViewer(informe,false);
                                                        ventanavisor.setTitle("Reporte de codigo de barras individual TANDA DE 4");
                                                        ventanavisor.setVisible(true);
                                                    }catch (Exception e) {
                                                        JOptionPane.showMessageDialog(this, e.getMessage());
                                                    }
                                                }else{

                                                    if(txt_recibe.getText().equals("27")){               
                                                        Map parametros = new HashMap();
                                                        Select=tabla.getSelectedRow();
                                                        String codigoArticulo= tabla.getValueAt(Select,0).toString();

                                                        parametros.put("codigo",codigoArticulo);
                                                        Connection miconexion = conexion.ObtenerConexion();

                                                        try{
                                                            String reporte="codigoBarrasX8Individual.jasper";
                                                            JasperPrint informe =JasperFillManager.fillReport(reporte,parametros,miconexion);
                                                            JasperViewer ventanavisor=new JasperViewer(informe,false);
                                                            ventanavisor.setTitle("Reporte de codigo de barras individual TANDA DE 8");
                                                            ventanavisor.setVisible(true);
                                                        }catch (Exception e) {
                                                            JOptionPane.showMessageDialog(this, e.getMessage());
                                                        }
                                                    }else{

                                                        if(txt_recibe.getText().equals("28")){               
                                                            Map parametros = new HashMap();
                                                            Select=tabla.getSelectedRow();
                                                            String codigoArticulo= tabla.getValueAt(Select,0).toString();

                                                            parametros.put("codigo",codigoArticulo);
                                                            Connection miconexion = conexion.ObtenerConexion();

                                                            try{
                                                                String reporte="codigoBarrasX12Individual.jasper";
                                                                JasperPrint informe =JasperFillManager.fillReport(reporte,parametros,miconexion);
                                                                JasperViewer ventanavisor=new JasperViewer(informe,false);
                                                                ventanavisor.setTitle("Reporte de codigo de barras individual TANDA DE 12");
                                                                ventanavisor.setVisible(true);
                                                            }catch (Exception e) {
                                                                JOptionPane.showMessageDialog(this, e.getMessage());
                                                            }
                                                        }else{

                                                            if(txt_recibe.getText().equals("29")){               
                                                                Map parametros = new HashMap();
                                                                Select=tabla.getSelectedRow();
                                                                String codigoArticulo= tabla.getValueAt(Select,0).toString();

                                                                parametros.put("codigo",codigoArticulo);
                                                                Connection miconexion = conexion.ObtenerConexion();

                                                                try{
                                                                    String reporte="codigoBarrasX16Individual.jasper";
                                                                    JasperPrint informe =JasperFillManager.fillReport(reporte,parametros,miconexion);
                                                                    JasperViewer ventanavisor=new JasperViewer(informe,false);
                                                                    ventanavisor.setTitle("Reporte de codigo de barras individual TANDA DE 16");
                                                                    ventanavisor.setVisible(true);
                                                                }catch (Exception e) {
                                                                    JOptionPane.showMessageDialog(this, e.getMessage());
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        
    }//GEN-LAST:event_jMenu1ActionPerformed
 
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
            java.util.logging.Logger.getLogger(enviar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(enviar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(enviar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(enviar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new enviar_producto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup FiltrarResultados;
    private javax.swing.JRadioButton checkCodigo;
    private javax.swing.JRadioButton checkDescripcion;
    private javax.swing.JRadioButton checkMarca;
    public static javax.swing.JButton enviarProducto;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txt_recibe;
    // End of variables declaration//GEN-END:variables
}
