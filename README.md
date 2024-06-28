# HtmlUnit Android package

Version 4.3.0 / June 28, 2024

:heart: [Sponsor](https://github.com/sponsors/rbri)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.htmlunit/htmlunit3-android/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.htmlunit/htmlunit3-android)

**Homepage**

[https://github.com/HtmlUnit/htmlunit-android](https://github.com/HtmlUnit/htmlunit-android)

**News**

[HtmlUnit@Twitter](https://twitter.com/HtmlUnit "https://twitter.com/HtmlUnit")

## Get it!

### Maven

Add to your `pom.xml`:

```xml
<dependency>
    <groupId>org.htmlunit</groupId>
    <artifactId>htmlunit3-android</artifactId>
    <version>4.4.0</version>
</dependency>
```

### Gradle

Add to your `build.gradle`:

```groovy
implementation group: 'org.htmlunit', name: 'htmlunit3-android', version: '4.3.0'
```

Overview
--------
HtmlUnit is a "GUI-Less browser for Java programs" usually runs on every platform supported by java.

But Android is a bit different because the Android SDK already includes some (old) classes from  the Apache HttpClient.
HtmlUnit uses a more recent version HttpClient; and this is where the problem begins.

To solve the conflicts, this package uses [shading](https://maven.apache.org/plugins/maven-shade-plugin/) and
includes the HttpClient and apache commons-codec in the jar file itself but with a modified package name.
There are no changes done to the implementation.

Or to be more detailed, this packages includes
* HtmlUnit itself (org.htmlunit, netscape.javascript)
* htmlunit-core-js (org.htmlunit.corejs)
* htmlunit-csp (org.htmlunit.csp)
* htmlunit-cssparser (org.htmlunit.cssparser)
* htmlunit-xpath (org.htmlunit.xpath)
* htmlunit-websocket-client (org.htmlunit.websocket)
* neko-htmlunit (org.htmlunit.cyberneko)

* httpcomponents/httpmime:jar (shaded org.apache.http -> org.htmlunit.org.apache.http)
* httpcomponents/httpclient (shaded org.apache.http -> org.htmlunit.org.apache.http)
* httpcomponents/httpcore (shaded org.apache.http -> org.htmlunit.org.apache.http)

* commons-codec/commons-codec (shaded org.apache.commons.codec -> org.htmlunit.org.apache.commons.codec)

All the other dependencies are still dependencies of this package (see the pom.xml for more).

**Android 7 (Nougat)**

Due to the lack of support for ThreadLocal#withInitial, the connons-io version 2.16.1 used by HtmlUnit does not work.
Therefore, you need to override the dependency and use commons-io 2.5 instead.

Contributing
--------
Pull Requests and and all other Community Contributions are essential for open source software.
Every contribution - from bug reports to feature requests, typos to full new features - are greatly appreciated.


Last CI build
--------
Usually snapshot builds are available based on the latest HtmlUnit code.

Read on if you want to try the latest bleeding-edge snapshot.

### Maven

Add the snapshot repository and dependency to your `pom.xml`: 

```xml
    <!-- ... --> 
    <repository>
      <id>OSS Sonatype snapshots</id>
      <url>https://s01.oss.sonatype.org/content/repositories/snapshots/</url>
      <snapshots>
        <enabled>true</enabled>
        <updatePolicy>always</updatePolicy>
      </snapshots>
      <releases>
        <enabled>false</enabled>
      </releases>
    </repository>

    <!-- ... -->
    <dependencies>
      <dependency>
          <groupId>net.sourceforge.htmlunit</groupId>
          <artifactId>htmlunit3-android</artifactId>
          <version>4.4.0-SNAPSHOT</version>
      </dependency>
      <!-- ... -->
    </dependencies>

    <!-- ... -->
```
  
### Gradle

Add the snapshot repository and dependency to your `build.gradle`:

```groovy
repositories {
  maven { url "https://s01.oss.sonatype.org/content/repositories/snapshots" }
  // ...
}
// ...
dependencies {
    implementation group: 'org.htmlunit', name: 'htmlunit3-android', version: '3.8.0-SNAPSHOT'
  // ...
}
```

License
--------

This project is licensed under the Apache 2.0 License
