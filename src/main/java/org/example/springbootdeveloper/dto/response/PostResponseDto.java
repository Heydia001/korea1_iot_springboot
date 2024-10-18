package org.example.springbootdeveloper.dto.response;

import lombok.Data;

import java.util.List;
@Data
public class PostResponseDto {
        private Long id;
        private String title;
        private String content;
        private String author;

        private List<CommentResponseDto> comments;
}
