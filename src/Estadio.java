import java.time.LocalDate;

public class Estadio {
    public static final String TABLA="Estadio";
    private int aforo;
    private String nombre;
    private LocalDate fechaConstruccion;

    public Estadio(int aforo, String nombre, LocalDate fecha_Construccion) {
        this.aforo = aforo;
        this.nombre = nombre;
        fechaConstruccion = fecha_Construccion;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaConstruccion() {
        return fechaConstruccion;
    }

    public void setFechaConstruccion(LocalDate fechaConstruccion) {
        this.fechaConstruccion = fechaConstruccion;
    }

    @Override
    public String toString() {
        return "Estadio{" +
                "aforo=" + aforo +
                ", nombre='" + nombre + '\'' +
                ", fechaConstruccion=" + fechaConstruccion +
                '}';
    }
}
