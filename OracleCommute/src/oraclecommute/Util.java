/*
 * This is the utility class containing all the standard random helper functions
 */
package oraclecommute;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;

public class Util {

	Connection conn = null;
	
	static Double d2r = 0.0174532925199433;
	
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
	

	public static Double distance(double lat1, double long1, double lat2, double long2)
	   {
		double dlong = (long2 - long1) * d2r;
	    double dlat = (lat2 - lat1) * d2r;
	    double a = Math.pow(Math.sin(dlat/2.0), 2) + Math.cos(lat1*d2r) * Math.cos(lat2*d2r) * Math.pow(Math.sin(dlong/2.0), 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double d = 3956 * c; 

	    return d;
	   }
	   
	   public static Double distance(Point a, Point b)
	   {
	       return Util.distance(a.getLat(), a.getLng(), b.getLat(), b.getLng());
	   }
	   
	   
	   
	   public static Double roadDistance(Point a, Point b) throws MalformedURLException, IOException, JSONException{
		  
		   
		   String start = a.getLat()+","+a.getLng();
		   String end = b.getLat()+","+b.getLng();
		   Direction obj = new Direction();
		   return obj.getRoadDistance(start, end);
		   
		  
	   }
	    
	   public static Boolean intersectCircle(Point rayA, Point rayB, Point C, double radius)
	   {
	        double x1 = rayA.getLat();
	        double y1 = rayA.getLng();

	        double x2 = rayB.getLat();
	        double y2 = rayB.getLng();

	        double x3 = C.getLat();
	        double y3 = C.getLng();

	        double x4;
	        double y4;
	        double k;
	        
	        double ab = Util.distance(x1, y1, x2, y2);
	                
	        if ( ab == 0)
	        {
	            return false;
	        }
	        k = ((y2-y1) * (x3-x1) - (x2-x1) * (y3-y1)) /(ab*ab) ;
	        x4 = x3 - k * (y2-y1);
	        y4 = y3 + k * (x2-x1);
	        
	        double cd = Util.distance(x3,y3,x4,y4);
	        
	        if (cd > radius)
	        {
	            return false;
	        }
	        double ad = Util.distance(x1,y1,x4,y4);
	        double db = Util.distance(x2,y2,x4,y4);
	        
	        if(ab != ad+db)
	        {
	            return false;
	        }
	       return true; 
	   }
	   
	   public static Integer getBestCandidate(ArrayList<ArrayList<Point>> paths, ArrayList<Integer> groupIds, ArrayList<Integer> candidateIds){
			Integer bestCandidateId = 0;
			
			return bestCandidateId;
		}
		
		public static Boolean checkDiversion(Point start, Point end, Point home){
			Boolean isOkay = true;
			
			
			
			
			return isOkay;
		}
   
}
