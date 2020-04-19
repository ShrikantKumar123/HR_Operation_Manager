package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Application;

import utility.ConnectionManager;

public  class ApplicationDaoImpl implements ApplicationDaoInterface{



	public int Addapplication(Application application) {

		String INSERT_USERS_SQL = "INSERT INTO APPLICATION(APPLICATION_ID,EMAIL,OPPORTUNITY_ID,APPLICATION_DATE,RESUME_LOCATION,STATUS,OFFER_ID,INTERVIEWER,INTERVIEW_DATE,INTERVIEW_TIME,RESULT,REASON)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

		String count="select count(*) AS rowcount from Application";
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
			int iid = rs.getInt("rowcount") ;
			rs.close();



			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setInt(1,++iid);
			preparedStatement.setString(2,application.getEmail());
			preparedStatement.setString(3,application.getOpportunity_id());
			preparedStatement.setDouble(4,application.getApplication_date());
			preparedStatement.setString(5,application.getResume_location());
			preparedStatement.setString(6,application.getStatus());
			preparedStatement.setString(7,application.getOffer_id());
			preparedStatement.setString(8,application.getInterviwer());
			preparedStatement.setDouble(9,application.getInterview_date());
			preparedStatement.setTime(10,application.getInterview_time());
			preparedStatement.setString(11,application.getResult());
			preparedStatement.setString(12,application.getReason());




			System.out.println("YOUR APPLICATION IS SUBMITED WITH APPLICATION ID "+iid+"  sucessfully\n\n");

			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return result;
	}

	public int viewstatus(int userid) throws Exception
	{
		Application application=new Application();

		String Fetch_results = "SELECT APPLICATION_ID,EMAIL,OPPORTUNITY_ID,STATUS FROM APPLICATION where OFFER_ID="+userid;

		Connection con=ConnectionManager.getConnection();
		ResultSet rs=con.createStatement().executeQuery(Fetch_results);

		//printing headings

		String heading1="APPLICATION_ID";
		String heading2="EMAIL";
		String heading3="OPPORTUNITY_ID";
		String heading4="STATUS";


		System.out.printf("view status of application ",heading1,heading2,heading3,heading4);

		System.out.println("__________________________________________________________________________________");
		while(rs.next())
		{
			


			int id=rs.getInt("APPLICATION_ID");
			String summary=rs.getString("EMAIL");	
			int depid=rs.getInt("OPPORTUNITY_ID");	
			String status=rs.getString("STATUS");

			//geting depname
			String getdepname="select NAME from department where ID="+depid;
			ResultSet rs1=con.createStatement().executeQuery(getdepname);
			rs1.next();
			String depname=rs1.getString("NAME");



			System.out.printf("  Application by user ",id,summary,depname,status);
			rs1.close();

		}
		System.out.println("__________________________________________________________________________________");
		System.out.println("\n\n\n");

		return 1;

	}




}
