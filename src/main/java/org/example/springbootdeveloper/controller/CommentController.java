package org.example.springbootdeveloper.controller;

import org.example.springbootdeveloper.dto.response.CommentResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
public class CommentController {

    // 의존성 주입
    // - 필드 주입, 메서드 주입, 생성자 주입
    @Autowired
    // @Autowired -> 필드 주입 방식
    // - 스프링이 필드 자체에 객체를 주입

    // @RequiredArgsConstructor -> 생성자 주입 방식 - 롬복 어노테이션
    // : final과 @NonNull 필드에 대해 자동으로 생성자를 생성
    private CommentService commentService;

    @PostMapping
    public ResponseDto<CommentResponseDto> createComment (@RequestBody CommentResponseDto dto){
        return commentService.createComment(dto);
    }

    @GetMapping("/post/{postId}")
    public ResponseDto<List<CommentResponseDto>> getCommentByPost(@PathVariable Long postId){
        return commentService.getCommentsByPost(postId);
    }

    @PutMapping("/{commentId}")
    public ResponseDto<CommentResponseDto> updateComment(
            @PathVariable Long commentId,
            @RequestBody String newContent
    ){
        return commentService.updateComment(commentId, newContent);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }


}
