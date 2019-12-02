package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.DBServer;


public class mysqlConnection {

	private static Connection con;
	
	/**
	 * this method defines the jdbc driver
	 * so that we can connect to the database
	 */
	private static void defineDriver()
	{
		try 
		{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("Driver definition succeed");
        } catch (Exception ex) {
        	/* handle the error*/
        	 System.out.println("Driver definition failed");
        	 }
        
	}
	
	/**
	 * this method establishes a connection
	 * to the database
	 */
	private static void establishDBConnection()
	{
		 try 
	        {
	            con = DriverManager.getConnection("jdbc:mysql://remotemysql.com/ZZUehgwcK6?serverTimezone=IST","ZZUehgwcK6","hfwrGUeVv7");
	            System.out.println("SQL connection succeed! : " + con);
	           
	     	} catch (SQLException ex) {/* handle any errors*/
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	     	    }
	}
	
	public static void connectToDB() {
		defineDriver();
		establishDBConnection();
	}
	
	public static void saveUserToDB(String uid, String pwd){
		Statement stmt;
		String query = "INSERT INTO sample_login (uid, upassword) VALUES (\'" + uid + "\', \'" + pwd + "\')";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
	 		
		} catch (SQLException e) {	e.printStackTrace();}	
	}
	
	// It will not stay boolean, but will change to AcademicUser or another entity.
	public static Boolean getUserFromDB(String uid, String pwd){
		System.out.println(con);
		if (con == null) {
			connectToDB();
		}
		Statement stmt;
		ResultSet result;
		// SELECT * WILL CHANGE TO SELECT SPECIFIC RELEVANT FIELDS AND NOT PWD - IN ORDER TO FILL AcademicUser Instance
		String query = "SELECT * FROM sample_login WHERE uid = \'" + uid + "\' AND upassword = \'" + pwd + "\'";
		System.out.println(query);
		try {
			stmt = con.createStatement();
			stmt.executeQuery(query);
			result = stmt.getResultSet();
			if (result.next() == false) { 
				System.out.println("ResultSet in Empty!");
			}
			else { 
				String data_uid = result.getString("uid");
				String data_upassword = result.getString("upassword");
				if (uid.equals(data_uid) && pwd.equals(data_upassword)) {
					System.out.println("Logged-in successfully!");
					//return new AcademicUser(uid, student_number, faculty_number ... etc);
					return true;
				}
				else
					System.out.println("ID OR PASSWORD ARE INCORRECT!");
			}
		} 
		catch (SQLException e) {	
			e.printStackTrace();
		}
		//return null;
		return false;
	}
	
	/*public static void printCourses()
	{
		Statement stmt;
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM courses;");
	 		while(rs.next())
	 		{
				 // Print out the values
				 System.out.println(rs.getString(1)+"  " +rs.getString(2));
			} 
			rs.close();
			//stmt.executeUpdate("UPDATE course SET semestr=\"W08\" WHERE num=61309");
		} catch (SQLException e) {e.printStackTrace();}
	}*/

	
	/*public static void createTableCourses(){
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("create table courses(num int, name VARCHAR(40), semestr VARCHAR(10));");
			stmt.executeUpdate("load data local infile \"courses.txt\" into table courses");
	 		
		} catch (SQLException e) {	e.printStackTrace();}
		 		
	}*/
	
	
	
}


