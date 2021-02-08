// ✅ Store data in Viewmodel: one of the recommended Android Architecture Components.
// You could resolve 'data lost' issue using the onSaveInstanceState() callback. 
// => However, using the onSaveInstanceState() method requires you to write extra code to save the state in a bundle, and to implement logic to retrieve that state. 
// => Also, the amount of data that can be stored is minimal.
// ✨ You can resolve these issues using the Android Architecture components that you learn about in this pathway.

// 🙌 Learn about App architecture
// The most common architectural principles are: separation of concerns and driving UI from a model.
// 1. separation of concerns: the app should be divided into classes, each with separate responsibilities.
// 2. driving UI from model: Models are components that are responsible for handling the data for an app. They're independent from the Views and app components in your app, so they're unaffected by the app's lifecycle and the associated concerns.
// => Instead, the decision-making logic about the data should be added in your ViewModel.

// cf. The main classes or components in Android Architecture are UI Controller (activity/fragment), ViewModel, LiveData and Room.
// => You'll learn LiveData and Room later.
// => In your Unscramble app, the decision-making code such as figuring out the next scrambled word, and calculations of score and word count should be in your ViewModel.

// summary: UI Controller displays data and capture user events, and ViewModel holds all data needed for the UI and prepares it for display.
// our app structure: MainActivity.kt -> GameFragment.kt -> GameViewModel.kt

// 🤔 Property delegation
// Property delegation in Kotlin helps you to handoff the getter-setter responsibility to a different class.
// => This class (called delegate class) provides getter and setter functions of the property and handles its changes.
// Syntax for property delegation
var <property-name> : <property-type> by <delegate-class>()

// Below, the app will lose the state of the viewModel reference when the device goes through a configuration change. 
private val viewModel = GameViewModel()

// Instead, use the property delegate approach and delegate the responsibility of the viewModel object to a separate class called viewModels. 
// The delegate class creates the viewModel object for you on the first access, and retains its value through configuration changes and returns the value when requested.
private val viewModel: GameViewModel by viewModels()

// 🤔 Backing property
// issue: Inside the ViewModel, the data should be editable, so they should be private and var. From outside the ViewModel, data should be readable, but not editable, so the data should be exposed as public and val.
// => To achieve this behavior, Kotlin has a feature called a backing property.

// for every property, the Kotlin framework generates getters and setters.
// For getter and setter methods, you could override one or both of these methods and provide your own custom behavior. 
// Declare private mutable variable that can only be modified
// within the class it is declared.
private var _count = 0 

// Declare another public immutable field and override its getter method. 
// Return the private property's value in the getter method.
// When count is accessed, the get() function is called and
// the value of _count is returned. 
// Since only the get() method is being overridden, this property is immutable and read-only. When an outside class accesses this property, it returns the value of _count and its value can't be modified. 
val count: Int
   get() = _count

// summary: Never expose mutable data fields from your ViewModel—make sure this data can't be modified from another class. Mutable data inside the ViewModel should always be private.

// 🤔 The Lifecycle of ViewModel
// The framework keeps the ViewModel alive as long as the scope of the activity or fragment is alive. 
// A ViewModel is not destroyed if its owner is destroyed for a configuration change, such as screen rotation. 
// The new instance of the owner reconnects to the existing ViewModel instance, as illustrated by the following diagram:

// 🤔 Array
// An Array is similar to List, but it has a fixed size when it's initialized. 
// An Array cannot expand or shrink its size (you need to copy an array to resize it) whereas a List has add() and remove() functions, so that it can increase and decrease in size.

// 🤔 Dialog
// A dialog is a small window (screen) that prompts the user to make a decision or enter additional information.
// Normally a dialog does not fill the entire screen, and it requires users to take an action before they can proceed.
// Android provides different types of Dialogs. 
// In this codelab, you learn about Alert Dialogs.
// => 'Alert Dialogs' consists of 'Title (optional)','Message','Text buttons'

// 🤔 Trailing lambda syntax
    ...
    .setNegativeButton(getString(R.string.exit)) { _, _ ->
        exitGame()
    }
    .setPositiveButton(getString(R.string.play_again)) { _, _ ->
        restartGame()
    }
// => this syntax may be new to you, but this is shorthand for setNegativeButton(getString(R.string.exit), { _, _ -> exitGame()}) where the setNegativeButton() method takes in two parameters: a String and a function, DialogInterface.OnClickListener() which can be expressed as a lambda. 
// => When the last argument being passed in is a function, you could place the lambda expression outside the parentheses. 
// => Both ways of writing the code (with the lambda inside or outside the parentheses) is acceptable.

// ✅ Use LiveData with ViewModel 
// ◽ LiveData holds data; LiveData is a wrapper that can be used with any type of data.
// ◽ LiveData is observable, which means that an observer is notified when the data held by the LiveData object changes.
// ◽ LiveData is lifecycle-aware. When you attach an observer to the LiveData, the observer is associated with a LifecycleOwner (usually an activity or fragment). 
// => The LiveData only updates observers that are in an active lifecycle state such as STARTED or RESUMED. 
// => Hence, the observer in the GameFragment will only be notified when the GameFragment is in STARTED or RESUMED states.

