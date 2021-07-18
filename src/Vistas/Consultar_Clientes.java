
package Vistas;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;


public class Consultar_Clientes extends javax.swing.JDialog {
    int Select;
    String telefono,cedula,nombres,localidad,direccion,dni,cuit;

    public Consultar_Clientes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        JTableHeader th; 
        th = tabla1.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        checkNombre.setSelected(true);   
        txtBuscar.setText("Ingrese su búsqueda");
        txtBuscar.requestFocus();
        txtBuscar.setForeground(Color.gray);
        
        
        //ANCHO DE LAS COLUMNAS
        tabla1.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla1.getColumnModel().getColumn(0).setMinWidth(0);
        tabla1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tabla1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        
        tabla1.getColumnModel().getColumn(1).setMaxWidth(320);
        tabla1.getColumnModel().getColumn(1).setMinWidth(320);
        tabla1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(320);
        tabla1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(320);
        
        tabla1.getColumnModel().getColumn(2).setMaxWidth(300);
        tabla1.getColumnModel().getColumn(2).setMinWidth(300);
        tabla1.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(300);
        tabla1.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(300);
        
        tabla1.getColumnModel().getColumn(3).setMaxWidth(300);
        tabla1.getColumnModel().getColumn(3).setMinWidth(300);
        tabla1.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(300);
        tabla1.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(300);
        
        tabla1.getColumnModel().getColumn(4).setMaxWidth(200);
        tabla1.getColumnModel().getColumn(4).setMinWidth(200);
        tabla1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(200);
        tabla1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(200);
        
        tabla1.getColumnModel().getColumn(5).setMaxWidth(200);
        tabla1.getColumnModel().getColumn(5).setMinWidth(200);
        tabla1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(200);
        tabla1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(200);
        
        tabla1.getColumnModel().getColumn(6).setMaxWidth(200);
        tabla1.getColumnModel().getColumn(6).setMinWidth(200);
        tabla1.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(200);
        tabla1.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(200);
        
        tabla1.getColumnModel().getColumn(7).setMaxWidth(300);
        tabla1.getColumnModel().getColumn(7).setMinWidth(300);
        tabla1.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(300);
        tabla1.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(300);
        
        tabla1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla1.doLayout();
         
