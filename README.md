Project to replicate example "Hello World" application from @xeno-by at Scala days" Berlin (https://youtu.be/IPnd_SZJ1nM?t=1219)

This project specifically targets the new "inline" macros.

**To Run**

Via Sbt:
 - sbt core/run
 
Via IntellIj:
 - Create a run configuration with the following
   - Classpath of Module: "core"
   - Main method: "Main" - Note: This will show an error because IntelliJ cant detect expanded macros yet*
