package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BookVO;
import kr.or.ddit.dto.RentVO;

public interface MyListService {
	
	// 목록조회
	Map<String, Object> getRentList(SearchCriteria cri, String id) throws SQLException;
	
	
}
