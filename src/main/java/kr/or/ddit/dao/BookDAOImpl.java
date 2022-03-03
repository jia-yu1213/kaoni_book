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

//	@Override
//	public NoticeVO selectNoticeByNno( int nno) throws SQLException {
//		NoticeVO notice=session.selectOne("Notice-Mapper.selectNoticeByNno",nno);
//		return notice;
//	}
//
//	@Override
//	public void increaseViewCount( int nno) throws SQLException {
//		session.update("Notice-Mapper.increaseViewCount",nno);
//		
//	}
//
//	@Override
//	public int selectNoticeSequenceNextValue() throws SQLException {
//		int seq_num=session.selectOne("Notice-Mapper.selectNoticeSequenceNextValue");
//		return seq_num;
//	}
//
//	@Override
//	public void insertNotice( NoticeVO notice) throws SQLException {
//		session.update("Notice-Mapper.insertNotice",notice);
//		
//	}
//	
//	@Override
//	public void updateNotice(NoticeVO notice) throws SQLException {
//		session.update("Notice-Mapper.updateNotice",notice);
//	}
//
//	@Override
//	public void deleteNotice(int nno) throws SQLException {
//		session.update("Notice-Mapper.deleteNotice",nno);
//	}
}











