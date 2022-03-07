package kr.or.ddit.command;

import java.util.Date;

import kr.or.ddit.dto.BookVO;

public class BookRegistCommand {
	
	private int rownum;
	
	
	private String title;
	private String writer;
	private String ori_title;
	private String translator;
	private String publisher;
	private Date publishing_date;
	private String book_intro;
	private String book_index;
	private String writer_intro;
	private String cate_no;
//	private String photo;
	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getOri_title() {
		return ori_title;
	}

	public void setOri_title(String ori_title) {
		this.ori_title = ori_title;
	}

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPublishing_date() {
		return publishing_date;
	}

	public void setPublishing_date(Date publishing_date) {
		this.publishing_date = publishing_date;
	}

	public String getBook_intro() {
		return book_intro;
	}

	public void setBook_intro(String book_intro) {
		this.book_intro = book_intro;
	}

	public String getBook_index() {
		return book_index;
	}

	public void setBook_index(String book_index) {
		this.book_index = book_index;
	}

	public String getWriter_intro() {
		return writer_intro;
	}

	public void setWriter_intro(String writer_intro) {
		this.writer_intro = writer_intro;
	}

	public String getCate_no() {
		return cate_no;
	}

	public void setCate_no(String cate_no) {
		this.cate_no = cate_no;
	}
	
	public BookVO toBookVO() {
		BookVO book = new BookVO();
		book.setTitle(title);
		book.setWriter(writer);
		book.setOri_title(ori_title);
		book.setTranslator(translator);
		book.setPublisher(publisher);
		book.setPublishing_date(publishing_date);
		book.setBook_intro(book_intro);
		book.setCate_no(cate_no);
		book.setBook_index(book_index);
		book.setWriter_intro(writer_intro);
		return book;
	}
	

}
