import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlTest {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection dc = null;
        //DdlTool.test();

        Jugador jugador = new Jugador(1, "Asd", 22, "FC Barcelona");
        Equipo equipo = new Equipo(50, "Real Betis Balonpie", 31);
        Partido partido = new Partido(3, 90);
        Estadio estadio = new Estadio(81044, "Santiago Bernabeu", LocalDate.of(1947,12,14));
      dc = new DatabaseConnection("jdbc:mysql://localhost:3306/Futbol?user=Mario&password=03062003mph");
        DatabaseManager dm = new DatabaseManager(dc);
    DatabaseManager2 dm2 = new DatabaseManager2();
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador(1, "Asd", 22, "FC Barcelona"));
        List<Equipo> equipos = new ArrayList<>();
        equipos.add(new Equipo(21, "Valencia CF", 32));
        List<Partido> partidos = new ArrayList<>();
        partidos.add(new Partido(7, 92));
        List<Estadio> estadios = new ArrayList<>();
        estadios.add(new Estadio(55345, "Alcoraz", LocalDate.of(1976,12,14)));

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("Nombre_equipo", "FC Barcelona");


        //System.out.println(dm.buscarJugadores("Benzema", 35));
        //System.out.println(dm2.buscar("Equipo", parametros));
        System.out.println(dm.comprobarIntegridadEq());
        //System.out.println(dm.aniadirJugadores(jugadores));
    }
}