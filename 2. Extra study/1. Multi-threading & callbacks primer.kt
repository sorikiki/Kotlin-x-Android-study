// ✅ Multi-threading
// multi-processing: Mobile devices have processors, and these days, most devices have multiple hardware processors that each run processes concurrently.
// multi-threading: To use processors more efficiently, the operating system can enable an application to create more than one thread of execution within a process. 
// a user-facing application usually has a main thread that runs in the foreground and can dispatch other threads that may run in the background.
// On Android, the main thread is a single thread that handles all updates to the UI. This main thread, also called the UI thread, is also the thread that calls all click handlers and other UI and lifecycle callbacks.(The UI thread is the default thread.)

// ❗ Many common tasks take longer than 16 milliseconds, such as fetching data from the internet, reading a large file, or writing data to a database. Therefore, calling code to perform tasks like those from the main thread can cause the app to pause, stutter, or even freeze.
// + And if you block the main thread for too long, the app may even crash and present an "application not responding" (ANR) dialog, so it's essential to avoid blocking the UI thread.
// => Then how to avoid blocking UI thread?

// ✨ Callbacks
// By using callbacks, you can start long-running tasks on a background thread.
// When the task completes, the callback, supplied as an argument, is called to inform your code of the result on the main thread.
// 😢 Callbacks are a great pattern, but they have a few drawbacks. 
// => Code that heavily uses callbacks can become hard to read and harder to reason about. 
// => Because while the code looks sequential, the callback code will run at some asynchronous time in the future. 
// => In addition, callbacks don't allow the use of some language features, such as exceptions.

// ✨ Coroutines
// Kotlin coroutines let you convert callback-based code to sequential code. ❤
// => Code written sequentially is typically easier to read, and can even use language features such as exceptions.

// In the end, coroutines and callbacks do exactly the same thing: wait until a result is available from a long-running task and continue execution.

