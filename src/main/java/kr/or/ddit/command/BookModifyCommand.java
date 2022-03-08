package kr.or.ddit.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.dto.BookVO;

public class BookModifyCommand {
	
	private String book_no;
	private String title;
	private String writer;
	private String ori_title;
	private String translator;
	private String publisher;
	private String publishing_date;
	private String book_intro;
	private String book_index;
	private String writer_intro;
	private String cate_no;
	private MultipartFile picture;
	private String oldPicture; // 이전 사진파일명
	private String uploadPicture; // 변경된 사진 파일명.

	public String getTitle() {
		return title;
	}

	public String getPublishing_date() {
		return publishing_date;
	}

	public void setPublishing_date(String publishing_date) {
		this.publishing_date = publishing_date;
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
	
	

	public String getBook_no() {
		return book_no;
	}

	public void setBook_no(String book_no) {
		this.book_no = book_no;
	}

	public MultipartFile getPicture() {
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}

	public String getOldPicture() {
		return oldPicture;
	}

	public void setOldPicture(String oldPicture) {
		this.oldPicture = oldPicture;
	}

	public String getUploadPicture() {
		return uploadPicture;
	}

	public void setUploadPicture(String uploadPicture) {
		this.uploadPicture = uploadPicture;
	}

	public BookVO toBookVO() throws ParseException {
		BookVO book = new BookVO();
		book.setBook_no(book_no);
		book.setTitle(title);
		book.setWriter(writer);
		book.setOri_title(ori_title);
		book.setTranslator(translator);
		book.setPublisher(publisher);
		Date date=null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (publishing_date != "") {
			date = format.parse(publishing_date);
		}else {
			date = format.parse("0000-00-00");
		}
		book.setPublishing_date(date);
		book.setBook_intro(book_intro);
		book.setCate_no(cate_no);
		book.setBook_index(book_index);
		book.setWriter_intro(writer_intro);
		
		return book;
	}
	

}
