package org.scoula.board.service;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.domain.BoardVO;
import org.scoula.config.RootConfig;
import org.scoula.domain.Criteria;
import org.scoula.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class} )
@Log4j
public class BoardServiceImplTest {

    @Autowired
    private BoardService service;

    @Test
    public void testGetList() {

        for(BoardVO board: service.getList(new Criteria(2, 10))) {
            log.info(board);
        }


    }

    @Test
    public void testGet() {

        log.info(service.get(10L));
    }


    @Test
    public void testCreate() {

        BoardVO board = new BoardVO();
        board.setTitle("새로 작성하는 글");
        board.setContent("새로 작성하는 내용");
        board.setWriter("user1");

        service.create(board, null);

        log.info("생성된 게시물의 번호: " + board.getNo());
    }

    @Test
    public void testUpdate() {

        BoardVO board = service.get(10L);

        if (board == null) {
            return;
        }

        board.setTitle("제목 수정합니다.");
        log.info("update RESULT: " + service.update(board, null));
    }

    @Test
    public void testDelete() {
        // 게시물 번호의 존재 여부를 확인하고 테스트할 것
        log.info("delete RESULT: " + service.delete(2L));

    }

}