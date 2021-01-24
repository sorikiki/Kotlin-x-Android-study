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

// ✅ Set up Menu and Icons
// make 'menu' folder in 'res' and write the xml like this.
// <menu xmlns:android="http://schemas.android.com/apk/res/android"
//    xmlns:app="http://schemas.android.com/apk/res-auto">
//    <item android:id="@+id/action_switch_layout"
//        android:title="@string/action_switch_layout"
//        android:icon="@drawable/ic_linear_layout"
//        app:showAsAction="always" />
// </menu>
// ✔ id: Just like views, the menu option has an id so that it can be referenced in code.
// ✔ title: This text won't actually be visible in your case, but may be useful for screen readers to identify the menu
// ✔ icon: The default is ic_linear_layout. However, this will be toggled on and off to show the grid icon, when the button is selected.
// ✔ showAsAction: This tells the system how to show the button. Because it's set to always, this button will always be visible in the app bar, and not become part of an overflow menu.

// MainActivity.kt
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        // Sets the LinearLayoutManager of the recyclerview
        chooseLayout()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                // Sets isLinearLayoutManager (a Boolean) to the opposite value
                isLinearLayoutManager = !isLinearLayoutManager
                // Sets layout and icon
                chooseLayout()
                setIcon(item)

                return true
            }
            //  Otherwise, do nothing and use the core event handling

            // when clauses require that all possible paths be accounted for explicitly,
            //  for instance both the true and false cases if the value is a Boolean,
            //  or an else to catch all unhandled cases.
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 4)
        }
        recyclerView.adapter = LetterAdapter()
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        // Set the drawable for the menu icon based on which LayoutManager is currently in use

        // An if-clause can be used on the right side of an assignment if all paths return a value.
        // The following code is equivalent to
        // if (isLinearLayoutManager)
        //     menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
        // else menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
            else ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
    }

}
// 1. First, it's a good idea to create a property to keep track of which layout state the app is in. 
// That will make it easier to toggle the layout button. Set the default value to true, as the linear layout manager will be used by default.

// 2. When the user toggles the button, you want the list of items to turn into a grid of items. 
// If you recall from learning about recycler views, there are many different layout managers, one of which, GridLayoutManager allows for multiple items on a single row.

// 3. When you initially set up the menu in xml, you gave it a static icon. 
// However, after toggling the layout, you should update the icon to reflect its new function—switching back to the list layout. 
// Here, simply set the linear and grid layout icons, based on the layout the button will switch back to, next time it's tapped.

// For your app to actually use the menu, you need to override two more methods.

// - onCreateOptionsMenu: where you inflate the options menu and perform any additional setup
// - onOptionsItemSelected: where you'll actually call chooseLayout() when the button is selected.

// + onCreateOptionsMenu: Initialize the contents of the Activity's standard options menu. This is only called once, the first time the options menu is displayed.
// + onOptionsItemsSelected: This hook is called whenever an item in your options menu is selected. The default implementation simply returns false to have the normal processing happen (calling the item's Runnable or sending a message to its Handler as appropriate).  

override fun onCreateOptionsMenu(menu: Menu?): Boolean {
   menuInflater.inflate(R.menu.layout_menu, menu)

   val layoutButton = menu?.findItem(R.id.action_switch_layout)
   // Calls code to set the icon based on the LinearLayoutManager of the RecyclerView
   setIcon(layoutButton)

   return true
}
// After inflating the layout, you call setIcon() to ensure the icon is correct, based on the layout. The method returns a Boolean—you return true here since you want the options menu to be created.

override fun onOptionsItemSelected(item: MenuItem): Boolean {
   return when (item.itemId) {
       R.id.action_switch_layout -> {
           // Sets isLinearLayoutManager (a Boolean) to the opposite value
           isLinearLayoutManager = !isLinearLayoutManager
           // Sets layout and icon
           chooseLayout()
           setIcon(item)

           return true
       }
       //  Otherwise, do nothing and use the core event handling

       // when clauses require that all possible paths be accounted for explicitly,
       //  for instance both the true and false cases if the value is a Boolean,
       //  or an else to catch all unhandled cases.
       else -> super.onOptionsItemSelected(item)
   }
}
// This is called any time a menu item is tapped so you need to be sure to check which menu item is tapped.

// Since the layout manager and adapter are now set in chooseLayout(), you should replace that code in onCreate() to call your new method. onCreate() should look like the following after the change.
override fun onCreate(savedInstanceState: Bundle?) {
   super.onCreate(savedInstanceState)

   val binding = ActivityMainBinding.inflate(layoutInflater)
   setContentView(binding.root)

   recyclerView = binding.recyclerView
   // Sets the LinearLayoutManager of the recyclerview
   chooseLayout()
}