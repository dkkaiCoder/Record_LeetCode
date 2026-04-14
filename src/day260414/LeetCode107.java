package day260414;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LeetCode107 {
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

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        //初始化一个列表存储最终结果
        List<List<Integer>> ans=new LinkedList<>();
        //特判：空树直接返回
        if(root==null){
            return ans;
        }
        //初始化一个队列存储遍历过程的中间数据
        Deque<TreeNode> queue=new LinkedList<>();
        //开始把根结点入队
        queue.offerLast(root);
        //只要队列不为空就说明还存在未遍历到的元素
        while(!queue.isEmpty()){
            //初始化一个列表存储每一层的数据
            List<Integer> list=new LinkedList<>();
            //初始化一个变量保存该层未被遍历的元素个数
            int size=queue.size();
            //初始化一个变量保存出队元素
            TreeNode node=null;
            //循环该层同时如果每个数据左右孩子不为空就把孩子入队
            while(size>0){
                //元素出队
                node=queue.poll();
                //保存该元素对应的值
                list.add(node.val);
                //只要左右孩子存在就入队
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
                //元素出队后更新还未遍历的元素个数
                size--;
            }
            //每次在列表ans中进行头插实现栈的功能
            ans.add(0,list);
        }
        //返回最终结果
        return ans;
    }
}
