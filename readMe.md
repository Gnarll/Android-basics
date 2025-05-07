Project features

- WindowSizeClass
- PermanentNavigationDrawer (permanent drawer)
- NavigationRail (concise drawer)
- NavigationBar (bottom nav bar)
- Adaptive layout for different window sizes
- Assertion on tree elements' children
- Annotation classes for tests with different screen sizes

Guide for IDE setting up tests grouped by class annotations:
create annotation class -> provide test functions with it ->
Run (IDE) -> Edit Configuration -> create Android Instrumented tests -> choose name and module ->
set up a variable (in instrumentation arguments) with name "annotation"
and value "com.example.androidbasics.TestMediumWidth",
where com.example.androidbasics is a path (from androidTest/java dir) to file with annotation class,
and the TestMediumWidth is an annotation class itself -> run tests with an appropriate device