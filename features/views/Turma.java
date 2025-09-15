package views;

import javax.swing.*;
import java.awt.*;

public class Turma extends JPanel {
    public Turma(TelaPrincipal telaPrincipal) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Gerenciamento de Turmas", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("TADS2024 - Manhã");
        listModel.addElement("TADS2024 - Noite");
        JList<String> lista = new JList<>(listModel);
        add(new JScrollPane(lista), BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnVoltar = new JButton("<- Voltar (Menu Professor)");
        btnVoltar.addActionListener(e -> telaPrincipal.changeWindow("MENU_PROFESSOR"));
        bottomPanel.add(btnVoltar);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}