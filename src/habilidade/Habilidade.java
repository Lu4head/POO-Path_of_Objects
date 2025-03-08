package habilidade;


public abstract class Habilidade {
    private String nome;
    private String descricao;
    private double custo_mana;
    private int nivel;

    // Construtor habilidade

    public Habilidade(String nome, String descricao, double mana, int nivel){
        this.nome = nome;
        this.descricao = descricao;
        this.custo_mana = Math.max(0, mana);
        this.nivel = Math.max(1, nivel);
    }

    // Metodos get

    public String getNome(){
        return nome;
    }

    public String getDescricao(){
        return descricao;
    }

    public double getCusto_mana(){
        return custo_mana;
    }

    public int getNivel(){
        return nivel;
    }

}
