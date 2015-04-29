package oraclecommute;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.json.JSONException;

public class Path {
	
	public ArrayList<Point> findPath(Integer groupId) throws MalformedURLException, IOException, JSONException{
		//read locations from DB
		ArrayList<Point> locations = new ArrayList<Point>();
		ArrayList<Point> path = new ArrayList<Point>();
		int[] used = new int[locations.size()];
		for(int i=0; i<used.length;i++){
			used[i]=0;
		}
		path.add(locations.get(0));
		used[0]=1;
		int cur_idx = 0;
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
		}		
		return path;
	}

}
