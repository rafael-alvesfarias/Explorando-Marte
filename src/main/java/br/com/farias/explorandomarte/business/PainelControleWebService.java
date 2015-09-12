package br.com.farias.explorandomarte.business;

import java.util.LinkedHashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.farias.explorandomarte.business.exception.BusinessException;
import br.com.farias.explorandomarte.model.DirecaoRosaDosVentos;
import br.com.farias.explorandomarte.model.Sonda;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class PainelControleWebService implements PainelControle {
	
	private static final String RESOURCE_URI = "http://localhost:8080/ExplorandoMarte/rest/";
	
	private Client client;
	
	public PainelControleWebService() {
		client = Client.create();
	}

	@Override
	public void definirPlanalto(int limiteX, int limiteY) throws BusinessException {
		ClientResponse response = client
				.resource(RESOURCE_URI + "/planalto")
				.queryParam("limiteX", String.valueOf(limiteX))
				.queryParam("limiteY", String.valueOf(limiteY))
				.accept("application/json")
				.post(ClientResponse.class);
		
		if(response.getStatus() != 200) {
			throw new BusinessException("Erro na execução do comando definir planalto");
		}
	}

	@Override
	public void definirSonda(int posicaoX, int posicaoY, char direcao) throws BusinessException {
		ClientResponse response = client
				.resource(RESOURCE_URI + "/sonda")
				.queryParam("posicaoX", String.valueOf(posicaoX))
				.queryParam("posicaoY", String.valueOf(posicaoY))
				.queryParam("direcao", String.valueOf(direcao))
				.accept("application/json")
				.post(ClientResponse.class);
		
		if(response.getStatus() != 200) {
			throw new BusinessException("Erro na execução do comando definir sonda");
		}
	}

	@Override
	public void controlarSonda(String comandos) throws BusinessException {
		ClientResponse response = client
				.resource(RESOURCE_URI + "/sonda/controlar")
				.queryParam("comandos", comandos)
				.accept("application/json")
				.post(ClientResponse.class);
		
		if(response.getStatus() != 200) {
			throw new BusinessException("Erro na execução do comando definir sonda");
		}
	}

	@Override
	public Set<Sonda> listarSondas() throws BusinessException {
		ClientResponse response = client
				.resource(RESOURCE_URI + "/sonda")
				.accept("application/json")
				.post(ClientResponse.class);
		
		if(response.getStatus() == 200) {
			String output = response.getEntity(String.class);
			JSONObject retorno = new JSONObject(output);
			JSONArray listaJSON = retorno.getJSONArray("sondas");
			Set<Sonda> sondas = new LinkedHashSet<Sonda>();
			
			for(int i = 0; i < listaJSON.length(); i++) {
				int posicaoX = listaJSON.getJSONObject(i).getInt("posicaoX");
				int posicaoY = listaJSON.getJSONObject(i).getInt("posicaoY");
				DirecaoRosaDosVentos direcao = DirecaoRosaDosVentos.fromString(listaJSON.getJSONObject(i).getString("direcao"));
				sondas.add(new Sonda(posicaoX, posicaoY, direcao));
			}

			return sondas;
		} else {
			throw new BusinessException("Erro na execução do comando definir sonda");
		}
	}

}
