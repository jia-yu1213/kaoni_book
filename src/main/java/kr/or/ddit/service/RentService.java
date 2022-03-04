package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.Map;

import kr.or.ddit.dto.RentVO;

public interface RentService {
	
	Map<String, Object> checkRent(String mem_id) throws SQLException;
	
	// 등록
	void registRent(RentVO rent)throws SQLException;
}
