![codecov](https://codecov.io/gh/DavidDudson/Elysium/branch/master/graph/badge.svg?bloop) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/696434ec2419482dbd3c95b29c28366f)](https://www.codacy.com/app/davidjohndudson/Elysium?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=DavidDudson/InlineMacros&amp;utm_campaign=Badge_Grade) 

Linux/Mac: ![Build Status](https://travis-ci.org/DavidDudson/Elysium.svg?branch=master) 

Windows: [![Build status](https://ci.appveyor.com/api/projects/status/055tsw3coigqixuj?svg=true)](https://ci.appveyor.com/project/DavidDudson/inlinemacros)

## Elysium

[![Join the chat at https://gitter.im/MetaElysium/Lobby](https://badges.gitter.im/MetaElysium/Lobby.svg)](https://gitter.im/MetaElysium/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

** Temperarily on hold due to https://github.com/scalameta/paradise/issues/37 which involves some extra compiler hijacking**

**Incredibly WIP/Experimental, everything is in a state of *flux* until 
scalameta/paradise hits 3.0.0. api's can change/dissapear at a moments notice**

"Elysium - any place or state of perfect happiness; paradise."

Elysium has 3 goals.

1. Make macro creation as simple as possible
2. Teach people how to write macros
3. Have a huge number of useful macros on hand for people to use

### Some useful things

- In core, Renamed and Decorator have examples of using arguments in macro
- In misc, there is a simple 


### Modules

I have also detailed what kind of dependency is required. Mixed means can be either depending on how you use it

- [Runtime] all - Aggregation of all projects
- [Compile] core - The main things people will want when creating their own macros or using this library
- [Runtime] debug - Macros for debugging (printing object state etc)
- [Runtime] profile - Macros for profiling (accumulating statistics etc)
- [Compile] log - Macros for generating loggers (will possibly use slf4j)
- [Compile] gen - Macro/Inline generation tooling. This would be in core but has to be compiled seperately
- [Mixed] manipulate - impicit classes to manipulate meta trees, The semantic api may replace some of this.
- [Mixed] validate - things for improving the validation and error messages for macros
- [Compile] misc - generic stuff, more for teaching purposes then anything else, there's usually a better way


### Things delaying this project

Ordered by importance will tick when they are complete in scala meta.

Note: Im mainly refraining from working only the macro heavy parts of 
the project at the moment because once I can parse/generate inline macros 
I will be rewriting all existing macros.

- [x] Argument Macros
- [ ] Inline Generation (Macros generating macros)
- [ ] Macro Testing framework
- [ ] Semantic API
- [x] Multiple Annotations
- [ ] Def Macros

### Navigating

Every module has an 'module_tests project'.

This is because we cannot test usages of the annotations in all cases 
without 3 compilation stages (including test:compile)

So to make a convention and stick with it...

- a/src/main - Macro code etc.
- a/src/test - Tests of macro compilation and structure output (eventually)
- a_test/src/main - Objects that are required in the test code that need to be expanded (eg things annotated with macros)
- a_test/src/test - General use case testing of macros.
