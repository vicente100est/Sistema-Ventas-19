
package Vistas;
import java.awt.Color;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class enviar_proveedor extends javax.swing.JDialog {
    int Select;
    String telefono,cedula,nombres,direccion;

    public enviar_proveedor(java.awt.Frame parent, boolean modal) {
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
        
        txtRecibe.setVisible(false);
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
        
        tablaproveedores.getColumnModel().getColumn(2).setMaxWidth(270);
        tablaproveedores.getColumnModel().getColumn(2).setMinWidth(270);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(270);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(270);
        
        tablaproveedores.getColumnModel().getColumn(3).setMaxWidth(250);
        tablaproveedores.getColumnModel().getColumn(3).setMinWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(250);
        
        tablaproveedores.getColumnModel().getColumn(4).setMaxWidth(250);
        tablaproveedores.getColumnModel().getColumn(4).setMinWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(250);
        
        tablaproveedores.getColumnModel().getColumn(5).setMaxWidth(250);
        tablaproveedores.getColumnModel().getColumn(5).setMinWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(25);
        
        tablaproveedores.getColumnModel().getColumn(6).setMaxWidth(250);
        tablaproveedores.getColumnModel().getColumn(6).setMinWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(250);
        
        tablaproveedores.getColumnModel().getColumn(7).setMaxWidth(250);
        tablaproveedores.getColumnModel().getColumn(7).setMinWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(250);
        
        tablaproveedores.getColumnModel().getColumn(8).setMaxWidth(250);
        tablaproveedores.getColumnModel().getColumn(8).setMinWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(250);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(250);

        
        
        tablaproveedores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tablaproveedores.doLayout();
        
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta.executeQuery("select * from proveedor WHERE estado='ACTIVO' order by nombre_firma");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tablaproveedores.getModel();
            tablaproveedores.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            while(r.next()){
                if(r.getString(12).equals("ACTIVO")){
                    modelo.addRow( new Object [] {null,null,null,null,null,null,null,null});
                    tablaproveedores.setValueAt(r.getString(1),i,0);
                    tablaproveedores.setValueAt(r.getString(2),i,1);
                    tablaproveedores.setValueAt(r.getString(3),i,2);
                    tablaproveedores.setValueAt(r.getString(4),i,3);
                    tablaproveedores.setValueAt(r.getString(5),i,4);
                    tablaproveedores.setValueAt(r.getString(8),i,5);     
                    tablaproveedores.setValueAt(r.getString(13),i,6);
                    tablaproveedores.setValueAt(r.getString(9),i,7);
                    tablaproveedores.setValueAt(r.getString(10),i,8);
                    i++;
                    if(i==3)break;//version demo
                }
            }
            r.close();
            
        } catch(SQLException e){
             JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
        }   
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FiltrarResultados = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        checkCuit = new javax.swing.JRadioButton();
        checkEmpresa = new javax.swing.JRadioButton();
        checkCbu = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaproveedores = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        checkLocalidad = new javax.swing.JRadioButton();
        txtRecibe = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONSULTA DE PROVEDOR");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FiltrarResultados.add(checkCuit);
        checkCuit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCuit.setText("NIT");
        checkCuit.setOpaque(false);
        checkCuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCuitActionPerformed(evt);
            }
        });
        jPanel1.add(checkCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 0, -1, -1));

        FiltrarResultados.add(checkEmpresa);
        checkEmpresa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkEmpresa.setText("Empresa");
        checkEmpresa.setOpaque(false);
        jPanel1.add(checkEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, -1, -1));

        FiltrarResultados.add(checkCbu);
        checkCbu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCbu.setText("CBU");
        checkCbu.setOpaque(false);
        jPanel1.add(checkCbu, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 0, -1, -1));

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
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(623, 40, 280, 36));

        tablaproveedores.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        tablaproveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "EMPRESA", "RESPONSABLE", "DIRECCION", "LOCALIDAD", "CUIT", "CONDICION", "INGRESOS BRUTOS", "CBU"
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 90, 990, 570));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel9.setText("PROVEEDORES");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 31, 241, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoProveedoresXs-01.png"))); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 25, 50, 50));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(613, 80, 280, 10));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 10, -1, 80));

        FiltrarResultados.add(checkLocalidad);
        checkLocalidad.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkLocalidad.setText("Ciudad");
        checkLocalidad.setOpaque(false);
        checkLocalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkLocalidadActionPerformed(evt);
            }
        });
        jPanel1.add(checkLocalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, -1, -1));

        txtRecibe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtRecibe, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 13, 60, 50));

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1014, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
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
                    }else if (checkCbu.isSelected()) {
                        columna = 9;
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
        String cedula="", nombres="", direccion="",localidad="",telefono="",dni="",cuit="",contribuyente="",contri="", comboIva="";
        Select=tablaproveedores.getSelectedRow();

        int fila= tablaproveedores.getSelectedRow();
        if(fila>=0){
             Select=tablaproveedores.getSelectedRow();
             cedula= tablaproveedores.getValueAt(Select,0).toString();
             nombres= tablaproveedores.getValueAt(Select,1).toString();
             direccion= tablaproveedores.getValueAt(Select,3).toString()+" "+tablaproveedores.getValueAt(Select,4).toString();
             cuit= tablaproveedores.getValueAt(Select,5).toString(); 
             contribuyente=tablaproveedores.getValueAt(Select,6).toString();
             
             if(!contribuyente.equals("Responsable Inscripto")){ //si no es RI LA COMPRA ES FACTURA B SIN IVA
                 comboIva="0%";
             }else{
                 comboIva="21%";
             }

        }else{
                JOptionPane.showMessageDialog(null,"No selecciono ninguna fila");  
        }
        
        if(txtRecibe.getText().equals("1")){
            Factura_Compra.comboProveedor.setSelectedItem(nombres);
            Factura_Compra.txtCodigoProveedor.setText(cedula);
            Factura_Compra.comboContribuyente.setSelectedItem(contribuyente);
            Factura_Compra.comboIva.setSelectedItem(comboIva);
            Factura_Compra.txtCuit.setText(cuit);
            Factura_Compra.txtDomicilio.setText(direccion);
            Factura_Compra.tipoFactura(); 
            this.setVisible(false);
        }else if(txtRecibe.getText().equals("2")){
            Agregar_Credito_Compra_Manual.comboProveedor.setSelectedItem(nombres);
            Agregar_Credito_Compra_Manual.txtCodProveedor.setText(cedula);
            this.setVisible(false);
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
            java.util.logging.Logger.getLogger(enviar_proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(enviar_proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(enviar_proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(enviar_proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                enviar_proveedor dialog = new enviar_proveedor(new javax.swing.JFrame(), true);
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
    private javax.swing.JRadioButton checkCbu;
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
    public static javax.swing.JLabel txtRecibe;
    // End of variables declaration//GEN-END:variables
}
