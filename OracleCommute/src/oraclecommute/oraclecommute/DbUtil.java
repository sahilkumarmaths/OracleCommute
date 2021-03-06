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
import java.util.LinkedList;
import java.util.List;
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
		      "     i_is_driver => ?,                                     \n"   +
                      "     is_grp_assigned => ?);                                     \n"   +
		      "END; ";
			
        
    String UPDATE_EMPLOYEE = "BEGIN                                                         \n"   +
                  "  commute_employee.updateEmployee (                 \n"   +
                  "     i_emp_id => ?,                                                \n"   +
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
                  "     i_is_driver => ?,                                      \n"   +
                  "     i_is_grp_assigned => ?);                                     \n"   +
                  "END; ";
        
       
	String RETRIEVE_EMPLOYEES = "BEGIN  \n" +
				"  commute_employee.retrieveEmployees (                 \n"   +
				"  		o_employees => ?); \n" +
				 "END; ";
        
        String GET_EMPLOYEE = "BEGIN  \n" +
                            "  commute_employee.getEmployee (                 \n"   +
                            "               o_employee => ?,                                      \n"   +
                            "               i_username => ?); \n" +
                             "END; ";
	
	String GET_GROUP_EMPLOYEE_LOCATIONS = "BEGIN                                                         \n"   +
		      "  commute_employee.get_grp_empl_locations (                 \n"   +
		      "     o_locations => ?,                                                \n"   +
		      "     i_group_id => ?);                                     \n"   +
		      "END; ";
	
	String WRITE_PATH = "BEGIN                                                         \n"   +
		      "  commute_employee.write_path (                 \n"   +
		      "     i_group_id => ?,                                                \n"   +
		      "     i_path => ?);                                     \n"   +
		      "END; ";
        
         String GETHOME = "BEGIN                                   \n"   +
		      "     commute_employee.getEmployeeLocation (     \n"   +
		      "     i_id  => ?,                                \n"   +
		      "     o_coordx => ?,                             \n"   +        
		      "     o_coordy => ?                              \n"   +
		      "); END; ";
         
    String GET_DRIVER = "BEGIN                                                         \n"   +
                  "  commute_employee.get_driver (                 \n"   +
                  "     o_driver => ?,                                                \n"   +
                  "     i_group_id => ?);                                     \n"   +
                  "END; ";
	
    String GET_GROUP_ID_AND_DELETE_EMPLOYEE = "BEGIN                                                         \n"   +
            "  commute_employee.get_group_id (                 \n"   +
            "     o_group => ?,                                                \n"   +
            "     i_emp_id => ?);                                     \n"   +
            "END; ";
    
    String GET_GROUP = "BEGIN                                                         \n"   +
            "  commute_employee.getGroup (                 \n"   +
            "     o_group => ?,                                                \n"   +
            "     i_grp_id => ?);                                     \n"   +
            "END; ";
    
	public void createEmployee(Employee emp)
	{	
         Connection conn = null; 
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
                cstmt.setString(12,"N"); //by default false
		cstmt.executeUpdate();
		conn.commit();
                conn.close(); 
                
                //assignEmployee(emp);
                // TODO
	 }
	 catch(Exception exp)
	 {
		 exp.printStackTrace();
	 }
		
	}
        
        
    public Employee getEmployee(String username)
    {
            
            Employee emp = new Employee();
             try {
                     Connection conn = null;  
                     conn = this.getConnection();
                     OracleCallableStatement cstmt = (OracleCallableStatement) conn.prepareCall(GET_EMPLOYEE);
                     cstmt.registerOutParameter(1, OracleTypes.CURSOR);
                     cstmt.setString(2, username);
                     cstmt.execute();
                     OracleResultSet rs = (OracleResultSet)cstmt.getCursor(1);
                     while (rs.next()) {
                                    
                             emp.setId(Double.valueOf(rs.getString("emp_id")));
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
                             emp.setIs_assigned_grp("Y".equals(rs.getString("is_grp_assigned"))? true:false);

                    }
                     
                     
                     
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
             
            return emp;
            
    }
        
        public List<Group> getAllGroup()
        {
            String RETRIEVE_PATHS = "BEGIN                      \n" +
                    "  commute_employee.getGroupPaths (         \n" +
                    "  		o_paths => ?);                  \n" +
                    "END; ";
            List<Group> groups = new LinkedList<Group>();
             Connection conn = this.getConnection();
            try
            {
                OracleCallableStatement cstmt = (OracleCallableStatement) conn.prepareCall(RETRIEVE_PATHS);
                cstmt.registerOutParameter(1, OracleTypes.CURSOR);
                cstmt.execute();
                OracleResultSet rs = (OracleResultSet)cstmt.getCursor(1);
                while (rs.next()) 
                {

                    Group gp = new Group();
                    gp.setDriver_id(rs.getDouble("driver_id"));
                    gp.setG_id(rs.getDouble("g_id"));
                    gp.setPath(rs.getString("path"));
                    gp.setStart_time(rs.getTimestamp("start_time"));
                    gp.setSize(rs.getDouble("size"));
                    groups.add(gp);
               } 
               conn.close(); 
            } catch (SQLException e) 
            {
                e.printStackTrace();
            }
            
            return groups;
        }
	
	public ArrayList<Employee> retreiveEmployees()
	{
		
		 ArrayList<Employee> empList = new ArrayList<Employee>();
		 try {
			 Connection conn = null;  
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
	
	public void updateEmployee(Employee emp)
	{
            
	    Connection conn = null; 
	    try
	    {     
	           conn = this.getConnection();
	            CallableStatement cstmt = conn.prepareCall(UPDATE_EMPLOYEE);
	                   
                  cstmt.setDouble(1, emp.getId());        
	           cstmt.setString(2, emp.getUsername());
	           cstmt.setString(3, emp.getPassword());
	           cstmt.setDouble(4, emp.getCoordx());
	           cstmt.setDouble(5, emp.getCoordy());
	           cstmt.setString(6, emp.getName());
	           cstmt.setDouble(7, emp.getPhone());
	           cstmt.setString(8, emp.getAddress());
	           cstmt.setString(9, emp.getEmail());
	           cstmt.setTimestamp(10, emp.getHome_departure());
	           cstmt.setTimestamp(11, emp.getOffice_departure());
	           cstmt.setString(12, emp.isIs_driver()? "Y":"N");
                
                   
                
                //   cstmt.setString(13, "N");
                   cstmt.setString(13, emp.isIs_assigned_grp()? "Y":"N");
	           cstmt.executeUpdate();
	           conn.commit();
	           conn.close(); 
	           
	           //assignEmployee(emp);
	           // TODO
	    }
	    catch(Exception exp)
	    {
	            exp.printStackTrace();
	    }
            
            
            
            
            
	}
	
        public void updateGroup(Group grp) {
            String UPDATE_GROUP = "BEGIN                                                         \n"   +
                          "  commute_employee.updateGroupAttr (                 \n"   +
                          "     i_group_id => ?,                                                \n"   +
                          "     i_path => ?,                                                \n"   +
                          "     i_start_time => ?,                                                \n"   +
                          "     i_driver_id => ?,                                                \n"   +
                          "     i_size => ?);                                     \n"   +
                          "END; ";
            
            
            try{
             
                 conn = this.getConnection();
                 CallableStatement cstmt = conn.prepareCall(UPDATE_GROUP);
                 cstmt.setDouble(1, grp.getG_id());
                 cstmt.setString(2, grp.getPath());
                 cstmt.setTimestamp(3, grp.getStart_time());
                 cstmt.setDouble(4, grp.getDriver_id());
                 cstmt.setDouble(5,grp.getSize() );
   
                    cstmt.executeUpdate();
            
                    conn.close(); 
  
                //assignEmployee(emp);
                // TODO
            }
            catch(Exception exp)
            {
                 exp.printStackTrace();
            }
            
            
            
            
        }
        
	public ArrayList<Point> getGroupEmployeeLocations(int groupId){
		ArrayList<Point> locations = new ArrayList<Point>();
		
		try
		 {     
			 conn = this.getConnection();
			 OracleCallableStatement cstmt = (OracleCallableStatement) conn.prepareCall(GET_GROUP_EMPLOYEE_LOCATIONS);
				
				
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
            
        public Point getHome(Integer empid)
        {
            Point home = new Point();
            try
            {
                Connection conn = this.getConnection();
                OracleCallableStatement cstmt = (OracleCallableStatement) conn.prepareCall(GETHOME);
                cstmt.setInt(1, empid);
                cstmt.registerOutParameter(2, OracleTypes.NUMBER);
                cstmt.registerOutParameter(3, OracleTypes.NUMBER);
                cstmt.execute();
                home.setLat(cstmt.getDouble(2));
                home.setLng(cstmt.getDouble(3));
            } catch (SQLException e) 
            {
                e.printStackTrace();
            }
            return home;
        }

        
        public void insertGroup(Double g_id, Double emp_id)
        {
             String INSERT_GROUP = "BEGIN                                                         \n"   +
		      "  commute_employee.insertGroup (                 \n"   +
		      "     gr_id => ?,                                                \n"   +
		      "     em_id=> ?);                                  \n"   +        
		      "END; ";
             
            try{
             
                 conn = this.getConnection();
		 CallableStatement cstmt = conn.prepareCall(INSERT_GROUP);
			
                 
                cstmt.setDouble(1, g_id);
                cstmt.setDouble(2, emp_id);
                cstmt.executeUpdate();
                conn.commit();
                conn.close(); 
                
                //assignEmployee(emp);
                // TODO
	 }
	 catch(Exception exp)
	 {
		 exp.printStackTrace();
	 }
             
            
        }
        
    public Double insertGroupAttr(Group grp)
    {
         String INSERT_GROUP_ATTR = "BEGIN                                                         \n"   +
                  "  commute_employee.insertGroupAttr (                 \n"   +
                "     o_g_id => ?,                                                \n"   +
                  "     i_start_time => ?,                                                \n"   +
                  "     i_driver_id => ?,                                                \n"   +
                  "     i_size => ?,                                                \n"   +
                  "     i_path=> ?);                                  \n"   +        
                  "END; ";
         
        try{
         
             conn = this.getConnection();
             CallableStatement cstmt = conn.prepareCall(INSERT_GROUP_ATTR);
            
             cstmt.registerOutParameter(1, OracleTypes.NUMBER);
             cstmt.setTimestamp(2, grp.getStart_time()); 
           
             cstmt.setDouble(3, grp.getDriver_id());
             cstmt.setDouble(4, grp.getSize());
             cstmt.setString(5, grp.getPath());
             
                cstmt.executeUpdate();
                Double id = cstmt.getDouble(1);
                conn.commit(); 
                conn.close(); 
            return  id;
            //assignEmployee(emp);
            // TODO
     }
     catch(Exception exp)
     {
             exp.printStackTrace();
     }
         
        return -1.0; 
    }
        
        
	public Connection getConnection()
	{
                Connection conn = null; 
		
		try {
			
			if(conn == null)
			{
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@192.168.2.34:1521:XE", "commute",
						"Welcome1");
			}
			
 
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		} 
		
		if (conn != null) {
			//System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		
		return conn;
		
	}
	
    public boolean assignGroup(Double gid, Double empId)
    {
        String assignQuery = "BEGIN                                   \n"   +
                  "     commute_employee.assignGroup (     \n"   +
                  "     i_gid  => ?,                                \n"   +
                  "     i_id => ?                             \n"   +        
                  "); END; ";
        try
        {
            Connection conn = this.getConnection();
            OracleCallableStatement cstmt = (OracleCallableStatement) conn.prepareCall(assignQuery);
            cstmt.setDouble(1, gid);
            cstmt.setDouble(2, empId);
            cstmt.execute();
            conn.close();
            return true;
        } catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }   
    }
    
    
    public List<Employee> getAllEmpNotAssigned()
    {
       String getEmpQuery = "BEGIN                                   \n"   +
                  "     commute_employee.getAllEmpNotAssigned (     \n"   +
                  "     o_emp_cur  => ?                                \n" +
                  "); END; ";
       
        List<Employee> getAllEmp = new LinkedList<Employee>();
        try
        {
            Connection conn = this.getConnection();
            OracleCallableStatement cstmt = (OracleCallableStatement) conn.prepareCall(getEmpQuery);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            
            OracleResultSet rs = (OracleResultSet) cstmt.getCursor(1);
            
            while(rs.next())
            {
                Employee emp = new Employee();
                emp.setUsername(rs.getString("username"));
                emp.setId(rs.getDouble("emp_id"));
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
                emp.setIs_assigned_grp("Y".equals(rs.getString("is_grp_assigned")) ? true:false);
                getAllEmp.add(emp);
            }
            conn.close();
            
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }    
        return getAllEmp;
    }
    
    public List<Group> getVacantGroups()
    {
        String queryVacantGroup = "BEGIN                                   \n"   +
                  "     commute_employee.getVacantGroups (     \n"   +
                  "     o_vacant_grp  => ?                                \n" +
                  "); END; ";
       
        List<Group> grps = new LinkedList<Group>();
        try
        {
            Connection conn = this.getConnection();
            OracleCallableStatement cstmt = (OracleCallableStatement) conn.prepareCall(queryVacantGroup);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            OracleResultSet rs = (OracleResultSet) cstmt.getCursor(1);
            
            while(rs.next())
            {
                Group gp = new Group();
                gp.setDriver_id(rs.getDouble("driver_id"));
                gp.setG_id(rs.getDouble("g_id"));
                gp.setPath(rs.getString("path"));
                gp.setStart_time(rs.getTimestamp("start_time"));
                gp.setSize(rs.getDouble("grp_size"));
                grps.add(gp);
            }
            conn.close();
            
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }    
        return grps;
    }
    
    
    public Point getDriver(int groupId){
                    Point location = new Point();
                    
                    try
                     {     
                             conn = this.getConnection();
                             OracleCallableStatement cstmt = (OracleCallableStatement) conn.prepareCall(GET_DRIVER);
                                    
                                    
                            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
                            cstmt.setString(2, ""+groupId);                 
                            cstmt.execute();
                            OracleResultSet rs = (OracleResultSet) cstmt.getCursor(1);
                             if (rs.next()) {
                                            
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
                                             location = new Point(lat,lng);                                  
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
                    
                    return location;
            }
    
    
    public Integer getGroupIdAndDeleteEmp(Double empId){
        Integer grpId = -1;
        
        try
         {     
                 conn = this.getConnection();
                 OracleCallableStatement cstmt = (OracleCallableStatement) conn.prepareCall(GET_GROUP_ID_AND_DELETE_EMPLOYEE);
                        
                        
                cstmt.registerOutParameter(1, OracleTypes.CURSOR);
                cstmt.setString(2, ""+empId);                 
                cstmt.execute();
                OracleResultSet rs = (OracleResultSet) cstmt.getCursor(1);
                 if (rs.next()) {
                                
                         String grpId_str = rs.getString("g_id");
                         
                         if(grpId_str!=null){
                                 grpId = Integer.parseInt(grpId_str);
                         }
                                      
            }
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
        
        return grpId;
    }
    
    public Group getGroup(Integer groupId)
	{
		
		 Group grp = new Group();
		 try {
			 Connection conn = null;  
			 conn = this.getConnection();
			 OracleCallableStatement cstmt = (OracleCallableStatement) conn.prepareCall(GET_GROUP);
			 cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			 cstmt.setString(2, ""+groupId);       
			 cstmt.execute();
			 OracleResultSet rs = (OracleResultSet)cstmt.getCursor(1);
			 if (rs.next()) {
				 	
				 
				 grp.setDriver_id(Double.parseDouble(rs.getString("driver_id")));
				 grp.setG_id(Double.parseDouble(rs.getString("g_id")));
				 grp.setPath(rs.getString("path"));
				 grp.setSize(Double.parseDouble(rs.getString("grp_size")));
				 grp.setStart_time(rs.getTimestamp("start_time"));

		        }
			 
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return grp;
		
	}
    
    public static void main(String[] args){
    	/*Employee emp = new Employee();
        emp.setId(4.0);
        emp.setAddress("744 Edgewater Blvd, Apt 200, Foster City, CA 94404");
        emp.setCoordx(37.55);
        emp.setCoordy(-122.27);
        emp.setHome_departure(Timestamp.valueOf("2011-10-02 18:48:05"));
        emp.setOffice_departure(Timestamp.valueOf("2011-10-02 18:48:05"));
        emp.setIs_driver(true);
        emp.setUsername("fdsggawr");
        emp.setPassword("foisdiohfds");
        emp.setPhone(9789384939.0);
        emp.setName("Virendra ff");
        emp.setEmail("dd@gmail.com");
        emp.setIs_assigned_grp(false);
        
        DbUtil obj = new DbUtil();
        obj.updateEmployee(emp);*/
    	
    	DbUtil obj = new DbUtil();
    	obj.getGroup(5);
    }

	
}
