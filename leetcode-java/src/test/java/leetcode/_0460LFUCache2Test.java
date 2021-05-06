package leetcode;

import leetcode.algorithms._0460LFUCache2;
import org.junit.Assert;
import org.junit.Test;

public class _0460LFUCache2Test {

    @Test
    public void test() {
        _0460LFUCache2 cache = new _0460LFUCache2(2);
        cache.put(1, 1);
        cache.put(2, 2);
        Assert.assertEquals(1, cache.get(1));
        cache.put(3, 3);
        Assert.assertEquals(-1, cache.get(2));
        Assert.assertEquals(3, cache.get(3));
        cache.put(4, 4);
        Assert.assertEquals(-1, cache.get(1));
        Assert.assertEquals(3, cache.get(3));
        Assert.assertEquals(4, cache.get(4));
    }
}
