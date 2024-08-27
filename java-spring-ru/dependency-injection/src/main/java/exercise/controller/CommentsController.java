package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<Comment> index() {
        return commentRepository.findAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @GetMapping(path = "/{id}")
    public Comment show(@PathVariable long id) {

        var comment =  commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));

        return comment;
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable long id, @RequestBody Comment data) {
        var comment =  commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));

        comment.setBody(data.getBody());
        comment.setPostId(data.getPostId());

        commentRepository.save(comment);

        return comment;
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable long id) {
        commentRepository.deleteById(id);
    }
}