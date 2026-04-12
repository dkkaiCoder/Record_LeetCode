package day260412;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode102 {
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

    public List<List<Integer>> levelOrder(TreeNode root) {

        //创建一个集合存储最终结果
        List<List<Integer>> ans=new ArrayList<>();
        //如果为空树直接返回
        if(root==null){
            return ans;
        }
        //初始化一个队列 开始时把根结点放入队列
        //每次出队一个数据 存储到一个list中
        Queue<TreeNode> queue=new LinkedList<>();
        //开始先把根结点入队
        queue.offer(root);
        //只要队列不为空 就说明还有元素没被遍历
        while(!queue.isEmpty()){
            //定义一个变量存储标记每一层未遍历的元素个数
            int size=queue.size();
            //定义一个变量用于存储遍历过程中的出队结点
            TreeNode tmp=null;
            //初始化一个集合存储该层的数据
            List<Integer> list=new LinkedList<>();
            //当size为0时代表当前层数据已经全部读入
            while(size>0){
                //元素出队
                tmp=queue.poll();
                //把出队元素的值存到集合中去
                list.add(tmp.val);
                //如果出队元素的左孩子不为空就添加到队列中
                if(tmp.left!=null){
                    queue.offer(tmp.left);
                }
                //如果出队元素的右子树不为空就添加到队列中
                if(tmp.right!=null){
                    queue.offer(tmp.right);
                }
                //操作完成 更新该层未遍历元素
                size--;
            }
            //把该层的遍历结果存储到ans中
            ans.add(list);
        }
        //返回最终结果
        return ans;
    }
}
