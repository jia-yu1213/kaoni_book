package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.Map;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BookVO;

public interface BookService {
	
	// 목록조회
	Map<String, Object> getBookList(SearchCriteria cri) throws SQLException;
	
//	// 대여 상태 수정
//	void modifyStatus(BookVO book) throws SQLException;
}
