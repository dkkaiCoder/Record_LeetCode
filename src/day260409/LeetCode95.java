package day260409;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.List;

/**
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
 */

public class LeetCode95 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<TreeNode> generateTrees(int n) {
          if(n==0){
              //空树
              return new LinkedList<>();
          }
          return generateTrees(1,n);
    }

    //这个解法就是枚举每个节点 当这个节点作为根节点
    //然后根据二叉搜索树的顺序性 其中序遍历是有序的
    //因此对每个节点i 它的左子树范围一定在[1，i-1] 右子树[i+1,n]
    //之后把他们拼接起来就是最后的结果
    public List<TreeNode> generateTrees(int start,int end){
        //使用一个集合保存最终结果
        List<TreeNode> allTrees=new LinkedList<>();
        //递归出口 如果起始位置比终止位置还大 说明这棵树不存在 返回null
        if(start>end){
            allTrees.add(null);
            return allTrees;
        }
        //枚举过程 让[start,end]每个数都做一次根结点
        for(int i=start;i<=end;i++){
            //再创建两个集合 保存左子树和右子树可能的结果  方便后面合并
            List<TreeNode> leftTrees=generateTrees(start,i-1);
            List<TreeNode> rightTrees=generateTrees(i+1,end);

            //在左右子树的可能结果中选取然后进行合并
            for(TreeNode leftNode:leftTrees){
                for(TreeNode rightNode:rightTrees){
                    //创建一个以i为根的节点
                    TreeNode root=new TreeNode(i);
                    //合并过程
                    root.left=leftNode;
                    root.right=rightNode;
                    //保存到最终结果
                    allTrees.add(root);
                }
            }
        }
        return allTrees;

    }
}
