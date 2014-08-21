package com.mantisadnetwork.android.admob;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class AdResponse {
	private Map<String, Ad> ads = new HashMap<String, Ad>();

	public AdResponse(JSONObject object) {
		JSONObject ads;

		try {
			ads = object.getJSONObject("ads");

			@SuppressWarnings("unchecked")
			Iterator<String> itr = ads.keys();

			while (itr.hasNext()) {
				String key = itr.next();

				this.ads.put(key, new Ad(ads.getJSONObject(key)));
			}
		} catch (JSONException e) {
			throw new RuntimeException("There was a problem converting MANTIS JSON to object", e);
		}
	}

	public Ad getAdForZone(String zone) {
		return this.ads.get(zone);
	}
}
