import java.util.Random;
import java.util.Scanner;

public final class Juego {
    static Mazo mazoCarrera = new Mazo();
    static Mazo mazoUniversitario = new Mazo();
    static Tablero tablero = new Tablero();
    static Integer aprobados = 0;
    static Integer reprobados = 0;
    static Mano mano = new Mano(6);
    static Scanner input = new Scanner(System.in);

    /**
    * getInput:
    * Pide un número al usuario.
    *
    * @param mensaje String: El mensaje mostrado antes de hacer la solicitud.
    * @param limite Integer: El número más alto válido.
    * @return Integer: El número que ingresó el usuario.
    */
    public static Integer getInput(String mensaje, Integer limite) {
        Integer opcion;
        while (true) {
            System.out.print(mensaje);
            if (!input.hasNextInt()) {
                System.out.println("Input no válido");
                input.next();
                continue;
            }

            opcion = input.nextInt();
            if (opcion < 1 || opcion > limite)
                System.out.println(opcion + " No es una opción válida");
            else
                break;
        }
        return opcion;
    }
    /**
    * getInput:
    * Pide un número al usuario.
    *
    * @param mensaje String: El mensaje mostrado antes de hacer la solicitud.
    * @param limiteInferior Integer: El número más bajo válido.
    * @param limiteSuperior Integer: EL numéro más alta válido.
    * @return Integer: El número que ingresó el usuario.
    */
    public static Integer getInput(String mensaje, Integer limiteInferior, Integer limiteSuperior) {
        Integer opcion;
        while (true) {
            System.out.print(mensaje);
            if (!input.hasNextInt()) {
                System.out.println("Input no válido");
                input.next();
                continue;
            }
            
            opcion = input.nextInt();
            if (opcion < limiteInferior || opcion > limiteSuperior)
                System.out.println(opcion + " No es una opción válida");
            else
                break;
        }
        return opcion;
    }

