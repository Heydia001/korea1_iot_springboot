package org.example.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.common.constant.ApiMappingPattern;
import org.example.springbootdeveloper.dto.request.PostTodoRequestDto;
import org.example.springbootdeveloper.dto.request.PutTodoRequestDto;
import org.example.springbootdeveloper.dto.response.GetTodoListResponseDto;
import org.example.springbootdeveloper.dto.response.PostTodoResponseDto;
import org.example.springbootdeveloper.dto.response.PutTodoResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.repository.TodoRepository;
import org.example.springbootdeveloper.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.TODO)
public class TodoController {

    private final TodoService todoService;

    // === MenuController mapping pattern 설정 ===
    public static final String TODO_POST = "/";

    //    public static final String TODO_GET_MENU_ID = "/{id}";
    public static final String TODO_GET_LIST = "/list";
//    public static final String TODO_GET_MENU_CATEGORY = "/search/category";

    public static final String TODO_PUT = "/{id}";

    public static final String TODO_DELETE = "/{id}";
    private final TodoRepository todoRepository;

    @GetMapping
    public ResponseEntity<ResponseDto<List<GetTodoListResponseDto>>> getAllTodos() {
        try {
            ResponseDto<List<GetTodoListResponseDto>> todos = todoService.getAllTodos();
            return ResponseEntity.status(HttpStatus.OK).body(todos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @PostMapping
    public ResponseEntity<ResponseDto<PostTodoResponseDto>> createTodo(@RequestBody PostTodoRequestDto todo) {
        ResponseDto<PostTodoResponseDto> createdTodo = todoService.createTodo(todo);
        return ResponseEntity.status(HttpStatus.OK).body(createdTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PutTodoResponseDto>> updateTodoStatus(@PathVariable Long id, @RequestBody PutTodoRequestDto todo) {
        ResponseDto<PutTodoResponseDto> updatedTodo = todoService.updateTodoStatus(id, todo.getStatus());
        if (updatedTodo != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedTodo);
        } else {
            return ResponseEntity.status(404).body(ResponseDto.setFailed("Todo not found"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteTodoById(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        if (!todoRepository.existsById(id)) {
            return ResponseEntity.ok(ResponseDto.setSuccess("Todo deleted successfully", null));
        } else {
            return ResponseEntity.status(404).body(ResponseDto.setFailed("Todo not found"));
        }
    }
}