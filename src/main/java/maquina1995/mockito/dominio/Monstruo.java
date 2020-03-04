package maquina1995.mockito.dominio;

/**
 * Clase que representa la información de un enemigo de la mazmorra
 * <p>
 * Esta clase hace uso de la api funcional para poder dejar estable el objeto a
 * traves de una concatenación de setters en una sola lÃ­nea
 * 
 * @author MaQuiNa1995
 *
 */
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

    public String getNombre() {
	return nombre;
    }

    public Monstruo setNombre(String nombre) {
	this.nombre = nombre;
	return this;
    }

    public int getDanno() {
	return danno;
    }

    public Monstruo setDanno(int danno) {
	this.danno = danno;
	return this;
    }

    public int getVida() {
	return vida;
    }

    public Monstruo setVida(int vida) {
	this.vida = vida;
	return this;
    }

    public int getExperiencia() {
	return experiencia;
    }

    public Monstruo setExperiencia(int experiencia) {
	this.experiencia = experiencia;
	return this;
    }

    /**
     * Método usado para poner el nombre a los test generados exclusivamente
     */
    @Override
    public String toString() {
	return nombre;
    }

}
