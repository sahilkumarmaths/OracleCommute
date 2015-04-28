/*
 * This is the utility class containing all the standard random helper functions
 */
package oraclecommute;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;



public class DbUtil {

	Connection conn = null;
	
	String CREATE_EMPLOYEE = "BEGIN                                                         \n"   +
		      "  commute_employee.createEmployee (                 \n"   +
		      "     i_username => ?,                                                \n"   +
		      "     i_password => ?,                                  \n"   +        
		      "     i_coordx => ?,                                     \n"   +
		      "     i_coordy => ?,                                            \n"   +
		      "     i_name => ?,                                      \n"   +
		      "     i_phone => ?,                                      \n"   +
		      "     i_addr => ?,                                      \n"   +
		      "     i_email => ?,                                      \n"   +
		      "     i_home_departure => ?,                                      \n"   +
		      "     i_office_departure => ?,                                      \n"   +
		      "     i_is_driver => ?);                                     \n"   +
		      "END; ";
			
	
	public void createEmployee(Employee emp)
	{
		
	 try
	 {
                 
             
             
		 conn = this.getConnection();
		 CallableStatement cstmt = conn.prepareCall(CREATE_EMPLOYEE);
			
			
		cstmt.setString(1, emp.getUsername());
		cstmt.setString(2, emp.getPassword());
		cstmt.setDouble(3, emp.getCoordx());
		cstmt.setDouble(4, emp.getCoordy());
		cstmt.setString(5, emp.getName());
		cstmt.setDouble(6, emp.getPhone());
		cstmt.setString(7, emp.getAddress());
		cstmt.setString(8, emp.getEmail());
		cstmt.setTimestamp(9, emp.getHome_departure());
        cstmt.setTimestamp(10, emp.getOffice_departure());
		cstmt.setString(11, emp.isIs_driver()? "Y":"N");
		cstmt.executeUpdate();
		 
	 }
	 catch(Exception exp)
	 {
		 exp.printStackTrace();
	 }
		
		
		
	}
	
	public Connection getConnection()
	{
		
		try {
			
			if(conn == null)
			{
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@slc06owh:1523:beedb", "commute",
						"Welcome1");
			}
			
 
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
 
		}
		
		if (conn != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		
		return conn;
		
	}
	
	public static void main(String args[])
	{
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");

		java.util.Date parsedTimeStamp = null;
		try {
			parsedTimeStamp = dateFormat.parse("2014-08-22 15:02:51");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Timestamp timestamp = new Timestamp(parsedTimeStamp.getTime());
		
        
        // create a java calendar instance
        Calendar calendar = Calendar.getInstance();
         
        // get a java date (java.util.Date) from the Calendar instance.
        // this java date will represent the current date, or "now".
        java.util.Date currentDate = calendar.getTime();
         
        // now, create a java.sql.Date from the java.util.Date
        Timestamp home_departure = new Timestamp(currentDate.getTime());
		
        Employee emp = new Employee();
        emp.setUsername("username");
        emp.setPassword("password");
        emp.setAddress("address");
        emp.setCoordx(123.0);
        emp.setCoordy(123.0);

        emp.setEmail("email");
        emp.setName("name");
        emp.setPhone(123.0);
        emp.setHome_departure(timestamp);
        emp.setOffice_departure(timestamp);
        emp.setIs_driver(true);


        DbUtil db = new DbUtil();
        db.createEmployee(emp);
	}
	
	
}
