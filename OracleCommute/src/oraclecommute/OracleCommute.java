/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oraclecommute;

import java.util.ArrayList;
import java.util.List;

import org.json.*;

/**
 *
 * @author sahkumar
 */
public class OracleCommute {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       DbUtil obj = new DbUtil();
       List <Group > abc = obj.getAllGroup();
       
       for(Group gp : abc)
       {
           System.out.println(gp.getDriver_id());
       }
      // System.out.println(a.getLat() + " " + a.getLng());
    }
    
    
    
}
