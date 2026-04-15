# Log

今天学习了MySQL数据的增删改查 但是还没来得及去敲代码  今天学到了一个快慢指针觉得很妙

然后感觉自己还是需要安排好自己的时间  不然一天时间完全不够用

今天觉得自己刷过的算法题 其实还是忘记 所以决定以后按月复习吧 每刷题一个月 然后对这个月做过的题进行复盘

# LeetcCode 109

> [109. 有序链表转换二叉搜索树 - 力扣（LeetCode）](https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree/?envType=problem-list-v2&envId=binary-tree)

## summary

这个如果使用快慢指针来做空间复杂度会偏低一些  属于是一个优化 

也可以按照把一个有序数组的折半查找还原AVL树的过程 这个是最容易想到的  

## code

```Java
//思路一：将链表中的数据存储到数组中
    //然后基于一个有序数组的折半查找一定有对应的平衡二叉树
    //对左右子树进行分治
    public TreeNode sortedListToBST1(ListNode head) {
        List<Integer> list=new ArrayList<>();
        //空链表
        if(head==null){
            return null;
        }
        //只含有一个结点的链表
        if(head.next==null){
            return new TreeNode(head.val);
        }
        ListNode node=head;
        while(node!=null){
            list.add(node.val);
            node=node.next;
        }
        return build(list,0,list.size()-1);
    }

    public TreeNode build(List<Integer> list,int l,int r){
        if(l>r){
            return null;
        }
        int mid=(l+r)/2;
        //循环过程中以当前mid结点作为每棵子树的根
        TreeNode node=new TreeNode(list.get(mid));
        node.left=build(list,l,mid-1);
        node.right=build(list,mid+1,r);
        return node;
    }

    //思路二：用快慢指针进行优化
    //所谓快慢指针就是两个遍历的指针 fast slow
    //整个过程slow每次移动一步 fast移动两步 利用相对运动的思想 当fast指向最后一个结点的时候 slow一定在中间
    //把这个slow结点拿出来作为根结点然后分治就行 相当于把整个单链表分成了左右两部分
    public TreeNode sortedListToBST(ListNode head) {
        //空链表直接返回
        if(head==null){
            return null;
        }
        //只存在一个结点 创建一个结点后存储val直接返回
        if(head.next==null){
            return new TreeNode(head.val);
        }
        ListNode slow=head;
        ListNode fast=head;
        //设置一个pre指针用于修改单链表左侧的next指针
        ListNode pre=null;
        while(fast!=null&&fast.next!=null){
            pre=slow;
            slow=slow.next;
            fast=fast.next.next;
        }
        //把中间结点的左侧链表断开
        pre.next=null;
        //当退出循环时slow指针指向中间 此时拿出它的值创建结点作为根结点
        TreeNode node=new TreeNode(slow.val);
        node.left=sortedListToBST(head);
        node.right=sortedListToBST(slow.next);
        return node;
    }
```



## LeetCode 110

> [110. 平衡二叉树 - 力扣（LeetCode）](https://leetcode.cn/problems/balanced-binary-tree/?envType=problem-list-v2&envId=binary-tree)

## summary

这个就是一个简单的AVL的还原 只需要对每个结点维护一个高度即可 整体还是dfs的思路

## code

```Java
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

```



## LeetCode 111

> [111. 二叉树的最小深度 - 力扣（LeetCode）](https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/?envType=problem-list-v2&envId=binary-tree)

## summary

这个题需要注意题目关键的一句话 叶子节点是没有子节点的节点  也就是说一个节点只有当左右孩子都为null时才是叶子节点

理解了这个整体思路比较简单

## code

```Java
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
```

