// ✅ Room DataBase
// In Android, data is represented in data classes. This data is accessed and possibly modified using function calls. 
// However, in the database world, you need entities and queries to access and modify data.

// Entity? An entity represents an object or a concept, along with its properties, to store in the database. In our application code, we need an entity class that defines a table, and each instance of that class represents a row in that table. 
// Queries? A query is a request for data or information from a database table or combination of tables, or a request to perform an action on the data. Common queries are for creating, reading, updating and deleting entities. 
// => On Android, the DAO(data acess object) provides convenience methods for inserting, deleting, and updating the database.

// ✨ Room does all the hard work to get from Kotlin data classes to entities that can be stored in SQLite tables, and from function declarations to SQL queries.
/*
    You must define each entity as an annotated data class, and the interactions with that entity as an annotated interface, called a data access object (DAO). 
    Room uses these annotated classes to create tables in the database, and to create queries that act on the database.
 */

// For the sleep-tracker database of sleep nights, you need to be able to do the following: 😊
// ✔ Insert new nights.
// ✔ Update an existing night to update an end time and a quality rating.
// ✔ Get a specific night based on its key.
// ✔ Get all nights, so you can display them.
// ✔ Get the most recent night.
// ✔ Delete all entries in the database.

// ◽ Annotations for Entity
// @Entity: Before the data class declaration, annotate the data class with @Entity. This annotation has several possible arguments. 
// => By default (no arguments to @Entity), the table name will be the same as the class. But let's use a helpful table name. This argument for the tableName is optional, but highly recommended. 
// @PrimaryKey: Define something as primary key. Set the parameter autoGenerate to true so that Room generates the ID for each entity. This guarantees that the ID for each instance is unique.
// @ColumnInfo: Annotate the remaining properties with @ColumnInfo.

// ◽ Annotations for DAO(data acess object)
// @Dao: All DAOs need to be annotated with the @Dao keyword.
// @Insert: Room executes a SQL query to insert the entity into the database. 
// @Update: The entity that's updated is the entity that has the same key as the one that's passed in. You can update some or all of the entity's other properties.

// There is no convenience annotation for the remaining functionality, so you have to use the @Query annotation and supply SQLite queries.

// ◽ Room Database
// Create a public abstract class that extends RoomDatabase. This class is to act as a database holder. The class is abstract, because Room creates the implementation for you.
// The database needs to know about the DAO. Inside the body of the class, declare an abstract value that returns the SleepDatabaseDao. You can have multiple DAOs.
// The companion object allows clients to access the methods for creating or getting the database without instantiating the class. Since the only purpose of this class is to provide a database, there is no reason to ever instantiate it.
// Inside the companion object, declare a private nullable variable INSTANCE for the database and initialize it to null. The INSTANCE variable will keep a reference to the database, when one has been created. This helps you avoid repeatedly opening connections to the database, which is computationally expensive.

// ❓ @Volatile: The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory.
// => This helps make sure the value of INSTANCE is always up-to-date
// => changes made by one thread to INSTANCE are visible to all other threads immediately

// ❓ synchronized(this) {}
// Multiple threads can potentially ask for a database instance at the same time, resulting in two databases instead of one.
// Wrapping the code to get the database into synchronized means that only one thread of execution at a time can enter this block of code, which makes sure the database only gets initialized once.

// ✅ Room with Coroutines
// 🌟 Pattern
// Launch a coroutine that runs on the main UI thread, because the result from that coroutine affects what is displayed in the UI. You can access the CoroutineScope of a ViewModel through the viewModelScope property of the ViewModel, as shown in the following example:
// Call a suspend function to do the long-running work, so that you don't block the UI thread while waiting for the result.
// The result of the long-running work may affect the UI, but its operation is independent from the UI. For efficiency, switch to the I/O dispatcher, (Room generates his code for you). The I/O dispatcher uses a thread pool that's optimized and set aside for these kinds of operations.
// Then call the long running function to do the work.
// The pattern is shown below.
// Without Room
fun someWorkNeedsToBeDone {
   viewModelScope.launch {
        suspendFunction()
   }
}

suspend fun suspendFunction() {
   withContext(Dispatchers.IO) {
       longrunningWork()
   }
}
// Using Room
fun someWorkNeedsToBeDone {
   viewModelScope.launch {
        suspendDAOFunction()
   }
}

suspend fun suspendDAOFunction() {
   // No need to specify the Dispatcher, Room uses Dispatchers.IO.
   longrunningDatabaseWork()
}

// ◽ Basic set-up
// step1. Mark DAO functions as suspend functions
// => Room already uses a background thread for that specific @Query which returns LiveData. The complete SleepDatabaseDao class will look like this.

// step2. Set up coroutines for database operations
// => When the Start button in the Sleep Tracker app is tapped, you want to call a function in the SleepTrackerViewModel to create a new instance of SleepNight and store the instance in the database.
// => Since database operations can take a while, you use coroutines to implement click handlers for the app's buttons.

// step3. Add the click handler for the Start button

// step4. Display the data 
// => It is a Room feature that every time the data in the database changes, the LiveData nights is updated to show the latest data. You never need to explicitly set the LiveData or update it. Room updates the data to match the database.
// => However, if you display nights in a text view, it will show the object reference. To see the contents of the object, transform the data into a formatted string.

// step5. Add the click handler for the Stop button

// step6. Add the click handler for the Clear button

// ◽ Triggering Navigation
// 1. Define onClick handlers to trigger navigation to a destination fragment.
// => onStopTracking(), onSetSleepQuality()
// 2. Define a LiveData value to record if navigation should occur.
// 3. Attach an observer to that LiveData value.
// 4. Your code then changes that value whenever navigation needs to be triggered or is complete.

// ◽ android:enabled attribute
// : The android:enabled attribute is defined in TextView and inherited by all subclasses, including Button.
// => The android:enabled attribute determines whether or not a View is enabled. The meaning of "enabled" varies by subclass. For example, a non-enabled EditText prevents the user from editing the contained text, and a non-enabled Button prevents the user from tapping the button.
// => The enabled attribute is not the same as the visibility attribute.
// => You can use transformation maps to set the value of the enabled attribute of buttons based on the state of another object or variable.
