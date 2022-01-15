package maquina1995.mockito.integration;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import maquina1995.mockito.dominio.Mapa;

@Component
@RequiredArgsConstructor
public class MapaIntegrationImpl implements MapaIntegration {

	public static final String URL_DOWNLOAD = "http://www.google.com/descargar/";

	private final RestTemplate restTemplate;

	@Override
	public Mapa descargar(int id) {

		ResponseEntity<Mapa> mapaDescargado = restTemplate.getForEntity(URL_DOWNLOAD + id, Mapa.class);

		return mapaDescargado.getBody();
	}

}
