package leetcode.algorithms;

//Given an integer array nums and an integer k, return the k most frequent eleme
//nts. You may return the answer in any order.
//
//
// Example 1:
// Input: nums = [1,1,1,2,2,3], k = 2
//Output: [1,2]
// Example 2:
// Input: nums = [1], k = 1
//Output: [1]
//
//
// Constraints:
//
//
// 1 <= nums.length <= 105
// k is in the range [1, the number of unique elements in the array].
// It is guaranteed that the answer is unique.
//
//
//
// Follow up: Your algorithm's time complexity must be better than O(n log n), w
//here n is the array's size.
// Related Topics Hash Table Heap
// 👍 4889 👎 267


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class _0347TopKFrequentElements {

    /**
     * 解法1：哈希表+最大堆
     * 哈希表key为num，value为出现频率(Pair)，遍历数组统计每个数出现频率，
     * 然后利用最大堆找到前k个出现频率最高的数
     */
    public int[] topKFrequent(int[] nums, int k) {
        // num -> freq
        Map<Integer, Pair> freqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (freqMap.containsKey(num)) {
                Pair pair = freqMap.get(num);
                pair.freq ++;
                freqMap.put(num, pair);
            } else {
                freqMap.put(num, new Pair(num, 1));
            }
        }

        // 最大堆，PriorityQueue默认最小堆，需要调整Comparator
        PriorityQueue<Pair> heap = new PriorityQueue<Pair>(k, Comparator.comparing(pair -> -pair.freq));
        heap.addAll(freqMap.values());

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            Pair pair = heap.poll();
            res[i] = pair.num;
        }

        return res;
    }

    class Pair {
        int num;
        int freq;
        public Pair(int num, int freq) {
            this.num = num;
            this.freq = freq;
        }
    }



    /**
     * 解法2：哈希表+列表
     * 哈希表key为num，value为出现频率(Integer)，遍历数组统计每个数出现频率，
     * 然后遍历哈希表，将其存入列表，其中value为数组索引
     */
    public int[] topKFrequent2(int[] nums, int k) {
        // num -> freq
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (freqMap.containsKey(num)) {
                Integer freq = freqMap.get(num);
                freq ++;
                freqMap.put(num, freq);
            } else {
                freqMap.put(num, 1);
            }
        }

        // index为freq, value为nums list，这里用数组，需要判断索引是否存在
        List<Integer>[] list = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            Integer freq = entry.getValue();
            if (list[freq] == null) {
                List<Integer> numsForThisFreq = new ArrayList<>();
                numsForThisFreq.add(entry.getKey());
                list[freq] = numsForThisFreq;
            } else {
                List<Integer> numsForThisFreq = list[entry.getValue()];
                numsForThisFreq.add(entry.getKey());
                list[entry.getValue()] = numsForThisFreq;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = list.length - 1; i >= 0 && res.size() < k; i--) {
            // 基于题目设定：题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
            // 即不存在[1,1,1,2,2,3,3,4,5], k=2这种情况
            List<Integer> numsWithSameFreq = list[i];
            if (numsWithSameFreq != null) {
                res.addAll(numsWithSameFreq);
            }
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }



    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{1,2}, topKFrequent(new int[]{1,1,1,2,2,3}, 2));
        Assert.assertArrayEquals(new int[]{1,3}, topKFrequent(new int[]{5,3,1,1,1,3,73,1}, 2));
    }

    @Test
    public void test2() {
        Assert.assertArrayEquals(new int[]{1,2}, topKFrequent2(new int[]{1,1,1,2,2,3}, 2));
        Assert.assertArrayEquals(new int[]{1,3}, topKFrequent2(new int[]{5,3,1,1,1,3,73,1}, 2));
    }
}
