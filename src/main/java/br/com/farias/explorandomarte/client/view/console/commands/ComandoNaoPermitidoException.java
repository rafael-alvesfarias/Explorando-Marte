package br.com.farias.explorandomarte.client.view.console.commands;

public class ComandoNaoPermitidoException extends Exception {

	private static final long serialVersionUID = 9047592501059221874L;

	public ComandoNaoPermitidoException(String comando) {
		super("Erro ao executar o comando, comando não permitido: " + comando);
	}

}
