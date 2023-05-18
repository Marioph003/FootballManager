import java.sql.*;

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
     public boolean getConnection(Connection connection){
        this.conexion = connection;
         return connection==null?false:true;
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
        public String getConnectionString(){
        return this.connectionString;
        }
        public void setConexion(Connection connection){
        this.conexion = connection;
        }
}
