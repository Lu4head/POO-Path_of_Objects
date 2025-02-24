package personagem;

import item.Item;
import item.Arma;
import item.Pocao;
import java.util.List;

public class Guerreiro extends Personagem {

    public Guerreiro(String nome, double vida, double mana, int nivel){
        super(nome, "Guerreiro", vida, mana);
    }

    //Metodo para atacar

    public double atacar(int escolha){
        List<Item> itens = getItens();

        if (escolha < 0 || escolha >= itens.size()){
            System.out.println("Posição escolhida não existe");
            return 0;
        }

        Item item = itens.get(escolha);

        if (item instanceof Arma) {
            Arma arma = (Arma) item; // faz o cast para obter o dano da arma
            return arma.getDano();
        }

        System.out.println("item selecionado não é uma arma");
        return 0;
    }

    // Metodo para curar 
    public double curar(int escolha){
        List<Item> itens = getItens();

        if (escolha < 0 || escolha >= itens.size()){
            System.out.println("Posição escolhida não existe");
            return 0;
        }

        Item item = itens.get(escolha);

        if (item instanceof Pocao){
            Pocao pocao = (Pocao) item; // faz o cast para obter a cura da pocao
            if( pocao.getCura() > 0){
                if (pocao.getCura() + getVida_Atual() > getVida()){
                    setVida_Atual(getVida()); // se caso o uso da poção + a vida atual, ultrapassar a vida maxima, só reseta a vida atual para a vida maxima
                }
                setVida_Atual(pocao.getCura() + getVida_Atual()); // se caso não for, apenas usa a poção de cura
                return pocao.getCura();
            }
            System.out.println("Poção selecionada é de mana");
            return 0;
        }

        System.out.println("item selecionado não é uma poção");
        return 0;
    }


    // Metodo para recuperar mana   
    public double regenerar(int escolha){
        List<Item> itens = getItens();

        if (escolha < 0 || escolha >= itens.size()){
            System.out.println("Posição escolhida não existe");
            return 0;
        }

        Item item = itens.get(escolha);

        if (item instanceof Pocao){
            Pocao pocao = (Pocao) item; // faz o cast para obter a regeneração da pocao
            if( pocao.getRegeneracao() > 0){
                if(pocao.getRegeneracao() + getMana_Atual() > getMana()){
                    setMana_Atual(getMana()); // se caso o uso da poção + a mana atual, ultrapassar a mana maxima, só reseta a mana atual para a mana maxima
                }
                setMana_Atual(pocao.getRegeneracao() + getMana_Atual()); // se caso não for, apenas usa a poção de mana
                return pocao.getRegeneracao();
            }
            System.out.println("Poção selecionada é de cura");
            return 0;
        }

        System.out.println("item selecionado não é uma poção");
        return 0;
    }

    // Metodo para usar habilidade 



}
