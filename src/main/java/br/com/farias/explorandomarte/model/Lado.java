package br.com.farias.explorandomarte.model;

enum Lado {
	E, //Esquerda
	D;  //Direita
	public Lado fromChar(char lado) {
		switch (lado) {
		case 'E':
		case 'e': return E;
		case 'D':
		case 'd': return D;

		default:
			throw new IllegalArgumentException("Parâmetro lado inválido: " + lado);
		}
	}
}