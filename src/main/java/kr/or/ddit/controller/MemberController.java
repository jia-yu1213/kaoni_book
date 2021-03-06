package kr.or.ddit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.command.MemberModifyCommand;
import kr.or.ddit.command.MemberRegistCommand;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.util.CryptoUtil;
import kr.or.ddit.util.MakeFileName;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/main")
	public void main() {
	}

	@RequestMapping("/list")
	public ModelAndView list(SearchCriteria cri, ModelAndView mnv) throws SQLException {
		String url = "member/list";

		Map<String, Object> dataMap = memberService.getMemberList(cri);
		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);	
		
		return mnv;
	}
	
	@RequestMapping(value = "/registForm", method = RequestMethod.GET)
	public String registForm() {
		String url = "member/regist";
		return url;
	}

	@Resource(name = "picturePath")
	private String picturePath;

	@RequestMapping(value = "/picture", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> picture(@RequestParam("pictureFile") MultipartFile multi, String oldPicture)
			throws Exception {
		ResponseEntity<String> entity = null;

		String result = "";
		HttpStatus status = null;
		System.out.println("?????? : " + oldPicture);
		
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
	
	@RequestMapping(value = "/getPictureById/{id}",method=RequestMethod.GET, 
			produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<byte[]> getPictureById(@PathVariable("id") String id) 
																throws Exception {
		String picture = memberService.getMember(id).getPicture();
		
		return  getPicture(picture);
	}
	
	@RequestMapping("/idCheck")
	@ResponseBody
	public ResponseEntity<String> idCheck(String id) throws Exception {
		ResponseEntity<String> entity = null;

		try {
			MemberVO member = memberService.getMember(id);

			if (member != null) {
				entity = new ResponseEntity<String>("duplicated", HttpStatus.OK);
			} else {
				entity = new ResponseEntity<String>("", HttpStatus.OK);
			}
		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(MemberRegistCommand memberReq, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/member/list";
		
		String key = "a1b2c3d4e5f6g7h8";
		System.out.println("????????? ?????? ??? : " + memberReq.getPwd());
		
		String enc = CryptoUtil.encryptAES256(memberReq.getPwd(), key);
		System.out.println("????????? ??? : " + enc );
		
		
		MemberVO member = memberReq.toMemberVO();
		member.setPwd(enc);
		
		memberService.regist(member);

		rttr.addFlashAttribute("from", "regist");

		return url;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(@ModelAttribute("id") String id, Model model) 
													throws SQLException {

		String url = "member/detail";
		
		MemberVO member = memberService.getMember(id);
		model.addAttribute("member", member);
		

		return url;
	}
	
	@RequestMapping(value = "/modifyForm")
	public String modifyForm(String id, Model model)throws SQLException {

		String url = "member/modify";

		MemberVO member = memberService.getMember(id);
		model.addAttribute("member", member);

		return url;
	}
	

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(MemberModifyCommand modifyReq,HttpSession session,
						 RedirectAttributes rttr)throws Exception {
		String url="redirect:/member/detail.do";
		
		MemberVO member = modifyReq.toParseMember();

		// ?????? ?????? ?????? ??? ?????? ?????? ??????
		String fileName = savePicture(modifyReq.getOldPicture(), modifyReq.getPicture());
		member.setPicture(fileName);
		
		//???????????? ????????? ?????? ????????? ??????
		if (modifyReq.getPicture().isEmpty()) {
			member.setPicture(modifyReq.getOldPicture());
		}
		//DB ?????? ??????
		memberService.modify(member);
		
		rttr.addFlashAttribute("parentReload",false);
		
		// ???????????? ???????????? ?????? ????????? ????????? session ?????????
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser != null && member.getId().equals(loginUser.getId())) {
			member.setAuthority(loginUser.getAuthority());
			session.setAttribute("loginUser", member);
			rttr.addFlashAttribute("parentReload",true);
		}
		
		rttr.addAttribute("id",member.getId());
		rttr.addAttribute("from","modify");
		
		rttr.addFlashAttribute("member",memberService.getMember(modifyReq.getId()));
		
		return url;
	}
	
	
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public String enabled(String id, HttpSession session, RedirectAttributes rttr) throws SQLException {
		String url = "redirect:/member/detail";
		
		MemberVO member;
		
		member = memberService.getMember(id); 
		//DB?????? enabled??? ??????(0)?????? ?????? ??????
		
		memberService.enabled(id);
		
		rttr.addFlashAttribute("activeMember",member);
		rttr.addAttribute("from","active");		
		rttr.addAttribute("id",id);
		
		return url;
	}
	
	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public String disabled(String id, HttpSession session, RedirectAttributes rttr) throws SQLException {
		String url = "redirect:/member/detail";
		
		MemberVO member;
		
		member = memberService.getMember(id); 

		//DB?????? enabled??? ?????????(1)?????? ?????? ??????
		memberService.disabled(id);
		
		rttr.addFlashAttribute("stopMember",member);
		rttr.addAttribute("from","stop");		
		rttr.addAttribute("id",id);
		
		return url;
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(String id, HttpSession session, RedirectAttributes rttr) throws SQLException {
		String url = "redirect:/member/detail";
		
		MemberVO member;

		// ????????? ????????? ??????
		member = memberService.getMember(id); 
		
		String savePath = this.picturePath;
		File imageFile = new File(savePath, member.getPicture());
		if (imageFile.exists()) {
			imageFile.delete();
		}
		//DB?????? ????????? ?????? ??????
		memberService.remove(id);
		
		// ???????????? ????????? ????????? ??????????????? ???????????? ?????????.
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser.getId().equals(member.getId())) {
			session.invalidate();
		}

		rttr.addFlashAttribute("removeMember",member);
		
		rttr.addAttribute("from","remove");		
		rttr.addAttribute("id",id);
		
		return url;
	}
	

}


