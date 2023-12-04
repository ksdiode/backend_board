package org.scoula.board.mapper;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.domain.BoardVO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j
class BoardMapperTest {

    @Autowired
    private BoardMapper mapper;
    @Test
    void testGetList() {
        for(BoardVO board : mapper.getList()) {
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

}