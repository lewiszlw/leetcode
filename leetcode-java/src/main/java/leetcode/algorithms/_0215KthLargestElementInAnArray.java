package leetcode.algorithms;

//Given an integer array nums and an integer k, return the kth largest element i
//n the array.
//
// Note that it is the kth largest element in the sorted order, not the kth dist
//inct element.
//
//
// Example 1:
// Input: nums = [3,2,1,5,6,4], k = 2
//Output: 5
// Example 2:
// Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
//Output: 4
//
//
// Constraints:
//
//
// 1 <= k <= nums.length <= 104
// -104 <= nums[i] <= 104
//
// Related Topics Divide and Conquer Heap
// 👍 5751 👎 365

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class _0215KthLargestElementInAnArray {

    /**
     * 解法1：直接全部排序
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }




    /**
     * 解法2：堆排序优化
     * 堆排序排前k个数就停止
     */
    public int findKthLargest2(int[] nums, int k) {
        // 建最大堆
        int lastNonLeaf = (nums.length - 1 - 1) / 2; // 最后一个非叶节点
        for (int i = lastNonLeaf; i >= 0; i--) {
            heapify(nums, i, nums.length);
        }

        // 依次交换堆顶元素和末尾元素并重新堆化k次
        for (int i = nums.length - 1; i >= nums.length - k; i--) {
            swap(nums, 0, i);
            heapify(nums, 0, i);
        }
        return nums[nums.length - k];
    }

    private void heapify(int[] nums, int root, int heapLen) {
        for (int i = root; i < heapLen - 1;) {
            int leftChild = i * 2 + 1;
            int rightChild = i * 2 + 2;

            int largest = i;
            if (leftChild <= heapLen - 1 && nums[leftChild] > nums[largest]) {
                largest = leftChild;
            }
            if (rightChild <= heapLen - 1 && nums[rightChild] > nums[largest]) {
                largest = rightChild;
            }

            if (largest != i) {
                swap(nums, i, largest);
                i = largest;
            } else {
                break;
            }

        }
    }

    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }




    /**
     * 解法3：快排序优化
     * 快排分治的时候判断第k大的数在哪个子数组
     */
    public int findKthLargest3(int[] nums, int k) {
        // 第k大的数在升序后数组中的下标
        int indexOfTarget = nums.length - k;

        // sep将数组分割成两部分[left, sep - 1]和[sep + 1, right]（如果两部分都存在）
        int sep = -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            // 初始切分
            if (sep == -1) {
                sep = quickSort(nums, left, right);
                continue;
            }
            if (sep == indexOfTarget) {
                return nums[sep];
            }
            // 第k大的数在[left, sep - 1]
            if (sep > indexOfTarget) {
                right = sep - 1;
                sep = quickSort(nums, left, right);
            }
            // 第k大的数在[sep + 1, right]
            if (sep < indexOfTarget) {
                left = sep + 1;
                sep = quickSort(nums, left, right);
            }
        }
        return 0;
    }

    private int quickSort(int[] nums, int left, int right) {
        if (left == right) {
            return left;
        }

        int key = nums[left];
        while (left < right) {
            // 从右至左找到一个小于key的数
            while (right > left && nums[right] >= key) {
                right --;
            }
            // 交换left和right，同时left右移一位
            if (right > left) {
                swap(nums, right, left);
                left ++;
            }

            // 从左至右找到一个大于等于key的数
            while (left < right && nums[left] < key) {
                left ++;
            }
            // 交换left和right，同时right左移一位
            if (left < right) {
                swap(nums, left, right);
                right --;
            }
        }
        // 返回中间分隔数
        return left;
    }



    @Test
    public void test() {
        Assert.assertEquals(5, findKthLargest(new int[]{3,2,1,5,6,4}, 2));
        Assert.assertEquals(4, findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }

    @Test
    public void test2() {
        Assert.assertEquals(5, findKthLargest2(new int[]{3,2,1,5,6,4}, 2));
        Assert.assertEquals(4, findKthLargest2(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }

    @Test
    public void test3() {
        Assert.assertEquals(5, findKthLargest3(new int[]{3,2,1,5,6,4}, 2));
        Assert.assertEquals(4, findKthLargest3(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }
}
