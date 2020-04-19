package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import utility.ConnectionManager;

public class ApplicationOperation {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	int applicid=0;
	
	int depempid=0;

	//view application and update application status to working
	public int viewapplication(String type) throws Exception
	{

		int depid=0;
		int empoperation=-10;
		String query = null;
		if(type=="pending")
		{
			query="select APPLICATION_ID,EMAIL,OPPORTUNITY_ID,APPLICATION_DATE,RESUME_LOCATION,STATUS,OFFER_ID,INTERVIEWER,INTERVIEW_DATE,INTERVIEW_TIME,RESULT,REASON from APPLICATION where status='"+type+"'";
		}
		else if(type=="working")
		{
			query="select APPLICATION_ID,EMAIL,OPPORTUNITY_ID,APPLICATION_DATE,RESUME_LOCATION,STATUS,OFFER_ID,INTERVIEWER,INTERVIEW_DATE,INTERVIEW_TIME,RESULT,REASON from APPLICATION where status='working'";

		}
		else if(type=="COMPLETED")
		{
			query="select APPLICATION_ID,EMAIL,OPPORTUNITY_ID,APPLICATION_DATE,RESUME_LOCATION,STATUS,OFFER_ID,INTERVIEWER,INTERVIEW_DATE,INTERVIEW_TIME,RESULT,REASON from APPLICATION where status='COMPLETED'";


		}
		else if(type=="all")
		{
			query="select APPLICATION_ID,EMAIL,OPPORTUNITY_ID,APPLICATION_DATE,RESUME_LOCATION,STATUS,OFFER_ID,INTERVIEWER,INTERVIEW_DATE,INTERVIEW_TIME,RESULT,REASON from APPLICATION";


		}
		else
		{

			//employee specific application;
			empoperation=1;
			int empId=Integer.parseInt(type);
			String getdepid="select DEPT_ID FROM EMPLOYEE WHERE EMPLOYEE_ID="+empId;
			Connection con=ConnectionManager.getConnection();
			ResultSet rs=con.createStatement().executeQuery(getdepid);
			rs.next();
			depid=rs.getInt("DEPT_ID");

			query="SELECT APPLICATION_ID,EMAIL,OPPORTUNITY_ID,APPLICATION_DATE,RESUME_LOCATION,STATUS,OFFER_ID,INTERVIEWER,INTERVIEW_DATE,INTERVIEW_TIME,RESULT,REASON FROM APPLICATION WHERE OFFER_ID="+depid+" and STATUS='pending'";
		}

		Connection con=ConnectionManager.getConnection();
		ResultSet rs=con.createStatement().executeQuery(query);
		if(rs.next()==false)
		{
			System.out.println("________________________________________________________");
			System.out.println("\t\tNO APPLICATION TO WORK THANK YOU");
			System.out.println("________________________________________________________");
		}
		else {
			String heading1="APPLICATION_ID";
			String heading2="EMAIL";
			String heading3="OPPORTUNITY_ID";
			String heading4="APPLICATION_DATE";
			String heading5="RESUME_LOCATION";
			String heading6="STATUS";
			String heading7="OFFER_ID";
			String heading8="INTERVIEWER";
			String heading9="INTERVIEW_DATE";
			String heading10="INTERVIEW_TIME";
			String heading11="RESULT";
			String heading12="REASON";

			System.out.printf("View Application ",heading1,heading2,heading3,heading4,heading5,heading6,heading7,heading8,heading9,heading10,heading11,heading12);
			System.out.println("_________________________________________________________________________________________________________________________________________________________________________");

			int did=0;
			while(rs.next())
			{

				int id=rs.getInt("APPLICATION_ID");
				String email=rs.getString("EMAIL");
				String oprid=rs.getString("OPPORTUNITY_ID");
				int appdate=rs.getInt("APPLICATION_DATE");
				did=rs.getInt("RESUME_LOCATION");
				String staus=rs.getString("STATUS");
				String offerid=rs.getString("OFFER_ID");
				
				String interv=rs.getString("INTERVIEWER");
				int interd=rs.getInt("INTERVIEW_DATE");
				double intert=rs.getInt("INTERVIEW_TIME");
				String res=rs.getString("RESULT");
				String resio=rs.getString("REASON");


				System.out.printf("   View Application   ",id,email,oprid,appdate,did,staus,offerid,interv,interd,intert,res,resio);


			}
			System.out.println("_________________________________________________________________________________________________________________________________________________________________________");

			if(empoperation==1) {

				do {
					System.out.println("\n\nENTER Application ID DO YOU WANT WORK |OR|  ENTER |0| GO BACK TO PREVIOUS MENU ");
					applicid=Integer.parseInt(br.readLine());
					if(applicid!=0)
					{
						updateapplicationstatus(did,applicid);
					}


				}while(applicid!=0);	
			}
		}	return depid;

	}

	public int updateapplicationstatus(int depid, int appid2) throws Exception//

