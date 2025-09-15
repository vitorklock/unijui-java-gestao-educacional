package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Frequencia extends JPanel {
    public Frequencia(TelaPrincipal telaPrincipal) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Controle de Frequência", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        
        String[] colunas = {"Aluno", "01/08", "03/08", "08/08", "Total de Faltas"};
        Object[][] dados = {
            {"Vitor Klock", "P", "P", "F", "1"},
            {"Maria da Silva", "P", "P", "P", "0"},
        };
        JTable tabela = new JTable(new DefaultTableModel(dados, colunas));
        add(new JScrollPane(tabela), BorderLayout.CENTER);

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