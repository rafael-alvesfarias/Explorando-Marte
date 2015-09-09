package br.com.farias.explorandomarte.model;

public class DirecaoRosaDosVentos extends Direcao {
	
	public DirecaoRosaDosVentos(int angulo) {
		super(angulo);
	}

	//Norte
	public static final DirecaoRosaDosVentos N = new DirecaoRosaDosVentos(90);
	
	//Leste
	public static final DirecaoRosaDosVentos E = new DirecaoRosaDosVentos(180);
	
	//Sul
	public static final DirecaoRosaDosVentos S = new DirecaoRosaDosVentos(270);
	
	//Oeste
	public static final DirecaoRosaDosVentos W = new DirecaoRosaDosVentos(0);
	
	public void proxima() {
		if(getAngulo() == 0) {
			girar(270);
		} else {
			girar(-90);
		}
	}
	
	public void anterior() {
		if(getAngulo() == 270) {
			girar(-270);
		} else {
			girar(90);
		}
	}

}
