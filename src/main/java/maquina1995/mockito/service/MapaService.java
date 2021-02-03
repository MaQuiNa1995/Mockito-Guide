package maquina1995.mockito.service;

import maquina1995.mockito.dominio.Mapa;
import maquina1995.mockito.exception.FicheroYaExisteException;

public interface MapaService {

	/**
	 * Método usado para crear un {@link Mapa} a partir de ciertos parámetros
	 * pasados al método
	 * 
	 * @param nombre {@link String} con el nombre del mapa
	 * @param salas  numero de salas que va a tener el mapa
	 * 
	 * @return {@link Mapa} creado a partir de los parámetros pasados al método
	 */
	Mapa generarMapa(String nombre, Integer salas);

	/**
	 * Método usado para generar un fichero del {@link Mapa}
	 * 
	 * @param mapa {@link Mapa} del que generar el fichero
	 * 
	 * @throws FicheroYaExisteException excepción lanzada cuando ya existe un
	 *                                  fichero de un {@link Mapa} creado con ese
	 *                                  nombre
	 */
	void generarFicheroDelMapa(Mapa mapa) throws FicheroYaExisteException;

	/**
	 * Método usado para la simulación de la renderización de un mapa al esperar 10
	 * segundos
	 * 
	 * @param mapa {@link Mapa} a ser renderizado
	 * 
	 */
	void renderizarMapa(Mapa mapa);

}