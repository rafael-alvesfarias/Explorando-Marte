package br.com.farias.explorandomarte.client.application.service;

import java.util.List;

/**
 * Interface padrão para controle das Sondas
 * @author Rafael A. Farias
 *
 */
public interface ExplorandoMarteService {
	
	public void definirPlanalto(int limiteX, int limiteY);
	
	public void controlarSonda(int posicaoX, int posicaoY, char direcao, String comandos);
	
	public List<String> listarSondas();
}
