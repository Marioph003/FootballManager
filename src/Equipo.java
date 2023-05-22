import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase de la entidad Equipo
 *
 * @author Mario
 * @version 1.0
 */
public class Equipo implements FootballManagerInterface {
    public static final String TABLA = "Equipo";
    private int puntos;
    private String nombre;
    private int numJugadores;

    public Equipo(int puntos, String nombre, int N_Jugadores) {
        this.puntos = puntos;
        this.nombre = nombre;
        numJugadores = N_Jugadores;
    }

    public Equipo() {

    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "puntos=" + puntos +
                ", nombre='" + nombre + '\'' +
                ", numJugadores='" + numJugadores + '\'' +
                '}';
    }
}
