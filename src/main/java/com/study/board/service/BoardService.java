package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    // @Autowired는 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.

    // 게시글 작성 처리
    public void boardWrite(Board board, MultipartFile file) throws Exception {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        UUID uuid = UUID.randomUUID(); // 랜덤성의 고유아이디를 부여
        String fileName = uuid + "_" + file.getOriginalFilename(); // 고유아이디 + _ +파일이름
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);
        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName); // static 하위로만 설정가능
        boardRepository.save(board);
    }

    // 게시글 리스트 불러오기
    public Page<Board> boardList(Pageable pageable) {return boardRepository.findAll(pageable);}
    
    // 게시글 콘텐츠 불러오기
    public Board boardDetail(Integer id){
        return boardRepository.findById(id).get();
    }

    // 게시글 삭제
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }

    // 게시글 검색
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {
        // repository에서 Pageable타입의 searchKeyword를 받아온다
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }
}
