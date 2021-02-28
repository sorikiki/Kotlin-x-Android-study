// ✅ Retrofit: connect to a web service with Retrofit
// ❓ What is JSON
// a JSON object is a collection of key-value pairs, sometimes called a dictionary, a hash map, or an associative array. 
// A collection of JSON objects is a JSON array. 
// This array is what you get back as a response from a web service.
// ❓ What Retrofit does
// To get this JSON data into the app, your app needs to establish a network connection to a server, communicate with that server, and then receive and parse the JSON response data into a format the app can use.
// => a REST client library called Retrofit to make this connection.

// Step 1: Add Retrofit dependencies to Gradle

// Step 2: Add support for Java 8 language features

// Step 3: Implement MarsApiService
private val retrofit = Retrofit.Builder()
   .addConverterFactory(ScalarsConverterFactory.create())
   .baseUrl(BASE_URL)
   .build()

// Retrofit needs at least two things available to it to build a web services API: the base URI for the web service, and a converter factory.
// The converter tells Retrofit what to do with the data it gets back from the web service. 
// In this case, you want Retrofit to fetch a JSON response from the web service, and return it as a String.
// Retrofit has a ScalarsConverter that supports strings and other primitive types, so you call addConverterFactory() on the builder with an instance of ScalarsConverterFactory.
// Finally, you call build() to create the Retrofit object.

interface MarsApiService {
    @GET("realestate")
    fun getProperties():
            Call<String>
}
// When the getProperties() method is invoked, Retrofit appends the endpoint realestate to the base URL (which you defined in the Retrofit builder), and creates a Call object. That Call object is used to start the request.

// Step 4: Call the web service in OverviewViewModel

// getMarsRealEstateProperties() is the method where you'll call the Retrofit service and handle the returned JSON string.

// Step 5: Define the internet permission

// ✅ Parse the JSON response with Moshi
// There's a library called Moshi, which is an Android JSON parser that converts a JSON string into Kotlin objects.
// To do this, it needs to have a Kotlin data class to store the parsed results, so the next step is to create that class.
// When Moshi parses the JSON, it matches the keys by name and fills the data objects with appropriate values.

// Sometimes the key names in a JSON response can make confusing Kotlin properties, or may not match your coding style—for example, in the JSON file the img_src key uses an underscore, whereas Kotlin properties commonly use upper and lowercase letters ("camel case").
// => To use variable names in your data class that differ from the key names in the JSON response, use the @Json annotation.

// ✅ Use Coroutines with Retrofit
// Now the Retrofit API service is running, but it uses a callback with two callback methods that you had to implement. One method handles success and another handles failure, and the failure result reports exceptions. 
// Your code would be more efficient and easier to read if you could use coroutines with exception handling, instead of using callbacks.
// In this task, you convert your network service and the ViewModel to use coroutines.

// Summary
// ◽ REST web services
// A web service is software-based functionality offered over the internet that enables your app to make requests and get data back.
// Common web services use a REST architecture. Web services that offer REST architecture are known as RESTful services. RESTful web services are built using standard web components and protocols.
// You make a request to a REST web service in a standardized way, via URIs.
// To use a web service, an app must establish a network connection and communicate with the service. Then the app must receive and parse response data into a format the app can use.
// The Retrofit library is a client library that enables your app to make requests to a REST web service.
// Use converters to tell Retrofit what to do with data it sends to the web service and gets back from the web service. For example, the ScalarsConverter converter treats the web service data as a String or other primitive.
// To enable your app to make connections to the internet, add the "android.permission.INTERNET" permission in the Android manifest.
// ◽ JSON parsing
// The response from a web service is often formatted in JSON, a common interchange format for representing structured data.
// A JSON object is a collection of key-value pairs. This collection is sometimes called a dictionary, a hash map, or an associative array.
// A collection of JSON objects is a JSON array. You get a JSON array as a response from a web service.
// The keys in a key-value pair are surrounded by quotes. The values can be numbers or strings. Strings are also surrounded by quotes.
// The Moshi library is an Android JSON parser that converts a JSON string into Kotlin objects. Retrofit has a converter that works with Moshi.
// Moshi matches the keys in a JSON response with properties in a data object that have the same name.
// To use a different property name for a key, annotate that property with the @Json annotation and the JSON key name.

// ✅ Display an internet image
// The image has to be downloaded, internally stored, and decoded from its compressed format to an image that Android can use. 
// The image should be cached to an in-memory cache, a storage-based cache, or both. All this has to happen in low-priority background threads so the UI remains responsive. 
// Also, for best network and CPU performance, you might want to fetch and decode more than one image at once.
// Fortunately, you can use a community-developed library called Glide to download, buffer, decode, and cache your images.

// Glide basically needs two things:

// ◽ The URL of the image you want to load and display.
// ◽ An ImageView object to actually display that image.
// => binding adapter will call Glide to load an image from a URL into an ImageView.
