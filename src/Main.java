import personagem.Guerreiro;
import personagem.Mago;
import personagem.Personagem;

import java.util.Scanner;

import eventos.Eventos;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Escolha nome de seu personagem: ");
        String nome = sc.nextLine();

        System.out.println("Escolha a classe: [1] - Guerreiro / [2] - Mago");
        int escolha;

        while (true) {
            if (sc.hasNextInt()) {
                escolha = sc.nextInt();
                sc.nextLine(); // Limpa o buffer

                if (escolha >= 1 && escolha <= 2) {
                    break; // Sai do loop quando a escolha for válida
                } else {
                    System.out.println("\033[1;31mEscolha fora do intervalo! Tente novamente.\033[0m");
                }
            } else {
                System.out.println("\033[1;31mEntrada inválida! Digite um número.\033[0m");
                sc.next(); // Descarta entrada inválida
            }
        }

        Personagem jogador;
        
        if (escolha == 1) {
            jogador = new Guerreiro(nome);
        } else {
            jogador = new Mago(nome);
        }

        Eventos eventos = new Eventos(jogador);
        eventos.iniciar();

        sc.close();
    }
}
