package maquina1995.mockito;

public class Heroe {

    private String nombre;
    private int danno;
    private int experiencia = 0;
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
