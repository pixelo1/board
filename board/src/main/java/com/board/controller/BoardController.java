package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	//게시물 조회 조회라GET 사용
	//@RequestParam([문자열])을 이용하면, 주소에 있는 특정한 값을 가져올 수 있다 
	//위 스크린샷에서는 주소에서 bno를 찾아 그 값을 int bno에 넣어줍니다.
	//BoardVO를 이용하여 서비스(service)에서 데이터를 받고, 모델(model)을 이용하여 뷰(view)에 데이터를 넘겨준다 
	//이때, 넘겨주는 모델의 이름은 view
	// 게시물 조회
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getView(@RequestParam("bno") int bno, Model model) throws Exception {
		
		BoardVO vo = service.view(bno);
		
		model.addAttribute("view", vo);

	}
	
	//게시물 수정 (컨트롤러에 게시물 수정용 GET 메서드 추가)게시물 조회구현 메서드 그대로 이용
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void getModify(@RequestParam("bno") int bno, Model model) throws Exception {
		
		BoardVO vo = service.view(bno);
		
		model.addAttribute("view", vo);
	}
	
	//게시물 수정 post 메서드 추가 이거 없이해서 게시판에서 바로 수정 하면 405 에러남
	//service.modify(vo); 에서 뷰에서 컨트롤러로 넘어온 데이터 (BoardVO)를 이용해 수정 한뒤
	//return "redirect:/board/view?bno=" + vo.getBno(); 에서 현재 bno에 해당되는 조회 페이지로 이동됨
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String postModify(BoardVO vo) throws Exception {
		
		service.modify(vo);
		
		return "redirect:/board/view?bno=" + vo.getBno();
	}
	
}

