package com.metacoding.blog.board;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponse> findAll() {
        return boardRepository.findAll().stream()
                .map(board -> BoardResponse.from(board))
                .toList();
    }

    public BoardResponse findById(Integer id) {
        return BoardResponse.from(getBoard(id));
    }

    @Transactional // 메서드 시작에 트랜잭션이 열리고, 정상 종료면 커밋 · 예외가 나가면 롤백된다
    public BoardResponse save(BoardRequest request) {
        Board board = request.toEntity();
        boardRepository.save(board);
        return BoardResponse.from(board);
    }

    @Transactional
    public BoardResponse update(Integer id, BoardRequest request) {
        Board board = getBoard(id);
        board.update(request.title(), request.content()); // 더티 체킹 — 커밋 시점에 UPDATE
        return BoardResponse.from(board);
    }

    @Transactional
    public void delete(Integer id) {
        boardRepository.delete(getBoard(id));
    }

    private Board getBoard(Integer id) {
        Board board = boardRepository.findById(id);
        if (board == null) {
            throw new RuntimeException("게시글을 찾을 수 없습니다 : " + id);
        }
        return board;
    }
}
