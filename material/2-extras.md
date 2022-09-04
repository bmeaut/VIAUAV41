# Chapter 2: Object-Oriented Programming - Extras

> This _Extras_ page provides additional detail for the [main chapter](./2.md). The material here is optional to study. Read it at your own leisure, it will not be part of the test.

On this page:

* [Without primaries](#without-primaries)
* [Class delegation (implementation by delegation)](#class-delegation-implementation-by-delegation)

## Without primaries

Chapter 2 covered various ways of [designing constructors for classes](./2.md#constructors), using primary and secondary constructors.

One last approach you can use when creating classes is to not have a primary constructor at all. This is useful when converting the inputs of the various required constructors into one canonical form is just too cumbersome to do.

In this case, the strict initialization requirements will apply to the secondary constructors. If you declare a property that's not initialized at its declaration or in an initializer block, it must be initialized by *all* secondary constructors.

You may still use those two ways of initializing properties that were used in the primary constructor before, and these will run before each of your secondary constructors' bodies. This means that any initialization that makes use of constructor parameters will now have to be placed inside the secondary constructors, and potentially needs to be duplicated. This is the downside of not using a primary constructor: losing its superpowers of accessing constructor parameters in the class body.

For example, you can no longer initialize `age` this way, since the initialization of `year` will only happen after the initializer block has run, in the body of each secondary constructor:

```kotlin
class Car {
    val model: String
    val year: Int
    var miles: Double = 0.0
    val age: Int

    init {
        age = getCurrentYear() - year
    }

    constructor(
            model: String,
            year: Int
    ) {
        this.model = model
        this.year = year
    }

    constructor(
            model: String,
            year: String,
            mileage: String
    ) {
        this.model = model
        this.year = year.toInt()
        this.miles = mileage.toDouble()
    }
}
```

Naturally, this is checked at compile time, and results in an error:

> *e: Variable 'year' must be initialized*

One solution for this would be to move the `age` initialization into each constructor separately:

```kotlin
constructor(
        model: String,
        year: Int,
) {
    this.model = model
    this.year = year
    this.age = getCurrentYear() - year
}

constructor(
        model: String,
        year: String,
        mileage: String,
) {
    this.model = model
    this.year = year.toInt()
    this.miles = mileage.toDouble()
    this.age = getCurrentYear() - year
}
```

In certain cases, you can get rid of this kind of duplication by calling one secondary constructor from another. In this case, the first constructor will guarantee that the class is already fully initialized, and therefore the second one has no strict initialization requirements imposed on it.

```kotlin
constructor(
        model: String,
        year: Int,
) {
    this.model = model
    this.year = year
    age = getCurrentYear() - year
}

constructor(
        model: String,
        year: String,
        mileage: String,
) : this(model, year.toInt()) {
    miles = mileage.toDouble()
}
```

![A secondary constructor delegating to another.](./images/2_secondary_delegation.png)

Visually, we've now lost the single node in the graph that we can delegate safe initialization to. Therefore, each secondary constructor has to perform correct initialization on its own merits, or call another, already known-to-be-correct secondary constructor. We now have a forest instead of a tree.

![A forest of secondary constructors](./images/2_secondary_constructor_forest.png)

## Class delegation (implementation by delegation)

Another significant item of the Effective Java book is *Item 18: Favor composition over inheritance*. To oversimplify the item:

> Inheritance is a popular way to reuse code, by extending a class that has the functionality you need. However, it's also very error-prone. It violates encapsulation, because the subclass depends on the internal implementation details of the superclass.

Here's the original example used in the book (it would be very, very similar in Kotlin - try converting it yourself!):

```java
// Broken - Inappropriate use of inheritance!
public class InstrumentedHashSet<E> extends HashSet<E> {
    // The number of attempted element insertions
    int addCount = 0;

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
}
```

This is a `HashSet` subclass that's supposed to count the number of elements (attempted to be) inserted into it, however, it's broken. It turns out that the superclass uses the `add` method in its implementation of `addAll`, causing this class to count every element added to it using `addAll` twice:

```kotlin
val set = InstrumentedHashSet<Int>()
set.addAll(listOf(1, 2, 3, 4, 5))
println(set.addCount) // 10
```

You could fix this by assuming that this will always be the case, and simply remove the override of `addAll`. However, this would break if the implementation of the superclass changed in a newer version.

So what can you do instead? As the item suggests, you can use composition over inheritance. *Contain* an instance of `HashSet` in your own custom implementation, instead of *extending* it:

```java
public class InstrumentedSet<E> implements Set<E> {
    int addCount = 0;

    private final Set<E> set;
    public InstrumentedSet(Set<E> set) { this.set = set; }

    public boolean add(E e) {
        addCount++;
        return set.add(e);
    }

    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return set.addAll(c);
    }
}
```

With this change, we'd have to provide the `Set` to wrap as a parameter at the use site:

```kotlin
val set = InstrumentedSet<Int>(HashSet())
set.addAll(listOf(1, 2, 3, 4, 5))
```

The problem with this solution, then, is that we're now implementing the entirety of the `Set` interface ourselves. We have `add` and `addAll` covered, but this interface requires twelve more methods! This would all be [boilerplate](https://en.wikipedia.org/wiki/Boilerplate_code), where each method would just forward calls to the contained `set` instance.

Effective Java proposes the introduction of an intermediate `ForwardingSet` class, which `InstrumentedSet` can then inherit from, and override just the two methods that it needs to intercept.

```java
public class ForwardingSet<E> implements Set<E> {
    private final Set<E> s;
    public ForwardingSet(Set<E> s) { this.s = s; }
    public void clear() { s.clear(); }
    public boolean contains(Object o) { return s.contains(o); }
    public boolean isEmpty() { return s.isEmpty(); }
    /* Lots of more methods... */
}
```

This is probably the best that Java can do. Let's implement `InstrumentedSet` in Kotlin instead. We'll make use of a new language feature: class delegation. This allows us to implement an interface by delegating it to another object, which is exactly what we're trying to achieve here.

```kotlin
class InstrumentedSet<E>(private val set: MutableSet<E>) : MutableSet<E> by set
```

> [`MutableSet`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/) is Kotlin's equivalent interface to [`java.util.Set`](https://docs.oracle.com/javase/8/docs/api/java/util/Set.html). We'll study Kotlin's collection types and API in detail later on.

Now our `InstrumentedSet` implements the `MutableSet` interface via the `set` property. Whenever a method is invoked on it, it will simply invoke the same method on the contained `set`. This is a one-liner implementation of `FowardingSet`!

All that's left to do then is to modify the `add` and `addAll` methods:

```kotlin
class InstrumentedSet<E>(
        private val set: MutableSet<E> = HashSet()
) : MutableSet<E> by set {
    var addCount = 0

    override fun add(element: E): Boolean {
        addCount++
        return set.add(element)
    }

    override fun addAll(elements: Collection<E>): Boolean {
        addCount += elements.size
        return set.addAll(elements)
    }
}
```

We've also added a default value for the `set` parameter, a simple `HashSet`. Clients can still pass in other `MutableSet` implementations, but they are no longer required to do so, making the class more convenient to use.

And that's it, we have a working `InstrumentedSet` implementation. All the other `MutableSet` methods that we haven't implemented will continue to forward to `set`. Everything works as expected now:

```kotlin
val set = InstrumentedSet<Int>()
set.addAll(listOf(1, 2, 3, 4, 5))
println(set.addCount) // 5
```

Interestingly, this feature - also referred to as *implementation by delegation* - has been named as the "worst" feature in Kotlin by the lead language designer, Andrey Breslav on several occasions (e.g. during the [KotlinConf 2018 closing panel discussion](https://youtu.be/heqjfkS4z2I?t=646)).

## Summary

Classes may have only secondary constructors, as long as they all (directly or indirectly) initialize all properties of the class.

Kotlin's implementation by delegation feature makes forwarding many calls to a contained instance easier, encouraging composition over inheritance.

## Sources

- [Effective Java - Joshua Bloch](https://www.amazon.co.uk/Effective-Java-Joshua-Bloch-ebook/dp/B078H61SCH/)
- [Delegation](https://kotlinlang.org/docs/reference/delegation.html)
