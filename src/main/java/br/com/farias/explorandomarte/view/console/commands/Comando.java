package br.com.farias.explorandomarte.view.console.commands;

public abstract class Comando {
		
	private String pattern;
	
	public abstract Object executar(String comando);
	
	public Comando(String pattern) {
		this.pattern = pattern;
	}
	
	public String getPattern() {
		return pattern;
	}
}