import java.util.ArrayList;

public class Ramo extends Carta {
    Integer creditos;
    ArrayList<Estudio> estudios = new ArrayList<Estudio>();
    Area area;
    
    /**
    * Ramo:
    * Constructor de la clase Ramo.
    *
    * @param nombre String: El nombre de la Carta.
    * @param lore String: El lore de la Carta.
    * @param area Area: El área del Ramo.
    * @return void: No retorna.
    */
    public Ramo(String nombre, String lore, Area area) throws Exception {
        this.nombre = nombre;
        this.lore = lore;
        this.area = area;
        switch (area) {
            case humanista:
                creditos = 2;
                break;
            case informatica:
                creditos = 5;
                break;
            case matematica:
                creditos = 7;
                break;
            default:
                throw new Exception("[ERROR] El área ingresada no es válida");
        }
    }

    public void mostrarCarta() {
        System.out.println(
            "Tipo: Ramo" +
            "\nNombre: " + nombre +
            "\nLore: " + lore +
            "\nCréditos: " + creditos +
            "\nÁrea: " + area
        );
        if (estudios.size() == 0) {
            System.out.println("Sin estudios jugados");
            return;
        }
        System.out.println("\u001b[1m\u001b[4mEstudios jugados:\033[0m");
        Estudio estudio = estudios.get(0);
        estudio.mostrarCarta();
        for (int i = 1; i < estudios.size(); i++) {
            estudio = estudios.get(i);
            System.out.println("--------------------");
            estudio.mostrarCarta();
        }
        System.out.println("\u001b[1m\u001b[4mFin estudios jugados\033[0m\n");
    }
    
    public int calcularNota(Boolean buff) {
        Float notaFinal = 0f, bonus;
        for (Estudio estudio: estudios) {
            bonus = estudio.calcularBonus() + 0f;
            if (estudio.area == area)
                bonus *= 1.25f;
            if (buff)
                bonus *= 1.25f;
            notaFinal += bonus;
        }
        if (notaFinal < 0)
            return 0;
        else if (notaFinal > 100)
            return 100;
        else
            return Math.round(notaFinal - 2*creditos);
    }
    
    public void anadirEstudio(Estudio estudio) {
        estudios.add(estudio);
    }
}
