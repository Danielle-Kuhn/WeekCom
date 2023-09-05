package service;

import com.example.demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Você não precisa definir o método 'save' aqui,
    // pois ele já é herdado de JpaRepository.
}
