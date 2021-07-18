
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

public class Consultar_Proveedores extends javax.swing.JDialog {
    int Select;
    String telefono,cedula,nombres,direccion;

    public Consultar_Proveedores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        //COLOR CABECERA TABLAS
        JTableHeader th;
        th = tablaproveedores.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        JTableHeader th3; 
        th3 = tablaRepresentante.getTableHeader(); 
        Font fuente3 = new Font("Calibri", Font.BOLD, 19); 
        th3.setFont(fuente3); 
        th3.setBackground(new Color(93,116,163));
        th3.setForeground(new Color(255,255,255));
        
        checkEmpresa.setSelected(true);   
        txtBuscar.setText("Ingrese su búsqueda");
        txtBuscar.requestFocus();
        txtBuscar.setForeground(Color.gray);
        
        //ANCHO DE LAS COLUMNAS
        tablaproveedores.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaproveedores.getColumnModel().getColumn(0).setMinWidth(0);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        
        tablaRepresentante.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaRepresentante.getColumnModel().getColumn(0).setMinWidth(0);
        tablaRepresentante.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tablaRepresentante.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        
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
        
        tablaproveedores.getColumnModel().getColumn(5).setMaxWidth(210);
        tablaproveedores.getColumnModel().getColumn(5).setMinWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(210);
        
        tablaproveedores.getColumnModel().getColumn(6).setMaxWidth(210);
        tablaproveedores.getColumnModel().getColumn(6).setMinWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(210);
        
        tablaproveedores.getColumnModel().getColumn(7).setMaxWidth(210);
        tablaproveedores.getColumnModel().getColumn(7).setMinWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(210);
        
        tablaproveedores.getColumnModel().getColumn(8).setMaxWidth(210);
        tablaproveedores.getColumnModel().getColumn(8).setMinWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(210);
        
        tablaproveedores.getColumnModel().getColumn(9).setMaxWidth(210);
        tablaproveedores.getColumnModel().getColumn(9).setMinWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(210);
        
        tablaproveedores.getColumnModel().getColumn(10).setMaxWidth(210);
        tablaproveedores.getColumnModel().getColumn(10).setMinWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(210);
        tablaproveedores.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(210);
        
        
        tablaproveedores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tablaproveedores.doLayout();
        
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta.executeQuery("select * from proveedor  WHERE estado='ACTIVO' AND cod_proveedor>0 order by nombre_firma");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tablaproveedores.getModel();
            tablaproveedores.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            //MUESTRO LOS PROVEEDORES
            while(r.next()){
                if(r.getString(12).equals("ACTIVO")){
                    modelo.addRow( new Object [] {null,null,null,null,null,null,null,null,null,null,null});
                    tablaproveedores.setValueAt(r.getString(1),i,0);
                    tablaproveedores.setValueAt(r.getString(2),i,1);
                    tablaproveedores.setValueAt(r.getString(3),i,2);
                    tablaproveedores.setValueAt(r.getString(4),i,3);
                    tablaproveedores.setValueAt(r.getString(5),i,4);
                    tablaproveedores.setValueAt(r.getString(6),i,5);
                    tablaproveedores.setValueAt(r.getString(7),i,6);
                    tablaproveedores.setValueAt(r.getString(8),i,7);
                    tablaproveedores.setValueAt(r.getString(9),i,8);
                    tablaproveedores.setValueAt(r.getString(10),i,9);
                    tablaproveedores.setValueAt(r.getString(11),i,10);
                    i++;
                }
            }
            r.close();
            
        } catch(SQLException e){
             JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
        }   
    }
    
     public void mostrarRepresentante(int codigoEmpresa){ //MUESTRO LOS REPRESENTANTES
        TableColumn  column = null;
        tablaRepresentante.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaRepresentante.getColumnModel().getColumn(0).setMinWidth(0);
        tablaRepresentante.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tablaRepresentante.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);     
        
         try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            Statement consulta2=conn.createStatement();

            ResultSet r= consulta.executeQuery("select * from representante_empresa where empresa='"+codigoEmpresa+"' order by nombre_apellido");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tablaRepresentante.getModel();
            tablaRepresentante.setRowSorter(new TableRowSorter(modelo));

            modelo.setNumRows(0);
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null,null});
                tablaRepresentante.setValueAt(r.getString(1),i,0);
                                    
                ResultSet r2= consulta2.executeQuery("select nombre_firma from proveedor where cod_proveedor='"+r.getString(5)+"'");
                String nombreEmpresa="";
                
                while(r2.next()){
                    nombreEmpresa=r2.getString(1);
                }
                
                tablaRepresentante.setValueAt(nombreEmpresa,i,1);
                tablaRepresentante.setValueAt(r.getString(2),i,2);
                tablaRepresentante.setValueAt(r.getString(3),i,3);
                tablaRepresentante.setValueAt(r.getString(4),i,4);
                i++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la Base de Datos") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
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
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaRepresentante = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta de proveedores");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FiltrarResultados.add(checkCuit);
        checkCuit.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkCuit.setText("NIT");
        checkCuit.setOpaque(false);
        checkCuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCuitActionPerformed(evt);
            }
        });
        jPanel1.add(checkCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, -1, -1));

        FiltrarResultados.add(checkEmpresa);
        checkEmpresa.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkEmpresa.setText("Empresa");
        checkEmpresa.setOpaque(false);
        jPanel1.add(checkEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, -1, -1));

        FiltrarResultados.add(checkCbu);
        checkCbu.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkCbu.setText("CBU");
        checkCbu.setOpaque(false);
        jPanel1.add(checkCbu, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, -1, -1));

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
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 280, 36));

        tablaproveedores.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        tablaproveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "EMPRESA", "RESPONSABLE", "DIRECCION", "LOCALIDAD", "COD POSTAL", "PROVINCIA", "CUIT", "INGRESOS BRUTOS", "CBU", "TELEFONO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 118, 970, 360));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel9.setText("PROVEEDORES");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 46, 241, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoProveedoresXs-01.png"))); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 40, 50, 50));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 280, 10));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, -1, 80));

        FiltrarResultados.add(checkLocalidad);
        checkLocalidad.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkLocalidad.setText("Ciudad");
        checkLocalidad.setOpaque(false);
        checkLocalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkLocalidadActionPerformed(evt);
            }
        });
        jPanel1.add(checkLocalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, -1, -1));

        tablaRepresentante.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        tablaRepresentante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "EMPRESA", "NOMBRE", "TELEFONO", "EMAIL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaRepresentante.setAlignmentX(1.0F);
        tablaRepresentante.setAlignmentY(1.0F);
        tablaRepresentante.setRowHeight(28);
        tablaRepresentante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaRepresentanteMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaRepresentante);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 960, 140));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel8.setText("Representantes");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 182, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    //FILTRO DE BUSQUEDA
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
        Select=tablaproveedores.getSelectedRow();
        if(Select<0){
            JOptionPane.showMessageDialog(null,"No se ha seleccionado ninguna fila");
        }else{
            mostrarRepresentante(Integer.parseInt(tablaproveedores.getValueAt(Select,0).toString()));
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

    private void tablaRepresentanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRepresentanteMouseClicked
        
    }//GEN-LAST:event_tablaRepresentanteMouseClicked

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
            java.util.logging.Logger.getLogger(Consultar_Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultar_Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultar_Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultar_Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Consultar_Proveedores dialog = new Consultar_Proveedores(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tablaRepresentante;
    private javax.swing.JTable tablaproveedores;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
