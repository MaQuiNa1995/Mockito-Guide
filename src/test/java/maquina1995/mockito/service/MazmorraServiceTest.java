package maquina1995.mockito.service;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import maquina1995.mockito.config.AbstractSpringTest;
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
 * <LI>{@link SpringExtension} para poder usar la inyección de dependencias de
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
//@TestMethodOrder(OrderAnnotation.class)
class MazmorraServiceTest extends AbstractSpringTest {

	/**
	 * Sirve para hacer mocks de objetos para ser usados con mockito
	 * <p>
	 * Adicionalmente si tenemos un objeto anotado con {@link InjectMocks} sirve
	 * tambien para marcar como inyectables los mocks que hemos creado (solo si
	 * estos son realmente una dependencia del otro objeto)
	 * 
	 * <p>
	 * Es decir para mockear las dependencias de este
	 */
	@MockBean
	private MonstruoServiceImpl monstruoService;

	/**
	 * {@link InjectMocks} sirve para decirle a mockito donde inyectar los mocks que
	 * hemos creado con {@link @Mock} o {@link @Spy}
	 */
	@Autowired
	private MazmorraServiceImpl cut;

	/**
	 * Variable que contiene nuestro héroe
	 */
	private Heroe heroe;

	/**
	 * Queremos tener una instancia por defecto antes de ejecutar cada test para
	 * cepcionarnos que cada test es independiente de otro asique recurrimos al
	 * {@link BeforeEach} para ejecutar este metodo antes de cada test
	 */
	@BeforeEach
	public void inicializarHeroe() {
		heroe = Heroe.builder()
		        .danno(200)
		        .experiencia(0)
		        .nombre("HeroeTester")
		        .vida(400)
		        .build();
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
//	@RepeatedTest(10), name = "{displayName} {currentRepetition}/{totalRepetitions}")
//    @DisplayName("Repeat!")
	@ParameterizedTest
	@MethodSource("generarMonstruos")
	@DisplayName("Test para probar entrarMazmorrarTest() con distintos monstruos")
	void entrarMazmorrarTest(Monstruo monstruo) {

		// Usamos mockito para decir que cuando se ejecute dentro de nuestro objeto
		// mockeado el metodo cogerMounstruoRandom (que tira de otro objeto
		// "dependencia")
		// escupa el objeto que obtenemos del metodo que alimenta este test para
		// eliminar la aleatoriedad del metodo y poder probar todos los monstruos
		Mockito.when(monstruoService.cogerMonstruoRandom())
		        .thenReturn(monstruo);

		// Probamos el método
		cut.entrarMazmorra(this.heroe);

		// Usamos una asunción para que en caso de que falle , skipee el test
		Assumptions.assumeTrue(this.heroe.getExperiencia() != 0);
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

		// Se crea un troll
		Monstruo troll = Monstruo.builder()
		        .nombre("Troll")
		        .danno(200)
		        .vida(1000)
		        .experiencia(500)
		        .build();

		// Se crea un esqueleto
		Monstruo esqueleto = Monstruo.builder()
		        .nombre("Esqueleto")
		        .danno(100)
		        .vida(500)
		        .experiencia(250)
		        .build();

		// Se crea un goblin
		Monstruo goblin = Monstruo.builder()
		        .nombre("Goblin")
		        .danno(50)
		        .vida(250)
		        .experiencia(125)
		        .build();

		// Se devuelve un stream que será consumido por 1 test
		return Stream.of(troll, esqueleto, goblin);

	}
}
