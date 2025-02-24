package eventos;

import java.util.ArrayList;
import java.util.List;

import item.Item;

public class Loja {
    private List<Item> itens = new ArrayList<>();

    // get e set
    public void setItens(List<Item> itens){
        this.itens = itens;
    }

    public List<Item> getItens(){
        return itens;
    }

    // Metodos
    public void adicionaritem(Item item){
        itens.add(item);
    }

    public void removeritem(Item item){
        itens.remove(item);
    }

    public void mostrarItens(){
        for (int i = 0; i < itens.size(); i++){
            System.out.println(i + ". " + itens.get(i).getNome() + " - " + itens.get(i).getCusto() + " moedas.");
        }
    }

}
