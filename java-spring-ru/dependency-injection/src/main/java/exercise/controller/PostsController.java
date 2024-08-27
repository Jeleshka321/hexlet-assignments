package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import exercise.model.Post;
import exercise.model.Comment;
import exercise.repository.PostRepository;
import exercise.repository.CommentRepository;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentsRepository;

    @GetMapping(path = "")
    public List<Post> index() {
        return postRepository.findAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @GetMapping(path = "/{id}")
    public Post show(@PathVariable long id) {

        var post =  postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        return post;
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable long id, @RequestBody Post data) {
        var post =  postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        post.setTitle(data.getTitle());
        post.setBody(data.getBody());

        postRepository.save(post);

        return post;
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable long id) {
        commentsRepository.deleteByPostId(id);
        postRepository.deleteById(id);
    }
}