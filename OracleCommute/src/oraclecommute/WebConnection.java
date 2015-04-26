/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oraclecommute;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author sahkumar
 */
public class WebConnection {
   /**
   * This method opens a new connection to the url
   * @param url URL to connect to
   * @return HttpURLConnection a new connection
   */
    public static HttpURLConnection getConnection(String url) throws MalformedURLException, IOException 
    {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        return con;   
    }
    
   /**
   * This method disconnects the connection(one might not need this method)
   * @param con HttpURLConnection
   * @return boolean true if successfully closed
   */
    public static boolean closeConnection(HttpURLConnection con)
    {
        if( con != null)
        {
            con.disconnect();
            return true;
        }
        else
           return false;
    }
}