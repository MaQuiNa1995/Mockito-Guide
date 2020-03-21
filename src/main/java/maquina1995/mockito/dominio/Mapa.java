package maquina1995.mockito.dominio;

/**
 * Clase que representa el mapa a usar en la mazmorra
 * <p>
 * Esta clase hace uso de la api funcional para poder dejar estable el objeto a
 * traves de una concatenación de setters en una sola línea
 * 
 * @author MaQuiNa1995
 *
 */
public class Mapa {

    /**
     * Representa el nombre del mapa
     */
    private String nombre;

    /**
     * Representa las salas que tiene el mapa
     */
    private int salas;

    public String getNombre() {
	return nombre;
    }

    public Mapa setNombre(String nombre) {
	this.nombre = nombre;
	return this;
    }

    public int getSalas() {
	return salas;
    }

    public Mapa setSalas(int salas) {
	this.salas = salas;
	return this;
    }

    @Override
    public String toString() {
	return "Nombre del Mapa es; " + nombre + " y tiene " + salas + " salas";
    }

}
