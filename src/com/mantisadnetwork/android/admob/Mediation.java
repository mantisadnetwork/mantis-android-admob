package com.mantisadnetwork.android.admob;

import android.app.Activity;

import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.customevent.CustomEventBanner;
import com.google.ads.mediation.customevent.CustomEventBannerListener;

public class Mediation implements CustomEventBanner {
	@Override
	public void requestBannerAd(CustomEventBannerListener listener, Activity activity, String label,
			String serverParameter, AdSize size, MediationAdRequest mediationAdRequest, Object customEventExtra) {
		AdRequestTask task = new AdRequestTask(activity, listener, serverParameter);
		task.execute();
	}

	@Override
	public void destroy() {
	}

}
