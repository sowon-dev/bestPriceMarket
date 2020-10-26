package com.bestpricemarket.domain;

import java.sql.Timestamp;

public class GoodsVO {
	
	private int gno;
	private String g_m_id;
	private String category;
	private String gname;
	private int lowestprice;
	private String content;
	private String image;
	private int like;
	private Timestamp regDate;
	private Timestamp endDate;
	private int actionstatus;
	
	
	// 생성자
	public GoodsVO() {}
	public GoodsVO(int gno, String g_m_id, String category, String gname, int lowestprice, String content, String image,
			int like, Timestamp regDate, Timestamp endDate, int actionstatus) {
		super();
		this.gno = gno;
		this.g_m_id = g_m_id;
		this.category = category;
		this.gname = gname;
		this.lowestprice = lowestprice;
		this.content = content;
		this.image = image;
		this.like = like;
		this.regDate = regDate;
		this.endDate = endDate;
		this.actionstatus = actionstatus;
	}
	
	
	// set/get 메소드
	public int getGno() {
		return gno;
	}
	
	public void setGno(int gno) {
		this.gno = gno;
	}
	
	public String getG_m_id() {
		return g_m_id;
	}
	public void setG_m_id(String g_m_id) {
		this.g_m_id = g_m_id;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	
	public int getLowestprice() {
		return lowestprice;
	}
	public void setLowestprice(int lowestprice) {
		this.lowestprice = lowestprice;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	
	public int getActionstatus() {
		return actionstatus;
	}
	public void setActionstatus(int actionstatus) {
		this.actionstatus = actionstatus;
	}




	@Override
	public String toString() {
		return "GoodsVO [gno=" + gno + ", g_m_id=" + g_m_id + ", category=" + category + ", gname=" + gname
				+ ", lowestprice=" + lowestprice + ", content=" + content + ", image=" + image + ", like=" + like
				+ ", regDate=" + regDate + ", endDate=" + endDate + ", actionstatus=" + actionstatus + "]";
	}
	
	
	

}
