package maquina1995.mockito.service;

import java.util.Random;

import maquina1995.mockito.dominio.Monstruo;

public interface MonstruoService {

	/**
	 * Ejemplo de método no determinístico, no se puede saber con certeza el
	 * mounstruo recuperado que en este caso depende de un numero pseudo-aleatorio
	 * generado por la clase {@link Random}
	 * 
	 * @return {@link Monstruo} aleatorio , cogido de una lista que contiene estos
	 *         objetos
	 */
	Monstruo cogerMonstruoRandom();

	/**
	 * Ejemplo de método no implementado para fines de mockeo en el test
	 * <p>
	 * El método finalizado creará un monstruo a partir de los parámetros del método
	 *
	 * @param nombre      cadena que representa el nombre del monstruo
	 * @param danno       entero que representa el daño que va a tener el monstruo
	 * @param vida        entero que representa la vida que va a tener el monstruo
	 * @param experiencia entero que representa la experiencia que da el monstruo
	 * 
	 * @return {@link Monstruo} creado a partir de los parámetros del método
	 */
	Monstruo crearMonstruoPersonalizado(String nombre, Integer danno, Integer vida, Integer experiencia);

}