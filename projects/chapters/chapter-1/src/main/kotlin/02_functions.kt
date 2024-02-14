fun main() {

    /*
    int add(int x, int y) {
        return x + y;
    }
    */

    fun add(x: Int, y: Int): Int {
        return x + y
    }


    fun noReturnValue() {
        /* Empty */
    }

    val result: Unit = noReturnValue()
    println(result) // kotlin.Unit

    fun register(
        username: String,
        password: String = "12345678",
        email: String = "",
    ) {
        // Pretend that there's something useful here.
        println("register: $username; $password; $email")
    }

    register("piglet", "0h_d34r", "piglet@hundred-acre-wood.co.uk")
    register("owl", "tea_party")
    register("eeyore")

    register("tigger", email = "tigger@hundred-acre-wood-co.uk")

}
