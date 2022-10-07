package com.study.board.repository;

import com.study.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
    // JpaRepository로 DB상속을 받고 타입지정 (Board라는 엔티티, ID 컬럼의 타입)

    //검색 메소드
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
    // findByTitleContaining 메소드 추가로 타이틀에서 검색키워드를 찾아서 담아올 수 있다.

}
