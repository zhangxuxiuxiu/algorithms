package cn.sspku.zx.aad.leetcode;

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

		int low1 = -1, high1 = -1, low2 = -1, high2 = -1, start = 0, low = -1, high;
		boolean isLowSaved = false;
		while (start < prices.length) {
			// 确定一个上坡的起点low
			while (start < prices.length - 1
					&& prices[start] >= prices[start + 1])
				++start;
			// this line is not so understandable
			if (isLowSaved) {
				low = Math.min(low, prices[start]);
				isLowSaved = false;
			} else
				low = prices[start];

			System.out.println("low=" + low);
			// 确定一个上坡的终点high
			while (start < prices.length - 1
					&& prices[start] <= prices[start + 1])
				++start;
			high = prices[start];
			System.out.println("high=" + high);

			++start;
			// 如果第一个坡还没有确定下来，就设置为第一个坡
			if (-1 == low1) {
				low1 = low;
				high1 = high;
			} else if (-1 == low2) {
				low2 = low;
				high2 = high;
			} else {
				// 需要在三个坡当中选出两个高度差最高的坡，其中选出的每个坡可以是两个连续的坡的结合
				// 如果后面两个坡连起来比较好，则把后面组合起来
				int _low1 = -1, _high1 = -1, _low2 = -1, _high2 = -1;
				if ((high1 - low2) > (high2 - low)) {
					_low1 = low1;
					_high1 = high1;
					_low2 = low2;
					_high2 = high;
				}
				// 否则把前面组合起来
				else {
					_low1 = low1;
					_high1 = high2;
					_low2 = low;
					_high2 = high;
				}

				if (high1 - low1 + high2 - low2 > _high1 - _low1 + _high2
						- _low2) {
					_low1 = low1;
					_high1 = high1;
					_low2 = low2;
					_high2 = high2;
					isLowSaved = true;// 这里没有用到low这个值，所以后面可以继续用
				}
				if (high1 - low1 + high - low > _high1 - _low1 + _high2 - _low2) {
					_low1 = low1;
					_high1 = high1;
					_low2 = low;
					_high2 = high;
				}
				if (high2 - low2 + high - low > _high1 - _low1 + _high2 - _low2) {
					_low1 = low2;
					_high1 = high2;
					_low2 = low;
					_high2 = high;
				}

				low1 = _low1;
				high1 = _high1;
				low2 = _low2;
				high2 = _high2;
				System.out.println("low1=" + low1 + " high1=" + high1
						+ " low2=" + low2 + " high2=" + high2);
			}
		}

		return high1 - low1 + high2 - low2;
	}

	public static void main(String[] argv) {
		BestStockSellerIII bss = new BestStockSellerIII();
		System.out.println(bss.maxProfit(new int[] { 7, 11, 1, 4, 2 }));
	}
}
