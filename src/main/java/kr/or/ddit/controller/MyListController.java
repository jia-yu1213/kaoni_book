package kr.or.ddit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.command.BookModifyCommand;
import kr.or.ddit.command.BookRegistCommand;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BookVO;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.service.BookService;
import kr.or.ddit.service.MyListService;
import kr.or.ddit.util.MakeFileName;

@Controller
@RequestMapping("/mylist")
public class MyListController {

	@Autowired
	private MyListService mylistService;

	@RequestMapping("/main")
	public String main()throws Exception{
		return "mylist/list";
	}
	
	@RequestMapping("/list")
	public void list(HttpSession session,SearchCriteria cri, Model model)throws Exception{
		MemberVO member = (MemberVO) session.getAttribute("loginUser");
		
		member.getId();
		
		Map<String,Object> dataMap = mylistService.getRentList(cri, null);		
		model.addAllAttributes(dataMap);
	}
	

	
	
}





