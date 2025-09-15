package views;

import javax.swing.*;
import java.awt.*;

public class Mural extends JPanel {
    public Mural(TelaPrincipal telaPrincipal) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Mural de Avisos", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        JTextArea muralContent = new JTextArea(" - Aviso 1: A prova será na próxima semana.\n\n - Aviso 2: Não haverá aula no feriado.");
        muralContent.setFont(new Font("Arial", Font.PLAIN, 16));
        muralContent.setEditable(false);
        add(new JScrollPane(muralContent), BorderLayout.CENTER);

        // Botões de Voltar
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnVoltarProf = new JButton("<- Voltar (Menu Professor)");
        btnVoltarProf.addActionListener(e -> telaPrincipal.trocarTela("MENU_PROFESSOR"));
        bottomPanel.add(btnVoltarProf);

        JButton btnVoltarAluno = new JButton("<- Voltar (Menu Aluno)");
        btnVoltarAluno.addActionListener(e -> telaPrincipal.trocarTela("MENU_ALUNO"));
        bottomPanel.add(btnVoltarAluno);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}