// ✅ Coroutines: Overview
// ✔ asynchronous
// ✔ non-blocking
// ✔ suspend functions to make asynchronous code sequential.
// => The keyword suspend is Kotlin's way of marking a function, or function type, as being available to coroutines.
// => When a coroutine calls a function marked with suspend, instead of blocking until the function returns like a normal function call, the coroutine suspends execution until the result is ready. Then the coroutine resumes where it left off, with the result.

// To use coroutines in Kotlin, you need three things:

// ◽ A job
// - Basically, a job is anything that can be canceled. Every coroutine has a job, and you can use the job to cancel the coroutine. 
// - Jobs can be arranged into parent-child hierarchies. Canceling a parent job immediately cancels all the job's children, which is a lot more convenient than canceling each coroutine manually.

// ◽ A dispatcher
// - The dispatcher sends off coroutines to run on various threads. For example, Dispatchers.Main runs tasks on the main thread, and Dispatchers.IO offloads blocking I/O tasks to a shared pool of threads
// => When using the Room library to perform a database operation, Room uses a Dispatchers.IO to perform the database operations in a background thread. Hence, you do not need to explicitly use dispatcher.
// => Room already uses a background thread for that specific @Query which returns LiveData. 
    /*
    Without Room
    
    
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
    Using Room
    
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
    */
    
// ◽ A scope
// - A coroutine's scope defines the context in which the coroutine runs. A scope combines information about a coroutine's job and dispatchers. Scopes keep track of coroutines. When you launch a coroutine, it's "in a scope," which means that you've indicated which scope will keep track of the coroutine.
// => Architecture components provide first-class support for coroutines for logical scopes in your app. Architecture components define the following built-in scopes that you can use in your app. 
// => ViewModelScope, LifecycleScope, liveData...
// => In this codelab, you will use ViewModelScope to initiate the database operations. Any coroutine launched in this scope is automatically canceled if the ViewModel is cleared.
