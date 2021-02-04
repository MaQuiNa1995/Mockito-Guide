package maquina1995.mockito.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

class EjemploTest extends AbstractSpringTest {

	private final String CADENA = "Soy una cadena";

	private List<String> cadenaReal = new ArrayList<>();

	@Mock // @MockBean
	private List<String> cadenasMock;

	@Spy // @Spybean
	private List<String> cadenasSpy = new ArrayList<>();

	@Captor
	private ArgumentCaptor<String> argumentCaptor;

	// ----------------- Real/Mock/Spy Mockito Básico ------------------
	@Test
	void diferenciasBasicasTest() {

		this.cadenaReal.add(CADENA);
		Assertions.assertEquals(1, this.cadenaReal.size());

		cadenasMock.add(CADENA);
		Assertions.assertEquals(0, cadenasMock.size());

		cadenasSpy.add(CADENA);
		Mockito.verify(cadenasSpy)
		        .add(CADENA);

		Assertions.assertEquals(1, cadenasSpy.size());

	}

	// ------------------------- DO_ANSWER ------------------

	/**
	 * Sirve para a la par que haces 1 mock de un metodo si por cualquier cosa
	 * necesitas que se hagan mas cosas dentro
	 * <p>
	 * (piensa que solo se puede mockear la salida y la entrada de los argumentos
	 * sin usar doAnswer)
	 * <p>
	 * 
	 */
	@Test
	void doAnswerTest() {

		AtomicInteger integer = new AtomicInteger(0);

		Mockito.doAnswer(invocation -> {
			integer.getAndIncrement();
			return null;
		})
		        .when(cadenasMock)
		        .add(CADENA);

		cadenasMock.add(CADENA);

		Assertions.assertEquals(1, integer.get());

	}

	// ------------------------- VERIFY ------------------

	@Test
	void verifyTest() {

		// Mock/Spy Sin argumentos

		cadenasMock.size();
		cadenasMock.size();
		cadenasMock.size();

		Mockito.verify(cadenasMock, Mockito.times(3))
		        .size();

		// Mock/Spy Con argumentos
		String otraCadena = "Otra cadena :D";

		cadenasMock.add(CADENA);
		cadenasMock.add(CADENA);
		cadenasMock.add(otraCadena);

		// n veces
		Mockito.verify(cadenasMock, Mockito.times(2))
		        .add(CADENA);
		// 1 veces
		Mockito.verify(cadenasMock)
		        .add(otraCadena);

	}

	// ------------------------- STUBS Real/Mock/Spy ------------------

	@Test
	void stubsTest() {

		// --------------------- Real ----------------------
		this.cadenaReal.add(CADENA);
		Assertions.assertEquals(CADENA, this.cadenaReal.get(0));

		// --------------------- Mock ----------------------

		// Antes del mockeo
		cadenasMock.add(CADENA);
		Assertions.assertNull(cadenasMock.get(90));

		// Despues del mockeo
		Mockito.when(cadenasMock.get(1057))
		        .thenReturn(CADENA);
		Assertions.assertEquals(CADENA, cadenasMock.get(1057));

		// --------------------- Spy ----------------------

		// Antes del mockeo
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> cadenasSpy.get(1000));

		// Despues del mockeo
		Mockito.doReturn(CADENA)
		        .when(cadenasSpy)
		        .get(1000);
		Assertions.assertEquals(CADENA, cadenasSpy.get(1000));

	}

	// ------------------------- Captor ------------------

	@Test
	void captor1ValorTest() {

		// Con 1 argumento
		cadenasSpy.add(CADENA);

		Mockito.verify(cadenasSpy)
		        .add(argumentCaptor.capture());

		Assertions.assertEquals(CADENA, argumentCaptor.getValue());

	}

	@Test
	void captorN_ValoresTest() {

		String otraCadena = "Otra cadena :D";

		// Con varios argumentos

		cadenasMock.add(CADENA);
		cadenasMock.add(CADENA);
		cadenasMock.add(otraCadena);

		Mockito.verify(cadenasMock)
		        .add(argumentCaptor.capture());

		Assertions.assertEquals(CADENA, argumentCaptor.getAllValues()
		        .get(0));
		Assertions.assertEquals(CADENA, argumentCaptor.getAllValues()
		        .get(1));
		Assertions.assertEquals(otraCadena, argumentCaptor.getAllValues()
		        .get(2));

	}

}
