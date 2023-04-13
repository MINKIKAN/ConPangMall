package com.console.mall.service;

import com.console.mall.dto.BoardDTO;
import com.console.mall.entitiy.Board;
import com.console.mall.respository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardDTO> findAll(){
        return boardRepository.findAll().stream()
                .sorted(Comparator.comparing(Board::getDate).reversed())
                .map(BoardDTO::new)
                .collect(Collectors.toList());
    }

    public BoardDTO save (BoardDTO boardDTO){
        Long adminId = 1L;
        Board board = new Board(boardDTO.getTitle(), boardDTO.getContent(), boardDTO.getWriter(), LocalDateTime.now());
        boardRepository.save(board);
        return new BoardDTO(board);
    }


    public void createDummyData() {
        Long adminId = 1L;
        Board board1 = new Board(adminId, "공지1","내용1", "관리자", LocalDateTime.now());
        boardRepository.save(board1);
    }

    public void deleteNotice(Long boardId){
        boardRepository.deleteById(boardId);
    }
}
