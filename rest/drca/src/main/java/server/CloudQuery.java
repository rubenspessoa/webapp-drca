package server;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

final class CloudQuery {
	
	static String callCloudCodeFunction(String functionName, String param) throws Exception {
		if(functionName == null) {
			throw new NullPointerException();
		}
		else if(param == null) {
			throw new NullPointerException();
		}
		
		String url = "http://162.243.58.202:1337/parse/functions/" + functionName;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("X-Parse-Application-Id", "drca");
		con.setRequestProperty("Content-Type", "application/json");
		
		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(param);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		String res = response.toString();
		System.out.println(res);
		return res;
	}
}
