package kr.or.ddit.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.josephoconnell.html.HTMLInputFilter;

import kr.or.ddit.command.BoardModifyCommand;
import kr.or.ddit.command.BoardRegistCommand;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BoardVO;
import kr.or.ddit.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	
	
	@Autowired
	private BoardService service;
	
	@RequestMapping("/main")
	public String main()throws Exception{
		return "board/main";
	}
	
	@RequestMapping("/list")
	public ModelAndView list(SearchCriteria cri, ModelAndView mnv)throws SQLException{
		String url="board/list";		
		
		Map<String,Object> dataMap = service.getBoardList(cri);
		
		mnv.addObject("dataMap",dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	

	@RequestMapping("/registForm")
	public String registForm(){
		String url="board/regist";		
		return url;
	}
	
	@RequestMapping("/regist")
	public String regist(BoardRegistCommand regReq,
			             HttpServletRequest request,
						 RedirectAttributes rttr)throws Exception{
		String url="redirect:/board/list.do";
		
		BoardVO board=regReq.toBoardVO();
		//board.setTitle(HTMLInputFilter.htmlSpecialChars(board.getTitle()));
		board.setTitle((String)request.getAttribute("XSStitle"));
		
		service.regist(board);
		
		rttr.addFlashAttribute("from","regist");
		
		return url;
	}
		
	@RequestMapping("/detail")
	public ModelAndView detail(int bno,String from, ModelAndView mnv )throws SQLException{
		String url="board/detail";		
		
		BoardVO board =null;
		if(from!=null && from.equals("list")) {
			board=service.getBoard(bno);
			url="redirect:/board/detail.do?bno="+bno;
		}else {
			board=service.getBoardForModify(bno);
		}
					
		mnv.addObject("board",board);		
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(int bno,ModelAndView mnv)throws SQLException{
		String url="board/modify";
		
		BoardVO board = service.getBoardForModify(bno);
		
		mnv.addObject("board",board);		
		mnv.setViewName(url);
		
		return mnv;
	}
	

	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modifyPost(BoardModifyCommand modifyReq,
							 HttpServletRequest request,
							 RedirectAttributes rttr) throws Exception{
		
		String url = "redirect:/board/detail.do";
		
		BoardVO board = modifyReq.toBoardVO();				
		//board.setTitle(HTMLInputFilter.htmlSpecialChars(board.getTitle()));
		board.setTitle((String)request.getAttribute("XSStitle"));
		
		service.modify(board);
		
		rttr.addFlashAttribute("from","modify");
		rttr.addAttribute("bno",board.getBno());
		
		return url;
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String remove(int bno,RedirectAttributes rttr) throws Exception{
		String url = "redirect:/board/detail.do";
		service.remove(bno);		
		
		rttr.addAttribute("bno",bno);
		rttr.addFlashAttribute("from","remove");
		return url;		
	}
		
}
