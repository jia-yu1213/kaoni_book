package kr.or.ddit.dto;

import java.util.Date;

public class RentVO {
	
	private String mem_id;
	private String rent_no;
	private Date rent_start;
	private Date rent_end;
	private Date real_end;
	private String book_no;
	private String book_title;
	private String writer;
	
	public String getBook_title() {
		return book_title;
	}
	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getRent_no() {
		return rent_no;
	}
	public void setRent_no(String rent_no) {
		this.rent_no = rent_no;
	}
	public Date getRent_start() {
		return rent_start;
	}
	public void setRent_start(Date rent_start) {
		this.rent_start = rent_start;
	}
	public Date getRent_end() {
		return rent_end;
	}
	public void setRent_end(Date rent_end) {
		this.rent_end = rent_end;
	}
	public Date getReal_end() {
		return real_end;
	}
	public void setReal_end(Date real_end) {
		this.real_end = real_end;
	}
	public String getBook_no() {
		return book_no;
	}
	public void setBook_no(String book_no) {
		this.book_no = book_no;
	}
	
	
}
