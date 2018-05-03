

package Vistas;

import java.sql.Connection;
import javax.swing.JOptionPane;
/**
 *
 * @author Manuel
 */
class cliente {
    public static void main(String[] args)
    {
        Connection miConexion;
        miConexion=conexion.GetConnection();
      
        if(miConexion!=null)
        {
            JOptionPane.showMessageDialog(null, "Conexión Realizada Correctamente");
        }
    }
}
