package com.kosta.myapp.s0610.vo;

import java.sql.Date;

public class BoardinfoVO {
	private int board_no;
	private String member_id;
	private String board_title;
	private String board_text;
	private Date board_date;
	private Date board_lastupdate;

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_text() {
		return board_text;
	}

	public void setBoard_text(String board_text) {
		this.board_text = board_text;
	}

	public Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}

	public Date getBoard_lastupdate() {
		return board_lastupdate;
	}

	public void setBoard_lastupdate(Date board_lastupdate) {
		this.board_lastupdate = board_lastupdate;
	}

}
