package oraclecommute;

import java.util.ArrayList;
import java.util.List;
import oraclecommute.Point;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    			pt.setX(Double.parseDouble(steps.getJSONObject(j).getJSONObject("start_location").getString("lat")));
    			pt.setY(Double.parseDouble(steps.getJSONObject(j).getJSONObject("start_location").getString("lng")));
    			path.add(pt);
    		}
    	}    	
    	return path;    	
    }
}
