package br.com.farias.explorandomarte.model;

enum Direcao {
	N(0, 1, 0), //Norte
	E(1, 0, 90), //Leste
	S(0, -1, 180), //Sul
	W(-1, 0, 270);  //Oeste
	
	private int fatorX;
	private int fatorY;
	private int angulo;
	
	Direcao(int fatorX, int fatorY, int angulo) {
		this.fatorX = fatorX;
		this.fatorY = fatorY;
		this.angulo = angulo;
	}
	
	public int getFatorX() {
		return fatorX;
	}
	
	public int getFatorY() {
		return fatorY;
	}
	
	public Direcao anterior() {
		if (angulo >= 90) {
			return fromAngulo(angulo - 90);
		} else {
			return W;
		}
	}
	
	public Direcao proximo() {
		if(angulo < 270) {
			return fromAngulo(angulo + 90);
		} else {
			return N;
		}
	}
	
	private static Direcao fromAngulo(int angulo) {
		for(Direcao d: Direcao.values()) {
			if(d.angulo == angulo) {
				return d;
			}
		}
		throw new RuntimeException("Nunca deveria chegar aqui!");
	}
}