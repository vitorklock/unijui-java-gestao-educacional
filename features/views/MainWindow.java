package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import main.bootstrap.AppBootstrap;
import main.bootstrap.AppContext;
import main.bootstrap.ServiceRegistry;

import java.awt.CardLayout;

public class MainWindow extends JFrame {

	private final CardLayout cardLayout = new CardLayout();
	private final JPanel painelPrincipal = new JPanel(cardLayout);
	private AppContext context;
    private String ultimoMuralAcessado = "LOGIN";

	public MainWindow(AppContext context) {
		this.context = context;
		
		setTitle("Sistema de Gestão Educacional");
		setSize(1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

        // Instanciando as telas (sem a SelecaoAluno)
		Login login = new Login(this);
        Aluno aluno = new Aluno(this);
        Arquivos arquivos = new Arquivos(this);
        Disciplinas disciplinas = new Disciplinas(this);
        Forum forum = new Forum(this);
        Frequencia frequencia = new Frequencia(this);
        Notas notas = new Notas(this);
        Professor professor = new Professor(this);
        Turma turma = new Turma(this);

        painelPrincipal.add(login, "LOGIN");
        painelPrincipal.add(aluno, "ALUNO");
        painelPrincipal.add(arquivos, "ARQUIVOS");
        painelPrincipal.add(disciplinas, "DISCIPLINAS");
        painelPrincipal.add(forum, "FORUM");
        painelPrincipal.add(frequencia, "FREQUENCIA");
        painelPrincipal.add(notas, "NOTAS");
        painelPrincipal.add(professor, "PROFESSOR");
        painelPrincipal.add(turma, "TURMA");

        add(painelPrincipal);
    }
	
	public AppContext getContext() {
		return this.context;
	}

    public void changeWindow(String nomeDaTela) {
        if (nomeDaTela.equals("MURAL_PROFESSOR") || nomeDaTela.equals("MURAL_ALUNO")) {
            this.ultimoMuralAcessado = nomeDaTela;
        }
        cardLayout.show(painelPrincipal, nomeDaTela);
    }

    public void voltarParaMural() {
        changeWindow(this.ultimoMuralAcessado);
    }

    public static void main(String[] args) {
    	AppContext ctx = AppBootstrap.init();
    	MainWindow window = new MainWindow(ctx);
		window.setVisible(true);
    }
}