import java.util.Scanner;

public class Partido implements FootballManagerInterface{
    private int codPartido;
    private int duracion;

    public Partido(int cod_Partido, int duracion) {
        codPartido = cod_Partido;
        this.duracion = duracion;
    }

    public int getCodPartido() {
        return codPartido;
    }

    public void setCodPartido(int codPartido) {
        this.codPartido = codPartido;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "codPartido=" + codPartido +
                ", duracion=" + duracion +
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
