package org.example.springbootdeveloper.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class PutTodoRequestDto {
    @NotNull
    private Long id;

    @NotNull
    private String task;

    @NotNull
    private Boolean status;

}
