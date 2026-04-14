package day260414;

import java.util.HashMap;

public class LeetCode106 {
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

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //哈希表构建
        HashMap<Integer,Integer> map=new HashMap<>();
        int n=inorder.length;
        for(int i=0;i<n;i++){
            map.put(inorder[i],i);
        }
        return build(inorder,0,inorder.length,postorder,0,postorder.length,map);
    }

    public TreeNode build(int[] inorder,int inStart,int inEnd,int[] postorder,int postStart,int postEnd,HashMap<Integer,Integer> map){
        if(inStart==inEnd||postStart==postEnd){
            return null;
        }
        //根结点为后序遍历的最后一个
        int rootVal=postorder[postEnd-1];
        //创建根结点
        TreeNode root=new TreeNode(rootVal);
        //在中序遍历中找到根结点的下标
        int rootIndex=map.get(root.val);
        //记录右子树的元素个数
        int rightNum=inEnd-rootIndex-1;
        //对左右子树进行还原
        root.left=build(inorder,inStart,rootIndex,postorder,postStart,postEnd-rightNum-1,map);
        root.right=build(inorder,rootIndex+1,inEnd,postorder,postEnd-rightNum-1,postEnd-1,map);
        return root;
    }

}
