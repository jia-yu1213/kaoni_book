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
import kr.or.ddit.util.MakeFileName;

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
	
	@RequestMapping(value = "/registForm", method = RequestMethod.GET)
	public ModelAndView registForm(ModelAndView mnv) throws SQLException {
		
		String url = "book/regist";
		
		List<BookVO> cateList =  bookService.selectCateList();

		mnv.setViewName(url);
		mnv.addObject("cateList",cateList);
		return mnv;
	}

	@Resource(name = "bookPicturePath")
	private String picturePath;

	@RequestMapping(value = "/picture", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> picture(@RequestParam("pictureFile") MultipartFile multi, String oldPicture)
			throws Exception {
		ResponseEntity<String> entity = null;

		String result = "";
		HttpStatus status = null;

		/* 파일저장확인 */
		if ((result = savePicture(oldPicture, multi)) == null) {
			result = "업로드 실패했습니다.!";
			status = HttpStatus.BAD_REQUEST;
		} else {
			status = HttpStatus.OK;

		}

		entity = new ResponseEntity<String>(result, status);

		return entity;
	}

	private String savePicture(String oldPicture, MultipartFile multi) throws Exception {
		String fileName = null;

		/* 파일유무확인 */
		if (!(multi == null || multi.isEmpty() || multi.getSize() > 1024 * 1024 * 5)) {

			/* 파일저장폴더설정 */
			String uploadPath = picturePath;
			fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");
			File storeFile = new File(uploadPath, fileName);

			storeFile.mkdirs();

			// local HDD 에 저장.
			multi.transferTo(storeFile);

			if (oldPicture != null && !oldPicture.isEmpty()) {
				File oldFile = new File(uploadPath, oldPicture);
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
		}
		return fileName;
	}
	
	@RequestMapping(value = "/getPicture", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<byte[]> getPicture(String picture) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		String imgPath = this.picturePath;
		try {

			in = new FileInputStream(new File(imgPath, picture));

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), 
					HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			in.close();
		}
		return entity;
	}
	

	@RequestMapping("/regist")
	public String regist(BookRegistCommand regReq,
			             HttpServletRequest request,
						 RedirectAttributes rttr)throws Exception{
		String url="redirect:/book/list.do";
		
		BookVO book = regReq.toBookVO();
		
		bookService.regist(book);
		
		rttr.addFlashAttribute("from","regist");
		
		return url;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(String book_no, ModelAndView mnv) throws SQLException{
			String url = "book/detail";
			BookVO book = bookService.getBook(book_no);
			mnv.addObject("book",book);
			mnv.setViewName(url);
			return mnv;
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String remove(String book_no,RedirectAttributes rttr) throws Exception{
		String url = "redirect:/book/detail.do";

		BookVO book = new BookVO();
		book.setBook_no(book_no);
		book.setBook_status(2);
		bookService.modifyStatus(book);
		
		rttr.addAttribute("book_no",book_no);
		rttr.addFlashAttribute("from","remove");
		return url;		
	}
	
	@RequestMapping(value = "/modifyForm")
	public ModelAndView modifyForm(String book_no, ModelAndView mnv)throws SQLException {

		String url = "book/modify";
		List<BookVO> cateList =  bookService.selectCateList();
		mnv.addObject("cateList",cateList);
		BookVO book = bookService.getBook(book_no);
		mnv.addObject("book", book);
		
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping(value="/modify")
	public String modify(BookModifyCommand modifyReq, RedirectAttributes rttr) throws Exception {
		String url ="redirect:/book/detail.do";
		
		BookVO book = modifyReq.toBookVO();
		
		String fileName = savePicture(modifyReq.getOldPicture(), modifyReq.getPicture());
		book.setPhoto(fileName);
		
		if (modifyReq.getPicture().isEmpty()) {
			book.setPhoto(modifyReq.getOldPicture());
		}
		
		bookService.modify(book);
		
		rttr.addFlashAttribute("from","modify");
		rttr.addAttribute("book_no", book.getBook_no());
		return url;
	}
	
	
	
	
	
}





