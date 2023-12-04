package org.scoula.board.service;

import java.util.List;

import org.scoula.board.domain.BoardVO;

public interface BoardService {

    public List<BoardVO> getList();

    public BoardVO get(Long no);

    public void create(BoardVO board);

    public boolean update(BoardVO board);

    public boolean delete(Long no);

}
