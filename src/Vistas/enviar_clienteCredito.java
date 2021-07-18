
package Vistas;
import java.awt.Color;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class enviar_clienteCredito extends javax.swing.JFrame {
    int Select;
    String telefono,cedula,nombres,apellidos,direccion;
    
    public enviar_clienteCredito() {
        initComponents();
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        setLocationRelativeTo(null);
        checkCliente.setSelected(true);
        
        
        JTableHeader th; 
        th = tabla1.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        tabla1.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla1.getColumnModel().getColumn(0).setMinWidth(0);
        tabla1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tabla1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        
        tabla1.getColumnModel().getColumn(1).setMaxWidth(350);
        tabla1.getColumnModel().getColumn(1).setMinWidth(350);
        tabla1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(300);
        tabla1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(300);
        
        tabla1.getColumnModel().getColumn(2).setMaxWidth(350);
        tabla1.getColumnModel().getColumn(2).setMinWidth(350);
        tabla1.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(350);
        tabla1.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(350);
        
                     
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            ResultSet r= consulta.executeQuery("select c.cod_cliente, c.nombres, c.dirrecion, c.localidad, c.telefono, c.cuit from cliente c, factura f where c.cod_cliente=f.cod_cliente AND f.tipo_pago='CREDITO' AND c.cod_cliente>0  group by c.cod_cliente order by c.cod_cliente");
            
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
            modelo.setNumRows(0);
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null,null});
                tabla1.setValueAt(r.getString(1),i,0);
                tabla1.setValueAt(r.getString(2),i,1);
                tabla1.setValueAt(r.getString(3)+", "+r.getString(4),i,2);
                tabla1.setValueAt(r.getString(5),i,3);
                tabla1.setValueAt(r.getString(6),i,4);
                i++;
            }
            r.close();
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"NO HAY CONEXION CON LA DB") ;
        }  
        enviar.setVisible(false);
        btnVerFactura.setVisible(false); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FiltrarResultados = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        enviar = new javax.swing.JButton();
        recibe = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnVerFactura = new javax.swing.JButton();
        checkCuit = new javax.swing.JRadioButton();
        checkDireccion = new javax.swing.JRadioButton();
        checkCliente = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla1.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COD", "CLIENTE", "DIRECCION", "TELEFONO", "NIT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla1.setRowHeight(25);
        tabla1.setRowMargin(4);
        tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabla1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tabla1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 1080, 570));

        enviar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        enviar.setText("ENVIAR");
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });
        jPanel1.add(enviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 630, -1, 30));
        jPanel1.add(recibe, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 310, 40, 20));

        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 330, 50));

        btnVerFactura.setText("Ver Factura");
        btnVerFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerFacturaActionPerformed(evt);
            }
        });
        jPanel1.add(btnVerFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 630, -1, -1));

        FiltrarResultados.add(checkCuit);
        checkCuit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCuit.setText("NIT");
        checkCuit.setOpaque(false);
        jPanel1.add(checkCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, -1));

        FiltrarResultados.add(checkDireccion);
        checkDireccion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkDireccion.setText("Dirección");
        checkDireccion.setOpaque(false);
        checkDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDireccionActionPerformed(evt);
            }
        });
        jPanel1.add(checkDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        FiltrarResultados.add(checkCliente);
        checkCliente.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCliente.setText("Cliente");
        checkCliente.setOpaque(false);
        checkCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkClienteActionPerformed(evt);
            }
        });
        jPanel1.add(checkCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesXs-01.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 50, 50));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 32)); // NOI18N
        jLabel2.setText("CLIENTES CON CREDITO");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 380, 50));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 320, 10));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked
        int fila= tabla1.getSelectedRow();
        if(fila>=0){
            Select=tabla1.getSelectedRow();
            String codCliente=tabla1.getValueAt(Select,0).toString();
            Connection miconexion = conexion.ObtenerConexion();    
            Map parametros = new HashMap();
            parametros.put("codCliente",codCliente);
            try{   
                String reporte="reporteCreditoClienteIndividual.jasper";
                JasperPrint informe =JasperFillManager.fillReport(reporte,parametros,miconexion);
                JasperViewer ventanavisor=new JasperViewer(informe,false);
                ventanavisor.setTitle("Reporte de cuentas a credito de venta");
                ventanavisor.setVisible(true);    
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

             
        }else{
            JOptionPane.showMessageDialog(null,"No selecciono ninguna fila");
        }
    }//GEN-LAST:event_tabla1MouseClicked

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        /*if(recibe.getText().equals("1")){
            Frm_facturap.cb2.setSelectedItem(nombres+" "+apellidos);
            Frm_facturap.cb2.setSelectedItem(nombres+" "+apellidos);
            this.setVisible(false); 
        }*/// TODO add your handling code here:
    }//GEN-LAST:event_enviarActionPerformed
        
    DefaultTableModel dm;
    /* Método filtro*/
    private void filtro2(String consulta, JTable jtableBuscar){      
        dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter(dm);
        jtableBuscar.setRowSorter(tr);

        int columna=0;
        // Identificamos cual es el JRadioButton seleccionado para filtrar el
        // resultado de acuerdo a los datos de la columna elegida
        if (checkCliente.isSelected()) {
            columna = 1;
        }else if (checkDireccion.isSelected()) {
                columna = 2;
              }else if (checkCuit.isSelected()) {
                        columna = 4;
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
            filtro2(txtBuscar.getText().toUpperCase(), tabla1);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void checkClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkClienteActionPerformed

    private void btnVerFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerFacturaActionPerformed
        Connection miconexion = conexion.ObtenerConexion();
        Map parametros = new HashMap();
        Select=tabla1.getSelectedRow();
        String codigoCliente= tabla1.getValueAt(Select,0).toString();      
        parametros.put("nombre",codigoCliente);
        try{     
            String reporte="fnombres.jasper";
            JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
            JasperViewer ventanavisor=new JasperViewer(informe,false);
            ventanavisor.setTitle("Reporte Factura por Codigo Cliente");
            ventanavisor.setVisible(true); 
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnVerFacturaActionPerformed

    private void tabla1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla1MouseEntered

    private void checkDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkDireccionActionPerformed
    

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
            java.util.logging.Logger.getLogger(enviar_clienteCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(enviar_clienteCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(enviar_clienteCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(enviar_clienteCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new enviar_clienteCredito().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup FiltrarResultados;
    public javax.swing.JButton btnVerFactura;
    private javax.swing.JRadioButton checkCliente;
    private javax.swing.JRadioButton checkCuit;
    private javax.swing.JRadioButton checkDireccion;
    public javax.swing.JButton enviar;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JLabel recibe;
    private javax.swing.JTable tabla1;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
