package kr.or.ddit.dto;

import java.util.Date;

public class RentVO {
	
	private String id;
	private String rent_no;
	private Date rent_start;
	private Date rent_end;
	private Date real_end;
	private String book_no;
	private String title;
	private String writer;
	private int rownum;
	private int rent_res;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public int getRent_res() {
		return rent_res;
	}
	public void setRent_res(int rent_res) {
		this.rent_res = rent_res;
	}
	
	
}
