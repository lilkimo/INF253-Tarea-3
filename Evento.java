import java.util.Random;

public class Evento extends Carta {
    Efecto efecto;
    Area area; // Adicional
    static Random rand = new Random();

    public Evento(String nombre, String lore, Efecto efecto) throws Exception {
        this.nombre = nombre;
        this.lore = lore;
        if (efecto != Efecto.buff)
            this.efecto = efecto;
        else
            throw new Exception("[ERROR] El argumento <area> se debe declarar para el efecto Buff");
    }
    public Evento(String nombre, String lore, Efecto efecto, Area area) {
        this.nombre = nombre;
        this.lore = lore;
        this.efecto = efecto;
        this.area = area;
    }
    
    void mostrarCarta() {
        
        System.out.print(
            "Tipo: Evento" +
            "\nNombre: " + nombre +
            "\nLore: " + lore +
            "\nEfecto: " + efecto
        );
        if (efecto == Efecto.buff)
            System.out.print(" (" + area + ")");
        System.out.println();
    }
    void aplicarEvento(Tablero tablero, Mazo mazoCarrera) throws Exception {
        Ramo ramo;
        switch (efecto) {
            case rav:
                if (tablero.cantidadRamos() > 1) {
                    System.out.println("Has tomado la desición de usar uno de tus RAVs, ¿Qué ramo deseas desinscribir?");
                    tablero.mostrarTablero();
                    Integer opcion = Juego.getInput("Ingrese el número (1.." + tablero.cantidadRamos() + ") del ramo que desea desinscribir: ", tablero.cantidadRamos());
                    ramo = tablero.removerRamo(opcion - 1);
                }
                else if (tablero.cantidadRamos() == 1) {
                    ramo = tablero.removerRamo();
                }
                // Equivalente a if (tablero.cantidadRamos() == 0)
                else {
                    System.out.println("¡No hay ramos en juego!");    
                    return;
                }
                Juego.mazoCarrera.putBack(ramo);
                System.out.println(ramo.nombre + " ha sido desinscrito... ¿Exitosamente?");
                break;
            case cambioDeCoordinacion:
                if (tablero.cantidadRamos() > 1) {
                    System.out.println("¡Ha cambiado la coordinación de...! ¿Cuál ramo era?");
                    tablero.mostrarTablero();
                    Integer opcion = Juego.getInput("Ingrese el número (1.." + tablero.cantidadRamos() + ") del ramo que ha cambiado su coordinación: ", tablero.cantidadRamos());
                    ramo = tablero.removerRamo(opcion - 1);
                }
                else if (tablero.cantidadRamos() == 1) {
                    ramo = tablero.removerRamo();
                }
                // Equivalente a if (tablero.cantidadRamos() == 0)
                else {
                    System.out.println("¡No hay ramos en juego!");    
                    return;
                }
                boolean buffo = rand.nextInt(2) == 1;
                if (buffo) {
                    ramo.creditos += 3;
                    System.out.println("¡Ahora " + ramo.nombre + " tiene " + ramo.creditos + " créditos (Se le agregaron 3 créditos)!... Espera, ¿Esto es bueno?");
                }
                else {
                    ramo.creditos -= 3;
                    System.out.println("¡Oh no! Ahora " + ramo.nombre + " tiene " + ramo.creditos + " créditos (Se le restaron 3 créditos)... Espera, creo que esto es bueno");
                }
                break;
            case buff:
                tablero.agregarBuffo(area);
                System.out.println("¡Has buffeado " + area + ", ahora la suerte está de tu lado!");
                break;
            default:
                throw new Exception("[ERROR] El efecto " + efecto + " no es válido");
            }
        System.out.println();
    }
}