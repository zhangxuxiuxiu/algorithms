package cn.sspku.zx.aad.chapterOne;

/**
 * Created by zhangxu on 22/4/14.
 *
 * 问题描述：对于a<=x<=b的正整数，找出[a,b]之间约数个数最多的x，并输出其约数个数。
 *
 * 问题分析：brute-force的方法，求出a,b之间所有树的约数个数
 */
public class Qs_1_3 {
    public static void main(String[] args)
    {
        int max=0,a=1,b=36,tpr;

        for(int idx=a;idx<=b;++idx)
        {
            if((tpr=countDividends(idx))>max) max=tpr;
        }

        System.out.println(max);
    }

    /**
     * @param n   the number with whose dividends to be counted
     * @return    the quantity of dividends
     */
    private static int countDividends(int n)
    {
        if(n<1) return 0;

        int occurs=0;
        for(int idx=1;idx<=n;idx++)
        {
            if(n%idx==0) occurs++;
        }

        return occurs;
    }
}
