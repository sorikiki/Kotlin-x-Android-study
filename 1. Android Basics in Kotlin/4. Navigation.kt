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