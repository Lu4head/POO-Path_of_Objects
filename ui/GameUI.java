package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import personagem.Personagem;
import personagem.Monstro;
import item.Item;

public class GameUI {
    private JFrame frame;
    private JProgressBar lifeBar, enemyLifeBar;
    private JTextArea logArea;
    private JComboBox<String> attackBox, healBox, manaBox;
    private Personagem personagem;
    private Monstro monstro;
    private JButton attackButton, healButton, manaButton;

    public GameUI(Personagem personagem) {
        this.personagem = personagem;
        this.monstro = gerarNovoMonstro();

        frame = new JFrame("RPG - Batalha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(3, 1));

        // Painel do Jogador
        JPanel playerPanel = new JPanel();
        playerPanel.setBorder(BorderFactory.createTitledBorder("Jogador"));
        playerPanel.setLayout(new GridLayout(2, 1));
        lifeBar = new JProgressBar(0, (int) personagem.getVida());
        lifeBar.setValue((int) personagem.getVida_Atual());
        lifeBar.setStringPainted(true);
        playerPanel.add(new JLabel("Vida: "));
        playerPanel.add(lifeBar);

        // Painel do Inimigo
        JPanel enemyPanel = new JPanel();
        enemyPanel.setBorder(BorderFactory.createTitledBorder("Monstro"));
        enemyPanel.setLayout(new GridLayout(2, 1));
        enemyLifeBar = new JProgressBar(0, (int) monstro.getVida_max());
        enemyLifeBar.setValue((int) monstro.getVida_max());
        enemyLifeBar.setStringPainted(true);
        enemyPanel.add(new JLabel("Vida: "));
        enemyPanel.add(enemyLifeBar);

        // Log de Combate
        logArea = new JTextArea(10, 40);
        logArea.setEditable(false);
        logArea.setBackground(Color.BLACK);
        logArea.setForeground(Color.GREEN);
        JScrollPane logScrollPane = new JScrollPane(logArea);

        // Botões de Ação
        JPanel actionPanel = new JPanel();
        actionPanel.setBorder(BorderFactory.createTitledBorder("Ações"));
        attackButton = new JButton("Atacar");
        healButton = new JButton("Curar");
        manaButton = new JButton("Recuperar Mana");

        // Ajustando os JComboBox para exibir corretamente os itens
        attackBox = new JComboBox<>(personagem.getItens().stream().map(Item::   toString).toArray(String[]::new));
        healBox = new JComboBox<>(personagem.getItens().stream().map(Item::toString).toArray(String[]::new));
        manaBox = new JComboBox<>(personagem.getItens().stream().map(Item::toString).toArray(String[]::new));

        attackButton.addActionListener(e -> attack());
        healButton.addActionListener(e -> heal());
        manaButton.addActionListener(e -> recoverMana());

        actionPanel.add(new JLabel("Atacar com:"));
        actionPanel.add(attackBox);
        actionPanel.add(attackButton);
        actionPanel.add(new JLabel("Curar com:"));
        actionPanel.add(healBox);
        actionPanel.add(healButton);
        actionPanel.add(new JLabel("Mana com:"));
        actionPanel.add(manaBox);
        actionPanel.add(manaButton);

        // Adicionando tudo na tela
        mainPanel.add(playerPanel);
        mainPanel.add(enemyPanel);
        mainPanel.add(actionPanel);

        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(logScrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
        updateUI();
    }

    private void updateUI() {
        lifeBar.setValue((int) personagem.getVida_Atual());
        enemyLifeBar.setValue((int) monstro.getVida_max());
    }

    private void log(String message, Color color) {
        logArea.setForeground(color);
        logArea.append(message + "\n");
    }

    private void attack() {
        if (monstro.getVida_max() <= 0) {
            log("O monstro já está morto!", Color.RED);
            return;
        }

        int itemIndex = attackBox.getSelectedIndex();
        double dano = personagem.atacar(itemIndex);
        monstro.setVida_max(monstro.getVida_max() - dano);
        log("Você causou " + dano + " de dano!", Color.YELLOW);

        if (monstro.getVida_max() <= 0) {
            log("Você derrotou o monstro!", Color.GREEN);
            proximoMonstro();
            return;
        }

        updateUI();
        enemyTurn();
    }

    private void heal() {
        int itemIndex = healBox.getSelectedIndex();
        double cura = personagem.curar(itemIndex);
        if (cura > 0) {
            personagem.removerItem(personagem.getItens().get(itemIndex));
            log("Você curou " + cura + " de vida!", Color.CYAN);
            updateUI();
        }
        enemyTurn();
    }

    private void recoverMana() {
        int itemIndex = manaBox.getSelectedIndex();
        double mana = personagem.regenerar(itemIndex);
        if (mana > 0) {
            personagem.removerItem(personagem.getItens().get(itemIndex));
            log("Você recuperou " + mana + " de mana!", Color.BLUE);
            updateUI();
        }
        enemyTurn();
    }

    private void enemyTurn() {
        if (monstro.getVida_max() <= 0) return;

        double dano = monstro.getDano();
        personagem.setVida_Atual(personagem.getVida_Atual() - dano);
        log("O monstro causou " + dano + " de dano!", Color.RED);

        if (personagem.getVida_Atual() <= 0) {
            log("Você foi derrotado!", Color.RED);
            JOptionPane.showMessageDialog(frame, "Game Over!");
            System.exit(0);
        }
        updateUI();
    }

    private void proximoMonstro() {
        int escolha = JOptionPane.showConfirmDialog(frame, "Você derrotou o monstro! Quer enfrentar outro?", "Novo Desafio", JOptionPane.YES_NO_OPTION);
        if (escolha == JOptionPane.YES_OPTION) {
            this.monstro = gerarNovoMonstro();
            log("Novo monstro apareceu: " + monstro.getNome() + "!", Color.ORANGE);
            updateUI();
        } else {
            JOptionPane.showMessageDialog(frame, "Você saiu vitorioso da batalha!");
            System.exit(0);
        }
    }

    private Monstro gerarNovoMonstro() {
        Random random = new Random();
        String[] nomes = {"Goblin", "Orc", "Lobo Selvagem", "Esqueleto", "Dragão Jovem"};
        String nome = nomes[random.nextInt(nomes.length)];
        double vida = 30 + random.nextInt(50);
        double dano = 5 + random.nextInt(20);
        int level = 1 + random.nextInt(5);
        return new Monstro(nome, vida, level, level, dano);
    }
}
