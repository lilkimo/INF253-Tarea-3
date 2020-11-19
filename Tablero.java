import java.util.*;

public class Tablero {
    ArrayList<Ramo> semestre = new ArrayList<Ramo>();
    Integer horasDisponibles;
    ArrayList<Area> buff = new ArrayList<Area>();

    public Tablero(Integer horasDisponibles) {
        this.horasDisponibles = horasDisponibles;
    }
    
    public void jugarEstudio(Estudio estudio, Integer posicion) { // int pos
        if (estudio.obtenerHoras() > horasDisponibles) {
            System.out.println("No tienes suficientes horas disponibles para jugar esta carta de estudio (Tienes " + horasDisponibles + "hr)");
            return;
        }
        semestre.get(posicion).anadirEstudio(estudio);
        horasDisponibles -= estudio.obtenerHoras();
        System.out.println("Aplicaste la técnica de estudio " + estudio.nombre + " en el ramo " + semestre.get(posicion).nombre + " ¡Ojalá sirva!\n");
    }

    public void mostrarTablero() {
        if (semestre.size() == 0) {
            System.out.println("\u001B[1mNo hay ramos en juego\033[0m");
            return;
        }
        System.out.println("\u001b[7mRamos en juego:\033[0m");
        Ramo ramo = semestre.get(0);
        ramo.mostrarCarta();
        for (int i = 1; i < semestre.size(); i++) {
            ramo = semestre.get(i);
            System.out.println("--------------------");
            ramo.mostrarCarta();
        }            
        System.out.println("\u001b[7mFin ramos en juego\033[0m\n");
    }

    public void establecerHorasEstudio(Integer horas) {
        horasDisponibles = horas;
    }

    public void anadirRamo(Ramo ramo) {
        semestre.add(ramo);
    }

    public Ramo removerRamo() {
        return semestre.remove(semestre.size() - 1);
    }
    public Ramo removerRamo(Integer indice) {
        return semestre.remove((int)indice);
    }

    public Boolean hayRamos() {
        return !semestre.isEmpty();
    }

    public Integer cantidadRamos() {
        return semestre.size();
    }

    public void agregarBuffo(Area area) {
        buff.add(area);
    }

    public Boolean estaBuffeado(Area area) {
        return buff.contains(area);
    }

    public void resetearBuffos() {
        buff.clear();
    }
}