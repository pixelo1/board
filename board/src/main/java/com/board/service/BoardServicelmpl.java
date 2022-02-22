package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.BoardDAO;
import com.board.domain.BoardVO;

@Service
public class BoardServicelmpl implements BoardService {
	
	@Inject
	private BoardDAO dao;
	
	//게시물 목록 
	@Override
	public List list() throws Exception {
		// TODO Auto-generated method stub
		return dao.list();
	}
	
	//게시물 작성
	@Override
	public void write(BoardVO vo) throws Exception {
		
		dao.write(vo);
	}
	

}
