// ✅ View and Viewgroups 
// The UI  of an app is what you see on the screen: text, images, buttons, and many other types of elements.
// Each of these elements is what's called a 'View'
// For example, you will work with a kind of View that is for displaying text and is called a 'TextView'
// To organize Views, you put them in a container.
// A 'ViewGroup' is a container that View objects can sit in, and is responsible for arranging the Views inside it.
// The arrangement, or layout, can change depending on the size and aspect ratio of the screen of the Android device that the app is running on, and the layout can adapt to whether the device is in portrait or landscape mode.
// One kind of ViewGroup is a 'ConstraintLayout', which helps you arrange the Views inside it in a flexible way.
// Views need to be constrained horizontally and vertically within a ConstraintLayout.
// ✨ Layout Editor: it helps you create the UI for your Android app
// => It supports code, split, design modes. 

// cf. the purpose of the activity_main.xml file in the project you created: 
// => It describes the layout of view groups and views for a screen.

// Note: A constraint in the context of the Layout Editor represents a connection or alignment of a view to another view, the parent layout, or an invisible guideline. 
// For example, a view can be constrained to be a certain distance from the edge of its container, or always be to the right of another view, or always the top view inside a container.

// ✅ Deal with some warnings
// ❓ Hardcoded string: written directly in the code of your app 
// 😡 This make it more difficult to translate your app into other languages and harder to reuse a string in different places in your app.
// 😊 "Extract Resource": define resource name(with '_' and lowercase names)
// cf. app > res > values > strings.xml and AS has created a string resource we made.
// + Note that the name of your app is also a string resource. 
// + TalkBack is the Google screen reader included on Android devices. TalkBack gives you spoken feedback so that you can use your device without looking at the screen.

// ❓ contentDescription: it can help make your app more usable with TalkBack by defining the purpose of the UI element
// 😊 Instead of setting the content description that is announced to the user, you can just tell TalkBack to skip the ImageView by setting its importantForAccessibility attribute to no.

// ✅ Activities
// an 'Activity' takes up the whole screen of your running app.
// The top-level or first activity is often called the 'MainActivity' and is provided by the project template.
// ex. In a photo gallery app, you could have an Activity for displaying a grid of photos, a second Activity for viewing an individual photo, and a third Activity for editing an individual photo. 

// app> java > com.example.diceroller > MainActivity.kt
// 💥 N O T I C E 💥
// 1. Earlier, you learned that every Kotlin program must have a main() function. Android apps operate differently. 
// => Instead of calling a main() function, the Android system calls the 'onCreate()' method of your MainActivity when your app is opened for the first time.
// 2. Android provides a framework of numerous classes to make writing Android apps easier.
// => You can specify which class in the framework to use in your code by using an import statement. 
// => For example, the Button class is defined in android.widget.Button.
// => Fortunately, Android Studio helps you choose the correct imports when you are using
// 3. Android automatically assigns ID numbers to the resources in your app. 
// => For example, the Roll button has a resource ID, and the string for the button text also has a resource ID. Resource IDs are of the form R.<type>.<name>; for example, R.string.roll. For View IDs, the <type> is id, for example, R.id.button.
// 4. When it assigns an object to a variable, Kotlin doesn't copy the entire object each time, it saves a reference to the object.


// ✅ View Binding
// Instead of calling findViewById() for each View in your app, you'll create and initialize a binding object once.
// => Use 'ActivityMainBinding' ✨
// ✔ modify like this.
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
// ✔ Why we use? using view binding is so much more concise that often you won't even need to create a variable to hold the reference for a View, just use it directly from the binding object.
// Old way with findViewById()
val myButton: Button = findViewById(R.id.my_button)
myButton.text = "A button"

// Better way with view binding
val myButton: Button = binding.myButton
myButton.text = "A button"

// Best way with view binding and no extra variable
binding.myButton.text = "A button"
/* N O T E
    The name of the binding class is generated by converting the name of the XML file to camel case and adding the word "Binding" to the end. 
    Similarly, the reference for each view is generated by removing underscores and converting the view name to camel case. 
    For example, activity_main.xml becomes ActivityMainBinding, and you can access @id/text_view as binding.textView.
 */
// ✅ Representing icons in your app
/*
    For icons in your app, instead of providing different versions of a bitmap image for different screen densities, the recommended practice is to use vector drawables. 
    Vector drawables are represented as XML files that store the instructions on how to create an image rather than saving the actual pixels that make up that image. 
    Vector drawables can be scaled up or down without any loss of visual quality or increase in file size.
 */
// ✅ Adaptive and legacy launcher icons
// ✔ you can add icons through 'Image Asset Studio'
// ◽ On devices running Android 8.0 or higher (API version 26 and above):
// => Adaptive icons can be used (combination of foreground vector drawable, background vector drawable, with an OEM mask applied on top of it). 
// => These are the relevant files in your project:
/*
res/drawable-anydpi-v26/ic_lancher_background.xml
res/drawable-anydpi-v26/ic_launcher_foreground.xml
res/mipmap-anydpi-v26/ic_launcher.xml
res/mipmap-anydpi-v26/ic_launcher_round.xml
 */

// ◽ On devices running anything below Android 8.0 (but above the minimum required API level of our app):
// => Legacy launcher icons will be used (the bitmap images in the mipmap folders of different density buckets).
// => These are the relevant files in your project:
/*
res/mipmap-mdpi/ic_launcher.png
res/mipmap-mdpi/ic_launcher_round.png
res/mipmap-hdpi/ic_launcher.png
res/mipmap-hdpi/ic_launcher_round.png
res/mipmap-xhdpi/ic_launcher.png
res/mipmap-xhdpi/ic_launcher_round.png
res/mipmap-xxdpi/ic_launcher.png
res/mipmap-xxdpi/ic_launcher_round.png
res/mipmap-xxxdpi/ic_launcher.png
res/mipmap-xxxdpi/ic_launcher_round.png
 */
// Essentially, Android will fall back to the bitmap images on older devices without adaptive icon support.

// ✅ Support older Android versions(vector drawables)
// To make your app work on these older versions of Android (known as backwards compatibility), add the vectorDrawables element to your app's build.gradle file. 
/* app/build.gradle
android {
  defaultConfig {
    ...
    vectorDrawables.useSupportLibrary = true
   }
   ...
}
*/