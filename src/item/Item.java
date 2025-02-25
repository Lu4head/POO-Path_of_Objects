package item;

public abstract class Item {
    private String nome;
    private String descricao;
    private double custo;
    
    // Construtores
    
    public Item(String nome, String descricao, double custo){
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
    }

    //Criação dos get
    public String getNome(){
        return nome;
    }

    public String getDescricao(){
        return descricao;
    }

    public double getCusto(){
        return custo;
    }

    @Override
    public String toString() {
        return this.nome; // Ou qualquer outra descrição que você queira
    }
}
