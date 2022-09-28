package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    // 게시글 작성
     @GetMapping("/board/write") //localhost:9090/board/write로 주소연결
     public String boardWriteForm(){ //boardWrite을 불러올 boardWriteForm 함수 작성
        return "board-write";
     }

     @PostMapping("/board/writepro")
     public String boardWritePro(Board board){
        boardService.write(board);
        return "";
     }

     // 게시글 리스트
     @GetMapping("/board/list")
     public String boardList(Model model) {
         model.addAttribute( "list", boardService.boardList());
        return "board-list";
    }

    // 게시글 디테일
    @GetMapping("/board/detail") // localhost:9090/board/detail?id=1
    public String boardDetail(Model model, Integer id) {
         model.addAttribute("board", boardService.boardDetail(id));
         return  "board-detail";
    }
}
