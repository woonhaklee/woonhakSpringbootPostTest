package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    // @Autowired는 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.

    // 게시글 작성 처리
    public void write(Board board){
        boardRepository.save(board);
    }

    // 게시글 리스트 불러오기
    public List<Board> boardList() {return boardRepository.findAll();}
    
    // 게시글 콘텐츠 불러오기
    public Board boardDetail(Integer id){
        return boardRepository.findById(id).get();
    }
}
