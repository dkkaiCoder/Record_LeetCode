# Log

>  今天复习了JavaSE的一些基础  具体如下：
>
> ```Java
> 数组扩容：数据类型[] 数组名=copeof(原数组,新数组长度);
> 数组拷贝：System.arraycopy(原数组，拷贝开始的位置，新数组,存放开始的位置,拷贝元素的个数)
> Arrays.fill(数组,value);
> ```



# LeetCode 98

> [98. 验证二叉搜索树 - 力扣（LeetCode）](https://leetcode.cn/problems/validate-binary-search-tree/description/)



## summary

> 这个题比较容易 抓住二叉搜索树一个结点的上下界 同时注意数据范围就好

## code

```Java
public class LeetCode98 {
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

    public boolean isValidBST1(TreeNode root) {
        //特判:空树是有序的
        if(root==null) return true;
        //因为-2^31 <= Node.val <= 2^31 - 1 当val取到最大时不能直接返回false 因此考虑使用long
        return isValid(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }


    //根据二叉搜索树中序遍历序列有序 由题目描述是升序的
    //因此可以按从小到大顺序去遍历每个结点 如果后一个结点的val>=前一个结点 就不满足
    //这里在一棵树中要想回到上一个结点 需要基于栈
    public boolean isValidBST(TreeNode root) {
        //初始化一个栈
        Deque<TreeNode> stack=new LinkedList<>();
        //把第一个结点也就是左子树的最左边非空结点的值设置为无穷小
        long inorder=Long.MIN_VALUE;
        //初始化一个变量存前一个结点
        TreeNode node=root;
        //只有栈和结点都为空的时候 说明已经没有元素了
        while(node!=null||!stack.isEmpty()){
            //先对路径上结点入栈
            while(node!=null){
                stack.push(node);
                node=node.left;
            }
            //返回null的上一个结点
            node=stack.pop();
            //上一个结点val大于当前结点 不满足顺序性
            if(node.val<=inorder){
                return false;
            }
            //更新用于存储上一个结点的val
            inorder=node.val;
            node=node.right;
        }
        //这个过程中每个结点严格满足递增 整棵树肯定是二叉搜索树
        return true;
    }

    //递归方法 核心思想就是定义一个上下界 在一次次递归过程去缩小每个结点的范围 如果超出这个范围就不满足
    //对一个二叉搜索树的结点 一定有一个范围
    //如果超出这个范围 说明不是严格满足题目要求
    //对于一个结点 它的范围由左孩子的最右结点以及右孩子的最左结点界定
    public static boolean isValid(TreeNode node,long min,long max){
        //对于null肯定满足 也是递归出口
        if(node==null) return true;

        //这里来到一个结点的时候 它的左孩子肯定比该结点值小 更新max
        boolean left=isValid(node.left,min,node.val);
        //右孩子比该结点值大 更新min
        boolean right=isValid(node.right,node.val,max);

        //如果该结点值越界 即结点值小于min or 大于max  说明中序遍历不是严格递增
        //因为是严格递增 因此这个min or max一定是某个结点的val 相等时也不满足严格递增
        if(node.val<=min||node.val>=max){
            return false;
        }

        //只有左右子树都满足有序最终才严格满足
        return left&&right;
    }
}
```

