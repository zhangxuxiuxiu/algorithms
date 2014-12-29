package cn.sspku.zx.aad.leetcode;

/**
 * Divide two integers without using multiplication, division and mod operator.
 * 
 * @author zhangxu
 * 
 */
public class DivideTwoIntegers {
	public int divide(int dividend, int divisor) {
		int op = 1, result = 0;
		// 处理边界
		if (divisor == 0)
			return -1;
		if (divisor == Integer.MIN_VALUE)
			if (dividend == Integer.MIN_VALUE)
				return 1;
			else
				return 0;
		if (dividend == Integer.MIN_VALUE) {
			if (divisor == -1)
				return -1;// 越界
			if (divisor == 1)
				return dividend;
			dividend += Math.abs(divisor);
			result = 1;
		}

		// 决定符号
		if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0)
			op = -1;
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);

		// MAX(2^N/N)=2 when N=1 or N=2 ==>SO 'step' should be 1 or 2.
		// Considering Integer.MAX_VALUE==(1<<31)-1, 'step' should be set to be
		// 2 for the best
		int incr = 1, tpr = divisor, step = 2, upperLimit = 1 << (31 - step);
		// 尽可能让tpr接近dividend
		while (tpr < upperLimit && tpr < dividend) {
			tpr = tpr << step;
			incr = incr << step;
		}

		while (dividend >= divisor) {
			while (tpr > dividend) {
				tpr >>= step;
				incr >>= step;
			}
			dividend -= tpr;
			result += incr;
		}

		return op * result;
	}

	public static void main(String[] args) {
		DivideTwoIntegers dti = new DivideTwoIntegers();
		// int dividend = -1021989372, divisor = -82778243;
		// int dividend = 12, divisor = 1;
		// int dividend = -1169994976, divisor = 158107113;
		// System.out.println("dividend=" + dividend + " divisor=" + divisor);
		int dividend = 1026117192, divisor = -874002063;
		System.out.println(dti.divide(dividend, divisor) + " real result="
				+ dividend / divisor);
	}
}
