package personagem;
import java.util.ArrayList;
import java.util.List;

public abstract class Personagem {
    // Criação de campos privados do personagem
    protected String nome;
    private double vida;
    private double mana;
    private int nivel;
    private List<Habilidade> habilidades = new ArrayList<>();
    private String classe;

    // Construtor o personagem (falta a habilidade)
    public Personagem(String nome, String classe, double vida, double mana){
        this.nome = nome;
        this.nivel = 1;
        this.classe = classe;
        this.vida = vida;
        this.mana = mana;
    }  

    // Metodos get (para obter acesso a informações)
     public String getNome(){
        return nome;
     }

     public int getNivel(){
        return nivel;
     }

     public String getClasse(){
        return classe;
     }

     public double getVida(){
        return vida;
     }

     public double getMana(){
        return mana;
     }

     public List<Habilidade> getHabilidades(){
        return habilidades;
     }

     // Metodos setter (para mudar informações quando necessário)

     public void setNivel(int nivel){
        this.nivel = nivel;
     }

     public void setVida(double vida){
        this.vida = vida;
     }

     public void setMana(double mana){
        this.mana = mana;
     }

     public void setHabilidade(Habilidade habilidade){
        this.habilidades.add(habilidade);
     }

     // Metodos abstratos que outras classes vão implementar e usar
     
     public abstract void atacar();

     

    


}
