package org.example.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.dto.request.PostRequestDto;
import org.example.springbootdeveloper.dto.response.CommentResponseDto;
import org.example.springbootdeveloper.dto.response.PostResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.entity.Post;
import org.example.springbootdeveloper.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto convertToDto (Post post){
        List<CommentResponseDto> commentDtos = post.getComments()
                .stream().map(comment -> new CommentResponseDto(comment.getId(), post.getId(), comment.getContent(), comment.getCommenter()))
                        .collect(Collectors.toList());

        return new PostResponseDto(
                post.getId()
                , post.getTitle()
                , post.getAuthor()
                , post.getContent()
                , commentDtos
        );
    }

    public ResponseDto<PostResponseDto> createPost(PostRequestDto dto) {
        try {
            Post post = Post.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .author(dto.getAuthor())
                    .build();
            postRepository.save(post);
            return ResponseDto.setSuccess("게시글이 정상적으로 등록되었습니다.", convertToDto(post));
        } catch (Exception e) {
            return ResponseDto.setFailed("게시글 등록 중 오류가 발생했습니다: " + e.getMessage());
        }
    }


    public ResponseDto<List<PostResponseDto>> getAllPosts() {
        try {
            List<PostResponseDto> posts = postRepository.findAll()
                    .stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess("모든 게시물 조회 성공", posts);

        } catch (Exception e) {
            return ResponseDto.setFailed("모든 게시물 조회 실패");
        }
    }

    public ResponseDto<PostResponseDto> getPostById(Long postId) {
        try{
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다: " + id));
            return ResponseDto.setSuccess("게시글이 정상적으로 조회되었습니다.", convertToDto(post));
        } catch (Exception e) {
            return ResponseDto.setFailed("게시글 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    public ResponseDto<List<PostResponseDto>> getPostByAuthor (String author){
        try {
            List<PostResponseDto> posts = postRepository.findByAuthor(author)
                    .stream().map(this::convertToDto).collect(Collectors.toList());
            return ResponseDto.setSuccess("정상적으로 작가로 게시물 조회되었습니다.", posts);
        } catch (Exception e) {
            return ResponseDto.setFailed("작가로 게시물 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }


    public ResponseDto<PostResponseDto> updatePost(Long postId, PostRequestDto dto) {
        try {
            Post post = postRepository.findById(postId)
                    .orElseThrow(()-> new IllegalArgumentException("게시물 못찾음" + postId));
            post.setTitle(dto.getTitle());
            post.setAuthor(dto.getAuthor());
            post.setContent(dto.getContent());

            postRepository.save(post);

            return ResponseDto.setSuccess("성공", convertToDto(post));

        } catch (Exception e) {
            return ResponseDto.setFailed("실패");
        }
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}