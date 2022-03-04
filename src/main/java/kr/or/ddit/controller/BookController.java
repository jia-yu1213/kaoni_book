package kr.or.ddit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping("/main")
	public String main()throws Exception{
		return "book/list";
	}
	
	@RequestMapping("/list")
	public void list(SearchCriteria cri, Model model)throws Exception{
		
		Map<String,Object> dataMap = bookService.getBookList(cri);		
		model.addAllAttributes(dataMap);
	}
	

}





