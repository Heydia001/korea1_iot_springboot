package org.example.springbootdeveloper.controller;

import org.example.springbootdeveloper.dto.BoardDto;
import org.example.springbootdeveloper.dto.StudentDto;
import org.example.springbootdeveloper.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public List<BoardDto> getAllStudents() {
        return boardService.getAllBoard();
    }

    @GetMapping("/{id}")
    public BoardDto getBoardByID(@PathVariable Long id){
        return boardService.getBoardById(id);
    }

    @PutMapping("{id}")
    public BoardDto updateBoard(@PathVariable Long id, @RequestBody BoardDto boardDto){
        return boardService.updateBoard(id, boardDto);
    }

    @PostMapping
    public BoardDto createBoard(@RequestBody BoardDto boardDto){
        return boardService.createBoard(boardDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}
