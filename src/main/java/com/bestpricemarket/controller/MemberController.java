package com.bestpricemarket.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bestpricemarket.domain.MemberVO;
import com.bestpricemarket.service.MemberService;

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {

	@Inject
	//@Autowired
	private MemberService service;
	
	private static final Logger l = LoggerFactory.getLogger(MemberController.class);
	
	/* 회원가입 */
	// http://localhost:8088/member/join
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String insertGET() throws Exception {
		return "/member/loginandjoin";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String insertPOST(MemberVO vo) throws Exception{
		l.info("C: 회원가입포스트메서드"+ vo);
		System.out.println("C: 회원가입 한글확인 "+vo);
		service.joinMember(vo);		
		return "redirect:/member/login";
	}
	
	//회원가입시 아이디중복확인
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public @ResponseBody int idCheck(@RequestParam("id") String id) throws Exception {
		MemberVO ck = service.idCheck(id);
		if(ck != null) return 1;
		else return 0;
	}
	
	/* 로그인 기능 */
	// http://localhost:8088/member/login
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGET() throws Exception{
		l.info("C: 로그인 입력페이지 GET");
		return "/member/loginandjoin";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
		l.info("C: 로그인POST"+ vo.getId() + vo.getPw());
		MemberVO returnVO = service.loginMember(vo);
		l.info("C: 리턴VO결과(서비스에서 예외처리를 진행했으므로 null이 출력되면 코드에 문제있다는 의미) "+returnVO);
		
		if(returnVO != null) {
			session.setAttribute("id", returnVO.getId());			
			rttr.addFlashAttribute("mvo", returnVO);
			return "redirect:/member/main"; 
		} else {
			// 해당 정보 없는 경우 : => login페이지로 이동
			return "redirect:/member/login";
		}
	}
	
	/* 메인페이지 */
	// http://localhost:8088/member/main
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String mainGET() throws Exception{
		l.info("C: 메인 출력페이지 GET");
		return "main";
	}
	
}
