// There are several ways to avoid applications from blocking.
// : Threading, Promise, Reactive Extensions and Coroutines
// ✅ Threading
// A thread is a unit of flow that runs within a program, especially within a process.
fun postItem(item: Item) {
    val token = preparePost()
    val post = submitPost(token, item)
    processPost(post)
}

fun preparePost(): Token {
    // makes a request and consequently blocks the main thread
    return token
}
// However, this method has a series of drawbacks.
// => Threads aren't cheap. Threads require context switches which are costly.
// => Threads aren't infinite. The number of threads that can be launched is limited by the underlying operating system. In server-side applications, this could cause a major bottleneck.
// => Threads aren't always available. Some platforms, such as JavaScript do not even support threads. (Javascript is basically synchronous.)
// => Threads aren't easy. Debugging threads, avoiding race conditions are common problems we suffer in multi-threaded programming.

// ✅ Callbacks
// With callbacks, the idea is to pass one function as a parameter to another function, and have this one invoked once the process has completed.
fun postItem(item: Item) {
    preparePostAsync { token ->
        submitPostAsync(token, item) { post ->
            processPost(post)
        }
    }
}

fun preparePostAsync(callback: (Token) -> Unit) {
    // make request and return immediately
    // arrange callback to be invoked later
}
// => Difficulty of nested callbacks. 
// => Error handling is complicated.

// ✅ Futures, promises, and others
// : The idea behind futures or promises (there are also other terms these can be referred to depending on language/platform), is that when we make a call, we're promised that at some point it will return with an object called a Promise, which can then be operated on.
fun postItem(item: Item) {
    preparePostAsync()
        .thenCompose { token ->
            submitPostAsync(token, item)
        }
        .thenAccept { post ->
            processPost(post)
        }

}

fun preparePostAsync(): Promise<Token> {
    // makes request an returns a promise that is completed later
    return promise
}
// This approach requires a series of changes.
// => Similar to callbacks, the programming model moves away from a top-down imperative approach to a compositional model with chained calls.
// => Different APIs. Usually there's a need to learn a completely new API such as thenCompose or thenAccept, which can also vary across platforms.
// => Specific return type. The return type moves away from the actual data that we need and instead returns a new type Promise which has to be introspected.
// => Error handling can be complicated. The propagation and chaining of errors aren't always straightforward.

// ✅ Coroutines
// : Kotlin's approach to working with asynchronous code is using coroutines, which is the idea of suspendable computations, i.e. the idea that a function can suspend its execution at some point and resume later on.
// One of the benefits however of coroutines is that when it comes to the developer, writing non-blocking code is essentially the same as writing blocking code. The programming model in itself doesn't really change.
fun postItem(item: Item) {
    launch {
        val token = preparePost()
        val post = submitPost(token, item)
        processPost(post)
    }
}

suspend fun preparePost(): Token {
    // makes a request and suspends the coroutine
    return suspendCoroutine { /* ... */ }
}
// ⬆ This code will launch a long-running operation without blocking the main thread.
