package org.scoula.board.service;

import java.util.List;

import org.scoula.board.domain.BoardVO;
import org.scoula.domain.Criteria;

public interface BoardService {
    public int getTotal(Criteria cri);

    public List<BoardVO> getList(Criteria cri);

    public BoardVO get(Long no);

    public void create(BoardVO board);

    public boolean update(BoardVO board);

    public boolean delete(Long no);

}
