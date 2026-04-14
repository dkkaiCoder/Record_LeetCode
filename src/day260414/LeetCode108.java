package day260414;

public class LeetCode108 {

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

    //算法思想：
    //一个有序数组一定对应着一棵平衡二叉树
    //只需要每次将数组中点值作为根结点的val 然后再用同样的方法去递归构建左右子树即可
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums,0,nums.length-1);
    }

    public TreeNode buildBST(int[] nums,int l,int r){
        if(l>r){
            return null;
        }
        int mid=(l+r)/2;
        TreeNode root=new TreeNode(nums[mid]);
        root.left=buildBST(nums,l,mid-1);
        root.right=buildBST(nums,mid+1,r);
        return root;
    }
}
