package novedades_junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import novedades_junit5.RandomMaQuiParametersExtension.RandomMaquiParameter;

@ExtendWith(RandomMaQuiParametersExtension.class)
class TestSuite {

	@Test
	void injectsInteger(@RandomMaquiParameter String cadena, @RandomMaquiParameter int numeroInteger) {

		System.out.println("String cadena:" + cadena);
		System.out.println("String numero:" + numeroInteger);
	}

}
