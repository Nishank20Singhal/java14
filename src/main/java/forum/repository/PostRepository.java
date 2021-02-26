package forum.repository;

import forum.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PostRepository extends CrudRepository<Post, Integer> {
    Collection<Post> findAll(Sort id);
}
