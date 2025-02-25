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
        this.combate = new Combate(); // Inicializa a classe de combate
    }

    public void iniciar() {
        System.out.println("\n\033[1;36mBem-vindo ao RPG!\033[0m");
        System.out.println("\033[1;33mSua jornada começa agora...\033[0m\n");

        while (jogador.getVida_Atual() > 0) { // O jogo só acaba se o jogador morrer ou sair
            Monstro monstro = gerarNovoMonstro();
            System.out.println("\n\033[1;32mUm " + monstro.getNome() + " apareceu!\033[0m");

            combate.batalha(jogador, monstro); // Chama a batalha usando a classe Combate

            if (jogador.getVida_Atual() <= 0) {
                System.out.println("\n\033[1;31mVocê foi derrotado! Fim de jogo.\033[0m");
                break;
            }

            System.out.println("\n\033[1;34mO que deseja fazer agora?\033[0m");
            System.out.println("[1] Continuar explorando");
            System.out.println("[2] Descansar (recupera um pouco de vida)");
            System.out.println("[3] Sair do jogo");

            int escolha = obterEscolhaUsuario(1, 3);

            if (escolha == 2) {
                jogador.setVida_Atual(Math.min(jogador.getVida(), jogador.getVida_Atual() + 20));
                System.out.println("\n\033[1;32mVocê descansou e recuperou um pouco de vida.\033[0m");
            } else if (escolha == 3) {
                System.out.println("\n\033[1;33mVocê decidiu sair da aventura. Fim de jogo.\033[0m");
                break;
            }
        }
    }

    private Monstro gerarNovoMonstro() {
        String[] nomes = {"Goblin", "Orc", "Lobo Selvagem", "Esqueleto", "Dragão Jovem"};
        String nome = nomes[random.nextInt(nomes.length)];

        int nivelJogador = jogador.getNivel();
        int nivelMonstro = Math.max(1, nivelJogador - 1 + random.nextInt(3)); // Nível próximo ao do jogador

        double vida = 30 + (nivelMonstro * 10) + random.nextInt(20);
        double dano = 5 + (nivelMonstro * 2) + random.nextInt(10);

        return new Monstro(nome, vida, nivelMonstro, nivelMonstro, dano);
    }

    private int obterEscolhaUsuario(int min, int max) {
        int escolha;
        while (true) {
            System.out.print("Escolha: ");
            if (scanner.hasNextInt()) {
                escolha = scanner.nextInt();
                if (escolha >= min && escolha <= max) {
                    return escolha;
                }
            }
            scanner.nextLine(); // Limpa buffer
            System.out.println("\033[1;31mOpção inválida! Tente novamente.\033[0m");
        }
    }
}
