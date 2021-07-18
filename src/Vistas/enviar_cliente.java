

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

public class enviar_cliente extends javax.swing.JFrame {
    int Select;
    String telefono,cedula,nombres,apellidos,direccion,dni,cuit,contribuyente,localidad;

    public enviar_cliente() {
        initComponents();
        
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("../img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        setLocationRelativeTo(null);
        
        JTableHeader th; 
        th = tabla1.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        recibe.setVisible(false);
        txtBuscar.requestFocus();
        checkNombre.setSelected(true);
              
        tabla1.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla1.getColumnModel().getColumn(0).setMinWidth(0);
        tabla1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tabla1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        
        tabla1.getColumnModel().getColumn(1).setMaxWidth(270);
        tabla1.getColumnModel().getColumn(1).setMinWidth(270);
        tabla1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(270);
        tabla1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(270);
        
        tabla1.getColumnModel().getColumn(2).setMaxWidth(250);
        tabla1.getColumnModel().getColumn(2).setMinWidth(250);
        tabla1.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(250);
        tabla1.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(250);
        
        tabla1.getColumnModel().getColumn(3).setMaxWidth(250);
        tabla1.getColumnModel().getColumn(3).setMinWidth(250);
        tabla1.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(250);
        tabla1.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(250);
        
        tabla1.getColumnModel().getColumn(4).setMaxWidth(180);
        tabla1.getColumnModel().getColumn(4).setMinWidth(180);
        tabla1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(180);
        tabla1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(180);
        
        tabla1.getColumnModel().getColumn(5).setMaxWidth(180);
        tabla1.getColumnModel().getColumn(5).setMinWidth(180);
        tabla1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(180);
        tabla1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(180);
        
        tabla1.getColumnModel().getColumn(6).setMaxWidth(180);
        tabla1.getColumnModel().getColumn(6).setMinWidth(180);
        tabla1.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(180);
        tabla1.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(180);
       
        tabla1.getColumnModel().getColumn(7).setMaxWidth(180);
        tabla1.getColumnModel().getColumn(7).setMinWidth(180);
        tabla1.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(180);
        tabla1.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(180);
        
        
        tabla1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla1.doLayout();
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            ResultSet r= consulta.executeQuery("select * from  cliente where estado='ACTIVO' order by nombres");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
            modelo.setNumRows(0);
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null,null,null,null,null});
                tabla1.setValueAt(r.getString(1),i,0);
                tabla1.setValueAt(r.getString(2),i,1);
                tabla1.setValueAt(r.getString(4),i,2);
                tabla1.setValueAt(r.getString(3),i,3);
                tabla1.setValueAt(r.getString(5),i,4);
                tabla1.setValueAt(r.getString(7),i,5);
                tabla1.setValueAt(r.getString(8),i,6);
                String contri;
                if (r.getString(9).equals("Responsable Monotributo")){
                    contri="M";
                }else
                    if (r.getString(9).equals("Responsable Inscripto")){
                        contri="RI";
                    }else
                        if (r.getString(9).equals("Responsable no Inscripto")){
                            contri="RNI";
                        }else
                        if (r.getString(9).equals("Consumidor Final")){
                            contri="CF";
                        }else
                            if (r.getString(9).equals("No Responsable")){
                                contri="NR";
                            }else                               
                                if (r.getString(9).equals("Excento")){
                                  contri="E";
                                }else
                                     contri="";
                tabla1.setValueAt(contri,i,7);
                i++;
                if(i==3)break;//version demo
            }

            r.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"NO HAY CONEXION CON LA DB") ;
        }          
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FiltrarResultados = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        recibe = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        checkLocalidad = new javax.swing.JRadioButton();
        checkNombre = new javax.swing.JRadioButton();
        checkDni = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COD", "APELLIDO Y NOMBRE", "DIRECCION", "LOCALIDAD", "TELEFONO", "DNI", "CUIT", "TIPO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        });
        jScrollPane1.setViewportView(tabla1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 970, 560));

        recibe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(recibe, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 40, 50));

        txtBuscar.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtBuscar.setBorder(null);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 280, 40));

        FiltrarResultados.add(checkLocalidad);
        checkLocalidad.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkLocalidad.setText("Ciudad");
        checkLocalidad.setOpaque(false);
        jPanel1.add(checkLocalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        FiltrarResultados.add(checkNombre);
        checkNombre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkNombre.setText("Apellido y Nombre");
        checkNombre.setOpaque(false);
        jPanel1.add(checkNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        FiltrarResultados.add(checkDni);
        checkDni.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkDni.setText("C.C");
        checkDni.setOpaque(false);
        checkDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDniActionPerformed(evt);
            }
        });
        jPanel1.add(checkDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel2.setText("CLIENTES");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 30, 160, 50));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesXs-01.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, -1, 60));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 80));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 260, 10));

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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1009, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked
        int fila= tabla1.getSelectedRow();
        if(fila>=0){
             Select=tabla1.getSelectedRow();
             cedula= tabla1.getValueAt(Select,0).toString();
             nombres= tabla1.getValueAt(Select,1).toString();
             direccion= tabla1.getValueAt(Select,2).toString();
             localidad=tabla1.getValueAt(Select,3).toString();
             telefono= tabla1.getValueAt(Select,4).toString();
             dni= tabla1.getValueAt(Select,5).toString();
             cuit= tabla1.getValueAt(Select,6).toString();
             
             String contri;
             if (tabla1.getValueAt(Select,7).toString().equals("RI")){
                contri="Responsable Inscripto";
             }else
                 if (tabla1.getValueAt(Select,7).toString().equals("RNI")){
                contri="Responsable no Inscripto";
                }else
                    if (tabla1.getValueAt(Select,7).toString().equals("M")){
                        contri="Responsable Monotributo";
                    }else
                         if (tabla1.getValueAt(Select,7).toString().equals("CF")){
                            contri="Consumidor Final";
                         }else
                             if (tabla1.getValueAt(Select,7).toString().equals("NR")){
                                 contri="No Responsable";
                            }else
                                if (tabla1.getValueAt(Select,7).toString().equals("E")){
                                    contri="Excento";
                               }else
                                   contri="Consumidor Final";
                
             contribuyente= contri;
             
             if(recibe.getText().equals("1")){ 

                Factura_Venta.comboCliente.setSelectedItem(nombres);
                Factura_Venta.txtCodigoCliente.setText(cedula);
                //Frm_facturap.txtTipo.setText(contribuyente);
                Factura_Venta.comboContribuyente.setSelectedItem(contri);
                Factura_Venta.txtCuit.setText(cuit);
                Factura_Venta.txtDomicilio.setText(direccion+", "+localidad);
                Factura_Venta.tipoFactura(); 
                this.setVisible(false); 
             }else{
                if(recibe.getText().equals("4")){

                   Factura_Remito.comboClientes.setSelectedItem(nombres);
                   Factura_Remito.txtCodigo_Cliente.setText(cedula);
                   this.setVisible(false); 
                }else{
                    if(recibe.getText().equals("15")){

                       Factura_Presupuesto.comboClientes.setSelectedItem(nombres);
                       Factura_Presupuesto.txtCod_Cliente.setText(cedula);
                       Factura_Presupuesto.txtCod_Cliente.setText(cedula);
                       this.setVisible(false); 
                    }else{
                         if(recibe.getText().equals("20")){
                            Connection miconexion = conexion.ObtenerConexion();
                            Map parametros = new HashMap();

                            Select=tabla1.getSelectedRow();
                            String codigoCliente= tabla1.getValueAt(Select,0).toString();

                            parametros.put("nombre",codigoCliente);
                            try {
                                String reporte="fnombres.jasper";
                                JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                                JasperViewer ventanavisor=new JasperViewer(informe,false);
                                ventanavisor.setTitle("Reporte Factura por Codigo Cliente");
                                ventanavisor.setVisible(true); 
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, e.getMessage());
                            }
                         }else{
                            if(recibe.getText().equals("22")){
                               Agregar_Credito_Venta_Manual.comboCliente.setSelectedItem(nombres);
                               Agregar_Credito_Venta_Manual.txtCodCliente.setText(cedula);
                               this.setVisible(false); 
                            }
                         }
                    }
                }
             }
             }else{
                JOptionPane.showMessageDialog(null,"No selecciono ninguna fila");
            }
    }//GEN-LAST:event_tabla1MouseClicked
        
    DefaultTableModel dm;
    /* Método filtro*/
    private void filtro2(String consulta, JTable jtableBuscar){
        dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter(dm);
        jtableBuscar.setRowSorter(tr);

        int columna=0;
        // Identificamos cual es el JRadioButton seleccionado para filtrar el
        // resultado de acuerdo a los datos de la columna elegida
        if (checkDni.isSelected()) {
            columna = 5;
        }else if (checkNombre.isSelected()) {
                columna = 1;
        }else if (checkLocalidad.isSelected()) {
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
            filtro2(txtBuscar.getText().toUpperCase(), tabla1);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void checkDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkDniActionPerformed

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
            java.util.logging.Logger.getLogger(enviar_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(enviar_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(enviar_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(enviar_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new enviar_cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup FiltrarResultados;
    private javax.swing.JRadioButton checkDni;
    private javax.swing.JRadioButton checkLocalidad;
    private javax.swing.JRadioButton checkNombre;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JLabel recibe;
    private javax.swing.JTable tabla1;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
