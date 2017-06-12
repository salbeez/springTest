package org.zerock.domain;

public class Emp {
	private int empno;
	private String ename;
	private String sal;

	public Emp() {
	}

	public Emp(int empno, String ename, String sal) {
		this.empno = empno;
		this.ename = ename;
		this.sal = sal;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getSal() {
		return sal;
	}

	public void setSal(String sal) {
		this.sal = sal;
	}

	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", sal=" + sal + "]";
	}

}
