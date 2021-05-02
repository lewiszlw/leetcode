package leetcode.algorithms;

//Given two sorted arrays nums1 and nums2 of size m and n respectively, return t
//he median of the two sorted arrays.
//
//
// Example 1:
//
//
//Input: nums1 = [1,3], nums2 = [2]
//Output: 2.00000
//Explanation: merged array = [1,2,3] and median is 2.
//
//
// Example 2:
//
//
//Input: nums1 = [1,2], nums2 = [3,4]
//Output: 2.50000
//Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
//
//
// Example 3:
//
//
//Input: nums1 = [0,0], nums2 = [0,0]
//Output: 0.00000
//
//
// Example 4:
//
//
//Input: nums1 = [], nums2 = [1]
//Output: 1.00000
//
//
// Example 5:
//
//
//Input: nums1 = [2], nums2 = []
//Output: 2.00000
//
//
//
// Constraints:
//
//
// nums1.length == m
// nums2.length == n
// 0 <= m <= 1000
// 0 <= n <= 1000
// 1 <= m + n <= 2000
// -106 <= nums1[i], nums2[i] <= 106
//
//
//
//Follow up: The overall run time complexity should be O(log (m+n)). Related Top
//ics Array Binary Search Divide and Conquer
// 👍 9894 👎 1512

import org.junit.Assert;
import org.junit.Test;

public class _0004MedianOfTwoSortedArrays {

