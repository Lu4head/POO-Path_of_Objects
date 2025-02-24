package combate;

import personagem.Personagem;
import java.util.Scanner;
import personagem.Monstro;

public class Combate {
    private Scanner sc;

    public Combate(){
        this.sc = new Scanner(System.in);
    }

    public void batalha(Personagem personagem, Monstro monstro){
        System.out.println("Batalha iniciada: " + personagem.getNome() + " VS " + monstro.getNome());

        while (personagem.getVida_Atual() > 0 && monstro.getVida() > 0) {

            // Turno do heroi
            System.out.println("-------- SEU TURNO --------");
            System.out.println("[1] - ATACAR\n[2] - CURAR\n[3] - RECUPERAR MANA");
            int escolha = sc.nextInt();

            if(escolha == 1){
                System.out.println("Escolha o item que deseja usar para o ataque!");
                escolha = sc.nextInt();

                double dano = personagem.atacar(escolha);
                monstro.setVida(dano);

                System.out.println("O personagem " + personagem.getNome() + " Causou " + dano + " de dano!");

            }else if(escolha == 2){
                System.out.println("Escolha o item que deseja usar para se curar");
                escolha = sc.nextInt();
                
                double cura = personagem.curar(escolha);

                System.out.println("O personagem " + personagem.getNome() + " Curou " + cura + " de vida!");

            }else if (escolha == 3){
                System.out.println("Escolha o item que deseja usar para recuperar sua mana");

                escolha = sc.nextInt();

                double mana = personagem.regenerar(escolha);

                System.out.println("O personagem " + personagem.getNome() + " Recuperou " + mana + " de vida!");
            }

            if (monstro.getVida() <= 0){
                System.out.println("Parabens você venceu o Monstro!");
                // Implementar o drop de item
                // Implementar o ganho de experiencia

                return;
            }

            //turno do Monstro

            System.out.println(" Monstro ira atacar, se prepare!");
            double dano_Monstro = monstro.getDano();
            personagem.setVida_Atual(dano_Monstro);

            System.out.println("O monstro " + monstro.getNome() + " Causou " + dano_Monstro + " De dano!");

            if (personagem.getVida_Atual() <= 0){
                System.out.println("Seu personagem foi derrotado!");
                System.exit(0);
            }
        }
    }

}
