package org.scoula.board.mapper;

import org.scoula.board.domain.CommentVO;

import java.util.List;

public interface CommentMapper {
    List<CommentVO> readAll(Long bno);
    CommentVO get(Long no);

    void create(CommentVO vo);
    void update(CommentVO vo);
    void delete(Long no);
}
