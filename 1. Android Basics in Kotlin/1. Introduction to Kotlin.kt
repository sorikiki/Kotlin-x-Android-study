// ✅ Basic elements: main() & println()
// main() is an entry point of a Kotlin application.
fun main() {
    println("Hello, world!")
    println("Dasol Kim")
    println("You are 24!")
} 

// ✔ println() vs print()
// => print(): text without line break.
// => we use 1) '\n' or 2) println() instead.

// ✅ Basic elements: variable
// ❗ Remember that read-only local variables are defined using the keyword 'val'. They can be assigned a value only once.
// ❗ 'var' is able to make us modify previous value.
fun main() {
    val age = 24
    val name = "Dasol"
    print("Happy Birthday, ${name}\n")
	// let's print a cake!
    println("   ,,,,,   ")
    println("   |||||   ")
    println(" =========")
    println("@@@@@@@@@@@")
    println("{~@~@~@~@~}")
    println("@@@@@@@@@@@")
	// This prints an empty line
    println("")
    println("You are already ${age} old, ${name}!")
    println("${age} is the very best age to celebrate")
}

// ✅ Application: functions
// 1. low-level function into main()
fun main() {
	printBorder()
    println("Happy Birthday, Dasol!")
    printBorder()
}

fun printBorder() {
        println("=======================")
}

// 2. repeat() = loop
fun main() {
	printBorder()
    println("Happy Birthday, Dasol!")
    printBorder()
}

fun printBorder() {
        repeat(23) {
            print("=")
        } // This iterates same code 'print("=")'!
        println()
        }

// 3. use arguments
fun main() {
    val border = "%"
	printBorder(border)
    println("Happy Birthday, Dasol!")
    printBorder(border)
}

fun printBorder(arg: String) {
        repeat(23) {
            print(arg)
        } // This iterates same code 'print("=")'!
        println()
        }

// ✅ Range and random()
fun main() {
	val diceRange = 1..6
    // val diceRance : IntRange = 1..6

    val randomNumber = diceRange.random()
	// You can read this as "generating a random number from diceRange".
    
    println("Random number: ${randomNumber}")
}

// + Tips about ranges.
// - Ranges can be between any integers. ex) -6..+6, -10..-4
// - You can call functions directly on a range. ex) (1..6).random()


// ✅ Class and Objects
// class should represent itself by name that indicates what the class represents. 
// By convention, the class name is in camel case.

fun main() {
    val myFirstDice = Dice()
    println(myFirstDice.sides)
    myFirstDice.roll()
}

class Dice {
    var sides = 6
    
    fun roll() {
        val randomNumber = (1..6).random()
        println(randomNumber)
    }
}
// You have defined a Dice class with a sides variable and a roll() function. 
// In the main() function, you created a new Dice object instance and then you called the roll() method on it to produce a random number.

// ❗ In previous codelabs you learned that you need to specify a data type for input arguments to functions. In the same way, you have to specify a data type for data that a function returns.
fun main() {
    val myFirstDice = Dice()
    val diceRoll = myFirstDice.roll()
    println("Your ${myFirstDice.sides} sided dice rolled ${diceRoll}!")
}

class Dice {
    var sides = 6
    
    fun roll(): Int {
        val randomNumber = (1..6).random()
        return randomNumber
    }
}

// ❗ An instance of a class represents an object, often a physical object, such as a dice. You can call the actions on the object and change its attributes.
fun main() {
    val myFirstDice = Dice()
    myFirstDice.sides = 20
    println("Your ${myFirstDice.sides} sided dice rolled ${myFirstDice.roll()}!")
}

class Dice {
    var sides = 6
    
    fun roll(): Int {
        val randomNumber = (1..sides).random()
        return randomNumber
    }
}
// 😡 This code above is not good convention.

// 😀 Programmatically, this means that instead of changing the sides property of an existing Dice object instance, you should create a new dice object instance with the number of sides you need.
// You can supply values to a class when you create an instance.
fun main() {
    val myFirstDice = Dice(6)
    println("Your ${myFirstDice.numSides} sided dice rolled ${myFirstDice.roll()}!")
}

