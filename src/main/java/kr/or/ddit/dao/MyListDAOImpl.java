package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BookVO;
import kr.or.ddit.dto.RentVO;

public class MyListDAOImpl implements MyListDAO {

	private SqlSession session;
	public void setSqlSession(SqlSession session) {
		this.session=session;
	}
	
	@Override
	public List<RentVO> selectRentListByID( SearchCriteria cri, String id) throws SQLException {
		
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();		
		RowBounds rowBounds=new RowBounds(offset,limit);		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("id", id);
		dataMap.put("cri", cri);
		List<RentVO> rentList=
				session.selectList("MyList-Mapper.selectRentListByID",dataMap,rowBounds);
		return rentList;
	}

	@Override
	public int selectRentListByIDCount( SearchCriteria cri, String id) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("id", id);
		dataMap.put("cri", cri);
		int count=session.selectOne("MyList-Mapper.selectRentListByIDCount",dataMap);
		return count;
	}

}











