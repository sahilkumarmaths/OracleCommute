/*
 * This is the utility class containing all the standard random helper functions
 */
package oraclecommute;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

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
		      "     i_time_departure => ?,                                      \n"   +
		      "     i_is_driver => ?,                                      \n"   +
		      "END; ";
			
	
	public void createEmployee(Emp employee)
	{
		
	}
	
	public Connection getConnection()
	{
		
		try {
			 
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@slc06owh:1523:beedb", "commute",
					"Welcome1");
 
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
	
	public 
	
	
	public static void main(String[] argv) {
		
	
		Util util = new Util();
		util.getConnection();
		
		
	}
	
    
   
}
