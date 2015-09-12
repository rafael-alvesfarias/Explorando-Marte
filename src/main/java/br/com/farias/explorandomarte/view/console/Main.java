package br.com.farias.explorandomarte.view.console;

import java.util.Scanner;
import java.util.Set;

import br.com.farias.explorandomarte.model.Sonda;
import br.com.farias.explorandomarte.view.console.commands.ComandoNaoPermitidoException;
import br.com.farias.explorandomarte.view.console.commands.ProcessadorComandos;

/**
 * Classe principal respons�vel por fornecer uma interface via linha de comando e
 * por receber os comandos para movimenta��o das sondas.
 * @author Rafael Farias
 *
 */
public class Main {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ProcessadorComandos processador = new ProcessadorComandos();
		
		System.out.println("------Explorando Marte------");
		System.out.println("Digite o ponto x e y superior direito do planalto: ");
		String comando = scanner.nextLine();
		
		try {
			processador.executar(comando);
			
			System.out.println("Digite o ponto x e y da sonda que deseja movimentar: ");
			comando = scanner.nextLine();
			
			while (!comando.equals("exit")) {
				processador.executar(comando);
				
				System.out.println("Digite os comandos para movimentar a sonda: ");
				comando = scanner.nextLine();
				
				processador.executar(comando);
				
				System.out.println("Digite o ponto x e y da sonda que deseja movimentar ou 'exit' para sair: ");
				comando = scanner.nextLine();
			}
			
			@SuppressWarnings("unchecked")
			Set<Sonda> sondas = (Set<Sonda>) processador.executar("listar");
			
			StringBuilder sb = new StringBuilder();
			for(Sonda s : sondas) {
				sb.append(s);
			}
			
			//Resultado final
			System.out.println(sb);
			
		} catch(ComandoNaoPermitidoException e) {
			
		} finally {
			scanner.close();
		}
	}

}
