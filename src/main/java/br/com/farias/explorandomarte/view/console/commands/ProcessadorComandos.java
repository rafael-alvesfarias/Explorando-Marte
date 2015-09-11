package br.com.farias.explorandomarte.view.console.commands;

import java.util.HashMap;
import java.util.Map;

import br.com.farias.explorandomarte.business.PainelControle;
import br.com.farias.explorandomarte.business.PainelControleLocal;

/**
 * Classe responsável por processar comandos recebidos via linha de comando
 * utilizando expressões regulares.
 * @author Rafael A. Farias
 *
 */
public class ProcessadorComandos {
	
	private PainelControle painelControle = new PainelControleLocal();
	
	private Map<String, Comando> comandos = new HashMap<String, Comando>();
	
	//Comandos pré-definidos
	{
		comandos.put("DEFINIR_PLANALTO", new Comando("[0-9]\\s[0-9]") {
			@Override
			public Object executar(String comando) {
				Integer lx = Integer.valueOf(comando.substring(0, 1));
				Integer ly = Integer.valueOf(comando.substring(2, 3));
				painelControle.definirPlanalto(lx, ly);
				return null;
			}
		});
		comandos.put("DEFINIR_SONDA", new Comando("[0-9]\\s[0-9]\\s[NSEW]") {
			@Override
			public Object executar(String comando) {
				int x = Integer.valueOf(comando.substring(0, 1));
				int y = Integer.valueOf(comando.substring(2, 3));
				char d = comando.charAt(4);
				painelControle.definirSonda(x, y, d);
				return null;
			}
		});
		comandos.put("CONTROLAR_SONDA", new Comando("[LRM]+") {
			@Override
			public Object executar(String comando) {
				painelControle.controlarSonda(comando);
				return null;
			}
		});
		comandos.put("LISTAR_SONDAS", new Comando("(listar|LISTAR)") {
			@Override
			public Object executar(String comando) {
				return painelControle.listarSondas();
			}
		});
	}
	
	public Object executar(String comando) throws ComandoNaoPermitidoException {
		for (String key : comandos.keySet()) {
			Comando cmd = comandos.get(key);
			if (comando.matches(cmd.getPattern())) {
				return cmd.executar(comando);
			}
		}
		throw new ComandoNaoPermitidoException(comando);
	}

}
