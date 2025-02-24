package personagem;

import java.util.ArrayList;
import java.util.List;
import item.Item;

public class Inventario {
    private List<Item> itens = new ArrayList<>();


    public void adicionaritem(Item item){
        itens.add(item);
    }

    public void removeritem(Item item){
        itens.remove(item);
    }

    // get 

    public List<Item> getItens(){
        return itens;
    }
}
