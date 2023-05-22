import java.util.Scanner;

/**
 * Clase de la entidad Jugador
 *
 * @author Mario
 * @version 1.0
 */
public class Jugador implements FootballManagerInterface {
    public static final String TABLA = "Jugador";
    private int codJugador;
    private String nombre;
    private String nombreEquipo;
    private int edad;

    public Jugador(int cod_Jugador, String nombre, int edad, String nombre_equipo) {
        codJugador = cod_Jugador;
        this.nombre = nombre;
        nombreEquipo = nombre_equipo;
        this.edad = edad;
    }

    public Jugador() {

    }

    public int getCodJugador() {
        return codJugador;
    }

    public void setCodJugador(int codJugador) {
        this.codJugador = codJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Jugadores{" +
                "Cod_Jugador=" + codJugador +
                ", nombre='" + nombre + '\'' +
                ", nombreEquipo='" + nombreEquipo + '\'' +
                ", edad=" + edad +
                '}';
    }
}
