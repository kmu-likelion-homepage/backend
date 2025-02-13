package kmu.likelion.homepage.post.service;

import kmu.likelion.homepage.post.entity.PostType;
import kmu.likelion.homepage.post.entity.dto.req.CreatePostRequsetDTO;
import kmu.likelion.homepage.post.entity.dto.req.GetAllRequestDTO;
import kmu.likelion.homepage.post.entity.dto.res.PostListResponseDTO;
import kmu.likelion.homepage.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostListResponseDTO> getAllPost(GetAllRequestDTO req){
        PostType postType = PostType.valueOf(req.getType().toUpperCase());

        List<PostListResponseDTO> posts = postRepository.findAllBySemesterAndType(req.getSemester(), postType).stream()
                .map(post -> {
                    if (postType==PostType.PROJECT) {
                        return PostListResponseDTO.builder()
                                .post_id(post.getId())
                                .type(post.getType())
                                .semester(post.getSemester())
                                .title(post.getTitle())
                                .award(post.getAward())
                                .competitionName(post.getCompetitionName())
                                .build();
                    } else if (postType==PostType.ACTIVITY) {
                        return PostListResponseDTO.builder()
                                .post_id(post.getId())
                                .type(post.getType())
                                .semester(post.getSemester())
                                .title(post.getTitle())
                                .subtitle(post.getSubtitle())
                                .build();
                    }
                    return null;
                }).collect(Collectors.toList());
        return posts;
    }

    @Transactional
    public String createPost(CreatePostRequsetDTO req){
        PostType postType = PostType.valueOf(req.getType().toUpperCase());

    }
}
