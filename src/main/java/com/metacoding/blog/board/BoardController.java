package com.metacoding.blog.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public ResponseEntity<?> list() {
        System.out.println("GET /boards 요청 → 목록 JSON 응답");
        return ResponseEntity.ok(boardService.findAll());
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") int id) {
        System.out.println("GET /boards/" + id + " 요청 → 상세 JSON 응답");
        return ResponseEntity.ok(boardService.findById(id));
    }

    @PostMapping("/boards")
    public ResponseEntity<?> save(@Valid @RequestBody BoardRequest request) {
        // @Valid가 있어야 BoardRequest에 붙인 검증 규칙이 실제로 동작한다
        System.out.println("POST /boards 요청 → title: " + request.title());
        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.save(request));
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody BoardRequest request) {
        System.out.println("PUT /boards/" + id + " 요청 → title: " + request.title());
        return ResponseEntity.ok(boardService.update(id, request));
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        System.out.println("DELETE /boards/" + id + " 요청");
        boardService.delete(id);
        return ResponseEntity.ok().build();
    }
}
