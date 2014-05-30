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
- **Show Location Settings** - opens system settings location services screen
- **Call Phone** - call to phone using standard dialer
- **Dial Phone** - opens standard dialer without starting a call
- **Open Link** - opens a web page in standard browser
- **Open Audio** - opens an audio file in appropriate app
- **Open Video** - opens a video file in appropriate app
- **Open Image** - opens an image file in appropriate app
- **Open Text** - opens a text file in appropriate app
- **Pick File** - opens file manager to choose a file
- **Pick Contact** - pick a contact from phone book
- **Pick Contact With Phone** - pick a contact only from contacts with phone number
- **Pick Image** - pick image from gallery
- **Capture Photo** - call standard camera app for taking a shot
- **Crop Image** - call gallery app to crop your image

## Installation
The library is available on maven central repository, just download jar and add it to your
project classpath or add the following dependency

**Maven**

```xml
<dependency>
    <groupId>com.dmitriy-tarasov</groupId>
    <artifactId>android-intents</artifactId>
    <version>%latest_version%</version>
</dependency>
```

**Gradle**

```xml
compile 'com.dmitriy-tarasov:android-intents:%latest_version%'
```

## Release History

### v1.2.0 (?/?/2013): New features
- Pick image from gallery
- Pick contact with phone

### v1.1.0 (20/08/2013): New features
- Open audio intent
- Open video intent
- Open image intent
- Open text intent
- Pick file intent
- Pick contact intent
- Show location settings intent
- Demo app improvements

### v1.0.1 (11/08/2013): Bugfixes
- Fixed issue #1: attach sources and javadocs to release builds
- Fixed issue #2: problem in IntentUtils#openLink with urls without protocol

### v1.0.0 (28/02/2013): First release
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
- integration with zxing intent integrator
- add icons to list items
- demo app UI on old androids

License
-----
[Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)




[1]: https://raw.github.com/d-tarasov/android-intents/master/logo.png
[2]: https://raw.github.com/d-tarasov/android-intents/master/qr.png