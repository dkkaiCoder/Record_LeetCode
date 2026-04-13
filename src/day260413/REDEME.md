# Log

今天感觉状态不咋样 没咋学 就写了两道比较简单的题 但是第一个锯齿状的层序遍历卡了很久 

其实想明白还是很简单 今天开始学习MySQL了 打算在五一收假之前把MySQL的全部知识学会



# LeetCode 103

> [103. 二叉树的锯齿形层序遍历 - 力扣（LeetCode）](https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/description/?envType=problem-list-v2&envId=binary-tree)

## summary

整个过程需要注意队列当有元素出队时 出队方向不同的时候左右孩子入队的顺序也要相应改变  其实还是基于层序遍历

 ## code

> ```Java
> public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
>         //初始化一个集合保存最终结果
>         List<List<Integer>> ans=new ArrayList<>();
>         //特判：如果root为空 直接返回
>         if(root==null){
>             return ans;
>         }
>         //使用双向队列实现层序遍历
>         Deque<TreeNode> queue=new LinkedList<>();
>         //开始时先把根结点入队
>         queue.offerLast(root);
>         //定义一个变量临时存储出队的元素
>         TreeNode node=null;
>         //定义一个变量标记元素的出队方向 默认为正方向
>         boolean positive=true;
>         //只要队列不为空就说明还有未被访问的元素
>         while(!queue.isEmpty()){
>             List<Integer> list=new ArrayList<>();
>             //定义一个变量记录当前层未被访问的元素总个数
>             int size=queue.size();
>             //只要当前层还有未被访问的元素 就继续访问
>             while(size>0){
>                 if(positive){//当前阶段从队首出队
>                     node=queue.pollFirst();
>                     list.add(node.val);
>                     if(node.left!=null){
>                         queue.offerLast(node.left);
>                     }
>                     if(node.right!=null){
>                         queue.offerLast(node.right);
>                     }
>                 }else{//当前阶段从队尾出队
>                     node=queue.pollLast();
>                     list.add(node.val);
>                     //左右放的顺序也要相反
>                     if(node.right!=null){
>                         queue.offerFirst(node.right);
>                     }
>                     if(node.left!=null){
>                         queue.offerFirst(node.left);
>                     }
>                 }
>                 //每出队一个元素更新还未遍历元素的大小
>                 size--;
>             }
>             ans.add(list);
>             //当来到下一层时更新出队方向
>             positive=!positive;
>         }
>         return ans;
>     }
> ```



# LeetCode 104

> [104. 二叉树的最大深度 - 力扣（LeetCode）](https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/?envType=problem-list-v2&envId=binary-tree)

## summary

可以使用深度优先搜索或者广度优先搜索 整个过程就是简单的遍历 比较容易

## code

> ```Java
> //深度优先搜素
>     public int maxDepth(TreeNode root) {
>         if(root==null) return 0;
>         return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
>     }
> 
>     //层序遍历
>     public int maxDepth(TreeNode root) {
>         if(root==null) return 0;
>         //定义一个变量记录层数
>         int depth=0;
>         //定义一个队列实现层序遍历
>         Queue<TreeNode> queue=new LinkedList<>();
>         //开始时先让根结点入队
>         queue.offer(root);
>         //定义一个变量临时存储出队元素
>         TreeNode node=null;
>         //只要队列不为空就说明还有元素没有被遍历
>         while(!queue.isEmpty()){
>             for(int i=queue.size();i>0;i--){
>                 node=queue.poll();
>                 if(node.left!=null) queue.offer(node.left);
>                 if(node.right!=null) queue.offer(node.right);
>             }
>             depth++;
>         }
>         return depth;
>     }
> ```

