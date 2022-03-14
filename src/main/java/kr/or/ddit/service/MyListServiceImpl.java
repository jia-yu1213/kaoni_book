package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dao.BookDAO;
import kr.or.ddit.dao.MyListDAO;
import kr.or.ddit.dao.RentDAO;
import kr.or.ddit.dto.BookVO;
import kr.or.ddit.dto.RentVO;

public class MyListServiceImpl implements MyListService {

	private MyListDAO mylistDAO;

	public void setMylistDAO(MyListDAO mylistDAO) {
		this.mylistDAO = mylistDAO;
	}

	@Override
	public Map<String, Object> getRentList(SearchCriteria cri, String id) throws SQLException {

		Map<String, Object> dataMap = new HashMap<String, Object>();

		// 현재 page 번호에 맞는 리스트를 perPageNum 개수 만큼 가져오기.
		List<RentVO> rentList = mylistDAO.selectRentListByID(cri, id);

		// 전체 board 개수
		int totalCount = mylistDAO.selectRentListByIDCount(cri, id);

		// PageMaker 생성.
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("rentList", rentList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}
	
	
	
	

}
