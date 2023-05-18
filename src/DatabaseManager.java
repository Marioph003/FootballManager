import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DatabaseManager {
    private DatabaseConnection databaseConnection;
    private Connection conexion;
    public DatabaseManager(@NonNull DatabaseConnection connection){
        this.databaseConnection = connection;
    }

    //Metodo para buscar un jugador por 2 parametros
    public List<Jugador> buscarJugadores(String nombre, int edad) {
        Connection connection=null;
        List<Jugador> jugadores = new ArrayList<Jugador>();
        //Esta es la consulta sql que voy a usar para filtrar
        String sql = "select * from Jugador where Nombre = ? and Edad = ?";
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            //Preparamos la consulta
            PreparedStatement ps = connection.prepareStatement(sql);
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
        }finally {
            this.databaseConnection.disconnect();
        }
        return jugadores;
    }

    public List<Equipo> buscarEquipos(String nombre, int puntos) {
        Connection connection=null;
        List<Equipo> equipos = new ArrayList<Equipo>();
        //Esta es la consulta sql que voy a usar para filtrar
        String sql = "select * from Equipo where Nombre = ? and Puntos = ?";
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            //Preparamos la consulta
            PreparedStatement ps = connection.prepareStatement(sql);
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
        } finally {
            this.databaseConnection.disconnect();
        }
        return equipos;
    }

    public List<Estadio> buscarEstadios(String nombre, int aforo) {
        Connection connection=null;
        List<Estadio> estadios = new ArrayList<Estadio>();
        //Esta es la consulta sql que voy a usar para filtrar
        String sql = "select * from Estadio where Nombre = ? and Aforo = ?";
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            //Preparamos la consulta
            PreparedStatement ps = connection.prepareStatement(sql);
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
        } finally {
            this.databaseConnection.disconnect();
        }
        return estadios;
    }

    public List<Partido> buscarPartidos(int duracion, int cod_Partido) {
        Connection connection=null;
        List<Partido> partidos = new ArrayList<Partido>();
        //Esta es la consulta sql que voy a usar para filtrar
        String sql = "select * from Partido where Duracion = ? and Cod_Partido = ?";
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            //Preparamos la consulta
            PreparedStatement ps = connection.prepareStatement(sql);
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
        } finally {
            this.databaseConnection.disconnect();
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
        Connection connection=null;
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
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);
            ps = connection.prepareStatement(sql.toString());
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
        } finally {
            this.databaseConnection.disconnect();
        }
        return jugadores;
    }

    public List<Equipo> obtenerEquipos(String campoOrdenacion, boolean asc
            , @NonNull Map<String, Object> parametros) throws SQLException {
        Connection connection=null;
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
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);
            ps = connection.prepareStatement(sql.toString());
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
        } finally {
            this.databaseConnection.disconnect();
        }
        return equipos;
    }

    public List<Partido> obtenerPartidos(String campoOrdenacion, boolean asc
            , @NonNull Map<String, Object> parametros) throws SQLException {
        Connection connection=null;
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
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);
            ps = connection.prepareStatement(sql.toString());
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
        } finally {
            this.databaseConnection.disconnect();
        }
        return partidos;
    }

    public List<Estadio> obtenerEstadios(String campoOrdenacion, boolean asc
            , @NonNull Map<String, Object> parametros) throws SQLException {
        Connection connection=null;
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
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);
            ps = connection.prepareStatement(sql.toString());
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
        } finally {
            this.databaseConnection.disconnect();
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
        Connection connection=null;
    //Preparo la consulta
        String sql = "update Jugador set Nombre=?, Nombre_equipo=?, Edad=? where Cod_Jugador=?";

        //Se crea el PrepareStatement
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            PreparedStatement ps = connection.prepareStatement(sql);
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
        } finally {
            this.databaseConnection.disconnect();
        }
    }
    public boolean actualizarEquipo(Equipo equipo){
        Connection connection=null;
        //Preparo la consulta
        String sql = "update Equipo set Puntos=?, N_Jugadores=? where Nombre=?";

        //Se crea el PrepareStatement
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            PreparedStatement ps = connection.prepareStatement(sql);
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
        } finally {
            this.databaseConnection.disconnect();
        }
    }
    public boolean actualizarPartido(Partido partido){
        Connection connection=null;
        //Preparo la consulta
        String sql = "update Partido set Duracion=? where Cod_Partido=?";

        //Se crea el PrepareStatement
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            PreparedStatement ps = connection.prepareStatement(sql);
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
        } finally {
            this.databaseConnection.disconnect();
        }
    }
    public boolean actualizarEstadios(Estadio estadio){
        Connection connection=null;
        //Preparo la consulta
        String sql = "update Estadio set Aforo=?, Fecha_Construccion=? where Nombre=?";

        //Se crea el PrepareStatement
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            PreparedStatement ps = connection.prepareStatement(sql);
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
        } finally {
            this.databaseConnection.disconnect();
        }
    }

    /**
     * Elimina el jugador cuyo código coincide con el que se pasa como parámetro
     * @param codJugador: Parametro para saber que registro de la base de datos voy a eliminar
     * @return: voy a devolver un booleano, que si devuelve true quiere decir que se ha eliminado
     * registro
     */
    public boolean eliminarJugadores(int codJugador){
        Connection connection=null;
    //Preparamos las consultas
        String sql = "delete from Jugador where Cod_Jugador = ?";
        String sql2 = "delete from Participa where Cod_Jugador = ?";

        //Creamos el PrepareStatement
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            PreparedStatement psParticipa = connection.prepareStatement(sql2);
            //Asigno los valores de los parámetros
            psParticipa.setInt(1, codJugador);
            psParticipa.executeUpdate();

            PreparedStatement psJugador = connection.prepareStatement(sql);
            //Asigno los valores de los parámetros
            psJugador.setInt(1, codJugador);
            int filasElminadas = psJugador.executeUpdate();

            if (filasElminadas > 0){
                System.out.println("Se han eliminado " + filasElminadas + " filas");
                return true;
            } else {
                System.out.println("No se ha eliminado ningún registro");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.databaseConnection.disconnect();
        }
    }
    public boolean eliminarEquipos(String nombreEquipo){
        Connection connection=null;
        //Preparamos las consultas
        String sql = "delete from Equipo where Nombre = ?";
        String sql2 = "delete from Juega where Nombre_Equipo = ?";

        //Creamos el PrepareStatement
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            PreparedStatement psJuega = connection.prepareStatement(sql2);
            //Asigno los valores de los parámetros
            psJuega.setString(1, nombreEquipo);
            psJuega.executeUpdate();

            PreparedStatement psEquipo = connection.prepareStatement(sql);
            //Asigno los valores de los parámetros
            psEquipo.setString(1, nombreEquipo);
            int filasElminadas = psEquipo.executeUpdate();

            if (filasElminadas > 0){
                System.out.println("Se han eliminado " + filasElminadas + " filas");
                return true;
            } else {
                System.out.println("No se ha eliminado ningún registro");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.databaseConnection.disconnect();
        }
    }
    public boolean eliminarPartido(int codPartido){
        Connection connection=null;
        //Preparamos las consultas
        String sql = "delete from Partido where Cod_Partido = ?";

        //Creamos el PrepareStatement
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            PreparedStatement psPartido = connection.prepareStatement(sql);
            //Asigno los valores de los parámetros
            psPartido.setInt(1, codPartido);
            int filasElminadas = psPartido.executeUpdate();

            if (filasElminadas > 0){
                System.out.println("Se han eliminado " + filasElminadas + " filas");
                return true;
            } else {
                System.out.println("No se ha eliminado ningún registro");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.databaseConnection.disconnect();
        }
    }
    public boolean eliminarEstadios(String nombreEstadio){
        Connection connection=null;
        //Preparamos las consultas
        String sql = "delete from Estadio where Nombre = ?";
        String sql2 = "delete from Juega where Nombre_Estadio = ?";

        //Creamos el PrepareStatement
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            PreparedStatement psJuega = connection.prepareStatement(sql2);
            //Asigno los valores de los parámetros
            psJuega.setString(1, nombreEstadio);
            psJuega.executeUpdate();

            PreparedStatement psEstadio = connection.prepareStatement(sql);
            //Asigno los valores de los parámetros
            psEstadio.setString(1, nombreEstadio);
            int filasElminadas = psEstadio.executeUpdate();

            if (filasElminadas > 0){
                System.out.println("Se han eliminado " + filasElminadas + " filas");
                return true;
            } else {
                System.out.println("No se ha eliminado ningún registro");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.databaseConnection.disconnect();
        }
    }
    public boolean aniadirEquipos(List<Equipo> equipos){
        Connection connection=null;
        String sql = "INSERT INTO Equipo(Puntos, Nombre, N_Jugadores) VALUES (?, ?, ?)";
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            PreparedStatement ps = connection.prepareStatement(sql);
            for (Equipo equipo: equipos) {
                ps.setInt(1, equipo.getPuntos());
                ps.setString(2, equipo.getNombre());
                ps.setInt(3, equipo.getNumJugadores());
                ps.addBatch();
            }
            //Añado las filas/registros insertados en la base de datos y después
            //con la clase Arrays sumo todos los registros
            int[] filasInsertadas = ps.executeBatch();
            int totalFilasInsertadas = Arrays.stream(filasInsertadas).sum();

            ps.clearParameters();

            if (totalFilasInsertadas > 0){
                System.out.println("Se han insertado " + totalFilasInsertadas +
                        " filas en la base de datos");
                return true;
            } else {
                System.out.println("No se ha insertado ningún registro en la base de datos");
                return  false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.databaseConnection.disconnect();
        }
    }

    /**
     * Es un método que se utiliza para añadir registros a mi base de datos
     *
     * @param jugadores: Es una lista en la que se guardan los jugadores que se van a añadir
     *                 a mi base de datos
     * @return: Devuelve un true: si se han insertado y actualizado los datos en la tabla,
     * y un false si no se ha realizado ninguna operacion en la base de datos
     */
    public boolean aniadirJugadores(List<Jugador> jugadores){
        Connection connection=null;
        String sqlJugador = "INSERT INTO Jugador(Cod_Jugador, Nombre, Nombre_equipo, Edad) VALUES (?, ?, ?, ?)";
        String sqlEquipo = "UPDATE Jugador SET Nombre_equipo = ? WHERE Cod_Jugador = ?";
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            PreparedStatement ps = connection.prepareStatement(sqlJugador);
            PreparedStatement psEquipo = connection.prepareStatement(sqlEquipo);
            for (Jugador jugador: jugadores) {
                ps.setInt(1, jugador.getCodJugador());
                ps.setString(2, jugador.getNombre());
                ps.setString(3, jugador.getNombreEquipo());
                ps.setInt(4, jugador.getEdad());
                ps.addBatch();

                psEquipo.setString(1, jugador.getNombreEquipo());
                psEquipo.setInt(2, jugador.getCodJugador());
                psEquipo.addBatch();
            }
            //Añado las filas/registros insertados en la base de datos y después
            //con la clase Arrays sumo todos los registros
            int[] filasInsertadas = ps.executeBatch();
            int[] filasInsertadasEquipo = psEquipo.executeBatch();

            int totalFilasInsertadasEquipo = Arrays.stream(filasInsertadasEquipo).sum();
            int totalFilasInsertadasJugador = Arrays.stream(filasInsertadas).sum();

            ps.clearParameters();
            psEquipo.clearParameters();

            if (totalFilasInsertadasJugador > 0 && totalFilasInsertadasEquipo > 0){
                System.out.println("Se han insertado " + totalFilasInsertadasJugador +
                        " filas en la tabla Jugador y actualizado " + totalFilasInsertadasEquipo +
                        " filas en la tabla Equipo");
                return true;
            } else {
                System.out.println("No se ha insertado ningún registro en la base de datos");
                return  false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.databaseConnection.disconnect();
        }
    }

    public boolean aniadirPartidos(List<Partido> partidos){
        Connection connection=null;
        String sqlPartido = "INSERT INTO Partido(Duracion, Cod_Partido) VALUES (?, ?)";
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            PreparedStatement ps = connection.prepareStatement(sqlPartido);
            for (Partido partido: partidos) {
                ps.setInt(1, partido.getDuracion());
                ps.setInt(2, partido.getCodPartido());
                ps.addBatch();

            }
            //Añado las filas/registros insertados en la base de datos y después
            //con la clase Arrays sumo todos los registros
            int[] filasInsertadas = ps.executeBatch();

            int totalFilasInsertadasPartido = Arrays.stream(filasInsertadas).sum();

            ps.clearParameters();

            if (totalFilasInsertadasPartido > 0){
                System.out.println("Se han insertado " + totalFilasInsertadasPartido +
                        " filas en la tabla Partido");
                return true;
            } else {
                System.out.println("No se ha insertado ningún registro en la base de datos");
                return  false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.databaseConnection.disconnect();
        }
    }

    public boolean aniadirEstadios(List<Estadio> estadios){
        Connection connection=null;
        String sqlPartido = "INSERT INTO Estadio(Aforo, Nombre, Fecha_Construccion) VALUES (?, ?, ?)";
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            PreparedStatement ps = connection.prepareStatement(sqlPartido);
            for (Estadio estadio: estadios) {
                ps.setInt(1, estadio.getAforo());
                ps.setString(2, estadio.getNombre());
                ps.setDate(3, Date.valueOf(LocalDate.parse(estadio.getFechaConstruccion().toString())));
                ps.addBatch();

            }
            //Añado las filas/registros insertados en la base de datos y después
            //con la clase Arrays sumo todos los registros
            int[] filasInsertadas = ps.executeBatch();

            int totalFilasInsertadasEstadio = Arrays.stream(filasInsertadas).sum();

            ps.clearParameters();

            if (totalFilasInsertadasEstadio > 0){
                System.out.println("Se han insertado " + totalFilasInsertadasEstadio +
                        " filas en la tabla Estadio");
                return true;
            } else {
                System.out.println("No se ha insertado ningún registro en la base de datos");
                return  false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.databaseConnection.disconnect();
        }
    }

    /**
     *
     * @return: Devuelve los registros que se han quedado huerfanos
     */
    public List<Jugador> comprobarIntegridadJug(){
        Connection connection=null;
List<Jugador> registrosHuerfanos = new ArrayList<>();
String sql = "select * from Jugador where Nombre_Equipo not in (select Nombre from Equipo)";

    try {
        //se abre la conexión usando la cadena de conexión guardada en el gestor
        //de conexión
        connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
        this.databaseConnection.setConexion(connection);

        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
    Jugador jugador = new Jugador();
    jugador.setCodJugador(rs.getInt("Cod_Jugador"));
    jugador.setNombre(rs.getString("Nombre"));
    jugador.setNombreEquipo(rs.getString("Nombre_equipo"));
    jugador.setEdad(rs.getInt("Edad"));
    registrosHuerfanos.add(jugador);

        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        this.databaseConnection.disconnect();
    }
    return registrosHuerfanos;
}
    public List<Equipo> comprobarIntegridadEq(){
        Connection connection=null;
        List<Equipo> registrosHuerfanos = new ArrayList<>();
        String sql = "select * from Equipo where Nombre not in (select Nombre_Equipo from Participa)";

        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Equipo equipo = new Equipo();
                equipo.setPuntos(rs.getInt("Puntos"));
                equipo.setNombre(rs.getString("Nombre"));
                equipo.setNumJugadores(rs.getInt("N_Jugadores"));
                registrosHuerfanos.add(equipo);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.databaseConnection.disconnect();
        }
        return registrosHuerfanos;
    }
    public List<Partido> comprobarIntegridadPar(){
        Connection connection=null;
        List<Partido> registrosHuerfanos = new ArrayList<>();
        String sql = "select * from Partido where Cod_Partido not in (select Cod_Partido from Juega)";

        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Partido partido = new Partido();
                partido.setDuracion(rs.getInt("Duracion"));
                partido.setCodPartido(rs.getInt("Cod_Partido"));
                registrosHuerfanos.add(partido);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.databaseConnection.disconnect();
        }
        return registrosHuerfanos;
    }
    public List<Estadio> comprobarIntegridadEst(){
        Connection connection=null;
        List<Estadio> registrosHuerfanos = new ArrayList<>();
        String sql = "select * from Estadio where Nombre not in (select Nombre_Estadio from Juega)";

        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Estadio estadio = new Estadio();
                estadio.setAforo(rs.getInt("Aforo"));
                estadio.setNombre(rs.getString("Nombre"));
                estadio.setFechaConstruccion(Date.valueOf(LocalDate.parse(rs.getDate("Fecha_Construccion").toLocalDate().toString())).toLocalDate());
                registrosHuerfanos.add(estadio);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.databaseConnection.disconnect();
        }
        return registrosHuerfanos;
    }
}