package maquina1995.mockito.service;

import maquina1995.mockito.dominio.Heroe;

public interface MazmorraService {

	/**
	 * Método usado para cuando un héroe entra a una mazmorra para hacer el ciclo
	 * del juego
	 * 
	 * @param heroe {@link Heroe} objeto que representa al heroe que entra a un
	 *              mazmorra
	 * 
	 * @return {@link Heroe} retorna al heroe con la experiencia subida si ha salido
	 *         vencedor y la vida restante despues de combatir
	 */
	Heroe entrarMazmorra(Heroe heroe);

}