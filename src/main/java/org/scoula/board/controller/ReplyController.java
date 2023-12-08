package org.scoula.board.controller;

import org.scoula.board.domain.ReplyVO;
import org.scoula.board.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board/{bno}/reply")
public class ReplyController {
    @Autowired
    private ReplyMapper mapper;

    @PostMapping("")
    public ReplyVO create(@RequestBody ReplyVO vo) {
        mapper.create(vo);
        return mapper.get(vo.getNo());
    }

    @PutMapping("/{no}")
    public ReplyVO update(@PathVariable Long no, @RequestBody ReplyVO vo) {
        mapper.update(vo);
        return mapper.get(vo.getNo());
    }

    @DeleteMapping("/{no}")
    public String delete(@PathVariable Long no) {
        mapper.delete(no);
        return "OK";
    }
}
