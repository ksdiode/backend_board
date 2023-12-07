package org.scoula.board.controller;

import lombok.extern.log4j.Log4j;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.service.BoardService;
import org.scoula.domain.Criteria;
import org.scoula.domain.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@Log4j
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

    @GetMapping("/list")
    public void list(@ModelAttribute("cri") Criteria cri, Model model) {
        log.info("list: " + cri);
        model.addAttribute("list", service.getList(cri));

        int total = service.getTotal(cri);
        log.info("total: " + total);

    //  model.addAttribute("pageMaker", new PageDTO(cri, 123);
        model.addAttribute("pageMaker", new PageDTO(cri, total));
    }


    @GetMapping("/create")
    public void create(@ModelAttribute("board") BoardVO board) {
        log.info("create");
    }



    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("board") BoardVO board,
                         Errors errors,
                         RedirectAttributes ra,
                         Principal principal) {

        log.info("create: " + board);

        if(errors.hasErrors()) {
            return "board/create";
        }

        String username = principal.getName();
        board.setWriter(username);
        service.create(board);

        ra.addFlashAttribute("result", board.getNo());

        return "redirect:/board/list";
    }

    @GetMapping({ "/get", "/update" })
    public void get(@ModelAttribute("cri") Criteria cri, @RequestParam("no") Long no, Model model) {

        log.info("/get or update");
        model.addAttribute("board", service.get(no));
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("cri") Criteria cri,
                         @Valid @ModelAttribute("board") BoardVO board,
                         Errors errors,
                         RedirectAttributes ra) {
        log.info("update:" + board);

        if(errors.hasErrors()) {
            return "board/update";
        }

        if (service.update(board)) {
            ra.addFlashAttribute("result", "success");
        }
        return "redirect:" + cri.getLink("/board/get", board.getNo());
    }


    @PostMapping("/delete")
    public String delete(@ModelAttribute("cri") Criteria cri, @RequestParam("no") Long no, RedirectAttributes ra) {

        log.info("delete..." + no);
        if (service.delete(no)) {
            ra.addFlashAttribute("result", "success");
        }

        return "redirect:" + cri.getLink("/board/list");
    }

}
