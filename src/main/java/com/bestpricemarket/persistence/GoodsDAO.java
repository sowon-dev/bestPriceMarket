package com.bestpricemarket.persistence;


import java.util.HashMap;
import java.util.List;

import com.bestpricemarket.domain.GoodsCommentVO;
import com.bestpricemarket.domain.GoodsVO;

public interface GoodsDAO {
	
	// 상품등록
	public void registerGoods(GoodsVO vo) throws Exception; 
	
	// 파일업로드
	public void uploadFile(HashMap<String, Object> hm);
	
	// 상품목록
	public List<GoodsVO> listGoods() throws Exception;
		
	// 상품조회(상품상세페이지)
	public GoodsVO goodsDetail(int gno) throws Exception;
		
	// 상품수정
		
	// 상품삭제
	
	

}