class Dice(val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}
// ✔ Notice that we add () after class name, and declare 'val ... : type'

// ✅ Conditionals: if, else if, else, when ✨
fun main() {
    val num = 4
    if (num > 4) {
        println("The variable is greater than 4")
    } else if (num == 4) {
        println("The variable is equal to 4")
    } else {
        println("The variable is less than 4")
    }
}

// 😡 Too long. 
fun main() {
    val myFirstDice = Dice(6)
    val diceRoll = myFirstDice.roll()
    val luckyNumber= 4
    if (diceRoll == luckyNumber) {
        println("You win!")
    } else if (rollResult == 1) {
        println("So sorry! You rolled a 1. Try again!")
    } else if (rollResult == 2) {
        println("Sadly, you rolled a 2. Try again!")
    } else if (rollResult == 3) {
        println("Unfortunately, you rolled a 3. Try again!")
    } else if (rollResult == 4) {
        println("No luck! You rolled a 4. Try again!")
    } else if (rollResult == 5) {
        println("Don't cry! You rolled a 5. Try again!")
    } else {
        println("Apologies! you rolled a 6. Try again!")
    }
}

class Dice (val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}

// 😊 There are several situations when we use a lot of 'else if' sentences. Then use 'when'. 💖
fun main() {
    val myFirstDice = Dice(6)
    val rollResult = myFirstDice.roll()
    val luckyNumber = 4

    when (rollResult) {
        luckyNumber -> println("You won!")
        1 -> println("So sorry! You rolled a 1. Try again!")
        2 -> println("Sadly, you rolled a 2. Try again!")
        3 -> println("Unfortunately, you rolled a 3. Try again!")
        4 -> println("No luck! You rolled a 4. Try again!")
        5 -> println("Don't cry! You rolled a 5. Try again!")
        6 -> println("Apologies! you rolled a 6. Try again!")
    }
}
// Read this as, "If rollResult is luckyNumber, then print the "You win!" message."
// ❓ when the value of the when expression is assigned to a variable, the 'when' must be exhaustive. 
// ❗ Let's add necessary ‘else' branch.


// ✅ Classes and Inheritance
// ✔ 'abstract': Create an abstract class where some functionality is left to be implemented by its subclasses. An abstract class can therefore not be instantiated.
// + All abstract properties, methods defined in an abstract class must be implemented in any of its subclasses with 'override' keyword.
// ✔ 'override': Use override keyword to override properties and functions in subclasses.
// ✔ 'private': Make a property private, so it can only be used inside the class.
// + 'private' is a visibility modifier in Kotlin meaning that the property is only visible to (and can be used inside) 'this class'.(so does inherited class ❗)
// ✔ 'with': When you are working with a specific instance of a class and need to access multiple properties and functions of that instance, you can say "do all the following operations with this instance object" using a with statement. 
// ✔ 'super': Use the super keyword to call the function that is defined in the parent.

