package day260415;

import java.util.ArrayList;
import java.util.List;

public class LeetCode109 {
    public class ListNode {
         int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

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
 }
