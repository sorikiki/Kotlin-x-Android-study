// ✔ 'java' folder for Kotlin files (or Java files)
// ✔ 'MainActivity' - class where all of the Kotlin code for the tip calculator logic will go
// ✔ 'res' folder for app resources
// ✔ 'activity_main.xml' - layout file for your Android app
// ✔ 'strings.xml' - contains string resources for your Android app
// Gradle Scripts folder

/*
    Gradle is the automated build system used by Android Studio. 
    Whenever you change code, add a resource, or make other changes to your app, Gradle figures out what has changed and takes the necessary steps to rebuild your app. 
    It also installs your app in the emulator or physical device and controls its execution. 
*/

// ✅ Organize your code into several packages
// : app > java: By default, it shows three packages, one for your code (com.example.affirmations), and two for test files (com.example.affirmations (androidTest) and com.example.affirmations (test)).
// => Notice that the name of the package consists of several words separated by a period!
// => it is a good practice to use packages to group classes by functionality.
// => The package name is usually structured from general to specific, with each part of the name in lowercase letters and separated by a period. 
// => + Important: The period is just part of the name. It does not indicate a hierarchy in code or mandate a folder structure!
// => Developers often use model as the package name for classes that model (or represent) the data. ex) com.example.affirmations.model
