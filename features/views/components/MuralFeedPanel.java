package views.components;

import main.bootstrap.AppContext;
import domain.entities.classroom.Classroom;
import domain.entities.comment.Comment;
import domain.entities.post.Post;
import views.MainWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Comparator;
import java.util.List;

/**
 * Scrollable feed that renders posts/comments for the classroom
 * currently selected in MainWindow. Call reload() after changing
 * MainWindow#setCurrentClassroom(...).
 */
public class MuralFeedPanel extends JPanel {

    private final MainWindow app;          // <- now we keep a reference to the app
    private final AppContext context;

    // UI
    private final JPanel listPanel = new JPanel();
    private final JScrollPane scroll;

    public MuralFeedPanel(MainWindow app) {
        this.app = app;
        this.context = app.getContext();

        setLayout(new BorderLayout());
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBorder(new EmptyBorder(8, 8, 8, 8));

        scroll = new JScrollPane(
                listPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scroll.getVerticalScrollBar().setUnitIncrement(14);
        add(scroll, BorderLayout.CENTER);
    }

    /** Fetches posts for the classroom selected in MainWindow and re-renders. */
    public void reload() {
        listPanel.removeAll();

        Classroom selected = app.getCurrentClassroom();
        if (selected == null) {
            listPanel.add(emptyLabel("Selecione uma turma para ver postagens."));
            refreshUI();
            return;
        }

        List<Post> posts = context.services().classrooms().listPosts(selected.getId());
        if (posts.isEmpty()) {
            listPanel.add(emptyLabel("Sem postagens no momento."));
            refreshUI();
            return;
        }

        posts.stream()
             .sorted(Comparator.comparingInt(Post::getId).reversed())
             .forEach(p -> {
                 listPanel.add(buildPostCard(p));
                 listPanel.add(Box.createVerticalStrut(12));
             });

        refreshUI();
    }

    private JComponent buildPostCard(Post p) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0xDADFE6)),
                new EmptyBorder(10, 12, 10, 12)
        ));
        card.setBackground(Color.WHITE);

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0xE9, 0xED, 0xF5));
        header.setBorder(new EmptyBorder(8, 10, 8, 10));
        JLabel lblTitle = new JLabel("Post " + p.getId() + " — " + p.getAutor().getName());
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD));
        header.add(lblTitle, BorderLayout.WEST);
        card.add(header, BorderLayout.NORTH);

        // Body (apenas o texto do post) com fundo cinza ~20% mais escuro
        JLabel lblBody = new JLabel("<html>" + escape(p.getContent()) + "</html>");
        lblBody.setOpaque(true);
        lblBody.setBackground(new Color(0xE6, 0xE6, 0xE6)); 
        lblBody.setBorder(new EmptyBorder(10, 10, 10, 10));
        card.add(lblBody, BorderLayout.CENTER);

        // Comments (ficam como estão, sem fundo)
        if (!p.getComments().isEmpty()) {
            JPanel comments = new JPanel();
            comments.setLayout(new BoxLayout(comments, BoxLayout.Y_AXIS));
            comments.setBorder(new EmptyBorder(8, 18, 6, 6)); // indent
            comments.setOpaque(false);
            for (Comment c : p.getComments()) {
                JLabel author = new JLabel(c.getAuthor().getName());
                author.setFont(author.getFont().deriveFont(Font.BOLD, 12f));
                JLabel text = new JLabel("<html>" + escape(c.getText()) + "</html>");
                text.setBorder(new EmptyBorder(0, 14, 8, 0));
                comments.add(author);
                comments.add(text);
            }
            card.add(comments, BorderLayout.SOUTH);
        }

        // Faz o card expandir em largura sem esticar altura infinita
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        Dimension pref = card.getPreferredSize();
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, pref.height));

        return card;
    }

    private JLabel emptyLabel(String text) {
        JLabel lbl = new JLabel(text, SwingConstants.CENTER);
        lbl.setBorder(new EmptyBorder(20, 0, 20, 0));
        lbl.setForeground(new Color(90, 96, 106));
        return lbl;
    }

    private void refreshUI() {
        listPanel.revalidate();
        listPanel.repaint();
    }

    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\n", "<br/>");
    }
}
