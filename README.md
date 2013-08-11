#android-intents

![Example Image][1]

A collection of well-known Android intents for most common actions. 
Helps you call thirdparty apps to do generic work instead of you. 
You can call camera app for taking photos, share text, make calls, 
send SMS messages, scan barcodes and QR-codes and more. See methods 
and javadocs of `IntentUtils` class inside of library.

Also demo app available at [Google Play](https://play.google.com/store/apps/details?id=com.dmitriy.tarasov.android.intents.demo)

![QR][2]


## Intents Collection

At this moment collection includes this intents

- **Share Text** - share text via thirdparty app like twitter, facebook, email, sms etc.
- **Send SMS** - send SMS message using built-in app
- **Send Email** - send email using appropriate app
- **Show Location** - opens the Maps application to the given coordinates
- **Open Play Store** - opens app page at Google Play store
- **Find Location** - opens the Maps application to the given query
- **Show Street View** - opens Street View app
- **Call Phone** - call to phone using standard dialer
- **Dial Phone** - opens standard dialer without starting a call
- **Open Link** - opens a web page in standard browser
- **Capture Photo** - call standard camera app for taking a shot
- **Crop Image** - call gallery app to crop your image

## Installation
- add the following repository into your pom.xml

```xml
<repository>
  <id>repo</id>
  <url>https://github.com/d-tarasov/maven-repo/raw/master/releases</url>
</repository>
```

- add dependency

```xml
<dependency>
  <groupId>com.dmitriy.tarasov</groupId>
  <artifactId>android-intents</artifactId>
  <version>1.0.1</version>
</dependency>
```

## Release History

### V1.0.1 (11/08/2013): Bugfixes
- Fixed issue #1: attach sources and javadocs to release builds
- Fixed issue #2: problem in IntentUtils#openLink with urls without protocol

### V1.0.0 (28/02/2013): First release
- Share text intent
- Send SMS intent
- Send email intent
- Play Store intent
- Capture photo intent
- Crop image intents
- Dialer intents
- Browser intents
- Map intents
- Street View intent
- `isIntentAvailable` method for checking thirdparty apps availability
- Demo application

### Future releases
- show video intent
- open settings to enable gps
- open image in gallery
- open text
- pick contact
- pick file
- integration with zxing intent integrator
- add icons to list items
- properly react in demo app on situations when target app isn't available (app mustn't crash)

License
-----
[Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)




[1]: https://raw.github.com/d-tarasov/android-intents/master/logo.png
[2]: https://raw.github.com/d-tarasov/android-intents/master/qr.png