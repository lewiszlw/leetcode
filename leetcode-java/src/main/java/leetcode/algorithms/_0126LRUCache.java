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
 * HashMap + 单链表实现
 *
 * 7 0 1 2 0 3 0 4
 * ---------------
 *     1 2 0 3 0 4
 *   0 0 1 2 0 3 0
 * 7 7 7 0 1 2 2 3
 *
 *
 * @author zhanglinwei02
 * @date 2019-04-02
 */
public class _0126LRUCache {

    private Node head;

    private int capacity;

    private Map<Integer, Integer> map;

    public _0126LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(0);
        map = new HashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            int val = map.get(key);
            deleteNodeK(key);
            updateKToHeadNext(key, val);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            deleteNodeK(key);
        }
        updateKToHeadNext(key, value);
        if (map.size() > capacity) {
            deleteLastNode();
        }
    }

    /**
     * 删除链表最后一个节点
     */
    private void deleteLastNode() {
        Node p = head;
        while (p.next != null) {
            if (p.next.next == null) {
                map.remove(p.next.key);
                p.next = null;
                break;
            }
            p = p.next;
        }
    }

    /**
     * 删除链表为key的节点
     */
    private Node deleteNodeK(int key) {
        Node p = head;
        while (p.next != null) {
            if (p.next.key == key) {
                Node kNode = p.next;
                Node kNext = kNode == null? null: kNode.next;
                p.next = kNext;
                map.remove(key);
                return kNode;
            }
            p = p.next;
        }
        return null;
    }

    /**
     * 更新head的下一个节点为key
     */
    private void updateKToHeadNext(int key, int value) {
        Node tmp = head.next;
        Node k = new Node(key);
        head.next = k;
        k.next = tmp;
        map.put(key, value);
    }

    /**
     * 链表节点
     */
    class Node {
        int key;
        Node next;
        Node(int key) {
            this.key = key;
        }
    }
}
