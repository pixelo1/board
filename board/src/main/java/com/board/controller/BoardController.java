package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.dao.BoardDAO;
import com.board.domain.BoardVO;
import com.board.service.BoardService;


@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	private BoardService service;
	
	//게시물 목록 (model은 controller 와 view를 연결해주는 역할)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void getList(Model model) throws Exception {
  
		  List list = null;
		  list = service.list();
		  model.addAttribute("list", list);
 }
	//게시물 작성
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getWirte() throws Exception{
		
	}
	//게시물 작성
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String postWirte(BoardVO vo) throws Exception {
		service.write(vo);
		
		return "redirect:/board/list";
		//모든 작업이 끝나면 게시물 목록 화면으로 이동시키는 리턴값
	}
}

