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
	
//	@RequestMapping("/registForm")
//	public String registForm(){
//		String url = "notice/regist";
//		return url;
//	}
//	
//	@RequestMapping("/regist")
//	public String regist(NoticeRegistCommand regReq,
//						 HttpServletRequest request,
//						 RedirectAttributes rttr) throws Exception{
//		String url = "redirect:/notice/list";
//		
//		NoticeVO notice = regReq.toNoticeVO();
//		
//		//notice.setTitle(HTMLInputFilter.htmlSpecialChars(notice.getTitle()));
//		notice.setTitle((String)request.getAttribute("XSStitle"));
//		
//		noticeService.regist(notice);		
//		
//		rttr.addFlashAttribute("from","regist");
//		
//		return url;
//	}
//	
//	@RequestMapping("/detail")
//	public ModelAndView detail(int nno,
//							   @RequestParam(defaultValue="") String from,
//							   HttpServletRequest request,
//							   ModelAndView mnv ) throws SQLException{
//		String url="notice/detail";
//		
//		NoticeVO notice = null;
//		
//		if(!from.equals("list")) {
//			notice = noticeService.getNoticeForModify(nno);
//		}else {
//			notice = noticeService.getNotice(nno);
//			url="redirect:/notice/detail.do?nno="+nno;
//		}
//		
//		mnv.addObject("notice",notice);
//		mnv.setViewName(url);
//		
//		return mnv;
//	}
//	
//	@RequestMapping("/modifyForm")
//	public ModelAndView modifyForm(int nno,ModelAndView mnv) throws Exception{
//		String url="notice/modify";
//		
//		NoticeVO notice = noticeService.getNoticeForModify(nno);
//		
//		mnv.addObject("notice",notice);
//		mnv.setViewName(url);
//		
//		return mnv;
//		
//	}
//	
//	@RequestMapping(value="/modify",method=RequestMethod.POST)
//	public String modifyPost(NoticeModifyCommand modifyReq,
//						     HttpServletRequest request,
//							 RedirectAttributes rttr)throws Exception{
//		String url = "redirect:/notice/detail.do";
//		
//		NoticeVO notice = modifyReq.toNoticeVO();		
//		//notice.setTitle(HTMLInputFilter.htmlSpecialChars(notice.getTitle()));
//		notice.setTitle((String)request.getAttribute("XSStitle"));
//		
//		noticeService.modify(notice);
//		
//		rttr.addAttribute("nno",notice.getNno());
//		rttr.addAttribute("from","modify");
//		
//		return url;
//	}
//	
//	@RequestMapping(value="/remove",method=RequestMethod.POST)
//	public String remove(int nno,RedirectAttributes rttr) throws Exception{
//		String url="redirect:/notice/detail.do";
//			
//		noticeService.remove(nno);		
//		
//		rttr.addFlashAttribute("from","remove");
//		rttr.addAttribute("nno",nno);
//		
//		return url;
//	}
}





