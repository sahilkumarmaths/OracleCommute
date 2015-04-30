package oraclecommute;

import java.io.IOException;

import java.net.MalformedURLException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import oraclecommute.Point;
import oraclecommute.Util;

import org.json.JSONException;

public class Group 
{
    private Double g_id;
    private String path;
    private String start_time;
    private Double driver_id;
    private Double size;

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

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
    
    
    
	private Double proximityRadius = 2.0;	
	

	public Double getProximityRadius() {
		return proximityRadius;
	}

	public void setProximityRadius(Double proximityRadius) {
		this.proximityRadius = proximityRadius;
	}
	
        //this function assigns a group to this employee
        
	public void assignEmployee(Employee emp) throws MalformedURLException, IOException, JSONException {
            DbUtil dbUtl = new DbUtil();
            Path objPath = new Path();
            Util utl  = new Util();
	    Direction dir = new Direction();
            boolean isDriver = emp.isIs_driver();
            List<Group> grps = null;
            
            if(isDriver)
            {
                // emp == driver...Find all singletons and create new group
                List<Employee> empList = dbUtl.getAllEmpNotAssigned();
                List<Employee> intersectingRiders = new LinkedList<Employee>();
                for(Employee rider : empList)
                {
                    if( intersectingRiders.size() > 3)
                    {
                        break;
                    }
                    
                    if(isIntersect(emp, new Point(rider.getCoordx(), rider.getCoordy())))
                    {
                        intersectingRiders.add(emp);
                    }
                }
                if(intersectingRiders.size() != 0)
                {
                    // we can form a group happy
                    //We have to set the start time??
                    
                    //create a new group
                    //add riders and driver to the group (insertgroup)
                    //call findpath
                    //write the path
                    
                    Group grp = new Group();
                    grp.setDriver_id(emp.getId());
                    grp.setSize(intersectingRiders.size() +  1.0);
                    grp.setStart_time(""); //set the start time...?
                    Double new_g_Id = dbUtl.insertGroupAttr(grp);
                    
                    for(Employee rider: intersectingRiders)
                    {
                        dbUtl.insertGroup(new_g_Id, rider.getId());
                        rider.setIs_assigned_grp(true);
                        dbUtl.updateEmployee(rider);
                    }
                    
                    dbUtl.insertGroup(new_g_Id, emp.getId());
                    
                    try
                    {
                        //table update
                        //findPath ...should also update the driver
                        dbUtl.writePath(new_g_Id.intValue(), objPath.findPath(new Integer(new_g_Id.intValue())));
                        // Update more tables
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    
                    return;
                }
                
                // This as a driver for existing groups
                grps = dbUtl.getVacantGroups();
                boolean intersect = false;
                Group candidateGroup = null;
                for(Group grp : grps)
                {
                    ArrayList<Point> grpPath = utl.pathStringToList(grp.getPath());
                    
                    if (isIntersect(emp ,grpPath.get(0)))
                    {
                        candidateGroup = grp;
                        break;
                    }
                }
                
                if(candidateGroup != null)
                {
                 
                    
                 // update the group
                        // make this emp as the new driver...??
                        // update the size
                        // Update all the tables
                    
                    dbUtl.insertGroup(candidateGroup.getG_id(), emp.getId());
                    candidateGroup.setSize(candidateGroup.getSize() + 1);
                    candidateGroup.setDriver_id(emp.getId());
                    dbUtl.updateGroup(candidateGroup);
                    emp.setIs_assigned_grp(true);
                    dbUtl.updateEmployee(emp); //write this code
                    
                    try
                    {
                        //table update
                        dbUtl.writePath(candidateGroup.getG_id().intValue(), objPath.findPath(new Integer(candidateGroup.getG_id().intValue())));
                     
                        
                        // Update more tables
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    
                
                    return;
                }
                
                //create empty group for the driver.
                
                
                
                 //create a group for the drive
                 //for each singleton empo try 
                 
                 
                // PLSQL ArrayList<Group> which don't have a driver
                // Assign this singletons to this drivers path
                // if yes . remove the old singletons
                // make a new group 
                
                //else if not person found
                // now try to asssign this in the existing group 
                    // this as a rider
                //this as a driver circle
                    
            }
            // emp as a rider
            Point home = new Point(emp.getCoordx(), emp.getCoordy());
            if(grps == null)
            {
                grps = dbUtl.getVacantGroups();
            }
            
            ArrayList<ArrayList<Point>> paths = new ArrayList<ArrayList<Point>>();
            ArrayList<Integer> groupIds = new ArrayList<Integer>();
            ArrayList<Integer> candidateIds = new ArrayList<Integer>();
            for(Group gp : grps)
            {
                ArrayList<Point> path = utl.pathStringToList(gp.getPath());
                paths.add(path);

                groupIds.add(gp.getG_id().intValue());
            }
            
          
            for(int i = 0; i < paths.size(); i++)
            {
                ArrayList<Point> path = new ArrayList<Point>();
                for(int j=1; j<path.size(); j++)
                {

                    Point pt1 = path.get(j-1);
                    Point pt2 = path.get(j);	
                    if(Util.intersectCircle(pt1, pt2, home, proximityRadius) && Util.checkDiversion(path.get(path.size()-1), path.get(0),home) ){
                            candidateIds.add(groupIds.get(i));
                            break;
                    }
                }			
            }
            
            Integer candidateGroupId = Util.getBestCandidate(paths, groupIds, candidateIds, home);
            
            if(candidateGroupId != -1)
            {
                try
                {
                    //table update
                    //u
                    dbUtl.writePath(candidateGroupId, objPath.findPath(candidateGroupId));
                    emp.setIs_assigned_grp(true);
                    dbUtl.insertGroup(candidateGroupId.doubleValue(), emp.getId());
                    dbUtl.updateEmployee(emp);
                    
                  
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            } else
            {
                //form new group if driver can't get riders and any driver for himself
                if(emp.isIs_driver()) {
                    
                    Group newStandaloneGrp = new Group();
                    
                    List<Point> driverPath = dir.getPath(emp.getCoordx().toString()+"," + emp.getCoordy().toString(), "500 Oracle Pkwy, Redwood Shores, 94065");
                    String path = utl.pathListToString((ArrayList) driverPath);
                    newStandaloneGrp.setDriver_id(emp.getId());
                    newStandaloneGrp.setSize(1.0);
                    newStandaloneGrp.setStart_time(emp.getHome_departure().toString()); //set the start time...?
                    Double new_g_Id = dbUtl.insertGroupAttr(newStandaloneGrp);
                    emp.setIs_assigned_grp(true);
                    dbUtl.updateEmployee(emp);
                    dbUtl.insertGroup(new_g_Id, emp.getId());
                    
                }
                
            }
	}
	
        // Intersections 
        public boolean isIntersect(Employee driver, Point rider)
        {
            Point home = new Point(rider.getLat(), rider.getLng());
	
            DbUtil utl = new DbUtil();
            Direction dir = new Direction();
            List<Group> gps = utl.getAllGroup();
            
            List<Point> driverPath;
            boolean intersect = false;
            try
            {
                driverPath = dir.getPath(driver.getCoordx().toString()+","
                    + driver.getCoordy().toString(), "500 Oracle Pkwy, Redwood Shores, 94065");
                
                for (int i=0; i < driverPath.size() -1 ; i++)
                {
                    intersect = Util.intersectCircle(driverPath.get(i), driverPath.get(i+1), home, proximityRadius);
                    if(intersect)
                    {
                        return intersect;
                    }
                }
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
        
        
                
                /*for(Group gp : gps)
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
                            if( && Util.checkDiversion(path.get(path.size()-1), path.get(0),home) ){
                                    candidateIds.add(groupIds.get(i));
                                    break;
                            }
                    }			
            }
            Integer candidateGroupId = Util.getBestCandidate(paths, groupIds, candidateIds, home);
            Path.findPath(candidateGroupId);
                
                */
        }
	
	
}