fun main() {
    val squareCabin = SquareCabin(6)

    with(squareCabin) {
        println("\nSquare Cabin\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
    }
}

abstract class Dwelling(private var residents: Int) {
    abstract val buildingMaterial : String
    abstract val capacity: Int
    
    fun hasRoom(): Boolean {
    return residents < capacity
	}
}

class SquareCabin(residents: Int): Dwelling(residents){
    override val buildingMaterial = "Wood"
    override val capacity = 6
}

// ❓ Error "This type is final, so it cannot be inherited from"
// => This error means that the RoundHut class cannot be subclassed (or inherited from). By default, in Kotlin, classes are final and cannot be subclassed.
// ✔ You are only allowed to inherit from 'abstract' classes or classes that are marked with the 'open' keyword. 
// = You do not need to use the open keyword when defining abstract classes.

// ✔ arguments only with a subclass
/** 
class RoundTower(
    residents: Int,
    val floors: Int = 2) : RoundHut(residents) {

    ...
}
*/
// Notice that you don't need to pass this to the parent RoundHut constructor because floors is defined here in RoundTower and RoundHut has no floors.
// In your code, add '= 2' after the declaration of floors to assign it a default value of 2.


// tip. For the area values, it would be a nicer user experience to only show a couple of decimal places. 
// => ex. println("Floor area: %.2f".format(floorArea()))
import kotlin.math.PI

fun main() {
    val squareCabin = SquareCabin(6, 11.1)
    val roundHut = RoundHut(3, 9.0)
    val roundTower = RoundTower(3, 9.0, 3)

    with(squareCabin) {
        println("\nSquare Cabin\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
        println("Floor area: %.2f".format(floorArea()))
    }
    with(roundHut) {
        println("\nRound Hut\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
        getRoom()
		println("Has room? ${hasRoom()}")
        getRoom()
        println("Floor area: %.2f".format(floorArea()))
    }
    with(roundTower) {
        println("\nRound Tower\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
        println("Floor area: %.2f".format(floorArea()))
    }
}

abstract class Dwelling(private var residents: Int) {
    abstract val buildingMaterial : String
    abstract val capacity: Int
    
    fun hasRoom(): Boolean {
    return residents < capacity
	}
    
    abstract fun floorArea() : Double

    fun getRoom() {
    if (capacity > residents) {
        residents++
        println("You got a room!")
    } else {
        println("Sorry, at capacity and no rooms left.")
    }
}
}

class SquareCabin(
    residents: Int, 
    val length: Double): Dwelling(residents){
    override val buildingMaterial = "Wood"
    override val capacity = 6
    override fun floorArea(): Double {
        return length * length
    }
}

open class RoundHut(
    residents: Int,
	val radius: Double): Dwelling(residents) {
    override val buildingMaterial = "Straw"
    override val capacity = 4
    override fun floorArea(): Double {
        return radius * radius * PI
    }
}

class RoundTower(
    residents: Int,
    radius: Double,
    val floor: Int = 2) : RoundHut(residents, radius) {
    override val buildingMaterial = "Stone"
    override val capacity = 4 * floor
    override fun floorArea(): Double {
        return super.floorArea() * floor
    }
}

// tip. You can use residents++ as a shorthand for residents = residents + 1 to add 1 to the residents variable.


// ✅ List
// ◽ Read-only list: List cannot be modified after you create it.
// ◽ Mutable list: MutableList can be modified after you create it, meaning you can add, remove, or update its elements.

// ✔ When using List or MutableList, you must specify the type of element that it can contain.
// => If the type of the variable can be guessed (or inferred) based on the value on the right hand side of the assignment operator (=), then you can omit the data type of the variable. 
/*
    For example, List<Int> holds a list of integers and List<String> holds a list of Strings. 
    If you define a Car class in your program, you can have a List<Car> that holds a list of Car object instances.
 */

// ✔ Create a List
// : Create a new List using the Kotlin standard library function listOf(), and pass in the elements of the list as arguments separated by commas.
fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6)
    println("List: " + numbers)
    println("Size: ${numbers.size}")
}
// List: [1, 2, 3, 4, 5, 6]
// Size: 6

// ✔ Accessing list elements
// : You could call the get() function with the desired index as numbers.get(0) or you can use shorthand syntax with square brackets around the index as numbers[0]
// : Kotlin also supports first() and last() operations on a list.
// : Another useful list operation is the contains() method to find out if a given element is in the list.
fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6)
    println("List: " + numbers)
    println("Size: ${numbers.size}")
    println("First element: ${numbers[0]}")
    println("Second element: ${numbers.get(1)}")
    println("First element: ${numbers.first()}")
    println("Last element: ${numbers.last()}")
    println("Is 4 in a list?: ${numbers.contains(4)}")
    println("Is 7 in a list?: ${numbers.contains(7)}")
}
/*
    List: [1, 2, 3, 4, 5, 6]
    Size: 6
    First element: 1
    Second element: 2
    Last index: 5
    Last element: 6
    First: 1
    Last: 6
    Contains 4? true
    Contains 7? false 
*/

