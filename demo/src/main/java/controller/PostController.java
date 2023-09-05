package controller;

import com.example.demo.entity.Post;
import dto.PostResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PostRepository;
import service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    @PostMapping("/fetch")
    public String fetchAndStorePosts() {
        postService.fetchAndStorePosts();
        return "Posts fetched and stored.";
    }

    public PostController(PostService postService, PostRepository postRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
    }


        @PostMapping("/{postId}/process")
        public ResponseEntity<String> processPost(@PathVariable Long postId) {
            postService.processPost(postId);
            return ResponseEntity.ok("Post processing initiated.");
        }

        @DeleteMapping("/{postId}")
        public ResponseEntity<String> disablePost(@PathVariable Long postId) {
            // Implemente a lógica para desativar a postagem
            // Atualize o estado e histórico
            return ResponseEntity.ok("Post disabled.");
        }

        @PutMapping("/{postId}/reprocess")
        public ResponseEntity<String> reprocessPost(@PathVariable Long postId) {
            // Implemente a lógica para reprocessar a postagem
            // Atualize o estado e histórico
            return ResponseEntity.ok("Post reprocessed.");
        }
    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDTO> responseDTOs = posts.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    private PostResponseDTO convertToResponseDTO(Post post) {
        PostResponseDTO responseDTO = new PostResponseDTO();
        responseDTO.setTitle(post.getTitle());
        responseDTO.setBody(post.getBody());
        responseDTO.setComments(post.getComments());
        responseDTO.setHistory(post.getHistory());
        return responseDTO;
    }

}


