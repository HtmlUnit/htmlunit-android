/*
 * Copyright (c) 2008-2021 wetator.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.gargoylesoftware.htmlunit.android;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * VersionUtil contains some useful extensions to read version info
 * from various sources.
 *
 * @author rbri
 * @author frank.danek
 */
public final class VersionUtil {
  private static final String BUNDLE_NAME = "Bundle-Name";
  private static final String BUNDLE_VERSION = "Bundle-Version";
  private static final String DEFAULT = "unknown";

  /**
   * Returns the name of the jar file the class is loaded from.
   *
   * @param aClass a class that is known to be loaded form the jar
   *        in question
   * @return the name of the jar file or "unknown"
   */
  public static String determineVersionFromJarFileName(final Class<?> aClass) {
    String tmpPath = aClass.getProtectionDomain().getCodeSource().getLocation().getPath();
    if (StringUtils.isNotBlank(tmpPath) && tmpPath.lastIndexOf('/') > -1) {
      tmpPath = tmpPath.substring(tmpPath.lastIndexOf('/') + 1);
      return tmpPath;
    }
    return DEFAULT;
  }

  /**
   * Returns the name of the jar file the class is loaded from.
   *
   * @param aClass a class that is known to be loaded form the jar
   *        in question
   * @return the name of the jar file or "unknown"
   */
  public static String determineCreationDateFromJarFileName(final Class<?> aClass) {
    final String tmpPath = aClass.getProtectionDomain().getCodeSource().getLocation().getPath();
    String tmpClassFile = aClass.getName();
    tmpClassFile = tmpClassFile.replace('.', '/');
    tmpClassFile = tmpClassFile + ".class"; // NOPMD
    try {
      try (JarFile tmpJar = new JarFile(tmpPath)) {
        final JarEntry tmpJarEntry = tmpJar.getJarEntry(tmpClassFile);
        final Date tmpDate = new Date(tmpJarEntry.getTime());

        return new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT).format(tmpDate);
      }
    } catch (final Throwable e) { // NOPMD
      // ignore
    }
    return DEFAULT;
  }

  /**
   * Returns the version of the jar file the class is loaded from.
   *
   * @param aJarFilePattern the regex pattern of the jar file
   * @param aPackage the name of the package or null
   * @return the name of the jar file or "unknown"
   */
  public static String determineVersionFromJarManifest(final String aJarFilePattern, final String aPackage) {
    return readAttributeFromJarManifest(aJarFilePattern, aPackage, Attributes.Name.IMPLEMENTATION_VERSION.toString(),
        DEFAULT);
  }

  /**
   * Returns the title of the jar file the class is loaded from.
   *
   * @param aJarFilePattern the regex pattern of the jar file
   * @param aPackage the name of the package or null
   * @return the name of the jar file or "unknown"
   */
  public static String determineTitleFromJarManifest(final String aJarFilePattern, final String aPackage) {
    return readAttributeFromJarManifest(aJarFilePattern, aPackage, Attributes.Name.IMPLEMENTATION_TITLE.toString(),
        DEFAULT);
  }

  /**
   * Returns the title of the jar file the class is loaded from.
   *
   * @param aJarFilePattern the regex pattern of the jar file
   * @param aPackage the name of the package or null
   * @return the name of the jar file or "unknown"
   */
  public static String determineBundleNameFromJarManifest(final String aJarFilePattern, final String aPackage) {
    return readAttributeFromJarManifest(aJarFilePattern, aPackage, BUNDLE_NAME, DEFAULT);
  }

  /**
   * Returns the version of the jar file the class is loaded from.
   *
   * @param aJarFilePattern the regex pattern of the jar file
   * @param aPackage the name of the package or null
   * @return the name of the jar file or "unknown"
   */
  public static String determineBundleVersionFromJarManifest(final String aJarFilePattern, final String aPackage) {
    return readAttributeFromJarManifest(aJarFilePattern, aPackage, BUNDLE_VERSION, DEFAULT);
  }

  /**
   * Returns the attribute from the manifest of the jar file or the default
   * if not found.
   *
   * @param aJarFilePattern the regex pattern of the jar file
   * @param aPackage the name of the package or null
   * @param anAttributeName the name of the attribute to look for
   * @param aDefault the return value if not found
   * @return the name of the jar file or the given default
   */
  public static String readAttributeFromJarManifest(final String aJarFilePattern, final String aPackage,
      final String anAttributeName, final String aDefault) {
    try {
      final Enumeration<URL> tmpResources = VersionUtil.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
      final Pattern tmpPattern = Pattern.compile(aJarFilePattern);
      while (tmpResources.hasMoreElements()) {
        final URL tmpUrl = tmpResources.nextElement();
        final String tmpLcUrl = tmpUrl.toExternalForm().toLowerCase(Locale.ROOT);

        final Matcher tmpMatcher = tmpPattern.matcher(tmpLcUrl);
        if (tmpMatcher.find()) {
          try (InputStream tmpStream = tmpUrl.openStream()) {
            final Manifest tmpManifest = new Manifest(tmpStream);

            final Attributes tmpAttributes;
            if (null == aPackage) {
              tmpAttributes = tmpManifest.getMainAttributes();
            } else {
              tmpAttributes = tmpManifest.getAttributes(aPackage);
            }

            if (null != tmpAttributes) {
              final String tmpAttribute = tmpAttributes.getValue(anAttributeName);
              if (StringUtils.isNotBlank(tmpAttribute)) {
                return tmpAttribute;
              }
            }
          }
        }
      }
    } catch (final Throwable e) {
      e.printStackTrace(); // NOPMD
      // fallback to default
    }
    return aDefault;
  }

  /**
   * Private constructor to be invisible.
   */
  private VersionUtil() {
    super();
  }
}
