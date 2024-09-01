package ru.zhogin.app.search.common

internal fun vacanciesRuEnding(amount: Int) : String {
    return if (amount.toString().endsWith("11")) "вакансий"
    else if (amount.toString().endsWith("12")) "вакансий"
    else if (amount.toString().endsWith("13")) "вакансий"
    else if (amount.toString().endsWith("14")) "вакансий"
    else if (amount.toString().endsWith("1")) "вакансия"
    else if (amount.toString().endsWith("2")) "вакансии"
    else if (amount.toString().endsWith("3")) "вакансии"
    else if (amount.toString().endsWith("4")) "вакансии"
    else "вакансий"
}
internal fun peopleRuEnding(amount: Int): String {
    return if (amount.toString().endsWith("11")) "человек"
    else if (amount.toString().endsWith("12")) "человек"
    else if (amount.toString().endsWith("13")) "человек"
    else if (amount.toString().endsWith("14")) "человек"
    else if (amount.toString().endsWith("1")) "человек"
    else if (amount.toString().endsWith("2")) "человека"
    else if (amount.toString().endsWith("3")) "человека"
    else if (amount.toString().endsWith("4")) "человека"
    else "человек"
}