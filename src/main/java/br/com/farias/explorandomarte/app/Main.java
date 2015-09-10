package br.com.farias.explorandomarte.app;

import java.util.Scanner;

import br.com.farias.explorandomarte.model.DirecaoRosaDosVentos;
import br.com.farias.explorandomarte.model.Planalto;
import br.com.farias.explorandomarte.model.Sonda;
import br.com.farias.explorandomarte.model.enums.Lado;

/**
 * Classe principal responsável por fornecer uma interface via linha de comando e
 * por receber os comandos para movimentação das sondas.
 * @author Rafael Farias
 *
 */
public class Main {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("------Explorando Marte------");
		System.out.println("Digite o ponto x e y superior direito do planalto: ");
		
		String comando = scanner.nextLine();
		if(!comando.matches("[0-9]\\s[0-9]")) {
			System.out.println("Comando inválido: " + comando + ". Formato permitido: [0-9]\\s[0-9]");
			System.exit(0);
		}
		
		Integer x = Integer.valueOf(comando.substring(0, 1));
		Integer y = Integer.valueOf(comando.substring(2, 3));
		Planalto planalto = new Planalto(x, y);
		
		System.out.println("Digite o ponto x e y da sonda que deseja movimentar: ");

		comando = scanner.nextLine();
		if(!comando.matches("[0-9]\\s[0-9]\\s[NSEW]")) {
			System.out.println("Comando inválido: " + comando + ". Formato permitido: [0-9]\\s[0-9]\\s[NSEW]");
			System.exit(0);
		}
		
		x = Integer.valueOf(comando.substring(0, 1));
		y = Integer.valueOf(comando.substring(2, 3));
		Sonda sonda = new Sonda(x, y, DirecaoRosaDosVentos.fromChar(comando.charAt(4)));
		planalto.add(sonda);
		
		System.out.println("Digite os comandos para movintar a sonda: ");
		
		comando = scanner.nextLine();
		if (!comando.matches("[LRM]+")) {
			System.out.println("Comando inválido: " + comando + ". Formato permitido: [LRM]+");
			System.exit(0);
		}
		
		for (int i = 0; i < comando.length(); i++) {
			char c = comando.charAt(i);
			switch(c) {
				case 'L': sonda.girar(Lado.L);
				break;
				case 'R': sonda.girar(Lado.R);
				break;
				case 'M': sonda.mover();
				break;
				default: throw new RuntimeException("Estado do programa inesperado.");
			}
		}
		
		//Saída
		for (Sonda s : planalto.getSondas()) {
			System.out.println(s);
		}
		
		scanner.close();
	}

}
