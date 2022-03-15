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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.command.ResverationCommand;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.dto.RentVO;
import kr.or.ddit.dto.ReservationVO;
import kr.or.ddit.service.RentService;
import kr.or.ddit.util.MakeFileName;

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
		rent.setId(member.getId());
		rent.setBook_no(book_no);
		rentService.registRent(rent);
		
		return url;
	}
	
	@RequestMapping("/reservation")
	public String reservation(ResverationCommand cmd, HttpSession session) throws Exception{
		String url = "redirect:/book/list";
		
		
		System.out.println("book_no : " + cmd.getBook_no());
		System.out.println("book_status : " +cmd.getBook_status());
		
		System.out.println("id : " + cmd.getId());
		
		ReservationVO res = cmd.toRegist();
		rentService.modifyBookResStatus(res);
		
		return url;
	}
	
	@RequestMapping("/delRes")
	public ResponseEntity<ReservationVO> delRes(ResverationCommand cmd, HttpSession session) throws Exception{
		
		ResponseEntity<ReservationVO> entity = null;
		
		ReservationVO res = cmd.toRemove();
		try {
			rentService.removeReservation(res);
			entity = new ResponseEntity<ReservationVO>(HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<ReservationVO>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
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
	
	@RequestMapping("/detail")
	public ModelAndView detail(String rent_no, ModelAndView mnv) throws SQLException {
			
			String url = "mylist/detail";
			
			RentVO rent = rentService.getRent(rent_no);
			mnv.addObject("rent", rent);
			mnv.setViewName(url);
			
			return mnv;
	}
	
	@RequestMapping("/modify")
	public String updateRent(String rent_no) throws Exception{
		String url = "redirect:/mylist/list";
		
		rentService.modifyRentStatus(rent_no);
		
		return url;
	}
	
	
// 도서 예약
	
	//리스트 출력
//	@RequestMapping("/reseveration")
//	public String list(RedirectAttributes rttr,HttpSession session,SearchCriteria cri, Model model)throws Exception{
//		MemberVO member = (MemberVO) session.getAttribute("loginUser");
//			String url="mylist/reseveration";
//			String id = member.getId();
//			
//			Map<String,Object> dataMap = rentService.getResList(cri, id);		
//			model.addAllAttributes(dataMap);
//			System.out.println("여기로 안오냐!!!!!!!!!!!!!");
//			return url;
//	}
	
	//예약하기
	@RequestMapping(value="/registRes")
	public String registRes(ResverationCommand resCom, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/book/list";
		
		ReservationVO res = resCom.toRegist();
		
		rentService.modifyBookResStatus(res);
		rttr.addFlashAttribute("form", "registRes");
		
		return url;
		
	}
	
	
	//예약취소
	@RequestMapping(value="/deleteRes")
	public String deleteRes(ResverationCommand resCom, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/mylist/list";
		
		ReservationVO res = resCom.toRegist();
		
		rentService.removeReservation(res);
		rttr.addFlashAttribute("form", "deleteRes");
		
		return url;
		
	}
	
	

}





