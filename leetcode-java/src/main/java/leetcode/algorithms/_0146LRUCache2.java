package leetcode.algorithms;

/*
 * @lc app=leetcode id=146 lang=java
 *
 * [146] LRU Cache
 *
 * https://leetcode.com/problems/lru-cache/description/
 *
 * algorithms
 * Hard (24.50%)
 * Total Accepted:    275.5K
 * Total Submissions: 1.1M
 * Testcase Example:  '["LRUCache","put","put","get","put","get","put","get","get","get"]\n[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]'
 *
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 *
 *
 *
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently
 * used item before inserting a new item.
 *
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 */
// LRUCache cache = new LRUCache( 2 /* capacity */ );
/*
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4, 4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 *
 *
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 * HashMap + 双向链表实现
 * HashMap的value指向双向链表节点, 这样放入和移除都是 O(1)
 *
 * 7 0 1 2 0 3 0 4
 * ---------------
 *     1 2 0 3 0 4
 *   0 0 1 2 0 3 0
 * 7 7 7 0 1 2 2 3
 *
 * @author zhanglinwei02
 * @date 2019-04-04
 */
public class _0146LRUCache2 {

    /**
     * 存放key和节点，查询复杂度为O(1)
     */
    private Map<Integer, DLinkedNode> cacheMap;

    /**
     * cache容量
     */
    private int capacity;

    /**
     * 双向链表头结点
     */
    private DLinkedNode head;

    /**
     * 双向链表尾节点
     */
    private DLinkedNode tail;

    public _0146LRUCache2(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + capacity);
        }
        this.capacity = capacity;
        cacheMap = new HashMap<>(capacity + 1);
        initHeadTail();
    }

    /**
     * 初始化头尾节点
     */
    private void initHeadTail() {
        head = new DLinkedNode(null, null);
        tail = new DLinkedNode(null, null);
        head.post = tail;
        tail.pre = head;
    }

    public void put(int key, int val) {
        if (cacheMap.containsKey(key)) {
            DLinkedNode node = cacheMap.get(key);
            // 更新node的值
            node.val = val;
            // 移除node
            remove(node);
            // 插入到头部
            insertToHead(node);
        } else {
            DLinkedNode node = new DLinkedNode(key, val);
            // 存入map
            cacheMap.put(key, node);
            // 插入到头部
            insertToHead(node);
            if (cacheMap.size() > capacity) {
                // 清除尾部节点
                removeTailPre();
            }
        }
    }

    public int get(int key) {
        if (cacheMap.containsKey(key)) {
            DLinkedNode node = cacheMap.get(key);
            // 移除node
            remove(node);
            // 插入到头部
            insertToHead(node);
            return node.val;
        }
        return -1;
    }

    /**
     * 清除尾部节点
     */
    private void removeTailPre() {
        DLinkedNode delNode = tail.pre;
        DLinkedNode tailPrePre = delNode.pre;
        tailPrePre.post = tail;
        tail.pre = tailPrePre;
        delNode.pre = null;
        delNode.post = null;
        cacheMap.remove(delNode.key);
    }

    /**
     * 插入节点到链表头部
     */
    private void insertToHead(DLinkedNode node) {
        DLinkedNode oldHeadPostNode = head.post;
        head.post = node;
        node.pre = head;
        node.post = oldHeadPostNode;
        oldHeadPostNode.pre = node;
    }

    /**
     * 移除node节点
     */
    private void remove(DLinkedNode node) {
        DLinkedNode preNode = node.pre;
        DLinkedNode postNode = node.post;
        preNode.post = postNode;
        postNode.pre = preNode;
        node.pre = null;
        node.post = null;
    }

    /**
     * 双向链表节点
     */
    private class DLinkedNode {
        Integer key;  // 便于从map中删除
        Integer val;
        DLinkedNode pre;
        DLinkedNode post;
        DLinkedNode(Integer key, Integer val) {this.key = key; this.val = val;}
    }

}