    public static void main(String[] args) throws Exception {
        // init mazos BEGINS
        for (int i = 0; i < 3; i++)
            mazoUniversitario.putBack(new Estudio("Buscar un resumen en internet", "Seguramente sirva de algo...", TipoEstudio.comun, Area.humanista));
        for (int i = 0; i < 2; i++)
            mazoUniversitario.putBack(new Estudio("Leer los textos", "¿Realmente alguien lee estas cosas?", TipoEstudio.raro, Area.humanista));
        mazoUniversitario.putBack(new Estudio("Viaje Astral", "Tan sólo preocupate de volver a tu forma física", TipoEstudio.epico, Area.humanista));

        for (int i = 0; i < 4; i++)
            mazoUniversitario.putBack(new Estudio("Ver las ppt", "¿Por qué están en Comic Sans?", TipoEstudio.comun, Area.informatica));
        for (int i = 0; i < 3; i++)
            mazoUniversitario.putBack(new Estudio("Stackoverflow", "Por favor respondanme llevo 3 días esperando", TipoEstudio.raro, Area.informatica));
        mazoUniversitario.putBack(new Estudio("Programar", "Hmm... No pensé que fuera tan sencillo", TipoEstudio.epico, Area.informatica));

        for (int i = 0; i < 3; i++)
            mazoUniversitario.putBack(new Estudio("Sears Semansky", "El viejo confiable... Espera, ¿Esto no era de Física?", TipoEstudio.comun, Area.matematica));
        for (int i = 0; i < 2; i++)
            mazoUniversitario.putBack(new Estudio("Ejercitar", "Suena fácil, no es fácil", TipoEstudio.raro, Area.matematica));
        mazoUniversitario.putBack(new Estudio("Certamenes anteriores", "Ojalá salgan preguntas parecidas", TipoEstudio.epico, Area.matematica));

        mazoUniversitario.putBack(new Evento("RAV", "Se suponía que tenía que guardar este para 120...", Efecto.rav));
        for (Area area: Area.values())
            mazoUniversitario.putBack(new Evento("Iluminación divina", "Y decían que los mails no iban a servir para nada", Efecto.buff, area));
        mazoUniversitario.putBack(new Evento("Cambio de Coordinación", "¿Diablo conocido o diablo por conocer?", Efecto.cambioDeCoordinacion));

        mazoUniversitario.shuffle();

        Random rand = new Random();
        Area area;
        for (int i = 0; i < 10; i++) {
            area = Area.values()[rand.nextInt(Area.values().length)];
            mazoCarrera.putBack(new Ramo(area + " " + (i + 1), "", area));
        }
        // END init mazos
        
        // ciclo de juego BEGINS
        Integer cartasRobadas, cantidadRamos, nota, opcion;
        Carta ramo, carta;
        Ramo ramoFinalizado;
        Boolean buffeado;
        while (aprobados < 4 && reprobados < 2) {
            // establecer tablero BEGINS
            tablero.establecerHorasDisponibles(12);
            tablero.resetearBuffos();

            for (int i = 0; i < 2; i++) {
                ramo = mazoCarrera.draw();
                if (ramo instanceof Ramo)
                    tablero.anadirRamo((Ramo)ramo);
                else
                    throw new Exception("[ERROR] Inesperado: No todos los elementos del Mazo Carrera son Ramos");
            }
            tablero.mostrarTablero();
            // END establecer tablero

            // turno del jugador BEGINS
            cartasRobadas = 0;
            while (mano.espacioDisponible()) {
                mano.anadirCarta(mazoUniversitario.draw());
                cartasRobadas++;
            }
            System.out.println("¡Has robado " + cartasRobadas + " cartas!\n");
            
            while (true) {
                mano.mostrarMano();
                
                opcion = getInput("Ingrese el número de la carta que quieras jugar (1.." + mano.cantidadCartas() + "), ingresa 0 para finalizar el turno: ", 0, mano.cantidadCartas());
                if (opcion == 0)
                    break;
                
                carta = mano.seleccionarCarta(opcion - 1);
                if (carta instanceof Evento)
                    ((Evento)carta).aplicarEvento(tablero, mazoCarrera);
                else if (carta instanceof Estudio) {
                    if (!tablero.hayRamos())
                        throw new Exception("[ERROR] Inesperado: No hay ramos sobre el tablero");
                    tablero.mostrarTablero();
                    opcion = getInput("Ingrese el número del ramo que quiere estudiar (1.." + tablero.cantidadRamos() + "): ", tablero.cantidadRamos());
                    tablero.jugarEstudio((Estudio)carta, opcion - 1);
                }
                else
                    throw new Exception("[ERROR] Inesperado: No todos los elementos de la Mano son Estudios o Eventos");
                
                if (mano.cantidadCartas() == 0) {
                    System.out.println("No hay más cartas en la mano");
                    break;
                }
            }
            // END turno del jugador

            // veredicto de los ramos BEGINS
            cantidadRamos = 0;
            while (tablero.hayRamos()) {
                cantidadRamos += 1;
                ramoFinalizado = tablero.removerRamo();
                buffeado = tablero.estaBuffeado(ramoFinalizado.area);
                nota = ramoFinalizado.calcularNota(buffeado);
                if (nota < 55) {
                    System.out.print("Reprobaste " + ramoFinalizado.nombre);
                    if (buffeado)
                        System.out.print(" (Buffeado)");
                    System.out.println(" con un " + nota + " ¡Que mala suerte!");
                    reprobados++;
                }
                else {
                    System.out.print("¡Aprobaste " + ramoFinalizado.nombre);
                    if (buffeado)
                        System.out.print(" (Buffeado)");
                    System.out.println(" con un " + nota + ", uno menos!");
                    aprobados++;
                }
            }
            System.out.println("Llevas " + aprobados + " ramos aprobados y " + reprobados + " reprobados, ¡Ánimo!");
            if (cantidadRamos > 2)
                throw new Exception("[ERROR] Inesperado: Hay más de 2 ramos sobre el tablero");
            else if (cantidadRamos == 0)
                throw new Exception("[ERROR] Inesperado: No hay ramos sobre el tablero"); // Sólo hay 1 RAV, por ende esta situación debería ser imposible.
            // END veredicto de los ramos
        }
        input.close();
        // END ciclo de juego
        
        // veredicto del semestre BEGINS
        if (aprobados == 4)
            System.out.print("¡Felicidades, sobreviviste al semestre!");
        else
            System.out.print("Bueno, lo importante es que tenemos salúd, espero...");
        System.out.println(" (" + aprobados + " aprobados, " + reprobados + " reprobados)");
        // END veredicto del semestre
    }
}
