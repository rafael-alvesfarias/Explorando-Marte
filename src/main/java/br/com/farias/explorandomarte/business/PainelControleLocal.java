package br.com.farias.explorandomarte.business;

import br.com.farias.explorandomarte.model.DirecaoRosaDosVentos;
import br.com.farias.explorandomarte.model.Planalto;
import br.com.farias.explorandomarte.model.Sonda;
import br.com.farias.explorandomarte.model.enums.Lado;

public class PainelControleLocal implements PainelControle {

	private Planalto planalto;
	private Sonda sonda;
	
	@Override
	public void definirPlanalto(int limiteX, int limiteY) {
		planalto = new Planalto(limiteX, limiteY);
	}

	@Override
	public void definirSonda(int posicaoX, int posicaoY, char direcao) {
		sonda = new Sonda(posicaoX, posicaoY, DirecaoRosaDosVentos.fromChar(direcao));
		planalto.add(sonda);
		//TODO Sonda.setPlanalto
		//sonda.setPlanalto(planalto);
	}

	@Override
	public void controlarSonda(String comandos) {
		for(int i = 0; i < comandos.length(); i++) {
			char comando = comandos.charAt(i);
			switch (comando) {
			case 'l':
			case 'L':
				sonda.girar(Lado.L);
				break;
			case 'r':
			case 'R':
				sonda.girar(Lado.R);
				break;
			case 'm':
			case 'M':
				sonda.mover();
				break;
			}
		}
	}

	@Override
	public String listarSondas() {
		StringBuilder sb = new StringBuilder();
		for(Sonda s : planalto.getSondas()) {
			sb.append(s);
		}
		return sb.toString();
	}

}
