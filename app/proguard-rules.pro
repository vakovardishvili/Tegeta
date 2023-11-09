# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response
# Keep Dagger Hilt generated code
-keep class * extends dagger.hilt.android.internal.builders.ViewModelComponentBuilder
-keep class * extends dagger.hilt.android.internal.builders.ActivityComponentBuilder
-keep class * extends dagger.hilt.android.internal.builders.FragmentComponentBuilder
-keep class * extends dagger.hilt.android.internal.builders.ServiceComponentBuilder
# R8 full mode strips generic signatures from return types if not kept.
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# OkHttp
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }

# Okio
-dontwarn okio.**
-keep class okio.** { *; }

# GSON
-keep class com.google.gson.** { *; }

# Depending on the converter you are using (e.g., GsonConverterFactory), you may need to add additional rules.

# Add any other rules needed for your specific use case.
