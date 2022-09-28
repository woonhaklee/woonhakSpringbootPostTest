package com.study.board.repository;

import com.study.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
    // JpaRepository로 상속을 받고 타입지정 (Board라는 엔티티, ID 컬럼의 타입)
}
