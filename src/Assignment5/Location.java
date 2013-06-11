package Assignment5V2;

import org.json.JSONArray;
import org.json.JSONObject;

public class Location {
	String address;
	String gps;
	double elevation;

	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public double getElevation(String json) {
		double elevation = 0;
		JSONObject jsonObject = new JSONObject(json);
		JSONArray resultsArray = jsonObject.getJSONArray("results");
		for (int i = 0; i < resultsArray.length(); ++i) {
			JSONObject elevationObject = resultsArray.getJSONObject(i);
			elevation = elevationObject.getDouble("elevation");
			System.out.println("elevation is: " + elevation);
		}
		return elevation;	
		}
	
	public String getGps(String json) {
		double lat = 0;
		double lng = 0;
		String gps = "";
		JSONObject jsonObject = new JSONObject(json);
		JSONArray resultsArray = jsonObject.getJSONArray("results");
		for (int i = 0; i < resultsArray.length(); ++i) {
			JSONObject geometryObject = resultsArray.getJSONObject(i)
					.getJSONObject("geometry");
			JSONObject locationObject = geometryObject
					.getJSONObject("location");
			System.out.println("lat is: " + locationObject.getDouble("lat"));
			lat = locationObject.getDouble("lat");
			System.out.println("lng is: " + locationObject.getDouble("lng"));
			lng = locationObject.getDouble("lng");
			gps = lat + "," + lng;
		}
		return gps;
	}

}
