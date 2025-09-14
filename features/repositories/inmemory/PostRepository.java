package repositories.inmemory;

import domain.entities.post.Post;
import shared.repository.InMemoryRepository;

public class PostRepository extends InMemoryRepository<Post> {
    public PostRepository() {
        super(Post::getId);
    }
}
