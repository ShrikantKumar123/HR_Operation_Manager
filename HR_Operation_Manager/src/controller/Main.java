package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import dao.ApplicationOperation;
import dao.AdminLogin;
import dao.DepartmentDaoImpl;
import dao.EmployeeDaoImpl;
import dao.ApplicationDaoImpl;
import dao.UserDaoImpl;
import model.Application;
import model.Department;
import model.Employee;

import model.User;

public class Main {


	public static void main(String[] args) throws Exception {

		// All Objects
		User user=new User();
		UserDaoImpl userdao=new UserDaoImpl();
		Employee employee=new Employee();
		EmployeeDaoImpl employeedao= new EmployeeDaoImpl();
		AdminLogin admin=new AdminLogin();
		Application application=new Application();
		ApplicationDaoImpl applicationdao=new ApplicationDaoImpl();
		Department department=new Department();
		DepartmentDaoImpl departmentdao=new DepartmentDaoImpl();
		ApplicationOperation applicationtask=new ApplicationOperation();

		//Date 
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int select;
		int select1;
		int choice;
		int userid=0;//for user specific operations
		int Empid=0;//for employee specific operations
		Boolean flag=false;
		Boolean eflag=false;
		Boolean uflag=false;

		do {
			mainmenu();
			//Main Menu

			select=Integer.parseInt(br.readLine()); 
			switch(select)
			{
			case 1:
				//admin menu display 
				do {
					if(flag!=true)
					{
						if(admin.AdminLogin()==true)//Admin Login
							
						{
							flag=true;
							System.out.println("Login successfully\n\n");
						}
						else
						{
							System.out.println("Please Enter Correct username or password");
						}

					}	
					if(flag==true)
					{
						adminmenu();//Admin menu display
						select=Integer.parseInt(br.readLine()); 
						switch(select)
						{
						case 1:
							//Adding Employee to DataBase
							System.out.println("add employee \n");
							
							
							
							
							System.out.println("Enter Employee Id");
							int employeeid=br.read();
							System.out.println("Enter Employee Password");
							String epassword=br.readLine();
							System.out.println("Enter Employee positionId");
							int positionid=br.read();
							System.out.println("Enter Employee superwiser");
							String superviser=br.readLine();
							
							System.out.println("Enter Employee hire date");
							int hiredate=br.read();
							System.out.println("Enter Employee salary");
							double salary=br.read();
							System.out.println("Enter Employee Department id");
							int deptid=br.read();
							System.out.println("Enter Employee quali Id");
							String qualid=br.readLine();
							System.out.println("Enter Employee gender");
							String gender=br.readLine();
							System.out.println("Enter Employee Namge");
							String ename=br.readLine();
							System.out.println("Enter Employee Role");
							String erole=br.readLine();

							employee.setEmployeeid(employeeid);
							employee.setEpassword(epassword);
							employee.setPositionid(positionid);
							employee.setSuperviser(superviser);
							employee.setHiredate(hiredate);
							employee.setSalary(salary);
							employee.setDeptid(deptid);
							employee.setQualid(qualid);
							employee.setGender(gender);
							employee.setEname(ename);
							employee.setErole(erole);
							
							
							employeedao.signUp(employee);


							break;
						case 2:
							//Adding User to DataBase
							System.out.println("Enter User Name");
							String name=br.readLine();
							System.out.println("Enter User Phone Number");
							String pnumber=br.readLine();
							System.out.println("Enter User Password");
							String password=br.readLine();

							user.setUname(name);
							user.setUnumber(pnumber);
							user.setUpassword(password);
							userdao.signUp(user);




							break;
						case 3:
							//Adding Department To DataBase
							System.out.println("ENTER  DEPARTMENT NAME");
							String dname=br.readLine();
							System.out.println("ENTER  DEPARTMENT NUMBER");
							String dnumber=br.readLine();

							department.setDepartment_name(dname);
							
							int status=departmentdao.AddDepartment(department);
							if(status==1)
							{
								System.out.println("\n________________________________________________________________________________________________");
								System.out.println("|\t\t\tDepartment "+dname+" Added Sucessfully With Department ID "+department.getDepartment_id()+"\t\t|");
								System.out.println("-------------------------------------------------------------------------------------------------\n");
							}


							break;
						case 4:
							//Check Application

							do{
								applicationtype();
								select1=Integer.parseInt(br.readLine());
								switch(select1) {


								case 1:
									applicationtask. viewapplication("pending");
									break;
								case 2:
									applicationtask. viewapplication("working");
									break;
								case 3:applicationtask. viewapplication("COMPLETED");
								break;
								case 4:applicationtask. viewapplication("all");	
								break;
								default:break;
								}


							}
							while(select1!=0);


							break;	

						default: break;

						}


					}

				}
				while (select !=0);
				break;
			case 2:
				//Employee Login
				System.out.println("Enter the user name");
				String name=br.readLine();

				System.out.println("Enter password");
				String pass=br.readLine();
				employee.setEname(name);
				employee.setEpassword(pass);


				do {
					if(eflag!=true)
					{

						if(employeedao.loginEmployee(employee)==true)
							
						{
							eflag=true;
							Empid=employeedao.returnid();
							System.out.println("Login successfully with "+Empid+"\n\n");

						}
						else
						{
							System.out.println("Please Enter Correct username or password");
							break;
						}

					}	
					if(eflag==true)
					{
						//Employee menu
						employeemenu();
						select=Integer.parseInt(br.readLine()); 
						switch(select)
						{
						case 1:
							//view Application status and work on Application
							String eid=Integer.toString(Empid);
							applicationtask. viewapplication(eid);	
							break;
						case 2:
							//resolve Application and send sms
							applicationtask.resolveapplication(Empid);
							break;
						case 0:System.out.println("Back to main menu");
						eflag=false;
						break;
						default:
							break;
						}
					}
				}
				while (select !=0);
				break;





			case 3:
				//User Login
				System.out.println("Enter the user name");
				String ename=br.readLine();
				System.out.println("Enter password");
				String epass=br.readLine();
				user.setUname(ename);
				user.setUpassword(epass);


				do {
					if(uflag!=true)

					{
						if(userdao.loginUser(user)==true)//user login
							
						{
							uflag=true;
							System.out.println("Login successfully\n\n");
							userid=userdao.returnid();


						}
						else
						{
							System.out.println("Please Enter Correct username or password");
							break;
						}

					}	
					if(uflag==true)
					{
						//User Menu
						usermenu();
						select=Integer.parseInt(br.readLine()); 
						switch(select)
						{
						case 1:
							//Adding Application
							int id=userdao.returnid();


							System.out.println("ENTER EMAIL");
							String email=br.readLine();

							System.out.println("ENTER OPURTUNITY ID");
							String opportunity_id=br.readLine();
							System.out.println("ENTER RESUME LOCATION");
							String resume_location=br.readLine();
							System.out.println("INTERVIEW DATE");
							double interview_date=br.read();
							System.out.println("STATUS");
							String status=br.readLine();



							application.setEmail(email);
							application.setOpportunity_id(opportunity_id);
							
							
							
							application.setResume_location(resume_location);
							
							application.setStatus(status);
							
							application.setInterview_date(interview_date);
							
							
							applicationdao.Addapplication(application);


							break;
						case 2:
							//check status of the Application


							applicationdao.viewstatus(userid);
							break;
						case 0:System.out.println("Back to main menu");
						uflag=false;
						break;
						default:
							break;
						}

					}
				}

				while (select !=0);
				break;

			default:
				break;



			}
		}
		while (select !=4);
		System.out.println("Thank You");



	}



