package maquina1995.mockito.dominio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa el héroe que entra a una mazmorra
 * 
 * @author MaQuiNa1995
 *
 */
@Builder
@Setter
@Getter
public class Heroe {

	/**
	 * Nombre del héroe
	 */
	private String nombre;

	/**
	 * Daño del héroe
	 */
	private Integer danno;

	/**
	 * Experiencia actual del héroe
	 */
	@Builder.Default
	private Integer experiencia = 0;

	/**
	 * Vida del héroe
	 */
	private Integer vida;

}
