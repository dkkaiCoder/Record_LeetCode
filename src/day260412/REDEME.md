# Log

今天写了三道比较容易的题  然后对之前从递归入手一维动态规划进行复习  还复现昨天学习Morris遍历时一些其他题进行复现

感觉之前Java基础就是纯敲代码了 一点自己的思考都没有  为什么这么设计

# LeetCode 100

> [100. 相同的树 - 力扣（LeetCode）](https://leetcode.cn/problems/same-tree/description/?envType=problem-list-v2&envId=binary-tree)



## summary

同时对两棵树进行遍历即可 只要每个结点值对应相同就行

## code

```Java
public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null){
            //两棵空树 相同
               return true;
        }
        if(p==null||q==null){
            //有且仅有一棵树为空 不同
            return false;
        }
        if(p.val!=q.val){
            //两颗树根结点不相等 不同
            return false;
        }
        //dfs
        return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);

    }
```



# LeetCode 101

> [101. 对称二叉树 - 力扣（LeetCode）](https://leetcode.cn/problems/symmetric-tree/description/?envType=problem-list-v2&envId=binary-tree)

## summary

抓住左右孩子节点相同  然后镜像对称即可 

## code

```Java
public boolean isSymmetric(TreeNode root) {
        return check(root.left,root.right);
    }

    //判断一棵树的左右结点是否对称
    public boolean check(TreeNode p,TreeNode q){
        if(p==null&&q==null){
            //两个结点都为空 对称
            return true;
        }
        if(p==null||q==null){
            //两个结点有且仅有一个为空 不对称
            return false;
        }

        //一个结点是否对称 取决于该结点 左右孩子val是否相等以及 左右子树是否镜像对称
        return p.val==q.val&&check(p.left,q.right)&&check(p.right,q.left);
    }
```



# LeetCode 102

> [102. 二叉树的层序遍历 - 力扣（LeetCode）](https://leetcode.cn/problems/binary-tree-level-order-traversal/description/?envType=problem-list-v2&envId=binary-tree)

## summary

层序遍历完整过程就是 先初始化一个队列 把根结点放入队列中  只要队列不为空 就说明还有没有遍历到的序列 继续循环

这里需要对每一层数据进行标记 只需要每次遍历之前记录队列的size 只要size不为0就说明该层还未结束 

但是不能直接调用队列方法来判断  因为在循环过程中 队列的实际长度是会发生变化的

在此过程只要出队一个元素 同时就把它的非空左右孩子结点添加到队列中

最后如果队列为空就能得到最终答案



## code

```Java
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
```

