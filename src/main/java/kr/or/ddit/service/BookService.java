package kr.or.ddit.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BookVO;

public interface BookService {
	
	// 목록조회
	Map<String, Object> getBookList(SearchCriteria cri) throws SQLException;
	
	//저장 목록 가져오기
	List<BookVO> saveBookList() throws SQLException;
	
//	// 대여 상태 수정
	void modifyStatus(BookVO book) throws SQLException;
	
	// 도서 추가
	void regist(BookVO book)throws SQLException;
	
	//카테고리 목록 가져오기
	List<BookVO> selectCateList() throws SQLException;
	
	//상세보기
	BookVO getBook(String book_no) throws SQLException;
	
	//수정하기
	void modify(BookVO book) throws SQLException;
	
	//엑셀 업로드
	void excelUpload(File destFile) throws SQLException, IOException;
}