	{

		String query="UPDATE  Application SET STATUS='working' WHERE OFFER_ID="+depid+"and APPLICATION_ID="+appid2;

		Connection con=ConnectionManager.getConnection();
		PreparedStatement ps=con.prepareStatement(query);
		ps.executeUpdate();
		con.close();
		System.out.println("_____________________________________________________________________");
		System.out.println("\nAPPLICATION STATUS UPDATED TO WORKING THANK YOU\n");
		System.out.println("_____________________________________________________________________");
		return 0;

	}
	//resolve application set status complete and send sms
	public int resolveapplication(int empid) throws Exception
	{



		String getdepid="select DEPT_ID FROM EMPLOYEE WHERE EMPLOYEE_ID="+empid;
		Connection con=ConnectionManager.getConnection();
		ResultSet rs=con.createStatement().executeQuery(getdepid);
		rs.next();
		int depid=rs.getInt("DEPT_ID");

		rs.close();

		String	Fetch_results="SELECT APPLICATION_ID,EMAIL,OPPORTUNITY_ID,APPLICATION_DATE,RESUME_LOCATION,STATUS,OFFER_ID,INTERVIEWER,INTERVIEW_DATE,INTERVIEW_TIME,RESULT,REASON FROM APPLICATION WHERE OFFER_ID="+depid+"and status='working'";

		con=ConnectionManager.getConnection();
		rs=con.createStatement().executeQuery(Fetch_results);

		if(rs.next()==false)
		{
			System.out.println("NO APPLICATION TO RESOLVE THANK YOU\n\n");
		}
		else {

			String heading1="APPLICATION_ID";
			String heading2="EMAIL";
			String heading3="OPPORTUNITY_ID";
			String heading4="APPLICATION_DATE";
			String heading5="RESUME_LOCATION";
			String heading6="STATUS";
			String heading7="OFFER_ID";
			String heading8="INTERVIEWER";
			String heading9="INTERVIEW_DATE";
			String heading10="INTERVIEW_TIME";
			String heading11="RESULT";
			String heading12="REASON";

			System.out.printf("   Solve Application   ",heading1,heading2,heading3,heading4,heading5,heading6,heading7,heading8,heading9,heading10,heading11,heading12);
			System.out.println("_________________________________________________________________________________________________________________________________________________________________________");
			while(rs.next())
			{
				//printing Application


				int id=rs.getInt("APPLICATION_ID");
				String email=rs.getString("EMAIL");
				String oprid=rs.getString("OPPORTUNITY_ID");
				int appdate=rs.getInt("APPLICATION_DATE");
				//did=rs.getInt("RESUME_LOCATION");
				String staus=rs.getString("STATUS");
				String offerid=rs.getString("OFFER_ID");
				
				String interv=rs.getString("INTERVIEWER");
				int interd=rs.getInt("INTERVIEW_DATE");
				double intert=rs.getInt("INTERVIEW_TIME");
				String res=rs.getString("RESULT");
				String resio=rs.getString("REASON");

				System.out.printf(" Printing Application ",id,email,oprid,appdate,staus,offerid,interv,interd,intert,res,resio);


			}
			System.out.println("_________________________________________________________________________________________________________________________________________________________________________");





			do {
				System.out.println("ENTER APPLICATION ID DO YOU WANK TO RESOLVE |OR| ENTER |0| TO GO BACK TO PREVIOUS MENU");
				applicid=Integer.parseInt(br.readLine());
				if(applicid!=0)
				{
					resolveapplicationstatus( depid,applicid);
				}



			}while(applicid!=0);

		}

		return 1;


	}
	//update resolve status of the Application
	public int resolveapplicationstatus(int depid, int applicid2) throws Exception//

	{
		System.out.println("Please enter Application descrption");
		String rsum=br.readLine();

		String getnumber="SELECT USER1.P_MONUMBER FROM USER1 LEFT JOIN APPLICATION ON APPLICATION.OFFER_ID= user1.ID";
		Connection con=ConnectionManager.getConnection();
		ResultSet rs=con.createStatement().executeQuery(getnumber);
		rs.next();
		String num=rs.getString("P_MONUMBER");
		sendsms(num,rsum);




		String query="UPDATE  APPLICATION SET STATUS='COMPLETED' WHERE OFFER_ID="+depid+"and APPLICATION_ID="+applicid2;

		con=ConnectionManager.getConnection();
		PreparedStatement ps=con.prepareStatement(query);
		ps.executeUpdate();
		con.close();
		System.out.println("____________________________________________________________________________________");
		System.out.println("\n\n APPLICATION RESOLVED AND USER GET MESSAGE --> "+rsum+"\n");
		System.out.println("____________________________________________________________________________________");
		return 0;

	}

	//Sending sms using api
	public String sendsms(String num, String rsum)
	{
		try {
			// Construct data
			String apiKey = "apikey=" + "Db1aHtwSQyA-QaxBKEJr9BNqDtqgJCEDWtH972leKF";
			String message = "&message=" +rsum;
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + num;

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);

			}
			rd.close();

			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}



}
