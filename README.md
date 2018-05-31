# Five_ways_to_modernize

Instructions:
 - Webinar-jlink is a folder that contains a simple example of how to create a custom runtime image using jlink. A custom runtime image is a self-contained distribution that contains an app and the entire JVM - no need to depend upon a pre-installed JVM. The project is built from a linux shell by running build.sh and the app is run via run.sh. Make sure you have JDK 9 installed.

 - webinar is a standalone project that can be imported in Eclipse or Intellij and run from there. Just make sure you set the language level to Java 9.

 - webinar-modular-framework-client and webinar-modular-framework are two JPMS modules that can be imported to Eclipse or Intellij. In Eclipse, modules are represented as distinct projects that can be bundled in the same workspace. webinar-modular-framework is rhe root module and depended upon by webinar-modular-framework-client.
