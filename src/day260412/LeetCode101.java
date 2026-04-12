package day260412;

public class LeetCode101 {
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

    public boolean isSymmetric(TreeNode root) {
        return check(root.left,root.right);
    }

    //判断一棵树的左右结点是否对称
    public boolean check(TreeNode p,TreeNode q){
        if(p==null&&q==null){
            //两个结点都为空 对称
            return true;
        }
        if(p==null||q==null){
            //两个结点有且仅有一个为空 不对称
            return false;
        }

        //一个结点是否对称 取决于该结点 左右孩子val是否相等以及 左右子树是否镜像对称
        return p.val==q.val&&check(p.left,q.right)&&check(p.right,q.left);
    }
}
