private fun saveUser(firstName: String?, lastName: String?) {
    fun validate(name: String?) {
        if (name.isNullOrBlank() || name.any { it.isDigit()}) {
            throw IllegalArgumentException("Invalid input $name")
        }
    }

    validate(firstName)
    validate(lastName)

    println("Saving user $firstName $lastName...")
}
