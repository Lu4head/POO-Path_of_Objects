package item;

public abstract class Item {
    private String nome;
    private String descricao;
    private int nivel;
    private double custo;
    
    // Construtores
     // Construtor dano
    public Item(String nome, String descricao, int nivel, double custo){
        this.nome = nome;
        this.descricao = descricao;
        this.nivel = nivel;
        this.custo = custo;
    }

    //Criação dos get
    public String getNome(){
        return nome;
    }

    public String getDescricao(){
        return descricao;
    }

    public int getNivel(){
        return nivel;
    }

    public double getCusto(){
        return custo;
    }

    @Override
    public String toString() {
        return this.nome; // Ou qualquer outra descrição que você queira
    }
}
