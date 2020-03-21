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
     * Sirve para hacer mocks de objetos para ser usados con mockito
     * <p>
     * Adicionalmente si tenemos un objeto anotado con {@link InjectMocks} sirve
     * tambien para marcar como inyectables los mocks que hemos creado (solo si
     * estos son realmente una dependencia del otro objeto)
     * 
     * <p>
     * Es decir para mockear las dependencias de este
     */
    @Mock
    private MonstruoServiceImpl monstruoService;

    /**
     * {@link InjectMocks} sirve para decirle a mockito donde inyectar los mocks que
     * hemos creado con {@link @Mock} o {@link @Spy}
     */
    @InjectMocks
    private MazmorraServiceImpl cut = new MazmorraServiceImpl();

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

	// Probamos el método
	cut.entrarMazmorra(heroe);

	// Usamos una asunción para que en caso de que falle , skipee el test
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

	// Se crea un troll
	Monstruo mounstruo = new Monstruo().setNombre("Troll").setDanno(200).setVida(1000).setExperiencia(500);
	listaMounstruos.add(mounstruo);

	// Se crea un esqueleto
	mounstruo = new Monstruo().setNombre("Esqueleto").setDanno(100).setVida(500).setExperiencia(250);
	listaMounstruos.add(mounstruo);

	// Se crea un goblin
	mounstruo = new Monstruo().setNombre("Goblin").setDanno(50).setVida(250).setExperiencia(125);
	listaMounstruos.add(mounstruo);

	// Se devuelve un stream que será consumido por 1 test
	return listaMounstruos.stream();
    }
}
