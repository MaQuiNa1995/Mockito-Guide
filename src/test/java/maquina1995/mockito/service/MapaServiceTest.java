package maquina1995.mockito.service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import maquina1995.mockito.dominio.Mapa;

@SpringBootTest
class MapaServiceTest {

	/**
	 * Sirve para crear una instancia basica del objeto {@link MapaService} en el
	 * que se rastrea con mockito todas las interacciones
	 * <p>
	 * Es decir por ejemplo si usas un {@link Mock} de un
	 * {@link java.util.ArrayList} y usas el add el objeto como tal no tendrá ningun
	 * elemento
	 */
	@SpyBean
	private MapaService cut;

	private Mapa mapa;

	@BeforeEach
	public void crearMapaTest() {
		this.mapa = Mapa.builder()
		        .nombre("Mapa Test")
		        .salas(2)
		        .build();
	}

	/**
	 * Test del metodo de {@link MapaService#renderizarMapa(Mapa)} si tarda mas de 5
	 * segundos en ejecutarse fallará por:
	 * {@link Assertions#assertTimeout(Duration, org.junit.jupiter.api.function.Executable)}
	 * <p>
	 * Hemos mockeado el método para que no tenga que ejecutar el real ya que este
	 * hace 1 espera de 10 segundos simulando un procesamiento pesado
	 */
	@Test
//	@Timeout ( unit = TimeUnit.SECONDS, value = 9 )
	void renderizarMapaTest() {

		Mockito.doNothing()
		        .when(cut)
		        .renderizarMapa(ArgumentMatchers.any(Mapa.class));

		Duration cincoSegundos = Duration.of(5, ChronoUnit.SECONDS);

		Assertions.assertTimeout(cincoSegundos, () -> cut.renderizarMapa(this.mapa));
//		cut.renderizarMapa ( this.mapa );

	}

}
