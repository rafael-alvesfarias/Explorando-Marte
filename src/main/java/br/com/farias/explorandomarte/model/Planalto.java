package br.com.farias.explorandomarte.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Planalto {
	
	private int tamanhoX;
	
	private int tamanhoY;
	
	//Utilizado LinkdedHashSet para manter a ordem de inserção e não permitir duplicatas
	private final Set<Sonda> sondas = new LinkedHashSet<Sonda>();
	
	public Planalto(int tamanhoX, int tamanhoY) {
		this.tamanhoX = tamanhoX;
		this.tamanhoY = tamanhoY;
	}

	public void add(Sonda sonda) {
		if (posicaoDisponivel(sonda)) {
			sondas.add(sonda);
		} else {
			//TODO Criar a exceção PosicaoNaoDisponivelException
			//throw new PosicaoNaoDisponivelException(sonda.getPosicaoX(), sonda.getPosicaoY());
		}
	}
	
	public boolean posicaoDisponivel(Sonda sonda) {
		return sonda.getPosicaoX() <= tamanhoX && sonda.getPosicaoY() <= tamanhoY && !sondas.contains(sonda);
	}
	
	public Set<Sonda> getSondas() {
		return Collections.unmodifiableSet(sondas);
	}

}
