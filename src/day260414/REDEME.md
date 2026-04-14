# Log

今天学了MySQL的基础 SQL的分类 数据库的创建 更改 删除 列类型 数据表的创建 更改 删除

感觉算法题慢慢有手感了 后面肯定也是需要去多学一些算法

然后希望能合理利用时间 安排时间学习去学习CSAPP 

# LeetCode 105

> [105. 从前序与中序遍历序列构造二叉树 - 力扣（LeetCode）](https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/?envType=problem-list-v2&envId=binary-tree)

## summary

先序遍历第一个元素是根结点 在中序遍历中找到该元素 就可以将元素分为左右子树

然后不断递归去构建子树的根结点  最终就能成功构建这棵树

## code

```JAva
//方案一：递归
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        return build(preorder,0,preorder.length,inorder,0,inorder.length);
    }

    public TreeNode build(int[] preorder,int preStart,int preEnd,int[] inorder,int inStart,int inEnd){
        if(preStart==preEnd||inStart==inEnd){
            return null;
        }
        //根结点的val一定是先序遍历的第一个元素
        int rootVal=preorder[preStart];
        //初始化一个根结点
        TreeNode root=new TreeNode(rootVal);
        //在中序遍历中找到根结点的位置  根结点的左侧即为左子树的数据  右侧即为右子树的数据
        int rootIndex=-1;
        for(int i=inStart;i<inEnd;i++){
            if(inorder[i]==rootVal){
                rootIndex=i;
                break;
            }
        }
        //左子树的元素个数为中序遍历根结点所在的下标减去左子树的起始坐标
        int leftNum=rootIndex-inStart;
        //此时对左右子树进行还原即可
        root.left=build(preorder,preStart+1,preStart+leftNum+1,inorder,inStart,rootIndex);
        root.right=build(preorder,preStart+leftNum+1,preEnd,inorder,rootIndex+1,inEnd);
        return root;
    }

    //方案一优化：在中序中寻找根结点时使用哈希表
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        //对中序序列构建哈希表
        HashMap<Integer,Integer> map=new HashMap<>();
        int n=inorder.length;
        for(int i=0;i<n;i++){
            //查找某个数据的下标
            map.put(inorder[i],i);
        }
        return build(preorder,0,preorder.length,inorder,0,inorder.length,map);
    }

    public TreeNode build(int[] preorder,int preStart,int preEnd,int[] inorder,int inStart,int inEnd,HashMap<Integer,Integer> map){
        if(preStart==preEnd||inStart==inEnd){
            return null;
        }
        //根结点的val一定是先序遍历的第一个元素
        int rootVal=preorder[preStart];
        //初始化一个根结点
        TreeNode root=new TreeNode(rootVal);
        //在中序遍历中找到根结点的位置  根结点的左侧即为左子树的数据  右侧即为右子树的数据
        int rootIndex=map.get(rootVal);
        //左子树的元素个数为中序遍历根结点所在的下标减去左子树的起始坐标
        int leftNum=rootIndex-inStart;
        //此时对左右子树进行还原即可
        root.left=build(preorder,preStart+1,preStart+leftNum+1,inorder,inStart,rootIndex,map);
        root.right=build(preorder,preStart+leftNum+1,preEnd,inorder,rootIndex+1,inEnd,map);
        return root;
    }
```

## optimize

发现在每次递归左右子树的时候都需要去寻找根结点在中序遍历序列的下标 由此想到使用哈希表来进行优化



# LeetCode 106

> [106. 从中序与后序遍历序列构造二叉树 - 力扣（LeetCode）](https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/?envType=problem-list-v2&envId=binary-tree)



## summary 

这个题整体思路和LeetCode 105一样 参考上面一题过程即可

## code

```JAva
public TreeNode buildTree(int[] inorder, int[] postorder) {
        //哈希表构建
        HashMap<Integer,Integer> map=new HashMap<>();
        int n=inorder.length;
        for(int i=0;i<n;i++){
            map.put(inorder[i],i);
        }
        return build(inorder,0,inorder.length,postorder,0,postorder.length,map);
    }

    public TreeNode build(int[] inorder,int inStart,int inEnd,int[] postorder,int postStart,int postEnd,HashMap<Integer,Integer> map){
        if(inStart==inEnd||postStart==postEnd){
            return null;
        }
        //根结点为后序遍历的最后一个
        int rootVal=postorder[postEnd-1];
        //创建根结点
        TreeNode root=new TreeNode(rootVal);
        //在中序遍历中找到根结点的下标
        int rootIndex=map.get(root.val);
        //记录右子树的元素个数
        int rightNum=inEnd-rootIndex-1;
        //对左右子树进行还原
        root.left=build(inorder,inStart,rootIndex,postorder,postStart,postEnd-rightNum-1,map);
        root.right=build(inorder,rootIndex+1,inEnd,postorder,postEnd-rightNum-1,postEnd-1,map);
        return root;
    }
```



# LeetCode 107

> [107. 二叉树的层序遍历 II - 力扣（LeetCode）](https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/?envType=problem-list-v2&envId=binary-tree)

## summary

这个就是从底开始的层序遍历 但是我们可以按照层序遍历的方式先去获取某一层

之后在添加到最终答案列表的时候  可以使用头插法来实现类似栈的功能 由此实现逆序

## code

```Java
public List<List<Integer>> levelOrderBottom(TreeNode root) {
        //初始化一个列表存储最终结果
        List<List<Integer>> ans=new LinkedList<>();
        //特判：空树直接返回
        if(root==null){
            return ans;
        }
        //初始化一个队列存储遍历过程的中间数据
        Deque<TreeNode> queue=new LinkedList<>();
        //开始把根结点入队
        queue.offerLast(root);
        //只要队列不为空就说明还存在未遍历到的元素
        while(!queue.isEmpty()){
            //初始化一个列表存储每一层的数据
            List<Integer> list=new LinkedList<>();
            //初始化一个变量保存该层未被遍历的元素个数
            int size=queue.size();
            //初始化一个变量保存出队元素
            TreeNode node=null;
            //循环该层同时如果每个数据左右孩子不为空就把孩子入队
            while(size>0){
                //元素出队
                node=queue.poll();
                //保存该元素对应的值
                list.add(node.val);
                //只要左右孩子存在就入队
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
                //元素出队后更新还未遍历的元素个数
                size--;
            }
            //每次在列表ans中进行头插实现栈的功能
            ans.add(0,list);
        }
        //返回最终结果
        return ans;
    }
```



# LeetCode 108

> [108. 将有序数组转换为二叉搜索树 - 力扣（LeetCode）](https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/?envType=problem-list-v2&envId=binary-tree)

## summary

这个比较容易 整体思路就是一个折半查找对应一棵平衡二叉树 但是需要注意一棵平衡二叉树不一定对应着一个折半查找

## code

```Java
//算法思想：
    //一个折半查找一定对应着一棵平衡二叉树
    //只需要每次将数组中点值作为根结点的val 然后再用同样的方法去递归构建左右子树即可
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums,0,nums.length-1);
    }

    public TreeNode buildBST(int[] nums,int l,int r){
        if(l>r){
            return null;
        }
        int mid=(l+r)/2;
        TreeNode root=new TreeNode(nums[mid]);
        root.left=buildBST(nums,l,mid-1);
        root.right=buildBST(nums,mid+1,r);
        return root;
    }
```

