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

import org.json.JSONException;
import org.json.JSONObject;

public class AdRequest {
	public static AdResponse exec(Context context, String[] zones) {
		JSONObject request = buildJsonRequest(context, zones);

		InputStream response = sendRequest("http://mantisadnetwork.apiary-mock.com/mobile/serve", request);

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

	protected static JSONObject buildJsonRequest(Context context, String[] zones) {
		JSONObject parameter = new JSONObject();

		try {
			if (context.getTitle() != null) {
				parameter.put("title", context.getTitle());
			}

			if (context.getScreen() != null) {
				parameter.put("screen", context.getScreen());
			}

			parameter.put("markImpression", "true");
			parameter.put("zones", zones);
			parameter.put("propertyId", context.getPropertyId());
			parameter.put("sdkVersion", "1");
		} catch (JSONException ex) {
			throw new RuntimeException("Unable to build JSON request object", ex);
		}

		return parameter;
	}
}
