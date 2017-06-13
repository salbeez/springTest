package com.kosta.web;

public class TestVO {
	private String id;// form의 name과 일치
	private String pass;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "TestVO [id=" + id + ", pass=" + pass + "]";
	}

}
