package oraclecommute;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
        
        return empData;
    }
}
