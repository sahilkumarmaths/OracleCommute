package oraclecommute;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee {
    
    private String name;
    private String username;
    private String password;
    private Double coordx;
    private Double coordy;
    private Double phone;
    private String address;
    private String email;
    private Timestamp home_departure;
    private Timestamp office_departure;
    private boolean is_driver;
    private boolean is_assigned_grp;

    public boolean isIs_assigned_grp() {
        return is_assigned_grp;
    }

    public void setIs_assigned_grp(boolean is_assigned_grp) {
        this.is_assigned_grp = is_assigned_grp;
    }


    public void setPhone(Double phone) {
        this.phone = phone;
    }

    public Double getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setIs_driver(boolean is_driver) {
        this.is_driver = is_driver;
    }

    public boolean isIs_driver() {
        return is_driver;
    }


    public Employee() {
        super();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setCoordx(Double coordx) {
        this.coordx = coordx;
    }

    public Double getCoordx() {
        return coordx;
    }

    public void setCoordy(Double coordy) {
        this.coordy = coordy;
    }

    public Double getCoordy() {
        return coordy;
    }
    
    public String toString(){
        String empXml = "<name>"+getName()+"</name>"+"\n";
        return empXml;
        }

	public Timestamp getOffice_departure() {
		return office_departure;
	}

	public void setOffice_departure(Timestamp office_departure) {
		this.office_departure = office_departure;
	}

	public Timestamp getHome_departure() {
		return home_departure;
	}

	public void setHome_departure(Timestamp home_departure) {
		this.home_departure = home_departure;
	}
  
}
