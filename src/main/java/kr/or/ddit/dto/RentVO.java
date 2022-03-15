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
	
	private String ori_title;
	private String translator;
	private String publisher;
	private Date publishing_date;
	private String book_intro;
	private String book_index;
	private String writer_intro;
	private int book_status;
	private String cate_name;
	private String cate_no;
	private String photo;
	private int res_status;
	
	private int rent_status;
	
	public int getRent_status() {
		return rent_status;
	}
	public void setRent_status(int rent_status) {
		this.rent_status = rent_status;
	}
	public String getId() {
		return id;
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
	public int getBook_status() {
		return book_status;
	}
	public void setBook_status(int book_status) {
		this.book_status = book_status;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public String getCate_no() {
		return cate_no;
	}
	public void setCate_no(String cate_no) {
		this.cate_no = cate_no;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getRes_status() {
		return res_status;
	}
	public void setRes_status(int res_status) {
		this.res_status = res_status;
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
