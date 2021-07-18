
package Vistas;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Consultar_Ingreso_Efectivo extends javax.swing.JFrame {
    int Select;
    String nombreCaja,codCaja,fechaApertura,horaApertura,cajaInicial,codigoIngreso;

    public Consultar_Ingreso_Efectivo() {
        initComponents();
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        
        setLocationRelativeTo(null);
        txt_recibe.setVisible(false);
        
        JTableHeader th; 
        th = tabla1.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        tabla1.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla1.getColumnModel().getColumn(0).setMinWidth(1);
        tabla1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tabla1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        
        tabla1.getColumnModel().getColumn(1).setMaxWidth(100);
        tabla1.getColumnModel().getColumn(1).setMinWidth(100);
        tabla1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(100);
        tabla1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(100);
        
        
        tabla1.getColumnModel().getColumn(3).setMaxWidth(120);
        tabla1.getColumnModel().getColumn(3).setMinWidth(120);
        tabla1.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(120);
        tabla1.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(120);
        
        tabla1.getColumnModel().getColumn(4).setMaxWidth(120);
        tabla1.getColumnModel().getColumn(4).setMinWidth(120);
        tabla1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(120);
        tabla1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(120);
        
        tabla1.getColumnModel().getColumn(5).setMaxWidth(180);
        tabla1.getColumnModel().getColumn(5).setMinWidth(180);
        tabla1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(180);
        tabla1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(180);
        
        checkCaja.setSelected(true);
        txtBuscar.requestFocus();
        
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            Statement consulta2=conn.createStatement();
            ResultSet r= consulta.executeQuery("select cod_ingreso,cod_caja,vendedor,fecha,hora,monto_ingresado FROM ingreso_efectivo ORDER BY cod_ingreso");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla1.getModel();
            modelo.setNumRows(0);
            String empleado="";
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null,null,null});
                tabla1.setValueAt(r.getString(1),i,0);
                tabla1.setValueAt(r.getString(2),i,1);

                ResultSet r2= consulta2.executeQuery("select nombres,apellidos FROM empleado where cod_empleado='"+r.getString(3)+"'");
                r2.next();
                empleado=r2.getString(1)+" "+r2.getString(2);
                tabla1.setValueAt(empleado,i,2);
                
                tabla1.setValueAt(r.getString(4),i,3);
                tabla1.setValueAt(r.getString(5),i,4);
                
                String pattern = "###,###,###.00";
                DecimalFormat myFormatter = new DecimalFormat(pattern);
                String str="";
                double value = Double.parseDouble(r.getString(6));
                myFormatter = new DecimalFormat(pattern);
                str = myFormatter.format(value);
                if(str.equals(",00")){
                        str="0,00";
                }
                tabla1.setValueAt(str,i,5);
                i++;
            }
            r.close();
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Esta carnet ya existe") ;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FiltrarResultados = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MostrarIngreso = new javax.swing.JMenuItem();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        txt_recibe = new javax.swing.JTextField();
        checkFecha = new javax.swing.JRadioButton();
        checkCaja = new javax.swing.JRadioButton();
        checkVendedor = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnExportar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        MostrarIngreso.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        MostrarIngreso.setText("Mostrar ingresos de efectivo");
        MostrarIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarIngresoActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MostrarIngreso);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelImage1.setBackground(new java.awt.Color(255, 255, 255));
        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoMenumar22.png"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla1.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COD", "COD CAJA", "NOMBRE VENDEDOR", "FECHA", "HORA", "MONTO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla1.setComponentPopupMenu(jPopupMenu1);
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

        panelImage1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 101, 1050, 560));
        panelImage1.add(txt_recibe, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 620, 37, -1));

        FiltrarResultados.add(checkFecha);
        checkFecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkFecha.setText("Fecha");
        checkFecha.setOpaque(false);
        panelImage1.add(checkFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, -1));

        FiltrarResultados.add(checkCaja);
        checkCaja.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCaja.setText("Cod caja");
        checkCaja.setOpaque(false);
        checkCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCajaActionPerformed(evt);
            }
        });
        panelImage1.add(checkCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, -1, -1));

        FiltrarResultados.add(checkVendedor);
        checkVendedor.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkVendedor.setText("Vendedor");
        checkVendedor.setOpaque(false);
        checkVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkVendedorActionPerformed(evt);
            }
        });
        panelImage1.add(checkVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, -1));

        txtBuscar.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtBuscar.setBorder(null);
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
        panelImage1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 36, 317, 43));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel9.setText("INGRESOS DE EFECTIVO");
        panelImage1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, 364, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoFacturasXs-01.png"))); // NOI18N
        panelImage1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, 50, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        panelImage1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 12, -1, 80));
        panelImage1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 82, 260, 10));

        btnExportar.setBackground(new java.awt.Color(5, 52, 99));
        btnExportar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnExportar.setForeground(new java.awt.Color(255, 255, 255));
        btnExportar.setText("Exportar a Excel");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        panelImage1.add(btnExportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 680, 190, 40));

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem1.setText("Cerra ventana");
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
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 1078, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed

    }//GEN-LAST:event_txtBuscarActionPerformed

    private void checkVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkVendedorActionPerformed

    }//GEN-LAST:event_checkVendedorActionPerformed

    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked
        int fila= tabla1.getSelectedRow();
        if(fila>=0){
            Select=tabla1.getSelectedRow();
            codigoIngreso= tabla1.getValueAt(Select,0).toString();
        }
           
    }//GEN-LAST:event_tabla1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void tabla1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla1MouseEntered

    private void MostrarIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarIngresoActionPerformed
        int fila= tabla1.getSelectedRow();
        if(fila>=0){
            Select=tabla1.getSelectedRow();
            codigoIngreso= tabla1.getValueAt(Select,0).toString();
            
            //IMPRIMO EL REPORTE
            Connection miconexion = conexion.ObtenerConexion();
            Map parametros = new HashMap();
            parametros.put("codf",codigoIngreso);

                try {
                    this.setVisible(false);
                    String reporte="ingresoEfectivo1.jasper";
                    JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                    JasperViewer ventanavisor=new JasperViewer(informe,false);
                    ventanavisor.setTitle("Reporte de ingreso de efectivo");
                    ventanavisor.setVisible(true);
                } catch (Exception e) {
                   JOptionPane.showMessageDialog(this, e.getMessage());
                }

        }
        
    }//GEN-LAST:event_MostrarIngresoActionPerformed

    private void checkCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCajaActionPerformed

    //PARA EXPORTAR A EXCEL
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
        //PARA EXPORTAR A EXCEL
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

    DefaultTableModel dm;
    /* Método filtro*/
    private void filtro2(String consulta, JTable jtableBuscar){  
        dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter(dm);
        jtableBuscar.setRowSorter(tr);

        int columna=0;
        // Identificamos cual es el JRadioButton seleccionado para filtrar el
        // resultado de acuerdo a los datos de la columna elegida
        if (checkVendedor.isSelected()) {
            columna = 2;
        }else if (checkCaja.isSelected()) {
                columna = 1;
              }else if (checkFecha.isSelected()) {
                        columna = 3;
                    }
        tr.setRowFilter(RowFilter.regexFilter(consulta, columna));
    }
   

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
            java.util.logging.Logger.getLogger(Consultar_Ingreso_Efectivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultar_Ingreso_Efectivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultar_Ingreso_Efectivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultar_Ingreso_Efectivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consultar_Ingreso_Efectivo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup FiltrarResultados;
    private javax.swing.JMenuItem MostrarIngreso;
    private javax.swing.JButton btnExportar;
    private javax.swing.JRadioButton checkCaja;
    private javax.swing.JRadioButton checkFecha;
    private javax.swing.JRadioButton checkVendedor;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JTable tabla1;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txt_recibe;
    // End of variables declaration//GEN-END:variables
}
