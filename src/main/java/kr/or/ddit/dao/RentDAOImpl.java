package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.RentVO;
import kr.or.ddit.dto.ReservationVO;

public class RentDAOImpl implements RentDAO {

	private SqlSession session;
	private String namespace = "Rent-Mapper.";
	
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
	
	@Override
	public void insertRent(RentVO rent) throws SQLException {
		session.update("Rent-Mapper.insertRent",rent);
	}

	@Override
	public void updateReturnBook(RentVO rent) throws SQLException {
		session.update("Rent-Mapper.updateReturnBook",rent);
		
	}

	@Override
	public void updateBookResStatus(ReservationVO resVO) throws SQLException {
		session.update(namespace + "updateBookResStatus");
	}

	@Override
	public void insertResveration(ReservationVO resVO) throws SQLException {
		session.update(namespace + "insertResveration");
	}

	@Override
	public void deleteResveration(ReservationVO resVO) throws SQLException {
		session.update(namespace + "deleteResveration");
	}


	@Override
	public RentVO selectRentByRentNo(String rent_no) throws SQLException {
		RentVO rent = session.selectOne("Rent-Mapper.selectRentByRentNo",rent_no);
		return rent;
	}
	
	
	@Override
	public RentVO selectRentDetail(String rent_no) throws SQLException {
		RentVO rent = session.selectOne("MyList-Mapper.selectRentDetail", rent_no);
		return rent;
	}

	@Override
	public void updateRentStatus(String rent_no) throws SQLException {
		session.update("MyList-Mapper.updateRentStatus", rent_no);
	}

	@Override
	public void updateBookStatus(String book_no) throws SQLException {
		session.update("MyList-Mapper.updateBookStatus", book_no);
	}

	@Override
	public List<ReservationVO> selectResveration(SearchCriteria cri, String id) throws SQLException {
		
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();		
		RowBounds rowBounds=new RowBounds(offset,limit);		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		dataMap.put("id", id);
		dataMap.put("cri", cri);
		
		List<ReservationVO> resList = session.selectList(namespace + "selectResveration", dataMap, rowBounds);
		
		return resList;
	}

	@Override
	public int selectResverationCount(SearchCriteria cri, String id) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("id", id);
		dataMap.put("cri", cri);
		int count = session.selectOne("Rent-Mapper.selectResverationCount", dataMap); 
		System.out.println("/////////////////////////////////////////////////////////////"+id);
		return count;
	}
	
}





