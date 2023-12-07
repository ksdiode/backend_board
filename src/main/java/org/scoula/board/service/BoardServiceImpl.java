package org.scoula.board.service;

import java.util.List;

import org.scoula.domain.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardMapper mapper;

    @Override
    public int getTotal(Criteria cri) {
        log.info("get total count");

        return mapper.getTotalCount(cri);
    }

    @Override
    public List<BoardVO> getList(Criteria cri) {

        log.info("get List with criteria: " + cri);

        return mapper.getList(cri);
    }



    @Override
    public BoardVO get(Long no) {

        log.info("get......" + no);

        return mapper.get(no);

    }


    @Override
    public void create(BoardVO board) {

        log.info("create......" + board);

        mapper.create(board);
    }


    @Override
    public boolean update(BoardVO board) {

        log.info("update......" + board);

        return mapper.update(board) == 1;
    }

    @Override
    public boolean delete(Long no) {

        log.info("delete...." + no);

        return mapper.delete(no) == 1;
    }

}