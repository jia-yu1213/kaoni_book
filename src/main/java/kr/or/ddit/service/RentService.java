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
	
	//하나 가져오기
	RentVO getRent(String rent_no) throws SQLException;
	
	//예약
	void modifyBookResStatus(ReservationVO resVO) throws SQLException;
	
	void removeReservation(ReservationVO resVO) throws SQLException;
	
	List<ReservationVO> getReservationList() throws SQLException; 
}
