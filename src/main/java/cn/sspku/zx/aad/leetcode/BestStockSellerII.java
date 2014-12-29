package cn.sspku.zx.aad.leetcode;

/**
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many
 * transactions as you like (ie, buy one and sell one share of the stock
 * multiple times). However, you may not engage in multiple transactions at the
 * same time (ie, you must sell the stock before you buy again).
 * 
 * @author zhangxu
 * 
 */
public class BestStockSellerII {
	public int maxProfit(int[] prices) {
		if(null==prices||prices.length<2) return 0;
		
		int left=0,right=0,maxProfit=0;
		while(right+1<prices.length)
		{
			while(right+1<prices.length&&prices[right]<=prices[right+1])
			{
				++right;
			}
			maxProfit+=prices[right]-prices[left];
			left=++right;
		}
		
		return maxProfit;
	}
}
