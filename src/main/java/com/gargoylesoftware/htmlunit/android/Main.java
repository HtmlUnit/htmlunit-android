/*
 * Copyright (c) 2002-2022 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.htmlunit.android;

import java.util.Set;

import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Just a simple main class.
 *
 * @author Ronald Brill
 */
public class Main {
    public static void main(String[] args) {
        final String[] tmpClassNames = { "com.gargoylesoftware.htmlunit.WebClient",
            "net.sourceforge.htmlunit.corejs.javascript.Function",
            "net.sourceforge.htmlunit.cyberneko.HTMLElements",
            "com.gargoylesoftware.css.parser.CSSOMParser",
            "",

            "org.htmlunit.org.apache.http.client.HttpClient",
            "org.htmlunit.org.apache.http.Header",
            "org.htmlunit.org.apache.http.entity.mime.MIME",
            "",

            "org.eclipse.jetty.client.HttpClient",
            "org.eclipse.jetty.http.HttpContent",
            "org.eclipse.jetty.util.ArrayUtil",
            "org.eclipse.jetty.io.AbstractConnection",
            "org.eclipse.jetty.websocket.client.DefaultHttpClientProvider",
            "org.eclipse.jetty.websocket.common.Parser",
            "org.eclipse.jetty.websocket.api.RemoteEndpoint",
            "org.brotli.dec.BrotliRuntimeException", "com.shapesecurity.salvation2.Policy" };
        for (final String tmpClassName : tmpClassNames) {
            StringBuilder info = new StringBuilder();
            if (tmpClassName.length() == 0) {
                System.out.println();
                continue;
            }

            try {
                final Class<?> tmpClass = Class.forName(tmpClassName);
                info.append(tmpClass.getName()).append("  ");
                // @formatter:off
                info.append(VersionUtil.determineVersionFromJarFileName(tmpClass))
                        .append(" (")
                        .append(VersionUtil.determineCreationDateFromJarFileName(tmpClass))
                        .append(')'); // NOPMD
                // @formatter:on
            } catch (final ClassNotFoundException e) {
                info.append("Class '").append(tmpClassName).append("' not found in classpath.");
            }
            System.out.println(info.toString());
        }

//        final String[] tmpJars = { "commons-lang3-\\S+jar", "commons-text-\\S+jar", "commons-codec-\\S+jar",
//            "commons-io-\\S+jar", "commons-logging-\\S+jar", "commons-net-\\S+jar"};
//        for (final String tmpJar : tmpJars) {
//            StringBuilder info = new StringBuilder();
//            // @formatter:off
//            info.append(VersionUtil.determineTitleFromJarManifest(tmpJar, null))
//                  .append(' ')
//                  .append(VersionUtil.determineVersionFromJarManifest(tmpJar, null));
//            // @formatter:on
//            System.out.println(info.toString());
//        }
//
//        System.out.println(org.apache.xmlcommons.Version.getVersion());
//        System.out.println(org.apache.xml.serializer.Version.getVersion());
//        System.out.println(org.apache.xerces.impl.Version.getVersion());
//        System.out.println(org.apache.xalan.Version.getVersion());
//
        System.out.println();
        System.out.println();
        System.out.println("Reading https://www.wetator.org/testform/");
        try (WebClient webClient = new WebClient()) {
            webClient.setCssErrorHandler(new SilentCssErrorHandler());

            HtmlPage page = webClient.getPage("https://www.wetator.org/testform/");
            System.out.println(page.asNormalizedText());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
