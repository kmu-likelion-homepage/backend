package kmu.likelion.homepage.post.controller;

import jakarta.validation.Valid;
import kmu.likelion.homepage.post.entity.dto.req.GetAllRequestDTO;
import kmu.likelion.homepage.post.entity.dto.res.PostListResponseDTO;
import kmu.likelion.homepage.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/all")
    public ResponseEntity<List<PostListResponseDTO>> getAllPosts(@Valid GetAllRequestDTO req){
        if (req.getType().isBlank()){
            ResponseEntity.badRequest();
        }

        return ResponseEntity.ok(postService.getAllPost(req));
    }

}
