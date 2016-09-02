![codecov](https://codecov.io/gh/DavidDudson/InlineMacros/branch/master/graph/badge.svg?bloop) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/696434ec2419482dbd3c95b29c28366f)](https://www.codacy.com/app/davidjohndudson/InlineMacros?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=DavidDudson/InlineMacros&amp;utm_campaign=Badge_Grade) 

Linux/Mac: ![Build Status](https://travis-ci.org/DavidDudson/InlineMacros.svg?branch=master) 

Windows: [![Build status](https://ci.appveyor.com/api/projects/status/055tsw3coigqixuj?svg=true)](https://ci.appveyor.com/project/DavidDudson/inlinemacros)

**Inline Macros**

This is a fully experimental library to show the usefulness of macros/meta-programming in scala.

There seems to be an apprehension towards macros in the scala community.

This should not be the case, with inline macros the learning curve is much much lower.

The goal is to eventually publish this as a library (Just the "macros" section of this project).

I want this library to show lots of possible uses for macros and meta-programming without limitations.

**Things delaying this project**

Ordered by importance will tick when they are complete in scala meta

- [ ] Argument Macros
- [ ] Inline Generation (Macros generating macros)
- [ ] Macro Testing framework
- [ ] Semantic API
- [ ] Multiple Annotations
- [ ] Def Macros

**Navigating**

 - Macros -> macros/src/main
 - Macro Tests -> src/tests
 - Usages/Experiments -> src/main (I will mostly use src/tests)
 - Documentation/Tutorials -> docs/
