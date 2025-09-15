package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Aluno extends JPanel {
    public Aluno(TelaPrincipal telaPrincipal) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Gerenciamento de Alunos", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        
        String[] colunas = {"Matrícula", "Nome", "Email"};
        Object[][] dados = {{"202401", "Vitor Klock", "vitor.k@email.com"}, {"202402", "Maria da Silva", "maria.s@email.com"}};
        JTable tabela = new JTable(new DefaultTableModel(dados, colunas));
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnVoltar = new JButton("<- Voltar para o Mural");
        // AÇÃO CORRIGIDA: Usa o novo método para voltar
        btnVoltar.addActionListener(e -> telaPrincipal.voltarParaMural());
        bottomPanel.add(btnVoltar);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}