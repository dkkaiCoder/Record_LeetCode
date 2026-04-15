package day260415;

public class LeetCode110 {

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

    //对每个结点维护一个树的高度即可
    public boolean isBalanced(TreeNode root) {
        //默认空树是一棵平衡二叉树
        if(root==null){
            return true;
        }
        //只要树高不为-1说明就是一棵AVL树
        return Height(root)!=-1;
    }

    public int Height(TreeNode node){
        //空结点的高度为0
        if(node==null){
            return 0;
        }
        //左子树的高度
        int left=Height(node.left);
        //只要左子树的树高不满足就退出
        if(left==-1){
            return -1;
        }
        //右子树的高度
        int right=Height(node.right)+1;
        //只要右子树高不满足 或者左右结点高度差大于1
        if(right==-1||Math.abs(left-right)>1){
            return -1;
        }
        //返回该结点的高度
        return Math.max(left,right)+1;
    }



}
