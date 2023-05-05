import java.time.LocalDate;
import java.util.Scanner;

public class Estadio implements FootballManagerInterface{
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
