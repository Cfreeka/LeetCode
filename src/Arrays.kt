import java.util.PriorityQueue

class ArraySolutions {

    /* 1.)
    You are given two integer arrays nums1 and nums2,
    sorted in non-decreasing order, and two integers m and n,
    representing the number of elements in nums1 and nums2 respectively.

    Merge nums1 and nums2 into a single array sorted in non-decreasing order.

    The final sorted array should not be returned by the function,
    but instead be stored inside the array nums1. To accommodate this,
    nums1 has a length of m + n, where the first m elements denote the
    elements that should be merged, and the last n elements are set to
    0 and should be ignored. nums2 has a length of n.
    */


    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {

        var p1 = m - 1   // Getting the last index of nums1
        var p2 = n - 1   // Getting the last index of nums2
        var p = m + n - 1  // Getting the last index of the both the nums1 and nums2


        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1]
                p1--
            } else {
                nums1[p] = nums2[p2]
                p2--
            }
            p--
        }
        while (p2 >= 0) {
            nums1[p] = nums2[p2]
            p2--
            p--
        }

    }

    /* 2.)
    You are given k sorted integer arrays. Write a function to merge
    them into a single sorted array efficiently.
     */
    fun mergeKSortedArrays(arrays: List<IntArray>): IntArray {

        // Min-Heap that sorts the values first
        val minHeap = PriorityQueue<HeapNode> { a, b -> a.value - b.value }
        val result = mutableListOf<Int>()

        // Step 1: Add the element of the first array to the heap.
        for (i in arrays.indices) {
            if (arrays[i].isNotEmpty()) {
                minHeap.add(HeapNode(arrays[i][0], i, 0)) // (value, arrayIndex, elementIndex)
            }
        }

        //Step 2:  Extract the smallest element and insert the next element from the same array.
        while (minHeap.isNotEmpty()) {
            val node = minHeap.poll() // Extract the smallest element
            result.add(node.value) // Append to result array

            val nextIndex = node.elementIndex + 1
            if (nextIndex < arrays[node.arrayIndex].size) {
                // Insert the next element from the same array into the heap
                minHeap.add(HeapNode(arrays[node.arrayIndex][nextIndex], node.arrayIndex, nextIndex))
            }
        }

        return result.toIntArray()
    }

    data class HeapNode(
        val value: Int,
        val elementIndex: Int,
        val arrayIndex: Int
    )


    /* 3.)
    You are given the heads of two sorted linked lists, list1 and list2.
    Merge the two lists into one sorted linked list and return its head.
    */
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        val dummy = ListNode(0)
        var curr = dummy

        var p1 = list1
        var p2 = list2

        while (p1 != null && p2 != null) {
            if (p1.`val` < p2.`val`) {
                curr.next = p1
                p1 = p1.next
            } else {
                curr.next = p2
                p2 = p2.next
            }
            curr = curr.next!!
        }
        curr.next = p1 ?: p2
        return dummy.next

    }

    /* 4.)
    You are given the heads of two sorted linked lists, list1 and list2.
    Write a function to merge the two lists into one sorted linked list and return its head.
    */

    class Node(var `val`: Int) {
        var next: Node? = null
    }

    fun mergeSortedLinkedLists(nodeList1: Node?, nodeList2: Node?): Node? {
        val nodeInstance = Node(0)
        var current = nodeInstance

        var p1 = nodeList1
        var p2 = nodeList2

        while (p1 != null && p2 != null) {
            if (p1.`val` < p2.`val`) {
                current.next = p1
                p1 = p1.next
            } else {
                current.next = p2
                p2 = p1.next
            }
            current = current.next!!
        }
        current.next = p1 ?: p2
        return nodeInstance.next
    }

    /* 5.)
    Kids With the Greatest Number of Candies There are n kids with candies.
    You are given an integer array candies,where each candies[i] represents
    the number of candies the ith kid has,and an integer extraCandies, denoting
    the number of extra candies that you have.

    Return a boolean array result of length n, where result[i] is true if, after
    giving the ith kid all the extraCandies, they will have the greatest number
    of candies among all the kids, or false otherwise.

    Note that multiple kids can have the greatest number of candies.
     */

    fun kidsWithCandies(candies: IntArray, extraCandies: Int): List<Boolean> {

        val maxCandies = candies.maxOrNull() ?: 0   // Find the max amount of candies among kids
        return candies.map { it + extraCandies >= maxCandies } // Check if adding extraCandies makes them the highest
    }


    /* (6.) Can Place Flowers
    You have a long flowerbed in which some of the plots are planted, and some are not.
    However, flowers cannot be planted in adjacent plots.
    Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1
    means not empty, and an integer n, return true if n new flowers can be planted in
    the flowerbed without violating the no-adjacent-flowers rule and false otherwise.
     */

    fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {

        var plantable = 0 // Count of flowers that can be planted
        val size = flowerbed.size

        for (i in flowerbed.indices) {
            if (flowerbed[i] == 0) { // Only consider empty spots
                val leftEmpty = (i == 0) || (flowerbed[i - 1] == 0) // Left boundary or empty
                val rightEmpty = (i == size - 1) || (flowerbed[i + 1] == 0) // Right boundary or empty

                if (leftEmpty && rightEmpty) { // Can plant a flower here
                    flowerbed[i] = 1 // Mark as planted
                    plantable++ // Increment planted count

                    if (plantable >= n) return true // Early exit if enough flowers are planted
                }
            }
        }

        return plantable >= n
    }

    /* 7.)
    Product of an array except self.
    Given an integer array nums, return an array answer such that answer[i] is equal to
    the product of all the elements of nums except nums[i].
    The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
    You must write an algorithm that runs in O(n) time and without using the division operation.
    */
    fun productExceptSelf(nums: IntArray): IntArray {

        val n = nums.size
        val result = IntArray(n) { 1 }  // Step 1: Initialize result with 1

        var prefix = 1
        for (i in nums.indices) {
            result[i] = prefix  // Store prefix product
            prefix *= nums[i]   // Update prefix
        }

        var suffix = 1
        for (i in nums.indices.reversed()) {
            result[i] *= suffix  // Multiply with suffix product
            suffix *= nums[i]    // Update suffix
        }

        return result
    }

    /* 8.)
    Increasing Triplet Subsequence
    Given an integer array nums, return true if there exists a triple of indices
    (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such
    indices exists, return false.
    */
    fun increasingTriplet(nums: IntArray): Boolean {

        var first = Int.MAX_VALUE
        var second = Int.MAX_VALUE
        for (i in nums.indices) {
            if (nums[i] <= first) {
                first = nums[i]
            } else if (nums[i] <= second) {
                second = nums[i]

            } else {
                return true
            }
        }
        return false
    }

    /* 9.)
     Given an array of integers nums and an integer target, return indices of
     the two numbers such that they add up to target.
     */

    fun twoSum(target: Int, nums: IntArray): IntArray {
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                if (nums[i] + nums[j] == target) {
                    return intArrayOf(i, j)
                }
            }
        }
        return intArrayOf()

    }

    /* 10.)
    Given two sorted arrays nums1 and nums2 of size m and n respectively, return the
    median of the two sorted arrays.

    The overall run time complexity should be O(log (m+n)).
     */
//    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
//
//
//    }
}








