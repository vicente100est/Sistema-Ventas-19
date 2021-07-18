
package Vistas;
import java.awt.Color;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class enviar_proveedorCredito extends javax.swing.JDialog {
    int Select;
    String telefono,cedula,nombres,direccion;

    public enviar_proveedorCredito(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        setLocationRelativeTo(null);
        JTableHeader th;
        th = tablaproveedores.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        checkEmpresa.setSelected(true);   
        txtBuscar.setText("Ingrese su búsqueda");
        txtBuscar.requestFocus();
        txtBuscar.setForeground(Color.gray);
        
        tablaproveedores.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaproveedores.getColumnModel().getColumn(0).setMinWidth(0);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        
        
        tablaproveedores.getColumnModel().getColumn(1).setMaxWidth(250);
        tablaproveedores.getColumnModel().getColumn(1).setMinWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(25);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(250);
        
        tablaproveedores.getColumnModel().getColumn(2).setMaxWidth(0);
        tablaproveedores.getColumnModel().getColumn(2).setMinWidth(0);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        
        tablaproveedores.getColumnModel().getColumn(3).setMaxWidth(250);
        tablaproveedores.getColumnModel().getColumn(3).setMinWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(250);
        
        tablaproveedores.getColumnModel().getColumn(4).setMaxWidth(250);
        tablaproveedores.getColumnModel().getColumn(4).setMinWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(250);
        
        tablaproveedores.getColumnModel().getColumn(5).setMaxWidth(200);
        tablaproveedores.getColumnModel().getColumn(5).setMinWidth(200);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(200);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(200);
        
        tablaproveedores.getColumnModel().getColumn(6).setMaxWidth(0);
        tablaproveedores.getColumnModel().getColumn(6).setMinWidth(0);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
        
        tablaproveedores.getColumnModel().getColumn(7).setMaxWidth(0);
        tablaproveedores.getColumnModel().getColumn(7).setMinWidth(0);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        
        tablaproveedores.getColumnModel().getColumn(8).setMaxWidth(200);
        tablaproveedores.getColumnModel().getColumn(8).setMinWidth(200);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(200);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(200);

        
        
        tablaproveedores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tablaproveedores.doLayout();
        
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta.executeQuery("select p.cod_proveedor,p.nombre_firma,p.responsable_firma,p.direccion_firma,p.ciudad_firma,p.cuit,p.ingresos_brutos,p.condicion,p.cbu from proveedor p, compra c WHERE p.estado='ACTIVO' AND p.cod_proveedor=c.cod_proveedor AND p.cod_proveedor>0 AND c.tipo_pago='CREDITO'  group by p.cod_proveedor order by p.nombre_firma");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tablaproveedores.getModel();
            tablaproveedores.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            while(r.next()){

                    modelo.addRow( new Object [] {null,null,null,null,null,null,null,null});
                    tablaproveedores.setValueAt(r.getString(1),i,0);
                    tablaproveedores.setValueAt(r.getString(2),i,1);
                    tablaproveedores.setValueAt(r.getString(3),i,2);
                    tablaproveedores.setValueAt(r.getString(4),i,3);
                    tablaproveedores.setValueAt(r.getString(5),i,4);
                    tablaproveedores.setValueAt(r.getString(6),i,5);     
                    tablaproveedores.setValueAt(r.getString(7),i,6);
                    tablaproveedores.setValueAt(r.getString(8),i,7);
                    tablaproveedores.setValueAt(r.getString(9),i,8);
                    i++;
                
            }
            r.close();
            
        } catch(SQLException e){
             System.out.println(e);
             JOptionPane.showMessageDialog(null,"Error en la base de datos") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
        }   
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FiltrarResultados = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        checkCuit = new javax.swing.JRadioButton();
        checkEmpresa = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaproveedores = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        checkLocalidad = new javax.swing.JRadioButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONSULTA DE PROVEDOR");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        FiltrarResultados.add(checkCuit);
        checkCuit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCuit.setText("NIT");
        checkCuit.setOpaque(false);
        checkCuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCuitActionPerformed(evt);
            }
        });

        FiltrarResultados.add(checkEmpresa);
        checkEmpresa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkEmpresa.setText("Empresa");
        checkEmpresa.setOpaque(false);

        txtBuscar.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        txtBuscar.setBorder(null);
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFocusLost(evt);
            }
        });
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

        tablaproveedores.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        tablaproveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "EMPRESA", "RESPONSABLE", "DIRECCION", "LOCALIDAD", "NIT", "CONDICION", "INGRESOS BRUTOS", "CBU"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaproveedores.setOpaque(false);
        tablaproveedores.setRowHeight(25);
        tablaproveedores.setRowMargin(4);
        tablaproveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaproveedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaproveedores);

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel9.setText("PROVEEDORES CON CREDITO");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoProveedoresXs-01.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N

        FiltrarResultados.add(checkLocalidad);
        checkLocalidad.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkLocalidad.setText("Localidad");
        checkLocalidad.setOpaque(false);
        checkLocalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkLocalidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(checkCuit))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(checkEmpresa))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(checkLocalidad))
                    .addComponent(jLabel3))
                .addGap(26, 26, 26))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(checkCuit)
                        .addGap(9, 9, 9)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(checkEmpresa)
                    .addComponent(checkLocalidad)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel9))
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        if (checkCuit.isSelected()) {
            columna = 7;
        }else if (checkLocalidad.isSelected()) {
                    columna = 4;
              }else if (checkEmpresa.isSelected()) {
                        columna = 1;
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
            filtro2(txtBuscar.getText().toUpperCase(), tablaproveedores);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void checkCuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCuitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCuitActionPerformed

    private void tablaproveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaproveedoresMouseClicked
        int fila= tablaproveedores.getSelectedRow();
        this.setVisible(false);
        if(fila>=0){
            Select=tablaproveedores.getSelectedRow();
            String codProveedor=tablaproveedores.getValueAt(Select,0).toString();
            Connection miconexion = conexion.ObtenerConexion();    
            Map parametros = new HashMap();
            parametros.put("codProveedor",codProveedor);
            try{   
                String reporte="reporteCreditoProveedorIndividual.jasper";
                JasperPrint informe =JasperFillManager.fillReport(reporte,parametros,miconexion);
                JasperViewer ventanavisor=new JasperViewer(informe,false);
                ventanavisor.setTitle("Reporte de cuentas a credito de compra");
                ventanavisor.setVisible(true); 
                ventanavisor.toFront();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

             
        }else{
            JOptionPane.showMessageDialog(null,"No selecciono ninguna fila");
        }
        
    }//GEN-LAST:event_tablaproveedoresMouseClicked

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
        if(txtBuscar.getText().equals("Ingrese su búsqueda")){
            txtBuscar.setText("");
        }
        txtBuscar.setForeground(Color.black);
    }//GEN-LAST:event_txtBuscarFocusGained

    private void txtBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusLost
       if(txtBuscar.getText().equals("")){
            txtBuscar.setText("Ingrese su búsqueda");
        }
        txtBuscar.setForeground(Color.gray);
    }//GEN-LAST:event_txtBuscarFocusLost

    private void checkLocalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkLocalidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkLocalidadActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(enviar_proveedorCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(enviar_proveedorCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(enviar_proveedorCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(enviar_proveedorCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                enviar_proveedorCredito dialog = new enviar_proveedorCredito(new javax.swing.JFrame(), true);
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
    private javax.swing.JRadioButton checkCuit;
    private javax.swing.JRadioButton checkEmpresa;
    private javax.swing.JRadioButton checkLocalidad;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tablaproveedores;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
