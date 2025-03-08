package item;

import java.util.ArrayList;
import java.util.List;

public class Listaitens {
    private static final List<Item> itens_drop = new ArrayList<>();
    private static final List<Item> itens_loja = new ArrayList<>();
    private static final List<Item> itens_iniciais_guerreiro = new ArrayList<>();
    private static final List<Item> itens_iniciais_mago = new ArrayList<>();

    static {
        // Itens Dropáveis
        itens_drop.add(new Arma("Espada Curta", "Uma espada leve e ágil", 1, 10, 0));
        itens_drop.add(new Pocao("Poção de Cura", "Restaura 10 de vida",  1, 10, 0));
        itens_drop.add(new Arma("Cajado Antigo", "Um cajado enfraquecido pelo tempo",  1, 8, 5));
        itens_drop.add(new Pocao("Poção de Mana", "Restaura 10 de mana",  1, 0, 10));

        // Itens da Loja
        itens_loja.add(new Arma("Espada Longa", "Uma lâmina poderosa", 5, 20, 0));
        itens_loja.add(new Arma("Varinha Antiga", "Um cajado enfraquecido pelo tempo",  5, 8, 5));
        itens_loja.add(new Arma("Espada lendaria do Lojista", "Da época em que ele ainda era um herói", 100, 100, 0));
        itens_loja.add(new Arma("Cajado do Mago Implacável", "Quando ele encontrou o Mar Morto este ainda não tinha esse nome...", 100, 150, 25 ));
        itens_loja.add(new Pocao("Poção Maior de Cura", "Restaura 25 de vida",  7, 25, 0));
        itens_loja.add(new Pocao("Poção Maior de Mana", "Restaura 25 de mana",  7, 0, 25));
        itens_loja.add(new Pocao("Pocao Pequena de Cura", "Restaura 5 de vida",  3, 5, 0));
        itens_loja.add(new Pocao("Pocao Pequena de Mana", "Restaura 5 de mana",  3, 0, 5));

        // Itens Iniciais do Mago
        itens_iniciais_mago.add(new Arma("Cajado de Aprendiz", "Um cajado simples",  1, 6, 10));
        itens_iniciais_mago.add(new Pocao("Poção Pequena de Mana", "Restaura 5 de mana",  1, 0, 5));

        // Itens Iniciais do Guerreiro
        itens_iniciais_guerreiro.add(new Arma("Adaga de Treinamento", "Uma lâmina curta",  1, 20
        , 0));
        itens_iniciais_guerreiro.add(new Pocao("Poção Pequena de Cura", "Restaura 5 de vida",  1, 5, 0));
    }

    // Métodos para acessar os itens
    public static List<Item> getItensDrop() {
        return itens_drop;
    }

    public static List<Item> getItensLoja() {
        return itens_loja;
    }

    public static List<Item> getItensIniciaisMago() {
        return itens_iniciais_mago;
    }

    public static List<Item> getItensIniciaisGuerreiro() {
        return itens_iniciais_guerreiro;
    }
}
