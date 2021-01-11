// ✅ View and Viewgroups 
// The UI  of an app is what you see on the screen: text, images, buttons, and many other types of elements.
// Each of these elements is what's called a 'View'
// For example, you will work with a kind of View that is for displaying text and is called a 'TextView'
// To organize Views, you put them in a container.
// A 'ViewGroup' is a container that View objects can sit in, and is responsible for arranging the Views inside it.
// The arrangement, or layout, can change depending on the size and aspect ratio of the screen of the Android device that the app is running on, and the layout can adapt to whether the device is in portrait or landscape mode.
// One kind of ViewGroup is a 'ConstraintLayout', which helps you arrange the Views inside it in a flexible way.
// Views need to be constrained horizontally and vertically within a ConstraintLayout.
// ✨ Layout Editor: it helps you create the UI for your Android app
// => It supports code, split, design modes. 

// cf. the purpose of the activity_main.xml file in the project you created: 
// => It describes the layout of view groups and views for a screen.

// Note: A constraint in the context of the Layout Editor represents a connection or alignment of a view to another view, the parent layout, or an invisible guideline. 
// For example, a view can be constrained to be a certain distance from the edge of its container, or always be to the right of another view, or always the top view inside a container.

// ✅ Deal with some warnings
// ❓ Hardcoded string: written directly in the code of your app 
// 😡 This make it more difficult to translate your app into other languages and harder to reuse a string in different places in your app.
// 😊 "Extract Resource": define resource name(with '_' and lowercase names)
// cf. app > res > values > strings.xml and AS has created a string resource we made.
// + Note that the name of your app is also a string resource. 
// + TalkBack is the Google screen reader included on Android devices. TalkBack gives you spoken feedback so that you can use your device without looking at the screen.

// ❓ contentDescription: it can help make your app more usable with TalkBack by defining the purpose of the UI element
// 😊 Instead of setting the content description that is announced to the user, you can just tell TalkBack to skip the ImageView by setting its importantForAccessibility attribute to no.
