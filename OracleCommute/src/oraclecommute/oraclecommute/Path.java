package oraclecommute;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

import org.json.JSONException;

public class Path {

	public String findPath(Integer groupId) throws MalformedURLException, IOException, JSONException, InterruptedException{
		//read locations from DB
		ArrayList<Point> locations = new ArrayList<Point>();
		ArrayList<Point> fullpath = new ArrayList<Point>();
		DbUtil obj = new DbUtil();
				
		locations = obj.getGroupEmployeeLocations(groupId);
		ArrayList<Point> path = new ArrayList<Point>();
		int[] used = new int[locations.size()];
		
		Point driver = obj.getDriver(groupId);
		
		for(int i=0; i<used.length;i++){
			used[i]=0;
		}
		int cur_idx = 0;
		for(int i=0; i<locations.size(); i++){
			if(driver == locations.get(i)){
				cur_idx = i;
			}
		}
		
		path.add(locations.get(cur_idx));
		used[cur_idx]=1;
		
		int i=1;
		while(i<locations.size()){
			Point cur_pt = locations.get(cur_idx);		
			Double dist = 1000000000.0;
			int nxt_idx = 0;
			for(int j=0;j<locations.size();j++){	
				if(used[j]==1)continue;
				Point nxt_pt = locations.get(j);
				Double cur_dist = Util.roadDistance(cur_pt, nxt_pt);
				if(cur_dist < dist){
					nxt_idx = j;
					dist = cur_dist;
				}
			}
			used[nxt_idx]=1;
			path.add(locations.get(nxt_idx));
			i++;
			cur_idx = nxt_idx;
		}	
		
		Point pt = new Point(37.530953,-122.263427);
		path.add(pt);
		
		for(int j=0; j<path.size()-1; j++){
			if(fullpath.size()>0){
				fullpath.remove(fullpath.size()-1);
			}
			Point pt1 = path.get(j);
			Point pt2 = path.get(j+1);
			String start = pt1.getLat()+","+ pt1.getLng();
			String end = pt2.getLat()+","+ pt2.getLng();
			List<Point> leg = Direction.getPath(start, end);
			for(int k=0;k<leg.size();k++){
				fullpath.add(leg.get(k));
			}
		}
		String path_str = Util.pathListToString(fullpath);
		//System.out.println(path_str);
		return path_str;
		//obj.writePath(groupId, path_str);
	}

	
	public static void main(String [] args) throws MalformedURLException, IOException, JSONException, InterruptedException{
		Path obj = new Path();
		obj.findPath(5);
	}

}
