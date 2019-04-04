package leetcode;

import leetcode.algorithms._0146LRUCache2;
import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-04
 */
public class _0146LRUCache2Test {
    @Test
    public void test() {
        _0146LRUCache2 cache = new _0146LRUCache2(2);
        cache.put(1, 1);
        cache.put(2, 2);
        Assert.assertTrue(cache.get(1) == 1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        Assert.assertTrue(cache.get(2) == -1);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        Assert.assertTrue(cache.get(1) == -1);       // returns -1 (not found)
        Assert.assertTrue(cache.get(3) == 3);       // returns 3
        Assert.assertTrue(cache.get(4) == 4);       // returns 4
    }
}
