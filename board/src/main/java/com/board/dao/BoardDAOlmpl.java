package com.board.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardVO;

@Repository
public class BoardDAOlmpl implements BoardDAO {

	@Inject
	private SqlSession sql;
	
	private static String namespace="com.board.mappers.board";
	
	//게시물 목록게시물, 즉 tbl_board 1행의 데이터의 형태는 BoardVO와 같습니다. 
	//게시물 목록은 tbl_board가 1행 이상 존재하는것이므로, BoardVO를 리스트(List) 형태로 만들면 게시물 목록을 받아올 수 있습니다.
	
	@Override
	public List list() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".list");
	}

	@Override
	public void write(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		sql.insert(namespace + ".write", vo);
		
	}
	
	//게시물 조회
	public BoardVO view(int bno) throws Exception {
		 
		 return sql.selectOne(namespace + ".view", bno);
		}
	
	//게시물 수정
	@Override
	public void modify(BoardVO vo) throws Exception {
		sql.update(namespace + ".modify", vo);
	}
	
	//게시물 삭제
	public void delete(int bno) throws Exception {
		sql.delete(namespace + ".delete", bno);
	}
	
	//게시물 총 갯수 namespace 는 boardMapper 를 당겨옴 그 맵퍼 안에 count id 가진 것을 불러옴 
	@Override
	public int count() throws Exception {
		return sql.selectOne(namespace + ".count");
	}
	
	//게시물 목록 + 페이징 (매개변수 displayPost,postNum를 해시맵을 이용하여 하나로 그룹핑 한뒤 매퍼에 전송
	//DAO와 매퍼에서는 데이터를 하나만 전송가능 하기때문에 2개이상 데이터 다룰 떄는 VO(Value Object)를 사용하거나 해시맵을 이용
	@Override
	public List listPage(int displayPost, int postNum) throws Exception {
		
		HashMap data = new HashMap();
		
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		
		return sql.selectList(namespace + ".listPage", data);
	}

}
