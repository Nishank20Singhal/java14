package forum.service;

import forum.model.Post;
import forum.model.User;
import forum.repository.PostMemRepository;
import forum.repository.UserMemRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ForumService {
    private final PostMemRepository postMemRepository;
    private final UserMemRepository userMemRepository;

    public ForumService(PostMemRepository postMemRepository, UserMemRepository userMemRepository) {
        this.postMemRepository = postMemRepository;
        this.userMemRepository = userMemRepository;
    }

    public void save(Post post) {
        postMemRepository.save(post);
    }

    public Collection<Post> getAll() {
        return postMemRepository.getAll();
    }

    public Post findById(int id) {
        return postMemRepository.findById(id);
    }

    public void saveUser(User user) {
        userMemRepository.saveUser(user);
    }

    public User findUserById(int id) {
        return userMemRepository.findById(id);
    }

    public Collection<User> getAllUsers() {
        return userMemRepository.getAll();
    }

    public boolean checkUser(String username, String password) {
        return userMemRepository.checkUser(username, password);
    }
}
