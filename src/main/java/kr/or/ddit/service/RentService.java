package kr.or.ddit.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

import kr.or.ddit.command.SearchCriteria;

public interface RentService {
	
	Map<String, Object> checkRent(String mem_id) throws SQLException;
}
