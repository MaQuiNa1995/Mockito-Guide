package maquina1995.mockito.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import maquina1995.mockito.dominio.Monstruo;

@Service
public class MonstruoServiceImpl implements MonstruoService {

    /**
     * Lista que contiene la lista de mounstruos
     */
    private List<Monstruo> listaMonstruos = new ArrayList<>();

    /**
     * Constructor por defecto en el que se rellena la lista de mounstruos
     */
    public MonstruoServiceImpl() {
	rellenarLista();
    }

    /*
     * @see maquina1995.mockito.MonstruoService#cogerMounstruoRandom()
     */
    @Override
    public Monstruo cogerMounstruoRandom() {

	int numeroRandom = new Random().nextInt(listaMonstruos.size());

	return listaMonstruos.get(numeroRandom);

    }

    /**
     * Método usado para rellenar la lista de mounstruos
     */
    private void rellenarLista() {

	Monstruo mounstruo = new Monstruo().setNombre("Troll").setDanno(200).setVida(1000).setExperiencia(500);
	listaMonstruos.add(mounstruo);

	mounstruo = new Monstruo().setNombre("Esqueleto").setDanno(100).setVida(500).setExperiencia(250);
	listaMonstruos.add(mounstruo);

	mounstruo = new Monstruo().setNombre("Goblin").setDanno(50).setVida(250).setExperiencia(125);
	listaMonstruos.add(mounstruo);

    }

    @Override
    public Monstruo crearMonstruoPersonalizado(String nombre, int danno, int vida, int experiencia) {

	throw new UnsupportedOperationException("Método no implementado, vuelve en 1 semana ;)");

    }

}
