package br.com.farias.explorandomarte.client.application.service;

import java.util.List;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import br.com.farias.explorandomarte.client.application.service.exception.ServiceException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

@RunWith(JMockit.class)
public class ExplorandoMarteServiceWSTest {
	
	private static final String PLANALTO_POST = "http://localhost:8080/ExplorandoMarte/rest_api/planalto";
	
	private static final String CONTROLAR_SONDA_POST = "http://localhost:8080/ExplorandoMarte/rest_api/sondas/1/1/controlar";
	
	private static final String SONDAS_GET = "http://localhost:8080/ExplorandoMarte/rest_api/sondas";
	
	@Tested
	private ExplorandoMarteServiceWS service;
	
	@Mocked 
	private Builder builder;
	
	@Mocked
	private ClientResponse clientResponse;
	
	@Mocked
	private Client client;
	
	@Mocked
	private WebResource webResource;
	
	@Test(expected = ServiceException.class)
	public void definirPlanaltoComErroNoServidor() {
		new Expectations() {{
			builder.post(ClientResponse.class); result = clientResponse;
			clientResponse.getStatus(); result = 400;
			clientResponse.getEntity(String.class); result = "erro";
		}};
		
		service.definirPlanalto(1, 1);
	}
	
	@Test
	public void definirPlanaltoSucesso() {
		new Expectations() {{
			client.resource(PLANALTO_POST); result = webResource;
			webResource.queryParam("limiteX", "1"); result = webResource;
			webResource.queryParam("limiteY", "1"); result = webResource;
			webResource.accept(anyString); result = builder;
			builder.post(ClientResponse.class); result = clientResponse;
			clientResponse.getStatus(); result = 200;
		}};
		
		service.definirPlanalto(1, 1);
	}
	
	@Test(expected = ServiceException.class)
	public void controlarSondaComErroNoServidor() {
		new Expectations() {{
			client.resource(CONTROLAR_SONDA_POST); result = webResource;
			webResource.queryParam(anyString, anyString); result = webResource;
			webResource.accept(anyString); result = builder;
			builder.post(ClientResponse.class); result = clientResponse;
			clientResponse.getStatus(); result = 400;
		}};
		
		service.controlarSonda(1, 1, 'N', "L");
	}
	
	@Test
	public void controlarSondaSucesso() {
		new Expectations() {{
			client.resource(CONTROLAR_SONDA_POST); result = webResource;
			webResource.queryParam("direcao", "N"); result = webResource;
			webResource.queryParam("comandos", "LRLRLRM"); result = webResource;
			webResource.accept(anyString); result = builder;
			builder.post(ClientResponse.class); result = clientResponse;
			clientResponse.getStatus(); result = 200;
		}};
		
		service.controlarSonda(1, 1, 'N', "LRLRLRM");
	}
	
	@Test
	public void listarSondasSucesso() {
		JSONObject sondasJson = new JSONObject("{sondas:[{posicaoX:1,posicaoY:2,direcao:\"N\"},"
				+ "{posicaoX:3,posicaoY:4,direcao:\"S\"}]}");
		new Expectations() {{
			client.resource(SONDAS_GET); result = webResource;
			webResource.accept(anyString); result = builder;
			builder.get(ClientResponse.class); result = clientResponse;
			clientResponse.getStatus(); result = 200;
			clientResponse.getEntity(String.class); result = sondasJson.toString();
		}};
		
		List<String> sondas = service.listarSondas();

		assertEquals("1 2 N", sondas.get(0));
		assertEquals("3 4 S", sondas.get(1));
		
	}
}
