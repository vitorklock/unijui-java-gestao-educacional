package gestao_educacional.features.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Notas extends JPanel {
    public Notas(TelaPrincipal telaPrincipal) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Quadro de Notas", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        
        String[] colunas = {"Aluno", "G1", "G2", "Média Final"};
        Object[][] dados = {
            {"Vitor Klock", "8.0", "7.5", "7.75"},
            {"Maria da Silva", "9.5", "9.0", "9.25"},
        };
        JTable tabela = new JTable(new DefaultTableModel(dados, colunas));
        add(new JScrollPane(tabela), BorderLayout.CENTER);

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