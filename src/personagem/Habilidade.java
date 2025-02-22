package personagem;

public class Habilidade {
    private String nome;
    private String descricao;
    private double dano;
    private double mana;
    private int nivel;

    // Construtor habilidade

    public Habilidade(String nome, String descricao, double dano, double mana, int nivel){
        this.nome = nome;
        this.descricao = descricao;
        this.dano = dano;
        this.mana = mana;
        this.nivel = nivel;
    }

    // Metodos get

    public String getNome(){
        return nome;
    }

    public String getDescricao(){
        return descricao;
    }

    public double getDano(){
        return dano;
    }

    public double getMana(){
        return mana;
    }

    public int Nivel(){
        return nivel;
    }
}
