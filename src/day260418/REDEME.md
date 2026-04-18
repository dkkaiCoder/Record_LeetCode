# Log

今天对SQL基础知识进行复习  然后写了项目的需求文档  对操作系统开始了解 准备一个月学完NJU的操作系统



# LeetCode 114

> [114. 二叉树展开为链表 - 力扣（LeetCode）](https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/description/?envType=problem-list-v2&envId=binary-tree)

## summary

从先序遍历入手很容易想到 但是要优化需要考虑到 cur左子树的根节点展开后是cur的后驱节点，所以cur的右边指向原左子树
而原右子树会等左子树遍历完之后才开始遍历，所以把它接到cur的左子树的最右边

## code

```Java
/先得到整棵树的先序遍历序列  然后在对他们进行连接
    public void flatten1(TreeNode root) {
        List<TreeNode> list=new LinkedList<>();
        preOrder(root,list);
        int size=list.size();
        TreeNode pre=null,cur=null;
        for(int i=1;i<size;i++){
            pre=list.get(i-1);
            cur=list.get(i);
            pre.left=null;
            pre.right=cur;
        }
    }

    //Morris遍历
    //cur左子树的根节点展开后是cur的后驱节点，所以cur的右边指向原左子树
    //而原右子树会等左子树遍历完之后才开始遍历，所以把它接到cur的左子树的最右边
    public void flatten2(TreeNode root) {
        TreeNode cur=root,rightmost=null;
        while(cur!=null){
            rightmost=cur.left;
            if(rightmost!=null){
                while(rightmost.right!=null&&rightmost.right!=cur){
                    rightmost=rightmost.right;
                }
                rightmost.right=cur.right;
                cur.right=cur.left;
                cur.left=null;
            }
            cur=cur.right;

        }
    }

    public void preOrder(TreeNode root, List<TreeNode> list){
        if(root==null){
            return;
        }
        list.add(root);
        preOrder(root.left,list);
        preOrder(root.right,list);
    }
```

