package forum.repository;

import forum.model.Post;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostMemRepository {

    private final Map<Integer, Post> posts = new HashMap<>();
    private final AtomicInteger idCount = new AtomicInteger(1);

    public PostMemRepository() {
        Post post1 = Post.of("I will sell VOLVO V70, an exchange",
                "Volvo V 70 2.0 Turbo 2013 !!! The car is in perfect condition. Technique 10+, no investment !!! The salon is clean and tidy. All original glass with windshield, no chips or cracks., Toning. Good equipment. Climate, cruise, light sensors, rain sensors, anti-collision system, start-stop. The spare tire did not get !!!! Winter tires. Two keys available. Price without bargaining. Exchange 12000.");
        post1.setId(idCount.incrementAndGet());
//        post1.setCreated(Calendar.getInstance().getTime().toString());

        posts.put(post1.getId(), post1);
        }


    public void save(Post post) {
        if (!posts.containsKey(post.getId())) {
            createNew(post);
        } else {
            update(post);
        }
    }

    public void createNew(Post post) {
        Post newPost = new Post();
        newPost.setId(idCount.incrementAndGet());
        newPost.setName(post.getName());
        newPost.setDescription(post.getDescription());
//        newPost.setCreated(Calendar.getInstance().getTime().toString());
        posts.put(newPost.getId(), newPost);
    }

    public void update(Post post) {
//        post.setCreated(Calendar.getInstance().getTime().toString());
        posts.replace(post.getId(), post);
    }

    public Collection<Post> getAll() {
        return posts.values();
    }

    public Post findById(int id) {
        return posts.get(id);
    }
}
