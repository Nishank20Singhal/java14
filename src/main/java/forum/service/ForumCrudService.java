package forum.service;

import forum.model.Authority;
import forum.model.User;
import forum.repository.AuthorityRepository;
import forum.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import forum.model.Post;
import forum.repository.PostRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForumCrudService {

    private final PostRepository posts;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    public ForumCrudService(PostRepository posts, UserRepository users, AuthorityRepository authorities) {
        this.posts = posts;
        this.users = users;
        this.authorities = authorities;
    }

    public List<Post> getAll() {
        return new ArrayList<>(posts.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }

    public void save(Post post) {
        post.setCreated(new Timestamp(System.currentTimeMillis()));
        posts.save(post);
    }

    public Post findById(int id) {
        return posts.findById(id).get();
    }

    public Authority findByAuthority(String authority) {
        return authorities.findByAuthority(authority);
    }

    public void saveUser(User user) {
        users.save(user);
    }
}