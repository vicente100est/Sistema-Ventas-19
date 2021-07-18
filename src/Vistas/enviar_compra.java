
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

public class enviar_compra extends javax.swing.JFrame {
    int Select;
    String codigo,nombre,apellido,fecha,empleado;
    String NOMBRE;

    public enviar_compra() {
        initComponents();
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        txt_recibe.setVisible(false);
        btnVer.setVisible(false);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        JTableHeader th; 
        th = tabla.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        tabla.getColumnModel().getColumn(0).setMaxWidth(90);
        tabla.getColumnModel().getColumn(0).setMinWidth(90);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(90);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(90);
        
        tabla.getColumnModel().getColumn(1).setMaxWidth(60);
        tabla.getColumnModel().getColumn(1).setMinWidth(60);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(60);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(60);
        
        tabla.getColumnModel().getColumn(3).setMaxWidth(200);
        tabla.getColumnModel().getColumn(3).setMinWidth(200);
        tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(200);
        tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(200);
        
        tabla.getColumnModel().getColumn(4).setMaxWidth(110);
        tabla.getColumnModel().getColumn(4).setMinWidth(110);
        tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(110);
        tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(110);
        
        tabla.getColumnModel().getColumn(5).setMaxWidth(0);
        tabla.getColumnModel().getColumn(5).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        
        tabla.getColumnModel().getColumn(6).setMaxWidth(0);
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
        

        
        
        
        TableColumn  column = null;
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta.executeQuery("select * from compra WHERE cod_compra>0 order by cod_compra");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            tabla.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            while(r.next()){
                String codigoProveedor=r.getString(3), //codigo proveedor
                codigoEmpleado=r.getString(4);   
                modelo.addRow( new Object [] {null,null,null,null,null,null});
                tabla.setValueAt(r.getString(1),i,0);// COD FACTURA
                tabla.setValueAt(r.getString(7),i,1);//TIPO FACTURA
                Statement consulta2=conn.createStatement();
                ResultSet r2= consulta2.executeQuery("select * from proveedor order by cod_proveedor");
                String nombreProveedor="N° "+codigoProveedor;
               while(r2.next()){
                    if((r2.getString("cod_proveedor")).equals(r.getString("cod_proveedor"))){
                        nombreProveedor=r2.getString(2);
    
                    }
                }
                tabla.setValueAt(nombreProveedor,i,2);//CLIENTE
                tabla.setValueAt(r.getString(2),i,3); //FECHA
                tabla.setValueAt(r.getString(9),i,4);
                tabla.setValueAt(r.getString(11),i,5);//iva Discriminado
                tabla.setValueAt(r.getString(3),i,6);//cod_proveedor

                
                i++;
                if(i==5)break;//version demo
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ;
            System.out.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnVer = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        checkCodigo = new javax.swing.JRadioButton();
        checkCliente = new javax.swing.JRadioButton();
        checkFecha = new javax.swing.JRadioButton();
        txt_recibe = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtFacturaNumero = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "N° FACT", "TIPO", "PROVEEDOR", "FECHA", "TOTAL", "iva", "cod_proveedor"
            }
        ));
        tabla.setRowHeight(25);
        tabla.setRowMargin(4);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 123, 1000, 530));

        btnVer.setText("Ver factura");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });
        jPanel1.add(btnVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 660, -1, -1));

        txtBuscar.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 410, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        buttonGroup1.add(checkCodigo);
        checkCodigo.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkCodigo.setText("N° Fac");
        checkCodigo.setOpaque(false);
        checkCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCodigoActionPerformed(evt);
            }
        });
        jPanel1.add(checkCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        buttonGroup1.add(checkCliente);
        checkCliente.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkCliente.setText("Proveedor");
        checkCliente.setOpaque(false);
        jPanel1.add(checkCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        buttonGroup1.add(checkFecha);
        checkFecha.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkFecha.setText("Fecha");
        checkFecha.setOpaque(false);
        checkFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkFechaActionPerformed(evt);
            }
        });
        jPanel1.add(checkFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));
        jPanel1.add(txt_recibe, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 660, 44, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, -1, -1));

        txtFacturaNumero.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        txtFacturaNumero.setText("VER COMPRAS");
        jPanel1.add(txtFacturaNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 40, 240, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 410, 10));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1028, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

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
        if (checkCodigo.isSelected()) {
            columna = 0;
        }else if (checkCliente.isSelected()) {
                    columna = 2;
               }else if (checkFecha.isSelected()) {
                        columna = 3;
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

    private void checkCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCodigoActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        Connection miconexion = conexion.ObtenerConexion();
        Map parametros = new HashMap();
        Select=tabla.getSelectedRow();
        String codigoFactura= tabla.getValueAt(Select,0).toString();     
        parametros.put("codf",codigoFactura);
        try{   
            String reporte="factura.jasper";
            JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
            JasperViewer ventanavisor=new JasperViewer(informe,false);
            ventanavisor.setTitle("Reporte de Factura");
            ventanavisor.setVisible(true); 
            this.setVisible(false);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnVerActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        String cod_proveedor,vendedor,tipo,iva,porcIva="",codigoFactura,cuit="",contribuyente="",direccion="",nombreProveedor="";
        
        Select=tabla.getSelectedRow();
        codigoFactura= tabla.getValueAt(Select,0).toString();
        cod_proveedor= tabla.getValueAt(Select,6).toString();
        tipo= tabla.getValueAt(Select,1).toString();
        iva= tabla.getValueAt(Select,5).toString();
        if (tipo.equals("A")){
            tipo="FACTURA A";
        }else if (tipo.equals("B")){
            tipo="FACTURA B";
        }
        
        if(iva.equals("19.00")){
            porcIva="19%";
        }if(iva.equals("0.00")){
             porcIva="0%";
           }

        try{     
            Connection miconexion2 = conexion.ObtenerConexion();
            Statement consulta2=miconexion2.createStatement();
            ResultSet r2= consulta2.executeQuery("select * from proveedor where cod_proveedor="+cod_proveedor);
             while(r2.next()){
                 nombreProveedor=r2.getString(2);
                 cuit=r2.getString(8);
                 contribuyente=r2.getString(13);
                 direccion=r2.getString(4)+", "+r2.getString(5);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } 
        
        if(txt_recibe.getText().equals("2")){
            Factura_Nota_Credito_Compra.txtNroFactura.setText(codigoFactura);
            Factura_Nota_Credito_Compra.txtCodigoProveedor.setText(cod_proveedor);
            Factura_Nota_Credito_Compra.txtProveedor.setText(nombreProveedor);
            Factura_Nota_Credito_Compra.txtCuit.setText(cuit);
            Factura_Nota_Credito_Compra.comboContribuyente.setSelectedItem(contribuyente);
            Factura_Nota_Credito_Compra.txtDomicilio.setText(direccion);
            Factura_Nota_Credito_Compra.comboFactura.setSelectedItem(tipo);
            Factura_Nota_Credito_Compra.comboIva.setSelectedItem(porcIva);
            this.dispose();
            
        }else{
           /*Connection miconexion = conexion.ObtenerConexion();
            Map parametros = new HashMap();
             
            parametros.put("codf",codigoFactura);
            try{     
                String reporte="factura.jasper";
                JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                JasperViewer ventanavisor=new JasperViewer(informe,false);
                ventanavisor.setTitle("Reporte de Factura");
                ventanavisor.setVisible(true); 
                this.setVisible(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } */
        }
        
        
    }//GEN-LAST:event_tablaMouseClicked

    private void checkFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkFechaActionPerformed

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
            java.util.logging.Logger.getLogger(enviar_compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(enviar_compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(enviar_compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(enviar_compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new enviar_compra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVer;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton checkCliente;
    private javax.swing.JRadioButton checkCodigo;
    private javax.swing.JRadioButton checkFecha;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JLabel txtFacturaNumero;
    public static javax.swing.JTextField txt_recibe;
    // End of variables declaration//GEN-END:variables
}
