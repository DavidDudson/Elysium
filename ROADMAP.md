**Decorating**
- @Decorate
- @DecorateGetters
- @DecorateSetters
- @DecorateFields
- @DecorateMethods

**Debugging**
- @Spy - Keeps track of updates to an object
- @SpyPrint - Prints updates to a class/object
- @Mock - Returns a constant rather then running the function for debugging purposes
- @Hide - Unit methods only, if the flag is true, method will be replace with noop.
- @Time - Prints the execution time of a method
- @Profile - Accumulate statistics about methods that can then be accessed and printed
- @Debug - Only execute the method during debug sessions
- @Benchmark - Generate a benchmark method that will run this method 1 million times
- @PrintTree - Prints the scalameta AST 

**Case class**
- @Case - Equivalent to "case class"
- @Equals - Generate equals and hashcode
- @ToString - Generate toString
- @PrettyPrint - Generate a toString with PPrint support

**Java Compat**
- @Of - Create an X.of method for ever X.apply method (Guava syntax)
- @GenJava - generate an identical method that returns a java collection rather then scala

**SanityChecks**
- @Nonnull - add assertions for non-null variables