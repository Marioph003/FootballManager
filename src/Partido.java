import java.util.Scanner;

public class Partido implements FootballManagerInterface {
    public static final String TABLA = "Partido";
    private int codPartido;
    private int duracion;

    public Partido(int cod_Partido, int duracion) {
        codPartido = cod_Partido;
        this.duracion = duracion;
    }

    public Partido() {

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
}