    /**
     * 解法1：归并排序
     *
     * 另一种办法，不需要合并两个有序数组，只要找到中位数的位置即可。
     * 由于两个数组的长度已知，因此中位数对应的两个数组的下标之和也是已知的。
     * 维护两个指针，初始时分别指向两个数组的下标 00 的位置，每次将指向较小值的指针后移一位
     * （如果一个指针已经到达数组末尾，则只需要移动另一个数组的指针），直到到达中位数的位置。
     *
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = mergeSortedArrays(nums1, nums2);
        boolean isOdd = nums.length % 2 == 1;
        if (isOdd) {
            return nums[(nums.length - 1) / 2];
        } else {
            return (double) (nums[nums.length / 2] + nums[(nums.length / 2) - 1]) / 2;
        }
    }

    private int[] mergeSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                nums[i + j] = nums1[i];
                i ++;
            } else {
                nums[i + j] = nums2[j];
                j ++;
            }
        }
        if (i < nums1.length) {
            while (i < nums1.length) {
                nums[i + j] = nums1[i];
                i ++;
            }
        }
        if (j < nums2.length) {
            while (j < nums2.length) {
                nums[i + j] = nums2[j];
                j ++;
            }
        }
        return nums;
    }


    /**
     * 解法2：二分查找
     *
     * num1  # # # | #
     *           ---
     * num2  # # | # # # #
     *
     * 找出一条线切分两个数组，保证
     * 1.这条线左右两侧元素个数相同（或相差1）
     * 2.这条线左侧所有元素均不大于右侧所有元素
     * 则中位数必定在这条线附近。
     *
     * example
     * num1  2 4 6 | 15
     *           ---
     * num2  1 7 | 8 10 17
     * 中位数为7。
     *
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // 边界情况：其中一个数组为空
        if (nums1.length == 0) {
            return getMedianFromSingleArray(nums2);
        }
        if (nums2.length == 0) {
            return getMedianFromSingleArray(nums1);
        }
        // 边界情况：两个数组不交叉
        if (nums1[nums1.length - 1] <= nums2[0]) {
            return getMedianFromTwoSortedArraysWithoutOverlapping(nums1, nums2);
        }
        if (nums1[0] >= nums2[nums2.length - 1]) {
            return getMedianFromTwoSortedArraysWithoutOverlapping(nums2, nums1);
        }

        // 线左侧元素个数（让线左侧元素个数等于线右侧元素个数或者大1） —— 条件1
        int totalLeftCount = (nums1.length + nums2.length + 1) / 2;

        // 二分搜索nums1
        int left = 0, right = nums1.length - 1;
        while (left <= right) {
            // num1数组划线
            int mid1 = (left + right) / 2;  // mid1以及mid1左侧元素，共 mid1 + 1 个元素
            // num2数组划线
            int mid2 = totalLeftCount - (mid1 + 1);   // mid2左侧元素（不包括mid2）
            if (mid2 > nums2.length) {
                // mid2 不能超过num2数组长度，否则此种情况不满足条件1
                // mid1右移
                left = mid1 + 1;
                continue;
            }
            if (mid2 < 0) {
                // mid2不能小于0
                // mid1左移
                right = mid1 - 1;
                continue;
            }

            // 判断条件2
            if (mid1 == nums1.length - 1) {
                //  a    b    c(mid1)   |
                //  d    |    e(mid2)   f    g    h
                if (Math.max(nums1[mid1], nums2[mid2 - 1]) <= nums2[mid2]) {
                    return getMedianAfterSliced(nums1, mid1, nums2, mid2);
                }
            } else if (mid2 == 0) {
                //  a    b    c(mid1)    |    d
                //  |    e(mid2)    f
                if (nums1[mid1] <= Math.min(nums1[mid1 + 1], nums2[mid2])) {
                    return getMedianAfterSliced(nums1, mid1, nums2, mid2);
                }
            } else if (mid2 == nums2.length) {
                //  a    b    c(mid1)    |    d    g    h    i
                //  e    f    |    null(mid2)
                if (Math.max(nums1[mid1], nums2[mid2 - 1]) <= nums1[mid1 + 1]) {
                    return getMedianAfterSliced(nums1, mid1, nums2, mid2);
                }
            } else {
                // 普通情况
                //  a    b(mid1)    |    c    d
                //  e    |    f(mid2)
                if (Math.max(nums1[mid1], nums2[mid2 - 1]) <= Math.min(nums1[mid1 + 1], nums2[mid2])) {
                    return getMedianAfterSliced(nums1, mid1, nums2, mid2);
                }
            }

            // 不满足条件2，缩小搜索范围
            if (mid2 < nums2.length && nums1[mid1] > nums2[mid2]) {
                // mid1左移
                right = mid1 - 1;
                continue;
            } else if (nums1[mid1 + 1] < nums2[mid2 - 1]) {
                // mid1右移
                left = mid1 + 1;
                continue;
            }
        }

        // mid1 = -1，即num1全部为划线右侧，二分搜索nums2
        //  null(mid1)    |    a    b    c
        //  d    e    f    g    |    h(mid2inNums2)
        int mid2inNums2 = totalLeftCount;
        if (mid2inNums2 < nums2.length && nums2[mid2inNums2 - 1] <= Math.min(nums1[0], nums2[mid2inNums2])) {
            return getMedianAfterSliced(nums1, -1, nums2, mid2inNums2);
        }

        return 0.0;
    }

    private double getMedianAfterSliced(int[] nums1, int mid1, int[] nums2, int mid2) {
        boolean isOdd = (nums1.length + nums2.length) % 2 == 1;
        if (isOdd) {
            // 奇数个元素，则返回划线左侧最大的那个元素
            // 二分搜索nums2
            if (mid1 == -1) {
                return nums2[mid2 - 1];
            }
            // 二分搜索nums1
            if (mid2 - 1 >= 0) {
                return Math.max(nums1[mid1], nums2[mid2 - 1]);
            } else {
                return nums1[mid1];
            }
        } else {
            // 偶数个元素，则范围划线左侧最大的元素与划线右侧最小的元素的平均值

            // 二分搜索nums2
            if (mid1 == -1) {
                return (double) (nums2[mid2 - 1] + Math.min(nums1[0], nums2[mid2])) / 2;
            }
            // 二分搜索nums1
            int leftMax;
            if (mid2 - 1 >= 0) {
                leftMax = Math.max(nums1[mid1], nums2[mid2 - 1]);
            } else {
                leftMax = nums1[mid1];
            }

            int rightMin;
            if (mid1 + 1 >= nums1.length) {
                rightMin = nums2[mid2];
            } else if (mid2 == nums2.length) {
                rightMin = nums1[mid1 + 1];
            } else {
                rightMin = Math.min(nums1[mid1 + 1], nums2[mid2]);
            }

            return (double) (leftMax + rightMin) / 2;
        }
    }

    private double getMedianFromSingleArray(int[] nums) {
        boolean isOdd = nums.length % 2 == 1;
        if (isOdd) {
            return nums[(nums.length - 1) / 2];
        } else {
            return (double) (nums[nums.length / 2] + nums[(nums.length / 2) - 1]) / 2;
        }
    }

    private double getMedianFromTwoSortedArraysWithoutOverlapping(int[] leftNums, int[] rightNums) {
        int totalLength = leftNums.length + rightNums.length;
        boolean isOdd = totalLength % 2 == 1;
        if (isOdd) {
            int medianIndex = totalLength / 2;
            if (medianIndex < leftNums.length) {
                // 中位数在左边数组
                return leftNums[medianIndex];
            } else {
                // 中位数在右边数组
                return rightNums[medianIndex - leftNums.length];
            }
        } else {
            int medianLeftIndex = totalLength / 2 - 1;
            int medianRightIndex = totalLength / 2;
            if (medianRightIndex < leftNums.length) {
                // 中位数左右两个数都在左侧数组
                return (double) (leftNums[medianLeftIndex] + leftNums[medianRightIndex]) / 2;
            } else if (medianLeftIndex >= leftNums.length) {
                // 中位数左右两个数都在右侧数组
                return (double) (rightNums[medianLeftIndex - leftNums.length] + rightNums[medianRightIndex - leftNums.length]) / 2;
            } else {
                // 中位数左右两个数横跨两个数组，即左边数组末尾和右边数组头部
                return (double) (leftNums[medianLeftIndex] + rightNums[medianRightIndex - leftNums.length]) / 2;
            }
        }
    }



    @Test
    public void test() {
        // should be 2.5
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
    }

    @Test
    public void test2() {
        Assert.assertEquals(4.5, findMedianSortedArrays2(new int[]{1,3,5,7}, new int[]{2,4,6,8}), 0.0);
        Assert.assertEquals(2.0, findMedianSortedArrays2(new int[]{1,2}, new int[]{1,2,3}), 0.0);
        Assert.assertEquals(2.5, findMedianSortedArrays2(new int[]{3}, new int[]{1,2,4}), 0.0);
        Assert.assertEquals(3.0, findMedianSortedArrays2(new int[]{1,2,3,5}, new int[]{4}), 0.0);
        Assert.assertEquals(4.0, findMedianSortedArrays2(new int[]{1,2,3,4,6,7}, new int[]{5}), 0.0);
    }

    @Test
    public void testGetMedianFromTwoSortedArraysWithoutOverlapping() {
        int[] leftNums1 = new int[]{1,3,4};
        int[] rightNums1 = new int[]{5,6,9};
        Assert.assertEquals(
                4.5,
                getMedianFromTwoSortedArraysWithoutOverlapping(leftNums1, rightNums1),
                0.0);

        int[] leftNums2 = new int[]{1,2,4};
        int[] rightNums2 = new int[]{5};
        Assert.assertEquals(
                3.0,
                getMedianFromTwoSortedArraysWithoutOverlapping(leftNums2, rightNums2),
                0.0);

        int[] leftNums3 = new int[]{1};
        int[] rightNums3 = new int[]{2,4,5};
        Assert.assertEquals(
                3.0,
                getMedianFromTwoSortedArraysWithoutOverlapping(leftNums3, rightNums3),
                0.0);
    }
}
