/*
 * This is the utility class containing all the standard random helper functions
 */
package oraclecommute;

import java.util.ArrayList;

/**
 *
 * @author sahkumar
 */
public class Util {
	public static Double distance(double x1, double y1, double x2, double y2)
	   {
	       return Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
	   }
	   
	   public static Double distance(Point a, Point b)
	   {
	       return Util.distance(a.getLat(), a.getLng(), b.getLat(), b.getLng());
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
