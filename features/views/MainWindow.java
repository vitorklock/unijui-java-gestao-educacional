package views;

import javax.swing.*;

import domain.entities.classroom.Classroom;
import domain.entities.user.User;
import main.bootstrap.AppContext;
import views.LoginScreen;

import java.awt.*;

public class MainWindow extends JFrame {
    public static final String LOGIN = "LOGIN";
    public static final String HOME  = "HOME";

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel root = new JPanel(cardLayout);

    private final AppContext context;
    private User currentUser;
    private Classroom currentClassroom;

    // keep references to screens so we can refresh on navigation
    private LoginScreen loginScreen;
    private HomeScreen homeScreen;

    public MainWindow(AppContext context) {
        this.context = context;

        setTitle("SGE - Sistema de Gestão Educacional");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(root);

        // instantiate screens (each receives MainWindow)
        loginScreen = new LoginScreen(this);
        homeScreen  = new HomeScreen(this);

        root.add(loginScreen, LOGIN);
        root.add(homeScreen,  HOME);

        changeWindow(LOGIN);
    }

    public AppContext getContext() { return this.context; }

    public User getCurrentUser() { return currentUser; }
    public void setCurrentUser(User user) { this.currentUser = user; }

    public void changeWindow(String screen) {
        if (HOME.equals(screen)) {
            homeScreen.refreshFor(getCurrentUser());
        }
        cardLayout.show(root, screen);
    }

	public Classroom getCurrentClassroom() {
		return currentClassroom;
	}

	public void setCurrentClassroom(Classroom currentClassroom) {
		this.currentClassroom = currentClassroom;
	}
}
