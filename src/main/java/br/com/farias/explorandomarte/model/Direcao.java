package br.com.farias.explorandomarte.model;

public class Direcao {

	private double fatorX;
	private double fatorY;
	private int angulo;
	
	public Direcao(int angulo) {
		if(angulo < 0 || angulo > 359) {
			throw new RuntimeException("O parâmetro ângulo é inválido: " + angulo);
		}
		this.angulo = angulo;
		this.fatorX = Math.cos(angulo);
		this.fatorY = Math.sin(angulo);
	}
	
	public void girar(int graus) {
		int novoAngulo = angulo + graus;
		if(novoAngulo < 0 || novoAngulo > 359) {
			throw new RuntimeException("O parâmetro graus é maior que o permitido: " + graus);
		}
		this.angulo = novoAngulo;
		this.fatorX = Math.cos(novoAngulo);
		this.fatorY = Math.sin(novoAngulo);
	}
	
	public double getFatorX() {
		return fatorX;
	}
	
	public double getFatorY() {
		return fatorY;
	}

	public int getAngulo() {
		return angulo;
	}
}