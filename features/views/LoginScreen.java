package views;

import domain.entities.user.Student;
import domain.entities.user.Teacher;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LoginScreen extends JPanel {
    private final MainWindow app;
    private final JComboBox<Student> cbStudents = new JComboBox<>();

    public LoginScreen(MainWindow mainWindow) {
        this.app = mainWindow;

        setLayout(new GridBagLayout());
        var gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Bem-vindo(a)", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));
        gbc.gridy = 0; gbc.insets = new Insets(0,0,20,0);
        add(title, gbc);

        JButton btnTeacher = new JButton("Entrar como professor");
        btnTeacher.setPreferredSize(new Dimension(320, 44));
        btnTeacher.addActionListener(e -> doTeacherLogin());
        gbc.gridy = 1; gbc.insets = new Insets(0,0,12,0);
        add(btnTeacher, gbc);

        gbc.gridy = 2; gbc.insets = new Insets(0,0,12,0);
        add(new JSeparator(SwingConstants.HORIZONTAL), gbc);

        JLabel lbl = new JLabel("Ou entre como um estudante:");
        gbc.gridy = 3; gbc.insets = new Insets(6,0,6,0);
        add(lbl, gbc);

        cbStudents.setPreferredSize(new Dimension(320, 40));
        cbStudents.setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setText(value == null ? "Selecione um estudante…" : ((Student)value).getName());
                return this;
            }
        });
        gbc.gridy = 4; gbc.insets = new Insets(0,0,10,0);
        add(cbStudents, gbc);

        JButton btnStudent = new JButton("Entrar como estudante");
        btnStudent.setPreferredSize(new Dimension(320, 44));
        btnStudent.addActionListener(e -> doStudentLogin());
        gbc.gridy = 5;
        add(btnStudent, gbc);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        // refresh students every time the login is displayed
        var model = new DefaultComboBoxModel<Student>();
        model.addElement(null);
        for (Student s : app.getContext().services().classrooms().listAllStudents()) {
            model.addElement(s);
        }
        cbStudents.setModel(model);
        cbStudents.setSelectedIndex(0);
    }

    private void doTeacherLogin() {
        List<Teacher> teachers = app.getContext().services().classrooms().listAllTeachers();
        if (teachers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum professor encontrado.", "Login", JOptionPane.WARNING_MESSAGE);
            return;
        }
        app.setCurrentUser(teachers.get(0)); // simple pick; later you can add a teacher selector
        app.changeWindow(MainWindow.HOME);
    }

    private void doStudentLogin() {
        Student s = (Student) cbStudents.getSelectedItem();
        if (s == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um estudante.", "Login", JOptionPane.WARNING_MESSAGE);
            return;
        }
        app.setCurrentUser(s);
        app.changeWindow(MainWindow.HOME);
    }
}