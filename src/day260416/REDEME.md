# log

今天学习了MySQl常用函数 然后对昨天MySQL的数据 插入 删除 更改 查询进行实现



# LeetCode 112

> [112. 路径总和 - 力扣（LeetCode）](https://leetcode.cn/problems/path-sum/submissions/718821697/?envType=problem-list-v2&envId=binary-tree)

## summary

第一个递归过程很容易想到   抓住叶子节点判断当前值和目标值是否相等 满足返回true 否则对左右子树进行判断 

第二个就是回溯自底向上过程  此过程记录一个pathSum 如果pathSum==target 就返回 否则就继续回到上一节点

## code

```Java
//思路一：常规递归方法
    //当整棵树为空的时候 路径不存在 直接返回false
    //当来到一个叶子节点的时候 只需要验证当前节点的val是否等于targetSum
    //然后递归遍历左右子树看是否存在等于targetSum-当前节点val的路径
    public boolean hasPathSum1(TreeNode root, int targetSum) {
        //空树
        if(root==null){
            return false;
        }
        //叶子节点
        if(root.left==null&&root.right==null){
            return root.val==targetSum;
        }

        return hasPathSum(root.left,targetSum-root.val)||hasPathSum(root.right,targetSum-root.val);
    }

    //思路二：dfs
    //定义一个dfs函数看是否存在路径值之和等于targetSum 不断更新路径长度 当当前路径长度等于targetSum时说明找到了
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null){
            return false;
        }
        return dfs(root,root.val,targetSum);
    }

    public boolean dfs(TreeNode node ,int pathSum,int targetSum){
        //空树
        if(node==null){
            return false;
        }
        //如果路径和等于目标值，并且当前节点是叶子节点（即左右子节点都为空），返回true
        if(pathSum==targetSum&&node.left==null&&node.right==null){
            return true;
        }

        //默认不存在
        boolean lflag=false,rflag=false;
        //左子树存在进行遍历
        if(node.left!=null){
            lflag=dfs(node.left,pathSum+node.left.val,targetSum);
        }
        //右子树存在进行遍历
        if(node.right!=null){
            rflag=dfs(node.right,pathSum+node.right.val,targetSum);
        }
        // 如果左子树或右子树中存在满足条件的路径，返回true，否则返回false
        return lflag||rflag;
    }
```

