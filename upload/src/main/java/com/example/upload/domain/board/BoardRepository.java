package com.example.upload.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Modifying
    @Query("update Board b set b.view = b.view + 1 where b.id = :id")
    int updateView(Long id);

    Optional<Board> findById(Long boardId);

}
