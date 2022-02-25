package com.board.domain;

public class Page {

	//각 변수들 선언
	
	// 현재 페이지 번호
	private int num;

	// 게시물 총 갯수
	private int count;

	// 한 페이지에 출력할 게시물 갯수
	private int postNum = 10;

	// 하단 페이징 번호 ([ 게시물 총 갯수 ÷ 한 페이지에 출력할 갯수 ]의 올림)
	private int pageNum;

	// 출력할 게시물
	private int displayPost;

	// 한번에 표시할 페이징 번호의 갯수
	private int pageNumCnt = 10;

	// 표시되는 페이지 번호 중 마지막 번호
	private int endPageNum;

	// 표시되는 페이지 번호 중 첫번째 번호
	private int startPageNum;

	// 다음/이전 표시 여부
	private boolean prev;
	private boolean next;
	
	//setter는 2개만 필요 입력이 필요한것은
	//현재페이지 num, 게시물 총 갯수 count  
	
		public void setNum(int num) {
		 this.num = num;
		}

		public void setCount(int count) {
		 this.count = count;
		 
		 dataCalc();
		}

		public int getCount() {
		 return count;
		}

		public int getPostNum() {
		 return postNum;
		}

		public int getPageNum() {
		 return pageNum;
		}

		public int getDisplayPost() {
		 return displayPost;
		}

		public int getPageNumCnt() {
		 return pageNumCnt;
		}

		public int getEndPageNum() {
		 return endPageNum;
		}

		public int getStartPageNum() {
		 return startPageNum;
		}

		public boolean getPrev() {
		 return prev;
		} 

		public boolean getNext() {
		 return next;
		}


	//데이터들 계산하는 메서드 생성
	//게시물 총 갯수를 알고난 시점부터 계산 할 수 있어서
	//setCount에서 메서드 호출
	//dataCalc 메서드는 개존 컨트롤러에 있는 코드를 가져와 수정하면 편함

	//표시되는 페이지 번호 중 마지막 번호구하는 식 
	//num 는 현재 페이지 번호 8으로 가정 -> pageNum_cnt = 10 한번에 페이지 나올 개수 으로 나눔 -> ceil 소수점 올림 1됨 *10
	private void dataCalc() {
	
	endPageNum = (int)(Math.ceil((double)num / (double)pageNumCnt) * pageNumCnt);
		 
	//표시되는 페이지 번호 중 첫번째 번호
	startPageNum = endPageNum - (pageNumCnt - 1);
	
	
	//마지막 번호 재계산  (올림)(현재페이지번호 / 한번에 표시할 페이지 번호의 갯수) * 한번에 표시할 페이지 번호의 갯수
	int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNumCnt));
	 
	 if(endPageNum > endPageNum_tmp) {
	  endPageNum = endPageNum_tmp;
	 }
	
	//페이지 번호의 간격을 넘어가는 이전과 다음 링크의 표시
	 prev = startPageNum == 1 ? false : true;
	 next = endPageNum * pageNumCnt >= count ? false : true;
	 
	 displayPost = (num - 1) * postNum;
	 
	}
	
	//검색 타입과 검색어
	/*
	private String searchTypeKeyword;
	
	public void setSearchTypeKeyword(String searchType, String keyword) {
		
		if(searchType.equals("") || keyword.equals("")) {
			searchTypeKeyword = "";
		} else {
			searchTypeKeyword = "&searchType=" + searchType + "&keyword=" + keyword;
		}
	}*/
	
	public String getSearchTypeKeyword() {
		
		if(searchType.equals("") || keyword.equals("")) {
			return "";
		} else {
			return "&searchType=" + searchType + "&keyword=" + keyword;
		}
	}
	
	private String searchType;
	private String keyword;
	
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchType() {
		return searchType;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeyword() {
		return keyword;
	}
	
	
	
}