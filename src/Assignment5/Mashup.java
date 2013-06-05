package Assignment5;

import org.json.*;
import java.io.*;
import java.net.*;

public class Mashup {
	/**
	 * Build a mashup with the following functionality: 
	 * The customer enters two locations by address. 
	 * The service returns the difference in elevation between the two locations. 
	 * As these Webservices are RESTful webservices,
	 * the requests are encoded in the url. 
	 * Therefore you may use plain java with no special webservice functionality
	 */
	public static void main(String[] args) {

		String GeoUrl = "";
		String ElevationUrl = "";
        String addressA = "";
        String addressB = "";
        
		try {
			System.out.println("Address Format: [street]Christoph-Probst-Str." +
					" + [number]8104 + [postcode]80805 + [city]muenchen ");
			System.out.println("Please type in address A: " );
			addressA = readUserInput();
			
			System.out.println("Please type in address B: ");
			addressB = readUserInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Location a = new Location();
		a.setAddress(addressA);
		GeoUrl = "http://maps.googleapis.com/maps/api/geocode/json?address="
				+ a.getAddress() + "&sensor=false";
		System.out.println("Address A is :" + a.getAddress());
		ElevationUrl = "http://maps.googleapis.com/maps/api/elevation/json?locations="
				+ parseLatLng(getJson(GeoUrl)) + "&sensor=false";
		parseElevation(getJson(ElevationUrl));

		System.out.println();
		Location b = new Location();
		b.setAddress(addressB);
		GeoUrl = "http://maps.googleapis.com/maps/api/geocode/json?address="
				+ b.getAddress() + "&sensor=false";
		System.out.println("Address B is :" + b.getAddress());

		ElevationUrl = "http://maps.googleapis.com/maps/api/elevation/json?locations="
				+ parseLatLng(getJson(GeoUrl)) + "&sensor=false";
		parseElevation(getJson(ElevationUrl));

	}

	static String getJson(String gUrl) {
		String json = "";
		try {
			URL url = new URL(gUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			// String response = conn.getResponseMessage();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			
			String line;
			while ((line = in.readLine()) != null) {
				// System.out.println(line);
				json += line;
			}

			// close connection
			is.close();
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	static String parseLatLng(String json) {
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

	static double parseElevation(String json) {
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
	
    private static String readUserInput() throws IOException {
        InputStreamReader is_reader = new InputStreamReader(System.in);
        return new BufferedReader(is_reader).readLine();
    }
}
