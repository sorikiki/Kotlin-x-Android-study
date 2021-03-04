// ✅ Caching
// : After an app fetches data from the network, the app can cache the data by storing the data in a device's storage. You cache data so that you can access it later when the device is offline, or if you want to access the same data again.
// Room is ecommended solution for complex and structured data, because the best way to store structured data on a device's file system is in a local SQLite database.

// ✨ Strategy: When the app fetches data from the network, store the data in the database instead of displaying the data immediately.
// => This technique ensures that the offline cache is always up-to-date. Also, if the device is offline, your app can still load locally cached data.

// 🤔 conversion logic
// : the structure of the domain, database, and network objects will be different so that you'll need conversion logic, which can get complicated.

// ✅ Repository
// : The repository pattern is a design pattern that isolates data sources from the rest of the app.

// 👍 advantages of using a repository
// a repository module handles data operations and allows you to use multiple backends. 
// In a typical real-world app, the repository implements the logic for deciding whether to fetch data from a network or use results that are cached in a local database. 
// This helps make your code modular and testable. You can easily mock up the repository and test the rest of the code.

// "A database refresh" is a process of updating or refreshing the local database to keep it in sync with data from the network.
// => For this sample app, you use a very simple refresh strategy, where the module that requests data from the repository is responsible for refreshing the local data.
// => In a real-world app, your strategy might be more complex. For example, your code might automatically refresh the data in the background (taking bandwidth into account), or cache the data that the user is most likely to use next.

// Summary
// Caching is a process of storing data fetched from a network on a device's storage. Caching lets your app access data when the device is offline, or if your app needs to access the same data again.
// The best way for your app to store structured data on a device's file system is to use a local SQLite database. Room is an SQLite object-mapping library, meaning that it provides an abstraction layer over SQLite. Using Room is the recommended best practice for implementing offline caching.
// A repository class isolates data sources, such as Room database and web services, from the rest of the app. The repository class provides a clean API for data access to the rest of the app.
// Using repositories is a recommended best practice for code separation and architecture.
// When you design an offline cache, it's a best practice to separate the app's network, domain, and database objects. This strategy is an example of separation of concerns.
// To add offline-support to an app, add a local database using Room. Implement a repository to manage and access the Room database. In the ViewModel, fetch and display the data directly from the repository instead of fetching the data from the network.
// Use a database refresh strategy to insert and update the data in the local database. In a database refresh, the local database is updated or refreshed to keep it in sync with data from the network.
// To update your app's UI data automatically when the data in the database changes, use observable queries with a return value of type LiveData in the DAO. When the Room database is updated, Room runs the query in background to update the associated LiveData.

// ✅ WorkManager
// WorkManager is one of the Android Architecture Components and part of Android Jetpack. WorkManager is for background work that's deferrable and requires guaranteed execution:
// ◽ "Deferrable" means that the work is not required to run immediately. For example, sending analytical data to the server or syncing the database in the background is work that can be deferred.
// ◽ "Guaranteed" execution means that the task will run even if the app exits or the device restarts.

// WorkManager chooses an appropriate way to schedule a background task, depending on the device API level. It might use JobScheduler (on API 23 and higher) or a combination of AlarmManager and BroadcastReceiver.

// WorkManager also lets you set criteria on when the background task runs. 

// < Libraries in work-manager >
// ◽ Worker
// This class is where you define the actual work (the task) to run in the background. 
// You extend this class and override the doWork() method. 
// The doWork() method is where you put code to be performed in the background, such as syncing data with the server or processing images. 
// The method performs work synchronously, and should return a ListenableWorker.Result object.
// The Android system gives a Worker a maximum of 10 minutes to finish its execution and return a ListenableWorker.Result object. 
// After this time has expired, the system forcefully stops the Worker.

/* 
    To create a ListenableWorker.Result object, call one of the following static methods to indicate the completion status of the background work:

    Result.success()—work completed successfully.
    Result.failure()—work completed with a permanent failure.
    Result.retry()—work encountered a transient failure and should be retried.
*/

// ◽ WorkRequest
// This class represents a request to run the worker in background. 
// Use WorkRequest to configure how and when to run the worker task, with the help of Constraints such as device plugged in or Wi-Fi connected. 
// There are two concrete implementations of the WorkRequest class:
// => The OneTimeWorkRequest class is for one-off tasks. (A one-off task happens only once.)
// => The PeriodicWorkRequest class is for periodic work, work that repeats at intervals.

// ◽ WorkManager
// This class schedules and runs your WorkRequest. 
// WorkManager schedules work requests in a way that spreads out the load on system resources, while honoring the constraints that you specify. 

// Summary
// The WorkManager API makes it easy to schedule deferrable, asynchronous tasks that must be run reliably.
// Most real-world apps need to perform long-running background tasks. To schedule a background task in an optimized and efficient way, use WorkManager.
// The main classes in the WorkManager library are Worker, WorkRequest, and WorkManager.
// The Worker class represents a unit of work. To implement the background task, extend the Worker class and override the doWork() method.
// The WorkRequest class represents a request to perform a unit of work. WorkRequest is the base class for specifying parameters for work that you schedule in WorkManager.
// There are two concrete implementations of the WorkRequest class: OneTimeWorkRequest for one-off tasks, and PeriodicWorkRequest for periodic work requests.
// When defining the WorkRequest, you can specify Constraints indicating when the Worker should run. Constraints include things like whether the device is plugged in, whether the device is idle, or whether Wi-Fi is connected.
// To add constraints to the WorkRequest, use the set methods listed in the Constraints.Builder documentation. For example, to indicate that the WorkRequest should not run if the device battery is low, use the setRequiresBatteryNotLow() set method.
// After you define the WorkRequest, hand off the task to the Android system. To do this, schedule the task using one of the WorkManager enqueue methods.
// The exact time that the Worker is executed depends on the constraints that are used in the WorkRequest, and on system optimizations. WorkManager is designed to give the best possible behavior, given these restrictions.