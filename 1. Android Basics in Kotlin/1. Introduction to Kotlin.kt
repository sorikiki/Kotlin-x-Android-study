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

