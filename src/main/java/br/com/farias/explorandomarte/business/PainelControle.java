package br.com.farias.explorandomarte.business;

/**
 * Interface padr�o para controle das Sondas
 * @author Rafael A. Farias
 *
 */
public interface PainelControle {
	
	public void definirPlanalto(int limiteX, int limiteY);
	
	public void definirSonda(int posicaoX, int posicaoY, char direcao);
	
	public void controlarSonda(String comandos);
	
	public String listarSondas();
}
