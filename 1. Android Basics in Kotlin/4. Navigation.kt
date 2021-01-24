// ✅ Intents
// An intent is an object representing some action to be performed. The most common, but certainly not only, use for an intent is to launch an activity.
// There are two types of intents—implicit and explicit.
// They both describes the request, not the actual result.
// explicit: specific, you know the exact activity that to be launched, often a screen in your own app.
// implicit: abstract, for actions that don't necessarily involve the current app, often for presenting activities from another app, or system feature.

// ✅ Set an explicit intent
// LettterAdapter.kt
override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item = list.get(position)
        val context = holder.view.context
        val intent = Intent(context, DetailActivity::class.java)
        holder.button.text = item.toString()
        holder.button.setOnClickListener {
            intent.putExtra("letter", holder.button.text.toString())
            context.startActivity(intent)
        }
    }
// The name of the activity you want to show is specified with DetailActivity::class.java. An actual DetailActivity object is created behind the scenes.
// What's an extra? Remember that an intent is simply a set of instructions—there's no instance of the destination activity just yet. Instead, an extra is a piece of data, such as a number or string, that is given a name to be retrieved later. This is similar to passing an argument when you call a function.
// Also, why do you think it's necessary to call toString()? The button's text is already a string, right?
// => Sort of. It's actually of type CharSequence, which is something called an interface.
// => it's a way to ensure a type, such as String, implements specific functions and properties. You can think of a CharSequence as a more generic representation of a string-like class.
// => A button's text property could be a string, or it could be any object that is also a CharSequence.
// => The putExtra() method, however, accepts a String, not just any CharSequence, hence the need to call toString().

// DetailActivity.kt 
val letterId = intent?.extras?.getString("letter").toString()
// First, where does the intent property come from? It's not a property of DetailActivity, but rather, a property of any activity.
// The extras property is of type Bundle, and as you might have guessed, provides a way to access all extras passed into the intent.
/* About 'Null Safety'
    Both of these properties are marked with a question mark. Why is this? The reason is that the intent and extras properties are nullable, meaning they may or may not have a value. 
    Sometimes you may want a variable to be null. 
    The intent property might not actually be an Intent (if the activity wasn't launched from an intent) and the extras property might not actually be a Bundle, but rather a value called null. 
    In Kotlin, null means the absence of a value. 
    The object may exist or it may be null. 
    If your app tries to access a property or call a function on a null object, the app will crash.
    To safely access this value, you put a ? after the name. 
    If intent is null, your app won't even attempt to access the extras property, and if extras is null, your code won't even attempt to call getString(). 
    How do you know which properties require a question mark to ensure null safety? You can tell if the type name is followed by either a question mark or exclamation point.
    The final thing to note is the actual letter is retrieved with getString, which returns a String?, so toString() is called to ensure it's a String, and not null.
*/

// ✔ Let's clean code.
// Remember that the string 'letter' is used in both DetailActivity and MainActivity. You need a way to define a constant that can be used across multiple classes, while keeping your code organized. ✨
// Thankfully, there's a handy Kotlin feature that can be used to separate your constants and make them usable without a particular instance of the class called companion objects. 
// A companion object is similar to other objects, such as instances of a class. However, only a single instance of a companion object will exist for the duration of your program, which is why this is sometimes called the singleton pattern. 
// DetailActivity.kt
companion object {
    const val LETTER = "letter"
}
val letterId = intent?.extras?.getString(LETTER).toString()
// Notice how this is similar to defining a class, except you use the object keyword. There's also a keyword companion, meaning it's associated with the DetailActivity class, and we don't need to give it a separate type name.

// LetterAdapter.kt
holder.button.setOnClickListener {
            intent.putExtra(DetailActivity.LETTER, holder.button.text.toString())
            context.startActivity(intent)
        }

// ✅ Set an implicit intent
// DetailActivity.kt
companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }
// WordAdapter.kt
 override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val item = filteredWords[position]
        // Needed to call startActivity
        val context = holder.view.context
        val queryUrl: Uri = Uri.parse("${DetailActivity.SEARCH_PREFIX}${item}")
        val intent = Intent(Intent.ACTION_VIEW, queryUrl)
        holder.button.setOnClickListener {
            context.startActivity(intent)
        }
        // Set the text of the WordViewHolder
        holder.button.text = item

    }
// If you're wondering what a URI is, it's not a typo, but stands for Uniform Resource Identifier. You may already know that a URL, or Uniform Resource Locator, is a string that points to a webpage.
// A URI is a more general term for the format. All URLs are URIs, but not all URIs are URLs. Other URIs, for example, an address for a phone number, would begin with tel:, but this is considered a URN or Uniform Resource Name, rather than a URL. The data type used to represent both is called URI.
// ACTION_VIEW is a generic intent that takes a URI, in your case, a web address. The system then knows to process this intent by opening the URI in the user's web browser.
/* 
    Some other intent types include:

    CATEGORY_APP_MAPS – launching the maps app
    CATEGORY_APP_EMAIL – launching the email app
    CATEGORY_APP_GALLERY – launching the gallery (photos) app
    ACTION_SET_ALARM – setting an alarm in the background
    ACTION_DIAL – initiating a phone call
*/

// ✅ Activity Lifecycle
// : The activity lifecycle is the set of states an activity can be in during its lifetime. 
// => The lifecycle extends from when the activity is initially created to when it is destroyed and the system reclaims that activity's resources. 
// => As a user navigates between activities in your app (and into and out of your app), those activities each transition between different states in the activity lifecycle.

