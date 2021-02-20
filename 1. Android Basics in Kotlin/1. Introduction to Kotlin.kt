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
}`  
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

// ➕ Override toString method.
/*
    When you print an object instance to the output, the object's toString() method is called. 
    In Kotlin, every class automatically inherits the toString() method. 
    The default implementation of this method just returns the object type with a memory address for the instance.
    You should override toString() to return something more meaningful and user-friendly
 */
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

// ✅ List with <class and inheritance>.

// ❓ when you input a various number of arguments into a class.
// ◽ solution 1: define the list type and call listOf function.
// joinToString: join all the toppings into a single string.
// => To specify a different separator other than a comma, pass in the desired separator string as an argument to the joinToString() method.

open class Item(val name: String, val price: Int)

class Noodles : Item("Noodles", 10) {
    override fun toString(): String {
        return name
    }
}

class Vegetables(val toppings: List<String>) : Item("Vegetables", 5) {
    override fun toString(): String {
        if (toppings.isEmpty()) {
            return "$name Chef's Choice"
        }
        return name + " " + toppings.joinToString()
    }
}

fun main() {
    val noodles = Noodles()
    val vegetables = Vegetables(listOf("cabbage", "sprouts", "onion"))
    println(noodles)
    println(vegetables)
}
// Noodles
// Vegetables cabbage, sprouts, onion

// ◽ solution 2: use 'vararg' modifier.
// : In Kotlin, the vararg modifier allows you to pass a variable number of arguments of the same type into a function or constructor. 
// => In that way, you can supply the different vegetables as individual strings instead of a list.
// ❗ Note: Only one parameter can be marked as vararg and is usually the last parameter in the list.
open class Item(val name: String, val price: Int)

class Noodles : Item("Noodles", 10) {
    override fun toString(): String {
        return name
    }
}

class Vegetables(vararg val toppings: String) : Item("Vegetables", 5) {
    override fun toString(): String {
        if (toppings.isEmpty()) {
            return "$name Chef's Choice"
        }
        return name + " " + toppings.joinToString()
    }
}

fun main() {
    val noodles = Noodles()
    val vegetables = Vegetables("cabbage", "sprouts", "onion")
    println(noodles)
    println(vegetables)
}

// Let's challenge
// ❓ print 1) Order number, 2) Items with price, 3) total price 
open class Order(val orderNumber: Int) {
    private val orderItem: MutableList<Item> = mutableListOf()
    
    fun addItem(item: Item) {
        orderItem.add(item)
    }
    
    fun addItems(items: List<Item>) {
        orderItem.addAll(items)
    }
    
    fun print() {
        println("Order Number: #" + orderNumber)
        var total = 0
        for (item in orderItem) {
            println("${item.name}: $" + item.price)
            total += item.price
        }
        println("Total: $$total")
        println()
    }
}

open class Item(val name: String, val price: Int)

class Noodles : Item("Noodles", 10) {
    override fun toString(): String {
        return name
    }
}

class Vegetables(vararg val toppings: String) : Item("Vegetables", 5) {
    override fun toString(): String {
        return name + " " + toppings.joinToString()
    }
}

fun main() {
    val order1 = Order(1)
    order1.addItem(Noodles())
    order1.print()
    
    val order2 = Order(2)
    order2.addItems(listOf(Noodles(), Vegetables("cabbage", "sprouts", "onion")))
    order2.print()
    
    val order3 = Order(3)
    order3.addItems(listOf(Noodles(), Vegetables("carrots", "potatoes", "onion")))
    order3.print()
}

// ✨ Implement Builder Pattern for Orders
// : Kotlin provides the keyword this to reference the current object instance. 
open class Order(val orderNumber: Int) {
    private val orderItem: MutableList<Item> = mutableListOf()
    
    fun addItem(item: Item): Order {
        orderItem.add(item)
        return this
    }
    
    fun addItems(items: List<Item>): Order {
        orderItem.addAll(items)
        return this
    }
    
    fun print() {
        println("Order Number: #" + orderNumber)
        var total = 0
        for (item in orderItem) {
            println("${item.name}: $" + item.price)
            total += item.price
        }
        println("Total: $$total")
        println()
    }
}

open class Item(val name: String, val price: Int)

class Noodles : Item("Noodles", 10) {
    override fun toString(): String {
        return name
    }
}

class Vegetables(vararg val toppings: String) : Item("Vegetables", 5) {
    override fun toString(): String {
        return name + " " + toppings.joinToString()
    }
}

fun main() {
    val ordersList = mutableListOf<Order>()

    // Add an item to an order
    val order1 = Order(1)
    order1.addItem(Noodles())
    ordersList.add(order1)

    // Add multiple items individually
    val order2 = Order(2)
    order2.addItem(Noodles())
    order2.addItem(Vegetables())
    ordersList.add(order2)

    // Add a list of items at one time
    val order3 = Order(3)
    val items = listOf(Noodles(), Vegetables("Carrots", "Beans", "Celery"))
    order3.addAll(items)
    ordersList.add(order3)

    // Use builder pattern
    val order4 = Order(4)
        .addItem(Noodles())
        .addItem(Vegetables("Cabbage", "Onion"))
    ordersList.add(order4)

    // Create and add order directly
    ordersList.add(
        Order(5)
            .addItem(Noodles())
            .addItem(Noodles())
            .addItem(Vegetables("Spinach"))
    )

    // Print out each order
    for (order in ordersList) {
        order.print()
        println()
    }
}
// The addItem() method returns the same Order instance (with the new state), and you can call addItem() again on it with vegetables. 

// ✅ Collections
//: A collection is a group of related items, like a list of words, or a set of employee records.
// The collection can have the items ordered or unordered, and the items can be unique or not. 
// => You've already learned about one type of collection, lists. Lists have an order to the items, but the items don't have to be unique.
// As with lists, Kotlin distinguishes between mutable and immutable collections. Kotlin provides numerous functions for adding or deleting items, viewing, and manipulating collections.

// ✅ Set: unordered, unique vs list: ordered, ununique
// => both mutable and immutable are available. 
// Another type of collection in Kotlin is a set. It's a group of related items, but unlike a list, there can't be any duplicates, and the order doesn't matter. 
val set1 = setOf(1,2,3)
val set2 = mutableSetOf(3,2,1)
println("$set1 == $set2: ${set1 == set2}") // [1, 2, 3] == [3, 2, 1]: true
// => Even though one is mutable and one isn't, and they have the items in a different order, they're considered equal because they contain exactly the same set of items.

// ◽ One of the main operations you might perform on a set is checking if a particular item is in the set or not with the contains() function.
println("contains 7: ${setOfNumbers.contains(7)}") // contains 7: false

// ◽ As with mathematical sets, in Kotlin you can also perform operations like the intersection (∩) or the union (∪) of two sets, using intersect() or union().
fun main() {
    val set1 = setOf(1, 2, 3)
    val set2 = mutableSetOf(3, 4, 5)
    println(set1.union(set2)) // [1, 2, 3, 4, 5]
    println(set1.intersect(set2)) // [3]
}

// ✅ Map (= dictionary)
// : A map is a set of key-value pairs, designed to make it easy to look up a value given a particular key.
// => Keys are unique, and each key maps to exactly one value, but the values can have duplicates. 
// => Values in a map can be strings, numbers, or objects—even another collection like a list or a set.
fun main() {
    val peopleAges = mutableMapOf<String, Int>(
        "Fred" to 30,
        "Ann" to 23
    )
    println(peopleAges)
    // {Fred=30, Ann=23}

    // To add more entries to the map, you can use the put() function, passing in the key and the value:
    peopleAges.put("Barbara", 42)
    // You can also use a shorthand notation to add entries:
    peopleAges["Joe"] = 51
    println(peopleAges)
    // {Fred=30, Ann=23, Barbara=42, Joe=51}
}  
// => This creates a mutable map of a String (key) to an Int (value), initializes the map with two entries, and prints the items.

// As noted above, the keys (names) are unique, but the values (ages) can have duplicates.
// => What do you think happens if you try to add an item using one of the same keys?
peopleAges["Fred"] = 31
// {Fred=31, Ann=23, Barbara=42, Joe=51}


// ✅ Several Useful Functions
// ◽ forEach
// : forEach, which goes through all the items for you and performs an operation on each one.
peopleAges.forEach { print("${it.key} is ${it.value}, ") }
// => It's similar to the for loop, but a little more compact. Instead of you specifying a variable for the current item, the forEach uses the special identifier it.
// => Note that you didn't need to add parentheses when you called the forEach() method, just pass the code in curly braces {}.
// Fred is 31, Ann is 23, Barbara is 42, Joe is 51,
// => at the end, there's an unnecessary comma.

// ◽ map
// : The map() function (which shouldn't be confused with a map or dictionary collection above) applies a transformation to each item in a collection.
// => Note that you didn't need to add parentheses when you called the forEach() method, just pass the code in curly braces {}.
println(peopleAges.map { "${it.key} is ${it.value}" }.joinToString(", ") ) // Fred is 31, Ann is 23, Barbara is 42, Joe is 51
// => It has the correct output, and no extra comma! 🤔
// => joinToString(", ") adds each item in the transformed collection to a string, separated by , and it knows not to add it to the last item 😊
// 1. peopleAges.map applies a transformation to each item in peopleAges and creates a new collection of the transformed items(no parentheses)
// 2. The part in the curly braces {} defines the transformation to apply to each item. The transformation takes a key value pair and transforms it into a string, for example <Fred, 31> turns into Fred is 31.
// 3. joinToString(", ") adds each item in the transformed collection to a string, separated by , and it knows not to add it to the last item
// 4. all this is chained together with . (dot operator), like you've done with function calls and property accesses in earlier codelabs

// ◽ filter
// : The filter() function returns the items in a collection that match, based on an expression.
val filteredNames = peopleAges.filter { it.key.length < 4 }
println(filteredNames)
// => Again note that the call to filter doesn't need parentheses, and it refers to the current item in the list.
// {Ann=23, Joe=51}


// ✅ Lambdas: a function with no name that can immediately be used as an expression
// 1. Kotlin has something called function types, where you can define a specific type of function based on its input parameters and return value
(Int) -> Int
// 2. For the syntax of a lambda expression, the parameters come first, followed by the function arrow, and followed by the function body. The last expression in the lambda is the return value.
a: Int -> a*3

fun main() {
    val triple: (Int) -> Int = { a: Int -> a * 3 }
    println(triple(5))
}
// 15

// + Note: It's common to have a lambda that has a single parameter, so Kotlin offers a shorthand. Kotlin implicitly uses the special identifier it for the parameter of a lambda with a single parameter.
val triple: (Int) -> Int = { it * 3 }

// ✅ Higher-order functions
// : This just means passing a function (in this case a lambda) to another function, or returning a function from another function.
// => it turns out that map, filter, and forEach functions are all examples of higher-order functions because they all took a function as a parameter.
// => In the lambda passed to this filter higher-order function, it's okay to omit the single parameter and arrow symbol, and also use the it parameter.
peopleAges.filter { it.key.length < 4 }

// if you wanted to sort the list by the length of the strings, you need to write some code to get the length of two strings and compare them. Kotlin lets you do this by passing a lambda to the sortedWith() method.
println(peopleNames.sortedWith { str1: String, str2: String -> str1.length - str2.length })
// => To compare two objects for sorting, the convention is to return a value less than 0 if the first object is less than the second, 0 if they are equal, and a value greater than 0 if the first object is greater than the second.
// => By doing a series of comparison between two Strings at a time, the sortedWith() function outputs a list where the names will be in order of increasing length.
// => Likewise, we need twe parameters(str1, str2), not just one, so we cannot use 'it' keyword.
// cf. It returns a new sorted list, not modify the original list.

// ✅ SAM(Single Abstract Method) conversion
// Long version
calculateButton.setOnClickListener{object: View.OnClickListener {
    override fun onClick(view: View?) {
        calculateTip()
    }
}}
// : Kotlin converts the lambda into an OnClickListener object which implements the single abstract method onClick(). 
// You just need to make sure the lambda function type matches the function type of the abstract function.
// Short version
calculateButton.setOnClickListener { view -> calculateTip() }
// Since the view parameter is never used in the lambda, the parameter can be omitted.
calculateButton.setOnClickListenr { calculateTip() }

// ✅ Example: pick a series of words which starts with a specific word.
fun main() {
    val words = listOf("about", "acute", "awesome", "balloon", "best", "brief", "class", "coffee", "creative")
    val filteredWords = words.filter { it.startsWith("b", ignoreCase = true) }
        .shuffled()
        .take(2)
        .sorted()
    println(filteredWords)
}
// ✔ startsWith(): It returns true if a string starts with the specified string. You can also tell it to ignore case, so "b" will match "b" or "B".
// ✔ shuffled(): make a copy of a collection with the items randomly shuffled.
