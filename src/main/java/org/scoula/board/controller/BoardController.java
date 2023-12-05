package org.scoula.board.controller;

import lombok.extern.log4j.Log4j;
import org.scoula.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

}
