

package Vistas;
import java.awt.Color;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class enviar_producto_seleccionado extends javax.swing.JFrame {
    int Select,seleccionarTodos=0,contador=0;
    String codigo,referencia,cantidad,marca,valor,valorBruto;
    String NOMBRE;
    String orden="NOMBRE";
    String forma="ASC";
    boolean TodosSeleccionados=false;

    public enviar_producto_seleccionado() {
        initComponents();
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        }
        txtRecibe.setVisible(false);
         
        setLocationRelativeTo(null);
        checkCodigo.setSelected(true);
        txtBuscar.requestFocus();
        
        
        JTableHeader th; 
        th = tabla.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
  
        MostrarArticulos();
    }
    
    DefaultTableModel modelo2 = new DefaultTableModel(); //ES PARA LA TABLA PAPELERA
     private boolean[] editable = {true,false,false,false,true,false,false,false,false,false,false,false,false};
     
     public void MostrarArticulos(){
        Object[] datos= new Object[6]; //LE DIGO QUE TIENE 5 COLUMNAS
        try{       
            Connection conn=conexion.ObtenerConexion();
            Statement consulta=conn.createStatement();
            Statement consulta2=conn.createStatement();
            
            ResultSet r2= consulta.executeQuery("select * from  articulo WHERE estado='ACTIVO' order by referencia");
            int i=0,j=0;

            Render3 r= new Render3(); // PARA LOS COLORES
             
            //MUESTRO LOS ART EN LA PAPELERA
            tabla.setDefaultRenderer(Object.class,r);
            DefaultTableModel modelo = new DefaultTableModel(new String[]{"SELECCIONAR","CODIGO", "      DESCRIPCION      ", "MARCA","STOCK","PRECIO"}, 0) {   
                Class[] types = new Class[]{
                    java.lang.Boolean.class,java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                };

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                 public boolean isCellEditable(int row, int column){
                     return editable[column];
                }
            };
            
            
                    
            if(seleccionarTodos==1){ //SI APRETO EL BOTON SELECCIONAR TODOS, SETEO TODOS LOS CHHECK EN HABILITADOS
               while(r2.next()){
                    if(r2.getString(7).equals("ACTIVO")){
                        datos[0]=true;
                        datos[1]=r2.getObject(1);
                        datos[2]=r2.getObject(2);
                        datos[3]=r2.getObject(8);    
                        datos[4]=r2.getObject(3);  
                        datos[5]=r2.getObject(12);  
                        
                        modelo.addRow(datos);
                    }      
                } 
                seleccionarTodos=0;
            }else{
                while(r2.next()){ //DESABILITO TODOS LOS CHECK
                    if(r2.getString(7).equals("ACTIVO")){
                        
                        if(r2.getString(18).equals("SI")){
                            datos[0]=true;
                        }else{
                            datos[0]=false;
                        }
                        
                        datos[1]=r2.getObject(1);
                        datos[2]=r2.getObject(2);
                        datos[3]=r2.getObject(8);
                        datos[4]=r2.getObject(3);
                        datos[5]=r2.getObject(12);  
                        modelo.addRow(datos);
                    }     
                }
            }
            tabla.setModel(modelo);
            
            tabla.getColumnModel().getColumn(0).setMaxWidth(130);
            tabla.getColumnModel().getColumn(0).setMinWidth(130);
            tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(130);
            tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(130);
            
            tabla.getColumnModel().getColumn(1).setMaxWidth(170);
            tabla.getColumnModel().getColumn(1).setMinWidth(170);
            tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(170);
            tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(170);
            
            tabla.getColumnModel().getColumn(2).setMaxWidth(470);
            tabla.getColumnModel().getColumn(2).setMinWidth(470);
            tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(470);
            tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(470);
            
            tabla.getColumnModel().getColumn(3).setMaxWidth(250);
            tabla.getColumnModel().getColumn(3).setMinWidth(250);
            tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(250);
            tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(250);
            
            tabla.getColumnModel().getColumn(4).setMaxWidth(100);
            tabla.getColumnModel().getColumn(4).setMinWidth(100);
            tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(100);
            tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(100);
            
            tabla.getColumnModel().getColumn(5).setMaxWidth(130);
            tabla.getColumnModel().getColumn(5).setMinWidth(130);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(130);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(130);




            tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //LE DIGO QUE LA TABLA NO SE PUEDA AUTOAJUSTAR
            tabla.doLayout();
            r2.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Ocurrio un error en la base de datos (pepelera articulos 701)") ;
        }      
    }
     
     public void enviarProducto(){
         try{     
            Connection conn=conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            Statement consulta1=conn.createStatement(); // crea una variable que se encargue del código de sql
            consulta1.executeUpdate("UPDATE articulo SET impresion_etiqueta='NO' where estado='ACTIVO'");
            contador=0;
            for(int i=0;i<tabla.getRowCount();i++){  
                String pago2= tabla.getValueAt(i,0).toString();
                
                if(pago2.equals("true")){ 
                   consulta.executeUpdate("UPDATE articulo SET impresion_etiqueta='SI' where cod_articulo='"+tabla.getValueAt(i,1).toString()+"'");
                   contador++;
                }
            }
            
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        if (txtRecibe.getText().equals("1")){
            Connection miconexion = conexion.ObtenerConexion();    
            try{   
                String reporte="codigoBarrasSeleccionado.jasper";
                JasperPrint informe =JasperFillManager.fillReport(reporte,null,miconexion);
                JasperViewer ventanavisor=new JasperViewer(informe,false);
                ventanavisor.setTitle("Reporte de codigo de barras TANDA DE 1");
                ventanavisor.setVisible(true);    
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }else if (txtRecibe.getText().equals("2")){
                Connection miconexion = conexion.ObtenerConexion();    
                try{   
                    String reporte="codigoBarrasSeleccionadoX2.jasper";
                    JasperPrint informe =JasperFillManager.fillReport(reporte,null,miconexion);
                    JasperViewer ventanavisor=new JasperViewer(informe,false);
                    ventanavisor.setTitle("Reporte de codigo de barras TANDA DE 2");
                    ventanavisor.setVisible(true);    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }else if (txtRecibe.getText().equals("3")){
                    Connection miconexion = conexion.ObtenerConexion();    
                    try{   
                        String reporte="codigoBarrasSeleccionadoX4.jasper";
                        JasperPrint informe =JasperFillManager.fillReport(reporte,null,miconexion);
                        JasperViewer ventanavisor=new JasperViewer(informe,false);
                        ventanavisor.setTitle("Reporte de codigo de barras TANDA DE 4");
                        ventanavisor.setVisible(true);    
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                }else if (txtRecibe.getText().equals("4")){
                        Connection miconexion = conexion.ObtenerConexion();    
                        try{   
                            String reporte="codigoBarrasSeleccionadoX8.jasper";
                            JasperPrint informe =JasperFillManager.fillReport(reporte,null,miconexion);
                            JasperViewer ventanavisor=new JasperViewer(informe,false);
                            ventanavisor.setTitle("Reporte de codigo de barras TANDA DE 8");
                            ventanavisor.setVisible(true);    
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, e.getMessage());
                        }
                    }
         }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FiltrarResultados = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        checkMarca = new javax.swing.JRadioButton();
        checkDescripcion = new javax.swing.JRadioButton();
        checkCodigo = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        enviarProducto = new javax.swing.JButton();
        txtRecibe = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscar.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtBuscar.setBorder(null);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 310, 50));

        FiltrarResultados.add(checkMarca);
        checkMarca.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkMarca.setText("Marca");
        checkMarca.setOpaque(false);
        jPanel1.add(checkMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));

        FiltrarResultados.add(checkDescripcion);
        checkDescripcion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkDescripcion.setText("Descripción");
        checkDescripcion.setOpaque(false);
        jPanel1.add(checkDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        FiltrarResultados.add(checkCodigo);
        checkCodigo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkCodigo.setText("Codigo");
        checkCodigo.setOpaque(false);
        jPanel1.add(checkCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, -1));

        tabla.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla.setRowHeight(25);
        tabla.setRowMargin(4);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 1250, 500));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel9.setText("ARTICULOS SELECCIONADOS");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 440, 60));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoArticulosXs-01.png"))); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 50, 70));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 320, 20));

        enviarProducto.setBackground(new java.awt.Color(93, 116, 163));
        enviarProducto.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        enviarProducto.setForeground(new java.awt.Color(255, 255, 255));
        enviarProducto.setText("Mostrar Etiquetas");
        enviarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarProductoActionPerformed(evt);
            }
        });
        jPanel1.add(enviarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 670, 240, 40));
        jPanel1.add(txtRecibe, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 670, 44, -1));

        jButton4.setBackground(new java.awt.Color(93, 116, 163));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Seleccionar Todos");
        jButton4.setToolTipText("");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusPainted(false);
        jButton4.setOpaque(true);
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonSelectTodosHover.png"))); // NOI18N
        jButton4.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonSelectTodosHover.png"))); // NOI18N
        jButton4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonSelectTodosHover.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 100, 208, 40));

        jButton5.setBackground(new java.awt.Color(93, 116, 163));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Deseleccionar Todos");
        jButton5.setToolTipText("");
        jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setFocusPainted(false);
        jButton5.setOpaque(true);
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonSelectTodosHover.png"))); // NOI18N
        jButton5.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonSelectTodosHover.png"))); // NOI18N
        jButton5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonSelectTodosHover.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 100, 208, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
            columna = 1;
        }else  if (checkDescripcion.isSelected()) {
                    columna = 2;
                }else if (checkMarca.isSelected()) {
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

    private void enviarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarProductoActionPerformed
        
       enviarProducto();
    }//GEN-LAST:event_enviarProductoActionPerformed

    private void tablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyPressed
        
    }//GEN-LAST:event_tablaKeyPressed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
       
    }//GEN-LAST:event_tablaMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        TodosSeleccionados=true;
        seleccionarTodos=1;
        MostrarArticulos();
        txtBuscar.requestFocus();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        for(int i=0;i<tabla.getRowCount();i++){  
                tabla.setValueAt(false, i, 0);
            }
        txtBuscar.requestFocus();
    }//GEN-LAST:event_jButton5ActionPerformed
 
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
            java.util.logging.Logger.getLogger(enviar_producto_seleccionado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(enviar_producto_seleccionado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(enviar_producto_seleccionado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(enviar_producto_seleccionado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new enviar_producto_seleccionado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup FiltrarResultados;
    private javax.swing.JRadioButton checkCodigo;
    private javax.swing.JRadioButton checkDescripcion;
    private javax.swing.JRadioButton checkMarca;
    public static javax.swing.JButton enviarProducto;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtRecibe;
    // End of variables declaration//GEN-END:variables
}
