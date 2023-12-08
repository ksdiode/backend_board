package org.scoula.board.service;

import java.util.List;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.domain.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

        BoardVO board = mapper.get(no);
        List<BoardAttachmentVO> list  = mapper.getAttachmentList(no);
        board.setAttaches(list);
        return board;

    }


    @Transactional
    @Override
    public void create(BoardVO board, List<MultipartFile> files) {

        log.info("create......" + board);

        mapper.create(board);
        if(files == null) return;

        Long no = board.getNo();

        for(MultipartFile part: files) {
            if(part.isEmpty()) continue;
            BoardAttachmentVO attach = new BoardAttachmentVO(no, part);
            mapper.createAttachment(attach);
        }
    }


    @Transactional
    @Override
    public boolean update(BoardVO board, List<MultipartFile> files) {
        log.info("update......" + board);

        boolean result = mapper.update(board) == 1;

        if(files == null) return result;

        Long no = board.getNo();

        for(MultipartFile part: files) {
            if(part.isEmpty()) continue;
            BoardAttachmentVO attach = new BoardAttachmentVO(no, part);
            mapper.createAttachment(attach);
        }

        return result;
    }

    @Override
    public boolean delete(Long no) {

        log.info("delete...." + no);

        return mapper.delete(no) == 1;
    }


    public BoardAttachmentVO getAttachment(Long no) {
        return mapper.getAttachment(no);
    }

    public boolean deleteAttachment(Long no) {
        return mapper.deleteAttachment(no) == 1;
    }

}