package day260413;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LeetCode103 {

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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        //初始化一个集合保存最终结果
        List<List<Integer>> ans=new ArrayList<>();
        //特判：如果root为空 直接返回
        if(root==null){
            return ans;
        }
        //使用双向队列实现层序遍历
        Deque<TreeNode> queue=new LinkedList<>();
        //开始时先把根结点入队
        queue.offerLast(root);
        //定义一个变量临时存储出队的元素
        TreeNode node=null;
        //定义一个变量标记元素的出队方向 默认为正方向
        boolean positive=true;
        //只要队列不为空就说明还有未被访问的元素
        while(!queue.isEmpty()){
            List<Integer> list=new ArrayList<>();
            //定义一个变量记录当前层未被访问的元素总个数
            int size=queue.size();
            //只要当前层还有未被访问的元素 就继续访问
            while(size>0){
                if(positive){//当前阶段从队首出队
                    node=queue.pollFirst();
                    list.add(node.val);
                    if(node.left!=null){
                        queue.offerLast(node.left);
                    }
                    if(node.right!=null){
                        queue.offerLast(node.right);
                    }
                }else{//当前阶段从队尾出队
                    node=queue.pollLast();
                    list.add(node.val);
                    //左右放的顺序也要相反
                    if(node.right!=null){
                        queue.offerFirst(node.right);
                    }
                    if(node.left!=null){
                        queue.offerFirst(node.left);
                    }
                }
                //每出队一个元素更新还未遍历元素的大小
                size--;
            }
            ans.add(list);
            //当来到下一层时更新出队方向
            positive=!positive;
        }
        return ans;
    }


}
