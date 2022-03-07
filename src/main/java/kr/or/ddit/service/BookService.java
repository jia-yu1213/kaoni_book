package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BookVO;

public interface BookService {
	
	// 목록조회
	Map<String, Object> getBookList(SearchCriteria cri) throws SQLException;
	
//	// 대여 상태 수정
//	void modifyStatus(BookVO book) throws SQLException;
	
	// 도서 추가
	void regist(BookVO book)throws SQLException;
	
	//카테고리 목록 가져오기
	List<BookVO> selectCateList() throws SQLException;
}
