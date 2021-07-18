
package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class enviar_remito extends javax.swing.JFrame {
    int Select;
    String codigo,nombre,apellido,fecha,empleado;
    boolean banderaFacturado=false;
    String NOMBRE,signo_moneda;

    public enviar_remito() {
        initComponents();
        
        //icono del sistema
        try{
            setIconImage(new ImageIcon(getClass().getResource("/img/iconoSistema64.png")).getImage());
        }catch(Exception e){
            
        } 
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement();
            ResultSet r2= consulta.executeQuery("select signo_moneda from  monedas where seleccion_moneda like 'SELECCIONADA'");
            while(r2.next()){
                signo_moneda=r2.getString(1);
            }
        }catch(Exception e){
            
        }
        
        setLocationRelativeTo(null);
        botonNoFacturados.setVisible(false);
        btnEliminar.setVisible(false);
        botonTodosLosRemitos.setVisible(false);
        txtCodigoCliente1.setVisible(false);
        txtBanderaRemito.setVisible(false);
        cargarRemitoNoFacturado();   
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        iniciar();
        JTableHeader th; 
        th = tabla.getTableHeader(); 
        Font fuente = new Font("Calibri", Font.BOLD, 20); 
        th.setFont(fuente); 
        th.setBackground(new Color(93,116,163));
        th.setForeground(new Color(255,255,255));
        
        JTableHeader thh; 
        thh = tabla2.getTableHeader(); 
        Font fuente2 = new Font("Calibri", Font.BOLD, 20); 
        thh.setFont(fuente2); 
        thh.setBackground(new Color(93,116,163));
        thh.setForeground(new Color(255,255,255));
    }    
    
    
    public void cargarRemitoNoFacturado(){
        TableColumn  column = null;    
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta.executeQuery("select * from remito where condicion='NO FACTURADO' AND cod_remito>0 order by cod_remito");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            
            tabla.setRowSorter(new TableRowSorter(modelo));
            tabla.getColumnModel().getColumn(1).setMaxWidth(400);
            tabla.getColumnModel().getColumn(1).setMinWidth(400);
            tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(400);
            tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(400);
            
            tabla.setRowSorter(new TableRowSorter(modelo));
            tabla.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla.getColumnModel().getColumn(3).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
            
            tabla.setRowSorter(new TableRowSorter(modelo));
            tabla.getColumnModel().getColumn(4).setMaxWidth(0);
            tabla.getColumnModel().getColumn(4).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
                           
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
            
            modelo.setNumRows(0);
            while(r.next()){
                String codigoCliente=r.getString(3),
                codigoEmpleado=r.getString(4);
                                
                modelo.addRow( new Object [] {null,null,null,null,null,null,null,null});
                tabla.setValueAt(r.getString(1),i,0);

                Statement consulta2=conn.createStatement();
                ResultSet r2= consulta2.executeQuery("select * from cliente order by cod_cliente");
                String nombreCliente="N° "+codigoCliente,apellidoCliente="";
                while(r2.next()){
                    if((r2.getString("cod_cliente")).equals(r.getString("cod_cliente"))){
                        nombreCliente=r2.getString(2);
                        apellidoCliente=r2.getString(3);      
                    }
                }
                tabla.setValueAt(nombreCliente,i,1);
                tabla.setValueAt(r.getString(2),i,2);

                Statement consulta3=conn.createStatement();
                ResultSet r3= consulta3.executeQuery("select * from empleado order by cod_empleado");
                String nombreEmpleado="N° "+codigoEmpleado,apellidoEmpleado="";
                while(r3.next()){
                    if(r3.getString(1).equals(codigoEmpleado)){
                        nombreEmpleado=r3.getString(2);
                        apellidoEmpleado=r3.getString(3);         
                    }
                }
                tabla.setValueAt(nombreEmpleado+" "+apellidoEmpleado,i,3);
                tabla.setValueAt(codigoCliente,i,4);
                tabla.setValueAt(codigoEmpleado,i,5);
                
                tabla.setValueAt(r.getString(7),i,6);//monto del remito
                i++;
                if(i==5)break;//version demo
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ;
            System.out.println(e);
        }
    }
    
    public void cargarRemitoFacturado(){
        TableColumn  column = null;     
         try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta.executeQuery("select * from remito where condicion='FACTURADO' order by cod_remito");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            tabla.setRowSorter(new TableRowSorter(modelo));
            
            tabla.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla.getColumnModel().getColumn(3).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);

            tabla.getColumnModel().getColumn(4).setMaxWidth(0);
            tabla.getColumnModel().getColumn(4).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
                            
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
            
            modelo.setNumRows(0);
            while(r.next()){
                String codigoCliente=r.getString(3),
                codigoEmpleado=r.getString(4);
                                
                modelo.addRow( new Object [] {null,null,null,null,null,null,null,null});
                tabla.setValueAt(r.getString(1),i,0);
                Statement consulta2=conn.createStatement();
                ResultSet r2= consulta2.executeQuery("select * from cliente order by cod_cliente");
                String nombreCliente="N° "+codigoCliente,apellidoCliente="";
                while(r2.next()){
                    if((r2.getString("cod_cliente")).equals(r.getString("cod_cliente"))){
                        nombreCliente=r2.getString(2);
                        apellidoCliente=r2.getString(3);                       
                    }
                }
                tabla.setValueAt(nombreCliente,i,1);
                tabla.setValueAt(r.getString(2),i,2);
                
                
                                
                Statement consulta3=conn.createStatement();
                ResultSet r3= consulta3.executeQuery("select * from empleado order by cod_empleado");
                String nombreEmpleado="N° "+codigoEmpleado,apellidoEmpleado="";
                while(r3.next()){
                    if(r3.getString(1).equals(codigoEmpleado)){
                        nombreEmpleado=r3.getString(2);
                        apellidoEmpleado=r3.getString(3);      
                    }
                }
                tabla.setValueAt(nombreEmpleado+" "+apellidoEmpleado,i,3);
                tabla.setValueAt(codigoCliente,i,4);
                tabla.setValueAt(codigoEmpleado,i,5);
                
                tabla.setValueAt(r.getString(7),i,6);//monto del remito
                i++;
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ;
            System.out.println(e);
        }
    }
    
    public void eliminarRemitoFacturado(){     
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
                   
            consulta.executeUpdate("delete from referencia_remito where cod_remito="+txtNumeroRemito.getText());
            consulta.executeUpdate("delete from remito where cod_remito="+txtNumeroRemito.getText());
            JOptionPane.showMessageDialog(null,"El remito fue eliminado del sistema") ;    
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en el SQL") ;
        }
        cargarRemitoFacturado();
    }
    
    public void iniciar(){
        panelRemito.setVisible(false);
        checkNombreCliente.setSelected(true);
        txtBuscar.setText("Ingrese su búsqueda");
        txtBuscar.setForeground(Color.gray);

        txtCodigoEmpleado1.setVisible(false);
        labelCodigoCliente.setVisible(false);
        labelCodigoEmpleado.setVisible(false);
        txtNombreApellidoEmpleado.setVisible(false);
        txtNombreApellidoCliente.setVisible(true);
        txtTotalRemito.setVisible(false);
        
        txtNumeroRemito.setEditable(false);
        txtFechaRemito.setEditable(false);
        txtTotalRemito2.setEditable(false);
    }
    
    public void MostrarRemito(){  
        TableColumn  column = null;
        
        tabla2.getColumnModel().getColumn(0).setMaxWidth(100);
        tabla2.getColumnModel().getColumn(0).setMinWidth(100);
        tabla2.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(100);
        tabla2.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(100);
        
        tabla2.getColumnModel().getColumn(1).setMaxWidth(110);
        tabla2.getColumnModel().getColumn(1).setMinWidth(110);
        tabla2.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(110);
        tabla2.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(110);
        
        tabla2.getColumnModel().getColumn(2).setMaxWidth(80);
        tabla2.getColumnModel().getColumn(2).setMinWidth(80);
        tabla2.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(80);
        tabla2.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(80);
        
        tabla2.getColumnModel().getColumn(4).setMaxWidth(400);
        tabla2.getColumnModel().getColumn(4).setMinWidth(400);
        tabla2.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(400);
        tabla2.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(400);

        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta.executeQuery("select * from referencia_remito where cod_remito="+txtNumeroRemito.getText());
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla2.getModel();
            tabla2.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            String total="";
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null,null,null,null});
                tabla2.setValueAt(r.getString(1),i,0);
                tabla2.setValueAt(txtFechaRemito.getText(),i,1);
                tabla2.setValueAt(r.getString(6),i,2);
                tabla2.setValueAt(r.getString(4),i,3);
                tabla2.setValueAt(r.getString(5),i,4);
                tabla2.setValueAt(r.getString(2),i,5);
                tabla2.setValueAt(r.getString(3),i,6);
                txtTotalRemito.setText(r.getString(7));
                txtTotalRemito2.setText(signo_moneda+" "+r.getString(7));
                i++;
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
        }
    }
    
    public void MostrarTodosLosRemitos(){     
        Double contador=0.0;
        TableColumn  column = null;

        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql

            ResultSet r= consulta.executeQuery("select rr.cantidad, rr.cod_articulo, rr.referencia, rr.valor_unitario, rr.valor_total, rr.cod_remito, r.fecha from referencia_remito rr, remito r where r.condicion='NO FACTURADO' AND r.cod_remito=rr.cod_remito AND r.cod_cliente='"+txtCodigoCliente1.getText()+"'");
            int i,j;
            i=0;
            j=0;
            DefaultTableModel modelo=(DefaultTableModel)tabla2.getModel();
            tabla2.setRowSorter(new TableRowSorter(modelo));
            modelo.setNumRows(0);
            String total="";
                            
            while(r.next()){
                modelo.addRow( new Object [] {null,null,null,null,null,null,null});
                tabla2.setValueAt(r.getString(6),i,0);
                tabla2.setValueAt(r.getString(7),i,1);
                tabla2.setValueAt(r.getString(1),i,2);
                tabla2.setValueAt(r.getString(2),i,3);
                tabla2.setValueAt(r.getString(3),i,4);
                tabla2.setValueAt(r.getString(4),i,5);
                tabla2.setValueAt(r.getString(5),i,6);
                contador=contador+ Double.parseDouble(tabla2.getValueAt(i,6).toString().replace(",","."));
                String contador2=String.format("%.2f", contador).replace(".",",");
                txtTotalRemito.setText(contador2);
                txtTotalRemito2.setText(signo_moneda+" "+contador2);
                i++;
            }
        } catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Este articulo Ya Existe") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
        }
    }    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        mostrarRemito = new javax.swing.JPopupMenu();
        verRemito = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        checkRemito = new javax.swing.JRadioButton();
        checkNombreCliente = new javax.swing.JRadioButton();
        checkEmpleado = new javax.swing.JRadioButton();
        panelRemito = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        txtNumeroRemito = new javax.swing.JTextField();
        txtCodigoCliente1 = new javax.swing.JTextField();
        txtFechaRemito = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelCodigoCliente = new javax.swing.JLabel();
        txtCodigoEmpleado1 = new javax.swing.JTextField();
        labelCodigoEmpleado = new javax.swing.JLabel();
        btnFacturarRemito = new javax.swing.JButton();
        txtNombreApellidoCliente = new javax.swing.JTextField();
        txtTotalRemito = new javax.swing.JTextField();
        txtNombreApellidoEmpleado = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTotalRemito2 = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        botonTodosLosRemitos = new javax.swing.JButton();
        txtBanderaRemito = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnEliminar1 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtFacturaNumero1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        botonNoFacturados = new javax.swing.JButton();
        botonFacturados = new javax.swing.JButton();
        checkFecha = new javax.swing.JRadioButton();

        verRemito.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        verRemito.setText("MOSTRAR REMITO");
        mostrarRemito.add(verRemito);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "N° REMITO", "CLIENTE", "FECHA", "VENDEDOR", "cod cliente", "cod empleado", "MONTO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setRowHeight(25);
        tabla.setRowMargin(4);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 980, 220));

        txtBuscar.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtBuscar.setBorder(null);
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFocusLost(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 50, 220, 40));

        buttonGroup1.add(checkRemito);
        checkRemito.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkRemito.setText("N° Remito");
        checkRemito.setOpaque(false);
        checkRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkRemitoActionPerformed(evt);
            }
        });
        jPanel1.add(checkRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, -1, -1));

        buttonGroup1.add(checkNombreCliente);
        checkNombreCliente.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkNombreCliente.setText("Cliente");
        checkNombreCliente.setOpaque(false);
        checkNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkNombreClienteActionPerformed(evt);
            }
        });
        jPanel1.add(checkNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, -1, -1));

        buttonGroup1.add(checkEmpleado);
        checkEmpleado.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkEmpleado.setText("Vendedor");
        checkEmpleado.setOpaque(false);
        checkEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkEmpleadoActionPerformed(evt);
            }
        });
        jPanel1.add(checkEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, -1, -1));

        panelRemito.setBackground(new java.awt.Color(255, 255, 255));
        panelRemito.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla2.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "N° Remito", "Fecha", "Cant", "Cod", "Descripcion", "Valor unitario", "Valor total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla2.setRowHeight(25);
        tabla2.setRowMargin(4);
        jScrollPane2.setViewportView(tabla2);

        panelRemito.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 980, 200));

        txtNumeroRemito.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtNumeroRemito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumeroRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroRemitoActionPerformed(evt);
            }
        });
        panelRemito.add(txtNumeroRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 90, 40));

        txtCodigoCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoCliente1ActionPerformed(evt);
            }
        });
        panelRemito.add(txtCodigoCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 70, 20));

        txtFechaRemito.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtFechaRemito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaRemitoActionPerformed(evt);
            }
        });
        panelRemito.add(txtFechaRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 130, 40));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        jLabel2.setText("TOTAL");
        panelRemito.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, 80, 40));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel3.setText("N° Remito");
        panelRemito.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        labelCodigoCliente.setText("COD_CLIENTE");
        panelRemito.add(labelCodigoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        txtCodigoEmpleado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoEmpleado1ActionPerformed(evt);
            }
        });
        panelRemito.add(txtCodigoEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, 70, 20));

        labelCodigoEmpleado.setText("COD_EMPLEADO");
        panelRemito.add(labelCodigoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, -1, -1));

        btnFacturarRemito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonFacturarRemito.png"))); // NOI18N
        btnFacturarRemito.setBorder(null);
        btnFacturarRemito.setBorderPainted(false);
        btnFacturarRemito.setContentAreaFilled(false);
        btnFacturarRemito.setFocusPainted(false);
        btnFacturarRemito.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonFacturarRemitoHover.png"))); // NOI18N
        btnFacturarRemito.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonFacturarRemitoHover.png"))); // NOI18N
        btnFacturarRemito.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonFacturarRemitoHover.png"))); // NOI18N
        btnFacturarRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarRemitoActionPerformed(evt);
            }
        });
        panelRemito.add(btnFacturarRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 290, -1, 42));

        txtNombreApellidoCliente.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        txtNombreApellidoCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreApellidoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreApellidoClienteActionPerformed(evt);
            }
        });
        panelRemito.add(txtNombreApellidoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 260, 40));

        txtTotalRemito.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        panelRemito.add(txtTotalRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, 121, 40));
        panelRemito.add(txtNombreApellidoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 150, 30));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel8.setText("Cliente");
        panelRemito.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, 40));

        txtTotalRemito2.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtTotalRemito2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panelRemito.add(txtTotalRemito2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 290, 190, 42));

        btnEliminar.setBackground(new java.awt.Color(5, 52, 99));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("ELIMINAR REMITO");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        panelRemito.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 290, 200, 40));

        botonTodosLosRemitos.setBackground(new java.awt.Color(5, 52, 99));
        botonTodosLosRemitos.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        botonTodosLosRemitos.setForeground(new java.awt.Color(255, 255, 255));
        botonTodosLosRemitos.setText("TODOS LOS REMITOS");
        botonTodosLosRemitos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonTodosLosRemitosActionPerformed(evt);
            }
        });
        panelRemito.add(botonTodosLosRemitos, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 200, 40));
        panelRemito.add(txtBanderaRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, 30, 50));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel9.setText("Fecha");
        panelRemito.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, 40));

        btnEliminar1.setBackground(new java.awt.Color(5, 52, 99));
        btnEliminar1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnEliminar1.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar1.setText("IMPRIMIR");
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });
        panelRemito.add(btnEliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 200, 40));

        jPanel1.add(panelRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoRemitosXs-01.png"))); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 50));

        txtFacturaNumero1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        txtFacturaNumero1.setText("REMITOS NO FACTURADOS");
        jPanel1.add(txtFacturaNumero1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, 230, 10));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonBuscar.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, -1, 80));

        botonNoFacturados.setBackground(new java.awt.Color(5, 52, 99));
        botonNoFacturados.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        botonNoFacturados.setForeground(new java.awt.Color(255, 255, 255));
        botonNoFacturados.setText("Mostrar Remitos NO Facturados");
        botonNoFacturados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNoFacturadosActionPerformed(evt);
            }
        });
        jPanel1.add(botonNoFacturados, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 400, 40));

        botonFacturados.setBackground(new java.awt.Color(93, 116, 163));
        botonFacturados.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        botonFacturados.setForeground(new java.awt.Color(255, 255, 255));
        botonFacturados.setText("Mostrar Remitos Facturados");
        botonFacturados.setBorderPainted(false);
        botonFacturados.setFocusPainted(false);
        botonFacturados.setRequestFocusEnabled(false);
        botonFacturados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFacturadosActionPerformed(evt);
            }
        });
        jPanel1.add(botonFacturados, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 450, 40));

        buttonGroup1.add(checkFecha);
        checkFecha.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        checkFecha.setText("Fecha");
        checkFecha.setOpaque(false);
        checkFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkFechaActionPerformed(evt);
            }
        });
        jPanel1.add(checkFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 710));

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
        if (checkRemito.isSelected()) {
            columna = 0;
        }else if (checkNombreCliente.isSelected()) {
                    columna = 1;
              }else if (checkEmpleado.isSelected()) {
                        columna = 4;
                    }else if (checkFecha.isSelected()) {
                        columna = 2;
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

    private void checkRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkRemitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkRemitoActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if(banderaFacturado==true){
            botonTodosLosRemitos.setVisible(false);
        }else
            botonTodosLosRemitos.setVisible(true);
        
        txtBanderaRemito.setText("0");
        panelRemito.setVisible(true);
        int fila= tabla.getSelectedRow();

        if(fila>=0){
            Select=tabla.getSelectedRow();
            txtNumeroRemito.setText(tabla.getValueAt(Select,0).toString());
            txtFechaRemito.setText(tabla.getValueAt(Select,2).toString());
            txtCodigoCliente1.setText(tabla.getValueAt(Select,4).toString());
            txtCodigoEmpleado1.setText(tabla.getValueAt(Select,5).toString());
            txtNombreApellidoEmpleado.setText(tabla.getValueAt(Select,3).toString());
            txtNombreApellidoCliente.setText(tabla.getValueAt(Select,1).toString());
            MostrarRemito();      
        }else{
            JOptionPane.showMessageDialog(null,"No selecciono ninguna fila");
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void txtNumeroRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroRemitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroRemitoActionPerformed

    private void txtCodigoCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoCliente1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoCliente1ActionPerformed

    private void txtFechaRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaRemitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaRemitoActionPerformed

    private void txtCodigoEmpleado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoEmpleado1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoEmpleado1ActionPerformed

    private void btnFacturarRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarRemitoActionPerformed
        String domicilioCliente="",cuitCliente="";
        Double sumaRemitos=0.00;
        Double pagoCuentaCorriente=0.0, saldoRestante=0.0;
        Double totalRemitoActual=Double.parseDouble(txtTotalRemito.getText().replace(",", "."));
        
        
        Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
                                    
        try{
            Statement consultaCliente=conn.createStatement();
            
            ResultSet resCliente= consultaCliente.executeQuery("select * from cliente where cod_cliente="+txtCodigoCliente1.getText());

            while(resCliente.next()){ //TRAIGO LOS DATOS DE LA EMPRESA PARA PASARCELOS A LA FACTURA
                if(!resCliente.getString(4).equals("") || !resCliente.getString(3).equals("")){
                    domicilioCliente= (resCliente.getString(4)+", "+resCliente.getString(3));
                }
                cuitCliente=resCliente.getString(8);
                
            }
            
            Statement consultaRemito=conn.createStatement();
            ResultSet resRemito= consultaRemito.executeQuery("SELECT SUM(total) FROM remito WHERE cod_cliente='"+txtCodigoCliente1.getText()+"' AND condicion LIKE 'NO FACTURADO'");
            
            while(resRemito.next()){
                sumaRemitos=Double.parseDouble(resRemito.getString(1));
            }
            
            Statement consultaCuentaCorriente=conn.createStatement();
            ResultSet resCuentaCorriente= consultaCuentaCorriente.executeQuery("select total_pago, saldo_restante from cuenta_corriente where cod_cliente="+txtCodigoCliente1.getText());
            
            while(resCuentaCorriente.next()){
                pagoCuentaCorriente=Double.parseDouble(resCuentaCorriente.getString(1));
                saldoRestante=Double.parseDouble(resCuentaCorriente.getString(2));
            }
            
            
        }catch(Exception e){
            
        }

        if(((sumaRemitos-totalRemitoActual)>=pagoCuentaCorriente) || saldoRestante==0.0){
            this.dispose();
            Frm_facturarRemito facturarRemito= new Frm_facturarRemito();
            
            if(txtBanderaRemito.getText().equals("0")){
                facturarRemito.txtCodigoCliente.setText(txtCodigoCliente1.getText());
                facturarRemito.txtCodigoEmpleado.setText(txtCodigoEmpleado1.getText());
                facturarRemito.txtCliente.setText(txtNombreApellidoCliente.getText());
                facturarRemito.txtDomicilio.setText(domicilioCliente);
                facturarRemito.txtCuit.setText(cuitCliente);
                try{
                    facturarRemito.cargarComboEmpleado();
                } catch(ClassNotFoundException e){
                    JOptionPane.showMessageDialog(null,"No hay conexion con Mysql") ;

                }
                facturarRemito.comboVendedor.setSelectedItem(txtNombreApellidoEmpleado.getText());
                facturarRemito.txtCodigoRemito.setText(txtNumeroRemito.getText());
                facturarRemito.txtCodiRemito.setText(txtNumeroRemito.getText());
                facturarRemito.txtRecibeRemito.setText(txtBanderaRemito.getText());
            }else{
               try{
                    facturarRemito.cargarComboEmpleado();
                } catch(ClassNotFoundException e){
                    JOptionPane.showMessageDialog(null,"No hay conexion con Mysql") ;

                }
                facturarRemito.txtDomicilio.setText(domicilioCliente);
                facturarRemito.txtCodigoCliente.setText(txtCodigoCliente1.getText());
                facturarRemito.txtCliente.setText(txtNombreApellidoCliente.getText());
                facturarRemito.txtRecibeRemito.setText(txtBanderaRemito.getText());
                facturarRemito.txtCuit.setText(cuitCliente);
           } 
        }else{
            JOptionPane.showMessageDialog(null,"¡ATENCIÓN! El remito que desea facturar dejaria una inconsistencia en los saldos del cliente, seleccione un remito con monto mas bajo") ;
        }
             
    }//GEN-LAST:event_btnFacturarRemitoActionPerformed

    private void txtNombreApellidoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreApellidoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreApellidoClienteActionPerformed

    private void checkEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkEmpleadoActionPerformed

    private void checkNombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkNombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkNombreClienteActionPerformed

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

    private void botonNoFacturadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNoFacturadosActionPerformed
        cargarRemitoNoFacturado();
        banderaFacturado=false;
        botonFacturados.setVisible(true);
        botonNoFacturados.setVisible(false);
        btnFacturarRemito.setVisible(true);
        panelRemito.setVisible(false);
        txtFacturaNumero1.setText("REMITOS NO FACTURADOS");
        btnEliminar.setVisible(false);
    }//GEN-LAST:event_botonNoFacturadosActionPerformed

    private void botonFacturadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFacturadosActionPerformed
        cargarRemitoFacturado();
        banderaFacturado=true;
        botonFacturados.setVisible(false);
        botonNoFacturados.setVisible(true);
        btnFacturarRemito.setVisible(false);
        panelRemito.setVisible(false);
        txtFacturaNumero1.setText("REMITOS FACTURADOS");
        btnEliminar.setVisible(true);
    }//GEN-LAST:event_botonFacturadosActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarRemitoFacturado();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void botonTodosLosRemitosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTodosLosRemitosActionPerformed
        MostrarTodosLosRemitos();
        txtBanderaRemito.setText("1");
    }//GEN-LAST:event_botonTodosLosRemitosActionPerformed

    private void checkFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkFechaActionPerformed

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
        Connection miconexion = conexion.ObtenerConexion();
        Map parametros = new HashMap();
        parametros.put("codf",txtNumeroRemito.getText());
     
        //int op=JOptionPane.showConfirmDialog(null, "REMITO AGREGADO ¿DESEA IMPRIMIRLO?","SELECCIONE UN OPCION" ,JOptionPane.YES_NO_OPTION);
        //if (op==JOptionPane.YES_NO_OPTION){
        if(!txtNumeroRemito.getText().equals("")){
            try {
                this.setVisible(false);
                String reporte="remitoa.jasper";
                JasperPrint informe =JasperFillManager.fillReport(reporte, parametros,miconexion);
                JasperViewer ventanavisor=new JasperViewer(informe,false);
                ventanavisor.setTitle("Reporte de remito");
                ventanavisor.setVisible(true);
            }catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } 
        }else{
            JOptionPane.showMessageDialog(this,"No hay ningun remito seleccionado");
        }
            
        //}

    }//GEN-LAST:event_btnEliminar1ActionPerformed

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
            java.util.logging.Logger.getLogger(enviar_remito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(enviar_remito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(enviar_remito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(enviar_remito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new enviar_remito().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonFacturados;
    private javax.swing.JButton botonNoFacturados;
    private javax.swing.JButton botonTodosLosRemitos;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminar1;
    private javax.swing.JButton btnFacturarRemito;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton checkEmpleado;
    private javax.swing.JRadioButton checkFecha;
    private javax.swing.JRadioButton checkNombreCliente;
    private javax.swing.JRadioButton checkRemito;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelCodigoCliente;
    private javax.swing.JLabel labelCodigoEmpleado;
    private javax.swing.JPopupMenu mostrarRemito;
    private javax.swing.JPanel panelRemito;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla2;
    private javax.swing.JTextField txtBanderaRemito;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigoCliente1;
    private javax.swing.JTextField txtCodigoEmpleado1;
    private javax.swing.JLabel txtFacturaNumero1;
    private javax.swing.JTextField txtFechaRemito;
    private javax.swing.JTextField txtNombreApellidoCliente;
    private javax.swing.JTextField txtNombreApellidoEmpleado;
    private javax.swing.JTextField txtNumeroRemito;
    private javax.swing.JTextField txtTotalRemito;
    private javax.swing.JTextField txtTotalRemito2;
    private javax.swing.JMenuItem verRemito;
    // End of variables declaration//GEN-END:variables
}
