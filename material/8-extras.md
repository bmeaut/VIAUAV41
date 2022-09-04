# Chapter 8: Chapter 8: Operators, Conventions, and Delegates - Extras

> This _Extras_ page provides additional detail for the [main chapter](./8.md). The material here is optional to study. Read it at your own leisure, it will not be part of the test.

Chapter 8 covered how you can [create your own lazy delegate](./8.md#implementing-lazy), which was a read-only delegate.

In these extras, you'll see how you can [implement read-write delegates](#implementing-a-read-write-delegate), and learn [a more advanced way](#the-providedelegate-function) to create delegate instances. 

### Implementing a read-write delegate

Besides [`ReadOnlyProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-only-property/), the Standard Library has a second delegate interface, [`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/), which can be used for properties that are either read-only (`val`) or read-write (`var`), as it also has a `setValue` method. This method takes a `V` as its last parameter, and returns `Unit`.

```kotlin
public interface ReadWriteProperty<in T, V> : ReadOnlyProperty<T, V> {
    public override operator fun getValue(thisRef: T, property: KProperty<*>): V
    public operator fun setValue(thisRef: T, property: KProperty<*>, value: V)
}
```

Let's implement another delegate, using this read-write interface. We'll create a `PersistentString` delegate, which will store a `String` value on disk, transparently. We'll implement the interface above, and in each method, use the `string.txt` file to store our value.

```kotlin
class PersistentString : ReadWriteProperty<Any?, String> {
    override operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        val file = File("string.txt")
        if (file.exists()) {
            return file.readText()
        }
        return ""
    }

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        val file = File("string.txt")
        file.writeText(value)
    }
}
```

Let's add a factory function for this delegate, too:

```kotlin
fun persistentString(): ReadWriteProperty<Any?, String> = PersistentString()
```

This is a working implementation, that can store our `String` value persistently:

```kotlin
// first run
var str by persistentString()
println(str) // ""
str = "hello"

// next run of the application...
var str by persistentString()
println(str) // "hello"
```

There's the issue of this `PersistentString` operating on a single, hardcoded file. What if we want to use multiple persisted `String` properties simultaneously? We could introduce a constructor parameter to the delegate class, where the filename could be provided. We can do one better: use the name of the property as the filename!

Here's where the second parameter of the getter and setter functions comes into play. This parameter is named `property`, and has the type of [`KProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/).

Java has reflection facilities such as the [`java.lang.Class`](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html) and [`java.lang.reflect.Method`](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Method.html) types, which are a meta representation of classes and methods, respectively. These types let you inspect the structure of classes dynamically, at runtime.

Kotlin comes with its own suite of reflection related features and types, and the `KProperty` type is one of these. It's a meta representation of the property that's being delegated, and contains information about it such as whether it's a `const`, whether it's `lateinit`, what annotations are on it, or what its name is - which is just what we need!

Inside each of our methods, we can use `property.name` to get the name of the property:

```kotlin
override operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
    val file = File("${property.name}.txt")
    /* ... */
}
```

With the client code above, this would make our property use the `str.txt` file as its storage.

Reading the value of this `String` from a file every time it's accessed within a single run of the application is expensive. We can upgrade our class with a property that acts as a read cache for the `String` in memory.

How do we initialize this property? We could make it nullable, and perform lazy initialization on it in `getValue`:

```kotlin
class PersistentString : ReadWriteProperty<Any?, String> {
    private var cachedValue: String? = null

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        if (cachedValue == null) {
            val file = File("${property.name}.txt")
            cachedValue = if (file.exists()) file.readText() else ""
        }
        return cachedValue!!
    }

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        cachedValue = value
        val file = File("${property.name}.txt")
        file.writeText(value)
    }
}
```

But what if we wanted to initialize this value when the delegate is created? The problem here is that the `property` parameter wouldn't be available for us in an initializer - we only get it when the getter or setter is first invoked, which is later than construction time.

```kotlin
class PersistentString() : ReadWriteProperty<Any?, String> {
    private var cachedValue: String
    
    init {
        val file = File("${property.name}.txt") // e: Unresolved reference: property
        cachedValue = if (file.exists()) file.readText() else ""
    }
    
    // ...
}
```

### The `provideDelegate` function

Here's where `provideDelegate` comes into play. This is a third operator you can make use of when creating your own delegates, and it allows you to define factory classes for your delegates - an extra layer of indirection.

To solve our troubles with `PersistedString`, we can create a `PersistentStringFactory` that will be tasked with creating our delegates. The `provideDelegate` function, just like the previous two, has a well-defined signature by convention - the parameters of which you'll already be familiar with: it receives the `thisRef` and `property` parameters, and can use these to create a `ReadWriteProperty`:

```kotlin
object PersistentStringFactory {
    operator fun provideDelegate(
            thisRef: Any?,
            property: KProperty<*>
    ): ReadWriteProperty<Any?, String> {
        return PersistentString(property.name)
    }
}
```

The `PersistentString` class has to be updated at this point, to receive the key to use as a parameter, but that's easy enough:

```kotlin
class PersistentString(key: String) : ReadWriteProperty<Any?, String> {
    private var cachedValue: String

    init {
        cachedValue = File("${key}.txt")
                .takeIf { it.exists() }
                ?.readText()
                ?: ""
    }

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return cachedValue
    }
    
    // ...
}
```

> Note the way we've refactored the initializer to a more functional code style, using [`takeIf`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/take-if.html) to convert the question of whether the file exists into a question of nullability.

How do we use this factory? We just delegate to it, as if it was the delegate instance backing our property. The compiler will then prompt the factory to create a delegate instance, and forward any accessor calls to that instance.

Like the previous delegate conventions, this one has its own corresponding interface too ([since Kotlin 1.4](https://kotlinlang.org/docs/whatsnew14.html#delegated-properties-improvements)). This interface is called [`PropertyDelegateProvider`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-property-delegate-provider/), and we can make our factory implement it like so:

```kotlin
object PersistentStringFactory
    : PropertyDelegateProvider<Any?, ReadWriteProperty<Any?, String>> {
    override operator fun provideDelegate(
        thisRef: Any?,
        property: KProperty<*>
    ): ReadWriteProperty<Any?, String> {
        return PersistentString(property.name)
    }
}
``` 

The first type parameter here, again, is the class in which the delegate may be used in. The second parameter is the delegate type it produces.

The `persistentString` factory function can now return this factory type instead of a `ReadWriteProperty`, to keep the factory a private implementation detail:

```kotlin
fun persistentString(): PropertyDelegateProvider<Any?, ReadWriteProperty<Any?, String>> {
    return PersistentStringFactory
}
```

The use site of `persistentString` remains the same, we just delegate the property to whatever object the function returns, which is now the single factory instance:

```kotlin
// first run
var str by persistentString()
println(str) // ""
str = "hello"
 
// next run of the application...
var str by persistentString()
println(str) // "hello"
```

Introducing this factory also lets us fix a bug that we've introduced by caching our value inside the delegate. With the current implementation, if multiple strings with the same name are declared, only the one that a write was performed through will have its cached value updated, while the rest will hold outdated values in memory. They won't read from file after the initialization, because it's assumed that the value in the file only changes when their setter is invoked.

This factory gives us control over the `PersistentString` instances being created, and we can ensure that only a single one of them exists for each name:

```kotlin
object PersistentStringFactory 
    : PropertyDelegateProvider<Any?, ReadWriteProperty<Any?, String>> {
    private val keys = mutableSetOf<String>()

    override operator fun provideDelegate(
        thisRef: Any?,
        property: KProperty<*>
    ): ReadWriteProperty<Any?, String> {
        require(property.name !in keys) { "No duplicates allowed in PersistentStrings" }
        keys += property.name
        return PersistentString(property.name)
    }
}
```
