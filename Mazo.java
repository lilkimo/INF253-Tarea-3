import java.util.Collections;
import java.util.LinkedList; 

public class Mazo {
    private LinkedList<Carta> cartas = new LinkedList<Carta>();

    public void putBack(Carta carta) {
        cartas.addFirst(carta);
    }

    public Carta draw() {
        return cartas.removeLast();
    }

    public void shuffle() {
        Collections.shuffle(cartas);
    } 
}
