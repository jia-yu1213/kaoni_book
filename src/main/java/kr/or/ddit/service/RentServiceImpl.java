package kr.or.ddit.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import kr.or.ddit.dao.RentDAO;

public class RentServiceImpl implements RentService {

	private RentDAO rentDAO;

	public void setRentDAO(RentDAO rentDAO) {
		this.rentDAO = rentDAO;
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
		}else if(nowRent!=0) {
			//현재 대여중인 책이 있는 경우
			dataMap.put("status", "nowRent");
			dataMap.put("data", nowRent);
		}
		return dataMap;
	}
 
}
