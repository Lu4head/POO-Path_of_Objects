package personagem;

import item.Item;
import item.Arma;
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

    

    // Metodo para usar habilidade 



}
