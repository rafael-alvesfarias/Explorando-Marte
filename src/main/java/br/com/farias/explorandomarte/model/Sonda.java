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
		posicaoX += (int) (1 * direcao.getFatorX());
		posicaoY += (int) (1 * direcao.getFatorY());
	}
	
	public void girar(Lado lado) {
		if (lado.equals(Lado.R)){
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + posicaoX;
		result = prime * result + posicaoY;
		return result;
	}

	//Considera iguais duas sondas com a mesma posição X e Y
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sonda other = (Sonda) obj;
		if (posicaoX != other.posicaoX)
			return false;
		if (posicaoY != other.posicaoY)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return posicaoX + " " + posicaoY + " " + direcao;
	}
	
}