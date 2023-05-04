import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MySqlTest {
    public static void main(String[] args) {
    DatabaseManager dm = new DatabaseManager();
    DatabaseManager2 dm2 = new DatabaseManager2();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("Nombre", "Real Madrid");
        parametros.put("Puntos", 32);


        System.out.println(dm.buscarJugadores("Benzema", 35));
        System.out.println(dm2.buscarJugadores2("Equipo", parametros));
    }
}