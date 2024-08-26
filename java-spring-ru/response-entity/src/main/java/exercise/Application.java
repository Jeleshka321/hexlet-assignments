package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity
                .ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(posts);
    }


    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable String id) {
        Optional<Post> post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return post.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }


    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody Post updatedPost) {
        Optional<Post> existingPost = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (existingPost.isPresent()) {
            Post post = existingPost.get();
            post.setTitle(updatedPost.getTitle());
            post.setBody(updatedPost.getBody());
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
