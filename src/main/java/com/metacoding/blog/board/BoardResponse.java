package com.metacoding.blog.board;

import java.sql.Timestamp;

// 응답 DTO — API 응답 모양을 테이블 구조에서 분리한다
public record BoardResponse(Integer id, String title, String content, Timestamp createdAt) {

    // 변환의 자리 ② — 엔티티를 응답 DTO로 감싼다
    public static BoardResponse from(Board board) {
        return new BoardResponse(board.getId(), board.getTitle(), board.getContent(), board.getCreatedAt());
    }
}
