package oraclecommute;

public class Point {
	private Double lat;
	private Double lng;

    public Point(Double lat, Double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public Point() {
		// TODO Auto-generated constructor stub
	}

	public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
 
}
