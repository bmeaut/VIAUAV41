# Chapter 10: Generics - Extras

> This _Extras_ page provides additional detail for the [main chapter](./10.md). The material here is optional to study. Read it at your own leisure, it will not be part of the test.

Chapter 10 covered the basics of [variance](./10.md#variance). In these extras, you'll learn more about some of the more exciting ways of using variance in Kotlin.

### Use-site variance

What you've seen so far was *declaration-site variance*. The variance of the generic type parameter is specified at the declaration of the class that it belongs to, and this variance will apply to all usages of the generic class. In the example above, this means that every `PickupPoint` will be covariant, and every `HandoffPoint` will be contravariant on its type parameter.

For invariant types, such as `Garage` or `MutableList`, we might still want to occasionally have some variance on their type parameters, when our specific use case allows it.

Let's say we want to empty a `Garage` with a function like this:

```kotlin
fun emptyGarage(garage: Garage<Car>) {
    while (true) {
        val car: Car = garage.take() ?: break
        println("Removed $car")
    }
}
```

As we've seen before, we can't pass a `Garage<Tesla>` into this function. While it always outputs `Car` instances, meaning our current usage would be safe, it can't accept any kind of `Car` instance in its `park` function, which would make using it as a `Garage<Car>` potentially unsafe.

We've seen that we can introduce new interfaces that will include just a part of the functionality of the `Garage` type, but that's quite a bit of extra work. What if we could simply promise the compiler that we won't use the `Garage` instance to park cars inside this specific function?

This is exactly what use-site variance allows us to do. Specify variance for a single use of a type which is otherwise invariant. To achieve covariance on the use-site, we can use `out` keyword:

```kotlin
fun emptyGarage(garage: Garage<out Car>) {
    while (true) {
        val car: Car = garage.take() ?: break
        println("Removed $car")
    }
}

val teslaGarage: Garage<Tesla> = ...
emptyGarage(teslaGarage)
```

This allows us to safely use any method where the type parameter is in an *out* position.

What ensures that we won't park just any `Car` into this garage? It turns out that wherever the type parameter is used in an *in* position, it's changed to the `Nothing` type when you employ use-site covariance:

![Can't park Nothing](images/10_park_nothing.png)

This prevents us from calling this method altogether, which is the price to pay for the covariance. The compiler effectively removed this method from the interface, by making it uncallable.

---

You can also add contravariance after-the-fact with use-site variance. Let's write a function that parks a `Tesla` in a `Garage<Tesla>`, like this:

```kotlin
fun parkTesla(garage: Garage<Tesla>) {
    garage.park(Tesla())
}
```

Again, this function can't be called with a `Garage<Car>`, as we might take a car from the garage inside the function while expecting to receive a `Tesla` specifically. But we can add use-site variance to get contravariance, and enable our limited use case of only placing cars in the garage:

```kotlin
fun parkTesla(garage: Garage<in Tesla>) {
    garage.park(Tesla())
}

val garage: Garage<Car> = ...
parkTesla(garage)
```

Methods where the type parameter appears in *in* positions are safe to use in this case, but what about the ones where it's in the *out* position? You can still call them, but it won't be guaranteed that you'll get a `Tesla` out of them. Instead, the type parameter in these positions is replaced by `Any?`:

![Trying to take a car from the use-site contravariant garage](images/10_take_any.png)

This means that with use-site contravariance, all functions remain callable on the object that the original type had. For this to be safe, you lose some typing when using methods that have the type parameter in the *out* position.

The special, restricted types that you get when using use-site variance are called *projected types*, and the mechanism creating them is *type projection*. For a nice visual recap of variance and projections, [take a look at this article](https://typealias.com/guides/ins-and-outs-of-generic-variance/).

### Star projections

You may want to refer to a `Garage` in your code, regardless of what its type arguments are. This would be useful, for example, if the type contained some methods that don't rely on its generic parameters (for example, a `count()` method that simply returns an `Int`).

The language feature that gives you a type with no specific type argument is a *star projection*, and it looks like this: `Garage<*>`.

```kotlin
fun useGarage(garage: Garage<*>) {
    // Use non-generic functions?
}
```

All `Garage` types are a subtype of `Garage<*>`, regardless of their type argument.

```kotlin
useGarage(garage) // Garage<Car>
useGarage(teslaGarage) // Garage<Tesla>
```

Using this projected type will have to come with serious restrictions: since you don't know the type parameter of the `Garage` instance you're handling, you can't use the type parameter in neither *in* nor *out* positions.

The restriction for *in* positions will be a familiar one: the type parameter is replaced with `Nothing` whenever it's in an *in* position, making the method impossible to call:

![Star projected "in" type parameter](images/10_star_park.png)

Interestingly, the type parameters in the *out* position don't simply get replaced with `Any?` in this case. This projection is a bit smarter than the one that deals with contravariance, and it knows that since there's a constraint on the type parameter of `Garage` (`T : Car`) it can at most be as broad as that constraint, so it deducts `Car?` as the return type for `take`.

![Star projected "out" type parameter](images/10_star_take.png)

For even more about star projections, and neat illustrations, [read this article](https://typealias.com/guides/star-projections-and-how-they-work/).

## Sources

- [Star-Projections and How They Work](https://typealias.com/guides/star-projections-and-how-they-work/)
