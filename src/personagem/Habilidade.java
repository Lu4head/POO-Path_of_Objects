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
        this.dano = Math.max(0, dano);
        this.mana = Math.max(0, mana);
        this.nivel = Math.max(1, nivel);
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

    public int getNivel(){
        return nivel;
    }
}
