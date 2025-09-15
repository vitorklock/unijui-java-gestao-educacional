package views;

import javax.swing.*;
import java.awt.*;

public class Forum extends JPanel {
    public Forum(TelaPrincipal telaPrincipal) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Fórum de Discussão", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Tópico 1: Dúvidas sobre o trabalho final");
        listModel.addElement("Tópico 2: Data da prova G2");
        JList<String> lista = new JList<>(listModel);
        add(new JScrollPane(lista), BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnVoltar = new JButton("<- Voltar (Menu Professor)");
        btnVoltar.addActionListener(e -> telaPrincipal.changeWindow("MENU_PROFESSOR"));
        bottomPanel.add(btnVoltar);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}