package maquina1995.mockito.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa el mapa a usar en la mazmorra
 * <p>
 * Esta clase hace uso de la api funcional para poder dejar estable el objeto a
 * traves de una concatenación de setters en una sola línea
 * 
 * @author MaQuiNa1995
 */
@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mapa {

	/**
	 * Representa el nombre del mapa
	 */
	private String nombre;

	/**
	 * Representa las salas que tiene el mapa
	 */
	private Integer salas;

	@Override
	public String toString() {
		return "Nombre del Mapa es; " + this.nombre + " y tiene " + this.salas + " salas";
	}

}
