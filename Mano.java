import java.util.ArrayList; 

public class Mano {
    private ArrayList<Carta> cartas = new ArrayList<Carta>();
    private Integer limite;

    /**
    * Mano:
    * Constructor de la clase Mano.
    *
    * @param limite Integer: Capacidad de la mano.
    * @return void: No retorna.
    */
    public Mano(Integer limite) {
        this.limite = limite;
    }

    public void mostrarMano() {
        if (cartas.size() == 0) {
            System.out.println("\u001B[1mNo hay cartas en la mano\033[0m");
            return;
        }
        System.out.println("\u001b[7mCartas en la mano:\033[0m");
        Carta carta = cartas.get(0);
        carta.mostrarCarta();
        for (int i = 1; i < cartas.size(); i++) {
            carta = cartas.get(i);
            System.out.println("--------------------");
            carta.mostrarCarta();
        }
        System.out.println("\u001b[7mFin cartas en la mano\033[0m\n");
    }

    public void anadirCarta(Carta carta) {
        if (cartas.size() < limite)
            cartas.add(carta);
        else
            System.out.println("[WARNING] Se ha intentando añadir una carta a la mano llena.");
    }

    public Carta seleccionarCarta(int pos) {
        Carta carta = cartas.remove(pos);
        System.out.println("\u001B[1mSeleccionaste:\033[0m");
        carta.mostrarCarta();
        System.out.println();
        return carta;
    }

    /**
    * espacioDisponible:
    * Indica si hay espacio disponible en la mano.
    *
    * @return Boolean: true para sí, flase para no.
    */
    public Boolean espacioDisponible() {
        return (limite - cartas.size() > 0);
    }
    
    /**
    * cantidadCartas:
    * Indica la cantidad de cartas que hay en la mano.
    *
    * @return Integer: Cantidad de cartas que hay en la mano.
    */
    public Integer cantidadCartas() {
        return cartas.size();
    }
}
