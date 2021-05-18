package leetcode.algorithms;

//You are given two integer arrays nums1 and nums2 both of unique elements, wher
//e nums1 is a subset of nums2.
//
// Find all the next greater numbers for nums1's elements in the corresponding p
//laces of nums2.
//
// The Next Greater Number of a number x in nums1 is the first greater number to
// its right in nums2. If it does not exist, return -1 for this number.
//
//
// Example 1:
//
//
//Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
//Output: [-1,3,-1]
//Explanation:
//For number 4 in the first array, you cannot find the next greater number for i
//t in the second array, so output -1.
//For number 1 in the first array, the next greater number for it in the second
//array is 3.
//For number 2 in the first array, there is no next greater number for it in the
// second array, so output -1.
//
// Example 2:
//
//
//Input: nums1 = [2,4], nums2 = [1,2,3,4]
//Output: [3,-1]
//Explanation:
//For number 2 in the first array, the next greater number for it in the second
//array is 3.
//For number 4 in the first array, there is no next greater number for it in the
// second array, so output -1.
//
//
// Constraints:
//
//
// 1 <= nums1.length <= nums2.length <= 1000
// 0 <= nums1[i], nums2[i] <= 104
// All integers in nums1 and nums2 are unique.
// All the integers of nums1 also appear in nums2.
//
//
//
//Follow up: Could you find an O(nums1.length + nums2.length) solution? Related
//Topics Stack
// 👍 2502 👎 2927

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class _0496NextGreaterElementI {

    /**
     * 解法：哈希表
     * 将 nums2 转成 nums2[i] -> i 的 map
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // nums2[i] -> i map
        Map<String, Integer> nums2Map = new HashMap<>();
        for (int k = 0; k < nums2.length; k++) {
            nums2Map.put(String.valueOf(nums2[k]), k);
        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            // 找到nums1[i]在nums2中的索引，并往后遍历找是否存在大于nums1[i]的数
            int indexInNums2 = nums2Map.get(String.valueOf(nums1[i]));
            boolean found = false;
            for (int j = indexInNums2 + 1; j < nums2.length; j++) {
                if (nums2[j] > nums1[i]) {
                    res[i] = nums2[j];
                    found = true;
                    break;
                }
            }
            if (!found) {
                res[i] = -1;
            }
        }
        return res;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{-1,3,-1}, nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2}));
        Assert.assertArrayEquals(new int[]{3,-1}, nextGreaterElement(new int[]{2,4}, new int[]{1,2,3,4}));
    }
}
