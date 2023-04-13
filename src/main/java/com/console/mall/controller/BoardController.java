package com.console.mall.controller;

import com.console.mall.dto.BoardDTO;
import com.console.mall.entitiy.Board;
import com.console.mall.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

//    @Autowired
//    public BoardController(BoardService boardService){
//        this.boardService = boardService;
//    }

    @GetMapping("/notice/new")
    public String create(){
//        boardService.createDummyData();
        return "notices/noticeFrom";
    }

    @PostMapping("/notice/save")
    @ResponseBody
    public ResponseEntity<?> save(@RequestParam("title") String title,
                                  @RequestParam("writer") String writer,
                                  @RequestParam("content") String content){
        try {
            BoardDTO boardDTO = new BoardDTO(new Board());
            boardDTO.setTitle(title);
            boardDTO.setWriter(writer);
            boardDTO.setContent(content);
            boardService.save(boardDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/notice/delete")
    public String delete(@RequestParam("boardId") Long boardId){
        boardService.deleteNotice(boardId);
        return "redirect:/notice";
    }




//    @GetMapping("/notice")
//    public String showNoticeList(Model model, HttpServletRequest request) {
//
//        // 세션에 저장된 모든 값을 콘솔 창에 출력
//        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
//        while (attributeNames.hasMoreElements()) {
//            String attributeName = attributeNames.nextElement();
//            Object attributeValue = request.getSession().getAttribute(attributeName);
//            System.out.println("Attribute: " + attributeName + ", Value: " + attributeValue);
//        }
//        return "notice";
//    }

    @GetMapping("/notice")
    public String list(Model model){
        model.addAttribute("list", boardService.findAll());
        return "notice";
    }



}
