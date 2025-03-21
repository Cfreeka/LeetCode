

/* 1.)  Merging two string alternatively.
You are given two strings word1 and word2. Merge the strings by adding letters
in alternating order, starting with word1. If a string is longer than the other,
append the additional letters onto the end of the merged string.

Return the merged string.
*/

fun mergeAlternately(word1: String, word2: String): String {

    val mergeString = StringBuilder()
    val minLength = minOf(word1.length, word2.length)

    for (i in 0 until minLength) {
        mergeString.append(word1[i])
        mergeString.append(word2[i])
    }

    if (word1.length > word2.length) {
        mergeString.append(word1.substring(minLength))
    } else {
        mergeString.append(word2.substring(minLength))
    }

    return mergeString.toString()
}

/* 2.) Greatest Common Divisor of Strings.
For two strings s and t, we say "t divides s" if and only if s = t + t +
t + ... + t + t (i.e., t is concatenated with itself one or more times).
Given two strings str1 and str2, return the largest string x such that x
divides both str1 and str2.
*/

fun gcdOfStrings(str1: String, str2: String): String {

    if ((str1 + str2) != (str2 + str1)) {
        return ""
    }
    val gcdLength = gcd(str1.length, str2.length)

    return str1.substring(0, gcdLength)
}

fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a else gcd(b, a % b)
}


