package kr.or.ddit.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.dto.RentVO;
import kr.or.ddit.service.RentService;

@Controller
@RequestMapping("/rent")
public class RentController {

	@Autowired
	private RentService rentService;
	
	@RequestMapping(value="/checkRent",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> memCheckRent(HttpSession session) throws Exception{
		
		//로그인 구현하고 아이디 넣기
		MemberVO member = (MemberVO) session.getAttribute("loginUser");
		HttpStatus status = HttpStatus.OK;
		Map<String, Object> dataMap = rentService.checkRent(member.getId());
		ResponseEntity<Map<String, Object>> entity = new ResponseEntity<>(dataMap, status);
		
		return entity;
	}
	
	@RequestMapping("/registRent")
	public String registRent(String book_no,HttpSession session) throws Exception{
		String url = "redirect:/book/list";
		
		MemberVO member = (MemberVO) session.getAttribute("loginUser");
		
		RentVO rent = new RentVO();
		rent.setMem_id(member.getId());
		rent.setBook_no(book_no);
		rentService.registRent(rent);
		
		return url;
	}

}





