package novedades_junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(RandomMaQuiParametersExtension.class)
class PruebasTest {

	@Test
	void pruebas(@RandomMaquiParameter Ejemplo ejemplo) {

		System.out.println(ejemplo.toString());
	}

}
