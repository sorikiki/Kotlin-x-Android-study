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
