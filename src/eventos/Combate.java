package eventos;

import personagem.Personagem;
import java.util.Scanner;
import personagem.Monstro;
import item.Item;

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
            
            // Exibe inventário
            System.out.println("Inventário: ");
            personagem.getItens();

            System.out.println("[1] - ATACAR\n[2] - CURAR\n[3] - RECUPERAR MANA");
            int escolha = sc.nextInt();

            // Atacar
            if(escolha == 1){
                System.out.println("Escolha o item que deseja usar para o ataque!");
                escolha = sc.nextInt();

                double dano = personagem.atacar(escolha);
                monstro.setVida(monstro.getVida() - dano);

                System.out.println("O personagem " + personagem.getNome() + " Causou " + dano + " de dano!");
            
            // Curar
            }else if(escolha == 2){
                System.out.println("Escolha o item que deseja usar para se curar");
                escolha = sc.nextInt();
                
                double cura = personagem.curar(escolha);
                
                if (cura > 0){
                    personagem.removerItem(personagem.getItens().get(escolha));
                    continue;
                }

                System.out.println("O personagem " + personagem.getNome() + " Curou " + cura + " de vida!");
            
            // Recuperar mana
            }else if (escolha == 3){
                System.out.println("Escolha o item que deseja usar para recuperar sua mana");

                escolha = sc.nextInt();

                double mana = personagem.regenerar(escolha);

                if (mana > 0){
                    personagem.removerItem(personagem.getItens().get(escolha));
                    continue;
                }

                System.out.println("O personagem " + personagem.getNome() + " Recuperou " + mana + " de vida!");
            }

            if (monstro.getVida() <= 0){
                System.out.println("Parabens você venceu o Monstro!");

                int exp_ganha = monstro.darExperiencia();
                Item drop_item = monstro.dropsItem();

                System.out.println("Você ganhou " + exp_ganha + " de experiencia e um item: " + drop_item.getNome());

                personagem.setExperiencia_atual(personagem.getExperiencia_atual() + exp_ganha);
                personagem.adicionarItem(drop_item);

                return;
            }

            //turno do Monstro

            System.out.println(" Monstro ira atacar, se prepare!");
            double dano_Monstro = monstro.getDano();
            personagem.setVida_Atual(personagem.getVida_Atual() - dano_Monstro);

            System.out.println("O monstro " + monstro.getNome() + " Causou " + dano_Monstro + " De dano!");

            if (personagem.getVida_Atual() <= 0){
                System.out.println("Seu personagem foi derrotado!");
                System.exit(0);
            }
        }
    }

}
