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
    return s.trim()
        .split("\\s+"
            .toRegex())
        .reversed()
        .joinToString(" ")
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
// 6.) Given a String x, return true if x is a palindrome, and false otherwise. 
fun isPalindrome(word: String): Boolean {
    return word == word.reversed()
} 

// 7.) Given an integer x, return true if x is a palindrome, and false otherwise. 
fun isIntPalindrome(x: Int): Boolean {
    if(x < 0) return false

    var temp = x
    var reversed = 0

    while(temp != 0) {
        val lastDigit = temp % 10
        reversed = reversed * 10 + lastDigit
        temp /= 10
    }

    return x == reversed
}
/* 8.) Roman to Integer
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together. 12 is
written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However,
the numeral for four is not IIII. Instead, the number four is written as IV. Because the
one is before the five we subtract it making four. The same principle applies to the
number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.
*/
fun romanToInt(s: String): Int {
    val romanValues = mapOf(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )
    var result = 0
    for (i in s.indices) {
        val currentValue = romanValues[s[i]]!!

        if (i < s.length - 1 && currentValue < romanValues[s[i + 1]]!!) {
            result -= currentValue
        } else {
            result += currentValue
        }
    }

    return result
}

 /* 9.) Valid Anagram
Given two strings s and t, return true if t is an anagram of s, and false otherwise.
An anagram means:
Same letters
Same number of each letter
Order does not matter */

fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length) return false

    val charCounts = IntArray(26)

    for (char in s) {
        charCounts[char -'a']++
    }

    for (char  in t) {
        charCounts[char - 'a']--
    }

    for (count in charCounts) {
        if (count != 0) return false
    }

    return true
}
