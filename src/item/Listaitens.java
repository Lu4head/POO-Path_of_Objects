package item;
import java.util.ArrayList;
import java.util.List;

public class Listaitens {
    private static final List<Item> Itens_drop = new ArrayList<>();


    static{
        // Armas de teste
        Itens_drop.add(new Arma("Espada curta", "Teste", 1, 1, 10, 0));
        // Poções de teste
        Itens_drop.add(new Pocao("Poção de cura", "Teste", 1, 1, 10, 0));
    }

    
}
