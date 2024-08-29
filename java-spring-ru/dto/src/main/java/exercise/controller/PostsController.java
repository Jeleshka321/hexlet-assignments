package exercise.controller;

import exercise.dto.CommentDTO;
import exercise.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import exercise.dto.PostDTO;
import exercise.repository.PostRepository;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.model.Comment;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentsRepository;

    @GetMapping(path = "")
    public List<PostDTO> index() {
        var posts = postRepository.findAll();
        var result = posts.stream()
                .map(this::toPostDTO)
                .toList();
        return result;
    }

    @GetMapping(path = "/{id}")
    public PostDTO show(@PathVariable long id) {

        var post =  postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        return toPostDTO(post);
    }

    private PostDTO toPostDTO(Post post) {
        var dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        var comments = commentsRepository.findByPostId(post.getId());
        var commentsDto = comments.stream()
                .map((comment) -> {
                    var commentDto = new CommentDTO();
                    commentDto.setBody(comment.getBody());
                    commentDto.setId(comment.getId());
                    return commentDto;
                })
                .toList();
        dto.setComments(commentsDto);
        return dto;
    }
}