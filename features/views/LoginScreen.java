package views;

import domain.entities.user.Student;
import domain.entities.user.Teacher;
import shared.AppScreen;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LoginScreen implements AppScreen {
    public static final String ID = MainWindow.LOGIN;

    private final MainWindow app;
    private final JPanel panel = new JPanel(new GridBagLayout());
    private final JComboBox<Student> cbStudents = new JComboBox<>();

    public LoginScreen(MainWindow app) {
        this.app = app;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("Welcome to the Educational Management System", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));
        gbc.gridy = 0; gbc.insets = new Insets(0,0,20,0);
        panel.add(title, gbc);

        // Login as Teacher
        JButton btnTeacher = new JButton("Login as Teacher");
        btnTeacher.setPreferredSize(new Dimension(320, 44));
        btnTeacher.addActionListener(e -> doTeacherLogin());
        gbc.gridy = 1; gbc.insets = new Insets(0,0,12,0);
        panel.add(btnTeacher, gbc);

        // Separator
        gbc.gridy = 2; gbc.insets = new Insets(0,0,12,0);
        panel.add(new JSeparator(SwingConstants.HORIZONTAL), gbc);

        // Student label
        JLabel lblStudent = new JLabel("Or select a student:");
        gbc.gridy = 3; gbc.insets = new Insets(6,0,6,0);
        panel.add(lblStudent, gbc);

        // Student combo
        cbStudents.setPreferredSize(new Dimension(320, 40));
        cbStudents.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value == null) setText("Select a student…");
                else setText(((Student) value).getName());
                return this;
            }
        });
        gbc.gridy = 4; gbc.insets = new Insets(0,0,10,0);
        panel.add(cbStudents, gbc);

        // Login as Student
        JButton btnStudent = new JButton("Login as Student");
        btnStudent.setPreferredSize(new Dimension(320, 44));
        btnStudent.addActionListener(e -> doStudentLogin());
        gbc.gridy = 5; gbc.insets = new Insets(0,0,0,0);
        panel.add(btnStudent, gbc);
    }

    private void doTeacherLogin() {
        // Simple: pick the first teacher from the dataset. Replace with a proper teacher selector later.
        List<Teacher> teachers = app.getContext().services().classrooms().listAllTeachers();
        if (teachers.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "No teachers found.", "Login", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Teacher t = teachers.get(0);
        app.setCurrentUser(t);
        app.changeWindow(MainWindow.HOME);
    }

    private void doStudentLogin() {
        Student s = (Student) cbStudents.getSelectedItem();
        if (s == null) {
            JOptionPane.showMessageDialog(panel, "Please select a student.", "Login", JOptionPane.WARNING_MESSAGE);
            return;
        }
        app.setCurrentUser(s);
        app.changeWindow(MainWindow.HOME);
    }

    @Override public String id() { return ID; }

    @Override public JComponent getComponent() { return panel; }

    @Override public void onShow() {
        // Refresh students list every time the screen appears
        DefaultComboBoxModel<Student> model = new DefaultComboBoxModel<>();
        model.addElement(null); // placeholder
        for (Student s : app.getContext().services().classrooms().listAllStudents()) {
            model.addElement(s);
        }
        cbStudents.setModel(model);
        cbStudents.setSelectedIndex(0);
    }
}

