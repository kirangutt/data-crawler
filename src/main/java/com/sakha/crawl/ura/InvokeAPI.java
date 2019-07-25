package com.sakha.crawl.ura;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InvokeAPI {

	public static String invokeAPI(String apiUrl) {

		String response = "";
		
		try {
		
			URL url = new URL(apiUrl);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			StringBuilder stringBuilder = new StringBuilder("");
			String line;
		
			while ((line = bufferReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			response = stringBuilder.toString();

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return response;
	}

}
