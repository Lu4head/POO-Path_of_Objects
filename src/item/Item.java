package item;

public class Item {
    private String nome;
    private String descricao;
    private double dano;
    private int nivel;
    private double pocao;
    private double mana;
    private double custo;
    
    // Construtores
     // Construtor dano
    public Item(String nome, String descricao, double dano, double mana, int nivel, double custo){
        this.nome = nome;
        this.descricao = descricao;
        this.dano = dano;
        this.nivel = nivel;
        this.pocao = 0;
        this.mana = mana;
        this.custo = custo;
    }

     // Construtor pocao
    public Item(String nome, String descricao, double pocao, int nivel, double custo){
        this.nome = nome;
        this.descricao = descricao;
        this.dano = 0;
        this.nivel = nivel;
        this.pocao = pocao;
        this.mana = 0;
        this.custo = custo;
    }

    //Criação dos get
    public String getNome(){
        return nome;
    }

    public String getDescricao(){
        return descricao;
    }

    public double getDano(){
        return dano;
    }

    public int getNivel(){
        return nivel;
    }

    public double getPocao(){
        return pocao;
    }

    public double getMana(){
        return mana;
    }

    public double getCusto(){
        return custo;
    }

}
