package day260415;

public class LeetCode111 {

    public class TreeNode {
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

    public int minDepth(TreeNode root) {
        //空树直接返回最大高度为0
        if(root==null){
            return 0;
        }
        int left=minDepth(root.left);
        int right=minDepth(root.right);
        //如果左右子树中有且只有一个为空 那么就存在的那棵子树基础上加1
        return (root.left==null||root.right==null)?left+right+1:Math.min(left,right)+1;
    }





}
