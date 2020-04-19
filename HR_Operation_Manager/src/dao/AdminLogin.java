package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminLogin {

	private String Adminname = "shrikant";
	private String Adminpass = "12345";
	
	


	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	public boolean AdminLogin() throws IOException {

		System.out.println("Enter Admin User Name");
		String Aname=br.readLine();
		System.out.println("Enter Admin Password");
		String Apass=br.readLine();
		if(Aname.equals(Adminname) && Apass.equals(Adminpass))
			return true;
		else 
			return false;

	}


}
