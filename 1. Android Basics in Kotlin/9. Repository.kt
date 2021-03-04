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