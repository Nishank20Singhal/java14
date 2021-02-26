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
        Post post2 = Post.of("MAZDA 3 for sale",
                "Pure 16 year. The car is from the USA, the damage was not critical. In fact, the rear cover, bumper and fender are painted. The engine, the transmission works perfectly, on the chassis without investments. There is no bargaining at all, this is the cheapest price. Call only to arrange a viewing");
        post2.setId(idCount.incrementAndGet());
//        post2.setCreated("Tue Nov 10 10:54:29 EET 2020");

        posts.put(post1.getId(), post1);
        posts.put(post2.getId(), post2);
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
