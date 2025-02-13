package kmu.likelion.homepage.post.service;

import kmu.likelion.homepage.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

}
