package service;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public void fetchAndSaveComments() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://exemplo.com/api/comments";  // URL da API externa (fictícia)

        Comment[] comments = restTemplate.getForObject(apiUrl, Comment[].class);

        for (Comment comment : comments) {
            // Simulando o relacionamento com o post (substitua pela lógica real)
            Long postId = comment.getPostId();
            Post post = postRepository.findById(postId).orElse(null);

            if (post != null) {
                comment.setPost(post);
                commentRepository.save(comment);
            }
        }
    }
}