	static void mainmenu()
	{
		
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println("                         Welcome To The HR Operation management System                              ");
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println("1. Admin ");
		System.out.println("2. Employee");
		System.out.println("3. User");
		System.out.println("4. Exit");
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println("Select Any Option");
	}

	static void adminmenu()
	{
		System.out.println("1. Add Employee");
		System.out.println("2. Add User");
		System.out.println("3. Add Department");
		System.out.println("4. View Application");
		System.out.println("0. LOGOUT AND  BACK TO MAIN MENU");
		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println("Select Any Option");
	}
	static void employeemenu()
	{
		System.out.println("1. View Application");
		System.out.println("2. Resolve Application");

		System.out.println("0. LOGOUT AND  BACK TO MAIN MENU");
		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println("Select Any Option");
	}

	static void usermenu()
	{
		System.out.println("1. Add Application");
		System.out.println("2. Check Application Status");
		System.out.println("0. LOGOUT AND  BACK TO MAIN MENU");
		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println("Select Any Option");
	}

	static void applicationtype()
	{
		System.out.println("Select Which type of Application Do You Want View");
		System.out.println("1. Pending Application");
		System.out.println("2. Working Application");
		System.out.println("3. Resolved Application");
		System.out.println("4. All Application");
		System.out.println("0. BACK TO Admin MENU");
	}


}


