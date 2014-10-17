package cn.sspku.zx.aad.daily;

/**
 * Find the contiguous subarray within an array (containing at least one number)
 * which has the largest product.
 * 
 * For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has
 * the largest product = 6.
 * 
 * @author zhangxu
 * 
 */
public class MaximumProductSubway {
	/**
	 * 分别用positiveVal记录纯正数区间的乘积， firstVal记录非零区间的乘积， secondVal记录非零区间第一个负数之后的乘积
	 * 以此在以上三者之中选择出最大值
	 * 
	 * @param A
	 * @return
	 */
	public int maxProduct(int[] A) {
		if (null == A || A.length == 0)
			return 0;
		if (A.length == 1)
			return A[0];

		int max = Integer.MIN_VALUE, firstVal = 0, secondVal = 0, positiveVal = 0, val;
		boolean afterSecond = false;
		for (int idx = 0; idx < A.length; ++idx) {
			val = A[idx];
			if (val == 0) {
				if (firstVal > max)
					max = firstVal;
				if (secondVal > max)
					max = secondVal;
				if (positiveVal > max)
					max = positiveVal;
				firstVal = secondVal = positiveVal = 0;
				afterSecond = false;
			} else if (val > 0) {
				if (positiveVal == 0)
					positiveVal = val;
				else
					positiveVal *= val;
				if (positiveVal > max)
					max = positiveVal;

				if (firstVal == 0)
					firstVal = val;
				else
					firstVal *= val;
				if (firstVal > max)
					max = firstVal;

				if (afterSecond) {
					if (secondVal == 0)
						secondVal = val;
					else
						secondVal *= val;
					if (secondVal > max)
						max = secondVal;
				}
			} else {

				if (firstVal == 0)
					firstVal = val;
				else
					firstVal *= val;
				if (firstVal > max)
					max = firstVal;

				if (afterSecond) {
					if (secondVal == 0)
						secondVal = val;
					else
						secondVal *= val;
					if (secondVal > max)
						max = secondVal;
				}
				afterSecond = true;

				if (positiveVal > max)
					max = positiveVal;
				positiveVal = 0;
			}
		}

		if (firstVal > max)
			max = firstVal;
		if (secondVal > max)
			max = secondVal;
		if (positiveVal > max)
			max = positiveVal;

		return max;
	}

	public static void main(String[] args) {
		MaximumProductSubway mps = new MaximumProductSubway();
		int[] arr = new int[] { 3, -2, -3, 3, -1, 0, 1 };
		System.out.println(mps.maxProduct(arr));
	}
}
