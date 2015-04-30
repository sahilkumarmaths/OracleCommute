package oraclecommute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

//import java.nio.charset.StandardCharsets.UTF_8;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class Direction {
    private static final String GEOCODE = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static final String USER_AGENT = "Mozilla/5.0";
        
    public static Point getCoordinates(String addr) throws UnsupportedEncodingException, IOException, JSONException
    {
        Point pt = new Point();

        String encodedStr = URLEncoder.encode(addr,  "UTF-8");
        HttpURLConnection con = WebConnection.getConnection(GEOCODE + encodedStr);

        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        JSONArray arr;
        JSONObject obj;

        obj = new JSONObject(response.toString());     //Total object
        arr = obj.getJSONArray("results");    //Results array
        obj = arr.getJSONObject(0);           //Results arryy 0

        obj = obj.getJSONObject("geometry").getJSONObject("location");   //Geomery object
        
        pt.setLat(obj.getDouble("lat"));
        pt.setLng(obj.getDouble("lng"));
        return pt;
    }

    public static List<Point> parsePath(String jsonPath) throws JSONException{
    	List<Point> path = new ArrayList<Point>();
    	JSONObject obj = new JSONObject(jsonPath);
    	JSONArray routes = obj.getJSONArray("routes");
    	obj = routes.getJSONObject(0);
    	JSONArray legs = obj.getJSONArray("legs");
    	for(int i=0; i<legs.length(); i++){
    		JSONObject leg = legs.getJSONObject(i);
    		JSONArray steps = leg.getJSONArray("steps");
    		for(int j=0;j<steps.length();j++){
    			Point pt = new Point(steps.getJSONObject(j).getJSONObject("start_location").getDouble("lat"),steps.getJSONObject(j).getJSONObject("start_location").getDouble("lng") );    			
    			path.add(pt);
    		}
    		Point dst = new Point(leg.getJSONObject("end_location").getDouble("lat"),leg.getJSONObject("end_location").getDouble("lng"));
        	path.add(dst);
    	} 
    	for(int i = 0 ; i<path.size(); i++){
    		System.out.println(path.get(i).getLat()+", "+path.get(i).getLng());
    	}
    	
    	return path;    	
    }
    
    public static Double parseDistance(String jsonPath) throws JSONException{
    	List<Point> path = new ArrayList<Point>();
    	JSONObject obj = new JSONObject(jsonPath);
    	JSONArray routes = obj.getJSONArray("routes");
    	obj = routes.getJSONObject(0);
    	JSONArray legs = obj.getJSONArray("legs");
    	Double distance = 0.0;
    	for(int i=0; i<legs.length(); i++){
    		JSONObject leg = legs.getJSONObject(i);
    		JSONObject dist = leg.getJSONObject("distance");
    		distance += dist.getDouble("value");
    	} 
    	
    	
    	return distance;    	
    }
	
    public static List<Point> getPath(String start, String end) throws MalformedURLException, IOException, JSONException
    {
        String url = "http://maps.googleapis.com/maps/api/directions/json?origin="+URLEncoder.encode(start)+"&destination="+URLEncoder.encode(end);

       
        HttpURLConnection con = WebConnection.getConnection(url);
       // Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("www-proxy.us.oracle.com", 80));
       // URL httpsUrl = new URL(url);
        
       // HttpsURLConnection con = (HttpsURLConnection) httpsUrl.openConnection(proxy); 
      
   //    System.out.println("Hello");
        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

  //      System.out.println("\nSending 'GET' request to URL : " + url);
   //     System.out.println("Response Code : " + responseCode);
        
        BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String jsonPath = response.toString();
        System.out.println(jsonPath);
        return parsePath(jsonPath);
    }
    
    public static Double getRoadDistance(String start, String end) throws MalformedURLException, IOException, JSONException
    {
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin="+URLEncoder.encode(start)+"&destination="+URLEncoder.encode(end);

        
        //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("www-proxy.us.oracle.com", 80));
       // URL httpsUrl = new URL(url);
        
    //    HttpsURLConnection con = (HttpsURLConnection) httpsUrl.openConnection(proxy); 
        
        HttpURLConnection con = WebConnection.getConnection(url);
    
        
        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        
        BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String jsonPath = response.toString();
        return parseDistance(jsonPath);
    }
    
    public static void main(String[] args) throws MalformedURLException, IOException, JSONException{
    	Direction obj = new Direction();
    	obj.getPath("461 25th Ave, San Francisco, CA 94121, USA", "Los Gatos, CA, USA");
    	System.out.println("NEW");
    	//System.out.println(obj.getRoadDistance("San Mateo", "100 Oracle Parkway, Redwood Shores, CA - 94064"));
    	//System.out.println(obj.getCoordinates("100 Oracle Parkway, Redwood Shores, CA - 94065"));
    	
    }
}
