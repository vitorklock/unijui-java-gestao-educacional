package domain.entities.mural;

import java.util.ArrayList;
import java.util.List;

import domain.entities.comment.Comment;
import domain.entities.post.Post;
import domain.entities.user.User;

public class Mural {
    private List<Post> posts;
    private int nextPostId;

    public Mural() {
        this.posts = new ArrayList<>();
        this.nextPostId = 1;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Post addPost(User author, String content) {
        Post post = new Post(nextPostId++, author, content);
        posts.add(post);
        return post;
    }

    public Post getPostById(int id) {
        for (Post p : posts) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean addComment(int postId, User author, String text) {
        Post post = getPostById(postId);
        if (post != null) {
            Comment comment = new Comment(author, text);
            post.addComment(comment);
            return true;
        }
        return false;
    }
}