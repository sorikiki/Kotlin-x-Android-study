// goal: Improve the user interface of your app by learning about layouts, Material Design guidelines, and best practices for UI development.

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

// ✔ enable view binding at first and modify like this.
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
// ✔ why we use? using view binding is so much more concise that often you won't even need to create a variable to hold the reference for a View, just use it directly from the binding object.
// + Null safety, Type safety (?)

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

//  ✔ comparison with data binding
// View binding and data binding both generate binding classes that you can use to reference views directly. 
// However, view binding is intended to handle simpler use cases and provides the following benefits over data binding:
// - Faster compilation: View binding requires no annotation processing, so compile times are faster.
// - Ease of use: View binding does not require specially-tagged XML layout files, so it is faster to adopt in your apps. Once you enable view binding in a module, it applies to all of that module's layouts automatically.
// Conversely, view binding has the following limitations compared to data binding:
// - View binding doesn't support layout variables or layout expressions, so it can't be used to declare dynamic UI content straight from XML layout files.
// - View binding doesn't support two-way data binding.
// =>✨ Because of these considerations, it is best in some cases to use both view binding and data binding in a project. You can use data binding in layouts that require advanced features and use view binding in layouts that do not.

// ✅ Data Binding
// : Once a binding object has been created for your app, you can access the views, and other data, through the binding object, without having to traverse the view hierarchy or search for the data.

// ✔ benefits
// - Code is shorter, easier to read, and easier to maintain than code that uses findViewById().
// - Data and views are clearly separated. This benefit of data binding becomes increasingly important later in this course.
// - The Android system only traverses the view hierarchy once to get each view, and it happens during app startup, not at runtime when the user is interacting with the app.
// - You get type safety for accessing views. (Type safety means that the compiler validates types while compiling, and it throws an error if you try to assign the wrong type to a variable.)

// ✔ how to use it
// 1. Enable data binding (similar to the method how to enable view binding)
// 2. Change layout file to be usable with data binding: you need to wrap your XML layout with a <layout> tag.
// + The namespace declarations for a layout must be in the outermost tag.
// 3. Create a binding object in the main activity


// ✅ RecyclerView
// The greatest benefit of RecyclerView is that it is very efficient for large lists:
// When an item scrolls off the screen, the item's views are recycled. That means the item is filled with new content as it scrolls onto the screen.
// adapter: RecyclerView uses an adapter to transform app data into something the RecyclerView can display, without changing how the app stores and processes the data.

// ✔ To display your data in a RecyclerView, you need the following parts:
// ◽ Data to display.

// ◽ A RecyclerView instance defined in your layout file, to act as the container for the views.

// ◽ A layout for one item of data. If all the list items look the same, you can use the same layout for all of them, but that is not mandatory. 
// - The item layout has to be created separately from the fragment's layout, so that one item view at a time can be created and filled with data.
// - Because this view is displayed inside the RecyclerView, you don't have to place the view inside a ViewGroup.

// ◽ A layout manager. The layout manager handles the organization (the layout) of UI components in a view.
// - Every RecyclerView needs a layout manager that tells it how to position items in the list. 
// - Android provides a LinearLayoutManager, which by default lays out the items in a vertical list of full width rows.

// ◽ A view holder. 
// - The view holder extends the ViewHolder class. It contains the view information for displaying one item from the item's layout. View holders also add information that RecyclerView uses to efficiently move views around the screen.

// ◽ An adapter. The adapter connects your data to the RecyclerView. It adapts the data so that it can be displayed in a ViewHolder. A RecyclerView uses the adapter to figure out how to display the data on the screen.
// - The adapter creates a view holder and fills it with data for the RecyclerView to display.
// - At the top level of Adapter, create a listOf  variable to hold the data.
// - The RecyclerView needs to know how many items the adapter has for it to display, and it does that by calling getItemCount()
// - The onBindViewHolder()function is called by RecyclerView to display the data for one list item at the specified position. So the onBindViewHolder() method takes two arguments: a view holder, and a position of the data to bind. 

// - override and implement onCreateViewHolder(), which is called when the RecyclerView needs a view holder.
// => This function takes two parameters and returns a ViewHolder. The parent parameter, which is the view group that holds the view holder, is always the RecyclerView. The viewType parameter is used when there are multiple views in the same RecyclerView.
// => For example, if you put a list of text views, an image, and a video all in the same RecyclerView, the onCreateViewHolder() function would need to know what type of view to use.
// => In onCreateViewHolder(), create an instance of LayoutInflater.
// => The layout inflater knows how to create views from XML layouts. The context contains information on how to correctly inflate the view. In an adapter for a recycler view, you always pass in the context of the parent view group, which is the RecyclerView.
// ex. val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
// => Pass in the XML layout for the view, and the parent view group for the view. The third, boolean, argument is attachToRoot. This argument needs to be false, because RecyclerView adds this item to the view hierarchy for you when it's time.
// => In onCreateViewHolder(), return a TextItemViewHolder made with view(adapterLayout)
// - The RecyclerView needs to know about the adapter to use to get view holders.

// N O T E : Because these view holders are recycled, make sure onBindViewHolder() sets or resets any customizations that previous items might have set on a view holder.
// => we'll deal with this issue later.
