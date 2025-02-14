package kmu.likelion.homepage.post.controller;

import jakarta.validation.Valid;
import kmu.likelion.homepage.post.entity.Post;
import kmu.likelion.homepage.post.entity.dto.req.CreatePostRequsetDTO;
import kmu.likelion.homepage.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam(name = "semester") short semester,
                                                  @RequestParam(name = "type") String type){
        if (type.isBlank()){
            ResponseEntity.badRequest();
        }

        return ResponseEntity.ok(postService.getAllPost(semester, type));
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createPost(@RequestPart("post") @Valid CreatePostRequsetDTO req,
                                             @RequestPart("image") List<MultipartFile> images) {
        return ResponseEntity.ok(postService.createPost(req, images));
    }

}
