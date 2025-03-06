package eventos;

import personagem.Personagem;
import java.util.Scanner;
import personagem.Monstro;
import item.Item;

public class Combate {
    private Scanner sc;

    public Combate() {
        this.sc = new Scanner(System.in);
    }

    public void batalha(Personagem personagem, Monstro monstro) {
        System.out.println("\n\033[1;33mBatalha iniciada: " + personagem.getNome() + " VS " + monstro.getNome() + "\033[0m");

        while (personagem.getVida_Atual() > 0 && monstro.getVida_max() > 0) {
            // Exibe status
            exibirStatus(personagem, monstro);

            // Exibe inventário
            System.out.println("\n\033[1;32m---- INVENTÁRIO ----\033[0m");
            personagem.exibirInventario();

            // Turno do herói
            System.out.println("\n\033[1;32m-------- SEU TURNO --------\033[0m");
            System.out.println("[1] - ATACAR\n[2] - CURAR\n[3] - RECUPERAR MANA");

            int escolha = lerEscolha(1, 3);

            switch (escolha) {
                case 1 -> {
                    System.out.println("Escolha o número do item para o ataque!");
                    escolha = lerEscolha(0, personagem.getItens().size() - 1);
                    double dano = personagem.atacar(escolha);
                    monstro.setVida_atual(monstro.getVida_atual() - dano);
                    System.out.println("\033[1;36mVocê causou " + dano + " de dano no " + monstro.getNome() + "!\033[0m");
                }
                case 2 -> {
                    System.out.println("Escolha o item que deseja usar para se curar:");
                    escolha = lerEscolha(0, personagem.getItens().size() - 1);
                    double cura = personagem.curar(escolha);
                    if (cura > 0) {
                        personagem.removerItem(personagem.getItens().get(escolha));
                        System.out.println("\033[1;32mVocê curou " + cura + " de vida!\033[0m");
                        continue;
                    }
                    System.out.println("\033[1;31mVocê não tem itens de cura!\033[0m");
                }
                case 3 -> {
                    System.out.println("Escolha o item que deseja usar para recuperar sua mana:");
                    escolha = lerEscolha(0, personagem.getItens().size() - 1);
                    double mana = personagem.regenerar(escolha);
                    if (mana > 0) {
                        personagem.removerItem(personagem.getItens().get(escolha));
                        System.out.println("\033[1;34mVocê recuperou " + mana + " de mana!\033[0m");
                        continue;
                    }
                    System.out.println("\033[1;31mVocê não tem itens de mana!\033[0m");
                }
            }

            // Verifica se o monstro morreu
            if (monstro.getVida_atual() <= 0) {
                System.out.println("\n\033[1;32mParabéns! Você venceu o " + monstro.getNome() + "!\033[0m");
                int exp_ganha = monstro.darExperiencia();
                Item drop_item = monstro.dropsItem();
                System.out.println("Você ganhou " + exp_ganha + " de experiência e um item: " + drop_item.getNome());

                personagem.setExperiencia_atual(personagem.getExperiencia_atual() + exp_ganha);
                personagem.adicionarItem(drop_item);
                return;
            }

            // Exibe status antes do ataque do monstro
            exibirStatus(personagem, monstro);

            // Turno do monstro
            System.out.println("\n\033[1;31mO monstro irá atacar! Se prepare!\033[0m");
            double dano_Monstro = monstro.getDano();
            personagem.setVida_Atual(personagem.getVida_Atual() - dano_Monstro);
            System.out.println("O " + monstro.getNome() + " causou " + dano_Monstro + " de dano!");

            // Verifica se o personagem morreu
            if (personagem.getVida_Atual() <= 0) {
                System.out.println("\n\033[1;31mSeu personagem foi derrotado!\033[0m");
                return;
            }
        }
    }

    // Método para validar a entrada do scanner e garantir que esteja dentro de um intervalo
    private int lerEscolha(int min, int max) {
        int escolha;
        while (true) {
            if (sc.hasNextInt()) {
                escolha = sc.nextInt();
                if (escolha >= min && escolha <= max) {
                    sc.nextLine(); // Limpa buffer
                    return escolha;
                } else {
                    System.out.println("\033[1;31mEscolha fora do intervalo! Tente novamente.\033[0m");
                }
            } else {
                System.out.println("\033[1;31mEntrada inválida! Digite um número.\033[0m");
                sc.next(); // Descarta entrada inválida
            }
        }
    }

    // Método para exibir as vidas do personagem e do monstro
    private void exibirStatus(Personagem personagem, Monstro monstro) {
        System.out.println("\n\033[1;36m--- STATUS ---\033[0m");
        System.out.println("\033[1;32m" + personagem.getNome() + " (Você)\033[0m");
        System.out.println("Vida: " + personagem.getVida_Atual() + "/" + personagem.getVida());
        System.out.println("\033[1;31m" + monstro.getNome() + "\033[0m");
        System.out.println("Vida: " + monstro.getVida_atual() + "/" + monstro.getVida_max());
        System.out.println("\033[1;36m----------------\033[0m\n");
    }
}
