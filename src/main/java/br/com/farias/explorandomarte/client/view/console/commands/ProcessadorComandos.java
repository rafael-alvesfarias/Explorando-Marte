package br.com.farias.explorandomarte.client.view.console.commands;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Classe respons�vel por processar comandos recebidos via linha de comando
 * utilizando express�es regulares.
 * @author Rafael A. Farias
 *
 */
public class ProcessadorComandos {
	
	private Map<String, Comando> comandos = new HashMap<String, Comando>();
	private Set<String> comandosPermitidos = new HashSet<String>();
	
	/**
	 * Executa o comando especificado de acordo com o padr�o de express�o
	 * regular que se encaixa para o par�metro comando.
	 * @param comando
	 * @return
	 * @throws ComandoNaoPermitidoException - caso o comando n�o exista ou n�o seja permitido no momento.
	 */
	public Object executar(String comando) throws ComandoNaoPermitidoException {
		for (String key : comandos.keySet()) {
			Comando cmd = comandos.get(key);
			if (comando.matches(cmd.getPattern())) {
				if(comandosPermitidos.contains(key)) {
					return cmd.executar(comando);
				}
			}
		}
		throw new ComandoNaoPermitidoException(comando);
	}

	/**
	 * Adiciona um novo comando para a execu��o
	 * @param chave
	 * @param comando
	 */
	public void adicionarComando(String chave, Comando comando) {
		comandos.put(chave, comando);
		comandosPermitidos.add(chave);
	}

	/**
	 * Informa ao Processador quais os comandos s�o permitidos para execu��o.
	 * @param comandos
	 */
	public void comandosPermitidos(String... comandos) {
		comandosPermitidos = new HashSet<String>();
		for (String c : comandos) {
			comandosPermitidos.add(c);
		}
	}

}
