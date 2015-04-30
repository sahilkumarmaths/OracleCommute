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