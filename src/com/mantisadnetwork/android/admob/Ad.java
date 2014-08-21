package com.mantisadnetwork.android.admob;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;

public class Ad {
	private final String image;
	private final String url;
	private final int width;
	private final int height;

	public Ad(JSONObject json) {
		try {
			image = json.getString("image");
			url = json.getString("url");
			width = json.getInt("width");
			height = json.getInt("height");
		} catch (JSONException e) {
			throw new RuntimeException("There was a problem converting JSON ad into object", e);
		}
	}

	public String getImage() {
		return image;
	}

	public String getUrl() {
		return url;
	}

	public Uri getUri() {
		return Uri.parse(this.getUrl());
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
