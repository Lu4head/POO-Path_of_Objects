package eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import item.Item;
import item.Listaitens;
import personagem.Personagem;

public class Loja {
    private List<Item> itens = new ArrayList<>();
    private Scanner scanner;

    public Loja(){
        scanner = new Scanner(System.in);
        itens = Listaitens.getItensLoja();
    }

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

    public void comprar(Personagem jogador){
        System.out.println("Escolha um item para comprar:");
        mostrarItens();
        int escolha = obterEscolhaUsuario(0, itens.size() - 1);
        Item item = itens.get(escolha);
        if (jogador.getMoedas() >= item.getCusto()){
            jogador.setMoedas(jogador.getMoedas() - item.getCusto());
            jogador.adicionarItem(item);
            System.out.println("Você comprou " + item.getNome() + " por " + item.getCusto() + " moedas.");
        } else {
            System.out.println("Você não tem moedas suficientes para comprar este item.");
        }
    }

    public void vender(Personagem jogador){
        System.out.println("Escolha um item para vender:");
        mostrarItens();
        int escolha = obterEscolhaUsuario(0, itens.size() - 1);
        Item item = itens.get(escolha);
        if (jogador.getItens().contains(item)){
            jogador.removerItem(item);
            jogador.setMoedas(jogador.getMoedas() + item.getCusto());
            System.out.println("Você vendeu " + item.getNome() + " por " + item.getCusto() + " moedas.");
        } else {
            System.out.println("Você não tem este item para vender.");
        }
    }

    public void acessar(Personagem jogador){
        System.out.println("Bem-vindo à loja!");
        while(true){
            System.out.println("Itens disponíveis:");
            mostrarItens();
            System.out.println("Escolha uma opção:");
            System.out.println("[1] Comprar");
            System.out.println("[2] Vender");
            System.out.println("[3] Sair");
            int escolha = obterEscolhaUsuario(1, 3);
            if (escolha == 1) {
                comprar(jogador);
            } else if (escolha == 2) {
                vender(jogador);
            } else {
                System.out.println("Saindo da loja.");
                return;
            }
        }

    }

    private int obterEscolhaUsuario(int min, int max) {
        int escolha;
        while (true) {
            System.out.print("Escolha: ");
            if (scanner.hasNextInt()) {
                escolha = scanner.nextInt();
                if (escolha >= min && escolha <= max) {
                    return escolha;
                }
            }
            scanner.nextLine(); // Limpa buffer
            System.out.println("\033[1;31mOpção inválida! Tente novamente.\033[0m");
        }
    }
}