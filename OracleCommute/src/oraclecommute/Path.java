package oraclecommute;

import java.util.ArrayList;

public class Path {
	
	public ArrayList<Point> findPath(Integer groupId){
		//read locations from DB
		ArrayList<Point> locations = new ArrayList<Point>();
		ArrayList<Point> path = new ArrayList<Point>();
		
		for(int i=0; i<locations.size();i++){
			Point cur = locations.get(i);
			Point nxt;
			for(int j=i+1;j<locations.size();j++){
				
			}
		}
		
		
		
		return path;
	}

}
