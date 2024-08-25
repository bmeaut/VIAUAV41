# Chapter 4: Functional Programming - Extras

> This _Extras_ page provides additional detail for the [main chapter](./4.md). The material here is optional to study. Read it at your own leisure, it will not be part of the test.

### Member extensions

Extensions may also be declared *inside a class* (or interface, or object) as a member. In these cases, the extensions behave differently from top-level extensions.

First of all, they will have two receivers available as `this`. The *dispatch receiver*, which is the instance of the enclosing class, and the *extension receiver*, the instance of whatever type the extension was defined on. In ambiguous cases, the extension receiver will take precedence (in other words, the closer `this` scope):

```kotlin
class Queue(val length: Int) {
    fun String.process() {
        println(length) // String's length
        println(this.length) // String's length
        println(this@Queue.length) // Queue's length
    }
}
```

The dispatch receiver can be accessed with a [qualified `this`](https://kotlinlang.org/docs/this-expressions.html#qualified), which is also used when choosing from multiple implicit receivers in other situations, such as in nested classes.

These member extensions are *not* static, instead, they are regular member functions under the hood. This means that overriding and dynamic dispatch *does* happen, although not by the type that is extended, but by the containing type.

You can make these extensions `open` or `abstract`, and have subtypes implement them:

```kotlin
abstract class Validator {
    protected abstract fun String.isValid(): Boolean

    fun validate(str: String?): Boolean {
        return str != null && str.isValid()
    }
}

class EmailValidator : Validator() {
    override fun String.isValid(): Boolean {
        return this.contains('@')
    }
}
```

While this is technically possible, it's often simpler to use regular functions that take the receiver as an explicit parameter.

There _are_ valid use cases for these types of extensions. These declarations are only visible within the class that they're declared in, which is a way of scoping extensions that won't be widely used in a project.

However, they also come with some surprising behaviour, which can be avoided by using private, top-level extensions, or even function-local (covered later, [in chapter 6](./6.md#local-functions)) extensions instead, if applicable.

