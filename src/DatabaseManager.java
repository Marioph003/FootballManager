import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    Connection conexion;
    {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql:/localhost:3306" +
                    "/Futbol(proyecto), Mario, 03062003mph");
            //Solicitamos un ResultSet de una consulta sql, esto va a hacer que accedamos
            //a una tabla en concreto de mi base de datos
            Statement stmnt = conexion.createStatement();
            ResultSet rs = stmnt.executeQuery("select * from Equipo");
            //Selecciono el registro Puntos de la tabla Equipo
            rs.getInt("Puntos");
            //Selecciono el registro Nombre de la tabla Equipo
            rs.getString("Nombre");
            //Selecciono el registro Nº_Jugadores de la tabla Equipo
            rs.getInt("Nº_Jugadores");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Metodo para buscar un jugador por 2 parametros
    public List<Jugadores> buscarJugadores(String nombre, int edad){
        List<Jugadores> jugadores = new ArrayList<Jugadores>();
        //Esta es la consulta sql que voy a usar para filtrar
        String sql = "select Nombre, Edad from jugadores where Nombre = ? and Edad = ?";
        try {
            //Preparamos la consulta
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, edad);
            //Una vez preparada la ejecutamos
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
            int id = rs.getInt("id");
            String nombreJugador = rs.getString("Nombre");
            int edadJugador = rs.getInt("Edad");
            String nombreEquipo = rs.getString("Nombre_Equipo");

            jugadores.add(new Jugadores
                    (id, nombreJugador, nombreEquipo, edadJugador));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return jugadores;
    }
}
