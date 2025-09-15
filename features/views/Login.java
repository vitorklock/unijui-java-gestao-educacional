package views;

import javax.swing.*;
import java.awt.*;

public class Login extends JPanel {

    public Login(TelaPrincipal telaPrincipal) {
        this.setLayout(new GridBagLayout()); // Centraliza o conteúdo

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));

        // Título
        JLabel titulo = new JLabel("Bem-vindo ao Sistema");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botão Professor
        JButton btnProfessor = new JButton("Sou Professor");
        btnProfessor.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnProfessor.setMaximumSize(new Dimension(250, 50));
        btnProfessor.setFont(new Font("Arial", Font.PLAIN, 16));
        btnProfessor.addActionListener(e -> telaPrincipal.trocarTela("MENU_PROFESSOR"));

        // Botão Aluno
        JButton btnAluno = new JButton("Sou Aluno");
        btnAluno.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAluno.setMaximumSize(new Dimension(250, 50));
        btnAluno.setFont(new Font("Arial", Font.PLAIN, 16));
        btnAluno.addActionListener(e -> telaPrincipal.trocarTela("MENU_ALUNO"));

        painelCentral.add(titulo);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 40))); // Espaçamento
        painelCentral.add(btnProfessor);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento
        painelCentral.add(btnAluno);

        this.add(painelCentral);
    }
}