![codecov](https://codecov.io/gh/DavidDudson/InlineMacros/branch/master/graph/badge.svg?bloop) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/696434ec2419482dbd3c95b29c28366f)](https://www.codacy.com/app/davidjohndudson/InlineMacros?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=DavidDudson/InlineMacros&amp;utm_campaign=Badge_Grade) 

Linux/Mac: ![Build Status](https://travis-ci.org/DavidDudson/InlineMacros.svg?branch=master) 

Windows: [![Build status](https://ci.appveyor.com/api/projects/status/055tsw3coigqixuj?svg=true)](https://ci.appveyor.com/project/DavidDudson/inlinemacros)

## Elysium

**Incredibly WIP/Experimental, everything is in a state of *flux* until 
scalameta/paradise hits 3.0.0. api's can change/dissapear at a moments notice**

"Elysium - any place or state of perfect happiness; paradise."

Elysium has 3 goals.

1. Make macro creation as simple as possible
2. Teach people how to write macros
3. Have a huge number of useful macros on hand for people to use

### Modules

- all - Aggregation of all projects
- core - The main things people will want when creating their own macros or using this library
- debug - Macros for debugging/logging/profiling (may split these if it gets too big)
- gen - Macro/Inline generation tooling. This would be in core but has to be compiled seperately
- manipulate - impicit classes to manipulate meta trees, The semantic api may replace some of this.
- misc - generic stuff, more for teaching purposes then anything else, there's usually a better way


### Things delaying this project

Ordered by importance will tick when they are complete in scala meta

- [ ] Argument Macros
- [ ] Inline Generation (Macros generating macros)
- [ ] Macro Testing framework
- [ ] Semantic API
- [ ] Multiple Annotations
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
