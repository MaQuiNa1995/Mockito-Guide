package maquina1995.mockito.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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

	@Test
	void diferenciasBasicasTest() {

		this.cadenaReal.add(CADENA);
		Assertions.assertEquals(1, this.cadenaReal.size());

		cadenasMock.add(CADENA);
		Assertions.assertEquals(0, this.cadenasMock.size());

		cadenasSpy.add(CADENA);
		Mockito.verify(cadenasSpy)
		        .add(CADENA);

		Assertions.assertEquals(1, this.cadenasSpy.size());

	}

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

}
