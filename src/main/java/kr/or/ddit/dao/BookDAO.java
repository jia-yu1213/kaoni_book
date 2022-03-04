package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BookVO;

public interface BookDAO {

	//책 목록 조회
	List<BookVO> selectSearchBookList(SearchCriteria cri) throws SQLException;

	//책 목록 갯수
	int selectSearchBookListCount(SearchCriteria cri) throws SQLException;
	
	//책 대여상태 변경
	void updateBookStatus(BookVO book) throws SQLException;

	//책 추가
	void insertBook(BookVO book) throws SQLException;
}
