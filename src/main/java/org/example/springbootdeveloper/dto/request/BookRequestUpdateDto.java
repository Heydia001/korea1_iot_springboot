package org.example.springbootdeveloper.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springbootdeveloper.entity.Category;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class BookRequestUpdateDto {
    private String title;
    private String content;
    private Category category;
}
