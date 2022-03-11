package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BookVO;

public interface BookDAO {

	//책 목록 조회
	List<BookVO> selectSearchBookList(SearchCriteria cri) throws SQLException;
	
	//책 목록 조회
	List<BookVO> saveBookList() throws SQLException;
	
	//책 목록 갯수
	int selectSearchBookListCount(SearchCriteria cri) throws SQLException;
	
	//책 대여상태 변경
	void updateBookStatus(BookVO book) throws SQLException;

	//책 추가
	void insertBook(BookVO book) throws SQLException;
	
	//카테고리 목록 가져오기
	List<BookVO> selectCateList() throws SQLException;
	
	//책번호로 책 상세보기
	BookVO selectBookByBookNo(String book_no) throws SQLException;
	
	//책 수정하기
	void updateBook(BookVO book) throws SQLException;
	
	//엑셀 책 추가
	void insertExcelBook(Map<String, Object> paramMap) throws SQLException;
}
