package maquina1995.mockito.service;

import java.util.Random;

import maquina1995.mockito.dominio.Monstruo;

public interface MonstruoService {

    /**
     * Ejemplo de método no determinístico, no se puede saber con certeza el
     * mounstruo recuperado que en este caso depende de un numero pseudo-aleatorio
     * generado por la clase {@link Random}
     * 
     * @return {@link Monstruo} aleatorio , cogido de una lista que contiene estos
     *         objetos
     */
    Monstruo cogerMounstruoRandom();

}