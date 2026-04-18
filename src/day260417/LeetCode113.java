package day260417;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode113 {

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


    //算法思路：
    //dfs遍历每一条路径  如果有一条路径满足条件  就添加到列表中
    //如果该条路径不满足就回到上一个结点 同时在列表中需要移除那个不满足路径所保存的结点
    //dfs过程往下过程中不断更新需要需要的targetSum
    //创建列表保存结果
    List<List<Integer>> res=new LinkedList<>();
    //用于深度优先搜索过程中保存路径数据
    List<Integer> list=new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root,targetSum);
        return res;
    }

    public void dfs(TreeNode root,int targetSum){
        //来到一个空结点 直接返回
        if(root==null){
            return;
        }
        //先把该点放到临时列表中
        list.add(root.val);
        if(root.val==targetSum&&root.left==null&&root.right==null){
            //如果当前结点的值等于目标值 那么就拷贝列表将当前路径放入最终集合中
            res.add(new ArrayList<>(list));
        }
        //如果不满足目标值 就继续往下去寻找
        dfs(root.left,targetSum-root.val);
        dfs(root.right,targetSum-root.val);
        //最后寻找完左右子树回到当前层的时候需要把当前结点从路径数据中移除  之后再回到上一层
        list.remove(list.size()-1);
    }
}
