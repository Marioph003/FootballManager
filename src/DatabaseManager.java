import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseManager {
    private Connection conexion;
    {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Futbol",
                    "Mario", "03062003mph");
            //Solicitamos un ResultSet de una consulta sql, esto va a hacer que accedamos
            //a una tabla en concreto de mi base de datos
            /*Statement stmnt = conexion.createStatement();
            ResultSet rs = stmnt.executeQuery("select * from Equipo");
            //Selecciono el registro Puntos de la tabla Equipo
            if (rs.next()) {
                rs.getInt("Puntos");
            } else {
                System.out.println("No se ha encontrado");
            }
            //Selecciono el registro Nombre de la tabla Equipo
            if (rs.next()) {
                rs.getString("Nombre");
            } else {
                System.out.println("No se ha encontrado");
            }
            //Selecciono el registro Nº_Jugadores de la tabla Equipo
            if (rs.next()) {
                rs.getInt("N_Jugadores");
            } else {
                System.out.println("No se ha encontrado");
            }*/
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Metodo para buscar un jugador por 2 parametros
    public List<Jugador> buscarJugadores(String nombre, int edad){
        List<Jugador> jugadores = new ArrayList<Jugador>();
        //Esta es la consulta sql que voy a usar para filtrar
        String sql = "select * from Jugador where Nombre = ? and Edad = ?";
        try {
            //Preparamos la consulta
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, edad);
            //Una vez preparada la ejecutamos
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
            int id = rs.getInt("Cod_Jugador");
            String nombreJugador = rs.getString("Nombre");
            int edadJugador = rs.getInt("Edad");
            String nombreEquipo = rs.getString("Nombre_equipo");

            jugadores.add(new Jugador
                    (id, nombreJugador, edadJugador, nombreEquipo));

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return jugadores;
    }
    //List<Map<String, Object>>, esto guarda una lista en la que cada uno de los elementos
    //Son un mapa, lo voy a utilizar para almacenar los datos de la base de datos
    public List<Map<String, Object>> buscarJugadores2 (String tabla,
                                                       @NotNull Map<String, Object> parametros){
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Map<String, Object>> resultados = new ArrayList<>();

        StringBuilder sql = new StringBuilder("select * from");
        sql.append(tabla).append("where");

        //Voy a construir la clausula where apartir de los parametros
        for (String columna: parametros.keySet()) {
            sql.append(columna).append("= ? ");

            try {
                ps = conexion.prepareStatement(sql.toString());
                int i = 1;

                for (Object valor: parametros.values()) {

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
