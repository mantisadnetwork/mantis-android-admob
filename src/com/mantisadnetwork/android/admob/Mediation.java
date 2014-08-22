package com.mantisadnetwork.android.admob;

import android.app.Activity;

import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.customevent.CustomEventBanner;
import com.google.ads.mediation.customevent.CustomEventBannerListener;

public class Mediation implements CustomEventBanner {
	@Override
	public void requestBannerAd(CustomEventBannerListener listener, Activity activity, String label,
			String serverParameter, AdSize size, MediationAdRequest request, Object customEventExtra) {

		UserContext context = new UserContext();

		if (request.getLocation() != null) {
			context.setLatitude(request.getLocation().getLatitude());
			context.setLongitude(request.getLocation().getLatitude());
		}

		if (request.getAgeInYears() != null) {
			context.setAge(request.getAgeInYears());
		}

		AdRequestTask task = new AdRequestTask(activity, listener, serverParameter, context);
		task.execute();
	}

	@Override
	public void destroy() {
	}

}
