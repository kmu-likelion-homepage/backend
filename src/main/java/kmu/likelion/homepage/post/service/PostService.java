package kmu.likelion.homepage.post.service;

import kmu.likelion.homepage.post.entity.Post;
import kmu.likelion.homepage.post.entity.PostImage;
import kmu.likelion.homepage.post.entity.PostType;
import kmu.likelion.homepage.post.entity.dto.req.CreatePostRequsetDTO;
import kmu.likelion.homepage.post.repository.PostImageRepository;
import kmu.likelion.homepage.post.repository.PostRepository;
import kmu.likelion.homepage.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Primary
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;
    private final S3Service s3Service;

    public List<Post> getAllPost(short semester, String type){
        PostType postType = PostType.valueOf(type.toUpperCase());

        return postRepository.findAllBySemesterAndType(semester, postType);
    }

    @Transactional
    public String createPost(CreatePostRequsetDTO req, List<MultipartFile> files) {
        PostType postType = PostType.valueOf(req.getType().toUpperCase());
        Post post = Post.builder()
                .type(postType)
                .semester(req.getSemester())
                .title(req.getTitle())
                .subtitle(postType == PostType.ACTIVITY ? req.getSubtitle() : null)
                .award(postType == PostType.PROJECT ? req.getAward() : null)
                .competitionName(postType == PostType.PROJECT ? req.getCompetitionName() : null)
                .build();

        Post savePost = postRepository.save(post);

        if(savePost.getPostImages() == null){
            savePost.setPostImages(new ArrayList<>());
        }

        if (files != null && !files.isEmpty()) {
            List<PostImage> postImages = files.stream()
                    .map(file -> {
                        try {
                            String imageUrl = s3Service.uploadFile(file);
                            return PostImage.builder()
                                    .post(savePost)
                                    .imageUrl(imageUrl)
                                    .build();
                        } catch (IOException e) {
                            throw new RuntimeException("파일 업로드 중 오류 발생", e);
                        }
                    })
                    .collect(Collectors.toList());

            post.getPostImages().addAll(postImages);
            postImageRepository.saveAll(postImages);
        }

        return "성공";
    }
}
