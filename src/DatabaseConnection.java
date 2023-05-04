import java.sql.*;

public class DatabaseConnection {
    Connection conexion;
     {
        try {
            //Obtenemos un objeto de conexion
            //usando una cadena de conexion
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Futbol",
                    "Mario", "03062003mph");

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
    public void mostrarDatos(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();

        // Imprimir los nombres de las columnas
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(md.getColumnName(i) + "\t");
        }
        System.out.println();

        // Imprimir los datos de cada fila
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getObject(i) + "\t");
            }
            System.out.println();
        }
    }

}
