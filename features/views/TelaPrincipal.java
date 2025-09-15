package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class TelaPrincipal extends JFrame {

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel painelPrincipal = new JPanel(cardLayout);
    private String ultimoMuralAcessado = "LOGIN";

    public TelaPrincipal() {
        setTitle("Sistema de Gestão Educacional");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Instanciando as telas (sem a SelecaoAluno)
        Login login = new Login(this);
        Mural muralProfessor = new Mural(this, "PROFESSOR");
        Mural muralAluno = new Mural(this, "ALUNO");
        Aluno aluno = new Aluno(this);
        Arquivos arquivos = new Arquivos(this);
        // ... instancie suas outras telas aqui da mesma forma

        // Adicionando as telas ao "baralho"
        painelPrincipal.add(login, "LOGIN");
        painelPrincipal.add(muralProfessor, "MURAL_PROFESSOR");
        painelPrincipal.add(muralAluno, "MURAL_ALUNO");
        painelPrincipal.add(aluno, "ALUNO");
        painelPrincipal.add(arquivos, "ARQUIVOS");
        // ... adicione suas outras telas aqui

        add(painelPrincipal);
    }

    public void trocarTela(String nomeDaTela) {
        if (nomeDaTela.equals("MURAL_PROFESSOR") || nomeDaTela.equals("MURAL_ALUNO")) {
            this.ultimoMuralAcessado = nomeDaTela;
        }
        cardLayout.show(painelPrincipal, nomeDaTela);
    }

    public void voltarParaMural() {
        trocarTela(this.ultimoMuralAcessado);
    }

    public static void main(String[] args) {
        TelaPrincipal janela = new TelaPrincipal();
        janela.setVisible(true);
    }
}