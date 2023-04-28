public class Participa {
    public static final String TABLA="Participa";
    private int codJugador;
    private int numAsistencias;
    private int numGoles;
    private String nombreEquipo;

    public Participa(int cod_Jugador, int n_Asistencias, int n_Goles, String nombre_Equipo) {
        codJugador = cod_Jugador;
        numAsistencias = n_Asistencias;
        numGoles = n_Goles;
        nombreEquipo = nombre_Equipo;
    }

    public int getCodJugador() {
        return codJugador;
    }

    public void setCodJugador(int codJugador) {
        this.codJugador = codJugador;
    }

    public int getNumAsistencias() {
        return numAsistencias;
    }

    public void setNumAsistencias(int numAsistencias) {
        this.numAsistencias = numAsistencias;
    }

    public int getNumGoles() {
        return numGoles;
    }

    public void setNumGoles(int numGoles) {
        this.numGoles = numGoles;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    @Override
    public String toString() {
        return "Participa{" +
                "codJugador=" + codJugador +
                ", numAsistencias=" + numAsistencias +
                ", numGoles=" + numGoles +
                ", nombreEquipo='" + nombreEquipo + '\'' +
                '}';
    }
}
