package subtyping

open class SavingsAccount {
    open fun deposit(amount: Int): Number {
        require(amount >= 0)

        TODO("Compute and return the updated balance")
    }
}

class AccessibleSavingsAccount : SavingsAccount() {
    private var balance = 0

    override fun deposit(amount: Int): Int {
        balance += amount
        return balance
    }
}

fun useAccount(account: SavingsAccount) {
    account.deposit(100)
}

fun main() {
    useAccount(SavingsAccount())
    useAccount(AccessibleSavingsAccount())
}
