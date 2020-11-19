import java.util.Random;

public class Estudio extends Carta {
    Integer horas;
    Integer bonusMin;
    Integer bonusMax;
    Area area;
    static Random rand = new Random();

    public Estudio(String nombre, String lore, TipoEstudio tipoEstudio, Area area) throws Exception {
        this.nombre = nombre;
        this.lore = lore;
        this.area = area;
        
        switch (tipoEstudio) {
            case comun:
                this.horas = 2;
                this.bonusMin = 18;
                this.bonusMax = 26;
                break;
            case raro:
                this.horas = 3;
                this.bonusMin = 28;
                this.bonusMax = 40;
                break;
            case epico:
                this.horas = 4;
                this.bonusMin = 20;
                this.bonusMax = 90;
                break;
            default:
                throw new Exception("[ERROR] El tipo de estudio ingresado no es válido");
        }
    }

    void mostrarCarta() {
        System.out.println(
            "Tipo: Estudio" +
            "\nNombre: " + nombre +
            "\nLore: " + lore +
            "\nHoras Necesarias: " + horas +
            "\nBonus Mínimo: " + bonusMin +
            "\nBonus Máximo: " + bonusMax +
            "\nArea: " + area
        );
    }
    
    Integer calcularBonus() { // Cambiar a int
        return rand.nextInt(bonusMax - bonusMin + 1) + bonusMin;
    }

    Integer obtenerHoras() {
        return horas;
    }
}