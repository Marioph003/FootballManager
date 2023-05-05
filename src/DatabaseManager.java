import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public List<Jugador> buscarJugadores(String nombre, int edad) {
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

            while (rs.next()) {
                int id = rs.getInt("Cod_Jugador");
                String nombreJugador = rs.getString("Nombre");
                int edadJugador = rs.getInt("Edad");
                String nombreEquipo = rs.getString("Nombre_equipo");

                jugadores.add(new Jugador
                        (id, nombreJugador, edadJugador, nombreEquipo));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jugadores;
    }

    public List<Equipo> buscarEquipos(String nombre, int puntos) {
        List<Equipo> equipos = new ArrayList<Equipo>();
        //Esta es la consulta sql que voy a usar para filtrar
        String sql = "select * from Equipo where Nombre = ? and Puntos = ?";
        try {
            //Preparamos la consulta
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, puntos);
            //Una vez preparada la ejecutamos
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int puntosEquipo = rs.getInt("Puntos");
                String nombreEquipo = rs.getString("Nombre");
                int numJugadores = rs.getInt("N_Jugadores");

                equipos.add(new Equipo(puntosEquipo, nombreEquipo, numJugadores));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipos;
    }

    public List<Estadio> buscarEstadios(String nombre, int aforo) {
        List<Estadio> estadios = new ArrayList<Estadio>();
        //Esta es la consulta sql que voy a usar para filtrar
        String sql = "select * from Estadio where Nombre = ? and Aforo = ?";
        try {
            //Preparamos la consulta
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, aforo);
            //Una vez preparada la ejecutamos
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int puntosEquipo = rs.getInt("Aforo");
                String nombreEquipo = rs.getString("Nombre");
                LocalDate fechaConstruccion = LocalDate.parse(rs.getDate("Fecha_Construccion").toString());

                estadios.add(new Estadio(puntosEquipo, nombreEquipo, fechaConstruccion));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estadios;
    }

    public List<Partido> buscarPartidos(int duracion, int cod_Partido) {
        List<Partido> partidos = new ArrayList<Partido>();
        //Esta es la consulta sql que voy a usar para filtrar
        String sql = "select * from Partido where Duracion = ? and Cod_Partido = ?";
        try {
            //Preparamos la consulta
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, duracion);
            ps.setInt(2, cod_Partido);
            //Una vez preparada la ejecutamos
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int duracionPartido = rs.getInt("Duracion");
                int codPartido = rs.getInt("Cod_Partido");

                partidos.add(new Partido(duracionPartido, codPartido));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partidos;
    }
}
