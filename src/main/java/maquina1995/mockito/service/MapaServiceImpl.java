package maquina1995.mockito.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import maquina1995.mockito.dominio.Mapa;
import maquina1995.mockito.exception.FicheroyaExisteException;

@Service
public class MapaServiceImpl implements MapaService {

    private static final Logger LOGGER = Logger.getLogger(MapaServiceImpl.class.getName());

    /*
     * @see maquina1995.mockito.service.MapaService2#generarMapa(java.lang.String,
     * int)
     */
    @Override
    public Mapa generarMapa(String nombre, int salas) {

	return new Mapa().setNombre(nombre).setSalas(salas);

    }

    /*
     * @see
     * maquina1995.mockito.service.MapaService2#generarFicheroDelMapa(maquina1995.
     * mockito.dominio.Mapa)
     */
    @Override
    public void generarFicheroDelMapa(Mapa mapa) throws FicheroyaExisteException {

	String nombreMapa = mapa.getNombre();

	LOGGER.info("Se va a generar el fichero del mapa con nombre: " + nombreMapa);

	File ficheroMapa = new File(nombreMapa);

	if (ficheroMapa.exists() == Boolean.FALSE) {

	    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ficheroMapa, Boolean.FALSE))) {
		bufferedWriter.write(mapa.toString());
	    } catch (IOException exception) {
		LOGGER.warning("Se ha producido una excepción mas info: " + exception.getMessage());
	    }
	} else {
	    throw new FicheroyaExisteException(ficheroMapa.getAbsolutePath());
	}
    }

    /*
     * @see maquina1995.mockito.service.MapaService2#renderizarMapa()
     */
    @Override
    public void renderizarMapa(Mapa mapa) {
	try {
	    Thread.sleep(10L);
	} catch (InterruptedException exception) {
	    LOGGER.warning("Se ha producido una excepción al renderizar el mapa, mas info: " + exception.getMessage());
	    Thread.currentThread().interrupt();
	}
    }

}
