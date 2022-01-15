package maquina1995.mockito.integration;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import maquina1995.mockito.dominio.Mapa;

/**
 * Podemos mockear las llamadas hechas con cierto RestTemplate en este caso el
 * que se hace Autowired es el usado por el método
 * {@link MapaIntegration#descargar(int)}
 * <p>
 * Por lo que podemos llamar a
 * {@link MockRestServiceServer#createServer(RestTemplate)}
 * 
 * para posteriormente hacer los assertions explicados en:
 * {@link MapaIntegrationTest#llamadaMockeadaTest()}
 * 
 * @author MaQuiNa1995
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MapaIntegrationTest {

	@Autowired
	private MapaIntegration cut;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	private MockRestServiceServer mockServer;

	/**
	 * Por cada test "reiniciamos" el MockRestServiceServer
	 */
	@BeforeEach
	public void setUp() {
		this.mockServer = MockRestServiceServer.createServer(restTemplate);
	}

	@Test
	void descargarTest() throws JsonProcessingException, URISyntaxException {

		Mapa expected = Mapa.builder()
		        .nombre("falso")
		        .salas(0)
		        .build();

		this.mockServer
		        // Verificamos que se llama 1 sola vez a cierta URL
		        .expect(ExpectedCount.once(), MockRestRequestMatchers.requestTo(MapaIntegrationImpl.URL_DOWNLOAD + "1"))
		        // Verificamos que esa llamada a la URl anterior sea un GET
		        .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
		        // Verificamos que devuelve un 200 (OK)
		        .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
		                // y la response sea un JSON
		                .contentType(MediaType.APPLICATION_JSON)
		                // y el body sea el json de nuestro objeto expected definiddo lo primero
		                .body(objectMapper.writeValueAsString(expected)));

		// Hacemos la llamada a nuestra clase a testear
		Mapa actual = cut.descargar(1);

		// Verificamos los mocks
		this.mockServer.verify();

		// A traves del equals y hashcode verificamos la equivalencia de los 2 objetos
		// el esperado y el que hemos recibido de la llamada al método (Que será este el
		// que hayamos mockeado)
		Assertions.assertEquals(expected, actual);
	}
}