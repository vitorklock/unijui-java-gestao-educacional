package views;

import javax.swing.*;

import domain.entities.user.User;
import domain.entities.user.User.UserRole;
import views.components.MuralFeedPanel;

import java.awt.*;

public class HomeScreen extends JPanel {
    private final MainWindow app;

    // shared
    private final JLabel lblWelcome = new JLabel("Welcome", SwingConstants.LEFT);

    // teacher-only area
    private final JPanel teacherBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
    // student-only area
    private final JPanel studentBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

    // shared content area (e.g., mural, notes list…)
    private final JPanel content = new JPanel(new BorderLayout());
    
    private MuralFeedPanel feed;

    public HomeScreen(MainWindow app) {
        this.app = app;

        setLayout(new BorderLayout(12,12));
        setBorder(BorderFactory.createEmptyBorder(16,16,16,16));

        // HEADER
        var header = new JPanel(new BorderLayout());
        lblWelcome.setFont(lblWelcome.getFont().deriveFont(Font.BOLD, 18f));
        header.add(lblWelcome, BorderLayout.WEST);

        var btnLogout = new JButton("Sair");
        btnLogout.addActionListener(e -> {
            app.setCurrentUser(null);
            app.changeWindow(MainWindow.LOGIN);
        });
        header.add(btnLogout, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // TEACHER BAR
        teacherBar.add(new JButton("Nova Turma"));
        teacherBar.add(new JButton("Notas"));
        teacherBar.add(new JButton("Alunos"));
        // STUDENT BAR
        studentBar.add(new JButton("Minhas Notas"));

        var topBars = new JPanel(new GridLayout(2, 1, 0, 6));
        topBars.add(teacherBar);
        topBars.add(studentBar);
        add(topBars, BorderLayout.WEST);

        // CONTENT (placeholder – plug your mural/notes panels here)
        content.add(new JLabel("Content area (mural, notes, etc.)"), BorderLayout.CENTER);
        add(content, BorderLayout.CENTER);
        
        feed = new MuralFeedPanel(app.getContext());
        add(feed, BorderLayout.CENTER);
    }

    /** Called by MainWindow before showing this screen. */
    public void refreshFor(User u) {
        if (u == null) {
            lblWelcome.setText("Bem vindo");
            teacherBar.setVisible(false);
            studentBar.setVisible(false);
            return;
        }
        lblWelcome.setText("Welcome, " + u.getName());
        UserRole r = u.role();
        boolean isTeacher = (r == UserRole.TEACHER);
        boolean isStudent = (r == UserRole.STUDENT);

        teacherBar.setVisible(isTeacher);
        studentBar.setVisible(isStudent);
        revalidate();
        repaint();
    }
}
