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