         try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            ResultSet r= consulta.executeQuery("select * from  cliente  WHERE estado='ACTIVO' AND cod_cliente>0 order by nombres");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
            modelo.setNumRows(0);
            //MUESTRO LOS CLIENTES
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null,null});
                tabla1.setValueAt(r.getString(1),i,0);
                tabla1.setValueAt(r.getString(2),i,1);
                tabla1.setValueAt(r.getString(4),i,2);
                tabla1.setValueAt(r.getString(3),i,3);
                tabla1.setValueAt(r.getString(5),i,4);
                tabla1.setValueAt(r.getString(7),i,5);
                tabla1.setValueAt(r.getString(8),i,6);
                tabla1.setValueAt(r.getString(9),i,7);
                i++;
            }
            r.close();
        } catch(SQLException e){
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
        checkDni = new javax.swing.JRadioButton();
        checkNombre = new javax.swing.JRadioButton();
        checkLocalidad = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        checkCuit = new javax.swing.JRadioButton();
        btnExportar = new javax.swing.JButton();
        checkDomicilio = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta de clientes");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla1.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COD", "APELLIDO Y NOMBRE", "DIRECCION", "LOCALIDAD", "TELEFONO", "DNI", "CUIT", "CONTRIBUYENTE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false, false
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 960, 480));

        FiltrarResultados.add(checkDni);
        checkDni.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkDni.setText("C.C");
        checkDni.setOpaque(false);
        checkDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDniActionPerformed(evt);
            }
        });
        jPanel1.add(checkDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

        FiltrarResultados.add(checkNombre);
        checkNombre.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkNombre.setText("Apellido y Nombre");
        checkNombre.setOpaque(false);
        jPanel1.add(checkNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, -1, -1));

        FiltrarResultados.add(checkLocalidad);
        checkLocalidad.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkLocalidad.setText("Ciudad");
        checkLocalidad.setOpaque(false);
        jPanel1.add(checkLocalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, -1, -1));

        txtBuscar.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
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
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 270, 50));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel2.setText("CLIENTES");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 380, 50));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoClientesXs-01.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 50, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 280, 10));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, -1, 80));

        FiltrarResultados.add(checkCuit);
        checkCuit.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkCuit.setText("NIT");
        checkCuit.setOpaque(false);
        checkCuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCuitActionPerformed(evt);
            }
        });
        jPanel1.add(checkCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, -1));

        btnExportar.setBackground(new java.awt.Color(5, 52, 99));
        btnExportar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnExportar.setForeground(new java.awt.Color(255, 255, 255));
        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoE.png"))); // NOI18N
        btnExportar.setText("Exportar a Excel");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        jPanel1.add(btnExportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 630, 220, 40));

        FiltrarResultados.add(checkDomicilio);
        checkDomicilio.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        checkDomicilio.setText("Dirección");
        checkDomicilio.setOpaque(false);
        jPanel1.add(checkDomicilio, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked
        Select=tabla1.getSelectedRow();
        cedula= tabla1.getValueAt(Select,0).toString();
        nombres= tabla1.getValueAt(Select,1).toString();
        localidad= tabla1.getValueAt(Select,3).toString();
        direccion= tabla1.getValueAt(Select,2).toString();
        telefono= tabla1.getValueAt(Select,4).toString(); 
        dni= tabla1.getValueAt(Select,5).toString(); 
        cuit= tabla1.getValueAt(Select,6).toString(); // TODO add your handling code here:
    }//GEN-LAST:event_tabla1MouseClicked

    private void checkDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkDniActionPerformed

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
        if (checkDni.isSelected()) {
            columna = 5;
        }else if (checkCuit.isSelected()) {
                    columna = 6;
              }else if (checkNombre.isSelected()) {
                        columna = 1;
                     }else if (checkLocalidad.isSelected()) {
                                columna = 3;
                            }else if (checkDomicilio.isSelected()) {
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
            filtro2(txtBuscar.getText().toUpperCase(), tabla1);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

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

    private void checkCuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCuitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCuitActionPerformed

    
    JFileChooser selecArchivo = new JFileChooser();
    File archivo;
    public void AgregarFiltro(){
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
    }
    
    public static boolean esNumerico(String cadena){
        try{
            Integer.parseInt(cadena);
                return true;
        }catch(NumberFormatException e){
                return false;
        }
    }
    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        //EXPORTO A EXCEL
        ModeloExcel modeloE = new ModeloExcel();
        int contAccion=0;

        contAccion++;
        if(contAccion==1)AgregarFiltro();

        if(evt.getSource() == btnExportar){
            if(selecArchivo.showDialog(null, "Exportar")==JFileChooser.APPROVE_OPTION){
                archivo=selecArchivo.getSelectedFile();
                String suffix=".xlsx";

                if(!selecArchivo.getSelectedFile().getAbsolutePath().endsWith(suffix)){
                    archivo= new File(selecArchivo.getSelectedFile() + suffix);
                }
                if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                    JOptionPane.showMessageDialog(null, modeloE.Exportar(archivo,tabla1) + "\n Formato ."+ archivo.getName().substring(archivo.getName().lastIndexOf(".")+1));
                }else{
                    JOptionPane.showMessageDialog(null, "Elija un formato valido.");
                }
            }
        }
    }//GEN-LAST:event_btnExportarActionPerformed

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
            java.util.logging.Logger.getLogger(Consultar_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultar_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultar_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultar_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Consultar_Clientes dialog = new Consultar_Clientes(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnExportar;
    private javax.swing.JRadioButton checkCuit;
    private javax.swing.JRadioButton checkDni;
    private javax.swing.JRadioButton checkDomicilio;
    private javax.swing.JRadioButton checkLocalidad;
    private javax.swing.JRadioButton checkNombre;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabla1;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
