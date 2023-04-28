public class Juega {
    public static final String TABLA="Juega";
    private String nombreEquipo;
    private int codPartido;
    private String nombreEstadio;

    public Juega(String nombre_Equipo, int cod_Partido, String nombre_Estadio) {
        nombreEquipo = nombre_Equipo;
        codPartido = cod_Partido;
        nombreEstadio = nombre_Estadio;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public int getCodPartido() {
        return codPartido;
    }

    public void setCodPartido(int codPartido) {
        this.codPartido = codPartido;
    }

    public String getNombreEstadio() {
        return nombreEstadio;
    }

    public void setNombreEstadio(String nombreEstadio) {
        this.nombreEstadio = nombreEstadio;
    }

    @Override
    public String toString() {
        return "Juega{" +
                "nombreEquipo='" + nombreEquipo + '\'' +
                ", codPartido=" + codPartido +
                ", nombreEstadio='" + nombreEstadio + '\'' +
                '}';
    }
}
