package maquina1995.mockito.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import maquina1995.mockito.configuracion.Configuracion;
import maquina1995.mockito.dominio.Monstruo;

@ExtendWith({ MockitoExtension.class, SpringExtension.class })
@ContextConfiguration(classes = { Configuracion.class })
public class MonstruoServiceTest {

    private static final int EXPERIENCIA_MONSTRUO = 666;
    private static final int VIDA_MONSTRUO = 3000;
    private static final int DANNO_MONSTRUO = 1000;

    private static final String NOMBRE_MONSTRUO = "NombreTest";

    /**
     * La anotación {@link Spy} sirve para envolver una instancia de la clase
     * {@link MonstruoService} que se comportará como si esta fuera la real solo que
     * tambien estará preparada para ser rastreadas todas las interacciones con
     * mockito
     * <p>
     * Es decir por ejemplo si usas un {@link Spy} de un {@link java.util.ArrayList}
     * y usas el add el objeto como tal tendrá el elemento que añadiste
     */
    @Spy
    private MonstruoService cut;

    private Monstruo monstruo;

    /**
     * Método usado para dejar el objeto en el mismo estado antes de ejecutar cada
     * test
     */
    @BeforeEach
    public void reiniciarMonstruo() {
	monstruo = new Monstruo().setNombre(NOMBRE_MONSTRUO)
		.setDanno(DANNO_MONSTRUO)
		.setVida(VIDA_MONSTRUO)
		.setExperiencia(EXPERIENCIA_MONSTRUO);
    }

    /**
     * Test de un método que no está implementado esto representa el uso que se
     * podría dar de mockito cuando trabajamos con otros equipos o personas y estos
     * no han desarrollado un servicio que necesitamos
     * <p>
     * En este caso usamos un {@link ArgumentMatchers#anyString()} y
     * {@link ArgumentMatchers#anyInt()} para retornar el monstruo con cualquier
     * valor
     */
    @Test
    public void crearMonstruoPersonalizadoCualquierValorTest() {

	Mockito.when(cut.crearMonstruoPersonalizado(ArgumentMatchers.anyString(), ArgumentMatchers
		.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(monstruo);

	Monstruo monstruoPersonalizado = cut
		.crearMonstruoPersonalizado(NOMBRE_MONSTRUO, DANNO_MONSTRUO, VIDA_MONSTRUO, EXPERIENCIA_MONSTRUO);

	Assertions.assertEquals(monstruoPersonalizado.getDanno(), DANNO_MONSTRUO);
	Assertions.assertEquals(monstruoPersonalizado.getExperiencia(), EXPERIENCIA_MONSTRUO);
	Assertions.assertEquals(monstruoPersonalizado.getVida(), VIDA_MONSTRUO);
	Assertions.assertEquals(monstruoPersonalizado.getNombre(), NOMBRE_MONSTRUO);
    }

    /**
     * Test de un método que no está implementado esto representa el uso que se
     * podría dar de mockito cuando trabajamos con otros equipos o personas y estos
     * no han desarrollado un servicio que necesitamos
     * <p>
     * En este caso usamos un {@link ArgumentMatchers#eq(Object)} para retornar el
     * monstruo solo cuando los argumentos con los que llamamos a
     * {@link MonstruoService#crearMonstruoPersonalizado(String, int, int, int)}
     * sean: las constantes definidas en este test
     * 
     */
    @Test
    public void crearMonstruoPersonalizadoParametrosEspecificosTest() {

	Mockito.when(cut.crearMonstruoPersonalizado(ArgumentMatchers.eq(NOMBRE_MONSTRUO), ArgumentMatchers
		.eq(DANNO_MONSTRUO), ArgumentMatchers.eq(VIDA_MONSTRUO), ArgumentMatchers.eq(EXPERIENCIA_MONSTRUO)))
		.thenReturn(monstruo);

	Monstruo monstruoPersonalizado = cut
		.crearMonstruoPersonalizado(NOMBRE_MONSTRUO, DANNO_MONSTRUO, VIDA_MONSTRUO, EXPERIENCIA_MONSTRUO);

	Assertions.assertEquals(monstruoPersonalizado.getDanno(), DANNO_MONSTRUO);
	Assertions.assertEquals(monstruoPersonalizado.getExperiencia(), EXPERIENCIA_MONSTRUO);
	Assertions.assertEquals(monstruoPersonalizado.getVida(), VIDA_MONSTRUO);
	Assertions.assertEquals(monstruoPersonalizado.getNombre(), NOMBRE_MONSTRUO);
    }

    /**
     * Test de un método que no está implementado
     * <p>
     * Para que el test pase se debe lanzar un {@link UnsupportedOperationException}
     * <p>
     * Adicionalmente hacemos un assert para ver si el mensaje de la excepción es el
     * correcto asi nos aseguramos de haber recorrido el camino que queremos
     * <p>
     * puede darse el caso en que se lancen el mismo tipo de excepciones mas de 1
     * vez pero con distinto mensaje)
     */
    @Test
    public void crearMonstruoPersonalizadoMetodoRealTest() {

	Mockito.when(cut.crearMonstruoPersonalizado(ArgumentMatchers.eq(NOMBRE_MONSTRUO), ArgumentMatchers
		.eq(DANNO_MONSTRUO), ArgumentMatchers.eq(VIDA_MONSTRUO), ArgumentMatchers.eq(EXPERIENCIA_MONSTRUO)));

	// Assert de que se ha lanzado X excepción
	UnsupportedOperationException excepcion = Assertions.assertThrows(UnsupportedOperationException.class, () -> cut
		.crearMonstruoPersonalizado(NOMBRE_MONSTRUO, DANNO_MONSTRUO, VIDA_MONSTRUO, EXPERIENCIA_MONSTRUO));

	// Assert del mensaje de la expepción
	Assertions.assertEquals("Método no implementado, vuelve en 1 semana ;)", excepcion.getLocalizedMessage());

    }
}
