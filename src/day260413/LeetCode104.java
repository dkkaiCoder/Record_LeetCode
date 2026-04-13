package day260413;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode104 {

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

        //深度优先搜素
        public int maxDepth(TreeNode root) {
            if(root==null) return 0;
            return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
        }

        //层序遍历
        public int maxDepth2(TreeNode root) {
            if(root==null) return 0;
            //定义一个变量记录层数
            int depth=0;
            //定义一个队列实现层序遍历
            Queue<TreeNode> queue=new LinkedList<>();
            //开始时先让根结点入队
            queue.offer(root);
            //定义一个变量临时存储出队元素
            TreeNode node=null;
            //只要队列不为空就说明还有元素没有被遍历
            while(!queue.isEmpty()){
                for(int i=queue.size();i>0;i--){
                    node=queue.poll();
                    if(node.left!=null) queue.offer(node.left);
                    if(node.right!=null) queue.offer(node.right);
                }
                depth++;
            }
            return depth;
        }
}
