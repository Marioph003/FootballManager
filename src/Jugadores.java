public class Jugadores {
    public static final String TABLA="Jugadores";
    private int codJugador;
    private String nombre;
    private String nombreEquipo;
    private int edad;

    public Jugadores(int cod_Jugador, String nombre, String nombre_equipo, int edad) {
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
}
