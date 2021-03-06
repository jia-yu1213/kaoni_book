package kr.or.ddit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.command.BookModifyCommand;
import kr.or.ddit.command.BookRegistCommand;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BookVO;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.dto.RentVO;
import kr.or.ddit.dto.ReservationVO;
import kr.or.ddit.service.BookService;
import kr.or.ddit.service.RentService;
import kr.or.ddit.util.MakeFileName;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private RentService rentService;
	
	@RequestMapping("/main")
	public String main()throws Exception{
		return "book/list";
	}
	
//	@RequestMapping("/list")
//	public ModelAndView list(SearchCriteria cri, ModelAndView mnv,HttpSession session)throws Exception{
//		List<BookVO> cateList =  bookService.selectCateList();
////		List<ReservationVO> resList = rentService.getResStatus0List();
////		model.addAttribute("resList",resList);
//		mnv.addObject("cateList",cateList);
//		Map<String,Object> dataMap = bookService.getBookList(session,cri);
//		
//		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
//		int countRes = rentService.getResverationCount(loginUser.getId());
//		
//		mnv.addObject("dataMap", dataMap);
//		mnv.addObject("countRes", countRes);
//		
//		return mnv;
//	}
	
	@RequestMapping("/list")
	public void list(SearchCriteria cri, Model model,HttpSession session)throws Exception{
		List<BookVO> cateList =  bookService.selectCateList();
//		List<ReservationVO> resList = rentService.getResStatus0List();
//		model.addAttribute("resList",resList);
		model.addAttribute("cateList",cateList);
		Map<String,Object> dataMap = bookService.getBookList(session,cri);		
		model.addAllAttributes(dataMap);
	}
	
	@RequestMapping("/returnBookMaster")
	public void returnList(SearchCriteria cri, Model model)throws Exception{
	
		Map<String,Object> dataMap = bookService.getWaitList(cri);		
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

		/* ?????????????????? */
		if ((result = savePicture(oldPicture, multi)) == null) {
			result = "????????? ??????????????????.!";
			status = HttpStatus.BAD_REQUEST;
		} else {
			status = HttpStatus.OK;

		}

		entity = new ResponseEntity<String>(result, status);

		return entity;
	}

	private String savePicture(String oldPicture, MultipartFile multi) throws Exception {
		String fileName = null;

		/* ?????????????????? */
		if (!(multi == null || multi.isEmpty() || multi.getSize() > 1024 * 1024 * 5)) {

			/* ???????????????????????? */
			String uploadPath = picturePath;
			fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");
			File storeFile = new File(uploadPath, fileName);

			storeFile.mkdirs();

			// local HDD ??? ??????.
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
	public ModelAndView detail(String book_no,Integer rent_able, ModelAndView mnv,HttpSession session) throws SQLException{
			String url = "book/detail";
			BookVO book = bookService.getBook(session,book_no);
			if (rent_able != null) {
				book.setRent_able(rent_able);
				
			}
			mnv.addObject("book",book);
			mnv.setViewName(url);
			return mnv;
	}
	
	@RequestMapping("/resDetail")
	public ModelAndView resDetail(String book_no,ModelAndView mnv,HttpSession session) throws SQLException{
			String url = "mylist/resDetail";
			
			BookVO book = bookService.getBook(session,book_no);

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
	public ModelAndView modifyForm(String book_no, ModelAndView mnv,HttpSession session)throws SQLException {

		String url = "book/modify";
		List<BookVO> cateList =  bookService.selectCateList();
		mnv.addObject("cateList",cateList);
		BookVO book = bookService.getBook(session,book_no);
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
	
	@RequestMapping(value="/saveBook")
	@ResponseBody
	public ResponseEntity<String> saveChat(String title, HttpSession session) throws Exception{
		//???????????? ???????????? ?????? ??????
		String fileName = MakeFileName.toUUIDFileName(title, "$$");
		
		//bookList ???????????? ????????????
		List<BookVO> bookList = bookService.saveBookList();
		
		writeBookListToFile("c:/kaonibook/"+fileName+".xlsx", bookList);
		HttpStatus status = HttpStatus.OK;
		ResponseEntity<String> entity = new ResponseEntity<>("ok",status);
		return entity;
		
	}
	
    public static void writeBookListToFile(String fileName, List<BookVO> bookList) throws Exception{
        Workbook workbook = null;
        
        if(fileName.endsWith("xlsx")){
            workbook = new XSSFWorkbook();
        }else if(fileName.endsWith("xls")){
            workbook = new HSSFWorkbook();
        }else{
            throw new Exception("invalid file name, should be xls or xlsx");
        }
         
        Sheet sheet = workbook.createSheet("cordova");
         
        Iterator<BookVO> iterator = bookList.iterator();
         
        int rowIndex = 0;
        int excelname=0; // ???????????? ???????????? ?????? ?????? ????????? ??????
        do{
        	BookVO book = iterator.next();
            Row row = sheet.createRow(rowIndex++);
            
            if(excelname==0){ // ????????? ????????? 
                 Cell cell0 = row.createCell(0);
                 cell0.setCellValue("??????");
                 Cell cell1 = row.createCell(1);
                 cell1.setCellValue("??????");
                 Cell cell2 = row.createCell(2);
                 cell2.setCellValue("??????");
                 Cell cell3 = row.createCell(3);
                 cell3.setCellValue("?????????");
                 Cell cell4 = row.createCell(4);
                 cell4.setCellValue("?????????");
                 excelname++;
                
            }else{  // ??????????????? ??????????????? ?????? ????????? 
                 
                 Cell cell0 = row.createCell(0);
                 cell0.setCellValue(book.getCate_name());
                 Cell cell1 = row.createCell(1);
                 cell1.setCellValue(book.getTitle());
                 Cell cell2 = row.createCell(2);
                 cell2.setCellValue(book.getWriter());
                 Cell cell3 = row.createCell(3);
                 cell3.setCellValue(book.getPublisher());
                 Cell cell4 = row.createCell(4);
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                 cell4.setCellValue(sdf.format(book.getPublishing_date()));
            }
            
        }while(iterator.hasNext());
     
        //lets write the excel data to file now
        FileOutputStream fos = new FileOutputStream(fileName);
        workbook.write(fos);
        fos.close();
        
        System.out.println(fileName + " written successfully");
    }
	
	
	@RequestMapping(value="/excelUpload")
	public String excelUpload(MultipartFile file, MultipartHttpServletRequest request)throws SQLException, IllegalStateException, IOException{
		
		String url = "redirect:/book/list";
		
		MultipartFile excelFile = request.getFile("excelFile");
		
		File destFile = new File("c:\\upload\\"+excelFile.getOriginalFilename());
		//?????? ????????? ????????? ?????? ?????????
		excelFile.transferTo(destFile);
		//????????? ?????? ??? ??????
		bookService.excelUpload(destFile);
		destFile.delete();
		
		
		return url;
	}
	
	
	@RequestMapping("/returnAccept")
	public String returnBook(String book_no,String rent_no) throws Exception{
		String url = "redirect:/book/returnBookMaster";
		
		BookVO book = new BookVO();
		book.setBook_no(book_no);
		book.setBook_status(0);
		bookService.modifyStatus(book);
		
		RentVO rent = rentService.getRent(rent_no);
		rent.setRent_status(2);
		rentService.updateRealRentStatus(rent);
		
		return url;
	}
	
	@RequestMapping("/returnCancle")
	public String returnCancle(String book_no,String rent_no) throws Exception{
		String url = "redirect:/book/returnBookMaster";

		RentVO rent = rentService.getRent(rent_no);
		rent.setRent_status(1);
		rentService.updateRealRentStatus(rent);
		
		//realend ??????
		rentService.updateReturnCancle(rent);
		return url;
	}
	
	
	//?????? ??? ?????? ?????? ???????????? 
	@RequestMapping("/statusCheck")
	public ModelAndView Status(ModelAndView mnv, HttpSession session, String id, String book_no) throws SQLException{
		Map<String, Object> dataMap = bookService.selectCheckStatus(id, book_no);
		mnv.addObject("dataMap", dataMap);
		
		return mnv;
	}
}





