import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    Connection conexion;
     {
        try {
            //Obtenemos un objeto de conexion
            //usando una cadena de conexion
            conexion = DriverManager.getConnection("jdbc:mysql:/localhost:3306" +
                    "/Futbol(proyecto), Mario, 03062003mph");

            System.out.println("Se ha establecido la conexion exitosamente");

                //Cargamos el driver
                DriverManager.registerDriver( new com.mysql.cj.jdbc.Driver());

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    //Metodo para desconectar
    public boolean disconnect() {
        try {
            conexion.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    //Metodo para comprobar si esta conectado o no
        public boolean estaConectado() {
            try {
                return !conexion.isClosed();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
