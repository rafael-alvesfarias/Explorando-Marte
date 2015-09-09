package br.com.farias.explorandomarte.model;

import br.com.farias.explorandomarte.model.enums.Lado;

public class Sonda {
	
	private int posicaoX;
	
	private int posicaoY;
	
	private DirecaoRosaDosVentos direcao;
	
	public Sonda(int posicaoX, int posicaoY, DirecaoRosaDosVentos direcao) {
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
		this.direcao = direcao;
	}
	
	public void mover() {
		posicaoX = (int) (1 * direcao.getFatorX());
		posicaoY = (int) (1 * direcao.getFatorY());
	}
	
	public void girar(Lado lado) {
		if (lado.equals(Lado.D)){
			//girar para Direita
			direcao.proxima();
		} else {
			//girar para Esquerda
			direcao.anterior();
		}
	}
	
	public int getPosicaoX() {
		return posicaoX;
	}
	
	public int getPosicaoY() {
		return posicaoY;
	}
	
}