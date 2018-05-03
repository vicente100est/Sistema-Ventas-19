/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Hugo
 */
public class enviar_factura extends javax.swing.JFrame {
    int Select;

String codigo,nombre,apellido,fecha,empleado;
String bd ="ventas";
             String NOMBRE;
             String login = "root";
             String password = "";
             String url = "jdbc:mysql://localhost/"+bd; // esta es la conexion
    /**
     * Creates new form enviar_factura
     */
    public enviar_factura() {
        initComponents();
        
        
        
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


                            ResultSet r= consulta.executeQuery("select * from factura order by cod_factura");
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
                                String codigoCliente=r.getString(3),
                                        codigoEmpleado=r.getString(4);
                                
                                modelo.addRow( new Object [] {null,null,null,null,null,null});
                                tabla.setValueAt(r.getString(1),i,0);
                                //JOptionPane.showMessageDialog(null,"por entrar a cliente");
                                Statement consulta2=conn.createStatement();
                                ResultSet r2= consulta2.executeQuery("select * from cliente order by cod_cliente");
                                String nombreCliente="N° "+codigoCliente,apellidoCliente="";
                                while(r2.next()){
                                    //JOptionPane.showMessageDialog(null,"ENTRO a cliente");
                                      if((r2.getString("cod_cliente")).equals(r.getString("cod_cliente"))){
                                          //JOptionPane.showMessageDialog(null,"ENTRO ALIF DE cliente");
                                          nombreCliente=r2.getString(2);
                                          apellidoCliente=r2.getString(3);    
                                      }
                                }
                                tabla.setValueAt(nombreCliente,i,1);
                                tabla.setValueAt(apellidoCliente,i,2);
                                
                                tabla.setValueAt(r.getString(2),i,3);
                                
                                 //JOptionPane.showMessageDialog(null,"por entrar a EMPLEADO");
                                 Statement consulta3=conn.createStatement();
                                ResultSet r3= consulta3.executeQuery("select * from empleado order by cod_empleado");
                                String nombreEmpleado="N° "+codigoEmpleado,apellidoEmpleado="";
                                while(r3.next()){
                                    if(r3.getString(1).equals(codigoEmpleado)){
                                        nombreEmpleado=r3.getString(2);
                                        apellidoEmpleado=r3.getString(3);
                                        
                                    }
                                }
                                tabla.setValueAt(nombreEmpleado,i,4);
                                tabla.setValueAt(apellidoEmpleado,i,5);
                                //    tabla.setValueAt(r.getString(3),i,2);
                                i++;
                            }
                        } catch(SQLException e){
                            JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ;
                            System.out.println(e);
                            // esto aparece cuando ya existe un código por lo cual no se guarda la info.
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        checkCodigo = new javax.swing.JRadioButton();
        checkReferencia = new javax.swing.JRadioButton();
        checkMarca = new javax.swing.JRadioButton();
        txt_recibe = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "N° FACTURA", "NOMBRE", "APELLIDO", "FECHA", "NOM EMP", "APELL EMP"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jButton1.setText("Ver factura");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        buttonGroup1.add(checkCodigo);
        checkCodigo.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkCodigo.setText("N° Fac");
        checkCodigo.setOpaque(false);
        checkCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCodigoActionPerformed(evt);
            }
        });

        buttonGroup1.add(checkReferencia);
        checkReferencia.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkReferencia.setText("Nom cliente");
        checkReferencia.setOpaque(false);

        buttonGroup1.add(checkMarca);
        checkMarca.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkMarca.setText("Nom Empleado");
        checkMarca.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 30)); // NOI18N
        jLabel1.setText("FACTURAS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 574, Short.MAX_VALUE)
                        .addComponent(txt_recibe, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jButton1)
                        .addGap(27, 27, 27))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(checkCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkReferencia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkMarca)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkCodigo)
                    .addComponent(checkReferencia)
                    .addComponent(checkMarca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(txt_recibe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    } 
    if (checkReferencia.isSelected()) {
         columna = 1;
    }
    if (checkMarca.isSelected()) {
        columna = 4;
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Connection miconexion = conexion.GetConnection();
        Map parametros = new HashMap();

        //parametros.put("nombre",JOptionPane.showInputDialog(null,"Ingrese Codigo del Cliente"));
             Select=tabla.getSelectedRow();
            String codigoFactura= tabla.getValueAt(Select,0).toString();
            
            parametros.put("codf",codigoFactura);
        try {
            
            String reporte="factura.jasper";
            JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
            JasperViewer ventanavisor=new JasperViewer(informe,false);
            ventanavisor.setTitle("Reporte de Factura");
            ventanavisor.setVisible(true); 
            this.setVisible(false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(enviar_factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(enviar_factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(enviar_factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(enviar_factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new enviar_factura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton checkCodigo;
    private javax.swing.JRadioButton checkMarca;
    private javax.swing.JRadioButton checkReferencia;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txt_recibe;
    // End of variables declaration//GEN-END:variables
}
