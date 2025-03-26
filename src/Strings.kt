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

/* 3.) Reverse Vowels of a String
Given a string s, reverse only all the vowels in the string and return it.
The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both
lower and upper cases, more than once.
*/
fun reverseVowels(s: String): String {

    val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
    val chars = s.toCharArray()  // Convert string to mutable character array
    var left = 0
    var right = chars.size - 1

    while (left < right) {
        // Move left pointer until it finds a vowel
        while (left < right && chars[left] !in vowels) {
            left++
        }
        // Move right pointer until it finds a vowel
        while (left < right && chars[right] !in vowels) {
            right--
        }
        // Swap the vowels
        if (left < right) {
            chars[left] = chars[right].also { chars[right] = chars[left] }
            left++
            right--
        }
    }
    return String(chars)
}

/* 4.) Reverse Words in a String
Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will
be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between
two words. The returned string should only have a single space separating the
words. Do not include any extra spaces.
*/
fun reverseWords(s: String): String {
    return s.trim().split("\\s+".toRegex()).reversed().joinToString(" ")
}

/* 5.) String Compression
Given an array of characters chars, compress it using the following algorithm:
Begin with an empty string s. For each group of consecutive repeating characters in chars:
If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, be stored
in the input character array chars. Note that group lengths that are 10 or longer will
be split into multiple characters in chars.
After you are done modifying the input array, return the new length of the array.

You must write an algorithm that uses only constant extra space.
*/

fun compress(chars: CharArray): Int {
    var write = 0
    var read = 0

    while (read < chars.size) {
        val char = chars[read]
        var count = 0

        while (read < chars.size && chars[read] == char) {
            read++
            count++
        }

        chars[write++] = char

        if (count > 1) {
            for (digit in count.toString()) {
                chars[write++] = digit
            }
        }
    }
    return write
}


















