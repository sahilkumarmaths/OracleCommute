/*
 * This is the utility class containing all the standard random helper functions
 */
package oraclecommute;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;



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
		      "     i_time_departure => ?,                                      \n"   +
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
		cstmt.setDate(9, emp.getTime_departure());
		cstmt.setString(10, emp.isIs_driver()? "Y":"N");
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
	
}
