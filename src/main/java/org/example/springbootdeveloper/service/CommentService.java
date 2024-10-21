package org.example.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.dto.response.CommentResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.entity.Comment;
import org.example.springbootdeveloper.entity.Post;
import org.example.springbootdeveloper.repository.CommentRepository;
import org.example.springbootdeveloper.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponseDto convertToDto(Comment comment){
        return new CommentResponseDto(comment.getId(), comment.getPost().getId() ,comment.getContent() ,comment.getCommenter());
    }

    public ResponseDto<CommentResponseDto> createComment(CommentResponseDto dto) {
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow();
        try {
            Comment comment = Comment.builder()
                            .content(dto.getContent())
                            .commenter(dto.getCommenter())
                            .post(post)
                            .build();
            commentRepository.save(comment);

            return ResponseDto.setSuccess("성공", convertToDto(comment));
        } catch (Exception e) {
            return ResponseDto.setFailed("오류가 발생했습니다: " + e.getMessage());
        }
    }

    public ResponseDto<List<CommentResponseDto>> getCommentsByPost(Long postId) {
        try {
            List<CommentResponseDto> comments = commentRepository.findByPostId(postId)
                    .stream().map(this::convertToDto)
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess("성공", comments);
        } catch (Exception e) {
            return ResponseDto.setFailed("오류가 발생했습니다: " + e.getMessage());
        }
    }

    public ResponseDto<CommentResponseDto> updateComment(Long commentId, String newContent) {
        try {
            Comment commnet = commentRepository.findById(commentId)
                            .orElseThrow();

            commnet.setContent(newContent);

            commentRepository.save(commnet);
            return ResponseDto.setSuccess("성공", convertToDto(commnet));
        } catch (Exception e) {
            return ResponseDto.setFailed("오류가 발생했습니다: " + e.getMessage());
        }
    }

    public void deleteComment(Long commentId) {
        try {
            commentRepository.deleteById(commentId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
