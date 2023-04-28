public class Equipo {
    private static final String TABLA="Equipo";
    private int puntos;
    private String nombre;
    private String numJugadores;

    public Equipo(int puntos, String nombre, String nº_Jugadores) {
        this.puntos = puntos;
        this.nombre = nombre;
        numJugadores = nº_Jugadores;
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

    public String getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(String numJugadores) {
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
