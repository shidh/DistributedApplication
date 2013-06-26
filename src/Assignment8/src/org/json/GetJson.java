package org.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetJson {
	public static String getJson(String gUrl) {
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
	
	
	public static String parseTemp(String json) {
		String temp = "";
		double tempMax = 0;
		double tempMin = 0;
		double currentTemp = 0;
		JSONObject jsonObject = new JSONObject(json);
		JSONObject jsonObj = jsonObject.getJSONObject("data");
		
		JSONArray current_condition = jsonObj.getJSONArray("current_condition");
		for (int i = 0; i < current_condition.length(); ++i) {
			JSONObject elevationObject = current_condition.getJSONObject(i);
			currentTemp = elevationObject.getDouble("temp_C");
			System.out.println("currentTemp is: " + currentTemp);
		}

		JSONArray weather = jsonObj.getJSONArray("weather");
		for (int i = 0; i < weather.length(); ++i) {
			JSONObject elevationObject = weather.getJSONObject(i);
			tempMax = elevationObject.getDouble("tempMaxC");
			tempMin = elevationObject.getDouble("tempMinC");
			System.out.println("temp is: " + tempMax + tempMin);
		}
		temp ="Current TempC:"+currentTemp+"    MaxtempC:"+ tempMax + "    MintempC:" + tempMin;
		return temp;
	}
	
	public static String parseWeather(String json) {
		String currentDesc = "";
		//String weatherDesc = "";
		String gweather = "";
		JSONObject jsonObject = new JSONObject(json);
		JSONObject jsonObj = jsonObject.getJSONObject("data");
		
		JSONArray current_condition = jsonObj.getJSONArray("current_condition");
		for (int i = 0; i < current_condition.length(); ++i) {
			JSONObject elevationObject = current_condition.getJSONObject(i);
			JSONArray weathers = elevationObject.getJSONArray("weatherDesc");
			JSONObject weather = weathers.getJSONObject(0);
			currentDesc = weather.getString("value");		
			System.out.println("current weather is: " + currentDesc);
		}
		
//		JSONArray resultsArray = jsonObj.getJSONArray("weather");
//		for (int i = 0; i < resultsArray.length(); ++i) {
//			JSONObject elevationObject = resultsArray.getJSONObject(i);
//			JSONArray weathers = elevationObject.getJSONArray("weatherDesc");
//			JSONObject weather = weathers.getJSONObject(0);
//			weatherDesc = weather.getString("value");		
//			System.out.println("today's weather is: " + weatherDesc);
//		}
		gweather="Current weather is :"+currentDesc;
		return gweather;
	}
}
