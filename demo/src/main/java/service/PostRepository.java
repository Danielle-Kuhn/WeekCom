package service;

import com.example.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByStatus(String status);

    List<Post> findByAuthor(String author);

    List<Post> findByStatusAndAuthor(String status, String author);


    List<Post> findByTitleContainingIgnoreCase(String keyword);
}
