package org.example.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.dto.request.PostRequestDto;
import org.example.springbootdeveloper.dto.response.PostResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PostController {

    @Autowired
    private PostService postService;

    //CRUD 명시
    @PostMapping
    public ResponseDto<PostResponseDto> createPost(@RequestBody PostResponseDto dto){
        return PostService.createPost(dto);
    }

    @GetMapping
    public ResponseDto<List<PostResponseDto>> getPostsAll(@PathVariable Long postId){
        return postService.getAllPosts(postId);
    }

    @GetMapping("{postId}")
    public ResponseDto<PostResponseDto> getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PutMapping("{postId}")
    public ResponseDto<PostResponseDto> updatePost(
            @PathVariable Long postId
            , @RequestBody PostRequestDto dto
    ){
        return postService.updatePost(postId, dto);
    }

    @DeleteMapping("{postId}")
    public ResponseDto<Void> deletePost(@PathVariable Long postId){
        return postService.deletePost(postId);
    }

}