// 🤔 MutableLiveData
// : MutableLiveData is the mutable version of the LiveData, that is, the value of the data stored within it can be changed.

// 🤔 Where to use it? 
// In GameFragment, delete the method updateNextWordOnScreen() and all the calls to it. 
// You do not require this method, as you will be attaching an observer to the LiveData.
// ✨ In the end, the scrambled word text view is automatically updated in the LiveData observer, not in the updateNextWordOnScreen() method.


// ✅ Data Binding
// Data Binding Library is also a part of the Android Jetpack library.
// In simpler terms Data binding is binding data (from code) to views + view binding (binding views to code)
// You can use the Architecture Components with the Data Binding Library to further simplify the development of your UI.
/* 
    Example using view binding in UI controller
    binding.textViewUnscrambledWord.text = viewModel.currentScrambledWord

    Example using data binding in layout file
    android:text="@{gameViewModel.currentScrambledWord}"
*/
// The main advantage of using data binding is, it lets you remove many UI framework calls in your activities, making them simpler and easier to maintain. 
// This can also improve your app's performance and help prevent memory leaks and null pointer exceptions.

// 🤔 Using Binding expression @{}
// Binding expressions are written within the layout in the attribute properties (such as android:text) referencing the layout properties. 
// Layout properties are declared at the top of the data binding layout file, via the <variable> tag. 
// When any of the dependent variables change, the ‘DB Library' will run your binding expressions (and thus updates the views).
// This change-detection is a great optimization which you get for free, when you use a Data Binding Library.

// You don't need the observer code in fragment any more. 
// The layout receives the updates of the changes to the LiveData directly.

// In the end, the scrambled word text view uses the binding expressions to update the UI, not the LiveData observers.

// 👌 A data binding expression can also reference app resources with the following syntax.
android:padding="@{@dimen/largePadding}"
// You can also pass layout properties as resource parameters.
// strings.xml: <string name="example_resource">Last Name: %s</string>
android:text="@{@string/example_resource(user.lastName)}"

// summary: You have learned how to use LiveData with LiveData observers and LiveData with binding expressions.

// 🤔 apply()
// : It forms a temporary scope, and in that scope, you can access the object without its name. 
// => Such calls can be read as "apply the following assignments to the object."
clark.apply {
    firstName = "Clark"
    lastName = "James"
    age = 18
}

// cf. when you want to bind the fragment data variable with the fragment instance..
// => You will use 'this' keyword differently here, because inside the binding?.apply block, the keyword this refers to the binding instance, not the fragment instance. Use @ and explicitly specify the fragment class name.
// for example this@FlavorFragment. 
 binding?.apply {
        lifecycleOwner = viewLifecycleOwner
        viewModel = sharedViewModel
        flavorFragment = this@FlavorFragment
    }

// The equivalent code without apply scope function would look like the following.

clark.firstName = "Clark"
clark.lastName = "James"
clark.age = 18

// 💥 Important
// Directly binding a property to an attribute works well when the view model’s type matches the attribute type. Unfortunately, this isn’t always the case.
/* 
<TextView
                    android:id="@+id/quantity"
                    style="@style/Widget.Cupcake.TextView.OrderSummary"
                    />
                <!-- Directly binding a property to an attribute works well when the view model’s type matches the attribute type.  -->
                ❗   android:text="@{viewModel.quantity.toString()}"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="@dimen/order_summary_margin"
                    tools:text="6 cupcakes" />
                    */

// ✅ Shared ViewModel: our best example 'CupCake App' 💕
// : You will use the activity instance instead of the fragment instance,
// => That means the view model can be shared across fragments. Each fragment could access the view model to check on some detail of the order or update some data in the view model.

// 🤔 Delegate Class
// To use the shared view model in StartFragment you will initialize the OrderViewModel using activityViewModels() instead of viewModels() delegate class.
// ◽ viewModels() gives you the ViewModel instance scoped to the current fragment. This will be different for different fragments.
// ◽ activityViewModels() gives you the ViewModel instance scoped to the current activity. Therefore the instance will remain the same across multiple fragments in the same activity.

// 🤔 Listener Bindings
// : Listener bindings are lambda expressions that run when an event happens, such as an onClick event.

// 🤔 Date Formatter
// : The Android framework provides a class called SimpleDateFormat, which is a class for formatting and parsing dates in a locale-sensitive manner. It allows for formatting (date → text) and parsing (text → date) of dates.
// => You will use the method Locale.getDefault() to retrieve the locale information set on the user's device and pass it into the SimpleDateFormat constructor.
// => Locale in Android is a combination of language and country code. The language codes are two-letter lowercase ISO language codes, such as "en" for english. The country codes are two-letter uppercase ISO country codes, such as "US" for the United States.

// 🤔 The Elvis operator ?:
private fun updatePrice() {
    _price.value = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
}
// The elvis operator (?:) means that if the expression on the left is not null, then use it. 
// Otherwise if the expression on the left is null, then use the expression to the right of the elvis operator (which is 0 in this case).

