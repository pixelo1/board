package com.board.dao;

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

}
