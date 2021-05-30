package leetcode.algorithms;

//There are n cities. Some of them are connected, while some are not. If city a
//is connected directly with city b, and city b is connected directly with city c,
// then city a is connected indirectly with city c.
//
// A province is a group of directly or indirectly connected cities and no other
// cities outside of the group.
//
// You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the
//ith city and the jth city are directly connected, and isConnected[i][j] = 0 othe
//rwise.
//
// Return the total number of provinces.
//
//
// Example 1:
//
//
//Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//Output: 2
//
//
// Example 2:
//
//
//Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
//Output: 3
//
//
//
// Constraints:
//
//
// 1 <= n <= 200
// n == isConnected.length
// n == isConnected[i].length
// isConnected[i][j] is 1 or 0.
// isConnected[i][i] == 1
// isConnected[i][j] == isConnected[j][i]
//
// Related Topics Depth-first Search Union Find
// 👍 3109 👎 187

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _0547NumberOfProvinces {

    /**
     * 解法1：深度优先搜索
     * 遍历所有城市，对于每个城市，如果该城市尚未被访问过，则从该城市开始深度优先搜索，
     * 通过矩阵 isConnected 得到与该城市直接相连的城市有哪些，这些城市和该城市属于同一个连通分量，
     * 然后对这些城市继续深度优先搜索，直到同一个连通分量的所有城市都被访问到，即可得到一个省份。
     */
    public int findCircleNum(int[][] isConnected) {
        int circle = 0;
        // 访问过的城市
        Set<Integer> citiesInProvince = new HashSet<>();
        for (int i = 0; i < isConnected.length; i++) {
            // 第i个城市已在某个省份中，则跳过
            if (citiesInProvince.contains(i)) {
                continue;
            }
            // 找第i个城市所处的省份
            findCircle(isConnected, i, citiesInProvince);
            circle ++;
        }
        return circle;
    }

    // dfs
    private void findCircle(int[][] isConnected, int city, Set<Integer> citiesInProvince) {
        int[] cityConnection = isConnected[city];
        // 第city个城市在当前省份中
        citiesInProvince.add(city);
        for (int i = 0; i < cityConnection.length; i++) {
            if (citiesInProvince.contains(i)) {
                continue;
            }
            if (cityConnection[i] == 1) {
                // 第i个城市在当前省份中
                citiesInProvince.add(i);
                // 找第i个城市相连的其他城市
                findCircle(isConnected, i, citiesInProvince);
            }
        }
    }



    /**
     * 解法2：并查集
     *
     * 并查集是以字典为基础的数据结构，它的基本功能是合并集合中的元素，查找集合中的元素。
     * 并查集跟树有些类似，只不过她跟树是相反的。在树这个数据结构里面，每个节点会记录它的子节点。在并查集里，每个节点会记录它的父节点。
     * 节点是相互连通的（从一个节点可以到达另一个节点），那么他们在同一棵树里，或者说在同一个集合里，或者说他们的祖先是相同的。
     */
    public int findCircleNum2(int[][] isConnected) {
        UnionFind uf = new UnionFind();
        for (int i = 0; i < isConnected.length; i++) {
            uf.add(i);
            for (int j = 0; j < i; j++) {
                if (isConnected[i][j] == 1) {
                    uf.merge(i, j);
                }
            }
        }
        return uf.numOfSets;
    }

    class UnionFind {
        // 记录父节点 当前节点->父节点
        private Map<Integer,Integer> father;
        // 记录集合的数量
        private int numOfSets = 0;

        public UnionFind() {
            father = new HashMap<Integer,Integer>();
            numOfSets = 0;
        }

        // 添加新节点
        public void add(int x) {
            if (!father.containsKey(x)) {
                father.put(x, null);
                numOfSets++;
            }
        }

        // 合并两个节点
        public void merge(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY){
                father.put(rootX,rootY);
                numOfSets--;
            }
        }

        // 查找根节点
        public int find(int x) {
            int root = x;

            while(father.get(root) != null){
                root = father.get(root);
            }

            // 路径压缩
            // 并查集只是记录了节点之间的连通关系，而节点相互连通只需要有一个相同的祖先就可以了
            while(x != root){
                int original_father = father.get(x);
                father.put(x,root);
                x = original_father;
            }

            return root;
        }

        // 判断两节点是否相连
        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }

        public int getNumOfSets() {
            return numOfSets;
        }

    }

    @Test
    public void test() {
        Assert.assertEquals(1, findCircleNum(new int[][]{{1}}));
        Assert.assertEquals(2, findCircleNum(new int[][]{{1,1,0},{1,1,0},{0,0,1}}));
        Assert.assertEquals(3, findCircleNum(new int[][]{{1,0,0},{0,1,0},{0,0,1}}));
        Assert.assertEquals(1, findCircleNum(new int[][]{{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}}));
    }
}