// ✔ Lists are read-only
// 🤔 Wrong code because of immutability
fun main() {
    val colors = listOf("green", "orange", "blue")
}
colors.add("purple") // Error
colors[0] = "yellow" // Error

// However, there are a number of operations on lists that don't change the list, but will return a new list. 
// => Two of those are reversed() and sorted(). ✨

// ✔ Create a mutable list
// : Mutable lists are of type MutableList, and you can create them by calling mutableListOf().
// ➕ Tip: when you want to make 'empty' list or mutable list!
// => Do this by adding the type in angle brackets right after mutableListOf or listOf.
// ex. val entrees = mutableListOf<String>()
// => Another way you could have fixed the error is by specifying the data type of the variable upfront.
// ex. val entrees: MutableList<String> = mutableListOf()
// ➕ Note: You can use val for a mutable list because the entrees variable contains a reference to the list, and that reference doesn't change even if the contents of the list do.

// ✔ Add elements to a mutable list
// : The add() function returns true if adding the element to the list succeeded, false otherwise.
// : Instead of adding elements one by one using add(), you can add multiple elements at a time using addAll() and pass in a list.
// : Remember to only add elements of the correct data type to a list.

// ✔ Remove elements from a mutable list
// : Call remove() to remove "spaghetti" from the list. Print the list again. 
// => it returns true or false whether adding an element suceeds.
// : You can also specify the index of the element to remove. 
// => The return value of the removeAt(0) is the first element which got removed from the list.
// : If you want to clear the whole list, you can call clear()
// : Kotlin gives you a way to check if a list is empty using isEmpty() function.
// => The isEmpty() method is useful if you want to do an operation on a list or access a certain element, but you want to make sure that the list is not empty first.
fun main() {
    val entrees = mutableListOf<String>()
    println("Entrees: $entrees")

    // Add individual items using add()
    println("Add noodles: ${entrees.add("noodles")}")
    println("Entrees: $entrees")
    println("Add spaghetti: ${entrees.add("spaghetti")}")
    println("Entrees: $entrees")

    // Add a list of items using addAll()
    val moreItems = listOf("ravioli", "lasagna", "fettuccine")
    println("Add list: ${entrees.addAll(moreItems)}")
    println("Entrees: $entrees")

    // Remove an item using remove()
    println("Remove spaghetti: ${entrees.remove("spaghetti")}")
    println("Entrees: $entrees")
    println("Remove item that doesn't exist: ${entrees.remove("rice")}")
    println("Entrees: $entrees")

    // Remove an item using removeAt() with an index
    println("Remove first element: ${entrees.removeAt(0)}")
    println("Entrees: $entrees")

    // Clear out the list
    entrees.clear()
    println("Entrees: $entrees")

    // Check if the list is empty
    println("Empty? ${entrees.isEmpty()}")
}

// ✅ Loop
// ✔ while
val guestsPerFamily = listOf(2, 4, 1, 3)
var totalGuests = 0
var index = 0
while (index < guestsPerFamily.size) {
    totalGuests += guestsPerFamily[index]
    index++
}
println("Total Guest Count: $totalGuests")

// ✔ for
// ➕ you can use the length property of a String to find the number of characters in that String.
fun main() {
    val names = listOf("Jessica", "Henry", "Alicia", "Jose")
	for (name in names) {
    	println("$name - Number of characters: ${name.length}")
	}
}
// ➕ Note: Here are some other variations of what you can do with for loops, including using them with ranges with specific steps (instead of incrementing by 1 each time).
/*
    for (item in list) print(item) // Iterate over items in a list

    for (item in 'b'..'g') print(item) // Range of characters in an alphabet

    for (item in 1..5) print(item) // Range of numbers

    for (item in 5 downTo 1) print(item) // Going backward, Prints: 5 4 3 2 1

    for (item in 3..6 step 2) print(item) // Prints: 3 5
 */