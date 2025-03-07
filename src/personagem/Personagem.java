package personagem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import habilidade.Habilidade;
import habilidade.ListaHabilidades;
import item.Item;
import item.Pocao;

public abstract class Personagem {

    // Criação de campos privados do personagem

    protected String nome;
    private double vida;
    private double vida_atual;
    private double mana;
    private double mana_atual;
    private int nivel;
    private int experiencia_necessaria;
    private int experiencia_atual;
    private List<Habilidade> habilidades = new ArrayList<>();
    private String classe;
    private Inventario inventario;
    private double moedas; // tem que fazer
    private double buff_dano;

    // Construtor o personagem (falta a habilidade)
    public Personagem(String nome, String classe, double vida, double mana){
        this.nome = nome;
        this.nivel = 1;
        this.classe = classe;
        this.vida = Math.max(0, vida);
        this.vida_atual = vida;
        this.mana = Math.max(0, nivel);
        this.mana_atual = mana;
        this.inventario = new Inventario();
        this.moedas = 0;
        this.experiencia_necessaria = nivel * 100;
        this.experiencia_atual = 0;
        this.buff_dano = 0;
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

     public double getMoedas(){
        return moedas;
     }

      public int getExperiencia_necessaria(){
         return experiencia_necessaria;
      }

      public int getExperiencia_atual(){
         return experiencia_atual;
      }

      public double getBuff_dano(){
        return buff_dano;
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

     public void setMoedas(double moedas){
        this.moedas = moedas;
     }

     public void setVida(double vida){
        this.vida = vida;
     }

     public void setMana(double mana){
        this.mana = mana;
     }

     public void setExperiencia_atual(int experiencia_atual){
        this.experiencia_atual = experiencia_atual;
        if (this.experiencia_atual >= this.experiencia_necessaria){
            subirNivel();
        }
     }

     public void setBuff_dano(double buff_dano){
        this.buff_dano = buff_dano;
     }

     // Metodos abstratos que outras classes vão implementar e usar
     public abstract double atacar(int escolha);

     // Metodos para combate

     // Metodo para curar 
      public double curar(int escolha){
        List<Item> itens = getItens();

        if (escolha < 0 || escolha >= itens.size()){
            System.out.println("Posição escolhida não existe");
            return 0;
        }

        Item item = itens.get(escolha);

        if (item instanceof Pocao){
            Pocao pocao = (Pocao) item; // faz o cast para obter a cura da pocao
            if( pocao.getCura() > 0){
                if (pocao.getCura() + getVida_Atual() > getVida()){
                    setVida_Atual(getVida()); // se caso o uso da poção + a vida atual, ultrapassar a vida maxima, só reseta a vida atual para a vida maxima
                }
                else {
                    setVida_Atual(pocao.getCura() + getVida_Atual()); // se caso não for, apenas usa a poção de cura
                }
                return pocao.getCura();
            }
            System.out.println("Poção selecionada é de mana");
            return 0;
        }

        System.out.println("item selecionado não é uma poção");
        return 0;
      } 


      // Metodo para recuperar mana   
      public double regenerar(int escolha){
        List<Item> itens = getItens();

        if (escolha < 0 || escolha >= itens.size()){
            System.out.println("Posição escolhida não existe");
            return 0;
        }

        Item item = itens.get(escolha);

        if (item instanceof Pocao){
            Pocao pocao = (Pocao) item; // faz o cast para obter a regeneração da pocao
            if( pocao.getRegeneracao() > 0){
                if(pocao.getRegeneracao() + getMana_Atual() > getMana()){
                    setMana_Atual(getMana()); // se caso o uso da poção + a mana atual, ultrapassar a mana maxima, só reseta a mana atual para a mana maxima
                } 
                else {
                    setMana_Atual(pocao.getRegeneracao() + getMana_Atual()); // se caso não for, apenas usa a poção de mana
                }
                return pocao.getRegeneracao();
            }
            System.out.println("Poção selecionada é de cura");
            return 0;
        }

        System.out.println("item selecionado não é uma poção");
        return 0;
      }

     
     // Metodos para gerenciar o iventario

     public void adicionarItem(Item item){
         inventario.adicionaritem(item);
     }

     public void removerItem(Item item){
      inventario.removeritem(item);
     }

     public List<Item> getItens(){
      return inventario.getItens();
     }
     
     // Metodo para subri de nivel
     public void subirNivel(){
         this.nivel += 1;
         // Calcula diferença de EXP
         this.experiencia_atual = this.experiencia_atual - this.experiencia_necessaria;
         // Calcula nova EXP necessária
         this.experiencia_necessaria = this.experiencia_necessaria * (50 / 100);
         // Aumenta vida e mana em 50% e cura e recupera mana atuais
         this.vida = this.vida * 1.5;
         this.vida_atual = this.vida;
         this.mana = this.mana * 1.5;
         this.mana_atual = this.mana;

         System.out.println("Parabéns, você subiu de nível! Seu nível agora é " + this.nivel);
         System.out.println("Sua vida e mana foram aumentadas em 50% e foram totalmente recuperadas");
         System.out.println("Sua experiência atual é " + this.experiencia_atual + " e a necessária para o próximo nível é " + this.experiencia_necessaria);
         System.out.println("Novos status - Vida: " + this.vida + ", Mana: " + this.mana);

         
      }

      public void ganharNovaHabilidade(){
         System.out.println("Parabéns, você ganhou uma nova habilidade!");
         System.out.println("Qual habilidade você deseja escolher?");
         List<Habilidade> habilidadesSorteadas = ListaHabilidades.sortearHabilidades();
         int escolha = 0;
         while(escolha < 1 || escolha > habilidadesSorteadas.size()){
            exibirHabilidades(habilidadesSorteadas);
            System.out.println("Digite o número da habilidade que deseja escolher:");
            escolha = obterEscolhaUsuario(1, habilidadesSorteadas.size());
         }
         this.habilidades.add(habilidadesSorteadas.get(escolha - 1));
      }

      private void exibirHabilidades(List<Habilidade> habilidades) {
         for (int i = 0; i < habilidades.size(); i++) {
            System.out.println((i + 1) + " - " + habilidades.get(i).getNome() + " - " + habilidades.get(i).getDescricao());
         }
      } 

      private int obterEscolhaUsuario(int min, int max) {
         try (Scanner scanner = new Scanner(System.in)) {
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


      public void exibirInventario() {
         System.out.println(" Inventário: ");
     
         List<Item> itens = getItens();  // Obtendo a lista de itens
     
         if (itens.isEmpty()) {
             System.out.println("  - (Vazio)");
             return;
         }
     
         for (int i = 0; i < itens.size(); i++) {
             System.out.println("  [" + i + "] " + itens.get(i).getNome());
         }
      }

     public Habilidade usarHabilidade(){
         System.out.println("Qual habilidade você deseja usar?");
         int escolha = 0;
         while(escolha < 1 || escolha > habilidades.size()){
            for (int i = 0; i < habilidades.size(); i++) {
               System.out.println( (i + 1) + " - " + habilidades.get(i).getNome() + " - " + habilidades.get(i).getDescricao());
            }
            System.out.println("Digite o número da habilidade que deseja usar:");
            escolha = obterEscolhaUsuario(1, habilidades.size());
         }
         return habilidades.get(escolha - 1);
     }
}
