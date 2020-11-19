import java.util.Collections;
import java.util.LinkedList; 

public class Mazo {
    LinkedList<Carta> cartas = new LinkedList<Carta>();

    void putBack(Carta carta) {
        cartas.addFirst(carta);
    }

    Carta draw() {
        return cartas.removeLast();
    }

    void shuffle() {
        Collections.shuffle(cartas);
    } 
}