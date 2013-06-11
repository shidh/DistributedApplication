package Assignment5V2;

import org.json.*;
import java.io.*;
import java.net.*;

public class Mashup {
	/**
	 * Build a mashup with the following functionality: The customer enters two
	 * locations by address. The service returns the difference in elevation
	 * between the two locations. As these Webservices are RESTful webservices,
	 * the requests are encoded in the url. Therefore you may use plain java
	 * with no special webservice functionality
	 */

	public static void main(String[] args) {

		String GeoUrl = "";
		String ElevationUrl = "";
		String addressA = "";
		String addressB = "";

		try {
			System.out.println("Address Format: [street]Christoph-Probst-Str."
					+ " + [number]8104 + [postcode]80805 + [city]muenchen ");
			System.out.println("Please type in address A: ");
			addressA = readUserInput();

			System.out.println("Please type in address B: ");
			addressB = readUserInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Start time out thread
		TimeoutThread t = new TimeoutThread(5000, new TimeoutException(
				"Time out 5 seconds"), Thread.currentThread());
		try {
			t.start();

			Location a = new Location();
			a.setAddress(addressA);
			GeoUrl = "http://maps.googleapis.com/maps/api/geocode/json?address="
					+ a.getAddress() + "&sensor=false";
			System.out.println("Address A is :" + a.getAddress());
			String json4gps = getJson(GeoUrl);

			ElevationUrl = "http://maps.googleapis.com/maps/api/elevation/json?locations="
					+ a.getGps(json4gps) + "&sensor=false";
			String json4evevation = getJson(ElevationUrl);
			a.getElevation(json4evevation);

			System.out.println();
			Location b = new Location();
			b.setAddress(addressB);
			GeoUrl = "http://maps.googleapis.com/maps/api/geocode/json?address="
					+ b.getAddress() + "&sensor=false";
			System.out.println("Address B is :" + b.getAddress());

			json4gps = getJson(GeoUrl);
			ElevationUrl = "http://maps.googleapis.com/maps/api/elevation/json?locations="
					+ b.getGps(json4gps) + "&sensor=false";
			json4evevation = getJson(ElevationUrl);
			b.getElevation(json4evevation);

			//end time out thread
			t.cancel();
		} catch (TimeoutException e) {
			// TODO
		}

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

	private static String readUserInput() throws IOException {
		InputStreamReader is_reader = new InputStreamReader(System.in);
		return new BufferedReader(is_reader).readLine();
	}
}
