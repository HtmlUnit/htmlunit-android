# HtmlUnit Android package

Version 2.66.0 / October 23, 2022

:heart: [Sponsor](https://github.com/sponsors/rbri)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.sourceforge.htmlunit/htmlunit-android/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.sourceforge.htmlunit/htmlunit-android)

**Homepage**

[htmlunit.sourceforge.io](https://github.com/HtmlUnit/htmlunit-android)

**News**

[HtmlUnit@Twitter](https://twitter.com/HtmlUnit "https://twitter.com/HtmlUnit")

## Get it!

### Maven

Add to your `pom.xml`:

```xml
<dependency>
    <groupId>net.sourceforge.htmlunit</groupId>
    <artifactId>htmlunit-android</artifactId>
    <version>2.66.0</version>
</dependency>
```

### Gradle

Add to your `build.gradle`:

```groovy
implementation group: 'net.sourceforge.htmlunit', name: 'htmlunit-android', version: '2.66.0'
```

Overview
--------
HtmlUnit is a "GUI-Less browser for Java programs" usually runs on every platform supported by java.

But Android is a bit different because the Android SDK already includes some (old) classes from  the Apache HttpClient.
HtmlUnit uses a more recent version HttpClient; and this is where the problem begins.

To solve the conflicts, this package uses [shading](https://maven.apache.org/plugins/maven-shade-plugin/) and
includes the HttpClient in the jar file itself but with a modified package name. There are no changes done
to the implementation.

Or to be more detailed, this packages includes
* HtmlUnit itself
* htmlunit-core-js
* htmlunit-cssparser

* neko-htmlunit
* xml-apis/xml-apis:jar
* xerces/xercesImpl

* httpcomponents/httpmime:jar (shaded)
* httpcomponents/httpclient (shaded)
* httpcomponents/httpcore (shaded)
* commons-codec/commons-codec (shaded)

All the other dependencies are still dependencies of this package (see the pom.xml for more).

**Android 7 (Nougat)**

Due to the lack of support for ThreadLocal#withInitial, the connons-io version 2.10 used by HtmlUnit does not work.
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
      <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
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
          <artifactId>htmlunit-android</artifactId>
          <version>2.67.0-SNAPSHOT</version>
      </dependency>
      <!-- ... -->
    </dependencies>

    <!-- ... -->
```
  
### Gradle

Add the snapshot repository and dependency to your `build.gradle`:

```groovy
repositories {
  maven { url "https://oss.sonatype.org/content/repositories/snapshots" }
  // ...
}
// ...
dependencies {
    implementation group: 'net.sourceforge.htmlunit', name: 'htmlunit-android', version: '2.67.0-SNAPSHOT'
  // ...
}
```

License
--------

This project is licensed under the Apache 2.0 License
