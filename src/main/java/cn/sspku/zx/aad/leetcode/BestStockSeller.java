package cn.sspku.zx.aad.leetcode;

/**
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * If you were only permitted to complete at most one transaction (ie, buy one
 * and sell one share of the stock), design an algorithm to find the maximum
 * profit.
 * 
 * @author zhangxu
 * 
 */
public class BestStockSeller {

	public int maxProfit(int[] prices) {
		if(null==prices||prices.length<2) return 0;
		
		int min=prices[0],maxProfit=0;
		for(int idx=1;idx<prices.length;++idx)
		{
			if(prices[idx]<min) min=prices[idx];
			else if(prices[idx]-min>maxProfit) maxProfit=prices[idx]-min;
		}
		
		return maxProfit>0?maxProfit:0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
