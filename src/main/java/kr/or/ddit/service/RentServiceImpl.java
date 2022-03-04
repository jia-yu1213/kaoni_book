package kr.or.ddit.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import kr.or.ddit.dao.BookDAO;
import kr.or.ddit.dao.RentDAO;
import kr.or.ddit.dto.BookVO;
import kr.or.ddit.dto.RentVO;

public class RentServiceImpl implements RentService {

	private RentDAO rentDAO;

	public void setRentDAO(RentDAO rentDAO) {
		this.rentDAO = rentDAO;
	}
	
	private BookDAO bookDAO;

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}
	
	@Override
	public Map<String, Object> checkRent(String mem_id) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		int nowRent = rentDAO.selectNowRentCount(mem_id);
		int overdueRent = rentDAO.selectOverdueRentCount(mem_id);
		String overdueDate = rentDAO.selectOverdueDate(mem_id);
		if (overdueRent!=0) {
			//연체중이 책이 있는 경우
			dataMap.put("status", "overdueRent");
			dataMap.put("data", overdueRent);
		}else if(overdueDate!=null) {
			//연체 기간인 경우
			
			dataMap.put("status", "overdueDate");
			dataMap.put("data", overdueDate);
		}else {
			//현재 대여중인 책이 있는 경우
			dataMap.put("status", "nowRent");
			dataMap.put("data", nowRent);
		}
		return dataMap;
	}

	@Override
	public void registRent(RentVO rent) throws SQLException {
		//대여이력 업데이트
		rentDAO.insertRent(rent);
		
		//책상태 업데이트
		BookVO book = new BookVO();
		book.setBook_no(rent.getBook_no());
		book.setBook_status(1);
		bookDAO.updateBookStatus(book);
	}
 
}
