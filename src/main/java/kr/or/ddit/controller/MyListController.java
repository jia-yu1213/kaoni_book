package kr.or.ddit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BookVO;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.dto.RentVO;
import kr.or.ddit.service.BookService;
import kr.or.ddit.service.MyListService;
import kr.or.ddit.service.RentService;
import kr.or.ddit.util.MakeFileName;

@Controller
@RequestMapping("/mylist")
public class MyListController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private MyListService mylistService;
	
	@Autowired
	private RentService rentService;
	
	
	@RequestMapping("/main")
	public String main()throws Exception{
		return "mylist/list";
	}
	
	@RequestMapping("/list")
	public String list(RedirectAttributes rttr,HttpSession session,SearchCriteria cri, Model model)throws Exception{
		MemberVO member = (MemberVO) session.getAttribute("loginUser");
		if (member==null) {
			String url="redirect:/book/list.do";
			rttr.addFlashAttribute("from","login");
			return url;
		}else {
			String url="mylist/list";
			String id = member.getId();
			
			Map<String,Object> dataMap = mylistService.getRentList(cri, id);		
			model.addAllAttributes(dataMap);
			Map<String,Object> dataMap2 = rentService.getResList(cri, id);		
			model.addAllAttributes(dataMap2);
			return url;
		}
	}
	

	@RequestMapping("/returnBookWait")
	public String returnBook(String rent_no) throws Exception{
		String url = "redirect:/mylist/list";

//		RentVO rent1 = new RentVO();
//		rent1.setRent_no(rent_no);
//		rentService.updateReturn(rent1);
		
		RentVO rent = rentService.getRent(rent_no);
		
		BookVO book = new BookVO();
		book.setBook_no(rent.getBook_no());
		book.setBook_status(3);
		bookService.modifyStatus(book);
		return url;
	}
	
}





