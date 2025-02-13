package kmu.likelion.homepage.post.repository;

import kmu.likelion.homepage.post.entity.Post;
import kmu.likelion.homepage.post.entity.PostType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySemesterAndType(short semester, PostType type);
}
