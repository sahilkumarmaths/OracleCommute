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

        Employee emp = new Employee();
        emp.setId(1.0);
        emp.setAddress("744 Edgewater Blvd, Apt 200, Foster City, CA 94404");
        emp.setCoordx(37.55);
        emp.setCoordy(-122.27);
        emp.setHome_departure(Timestamp.valueOf("2011-10-02 18:48:05"));
        emp.setOffice_departure(Timestamp.valueOf("2011-10-02 18:48:05"));
        emp.setIs_driver(true);
        emp.setUsername("vkarappa1");
        emp.setPassword("Welcome1");
        emp.setPhone(979739.0);
        emp.setName("Virendra Karappa");
        emp.setEmail("vkarappa1@gmail.com");
        
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

        //  dbUtil.createEmployee(emp);
        
     // dbUtil.getAllEmpNotAssigned();
        Group gp = new Group();


       try {
           gp.assignEmployee(emp);
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } catch (JSONException e) {
        } 
    }
    
    
}
