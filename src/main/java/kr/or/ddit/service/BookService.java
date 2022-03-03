package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.Map;

import kr.or.ddit.command.SearchCriteria;

public interface BookService {
	
	// 목록조회
	Map<String, Object> getBookList(SearchCriteria cri) throws SQLException;
	
//	// 상세보기
//	NoticeVO getNotice(int nno) throws SQLException;
//	
//	// 수정화면 상세
//	NoticeVO getNoticeForModify(int nno) throws SQLException;
//
//	// 등록
//	void regist(NoticeVO notice)throws SQLException;
//	
//	// 수정
//	void modify(NoticeVO board) throws SQLException;
//
//	// 삭제
//	void remove(int nno) throws SQLException;
}
