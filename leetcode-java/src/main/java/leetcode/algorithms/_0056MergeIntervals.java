package leetcode.algorithms;

//Given an array of intervals where intervals[i] = [starti, endi], merge all ove
//rlapping intervals, and return an array of the non-overlapping intervals that co
//ver all the intervals in the input.
//
//
// Example 1:
//
//
//Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
//
//
// Example 2:
//
//
//Input: intervals = [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping.
//
//
//
// Constraints:
//
//
// 1 <= intervals.length <= 104
// intervals[i].length == 2
// 0 <= starti <= endi <= 104
//
// Related Topics Array Sort
// 👍 7524 👎 388

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _0056MergeIntervals {
    /**
     * 解法：排序
     * 将区间数组按左端点排序
     */
    public int[][] merge(int[][] intervals) {
        // 区间起始端点升序排序
        Arrays.sort(intervals, Comparator.comparing(interval -> interval[0]));

        // 非重叠区间端点列表
        List<Integer> list = new ArrayList<>();

        // 重叠部分合并
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            int start = interval[0];
            int end = interval[1];
            addList(list, start, end);
        }

        int[][] res = new int[list.size() / 2][2];
        int i = 0;
        for (int j = 0; j < list.size(); ) {
            int[] interval = new int[]{list.get(j), list.get(j + 1)};
            res[i] = interval;
            j += 2;
            i ++;
        }
        return res;
    }

    private void addList(List<Integer> list, int start, int end) {
        if (list.isEmpty()) {
            list.add(start);
            list.add(end);
        } else {
            int end0 = list.get(list.size() - 1);
            int start0 = list.get(list.size() - 2);
            if (isOverlapped(start0, end0, start, end)) {
                // 合并两个重叠区间，只需更新end
                list.remove(list.size() - 1);
                list.add(Math.max(end0, end));
            } else {
                list.add(start);
                list.add(end);
            }
        }
    }

    // 判断[start1, end1]和[start2, end2]是否重叠
    private boolean isOverlapped(int start1, int end1, int start2, int end2) {
        // 因为排序过，start1<=start2，所以只需判断start2是否小于等于end1
        if (start2 <= end1) {
            return true;
        }
        return false;
    }


    @Test
    public void test() {
        int[][] intervals = merge(new int[][]{{1, 3}, {8, 10}, {2, 6}, {15, 18}});
        for (int i = 0; i < intervals.length; i++) {
            System.out.println(Arrays.toString(intervals[i]));
        }

        System.out.println("================");

        int[][] intervals2 = merge(new int[][]{{1,4},{4,5}});
        for (int i = 0; i < intervals2.length; i++) {
            System.out.println(Arrays.toString(intervals2[i]));
        }

        System.out.println("================");

        int[][] intervals3 = merge(new int[][]{{1,4},{5,6}});
        for (int i = 0; i < intervals3.length; i++) {
            System.out.println(Arrays.toString(intervals3[i]));
        }
    }

}
