package br.com.farias.explorandomarte.business;

import java.util.Set;

import br.com.farias.explorandomarte.model.Sonda;

/**
 * Interface padrão para controle das Sondas
 * @author Rafael A. Farias
 *
 */
public interface PainelControle {
	
	public void definirPlanalto(int limiteX, int limiteY);
	
	public void definirSonda(int posicaoX, int posicaoY, char direcao);
	
	public void controlarSonda(String comandos);
	
	public Set<Sonda> listarSondas();
}
