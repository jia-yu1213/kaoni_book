package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.RentVO;
import kr.or.ddit.dto.ReservationVO;

public interface RentService {
	
	Map<String, Object> checkRent(String mem_id) throws SQLException;
	
	// 대여이력
	void registRent(RentVO rent)throws SQLException;
	
	//반납
	void updateReturn(RentVO rent) throws SQLException;
	
	//예약
	void modifyBookResStatus(ReservationVO resVO) throws SQLException;
	
	void removeReservation(ReservationVO resVO) throws SQLException;
	
	List<ReservationVO> getReservationList() throws SQLException;

	// 회원 책 정보 상세조회
	RentVO getRent(String rent_no) throws SQLException;
	
	// 반납하기로 변경
	void modifyRentStatus(String rent_no) throws SQLException;
	
}



