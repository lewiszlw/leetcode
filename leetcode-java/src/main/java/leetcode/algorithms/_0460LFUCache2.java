package leetcode.algorithms;

//Design and implement a data structure for a Least Frequently Used (LFU) cache.
//
//
// Implement the LFUCache class:
//
//
// LFUCache(int capacity) Initializes the object with the capacity of the data s
//tructure.
// int get(int key) Gets the value of the key if the key exists in the cache. Ot
//herwise, returns -1.
// void put(int key, int value) Update the value of the key if present, or inser
//ts the key if not already present. When the cache reaches its capacity, it shoul
//d invalidate and remove the least frequently used key before inserting a new ite
//m. For this problem, when there is a tie (i.e., two or more keys with the same f
//requency), the least recently used key would be invalidated.
//
//
// To determine the least frequently used key, a use counter is maintained for e
//ach key in the cache. The key with the smallest use counter is the least frequen
//tly used key.
//
// When a key is first inserted into the cache, its use counter is set to 1 (due
// to the put operation). The use counter for a key in the cache is incremented ei
//ther a get or put operation is called on it.
//
//
// Example 1:
//
//
//Input
//["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "g
//et"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
//Output
//[null, null, null, 1, null, -1, 3, null, -1, 3, 4]
//
//Explanation
//// cnt(x) = the use counter for key x
//// cache=[] will show the last used order for tiebreakers (leftmost element is
//  most recent)
//LFUCache lfu = new LFUCache(2);
//lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
//lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
//lfu.get(1);      // return 1
//                 // cache=[1,2], cnt(2)=1, cnt(1)=2
//lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalid
//ate 2.
//                 // cache=[3,1], cnt(3)=1, cnt(1)=2
//lfu.get(2);      // return -1 (not found)
//lfu.get(3);      // return 3
//                 // cache=[3,1], cnt(3)=2, cnt(1)=2
//lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1
//.
//                 // cache=[4,3], cnt(4)=1, cnt(3)=2
//lfu.get(1);      // return -1 (not found)
//lfu.get(3);      // return 3
//                 // cache=[3,4], cnt(4)=1, cnt(3)=3
//lfu.get(4);      // return 4
//                 // cache=[3,4], cnt(4)=2, cnt(3)=3
//
//
//
// Constraints:
//
//
// 0 <= capacity, key, value <= 104
// At most 105 calls will be made to get and put.
//
//
//
//Follow up: Could you do both operations in O(1) time complexity? Related Topic
//s Design
// 👍 1991 👎 152

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解法2：双哈希表
 */
public class _0460LFUCache2 {

    // 缓存key -> entry
    private Map<Integer, Entry> cache;

    // 频次->Entry list
    private Map<Integer, List<Entry>> freqMap;

    private int capacity;

    // cache中最小频次
    private int minFreq;

    // 时间戳，用于判断entry先后顺序，非真实时间，是一个全局自增id
    private volatile long time;

    public _0460LFUCache2(int capacity) {
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.capacity = capacity;
        this.minFreq = 0;
        this.time = Long.MIN_VALUE;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        // 更新频次map
        Entry entry = cache.get(key);
        int originFreq = entry.freq;
        entry.freq ++;
        entry.time = latestTime();
        updateFreqMapEntryAfterAccess(originFreq, entry);

        return entry.val;
    }

    public synchronized void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (cache.containsKey(key)) {
            // 更新频次map
            Entry entry = cache.get(key);
            int originFreq = entry.freq;
            entry.freq ++;
            entry.val = value;
            entry.time = latestTime();
            updateFreqMapEntryAfterAccess(originFreq, entry);

            // 更新cache
            cache.put(key, entry);
        } else {

            // 更新频次map
            Entry entry = new Entry(key, value, 1, latestTime());
            Entry entryTobeDeleted = updateFreqMapEntryAfterAccess(-1, entry);

            // 更新cache
            if (entryTobeDeleted != null) {
                cache.remove(entryTobeDeleted.key);
            }
            cache.put(key, entry);
        }
    }

    /**
     * 更新频次map
     * @param originFreq 更新前频次, -1则为新增的entry
     * @param entry 更新后val和freq后的entry
     * @return 需要删除的entry
     */
    private Entry updateFreqMapEntryAfterAccess(int originFreq, Entry entry) {
        // 1.新增entry
        if (originFreq == -1) {
            Entry entryToBeDeleted = null;
            if (cache.size() >= capacity) {
                // 移除freq最小，time最小的entry
                List<Entry> originEntries = freqMap.get(minFreq);
                entryToBeDeleted = originEntries.stream().min(Comparator.comparing(Entry::getTime)).get();
                originEntries.remove(entryToBeDeleted);
            }
            // 更新minFreq
            updateMinFreq(entry);
            // 添加新entry
            addEntryToFreqMap(entry);
            return entryToBeDeleted;
        }

        // 2.更新已有的entry
        // 移除原有entry
        List<Entry> originEntries = freqMap.get(originFreq);
        Entry originEntry = originEntries.stream()
                .filter(entryItem -> entryItem.key == entry.key)
                .findAny()
                .get();
        originEntries.remove(originEntry);
        if (originEntries.isEmpty()) {
            freqMap.remove(originFreq);
        }
        // 更新minFreq
        updateMinFreq(entry);
        // 添加新entry
        addEntryToFreqMap(entry);

        return originEntry;
    }

    private void updateMinFreq(Entry entry) {
        // 前提 entry.freq == originFreq + 1
        if (!freqMap.containsKey(minFreq) || entry.freq < minFreq) {
            minFreq = entry.freq;
        }
    }

    private void addEntryToFreqMap(Entry entry) {
        int freq = entry.freq;
        if (freqMap.containsKey(freq)) {
            freqMap.get(freq).add(entry);
        } else {
            List<Entry> entries = new ArrayList<>();
            entries.add(entry);
            freqMap.put(freq, entries);
        }
    }

    private synchronized long latestTime() {
        long t = time;
        time ++;
        return t;
    }

    class Entry {
        int key;    // 缓存key
        int val;    // 缓存值
        int freq;   // 访问频率
        long time;  // 上一次访问时间
        public Entry(int key, int val, int freq, long time) {
            this.key = key;
            this.val = val;
            this.freq = freq;
            this.time = time;
        }
        public long getTime() {
            return time;
        }
    }

}
