import java.util.*;

public class Tablero {
    private ArrayList<Ramo> semestre = new ArrayList<Ramo>();
    private Integer horasDisponibles;
    private ArrayList<Area> buff = new ArrayList<Area>();
    
    public void jugarEstudio(Estudio estudio, int pos) {
        if (estudio.horas > horasDisponibles) {
            System.out.println("No tienes suficientes horas disponibles para jugar esta carta de estudio (Tienes " + horasDisponibles + "hr)");
            return;
        }
        Ramo ramo = semestre.get(pos);
        ramo.anadirEstudio(estudio);
        horasDisponibles -= estudio.horas;
        System.out.print("Aplicaste la técnica de estudio " + estudio.nombre + " en el ramo " + ramo.nombre);
        if (estudio.area == ramo.area)
            System.out.println(". ¡Obtienes bonificación por misma área!\n");
        else
            System.out.println(". No es lo ideal pero ¡Ojalá sirva!\n");
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

    /**
    * establecerHorasDisponibles:
    * Establece el valor de las horas que tiene disponible el jugador para estudiar.
    *
    * @param horas Integer: Las horas que tendrá disponibles el jugador para estudiar.
    * @return void: No retorna.
    */
    public void establecerHorasDisponibles(Integer horas) {
        horasDisponibles = horas;
    }

    /**
    * anadirRamo:
    * Agrega un ramo al semestre.
    *
    * @param ramo Ramo: El Ramo que se desea agregar.
    * @return void: No retorna.
    */
    public void anadirRamo(Ramo ramo) {
        semestre.add(ramo);
    }

    /**
    * removerRamo:
    * Remueve un ramo del semestre.
    *
    * @return Ramo: El ramo removido.
    */
    public Ramo removerRamo() {
        return semestre.remove(semestre.size() - 1);
    }
    /**
    * removerRamo:
    * Remueve un ramo del semestre.
    *
    * @param indice Integer: El índice del ramo en el semestre.
    * @return Ramo: El ramo removido.
    */
    public Ramo removerRamo(Integer indice) {
        return semestre.remove((int)indice);
    }

    /**
    * hayRamos:
    * Indica si quedan ramos en el semestre o no.
    *
    * @return Boolean: true si quedan ramos, false si no.
    */
    public Boolean hayRamos() {
        return !semestre.isEmpty();
    }

    /**
    * cantidadRamos:
    * Indica la cantidad de ramos que quedan en el semestre.
    *
    * @return Integer: La cantidad de ramos que quedan en el semestre.
    */
    public Integer cantidadRamos() {
        return semestre.size();
    }

    /**
    * agregarBuffo:
    * Aumenta el puntaje máximo que pueden entregar todas
    * las cartas de estudio a los ramos de un área específica.
    *
    * @param area Area: El área en la que se desea aplicar el aumento.
    * @return void: No retorna.
    */
    public void agregarBuffo(Area area) {
        buff.add(area);
    }

    /**
    * estaBuffeado:
    * Indica si un área goza de un aumento o no.
    *
    * @param area Area: El área de la cual se requiere la información.
    * @return Boolean: true para sí, false para no.
    */
    public Boolean estaBuffeado(Area area) {
        return buff.contains(area);
    }

    /**
    * resetearBuffos:
    * Remueve el aumento a todos los ramos
    *
    * @return void: No retorna.
    */
    public void resetearBuffos() {
        buff.clear();
    }
}
