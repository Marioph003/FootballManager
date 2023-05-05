import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager2 {
    private Connection conexion;

    {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Futbol",
                    "Mario", "03062003mph");
                /*//Solicitamos un ResultSet de una consulta sql, esto va a hacer que accedamos
                //a una tabla en concreto de mi base de datos
                Statement stmnt = conexion.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from Equipo");
                //Selecciono el registro Puntos de la tabla Equipo
                rs.getInt("Puntos");
                //Selecciono el registro Nombre de la tabla Equipo
                rs.getString("Nombre");
                //Selecciono el registro Nº_Jugadores de la tabla Equipo
                rs.getInt("Nº_Jugadores");*/
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //List<Map<String, Object>>, esto guarda una lista en la que cada uno de los elementos
    //Son un mapa, lo voy a utilizar para almacenar los datos de la base de datos
    public <T> List<Map<String, Object>> buscar2(String tabla,
                                                 @NotNull Map<String, Object> parametros) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> campos = new ArrayList<>();

        StringBuilder sql = new StringBuilder("select * from ");
        sql.append(tabla).append(" where ");

        //Voy a construir la clausula where a partir de los parametros
        for (String columna : parametros.keySet()) {
            sql.append(columna).append(" = ? and ");
        }
        sql.setLength(sql.length() - 5); //Eliminamos el AND porque no nos hace falta

        try {
            ps = conexion.prepareStatement(sql.toString());
            int i = 1;

            //Este forEach lo que hace es ir iterando con todos los elementos del mapa
            for (Object valor : parametros.values()) {
                ps.setObject(i, valor);
                i++;
            }

            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            //Sirve para controlar el numero de columnas que hay
            int numColumnas = rsmd.getColumnCount();

            while (rs.next()) {
                //creamos un mapa donde vamos a guardar el nombre de las columnas
                //Y el valor de cada una
                Map<String, Object> fila = new HashMap<>();
                //El bucle for nos sirve para iterar todas las columnas
                for (int j = 1; j <= numColumnas; j++) {
                    //Segun el registro que corresponda saldrá un nombre u otro
                    String nombreColumna = rsmd.getColumnName(j);
                    //Y tambien dependiendo del registro se ejecutara un valor
                    Object valorColumna = rs.getObject(j);
                    //añadimos los elementos al mapa
                    fila.put(nombreColumna, valorColumna);
                }
                //Y finalmente añadimos el mapa al ArrayList de mapas
                campos.add(fila);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return campos;
    }

    public List<FootballManagerInterface> buscar(String tabla,
                                                 @NotNull Map<String, Object> parametros) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<FootballManagerInterface> resultados = new ArrayList<>();

        StringBuilder sql = new StringBuilder("select * from ");
        sql.append(tabla).append(" where ");

        //Voy a construir la clausula where a partir de los parametros
        for (String columna : parametros.keySet()) {
            sql.append(columna).append(" = ? and ");
        }
        sql.setLength(sql.length() - 5); //Eliminamos el AND porque no nos hace falta

        try {
            ps = conexion.prepareStatement(sql.toString());
            int i = 1;

            //Este forEach lo que hace es ir iterando con todos los elementos del mapa
            for (Object valor : parametros.values()) {
                ps.setObject(i, valor);
                i++;
            }

            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            //Sirve para controlar el numero de columnas que hay
            int numColumnas = rsmd.getColumnCount();

            while (rs.next()) {

                //creamos un mapa donde vamos a guardar el nombre de las columnas
                //Y el valor de cada una

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return resultados;
    }
}
