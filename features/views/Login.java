package views;

import javax.swing.*;

import main.bootstrap.ServiceRegistry;

import java.awt.*;

public class Login extends JPanel {

    public Login(MainWindow telaPrincipal) {

        // Usamos GridBagLayout para alinhar os componentes verticalmente de forma elegante
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // --- TÍTULO ---
        JLabel titulo = new JLabel("Bem-vindo ao Sistema de Gestão");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0); // Margem inferior
        add(titulo, gbc);

        // --- ÁREA DO PROFESSOR ---
        JButton btnProfessor = new JButton("Entrar como Professor");
        btnProfessor.setPreferredSize(new Dimension(300, 50));
        btnProfessor.setFont(new Font("Arial", Font.PLAIN, 16));

        btnProfessor.addActionListener(e -> telaPrincipal.trocarTela("MURAL_PROFESSOR"));
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(btnProfessor, gbc);

        // --- SEPARADOR ---
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Faz o separador preencher a largura
        add(new JSeparator(SwingConstants.HORIZONTAL), gbc);
        gbc.fill = GridBagConstraints.NONE; // Reseta para o padrão

        // --- ÁREA DO ALUNO ---
        JLabel labelAluno = new JLabel("Ou, selecione um aluno para entrar:");
        labelAluno.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 3;
        gbc.insets = new Insets(20, 0, 5, 0);
        add(labelAluno, gbc);

        // LISTA DE ALUNOS (DROPDOWN)
        String[] alunos = {"(Selecione um aluno)", "Vitor Klock", "Maria da Silva", "João Pereira"};
        JComboBox<String> comboAlunos = new JComboBox<>(alunos);
        comboAlunos.setPreferredSize(new Dimension(300, 40));
        comboAlunos.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(comboAlunos, gbc);

        // BOTÃO DE ENTRAR COMO ALUNO
        JButton btnAluno = new JButton("Entrar como Aluno");
        btnAluno.setPreferredSize(new Dimension(300, 50));
        btnAluno.setFont(new Font("Arial", Font.PLAIN, 16));
        btnAluno.addActionListener(e -> {
            // Verifica se um aluno válido foi selecionado (ignora o primeiro item)
            if (comboAlunos.getSelectedIndex() <= 0) {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um aluno da lista.", "Seleção Inválida", JOptionPane.WARNING_MESSAGE);
            } else {
                telaPrincipal.trocarTela("MURAL_ALUNO");
            }
        });
        gbc.gridy = 5;
        add(btnAluno, gbc);
    }
}