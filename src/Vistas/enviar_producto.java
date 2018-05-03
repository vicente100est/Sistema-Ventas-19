/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vistas;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.sql.*;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import javax.swing.RowFilter;





/**
 *
 * @author EQUIPO
 */
public class enviar_producto extends javax.swing.JFrame {
int Select;
String codigo,referencia,cantidad,marca,valor;
    /**
     * Creates new form enviar_producto
     */
    public enviar_producto() {
        initComponents();
        setLocationRelativeTo(null);
        
        
        // cb1.setSelectedIndex(2);
        
        TableColumn  column = null;
        column = tabla.getColumnModel().getColumn(0);
        column.setPreferredWidth(60);
        column = tabla.getColumnModel().getColumn(1);
        column.setPreferredWidth(260);
        column = tabla.getColumnModel().getColumn(2);
        column.setPreferredWidth(100);
        column = tabla.getColumnModel().getColumn(3);
        column.setPreferredWidth(60);
         column = tabla.getColumnModel().getColumn(4);
        column.setPreferredWidth(60);
        
         try{
                            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
                            Class.forName("com.mysql.jdbc.Driver"); // este es el driver que copiaron y pegaron
                            Connection conn=(Connection) DriverManager.getConnection(url,login,password); // esta es la verificación de la conexión con mysql

                            //Connection conn=(Connection) DriverManager.getConnection(url,usuario,contraseña); // esta es la verificación de la conexión con mysql
                            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql


                            ResultSet r= consulta.executeQuery("select * from articulo order by referencia");
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
                                tabla.setValueAt(r.getString(4),i,3);
                                tabla.setValueAt(r.getString(5),i,4);
                                //    tabla.setValueAt(r.getString(3),i,2);
                                i++;
                            }
                        } catch(SQLException e){
                            JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
                            //t2.setText("");


                        } catch(ClassNotFoundException e){
                            JOptionPane.showMessageDialog(null,"Error en la Base de Datos") ; // esto aparece cuando hay problemas con la conexión con mysql

                        }

        
        
        

    }
  String bd ="ventas";
             String NOMBRE;
             String login = "root";
             String password = "";
             String url = "jdbc:mysql://localhost/"+bd; // esta es la conexion
             String orden="NOMBRE";
             String forma="ASC";

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FiltrarResultados = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        sp1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        enviar = new javax.swing.JButton();
        txt_recibe = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        checkMarca = new javax.swing.JRadioButton();
        checkReferencia = new javax.swing.JRadioButton();
        checkCodigo = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        sp1.setBackground(new java.awt.Color(102, 204, 255));
        sp1.setBorder(null);
        sp1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "C O D I G O", "R E F E R E N C I A", "C A N T I D A D", "M A R C A", "V A L O R"
            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        sp1.setViewportView(tabla);

        enviar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        enviar.setText("ENVIAR");
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 30)); // NOI18N
        jLabel1.setText("ARTICULOS");

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/articulo.png"))); // NOI18N

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        FiltrarResultados.add(checkMarca);
        checkMarca.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkMarca.setText("Marca");
        checkMarca.setOpaque(false);

        FiltrarResultados.add(checkReferencia);
        checkReferencia.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkReferencia.setText("Referencia");
        checkReferencia.setOpaque(false);

        FiltrarResultados.add(checkCodigo);
        checkCodigo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCodigo.setText("Codigo");
        checkCodigo.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(checkCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkReferencia)
                        .addGap(18, 18, 18)
                        .addComponent(checkMarca))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_recibe, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(checkMarca)
                                    .addComponent(checkReferencia)
                                    .addComponent(checkCodigo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_recibe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(213, 213, 213))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, Short.MAX_VALUE)
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
    } 
    if (checkReferencia.isSelected()) {
         columna = 1;
    }
    if (checkMarca.isSelected()) {
        columna = 3;
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

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
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
    }//GEN-LAST:event_enviarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        Select=tabla.getSelectedRow();
        codigo= tabla.getValueAt(Select,0).toString();
        referencia= tabla.getValueAt(Select,1).toString();
        cantidad= tabla.getValueAt(Select,2).toString();
        marca= tabla.getValueAt(Select,3).toString();
        valor= tabla.getValueAt(Select,4).toString();
    }//GEN-LAST:event_tablaMouseClicked

    
    
   
    
    
    
    
    
    
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
    private javax.swing.JRadioButton checkMarca;
    private javax.swing.JRadioButton checkReferencia;
    private javax.swing.JButton enviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JScrollPane sp1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txt_recibe;
    // End of variables declaration//GEN-END:variables
}