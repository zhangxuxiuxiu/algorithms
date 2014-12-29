package cn.sspku.zx.aad.leetcode;

/**
 * Implement pow(x, n).
 * 
 * @author zhangxu
 * 
 */
public class Pow {
	public double pow(double x, int n) {
		if (0 == x)
			return 0;
		if (0 == n || 1 == x)
			return 1;
		if (-1 == x)
			return n % 2 == 0 ? 1 : -1;

		boolean negative = false;
		if (n < 0) {
			n = -n;
		}

		if (!negative)
			return pPow(x, n);
		return 1 / pPow(x, n);
	}

	private double pPow(double x, int n) {
		if (1 == n)
			return x;
		
		double result = pPow(x, n / 2);
		result *= result;
		if (n % 2 == 0)
			return result;

		return x * result;
	}
}
