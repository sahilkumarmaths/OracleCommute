/*
 * This is the utility class containing all the standard random helper functions
 */
package oraclecommute;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.sql.Types;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.OracleResultSet;

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
			
	String RETRIEVE_EMPLOYEES = "BEGIN  \n" +
				"  commute_employee.retrieveEmployees (                 \n"   +
				"  		o_employees => ?); \n" +
				 "END; ";
	
	String GET_GROUP_EMPLOYEE_LOCATIONS = "BEGIN                                                         \n"   +
		      "  commute_employee.get_grp_empl_locations (                 \n"   +
		      "     o_locations => ?,                                                \n"   +
		      "     i_group_id => ?);                                     \n"   +
		      "END; ";
	
	String WRITE_PATH = "BEGIN                                                         \n"   +
		      "  commute_employee.get_grp_empl_locations (                 \n"   +
		      "     i_group_id => ?,                                                \n"   +
		      "     i_path => ?);                                     \n"   +
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
		conn.commit();
		conn.close(); 
	 }
	 catch(Exception exp)
	 {
		 exp.printStackTrace();
	 }
	 finally
	 {
		 
		 
	 }
		
		
		
	}
	
	
	public ArrayList<Employee> retreiveEmployees()
	{
		
		 ArrayList<Employee> empList = new ArrayList<Employee>();
		 try {
			 
			
			 conn = this.getConnection();
			 OracleCallableStatement cstmt = (OracleCallableStatement) conn.prepareCall(RETRIEVE_EMPLOYEES);
			 cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			 cstmt.execute();
			 OracleResultSet rs = (OracleResultSet)cstmt.getCursor(1);
			 while (rs.next()) {
				 	
				 Employee emp = new Employee();
				 emp.setUsername(rs.getString("username"));
				 emp.setPassword(rs.getString("passwd"));
				 emp.setCoordx(rs.getDouble("coordx"));
				 emp.setCoordy(rs.getDouble("coordy"));
				 emp.setEmail(rs.getString("email"));
				 emp.setAddress(rs.getString("address"));
				 emp.setHome_departure(rs.getTimestamp("home_departure"));
				 emp.setOffice_departure(rs.getTimestamp("office_departure"));
				 emp.setIs_driver("Y".equals(rs.getString("is_driver"))? true:false);
				 emp.setPhone(rs.getDouble("phno"));
				 emp.setName(rs.getString("name"));
				 empList.add(emp);

		        }
			 
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return empList;
		
	}
	
	public void updateEmployee(String username, HashMap<String,String> attributes)
	{
		
	}
	
	public ArrayList<Point> getGroupEmployeeLocations(int groupId){
		ArrayList<Point> locations = new ArrayList<Point>();
		
		try
		 {     
			 conn = this.getConnection();
			 OracleCallableStatement cstmt = (OracleCallableStatement) conn.prepareCall(CREATE_EMPLOYEE);
				
				
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.setString(2, ""+groupId);			
			cstmt.execute();
			OracleResultSet rs = (OracleResultSet) cstmt.getCursor(1);
			 while (rs.next()) {
				 	
				 String lat_str = rs.getString("coordx");
				 Double lat=null;
				 if(lat_str!=null){
					 lat = Double.parseDouble(lat_str);
				 }
				 String lng_str = rs.getString("coordy");
				 Double lng=null;
				 if(lng_str!=null){
					 lng = Double.parseDouble(lng_str);
				 }
				 if(lat!=null && lng!=null){
					 Point pt = new Point(lat,lng);
					 locations.add(pt);
				 }
				 
		    }
			conn.close();
			
			
			
		 }
		 catch(Exception exp)
		 {
			 exp.printStackTrace();
		 }
		 finally
		 {
			 
			 
		 }
		
		return locations;
	}
	
	public void writePath(int groupId, String path){
		try
		 {     
			 conn = this.getConnection();
			 OracleCallableStatement cstmt = (OracleCallableStatement) conn.prepareCall(WRITE_PATH);
				
				
			cstmt.setString(1, ""+groupId);
			cstmt.setString(2, path);			
			cstmt.execute();
			conn.commit();
			conn.close();
			
			
			
		 }
		 catch(Exception exp)
		 {
			 exp.printStackTrace();
		 }
		 finally
		 {
			 
			 
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
		
		
	/*	SimpleDateFormat dateFormat = new SimpleDateFormat(
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
        emp.setIs_driver(true); */
		


        DbUtil db = new DbUtil();
        //db.createEmployee(emp);
        db.retreiveEmployees();
	}
	
	
}
