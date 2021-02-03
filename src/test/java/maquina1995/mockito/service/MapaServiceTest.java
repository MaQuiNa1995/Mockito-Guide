package maquina1995.mockito.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import maquina1995.mockito.dominio.Mapa;

class MapaServiceTest extends AbstractSpringTest {

	/**
	 * Sirve para crear una instancia basica del objeto {@link MapaService} en el
	 * que se rastrea con mockito todas las interacciones
	 * <p>
	 * Es decir por ejemplo si usas un {@link Mock} de un
	 * {@link java.util.ArrayList} y usas el add el objeto como tal no tendrá ningun
	 * elemento
	 */
	@MockBean
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
	 * Test del metodo de {@link MapaService#renderizarMapa(Mapa)} si tarda mas de 7
	 * segundos en ejecutarse fallará por: {@link Timeout}
	 * <p>
	 * Hemos mockeado el método para que no tenga que ejecutar el real ya que este
	 * hace 1 espera de 10 segundos simulando un procesamiento pesado
	 * <p>
	 * En este caso no es necesario el uso de Assertions
	 */
	@Test
	@Timeout(value = 7)
	void renderizarMapaTest() {

		Mockito.doNothing()
		        .when(cut)
		        .renderizarMapa(ArgumentMatchers.any(Mapa.class));

		cut.renderizarMapa(mapa);

	}

}
