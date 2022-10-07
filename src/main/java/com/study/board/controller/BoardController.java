package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
// 컨트롤러 선언
// 템플릿(캄포넌트)와 서버연동

public class BoardController {
    @Autowired
    private BoardService boardService;

    // 게시글 작성
     @GetMapping("/board/write") //localhost:9090/board/write로 주소연결
     public String boardWriteForm(){ //boardWrite을 불러올 boardWriteForm 함수 작성
        return "board-write";
     }

     @PostMapping("/board/writepro")
     public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception{

        boardService.boardWrite(board, file);
        model.addAttribute("boardMessage", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "board-message";
     }

     // 게시글 리스트
     @GetMapping("/board/list")
     public String boardList( Model model,
          // 페이징
          @PageableDefault(
                  page = 0,
                  size = 10,
                  sort = "id",
                  direction = Sort.Direction.DESC) Pageable pageable,
          // 검색
          String searchKeyword
          ) {
        Page<Board> list = null;

         // 검색
         if(searchKeyword == null) {
             list = boardService.boardList(pageable);
         }else{
             list = boardService.boardSearchList(searchKeyword, pageable);
         }

        // 페이징
        int nowPage = list.getPageable().getPageNumber() + 1; // 페이지가 0부터 시작하기 떄문에 출력시 1을 위한 설정
        // int nowPage = pageable().getPageNumber();
        int startPage = Math.max(nowPage - 4, 1); //  Math 자바문법을 이용한 최대 1 이하로 출력시 지정
        int endPage = Math.min(nowPage + 5, list.getTotalPages()); // Math 자바문법을 이용한 전체 리스트 이하로 출력 지정

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board-list";
    }

    // 게시글 디테일
    @GetMapping("/board/detail") // localhost:9090/board/detail?id=1
    public String boardDetail(Model model, Integer id) {
         model.addAttribute("board", boardService.boardDetail(id));
         return  "board-detail";
    }

    // 게시글 삭제
    @GetMapping("/board/delete")
    public String boardDelete(Integer id, Model model) {
        boardService.boardDelete(id);
        model.addAttribute("boardMessage", "글 삭제가 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "board-message";
    }

    // 게시글 수정
    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("board", boardService.boardDetail(id));

        return "board-modify";
    }

    // 게시글 수정 업데이트
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model, MultipartFile file) throws Exception {
        Board boardTemp = boardService.boardDetail(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());
        boardService.boardWrite(boardTemp, file);

        model.addAttribute("boardMessage", "글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "board-message";
    }
}
