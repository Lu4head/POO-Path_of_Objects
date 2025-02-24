package personagem;
import java.util.ArrayList;
import java.util.List;
import item.Item;

public abstract class Personagem {
    // Criação de campos privados do personagem
    protected String nome;
    private double vida;
    private double vida_atual;
    private double mana;
    private double mana_atual;
    private int nivel;
    private List<Habilidade> habilidades = new ArrayList<>();
    private String classe;
    private Iventario iventario;

    // Construtor o personagem (falta a habilidade)
    public Personagem(String nome, String classe, double vida, double mana){
        this.nome = nome;
        this.nivel = 1;
        this.classe = classe;
        this.vida = Math.max(0, vida);
        this.vida_atual = vida;
        this.mana = Math.max(0, nivel);
        this.mana_atual = mana;
        this.iventario = new Iventario();
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

     public double getVida_Atual(){
      return vida_atual;
     }

     public double getMana(){
        return mana;
     }

     public double getMana_Atual(){
      return mana_atual;
     }

     public List<Habilidade> getHabilidades(){
        return habilidades;
     }

     // Metodos setter (para mudar informações quando necessário)

     public void setNivel(int nivel){
        this.nivel = nivel;
     }

     public void setVida_Atual(double vida_atual){
        this.vida_atual = Math.max(0,vida_atual);
     }

     public void setMana_Atual(double mana_atual){
        this.mana_atual = Math.max(0,mana_atual);
     }

     public void setHabilidade(Habilidade habilidade){
        this.habilidades.add(habilidade);
     }

     // Metodos abstratos que outras classes vão implementar e usar
     //public abstract void atacar();

     // Metodos para gerenciar o iventario

     public void adicionarItem(Item item){
         iventario.adicionaritem(item);
     }

     public void removerItem(Item item){
      iventario.removeritem(item);
     }

     public List<Item> getItens(){
      return iventario.getItens();
     }
     

    


}
