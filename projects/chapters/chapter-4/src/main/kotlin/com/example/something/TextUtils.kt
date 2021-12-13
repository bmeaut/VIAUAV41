@file:JvmName("TextUtils")

package util

val LOWERCASE_ALPHABET = "abcdefghijklmnopqrstuvwxyz"

fun isEmpty(str: String?): Boolean {
    return str == null || str.length == 0
}
