# log

今天就简单了解了一下LinkedList和ArrarList的底层 



# LeetCode 95

> [95. 不同的二叉搜索树 II - 力扣（LeetCode）](https://leetcode.cn/problems/unique-binary-search-trees-ii/description/?envType=problem-list-v2&envId=binary-tree)



## summary

> 算法过程：回溯法
>
> 1.首先，创建三个集合 这里使用LinkedList （最后解释）用于分别用于存储最终答案以及左右子树的结果
>
> 2.接着递归调用这个函数去把左右子树的可能结果返回到上一步创建的结果中  
>
> 3.最后以每个节点为根 去选择它的左右子树 返回最终结果
>
> 特判:n==0是空树 返回null



## code

> ```Java
>     public List<TreeNode> generateTrees(int n) {
>           if(n==0){
>               //空树
>               return new LinkedList<>();
>           }
>           return generateTrees(1,n);
>     }
> 
>     //这个解法就是枚举每个节点 当这个节点作为根节点
>     //然后根据二叉搜索树的顺序性 其中序遍历是有序的
>     //因此对每个节点i 它的左子树范围一定在[1，i-1] 右子树[i+1,n]
>     //之后把他们拼接起来就是最后的结果
>     public List<TreeNode> generateTrees(int start,int end){
>         //使用一个集合保存最终结果
>         List<TreeNode> allTrees=new LinkedList<>();
>         //递归出口 如果起始位置比终止位置还大 说明这棵树不存在 返回null
>         if(start>end){
>             allTrees.add(null);
>             return allTrees;
>         }
>         //枚举过程 让[start,end]每个数都做一次根结点
>         for(int i=start;i<=end;i++){
>             //再创建两个集合 保存左子树和右子树可能的结果  方便后面合并
>             List<TreeNode> leftTrees=generateTrees(start,i-1);
>             List<TreeNode> rightTrees=generateTrees(i+1,end);
> 
>             //在左右子树的可能结果中选取然后进行合并
>             for(TreeNode leftNode:leftTrees){
>                 for(TreeNode rightNode:rightTrees){
>                     //创建一个以i为根的节点
>                     TreeNode root=new TreeNode(i);
>                     //合并过程
>                     root.left=leftNode;
>                     root.right=rightNode;
>                     //保存到最终结果
>                     allTrees.add(root);
>                 }
>             }
>         }
>         return allTrees;
> 
>     }
> ```



## optimize

这个递归过程是有重复计算的 可以运用动态规划去优化 现在学的还比较少 以后再来补充吧



# LinkedList 与 ArrayList

## 优点

```text
首先,LinkedList底层由双向链表实现 显然优势就是插入删除有优势 而ArrayList底层由数组实现 优势就是随机访问
```

## 缺点

```text
对于LinkedList它进行访问需要去遍历链表 适合增删多的场景
而ArrarList用数组实现 显然当数据多时就是它的痛点 需要去重新开一个更大的数组然后把原来的数据拷贝一份存进去
```

## 选择

```text
我们知道n个结点的二叉排序树的个数满足卡特兰数
在这个算法过程中需要把左右子树都存储起来 n=19的时候卡特兰数已经超过10亿了 这个过程让ArrayList去扩容就会慢很多
整个过程主要是add操作 很显然LinkedList在这里更快
```

