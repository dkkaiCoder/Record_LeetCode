# Log

今天写了一个Gomoky  比较容易  然后学了Morris遍历可以通过使用O(1)的空间复杂度去实现一些过程 



# LeetCode 99

> [恢复二叉搜索树 - 力扣（LeetCode）](https://leetcode.cn/submissions/detail/717303695/)

## summary

这题核心抓住二叉搜索树中序遍历序列有序  可以用数组把中序序列存起来然后去找到异常的两个值 然后交换位置就行

但是注意这两个异常的值都有a[k]<a[k+1] 前一个a[i]>a[i+1]取i下标的值 后一个a[j]>a[j+1]

注意交换两个结点的时候只需要交换值就好了  不需要去改动结点的指向去交换整个结点、	

## code

> ```Java
> public class LeetCode99 {
>     public class TreeNode {
>         int val;
>         TreeNode left;
>         TreeNode right;
> 
>         TreeNode() {
>         }
> 
>         TreeNode(int val) {
>             this.val = val;
>         }
> 
>         TreeNode(int val, TreeNode left, TreeNode right) {
>             this.val = val;
>             this.left = left;
>             this.right = right;
>         }
>     }
> 
>     //方案一：考虑到二叉搜索树中序遍历的顺序性  对中序遍历进行遍历
>     //两个不满足顺序性的两个数(a[i]>a[i+1] a[j]>a[j+1])
>     // 交换i j+1即可
>     public void recoverTree(TreeNode root) {
>         //创建一个集合,存中序遍历结果
>         List<TreeNode> list=new ArrayList<>();
>         //存储树的中序遍历序列
>         inOrder(root,list);
>         //定义两个变量存储异常结点
>         TreeNode x=null,y=null;
>         //集合的总长
>         int len=list.size();
>         //遍历找到异常结点 这里需要保证i+1不越界
>         for(int i=0;i<len-1;i++){
>             //如果后一个结点比前一个结点小 说明此处有异常结点
>             if(list.get(i).val>list.get(i+1).val){
>                 //默认后一个结点是异常结点
>                 y=list.get(i+1);
>                 //如果此时前一个结点还没有找到就先把前一个给它  继续去找下一个异常结点
>                 if(x==null){
>                     x=list.get(i);
>                 }else{
>                     //当前一个结点不为空说明前一个异常结点x已经找到了 现在又找到了另外一个y 此时直接退出
>                     break;
>                 }
>             }
>         }
> 
>         //交换两个异常结点的val即可
>         int t=x.val;
>         x.val=y.val;
>         y.val=t;
> 
>     }
> 
>     //Morris遍历
>     public static void recoverTree2(TreeNode root){
>         //创建两个变量 cur表示当前结点 mostright表示当前结点左子树的最靠右结点
>         TreeNode cur=root,mostright=null;
>         //保存两个异常结点
>         TreeNode x=null,y=null;
>         //保存上一次遍历的结点
>         TreeNode pre=null;
>         while(cur!=null){
>             mostright=cur.left;
>             if(mostright!=null){
>                 //当前结点左孩子存在
>                 //去寻找左孩子的最右结点
>                 //左孩子的最右结点的右孩子可能是空 也可能是cur
>                 while(mostright.right!=null&&mostright.right!=cur){
>                     mostright=mostright.right;
>                 }
>                 //找到左孩子的最右结点了 判断mostright右孩子是否为空
>                 if(mostright.right==null){//第一次到达
>                     //mostright为空
>                     //左孩子最右结点指向当前结点
>                     mostright.right=cur;
>                     //当前结点指向它的左孩子
>                     cur=cur.left;
>                 }else{//第二次到达
>                     if(pre!=null&&cur.val<pre.val){
>                         if(x==null) x=pre;
>                         y=cur;
>                     }
>                     pre=cur;
>                     //mostright不为空
>                     //就让mostright的右孩子指向空
>                     mostright.right=null;
>                     //第二次访问到某个结点 就去访问cur的右孩子
>                     cur=cur.right;
> 
>                 }
>             }else{
>                 //当前结点的左孩子不存在
>                 if(pre!=null&&pre.val>cur.val){
>                     if(x==null) x=pre;
>                     y=cur;
>                 }
>                 pre=cur;
>                 cur=cur.right;
>             }
>         }
> 
>         int t=x.val;
>         x.val=y.val;
>         y.val=t;
>     }
> 
>     //Morris遍历
>     //算法思路：
>     //1.开始时 cur指向root 当cur为空时结束整个过程
>     //2.整个过程如cur的左孩子不存在 cur指向右孩子
>     //3.cur的左孩子存在 就找到cur左孩子的最右结点 mostright
>     //如果mostright右孩子为空 就让mostright的右孩子指向cur  然后cur向左移动
>     //如果mostright右孩子不为空 就让mostright的右孩子指向空 然后cur向右移动
>     public static void morris(TreeNode root){
>         //创建两个变量 cur表示当前结点 mostright表示当前结点左子树的最靠右结点
>         TreeNode cur=root,mostright=null;
>         while(cur!=null){
>             mostright=cur.left;
>             if(mostright!=null){
>                 //当前结点左孩子存在
>                 //去寻找左孩子的最右结点
>                 //左孩子的最右结点的右孩子可能是空 也可能是cur
>                 while(mostright.right!=null&&mostright.right!=cur){
>                     mostright=mostright.right;
>                 }
>                 //找到左孩子的最右结点了 判断mostright右孩子是否为空
>                 if(mostright.right==null){
>                     //mostright为空
>                     //左孩子最右结点指向当前结点
>                     mostright.right=cur;
>                     //当前结点指向它的左孩子
>                     cur=cur.left;
>                     continue;
>                 }else{
>                     //mostright不为空
>                     //就让mostright的右孩子指向空
>                     mostright.right=null;
> 
>                 }
> 
>             }
>             //当前结点的左孩子不存在  or 第二次访问到某个结点 就去访问cur的右孩子
>             cur=cur.right;
> 
>         }
>     }
>     
>     //递归版本中序遍历
>     public void inOrder(TreeNode node,List<TreeNode> list){
>         if(node==null){
>             return;
>         }
>         inOrder(node.left,list);
>         list.add(node);
>         inOrder(node.right,list);
>     }
> }
> ```
>
> 

## optimize

上面已经给出优化了 就是使用Morris遍历

在Morris遍历过程基础上再设置一个pre指针  每次只要cur的左孩子为空 或者cur第二次去到某个结点的时候 就跟pre进行比较

然后更新pre 如果pre.val大于cur.val说明前一个结点比后一个结点大 是异常结点 记录下来  最终循环结束 对两个异常结点进行交换即可