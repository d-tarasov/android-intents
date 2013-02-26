#android-intents

![Example Image][1]

A collection of well-known Android intents for most common actions. 
Helps you call thirdparty apps to do generic work instead of you. 
You can call camera app for taking photos, share text, make calls, 
send SMS messages, scan barcodes and QR-codes and more. See methods 
and javadocs of `IntentUtils` class inside of library. Also demo app 
available at [Play Store](http://my_demo_app).

## Intents Collection

At this moment collection includes this intents

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
  <version>0.0.1</version>
</dependency>
```

## Release History

### V0.0.1 (?): First release
- Capture photo intent
- Crop image intents
- Dialer intents
- Browser intents
- `isIntentAvailable` method for checking thirdparty apps availability
- Demo application

### Future releases
- add share intent
- add send sms
- add send email
- add show on map intent
- app chooser dialog
- show video intent
- open market page
- open settings to enable gps
- http://developer.android.com/guide/appendix/g-app-intents.html
- prepare demo and upload to play store
- integration with zxing intent integrator

License
-----
[Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)




[1]: https://raw.github.com/d-tarasov/android-intents/master/logo.png