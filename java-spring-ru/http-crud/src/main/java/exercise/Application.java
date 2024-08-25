package exercise;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // Получение списка всех постов с поддержкой пейджинга
    @GetMapping("/posts")
    public List<Post> getPosts(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10") int limit) {
        int start = (page - 1) * limit;
        int end = Math.min(start + limit, posts.size());
        return posts.subList(start, end);
    }

    // Получение конкретного поста по ID
    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable String id) {
        return posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Создание нового поста
    @PostMapping("/posts")
    public Post createPost(@RequestBody Post newPost) {
        posts.add(newPost);
        return newPost;
    }

    // Обновление поста по ID
    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable String id, @RequestBody Post updatedPost) {
        Optional<Post> existingPost = posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();

        if (existingPost.isPresent()) {
            Post post = existingPost.get();
            post.setTitle(updatedPost.getTitle());
            post.setBody(updatedPost.getBody());
            return post;
        } else {
            return null;
        }
    }

    // Удаление поста по ID
    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable String id) {
        posts.removeIf(post -> post.getId().equals(id));
    }
}