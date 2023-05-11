import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MySqlTest {
    public static void main(String[] args) throws SQLException {
        Jugador jugador = new Jugador(1, "Asd", 22, "FC Barcelona");
        Equipo equipo = new Equipo(50, "Real Betis Balonpie", 31);
        Partido partido = new Partido(3, 90);
        Estadio estadio = new Estadio(81044, "Santiago Bernabeu", LocalDate.of(1947,12,14));
    DatabaseManager dm = new DatabaseManager();
    DatabaseManager2 dm2 = new DatabaseManager2();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("Nombre_equipo", "FC Barcelona");


        //System.out.println(dm.buscarJugadores("Benzema", 35));
        //System.out.println(dm2.buscar("Equipo", parametros));
        System.out.println(dm.actualizarPartido(partido));
    }
}