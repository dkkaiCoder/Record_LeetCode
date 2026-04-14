package day260414;

import java.util.HashMap;

public class LeetCode105 {
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

    //方案一：递归
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        return build(preorder,0,preorder.length,inorder,0,inorder.length);
    }

    public TreeNode build(int[] preorder,int preStart,int preEnd,int[] inorder,int inStart,int inEnd){
        if(preStart==preEnd||inStart==inEnd){
            return null;
        }
        //根结点的val一定是先序遍历的第一个元素
        int rootVal=preorder[preStart];
        //初始化一个根结点
        TreeNode root=new TreeNode(rootVal);
        //在中序遍历中找到根结点的位置  根结点的左侧即为左子树的数据  右侧即为右子树的数据
        int rootIndex=-1;
        for(int i=inStart;i<inEnd;i++){
            if(inorder[i]==rootVal){
                rootIndex=i;
                break;
            }
        }
        //左子树的元素个数为中序遍历根结点所在的下标减去左子树的起始坐标
        int leftNum=rootIndex-inStart;
        //此时对左右子树进行还原即可
        root.left=build(preorder,preStart+1,preStart+leftNum+1,inorder,inStart,rootIndex);
        root.right=build(preorder,preStart+leftNum+1,preEnd,inorder,rootIndex+1,inEnd);
        return root;
    }

    //方案一优化：在中序中寻找根结点时使用哈希表
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        //对中序序列构建哈希表
        HashMap<Integer,Integer> map=new HashMap<>();
        int n=inorder.length;
        for(int i=0;i<n;i++){
            //查找某个数据的下标
            map.put(inorder[i],i);
        }
        return build(preorder,0,preorder.length,inorder,0,inorder.length,map);
    }

    public TreeNode build(int[] preorder,int preStart,int preEnd,int[] inorder,int inStart,int inEnd,HashMap<Integer,Integer> map){
        if(preStart==preEnd||inStart==inEnd){
            return null;
        }
        //根结点的val一定是先序遍历的第一个元素
        int rootVal=preorder[preStart];
        //初始化一个根结点
        TreeNode root=new TreeNode(rootVal);
        //在中序遍历中找到根结点的位置  根结点的左侧即为左子树的数据  右侧即为右子树的数据
        int rootIndex=map.get(rootVal);
        //左子树的元素个数为中序遍历根结点所在的下标减去左子树的起始坐标
        int leftNum=rootIndex-inStart;
        //此时对左右子树进行还原即可
        root.left=build(preorder,preStart+1,preStart+leftNum+1,inorder,inStart,rootIndex,map);
        root.right=build(preorder,preStart+leftNum+1,preEnd,inorder,rootIndex+1,inEnd,map);
        return root;
    }

}
