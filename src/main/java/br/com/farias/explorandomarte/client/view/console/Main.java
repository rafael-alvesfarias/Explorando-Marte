package br.com.farias.explorandomarte.client.view.console;

import java.util.List;
import java.util.Scanner;

import br.com.farias.explorandomarte.client.application.service.ExplorandoMarteService;
import br.com.farias.explorandomarte.client.application.service.ExplorandoMarteServiceWS;
import br.com.farias.explorandomarte.client.view.console.commands.Comando;
import br.com.farias.explorandomarte.client.view.console.commands.ComandoNaoPermitidoException;
import br.com.farias.explorandomarte.client.view.console.commands.ProcessadorComandos;

/**
 * Classe principal responsável por fornecer uma interface via linha de comando e
 * por receber os comandos para movimentação das sondas.
 * @author Rafael Farias
 *
 */
public class Main {
	
	public static void main(String[] args) {
		ExplorandoMarteService painelControle = new ExplorandoMarteServiceWS();
		Scanner scanner = new Scanner(System.in);
		ProcessadorComandos processador = carregarComandos(painelControle);
		
		System.out.println("------Explorando Marte------");
		System.out.println("Digite os comandos solicitados ou sair para interromper a execução.");
		
		processador.comandosPermitidos("DEFINIR_PLANALTO", "SAIR");
		System.out.println("Digite o ponto x e y superior direito do planalto: ");
		String comando = scanner.nextLine();
		try {
			processador.executar(comando);
			
			processador.comandosPermitidos("CONTROLAR_SONDA", "SAIR");
			System.out.println("Digite o ponto x e y da sonda que deseja movimentar: ");
			comando = scanner.nextLine();
			
			while (!comando.equals("sair")) {
				
				System.out.println("Digite os comandos para movimentar a sonda: ");
				comando += scanner.nextLine(); //Concatena dois comandos para executaren juntos
				
				processador.executar(comando);
				
				System.out.println("Digite o ponto x e y da sonda que deseja movimentar: ");
				comando = scanner.nextLine();
			}
			
			processador.comandosPermitidos("LISTAR_SONDAS");
			
			@SuppressWarnings("unchecked")
			List<String> sondas = (List<String>) processador.executar("listar");
			
			//Resultado final
			for(String s : sondas) {
				System.out.println(s);
			}			
			
		} catch (ComandoNaoPermitidoException e) {
			System.out.println(e.getMessage());
		} finally {
			scanner.close();
		}
	}

	/**
	 * Cria uma nova instância da classe ProcessadorComandos e carrega os comandos
	 * necessários para a interface atual.
	 * @param painelControle - interface de negócio para execução dos comandos
	 * @return
	 */
	private static ProcessadorComandos carregarComandos(ExplorandoMarteService painelControle) {
		ProcessadorComandos processador = new ProcessadorComandos();
		processador.adicionarComando("DEFINIR_PLANALTO", new Comando("[0-9]\\s[0-9]") {
			@Override
			public Object executar(String comando) {
				Integer lx = Integer.valueOf(comando.substring(0, 1));
				Integer ly = Integer.valueOf(comando.substring(2, 3));
				painelControle.definirPlanalto(lx, ly);
				return null;
			}
		});
		
		processador.adicionarComando("CONTROLAR_SONDA", new Comando("[0-9]\\s[0-9]\\s[NSEW][LRM]+") {
			@Override
			public Object executar(String comando) {
				int x = Integer.valueOf(comando.substring(0, 1));
				int y = Integer.valueOf(comando.substring(2, 3));
				char d = comando.charAt(4);
				painelControle.controlarSonda(x, y, d, comando.substring(5));
				return null;
			}
		});
		
		processador.adicionarComando("LISTAR_SONDAS", new Comando("(listar|LISTAR)") {
			@Override
			public Object executar(String comando) {
				return painelControle.listarSondas();
			}
		});
		
		processador.adicionarComando("SAIR", new Comando("(sair)") {
			@Override
			public Object executar(String comando) {
				System.out.println("Finalizando ...");
				System.exit(0);
				return null;
			}
		});
		
		return processador;
	}

}
