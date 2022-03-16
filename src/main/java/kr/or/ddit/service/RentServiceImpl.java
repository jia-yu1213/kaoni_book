  package kr.or.ddit.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dao.BookDAO;
import kr.or.ddit.dao.RentDAO;
import kr.or.ddit.dto.BookVO;
import kr.or.ddit.dto.RentVO;
import kr.or.ddit.dto.ReservationVO;

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
		int countRes = rentDAO.selectResverationCount(mem_id);
		
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
		
			dataMap.put("countRes", countRes);
			
		return dataMap;
	}

	
	//예약 내역 count
	@Override
	public Map<String, Object> getResverationCount(String id) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		int countRes = rentDAO.selectResverationCount(id);
		dataMap.put("countRes", countRes);
		
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

	@Override
	public void updateReturn(RentVO rent) throws SQLException {
		
		rentDAO.updateReturnBook(rent);
		
	}
    
	// 예약
	@Override
	public void modifyBookResStatus(ReservationVO resVO) throws SQLException {
		rentDAO.updateBookResStatus(resVO);
		rentDAO.insertResveration(resVO);
		
	}
	
	@Override
	public void removeSuccesRes(ReservationVO resVO) throws SQLException {
		rentDAO.deleteResveration(resVO);
	}
	
	@Override
	public void removeReservation(ReservationVO resVO) throws SQLException {
		rentDAO.deleteResveration(resVO);
		rentDAO.updateBookResStatus(resVO);
	}
	


	@Override
	public RentVO getRent(String rent_no) throws SQLException {
		RentVO rent = rentDAO.selectRentByRentNo(rent_no);
		return rent;
	}
 

	@Override
	public void modifyRentStatus(String rent_no) throws SQLException {
		
		rentDAO.updateRentStatus(rent_no);
		
	}

	@Override
	public Map<String, Object> getResList(SearchCriteria cri, String id) throws SQLException {
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<ReservationVO> resList = rentDAO.selectResveration(cri, id);
		
		int totalCount = rentDAO.selectResverationCount(cri, id);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		dataMap.put("resList", resList);
		dataMap.put("pageMaker", pageMaker);
		
		
		return dataMap;
	}

	@Override
	public void updateRealRentStatus(RentVO rent) throws SQLException {
		rentDAO.updateRealRentStatus(rent);
	}

	@Override
	public void updateReturnCancle(RentVO rent) throws SQLException {
		rentDAO.updateReturnCancleBook(rent);
		
	}





}
