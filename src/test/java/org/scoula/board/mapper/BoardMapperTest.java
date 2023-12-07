package org.scoula.board.mapper;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.domain.BoardVO;
import org.scoula.config.RootConfig;
import org.scoula.domain.Criteria;
import org.scoula.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class, SecurityConfig.class })
@Log4j
class BoardMapperTest {

    @Autowired
    private BoardMapper mapper;
    @Test
    void testGetList() {
        Criteria cri = new Criteria();
        //10개씩 3페이지
        cri.setPageNum(3);
        cri.setAmount(10);

        for(BoardVO board : mapper.getList(cri)) {
            log.info(board);
        }

    }

    @Test
    @DisplayName("BoardMapper의 게시글 읽기")
    public void testGet() {
        // 존재하는 게시물 번호로 테스트
        BoardVO board = mapper.get(5L);

        log.info(board);
    }

    @Test
    @DisplayName("BoardMapper의 새글 작성")
    public void testCreate() {

        BoardVO board = new BoardVO();
        board.setTitle("새로 작성하는 글");
        board.setContent("새로 작성하는 내용");
        board.setWriter("user0");

        mapper.create(board);

        log.info(board);
    }

    @Test
    @DisplayName("BoardMapper의 글 수정")
    public void testUpdate() {

        BoardVO board = new BoardVO();
        board.setNo(5L);
        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");
        board.setWriter("user00");

        int count = mapper.update(board);

        log.info("UPDATE COUNT: " + count);
    }

    @Test
    @DisplayName("BoardMapper의 글 삭제")
    public void testDelete() {
        log.info("DELETE COUNT: " + mapper.delete(3L));
    }


    @Test
    public void testSearch() {
        Criteria cri = new Criteria();

        cri.setKeyword("새로");
        cri.setType("TC");	// 제목, 내용에서 검색

        List<BoardVO> list = mapper.getList(cri);

        for(BoardVO board: list) {
            log.info(board);
        }
    }

}