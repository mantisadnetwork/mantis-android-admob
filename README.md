![mantis ad network](https://github.com/mantisadnetwork/mantis-android-admob/raw/master/images/logo.png)

MANTIS Ad Network - Android AdMob Mediation
====================

Mediation layer that can be plugged into an AdMob implementation to serve advertisements from the MANTIS Ad Network on an Android application.



# Configuring AdMob

After updating your application to include the MANTIS mediation library, you must now configure mediation per ad unit in the AdMob interface. Here is a breakdown of each setup if creating an ad unit from scratch.

## Create the ad unit

The AdMob "ad unit" is the equivelant of a "zone" on the MANTIS platform. For each ad unit you configure, you should already have a zone configured through the MANTIS administration panel.

![new ad unit](https://github.com/mantisadnetwork/mantis-android-admob/raw/master/images/screen1.jpg "To begin, select New ad unit")

## Configure the ad unit

Currently, we only support the "Banner" ad format with the "Image" ad type. Whether you refresh the ads are up to you and we have no restriction on the ad unit name.

![configure ad unit](https://github.com/mantisadnetwork/mantis-android-admob/raw/master/images/screen2.jpg "Make sure Banner and Ad type = Image")