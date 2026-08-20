package com.metacoding.blog.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// 요청 DTO — Java 21 record: 필드·생성자·getter가 한 줄로 끝나고 세터가 아예 없다
// 검증 규칙이 사는 자리이기도 하다 — 엔티티로 받던 6차시에는 이 규칙을 붙일 곳이 없었다
public record BoardRequest(
        @NotBlank(message = "제목은 비어 있을 수 없습니다") @Size(max = 20, message = "제목은 20자 이하여야 합니다") String title,
        @Size(max = 100, message = "내용은 100자 이하여야 합니다") String content) {

    // 변환의 자리 ① — 요청 DTO가 엔티티를 만든다
    public Board toEntity() {
        return Board.builder().title(title).content(content).build();
    }
}
