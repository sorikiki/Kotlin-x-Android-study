// ✅ Room
// In Android, data is represented in data classes. This data is accessed and possibly modified using function calls. 
// However, in the database world, you need entities and queries to access and modify data.

// Entity? An entity represents an object or a concept, along with its properties, to store in the database. In our application code, we need an entity class that defines a table, and each instance of that class represents a row in that table. 
// Queries? A query is a request for data or information from a database table or combination of tables, or a request to perform an action on the data. Common queries are for creating, reading, updating and deleting entities. 

// ✨ Room does all the hard work to get from Kotlin data classes to entities that can be stored in SQLite tables, and from function declarations to SQL queries.
/*
    You must define each entity as an annotated data class, and the interactions with that entity as an annotated interface, called a data access object (DAO). 
    Room uses these annotated classes to create tables in the database, and to create queries that act on the database.
 */