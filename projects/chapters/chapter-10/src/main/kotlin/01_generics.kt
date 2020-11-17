package generics

class StringList {
    fun add(string: String) {
        TODO()
    }

    fun get(index: Int): String {
        TODO()
    }
}

fun useNonGenericList1() {
    val stringList = StringList()
    stringList.add("testing")
    val test: String = stringList.get(0)
}

class ObjectList {
    fun add(t: Any) {
        TODO()
    }

    fun get(index: Int): Any {
        TODO()
    }
}

fun useNonGenericList2() {
    val stringList = ObjectList()
    stringList.add("testing") // remember, Strings only!
    val test: String = stringList.get(0) as String // hope it's a String
}

class GenericList<T> {
    fun add(t: T) {
        TODO()
    }

    fun get(index: Int): T {
        TODO()
    }
}

fun useGenericList() {
    val stringList = GenericList<String>()
    stringList.add("testing")
    val test: String = stringList.get(0)
}

inline fun <T> List<T>.forEach(actions: (T) -> Unit) {
    for (element in this) actions(element)
}

fun <T> create(): T { TODO("create an instance of T somehow") }

fun useCreate() {
    create<String>()
    create<Int>()
}
