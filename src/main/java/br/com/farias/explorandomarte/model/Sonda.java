package br.com.farias.explorandomarte.model;

public class Sonda {
	
	private int posicaoX;
	
	private int posicaoY;
	
	private Direcao direcao;
	
	public Sonda(int posicaoX, int posicaoY, Direcao direcao) {
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
		this.direcao = direcao;
	}
	
	public void mover() {
		posicaoX = 1 * direcao.getFatorX();
		posicaoY = 1 * direcao.getFatorY();
	}
	
	public void girar(Lado lado) {
		if (lado.equals(lado.D)){
			//girar para Direita
			direcao = direcao.proximo();
		} else {
			//girar para Esquerda
			direcao = direcao.anterior();
		}
	}
	
	public int getPosicaoX() {
		return posicaoX;
	}
	
	public int getPosicaoY() {
		return posicaoY;
	}
	
}
