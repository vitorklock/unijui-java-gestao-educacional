package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Mural extends JPanel {

    public Mural(MainWindow telaPrincipal, String tipoUsuario) {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // --- PAINEL SUPERIOR (CABEÇALHO) ---
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel tituloLabel = new JLabel("Sistema de Gestão Educacional");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(tituloLabel, BorderLayout.WEST);
        add(topPanel, BorderLayout.NORTH);

        // --- PAINEL CENTRAL (CONTEÚDO) ---
        JPanel mainContentPanel = new JPanel(new BorderLayout(10, 10));

        // --- PAINEL DE ATALHOS E POSTAGEM ---
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // PAINEL DE ATALHOS (BOTÕES)
        JPanel shortcutsPanel = new JPanel(new GridLayout(1, 3, 15, 15));
        shortcutsPanel.setBorder(BorderFactory.createTitledBorder("Acesso Rápido"));
        
        // --- LÓGICA DE ADAPTAÇÃO ---
        // AQUI A MÁGICA ACONTECE: CRIAMOS OS BOTÕES DEPENDENDO DO TIPO DE USUÁRIO
        if ("PROFESSOR".equals(tipoUsuario)) {
            shortcutsPanel.add(createShortcutButton("Gerenciar Alunos", "ALUNO", telaPrincipal));
            shortcutsPanel.add(createShortcutButton("Lançar Notas", "NOTAS", telaPrincipal));
            shortcutsPanel.add(createShortcutButton("Gerenciar Turmas", "TURMA", telaPrincipal));
        } else { // ALUNO
            shortcutsPanel.add(createShortcutButton("Minhas Notas", "NOTAS", telaPrincipal));
            shortcutsPanel.add(createShortcutButton("Arquivos", "ARQUIVOS", telaPrincipal));
            shortcutsPanel.add(createShortcutButton("Fórum", "FORUM", telaPrincipal));
        }
        centerPanel.add(shortcutsPanel);
        
        // PAINEL DE NOVA POSTAGEM
        if ("PROFESSOR".equals(tipoUsuario)) {
            JPanel newPostPanel = new JPanel(new BorderLayout(5, 5));
            newPostPanel.setBorder(BorderFactory.createTitledBorder("Escrever no Mural"));
            JTextArea newPostTextArea = new JTextArea("Compartilhe algo com a turma...");
            newPostPanel.add(new JScrollPane(newPostTextArea), BorderLayout.CENTER);

            // BOTÃO PUBLICAR CORRIGIDO
            JButton publicarButton = new JButton("Publicar");
            publicarButton.addActionListener(e -> {
                // Ação simples para confirmar que funciona
                JOptionPane.showMessageDialog(this, "Sua mensagem foi publicada no mural!");
                newPostTextArea.setText(""); // Limpa a caixa de texto
            });
            newPostPanel.add(publicarButton, BorderLayout.EAST);
            centerPanel.add(newPostPanel);
        }

        mainContentPanel.add(centerPanel, BorderLayout.NORTH);

        // --- FEED DE POSTS (COM SCROLL) ---
        JPanel feedPanel = new JPanel();
        feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
        feedPanel.setBorder(BorderFactory.createTitledBorder("Mural da Turma"));
        // Adicionando posts de exemplo
        for (int i = 1; i <= 10; i++) {
            JPanel post = createPostPanel("Professor Klock", "Aviso importante " + i + ": Acompanhem o material postado.");
            feedPanel.add(post);
        }
        mainContentPanel.add(new JScrollPane(feedPanel), BorderLayout.CENTER);

        add(mainContentPanel, BorderLayout.CENTER);

        // --- BOTÃO DE SAIR (LOGOUT) ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = new JButton("Sair");
        logoutButton.addActionListener(e -> telaPrincipal.trocarTela("LOGIN"));
        bottomPanel.add(logoutButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Método auxiliar para criar botões de atalho padronizados
    private JButton createShortcutButton(String text, String screenName, MainWindow tela) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(100, 80));
        button.addActionListener(e -> tela.trocarTela(screenName));
        return button;
    }

    // Método auxiliar para criar um painel de postagem
    private JPanel createPostPanel(String author, String content) {
        JPanel postPanel = new JPanel(new BorderLayout(5, 5));
        postPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY), 
            new EmptyBorder(15, 10, 15, 10)
        ));
        JLabel authorLabel = new JLabel(author);
        authorLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextArea contentArea = new JTextArea(content);
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        postPanel.add(authorLabel, BorderLayout.NORTH);
        postPanel.add(contentArea, BorderLayout.CENTER);
        return postPanel;
    }
}