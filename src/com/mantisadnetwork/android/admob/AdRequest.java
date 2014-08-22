package com.mantisadnetwork.android.admob;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;

public class AdRequest {
	public static String ENDPOINT_URL = "http://mantodea.mantisadnetwork.com/mobile/ads";
	
	public static AdResponse exec(Context context, UserContext userContext, AdvertisingIdClient.Info aid, String[] zones) {
		JSONObject request = buildJsonRequest(context, userContext, aid, zones);

		InputStream response = sendRequest(ENDPOINT_URL, request);

		return parseResponse(response);
	}

	protected static AdResponse parseResponse(InputStream is) {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));

		StringBuilder out = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		String line;

		try {
			while ((line = in.readLine()) != null) {
				out.append(line);
				out.append(newLine);
			}
		} catch (IOException e) {
			throw new RuntimeException("There was a problem processing the response", e);
		}

		JSONObject json;

		try {
			json = new JSONObject(out.toString());
		} catch (JSONException e) {
			throw new RuntimeException("Invalid JSON return from MANTIS", e);
		}

		return new AdResponse(json);
	}

	protected static InputStream sendRequest(String endpoint, JSONObject request) {
		URL url;

		try {
			url = new URL(endpoint);
		} catch (MalformedURLException e) {
			throw new RuntimeException("Invalid endpoint URL defined: " + endpoint, e);
		}

		HttpURLConnection conn;

		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			throw new RuntimeException("Unable to connect to the endpoint: " + endpoint, e);
		}

		conn.setReadTimeout(10000);
		conn.setConnectTimeout(15000);
		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("Content-length", String.format("%d", request.toString().length()));
		conn.setDoInput(true);
		conn.setDoOutput(true);

		try {
			conn.setRequestMethod("POST");
		} catch (ProtocolException e) {
			throw new RuntimeException("This should never happen.", e);
		}

		OutputStream os;

		try {
			os = conn.getOutputStream();

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

			writer.write(request.toString());
			writer.flush();
			writer.close();
			os.close();
			conn.connect();

			return conn.getInputStream();
		} catch (IOException e) {
			throw new RuntimeException("There was a problem retrieving the response from: " + endpoint, e);
		}
	}

	protected static JSONObject buildJsonRequest(Context context, UserContext userContext,
			AdvertisingIdClient.Info aid, String[] zones) {
		JSONObject parameter = new JSONObject();

		try {
			if (aid != null) {
				parameter.put("trackable", aid.isLimitAdTrackingEnabled());
				parameter.put("mobileUUID", aid.getId());
			}

			if (context.getTitle() != null) {
				parameter.put("title", context.getTitle());
			}

			if (context.getScreen() != null) {
				parameter.put("screen", context.getScreen());
			}

			if (userContext.getAge() != null) {
				parameter.put("age", userContext.getAge());
			}

			if (userContext.getLatitude() != null) {
				parameter.put("latitude", userContext.getLatitude());
			}

			if (userContext.getLongitude() != null) {
				parameter.put("longitude", userContext.getLongitude());
			}

			parameter.put("mobileSdk", true);
			parameter.put("zones", new JSONArray(Arrays.asList(zones)));
			parameter.put("propertyId", context.getPropertyId());
		} catch (JSONException ex) {
			throw new RuntimeException("Unable to build JSON request object", ex);
		}

		return parameter;
	}
}
