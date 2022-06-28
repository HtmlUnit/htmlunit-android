# HtmlUnit Android package

Version 2.59.0 / March 6, 2022

:heart: [Sponsor](https://github.com/sponsors/rbri)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.sourceforge.htmlunit/htmlunit-android/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.sourceforge.htmlunit/htmlunit-android)

**Homepage**

[htmlunit.sourceforge.io](https://github.com/HtmlUnit/htmlunit-android)

**News**

[HtmlUnit@Twitter](https://twitter.com/HtmlUnit "https://twitter.com/HtmlUnit")

**Download**

For maven, you simply use this dependency instead of the usual htmlunit dependency:

    <dependency>
        <groupId>net.sourceforge.htmlunit</groupId>
        <artifactId>htmlunit-android</artifactId>
        <version>2.59.0</version>
    </dependency>

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


Contributing
--------
Pull Requests and and all other Community Contributions are essential for open source software.
Every contribution - from bug reports to feature requests, typos to full new features - are greatly appreciated.


Latest CI build
--------
Usually snapshot builds are available based on the latest HtmlUnit code.

If you use maven please add:

    <dependency>
        <groupId>net.sourceforge.htmlunit</groupId>
        <artifactId>htmlunit-android</artifactId>
        <version>2.63.0-SNAPSHOT</version>
    </dependency>

You have to add the sonatype snapshot repository to your pom distributionManagement section also:

    <snapshotRepository>
        <id>sonatype-nexus-snapshots</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    </snapshotRepository>


License
--------

This project is licensed under the Apache 2.0 License
