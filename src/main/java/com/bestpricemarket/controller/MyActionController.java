package com.bestpricemarket.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bestpricemarket.domain.BasketPager;
import com.bestpricemarket.domain.CSVO;
import com.bestpricemarket.domain.MemberVO;
import com.bestpricemarket.domain.MyActionVO;

import com.bestpricemarket.service.MyActionService;

@Controller
@RequestMapping(value ="/myAction/*")
public class MyActionController {
	@Inject
	private MyActionService service;
	
	private static final Logger l = LoggerFactory.getLogger(MyActionController.class);
	
	
	  @RequestMapping(value = "/insert", method = RequestMethod.GET) 
	  public String insertaction(MyActionVO av) throws Exception{
	      
	  service.insertAction(av);
		  
	  return "/myAction/myAction"; 
	 }
	
	
	// 입찰 목록
	@RequestMapping(value = "/actionlist", method = RequestMethod.GET)
	public String getOrderList(HttpSession session, MyActionVO action, Model model,@RequestParam(value="num",defaultValue="1") int num) throws Exception {
				// num = 페이지 번호
				String a_m_id = (String)session.getAttribute("id");
		
				// 등록된 글의 총 갯수
				int count = service.getCount();

				// 페이지당 출력할 글의 갯수
				int postNum = 5;

				// 페이지 하단 페이징 번호 (등록된 글의 총 갯수 / 한 페이지에 출력할 갯수) + 소수점은 올림(Math.ceil)
				int pageNum = (int) Math.ceil((double) count / postNum);

				// 출력할 글
				int displayPost = (num - 1) * postNum;

				// 한번에 표시할 페이징 번호의 갯수
				int pageNum_cnt = 5;

				// 표시되는 페이지 번호 중 마지막 번호
				int endPageNum = (int) (Math.ceil((double) num / (double) pageNum_cnt) * pageNum_cnt);

				// 표시되는 페이지 번호 중 첫번째 번호
				int startPageNum = endPageNum - (pageNum_cnt - 1);

				// 마지막 번호 재계산
				int endPageNum_tmp = (int) (Math.ceil((double) count / (double) pageNum_cnt));

				if (endPageNum > endPageNum_tmp) {
					endPageNum = endPageNum_tmp;
				}

				boolean prev = startPageNum == 1 ? false : true;
				boolean next = endPageNum * pageNum_cnt >= count ? false : true;

				// 글 목록 가져오기
				List<MyActionVO> actionlist = null;
				actionlist = service.actionlist(displayPost, postNum, a_m_id);
				model.addAttribute("actionlist", actionlist);
				model.addAttribute("pageNum", pageNum);

				// 시작 및 끝 번호
				model.addAttribute("startPageNum", startPageNum);
				model.addAttribute("endPageNum", endPageNum);

				// 이전 및 다음
				model.addAttribute("prev", prev);
				model.addAttribute("next", next);

				// 현재 페이지
				model.addAttribute("select", num);

			 return "/myAction/myAction";
	}
    
	 //낙찰 목록
	@RequestMapping(value = "/paylist", method = RequestMethod.GET)
    public String getPaylist(HttpSession session, MyActionVO action, Model model,@RequestParam(value="num",defaultValue="1") int num) throws Exception {
    	
		String a_m_id = (String)session.getAttribute("id");
		
		// 등록된 글의 총 갯수
		int count = service.getPayCount();

		// 페이지당 출력할 글의 갯수
		int postNum = 5;

		// 페이지 하단 페이징 번호 (등록된 글의 총 갯수 / 한 페이지에 출력할 갯수) + 소수점은 올림(Math.ceil)
		int pageNum = (int) Math.ceil((double) count / postNum);

		// 출력할 글
		int displayPost = (num - 1) * postNum;

		// 한번에 표시할 페이징 번호의 갯수
		int pageNum_cnt = 5;

		// 표시되는 페이지 번호 중 마지막 번호
		int endPageNum = (int) (Math.ceil((double) num / (double) pageNum_cnt) * pageNum_cnt);

		// 표시되는 페이지 번호 중 첫번째 번호
		int startPageNum = endPageNum - (pageNum_cnt - 1);

		// 마지막 번호 재계산
		int endPageNum_tmp = (int) (Math.ceil((double) count / (double) pageNum_cnt));

		if (endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}

		boolean prev = startPageNum == 1 ? false : true;
		boolean next = endPageNum * pageNum_cnt >= count ? false : true;

		// 글 목록 가져오기
		List<MyActionVO> paylist = null;
		paylist = service.paylist(displayPost, postNum, a_m_id);
		model.addAttribute("paylist", paylist);
		model.addAttribute("pageNum", pageNum);
		
		// 시작 및 끝 번호
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);

		// 이전 및 다음
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);

		// 현재 페이지
		model.addAttribute("select", num);
		
    	return "/myAction/myAction2";
    }
	
	// 선택 삭제
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int deleteCart(HttpSession session,
	     @RequestParam(value = "chbox[]") List<String> chArr, MyActionVO av) throws Exception {
	 l.info("delete cart");
	
	 String id = (String)session.getAttribute("id");
	 
	 int result = 0;
	 int cartNum = 0;
	 
	 
	 if(id != null) {
	  av.setA_m_id(id);
	  
	  for(String i : chArr) {   
	   cartNum = Integer.parseInt(i);
	   av.setAno(cartNum);
	   service.delete(av);
	  }   
	  result = 1;
	 }  
	 return result;  
	}
	

}
