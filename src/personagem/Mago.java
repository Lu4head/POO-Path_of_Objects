package personagem;

import java.util.List;

import item.Arma;
import item.Item;
import item.Listaitens;

public class Mago extends Personagem {
    
    public Mago(String nome){
        super(nome, "Mago", 100, 50);
        List<Item> itens = Listaitens.getItensIniciaisMago();
        getItens().addAll(itens);
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
            return arma.getDano() * this.getBuff_dano();
        }
        System.out.println("item selecionado não é uma arma");
        return 0;
    }

    
    // Metodo para usar habilidade 

}
