package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class TelaPrincipal extends JFrame {

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel painelPrincipal = new JPanel(cardLayout);

    public TelaPrincipal() {
        setTitle("Sistema de Gestão Educacional");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Instanciando TODAS as telas e passando a referência desta janela
        Login login = new Login(this);
        MenuProfessor menuProfessor = new MenuProfessor(this);
        MenuAluno menuAluno = new MenuAluno(this);
        Mural mural = new Mural(this);
        Aluno aluno = new Aluno(this);
        Turma turma = new Turma(this);
        Disciplinas disciplinas = new Disciplinas(this);
        Notas notas = new Notas(this);
        Forum forum = new Forum(this);
        Arquivos arquivos = new Arquivos(this);
        Frequencia frequencia = new Frequencia(this);
        Professor professor = new Professor(this);

        // Adicionando as telas ao "baralho"
        painelPrincipal.add(login, "LOGIN");
        painelPrincipal.add(menuProfessor, "MENU_PROFESSOR");
        painelPrincipal.add(menuAluno, "MENU_ALUNO");
        painelPrincipal.add(mural, "MURAL");
        painelPrincipal.add(aluno, "ALUNO");
        painelPrincipal.add(turma, "TURMA");
        painelPrincipal.add(disciplinas, "DISCIPLINAS");
        painelPrincipal.add(notas, "NOTAS");
        painelPrincipal.add(forum, "FORUM");
        painelPrincipal.add(arquivos, "ARQUIVOS");
        painelPrincipal.add(frequencia, "FREQUENCIA");
        painelPrincipal.add(professor, "PROFESSOR");

        add(painelPrincipal);
        // A primeira tela a ser exibida é a de Login
        cardLayout.show(painelPrincipal, "LOGIN");
    }

    // Método para trocar a tela visível
    public void trocarTela(String nomeDaTela) {
        cardLayout.show(painelPrincipal, nomeDaTela);
    }

    // Método main para iniciar a aplicação
    public static void main(String[] args) {
        TelaPrincipal janela = new TelaPrincipal();
        janela.setVisible(true);
    }
}