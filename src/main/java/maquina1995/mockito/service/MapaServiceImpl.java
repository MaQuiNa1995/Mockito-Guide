package maquina1995.mockito.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import maquina1995.mockito.dominio.Mapa;
import maquina1995.mockito.exception.FicheroYaExisteException;

@Service
public class MapaServiceImpl implements MapaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MapaServiceImpl.class);

	@Override
	public Mapa generarMapa(String nombre, Integer salas) {

		return Mapa.builder()
		        .nombre(nombre)
		        .salas(salas)
		        .build();
	}

	@Override
	public void generarFicheroDelMapa(Mapa mapa) throws FicheroYaExisteException {

		String nombreMapa = mapa.getNombre();

		LOGGER.info("Se va a generar el fichero del mapa con nombre: {}", nombreMapa);

		File ficheroMapa = new File(nombreMapa);

		if (!ficheroMapa.exists()) {

			try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ficheroMapa, Boolean.FALSE))) {
				bufferedWriter.write(mapa.toString());
			} catch (IOException exception) {
				LOGGER.warn("Se ha producido una excepción mas info: {}", exception.getMessage());
			}
		} else {
			throw new FicheroYaExisteException(ficheroMapa.getAbsolutePath());
		}
	}

	@Override
	public void renderizarMapa(Mapa mapa) {
		try {
			Thread.sleep(1000 * 1L);
		} catch (InterruptedException exception) {
			LOGGER.warn("Se ha producido una excepción al renderizar el mapa, mas info: {}", exception.getMessage());
			Thread.currentThread()
			        .interrupt();
		}
	}

}
