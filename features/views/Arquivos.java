package views;

import javax.swing.*;
import java.awt.*;

public class Arquivos extends JPanel {
    public Arquivos(TelaPrincipal telaPrincipal) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Arquivos da Disciplina", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Plano de Ensino.pdf");
        listModel.addElement("Aula 01 - Introdução.pptx");
        JList<String> lista = new JList<>(listModel);
        add(new JScrollPane(lista), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnVoltarProf = new JButton("<- Voltar (Menu Professor)");
        btnVoltarProf.addActionListener(e -> telaPrincipal.changeWindow("MENU_PROFESSOR"));
        bottomPanel.add(btnVoltarProf);

        JButton btnVoltarAluno = new JButton("<- Voltar (Menu Aluno)");
        btnVoltarAluno.addActionListener(e -> telaPrincipal.changeWindow("MENU_ALUNO"));
        bottomPanel.add(btnVoltarAluno);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}