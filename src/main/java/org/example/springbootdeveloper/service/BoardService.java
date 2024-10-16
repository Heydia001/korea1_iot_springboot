package org.example.springbootdeveloper.service;

import org.example.springbootdeveloper.dto.BoardDto;
import org.example.springbootdeveloper.entity.Board;
import org.example.springbootdeveloper.repository.BoardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    //전체 조회
    public List<BoardDto> getAllBoard() {
        try {
            List<Board> boards = boardRepository.findAll();
            List<BoardDto> boardDto = boards.stream()
                    .map(board -> new BoardDto(
                            board.getId()
                            , board.getWriter()
                            , board.getTitle()
                            , board.getContent()
                            , board.getCategory()
                    ))
                    .collect(Collectors.toList());
            return boardDto;
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while fetching boards", e
            );
        }
    }
    //단건 조회
    public BoardDto getBoardById(Long id) {
        try {
            Board board = boardRepository.findById(id)
                    .orElseThrow(() -> new Error("board ID로 찾기 실패: " + id));
            BoardDto boardDto = new BoardDto(
                    board.getId()
                    , board.getWriter()
                    , board.getTitle()
                    , board.getContent()
                    , board.getCategory()
            );
            return boardDto;
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                            "단건 조회 에러남", e
            );
        }
    }

    // 생성
    public BoardDto createBoard(BoardDto boardDto) {
        try {
            Board board = new Board(
                      boardDto.getId()
                    , boardDto.getWriter()
                    , boardDto.getTitle()
                    , boardDto.getContent()
                    , boardDto.getCategory()
            );

            Board savedBoard = boardRepository.save(board);

            return new BoardDto(
                    savedBoard.getId()
                    , savedBoard.getWriter()
                    , savedBoard.getTitle()
                    , savedBoard.getContent()
                    , savedBoard.getCategory()
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "생성 에러남", e
            );
        }
    }
    // 수정
    public BoardDto updateBoard(Long id, BoardDto boardDto){
        try {
            Board board = boardRepository.findById(id)
                    .orElseThrow(() -> new Error("board 업데이트 실패: " + id));

            board.setId(boardDto.getId());
            board.setWriter(boardDto.getWriter());
            board.setTitle(boardDto.getTitle());
            board.setContent(boardDto.getContent());
            board.setCategory(boardDto.getCategory());

            Board updatedBoard = boardRepository.save(board);

            return new BoardDto(
                    updatedBoard.getId()
                    , updatedBoard.getWriter()
                    , updatedBoard.getTitle()
                    , updatedBoard.getContent()
                    , updatedBoard.getCategory()
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "업데이트 보드 중 오류", e
            );
        }
    }

    // 삭제
    public void deleteBoard (Long id) {
        try {
            Board board = boardRepository.findById(id)
                            .orElseThrow(() ->
                    new Error("board found with id" + id)
            );
            boardRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "삭제 중 오류", e
            );
        }
    }

}