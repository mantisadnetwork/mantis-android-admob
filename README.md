![mantis ad network](https://github.com/mantisadnetwork/mantis-android-admob/raw/master/images/logo.png)

# Application Requirements

This library depends on **Google Play Services** which is a requirement of AdMob as the old SDK has been deprecated as of August 1st, 2014. Only API level 9 (Android 2.3) or higher is supported.

# Integrating our mediation layer

Prior to integration, be sure that your application is setup to include **Google Play Services**. Instructions on how to configure the library can be found at http://developer.android.com/google/play-services/setup.html.

## Workspace Setup

1. Clone our repository to your machine locally or download the latest release as a zip file from: https://github.com/mantisadnetwork/mantis-android-admob/archive/master.zip
2. Import the project into your application workspace (File > Import > Android > Existing Android Code Into Workspace)
3. On your android application, open the Android properties screen (File > Properties > Android) and add the recently imported project as a library.

## Example Implementation

If you have never integrated into the AdMob SDK before, you can take a look at our example project that shows you how to not only configure AdMob, but to configure the required MANTIS values. https://github.com/mantisadnetwork/mantis-android-admob-example/

## Basic Configuration

To get started, the only thing you need to do is configure the MANTIS context with the property ID found in your MANTIS administration panel. Typically, this call is done in your main activity, prior to any AdMob initialization.

```
import com.mantisadnetwork.android.admob.Context;

Context.get().setPropertyId("property-id-found-in-admin");
```

# Configuring AdMob

After updating your application to include the MANTIS mediation library, you must now configure mediation per ad unit in the AdMob interface. Here is a breakdown of each setup if creating an ad unit from scratch.

## Create the ad unit

The AdMob "ad unit" is the equivelant of a "zone" on the MANTIS platform. For each ad unit you configure, you should already have a zone configured through the MANTIS administration panel.

![new ad unit](https://github.com/mantisadnetwork/mantis-android-admob/raw/master/images/screen1.jpg)

## Configure the ad unit

Currently, we only support the "Banner" ad format with the "Image" ad type. Whether you refresh the ads are up to you and we have no restriction on the ad unit name.

![configure ad unit](https://github.com/mantisadnetwork/mantis-android-admob/raw/master/images/screen2.jpg)

## Copy the "Ad unit ID"

This ID is used within your application to display the AdMob banner (regardless of whether you use MANTIS or not). See our example application for an example of where to plug this in.

![configure ad unit](https://github.com/mantisadnetwork/mantis-android-admob/raw/master/images/screen3.jpg)

## Configure mediation

After creating the ad unit, Admob should then take you back to your list of ad units for the application. Find the one you just created and "Edit mediation".

![configure ad unit](https://github.com/mantisadnetwork/mantis-android-admob/raw/master/images/screen4.jpg)

## New ad network

Each ad unit is configurable to allow for any combintation of ad networks. You could in theory use one ad unit with just AdMob and another with just MANTIS advertisements. To continue, select "New ad network".

![configure ad unit](https://github.com/mantisadnetwork/mantis-android-admob/raw/master/images/screen5.jpg)

## Custom ad network configuration

Because MANTIS isn't an official AdMob partner, you will need to configure mediation with us through a "Custom event".

![configure ad unit](https://github.com/mantisadnetwork/mantis-android-admob/raw/master/images/screen6.jpg)

## Configure MANTIS mediation

Once selected, a form will appear and prompt you to fill out the following fields:

### Class Name
```
com.mantisadnetwork.android.admob.Mediation
```

### Label
Feel free to name this what you wish, but to keep it consistent we recommend:
```
MANTIS Ad Network
```

### Parameter
The parameter you pass to our mediation class should be the MANTIS zone id that is provided to you within our interface.

![configure ad unit](https://github.com/mantisadnetwork/mantis-android-admob/raw/master/images/screen7.jpg)

## Prioritization

Once we've been setup as an ad network on your ad unit, you have to make a few changes to ensure we display.

1. Disable the "Optimize AdMob Network" option. Until you have enough data from our reporting interface to tell you what your eCPM is, you will want to keep this turned off.
2. Enter a number in the Default eCPM field that is HIGHER than the AdMob network field. Currently, there is no automated way to update this value or track from AdMob.

After making those changes, be sure to hit save at the top!

![configure priority](https://github.com/mantisadnetwork/mantis-android-admob/raw/master/images/screen8.jpg)

## ALL DONE!

You are now ready to serve ads from MANTIS! Feel free to submit any pull requests for enhancements or create issues here on github.