package domain.entities.comment;

import domain.entities.user.User;

public class Comment {
    private User author;
    private String text;

    public Comment(User author, String text) {
        this.author = author;
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return author.getName() + ": " + text;
    }
}		