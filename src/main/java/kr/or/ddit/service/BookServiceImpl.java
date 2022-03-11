package kr.or.ddit.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dao.BookDAO;
import kr.or.ddit.dto.BookVO;
import kr.or.ddit.dto.ExcelReadOption;
import kr.or.ddit.util.ExcelRead;

public class BookServiceImpl implements BookService {

	private BookDAO bookDAO;

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	@Override
	public Map<String, Object> getBookList(SearchCriteria cri) throws SQLException {

		Map<String, Object> dataMap = new HashMap<String, Object>();

		// 현재 page 번호에 맞는 리스트를 perPageNum 개수 만큼 가져오기.
		List<BookVO> bookList = bookDAO.selectSearchBookList(cri);
		
		//cateList
		List<BookVO> cateList = bookDAO.selectCateList();
		
		// 전체 board 개수
		int totalCount = bookDAO.selectSearchBookListCount(cri);
		
		// PageMaker 생성.
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("bookList", bookList);
		dataMap.put("cateList", cateList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}

	@Override
	public void regist(BookVO book) throws SQLException {
		bookDAO.insertBook(book);
	}

	@Override
	public void modifyStatus(BookVO book) throws SQLException {
		bookDAO.updateBookStatus(book);
	}
	

	@Override
	public List<BookVO> selectCateList() throws SQLException {
		List<BookVO> cateList = bookDAO.selectCateList();
		return cateList;
	}

	@Override
	public BookVO getBook(String book_no) throws SQLException {
		BookVO book = bookDAO.selectBookByBookNo(book_no);
		return book;
	}

	@Override
	public void modify(BookVO book) throws SQLException {
		bookDAO.updateBook(book);
	}

	@Override
	public List<BookVO> saveBookList() throws SQLException {
		List<BookVO> bookList = bookDAO.saveBookList();
		return bookList;
	}

	@Override
	public void excelUpload(File destFile) throws SQLException, IOException {
		ExcelReadOption excelReadOption = new ExcelReadOption();
		//파일 경로 추가
		excelReadOption.setFilePath(destFile.getAbsolutePath());
		//추출할 컬럼명 추가
		excelReadOption.setOutputColumns("A","B","C");
		//시작행
		excelReadOption.setStartRow(2);

		List<Map<String, String>> excelContent = ExcelRead.read(excelReadOption);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("excelContent", excelContent);
		bookDAO.insertExcelBook(paramMap);
	}

	

}
