import java.util.Scanner;

public class Jugador implements FootballManagerInterface{
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
