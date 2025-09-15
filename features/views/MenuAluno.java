package views;

import javax.swing.*;
import java.awt.*;

public class MenuAluno extends JPanel {

    public MenuAluno(TelaPrincipal telaPrincipal) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Painel do Aluno", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel menuGrid = new JPanel(new GridLayout(2, 2, 15, 15));
        menuGrid.add(createMenuButton("Mural", "MURAL", telaPrincipal));
        menuGrid.add(createMenuButton("Minhas Notas", "NOTAS", telaPrincipal));
        menuGrid.add(createMenuButton("Minha Frequência", "FREQUENCIA", telaPrincipal));
        menuGrid.add(createMenuButton("Arquivos", "ARQUIVOS", telaPrincipal));
        add(menuGrid, BorderLayout.CENTER);

        JButton logoutButton = new JButton("Sair");
        logoutButton.addActionListener(e -> telaPrincipal.trocarTela("LOGIN"));
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.add(logoutButton);
        add(southPanel, BorderLayout.SOUTH);
    }
    
    private JButton createMenuButton(String text, String screenName, TelaPrincipal tela) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.addActionListener(e -> tela.trocarTela(screenName));
        return button;
    }
}