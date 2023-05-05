import java.util.Scanner;

public class Equipo implements FootballManagerInterface{
    private int puntos;
    private String nombre;
    private int numJugadores;

    public Equipo(int puntos, String nombre, int N_Jugadores) {
        this.puntos = puntos;
        this.nombre = nombre;
        numJugadores = N_Jugadores;
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

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre de la tabla");
        name = sc.nextLine();
    }
}
