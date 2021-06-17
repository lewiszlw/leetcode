package leetcode.algorithms;

/**
 * 解法：哈希表+双向链表
 *
 * 双向链表：每个节点内，存储key和value，并按照value降序。降序方法为，每次Inc/Dec的时候，都向前/向后移动该节点的位置。
 * 哈希表：用于查找定位，3个哈希表分别用于维护3个“node指针”：当前node指针、当前value对应的第一个node指针、当前value对应的最后一个node指针。
 *
 */
public class _0432AllOOneDataStructure {
}
