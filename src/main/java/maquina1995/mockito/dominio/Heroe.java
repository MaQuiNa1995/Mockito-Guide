package maquina1995.mockito.dominio;

/**
 * Clase que representa el héroe que entra a una mazmorra
 * 
 * @author MaQuiNa1995
 *
 */
public class Heroe {

    /**
     * Nombre del héroe
     */
    private String nombre;

    /**
     * Daño del héroe
     */
    private int danno;

    /**
     * Experiencia actual del héroe
     */
    private int experiencia = 0;

    /**
     * Vida del héroe
     */
    private int vida;

    public String getNombre() {
	return nombre;
    }

    public Heroe setNombre(String nombre) {
	this.nombre = nombre;
	return this;
    }

    public int getExperiencia() {
	return experiencia;
    }

    public Heroe setExperiencia(int experiencia) {
	this.experiencia = experiencia;
	return this;
    }

    public int getVida() {
	return vida;
    }

    public Heroe setVida(int vida) {
	this.vida = vida;
	return this;
    }

    public int getDanno() {
	return danno;
    }

    public Heroe setDanno(int danno) {
	this.danno = danno;
	return this;
    }

}
