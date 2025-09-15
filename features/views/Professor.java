package views;

import javax.swing.*;
import java.awt.*;

public class Professor extends JPanel {
    public Professor(MainWindow telaPrincipal) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Dados do Professor", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        JTextArea content = new JTextArea("Nome: Dr. João da Silva\nID: 98765\nDepartamento: Ciência da Computação");
        content.setFont(new Font("Arial", Font.PLAIN, 16));
        content.setEditable(false);
        add(content, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnVoltar = new JButton("<- Voltar (Menu Professor)");
        btnVoltar.addActionListener(e -> telaPrincipal.changeWindow("MENU_PROFESSOR"));
        bottomPanel.add(btnVoltar);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}