package entities.post;

import java.util.ArrayList;
import java.util.List;

import entities.comment.Comment;
import entities.user.User;

public class Post {
    private int id;
    private String content;
    private User author;
    private List<Comment> comments;

    public Post(int id, User author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.comments = new ArrayList<Comment>();
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public User getAutor() {
        return author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public String toString() {
        return "Post #" + id + " (" + author.getName() + "): " + content;
    }
}