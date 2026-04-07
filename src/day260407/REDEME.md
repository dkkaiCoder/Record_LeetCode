# Log

```text
今天开始刷LeetCode的习题 提升自己的代码能力 每天最少刷一个题吧 慢慢养成刷题习惯 刚刚学习完数据结构 但是那个我是使用C语言实现的
上个学习学完了JavaSE但是过了三个月了没咋使用 感觉忘记的差不多了 用Java去实现还是不咋会 但是我会慢慢去熟悉 今天也开始在回去复习了 刷题过程有不懂的知识我也先学习掌握后记录一下吧 主要记录还是一下自己以后需要注意的点 还有从中的收获吧
```

```text
我会按照自己遇到的困难 然后解决方案 如果有多个通过比较去选择后为什么选择进行一点记录 最后把整个代码给出
```



# LeetCode 94

## summary

```text
1.树的中序遍历这个递归版本我已经掌握了 但是它还有一个用栈去实现的 这个不太熟悉了 今天对它进行复习 然后实现一下
2.对Java语言还是不太熟悉 希望自己慢一点 然后深一点
```



## problem

```text
1.引用数据类型的默认值都是null --->递归出口的设置root==null
2.List<Integer> list这个属于引用数据类型 是在堆上创建的 所以在inOrder在递归的时候不会把数据添加到多个列表 能够实现累计
```



## code

```Java
package day260407;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/?envType=problem-list-v2&envId=tree
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 * class Solution {
 *     public List<Integer> inorderTraversal(TreeNode root) {
 *
 *     }
 * }
 *
 */

public class LeetCode94 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        inOrder(root,list);
        return list;
    }

    public void inOrder(TreeNode root,List<Integer> res){
        if(root==null){
            return;
        }
        inOrder(root.left,res);
        res.add(root.val);
        inOrder(root.right,res);
    }
}

```



## optimize

```text
我看题解还可以使用莫里斯遍历来实现 但是我现在Java基础一般就以后来补吧 先留白
```

