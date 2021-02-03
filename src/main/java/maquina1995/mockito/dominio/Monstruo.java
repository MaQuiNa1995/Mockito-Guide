package maquina1995.mockito.dominio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase que representa la información de un enemigo de la mazmorra
 * <p>
 * Esta clase hace uso de la api funcional para poder dejar estable el objeto a
 * traves de una concatenación de setters en una sola línea
 * 
 * @author MaQuiNa1995
 *
 */
@Builder
@Setter
@Getter
@ToString(of = "nombre")
public class Monstruo {

	/**
	 * Nombre del monstruo
	 */
	private String nombre;
	/**
	 * Daño que tiene el monstruo
	 */
	private int danno;
	/**
	 * Vida que tiene el monstruo
	 */
	private int vida;
	/**
	 * Experiencia obtenida por reventar al monstruo
	 */
	private int experiencia;

}
