package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BookVO;
import kr.or.ddit.dto.RentVO;

public interface MyListDAO {

	//책 목록 조회
	List<RentVO> selectRentListByID(SearchCriteria cri, String id) throws SQLException;

	//책 목록 갯수
	int selectRentListByIDCount(SearchCriteria cri, String id) throws SQLException;
	
}
