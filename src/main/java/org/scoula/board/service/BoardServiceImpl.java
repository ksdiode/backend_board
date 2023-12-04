package org.scoula.board.service;

import java.util.List;

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
    public List<BoardVO> getList() {
        return null;
    }

    @Override
    public BoardVO get(Long no) {
        return null;
    }

    @Override
    public void create(BoardVO board) {

    }

    @Override
    public boolean update(BoardVO board) {
        return false;
    }

    @Override
    public boolean delete(Long no) {
        return false;
    }
}