package oraclecommute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import oraclecommute.Point;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

import java.net.URLEncoder;

public class Direction {
	public List<Point> parsePath(String jsonPath) throws JSONException{
    	List<Point> path = new ArrayList<Point>();
    	JSONObject obj = new JSONObject(jsonPath);
    	JSONArray routes = obj.getJSONArray("routes");
    	obj = routes.getJSONObject(0);
    	JSONArray legs = obj.getJSONArray("legs");
    	for(int i=0; i<legs.length(); i++){
    		JSONObject leg = legs.getJSONObject(i);
    		JSONArray steps = leg.getJSONArray("steps");
    		for(int j=0;j<steps.length();j++){
    			Point pt = new Point();    			
    			pt.setX(steps.getJSONObject(j).getJSONObject("start_location").getDouble("lat"));
    			pt.setY(steps.getJSONObject(j).getJSONObject("start_location").getDouble("lng"));
    			path.add(pt);
    		}
    		Point dst = new Point();
        	dst.setX(leg.getJSONObject("end_location").getDouble("lat"));
        	dst.setY(leg.getJSONObject("end_location").getDouble("lng"));
    	} 
    	
    	for(int i=0; i<path.size(); i++){
    		System.out.println(path.get(i).getX()+", "+path.get(i).getY());
    	}
    	return path;    	
    }
	
	public List<Point> getPath(String start, String end) throws MalformedURLException, IOException, JSONException{
		
		String USER_AGENT = "Mozilla/5.0";
		
		String url = "https://maps.googleapis.com/maps/api/directions/json?origin="+URLEncoder.encode(start)+"&destination="+URLEncoder.encode(end);

		List<Point> path = new ArrayList<Point>();
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
		
		return parsePath(jsonPath);
	}
	
	public static void main(String[] args) throws MalformedURLException, IOException, JSONException{
		Direction obj =  new Direction();
		obj.getPath("Toronto", "Montreal");
	}
	
}
