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
        Post post1 = Post.of("Продам VOLVO V70 , обмен",
                "Volvo V 70 2.0 Turbo 2013 год !!! Машина в идеальном состоянии. Техника 10+,без вложений !!! Салон чистый,аккуратный. Все стекла оригинал с лобовым,без сколов и трещин.,тонировка. Хорошая комплектация. Климат,круиз,датчики света,дождя,система предотвращения столкновения,старт-стоп. Запаска не доставалась!!!!Резина зима. Два ключа в наличии. Цена без торга. Обмен 12000.");
        post1.setId(idCount.incrementAndGet());
//        post1.setCreated(Calendar.getInstance().getTime().toString());
        Post post2 = Post.of("Продам MAZDA 3",
                "Чистый 16 год. Машина из США, повреждения были не критические. По факту крашена задняя крышка, бампер и крыло. Двигатель, коробка работает идеально,по ходовой без вложений.Торга нет совсем,это самая дешёвая цена .Звонить только договориться о просмотре");
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
