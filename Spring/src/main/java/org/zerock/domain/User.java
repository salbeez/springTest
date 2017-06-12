package org.zerock.domain;

import java.util.Date;

public class User {
	private int id;
	private String name;
	private String pass;
	private String email;
	private String tel;
	private String contents;
	private Date gdate;

	public User() {
	}

	public User(String name, String pass, String email, String tel, String contents) {
		super();
		this.name = name;
		this.pass = pass;
		this.email = email;
		this.tel = tel;
		this.contents = contents;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getGdate() {
		return gdate;
	}

	public void setGdate(Date gdate) {
		this.gdate = gdate;
	}

	public int size() {
		return 7;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pass=" + pass + ", email=" + email + ", tel=" + tel
				+ ", contents=" + contents + ", gdate=" + gdate + "]";
	}

}
