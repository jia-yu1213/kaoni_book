package kr.or.ddit.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.dto.RentVO;
import kr.or.ddit.service.MyListService;
import kr.or.ddit.service.RentService;

@Controller
@RequestMapping("/mylist")
public class MyListController {

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
			return url;
		}
	}
	
	@RequestMapping("/returnBook")
	public String returnBook(String rent_no) throws Exception{
		String url = "redirect:/mylist/list";

		RentVO rent = new RentVO();
		rent.setRent_no(rent_no);
		rentService.updateReturn(rent);
		
		return url;
	}
	
}





