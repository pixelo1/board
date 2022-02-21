// boardDAO 를 상송받는 lmpl 생성
package com.board.dao;


import java.util.List;

import com.board.domain.BoardVO;

public interface BoardDAO {

		public List<BoardVO> list() throws Exception;
}
