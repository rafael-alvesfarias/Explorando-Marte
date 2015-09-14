package br.com.farias.explorandomarte.client.view.console.commands;

public abstract class Comando {
		
	/**
	 * Padrão de exprssão regular para identificação do comando.
	 */
	private String pattern;
	
	public abstract Object executar(String comando);
	
	public Comando(String pattern) {
		this.pattern = pattern;
	}
	
	public String getPattern() {
		return pattern;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pattern == null) ? 0 : pattern.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comando other = (Comando) obj;
		if (pattern == null) {
			if (other.pattern != null)
				return false;
		} else if (!pattern.equals(other.pattern))
			return false;
		return true;
	}

}