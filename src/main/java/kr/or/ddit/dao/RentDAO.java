package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.RentVO;
import kr.or.ddit.dto.ReservationVO;

public interface RentDAO {
	
	RentVO selectRentByRentNo(String rent_no) throws SQLException;
	
	//현재 연체가 아니면서 대여중인 책의 갯수
	int selectNowRentCount(String mem_id) throws SQLException;
	
	//현재 연체중인 책의 갯수
	int selectOverdueRentCount(String mem_id) throws SQLException;
	//예약 내역 count
	int selectResverationCount(String mem_id)throws SQLException;
	
	//연체 기간
	String selectOverdueDate(String mem_id) throws SQLException;
	
	//대여 이력 추가
	void insertRent(RentVO rent) throws SQLException;
	
	//책 반납 업데이트
	void updateReturnBook(RentVO rent) throws SQLException;
	
	//책 반납취소 업데이트
	void updateReturnCancleBook(RentVO rent) throws SQLException;
	
	//회원 대여정보 상세보기
	RentVO selectRentDetail(String rent_no) throws SQLException;
	
	void updateRentStatus(String rent_no) throws SQLException;
	
	void updateBookStatus(String book_no) throws SQLException;
	
	void updateRealRentStatus(RentVO rent) throws SQLException;
	
	
	// 책 예약 상태 업데이트
	void updateBookResStatus(ReservationVO resVO)throws SQLException;
	
	// 예약 테이블 insert
	void insertResveration(ReservationVO resVO)throws SQLException;
	
	// 예약 테이블 delete
	void deleteResveration(ReservationVO resVO)throws SQLException;
	
	//나의 도서 예약내역 출력
	List<ReservationVO> selectResveration(SearchCriteria cri, String id) throws SQLException;
	
	int selectResverationCount(SearchCriteria cri, String id) throws SQLException;
	
	//status0인 예약
	List<ReservationVO> selectReservationStatus0()throws SQLException;
	
	
}