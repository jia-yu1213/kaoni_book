package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BookVO;

public class BookDAOImpl implements BookDAO {

	private SqlSession session;
	public void setSqlSession(SqlSession session) {
		this.session=session;
	}
	
	@Override
	public List<BookVO> selectSearchBookList( SearchCriteria cri) throws SQLException {
		
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();		
		RowBounds rowBounds=new RowBounds(offset,limit);		
		
		List<BookVO> noticeList=
				session.selectList("Book-Mapper.selectSearchBookList",cri,rowBounds);
		
		return noticeList;
	}

	@Override
	public int selectSearchBookListCount( SearchCriteria cri) throws SQLException {
		int count=session.selectOne("Book-Mapper.selectSearchBookListCount",cri);
		return count;
	}

	@Override
	public void updateBookStatus(BookVO book) throws SQLException {
		session.update("Book-Mapper.updateBookStatus",book);
		
	}


}











