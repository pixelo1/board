package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardVO;
import com.board.domain.Page;
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
	
	//게시물 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDelete(@RequestParam("bno") int bno) throws Exception {
		
		service.delete(bno);
		
		return "redirect:/board/list";
	} // 게시물 삭제시 리턴으로 게시물 목록 페이지로 이동시킴
	
	// 게시물 목록 + 페이징 추가
	@RequestMapping(value = "/listpage", method = RequestMethod.GET)
	public void getListPage(Model model) throws Exception {
	  
	 List list = null; 
	 list = service.list();
	 model.addAttribute("list", list);   
	}
	
	
	//게시물 목록 + 페이징 추가  매개변수로 num은 페이지 번호
		//1. 게시물 총갯수를 구하고
		//2. 한 페이지당 출력할 게시물 갯수를 정하고(10개)
		//3. 하단에 표시할 페이징 번호의 갯루를 구하고(소수점은 올림)
		//4. 현재 페이지를 기준으로 10개의 데이터를 출력한다
	
	// 게시물 목록 + 페이징 추가
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void getListPage(Model model, @RequestParam("num") int num) throws Exception {
	
		// ctrl+shift+o(영어) 상단에 임포트됨 
		Page page = new Page();

		//Page에 현재 페이지인 num, 게시물 총 갯수인 service.count()를 넣어주면 클래스 내부에서 계산함
		//계산된 데이터는 page.getDisplayPost() 처럼 호출하여 사용 가능  - 페이징 기능을 클래스로 분리하여 컨트롤러의 코드가 짧아짐
		page.setNum(num);
		page.setCount(service.count());
		
		List list = null;
		list = service.listPage(page.getDisplayPost(), page.getPostNum());
		
		model.addAttribute("list", list);
		
		/*
		model.addAttribute("pageNum", page.getPageNum());
		
		model.addAttribute("startPageNum", page.getStartPageNum());
		model.addAttribute("endPageNum", page.getEndPageNum());
		
		model.addAttribute("prev", page.getPrev());
		model.addAttribute("next", page.getNext());
		아래 모델 page 추가로 Page class에서 데이터 끌어와 view로 전달 가능 */ 
		model.addAttribute("page", page);
		
		model.addAttribute("select", num);
	}
		/* board.domain.page 에 페이징 관련 코드 정리 현재 페이징 관련 코드 주석처리
	 // 게시물 총 갯수
	 int count = service.count();
	  
	 // 한 페이지에 출력할 게시물 갯수
	 int postNum = 10;
	  
	 // 하단 페이징 번호 ([ 게시물 총 갯수 ÷ 한 페이지에 출력할 갯수 ]의 올림)
	 int pageNum = (int)Math.ceil((double)count/postNum);
	  
	 // 출력할 게시물
	 int displayPost = (num - 1) * postNum;
	
	//한번에 표시할 페이징 번호의 갯수
	int pageNum_cnt = 10;
	
	//표시되는 페이지 번호 중 마지막 번호구하는 식 
	//num 는 현재 페이지 번호 8으로 가정 -> pageNum_cnt = 10 한번에 페이지 나올 개수 으로 나눔 -> ceil 소수점 올림 1됨 *10
	int endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);
	
	//표시되는 페이지 번호 중 첫번째 번호
	int startPageNum = endPageNum - (pageNum_cnt - 1);
	
	//마지막 번호 재계산  (올림)(현재페이지번호 / 한번에 표시할 페이지 번호의 갯수) * 한번에 표시할 페이지 번호의 갯수
	int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNum_cnt));
	 
	if(endPageNum > endPageNum_tmp) {
	 endPageNum = endPageNum_tmp;
	}
	
	
	
	//페이지 번호의 간격을 넘어가는 이전과 다음 링크의 표시
	boolean prev = startPageNum == 1 ? false : true;
	boolean next = endPageNum * pageNum_cnt >= count ? false : true;
	
	List list = null; 
	list = service.listPage(displayPost, postNum);
	model.addAttribute("list", list);   
	model.addAttribute("pageNum", pageNum);
	
	//추가적으로 위에서 구한 시작과 끝번호, 이전과 다음 링크 표시를 뷰(view)에 출력하기 위해 모델(model)에 넣어줌
	//시작 및 끝 번호
	model.addAttribute("startPageNum", startPageNum);
	model.addAttribute("endPageNum", endPageNum);
	
	//이전 및 다음
	model.addAttribute("prev", prev);
	model.addAttribute("next", next);
	
	// 현재 페이지
	model.addAttribute("select", num);
	*/

}

