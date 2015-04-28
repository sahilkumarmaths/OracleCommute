package oraclecommute;

import java.util.ArrayList;
import java.util.List;

import oraclecommute.Point;
import oraclecommute.Util;

public class Group {
	
	private Double proximityRadius = 2.0;	
	

	public Double getProximityRadius() {
		return proximityRadius;
	}

	public void setProximityRadius(Double proximityRadius) {
		this.proximityRadius = proximityRadius;
	}
	
	public void assignEmployee(Integer empId){
		//populate from DB
		Point home = new Point();
		
		ArrayList<ArrayList<Point>> paths = new ArrayList<ArrayList<Point>>();
		ArrayList<Integer> groupIds = new ArrayList<Integer>();
		ArrayList<Integer> candidateIds = new ArrayList<Integer>();
		
		for(int i=0; i< paths.size(); i++){
			ArrayList<Point> path = new ArrayList<Point>();
			for(int j=1; j<path.size(); j++){
				
				Point pt1 = path.get(j-1);
				Point pt2 = path.get(j);	
				if(Util.intersectCircle(pt1, pt2, home, proximityRadius) && Util.checkDiversion(path.get(path.size()-1), path.get(0),home) ){
					candidateIds.add(groupIds.get(i));
					break;
				}
			}			
		}
		Integer candidateGroupId = Util.getBestCandidate(paths, groupIds, candidateIds);
		
		//Update DB
	}
	
	
	public void removeEmployee(){
		
	}
}
