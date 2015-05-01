package oraclecommute;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.List;

import org.json.JSONException;

public class Tests {
    public Tests() {
        super();
    }
    
    
    public static void main(String args[]) {
        
        DbUtil dbUtil = new DbUtil();

 /*       Employee emp = new Employee();
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
        emp.setIs_driver(true);*/
        
    //    dbUtil.createEmployee(emp);
        
     /*   Direction dir = new Direction();
        try {
            List<Point> driverPath =
                dir.getPath("744 Edgewater Blvd Foster City",
                            "400 Oracle Pkway Redwood Shores");
            for(Point pt: driverPath) {
                
                System.out.println(pt.getLat());
            }
        } catch (JSONException e) {
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }*/

     //     dbUtil.updateEmployee(emp);
    


       try {
           
            
            Employee emp = dbUtil.getEmployee("138872");
       //    Employee emp2 = dbUtil.getEmployee(username);
           

             Group gp = new Group();
           
           
           gp.assignEmployee(emp);
           
           
        } catch (Exception e) {
        } 
    }
    
    
}
