package personagem;

import java.util.List;

import item.Arma;
import item.Item;

public class Mago extends Personagem {
    
    public Mago(String nome, double vida, double mana, int nivel){
        super(nome, "Mago", vida, mana);
    }

    // Metodo de atacar
    public double atacar(int escolha){
        List<Item> itens = getItens();

        if (escolha < 0 || escolha >= itens.size()){
            System.out.println("Posição escolhida não existe");
            return 0;
        }

        Item item = itens.get(escolha);

        if (item instanceof Arma) {
            Arma arma = (Arma) item; // faz o cast para obter o dano da arma
            if(getMana_Atual() <= arma.getMana()){
                System.out.println("Não é possivel usar o item, sem mana suficiente!");
                return 0;
            }
            setMana_Atual(getMana() - arma.getMana());
            return arma.getDano();
        }
        System.out.println("item selecionado não é uma arma");
        return 0;
    }

    
    // Metodo para usar habilidade 

}
