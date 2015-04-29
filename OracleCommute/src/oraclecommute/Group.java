package oraclecommute;

import java.util.ArrayList;
import java.util.List;

import oraclecommute.Point;
import oraclecommute.Util;

public class Group 
{
    private Double g_id;
    private String path; 

    public Double getG_id() {
        return g_id;
    }

    public void setG_id(Double g_id) {
        this.g_id = g_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public Double getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Double driver_id) {
        this.driver_id = driver_id;
    }
    private String start_time;
    private Double driver_id;
    
    
	private Double proximityRadius = 2.0;	
	

	public Double getProximityRadius() {
		return proximityRadius;
	}

	public void setProximityRadius(Double proximityRadius) {
		this.proximityRadius = proximityRadius;
	}
	
	public void assignEmployee(Employee emp){
		Point home = new Point(emp.getCoordx(), emp.getCoordy());
		
		ArrayList<ArrayList<Point>> paths = new ArrayList<ArrayList<Point>>();
		ArrayList<Integer> groupIds = new ArrayList<Integer>();
		ArrayList<Integer> candidateIds = new ArrayList<Integer>();
		
                DbUtil utl = new DbUtil();
                List<Group> gps = utl.getAllGroup();
                
                for(Group gp : gps)
                {
                    ArrayList<Point> path = pathStringToList(gp.getPath());
                    paths.add(path);
                    
                    groupIds.add(gp.getG_id().intValue());
                }
                
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
                Integer candidateGroupId = Util.getBestCandidate(paths, groupIds, candidateIds, home);
                Path.findPath(candidateGroupId);
                
                
                
		
		//Update DB
	}
	
	
	public void removeEmployee(){
		
	}
}
