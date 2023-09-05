package service;

import com.example.demo.entity.Post;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PostState {
    public static final String COMMENTS_OK = "COMMENTS_OK"; // Defina o estado como String

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private LocalDateTime timestamp;
    private String status;

    public PostState() {
        this.timestamp = LocalDateTime.now();
    }

    public PostState(Post post, String status) {
        this.post = post;
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PostState{" +
                "id=" + id +
                ", post=" + post.getId() +
                ", timestamp=" + timestamp +
                ", status='" + status + '\'' +
                '}';
    }
}