// the Activity class itself, and any subclasses of Activity such as AppCompatActivity, implement a set of lifecycle callback methods. 
// Android invokes these callbacks when the activity moves from one state to another, and you can override those methods in your own activities to perform tasks in response to those lifecycle state changes.

// cf. The Log class writes messages to the Logcat. The Logcat is the console for logging messages.
//     + the Log.d() method writes a debug message. Other methods in the Log class include Log.i() for informational messages, Log.e() for errors, Log.w() for warnings, or Log.v() for verbose messages.
// ex. Log.d("MainActivity", "onCreate Called")  
//     + The log tag (the first parameter), in this case "MainActivity". The tag is a string that lets you more easily find your log messages in the Logcat. The tag is typically the name of the class.
//     + The actual log message (the second parameter), is a short string, which in this case is "onCreate called".
// Note: A good convention is to declare a TAG constant in your class:
/*  'A compile-time constant' is a value that won't change. Use const before a variable declaration to mark it as a compile-time constant.
    const val TAG = "MainActivity"

    and use that in subsequent calls to the log methods, like below:

    Log.d(TAG, "onCreate Called")
*/

// ◽ onCreate()
// : The onCreate() method is where you should do any one-time initializations for your activity. 
// For example, in onCreate() you inflate the layout, define click listeners, or set up view binding.
// After onCreate() executes, the activity is considered created.
// you must call the superclass implementation to complete the creation of the Activity, so within it, you must immediately call super.onCreate(). The same is true for other lifecycle callback methods.

// ◽ onStart()
// : The onStart() lifecycle method is called just after onCreate(). 
// After onStart() runs, your activity is visible on the screen. 
// Unlike onCreate(), which is called only once to initialize your activity, onStart() can be called many times in the lifecycle of your activity.
// Note that onStart() is paired with a corresponding onStop() lifecycle method. If the user starts your app and then returns to the device's home screen, the activity is stopped and is no longer visible on screen.

// When an activity starts from scratch, you see all three of these lifecycle callbacks called in order:
// - onCreate() to create the app.
// - onStart() to start it and make it visible on the screen.
// - onResume() to give the activity focus and make it ready for the user to interact with it.
// cf. Despite the name, the onResume() method is called at startup, even if there is nothing to resume.

// When onPause() is called, the app no longer has focus. 
// After onStop(), the app is no longer visible on screen. 
// Although the activity has been stopped, the Activity object is still in memory, in the background. The activity has not been destroyed. 
// The user might return to the app, so Android keeps your activity resources around.

// ✅ Configuration change
// :it happens when the state of the device changes so radically that the easiest way for the system to resolve the change is to completely shut down and rebuild the activity.
// ex. if the user changes the device language, the whole layout might need to change to accommodate different text directions and string lengths. If the user plugs the device into a dock or adds a physical keyboard, the app layout may need to take advantage of a different display size or layout. 
//     And if the device orientation changes—if the device is rotated from portrait to landscape or back the other way—the layout may need to change to fit the new orientation. 

// Notice that when the device or emulator rotates the screen, the system calls all the lifecycle callbacks to shut down the activity. Then, as the activity is re-created, the system calls all the lifecycle callbacks to start the activity.
// When the device is rotated and the activity is shut down and re-created, the activity starts up with default values—the number of desserts sold and the revenue have reset to zeroes.🤔

// ✅ Use onSaveInstanceState() to save bundle data
// : The onSaveInstanceState() method is a callback you use to save any data that you might need if the Activity is destroyed.
// In the lifecycle callback diagram, onSaveInstanceState() is called after the activity has been stopped.
override fun onSaveInstanceState(outState: Bundle) {
   super.onSaveInstanceState(outState)

   Log.d(TAG, "onSaveInstanceState Called")
   outState.putInt(KEY_REVENUE, revenue)
   outState.putInt(KEY_DESSERT_SOLD, dessertsSold)
}
// A Bundle is a collection of key-value pairs, where the keys are always strings. You can put simple data, such as Int and Boolean values, into the bundle.
// The size of this bundle is also limited, though the size varies from device to device. If you store too much data, you risk crashing your app with the TransactionTooLargeException error. 
// In onSaveInstanceState(), put the revenue value (an integer) into the bundle with the putInt() method:

// The Activity state can be restored in onCreate(Bundle) or onRestoreInstanceState(Bundle) (the Bundle populated by onSaveInstanceState() method will be passed to both lifecycle callback methods).
override fun onCreate(savedInstanceState: Bundle) {
// Notice that onCreate() gets a Bundle each time it is called. When your activity is restarted due to a process shut down, the bundle that you saved is passed to onCreate(). If your activity was starting fresh, this Bundle in onCreate() is null. So if the bundle is not null, you know you're "re-creating" the activity from a previously known point.
/*
    Note: If the activity is being re-created, the onRestoreInstanceState() callback is called after onStart(), also with the bundle. Most of the time, you restore the activity state in onCreate(). But because onRestoreInstanceState() is called after onStart(), if you ever need to restore some state after onCreate() is called, you can use onRestoreInstanceState().
 */
if (savedInstanceState != null) {
   revenue = savedInstanceState.getInt(KEY_REVENUE, 0)
}
// about 'getInt()' method
// first parameter: A string that acts as the key, for example "key_revenue" for the revenue value.
// second parameter: A default value in case no value exists for that key in the bundle.