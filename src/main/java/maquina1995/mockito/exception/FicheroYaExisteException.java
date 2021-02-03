package maquina1995.mockito.exception;

public class FicheroYaExisteException extends Exception {

	public FicheroYaExisteException(String rutaFichero) {
		super("El nombre de fichero proporcionado ya existe: " + rutaFichero);
	}

}
