import java.sql.*;

/**
 * Clase para establecer conexion con la base de datos
 * atraves de un driver
 *
 * @author Mario
 * @version 1.0
 */
public class DatabaseConnection {
    private Connection conexion;
    private String connectionString;

    public DatabaseConnection(@NonNull String connectionString){
    //guarda los datos de conexión
        this.connectionString = connectionString;
        try {
            //registrar el controlador
            DriverManager.registerDriver (new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para establecer conexion con la base de datos
     *
     * @param connection: objeto de tipo connection necesario
     *                  para establecer la conexion
     *
     * @return: devuelve true: en caso de ser exitosa
     * y false: en caso de que haya fracasado
     */
     public boolean getConnection(Connection connection){
        this.conexion = connection;
         return connection==null?false:true;
     }

    /**
     * Metodo para desconectar la conexion a la base de datos
     *
     * @return: true: Si se ha desconectado correctamente
     * false: si no se ha desconectado
     */
    public boolean disconnect() {
        try {
            conexion.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Metodo para comprobar si esta conectado o no
     *
     * @return: Si la conexion no esta cerrada devolvera un true
     */
        public boolean estaConectado() {
            try {
                return !conexion.isClosed();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    /**
     * Metodo getter de connectionString
     * @return: devuelve la propiedad
     */
    public String getConnectionString(){
        return this.connectionString;
        }

    /**
     * Metodo setter del objeto conexion
     * @param connection: Un objeto connection
     */
    public void setConexion(Connection connection){
        this.conexion = connection;
        }
}
