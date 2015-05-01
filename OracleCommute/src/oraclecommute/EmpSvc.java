package oraclecommute;

import java.sql.Timestamp;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONObject;

@Path("oraclecommute")
public class EmpSvc {


    @POST
    @Consumes("text/plain")
    @Produces("text/plain")
    @Path("empSave")
    public String saveData(String empData)
        {
           
            Employee emp = new Employee();
            try
            { 
                JSONObject empObj = new JSONObject(empData);
                emp.setUsername(empObj.getString("username"));
                emp.setPassword(empObj.getString("passwd"));
                emp.setCoordx(Double.valueOf(empObj.getString("coordx")));
                emp.setCoordy(Double.valueOf(empObj.getString("coordy")));
                emp.setName(empObj.getString("name"));
                emp.setPhone(Double.valueOf(empObj.getString("phNo")));
                emp.setAddress(empObj.getString("address"));
                emp.setEmail(empObj.getString("email"));
                emp.setHome_departure(Timestamp.valueOf(empObj.getString("home_departure")));
                emp.setOffice_departure(Timestamp.valueOf(empObj.getString("office_departure")));
                emp.setIs_driver(empObj.getString("is_driver").equals('Y') ? true : false);
                emp.setIs_assigned_grp(empObj.getString("is_grp_assigned").equals('Y') ? true : false);
               
                DbUtil db = new DbUtil();
                db.createEmployee(emp);           
               /* emp_id NUMBER NOT NULL,
                    username VARCHAR2(64),
                    passwd VARCHAR2(64),
                    coordx NUMBER,
                    coordy NUMBER,
                    name VARCHAR2(256),
                    phNo NUMBER(10),
                    address VARCHAR2(256),
                    email VARCHAR2(64),
                    home_departure TIMESTAMP(6),
                    office_departure TIMESTAMP(6),
                    is_driver VARCHAR2(1 CHAR),
                    is_grp_assigned VARCHAR2(1 CHAR)
                */
                return "true";
            }
            catch (Exception e)
            {
                System.out.println("Sahil String: " + empData );
                System.out.println(e.getMessage());
               
                e.printStackTrace();
                return "false";
            }
        }
    
}




/*package oraclecommute;

import java.sql.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

@Path("oraclecommute")
public class EmpSvc {
    public EmpSvc() {
        super();
    }

    @POST
    @Consumes("text/plain")
    @Produces("text/plain")
    @Path("save")
    public String saveData(String empData) {
        
        Object obj=JSONValue.parse(empData);
        JSONObject empObj  = (JSONObject)obj;
        
        Date dte = new Date(0, 0, 0);
        Employee emp = new Employee();
        emp.setUsername(empObj.get("username").toString());
        emp.setPassword(empObj.get("password").toString());
        emp.setAddress(empObj.get("address").toString());
        emp.setCoordx(Double.valueOf(empObj.get("coordx").toString()));
        emp.setCoordy(Double.valueOf(empObj.get("coordy").toString()));
        
        emp.setEmail(empObj.get("email").toString());
        emp.setName(empObj.get("name").toString());
        emp.setPhone(Double.valueOf(empObj.get("phone").toString()));
        emp.setTime_departure(dte);
        emp.setIs_driver(true);
        
        
        DbUtil db = new DbUtil();
        db.createEmployee(emp);
       
        return empObj.get("name").toString();
    }
}
*/