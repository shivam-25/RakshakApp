# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Devipriya\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Add this global rule
-keepattributes Signature

# This rule will properly ProGuard all the model classes in
# the package com.yourcompany.models. Modify to fit the structure
# of your app.
-keepclassmembers class com.teapink.damselindistress {
  *;
}
-keep class io.agora.**{*;}
-keep public class org.slf4j.** { *; }
-keep public class ch.** { *; }
-dontwarn javax.mail.**, javax.naming.Context, javax.naming.InitialContext
-keep class ch.qos.** { *; }
-keep class org.slf4j.** { *; }
-keep class io.protostuff.** { *; }
-keep class com.google.common.** { *; }
-keep,allowshrinking class ch.qos.logback.core.status.ViewStatusMessagesServletBase {
    <fields>;
    <methods>;
}
-dontwarn org.slf4j.**

-dontwarn javax.xml.**

-dontwarn org.apache.**