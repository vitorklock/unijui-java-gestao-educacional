package repositories.inmemory;

import entities.post.Post;
import shared.InMemoryRepository;

public class PostRepository extends InMemoryRepository<Post> {
    public PostRepository() {
        super(Post::getId);
    }
}
