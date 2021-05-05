package leetcode.algorithms;

//The median is the middle value in an ordered integer list. If the size of the
//list is even, there is no middle value and the median is the mean of the two mid
//dle values.
//
//
// For example, for arr = [2,3,4], the median is 3.
// For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
//
//
// Implement the MedianFinder class:
//
//
// MedianFinder() initializes the MedianFinder object.
// void addNum(int num) adds the integer num from the data stream to the data st
//ructure.
// double findMedian() returns the median of all elements so far. Answers within
// 10-5 of the actual answer will be accepted.
//
//
//
// Example 1:
//
//
//Input
//["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
//[[], [1], [2], [], [3], []]
//Output
//[null, null, null, 1.5, null, 2.0]
//
//Explanation
//MedianFinder medianFinder = new MedianFinder();
//medianFinder.addNum(1);    // arr = [1]
//medianFinder.addNum(2);    // arr = [1, 2]
//medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
//medianFinder.addNum(3);    // arr[1, 2, 3]
//medianFinder.findMedian(); // return 2.0
//
//
//
// Constraints:
//
//
// -105 <= num <= 105
// There will be at least one element in the data structure before calling findM
//edian.
// At most 5 * 104 calls will be made to addNum and findMedian.
//
//
//
// Follow up:
//
//
// If all integer numbers from the stream are in the range [0, 100], how would y
//ou optimize your solution?
// If 99% of all integer numbers from the stream are in the range [0, 100], how
//would you optimize your solution?
//
// Related Topics Heap Design
// 👍 4091 👎 78

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 解法1：插入排序
 * 维持一个有序的容器，新增一个数时进行插入排序，利用二分搜索快速找到插入的位置
 */
public class _0295FindMedianFromDataStream {

    // 容器
    private List<Integer> list;

    /** initialize your data structure here. */
    public _0295FindMedianFromDataStream() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        if (list.size() == 0) {
            list.add(num);
            return;
        }
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (num < list.get(mid)) {
                // 由于是需要找到该数所在范围，所以需要往前判断一位，因为后面 right = mid - 1
                if (mid == 0 || num >= list.get(mid - 1)) {
                    list.add(mid, num);
                    return;
                }
                right = mid - 1;
            }
            if (num == list.get(mid)) {
                list.add(mid, num);
                return;
            }
            if (num > list.get(mid)) {
                // 由于是需要找到该数所在范围，所以需要往后判断一位，因为后面 left = mid + 1
                if (mid == list.size() - 1 || num <= list.get(mid + 1)) {
                    list.add(mid + 1, num);
                    return;
                }
                left = mid + 1;
            }
        }
    }

    public double findMedian() {
        boolean isOdd = list.size() % 2 == 1;
        return isOdd ?
                (double) list.get(list.size() / 2) :
                (double) (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2;
    }


    @Test
    public void test() {
        _0295FindMedianFromDataStream medianFinder = new _0295FindMedianFromDataStream();
        medianFinder.addNum(2);
        Assert.assertEquals(2.0, medianFinder.findMedian(), 0.0);
        medianFinder.addNum(5);
        medianFinder.addNum(3);
        Assert.assertEquals(3.0, medianFinder.findMedian(), 0.0);
        medianFinder.addNum(4);
        Assert.assertEquals(3.5, medianFinder.findMedian(), 0.0);
    }

    @Test
    public void test2() {
        _0295FindMedianFromDataStream medianFinder = new _0295FindMedianFromDataStream();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        medianFinder.addNum(3);
        Assert.assertEquals(2.0, medianFinder.findMedian(), 0.0);
    }
}
