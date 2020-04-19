package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;

import utility.ConnectionManager;

public class EmployeeDaoImpl implements EmployeeDaoInterface {

	int loginid;
	String returnuser;
	String returnpass;

	public int signUp(Employee employee) throws Exception {
		String INSERT_USERS_SQL = "INSERT INTO EMPLOYEE(EMPLOYEE_ID,PASSWORD,POSITION_ID,SUPERVISOR,HIREDATE,SALARY,DEPT_ID,QUAL_ID,GENDER,NAME,ROLE)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		String count="select count(*) AS rowcount from EMPLOYEE";

		
		
		
		
		
		
		int result = 0;
		try
		{
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
			} catch (Exception e) {

				e.printStackTrace();
			}
			ResultSet rs=connection.createStatement().executeQuery(count);
			rs.next();
			int Eid = rs.getInt("rowcount") ;
			rs.close();

			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setInt(1,++Eid);
			preparedStatement.setString(2,employee.getEpassword());
			preparedStatement.setInt(3,employee.getPositionid());
			preparedStatement.setString(4,employee.getSuperviser());
			preparedStatement.setDouble(5,employee.getHiredate());
			preparedStatement.setDouble(6, employee.getSalary());
			preparedStatement.setInt(7,employee.getDeptid());
			preparedStatement.setString(8,employee.getQualid());
			preparedStatement.setString(9,employee.getGender());
			preparedStatement.setString(10,employee.getEname());
			preparedStatement.setString(11,employee.getErole());
			

			result = preparedStatement.executeUpdate();




			System.out.println("Employee "+employee.getEname()+" Added sucessfully\n\n");

		} catch (SQLException e) {
			System.out.println(e);
		}
		return result;
	}



	public boolean loginEmployee(Employee employee) throws Exception {
		boolean status = false;
		try{
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
			} catch (Exception e) {

				e.printStackTrace();
			}

			String getid="select ID as userid from USER1  where NAME = ? and PASSWORD = ?";
			PreparedStatement preparedStatement = connection.prepareStatement("select * from employee where NAME = ? and PASSWORD = ? ");


			returnuser=employee.getEname();
			returnpass=	employee.getEpassword();
			preparedStatement.setString(1,employee.getEname());
			preparedStatement.setString(2, employee.getEpassword());

		
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();





		} catch (SQLException e) {

			System.out.println(e);
		}

		return status;
	}

	//get employee id

	public int returnid(){



		try{
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
			} catch (Exception e) {

				e.printStackTrace();
			}
			PreparedStatement preparedStatement = connection.prepareStatement("select EMPLOYEE_ID as userid from employee  where NAME = ? and PASSWORD = ?");

			preparedStatement.setString(1,returnuser);
			preparedStatement.setString(2,returnpass);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			loginid = rs.getInt("userid") ;

			rs.close();
		}

		catch (SQLException e) {

			System.out.println(e);
		}

		return loginid;
	}





}
