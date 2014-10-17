package cn.sspku.zx.aad.daily;

/**
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most two
 * transactions.
 * 
 * Note: You may not engage in multiple transactions at the same time (ie, you
 * must sell the stock before you buy again).
 * 
 * @author zhangxu
 * 
 */
public class BestStockSellerIII {
	public int maxProfit(int[] prices) {
		if (null == prices || prices.length < 2)
			return 0;

		int left = 0, right = 0, profit, maxProfit = 0, secProfit = 0;
		while (right + 1 < prices.length) {
			while (right + 1 < prices.length
					&& prices[right] <= prices[right + 1]) {
				++right;
			}
			profit = prices[right] - prices[left];
			if (profit >= maxProfit) {
				secProfit = maxProfit;
				maxProfit = profit;
			} else if (profit > secProfit)
				secProfit = profit;

			left = ++right;
		}

		return maxProfit + secProfit;
	}
}
