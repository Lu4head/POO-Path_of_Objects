package personagem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import habilidade.Habilidade;
import habilidade.Habilidade_Buff;
import habilidade.Habilidade_Ofensiva;
import habilidade.Habilidade_Recuperacao;
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
   private Scanner sc;

   // Construtor o personagem (falta a habilidade)
   public Personagem(String nome, String classe, double vida, double mana) {
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
      this.buff_dano = 1;
      this.sc = new Scanner(System.in);
   }

   // Metodos get (para obter acesso a informações)
   public String getNome() {
      return nome;
   }

   public int getNivel() {
      return nivel;
   }

   public String getClasse() {
      return classe;
   }

   public double getVida() {
      return vida;
   }

   public double getVida_Atual() {
      return vida_atual;
   }

   public double getMana() {
      return mana;
   }

   public double getMana_Atual() {
      return mana_atual;
   }

   public List<Habilidade> getHabilidades() {
      return habilidades;
   }

   public double getMoedas() {
      return moedas;
   }

   public int getExperiencia_necessaria() {
      return experiencia_necessaria;
   }

   public int getExperiencia_atual() {
      return experiencia_atual;
   }

   public double getBuff_dano() {
      return buff_dano;
   }


   // Metodos setter (para mudar informações quando necessário)

   public void setNivel(int nivel) {
      this.nivel = nivel;
   }

   public void setVida_Atual(double vida_atual) {
      this.vida_atual = Math.max(0, vida_atual);
   }

   public void setMana_Atual(double mana_atual) {
      this.mana_atual = Math.max(0, mana_atual);
   }

   public void setHabilidade(Habilidade habilidade) {
      this.habilidades.add(habilidade);
   }

   public void setMoedas(double moedas) {
      this.moedas = moedas;
   }

   public void setVida(double vida) {
      this.vida = vida;
   }

   public void setMana(double mana) {
      this.mana = mana;
   }

   public void setExperiencia_atual(int experiencia_atual) {
      this.experiencia_atual = experiencia_atual;
      if (this.experiencia_atual >= this.experiencia_necessaria) {
         subirNivel();
      }
   }

   public void setBuff_dano(double buff_dano) {
      this.buff_dano = buff_dano;
   }

   // Metodos abstratos que outras classes vão implementar e usar
   public abstract double atacar(int escolha);


   // Metodo para curar
   public double curar(int escolha) {
      List<Item> itens = getItens();

      if (escolha < 0 || escolha >= itens.size()) {
         System.out.println("Posição escolhida não existe");
         return 0;
      }

      Item item = itens.get(escolha);

      if (item instanceof Pocao) {
         Pocao pocao = (Pocao) item; // faz o cast para obter a cura da pocao
         if (pocao.getCura() > 0) {
            if (pocao.getCura() + getVida_Atual() > getVida()) {
               setVida_Atual(getVida()); // se caso o uso da poção + a vida atual, ultrapassar a vida maxima, só reseta
                                         // a vida atual para a vida maxima
            } else {
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
   public double regenerar(int escolha) {
      List<Item> itens = getItens();

      if (escolha < 0 || escolha >= itens.size()) {
         System.out.println("Posição escolhida não existe");
         return 0;
      }

      Item item = itens.get(escolha);

      if (item instanceof Pocao) {
         Pocao pocao = (Pocao) item; // faz o cast para obter a regeneração da pocao
         if (pocao.getRegeneracao() > 0) {
            if (pocao.getRegeneracao() + getMana_Atual() > getMana()) {
               setMana_Atual(getMana()); // se caso o uso da poção + a mana atual, ultrapassar a mana maxima, só reseta
                                         // a mana atual para a mana maxima
            } else {
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

   public void adicionarItem(Item item) {
      inventario.adicionaritem(item);
   }

   public void removerItem(Item item) {
      inventario.removeritem(item);
   }

   public List<Item> getItens() {
      return inventario.getItens();
   }

   // Metodo para subri de nivel
   public void subirNivel() {
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
      ganharNovaHabilidade();

      System.out.println("Parabéns, você subiu de nível! Seu nível agora é " + this.nivel);
      System.out.println("Sua vida e mana foram aumentadas em 50% e foram totalmente recuperadas");
      System.out.println("Sua experiência atual é " + this.experiencia_atual + " e a necessária para o próximo nível é "
            + this.experiencia_necessaria);
      System.out.println("Novos status - Vida: " + this.vida + ", Mana: " + this.mana);

   }

   private void ganharNovaHabilidade() {
      System.out.println(" Parabéns! Você ganhou uma nova habilidade!");
      System.out.println("Escolha uma das habilidades abaixo:");

      List<Habilidade> habilidadesSorteadas = ListaHabilidades.sortearHabilidades();
      exibirHabilidadesSorteadas(habilidadesSorteadas);

      int escolha = lerEscolha(1, habilidadesSorteadas.size());
      Habilidade novaHabilidade = habilidadesSorteadas.get(escolha - 1);
      this.habilidades.add(novaHabilidade);
      if (novaHabilidade instanceof Habilidade_Buff){
         Habilidade_Buff habilidade_Buff = (Habilidade_Buff) novaHabilidade;
         habilidade_Buff.ApplyBuff(this);
      }

      System.out.println(" Você aprendeu: " + novaHabilidade.getNome() + " - " + novaHabilidade.getDescricao());
   }

   private void exibirHabilidadesSorteadas(List<Habilidade> habilidades) {
      for (int i = 0; i < habilidades.size(); i++) {
         System.out.println((i + 1) + " - " + habilidades.get(i).getNome() + " - " + habilidades.get(i).getDescricao());
      }
   }

   public void exibirHabilidades(){
      System.out.println("\n🌀 Suas Habilidades 🌀");
      System.out.println("-----------------------------------");

      for (int i = 0; i < habilidades.size(); i++) {
         System.out.print("[" + i + "] " + habilidades.get(i).getNome() + "  ");
         if ((i + 1) % 3 == 0) { // Quebra de linha a cada 3 habilidades
            System.out.println();
         }
      }
      System.out.println("\n-----------------------------------");
   }

   private int lerEscolha(int min, int max) {
      int escolha;
      while (true) {
         if (sc.hasNextInt()) {
            escolha = sc.nextInt();
            if (escolha >= min && escolha <= max) {
               sc.nextLine(); // Limpa buffer
               return escolha;
            } else {
               System.out.println("\033[1;31mEscolha fora do intervalo! Tente novamente.\033[0m");
            }
         } else {
            System.out.println("\033[1;31mEntrada inválida! Digite um número.\033[0m");
            sc.next(); // Descarta entrada inválida
         }
      }
   }

   public void exibirInventario() {
      List<Item> itens = getItens(); // Obtendo a lista de itens
      if (itens.isEmpty()) {
         System.out.println("\n🔹 Inventário: (Vazio) 🔹");
         return;
      }

      // Ordena os itens pelo nome (opcional)
      itens.sort((a, b) -> a.getNome().compareToIgnoreCase(b.getNome()));

      Scanner scanner = new Scanner(System.in);
      int pagina = 0;
      int itensPorPagina = 5;
      int totalPaginas = (int) Math.ceil((double) itens.size() / itensPorPagina);

      while (true) {
         System.out.printf("\nInventário (Página %d de %d)\n", pagina + 1, totalPaginas);

         int inicio = pagina * itensPorPagina;
         int fim = Math.min(inicio + itensPorPagina, itens.size());

         for (int i = inicio; i < fim; i++) {
            System.out.printf("  [%d] %s\n", i, itens.get(i).getNome());
         }

         System.out.println("\n[ N ] Próxima | [ P ] Anterior | [ S ] Sair");
         System.out.print("Opção: ");
         String opcao = scanner.nextLine().trim().toLowerCase();

         if (opcao.equals("n") && pagina < totalPaginas - 1) {
            pagina++;
         } else if (opcao.equals("p") && pagina > 0) {
            pagina--;
         } else if (opcao.equals("s")) {
            break;
         }
      }
   }

   public double usarHabilidade(int escolha) {
      Habilidade habilidadeEscolha = habilidades.get(escolha);

      if (habilidadeEscolha instanceof Habilidade_Ofensiva){
         Habilidade_Ofensiva habilidade_Ofensiva = (Habilidade_Ofensiva) habilidadeEscolha;
         return habilidade_Ofensiva.getDano();
      }

      if (habilidadeEscolha instanceof Habilidade_Recuperacao){
         Habilidade_Recuperacao habilidade_Recuperacao = (Habilidade_Recuperacao) habilidadeEscolha;
         habilidade_Recuperacao.usarHabilidade(this);
         return 0;
      }

      if (habilidadeEscolha instanceof Habilidade_Buff){
         System.out.println("Habilidade passiva");
         return 0;
      }
      return 0;
   }
}
