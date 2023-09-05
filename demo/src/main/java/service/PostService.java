package service;

import com.example.demo.entity.Comment;
import com.example.demo.entity.HistoryEntry;
import com.example.demo.entity.Post;
import dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final ExternalAPIClient externalAPIClient;
    private final PostRepository postRepository;
    private java.lang.String String;

    @Autowired
    public PostService(ExternalAPIClient externalAPIClient, PostRepository postRepository) {
        this.externalAPIClient = externalAPIClient;
        this.postRepository = postRepository;
    }

    public void fetchAndStorePosts() {
        PostDTO[] postDTOs = externalAPIClient.fetchPosts();

        for (PostDTO postDTO : postDTOs) {
            Post post = new Post(String);
            post.setTitle(postDTO.getTitle());
            post.setBody(postDTO.getBody());

            // Set initial state and other properties

            postRepository.save(post);
        }
    }

    public void processPost(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            // Simulação: Enriqueça o post com comentários e atualize o estado e histórico
            post.setComments(fetchCommentsForPost(post.getId()));
            post.setState(PostState.COMMENTS_OK);

            HistoryEntry historyEntry = new HistoryEntry();
            historyEntry.setState(PostState.COMMENTS_OK); // Correção
            historyEntry.setTimestamp(LocalDateTime.now());
            historyEntry.setPost(post);
            post.getHistory().add(historyEntry);

            postRepository.save(post);
        }
    }


    private List<Comment> fetchCommentsForPost(Long postId) {
        // Simulação: Busque os comentários da API externa e crie objetos Comment
        List<Comment> comments = new ArrayList<>();
        // ... (lógica para buscar comentários)
        return comments;
    }
}
