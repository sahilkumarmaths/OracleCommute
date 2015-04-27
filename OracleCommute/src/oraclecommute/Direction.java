package oraclecommute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import static java.nio.charset.StandardCharsets.UTF_8;
import oraclecommute.Point;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Direction {
        private static final String GEOCODE = "https://maps.googleapis.com/maps/api/geocode/json?address=";
        private static final String USER_AGENT = "Mozilla/5.0";
        
        public static Point getCoordinates(String addr) throws UnsupportedEncodingException, IOException, JSONException
        {
            Point pt = new Point();
            
            String encodedStr = URLEncoder.encode(addr, UTF_8.toString());
            System.out.println(GEOCODE + encodedStr);
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
            System.out.println(response);
            
            JSONArray arr;
            JSONObject obj;
            
            obj = new JSONObject(response.toString());     //Total object
            
            arr = obj.getJSONArray("results");    //Results array
            obj = arr.getJSONObject(0);           //Results arryy 0
            
            obj = obj.getJSONObject("geometry").getJSONObject("location");   //Geomery object
            System.out.println(obj.toString());
            pt.setX(obj.getDouble("lat"));
            pt.setY(obj.getDouble("lng"));
            return pt;
        }
        
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
    			pt.setX(Double.parseDouble(steps.getJSONObject(j).getJSONObject("start_location").getString("lat")));
    			pt.setY(Double.parseDouble(steps.getJSONObject(j).getJSONObject("start_location").getString("lng")));
    			path.add(pt);
    		}
    	}    	
    	return path;    	
    }
}
