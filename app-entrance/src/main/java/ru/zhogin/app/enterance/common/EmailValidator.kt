package ru.zhogin.app.enterance.common

private const val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
internal fun isValidEmail(email: String): Boolean {
    return email.matches(emailRegex.toRegex())
}