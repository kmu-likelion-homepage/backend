package kmu.likelion.homepage.post.service;

import kmu.likelion.homepage.post.entity.ActivityPost;
import kmu.likelion.homepage.post.entity.Post;
import kmu.likelion.homepage.post.entity.PostType;
import kmu.likelion.homepage.post.entity.ProjectPost;
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

    @Transactional
    public List<PostListResponseDTO> getAllPost(short semester, String type){
        PostType postType = PostType.valueOf(type.toUpperCase());

        List<PostListResponseDTO> posts = postRepository.findAllBySemesterAndType(semester, postType).stream()
                .map(post -> {
                    if (post instanceof ProjectPost) {
                        ProjectPost project = (ProjectPost) post;
                        return PostListResponseDTO.builder()
                                .post_id(project.getId())
                                .type(project.getType())
                                .semester(project.getSemester())
                                .title(project.getTitle())
                                .award(project.getAward())
                                .competitionName(project.getCompetitionName())
                                .build();
                    } else if (post instanceof ActivityPost) {
                        ActivityPost activity = (ActivityPost) post;
                        return PostListResponseDTO.builder()
                                .post_id(activity.getId())
                                .type(activity.getType())
                                .semester(activity.getSemester())
                                .title(activity.getTitle())
                                .subtitle(activity.getSubtitle())
                                .build();
                    }
                    return null;
                }).collect(Collectors.toList());
        return posts;
    }
}
