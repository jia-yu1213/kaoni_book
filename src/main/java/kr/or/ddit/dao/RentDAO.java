package kr.or.ddit.dao;

import java.sql.SQLException;

import kr.or.ddit.dto.RentVO;

public interface RentDAO {
	//현재 연체가 아니면서 대여중인 책의 갯수
	int selectNowRentCount(String mem_id) throws SQLException;
	
	//현재 연체중인 책의 갯수
	int selectOverdueRentCount(String mem_id) throws SQLException;
	
	//연체 기간
	String selectOverdueDate(String mem_id) throws SQLException;
	
	//대여 이력 추가
	void insertRent(RentVO rent) throws SQLException;
	
	//책 반납 업데이트
	void updateReturnBook(RentVO rent) throws SQLException;
}
