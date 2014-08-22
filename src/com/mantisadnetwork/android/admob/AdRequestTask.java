package com.mantisadnetwork.android.admob;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.ads.mediation.customevent.CustomEventBannerListener;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.loopj.android.image.SmartImageView;

public class AdRequestTask extends AsyncTask<Void, Void, Void> {
	private final String zone;
	private final Activity activity;
	private final UserContext context;
	private final CustomEventBannerListener listener;

	private AdResponse result;

	public AdRequestTask(Activity activity, CustomEventBannerListener listener, String zone, UserContext context) {
		this.activity = activity;
		this.listener = listener;
		this.zone = zone;
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
	}

	@Override
	protected Void doInBackground(Void... params) {
		AdvertisingIdClient.Info info = null;

		try {
			info = AdvertisingIdClient.getAdvertisingIdInfo(this.activity);
		} catch (Exception e) {

		}

		result = AdRequest.exec(Context.get(), context, info, new String[] { zone });

		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		final Ad ad = this.result.getAdForZone(zone);

		if (ad != null) {
			SmartImageView imageView = new SmartImageView(activity);
			imageView.setImageUrl(ad.getImage());
			imageView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					listener.onClick();
					listener.onPresentScreen();
					listener.onLeaveApplication();
					Intent intent = new Intent("android.intent.action.VIEW", ad.getUri());
					activity.startActivity(intent);
				}
			});

			listener.onReceivedAd(imageView);
		}
	}
}