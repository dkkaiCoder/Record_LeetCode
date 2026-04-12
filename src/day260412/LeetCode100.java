package day260412;

import day260411.LeetCode99;
public class LeetCode100 {
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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null){
            //两棵空树 相同
               return true;
        }
        if(p==null||q==null){
            //有且仅有一棵树为空 不同
            return false;
        }
        if(p.val!=q.val){
            //两颗树根结点不相等 不同
            return false;
        }
        //dfs
        return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);

    }


}
