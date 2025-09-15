package views;

import javax.swing.*;
import java.awt.*;
import domain.entities.post.Post;
import main.bootstrap.ServiceRegistry;

public class MainWindow extends JFrame {
    private final ServiceRegistry services;
    private final DefaultListModel<String> postsModel = new DefaultListModel<>();

    public MainWindow(ServiceRegistry services) {
        this.services = services;
        initComponents();
        loadDemoDataIntoList();
    }

    private void initComponents() {
        setTitle("Educational Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLocationRelativeTo(null);

        var list = new JList<>(postsModel);
        var refreshBtn = new JButton("Refresh Posts");
        refreshBtn.addActionListener(e -> loadDemoDataIntoList());

        var panel = new JPanel(new BorderLayout(8,8));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.add(new JScrollPane(list), BorderLayout.CENTER);
        panel.add(refreshBtn, BorderLayout.SOUTH);
        setContentPane(panel);
    }

    private void loadDemoDataIntoList() {
        postsModel.clear();
        // Grab the first classroom and list its posts
        var all = services.classrooms().findAll();
        if (all.isEmpty()) return;
        var c1 = all.get(0);
        for (Post p : services.classroomService().listPosts(c1.getId())) {
            postsModel.addElement(p.toString());
        }
    }
}
