import java.sql.*;
import java.time.LocalDate;
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

    /**
     * @param campoOrdenacion: Lo utilizo para especificar el campo por el cual voy a ordenar
     *                         Ej(NombreEquipo). Este seria un ejemplo de campo de ordenacion
     * @param asc:             Lo utilizo para especificar si es ascendente o descendente por lo que voy a ordenar
     * @param parametros:      Aqui recojo el nombre y el valor de la columna de la tabla de mi base de
     *                         datos
     * @return: Voy a devolver una lista en la que se recogen los datos obtenidos
     */
    public List<Jugador> obtenerJugadores(String campoOrdenacion, boolean asc
            , @NonNull Map<String, Object> parametros) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Jugador> jugadores = new ArrayList<>();

        StringBuilder sql = new StringBuilder("select * from Jugador where ");

        //Construimos la clusula where
        for (String columna : parametros.keySet()) {
            sql.append(columna).append(" = ? and ");
        }
        sql.setLength(sql.length() - 5); //Eliminamos la clusula "AND"

        //Agregamos la clausula ORDER BY
        sql.append(" order by ").append(campoOrdenacion);
        if (asc) {
            sql.append(" asc");
        } else {
            sql.append(" desc");
        }
        try {
            ps = conexion.prepareStatement(sql.toString());
            int i = 1;

            for (Object valor : parametros.values()) {
                ps.setObject(i, valor);
                i++;
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                Jugador jugador = new Jugador();
                jugador.setCodJugador(rs.getInt("Cod_Jugador"));
                jugador.setNombre(rs.getString("Nombre"));
                jugador.setNombreEquipo(rs.getString("Nombre_equipo"));
                jugador.setEdad(rs.getInt("Edad"));
                jugadores.add(jugador);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jugadores;
    }

    public List<Equipo> obtenerEquipos(String campoOrdenacion, boolean asc
            , @NonNull Map<String, Object> parametros) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Equipo> equipos = new ArrayList<>();

        StringBuilder sql = new StringBuilder("select * from Equipo where ");

        //Construimos la clusula where
        for (String columna : parametros.keySet()) {
            sql.append(columna).append(" = ? and ");
        }
        sql.setLength(sql.length() - 5); //Eliminamos la clusula "AND"

        //Agregamos la clausula ORDER BY
        sql.append(" order by ").append(campoOrdenacion);
        if (asc) {
            sql.append(" asc");
        } else {
            sql.append(" desc");
        }
        try {
            ps = conexion.prepareStatement(sql.toString());
            int i = 1;

            for (Object valor : parametros.values()) {
                ps.setObject(i, valor);
                i++;
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setPuntos(rs.getInt("Puntos"));
                equipo.setNombre(rs.getString("Nombre"));
                equipo.setNumJugadores(rs.getInt("N_Jugadores"));
                equipos.add(equipo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return equipos;
    }

    public List<Partido> obtenerPartidos(String campoOrdenacion, boolean asc
            , @NonNull Map<String, Object> parametros) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Partido> partidos = new ArrayList<>();

        StringBuilder sql = new StringBuilder("select * from Partido where ");

        //Construimos la clusula where
        for (String columna : parametros.keySet()) {
            sql.append(columna).append(" = ? and ");
        }
        sql.setLength(sql.length() - 5); //Eliminamos la clusula "AND"

        //Agregamos la clausula ORDER BY
        sql.append(" order by ").append(campoOrdenacion);
        if (asc) {
            sql.append(" asc");
        } else {
            sql.append(" desc");
        }
        try {
            ps = conexion.prepareStatement(sql.toString());
            int i = 1;

            for (Object valor : parametros.values()) {
                ps.setObject(i, valor);
                i++;
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                Partido partido = new Partido();
                partido.setDuracion(rs.getInt("Duracion"));
                partido.setCodPartido(rs.getInt("Cod_Partido"));
                partidos.add(partido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return partidos;
    }

    public List<Estadio> obtenerEstadios(String campoOrdenacion, boolean asc
            , @NonNull Map<String, Object> parametros) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Estadio> estadios = new ArrayList<>();

        StringBuilder sql = new StringBuilder("select * from Estadio where ");

        //Construimos la clusula where
        for (String columna : parametros.keySet()) {
            sql.append(columna).append(" = ? and ");
        }
        sql.setLength(sql.length() - 5); //Eliminamos la clusula "AND"

        //Agregamos la clausula ORDER BY
        sql.append(" order by ").append(campoOrdenacion);
        if (asc) {
            sql.append(" asc");
        } else {
            sql.append(" desc");
        }
        try {
            ps = conexion.prepareStatement(sql.toString());
            int i = 1;

            for (Object valor : parametros.values()) {
                ps.setObject(i, valor);
                i++;
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                Estadio estadio = new Estadio();
                estadio.setAforo(rs.getInt("Aforo"));
                estadio.setNombre(rs.getString("Nombre"));
                estadio.setFechaConstruccion(LocalDate.parse(rs.getDate("N_Jugadores").toString()));
                estadios.add(estadio);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estadios;
    }

    /**
     *
     * @param jugador: Aqui recojo la instancia del jugador
     * @return: Voy a devolver un boolean, True: Si se ha actualizado almenos 1 fila,
     * False: Si no se ha actualizado nada
     */
    public boolean actualizarJugadores(Jugador jugador){
    //Preparo la consulta
        String sql = "update Jugador set Nombre=?, Nombre_equipo=?, Edad=? where Cod_Jugador=?";

        //Se crea el PrepareStatement
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, jugador.getNombre());
            ps.setString(2, jugador.getNombreEquipo());
            ps.setInt(3, jugador.getEdad());
            ps.setInt(4, jugador.getCodJugador());
            int filasActualizadas = ps.executeUpdate();

            if (filasActualizadas > 0){
                System.out.println("Se han actualizado " + filasActualizadas + " filas");
                return true;
            } else {
                System.out.println("No se ha actualizado ningún registro");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean actualizarEquipo(Equipo equipo){
        //Preparo la consulta
        String sql = "update Equipo set Puntos=?, N_Jugadores=? where Nombre=?";

        //Se crea el PrepareStatement
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, equipo.getPuntos());
            ps.setInt(2, equipo.getNumJugadores());
            ps.setString(3, equipo.getNombre());
            int filasActualizadas = ps.executeUpdate();

            if (filasActualizadas > 0){
                System.out.println("Se han actualizado " + filasActualizadas + " filas");
                return true;
            } else {
                System.out.println("No se ha actualizado ningún registro");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean actualizarPartido(Partido partido){
        //Preparo la consulta
        String sql = "update Partido set Duracion=? where Cod_Partido=?";

        //Se crea el PrepareStatement
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, partido.getDuracion());
            ps.setInt(2, partido.getCodPartido());
            int filasActualizadas = ps.executeUpdate();

            if (filasActualizadas > 0){
                System.out.println("Se han actualizado " + filasActualizadas + " filas");
                return true;
            } else {
                System.out.println("No se ha actualizado ningún registro");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean actualizarEstadios(Estadio estadio){
        //Preparo la consulta
        String sql = "update Estadio set Aforo=?, Fecha_Construccion=? where Nombre=?";

        //Se crea el PrepareStatement
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, estadio.getAforo());
            ps.setDate(2, Date.valueOf(LocalDate.parse(estadio.getFechaConstruccion().toString())));
            ps.setString(3, estadio.getNombre());
            int filasActualizadas = ps.executeUpdate();

            if (filasActualizadas > 0){
                System.out.println("Se han actualizado " + filasActualizadas + " filas");
                return true;
            } else {
                System.out.println("No se ha actualizado ningún registro");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

