package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;

public class RentDAOImpl implements RentDAO {

	private SqlSession session;
	public void setSqlSession(SqlSession session) {
		this.session=session;
	}
	@Override
	public int selectNowRentCount(String mem_id) throws SQLException {
		int cnt = session.selectOne("Rent-Mapper.selectNowRentCount",mem_id);
		return cnt;
	}
	@Override
	public int selectOverdueRentCount(String mem_id) throws SQLException {
		int cnt = session.selectOne("Rent-Mapper.selectOverdueRentCount",mem_id);
		return cnt;
	}
	@Override
	public String selectOverdueDate(String mem_id) throws SQLException {
		String date = session.selectOne("Rent-Mapper.selectOverdueDate",mem_id);
		return date;
	}
	

}











