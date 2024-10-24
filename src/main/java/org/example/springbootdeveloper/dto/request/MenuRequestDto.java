package org.example.springbootdeveloper.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class MenuRequestDto {
    @NotNull
    private String name;

    @NotNull
    private String description;

    @Min(0)
    @NotNull
    private int price;

    @NotNull
    private boolean isAvailable;

    @NotNull
    private String category;
    private String size;

}
