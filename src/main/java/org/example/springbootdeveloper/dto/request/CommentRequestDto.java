package org.example.springbootdeveloper.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CommentRequestDto {
    private Long postId;
    private String content;
    private String commenter;
}
