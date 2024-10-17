package org.example.springbootdeveloper.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springbootdeveloper.entity.Category;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BookResponseDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private Category category;
}
