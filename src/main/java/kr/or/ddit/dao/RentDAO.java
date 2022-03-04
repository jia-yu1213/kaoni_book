package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.Date;

public interface RentDAO {

	int selectNowRentCount(String mem_id) throws SQLException;

	int selectOverdueRentCount(String mem_id) throws SQLException;

	String selectOverdueDate(String mem_id) throws SQLException;
}
