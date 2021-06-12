package maquina1995.mockito.service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import maquina1995.mockito.dominio.Monstruo;

@Service
public class MonstruoServiceImpl implements MonstruoService {

	/**
	 * Lista que contiene la lista de mounstruos
	 */
	private final List<Monstruo> listaMonstruos = this.crearMonstruos();

	@Override
	public Monstruo cogerMonstruoRandom() {

		int numeroRandom = new Random().nextInt(this.listaMonstruos.size());

		return this.listaMonstruos.get(numeroRandom);

	}

	@Override
	public Monstruo crearMonstruoPersonalizado(String nombre, Integer danno, Integer vida, Integer experiencia) {

		throw new UnsupportedOperationException("Método no implementado, vuelve en 1 semana ;)");

	}

	/**
	 * Método usado para rellenar la lista de mounstruos
	 * 
	 * @return
	 */
	private List<Monstruo> crearMonstruos() {

		Monstruo monstruo = Monstruo.builder()
		        .nombre("Troll")
		        .danno(200)
		        .vida(1000)
		        .experiencia(500)
		        .build();

		Monstruo monstruo2 = Monstruo.builder()
		        .nombre("Esqueleto")
		        .danno(100)
		        .vida(500)
		        .experiencia(250)
		        .build();

		Monstruo monstruo3 = Monstruo.builder()
		        .nombre("Goblin")
		        .danno(50)
		        .vida(250)
		        .experiencia(125)
		        .build();

		return Arrays.asList(monstruo, monstruo2, monstruo3);
	}

}
