package day260408;

import java.util.Arrays;

/**
 *class Solution {
 *     public int numTrees(int n) {
 *
 *     }
 * }
 */
public class LeetCode96 {
    //首先还是从递归入手动态规划
    public int numTrees(int n) {
        if(n==0||n==1){
            //在空树或者只有一个节点的时候只存在唯一建树方案
            return 1;
        }
        int num=0;
        for(int i=1;i<=n;i++){
            //相当于把数组下标为i位置的树作为根节点 然后[1,i-1]做左子树 [i+1,n]做右子树组合起来
            //左边i-1个节点 右边n-i-1个节点
            num+=numTrees(i-1)*numTrees(n-i);
        }
        return num;
    }

    public int numTrees2(int n){
        int[] dp=new int[n+1];
        Arrays.fill(dp,-1);
        return f2(n,dp);
    }

    public int f2(int n,int[] dp){
        if(n==0||n==1){
            return 1;
        }
        if(dp[n]!=-1){
            return dp[n];
        }

        int num=0;
        for(int i=0;i<n;i++){
            num+=f2(n-1,dp)*f2(n-i-1,dp);
        }
        dp[n]=num;
        return num;
    }

    public int numTrees3(int n){
        return f3(n);
    }

    public int f3(int n){
        if(n==0||n==1){
            return 1;
        }
        int[] dp=new int[n+1];
        dp[0]=1;
        for(int i=1;i<=n;i++){
            for(int j=0;j<i;j++){
                //这里i表示总的个数
                //j表示左子树分的个数  n-j-1则表示右子树分的个数
                //左子树最多i-1个
                dp[i]+=dp[j]*dp[i-j-1];
            }
        }

        return dp[n];
    }

    public int numTrees4(int n){
        //卡特兰数公式：C(2n, n) / (n + 1)
        long ans=1;
        for(int i=1;i<=n;i++){
            ans=ans*2*(2*n-1)/(n+1);
        }
        return (int)ans;
    }

}
