package oraclecommute;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee {
    
    private String name;
    private String username;
    private String password;
    private String coordx;
    private String coordy;
    private String phone;
    private String address;
    private String email;
    private String time_departure;
    private boolean is_driver;


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
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

    public void setTime_departure(String time_departure) {
        this.time_departure = time_departure;
    }

    public String getTime_departure() {
        return time_departure;
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

    public void setCoordx(String coordx) {
        this.coordx = coordx;
    }

    public String getCoordx() {
        return coordx;
    }

    public void setCoordy(String coordy) {
        this.coordy = coordy;
    }

    public String getCoordy() {
        return coordy;
    }
    
    public String toString(){
        String empXml = "<name>"+getName()+"</name>"+"\n";
        return empXml;
        }
  
}
