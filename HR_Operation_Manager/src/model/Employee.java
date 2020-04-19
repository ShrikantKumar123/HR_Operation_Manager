package model;

import java.util.Date;

public class Employee {
	
	//private int eid;
	private int employeeid;
	private String epassword;
	private int positionid;
	private String superviser;
	private double hiredate;
	private double salary;
	private String qualid;
	private String gender;
	private String ename;
	private String erole;
	private int deptid;
	
	public Employee(int employeeid, String epassword, int positionid, String superviser, double hiredate, double salary,
			String qualid, String gender, String ename, String erole, int deptid) {
		super();
		this.employeeid = employeeid;
		this.epassword = epassword;
		this.positionid = positionid;
		this.superviser = superviser;
		this.hiredate = hiredate;
		this.salary = salary;
		this.qualid = qualid;
		this.gender = gender;
		this.ename = ename;
		this.erole = erole;
		this.deptid = deptid;
	}
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	//public Employee() {
		// TODO Auto-generated constructor stub
	//}
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	public String getEpassword() {
		return epassword;
	}
	public void setEpassword(String epassword) {
		this.epassword = epassword;
	}
	public int getPositionid() {
		return positionid;
	}
	public void setPositionid(int positionid) {
		this.positionid = positionid;
	}
	public String getSuperviser() {
		return superviser;
	}
	public void setSuperviser(String superviser) {
		this.superviser = superviser;
	}
	public double getHiredate() {
		return hiredate;
	}
	public void setHiredate(double hiredate) {
		this.hiredate = hiredate;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getQualid() {
		return qualid;
	}
	public void setQualid(String qualid) {
		this.qualid = qualid;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getErole() {
		return erole;
	}
	public void setErole(String erole) {
		this.erole = erole;
	}
	public int getDeptid() {
		return deptid;
	}
	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}
	
	}
