package maquina1995.mockito.exception;

public class FicheroyaExisteException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -1769831507675786369L;

    private String rutaFichero;

    public FicheroyaExisteException(String rutaFichero) {
	super();
	this.rutaFichero = rutaFichero;
    }

    @Override
    public String getLocalizedMessage() {
	return "El nombre de fichero proporcionado ya existe: " + rutaFichero;
    }

}
