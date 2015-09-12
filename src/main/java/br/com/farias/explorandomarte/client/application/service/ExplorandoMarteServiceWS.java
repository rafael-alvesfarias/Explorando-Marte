package br.com.farias.explorandomarte.client.application.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.farias.explorandomarte.client.application.service.exception.ServiceException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class ExplorandoMarteServiceWS implements ExplorandoMarteService {
	
	private static final String RESOURCE_URI = "http://localhost:8080/ExplorandoMarte/rest_api";
	
	private Client client;
	
	public ExplorandoMarteServiceWS() {
		client = Client.create();
	}

	@Override
	public void definirPlanalto(int limiteX, int limiteY) throws ServiceException {
		ClientResponse response = client
				.resource(RESOURCE_URI + "/planalto")
				.queryParam("limiteX", String.valueOf(limiteX))
				.queryParam("limiteY", String.valueOf(limiteY))
				.accept("application/json")
				.post(ClientResponse.class);
		
		if(response.getStatus() != 200) {
			throw new ServiceException("Erro ao definir o planalto.");
		}
	}

	@Override
	public void controlarSonda(int posicaoX, int posicaoY, char direcao, String comandos) throws ServiceException {
		ClientResponse response = client
				.resource(RESOURCE_URI + "/sondas/" + posicaoX + "/" + posicaoY + "/controlar")
				.queryParam("direcao", String.valueOf(direcao))
				.queryParam("comandos", comandos)
				.accept("application/json")
				.post(ClientResponse.class);
		
		if(response.getStatus() != 200) {
			throw new ServiceException("Erro no controle da sonda.");
		}
	}

	@Override
	public List<String> listarSondas() throws ServiceException {
		ClientResponse response = client
				.resource(RESOURCE_URI + "/sondas")
				.accept("application/json")
				.get(ClientResponse.class);
		
		if(response.getStatus() == 200) {
			String output = response.getEntity(String.class);
			JSONObject retorno = new JSONObject(output);
			JSONArray listaJSON = retorno.getJSONArray("sondas");
			List<String> sondas = new ArrayList<String>();
			
			for(int i = 0; i < listaJSON.length(); i++) {
				JSONObject sondaJSON = listaJSON.getJSONObject(i);
				StringBuilder sb = new StringBuilder();
				sb.append(sondaJSON.getInt("posicaoX"))
					.append(" ")
					.append(sondaJSON.getInt("posicaoY"))
					.append(" ")
					.append(sondaJSON.getString("direcao"));
				sondas.add(sb.toString());
			}

			return sondas;
		} else {
			throw new ServiceException("Erro ao listar as sondas.");
		}
	}

}
