# Integrantes
Zarko Kuljis, 201823535-7

Obtuve un 82
## Observación del corrector
Cuando usé RAV se descinscribió el primer ramo sin preguntarme si quería ese :(, luego intenté jugar una carta y me decía que no habían ramos en el tablero:
```
Ingrese el número de la carta que quieras jugar (1..4), ingresa 0 para finalizar el turno: 1
Seleccionaste:
Tipo: Estudio
Nombre: Ver las ppt
Lore: ¿Por qué están en Comic Sans?
Horas Necesarias: 2
Bonus Mínimo: 18
Bonus Máximo: 26
Area: informatica

Exception in thread "main" java.lang.Exception: [ERROR] Inesperado: No hay ramos sobre el tablero
        at Juego.main(Juego.java:142)
make: *** [Makefile:3: all] Error 1
```
Cuando no tengo suficientes horas, la carta de estudio que quiero jugar desaparece
# Instrucciones de uso
Ejecute el comando make en el directorio de la tarea.
# Consideraciones
* Si ocurre un empate (4 aprobados y 2 reprobados) se ganará el juego.
* El efecto buff multiplica el puntaje obtenido por un estudio por 1.25, por ende, si ya se tenía un bonus por coincidencia de áreas entre estudio y ramo, se multiplicará el puntaje por 1.5625 (1.25*1.25).
* Mano::seleccionarCarta() removerá la carta de la mano.
* Mazo::draw() remueve la carta en el tope del mazo.
 