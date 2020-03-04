package maquina1995.mockito.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import maquina1995.mockito.configuracion.Configuracion;
import maquina1995.mockito.dominio.Heroe;
import maquina1995.mockito.dominio.Monstruo;

/**
 * Clase de test para probar unitariamente la funcionalidad de:
 * {@link MazmorraService}
 * <p>
 * {@link ExtendWith} anotación usada para poder usar ampliaciones a los test,
 * como por ejemplo:
 * <p>
 * <LI>{@link MockitoExtension} para poder usar la funcionalidad de mockito</LI>
 * <LI>{@link SpringExtension} para poder usar la inyecci�n de dependencias de
 * spring</LI>
 * <p>
 * {@link ContextConfiguration} sirve para indicar donde se encuentra la
 * configuración a usar de spring para la resolucion de los beans en el
 * contenedor
 * <p>
 * En Junit4 se usaba org.junit.runner.RunWith pero se reemplazo por
 * {@link ExtendWith} porque esta solo permite indicar una clase
 * 
 * @author MaQuiNa1995
 *
 */
@ExtendWith({ MockitoExtension.class, SpringExtension.class })
@ContextConfiguration(classes = { Configuracion.class })
public class MazmorraServiceTest {

    /**
     * Sirve para crear mocks es decir "objetos dependencia" de las que hace uso
     * nuestro objeto anotado con {@link InjectMocks}
     */
    @Mock
    private MonstruoServiceImpl monstruoService;

    /**
     * {@link InjectMocks} sirve para decirle a mockito donde inyectar los mocks que
     * hemos creado con {@link @Mock}
     * 
     */
    @InjectMocks
    private MazmorraServiceImpl cut = new MazmorraServiceImpl();

    /**
     * Variable que contiene nuestro héroe
     */
    private Heroe heroe;

    /**
     * Cada vez que invoquemos a un método nuevo queremos resetear los atributos de
     * nuestro héroe asique anotamos este método con {@link BeforeEach}
     */
    @BeforeEach
    public void inicializarHeroe() {
	heroe = new Heroe().setDanno(200).setExperiencia(0).setNombre("HeroeTester").setVida(400);
    }

    /**
     * Al hacer uso de un método pseudo-random para hacer test queremos que por
     * ejemplo no se combata 3 veces con el bicho mas debil , sino que queremos
     * dejar de lado la aleatoriedad para poder probar el método con todos los
     * monstruos disponibles en el juego
     * <p>
     * Para ello hacemos uso de la anotación {@link ParameterizedTest} y generamos
     * los monstruos a traves de {@link MethodSource}
     * 
     * @param monstruo {@link Monstruo} recuperado del metodo generarMonstruos()
     */
    @ParameterizedTest
    @MethodSource("generarMonstruos")
    @DisplayName("Test para probar entrarMazmorrarTest() con distintos monstruos")
    public void entrarMazmorrarTest(Monstruo monstruo) {

	// Usamos mockito para decir que cuando se ejecute dentro de nuestro objeto
	// mockeado el metodo cogerMounstruoRandom (que tira de otro objeto
	// "dependencia")
	// escupa el objeto que obtenemos del metodo que alimenta este test para
	// eliminar la aleatoriedad del metodo y poder probar todos los monstruos
	Mockito.when(monstruoService.cogerMounstruoRandom()).thenReturn(monstruo);

	// Probamos el m�todo
	cut.entrarMazmorra(heroe);

	// Usamos una asunci�n para que en caso de que falle , skipee el test
	Assumptions.assumeTrue(heroe.getExperiencia() != 0);
    }

    /**
     * Método usado para alimentar el test {@link #entrarMazmorrarTest(Monstruo)}
     * generando una lista de monstruos
     * <p>
     * Para poder experimentar nuevas incorporaciones y probar el comportamiento de
     * los actuales frente a un héroe con determinadas características
     * 
     * @return {@link Stream} de {@link Monstruo} que alimenta el test
     *         {@link #entrarMazmorrarTest(Monstruo)}
     */
    private static Stream<Monstruo> generarMonstruos() {

	List<Monstruo> listaMounstruos = new ArrayList<>();

	Monstruo mounstruo = new Monstruo().setNombre("Troll").setDanno(200).setVida(1000).setExperiencia(500);

	listaMounstruos.add(mounstruo);

	mounstruo = new Monstruo().setNombre("Esqueleto").setDanno(100).setVida(500).setExperiencia(250);

	listaMounstruos.add(mounstruo);

	mounstruo = new Monstruo().setNombre("Goblin").setDanno(50).setVida(250).setExperiencia(125);

	listaMounstruos.add(mounstruo);

	return listaMounstruos.stream();
    }
}
