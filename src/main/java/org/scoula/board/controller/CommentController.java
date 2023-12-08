package org.scoula.board.controller;


import org.scoula.board.domain.CommentVO;
import org.scoula.board.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board/{bno}/comment")
public class CommentController {
    @Autowired
    CommentMapper mapper;

    @GetMapping("")
    public List<CommentVO> readComments(@PathVariable Long bno) {
        return mapper.getList(bno);
    }

    @GetMapping("/{no}")
    public CommentVO readComment(@PathVariable Long bno, @PathVariable Long no) {
        return mapper.get(no);
    }

    @PostMapping("")
    public CommentVO create(@RequestBody CommentVO vo) {

        mapper.create(vo);
        return mapper.get(vo.getNo());
    }

    @PutMapping("/{no}")
    public CommentVO update(	@PathVariable Long no, @RequestBody CommentVO vo) {
        System.out.println("==> " + vo);
        mapper.update(vo);
        return vo;
    }

    @DeleteMapping("/{no}")
    public String delete(@PathVariable Long no) {
        System.out.println("delete ==>" + no);
        mapper.delete(no);
        return "OK";
    }
}
