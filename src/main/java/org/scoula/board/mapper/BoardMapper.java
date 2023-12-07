package org.scoula.board.mapper;

import org.apache.ibatis.annotations.Select;
import org.scoula.board.domain.BoardVO;
import org.scoula.domain.Criteria;

import java.util.List;

public interface BoardMapper {
    //@Select("select * from tbl_board")
    public List<BoardVO> getList(Criteria cri);

    public BoardVO get(Long no);

    public void create(BoardVO board);

    public int update(BoardVO board);

    public int delete(Long no);

}
