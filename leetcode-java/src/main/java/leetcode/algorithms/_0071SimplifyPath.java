package leetcode.algorithms;

//Given a string path, which is an absolute path (starting with a slash '/') to
//a file or directory in a Unix-style file system, convert it to the simplified ca
//nonical path.
//
// In a Unix-style file system, a period '.' refers to the current directory, a
//double period '..' refers to the directory up a level, and any multiple consecut
//ive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any
// other format of periods such as '...' are treated as file/directory names.
//
// The canonical path should have the following format:
//
//
// The path starts with a single slash '/'.
// Any two directories are separated by a single slash '/'.
// The path does not end with a trailing '/'.
// The path only contains the directories on the path from the root directory to
// the target file or directory (i.e., no period '.' or double period '..')
//
//
// Return the simplified canonical path.
//
//
// Example 1:
//
//
//Input: path = "/home/"
//Output: "/home"
//Explanation: Note that there is no trailing slash after the last directory nam
//e.
//
//
// Example 2:
//
//
//Input: path = "/../"
//Output: "/"
//Explanation: Going one level up from the root directory is a no-op, as the roo
//t level is the highest level you can go.
//
//
// Example 3:
//
//
//Input: path = "/home//foo/"
//Output: "/home/foo"
//Explanation: In the canonical path, multiple consecutive slashes are replaced
//by a single one.
//
//
// Example 4:
//
//
//Input: path = "/a/./b/../../c/"
//Output: "/c"
//
//
//
// Constraints:
//
//
// 1 <= path.length <= 3000
// path consists of English letters, digits, period '.', slash '/' or '_'.
// path is a valid absolute Unix path.
//
// Related Topics String Stack
// 👍 404 👎 121

import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class _0071SimplifyPath {

    /**
     * 解法
     * 利用双端队列（或者栈）存储各级目录或文件，找到每个目录或文件往队列push，
     *   目录为"."，则不push
     *   目录为".."，则弹出队列头部元素（队列不为空）
     *   目录为其他，则push到队列
     */
    public String simplifyPath(String path) {
        // 找到下一个目录或文件的起始字符
        int beginIndexOfNextDir = findBeginIndexOfNextDir(path, 0);
        if (beginIndexOfNextDir == -1) {
            return "/";
        }

        // 存放目录或文件
        Deque<String> deque = new LinkedList();

        int i = beginIndexOfNextDir, j = beginIndexOfNextDir;
        while (j < path.length()) {

            if (path.charAt(j) == '/') {

                // 将目录或文件push到队列
                String dir = path.substring(i, j);
                pushDeque(deque, dir);

                // 找到下一个目录或文件的起始字符
                beginIndexOfNextDir = findBeginIndexOfNextDir(path, j);
                i = beginIndexOfNextDir;
                j = beginIndexOfNextDir;
                if (beginIndexOfNextDir == -1) {
                    break;
                }
                continue;
            }
            j ++;
        }

        // while循环因j = path.length() 结束
        if (i != -1) {
            String dir = path.substring(i, j);
            pushDeque(deque, dir);
        }

        StringBuilder simplifiedPath = new StringBuilder();
        while (!deque.isEmpty()) {
            simplifiedPath.append("/");
            simplifiedPath.append(deque.pollLast());
        }
        if (simplifiedPath.length() == 0) {
            simplifiedPath.append("/");
        }
        return simplifiedPath.toString();
    }

    // 从start处开始找下一个目录或文件
    private int findBeginIndexOfNextDir(String path, int start) {
        int beginIndexOfNextDir = -1;
        for (int i = start; i < path.length(); i++) {
            if (path.charAt(i) != '/') {
                beginIndexOfNextDir = i;
                break;
            }
        }
        return beginIndexOfNextDir;
    }

    private void pushDeque(Deque<String> deque, String dir) {
        if (".".equals(dir)) {
            return;
        }
        if ("..".equals(dir)) {
            if (!deque.isEmpty()) {
                deque.removeFirst();
            }
            return;
        }
        deque.addFirst(dir);
    }


    @Test
    public void test() {
        Assert.assertEquals("/home", simplifyPath("/home/"));
        Assert.assertEquals("/", simplifyPath("/../"));
        Assert.assertEquals("/home/foo", simplifyPath("/home//foo/"));
        Assert.assertEquals("/c", simplifyPath("/a/./b/../../c/"));
    }
}
