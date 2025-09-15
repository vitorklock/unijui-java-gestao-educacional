package views;

import javax.swing.*;

import domain.entities.classroom.Classroom;
import domain.entities.user.Student;
import domain.entities.user.Teacher;
import domain.entities.user.User;
import views.components.MuralFeedPanel;

import java.awt.*;
import java.util.List;

public class HomeScreen extends JPanel {
    private final MainWindow app;

    // header / actions
    private final JLabel lblWelcome = new JLabel("Welcome", SwingConstants.LEFT);
    private final JComboBox<Classroom> cbClassrooms = new JComboBox<>();

    // role-specific toolbars
    private final JPanel teacherBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JPanel studentBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    // feed
    private final MuralFeedPanel feed;

    public HomeScreen(MainWindow app) {
        this.app = app;
        this.feed = new MuralFeedPanel(app);       

        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        // ===== HEADER =====
        JPanel header = new JPanel(new BorderLayout(8, 0));
        lblWelcome.setFont(lblWelcome.getFont().deriveFont(Font.BOLD, 18f));
        header.add(lblWelcome, BorderLayout.WEST);

        // right side: classroom combo + logout
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        cbClassrooms.setPreferredSize(new Dimension(260, 30));
        cbClassrooms.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value == null) {
                    setText("Select a classroom…");
                } else {
                    Classroom c = (Classroom) value;
                    setText("ID " + c.getId() + " — " + c.getSubject().getName());
                }
                return this;
            }
        });
        cbClassrooms.addActionListener(e -> onClassroomChanged());
        right.add(cbClassrooms);

        JButton btnLogout = new JButton("Log out");
        btnLogout.addActionListener(e -> {
            app.setCurrentUser(null);
            app.setCurrentClassroom(null);
            app.changeWindow(MainWindow.LOGIN);
        });
        right.add(btnLogout);

        header.add(right, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // ===== SIDE TOOLBARS (role-based) =====
        teacherBar.add(new JButton("New Class"));
        teacherBar.add(new JButton("Grades"));
        teacherBar.add(new JButton("Students"));

        studentBar.add(new JButton("My Grades"));

        JPanel sideBars = new JPanel(new GridLayout(2, 1, 0, 6));
        sideBars.add(teacherBar);
        sideBars.add(studentBar);
        add(sideBars, BorderLayout.WEST);       
        
        // FEED
        add(this.feed, BorderLayout.CENTER);
    }

    /** Called by MainWindow before showing this screen. */
    public void refreshFor(User u) {
        if (u == null) {
            lblWelcome.setText("Welcome");
            teacherBar.setVisible(false);
            studentBar.setVisible(false);
            setClassroomsModel(List.of()); // clears combo + feed
            return;
        }

        lblWelcome.setText("Welcome, " + u.getName());

        // determine role using instanceof (works regardless of helper methods)
        boolean isTeacher = (u instanceof Teacher);
        boolean isStudent = (u instanceof Student);

        teacherBar.setVisible(isTeacher);
        studentBar.setVisible(isStudent);

        // load classrooms for this user
        List<Classroom> classes;
        if (isTeacher) {
            classes = app.getContext().services().classrooms().listByTeacher(u.getId());
        } else if (isStudent) {
            classes = app.getContext().services().classrooms().listByStudent(u.getId());
        } else {
            classes = List.of();
        }
        setClassroomsModel(classes);
        revalidate();
        repaint();
    }

    // ===== helpers =====

    private void setClassroomsModel(List<Classroom> classes) {
        DefaultComboBoxModel<Classroom> model = new DefaultComboBoxModel<>();
        if (classes == null || classes.isEmpty()) {
            model.addElement(null);
            cbClassrooms.setModel(model);
            cbClassrooms.setSelectedIndex(0);
            app.setCurrentClassroom(null);
            feed.reload();
            return;
        }
        for (Classroom c : classes) model.addElement(c);
        cbClassrooms.setModel(model);
        cbClassrooms.setSelectedIndex(0);

        Classroom first = classes.get(0);
        app.setCurrentClassroom(first);
        feed.reload();
    }

    private void onClassroomChanged() {
        Classroom selected = (Classroom) cbClassrooms.getSelectedItem();
        app.setCurrentClassroom(selected);
        feed.reload();
    }
}
