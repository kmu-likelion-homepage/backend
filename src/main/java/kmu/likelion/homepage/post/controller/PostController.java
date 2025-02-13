package kmu.likelion.homepage.post.controller;

import kmu.likelion.homepage.post.entity.dto.res.PostListResponseDTO;
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

    @GetMapping("/all")
    public ResponseEntity<List<PostListResponseDTO>> getAllPosts(){

    }

}
