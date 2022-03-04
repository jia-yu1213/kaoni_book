package kr.or.ddit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.service.RentService;

@Controller
@RequestMapping("/rent")
public class RentController {

	@Autowired
	private RentService rentService;

	@RequestMapping(value="/checkRent",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> memCheckRent() throws Exception{
		
		//로그인 구현하고 아이디 넣기
		HttpStatus status = HttpStatus.OK;
		Map<String, Object> dataMap = rentService.checkRent("1");
		ResponseEntity<Map<String, Object>> entity = new ResponseEntity<>(dataMap, status);
		
		return entity;
	}
	

}





