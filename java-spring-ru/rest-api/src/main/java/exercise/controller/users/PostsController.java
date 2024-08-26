package exercise.controller.users;

import exercise.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// BEGIN
@RestController
@RequestMapping("/api/users/{userId}/posts")
public class PostsController {
    private List<Post> posts = Data.getPosts();

    // Получение всех постов пользователя по userId
    @GetMapping
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable int userId) {
        List<Post> userPosts = posts.stream()
                .filter(post -> post.getUserId() == userId)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userPosts);
    }

    // Создание нового поста для пользователя по userId
    @PostMapping
    public ResponseEntity<Post> createUserPost(@PathVariable int userId, @RequestBody Post post) {
        post.setUserId(userId);
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
}
// END
