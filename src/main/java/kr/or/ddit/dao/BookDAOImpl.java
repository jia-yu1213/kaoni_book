package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
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
		
		List<BookVO> bookList=
				session.selectList("Book-Mapper.selectSearchBookList",cri,rowBounds);
		
		return bookList;
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

	@Override
	public void insertBook(BookVO book) throws SQLException {
		session.update("Book-Mapper.insertBook",book);
		
	}

	@Override
	public List<BookVO> selectCateList() throws SQLException {
		List<BookVO> cateList =  session.selectList("Book-Mapper.selectCateList");
		return cateList;
	}

	@Override
	public BookVO selectBookByBookNo(String book_no) throws SQLException {
		BookVO book = session.selectOne("Book-Mapper.selectBookByBookNo",book_no);
		return book;
	}

	@Override
	public void updateBook(BookVO book) throws SQLException {
		session.update("Book-Mapper.updateBook",book);
	}

	@Override
	public List<BookVO> saveBookList() throws SQLException {
		List<BookVO> bookList=
				session.selectList("Book-Mapper.selectSearchBookList");
		
		return bookList;
	}

	@Override
	public void insertExcelBook(Map<String, Object> paramMap) throws SQLException {
		session.update("Book-Mapper.updateExcelBook",paramMap);
	}

	@Override
	public List<BookVO> rentWaitBook(SearchCriteria cri) throws SQLException {
		List<BookVO> bookList=
				session.selectList("Book-Mapper.selectWaitBookList",cri);
		
		return bookList;
	}

	@Override
	public int rentWaitBookCount(SearchCriteria cri) throws SQLException {
		int count=session.selectOne("Book-Mapper.selectWaitBookListCount",cri);
		return count;
	}

	//나의 책 대여 현황 체크하기 
	@Override
	public List<BookVO> selectCheckStatus(String id, String book_no) throws SQLException {
		
		Map<String, Object> dataMap = new HashedMap<String, Object>();
		dataMap.put("id", id);
		dataMap.put("book_no", book_no);
		
		List<BookVO> scList = session.selectList("Book-Mapper.selectCheckStatus", dataMap);
		
		return scList;
	}
	



}











