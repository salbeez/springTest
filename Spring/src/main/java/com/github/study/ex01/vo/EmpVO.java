package com.github.study.ex01.vo;

public class EmpVO {
	private Integer empno;
	private String ename;
	private Integer sal;

	public EmpVO() {
	}

	public EmpVO(Integer empno, String ename, Integer sal) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.sal = sal;
	}

	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Integer getSal() {
		return sal;
	}

	public void setSal(Integer sal) {
		this.sal = sal;
	}

}
