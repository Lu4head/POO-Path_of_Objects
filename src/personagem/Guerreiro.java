package personagem;

import item.Item;
import item.Listaitens;
import item.Arma;
import java.util.List;

public class Guerreiro extends Personagem {

    public Guerreiro(String nome){
        super(nome, "Guerreiro", 100, 0);
        List<Item> itens = Listaitens.getItensIniciaisGuerreiro();
        getItens().addAll(itens);
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
