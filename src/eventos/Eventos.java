package eventos;

import java.util.Random;
import java.util.Scanner;
import personagem.Personagem;
import personagem.Monstro;

public class Eventos {
    private Personagem jogador;
    private Scanner scanner;
    private Random random;
    private Combate combate;

    public Eventos(Personagem jogador) {
        this.jogador = jogador;
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.combate = new Combate();
    }

    public void iniciar() {
        System.out.println("Bem-vindo ao jogo!");

        while (jogador.getVida_Atual() > 0) { // O jogo só acaba se o jogador morrer ou sair
            Monstro monstro = gerarNovoMonstro();
            System.out.println(" Um " + monstro.getNome() + " apareceu!");

            combate.batalha(jogador, monstro); // Chama a batalha usando a classe Combate

            if (jogador.getVida_Atual() <= 0) {
                System.out.println(" Você foi derrotado! Fim de jogo.");
                break;
            }

            System.out.println(" O que deseja fazer agora?");
            System.out.println("[1] Continuar explorando");
            System.out.println("[2] Descansar (recupera um pouco de vida)");
            System.out.println("[3] Sair do jogo");

            int escolha = scanner.nextInt();
            if (escolha == 2) {
                jogador.setVida_Atual(Math.min(jogador.getVida(), jogador.getVida_Atual() + 20));
                System.out.println("Você descansou e recuperou um pouco de vida.");
            } else if (escolha == 3) {
                System.out.println(" Você decidiu sair da aventura. Fim de jogo.");
                break;
            }
        }
    }

    private Monstro gerarNovoMonstro() {
        String[] nomes = {"Goblin", "Orc", "Lobo Selvagem", "Esqueleto", "Dragão Jovem"};
        String nome = nomes[random.nextInt(nomes.length)];
        double vida = 30 + random.nextInt(50);
        double dano = 5 + random.nextInt(20);
        int level = 1 + random.nextInt(5);

        return new Monstro(nome, vida, level, level, dano);
    }
}
